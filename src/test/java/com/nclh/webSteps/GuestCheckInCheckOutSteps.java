package com.nclh.webSteps;

import com.nclh.base.BasePage;
import com.nclh.constants.GlobalConstants;
import com.nclh.constants.GuestCheckInCheckOutConstants;
import com.nclh.constants.PreconditionConstants;
import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.*;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import com.nclh.utilities.DateTimeUtils;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

@Slf4j
public class GuestCheckInCheckOutSteps extends AllDriverManager {

    Context context;
    CommonPage commonPage;
    GuestCheckInCheckOutPage guestCheckInCheckOutPage;
    CrewInformationPage crewInformationPage;
    CommonFunctions commonFunctions;
    GuestServicePage guestServicePage;
    CommonSteps commonSteps;
    GuestServiceRequestPage guestServiceRequestPage;
    ShipSecurityPage shipSecurityPage;
    GuestProfilePage guestProfilePage;
    BasePage basePage;
    ExcursionManagementPage excursionManagementPage;

    public GuestCheckInCheckOutSteps(Context context) {
        this.context = context;
        commonPage = new CommonPage(getDriver());
        guestCheckInCheckOutPage= new GuestCheckInCheckOutPage(getDriver());
        crewInformationPage = new CrewInformationPage(getDriver());
        commonFunctions = new CommonFunctions();
        guestServicePage = new GuestServicePage(getDriver());
        commonSteps = new CommonSteps(context);
        guestServiceRequestPage = new GuestServiceRequestPage(getDriver());
        shipSecurityPage = new ShipSecurityPage(getDriver());
        guestProfilePage = new GuestProfilePage(getDriver());
        basePage = new BasePage(getDriver());
        excursionManagementPage = new ExcursionManagementPage(getDriver());
    }

    @And("User should update the personal information details {string}")
    public void userShouldUpdateThePersonalInformationDetails(String lastName) {
        guestCheckInCheckOutPage.updateLastName(lastName);
    }

    @And("User need to verify the last name is updated {string}")
    public void userNeedToVerifyTheLastNameIsUpdated(String lastName) {
        String getLastName = guestCheckInCheckOutPage.getLastName();
        System.out.println("getLastName :: " + getLastName);
        Assert.assertTrue(getLastName.contains(lastName));
    }

    @And("User save the reservation status")
    public void userSaveTheReservationStatus() {
        String status = guestCheckInCheckOutPage.getReservationStatus();
        System.out.println("reservationStatus" + status);
        context.setContext("reservationStatus",status);
    }

    @Then("user verify the guest reservation")
    public void userVerifyTheGuestReservation() {
        String guestStatus = guestCheckInCheckOutPage.getGuestStatus();
        System.out.println("guestStatus" + guestStatus);
        Assert.assertEquals(context.getContext("reservationStatus"),guestStatus);
    }

    @Then("User save the guest details")
    public void userSaveTheGuestDetails() {
        String nationality = guestCheckInCheckOutPage.getNationality();
        context.setContext("nationality",nationality);
        String speaking = guestCheckInCheckOutPage.getSpeaking();
        context.setContext("speakingLanguage",speaking);
        String bookingNr =guestCheckInCheckOutPage.getBookingNr();
        context.setContext("bookingNr",bookingNr);
        String disembarkationDate = guestCheckInCheckOutPage.getDisembarkationDate();
        context.setContext("disembarkationDate",disembarkationDate);
//        String musterStation = guestCheckInCheckOutPage.getMusterStation();
//        context.setContext("musterStation",musterStation);
    }

    @Then("User verify the guest details on personal Information")
    public void userVerifyTheGuestDetailsOnPersonalInformation() {
        String getNationality = guestCheckInCheckOutPage.guestNationality();
        Assert.assertEquals(getNationality,context.getContext("nationality"));
        String getSpeakingLanguage = guestCheckInCheckOutPage.getPreferredLanguage();
        Assert.assertEquals(getSpeakingLanguage,context.getContext("speakingLanguage"));
    }

    @And("user verify the guest name")
    public void userVerifyTheGuestName() {
        String getLastName = guestCheckInCheckOutPage.getLastName();
        System.out.println("getLastName::"+getLastName);
        Assert.assertEquals(getLastName,context.getContext("nonSensitiveGuestFullName"));
    }

