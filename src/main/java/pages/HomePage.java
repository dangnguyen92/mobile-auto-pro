package pages;

import org.testng.Assert;
import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import static utils.ElementUtils.*;

public class HomePage extends BasePage {
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"StartTrustWalletButton\")")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'Start Trust Wallet'")
    public WebElement startTrustWalletButton;

    public void verifyHomePage() {
        waitForVisibility(startTrustWalletButton, shortTimeout);
        Assert.assertTrue(isElementPresent(startTrustWalletButton), "Start Trust Wallet button is not present");
    }
}
