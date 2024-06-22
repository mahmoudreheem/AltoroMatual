package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Pages.PageBase.shortWait;
import static org.junit.Assert.fail;

public class P01_HomePage {

    WebDriver driver;

    public P01_HomePage(WebDriver driver) {

        this.driver = driver;
    }

    private final By SIGN_IN_LINK = By.xpath("//font[normalize-space()='Sign In']");

    public P01_HomePage clickSignInLink() {
        try {
            shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(SIGN_IN_LINK));
        } catch (TimeoutException exception) {
            fail("Element not found");
        }

        driver.findElement(this.SIGN_IN_LINK).click();
        return this;
    }

    // verification method
    public boolean verifyRedirectToLoginPage(String pageTitle){
        return driver.findElement(By.xpath("//h1[normalize-space()='Online Banking Login']")).getText().equals(pageTitle);
    }

}
