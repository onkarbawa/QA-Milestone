package ui;

import com.milestone.uifactory.UIElement;

public class AccountPage {
    UIElement orderHistory = UIElement.byXpath("//a[@title='Orders']");
    UIElement creditSlips = UIElement.byXpath("//a[@title='Credit slips']");
    UIElement myAddress = UIElement.byXpath("//a[@title='Addresses']");
    UIElement personalInfo = UIElement.byXpath("//a[@title='Information']");
    UIElement backToMyAccountPageButton = UIElement.byClassName("btn btn-default button button-small");

    public void goToOrderHistoryPage() {
        orderHistory.waitFor(4);
        orderHistory.getElement().click();
    }

    public void goToCreditSlipsPage() {
        creditSlips.waitFor(3);
        creditSlips.getElement().click();
    }
    public void goToMyAddressPage() {
        myAddress.waitFor(3);
        myAddress.getElement().click();
    }
    public void goToPersonalInfoPage() {
        personalInfo.waitFor(3);
        personalInfo.getElement().click();
    }
    public void navigateBackToMyAccountPage() {

        backToMyAccountPageButton.getElement().click();
        orderHistory.waitFor(4);
    }
}
