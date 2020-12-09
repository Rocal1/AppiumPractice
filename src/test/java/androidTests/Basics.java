package androidTests;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class Basics extends Base {

    @Test
    public void basicsTest() throws IOException {
        AndroidDriver driver= seUpCapabilities();
        driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();

        driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
        driver.findElementById("android:id/checkbox").click();

        //getAndroidDriver().findElementByClassName("(//android.widget.RelativeLayout)[2]").click();
        //driver.findElementsByClassName("android.widget.RelativeLayout").get(2).click();
        driver.findElementByXPath("//android.widget.TextView[@text='WiFi settings']").click();

        driver.findElementById("android:id/edit").sendKeys("asdasd");
        driver.findElementById("android:id/button1").click();
    }
}
