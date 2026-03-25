package utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;

@Configuration
@PropertySource("classpath:application.properties")
public class Environment {
    @Getter
    @Value("${device.name}")
    String deviceName;

    @Getter
    @Value("${platform.name}")
    String platformName;

    @Getter
    @Value("${platform.version}")
    String platformVersion;

    @Getter
    @Value("${full.reset:true}")
    boolean fullReset;

    @Getter
    @Value("${no.reset:false}")
    boolean noReset;

    @Value("${app}")
    String app;

    @Getter
    @Value("${app.package}")
    String appPackage;

    @Getter
    @Value("${appium.server.url}")
    String appiumServerUrl;

    @Getter
    @Value("${bundle.id}")
    String bundleId;

    @Getter
    @Value("${udid}")
    String udid;

    public String getApp() {
        return new File("src/test/resources/" + app).getAbsolutePath();
    }
}
