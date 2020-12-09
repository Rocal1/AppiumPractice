package androidTests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class SwipeDemo extends Base {

    @Test
    public void swipeDemo() throws IOException {
        AndroidDriver driver= seUpCapabilities();
        driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
        driver.findElementByAndroidUIAutomator("text(\"Date Widgets\")").click();
        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"2. Inline\"]").click();
        driver.findElementByXPath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"9\"]").click();

        //Long Press on element + wait 1 second + swipe to another element
        TouchAction t= new TouchAction(driver);
        WebElement minutesFrom= driver.findElementByAccessibilityId("15");
        WebElement minutesTo= driver.findElementByAccessibilityId("45");
        t.longPress(LongPressOptions.longPressOptions().withElement(element(minutesFrom)).withDuration(Duration.ofSeconds(1)))
                .moveTo(element(minutesTo)).release().perform();

    }
}
