package androidTests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class Gestures extends Base {

    @Test
    public void gestures() throws IOException {

        AndroidDriver driver= seUpCapabilities();
        driver.findElementByAndroidUIAutomator("text(\"Views\")").click();

        WebElement expandableList = driver.findElementByAndroidUIAutomator("text(\"Expandable Lists\")");
        TouchAction t= new TouchAction(driver);
        t.tap(tapOptions().withElement(element(expandableList))).perform();

        WebElement adapter = driver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']");
        t.tap(tapOptions().withElement(element(adapter))).perform();

        WebElement peopleNames = driver.findElementByXPath("//android.widget.TextView[@text='People Names']");
        t.longPress(longPressOptions().withElement(element(peopleNames)).withDuration(ofSeconds(2))).release().perform();

        System.out.println(driver.findElementById("android:id/title").isDisplayed());
    }
}
