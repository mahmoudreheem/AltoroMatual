package TestCases;

import Pages.P01_HomePage;
import RetryAnalyser.MyRetry;

import org.testng.Assert;
import org.testng.annotations.Test;


public class TC01_Home extends TestBase {


    // check login positive scenarios
    @Test(priority = 1, description = "Login with Valid Username and Password", retryAnalyzer = MyRetry.class, timeOut = 20000)
    public void CheckSignInLinkClickable() {
        new P01_HomePage(driver).clickSignInLink();
        Assert.assertTrue(new P01_HomePage(driver).verifyRedirectToLoginPage("Online Banking Login"));
    }

}