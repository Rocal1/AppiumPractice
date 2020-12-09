package androidTests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    private AndroidDriver driver;
    private DesiredCapabilities cap;
    private Properties prop;

    /** BEFORE LAUNCH A TEST DRIVER/SESSION (BY CAPABILITIES) REMEMBER FIRST:
     1. OPEN THE EMULATOR
     2. START APPIUM SERVER **/
    public AndroidDriver seUpCapabilities() throws IOException {
        appiumServer().start();

        File resourcesPath= new File("src/main/resources/"); //Resources project Path
        File app= new File(resourcesPath,"ApiDemos-debug.apk"); //Native APK Path
        //File app= new File(resourcesPath,"General-Store.apk"); //Hybrid APK Path

        System.out.println("SETTING CAPABILITIES");
        cap= new DesiredCapabilities();
        System.out.println("Device mode: "+ getPropertyFile("DEVICE_NAME"));
        if(getPropertyFile("DEVICE_NAME").equals("EMULATOR")){
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "AndroidEmulator");
            cap.setCapability(AndroidMobileCapabilityType.AVD,"AndroidEmulator");
            cap.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT,"8000");
        }
        else if (getPropertyFile("DEVICE_NAME").equals("REAL_DEVICE")){
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        }

        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,14); //(OPTIONAL)
        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
//        cap.setCapability("appActivity", "com.android.calculator2.Calculator");
//        cap.setCapability("appPackage", "com.android.calculator2");
        System.out.println(cap);
        driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public String getPropertyFile(String keyProperty) throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/resources/capabilities.properties");
        prop.load(fis);
        return prop.getProperty(keyProperty);
    }
    @AfterTest
    public void tearDown(){
        System.out.println("TEARING DOWN TEST");
        driver.quit();
        appiumServer().stop();
    }

    public AppiumDriverLocalService appiumServer(){
        AppiumDriverLocalService service=AppiumDriverLocalService.buildDefaultService();
        return service;
    }

}
