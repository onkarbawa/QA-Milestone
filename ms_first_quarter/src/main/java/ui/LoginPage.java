package ui;

import com.milestone.appfactory.AppStore;
import com.milestone.uifactory.UIElement;

public class LoginPage {
    UIElement email = UIElement.byId("email");
    UIElement password = UIElement.byId("passwd");
    UIElement btnSubmit = UIElement.byXpath("//i[@class='icon-lock left']");
    UIElement btnSignIn = UIElement.byXpath("//a[@title='Log in to your customer account']");
    UIElement btnHome = UIElement.byClassName("icon-home");
    UIElement btnContactUs = UIElement.byXpath("//a[@title='Contact Us']");

    public void tapOnSignInButton(){
        btnSignIn.waitFor(5);
        btnSignIn.getElement().click();
    }

    public void tapOnSubmitButton() {
        btnSubmit.getElement().click();
    }
    public void enterCredentials() {
        email.getElement().sendKeys("bawa_onkar@yahoo.com");
        password.getElement().sendKeys("testing");
    }

    public void tapOnHomeButton() {
        btnHome.getElement().click();
    }

    public void tapOnContactUsButton() {
        btnContactUs.getElement().click();
    }
}
