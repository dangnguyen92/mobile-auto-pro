package utils;

import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import configs.AndroidCapabilities;
import configs.Capabilities;
import configs.IOSCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import static utils.ElementUtils.defaultTimeout;
@Component
public abstract class AUT extends AbstractTestNGSpringContextTests {
    protected Capabilities capabilities;

    @Autowired
    protected AppiumService appiumService;

    public void setUpDriver(URL appiumUrl, MutableCapabilities capabilities) {
        if (getEnvironment().getPlatformName().equalsIgnoreCase("android")) {
            DriverManager.setDriver(new AndroidDriver(appiumUrl, capabilities));
        } else if (getEnvironment().getPlatformName().equalsIgnoreCase("ios")) {
            DriverManager.setDriver(new IOSDriver(appiumUrl, capabilities));
        } else {
            throw new IllegalStateException("Setup driver failed for platform name: " + getEnvironment().getPlatformName());
        }
        DriverManager.getDriver().manage().timeouts().implicitlyWait(defaultTimeout);
    }

    public Capabilities setupCapabilities(Environment environment) {
        if (capabilities == null) {
            capabilities = initCapabilities(environment);
        }
        return capabilities;
    }

    public void tearDownDriver() {
        try {
            DriverManager.tearDownDriver();
        } catch (Exception e) {
            throw new RuntimeException("Failed to tear down driver", e);
        }
    }

    public MutableCapabilities getCapabilities() {
        return capabilities.getCapabilities();
    }

    public Capabilities initCapabilities(Environment environment) {
        if (environment.getPlatformName().equalsIgnoreCase("android")) {
            return new AndroidCapabilities(environment);
        } else if (environment.getPlatformName().equalsIgnoreCase("ios")) {
            return new IOSCapabilities(environment);
        } else {
            throw new IllegalArgumentException("Invalid platform name: " + environment.getPlatformName());
        }
    }

    @SneakyThrows
    public URL getAppiumUrl() {
        // If the appium server url is set
        if (getEnvironment().getAppiumServerUrl() != null && !getEnvironment().getAppiumServerUrl().isEmpty()) {
            return new URL(getEnvironment().getAppiumServerUrl());
        }

        // Starts local Appium automatically when appium.server.url is empty
        if (!isAppiumServerRunning()) {
            appiumService.start();
        }

        return appiumService.getServiceUrl();
    }

    public void stopAppiumServer() {
        try {
            if (isAppiumServerRunning()) {
                appiumService.stop();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to stop appium server", e);
        }
    }

    public boolean isAppiumServerRunning() {
        return appiumService.isServiceRunning();
    }

    public Environment getEnvironment() {
        return AppContext.getEnv();
    }

    public TestData getTestData() {
        return AppContext.getTestData();
    }
}
