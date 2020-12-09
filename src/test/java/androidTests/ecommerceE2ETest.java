package androidTests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class ecommerceE2ETest extends Base{
    AndroidDriver driver= seUpCapabilities();

    public ecommerceE2ETest() throws IOException {
    }

    @Test(priority = 1)
    public void validateToastMessage()  {
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMessage=driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");//name attribute for toast messages will have content
        System.out.println(toastMessage);
        Assert.assertEquals("Please enter your name", toastMessage,"Error message validation");//Actual validation
    }

    @Test(priority = 2)
    public void searchForFemaleProduct(){
        driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Hello");
        driver.hideKeyboard();
        driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();
        driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))").click();
        //OPTIONAL SCROLL
        //driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))"));
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    }

    @Test(priority = 3)
    public void addProductoToCart() throws InterruptedException {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().text(\"Jordan 6 Rings\"))");
        int count=    driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for(int i=0;i<count;i++)
        {
            List<MobileElement> productname = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
            String text = productname.get(i).getText();

            if(text.equalsIgnoreCase("Jordan 6 Rings"))
            {
                List<MobileElement> productCart =driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart"));
                productCart.get(i).click();
                break;
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(2000);
        String lastpageText=driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals("Jordan 6 Rings", lastpageText);
    }

    @Test(priority = 4)
    public void validateAmount(){
        int count=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
        double sum=0;
        for(int i=0;i<count;i++) {
            List<MobileElement> productPrice=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
            String amount1= productPrice.get(i).getText();
            double amount=getAmount(amount1);
            sum=sum+amount;//ADDING ONLY THE JORDAN 6 RINGS SO TOTAL AMOUNT = 116.97
        }

        System.out.println(sum+"sum of products");
        String total=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        total= total.substring(1);
        double totalValue=Double.parseDouble(total);
        System.out.println(totalValue+"Total value of products");
        Assert.assertEquals(sum, totalValue);
    }
    public static double getAmount(String value)
    {
        value= value.substring(1);
        double amount2value=Double.parseDouble(value);
        return amount2value;
    }
    @Test (priority = 5)
    public void completePurchase(){
        //Mobile Gestures
        WebElement checkbox=driver.findElement(By.className("android.widget.CheckBox"));
        //Validate the terms and condition by a long press on the link in the bottom
        TouchAction t=new TouchAction(driver);
        t.tap(tapOptions().withElement(element(checkbox))).perform();
        WebElement tc=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
        t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
    }
    @Test (priority = 6)
    public void switchContext() throws InterruptedException {
        Thread.sleep(7000);
        Set<String> contexts=driver.getContextHandles();
        System.out.println(contexts.size());
        for(String contextName :contexts) {
            System.out.println(contextName);
        }
        driver.context("WEBVIEW_com.androidsample.generalstore");//Switching from native to webview
        //driver.context("WEBVIEW_chrome");
        //driver.findElementByClassName("android.widget.EditText").sendKeys("hello");
        driver.findElement(By.cssSelector("input[name=\"q\"]")).sendKeys("hello");
        driver.findElement(By.cssSelector("input[name=\"q\"]")).sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
    }

}
