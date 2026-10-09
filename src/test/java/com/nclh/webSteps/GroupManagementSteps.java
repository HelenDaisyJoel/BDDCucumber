package com.nclh.webSteps;

import com.nclh.constants.*;
import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.*;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.nclh.dataProviders.JsonReader.readUserData;

public class GroupManagementSteps extends AllDriverManager {

    Context context;
    GroupManagementPage groupManagementPage;
    CommonPage commonPage;
    PointOfSalePage pointOfSalePage;
    SafetyManagementPage safetyManagementPage;
    CommonFunctions commonFunctions;
    CommonSteps commonSteps;
    ShipSecurityPage shipSecurityPage;
    GuestServicePage guestServicePage;
    HomePage homePage;
    SeleniumActions seleniumActions;
    CrewInformationPage crewInformationPage;
    GuestServiceRequestPage guestServiceRequestPage;
    HotelMaintenancePage hotelMaintenancePage;

    File directory = new File(System.getProperty("user.dir") + "\\Downloads");
    public GroupManagementSteps(Context context) {
        this.context = context;
        safetyManagementPage = new SafetyManagementPage(getDriver());
        groupManagementPage = new GroupManagementPage(getDriver());
        pointOfSalePage = new PointOfSalePage(getDriver());
        commonPage = new CommonPage(getDriver());
        commonFunctions = new CommonFunctions();
        commonSteps = new CommonSteps(context);
        shipSecurityPage = new ShipSecurityPage(getDriver());
        guestServicePage = new GuestServicePage(getDriver());
        homePage = new HomePage(getDriver());
        seleniumActions = new SeleniumActions(getDriver());
        crewInformationPage = new CrewInformationPage(getDriver());
        guestServiceRequestPage = new GuestServiceRequestPage(getDriver());
        hotelMaintenancePage = new HotelMaintenancePage(getDriver());
    }

