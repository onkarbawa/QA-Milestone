package ui;

import com.milestone.uifactory.UIElement;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HomePage {
    UIElement signOutBtn = UIElement.byXpath("//a[@title='Log me out']");
    UIElement profileName = UIElement.byXpath("//a[@title='View my customer account']");
    UIElement menuItems = UIElement.byXpath("//ul[@class='sf-menu clearfix menu-content']/li/a");
    UIElement categories = UIElement.byClassName("cat-title");

    public void signOut() {
        signOutBtn.getElement().click();
    }

    public boolean isSignOutDisplayed() {
        signOutBtn.waitFor(3);
        return signOutBtn.getElement().isDisplayed();
    }

    public String getProfileName() {
        signOutBtn.waitFor(5);
        return profileName.getElement().getText();
    }

    public void goToProfilePage() {
        profileName.waitFor(4);
        profileName.getElement().click();
    }

    public void selectMenuItem(String attribute) {
        categories.waitFor(4);
        categories.getElement().click();
        System.out.println(menuItems.getElements().size());
        Optional<WebElement> element = menuItems.getElements().stream().filter(el ->
                el.getAttribute("title").equalsIgnoreCase(attribute)).findFirst();
        element.get().click();
        UIElement.waitForTitle(8, attribute);
    }
}
