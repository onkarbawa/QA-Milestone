package com.milestone.uifactory;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class UIElement {
    By locator = null;

    public UIElement(By locator) {
        this.locator = locator;
    }

    public WebElement getElement() {
        WebDriver driver = DriverFactory.getDriver();
        return driver.findElement(locator);
    }

    public List<WebElement> getElements() {
        return DriverFactory.getDriver().findElements(locator);
    }

    public static UIElement byClassName(String locator) {
        return new UIElement(By.className(locator));
    }

    public static UIElement byId(String locator) {
        return new UIElement(By.id(locator));
    }

    public static UIElement byXpath(String locator) {
        return new UIElement(By.xpath(locator));
    }

    public static UIElement byAccessibilityID(String locator) {
        return new UIElement(MobileBy.AccessibilityId(locator));
    }

    public static UIElement byIOSClassChain(String locator) {
        return new UIElement(MobileBy.iOSClassChain(locator));
    }

    public static void navigateToURL(String URL) {
        DriverFactory.getDriver().get(URL);
    }

    public static String getURL() {
        return DriverFactory.getDriver().getCurrentUrl();
    }

    public static String getTitle() {
       return DriverFactory.getDriver().getTitle();
    }

    public static void waitForTitle(int time, String titleContains) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), time);
        wait.until(ExpectedConditions.titleContains(titleContains));
    }

    public UIElement waitFor(int timeout) {
        long startTime= System.currentTimeMillis();
        do {
            try {
                if(getElement().isDisplayed()) break;;
            } catch (Exception e) {
                continue;
            }

        } while ((System.currentTimeMillis()- startTime) < timeout * 1000);

        return this;
    }
}
