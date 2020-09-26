package tests;

import core.WebDriverFactory;
import enums.BrowserEnum;
import enums.EnvironmentEnum;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageobject.LoginPage;

import java.util.concurrent.TimeUnit;

@Slf4j
public class BaseTest {

    public static final String URL = "https://www.phptravels.net/admin/";

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeClass
    @Parameters({"browser", "browserVersion", "environment"})
    public void setup(BrowserEnum browser, String browserVersion, EnvironmentEnum environment) {
        driver = WebDriverFactory.getDriver(browser, browserVersion, environment);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        log.info("Open page: " + URL);
        driver.get(URL);

        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
