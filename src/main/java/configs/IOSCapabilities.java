package configs;

import java.time.Duration;

import org.openqa.selenium.MutableCapabilities;

import io.appium.java_client.ios.options.XCUITestOptions;
import utils.Environment;

public class IOSCapabilities extends Capabilities {
    private static final Duration WDA_TIMEOUT = Duration.ofSeconds(240);

    public IOSCapabilities(Environment environment) {
        super(environment);
    }

    // use synchronized to ensure that the capabilities are accessed by only one thread at a time
    @Override
    public synchronized MutableCapabilities getCapabilities() {
        XCUITestOptions capabilities = new XCUITestOptions()
            .setNewCommandTimeout(Duration.ZERO)
            .setUdid(environment.getUdid())
            .setPlatformName(environment.getPlatformName())
            .setPlatformVersion(environment.getPlatformVersion())
            .setApp(environment.getApp())
            .setFullReset(environment.isFullReset())
            .setNoReset(environment.isNoReset())
            .setBundleId(environment.getBundleId())
            .setUseNewWDA(true)
            .setWdaLaunchTimeout(WDA_TIMEOUT)
            .setWdaConnectionTimeout(WDA_TIMEOUT)
            .setAutoAcceptAlerts(true)
            .setMaxTypingFrequency(30) // to avoid typing too fast
            .showXcodeLog()
            .setLocale("en_US");

        System.out.println("Capabilities: " + capabilities.toString());

        return capabilities;
    }
}
