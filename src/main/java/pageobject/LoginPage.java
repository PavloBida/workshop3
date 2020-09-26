package pageobject;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class LoginPage {

    @FindBy(xpath = "//*[@name='email' and not(@id)]")
    private WebElement emailInput;
    @FindBy(xpath = "//*[@name='password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//*[text()='Login']")
    private WebElement loginButton;

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Login as email: {0}, password {1}")
    public HomePage loginAs(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        return clickLogin();
    }
    private void enterEmail(String email) {
        log.info("Enter email: " + email);
        emailInput.sendKeys(email);
    }

    private void enterPassword(String password) {
        log.info("Enter password: " + password);
        passwordInput.sendKeys(password);
    }

    private HomePage clickLogin() {
        log.info("Click 'Login' button");
        loginButton.click();
        return PageFactory.initElements(driver, HomePage.class);
    }
}
