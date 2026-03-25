package configs;

import java.time.Duration;

import org.openqa.selenium.MutableCapabilities;

import io.appium.java_client.android.options.UiAutomator2Options;
import utils.Environment;

public class AndroidCapabilities extends Capabilities {
    public AndroidCapabilities(Environment environment) {
        super(environment);
    }

    // use synchronized to ensure that the capabilities are accessed by only one thread at a time
    @Override
    public synchronized MutableCapabilities getCapabilities() {
        UiAutomator2Options capabilities = new UiAutomator2Options()
            .setNewCommandTimeout(Duration.ZERO)
            .setDeviceName(environment.getDeviceName())
            .setPlatformName(environment.getPlatformName())
            .setPlatformVersion(environment.getPlatformVersion())
            .setApp(environment.getApp())
            .setFullReset(environment.isFullReset())
            .setNoReset(environment.isNoReset())
            .setAllowTestPackages(true)
            .autoGrantPermissions()
            // .setAppActivity(environment.getAppActivity())
            // .setAppPackage(environment.getAppPackage())
            // .setAppWaitPackage(environment.getAppPackage())
            .setLocale("en_US");
        
        // capabilities.setCapability("disableIdLocatorAutocompletion", true);
        System.out.println("Capabilities: " + capabilities.toString());

        return capabilities;
    }
}
