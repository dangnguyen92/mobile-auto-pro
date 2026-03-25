package configs;

import org.openqa.selenium.MutableCapabilities;

import utils.Environment;

public abstract class Capabilities {

    protected final Environment environment;

    public Capabilities(Environment environment) {
        this.environment = environment;
    }

    public abstract MutableCapabilities getCapabilities();
}