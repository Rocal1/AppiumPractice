package androidTests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.annotations.Test;

import java.io.IOException;

public class Miscelleanous extends Base {

    @Test
    public void miscelleanous() throws IOException {
        AndroidDriver driver= seUpCapabilities();
        System.out.println(driver.currentActivity());
        System.out.println(driver.getContext());
        System.out.println(driver.getOrientation());
        System.out.println(driver.isDeviceLocked());
        driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

    }
}
