package iOSTests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSafari {
    private DesiredCapabilities capabilities;
    private Properties prop;
    private IOSDriver driver;

    public IOSDriver setCapabilities() throws IOException {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.1");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://gmail.com");
        return driver;
    }

}
