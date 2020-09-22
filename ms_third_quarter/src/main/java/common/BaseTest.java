package common;

import com.milestone.appfactory.AppStore;
import com.milestone.uifactory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {

    WebDriver driver;
    @BeforeTest
    @Parameters(value = "app")
    public void setUp(String appName){
        AppStore.setAppName(appName);
        driver = DriverFactory.getDriver();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
