import common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.HomePage;
import ui.LiveTvPage;
import ui.PrivacyPolicyPage;

public class AppleTv extends BaseTest {

    HomePage homePage = new HomePage();
    PrivacyPolicyPage privacyPolicyPage = new PrivacyPolicyPage();
    LiveTvPage liveTvPage = new LiveTvPage();

    @Test(description = "Verify Privacy policy pop-up is displayed", priority = 1)
    public void verifyPrivacyPolicyPopUp(){
        homePage.waitAppToLoad();
        String popUpText = homePage.getPrivacyPopUpText();
        Assert.assertEquals("We’ve updated our terms of use and privacy policy.", popUpText,
                "Pop-up is not displayed");
    }

    @Test(description = "Verify Privacy policy button are displayed", priority = 2)
    public void verifyPrivacyPolicyButtons() {
        homePage.waitAppToLoad();
        Assert.assertEquals("Accept & Continue", homePage.getAcceptAndContinueButtonText(),
                "Accept button is not displayed");
        Assert.assertEquals("Review Update", homePage.getReviewUpdateButtonText(),
                "Review button is not displayed");
    }

    @Test(description = "Verify User is able to move to the Privacy policy page", priority = 3)
    public void verifyPrivacyPolicyPage(){
        homePage.waitAppToLoad();
        homePage.tapOnReviewUpdateButton();
        Assert.assertEquals("Your privacy is important to us.", privacyPolicyPage.getPrivacyPolicyHeading());
    }

    @Test(description = "Verify after sccepting Privacy policy document user lands to the LiveTv page", priority = 4)
    public void verifyUserLandsOnLivePage(){
        privacyPolicyPage.tapOnAcceptButton();
        liveTvPage.waitToLoadTv();
        Assert.assertEquals("Channels",liveTvPage.getLiveTvText(),"LiveTv page is not displayed");
    }
}
