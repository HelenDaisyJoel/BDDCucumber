package com.nclh.webSteps;

import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.*;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class CrewInformation2Steps extends AllDriverManager {
    Context context;
    CrewInformation2Page crewInformation2Page;
    CrewInformationPage crewInformationPage;
    GuestServicePage guestServicePage;
    CommonFunctions commonFunctions;
    CommonPage commonPage;
    FinanceManagementPage financeManagementPage;
    ExcursionManagementPage excursionManagementPage;
    GuestServiceRequestPage guestServiceRequestPage;

    public CrewInformation2Steps(Context context) {
        this.context = context;
        commonPage = new CommonPage(getDriver());
        crewInformation2Page = new CrewInformation2Page(getDriver());
        guestServicePage = new GuestServicePage(getDriver());
        crewInformationPage = new CrewInformationPage(getDriver());
        excursionManagementPage = new ExcursionManagementPage(getDriver());
        commonFunctions = new CommonFunctions();
        financeManagementPage = new FinanceManagementPage(getDriver());
        guestServiceRequestPage = new GuestServiceRequestPage(getDriver());
    }

    @Then("User enter document number and click on open eye button")
    public void user_enter_document_number_and_click_on_open_eye_button() {
        crewInformationPage.enterDocumentNr(String.valueOf(excursionManagementPage.getRandomNumber()));
        WebElement element = crewInformationPage.getCommentTextWebElement();
        String textMessage  = commonFunctions.readToastMessage(element);
        context.setContext("firstAndLastNameText", textMessage);
        crewInformation2Page.clickEyeOpenedButton();
    }

    @And("User search for crew and navigated to crew profile page")
    public void userSearchForCrewAndNavigatedToCrewProfilePage() {
        String[] fullName = context.getContext("crewName").split(" ");
        System.out.println("fullName :: "+ Arrays.toString(fullName));
        if(fullName.length > 2){
            guestServicePage.openCrewInformation(fullName[0] + " " + fullName[1]);
        }else{
            guestServicePage.openCrewInformation(fullName[0]);
        }
    }

    @And("User verify the checkout button not available in quick action groups panel")
    public void userVerifyTheCheckoutButtonNotAvailableInQuickActionGroupsPanel() {
        List<String> checkoutButton = crewInformation2Page.getQuickActionGroupListName();
        Assert.assertFalse(checkoutButton.contains("Checkout"));
    }

    @Then("User click on {string} in Check in dashboard")
    public void userClickOnInCheckInDashboard(String link) {
        guestServiceRequestPage.switchToNewWindow();
        commonPage.openLinkUsingHref(link);
    }

    @Then("User search for crew and click on checkbox for cabin allocation")
    public void userSearchForCrewAndClickOnCheckboxForCabinAllocation() {
        String[] fullName = context.getContext("crewName").split(" ");
        System.out.println("fullName :: "+ Arrays.toString(fullName));
        if(fullName.length > 2){
           crewInformation2Page.searchAndSelectCrew(fullName[0] + " " + fullName[1]);
        }else{
            crewInformation2Page.searchAndSelectCrew(fullName[0]);
        }
    }

    @And("User select the crew from select accounts")
    public void userSelectTheCrewFromSelectAccounts() {
        String[] fullName = context.getContext("crewName").split(" ");
        System.out.println("fullName :: "+ Arrays.toString(fullName));
        if(fullName.length > 2){
            crewInformation2Page.selectCrewFromSelectAccounts(fullName[0] + " " + fullName[1]);
        }else{
            crewInformation2Page.selectCrewFromSelectAccounts(fullName[0]);
        }
    }
}
