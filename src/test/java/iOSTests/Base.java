package iOSTests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Base {
    private DesiredCapabilities cap;
    private Properties prop;

    public IOSDriver setCapabilities() throws IOException {
        File resourcesPath= new File("src/main/resources/"); //Resources project Path
        File app= new File(resourcesPath,"UICatalog.app"); //APP IOS
         System.out.println("SETTING CAPABILITIES");
        cap= new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.1");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        cap.setCapability(MobileCapabilityType.UDID, "04EF2A20-0873-4012-A6F1-E86E239F6749");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,14); //(OPTIONAL)
        cap.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
        IOSDriver driver= new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
        return driver;
    }

    public String getPropertyFile(String keyProperty) throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/resources/capabilities.properties");
        prop.load(fis);
        return prop.getProperty(keyProperty);
    }
}
