package com.nclh.webSteps;

import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.*;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SafetyManagementSteps2 extends AllDriverManager {

    Context context;
    SafetyManagementPage2 safetyManagementPage2;
    SafetyManagementPage safetyManagementPage;
    CommonPage commonPage;
    CommonFunctions commonFunctions;

    public static final Logger logger = LoggerFactory.getLogger(SafetyManagementSteps2.class);

    public SafetyManagementSteps2(Context context) {
        this.context = context;
        safetyManagementPage2 = new SafetyManagementPage2(getDriver()); // matches type
        commonPage = new CommonPage(getDriver());
        commonFunctions = new CommonFunctions();
        safetyManagementPage = new SafetyManagementPage(getDriver());
    }

    @Then("User is able to confirm training {string} data is display")
    public void userIsAbleToConfirmTrainingDataIsDisplay(String str) {
        safetyManagementPage2.userIsAbleToConfirmTrainingDataVisible(str);
    }

    @Then("User able {string} that the crew member is part of should be displayed")
    public void userAbleThatTheCrewMemberIsPartOfShouldBeDisplayed(String str) {
        safetyManagementPage2.userAbleThatTheCrewMemberIsPartOfShouldBeDisplayed(str);
    }

    @Then("User is able to navigate to the {string} page link")
    public void userIsAbleToNavigateToThePageLink(String str) {
        safetyManagementPage2.userIsAbleToNavigateToThePageLink(str);
    }

    @Then("User is able to click on the hidden filters and verify they are displayed")
    public void userIsAbleToClickOnTheHiddenFiltersAndVerifyTheyAreDisplayed() {
        safetyManagementPage2.userIsAbleToClickOnTheHiddenFiltersAndVerifyTheyAreDisplayed();
    }

    @Then("User is able to see and click on the Emergency role Team")
    public void userIsAbleToSeeAndClickOnTheEmergencyRoleTeam() {
        safetyManagementPage2.userIsAbleToSeeAndClickOnTheEmergencyRoleTeam();
    }

    @Then("User is able to view Emergency Role Team in the Safety Build up dropdown list")
    public void userIsAbleToViewEmergencyRoleTeamInTheSafetyBuildUpDropdownList() {
        safetyManagementPage2.userIsAbleToViewEmergencyRoleTeamInTheSafetyBuildUpDropdownList();
    }
    @And("click the cancel Button")
    public void clickTheCancelButton() {
        safetyManagementPage.cancelButton();
    }
    @Then("User checks if the {string} screen is enabled on the crew member page")
    public void userChecksIfTheScreenIsEnabledOnTheCrewMemberPage(String str) {
        safetyManagementPage2.userChecksIfTheActiveModeScreenIsEnabledOnTheCrewMemberPage(str);
        context.setContext("SKIP_SCREENSHOT", "true");
    }
    @Then("User respective Safety Numbers landing page should be displayed")
    public void userRespectiveSafetyNumbersLandingPageShouldBeDisplayed() {
        safetyManagementPage2.userRespectiveSafetyNumbersLandingPageShouldBeDisplayed();
    }
    @And("User selects the required number checkbox under Suggested Unassigned Numbers")
    public void userSelectsTheRequiredNumberCheckboxUnderSuggestedUnassignedNumbers() {
        safetyManagementPage2.userSelectsTheRequiredNumberCheckboxUnderSuggestedUnassignedNumbers();
    }

    @And("Click o the ASSIGN button")
    public void clickOTheASSIGNButton() {
        safetyManagementPage2.clickOTheASSIGNButton();
    }

    @And("User click the confirm Button in crew page")
    public void userClickTheConfirmButtonInCrewPage() {
        safetyManagementPage2.userClickTheConfirmButtonInCrewPage();
    }
}






















