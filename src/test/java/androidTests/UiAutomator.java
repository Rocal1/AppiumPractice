package androidTests;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class UiAutomator extends Base {

    @Test
    public void uiAutomator() throws IOException {
        AndroidDriver driver= seUpCapabilities();
        driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
        driver.findElementByAndroidUIAutomator("text(\"Animation\")").click();

        System.out.println(driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)").size());

    }
}
