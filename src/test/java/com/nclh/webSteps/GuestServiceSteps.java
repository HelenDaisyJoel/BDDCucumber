package com.nclh.webSteps;

import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.*;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import com.nclh.utilities.SeleniumActions;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.assertTrue;

public class GuestServiceSteps extends AllDriverManager {

    Context context;
    GuestServicePage guestServicePage;
    SeleniumActions seleniumActions;
    CrewInformationPage crewInformationPage;
    ShipSecurityPage shipSecurityPage;
    GuestServiceRequestPage guestServiceRequestPage;
    HouseKeepingPage houseKeepingPage;
    HotelMaintenancePage hotelMaintenancePage;
    CommonFunctions commonFunctions;
    CommonPage commonPage;

    public GuestServiceSteps(Context context) {
        this.context = context;
        guestServicePage = new GuestServicePage(getDriver());
        seleniumActions = new SeleniumActions(getDriver());
        crewInformationPage = new CrewInformationPage(getDriver());
        commonFunctions = new CommonFunctions();
        commonPage = new CommonPage(getDriver());
        shipSecurityPage = new ShipSecurityPage(getDriver());
        houseKeepingPage = new HouseKeepingPage(getDriver());
        hotelMaintenancePage = new HotelMaintenancePage(getDriver());
        guestServiceRequestPage = new GuestServiceRequestPage(getDriver());
    }

    @Then("User report guest as missing")
    public void userReportGuestAsMissing() {
        commonPage.openLinkUsingHref("edit");
        shipSecurityPage.searchGuestAndMoveToOnboard("a");
    }

    @And("User search for cruise {string}")
    public void searchForCruise(String cruiseName) {
        guestServicePage.searchCruise(cruiseName);
    }

    @And("User navigated to {string} profile page")
    public void userNavigatedToCrewInfoPage(String crewFirstName) {
        if (crewFirstName.contains("Dynamic")) {
//            guestServicePage.navigateToCrewInformation(context.getContext("nonSensitiveGuestFirstName"));
            guestServicePage.navigateToCrewInformation(context.getContext("guestFirstName"));

        } else if (crewFirstName.contains("SavedList")) {
            guestServicePage.navigateToCrewInformation(context.getContext("guestFirstNameFromTheList"));
        } else {
            guestServicePage.openCrewInformation(crewFirstName);
        }
    }

    @And("User open service {string} item")
    public void openGuestServiceItem(String serviceItem) {
        guestServicePage.openGuestServiceItem(serviceItem);
    }

    @And("User verify and save status of the guest")
    public void userVerifyAndSaveInitialStatusOfTheGuest() {
        String initialStatus = guestServicePage.getStatus();
        System.out.println("initialStatus :: " + initialStatus);
        context.setContext("statusName", initialStatus);
    }

    @Then("User change loyalty status of the guest {string} and {string}")
    public void userChangeLoyaltyStatusOfTheGuestAnd(String loyaltyProgram, String loyaltyTier) {
        guestServicePage.changeStatus("Status", loyaltyProgram, loyaltyTier);
    }

    @Then("User verify loyalty status of the guest")
    public void userVerifyLoyaltyStatusOfTheGuest() {
        String latestStatus = guestServicePage.getStatus();
        System.out.println("latestStatus :: " + latestStatus);
        String expectedStatus = context.getContext("statusName");
        System.out.println("expectedStatus :: " + expectedStatus);
        Assert.assertNotEquals(latestStatus, expectedStatus);
    }

