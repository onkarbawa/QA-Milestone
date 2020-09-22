package ui;

import com.milestone.uifactory.UIElement;

public class LiveTvPage {
    UIElement livePage = UIElement.byAccessibilityID("Channels");

    public void waitToLoadTv() {
        livePage.waitFor(12);
    }

    public String getLiveTvText() {
        return livePage.getElement().getText();
    }
}
