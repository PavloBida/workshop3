package pageobject;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Slf4j
public class HomePage {

    @FindBy(xpath = "//button[contains(text(), 'View Website')]")
    private List<WebElement> viewWebsiteButtons;

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Verify if 'View Website' button is displayed")
    public boolean isViewWebsiteButtonDisplayed() {
        log.info("Verify if 'View Website' button is displayed");
        return viewWebsiteButtons.size() > 0;
    }

}