    @And("User refresh the page")
    public void userRefreshThePage() {
        guestServicePage.refreshPage();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User open guest {string} information details")
    public void userOpenGuestInformationDetails(String information) {
        guestServicePage.openDropDown(information);
    }

    @And("User edit cruise cabin information")
    public void userEditCruiseCabinInformation() {
        guestServicePage.editCabinInformation();
    }

    @Then("User search with cabin number and save")
    public void userSearchWithCabinNumber() {
        String cabinNumber = context.getContext("cabinNumber");
        System.out.println("cabinNumber :: " + cabinNumber);
        guestServicePage.searchAndSelectCabin(cabinNumber);
    }

    @And("User store the cabin number")
    public void userStoreTheCabinNumber() {
        String cabinNum = guestServicePage.getCabinNumber();
        context.setContext("cabinNumber", cabinNum);
        System.out.println(cabinNum);
    }

    @Then("User verify the cabin displayed on guest home page")
    public void userVerifyTheCabinDisplayedOnGuestHomePage() {
        String cabinNumberOnGuestPage = guestServicePage.getCabinNumberOnGuestPage();
        System.out.println("cabinNumberOnGuestPage :: " + cabinNumberOnGuestPage);
        String cabinNumber = context.getContext("cabinNumber");
        System.out.println("cabinNumber :: " + cabinNumber);
        Assert.assertEquals(cabinNumber, cabinNumberOnGuestPage);
    }

    @And("User add {string} filter to guest service page")
    public void userAddFilterToGuestServicePage(String filterType) {
        guestServicePage.addFilter(filterType);
    }

    @And("User select {string} filter option to {string}")
    public void userSelectFilterOptionTo(String filter, String option) {
        seleniumActions.sleep(2000);
        guestServicePage.changeStatusOfFilter(filter, option);
    }

    @And("User save guest name")
    public void userSaveGuestName() {
        String guestName = guestServicePage.getGuestName();
        context.setContext("guestName", guestName);
        System.out.println("guestName :: " + guestName);
    }

    @And("User search for Guest and navigated to {string} profile page")
    public void userSearchForGuestAndNavigatedToProfilePage(String guestFirstName) {
        if (guestFirstName.contains("Dynamic")) {
            String[] fullName = context.getContext("guestName").split(" ");
            guestServicePage.openCrewInformation(fullName[0]);
        }

    }

    @Then("Verify that the stateroom status icon is in {string} color for the guests that are onboarded")
    public void verifyThatTheStateroomStatusIconIsInGreenColorForTheGuestsThatAreOnboarded(String colour) {
        String[] fullName = context.getContext("guestName").split(" ");
        String colourCode = guestServicePage.getStateRoomStatus(fullName[0]);
        switch (colour) {
            case "green":
                Assert.assertEquals(Color.fromString(colourCode).asHex(), "#44a855");
                break;
            case "orange":
                Assert.assertEquals(Color.fromString(colourCode).asHex(), "#f2a126");
                break;
            default:
                System.out.println("Provide right colour or colour state not visible");
        }

    }

    @Then("User select {string} option from the filter")
    public void userSelectOptionFromTheFilter(String option) {
        guestServicePage.checkOptionInTheFilter(option);
//        context.setContext("SKIP_SCREENSHOT", "true");
        seleniumActions.sleep(6000);
    }

    @And("User set age filter to be adult")
    public void userSetAgeFilterToBeAdult() {
        guestServicePage.setAgeFilterToAdult();
    }

    @Then("User save guest first name")
    public void userSaveGuestFirstName() {
        String guestFirstName = guestServicePage.getGuestFirstName();
        context.setContext("guestFirstName", guestFirstName);
        System.out.println("guestFirstName :: " + guestFirstName);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User set date for {string}")
    public void userSetDateFor(String embarkationOrDisembarkation) {
        String date = null;
        if (embarkationOrDisembarkation.equals("Embarkation")) {
            date = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
        } else {
            date = commonFunctions.getDateIncrementedBy(30, "M/d/yyyy");
        }
        guestServicePage.setDates(embarkationOrDisembarkation, date);
    }

    @And("User enables qualified for checkIn for guest")
    public void userEnablesQualifiedForCheckInForGuest() {
        guestServicePage.enableCheckInToggle();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User open submenu {string} details")
    public void userOpenSubmenuDetails(String submenu) {
        guestServicePage.openSubMenu(submenu);
    }

    @Then("User set expiry date and upload document")
    public void userSetExpiryDateAndUploadDocument() throws AWTException {
        String date = commonFunctions.getDateIncrementedBy(365, "M/d/yyyy");
        guestServicePage.upLoadDocument();
        guestServicePage.setExpiryDate("Expiry", date);
    }

    @And("User select disembarkation port")
    public void userSelectDisembarkationPort() {
        guestServicePage.selectDisembarkationPort();
    }

    @And("User confirm and perform signature")
    public void userConfirmAndPerformSignature() {
        guestServicePage.drawSignature();
        commonPage.navigateTo("Sign & confirm");
    }

    @And("User set missing date and time")
    public void userSetMissingDateAndTime() {
        String date = commonFunctions.getDateIncrementedBy(-1, "M/dd/yyyy, h:mm a");
        guestServicePage.setDateAndTime(date);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User can see {string} flag on guest profile")
    public void userCanSeeFlagOnGuestProfile(String expectedFlag) {
        String guestFlag = guestServicePage.getFlagOnUserProfile();
        Assert.assertEquals(guestFlag, expectedFlag);
    }

    @Then("User select crew for reporting")
    public void userSelectCrewForReporting() {
        commonPage.openLinkUsingHref("edit");
        shipSecurityPage.searchGuestAndMoveToOnboard("a");
    }

    @Then("User should not see {string} flag on guest profile")
    public void userShouldNotSeeFlagOnGuestProfile(String expectedFlag) {
        boolean guestFlag = guestServicePage.getFlag();
        Assert.assertFalse(guestFlag);
    }

    @And("User enables {string} for guest")
    public void userEnablesForGuest(String toggleName) {
        guestServicePage.enableToggle(toggleName);
    }

    @And("User disAble {string} for guest")
    public void userDisAbleForGuest(String toggleName) {
        guestServicePage.disAbleToggle(toggleName);
    }

    @Then("User verify the {string} warning to guest profile")
    public void userVerifyTheWarningToGuestProfile(String warning) {
        List<String> warningText = guestServicePage.getWarningMessage();
        System.out.println("warningText :: " + warningText);
        Assert.assertTrue(warningText.contains(warning));
    }

    @And("User should provide {string} reason and reasonType {string}")
    public void userShouldProvideReasonAndReasonType(String reason, String reasonType) {
        guestServicePage.enterReasonForDeny(reason, reasonType);
    }

    @And("User open hidden filters")
    public void userClickOnFilterIconForHiddenFilters() {
        guestServicePage.openHiddenFilters();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User click on {string} link")
    public void userClickOnLink(String button) {
        commonPage.jsClickOnButton(button);
    }

    @And("User click on button {string}")
    public void userClickOnButton(String button) {
        guestServicePage.clickOnMissingFoundButton(button);
    }

    @And("User search and open the guest or crew {string} profile page")
    public void userSearchAndOpenTheGuestOrCrewProfilePage(String name) {
        guestServicePage.openCrewInformation(context.getContext("guestFirstName"));
    }

    @Then("User save guest full name")
    public void userSaveGuestFullName() {
        List<String> guestFullName = guestServicePage.getGuestFullName();
        String lastName = guestFullName.get(0);
        String firstName = guestFullName.get(1);
        String fullName = firstName + " " + lastName;
        context.setContext("guestFullName", fullName);
        context.setContext("guestFirstName", firstName);
        context.setContext("guestLastName", lastName);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User {string} qualified for checkIn for guest")
    public void userQualifiedForCheckInForGuest(String onOrOff) {
        guestServicePage.checkInToggle(onOrOff);
    }

    @And("User click on save button")
    public void userClickOnSaveButton() {
        guestServicePage.clickOnSaveButton();
    }

    @And("User set age filter to be {string}")
    public void userSetAgeFilterToBe(String age) {
        guestServicePage.setAgeFilterToBe(age);
    }

    @And("User save {string} guests count on guest service")
    public void userSaveGuestsCount(String checkedInOnboardOrAshore) {
        String guestCount = guestServicePage.getGuestCount(checkedInOnboardOrAshore);

        switch (checkedInOnboardOrAshore) {
            case "Checked In Ashore":
                System.out.println("Guest count at Ashore :: " + guestCount);
                context.setContext("GuestServiceGuestsAtCheckedInAshoreCount", guestCount);

                break;
            case "Checked In Onboard":
                System.out.println("Guest count at Onboard :: " + guestCount);
                context.setContext("GuestServiceGuestsAtCheckedInOnboardCount", guestCount);
                break;
        }
    }

    @And("User save the port name")
    public void userThePortName() {
        String portName = guestServicePage.getPortName();
        context.setContext("portName", portName);
    }

    @And("User set {string} port")
    public void userSetPort(String embarkOrDisembark) {
        String portName = context.getContext("portName");
        if (portName.equals("At Sea")) {
            guestServicePage.setPort(embarkOrDisembark);
        }
    }


    @And("verify user able to view guest folios")
    public void verifyUserAbleToViewGuestFolios() {
        guestServicePage.getFolioHeaders();
    }

    @And("User should change actual {string} and update to {string}")
    public void userShouldChangeActualAndUpdateTo(String actual, String updated) {
        guestServicePage.changePreferredLanguage(actual, updated);
    }

    @Then("User change the current cabin")
    public void userChangeTheCurrentCabin() {
        guestServicePage.changeCurrentCabin();
    }

    @And("User need to enter {string}")
    public void userNeedToEnter(String reason) {

        guestServicePage.cabinChangeReason(reason);
    }

    @And("User save the new cabin number")
    public void userSaveTheNewCabinNumber() {
        String cabinNUmber = guestServicePage.getCabinNumberToUpdate();
        System.out.println("cabinNumberToUpdate :: " + cabinNUmber);
        context.setContext("updatedCabinNumber", cabinNUmber);
    }

    @And("User need to verify the cabin number is updated")
    public void userNeedToVerifyTheCabinNumberIsUpdated() {
        String presentCabinNumber = guestServicePage.getCabinNumberOnGuestPage();
        System.out.println(presentCabinNumber);
        System.out.println(context.getContext("updatedCabinNumber"));
        Assert.assertEquals(presentCabinNumber, context.getContext("updatedCabinNumber"));
    }

    @Then("User save the country {string}")
    public void userSaveTheCountry(String country) {
        String countryName = guestServicePage.saveTheCountry(country);
        System.out.println("updatedCountryName :: " + countryName);
        context.setContext("updatedCountryName", countryName);
    }

    @And("User need to verify the speaking language is updated")
    public void userNeedToVerifyTheSpeakingLanguageIsUpdated() {
        String changedLanguage = guestServicePage.getLanguageSpeaking();
        System.out.println(changedLanguage);
        System.out.println(context.getContext("updatedCountryName"));
        Assert.assertEquals(changedLanguage, context.getContext("updatedCountryName"));
    }

    @And("User check the Date of Birth")
    public void userCheckTheDateOfBirth() {
        System.out.println(guestServicePage.getDateOfBirth());
        Assert.assertTrue(guestServicePage.getDateOfBirth());
    }

    @And("User click on special request")
    public void userClickOnSpecialRequest() {
        guestServicePage.clickOnSpecialRequest();
    }

    @Then("User click on add icon")
    public void userClickOnAddIcon() {
        guestServicePage.clickOnAddIcon();
    }

    @And("User select Special Request")
    public void userSelectSpecialRequest() {
        String request = guestServicePage.setSpecialRequest();
        System.out.println(request);
        context.setContext("request",request);
    }

    @And("User verify the special request is added to the list")
    public void userVerifyTheSpecialRequestIsAddedToTheList() {
        List<String> specialRequestList = guestServicePage.getSpecialRequestList();
        System.out.println(specialRequestList);
        String selectedRequest = context.getContext("request");
        Assert.assertTrue(specialRequestList.contains(selectedRequest),
                "The special request '" + selectedRequest + "' was not found in the list.");
    }

    @And("User click on Onboard Credits")
    public void userClickOnOnboardCredits() {
        guestServicePage.clickOnOnboardCredits();
    }

    @And("User select Category {string}")
    public void userSelectCategory(String category) {
        guestServicePage.setCategory(category);
    }

    @And("User select Non Refundable OnBoard credits {string}")
    public void userSelectNonRefundableOnBoardCredits(String onBoardCredits) {
        guestServicePage.setNonRefundableDropDown(onBoardCredits);
    }

    @And("User save the cabin number")
    public void userSaveTheCabinNumber() {
        String presentCabinNumber = guestServicePage.getCabinNumberOnGuestPage();
        System.out.println("cabinNumber :: " + presentCabinNumber);
        context.setContext("cabinNumber", presentCabinNumber);

    }

    @And("User search for guest in cabin readiness search box")
    public void userSearchForGuestInCabinReadinessSearchBox() {
        String name = context.getContext("nonSensitiveGuestLastName");
        guestServicePage.typeInSearchBox(name);
    }

    @And("User click on arrow button")
    public void userClickOnArrowButton() {
        guestServicePage.clickOnArrow();
    }

    @And("User verify the guest first name")
    public void userVerifyTheGuestFirstName() {
        String actualFirstName = context.getContext("nonSensitiveGuestLastName");
        System.out.println(actualFirstName);
        List<String> nameList = guestServicePage.getFirstNameTextList();
        Assert.assertTrue(nameList.contains(actualFirstName));

    }

    @And("User Verify the cabin number")
    public void userVerifyTheCabinNumber() {
        String textField = guestServicePage.getCabNumberText();
        String[] parts = textField.split("-");
        String number = parts[1];
        System.out.println("Extracted number: " + number);
        Assert.assertEquals(number, context.getContext("cabinNumber"));
    }

    @And("User select code")
    public void userSelectCode() {
       String code = guestServicePage.setCodeDropDown();
       System.out.println(code);
       context.setContext("code",code);
    }

    @And("User select Category for special request")
    public void userSelectCategoryForSpecialRequest() {
        guestServicePage.setCategorySpecialRequest();
    }

    @And("User select origin")
    public void userSelectOrigin() {
        guestServicePage.setOrigin();
    }

    @And("User verify the special request is added to the  complaint list")
    public void userVerifyTheSpecialRequestIsAddedToTheComplaintList() {
        List<String> specialRequestList = guestServicePage.getSpecialRequestComplaintList();
        System.out.println(specialRequestList);
        String code = context.getContext("code").toUpperCase();
        Assert.assertTrue(specialRequestList.contains(code),
                "The special request '" + code + "' was not found in the list.");
    }

    @And("USer select collector department {string}")
    public void userSelectCollectorDepartment(String collector) {
        String collectorName = collector.replaceAll("[0-9]", "");

        guestServicePage.setCollectorDropDown(collectorName.substring(2).trim());
    }

    @And("User type the limit per cruise {string}")
    public void userTypeTheLimitPerCruise(String name) {
        guestServicePage.typeLimit(name);
    }

    @And("User select payment department {string}")
    public void userSelectPaymentDepartment(String payment) {
        String paymentName = payment.replaceAll("[0-9]", "");
        System.out.println(paymentName);
        System.out.println(paymentName.substring(2));
        guestServicePage.setPaymentDepartmentDropDownDropDown(paymentName.substring(2).trim());
    }

    @And("User Verify the payment department {string}")
    public void userVerifyThePaymentDepartment(String payment) {
        String paymentName = guestServicePage.getTextOfPayment(payment);
        Assert.assertEquals(payment, paymentName);
        System.out.println("assertion is done");
    }

    @And("User navigate to folio {string}")
    public void userNavigateToFolio(String folio) {
        guestServicePage.clickOnFolio(folio);
    }

    @And("User select financial department {string}")
    public void userSelectFinancialDepartment(String folio) {
        guestServicePage.setFinancialDepartmentDropDown();
    }

    @And("User type the amount")
    public void userTypeTheAmount() {
        guestServicePage.typeAmount();
    }

    @And("User verify that folio {string} is added to table")
    public void userVerifyThatFolioIsAddedToTable(String department) {
        List<String> departmentList = guestServicePage.getFolioDepartmentList();
        System.out.println(departmentList);
        Assert.assertTrue(departmentList.contains(department),
                "The department '" + department + "' was not found in the list.");
    }

    @And("User save the amount")
    public void userSaveTheAmount() {
        String folioAmount = guestServicePage.getFolioAmountValue();
        context.setContext("amount", folioAmount);
        System.out.println(folioAmount);
        WebElement subDepartment = guestServicePage.getSubDepartmentIdFieldTextInputFieldText();
        String textMessageMubDepartment = commonFunctions.readToastMessage(subDepartment).trim();
        context.setContext("subDepartmentText", textMessageMubDepartment);
        System.out.println("subDepartment text message : " + context.getContext("subDepartmentText"));
    }

    @Then("User verify the {string} folio")
    public void userVerifyTheFolio(String columnName) {
        String text = guestServicePage.getDepartmentText(columnName);
        System.out.println(text);
    }


    @And("User save the {string} of the first request")
    public void userSaveTheOfTheFirstRequest(String columnName) {
        String text = guestServicePage.getQuantityText(columnName);
        System.out.println(text);
        context.setContext("Quantity", text);
    }

    @And("User click on edit and edit the quantity")
    public void userClickOnEditAndEditTheQuantity() {
        guestServicePage.clickOnEdit();
    }

    @Then("verify that {string} is updated")
    public void verifyThatIsUpdated(String columnName) {
        String updated = guestServicePage.getQuantityText(columnName);
        System.out.println(updated);
        context.setContext("UpdatedQuantity", updated);
        int toNumber = Integer.parseInt(context.getContext("Quantity")) + 1;
        String toNumberString = String.valueOf(toNumber);
        Assert.assertEquals(context.getContext("UpdatedQuantity"), toNumberString);
    }

    @And("User reEdit the confirm button")
    public void userReEditTheConfirmButton() {
        String cabinNumber = context.getContext("cabinNumber");
        System.out.println("cabinNumber :: " + cabinNumber);
        guestServicePage.reEditConfirm(cabinNumber);
    }

    @And("User select the printer")
    public void userSelectThePrinter() {
        guestServicePage.selectPrinterDropDown();
    }

    @And("User click on Addresses")
    public void userClickOnAddresses() {
        guestServicePage.clickOnAddresses();
    }

    @And("User verify that zip code is displayed")
    public void userVerifyThatZipCodeIsDisplayed() {
        System.out.println(guestServicePage.checkZipCodeIsDisplayed());
        Assert.assertTrue(guestServicePage.checkZipCodeIsDisplayed());
    }

    @Then("User click on eye opened if its closed")
    public void userClickOnEyeOpenedIfItsClosed() {
        if (guestServicePage.checkEyeIsOpened()) {
            guestServicePage.clickOnOpenedEye();
        }
    }

    @Then("User click on Flight Info tab")
    public void userClickOnFlightInfoTab() {
        guestServicePage.clickOnFlightInfo();
    }

    @And("User click on Add flight info if present")
    public void userClickOnAddFlightInfoIfPresent() {
        if (guestServicePage.checkAddFlightInfo()) {
            guestServicePage.clickOnAddFlightInfo();
        }
    }

    @And("User verify {string} dropdown is present")
    public void userVerifyDropdownIsPresent(String airport) {
        Assert.assertTrue(guestServicePage.selectDepartureAirport(airport));

    }

    @And("User verify the saved linked cabins")
    public void userVerifyTheSavedLinkedCabins() {
        String savedCabins = guestServicePage.getSavedLinkedCabins();
        System.out.println(savedCabins);
        List<String> linkedCabins = context.getListContext("linkedCabins");
        System.out.println(linkedCabins);
        String linkedCabinsString = String.join(", ", linkedCabins);
        //Assert.assertEquals(savedCabins,linkedCabinsString);

    }

    @And("User select the salutation dropdown")
    public void userSelectTheSalutationDropdown() {
        String salutation = guestServicePage.selectSalutationDropdown();
        System.out.println(salutation);
        context.setContext("salutation", salutation);
    }


    @And("User verify get the guest full name with salutation")
    public void userVerifyGetTheGuestFullNameWithSalutation() {
        String salutationName = guestServicePage.getGuestFullNameSalutaion();
        System.out.println(salutationName);
        String salutation = context.getContext("salutation");
        System.out.println(salutation);
        Assert.assertTrue(salutationName.contains(salutation));
      //  Assert.assertTrue(salutationName.contains("Test"));
    }

    @And("User select the classification type and classificationsId")
    public void userSelectTheClassificationTypeAndClassificationsId() {
        String type = guestServicePage.selectClassificationType();
        System.out.println(type);
        context.setContext("ClassificationType", type);
        String id = guestServicePage.selectClassifications();
        System.out.println(id);
        context.setContext("ClassificationsId", id);
    }

    @And("User verify that status is updated in guest profile")
    public void userVerifyThatStatusIsUpdatedInGuestProfile() {
        String statusText = guestServicePage.statusTextInProfile();
        System.out.println(statusText);
        String type = context.getContext("ClassificationType");
        System.out.println(type);
        String id = context.getContext("ClassificationsId");
        System.out.println(id);
        Assert.assertTrue(statusText.contains(type));
        Assert.assertTrue(statusText.contains(id));
    }

    @And("User select the {int} linked cabins")
    public void userSelectTheLinkedCabins(int selectCabins) {
        List<String> linkedCabins = guestServicePage.clickOnLinkedCabins(selectCabins);
        System.out.println(linkedCabins);
        context.setListContext("linkedCabins", linkedCabins);
    }

    @Then("User verify the added department is displayed in account statement")
    public void userVerifyTheAddedDepartmentIsDisplayedInAccountStatement() {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        List<String> deptList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            deptList.add(jse.executeScript("return document.querySelectorAll(\"[aria-colindex='5'][class='dx-hidden-cell']\")[" + i + "].innerHTML").toString());
        }

        System.out.println(deptList);
        System.out.println(context.getContext("subDepartmentText"));
//        List<String> departmentList = guestServicePage.getDepartmentList();
//        System.out.println(departmentList
        Assert.assertTrue(deptList.contains(context.getContext("subDepartmentText")));
    }

    @And("User uncheck the remaining guests")
    public void userUncheckTheRemainingGuests() {
        guestServicePage.removeGuests();

    }

    @Then("User select the {string} dropdown as {string}")
    public void userSelectTheDropdownAs(String dropdown, String option) {
        guestServiceRequestPage.selectCategoryDropDown(dropdown,option);
    }

    @Then("User select the {string} and select")
    public void userSelectTheAndSelect(String dropdown) {
        guestServiceRequestPage.selectNonRefundableDropdown(dropdown);
    }

    @And("User save the loyalty ID of guest")
    public void userSaveTheLoyaltyIDOfGuest() {
        String status = guestServicePage.getLoyaltyNumber();
        System.out.println(status);
        String[] parts = status.split("/ ");
        String number = parts[1];
        System.out.println(number);
        context.setContext("loyalty",number);

    }

    @And("User select {string} filter and type loyalty number")
    public void userSelectFilterAndTypeLoyaltyNumber(String filterName) {
        String number = context.getContext("loyalty");
        guestServicePage.typeLoyaltyNumber(filterName,number);
    }

    @Then("the user should click the {string} tab, and select {string} under Financial Information")
    public void theUserShouldClickTheTabAndSelectUnderFinancialInformation(String tab, String button2) {
        guestServicePage.clickOnTabAndClickAddRouting(tab,button2);

    }

    @And("User click on Plus button to add lost item")
    public void userClickOnPlusButtonToAddLostItem(){
       guestServicePage.clickOnPlusButtonInLostAndFound();
    }

    @And("User select item dropdown")
    public void userSelectItemDropdown() {
        guestServicePage.setItemDropdown();
    }

    @And("User click on lost by icon")
    public void userClickOnLostByIcon() {
        commonPage.openLinkUsingHref("add-user");
    }

    @And("User click on lost and found tab")
    public void userClickOnLostAndFoundTab() {
        guestServicePage.clickOnLostAndFound();
    }

    @And("User Verify lost and found item visible in guest page")
    public void userVerifyLostAndFoundItemVisibleInGuestPage() {
        Assert.assertTrue(guestServicePage.lostAndFoundItemDisplayed());
    }

    @And("User confirm and perform signature and delete it")
    public void userConfirmAndPerformSignatureAndDeleteIt() {
        guestServicePage.drawSignature();
        commonPage.openLinkUsingHref("delete");
        commonPage.navigateTo("Sign & confirm");
        String name = context.getContext("nonSensitiveGuestFirstName");
        guestServicePage.clickOnGuestName(name);
    }

    @And("User can get the embarkation date and disembarkation date")
    public void userCanGetTheEmbarkationDateAndDisembarkationDate() {
        String embarkation = guestServicePage.getEmbarkationDate();
        String disembarkation = guestServicePage.getDisembarkationDate();
        System.out.println(embarkation);
        System.out.println(disembarkation);
    }

    @And("User select the classification type as VIP and classificationsId")
    public void userSelectTheClassificationTypeAsVIPAndClassificationsId() {
        String type = guestServicePage.selectClassificationTypeVIP();
        System.out.println(type);
        context.setContext("ClassificationType", type);
        String id = guestServicePage.selectClassifications();
        System.out.println(id);
        context.setContext("ClassificationsId", id);
    }

    @Then("User select the {string} and select code {string}")
    public void userSelectTheAndSelectCode(String dropdown, String code) {
        guestServicePage.selectCodeNewspaperDropdown(dropdown,code);
    }
  
    @And("User Verify travel documents are visible in documents dashboard")
    public void userVerifyTravelDocumentsAreVisibleInDocumentsDashboard() {
        Assert.assertTrue(guestServicePage.documentsDataDisplayed());
    }

    @And("User select {string} filter and type lost cabin number")
    public void userSelectFilterAndTypeLostCabinNumber(String filterName) {
        String number = context.getContext("CabinNumber");
        guestServicePage.typeLostCabinNumber(filterName,number);
    }

    @And("User click on edit and change the lost item")
    public void userClickOnEditAndChangeTheLostItem() {
        guestServicePage.clickOnEditInLost();
        guestServicePage.editItemDropdown();
    }

    @And("User verify amenity is added to guest profile")
    public void userVerifyAmenityIsAddedToGuestProfile() {
        List<String> amenitiesListData =  houseKeepingPage.getamenitiesList();
        System.out.println(amenitiesListData);
        String code = context.getContext("AmenityCode");
        System.out.println(code);

        boolean found = amenitiesListData.stream()
                .anyMatch(amenity -> code.contains(amenity));

        assertTrue("Amenity code '" + code + "' not found in amenities list " + amenitiesListData, found);

    }

    @And("User type cabin number in filter in housekeeping")
    public void userTypeCabinNumberInFilterInHousekeeping() {
        //String cabin = context.getContext("CabinNumber");
        String cabin = "10202";
        guestServicePage.searchLocationHousekeeping(cabin);
    }

    @Then("User set date for {string} in add new document on guest reservation")
    public void userSetDateForInAddNewDocumentOnGuestReservation(String embarkationOrDisembarkation) {
        String date = null;
        if (embarkationOrDisembarkation.equals("Embarkation")) {
            date = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
        } else {
            date = commonFunctions.getDateIncrementedBy(-10000, "M/d/yyyy");
        }
        crewInformationPage.setDatesExpiryOrBirth(embarkationOrDisembarkation, date);
    }

    @And("User search {string} in add filter and click on {string}")
    public void userSearchInAddFilterAndClickOn(String search, String filterName) {
        guestServicePage.addDisembarkationFilter(search,filterName);
    }

    @And("User click on disembarkation date arrow down icon and select {string}")
    public void userClickOnDisembarkationDateArrowDownIconAndSelect(String date) {
        guestServicePage.guestSearchDisembarkationDateFilter(date);

    }

    @And("User verifies search results only show reservation status as {string}")
    public void userVerifiesReservationStatus(String status) {
        List<String> statusList = guestServicePage.getHighlightedReservationStatusListOfAll();
        System.out.println(statusList);
        Assert.assertTrue(statusList.contains(status),
                "The status list has '" + status + "'  found in the list.");
    }

    @Then("User unselects {string} option from the filter")
    public void userUnSelectsOptionFromTheFilter(String option) {
        guestServicePage.uncheckOptionFromTheFilter(option);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User validates the {string} with total list count")
    public void userValidatesTheCounts(String checkedInType) {

        String contextKey;

//        switch (checkedInType.trim().toLowerCase()) {
        switch (checkedInType.trim()) {

            case "Checked In Ashore":
                contextKey = "GuestServiceGuestsAtCheckedInAshoreCount";
                break;

            case "Checked In Onboard":
                contextKey = "GuestServiceGuestsAtCheckedInOnboardCount";
                break;

            default:
                throw new IllegalArgumentException(
                        "Invalid CheckedInType provided in feature file: " + checkedInType);
        }

        int expectedCount = Integer.parseInt(
                context.getContext(contextKey).toString()
        );

        int actualCount = commonPage.getTotalItemsCount();

        System.out.println("Validating for: " + checkedInType);
        System.out.println("Expected Count: " + expectedCount);
        System.out.println("Actual Count: " + actualCount);

        Assert.assertEquals(actualCount, expectedCount,
                "Mismatch in counts for: " + checkedInType);
    }
    @And("User add Loyalty Number {string} filter to guest service page")
    public void userAddLoyaltyNumberFilterToGuestServicePage(String filterType) {
        guestServicePage.addLoyaltyFilter(filterType);

    }

    @And("User verify the guest")
    public void userVerifyTheGuest() {
        String actual = context.getContext("guestFirstName");
        System.out.println(actual);
        String expected = context.getContext("nonSensitiveGuestLastName");
        System.out.println(expected);
        Assert.assertEquals(actual,expected);

    }

    @And("User get the guest full name")
    public void userGetTheGuestFullName() {
        String salutationName = guestServicePage.getGuestFullNameSalutaion();
        System.out.println(salutationName);
        String salutation = context.getContext("salutation");
        System.out.println(salutation);
    }

    @And("User get guest age, gender")
    public void userGetGuestAgeGender() {
        String age = guestServicePage.getGuestAgeGenderText();
        System.out.println(age);
        System.out.println(age);
    }

    @And("User open service {string} item in guest page")
    public void userOpenServiceItemInGuestPage(String serviceItem) {
        guestServicePage.openCreateMaintenanceRequest(serviceItem);

    }

    @Then("User click on add icon in special request")
    public void userClickOnAddIconInSpecialRequest() {
        guestServicePage.clickOnSpecialRequestAdd();
    }

    @And("User edit the guest last name")
    public void userEditTheGuestLastName() {
        guestServicePage.editLastName();
    }

    @Then("the user should click the {string} tab, and get the payment methods")
    public void theUserShouldClickTheTabAndGetThePaymentMethods(String tab) {
        List<String> PaymentMethodData =  guestServicePage.clickOnTabAndGetPaymentMethod(tab);
        System.out.println(PaymentMethodData);
    }

    @And("User unselects same cabin guests")
    public void userUnselectsSameCabinGuests() {
        guestServicePage.clearGuestCheckBoxes();
    }

    @And("User validate same cabin guests cabins are also updated")
    public void userValidateSameCabinGuestsCabinsAreAlsoUpdated() {
        List<String> cabinList = guestServicePage.getCabinsNumbers();
        System.out.println(cabinList);
        String cabinNumber = context.getContext("updatedCabinNumber");
        System.out.println("cabinNumber :: " + cabinNumber);
        for (String cabin : cabinList) {
            Assert.assertEquals(
                    cabin,
                    cabinNumber,
                    "Mismatch found. Expected: " + cabinNumber + " but found: " + cabin
            );
        }
    }

    @And("User save the count of folios before adding")
    public void userSaveTheCountOfFoliosBeforeAdding() {
        int count = guestServicePage.getFolioCount();
        System.out.println(count);
        context.setContext("Count",String.valueOf(count));
    }

    @And("User click on delete icon")
    public void userClickOnDeleteIcon() {
        //guestServicePage.clickOnDeleteAshoreDenied();
        hotelMaintenancePage.openLinkUsingHref("delete");


    }

    @Then("User verify Successfully toast message appears on the screen with message {string}")
    public void userVerifySuccessfullyToastMessageAppearsOnTheScreenWithMessage(String message) {
        String ToastMessage = guestServicePage.getSuccessfulToastMessage();
        Assert.assertTrue(ToastMessage.contains(message));
    }

    @And("User Verify able to view package consumption data")
    public void userVerifyAbleToViewPackageConsumptionData() {
        Assert.assertTrue(guestServicePage.getLogsList());
    }

    @And("User click on onboard toggle")
    public void userClickOnOnboardToggle() {
        guestServicePage.clickOnOnboard();
    }

    @And("User Verify loyalty tire of the guest")
    public void userVerifyLoyaltyTireOfTheGuest() {
        String loyaltyTireInStatus = guestServicePage.getLoyaltyTireText();
        System.out.println(loyaltyTireInStatus);
        String verify = context.getContext("statusName");
       // Assert.assertEquals(loyaltyTireInStatus,verify);
        Assert.assertTrue(verify.contains(loyaltyTireInStatus));

    }

    @And("User click on Absences tab")
    public void userClickOnAbsencesTab() {
        guestServicePage.clickOnAbsences();
        guestServicePage.clickNewAbsencesButton();
    }

    @And("User Verify the excursion radio button is selected")
    public void userVerifyTheExcursionRadioButtonIsSelected() {
        Assert.assertTrue(guestServicePage.verifyExcursionRadioButton());
    }

    @And("User select item as {string}")
    public void userSelectItemAs(String item) {
        guestServicePage.setItemDropdownAs(item);
    }

    @And("User click On Kebab icon and select return")
    public void userClickOnKebabIconAndSelectReturn() {
        guestServicePage.clickOnKebabIcon();
    }

    @And("User click on found by icon")
    public void userClickOnFoundByIcon() {
        commonPage.openLinkUsingHref("add-user");
    }

    @Then("User click on document tab")
    public void userClickOnDocumentTab() {
        guestServicePage.clickOnDocumentTab();
    }

    @And("User verify that travel document is visible")
    public void userVerifyThatTravelDocumentIsVisible() {
        System.out.println(guestServicePage.checkDocumentIsDisplayed());
        Assert.assertTrue(guestServicePage.checkDocumentIsDisplayed());
    }

    @And("User Verify that quick-links is opened")
    public void userVerifyThatQuickLinksIsOpened() {
        Assert.assertTrue(guestServicePage.verifyQuickLinksHeading());
    }

    @And("User add Loyalty Tire {string} filter to guest service page")
    public void userAddLoyaltyTireFilterToGuestServicePage(String filterType) {
        guestServicePage.addLoyaltyTireFilter(filterType);
    }

    @Then("User verify loyalty tire is updated to {string}")
    public void userVerifyLoyaltyTireIsUpdatedTo(String expectedLoyalty) {
        List<String> loyalityList = guestServicePage.getLoyaltyListListOfAll();
        System.out.println(loyalityList);
        Set<String> loyaltyPostingList = new HashSet<>(loyalityList);
        System.out.println("postingList :: " + loyaltyPostingList);
        List<String> actualPriority = new ArrayList<>(loyaltyPostingList);
        System.out.println(actualPriority);
        String actualPriorityString = String.join(",", actualPriority);
        System.out.println("Actual Priority (String): " + actualPriorityString);
        Assert.assertEquals(expectedLoyalty, actualPriorityString);
    }

    @And("User click on delete special request icon")
    public void userClickOnDeleteSpecialRequestIcon() {
        guestServicePage.clickOnDeleteSpecialRequest();
    }

    @And("User save the booking number of guest")
    public void userSaveTheBookingNumberOfGuest() {
        String number = guestServicePage.getBookingNumber();
        System.out.println(number);
        context.setContext("bookingNumber",number);

    }

    @And("User search guest with booking number")
    public void userSearchGuestWithBookingNumber() {
       String number = context.getContext("bookingNumber");
        guestServicePage.searchUser(number);

    }

    @And("User click on booking info")
    public void userClickOnBookingInfo() {
        guestServicePage.clickOnBookingInfo();
    }
}