    @Then("User select {string} radio button")
    public void userSelectRadioButton(String name) {
        guestCheckInCheckOutPage.selectRouting(name);
    }

    @Then("User verify the add routing")
    public void userVerifyTheAddRouting() {
        String addGuestName = guestCheckInCheckOutPage.addRoutingGuestName();
        Assert.assertEquals(addGuestName,context.getContext("guestName"));
        String addGuestCabinNumber = guestCheckInCheckOutPage.addRoutingGuestCabinText();
        Assert.assertEquals(addGuestCabinNumber,context.getContext("CabinNumber"));
    }

    @Then("User verify the Do not auto check-out at Gangway toggle button is off")
    public void userVerifyTheDoNotAutoCheckOutAtGangwayToggleButtonIsOff() {
        String toggleStatus = guestCheckInCheckOutPage.toggleButtonStatus();
        Assert.assertEquals(toggleStatus,"false");
    }

    @And("User enable Do not auto check-out at Gangway toggle button")
    public void userEnableDoNotAutoCheckOutAtGangwayToggleButton() {
        guestCheckInCheckOutPage.clickDoNotAutoCheckoutAtGangway();
    }

    @Then("User verify the Do not auto check-out at Gangway toggle button is on")
    public void userVerifyTheDoNotAutoCheckOutAtGangwayToggleButtonIsOn() {
        String toggleStatus = guestCheckInCheckOutPage.toggleButtonStatus();
        Assert.assertEquals(toggleStatus,"true");
    }

    @Then("User verify the view all voyage details by date and port")
    public void userVerifyTheViewAllVoyageDetailsByDateAndPort() {
        String getMusterStation = guestCheckInCheckOutPage.musterStationInputField();
        Assert.assertEquals(getMusterStation,context.getContext("musterStation"));
//        String getDisembarkationDate = guestCheckInCheckOutPage.disembarkationDateInputField();
//        System.out.println("disembarkation ::"+getDisembarkationDate);
//        Assert.assertEquals(getDisembarkationDate,context.getContext("disembarkationDate"));
//        String getBookingNr = guestCheckInCheckOutPage.bookingNrInputField();
//        Assert.assertEquals(getBookingNr,context.getContext("bookingNr"));
    }

    @And("User verify the {string}")
    public void userVerifyThe(String text) {
        Assert.assertEquals(guestCheckInCheckOutPage.lastGangwaysMovementText(),text);
        Assert.assertTrue(guestCheckInCheckOutPage.checkLastGangwayText());
    }

    @Then("User save the embarkation date and port name")
    public void userSaveTheEmbarkationDateAndPortName() {
        String date = guestCheckInCheckOutPage.getEmbarkationDateText();
        context.setContext("embarkationDate",date);
        String port = guestCheckInCheckOutPage.getEmbarkationPortText().trim();
        context.setContext("embarkationPort",port);
    }

    @And("User verify the embarkation date and Port name")
    public void userVerifyTheEmbarkationDateAndPortName() {
//        WebElement element = guestCheckInCheckOutPage.getEmbarkationDateInputFieldText();
//        String textMessageDate  = commonFunctions.readToastMessage(element);
//        context.setContext("dateText", textMessageDate);
//        System.out.println("date text message : " + context.getContext("dateText"));
        WebElement elementPort = guestCheckInCheckOutPage.getEmbarkationPortInputFieldText();
        String textMessagePort  = commonFunctions.readToastMessage(elementPort).trim();
        context.setContext("portText", textMessagePort);
        System.out.println("Port text message : " + context.getContext("portText"));
//        Assert.assertEquals(context.getContext("embarkationDate"),context.getContext("dateText"));
        Assert.assertEquals(context.getContext("embarkationPort"),context.getContext("portText"));
    }

    @Then("User set date for {string} in add new guest reservation")
    public void userSetDateForInAddNewGuestReservation(String embarkationOrDisembarkation) {
        String date = null;
        if (embarkationOrDisembarkation.equals("Embarkation")) {
            date = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
        } else {
            date = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
        }
        guestCheckInCheckOutPage.setReservationsDates(embarkationOrDisembarkation, date);
    }

