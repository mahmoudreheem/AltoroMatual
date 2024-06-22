package TestCases;

import Pages.P02_LoginPage;
import Pages.PageBase;
import RetryAnalyser.MyRetry;
import Util.Utility;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC02_Login extends TestBase {

    // define test data
    String userNameFromJsonFile ;
    String passwordFromJsonFile ;

    // check login positive scenarios
    @Test(priority = 1, description = "Login with Valid Username and Password", retryAnalyzer = MyRetry.class, timeOut = 20000)
    public void loginWithValidData_P() throws IOException, ParseException {
        userNameFromJsonFile = Utility.getSingleJsonData(System.getProperty("user.dir")+"/src/test/resources/test_data/loginmultibledata.json","username");
        passwordFromJsonFile = Utility.getSingleJsonData(System.getProperty("user.dir")+"/src/test/resources/test_data/loginmultibledata.json","password");

        new P02_LoginPage(driver).inputUsername(userNameFromJsonFile).inputPassword(passwordFromJsonFile).clickLoginButton();
        // capture screenshot after login
        PageBase.captureScreenshot(driver,"LoginSuccessfully");
        // assert login successfully
        Assert.assertTrue(new P02_LoginPage(driver).verifyLoginSuccessfully("Hello Admin User"));
    }

}