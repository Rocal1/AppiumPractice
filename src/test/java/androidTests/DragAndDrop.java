package androidTests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class DragAndDrop extends Base {

    @Test
    public void dragAndDrop() throws IOException, InterruptedException {
        AndroidDriver driver= seUpCapabilities();

        driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
        driver.findElementByAndroidUIAutomator("text(\"Drag and Drop\")").click();

        WebElement source= (WebElement) driver.findElementsByClassName("android.view.View").get(0);
        WebElement destination= (WebElement) driver.findElementsByClassName("android.view.View").get(1);
        TouchAction t = new TouchAction(driver);
//        t.longPress(LongPressOptions.longPressOptions().withDuration(Duration.ofSeconds(2)).withElement(element(source)))
//                .moveTo(element(destination)).release().perform();
        //FOLLOWING LINE IS AN OPTIONAL OF ABOVE WITHOUT DURATION
        t.longPress(element(source)).moveTo(element(destination)).release().perform();
    }
}
