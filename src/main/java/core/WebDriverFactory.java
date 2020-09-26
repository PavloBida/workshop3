package core;

import enums.BrowserEnum;
import enums.EnvironmentEnum;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public final class WebDriverFactory {

    public static final String USERNAME = "pavlobida1";
    public static final String AUTOMATE_KEY = "rzs4sVp5nXMqD3awzcdc";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    private static WebDriver driver;
    private static DesiredCapabilities caps;

    private WebDriverFactory() {}

    public static WebDriver getDriver(BrowserEnum browser,
                                      String browserVersion,
                                      EnvironmentEnum environment) {
        log.info(
                String.format("Initializing WebDriver for browser: %s, version: %s on environment: %s",
                        browser.name(), browserVersion, environment.name())
        );
        setProperties();
        if (environment.equals(EnvironmentEnum.LOCAL)) {
            return getLocalDriver(browser);
        } else if (environment.equals(EnvironmentEnum.REMOTE)) {
            setCaps(browser, browserVersion);
            return getRemoteDriver();
        } else {
            log.error("Unsupported environment: " + environment);
            throw new RuntimeException();
        }
    }

    private static WebDriver getLocalDriver(BrowserEnum browser) {
        switch (browser) {
            case CHROME: return new ChromeDriver();
            case FIREFOX: return new FirefoxDriver();
            default:throw new RuntimeException("Browser not supported: " + browser);
        }
    }

    private static WebDriver getRemoteDriver() {
        java.net.URL url = null;
        try {
            url = new URL(URL);
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
        }
        return new RemoteWebDriver(url, caps);
    }

    private static void setProperties() {
        String driversFolderWin = "src\\test\\resources\\drivers\\";
        String driversFolderUnix = "src/test/resources/drivers/";

        String os = System.getProperty("os.name");
        String arch = System.getProperty("os.arch");

        if(os.toLowerCase().contains("win")) {
            if(arch.contains("64")) {
                System.setProperty("webdriver.chrome.driver", driversFolderWin + "win\\64\\chromedriver.exe");
                System.setProperty("webdriver.gecko.driver", driversFolderWin + "win\\64\\geckodriver.exe");
            } else {
                System.setProperty("webdriver.chrome.driver", driversFolderWin + "win\\86\\chromedriver.exe");
                System.setProperty("webdriver.gecko.driver", driversFolderWin + "win\\86\\geckodriver.exe");
            }
        } else if (os.toLowerCase().contains("nux") || os.toLowerCase().contains("nix")) {
            if(arch.contains("64")) {
                System.setProperty("webdriver.chrome.driver", driversFolderUnix + "linux/64/chromedriver");
                System.setProperty("webdriver.gecko.driver", driversFolderUnix + "linux/64/geckodriver");
            } else {
                System.setProperty("webdriver.chrome.driver", driversFolderUnix + "linux/86/chromedriver");
                System.setProperty("webdriver.gecko.driver", driversFolderUnix + "linux/86/geckodriver");
            }
        } else if (os.toLowerCase().contains("mac") ) {
            System.setProperty("webdriver.chrome.driver", driversFolderUnix + "macos/64/chromedriver");
            System.setProperty("webdriver.gecko.driver", driversFolderUnix + "macos/64/geckodriver");

        } else {
            throw new RuntimeException("Unsupported OS: " + os);
        }
    }

    private static void setCaps(BrowserEnum browserEnum, String browserVersion) {
        caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browserName", browserEnum.name().toLowerCase());
        caps.setCapability("browser_version", browserVersion);
        caps.setCapability("name", "pavlobida1's First Test");
    }


}
