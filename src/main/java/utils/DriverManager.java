package utils;

import io.appium.java_client.AppiumDriver;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(AppiumDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static void tearDownDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
