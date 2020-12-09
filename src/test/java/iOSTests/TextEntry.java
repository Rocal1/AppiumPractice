package iOSTests;

import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class TextEntry extends Base{

    @Test
    public void test1() throws IOException {
        IOSDriver driver= setCapabilities();

        driver.findElementByAccessibilityId("Alert Views").click();
        driver.findElementByAccessibilityId("Text Entry").click();
        driver.findElementByClassName("XCUIElementTypeTextField").sendKeys("Hello");
        driver.findElementByName("OK").click();
    }
}
