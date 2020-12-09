package androidTests;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;

import java.io.IOException;

public class ScrollingDemo extends Base {

    @Test
    public void scrollingDemo() throws IOException {

        AndroidDriver driver= seUpCapabilities();
        driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
        //Add click If you want to click on the element yo scrollInto
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))").click();

    }
}
