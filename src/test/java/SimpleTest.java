import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class SimpleTest {

    public static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.10.0/TheApp-v1.10.0.apk";
    public static final String APPIUM = "http://localhost:4723/wd/hub";

    private AndroidDriver driver;

    @Before
    public void setUp() throws  Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion","9");
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("automationName","UiAutomator2");
        capabilities.setCapability("app",APP);
        driver = new AndroidDriver(new URL(APPIUM),capabilities);

    }

    @After
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }

    @Test
    public void test(){
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement screen =  wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("Login Screen")));
        screen.click();
        WebElement userName = wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("username")));
        userName.sendKeys("alice");
        WebElement password =  driver.findElement(MobileBy.AccessibilityId("password"));
        password.sendKeys("mypassword");
        WebElement login =  driver.findElement(MobileBy.AccessibilityId("loginBtn"));
        login.click();

        WebElement loginText = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        MobileBy.xpath("//android.widget.TextView[contains(@text,'You are logged in')]")));
        assert(loginText.getText().contains("alice"));
    }
}