    @Then("User set disembarkation date for {string} in add new guest reservation")
    public void userSetDisembarkationDateForInAddNewGuestReservation(String embarkationOrDisembarkation) {
        String date = null;
        if (embarkationOrDisembarkation.equals("Embarkation")) {
            date = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
        } else {
            date = commonFunctions.getDateIncrementedBy(30, "M/d/yyyy");
        }
        guestCheckInCheckOutPage.setReservationsDates(embarkationOrDisembarkation, date);
    }

    @And("User click on add document in create new reservation")
    public void userClickOnAddDocumentInCreateNewReservation() {
        guestCheckInCheckOutPage.clickAddDocumentTab();
    }

    @Then("User set date for {string} in add new document guest reservation")
    public void userSetDateForInAddNewDocumentGuestReservation(String embarkationOrDisembarkation) {
        String date = null;
        if (embarkationOrDisembarkation.equals("Embarkation")) {
            date = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
        } else {
            date = commonFunctions.getDateIncrementedBy(-10000, "M/d/yyyy");
        }
        crewInformationPage.setDates(embarkationOrDisembarkation, date);
    }

    @And("User click on {string} in add photo page")
    public void userClickOnInAddPhotoPage(String button) {
        guestCheckInCheckOutPage.photoSubmitNextButton(button);
    }

    @And("User click on {string} in more information page")
    public void userClickOnInMoreInformationPage(String button) {
        guestCheckInCheckOutPage.reservationFinishButton(button);
    }

    @Then("User verify create a new reservation for guest member.")
    public void userVerifyCreateANewReservationForGuestMember() {
        String fullName = crewInformationPage.getFullName();
        String[] name = fullName.split(" ");

        String actual = name[0].toUpperCase().trim();
        log.info("Actual :: " + actual);

        String expected = context.getContext("firstAndLastNameText").toUpperCase().trim();
        log.info("Expected :: " + expected);

        String actualPrefix = actual.replaceAll("\\d", "");
        String expectedPrefix = expected.replaceAll("\\d", "");

        Assert.assertEquals(actualPrefix, expectedPrefix, "Name prefix does not match");
    }

    @And("User enter flight details")
    public void userEnterFlightDetails() {
        guestCheckInCheckOutPage.selectDirectionDropdown();
        guestCheckInCheckOutPage.enterFlightNumber();
        commonPage.openLinkUsingHref("calendar-clean");
        guestCheckInCheckOutPage.clickOnTodayDate();
        String text = guestCheckInCheckOutPage.getDirectionText();
        context.setContext("Direction",text);
        System.out.println(context.getContext("Direction"));
//        WebElement elementFlight = guestCheckInCheckOutPage.flightNumberText();
//        String flightNumberText  = commonFunctions.readToastMessage(elementFlight).trim();
//        context.setContext("flightNumber", flightNumberText);
//        System.out.println(context.getContext("flightNumber"));
    }

    @Then("User verify the flight details")
    public void userVerifyTheFlightDetails() {
        String text = guestCheckInCheckOutPage.getDirectionText();
        context.setContext("DirectionText",text);
        System.out.println(context.getContext("Direction"));
        Assert.assertEquals(context.getContext("Direction"),context.getContext("Direction"));
    }
    @Then("User verify guest name is updated in a profile page")
    public void userVerifyGuestNameIsUpdatedInProfilePage() {
        String afterUpdateGuestName = guestCheckInCheckOutPage.userGetsTheGuestNameText();
        String beforeUpdateGuestName = context.getContext("BEFORE_UPDATE_GUEST_NAME");
        Assert.assertNotEquals(afterUpdateGuestName, beforeUpdateGuestName, "Guest name was not updated. Still showing old name.");
    }

    @Then("User click on {string} tab and click on {string} in Cruise Information")
    public void userClickOnTabAndClickOnInCruiseInformation(String arg1, String arg2) {
        guestCheckInCheckOutPage.clickOnTabAndClick(arg1,arg2);
    }

    @And("User enter flight mandatory details")
    public void userEnterFlightMandatoryDetails() {
        guestCheckInCheckOutPage.selectDirectionDropdown();
        guestCheckInCheckOutPage.enterFlightNumber();
        String actualDirection = guestCheckInCheckOutPage.getDirectionText();
        context.setContext("Direction", actualDirection);
        Assert.assertFalse(actualDirection.trim().isEmpty(), "Direction is empty");
    }

