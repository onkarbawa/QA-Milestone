package ui;

import com.milestone.uifactory.UIElement;

public class PrivacyPolicyPage {

    UIElement privacyPolicy = UIElement.byAccessibilityID("Your privacy is important to us.");
    UIElement acceptAndContinue = UIElement.byAccessibilityID("Accept & Continue");

    public String getPrivacyPolicyHeading() {
        privacyPolicy.waitFor(8);
        return privacyPolicy.getElement().getText();
    }

    public void tapOnAcceptButton() {
        acceptAndContinue.getElement().click();
    }

}
