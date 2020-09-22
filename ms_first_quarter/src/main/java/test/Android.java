package test;

import com.milestone.uifactory.UIElement;
import common.BaseTest;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.AccountPage;
import ui.ContactUsPage;
import ui.HomePage;
import ui.LoginPage;

import java.util.Properties;

public class Android extends BaseTest {
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    ContactUsPage contactUsPage = new ContactUsPage();
    AccountPage accountPage = new AccountPage();

    @Test(description = "Verify user is able to login", priority = 0)
    public void loginAndroid(){
        try {
            if (homePage.isSignOutDisplayed())
                homePage.signOut();
        } catch (Exception e) {}
        homePage.selectMenuItem("Dresses");
        loginPage.tapOnSignInButton();
        loginPage.enterCredentials();
        loginPage.tapOnSubmitButton();
        Assert.assertEquals("Onkar Bawa", homePage.getProfileName());
//        homePage.signOut();
    }

    @Test()
    public void verifyCheckoutFunctionality(){

    }

    @Test(description = "Verify click on menu tabs will move to its section", dataProvider = "menuTabs", priority = 1, enabled = false)
    public void verifyMenuTabs(String menuTabs) {
        homePage.selectMenuItem(menuTabs);
        Assert.assertTrue(UIElement.getTitle().contains(menuTabs));
        loginPage.tapOnHomeButton();
    }

    @Test(description = "Verify click on contact us button user will move to the contact us section", priority = 2)
    public void verifyContactUsButton() {
        loginPage.tapOnContactUsButton();
        contactUsPage.waitForContactUsForm();
        Assert.assertTrue(UIElement.getTitle().contains("Contact us"));
        loginPage.tapOnHomeButton();
    }

    @Test(description = "Verify that after click on profile button user move to the My Account Page", priority = 4)
    public void verifyProfileButton(){
        homePage.goToProfilePage();
        Assert.assertEquals(UIElement.getTitle(), "My account - My Store");
        System.setProperty("myAccountURL",UIElement.getURL());
    }

    @Test(description = "Verify click on My Account order history button user should move to the Order history page", priority = 5)
    public void verifyOrderHistoryButton() {
        accountPage.goToOrderHistoryPage();
        Assert.assertEquals(UIElement.getTitle(), "Order history - My Store");
        UIElement.navigateToURL(System.getProperty("myAccountURL"));
//        accountPage.navigateBackToMyAccountPage();
    }

    @Test(description = "Verify click on Credit Slips button user should move to the Credit Slips page", priority = 6)
    public void verifyCreditSlipButton() {
        accountPage.goToCreditSlipsPage();
        Assert.assertEquals(UIElement.getTitle(), "Order slip - My Store");
        UIElement.navigateToURL(System.getProperty("myAccountURL"));
    }

    @Test(description = "Verify click on my addresses button user should move to the My Addresses page", priority = 7)
    public void verifyMyAddressesButton() {
        accountPage.goToMyAddressPage();
        Assert.assertEquals(UIElement.getTitle(), "Addresses - My Store");
        UIElement.navigateToURL(System.getProperty("myAccountURL"));
    }

    @Test(description = "Verify click on personal info button user should move to the personal info page", priority = 8)
    public void verifyPersonalInfoButton() {
        accountPage.goToPersonalInfoPage();
        Assert.assertEquals(UIElement.getTitle(), "Identity - My Store");
    }

    @DataProvider(name = "menuTabs")
    public Object [][] getMenuTabs(){
        return new Object[][]
                {
                        {"Women"},
                        {"Dresses"},
                        {"T-shirts"}
                };
    }
}