    @And("User check the guest is {string} and {string}")
    public void userCheckTheGuestIsAnd(String filter, String filter1) {
        crewInformationPage.clearReservationStatus();
        guestServicePage.checkOptionInTheFilter(filter1);
        guestServicePage.addFilter(filter);
        guestServicePage.changeStatusOfFilter(filter, GlobalConstants.YES);
        guestServicePage.getGuestFirstName();
        guestServicePage.getGuestFullName();
    }

    @And("User is {string} for the respect guest")
    public void userIsForTheRespectGuest(String option) {
        crewInformationPage.selectAllAccountCheckbox();
        commonSteps.userClickOn(option);
        guestServicePage.setFinancialDepartmentDropDown();
        commonSteps.userClickOn(GuestCheckInCheckOutConstants.ADD);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User change the {string} for today")
    public void userChangeTheForToday(String date) {
        guestServicePage.openSubMenu(GuestCheckInCheckOutConstants.CRUISE_INFORMATION);
        crewInformationPage.setDisembarkationToday(date);
        guestServiceRequestPage.selectEmbarkationPortDropdown(GuestCheckInCheckOutConstants.DISEMBARKATION_PORT);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User search guest to update the name")
    public void userSearchGuestToUpdateName() {
        guestServicePage.searchUser(GuestCheckInCheckOutConstants.GUESTNAME_TO_UPDATE);
    }

    @And("User search guest with future expected date")
    public void searchExpectedFutureGuest() {
        guestServicePage.searchUser(GuestCheckInCheckOutConstants.FUTURE_EXPECTED_GUEST_USER);
    }

    @And("User search guest with boarding denied user")
    public void searchDeniedBoardingGuest() {
        guestServicePage.searchUser(GuestCheckInCheckOutConstants.BOARDING_DENIED_GUEST_USER);
    }

    @And("User search for a checked in guest")
    public void searchCheckedInGuest() {
        guestServicePage.searchUser(GuestCheckInCheckOutConstants.CHECKED_IN_GUEST_USER);
    }

    @And("User search for a checked in guest to add flight info")
    public void searchCheckedInFlightGuest() {
        guestServicePage.searchUser(GuestCheckInCheckOutConstants.CHECKED_IN_FLIGHT_INFO_GUEST_USER);
    }

    @And("User selects the {string} dropdown filter option as {string}")
    public void userSelectsTheDropdownFilterOptionAs(String filter, String option) {

        crewInformationPage.addMoreFilters();
        crewInformationPage.searchFilters(GuestCheckInCheckOutConstants.PREPAID);
        guestCheckInCheckOutPage.userSelectsTheDropdownFilterOptionAs(filter,option);
        guestServicePage.addFilter(GuestCheckInCheckOutConstants.ONBOARD);
    }


    @Then("User click on chain icon at top right pane")
    public void userClickOnChainIconAtTopRightPane() {
        List<String> warningText = guestServicePage.getWarningMessage();

        if (warningText.contains(GuestCheckInCheckOutConstants.ASHORE_DENIED)) {
            guestServicePage.openDropDown(GuestCheckInCheckOutConstants.LOST_AND_FOUND_SECURITY_INCIDENTS);
            shipSecurityPage.disableAshoreOnBoard();
            shipSecurityPage.userViewTheProfile();
            guestServicePage.refreshPage();
            shipSecurityPage.userViewTheProfile();
        }
        commonPage.openLinkUsingHref(GuestCheckInCheckOutConstants.QUICK_LINKS);
    }

    @Then("User searches for the newly created guest")
    public void userSearchesForTheNewlyCreatedGuest() {
        crewInformationPage.clearReservationStatus();
        guestServicePage.searchUser(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        List<WebElement> lastNames = guestCheckInCheckOutPage.getLastNames();
        Assert.assertFalse(lastNames.isEmpty(), "No last name elements were found in the guest search results.");
    }

    @And("User click on three dots and select Bulk Operation")
    public void userClickOnThreeDotsAndSelectBulkOperation() {
        guestCheckInCheckOutPage.userClickOnThreeDotsAndSelectBulkOperation();
    }


    @And("User views the confirmation pop-up and clicks Confirm")
    public void userViewsTheConfirmationPopUpAndClicksConfirm() {
        guestCheckInCheckOutPage.userViewsTheConfirmationPopUpAndClicksConfirm();
    }

    @And("User select the printer type in printer dropdown")
    public void userSelectThePrinterTypeInPrinterDropdown() {
        guestCheckInCheckOutPage.userSelectThePrinterTypeInPrinterDropdown();
    }

    @Then("User close the pop error message and left panel")
    public void userCloseThePopErrorMessageAndLeftPanel(){
        guestCheckInCheckOutPage.userCloseThePopErrorMessage();
        guestProfilePage.closeQuickLinks();
        context.setContext("SKIP_SCREENSHOT", "true");
    }
    @And("User enters all mandatory fields in the Fill Name and Date section")
    public void userEntersAllMandatoryFieldsInTheFillNameAndDateSection() {
        PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME = basePage.generateName(PreconditionConstants.FM_FIRST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME = basePage.generateName(PreconditionConstants.FM_LAST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME = PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME+" "+PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME;

        crewInformationPage.enterFirstName(PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME);
        crewInformationPage.enterLastName(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        log.info("#################################################");
        log.info("Created guest user full name is: {}", PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME);
        log.info("#################################################");
        basePage.type(commonPage.dateInput(PreconditionConstants.EMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDateByDays(5));
        basePage.type(commonPage.dateInput(PreconditionConstants.DISEMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDate(PreconditionConstants.DISEMBARKATION_DATE_MONTH));
    }

    @And("User selects the required cabin")
    public void userSelectsTheRequiredCabin() {
        guestCheckInCheckOutPage.guestCabinNumber();
    }

    @And("Clicks the Save and Next button")
    public void clicksTheSaveAndNextButton() {
        basePage.clickOnMatchingTextByIndex(PreconditionConstants.SAVE_NEXT_OPTIONAL_TXT, 2);
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
    }

    @And("Enter all the mandatory fields")
    public void enterAllTheMandatoryFields() {
        commonPage.enterDate(PreconditionConstants.DOC_EXPIRY_DATE_TXT, DateTimeUtils.getDate(PreconditionConstants.DOC_EXPITY_DATE_MONTH_VALUE));
        commonPage.enterDate(PreconditionConstants.BIRTH_DATE, DateTimeUtils.getDateYearsAgo(PreconditionConstants.GUEST_BIRTH_DATE_MONTH));
        guestServiceRequestPage.selectCategoryDropDown(PreconditionConstants.GENDER_DROPDOWN_TXT,PreconditionConstants.MALE_FIELD_TXT);
        crewInformationPage.enterDocumentNr(String.valueOf(excursionManagementPage.getRandomNumber()));
    }

    @And("Click on save and finish in add document section page")
    public void clickOnSaveAndFinishInAddDocumentSectionPage() {
        basePage.clickOnMatchingTextByIndex(PreconditionConstants.SAVE_AND_FINISH_TXT, 3);
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
    }

    @And("User unselects all guest members")
    public void userUnselectsAllGuestMembers() {
        guestCheckInCheckOutPage.unselectAllGuestMembers();
    }

    @And("Selects one guest member")
    public void selectsOneGuestMember() {
        guestCheckInCheckOutPage.selectsOneGuestMember();
    }

    @Then("User gets the guest name text")
    public void userGetsTheGuestNameText() {
        String beforeUpdateGuestName = guestCheckInCheckOutPage.userGetsTheGuestNameText();
        context.setContext("BEFORE_UPDATE_GUEST_NAME", beforeUpdateGuestName);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User adds the amount as a deposit to the guest account")
    public void userAddsTheAmountAsADepositToTheGuestAccount() {
        guestServicePage.openSubMenu(GuestCheckInCheckOutConstants.ACCOUNT_STATEMENT);
        commonSteps.userClickOn(GuestCheckInCheckOutConstants.ADD_DEPOSIT);
        guestServicePage.setFinancialDepartmentDropDown();
        guestServicePage.typeAmount();
        commonSteps.userClickOn(GuestCheckInCheckOutConstants.ADD);
        context.setContext("SKIP_SCREENSHOT", "true");

    }



}
