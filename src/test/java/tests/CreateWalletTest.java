package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.WalletPage;

public class CreateWalletTest extends BaseTest {
    @Test
    public void testCreateWallet() {
        WalletPage walletPage = new WalletPage();
        HomePage homePage = new HomePage();

        walletPage.clickGetStartedButton();
        walletPage.clickCreateNewWalletButton();
        walletPage.clickTrySecretPhraseButton();
        walletPage.clickSkipButton();
        walletPage.enterPasscode();
        homePage.verifyHomePage();
    }
}
