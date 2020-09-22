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
    @Parameters(value = "browser")
    public void setUp(String appName){
        AppStore.setAppName(appName);
        driver = DriverFactory.getDriver();
        driver.get("http://automationpractice.com");
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
