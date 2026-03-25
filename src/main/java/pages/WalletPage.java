package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class WalletPage extends BasePage {
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"GetStartedButton\")")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'Get Started'")
    public WebElement getStartedButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"CreateNewWallet\")")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'Create a new wallet'")
    public WebElement createNewWalletButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"secretPhraseCreateButton\")")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Secret phrase']/following-sibling::XCUIElementTypeButton[@name='Create']")
    public WebElement trySecretPhraseButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"BackupSkipButton\")")
    @iOSXCUITFindBy(iOSNsPredicate = "name == 'SKIP'")
    public WebElement skipButton;

    public void clickGetStartedButton() {
        click(getStartedButton);
    }

    public void clickCreateNewWalletButton() {
        click(createNewWalletButton);
    }

    public void clickTrySecretPhraseButton() {
        click(trySecretPhraseButton);
    }

    public void clickSkipButton() {
        click(skipButton);
    }

    public By generateNumpadElement(String number) {
        if (getEnvironment().getPlatformName().equalsIgnoreCase("android")) {
            return AppiumBy.androidUIAutomator("new UiSelector().text(\"" + number + "\")");
        } else {
            return AppiumBy.iOSNsPredicateString("name == \"" + number + "\" AND type == \"XCUIElementTypeKey\"");
        }
    }

    public void enterPasscode() {
        enterPasscode(getTestData().getPasscode());
        enterPasscode(getTestData().getPasscode());
    }

    public void enterPasscode(String passcode) {
        String[] passcodeArray = passcode.split("");
        for (int i = 0; i < passcode.length(); i++) {
            if (i == 0) {
                // We await numpad before clicking on it
                click(getElement(generateNumpadElement(passcodeArray[i]), longTimeout));
            } else {
                // Since we already interacted with numpad, it will be displayed, we can click without wait
                getElement(generateNumpadElement(passcodeArray[i])).click();
            }
        }
    }
}
