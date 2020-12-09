package iOSTests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

public class ScrollDown extends Base{

    private IOSDriver driver;

    @Test
    public void scrollDown() throws IOException {
        driver = setCapabilities();

        MobileElement option= (MobileElement) driver.findElementByAccessibilityId("Steppers");
        scrollDownToElement(option);
        option.click();
        driver.findElementByAccessibilityId("Increment").click();
        driver.findElementByAccessibilityId("Increment").click();
        List<MobileElement> incrementValue = driver.findElementsByClassName("XCUIElementTypeStaticText");
        System.out.println("DEFAULT VALUE: " + incrementValue.get(0).getText());
        System.out.println("TINTED VALUE: " + incrementValue.get(1).getText());
        System.out.println("CUSTOM VALUE: " + incrementValue.get(2).getText());
        //driver.navigate().back();

    }

    public void scrollDownToElement(MobileElement element) {
        Dimension size = driver.manage().window().getSize();
        // calculate coordinates for vertical swipe
        int startVerticalY = (int) (size.height * 0.8);
        int endVerticalY = (int) (size.height * 0.21);
        int startVerticalX = (int) (size.width / 2.1);
        int i=0;
        TouchAction action = new TouchAction(driver);
        do {
            action.press(point(startVerticalX, startVerticalY))
                        .waitAction(waitOptions(ofSeconds(1))).moveTo(point(startVerticalX, endVerticalY))
                        .release().perform();
            i++;
        }
        while(element.isDisplayed()==false && i<5);
    }

    public void scrollUpToElement(MobileElement element) {
        Dimension size = driver.manage().window().getSize();
        // calculate coordinates for vertical swipe
        int startVerticalY = (int) (size.height * 0.8);
        int endVerticalY = (int) (size.height * 0.21);
        int startVerticalX = (int) (size.width / 2.1);
        int i=0;
        TouchAction action = new TouchAction(driver);
        do {
            action.press(point(startVerticalX, endVerticalY))
                    .waitAction(waitOptions(ofSeconds(1))).moveTo(point(startVerticalX, startVerticalY))
                    .release().perform();
            i++;
        }
        while(element.isDisplayed()==false && i<5);
    }


}
