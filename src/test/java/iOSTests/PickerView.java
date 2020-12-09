package iOSTests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class PickerView extends Base{
    private IOSDriver driver;

    @Test
    public void pickerView() throws IOException {
        driver = setCapabilities();
        driver.findElementByAccessibilityId("Picker View").click();
        driver.findElementByXPath("//*[@name='Green color component value']").sendKeys("220");
        driver.findElementByXPath("//*[@name='Red color component value']").sendKeys("55");
        driver.findElementByXPath("//*[@name='Blue color component value']").sendKeys("130");

    }
}
