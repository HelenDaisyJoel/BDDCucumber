package com.nclh.webSteps;

import com.nclh.constants.HousekeepingConstants;
import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.CommonPage;
import com.nclh.pageObjects.GuestServicePage;
import com.nclh.pageObjects.HouseKeepingPage;
import com.nclh.pageObjects.ShipSecurityPage;
import com.nclh.utilities.Context;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class HousekeepingSteps extends AllDriverManager {

    HouseKeepingPage houseKeepingPage;
    ShipSecurityPage shipSecurityPage;
    GuestServicePage guestServicePage;
    Context context;
    CommonPage commonPage;

    public HousekeepingSteps(Context context) {
        this.context = context;
        houseKeepingPage = new HouseKeepingPage(getDriver());
        guestServicePage = new GuestServicePage(getDriver());
        shipSecurityPage = new ShipSecurityPage(getDriver());
        commonPage = new CommonPage(getDriver());
    }

    @Then("Verify Guest user details")
    public void verify_guest_user_details() {
        String guestFirstName = houseKeepingPage.getGuestFirstName();
        context.setContext("guestFirstName", guestFirstName);
        System.out.println("guestFirstName :: " + guestFirstName);

    }

    @And("User click on By section")
    public void userClickOnBySection() {
        houseKeepingPage.clickOnBySection();
    }

    @Then("User click on unassigned first section")
    public void userClickOnUnassignedFirstSection() {
        houseKeepingPage.clickOnFirstSection();
    }


    @Then("click the crew for section")
    public void clickTheCrewForSection() {
        houseKeepingPage.checkBoxCrew();
    }

    @Then("click on Assign button")
    public void clickOnAssignButton() {
        houseKeepingPage.clickOnAssignButton();
    }

    @And("User save crew name allocated for section")
    public void userSaveCrewNameAllocatedForSection() {
        String crewName = houseKeepingPage.getCrewNameForSectionAssignment();
        context.setContext("crewName", crewName);
        System.out.println("crewName :: " + crewName);
        String fullCrewName = houseKeepingPage.getCrewNameForSectionAssignment().trim();
        String[] nameParts = fullCrewName.split(" ");
        String firstName = (nameParts.length > 0) ? nameParts[0] : "";
        context.setContext("CrewFirstName", firstName);
        System.out.println("CrewFirstName :: " + firstName);
    }

    @And("save the section name")
    public void saveTheSectionName() {
        String sectionName = houseKeepingPage.getSectionName();
        context.setContext("SectionName", sectionName);
        System.out.println("SectionName :: " + sectionName);
    }

    @And("user search for the {string}")
    public void userSearchForThe(String sectionName) {
        if (sectionName.contains("Dynamic")) {
            String[] fullName = context.getContext("SectionName").split(" ");
            houseKeepingPage.openSectionInformation(fullName[0]);
        }
    }

    @Then("click on assigned crew members")
    public void clickOnAssignedCrewMembers() {
        houseKeepingPage.clickOnAssignedCrewMember();
    }

    @And("User verify assigned crew member")
    public void userVerifyAssignedCrewMember() {
        String crewName = houseKeepingPage.getCrewNameForSectionAssignment();
        Assert.assertEquals(context.getContext("crewName"), crewName);
        System.out.println("same crew is assigned");
    }

    @And("user select the position filter")
    public void userSelectThePositionFilter() {
        houseKeepingPage.setPositionFilter();
    }

    @Then("User select {string} option in the filter")
    public void userSelectOptionInTheFilter(String option) {
        houseKeepingPage.checkOptionOnTheFilter(option);
    }

    @And("User click on Cabin Service")
    public void userClickOnCabinService() {
        houseKeepingPage.clickOnCabinService();
    }

    @Then("click the  assigned crew for section")
    public void clickTheAssignedCrewForSection() {
        houseKeepingPage.checkBoxCrew();
    }

    @And("User search for crew {string}")
    public void userSearchForCrew(String crewFirstName) {
        if (crewFirstName.contains("Dynamic")) {
            String[] fullName = context.getContext("CrewFirstName").split(" ");
            houseKeepingPage.crewSearchByName(fullName[0]);
        }
    }

    @And("User verify unassigned crew member")
    public void userVerifyUnassignedCrewMember() {
        String crewName = houseKeepingPage.getCrewNameForSectionAssignment();
        System.out.println(crewName);
        System.out.println(context.getContext("crewName"));
        Assert.assertEquals(context.getContext("crewName"),crewName);
    }

    @Then("User verify crew table")
    public void userVerifyCrewTable(DataTable dataTable) {
        List<String> dataRow = dataTable.row(0);
        List<String> columnData = new ArrayList<>();
        for (int i = 0; i < dataRow.size(); i++) {
            columnData.add(dataRow.get(i));
        }

        System.out.println(columnData);
        System.out.println(houseKeepingPage.getCrewColumnHeaders());
        Assert.assertEquals(columnData, houseKeepingPage.getCrewColumnHeaders());
    }

    @Then("User verify the {string} section")
    public void userVerifyTheSection(String section) {
        String name =houseKeepingPage.getUnAssignedCrewMember(section);
        int openParenIndex = name.indexOf("(");
        if (openParenIndex != -1) {
            // Remove the unwanted part if it exists
            name = name.substring(0, openParenIndex).trim();
        }
        Assert.assertEquals(name, section);
    }

    @Then("User click Fast Section Assignment")
    public void userClick() {
        houseKeepingPage.clickOnTheSearch();

    }

    @And("user verify the {string} filter")
    public void userVerifyTheFilter(String filter) {
        String positionText=houseKeepingPage.getFilter(filter);
        Assert.assertEquals(filter, positionText);

    }

    @And("User click on By crew member")
    public void userClickOnByCrewMember() {
        houseKeepingPage.clickOnByCrewMember();
    }

    @Then("User click on assigned first crew member")
    public void userClickOnAssignedFirstCrewMember() {
        houseKeepingPage.clickOnFirstCrewMember();
    }

    @And("save the first crew name")
    public void saveTheFirstCrewName() {
        String firstCrewName = houseKeepingPage.getCrewMemberName();
        context.setContext("FirstCrewName", firstCrewName);
        System.out.println("FirstCrewName :: " + firstCrewName);
    }

    @Then("click on assigned section")
    public void clickOnAssignedSection() {
        houseKeepingPage.clickOnAssignedSection();
    }

    @And("User save section name allocated for crew")
    public void userSaveSectionNameAllocatedForCrew() {
        String sectionName = houseKeepingPage.getSectionNameForCrewAssignment();
        context.setContext("SectionName", sectionName);
        System.out.println("SectionName :: " + sectionName);
    }

    @And("User verify assigned crew member for section")
    public void userVerifyAssignedCrewMemberForSection() {
        String crewName = houseKeepingPage.getCrewNameForSectionAssignment();
        Assert.assertEquals(context.getContext("FirstCrewName"), crewName);
        System.out.println("same crew is assigned");
    }

    @Then("click on unassigned section")
    public void clickOnUnassignedSection() {
        houseKeepingPage.clickOnUnAssignedSection();
    }

    @Then("click on unassigned crew members")
    public void clickOnUnassignedCrewMembers() {
        houseKeepingPage.clickOnUnAssignedCrewMember();
    }

    @And("User verify unassigned crew member for section")
    public void userVerifyUnassignedCrewMemberForSection() {
        String expectedCrewName = houseKeepingPage.getCrewNameForSectionAssignment();
        List<String> unassignedCrewNames = houseKeepingPage.getUnassignedCrewNames();
        Assert.assertTrue(unassignedCrewNames.contains(expectedCrewName),
                "The crew name '" + expectedCrewName + "' was not found in the unassigned crew list.");
    }

    @Then("Verify Guest user name")
    public void verifyGuestUserName() {
        String guestNameText = houseKeepingPage.getGuestName();
        String cleanedString = guestNameText.replaceAll("[^a-zA-Z]", "");
        boolean onlyAlphabets = cleanedString.equals(guestNameText.replaceAll("[^a-zA-Z]", ""));
        assertTrue("The guest name contains non-alphabetic characters", onlyAlphabets);
       // String[] fullName = guestNameText.split(" ");
       // System.out.println(fullName);
        //Assert.assertEquals(fullName.length, 2);
    }


    @Then("Verify Guest user country and language")
    public void verifyGuestUserCountryAndLanguage() {
        List<String> countryAndLanguageData =  houseKeepingPage.getCountryAndLanguage();
        for(String text : countryAndLanguageData){
            String cleanedString = text.replaceAll("[^a-zA-Z]", "");
            boolean onlyAlphabets = cleanedString.equals(text.replaceAll("[^a-zA-Z]", ""));
            assertTrue( "The text '" + text + "' contains non-alphabetic characters",onlyAlphabets);
        }
    }

    @Then("verify Guest user Amenities list")
    public void verifyGuestUserAmenitiesList() {
        List<String> amenitiesListData =  houseKeepingPage.getamenitiesList();
        for(String text : amenitiesListData){
            String cleanedString = text.replaceAll("[^a-zA-Z]", "");
            boolean onlyAlphabets = cleanedString.equals(text.replaceAll("[^a-zA-Z]", ""));
            assertTrue( "The text '" + text + "' contains non-alphabetic characters",onlyAlphabets);
        }
    }

    @Then("verify Guest user diningInfo List")
    public void verifyGuestUserDiningInfoList() {
        List<String> getdiningInfoListData =  houseKeepingPage.getdiningInfoList();
        for(String text : getdiningInfoListData){
            String cleanedString = text.replaceAll("[^a-zA-Z]", "");
            boolean onlyAlphabets = cleanedString.equals(text.replaceAll("[^a-zA-Z]", ""));
            assertTrue( "The text '" + text + "' contains non-alphabetic characters",onlyAlphabets);
        }
    }


    @And("User is able to view guest name")
    public void userIsAbleToViewGuestName() {
        Assert.assertTrue(houseKeepingPage.getOperation());
    }

    @And("User is able to view loyalty Tire")
    public void userIsAbleToViewLoyaltyTire() {
        Assert.assertTrue(houseKeepingPage.getLoyalty());
    }

    @And("User is able to view Guest Image")
    public void userIsAbleToViewGuestImage() {
        Assert.assertTrue(houseKeepingPage.getImage());
    }

    @And("User save section allocated for crew")
    public void userSaveSectionAllocatedForCrew() {
        String section = houseKeepingPage.getSectionForCrewNameAssignment();
        context.setContext("SectionName", section);
        System.out.println("SectionName :: " + section);
    }

    @And("User search  crew {string}")
    public void userSearchCrew(String arg0) {
        String[] fullName = context.getContext("FirstCrewName").split(" ");
        System.out.println(fullName[0]);
        houseKeepingPage.crewSearchName(fullName[0]);
    }

    @And("User click on By {string}")
    public void userClickOnBy(String option) {
        houseKeepingPage.clickOnBySelect(option);
    }

    @And("User verify unassigned crewMember")
    public void userVerifyUnassignedCrewmember() {
        String crewName = houseKeepingPage.getCrewNameForSectionAssignment();
        System.out.println(crewName);
        System.out.println(context.getContext("FirstCrewName"));
        Assert.assertEquals(context.getContext("FirstCrewName"),crewName);
    }

    @And("User click on public location")
    public void userClickOnPublicLocation() {
        houseKeepingPage.clickOnPublicLocation();
    }

    @And("User verify that data is visible")
    public void userVerifyThatDataIsVisible() {
        Assert.assertTrue(houseKeepingPage.verifyDataIsVisible());
    }

    @Then("User click on one Section")
    public void userClickOnOneSection() {
        houseKeepingPage.clickOnSectionOne();
    }

    @And("save the section text")
    public void saveTheSectionText() {
        String sectionText1 = houseKeepingPage.getSectionOneText();
        context.setContext("SectionText1", sectionText1);
        System.out.println("SectionText1 :: " + sectionText1);
        String sectionText2 = houseKeepingPage.getSectionTextTwo();
        context.setContext("SectionText2", sectionText2);
        System.out.println("SectionText2 :: " + sectionText2);
    }

    @Then("User click on two Section")
    public void userClickOnTwoSection() {
        houseKeepingPage.clickOnSectionOne();
        houseKeepingPage.clickOnSectionTwo();
    }

    @And("user search for  {string}")
    public void userSearchFor(String crewName) {
        if (crewName.contains("Dynamic")) {
            String[] fullName = context.getContext("FirstCrewName").split(" ");
            houseKeepingPage.openSectionInformation(fullName[0]);
        }
    }

    @Then("verify the user is able to assign multiples sections to the crew member under Cabin service")
    public void verifyTheUserIsAbleToAssignMultiplesSectionsToTheCrewMemberUnderCabinService() {
        List<String> statusList = houseKeepingPage.getSectionListOfAll();
        System.out.println(statusList);
        String sectionText1 = context.getContext("SectionText1");
        String sectionText2 = context.getContext("SectionText2");
        List<String> sectionTexts = new ArrayList<>();

        sectionTexts.add(sectionText1);
        sectionTexts.add(sectionText2);

        System.out.println("SectionTexts :: " + sectionTexts);
        Assert.assertTrue(statusList.containsAll(sectionTexts));
    }

    @Then("click multiple crew for section")
    public void clickMultipleCrewForSection() {
        houseKeepingPage.checkBoxMultipleCrew();
    }

    @And("User save crew names allocated for section")
    public void userSaveCrewNamesAllocatedForSection() {
        String crewName1 = houseKeepingPage.getCrewNameForSectionAssignment();
        context.setContext("crewName1", crewName1);
        System.out.println("crewName1 :: " + crewName1);
        String crewName2 = houseKeepingPage.getCrewNameForSectionTwoAssignment();
        context.setContext("crewName2", crewName2);
        System.out.println("crewName2 :: " + crewName2);
    }

    @Then("verify the user is able to assign multiples  crew member  to the sections under Public Location Service")
    public void verifyTheUserIsAbleToAssignMultiplesCrewMemberToTheSectionsUnderPublicLocationService() {
        List<String> crewList = houseKeepingPage.getCrewListOfAll();
        System.out.println(crewList);
        String sectionText1 = context.getContext("crewName1");
        String sectionText2 = context.getContext("crewName2");
        List<String> crewTexts = new ArrayList<>();

        crewTexts.add(sectionText1);
        crewTexts.add(sectionText2);

        System.out.println("CrewNameTexts :: " + crewTexts);
        Assert.assertTrue(crewList.containsAll(crewTexts));
    }

    @And("User open module {string} in reporting overview")
    public void userOpenModuleInReportingOverview(String moduleName) {
        houseKeepingPage.clickOnModule(moduleName);
    }

    @And("User click on report overview")
    public void userClickOnReportOverview() {
        houseKeepingPage.clickOnReportOverView();
    }

    @And("User Verify unassigned crew")
    public void userVerifyUnassignedCrew() {
        String crewName = houseKeepingPage.getCrewNameForSectionAssignment();
        System.out.println(crewName);
        System.out.println(context.getContext("FirstCrewName"));
        Assert.assertEquals(context.getContext("FirstCrewName"),crewName);
    }

    @Then("click the  assigned  section")
    public void clickTheAssignedSection() {
        houseKeepingPage.checkBoxCrew();
    }

    @And("User verify that reassign of section is done")
    public void userVerifyThatReassignOfSectionIsDone() {
        String sectionName = houseKeepingPage.getSectionNameText();
        Assert.assertEquals(context.getContext("SectionName"),sectionName);

    }

    @And("User save  second guest name")
    public void userSaveSecondGuestName() {
        String guestName = houseKeepingPage.getGuestNameTwo();
        context.setContext("guestName", guestName);
        System.out.println("guestName :: " + guestName);
    }

    @And("User select {string} filter as {string}")
    public void userSelectFilterAs(String filter, String option) {
        houseKeepingPage.selectCleaningStatusFilter(filter,option);
    }

    @And("User click on status filter")
    public void userClickOnStatusFilter() {
        houseKeepingPage.clickOnStatusFilter();
    }

    @And("Verify the Status list")
    public void verifyTheStatusList() {
        List<String> statusList = houseKeepingPage.getStatusList();
        List<String> actualList = Arrays.asList(HousekeepingConstants.inProgress,HousekeepingConstants.newFilter,HousekeepingConstants.dnd,HousekeepingConstants.failed,HousekeepingConstants.completed,HousekeepingConstants.notRequired,HousekeepingConstants.inspected);
        System.out.println(statusList);
        Assert.assertEquals(statusList, actualList);

    }

    @And("User get the cabin number and occupancy status")
    public void userGetTheCabinNumberAndOccupancyStatus() {
        String presentCabinNumber = guestServicePage.getCabinNumberOnGuestPage();
        System.out.println(presentCabinNumber);
        String occupancyStatus = houseKeepingPage.getOccupancyStatus();
        System.out.println(occupancyStatus);

    }
}
