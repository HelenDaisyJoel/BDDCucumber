package com.nclh.webSteps;

import com.nclh.constants.*;
import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.*;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CrewInformationSteps extends AllDriverManager {

    Context context;
    CrewInformationPage crewInformationPage;
    GuestServicePage guestServicePage;
    CommonFunctions commonFunctions;
    CommonPage commonPage;
    FinanceManagementPage financeManagementPage;
    ExcursionManagementPage excursionManagementPage;
    public static final Logger logger
            = LoggerFactory.getLogger(SeleniumActions.class);
    HotelMaintenancePage hotelMaintenancePage;
    ShipSecurityPage shipSecurityPage;
    GuestServiceRequestPage guestServiceRequestPage;
    CommonSteps commonSteps;
    GroupManagementPage groupManagementPage;
    HomePage homePage;
    SafetyManagementPage safetyManagementPage;
    SeleniumActions seleniumActions;
    SeleniumWaits seleniumWaits;

    public CrewInformationSteps(Context context) {
        this.context = context;
        commonPage = new CommonPage(getDriver());
        guestServicePage = new GuestServicePage(getDriver());
        crewInformationPage = new CrewInformationPage(getDriver());
        excursionManagementPage = new ExcursionManagementPage(getDriver());
        commonFunctions = new CommonFunctions();
        financeManagementPage = new FinanceManagementPage(getDriver());
        hotelMaintenancePage = new HotelMaintenancePage(getDriver());
        shipSecurityPage = new ShipSecurityPage(getDriver());
        commonSteps = new CommonSteps(context);
        groupManagementPage = new GroupManagementPage(getDriver());
        homePage = new HomePage(getDriver());
        safetyManagementPage = new SafetyManagementPage(getDriver());
        seleniumActions = new SeleniumActions(getDriver());
        seleniumWaits = new SeleniumWaits(getDriver());
    }

    @When("Verify crew details")
    public void verifyCrewDetails(DataTable dataTable) {
        SoftAssert softAssert = new SoftAssert();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> dataMap : dataRow) {
            String name = dataMap.get("Name");
            String country = dataMap.get("Country");
            String language = dataMap.get("Language");

            String[] splitName = crewInformationPage.getCrewName().split(" ");
            String actualName = splitName[1] + " " + splitName[2];
            softAssert.assertEquals(actualName, name);
            softAssert.assertEquals(crewInformationPage.getCountryAndLanguage(), Arrays.asList(country, language));
            System.out.println("row header name " + name);
            System.out.println("row index 1 --- " + dataMap.get("Country"));
            System.out.println("row index 2 --- " + dataMap.get("Language"));

            softAssert.assertAll();
            //softAssert.assertEquals(crewInformationPage.getStatus(), status_check.addAll(status_onboard));
        }
    }

    @And("User clear existing filters of embarkation date")
    public void userClearExistingFiltersOfEmbarkationDate() {
        crewInformationPage.clearFilters();
    }

    @And("User save crew name")
    public void userSaveCrewName() {
        String crewName = crewInformationPage.getCrewNameForCabinAssignment();
        context.setContext("crewName", crewName);
        System.out.println("crewName :: " + crewName);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User select cabin for crew")
    public void userSelectCabinForCrew() {
        String cabinNumber = context.getContext("cabinNumber");
        crewInformationPage.selectCabinForCrew(cabinNumber);
    }

    @And("User select crew for cabin allocation")
    public void userSelectCrewForCabinAllocation() {
        String crewName = context.getContext("crewName");
        crewInformationPage.selectCrewForCabinAssign(crewName);
    }

    @And("User save cabin number")
    public void userSaveCabinNumber() {
        String cabinNumber = crewInformationPage.getCabinNumber();
        context.setContext("cabinNumber", cabinNumber);
        System.out.println("cabinNumber :: " + cabinNumber);
    }

    @And("User search for crew and navigated to {string} profile page")
    public void userSearchForCrewAndNavigatedToProfilePage(String crewFirstName) {

        if (crewFirstName.contains("Dynamic")) {
            String[] fullName = context.getContext("crewName").split(" ");
            System.out.println("fullName :: " + Arrays.toString(fullName));
            if (fullName.length > 2) {
                guestServicePage.openCrewInformation(fullName[0] + " " + fullName[1]);
            } else {
                guestServicePage.openCrewInformation(fullName[1]);
            }

        }
    }

    @Then("User verify cabin number assigned to crew member on crew information page")
    public void userVerifyCabinNumberAssignedCabinNumberOnCrewInformationPage() {
        String cabinNumber = crewInformationPage.getCabinNumberOnCrewInformationPage();
        Assert.assertEquals(context.getContext("cabinNumber"), cabinNumber);
    }

    @And("User clear reservation status of the crew")
    public void userClearReservationStatusOfTheCrew() {
        crewInformationPage.clearReservationStatus();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User change status of assign to {string}")
    public void userChangeStatusOfAssignTo(String status) {
        crewInformationPage.changeAssignedStatus(status);
    }

    @And("User unAssign cabin for crew member")
    public void userUnAssignCabinForCrewMember() {
        crewInformationPage.unAssignCabinForCrew();
    }

    @Then("User verify cabin number having {string} on crew information page")
    public void userVerifyCabinNumberHavingOnCrewInformationPage(String status) {
        String cabinNumber = crewInformationPage.getCabinNumberOnCrewInformationPage();
        Assert.assertEquals(cabinNumber, status);

    }

    @And("User save {string} crews count on crew service")
    public void userSaveCrewsCountOnCrewService(String checkedInOnboardOrAshore) {

        String crewCount = crewInformationPage.getCrewCount(checkedInOnboardOrAshore);

        switch (checkedInOnboardOrAshore) {
            case "Checked In Ashore":
                System.out.println("Crew count at Ashore :: " + crewCount);
                context.setContext("CrewServiceCrewsAtCheckedInAshoreCount", crewCount);
                break;
            case "Checked In Onboard":
                System.out.println("Crew count at Onboard :: " + crewCount);
                context.setContext("CrewServiceCrewsAtCheckedInOnboardCount", crewCount);
                break;
        }
    }

    @And("Click on three dots and select Bulk Operation")
    public void clickOnThreeDotsAndSelectBulkOperation() {
        crewInformationPage.bulkOperation();
    }

    @And("User click on Next button")
    public void userClickOnNextButton() {
        crewInformationPage.clickNextButton();
    }

    @When("User select radio button {string}")
    public void userSelectRadioButton(String radio) {
        crewInformationPage.selectRadioButton(radio);
    }

    @And("User click on {string} checkbox")
    public void userClickOnCheckbox(String checkBox) {
        crewInformationPage.clickOnCheckBox(checkBox);
        String operationName = financeManagementPage.getSelectedOperationCheckBoxName();
        System.out.println(operationName);
        context.setContext("Operation", operationName);
    }

    @And("User click on Confirm button")
    public void userClickOnConfirmButton() {
        crewInformationPage.clickOnConfirmButton();
    }

    @When("User click on menu button")
    public void user_click_on_menu_button() {
        crewInformationPage.threeDotsIconButton();
    }

    @When("User select crew and click on first name")
    public void userSelectCrewAndClickOnFirstName() {
        crewInformationPage.selectCrewFirstName();
    }

    @Then("User verify the crew profile picture")
    public void user_verify_the_crew_profile_picture() {
        Assert.assertTrue(crewInformationPage.crewProfilePicture(), "Take a photo");
    }


    @Then("Unselect all crew member and select {int} crew members")
    public void unselectAllCrewMemberAndSelectCrewMembers(int userCount) {
        crewInformationPage.selectRandomRelatedCrew(userCount);
    }

    @Then("User validate the {int} and disembarkation status")
    public void userValidateTheAndDisembarkationStatus(int userCount) {
        guestServicePage.refreshPage();
        int successCount;
        successCount = Integer.parseInt(crewInformationPage.validateSuccessCount(userCount));
        Assert.assertEquals(userCount, successCount);
        Assert.assertEquals(crewInformationPage.validateStatus(), "Done");
    }

    @Then("User set {string} date for Disembarkation")
    public void userSetDateForDisembarkation(String futureDate) {
        String date = null;

        switch (futureDate) {
            case "futureDate":
                date = commonFunctions.getDateIncrementedBy(1, "M/d/yyyy");
                context.setContext("futureDate", date);
                crewInformationPage.updateDate(date);
                break;
            case "pastDate":
                date = commonFunctions.getDateIncrementedBy(-30, "M/d/yyyy");
                context.setContext("pastDate", date);
                crewInformationPage.updateDate(date);
                break;

            case "currentDate":
                date = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
                context.setContext("currentDate", date);
                crewInformationPage.updateDate(date);
                break;

            default:
                throw new IllegalArgumentException("Invalid date type: " + futureDate);
        }
    }

    @And("User click on Update person reservation's disembarkation date")
    public void userClickOnUpdatePersonReservationSDisembarkationDate() {
        crewInformationPage.updatePersonReservation();
    }

    @And("User Validate the update person reservation's {string} details")
    public void userValidateTheUpdatePersonReservationSDetails(String data) {
        switch (data) {
            case "Status":
                Assert.assertEquals(crewInformationPage.getDataOfStatus(data), "SUCCESSFUL");
                break;
            case "Disembarkation":
                Assert.assertEquals(crewInformationPage.getDataOfTheSpecificColumnData(data), commonFunctions.getDateIncrementedBy(1, "yyyy-MM-dd"));
                break;
        }
    }

    @And("User click on {string} in menu button")
    public void userClickOnInMenuButton(String name) {
        crewInformationPage.clickOnStart(name);
    }

    @Then("User verify error message on bulk operation {string}")
    public void userVerifyErrorMessageOnBulkOperation(String reason) {
        String errorText = crewInformationPage.getErrorMessageOnBulkOperation();
        System.out.println("errorText :: " + errorText);
        Assert.assertTrue(errorText.contains(reason));
    }

    @Then("User verify able to check out expected status crew member")
    public void userVerifyAbleToCheckOutExpectedStatusCrewMember() {
        Assert.assertTrue(crewInformationPage.getUserStatus());
    }

    @Then("User save crew first name")
    public void userSaveCrewFirstName() {
        String crewFirstName = crewInformationPage.getCrewFirstName();
        context.setContext("crewFirstName", crewFirstName);
        logger.info("crewFirstName :: " + crewFirstName);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User save crew full name on crew page")
    public void userSaveCrewFullNameOnCrewPage() {
        List<String> crewFullName = crewInformationPage.getcrewFullName();
        String lastName = crewFullName.get(0);
        String firstName = crewFullName.get(1);
        String fullName = firstName + " " + lastName;
        context.setContext("crewFullName", fullName);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User click {string} navigate to profile page")
    public void userClickNavigateToProfilePage(String crewFirstName) {
        if (crewFirstName.contains("Dynamic")) {
            crewInformationPage.clickNavigateToCrewInformation(context.getContext("crewFirstName"));
        } else {
            crewInformationPage.openCrewDetails(crewFirstName);
        }
    }

    @Then("User verify crew details on profile page")
    public void userVerifyCrewDetailsOnProfilePage() {
        String crewName = crewInformationPage.getFullName().replace("Mr.", "");
        Assert.assertEquals(crewName.trim(), context.getContext("crewFullName"));
        Assert.assertTrue(crewInformationPage.getOperation());
    }

    @And("User click on add more filters")
    public void userClickOnAddMoreFilters() {
        crewInformationPage.addMoreFilters();
    }

    @Then("User search {string} filters")
    public void userSearchFilters(String filter) {
        crewInformationPage.searchFilters(filter);
    }

    @And("User set balance {string} min and {string} max range")
    public void userSetBalanceMinAndMaxRange(String min, String max) {
        crewInformationPage.setBalance(min, max);
    }

    @And("User click on {string} filter and select option to {string}")
    public void userClickOnFilterAndSelectOptionTo(String filters, String checkbox) {
        crewInformationPage.selectDropDown(filters, checkbox);
    }

    @Then("User verify the following operations details")
    public void userVerifyTheFollowingOperationsDetails(DataTable dataTable) {
        List<String> expectedSubheadingTexts = dataTable.asList();
        List<String> actualSubheadingTexts = crewInformationPage.getSubheadingTexts();
        Assert.assertEquals(expectedSubheadingTexts, actualSubheadingTexts);
        actualSubheadingTexts.forEach(System.out::println);
    }

    @Then("User verify the operation data and verify the size {string}")
    public void userVerifyTheOperationDataAndVerifyTheSize(String expectedSizeString) {
        int expectedSize = Integer.parseInt(expectedSizeString);
        int actualSize = crewInformationPage.getSubheadingDataCount();
        Assert.assertEquals(expectedSize, actualSize);
        List<String> subheadingDataTexts = crewInformationPage.getSubheadingDataTexts();
        subheadingDataTexts.forEach(System.out::println);
    }

    @And("User click {string} on Personal Information")
    public void userClickOnPersonalInformation(String item) {
        commonPage.clickOnLabel(item);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User enables {string} for crew")
    public void userEnablesForCrew(String toggleName) {
        guestServicePage.enableToggle(toggleName);
    }

    @And("User open crew {string} information details")
    public void userOpenCrewInformationDetails(String submenu) {
        guestServicePage.openSubMenu(submenu);
    }

    @Then("User verify the ashore-denied toggle button is off")
    public void userVerifyTheAshoreDeniedToggleButtonIsOff() {
        String ashoreDeniedStatus = crewInformationPage.ashoreDeniedToggleButtonStatus();
        Assert.assertEquals(ashoreDeniedStatus, "false");
    }

    @And("User type the Quantity as {string}")
    public void userTypeTheQuantityAs(String number) {
        crewInformationPage.setQuantity(number);
    }

    @Then("User verify error message {string}")
    public void userVerifyErrorMessage(String reason) {
        String errorText = crewInformationPage.getErrorMessageOnPersonalItems();
        System.out.println("errorText :: " + errorText);
        Assert.assertTrue(errorText.contains(reason));
    }

    @And("Click on three dots and select click on {string} option")
    public void clickOnThreeDotsAndSelectClickOnOption(String option) {
        crewInformationPage.selectActionOption(option);
    }

    @And("User verify the status as {string}")
    public void userVerifyTheStatusAs(String status) {
        Assert.assertEquals(crewInformationPage.getStatusText(), status);
    }

    @And("User enable toggle {string} on account statement")
    public void userEnableToggleOnAccountStatement(String toggleName) {
        crewInformationPage.enablePostingStatusToggle(toggleName, "Text");
    }

    @Then("User verify the posting status toggle button on in account statement")
    public void userVerifyThePostingStatusToggleButtonOnInAccountStatement() {
        String togglePostingStatus = crewInformationPage.postingStatusToggleButtonStatus();
        Assert.assertEquals(togglePostingStatus, "true");
    }

    @Then("User validate the {int} and disembarkation status on reservation bulk log")
    public void userValidateTheErrorCountAndDisembarkationStatusOnReservationBulkLog(int errorCount) {
        guestServicePage.refreshPage();

        int successCount;
        successCount = Integer.parseInt(crewInformationPage.validateErrorCount(errorCount));
        Assert.assertEquals(errorCount, successCount);
        Assert.assertEquals(crewInformationPage.validateStatus(), "Done");
    }

    @Then("User verify crew required details")
    public void userVerifyCrewRequiredDetails(DataTable dataTable) {
        List<String> dataRow = dataTable.row(0);
        List<String> columnData = new ArrayList<>();
        for (int i = 0; i < dataRow.size(); i++) {
            columnData.add(dataRow.get(i));
        }

        System.out.println(columnData);
        System.out.println(crewInformationPage.getCrewColumnHeaders());
        Assert.assertEquals(columnData, crewInformationPage.getCrewColumnHeaders());

    }

    @And("User click on {string} link under status")
    public void userClickOnLinkUnderStatus(String link) {
        crewInformationPage.clickOnStatusLink(link);
    }

    @Then("User click on chain icon at top right pane.")
    public void userClickOnChainIconAtTopRightPane() {
        commonPage.openLinkUsingHref("quick-links");
    }

    @Then("User verify the crew status as {string}")
    public void userVerifyTheCrewStatusAs(String status) {
        String crewStatus = crewInformationPage.getCrewStatus().replace("/", " ").trim();
        Assert.assertEquals(crewStatus, status);
    }

    @Then("User select all checkbox on account statement")
    public void userSelectAllCheckboxOnAccountStatement() {
        crewInformationPage.selectAllAccountCheckbox();
    }

    @Then("User enable the toggle {string} on account statement")
    public void userEnableTheToggleOnAccountStatement(String toggleName) {
        crewInformationPage.enableTogglePosting(toggleName);
    }

    @Then("User verify the {string} toggle button on in account statement")
    public void userVerifyTheToggleButtonOnInAccountStatement(String toggleName) {
        String toggleHideNoPrintStatus = crewInformationPage.hideNoPrintsToggleButtonStatus(toggleName);
        Assert.assertEquals(toggleHideNoPrintStatus, "true");
    }

    @Then("User disAble {string} on account statement")
    public void userDisAbleOnAccountStatement(String toggleName) {
        crewInformationPage.disableTogglePosting(toggleName);
    }

    @Then("User verify the {string} toggle button off in account statement")
    public void userVerifyTheToggleButtonOffInAccountStatement(String toggleName) {
        String toggleHideNoPrintStatus = crewInformationPage.hideNoPrintsToggleButtonStatus(toggleName);
        Assert.assertEquals(toggleHideNoPrintStatus, "false");
    }

    @Then("User verify Assignments and Contract Information details")
    public void userVerifyAssignmentsAndContractInformationDetails() {
        List<String> headerList = crewInformationPage.getCrewAssignmentsAndContractInformationHeaders();
        List<String> status = new ArrayList<>(Arrays.asList("Flight Info", "Hotel Info"));
        Assert.assertTrue(headerList.containsAll(status));
    }


    @Then("User enter the crew first name and last name")
    public void userEnterTheCrewFirstNameAndLastName() {
        crewInformationPage.enterFirstAndLastName("test" + String.valueOf(excursionManagementPage.getRandomNumber()), "test" + String.valueOf(excursionManagementPage.getRandomNumber()));
        WebElement element = crewInformationPage.getFirstNameTextWebElement();
        String firstAndLastNameTextMessage = commonFunctions.readToastMessage(element).replace(" ", "");
        System.out.println(firstAndLastNameTextMessage);
        context.setContext("firstAndLastNameText", firstAndLastNameTextMessage);
    }

    @Then("User set date for {string} in add new crew reservation")
    public void userSetDateForInAddNewCrewReservation(String embarkationOrDisembarkation) {
        String date = null;
        if (embarkationOrDisembarkation.equals("Embarkation")) {
            date = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
        } else {
            date = commonFunctions.getDateIncrementedBy(2, "M/d/yyyy");
        }
        crewInformationPage.setDates(embarkationOrDisembarkation, date);
    }

    @Then("User select the requires cabin")
    public void userSelectTheRequiresCabin() {
        crewInformationPage.selectCabinNumber();
    }

    @Then("User enter document number")
    public void userEnterDocumentNumber() {
        crewInformationPage.enterDocumentNr(String.valueOf(excursionManagementPage.getRandomNumber()));
        WebElement element = crewInformationPage.getCommentTextWebElement();
        String textMessage = commonFunctions.readToastMessage(element);
        context.setContext("imageText", textMessage);
    }

    @Then("User set date for {string} in add new document on crew reservation")
    public void userSetDateForInAddNewDocumentOnCrewReservation(String embarkationOrDisembarkation) {
        String date = null;
        if (embarkationOrDisembarkation.equals("Embarkation")) {
            date = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
        } else {
            date = commonFunctions.getDateIncrementedBy(90, "M/d/yyyy");
        }
        crewInformationPage.setDates(embarkationOrDisembarkation, date);
    }

    @Then("User set date for {string} in add new document crew reservation")
    public void userSetDateForInAddNewDocumentCrewReservation(String embarkationOrDisembarkation) {
        String date = null;
        if (embarkationOrDisembarkation.equals("Embarkation")) {
            date = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
        } else {
            date = commonFunctions.getDateIncrementedBy(-10000, "M/d/yyyy");
        }
        crewInformationPage.setDates(embarkationOrDisembarkation, date);
    }

    @Then("User verify create a new reservation for crew member.")
    public void userVerifyCreateANewReservationForCrewMember() {
        /*String fullName =crewInformationPage.getFullName().toLowerCase();
        context.setContext("crewName",fullName);
        String[] name= fullName.split(" ");
        Assert.assertEquals(name[0],context.getContext("firstNameText").toLowerCase().trim());*/

        String fullName = crewInformationPage.getFullName().toLowerCase().replace(",", "").trim();
        context.setContext("crewName", fullName);
        String[] name = fullName.split("\\s+");
        Assert.assertEquals(name[0], context.getContext("firstNameText").toLowerCase().trim(), "First name mismatch after normalization.");
    }

    @And("User click on {string} on new document crew reservation")
    public void userClickOnOnNewDocumentCrewReservation(String button1) {
        crewInformationPage.navigateToSaveAndOpenProfile(button1);
    }

    @Then("User clear data on gangway logs")
    public void userClearDataOnGangwayLogs() {
        crewInformationPage.clearDate();
    }

    @And("User select action types as {string}")
    public void userSelectActionTypesAs(String type) {
        crewInformationPage.selectActionType(type);
    }

    @And("User verify that action type list do not contain {string}")
    public void userVerifyThatActionTypeListDoNotContain(String type) {
        List<String> statusList = crewInformationPage.getActionTypeListOfAll();
        System.out.println(statusList);
        Assert.assertFalse(statusList.contains(type),
                "The status list has '" + type + "'  found in the list.");
    }

//    @Then("User set disembarkation date for today")
//    public void userSetDisembarkationDateForToday(String date) {
//        crewInformationPage.setDisembarkationToday(date);
//    }

    @Then("User select disembarkation port as {string}")
    public void userSelectDisembarkationPortAs(String option) {
    }

    @Then("User set {string} for today")
    public void userSetForToday(String date) {
        crewInformationPage.setDisembarkationToday(date);
    }

    @Then("User save the fill name details on add new reservation")
    public void userSaveTheFillNameDetailsOnAddNewReservation() {
        commonPage.waitForDomReady();
        WebElement firstNameElement = crewInformationPage.getFirstNameFieldTextWebElement();
        String textMessageFirstName = commonFunctions.readToastMessage(firstNameElement);
        context.setContext("firstNameText", textMessageFirstName);
        logger.info("FirstName text message : " + context.getContext("firstNameText"));
        WebElement lastNameElement = crewInformationPage.getLastNameFieldTextWebElement();
        String textMessageLastName = commonFunctions.readToastMessage(lastNameElement);
        context.setContext("lastNameText", textMessageLastName);
        logger.info("lastName text message : " + context.getContext("lastNameText"));
    }

    @And("User click on {string} on add travel documents")
    public void userClickOnOnAddTravelDocuments(String button1) {
        crewInformationPage.navigateToSaveAndNext(button1);
    }

    @And("User set date for {string} in new crew reservation")
    public void userSetDateForInNewCrewReservation(String embarkationOrDisembarkation) {
        String date = null;
        if (embarkationOrDisembarkation.equals("Embarkation")) {
            date = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
        } else {
            date = commonFunctions.getDateIncrementedBy(30, "M/d/yyyy");
        }
        crewInformationPage.setDates(embarkationOrDisembarkation, date);
    }

    @Then("User click on three button and select {string}")
    public void userClickOnThreeButtonAndSelect(String button) {
        crewInformationPage.personSecurityFlagsButton(button);
    }

    @Then("User type the reason details as {string}")
    public void userTypeTheReasonDetailsAs(String text) {
        crewInformationPage.enterReasonDetails(text);
    }

    @Then("User click {string} plus button in allowed to post")
    public void userClickPlusButtonInAllowedToPost(String accounts) {
        crewInformationPage.clickPlusButtonInAllowedToPost(accounts);
    }

    @Then("User verify the selected account name")
    public void userVerifyTheSelectedAccountName() {
        List<String> names = crewInformationPage.systemAccountNames();
        String savedName = context.getContext("guestName");
        Assert.assertTrue(names.contains(savedName));

    }

    @And("User save the account name")
    public void userSaveTheAccountName() {
        String guestName = crewInformationPage.getAccountName();
        System.out.println("guestName :: " + guestName);
        context.setContext("guestName", guestName);
    }

    @Then("User select the Folder as {string}")
    public void userSelectTheFolderAs(String option) {
        crewInformationPage.selectFolder(option);
    }

    @And("User click on {string} code link")
    public void userClickOnCodeLink(String link) {
        crewInformationPage.clickOnCodeLink(link);
    }

    @Then("User click on {string} button in reporting")
    public void userClickOnButtonInReporting(String button1) {
        crewInformationPage.navigateToPreview(button1);
    }

    @Then("User verify the move & route the postings for respective account to crew member.")
    public void userVerifyTheMoveRouteThePostingsForRespectiveAccountToCrewMember() {
        String buyerName = crewInformationPage.buyerNameText();
        Assert.assertEquals(context.getContext("guestName"), buyerName);
    }

    @Then("user select {string} in security flags tab")
    public void userSelectInSecurityFlagsTab(String button1) {
        crewInformationPage.addSecurityFlagButton(button1);
    }

    @And("User add the security flags details")
    public void userAddTheSecurityFlagsDetails() {
        crewInformationPage.enterSecurity();
        String reason = crewInformationPage.getReasonText();
        context.setContext("reasonText", reason);
    }

    @Then("User verify content present in the PDF content")
    public void userVerifyContentPresentInThePDFContent() {
        Assert.assertTrue(context.getContext("pdfContent").contains("Crew Passport Labels by Name"));
    }

    @Then("User click on {string} button in security flags tabs")
    public void userClickOnButtonInSecurityFlagsTabs(String button) {
        String name = context.getContext("reasonText");
        crewInformationPage.editSecurityFlags(name, button);
    }

    @Then("User verify the {string} warning is remove to guest profile")
    public void userVerifyTheWarningIsRemoveToGuestProfile(String warning) {
        List<String> warningText = guestServicePage.getWarningMessage();
        Assert.assertFalse(warningText.contains(warning));
    }

    @And("User searches for crew by cabin number")
    public void searchByCabinNumber() {
        String cabinNumber = context.getContext("cabinNumber");
        guestServicePage.searchUser(cabinNumber);
    }

    @And("User navigates to {string} profile page")
    public void userNavigatesToProfilePage(String crewFirstName) {
        if (crewFirstName.contains("Dynamic")) {
            String[] fullName = context.getContext("crewName").split(" ");
            System.out.println("fullName :: " + Arrays.toString(fullName));
            if (fullName.length > 2) {
                guestServicePage.navigateToCrewInformation(fullName[0] + " " + fullName[1]);
            } else {
                guestServicePage.navigateToCrewInformation(fullName[0] + " " + fullName[1]);
            }

        }
    }


    @And("User click on {string} button personal items tab")
    public void userClickOnButtonPersonalItemsTab(String button) {
        commonPage.clickOnButton(button);
    }

    @Then("User verify cabin number {string} to crew member on crew information page")
    public void userVerifyCabinNumberToCrewMemberOnCrewInformationPage(String condition) {
        String expectedCabinNumber = context.getContext("cabinNumber");
        String actualCabinNumber = crewInformationPage.getCabinNumberOnCrewInformationPage();

        if (condition.equalsIgnoreCase("same")) {
            Assert.assertEquals("Cabin number should match", expectedCabinNumber, actualCabinNumber);
        } else if (condition.equalsIgnoreCase("different")) {
            Assert.assertNotEquals("Cabin number should not match", expectedCabinNumber, actualCabinNumber);
        } else {
            throw new IllegalArgumentException("Invalid condition: " + condition + ". Use 'same' or 'different'.");
        }

    }

    @And("User able navigates to {string} profile page")
    public void userAbleNavigatesToProfilePage(String crewFirstName) {
        if (crewFirstName.contains("Dynamic")) {

            String crewName = context.getContext("crewName").trim();
            String[] fullName = crewName.split("\\s+");

            System.out.println("fullName :: " + Arrays.toString(fullName));

            String firstName = fullName[0];
            String lastName = fullName.length > 1 ? fullName[1] : "";
            crewInformationPage.clickCrewFirstName(firstName, lastName);

        }

    }

    @Then("Validate user should not be able to add crew effects to checked-out crew members")
    public void userShouldNotBeAbleToAddCrewEffectsToCheckedOutCrewMembers() {
        crewInformationPage.userShouldNotBeAbleToAddCrewEffectsToCheckedOutCrewMembers();
    }

    @And("User able enable the toggle slider in new profile")
    public void userAbleEnableTheToggleSliderInNewProfile() {
        crewInformationPage.userAbleEnableTheToggleSliderInNewProfile();
    }

    @And("User able click the tab options")
    public void userAbleClickTheTabOptions() {
        crewInformationPage.userAbleClickTheTabOptions();
    }

    @And("User select a guest with random person")
    public void userSelectAGuestWithRandomPerson() {
        crewInformationPage.searchGuestUsingAlphabetic();
        int number = hotelMaintenancePage.getRandomNumber();
        System.out.println(number);
        hotelMaintenancePage.selectGuests(number);
    }

    @And("User clear the operation position")
    public void userClearTheOperationPosition() {
        crewInformationPage.clearOperationPosition();
    }


    @And("User enables intoxicated for crew")
    public void userEnablesIntoxicatedForCrew() {
        crewInformationPage.enableIntoxicatedToggle();
    }

    @And("User search the unassign cabin number")
    public void userSearchTheUnassignCabinNumber() {
        String crewName = ((String) context.getContext("crewName")).trim().split("\\s+")[0];
        guestServicePage.searchUser(crewName);
    }

    @Then("User checking the cabin number is empty")
    public void userCheckingTheCabinNumberIsEmpty() {
        List<WebElement> elements = crewInformationPage.getSecondRowEmptyCabinCell();

        Assert.assertTrue(elements.size() > 0,
                "Cabin number is NOT empty for second row");
    }

    @Then("User verifies {string} navigation")
    public void userVerifiesNavigation(String tab) {
        int count = crewInformationPage.getTabCount(tab);
        Assert.assertEquals(count, 1);
    }

    @And("User search for cabin numbers")
    public void userSearchForCabinNumbers() {
        String cabinNumber = (String) context.getContext("cabinNumber");
        crewInformationPage.searchCabinNumber(cabinNumber);
    }

    @And("User searches for crew by name")
    public void userSearchesForCrewByName() {
        String fullName = context.getContext("crewName");
        String firstName = fullName.split(" ")[1];
        guestServicePage.searchUser(firstName);
    }

    @Then("User verifies crew name is displayed correctly")
    public void userVerifiesCrewNameIsDisplayedCorrectly() {

        String fullName = context.getContext("crewName");
        String crewName = crewInformationPage.getCrewNameForCabinAssignment();
        Assert.assertTrue(crewName.contains(fullName), "Expected crew name: " + fullName + " but found: " + crewName);
    }

    @Then("User saves the {string}")
    public void userSavesTheDate(String placeholder) {

        WebElement element = getDriver().findElement(crewInformationPage.getInputByPlaceholder(placeholder));
        String date = element.getAttribute("value");
        context.setContext("DATE", date);
    }

    @Then("User validates the {string} is updated")
    public void validateDateIsUpdated(String placeholder) {

        String oldDate = (String) context.getContext("DATE");
        WebElement element = getDriver().findElement(crewInformationPage.getInputByPlaceholder(placeholder));
        String newDate = element.getAttribute("value");
        Assert.assertNotEquals(newDate, oldDate,
                "Date is not updated. Both dates are same: " + newDate);
    }

    @And("User selects the {string} from the dropdown")
    public void userSelectsTheFromTheDropdown(String text) {
        crewInformationPage.userSelectsTheFromTheDropdown(text);

    }

    @And("User clear the reservation status of the crew")
    public void userClearTheReservationStatusOfTheCrew() {
        crewInformationPage.userClearTheReservationStatusOfTheCrew();
    }

    private String getNationalityText() {
        WebElement element = getDriver().findElement(By.cssSelector("[id*='nationality-field']"));
        seleniumWaits.untilElementIsVisible(element);
        return element.getText().trim();
    }

    @Then("User select the {string}")
    public void userSelectThe(String dropdown) {
        String beforeNationality = getNationalityText();
        context.setContext("NATIONALITY_BEFORE", beforeNationality);
        crewInformationPage.userSelectThe(dropdown);
    }

    @Then("User validates the nationality is updated")
    public void userValidatesTheNationalityIsUpdated() {
        String beforeNationality = (String) context.getContext("NATIONALITY_BEFORE");
        String afterNationality = getNationalityText();
        Assert.assertNotEquals(afterNationality, beforeNationality, "Nationality is not updated. Both are same: " + afterNationality);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User verifies the trash can icon functionality")
    public void userVerifiesTheTrashCanIconFunctionality() {
        int beforeCount = crewInformationPage.getDeleteIconCount();
        logger.info("Before : " + beforeCount);

        if (beforeCount == 0) {
            excursionManagementPage.clickOnPlusButton();
            commonPage.clickOnButton(CrewInformationConstants.ADD);
            crewInformationPage.addingThedata();
            crewInformationPage.setQuantity("4");
            commonPage.clickOnButton(CrewInformationConstants.ADD);
            beforeCount = crewInformationPage.getDeleteIconCount();
            logger.info("Before : " + beforeCount);
        }
        crewInformationPage.clickFirstDeleteIcon();
        commonPage.clickOnButton(CrewInformationConstants.DELETE);
        guestServicePage.getSuccessfulToastMessage();
        int afterCount = crewInformationPage.getDeleteIconCount();
        logger.info("After : " + afterCount);
        Assert.assertNotEquals(afterCount, beforeCount - 1, "Delete failed. Expected: " + (beforeCount - 1) + " but got: " + afterCount);
    }


    @Then("User verifies the edit icon functionality")
    public void userVerifiesEditIconFunctionality() {
        int beforeCount = crewInformationPage.getEditIconCount();
        logger.info("Before : " + beforeCount);

        if (beforeCount == 0) {
            excursionManagementPage.clickOnPlusButton();
            commonPage.clickOnButton(CrewInformationConstants.ADD);
            crewInformationPage.addingThedata();
            crewInformationPage.setQuantity("4");
            commonPage.clickOnButton(CrewInformationConstants.ADD);
            beforeCount = crewInformationPage.getEditIconCount();
            logger.info("Before : " + beforeCount);
        }
        crewInformationPage.clickFirstEditIcon();
        int afterCount = crewInformationPage.getEditIconCount();
        logger.info("After : " + afterCount);
        Assert.assertNotEquals(afterCount, beforeCount - 1, "Edit failed. Expected: " + (beforeCount - 1) + " but got: " + afterCount);
    }


    @Then("User verifies member name")
    public void user_verify_the_crew_full_name() {
        Assert.assertEquals(crewInformationPage.getFullNameText(), CrewInformationConstants.CREWMEMBER_FULLNAME);
    }

    @Then("User verifies member demographics")
    public void user_verify_the_crew_demographics() {
        Assert.assertEquals(crewInformationPage.getCrewMemberDemographicsText(), CrewInformationConstants.CREWMEMBER_DEMOGRAPHICS);
    }


    @Then("User verifies member onboard status")
    public void user_verify_the_crew_status() {
        Assert.assertEquals(crewInformationPage.getProfileStatusText(), CrewInformationConstants.CREW_PROFILESTATUS);
    }

    @Then("User verifies member embarkation date")
    public void user_verify_the_crew_embarkationdate() {
        Assert.assertEquals(crewInformationPage.getEmbarkationDateText(), CrewInformationConstants.CREW_EMBARKATIONDATE);
    }

    @Then("User verifies member disembarkation date")
    public void user_verify_the_crew_disembarkationDate() {
        Assert.assertEquals(crewInformationPage.getDisembarkationDateText(), CrewInformationConstants.CREW_DISEMBARKATIONDATE);
    }
    @Then("User should be able to view the emergency contact information of the crew")
    public void userShouldBeAbleToViewTheEmergencyContactInformationOfTheCrew() {
        int count = crewInformationPage.verifyEmergencyContactInformationIsDisplayed();
        Assert.assertTrue(count >= 1, "Expected at least one emergency contact card, but found none.");
    }

    @And("User add the ashore denied")
    public void userAddTheAshoreDenied() {
        crewInformationPage.userAddTheAshoreDenied();
    }

    @Then("User should be able to verify that a salutation is selected on the Guest Profile home page")
    public void userShouldBeAbleToVerifyThatASalutationIsSelectedOnTheGuestProfileHomePage() {
        String guestName = crewInformationPage.getGuestProfileName();
        Assert.assertFalse(guestName.matches("(?i)^(Master\\s*\\(youth\\)|Doctor|Mr|Mrs|Ms)\\b.*"), "Guest name should not contain a salutation, but found: " + guestName);
    }

    @And("User should be able to select {string} as the salutation dropdown")
    public void userShouldBeAbleToSelectAsTheSalutationDropdown(String text) {
        crewInformationPage.selectSalutation(text);
    }

    @And("User should be able to select {string}")
    public void userShouldBeAbleToSelect(String text) {
        crewInformationPage.userShouldBeAbleToSelectNoneAsTheDropdown(text);
    }

    @Then("User should be able to view the payment method details of the crew member")
    public void userShouldBeAbleToViewThePaymentMethodDetailsOfTheCrewMember() {
        Assert.assertTrue(crewInformationPage.getPaymentMethodAssignmentForms().size() >= 1, "Payment Method Assignment Form is not displayed.");
        crewInformationPage.highlightPaymentMethodAssignmentForms();
    }

    @Then("User ensures a payment method is available by using an existing payment method or creating a new one")
    public void userEnsuresAPaymentMethodIsAvailableByUsingAnExistingPaymentMethodOrCreatingANewOne() {
        if (crewInformationPage.getPaymentMethodAssignmentForms().size() < 2) {

            commonSteps.userViewsTheButton(CrewInformationConstants.ADD_NEW_PAYMENT_METHOD);
            commonSteps.userClickOnThe(CrewInformationConstants.ADD_NEW_PAYMENT_METHOD);

            commonSteps.userViewsTheButton(CrewInformationConstants.PAYMENT_METHOD);
            commonSteps.userClickOnThe(CrewInformationConstants.PAYMENT_METHOD);

            crewInformationPage.financialDepartmentDropDown();
            guestServicePage.iterateToGetEnabledValue();
            crewInformationPage.clickOnEnabledOptionText();

            groupManagementPage.enterComment(ExcursionManagementConstants.COMMENT_MESSAGE);

            commonSteps.userViewsTheButton(GlobalConstants.SAVE_BUTTON);
            commonSteps.userClickOnThe(GlobalConstants.SAVE_BUTTON);
            guestServicePage.getSuccessfulToastMessage();
            Assert.assertTrue(crewInformationPage.getPaymentMethodAssignmentForms().size() >= 2, "Payment method was not created successfully.");
            context.setContext("SKIP_SCREENSHOT", "true");
        }

    }

    @Then("The checked box on new payment method is to set it to default, deactivating the previous {string} payment method")
    public void theCheckedBoxOnNewPaymentMethodIsToSetItToDefaultDeactivatingThePreviousActivePaymentMethod(String status) {
        crewInformationPage.displayDisabledActiveCheckBox();
        crewInformationPage.clickEnableActiveCheckBox();
    }

    @Then("User verifies that the payment method is updated in the account statement")
    public void userVerifiesThatThePaymentMethodIsUpdatedInTheAccountStatement() {
        String updatedAccountStatement = crewInformationPage.getDisabledPaymentMethod();
        guestServicePage.refreshPage();
        Assert.assertEquals(crewInformationPage.getAccountStatementPaymentMethod(), updatedAccountStatement, "Payment method in the Account Statement does not match the selected payment method.");
    }

    @Then("User should be able to view the reservation logs for the selected crew member")
    public void userShouldBeAbleToViewTheReservationLogsForTheSelectedCrewMember() {
        Assert.assertTrue(crewInformationPage.highlightCrewReservationLogs() > 0, "No crew reservation logs were found.");
    }

    @And("User searches for an check in the crew members")
    public void userSearchesForAnCheckInTheCrewMembers() {
        excursionManagementPage.userSearchForTheGuest();
        String[] guestName = hotelMaintenancePage.getGuestName().split(" ");
        System.out.println("guestName :: " + guestName[1].trim());
        context.setContext("firstguestName", guestName[0].trim());
        context.setContext("guestName", guestName[1].trim());
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User fills in all mandatory fields")
    public void userFillsInAllMandatoryFields() {
        List<String> nonMandatoryFields = crewInformationPage.getNonMandatoryFields();
        Assert.assertTrue(nonMandatoryFields.isEmpty(), "The following mandatory fields are missing: " + String.join(", ", nonMandatoryFields));
    }

    @And("User should verify the Save button is enabled and click it")
    public void userShouldVerifyTheSaveButtonIsEnabledAndClickIt() {
        Assert.assertTrue(crewInformationPage.isSaveButtonEnabled(), "The Save button is disabled even after entering all mandatory fields.");
    }

    @And("User click on {string} button in checkin page")
    public void userClickOnButtonInCheckinPage(String text) {
        crewInformationPage.userClickOnButtonInCheckinPage();
    }

    @Then("User should verify the boarding status of the selected crew member")
    public void userShouldVerifyTheBoardingStatusOfTheSelectedCrewMember() {
        homePage.navigateToSelectedService(GlobalConstants.CREW_SERVICES);
        guestServicePage.openGuestServiceItem(GlobalConstants.CREW_SEARCH);
        commonPage.searchForCrew(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User clicks on the {string} quick link")
    public void userClicksOnTheQuickLink(String quickLinkName) {
        crewInformationPage.clickQuickLink(quickLinkName);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User should be able to view the Crew Log page with the details")
    public void userShouldBeAbleToViewTheCrewLogPageWithTheDetails() {
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        safetyManagementPage.selectCrewByReservationStatus("Status:", "In progress");
        Assert.assertTrue(crewInformationPage.highlightCrewReservationLogs() > 0, "No crew reservation logs were found.");
    }
    @And("User clicks on the {string} button")
    public void userClicksOnTheButton(String buttonName) {
        crewInformationPage.userClicksOnTheButton(buttonName);
    }
    @And("User selects the {string} from the Template dropdown")
    public void userSelectsTheFromTheTemplateDropdown(String templateName) {
        crewInformationPage.selectTemplate(templateName);
    }

    @When("the user selects past embarkation dates in the From and To fields and clicks {string}")
    public void theUserSelectsPastEmbarkationDatesInTheFromAndToFieldsAndClicks(String buttonName) {
        crewInformationPage.selectPastEmbarkationDates();
        crewInformationPage.userClicksOnTheButton(buttonName);
    }

    @And("User scroll down to Close Security Flag")
    public void userScrollDownToCloseSecurityFlag() {
        crewInformationPage.userScrollDownToCloseSecurityFlag();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User should see the {string} dropdown option as {string}")
    public void userShouldSeeTheDropdownOptionAs(String optionName, String expectedValue) {
        Assert.assertTrue(crewInformationPage.isOptionDisplayed(optionName,expectedValue), optionName + " option is not displayed");
    }

    private static final Path EXCEL_FILE = Path.of(System.getProperty("user.dir"), "Downloads", "crew-members-crew-members-list-export.xlsx");

    @Then("User validates the last name and first name of the crew")
    public void userValidatesTheLastNameAndFirstNameOfTheCrew() {
        String expectedFirstName = context.getContext("CREW_FIRST_NAME");
        String expectedLastName = context.getContext("CREW_LAST_NAME");
        String expectedCrewId = context.getContext("CREW_ID");

        boolean present = crewInformationPage.isCrewMemberPresent(EXCEL_FILE, expectedFirstName, expectedLastName, expectedCrewId);
        Assert.assertTrue(present, "Crew member not found: " + expectedFirstName + " | " + expectedLastName + " | " + expectedCrewId);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User saves the crew ID, last name, and first name")
    public void userSavesTheCrewIDLastNameAndFirstName() {
        String firstName = crewInformationPage.getFirstCrewFirstName();
        String lastName = crewInformationPage.getFirstCrewLastName();
        String crewId = crewInformationPage.getFirstCrewId();

        context.setContext("CREW_FIRST_NAME", firstName);
        context.setContext("CREW_LAST_NAME", lastName);
        context.setContext("CREW_ID", crewId);
    }

    @And("Navigates to profile photo cabin crew profile")
    public void navigatesToProfilePhotoCabinCrewProfile() {
        crewInformationPage.navigatesToProfilePhotoCabinCrewProfile();
    }

    @Then("User verifies that the crew member details such as name, age, onboard status, gender, embarkation date, and disembarkation date are displayed")
    public void userVerifiesThatTheCrewMemberDetailsSuchAsNameAgeOnboardStatusGenderEmbarkationDateAndDisembarkationDateAreDisplayed() {
        Assert.assertTrue(crewInformationPage.isCrewMemberNameDisplayed(), "Crew member name is not displayed: "); //Full Name

        String info = crewInformationPage.getCrewInfoText().replace("(", "").replace(")", "").trim(); //Age and Gender
        String[] details = info.split(",");
        Assert.assertEquals(details.length, 3, "Expected 3 crew details, but found: ");

        Assert.assertTrue(crewInformationPage.isCrewBoardStatusDisplayed(), "Crew member onboard status is not displayed."); //Status

        List<String> crewDetails = crewInformationPage.getEmbarkationAndDisembarkationDetails();

        Assert.assertTrue(crewDetails.size() >= 3, "Expected at least 4 crew details, but found: " + crewDetails);
        Assert.assertTrue(crewDetails.get(1).matches("\\d{1,2}/\\d{1,2}/\\d{4}"), "Invalid Embarkation date: " + crewDetails.get(1));
        Assert.assertTrue(crewDetails.get(3).matches("\\d{1,2}/\\d{1,2}/\\d{4}"), "Invalid Disembarkation date: " + crewDetails.get(3));
    }

    @And("User searches for the newly created crew member")
    public void userSearchesForTheNewlyCreatedCrewMember() {
        shipSecurityPage.userSearchForTheGuest(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
    }

    @And("User click on {string} text")
    public void userClickOnText(String text) {
        commonPage.clickOnButton(text);
    }


    @And("User click on {string} and select {string} and add Reason Detail")
    public void userClickOnAndSelectAndAddReasonDetail(String dropdown, String text) {
       shipSecurityPage.userClickOnAndSelectAndAddReasonDetail(dropdown, text);
    }

    @And("User click on three dots icon")
    public void userClickOnThreeDotsIcon() {
        shipSecurityPage.userClickOnThreeDotsIcon();
    }

    @Then("User added crew effects should be disabled after crew checkout")
    public void userAddedCrewEffectsShouldBeDisabledAfterCrewCheckout() {
        Assert.assertTrue(crewInformationPage.isCrewEffectsDisabled(), "Crew Effects button should be disabled after crew checkout");
    }
}


