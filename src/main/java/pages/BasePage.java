package pages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AppContext;
import utils.ElementUtils;
import utils.Environment;
import utils.TestData;

import static utils.DriverManager.getDriver;

public class BasePage implements ElementUtils {
    public BasePage() {
        // use AppiumFieldDecorator to initialize the elements
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public TestData getTestData() {
        return AppContext.getTestData();
    }

    public Environment getEnvironment() {
        return AppContext.getEnv();
    }
}
