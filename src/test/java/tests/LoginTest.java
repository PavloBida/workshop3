package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.HomePage;

public class LoginTest extends BaseTest {

    private String email = "admin@phptravels.com";
    private String password = "demoadmin";
    private HomePage homePage;

    @Test
    @Description("This test case verifies login functionality")
    @Severity(SeverityLevel.BLOCKER)
    @Epic("Login")
    @TmsLink("https://google.com")
    @Issue("https://google.com")
    public void test() {
        homePage = loginPage.loginAs(email, password);

        Assert.assertTrue(homePage.isViewWebsiteButtonDisplayed(),
                "Login is not successful");
    }

}
