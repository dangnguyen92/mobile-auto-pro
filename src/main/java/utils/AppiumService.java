package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.SneakyThrows;

import java.net.URL;

@Service
public class AppiumService {
    private static final Logger logger = LoggerFactory.getLogger(AppiumService.class);

    private AppiumDriverLocalService server;

    @SneakyThrows
    public void start() {
        logger.debug("Starting the Appium server");
        if (server == null || !server.isRunning()) {
            AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
            serviceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "info"); // Set log level to info
            serviceBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
            serviceBuilder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/");
            serviceBuilder.withIPAddress("127.0.0.1");
            serviceBuilder.usingAnyFreePort();
            serviceBuilder.withArgument(GeneralServerFlag.RELAXED_SECURITY);

            server = AppiumDriverLocalService.buildService(serviceBuilder);
            // Check if running in a GitHub Actions environment
            // if (System.getenv("GITHUB_ACTIONS") != null) {
            //     server.removeOutPutStream(System.out);
            // }
            server.start();
            logger.debug("Finished starting the Appium server");
        } else {
            logger.warn("The Appium server is already running");
        }
    }

    public void stop() {
        logger.debug("Stop the Appium server");
        server.stop();
        logger.debug("Finished stopping the Appium server");
    }

    public URL getServiceUrl() {
        if (server == null || !server.isRunning()) {
            throw new RuntimeException("Appium server is not running");
        }

        URL url = server.getUrl();
        logger.debug("The Appium server URL is: " + url.toString());
        return url;
    }

    public boolean isServiceRunning() {
        return server != null && server.isRunning();
    }
}
