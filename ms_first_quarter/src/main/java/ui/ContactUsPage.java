package ui;

import com.milestone.uifactory.UIElement;

public class ContactUsPage {
    UIElement contactUsForm = UIElement.byClassName("contact-form-box");

    public void waitForContactUsForm() {
        contactUsForm.waitFor(5);
    }
}
