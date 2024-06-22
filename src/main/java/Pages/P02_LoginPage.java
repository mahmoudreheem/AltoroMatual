package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Pages.PageBase.shortWait;
import static org.junit.Assert.fail;

public class P02_LoginPage {

    WebDriver driver;

    public P02_LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    private final By USERNAME_TEXT = By.xpath("//input[@id='uid']");
    private final By PASSWORD_TEXT = By.xpath("//input[@id='passw']");
    private final By LOGIN_BUTTON = By.xpath("//input[@name='btnSubmit']");

    public P02_LoginPage inputUsername(String username) {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(USERNAME_TEXT));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }

        driver.findElement(this.USERNAME_TEXT).sendKeys(username);
        return this;
    }
    public P02_LoginPage inputPassword(String password) {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_TEXT));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }

        driver.findElement(this.PASSWORD_TEXT).sendKeys(password);
        return this;
    }
    public P02_LoginPage clickLoginButton() {
        try {
            shortWait(driver).until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }

        driver.findElement(this.USERNAME_TEXT).click();
        return this;
    }

    // verification
    public boolean verifyLoginSuccessfully(String profileTitle){
        return driver.findElement(By.xpath("//h1[normalize-space()='Hello Admin User']")).getText().contains(profileTitle);
    }

}
