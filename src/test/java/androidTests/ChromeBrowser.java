package androidTests;

import java.io.IOException;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ChromeBrowser extends BaseChrome{

        @Test
        public void mobileBrowser() throws IOException, InterruptedException {
            AndroidDriver driver= capabilities();
            driver.get("http://facebook.com");
            //driver.findElementById("com.android.chrome:id/m_login_email").sendKeys("qwerty");
            driver.findElementByXPath("//android.widget.EditText[@resource-id=\"m_login_email\"]").sendKeys("qwerty");
            driver.findElementByXPath("//android.widget.EditText[@resource-id=\"m_login_password\"]").sendKeys("12345");
            driver.findElementByXPath("//android.widget.Button[@text=\"Log In\"]").click();

            //IF adb devices is Unauthorized try the following:
            //adb kill-server
            //adb start-server
            //adb devices


            driver.get("http://cricbuzz.com");
            driver.findElementByXPath("//a[@href='#menu']").click();
            driver.findElementByCssSelector("a[title='Cricbuzz Home']").click();
            System.out.println(driver.getCurrentUrl());
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0,480)", "");
            Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Top Stories']")).getAttribute("class").contains("header"));


        }

    }
