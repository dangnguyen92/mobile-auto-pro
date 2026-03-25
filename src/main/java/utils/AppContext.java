package utils;

import org.springframework.stereotype.Component;

@Component
public final class AppContext {
    // use volatile to ensure that the instance can be accessed by multiple threads
    private static volatile AppContext instance;

    private final Environment environment;
    private final TestData testData;

    public AppContext(Environment environment, TestData testData) {
        this.environment = environment;
        this.testData = testData;
        instance = this;
    }

    public static Environment getEnv() {
        return getInstance().environment;
    }

    public static TestData getTestData() {
        return getInstance().testData;
    }

    private static AppContext getInstance() {
        if (instance == null) {
            throw new IllegalStateException(
                    "AppContext not initialized. Ensure Spring context is loaded before accessing configuration.");
        }
        return instance;
    }
}
