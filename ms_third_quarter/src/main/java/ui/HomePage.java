package ui;

import com.milestone.uifactory.UIElement;

public class HomePage {
    UIElement popUpText = UIElement.byAccessibilityID("We’ve updated our terms of use and privacy policy.");
    UIElement reviewUpdate = UIElement.byAccessibilityID("Review Update");
    UIElement acceptAndContinue = UIElement.byXpath("//XCUIElementTypeStaticText[@name='Accept & Continue']");

    public void waitAppToLoad() {
        popUpText.waitFor(12);
    }

    public String getPrivacyPopUpText() {
        return popUpText.getElement().getText();
    }

    public void tapOnReviewUpdateButton() {
        reviewUpdate.getElement().click();
    }

    public String getReviewUpdateButtonText() {
        return reviewUpdate.getElement().getText();
    }

    public String getAcceptAndContinueButtonText() {
        return acceptAndContinue.getElement().getText();
    }
}
