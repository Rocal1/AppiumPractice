package iOSTests;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class SafariBrowser extends BaseSafari{
    private IOSDriver driver;

    @Test
    public void openSafari() throws IOException {
        driver= setCapabilities();
        driver.findElementByName("Email").sendKeys("rahul");
        driver.findElementByName("Passwd").sendKeys("rahul");
        driver.findElementByName("signIn").click();
    }
}
