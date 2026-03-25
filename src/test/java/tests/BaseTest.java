package tests;

import org.testng.annotations.BeforeMethod;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import utils.AUT;
import utils.AppContext;
import utils.AppiumService;
import utils.Environment;
import utils.TestData;

@SpringBootTest
@ContextConfiguration(classes = {Environment.class, TestData.class, AppContext.class, AppiumService.class})
// Base Test class that extends AUT that extends AbstractTestNGSpringContextTests that belongs to testNG so that @ComponentScan here might not work as expected
public class BaseTest extends AUT {
    @BeforeMethod
    public void setUp() {
        setupCapabilities(getEnvironment());
        setUpDriver(getAppiumUrl(), getCapabilities());
    }

    @AfterMethod
    public void tearDown() {
        tearDownDriver();
        stopAppiumServer();
    }
}
