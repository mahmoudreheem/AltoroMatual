package TestCases;

import Drivers.DriverFactory;
import Drivers.DriverHolder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.time.Duration;


@Listeners(listeners.Listener.class)
public class TestBase {
 protected static WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void setupDriver(@Optional("chrome") String browser) throws Exception {
// start recording
        // MyScreenRecorder.startRecording("sprint1");
        driver = DriverFactory.getNewInstance(browser);
        DriverHolder.setDriver(driver);
        //set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7000));

        driver.get("http://demo.testfire.net/index.jsp");
    }

    @AfterTest
    public void tearDown() {

        driver.quit();
      //Thread.currentThread().interrupt();
    }
}