    @And("User click on cabin number dropdown")
    public void userClickOnCabinNumberDropdown() {
        groupManagementPage.clickOnCabinNumberDropDown();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User enter cabin number {string} in search box and select the checkbox")
    public void userEnterCabinNumberInSearchBox(String cabinNumber) {
        groupManagementPage.enterCabinNumber(cabinNumber);
        groupManagementPage.clickOnCheckBox();
    }

    @And("User open service link {string}")
    public void userOpenServiceLink(String link) {
        groupManagementPage.clickOnServiceLink(link);
    }

    @And("User open group on the reservation screen")
    public void userOpenGroupOnTheReservationScreen() {
        groupManagementPage.openGroup();
    }

    @And("User select tab {string} under group members")
    public void userSelectTabUnderGroupMembers(String tab) {
        groupManagementPage.clickOnTab(tab);
    }

    @And("User clear reservation status in the group management")
    public void userClearReservationStatusInTheGroupManagement() {
        groupManagementPage.clearReservationStatus();
    }

    @And("User search guest")
    public void userSearchGuest() {
        String[] guestName = context.getContext("guestFullName").split(" ");
        groupManagementPage.searchGuest(guestName[0] + " " + guestName[1]);
    }

    @And("User select guest")
    public void userSelectGuest() {
        groupManagementPage.selectUser();
    }

    @And("User select role form options {string}")
    public void userSelectRoleFormOptions(String option) {
        groupManagementPage.selectRole(option);
    }

    @Then("User verify the guest present inside the group from {string}")
    public void userVerifyTheGuestPresentInsideTheGroup(String room) {
        switch (room) {
            case "new group":
                String[] guestName = context.getContext("fullNameGuest").split(" ");
                System.out.println(guestName[0] + " " + guestName[1]);
                Assert.assertTrue(groupManagementPage.isUserFullNamePresent(guestName[0], guestName[1]));
                break;
            case "stateroom":
                String[] guestFullName = context.getContext("guestFullName").split(" ");
                Assert.assertTrue(groupManagementPage.isUserFullNamePresent(guestFullName[0], guestFullName[1]));
                break;
        }

    }

    @And("User search guests to add them in group")
    public void userSearchGuestsToAddThemInGroup() {
        pointOfSalePage.searchGuestUsingAlphabetic();
        groupManagementPage.addUser();
        String fullNameGuest = groupManagementPage.getUserFullName();
        System.out.println("fullNameGuest :: " + fullNameGuest);
        context.setContext("fullNameGuest", fullNameGuest);
        groupManagementPage.clickOnSelectButton();
    }

    @Then("User verify loyalty information")
    public void userVerifyLoyaltyInformation() {
        Assert.assertEquals(groupManagementPage.getListOfWebElements().size(), 3);
    }

    @And("User click on hide columns button")
    public void userClickOnHideColumnsButton() {
        groupManagementPage.clickOnHideColumnButton();
    }

    @And("User drag {string} and drop at the column header")
    public void userDragAndDropAtTheColumnHeader(String columnName) {
        groupManagementPage.dragItemToTheColumnHeader(columnName);
    }

    @Then("User verify {string} column displayed in the header row")
    public void userVerifyColumnDisplayedInTheHeaderRow(String column) {
        getDriver().manage().window().setSize(new Dimension(1920, 768));
        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        jse.executeScript("document.body.style.zoom = '67%';");

        Actions actions = new Actions(getDriver());
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();
        System.out.println(groupManagementPage.getColumnNames());
        Assert.assertTrue(groupManagementPage.getColumnNames().contains(column));
    }

    @And("User add group on the Group screen")
    public void userAddGroupOnTheGroupScreen() {
        groupManagementPage.addGroup();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User save group code")
    public void userSaveGroupCode() {
        String groupCode = groupManagementPage.getGroupCode();
        context.setContext("groupCode", "XXXCODE" + groupCode +"1");
        System.out.println(groupCode);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User verify group is created")
    public void userVerifyGroupIsCreated() {
        groupManagementPage.searchForGroupCode(context.getContext("groupCode"));
        String groupCode = safetyManagementPage.getSafetyNumberLinkText();
        Assert.assertEquals(groupCode, context.getContext("groupCode"));
    }

    @And("User select an option from dropdown")
    public void userSelectAnOptionFromDropdown() {
        groupManagementPage.selectValueFromDropDown();
    }

    @And("User save group name")
    public void userSaveGroupName() {
        String[] groupName = groupManagementPage.getGroupName().split(" ");
        context.setContext("groupName", groupName[0]);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User verify saved group name present in pdf content")
    public void userVerifySavedGroupNamePresentInPdfContent() {
        System.out.println(context.getContext("groupName"));
        Assert.assertTrue(context.getContext("pdfContent").contains(context.getContext("groupName")));
    }

    @And("User click on group info tab {string}")
    public void userClickOnGroupInfoTab(String tab) {
        groupManagementPage.clickOnGroupTab(tab);
    }

    @And("User type communication value {string}")
    public void userTypeCommunicationValue(String email) {
        groupManagementPage.enterCommunicationValue(email);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User verify communication information is added")
    public void userVerifyCommunicationInformationIsAdded() {
        Assert.assertTrue(groupManagementPage.getNumberOfCommunicationInputs() >= 1);
    }

    @And("User save communication type before modification of value")
    public void userSaveCommunicationTypeBeforeModificationOfValue() {
        String optionValue = groupManagementPage.getSelectedValueFromCommunicationTypeDropDown();
        System.out.println(optionValue);
        context.setContext("beforeEditCommunicationTypeValue", optionValue);
    }

    @And("User modify an option from dropdown")
    public void userModifyAnOptionFromDropdown() {
        groupManagementPage.modifyCommunicationType();
    }

    @And("User save communication type after modification of value")
    public void userSaveCommunicationTypeAfterModificationOfValue() {
        String optionValue = groupManagementPage.getSelectedValueFromCommunicationTypeDropDown();
        System.out.println(optionValue);
        context.setContext("afterEditCommunicationTypeValue", optionValue);
    }

    @Then("User verify communication type is updated with latest value")
    public void userVerifyCommunicationTypeIsUpdatedWithLatestValue() {
        Assert.assertNotEquals(context.getContext("beforeEditCommunicationTypeValue"), context.getContext("afterEditCommunicationTypeValue"));
    }

    @And("User open created group")
    public void userOpenCreatedGroup() {
        safetyManagementPage.clickOnSafetyNumberLink();
    }

    @And("User add reservation for group with estimated {string} and expected {string} members")
    public void userAddReservationForGroup(String estimatedMembers, String expectedMembers) {

        String fromDate = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
        String toDate = commonFunctions.getDateIncrementedBy(30, "M/d/yyyy");
        context.setContext("fromDate", fromDate);
        context.setContext("toDate", toDate);
        groupManagementPage.addReservation(fromDate, toDate, estimatedMembers, expectedMembers);
        groupManagementPage.clickOnReservationAddButton();
    }

    @Then("Verify reservation is created for the group")
    public void verifyReservationIsCreatedForTheGroup() {
        List<String> reservationDetails = groupManagementPage.getReservationDetails();
        System.out.println(reservationDetails);
        List<String> dates = Arrays.asList(context.getContext("fromDate"), context.getContext("toDate"));
        System.out.println(dates);
        Assert.assertTrue(reservationDetails.containsAll(dates));
    }

    @And("User save cabin number from the list")
    public void userSaveCabinNumberFromTheList() {
        List<String> sensitiveCabins1 = Arrays.asList(readUserData("sensitiveData", "cabinNumbers", "cabin1"),
                readUserData("sensitiveData", "cabinNumbers", "cabin2"),
                readUserData("sensitiveData", "cabinNumbers", "cabin3"),
                readUserData("sensitiveData", "cabinNumbers", "cabin4"),
                readUserData("sensitiveData", "cabinNumbers", "cabin5"));

        List<String> sensitiveCabins = new ArrayList<>();
        sensitiveCabins.add(readUserData("sensitiveData", "cabinNumbers", "cabin1"));
        sensitiveCabins.add(readUserData("sensitiveData", "cabinNumbers", "cabin2"));
        sensitiveCabins.add(readUserData("sensitiveData", "cabinNumbers", "cabin3"));
        sensitiveCabins.add(readUserData("sensitiveData", "cabinNumbers", "cabin4"));
        sensitiveCabins.add(readUserData("sensitiveData", "cabinNumbers", "cabin5"));
        System.out.println("sensitiveCabins ::" + sensitiveCabins);
        String randomCabinNumber = groupManagementPage.getCabinNumber(sensitiveCabins);
        context.setContext("randomCabinNumber", randomCabinNumber);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User enter cabin number in search box and select the checkbox")
    public void userEnterCabinNumberInSearchBoxAndSelectTheCheckbox() {
        groupManagementPage.enterCabinNumber(context.getContext("randomCabinNumber"));
        groupManagementPage.clickOnCheckBox();
    }

    @And("User enter comment {string} in the comment inputBox")
    public void userEnterCommentInTheCommentInputBox(String comment) {
        groupManagementPage.enterComment(comment);
    }

    @Then("User verify payment method is created")
    public void userVerifyPaymentMethodIsCreated() {
        Assert.assertTrue(groupManagementPage.getNumberOfPaymentMethods() > 1);
    }

    @And("User deleted payment method")
    public void userDeletedPaymentMethod() {
        groupManagementPage.deletePaymentMethod();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User verify payment is deleted")
    public void userVerifyPaymentIsDeleted() {
        Assert.assertEquals(groupManagementPage.getNumberOfPaymentMethods(), 1);
    }

    @And("User tap on add icon")
    public void userTapOnAddIcon() {
        groupManagementPage.clickOnAddIcon();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User click on saved communication type")
    public void userClickOnSavedCommunicationType() {
        commonPage.navigateTo(context.getContext("beforeEditCommunicationTypeValue"));
    }

    @And("User update reservation for group  with estimated {string} and expected {string} members")
    public void userUpdateReservationForGroupWithEstimatedAndExpectedMembers(String estimatedMembers, String expectedMembers) {
        String fromDate = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
        String toDate = commonFunctions.getDateIncrementedBy(30, "M/d/yyyy");
        context.setContext("fromDateExist", fromDate);
        context.setContext("fromDateNew", toDate);
        groupManagementPage.updateReservation(fromDate, toDate, estimatedMembers, expectedMembers);
    }

    @And("User search guest member")
    public void userSearchGuestMember() {
        groupManagementPage.searchGuestMemeber();
    }

    @And("User click on group info tab {string} and Value {int} dropdown")
    public void userClickOnGroupInfoTabAndValueDropdown(String dropdown, int value) {
        groupManagementPage.userClickOnGroupInfoTabAndValueDropdown(dropdown, value);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User click on profile")
    public void userClickOnProfile() {
     groupManagementPage.userClickOnProfile();
    }

    @And("User click on {string} to add new communication type")
    public void userClickOnToAddNewCommunicationType(String button1) {
        if (groupManagementPage.checkHideIcon()) {
            SeleniumActions seleniumActions = new SeleniumActions(getDriver());
            SeleniumWaits seleniumWaits = new SeleniumWaits(getDriver());

            seleniumActions.sleep(3000);

            String text = "//span[text()='" + button1 + "' or normalize-space()='" + button1 + "']";

            try {
                seleniumWaits.untilElementIsPresent(By.xpath(text), 10);
                seleniumActions.scrollInToElement(getDriver().findElement(By.xpath(text)));
                seleniumActions.jsClick(getDriver().findElement(By.xpath(text)));
            } catch (TimeoutException e) {
                // Optionally log or handle timeout
            }

            seleniumActions.sleep(10000);
        }

    }

    @And("User select email option from dropdown")
    public void userSelectEmailOptionFromDropdown() {
        groupManagementPage.selectEmailFromDropDown();
    }

    @And("User verify communication info is reset")
    public void userVerifyCommunicationInfoIsReset() {
        Assert.assertTrue(groupManagementPage.getNumberOfHideIcon() == 0);

    }

    @And("User save the folio header count before clicking on add")
    public void userSaveTheFolioHeaderCountBeforeClickingOnAdd() {
        int beforeAdding = groupManagementPage.getHeaderCount();
        System.out.println(beforeAdding);
        String beforeAddingStr = String.valueOf(beforeAdding);
        context.setContext("BeforeAdd",beforeAddingStr);
    }

    @And("User click on add folio header")
    public void userClickOnAddFolioHeader() {
        groupManagementPage.clickOnAddFolioHeader();
    }

    @And("User Verify the folio is added")
    public void userVerifyTheFolioIsAdded() {
        String before = context.getContext("BeforeAdd");
        int beforeInt = Integer.parseInt(before);
        int expectedInt = beforeInt + 1;
        int actual = groupManagementPage.getHeaderCount();
        Assert.assertEquals(expectedInt,actual);

    }
    @Then("User view plus icon in group page")
    public void userViewPlusIconInGroupPage() {
        groupManagementPage.userViewPlusIconInGroupPage();
    }

    @Then("User view delete icon for payment method")
    public void userViewDeleteIconForPaymentMethod() {
        groupManagementPage.userViewDeleteIconForPaymentMethod();
    }

    @Then("User view the added guest")
    public void userViewTheAddedGuest() {
       groupManagementPage.userViewTheAddedGuest();
    }

    @And("User view the add icon")
    public void userViewTheAddIcon() {
        groupManagementPage.userViewTheAddIcon();
    }

    @And("User add the communication info data")
    public void userAddTheCommunicationInfoData() {
        userClickOnToAddNewCommunicationType(GroupManagementConstants.ADD_NEW_COMMUNICATION_TYPE);
        commonSteps.userClickOn(GroupManagementConstants.COMMUNICATION_TYPE);
        groupManagementPage.selectEmailFromDropDown();
        groupManagementPage.enterCommunicationValue(GroupManagementConstants.EMAIL_ID);
    }

    @Then("User check the column is drag")
    public void userCheckTheColumnIsDrag() {
        groupManagementPage.userCheckTheColumnIsDrag();
    }

    @And("User adding a new guest in the group")
    public void userAddingANewGuestInTheGroup() {
        groupManagementPage.userAddingANewGuestInTheGroup();
    }

    @Then("User should see the {string} text")
    public void userShouldSeeTheText(String expectedText) {
        String actualText = groupManagementPage.getAllFolioText();
        Assert.assertEquals(actualText, expectedText, "Expected text is not displayed");
    }

    @Then("User validate the folio count and click on add folio button")
    public void userValidateTheFolioCountAndClickOnAddFolioButton() {
        int beforeCount = groupManagementPage.getFolioCount();
        context.setContext("beforeFolioCount", String.valueOf(beforeCount));
        groupManagementPage.clickAddFolioButton();
    }

    @Then("User validate the folio count is increased")
    public void userValidateTheFolioCountIsIncreased() {
        int beforeCount = Integer.parseInt(context.getContext("beforeFolioCount"));
        Assert.assertTrue(groupManagementPage.isFolioCountIncreased(beforeCount), "Folio count is not increased");
    }

    @And("User adds a guest member to the Group Member list")
    public void userAddsAGuestMemberToTheGroupMemberList() {
        groupManagementPage.userViewTheAddIcon();
        shipSecurityPage.userSearchForTheGuest(GlobalConstants.GUEST_PREFIX_NAME);
        commonSteps.userClickOnThe(GroupManagementConstants.SELECT);
        groupManagementPage.userViewTheAddedGuest();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User view the added member based on stateroom")
    public void userViewTheAddedMemberBasedOnStateroom() {
        int beforeCount = Integer.parseInt(context.getContext("beforeCount"));
        int afterCount = groupManagementPage.getItemsCount();
        Assert.assertEquals(afterCount, beforeCount + 1, "Items count is not increased");
    }

    @And("User checks the guest in the Group Member list")
    public void userChecksTheGuestInTheGroupMemberList() {
        int beforeCount = groupManagementPage.getItemsCount();
        context.setContext("beforeCount", String.valueOf(beforeCount));
    }

    @And("User add the guest in the group")
    public void userAddTheGuestInTheGroup() {
        groupManagementPage.clickOnServiceLink(GroupManagementConstants.GROUP_RESERVATION_SEARCH);
        groupManagementPage.openGroup();
        groupManagementPage.getGroupName();
        guestServicePage.openSubMenu(GroupManagementConstants.GROUP_MEMBERS);
        groupManagementPage.clickOnTab(GroupManagementConstants.ALL);
        userAddTheGuestMemberInGroup();
        homePage.navigateToService(GroupManagementConstants.GUEST_SERVICES);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User is able to see the group name in the guest profile")
    public void userIsAbleToSeeTheGroupNameInTheGuestProfile() {
        int guestCount = groupManagementPage.getGroupGuestListCount();
        Assert.assertTrue(guestCount >= 1, "Group guest list count is 0");
    }

    @And("User add the Guest member in group")
    public void userAddTheGuestMemberInGroup() {
        groupManagementPage.getItemsCount();
        List<WebElement> passengerPictures = getDriver().findElements(By.xpath("//otalio-passenger-picture"));
        if (passengerPictures.size() >= 1) {
            String fullNameGuest = groupManagementPage.getGroupUserFullName();
            context.setContext("fullNameGuest", fullNameGuest);
        } else {
            groupManagementPage.userViewTheAddIcon();
            shipSecurityPage.userSearchForTheGuest(GlobalConstants.GUEST_PREFIX_NAME);
            String fullNameGuest = groupManagementPage.getUserFullName();
            context.setContext("fullNameGuest", fullNameGuest);
            groupManagementPage.clickOnSelectButton();
            groupManagementPage.selectRole(GroupManagementConstants._GROUP_MEMBER);
            commonSteps.userClickOnThe(GroupManagementConstants.SAVE);
            List<WebElement> closeIcons = getDriver().findElements(By.xpath("//snack-bar-container//button[.//*[name()='use' and contains(@href,'close')]]"));
            if (closeIcons.size() > 0) {
                WebElement closeButton = closeIcons.get(0);
                closeButton.click();
            }
        }
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User select a guest from the list of group members")
    public void userSelectAGuestFromTheListOfGroupMembers() {
        List<WebElement> passengerPictures = getDriver().findElements(By.xpath("//otalio-passenger-picture"));
        WebElement firstPassenger = passengerPictures.get(0);
        seleniumActions.highLighterMethod(firstPassenger);
        firstPassenger.click();
    }

    @And("User select tab {string} under group members and click delete icon")
    public void userSelectTabUnderGroupMembersAndClickDeleteIcon(String tab) {
        groupManagementPage.clickOnTab(tab);
        int deleteIconCount = groupManagementPage.clickDeleteIcon();
        context.setContext("deleteIconCount", String.valueOf(deleteIconCount));
    }

    @And("User view delete pop up box")
    public void userViewDeletePopUpBox() {
        groupManagementPage.userViewDeletePopUpBox();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User view the success pop up")
    public void userViewTheSuccessPopUp() {
        int successPopupCount = groupManagementPage.getSuccessPopupCount();
        Assert.assertEquals(successPopupCount, 1, "Success popup is not displayed");
    }

    @And("User searches by group name and verifies the result")
    public void userSearchesByGroupNameAndVerifiesTheResult() {
        String actualGroupName = groupManagementPage.searchRandomGroupName();
        Assert.assertTrue(actualGroupName.contains(actualGroupName), "Group name is not matching after search");
    }

    @And("User views the created Communication Info boxes")
    public void userViewsTheCreatedCommunicationInfoBoxes() {
        groupManagementPage.userViewsTheCreatedCommunicationInfoBoxes();
    }

    @And("user click on the group link list")
    public void userClickOnTheGroupLinkList() {
        groupManagementPage.userClickOnTheGroupLinkList();
    }

    @And("User clicks on the Edit button under the Reservations section")
    public void userClicksOnTheEditButtonUnderTheReservationsSection() {
        groupManagementPage.userClicksOnTheEditButtonUnderTheReservationsSection();
    }

    @Then("User is able to view the Add Reservation page box")
    public void userIsAbleToViewTheAddReservationPageBox() {
        groupManagementPage.userIsAbleToViewTheAddReservationPageBox();
    }

    @And("User clicks on the Active From and Active To fields and updates the dates")
    public void andUserClicksOnTheActiveFromAndActiveToFieldsAndUpdatesTheDates() {
        String fromDate = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
        String toDate = commonFunctions.getDateIncrementedBy(365, "M/d/yyyy");
        context.setContext("fromDate", fromDate);
        context.setContext("toDate", toDate);
        String updatedDateValue = groupManagementPage.updateTheFromAndToDates(fromDate, toDate);
        context.setContext("updatedToDate", updatedDateValue);
    }

    @Then("User verifies that the Active From and Active To fields contain the previous details")
    public void userVerifiesThatTheActiveFromAndActiveToFieldsContainThePreviousDetails() {
        String expectedToDate = context.getContext("toDate").toString();
        String actualUpdatedToDate = context.getContext("updatedToDate").toString();
        Assert.assertNotEquals(actualUpdatedToDate, expectedToDate, "To Date is not updated");
    }

    @And("User views the previous reservation dates of the entire group")
    public void userViewsThePreviousReservationDatesOfTheEntireGroup() {
        String previousDateValue = groupManagementPage.previousReservationDatesOfTheEntireGroup();
        context.setContext("previousToDate", previousDateValue);
    }

    @Then("User verifies the updated reservation dates for the entire group")
    public void userVerifiesTheUpdatedReservationDatesForTheEntireGroup() {
        String previousToDate = context.getContext("previousToDate").toString();
        String fromDateNew = context.getContext("fromDateNew").toString();
        Assert.assertNotEquals(previousToDate, fromDateNew, "Both dates are matching");
    }

    @Then("User verifies that the group payment methods are displayed")
    public void userVerifiesThatTheGroupPaymentMethodsAreDisplayed() {
        List<WebElement> paymentMethodHistory = groupManagementPage.getPaymentMethodHistory();
        Assert.assertTrue(paymentMethodHistory.size() >= 1, "Payment Method History is not displayed");
        for (WebElement paymentMethod : paymentMethodHistory) {
            seleniumActions.scrollInToElement(paymentMethod);
            seleniumActions.highLighterMethod(paymentMethod);
        }
    }

    @Then("User views the added guest")
    public void userViewsTheAddedGuest() {
        String firstGuestName = context.getContext("firstguestName").toString();
        String guestName = context.getContext("guestName").toString();
        String fullNameGuest = context.getContext("fullNameGuest").toString();
        Assert.assertTrue(fullNameGuest.contains(firstGuestName) && fullNameGuest.contains(guestName), "Guest names are not matching");
    }

    @Then("User should be able to view the payment method details for the selected group")
    public void userShouldBeAbleToViewThePaymentMethodDetailsForTheSelectedGroup() {
        Assert.assertTrue(crewInformationPage.getPaymentMethodAssignmentForms().size() >= 1, "Payment Method Assignment Form is not displayed.");
        crewInformationPage.highlightPaymentMethodAssignmentForms();
    }

    @And("User searches for {string} report folder and selects it")
    public void userSearchesForReportFolderAndSelectsIt(String folderName) {
        groupManagementPage.searchAndSelectReportFolder(folderName);
    }

    @And("User searches for {string} in the report and clicks the first matching title")
    public void userSearchesForInTheReportAndClicksTheFirstMatchingTitle(String reportName) {
        groupManagementPage.searchAndSelectFirstReport(reportName);
        groupManagementPage.clickFirstReport(reportName);

    }

    @And("User enters all required fields and clicks on the {string} button")
    public void userEntersAllRequiredFieldsAndClicksOnTheButton(String buttonName) {
        String date = commonFunctions.getDateIncrementedBy(-1, "M/d/yyyy");
        groupManagementPage.enterRequiredFieldsAndClickButton(date,buttonName);
    }

    @Then("User should be able to view the report preview")
    public void userShouldBeAbleToViewTheReportPreview() {
        Assert.assertTrue(groupManagementPage.isReportPreviewDisplayed(), "Report preview is not displayed.");
    }

    @Then("User closes the error pop-up message")
    public void userClosesTheErrorPopUpMessage() {
        groupManagementPage.closeToastMessage();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User should be able to view all event locations")
    public void userShouldBeAbleToViewAllEventLocations() {
        Assert.assertTrue(groupManagementPage.getEventLocationCount() > 0, "No event locations are displayed.");

    }

    @Then("User should be able to view all event types")
    public void userShouldBeAbleToViewAllEventTypes() {
        userShouldBeAbleToViewAllEventLocations();
    }

    @And("User selects an existing event from the calendar")
    public void userSelectsAnExistingEventFromTheCalendar() {
        Assert.assertTrue(groupManagementPage.selectExistingEvent(), "No existing event is available even after switching to Month view.");
    }

    @And("User edit the required details on the Event Details page")
    public void userEntersTheRequiredDetailsOnTheEventDetailsPage() {
        groupManagementPage.enterRequiredEventDetails();
    }

    @Then("User should be able to view all item codes")
    public void userShouldBeAbleToViewAllItemCodes() {
        userShouldBeAbleToViewAllEventLocations();
    }

    @And("User selects the first available time slot on the calendar")
    public void userSelectsTheFirstAvailableTimeSlotOnTheCalendar() {
        groupManagementPage.selectFirstAvailableTimeSlot();
    }

    @And("User views the {string} button in an event page")
    public void userViewsTheButtonInAnEventPage(String arg0) {
       groupManagementPage.userViewsTheButtonInAnEventPage();
    }

    @And("User click on the {string} button in an event page")
    public void userClickOnTheButtonInAnEventPage(String arg0) {
        groupManagementPage.userClickOnTheButtonInAnEventPage();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User need to selects an existing event from the calendar")
    public void userNeedToSelectsAnExistingEventFromTheCalendar() {
        groupManagementPage.selectsAnExistingEventFromTheCalendar();
    }

    @And("User clicks the Delete icon for the existing event")
    public void userClicksTheDeleteIconForTheExistingEvent() {
        groupManagementPage.clickEventDeleteButton();
    }

    @And("User click on an open calendar slot at current time")
    public void userClickOnAnOpenCalendarSlotAtCurrentTime() {
        groupManagementPage.clickOnOpenCalendarSlotCurrectTime();
    }

    @And("User enters all required details for the new event")
    public void userEntersAllRequiredDetailsForTheNewEvent() {
        groupManagementPage.userEntersAllRequiredDetailsForTheNewEvent();
    }

    @Then("User validates the {string} view")
    public void userValidatesTheView(String timelineView) {
        groupManagementPage.clickTimelineView(timelineView);
        Assert.assertTrue(groupManagementPage.isTimelineViewDisplayed(timelineView), timelineView + " view is not displayed.");
        Assert.assertNotNull(groupManagementPage.getCalendarEvent(), "No calendar events are displayed.");
        seleniumActions.highLighterMethod(groupManagementPage.getCalendarEvent());
    }

    @Then("User select the {string} service from the left-side panel")
    public void userSelectTheServiceFromTheLeftSidePanel(String service) {
        homePage.navigateToSelectedService(service);
        groupManagementPage.closeToastMessage();
    }

    @And("User select the required event and open event details")
    public void userSelectTheRequiredEventAndOpenEventDetails() {
        groupManagementPage.selectExistingEvent();
        guestServiceRequestPage.switchToNewWindow();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User verifies the {string} & {string} section and views the existing event notes")
    public void userVerifiesTheSectionAndViewsTheExistingEventNotes(String firstSection, String secondSection) {
        Assert.assertTrue(groupManagementPage.isSectionDisplayed(firstSection), firstSection + " section is not displayed.");
        Assert.assertTrue(groupManagementPage.isSectionDisplayed(secondSection), secondSection + " section is not displayed");
        Assert.assertTrue(groupManagementPage.isSectionFirstRowDisplayed(secondSection), secondSection + " details are not displayed.");

    }

    @And("User click on plus icon in note and history page")
    public void userClickOnPlusIconInNoteAndHistoryPage() {
        groupManagementPage.clickOnPlusIcon();
    }

    @And("User fills in the required details in the Add Note box")
    public void userFillsInTheRequiredDetailsInTheAddNoteBox() {
        context.setContext("expectedNoteText", groupManagementPage.enterRequiredDetailsInTheAddNoteBox());
    }


    @And("User clicks the {string} button in the Add Note box")
    public void userClicksTheButtonInTheAddNoteBox(String arg0) {
       groupManagementPage.clicksTheButtonInTheAddNoteBox();

    }

    @And("User views the {string} button in note and history")
    public void userViewsTheButtonInNoteAndHistory(String arg0) {
        String expectedNoteText = context.getContext("expectedNoteText").toString();
        String actualNoteText = groupManagementPage.getAddedNoteText();
        Assert.assertEquals(actualNoteText, expectedNoteText, "Added note text does not match the expected text.");
        groupManagementPage.viewsTheButtonInNoteAndHistory();
    }

    @And("User click on the {string} button in note and history")
    public void userClickOnTheButtonInNoteAndHistory(String arg0) {
        groupManagementPage.clickOnTheButtonInNoteAndHistory();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User click on plus icon in item page")
    public void userClickOnPlusIconInItemPage() {
        groupManagementPage.clickOnPlusIconInItemPage();
    }

    @And("User fills in the required details in the item box")
    public void userFillsInTheRequiredDetailsInTheItemBox() {
        groupManagementPage.fillsInTheRequiredDetailsInTheItemBox();

    }

    @And("User views the {string} button in an item page")
    public void userViewsTheButtonInAnItemPage(String arg0) {
        groupManagementPage.viewsTheButtonInAnItemPage();
    }

    @And("User click on the {string} button in an item page")
    public void userClickOnTheButtonInAnItemPage(String arg0) {
        groupManagementPage.clickOnTheButtonInAnItemPage();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User views the Report Format pop-up box")
    public void userViewsTheReportFormatPopUpBox() {
        groupManagementPage.viewsTheReportFormatPopUpBox();
    }

    @And("User enters the email address in the New Message box")
    public void userEntersTheEmailAddressInTheNewMessageBox() {
        groupManagementPage.emailAddressInTheNewMessageBox();
    }

    @And("User enters all required fields Package page and clicks on the {string} button")
    public void userEntersAllRequiredFieldsPackagePageAndClicksOnTheButton(String buttonName) {
        groupManagementPage.PackagePageAndClicksOnTheButton(buttonName);
    }

    @And("User verify that pdf is downloaded")
    public void userVerifyThatPdfIsDownloaded() {
        File[] excelFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".xlsx") || name.toLowerCase().endsWith(".pdf"));
        Assert.assertTrue(excelFiles != null && excelFiles.length > 0, "Excel file was not downloaded.");
    }

    @Then("User should see the {string} button")
    public void userShouldSeeTheButton(String buttonName) {
        Assert.assertTrue(groupManagementPage.isAddEventButtonDisplayed(), buttonName + " button is not displayed.");
    }

    @And("User enters all the required details in the {string} dialog")
    public void userEntersAllTheRequiredDetailsInTheDialog(String arg0) {
        groupManagementPage.enterAllRequiredEventDetails();
    }

    @And("User clears the downloaded folder")
    public void userClearsDownloadedFolder() {

        Path folder = directory.toPath();

        if (Files.exists(folder) && Files.isDirectory(folder)) {

            try (DirectoryStream<Path> files = Files.newDirectoryStream(folder)) {

                for (Path file : files) {
                    if (Files.isRegularFile(file)) {
                        Files.delete(file);
                        System.out.println("Deleted: " + file.getFileName());
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Folder does not exist: " + directory);
        }


    }
    @And("User searches for the report title in the report and clicks the first matching title")
    public void userSearchesForTheReportTitleInTheReportAndClicksTheFirstMatchingTitle() {
        groupManagementPage.searchAndSelectFirstReport(SafetyManagementConstatnts.REPORT_FOLDER);
        groupManagementPage.clickFirstReport(SafetyManagementConstatnts.REPORT_FOLDER);
    }

    @And("search for the created guest user")
    public void searchForTheCreatedGuestUser() {
        shipSecurityPage.userSearchForTheGuest(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        String[] guestName = hotelMaintenancePage.getGuestName().split(" ");
        context.setContext("firstguestName", guestName[0].trim());
        context.setContext("guestName", guestName[1].trim());
    }

    @And("User searches for {string} in the report")
    public void userSearchesForInTheReport(String reportName) {
        groupManagementPage.searchAndSelectFirstReport(reportName);
    }

    @And("User clicks the first {string} matching title")
    public void userClicksTheFirstMatchingTitle(String reportName) {
        groupManagementPage.clickFirstReport(reportName);
    }

    @And("User creates a new event")
    public void userCreatesANewEvent() {
        groupManagementPage.isAddEventButtonDisplayed();
        commonPage.navigateTo(GroupManagementConstants.ADD_EVENTS);
        groupManagementPage.enterAllRequiredEventDetails();
        commonSteps.userClickTheButton(GroupManagementConstants.ADD);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User open link {string} and close pop message")
    public void userOpenLinkAndClosePopMessage(String TEXT) {
    commonSteps.userOpen(TEXT);
    commonPage.waitForElementToDisappear();
    //groupManagementPage.closingPopUpMessage();


    }

    @And("User enters date fields and clicks on the {string} button")
    public void userEntersDateFieldsAndClicksOnTheButton(String buttonName) {
        String date = commonFunctions.getDateIncrementedBy(-1, "M/d/yyyy");
        groupManagementPage.userEntersDateFieldsAndClicksOnTheButton(date,buttonName);
    }

    @And("User select financial department {string} in payment method")
    public void userSelectFinancialDepartmentInPaymentMethod(String arg0) {
        guestServicePage.iterateToGetEnabledValue();
        groupManagementPage.selectingTheDropdownValue();
    }

    @Then("User verifies that the event is created in the calendar view")
    public void userVerifiesThatTheEventIsCreatedInTheCalendarView() {
        WebElement calendarEvent = groupManagementPage.calendarEvent();
        Assert.assertTrue(calendarEvent.isDisplayed(), "Event was not displayed in the calendar view.");
    }
}
