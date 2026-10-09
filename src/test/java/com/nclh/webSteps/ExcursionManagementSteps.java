package com.nclh.webSteps;

import com.nclh.base.BasePage;
import com.nclh.constants.ExcursionManagementConstants;
import com.nclh.constants.GlobalConstants;
import com.nclh.constants.PreconditionConstants;
import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.*;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import com.nclh.utilities.SeleniumActions;
import com.sun.source.tree.AssertTree;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExcursionManagementSteps extends AllDriverManager {

    Context context;
    ExcursionManagementPage excursionManagementPage;
    CrewInformationPage crewInformationPage;
    SafetyManagementPage safetyManagementPage;
    CommonFunctions commonFunctions;
    CommonPage commonPage;
    GuestServicePage guestServicePage;
    HotelMaintenancePage hotelMaintenancePage;
    HomePage homePage;
    CommonSteps commonSteps;
    LoginPage loginPage;
    GuestServiceRequestPage guestServiceRequestPage;
    SeleniumActions seleniumActions;
    ShipSecurityPage shipSecurityPage;
    BasePage basePage;
    public static final Logger logger
            = LoggerFactory.getLogger(SeleniumActions.class);

    public ExcursionManagementSteps(Context context) {
        this.context = context;
        commonPage = new CommonPage(getDriver());
        commonFunctions = new CommonFunctions();
        safetyManagementPage = new SafetyManagementPage(getDriver());
        excursionManagementPage = new ExcursionManagementPage(getDriver());
        crewInformationPage = new CrewInformationPage(getDriver());
        guestServicePage = new GuestServicePage(getDriver());
        hotelMaintenancePage = new HotelMaintenancePage(getDriver());
        homePage = new HomePage(getDriver());
        commonSteps = new CommonSteps(context);
        loginPage = new LoginPage(getDriver());
        guestServiceRequestPage = new GuestServiceRequestPage(getDriver());
        seleniumActions = new SeleniumActions(getDriver());
        shipSecurityPage = new ShipSecurityPage(getDriver());
        basePage = new BasePage(getDriver());
    }

    @Then("User click on plus button")
    public void user_click_on_plus_button() {
        excursionManagementPage.clickOnPlusButton();
    }

    @Then("User click edit on Tour Activities page")
    public void userClickEditOnTourActivitiesPage() {
        excursionManagementPage.clickOnEdit();
    }

    @Then("User save tour template code")
    public void userSaveTourTemplateCode() {
        String tourTemplateCode = excursionManagementPage.getTourTemplateCode();
        context.setContext("tourTemplateCode", tourTemplateCode);
        System.out.println("tourTemplateCode :: " + tourTemplateCode);
    }

    @And("User edit Article General Information {string} and {string}on tour template")
    public void userEditArticleGeneralInformationAndOnTourTemplate(String description, String article) {
        excursionManagementPage.editExternalDescription(description);
        excursionManagementPage.selectArticle(article);
    }

    @And("User edit Article General Information on tour template page")
    public void userEditArticleGeneralInformationOnTourTemplatePage(DataTable dataTable) {
        List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dataMap : dataRow) {
            String description = dataMap.get("External Template Description");
            String article = dataMap.get("Article Class");
            excursionManagementPage.editExternalDescription(description);
            excursionManagementPage.selectArticle(article);
        }
    }

    @And("User edit Template General Information on tour template page")
    public void userEditTemplateGeneralInformationOnTourTemplatePage(DataTable dataTable) {
        List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dataMap : dataRow) {
            String nameEN = dataMap.get("Name (EN)");
            String descriptionEN = dataMap.get("Description (EN)");
            String externalCode = dataMap.get("External Code");
            String brochureID = dataMap.get("Brochure ID");
            excursionManagementPage.editName(nameEN);
            excursionManagementPage.editDescriptionEN(descriptionEN);
            excursionManagementPage.enterExternalCode(externalCode);
            excursionManagementPage.enterBrochureID(brochureID);
        }
    }

    @And("User enter Template General Information details on Tour Activity New")
    public void userEnterTemplateGeneralInformationDetailsOnTourActivityNew(DataTable dataTable) {
        List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dataMap : dataRow) {
            String nameEN = dataMap.get("Name (EN)");
            String descriptionEN = dataMap.get("Description (EN)");
            context.setContext("Name (EN)", nameEN + String.valueOf(excursionManagementPage.getRandomNumber()));
            excursionManagementPage.enterName(context.getContext("Name (EN)"));
            excursionManagementPage.enterDescriptionEN(descriptionEN);
        }
    }

    @And("User edit {string} on tour template page")
    public void userEditOnTourTemplatePage(String name, DataTable dataTable) {
        excursionManagementPage.plusButton(name);
        List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dataMap : dataRow) {
            String departureTime = dataMap.get("Departure Time");
            String meetingTime = dataMap.get("Meeting Time");
            String meetingLocation = dataMap.get("Meeting Location");
            String languages = dataMap.get("Languages");
            String ageGroup = dataMap.get("Age Group");
            String price = dataMap.get("Price");
            excursionManagementPage.enterDepartureTime(departureTime);
            excursionManagementPage.enterMeetingTime(meetingTime);
            excursionManagementPage.selectMeetingLocation(meetingLocation);
            excursionManagementPage.editLanguages(languages);
            excursionManagementPage.selectAgeGroup(ageGroup);
            excursionManagementPage.enterPrice(price);
            safetyManagementPage.clickOnAddButton();
        }
    }

    @Then("User click save button on {string}")
    public void userClickSaveButtonOn(String button) {
        excursionManagementPage.saveButton(button);
    }

    @And("User click {string} plus button on Tour Activities")
    public void userClickPlusButtonOnTourActivities(String name) {
        excursionManagementPage.clickPlusButton(name);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User enter Article General Information details on Tour Activity New")
    public void userEnterArticleGeneralInformationDetailsOnTourActivityNew(DataTable dataTable) {
        List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dataMap : dataRow) {
            String templateCode = dataMap.get("Tour Template Code");
            String tourDescription = dataMap.get("Tour Template Description");
            String description = dataMap.get("External Template Description");
            String article = dataMap.get("Article Class");
            context.setContext("templateTestCode", templateCode + String.valueOf(excursionManagementPage.getRandomNumber()));
            System.out.println(context.getContext("templateTestCode"));
            context.setContext("templateTourDescription", tourDescription + String.valueOf(excursionManagementPage.getRandomNumber()));
            context.setContext("templateExternalDescription", description + String.valueOf(excursionManagementPage.getRandomNumber()));
            excursionManagementPage.enterTourCode(context.getContext("templateTestCode"));
            excursionManagementPage.enterTourDescription(context.getContext("templateTourDescription"));
            excursionManagementPage.enterExternalDescription(context.getContext("templateExternalDescription"));
            excursionManagementPage.selectArticle(article);
        }
    }

    @And("User enter Template General Information details on tour template page")
    public void userEnterTemplateGeneralInformationDetailsOnTourTemplatePage(DataTable dataTable) {
        List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dataMap : dataRow) {
            String nameEN = dataMap.get("Name (EN)");
            String descriptionEN = dataMap.get("Description (EN)");
            context.setContext("Name (EN)", nameEN + String.valueOf(excursionManagementPage.getRandomNumber()));
            excursionManagementPage.enterName(context.getContext("Name (EN)"));
            excursionManagementPage.enterDescriptionEN(descriptionEN);
        }
    }

    @And("User enter Operations details on Tour Activity New")
    public void userEnterOperationsDetailsOnTourActivityNew(DataTable dataTable) {
        List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dataMap : dataRow) {
            String area = dataMap.get("Area");
            String country = dataMap.get("Country");
            String port = dataMap.get("Port");
            excursionManagementPage.selectArea(area);
            excursionManagementPage.selectCountry(country);
            context.setContext("Country", country);
            excursionManagementPage.selectPort(port);
            context.setContext("Port", port);
        }
    }

    @And("User enter Departures details on Tour Activity New")
    public void userEnterDeparturesDetailsOnTourActivityNew(DataTable dataTable) {
        List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dataMap : dataRow) {
            String departureTime = dataMap.get("Departure Time");
            String meetingTime = dataMap.get("Meeting Time");
            String meetingLocation = dataMap.get("Meeting Location");
            String languages = dataMap.get("Languages");
            context.setContext("templateDepartureTime", departureTime);
            excursionManagementPage.clickDeparturePlusButton();
            excursionManagementPage.enterDepartureTime(context.getContext("templateDepartureTime"));
            excursionManagementPage.enterMeetingTime(meetingTime);
            excursionManagementPage.selectMeetingLocation(meetingLocation);
            try {
                excursionManagementPage.editLanguages(languages);
            } catch (Exception e) {
                excursionManagementPage.userAbleToEnterDeparturesDetailsOnTourActivityNewLangauge(languages);
            }
//            excursionManagementPage.clickOnPriceForAgeGroupe();
        }
    }

    @And("User edit Transportation Management - Languages on tour template page")
    public void userEditTransportationManagementLanguagesOnTourTemplatePage(DataTable dataTable) {
        List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dataMap : dataRow) {
            String languages = dataMap.get("Language");
            context.setContext("Language", languages);
            excursionManagementPage.deleteLanguageIfPresent(context.getContext("Language"));
            excursionManagementPage.editLanguages(context.getContext("Language"));

        }
        safetyManagementPage.clickOnAddButton();
        excursionManagementPage.deleteLanguage(context.getContext("Language"));
    }

    @And("User enter Prices for Age Groups details on Tour Activity New")
    public void userEnterPricesForAgeGroupsDetailsOnTourActivityNew(DataTable dataTable) {
        List<Map<String, String>> dataRows = dataTable.asMaps(String.class, String.class);

        int index = 1;
        for (Map<String, String> row : dataRows) {
            String ageGroup = row.get("Age Group");
            String price = row.get("Price");
            excursionManagementPage.selectAgeGroup(index, ageGroup);
            excursionManagementPage.enterPrice(price);

            if (index < dataRows.size()) {
                excursionManagementPage.clickOnAddNewGroup();
            }
            index++;
        }
    }

    @And("User click save button on Tour Activity New")
    public void userClickSaveButtonOnTourActivityNew() {
        excursionManagementPage.clickOnSave();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User click on {string} and search for template code")
    public void userClickOnAndSearchForTemplateCode(String dropDown) {
        String value = context.getContext("tourCode");
        excursionManagementPage.searchTemplateCode(dropDown, value);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User verify created new template details")
    public void userVerifyCreatedNewTemplateDetails() {
        /*String code = context.getContext("tourCode");
        String country = context.getContext("countryName");
        String port = context.getContext("portName");
        String nameEN = context.getContext("nameEN");

        List<String> rowData=excursionManagementPage.getRowValues();
        System.out.println(rowData);
        List<String> expectedValues = Arrays.asList(code, country, port, nameEN);
        Assert.assertTrue(rowData.containsAll(expectedValues), "One or more expected elements are not in the list");*/
        String code = context.getContext("tourCode");
        String country = context.getContext("countryName");
        String port = context.getContext("portName");
        String nameEN = context.getContext("nameEN");

        List<String> rowData = excursionManagementPage.getRowValues();

        logger.info("Actual Row Data : {}", rowData);

        Assert.assertTrue(
                rowData.contains(code),
                "Tour Code is not matched : " + code);

        Assert.assertTrue(
                rowData.contains(country),
                "Country Name is not matched : " + country);

        Assert.assertTrue(
                rowData.contains(port),
                "Port Name is not matched : " + port);

        Assert.assertTrue(
                rowData.contains(nameEN),
                "Name EN is not matched : " + nameEN);

    }

    @And("User click on selected tour")
    public void userClickOnSelectedTour() {
        excursionManagementPage.clickDateArrowDownIcon();
        excursionManagementPage.clickClearDateButton();
//        excursionManagementPage.deleteLanguageIfPresent("English");
//        excursionManagementPage.functionName();
    }

    @Then("User verify the add Transportation Management - Languages on tour template page")
    public void userVerifyTheAddTransportationManagementLanguagesOnTourTemplatePage() {
        System.out.println("noLanguage::" + excursionManagementPage.getLanguageData());
        System.out.println("language ::" + context.getContext("Language"));
        Assert.assertTrue(
                excursionManagementPage.getLanguageData().contains(context.getContext("Language")),
                "The language data does not contain the expected value: " + context.getContext("Language")
        );
//        Assert.assertEquals(
//                excursionManagementPage.getLanguageData().get(0), // Assuming the list has exactly one element
//                context.getContext("Language"),
//                "The language data does not match the expected value"
//        );
    }

    @Then("User check the Guest details")
    public void userCheckTheGuestDetails() {
        String bookingNr = excursionManagementPage.getBookingNrInformation();
        System.out.println("BookingNr ::" + bookingNr);
        context.setContext("BookingNr", bookingNr);
        String disembarkationDate = excursionManagementPage.getDisembarkationDateInformation();
        System.out.println("Disembarkation::" + disembarkationDate);
        context.setContext("Disembarkation", disembarkationDate);
        String embarkationDate = excursionManagementPage.getEmbarkationDateInformation();
        System.out.println("Embarkation::" + embarkationDate);
        context.setContext("Embarkation", embarkationDate);

    }

    @Then("User verify the booking information for guests by Name,Booking Nr,Embarkation and Disembarkation.")
    public void userVerifyTheBookingInformationForGuestsByNameBookingNrEmbarkationAndDisembarkation() {
//        String bookingNr = excursionManagementPage.getBookingNrDetails();
//        System.out.println(bookingNr);
        System.out.println("crewName :: " + crewInformationPage.getFullName());
        System.out.println("crewSensitive ::" + context.getContext("nonSensitiveGuestFullName"));
        Assert.assertEquals(crewInformationPage.getFullName(), context.getContext("nonSensitiveGuestFullName"));
//        Assert.assertEquals(bookingNr,context.getContext("BookingNr"));
//        Assert.assertEquals(excursionManagementPage.getEmbarkationDateDetails(),context.getContext("Embarkation"));
//        Assert.assertEquals(excursionManagementPage.getDisembarkationDateDetails(),context.getContext("Disembarkation"));
    }

    @And("User select a tour on Excursion page")
    public void userSelectATourOnExcursionPage() {
        excursionManagementPage.selectTourForExcursion();
    }

    @Then("User Verify the tour Details")
    public void userVerifyTheTourDetails() {
        String date = context.getContext("Date");
        String code = context.getContext("templateTestCode");
        String port = context.getContext("Port");
        String name = context.getContext("Name");
        List<String> rowData = excursionManagementPage.getRowValuesForTour();
        List<String> expectedValues = Arrays.asList(date, code, port, name);
        System.out.println("Expected values: " + expectedValues);
        System.out.println("Row data: " + rowData);
        Assert.assertTrue(rowData.containsAll(expectedValues), "One or more expected elements are not in the list.");
    }


    @Then("User verify the Save button is disabled")
    public void userVerifyTheSaveButtonIsDisabled() {
        boolean isDisabled = excursionManagementPage.saveButtonDisabled();
        System.out.println(isDisabled);
        Assert.assertTrue(isDisabled, "Save button should be disabled, but it is not!");
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User click on special needs")
    public void userClickOnSpecialNeeds() {
        excursionManagementPage.clickOnSpecialNeeds();
    }

    @And("User select special needs {string}")
    public void userSelectSpecialNeeds(String need) {
        excursionManagementPage.setSpecialNeedsDropDown(need);
    }

    @And("User verify the special need {string} is added to the list")
    public void userVerifyTheSpecialNeedIsAddedToTheList(String specialNeed) {
        guestServicePage.refreshPage();
        List<String> specialNeedList = excursionManagementPage.specialNeedsListList();
        System.out.println(specialNeedList);
        Assert.assertTrue(specialNeedList.contains(specialNeed),
                "The special request '" + specialNeed + "' was not found in the list.");
    }

    @And("User edit {string} on tour template")
    public void userEditOnTourTemplate(String name, DataTable dataTable) {
        excursionManagementPage.plusButton(name);
        List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> dataMap : dataRow) {
            String ageGroup = dataMap.get("Age Group");
            String price = dataMap.get("Price");
            excursionManagementPage.selectAgeGroup(ageGroup);
            excursionManagementPage.enterPrice(price);
            safetyManagementPage.clickOnAddButton();
        }
    }

    @Then("User click {string} tab on Tour Activity New")
    public void userClickTabOnTourActivityNew(String item) {
        commonPage.clickOnLabel(item);
        context.setContext("SKIP_SCREENSHOT", "true");
    }


    @And("User enter min capacity, max capacity and available capacity in operations")
    public void userEnterMinCapacityMaxCapacityAndAvailableCapacityInOperations() {
        excursionManagementPage.enterMinCapacity(String.valueOf(excursionManagementPage.getRandomNumberForCapacity()));
        excursionManagementPage.enterMaxCapacity(String.valueOf(excursionManagementPage.getRandomNumberForCapacity()));
        excursionManagementPage.enterAvailableCapacity(String.valueOf(excursionManagementPage.getRandomNumberMaxWeightPerPerson()));
        WebElement minElement = excursionManagementPage.getMinCapacityTextWebElement();
        String minTextMessage = commonFunctions.readToastMessage(minElement);
        context.setContext("minText", minTextMessage);
        System.out.println("Comment text message : " + context.getContext("minText"));
        WebElement maxElement = excursionManagementPage.getMaxCapacityTextWebElement();
        String maxTextMessage = commonFunctions.readToastMessage(maxElement);
        context.setContext("maxText", maxTextMessage);
        System.out.println("Comment text message : " + context.getContext("maxText"));
        WebElement availableElement = excursionManagementPage.getAvailableCapacityTextWebElement();
        String availableTextMessage = commonFunctions.readToastMessage(availableElement);
        context.setContext("availableText", availableTextMessage.replaceAll("[^0-9]", ""));
        System.out.println("Comment text message : " + context.getContext("availableText"));
    }

    @And("User verify the added min capacity, max capacity and available capacity in operations")
    public void userVerifyTheAddedMinCapacityMaxCapacityAndAvailableCapacityInOperations() {
        WebElement getMinElement = excursionManagementPage.getMinCapacityTextWebElement();
        String getMinTextMessage = commonFunctions.readToastMessage(getMinElement);
        Assert.assertEquals(getMinTextMessage, context.getContext("minText"));
        WebElement getMaxElement = excursionManagementPage.getMaxCapacityTextWebElement();
        String getMaxTextMessage = commonFunctions.readToastMessage(getMaxElement);
        Assert.assertEquals(getMaxTextMessage, context.getContext("maxText"));
        WebElement getAvailableElement = excursionManagementPage.getAvailableCapacityTextWebElement();
        String getAvailableTextMessage = commonFunctions.readToastMessage(getAvailableElement);
        System.out.println(getAvailableTextMessage.replaceAll("[^0-9]", "").trim());
        Assert.assertEquals(getAvailableTextMessage.replaceAll("[^0-9]", "").trim(), context.getContext("availableText"));
    }

    @Then("User enter above value of min capacity, max capacity and available capacity in operations")
    public void userEnterAboveValueOfMinCapacityMaxCapacityAndAvailableCapacityInOperations() {
        excursionManagementPage.enterMinCapacity(String.valueOf(excursionManagementPage.getRandomNumberForAboveCapacity()));
        excursionManagementPage.enterMaxCapacity(String.valueOf(excursionManagementPage.getRandomNumberForAboveCapacity()));
        excursionManagementPage.enterAvailableCapacity(String.valueOf(excursionManagementPage.getRandomNumberForAboveCapacity()));
        excursionManagementPage.enterMaxWeight(String.valueOf(excursionManagementPage.getRandomNumberMaxWeight()).trim());
    }

    @And("User verify the error messages in min capacity, max capacity and available capacity field in operations")
    public void userVerifyTheErrorMessagesInMinCapacityMaxCapacityAndAvailableCapacityFieldInOperations() {
        String minErrorMessage = excursionManagementPage.getMinInputFieldErrorMessage();
        Assert.assertEquals(minErrorMessage, "Max value allowed is 9999");
        String maxErrorMessage = excursionManagementPage.getMaxInputFieldErrorMessage();
        Assert.assertEquals(maxErrorMessage, "Max value allowed is 9999");
        String availableErrorMessage = excursionManagementPage.getAvailableInputFieldErrorMessage();
        Assert.assertEquals(availableErrorMessage, "Max value allowed is 9999");
    }

    @Then("User view on bookings guests add button")
    public void userViewOnBookingsGuestsAddButton() {
        excursionManagementPage.viewBookingGuestsAddButton();
    }

    @And("User click on add more filters in guest search")
    public void userClickOnAddMoreFiltersInGuestSearch() {
        excursionManagementPage.guestSearchComplexFilterAddMoreButton();
    }

    @And("User click on disembarkation date arrow down button and select {string}")
    public void userClickOnDisembarkationDateArrowDownButtonAndSelect(String date) {
        excursionManagementPage.guestSearchDisembarkationDateFilter(date);
    }

    @And("User clear today date")
    public void userClearTodayDate() {
        excursionManagementPage.clearTodayDate();
    }

//    @And("User book a tour for selected guest")
//    public void userBookATourForSelectedGuest() {
//        List<String> bookingDate = excursionManagementPage.getBookingDateAndMonth();
//        String date = bookingDate.get(0);
//        context.setContext("bookingDate",date);
//        String month = bookingDate.get(1);
//        String month1 = month.substring(1).toLowerCase();
//        String month2 = month.substring(0,1).toUpperCase();
//        String actualMonth = month2+month1;
//        System.out.println(actualMonth);
//        context.setContext("bookingMonth",actualMonth);
//        excursionManagementPage.clickBookButton();
//        String[] tourName = excursionManagementPage.getBookTittleNameText().split("/");
//        context.setContext("bookTourName",tourName[1].trim());
//        excursionManagementPage.clickPostButton();
//    }

    @Then("User select booked date and click booked tour")
    public void userSelectBookedDateAndClickBookedTour() {
        String date = context.getContext("bookingDate");
        System.out.println(date);
        String month = context.getContext("bookingMonth");
        System.out.println(month);
        excursionManagementPage.clickOnBookedTour(date, month);
        String bookedTourName = context.getContext("bookTourName");
        System.out.println(bookedTourName);
        excursionManagementPage.clickOnTourName(bookedTourName);
    }

    @And("User search for added Guest in booking overview")
    public void userSearchForAddedGuestInBookingOverview() {
        String participant = context.getContext("guestName");
        excursionManagementPage.searchParticipantOrCabin(participant);
    }

    @And("user click on {string} in add guest search")
    public void userClickOnInAddGuestSearch(String name) {
        commonPage.clickOnLabel(name);
    }

    @And("User save selected guest name from the guest search")
    public void userSaveSelectedGuestNameFromTheGuestSearch() {
        String[] guestName = hotelMaintenancePage.getGuestName().split(" ");
        System.out.println("guestName :: " + guestName[1].trim());
        context.setContext("firstguestName", guestName[0].trim());
        context.setContext("guestName", guestName[1].trim());
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User click on tour guest add button")
    public void userClickOnTourGuestAddButton() {
        excursionManagementPage.clickTourGuestAddButton();
    }

    @And("User enter tour max weight and max weight per person in operation")
    public void userEnterTourMaxWeightAndMaxWeightPerPersonInOperation() {
        excursionManagementPage.enterMaxWeight(String.valueOf(excursionManagementPage.getRandomNumberMaxWeight()).trim());
        excursionManagementPage.enterMaxWeightPerPerson(String.valueOf(excursionManagementPage.getRandomNumberMaxWeightPerPerson()).trim());
        WebElement maxWeightElement = excursionManagementPage.getTourMaxWeightTextWebElement();
        String maxWeightTextMessage = commonFunctions.readToastMessage(maxWeightElement);
        context.setContext("maxWeightText", maxWeightTextMessage);
        System.out.println("Comment text message : " + context.getContext("maxWeightText"));
        WebElement maxWeightPerGuestElement = excursionManagementPage.getTourMaxWeighPerGuestTextWebElement();
        String maxWeightPerPersonTextMessage = commonFunctions.readToastMessage(maxWeightPerGuestElement);
        context.setContext("maxWeightPerGuestText", maxWeightPerPersonTextMessage);
        System.out.println("Comment text message : " + context.getContext("maxWeightPerGuestText"));
    }

    @Then("User click on three dot button and click on {string}")
    public void userClickOnThreeDotButtonAndClickOn(String menu) {
        excursionManagementPage.selectBookingActionMenu(menu);
    }

    @And("User select {string} filter and select option as {string}")
    public void userSelectFilterAndSelectOptionAs(String filterName, String status) {
        excursionManagementPage.changeStatusAndFilter(filterName, status);
    }

    @Then("User verify cancelled the booked excursion")
    public void userVerifyCancelledTheBookedExcursion() {
        String participantName = excursionManagementPage.getParticipantName();
        // String[] participantNameText  = commonFunctions.readToastMessage(participantName).split(" ");
        // context.setContext("participantName", participantNameText[1].trim());
        String participant = context.getContext("guestName");
        //Assert.assertEquals(context.getContext("participantName"),participant);
        Assert.assertTrue(participantName.contains(participant));
    }

    @Then("User clear booking status filter")
    public void userClearBookingStatusFilter() {
        excursionManagementPage.clearBookingStatus();
    }

    @And("User {string} a tour for selected guest")
    public void userATourForSelectedGuest(String book) {
        excursionManagementPage.clickFullWidthBlock();
       /* List<String> bookingDate = excursionManagementPage.getBookingDateAndMonth(book);
        String date = bookingDate.get(0);
        context.setContext("bookingDate",date);
        String month = bookingDate.get(1);
        String month1 = month.substring(1).toLowerCase();
        String month2 = month.substring(0,1).toUpperCase();
        String actualMonth = month2+month1;
        System.out.println(actualMonth);
        context.setContext("bookingMonth",actualMonth);*/
        List<String> bookingDate = excursionManagementPage.getBookingDateAndMonth(book);
        String date = bookingDate.get(0);
        context.setContext("bookingDate", date);

        String monthYear = bookingDate.get(1).trim();

        String[] parts = monthYear.split("\\s+");

        if (parts.length < 2) {
            throw new RuntimeException("Invalid monthYear format: " + monthYear);
        }

        String month = parts[0];
        String year = parts[1];

        String actualMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();

        context.setContext("bookingMonth", actualMonth);
        context.setContext("bookingYear", year);
        context.setContext("bookingMonthYear", actualMonth + " " + year);
        switch (book) {
            case "Book":
                excursionManagementPage.clickBookButton(book);
                break;
            case "overbook":
                excursionManagementPage.clickBookButton(book);
                excursionManagementPage.clickWaitListButton();
                break;
        }
        commonSteps.userClickOn(ExcursionManagementConstants.CONFIRM);
        String[] tourName = excursionManagementPage.getBookTittleNameText().split("/");
        context.setContext("bookTourName", tourName[1].trim());
        String totalValue = excursionManagementPage.getTotalValue();
        context.setContext("TOTAL_VALUE", totalValue);
        excursionManagementPage.clickPostButton();
    }

    @Then("User enter general details on tour activity new")
    public void userEnterGeneralDetailsOnTourActivityNew() {
        excursionManagementPage.tourGeneralDetails();
        WebElement tourCodeText = excursionManagementPage.getTourCode();
        String tourCode = commonFunctions.readToastMessage(tourCodeText).trim();
        context.setContext("tourCode", tourCode);

        WebElement nameENText = excursionManagementPage.getNameEN();
        String nameEN = commonFunctions.readToastMessage(nameENText).trim();
        nameEN = nameEN.replace(",", "").replace("{", "").replace("}", "").trim();
        String cleanNameEN = nameEN.split("\\s+")[0];
        context.setContext("nameEN", cleanNameEN);
        logger.info("Cleaned nameEN: " + cleanNameEN);
        String countryNameText = excursionManagementPage.getCountryName();
        context.setContext("countryName", countryNameText);
        String portNameText = excursionManagementPage.getPortName();
        context.setContext("portName", portNameText);
    }

    @Then("User enter departure, price, sales,and feed details on tour activity new")
    public void userEnterDeparturePriceSalesAndFeedDetailsOnTourActivityNew() {
        excursionManagementPage.enterDeparturesDetails();
        excursionManagementPage.enterPricesSalesFeesDetails();
    }

    @Then("User enter departure, without including price, sales,and feed details on tour activity new")
    public void userEnterDepartureWithoutIncludingPriceSalesAndFeedDetailsOnTourActivityNew() {
        excursionManagementPage.enterDeparturesDetails();
    }

    @Then("Get the tour name and tour code from the existing records")
    public void getExistingTourNameAndCode() {
        String tourCode = excursionManagementPage.getGridTourCode();
        System.out.println(tourCode);
        context.setContext("tourCode", tourCode);

        String gridTourDescription = excursionManagementPage.getGridTourDescription();
        System.out.println(gridTourDescription);
        context.setContext("gridTourDescription", gridTourDescription);

    }

    @And("User searches for tour code in bookings overview")
    public void searchInBookingOverview(String tourCode) {
        String value = context.getContext("tourCode");
        excursionManagementPage.searchExcursionBookingsOverview(value);
    }


    @And("Verify that the tour code and tour name is appearing in the Excursion bookings overview")
    public void verifyTourCodeAndDescriptionInBookingOverview() {
        String tourCode = context.getContext("tourCode");
        Assert.assertEquals(excursionManagementPage.getSearchedTourCode().toLowerCase(), (tourCode));
        String tourDescription = context.getContext("gridTourDescription");
        Assert.assertEquals(excursionManagementPage.getSearchedTourName().toLowerCase(), (tourDescription));

    }

    @And("User able click on the {string}")
    public void userAbleClickOnThe(String str) {
        excursionManagementPage.userAbleClickOnThe(str);
    }

    @And("User Click on the Edit icon in Tour Activities page")
    public void userClickOnTheEditIconInTourActivitiesPage() {
        boolean isClicked = excursionManagementPage.isClickEditButton();
        Assert.assertTrue(isClicked, "Edit button not found or not clickable");
    }

    @Then("User scrolls to Transportation Management")
    public void userScrollsToTransportationManagement() {
        excursionManagementPage.scrollToTransportationManagement();
        context.setContext("SKIP_SCREENSHOT", "true");
    }


    @And("User clicks on Edit pencil icon")
    public void userClicksOnEditPencilIcon() {
        excursionManagementPage.userClicksOnEditPencilIcon();
    }

    @And("User edits the selected language")
    public void userEditsTheSelectedLanguage() {
        excursionManagementPage.userEditsTheSelectedLanguage();
    }

    @And("User checks edit icon and handles language")
    public void userChecksEditIconAndHandlesLanguage() {
        if (excursionManagementPage.isEditIconPresent()) {
            String language = excursionManagementPage.getLanguage();
            context.setContext("languageValue", language);
        } else {
            excursionManagementPage.userAddANewLanguageInTransportationManagement();

            String language = excursionManagementPage.getLanguage();
            context.setContext("languageValue", language);
        }

    }
    @Then("User validates the language is edited")
    public void userValidatesTheLanguageIsEdited() {
        String previous = context.getContext("languageValue");
        String edited = context.getContext("languageValueEdit");

        Assert.assertNotEquals(previous, edited, "Language is not edited. Both values are same: " + previous);
    }

    @Then("User stores delete button count")
    public void userStoresDeleteButtonCount() {
        int count = excursionManagementPage.getDeleteButtonCount();
        context.setContext("beforeDeleteCount", String.valueOf(count));
    }

    @And("User delete the language in the Transportation Management")
    public void userDeleteTheLanguageInTheTransportationManagement() {
        excursionManagementPage.userClickOnDeleteIcon();
        int afterCount = excursionManagementPage.getDeleteButtonCount();
        context.setContext("afterDeleteCount", String.valueOf(afterCount));
    }

    @Then("User verifies the language is deleted")
    public void userVerifiesTheLanguageIsDeleted() {
        int before = Integer.parseInt(context.getContext("beforeDeleteCount"));
        int after = Integer.parseInt(context.getContext("afterDeleteCount"));
        Assert.assertEquals(after, before - 1, "Delete count is not reduced correctly. Before: " + before + " After: " + after);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User clears the date from the calendar field")
    public void userClearsTheDateFromTheCalendarField() {
        //excursionManagementPage.clearDateIfBookableItemsVisible();
        userClearsTheDateFromTheCalendarFieldAndSelectsTheDateRange();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User draws signature if status label exists")
    public void userDrawsSignatureIfStatusLabelExists() {
        excursionManagementPage.drawSignatureforConfirmation();
    }

    @Then("User search guest first name")
    public void userSearchGuestFirstName() {
        String guestName = (String) context.getContext("firstguestName");
        guestServicePage.searchUser(guestName);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User click on the last name")
    public void userClickOnTheLastName() {
        String guestName = (String) context.getContext("guestName");
        WebElement element = getDriver().findElement(By.xpath("//a[normalize-space()='" + guestName + "']"));
        element.click();
    }

    @Then("User verifies the booking details")
    public void userVerifiesTheBookingDetails() {

        String grossAmount = excursionManagementPage.getGrossAmountFirstRow();
        context.setContext("GROSS_AMOUNT", grossAmount);
        String totalValue = (String) context.getContext("TOTAL_VALUE");
        logger.info("Total Value  : {}", totalValue);
        logger.info("Gross Amount : {}", grossAmount);
        double total = Double.parseDouble(totalValue);
        double gross = Double.parseDouble(grossAmount);
        Assert.assertEquals(gross, total, 0.01, "Mismatch between Total Value and Gross Amount");
    }

    @And("User select a guest name")
    public void userSelectAGuestName() {
        excursionManagementPage.userSearchForTheGuest();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User {string} tour for guest")
    public void userTourForGuest(String book) {
        homePage.navigateToSelectedService(ExcursionManagementConstants.EXCURSIONS);
        userViewsTheBookingsManagerItemCard();
        excursionManagementPage.userNavigateItemCard(ExcursionManagementConstants.BOOKINGS_MANAGER_CARDS);
        excursionManagementPage.userClickOnBookingsGuestsAddButton();
        commonPage.clickOnLabel(ExcursionManagementConstants.GUESTS);
        excursionManagementPage.userSearchForTheGuest();
        userSaveSelectedGuestNameFromTheGuestSearch();
        commonSteps.userClickOnThe(ExcursionManagementConstants.SELECT);
        userClearsTheDateFromTheCalendarFieldAndSelectsTheDateRange();
        //excursionManagementPage.clearDateIfBookableItemsVisible();
        userATourForSelectedGuest(book);
        excursionManagementPage.drawSignatureforConfirmation();
        commonSteps.userWaitForSeconds(ExcursionManagementConstants.WAITING_FOR_ELEMENT);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("the user views the booking information")
    public void theUserViewsTheBookingInformation() {
        String totalValue = (String) context.getContext("TOTAL_VALUE");
        double total = Double.parseDouble(totalValue);
        boolean isDisplayed = excursionManagementPage.isTotalValueDisplayed(totalValue);
        excursionManagementPage.guestBookingDetails();
        Assert.assertTrue(isDisplayed, " Total value is NOT displayed: " + totalValue);
    }

    @And("User selects multi guest members")
    public void userSelectsMultiGuestMembers() {
        excursionManagementPage.userSelectsTwoGuestMembers(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);

        excursionManagementPage.userSelectsTwoGuestMembers(PreconditionConstants.NEWLY_CREATED_GUEST2_LASTNAME);
        List<String> names = excursionManagementPage.getGuestNames();
        String[] firstGuestParts = names.get(0).split(" ");


        String firstGuestFirstName = firstGuestParts[0];
        String firstGuestLastName = firstGuestParts[firstGuestParts.length - 1];

        String[] secondGuestParts = names.get(1).split(" ");
        String secondGuestFirstName = secondGuestParts[0];
        String secondGuestLastName = secondGuestParts[secondGuestParts.length - 1];

        context.setContext("firstguestFirstName", firstGuestFirstName);
        context.setContext("firstguestLastName", firstGuestLastName);

        context.setContext("secondguestFirstName", secondGuestFirstName);
        context.setContext("secondguestLastName", secondGuestLastName);
    }

    @Then("User search multi-guest {string} name")
    public void userSearchMultiGuestName(String guestKey) {
        String guestName = (String) context.getContext(guestKey);
        excursionManagementPage.searchUser(guestName);
        List<WebElement> elements = getDriver().findElements(By.xpath("//td[contains(@class,'dx-hidden-cell')]//div[contains(.,'" + guestName + "')]"));
        logger.info("Element count: " + elements.size());
        excursionManagementPage.guestBookingDetails();
        Assert.assertTrue(elements.size() > 0, "Guest is NOT displayed: " + guestName);
    }


    @And("User searches for a special needs guest")
    public void userSearchesForASpecialNeedsGuest() {
        String firstName = (String) context.getContext("guestFirstName");
        String fullName = (String) context.getContext("guestFullName");
        Assert.assertNotNull(fullName, "Guest full name is null");
        Assert.assertFalse(fullName.isEmpty(), "Guest full name is empty");
        excursionManagementPage.searchSpecialNeedsGuest(firstName, fullName);
    }

    @Then("User verifies the special needs information on the guest tile")
    public void userVerifiesTheSpecialNeedsInformationOnTheGuestTile() {
        boolean isDisplayed = excursionManagementPage.isSpecialNeedsIconDisplayed();
        Assert.assertTrue(isDisplayed, "Special needs icon is not displayed on the guest tile");
    }

    @Then("User adds a special needs guest member")
    public void userAddsASpecialNeedsGuestMember() {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.GUESTSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.GUEST_SERVICES);
        guestServicePage.openGuestServiceItem(GlobalConstants.GUEST_SEARCH);
        commonPage.searchFor(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        List<String> guestFullName = guestServicePage.getGuestFullName();
        String lastName = guestFullName.get(0);
        String firstName = guestFullName.get(1);
        String fullName = firstName + " " + lastName;
        context.setContext("guestFullName", fullName);
        context.setContext("guestFirstName", firstName);
        context.setContext("guestLastName", lastName);
        safetyManagementPage.userAbleToClickInTheDetailTable(ExcursionManagementConstants.LASTNAME);
        guestServicePage.openSubMenu(ExcursionManagementConstants.PERSONAL_INFORMATION);
        safetyManagementPage.currentSafetyAssignmentSection(ExcursionManagementConstants.SPECIAL_NEEDS);
        excursionManagementPage.userAddTheDataInSpecialFileds();
        commonSteps.userClickOn(ExcursionManagementConstants.SAVE);
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User click on view icon")
    public void userClickOnViewIcon() {
        excursionManagementPage.clickFullWidthBlock();
    }

    @Then("User verifies pricing for {string} in each tour")
    public void userVerifiesPricingForAgeGroup(String ageGroup) {

        excursionManagementPage.getFirstPriceElementByAgeGroup(ageGroup);
        Map<String, List<Double>> pricingMap = excursionManagementPage.getPricingByAgeGroup(ageGroup);
        List<Double> prices = pricingMap.get(ageGroup);

        if (prices == null || prices.isEmpty()) {
            throw new AssertionError("No prices found for " + ageGroup);
        }

        for (Double price : prices) {

            logger.info("Validating Price: " + price);

            if (price < 0) {
                throw new AssertionError("Negative price found for " + ageGroup);
            }

            if (price == 0 && !ageGroup.equalsIgnoreCase(ExcursionManagementConstants.INFANT)) {
                throw new AssertionError("Unexpected zero price for " + ageGroup);
            }
        }

        Set<String> validateVariationGroups = Set.of(ExcursionManagementConstants.ADULT, ExcursionManagementConstants.MINOR);
        Set<Double> uniquePrices = new HashSet<>(prices);

        if (validateVariationGroups.contains(ageGroup) && uniquePrices.size() <= 1) {
            throw new AssertionError("Prices are constant for " + ageGroup);
        }

        if (ageGroup.equalsIgnoreCase(ExcursionManagementConstants.INFANT)) {
            for (Double price : prices) {
                if (price < 0.0) {
                    throw new AssertionError("Infant price should be 0 but found: " + price);
                }
            }
        }
        logger.info("Price validation passed for " + ageGroup + " → " + uniquePrices);
    }

    @Then("User stores the {string} guest name in context")
    public void userStoresTheGuestNameInContext(String guestAgeType) {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.GUESTSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.GUEST_SERVICES);
        guestServicePage.openGuestServiceItem(GlobalConstants.GUEST_SEARCH);
        crewInformationPage.clearReservationStatus();
        guestServicePage.checkOptionInTheFilter(GlobalConstants.CHECK_IN);
        guestServicePage.addFilter(GlobalConstants.ONBOARD);
        guestServicePage.changeStatusOfFilter(GlobalConstants.ONBOARD, GlobalConstants.YES);
        guestServicePage.setAgeFilterToBe(guestAgeType);
        List<String> guestFullName = guestServicePage.getGuestFullName();
        String lastName = guestFullName.get(0);
        String firstName = guestFullName.get(1);
        String fullName = firstName + " " + lastName;
        context.setContext("guestFullName", fullName);
        context.setContext("guestFirstName", firstName);
        context.setContext("guestLastName", lastName);

        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User verifies that {string} guest type is displayed in the selected guest section")
    public void userVerifiesThatGuestTypeIsDisplayedInTheSelectedGuestSection(String guestType) {
        boolean isDisplayed = excursionManagementPage.isGuestTypeDisplayed(guestType);
        if (!isDisplayed) {
            throw new AssertionError("Guest type not displayed: " + guestType);
        }
        logger.info("Guest type displayed successfully: " + guestType);
    }

    @Then("User verifies that {string} value is displayed in the table")
    public void userVerifiesThatValueIsDisplayedInTheTable(String tableValue) {
        WebElement element = excursionManagementPage.getTableValueElement(tableValue);
        if (!element.isDisplayed()) {
            throw new AssertionError("Table value not displayed: " + tableValue);
        }
        logger.info("Table value verified successfully: " + tableValue);
    }

    @And("User verifies the Excursions booking overview and inputs the port name")
    public void userVerifiesTheExcursionsBookingOverviewAndInputsThePortName() {
        String bookedTourName = context.getContext("bookingCode");
        excursionManagementPage.userVerifiesTheExcursionsBookingOverviewAndInputsThePortName(bookedTourName);
    }

    @Then("User chooses a date from the list of available tours")
    public void userChoosesADateFromTheListOfAvailableTours() {
        String firstDate = excursionManagementPage.getFirstAvailableDate();
        Assert.assertNotNull(firstDate, "No booking dates are displayed in the overview.");
        Assert.assertFalse(firstDate.isEmpty(), "First booking date text is empty.");
        context.setContext("selectedDate", firstDate);
        excursionManagementPage.userChoosesADateFromTheListOfAvailableTours();
    }

    @Then("User validates the selected date with details page")
    public void userValidatesTheSelectedDateWithDetailsPage() {

        String storedDate = context.getContext("selectedDate");
        String uiDate = excursionManagementPage.getDetailsPageDate();

        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(ExcursionManagementConstants.M_D_YEAR);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(ExcursionManagementConstants.E_M_D_YEAR);

        LocalDate parsedDate = LocalDate.parse(storedDate, inputFormat);
        String expectedDate = parsedDate.format(outputFormat);

        String actualNormalized = uiDate.trim().toLowerCase();
        String expectedNormalized = expectedDate.trim().toLowerCase();

        Assert.assertEquals(actualNormalized, expectedNormalized, "Date mismatch! Expected: " + expectedDate + " but found: " + uiDate);
    }

    @Then("User should see the following transportation details:")
    public void userShouldSeeTheFollowingTransportationDetails(io.cucumber.datatable.DataTable table) {
        List<String> labels = table.asList();
        for (String label : labels) {
            boolean isPresent = excursionManagementPage.isLabelDisplayed(label);
            Assert.assertTrue(isPresent, "Label NOT displayed on screen: " + label);
        }
    }

    @Then("User should get the booking code number")
    public void userShouldGetTheBookingCodeNumber() {
        homePage.navigateToService(ExcursionManagementConstants.EXCURSIONS);
        guestServicePage.openGuestServiceItem(ExcursionManagementConstants.BOOKINGS_MANAGER);
        excursionManagementPage.clearDateIfBookableItemsVisible();
        excursionManagementPage.clickFullWidthBlock();
        String bookingCode = excursionManagementPage.getBookingCode();
        context.setContext("bookingCode", bookingCode);
        commonSteps.userWaitForSeconds(ExcursionManagementConstants.WAITING_FOR_ELEMENT);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("Guest name should be displayed and the guest checkbox is selected")
    public void guestNameShouldBeDisplayedAndTheGuestCheckboxIsSelected() {
        String guestName = (String) context.getContext("firstguestName");
        WebElement element = getDriver().findElement(By.xpath("(//td[not(contains(@class,'dx-hidden-cell'))][.//*[contains(normalize-space(),'" + guestName + "')]])[1]"));
        if (!element.isDisplayed()) {
            throw new AssertionError("Table value not displayed: " + guestName);
        }
        logger.info("Table value verified successfully: " + guestName);

        WebElement checkbox = getDriver().findElement(By.xpath("(//div[contains(normalize-space(),'" + guestName + "')]/ancestor::otalio-bookings-list//span[contains(@class,'dx-checkbox-icon')])[1]"));
        checkbox.click();

    }

    @Then("User books a tour for a future date")
    public void userBooksATourForAFutureDate() {
        homePage.navigateToSelectedService(ExcursionManagementConstants.EXCURSIONS);
        userViewsTheBookingsManagerItemCard();
        excursionManagementPage.userNavigateItemCard(ExcursionManagementConstants.BOOKINGS_MANAGER_CARDS);
        excursionManagementPage.userClickOnBookingsGuestsAddButton();
        commonPage.clickOnLabel(ExcursionManagementConstants.GUESTS);
        excursionManagementPage.userSearchForTheGuest();
        userSaveSelectedGuestNameFromTheGuestSearch();
        commonSteps.userClickOn(ExcursionManagementConstants.SELECT);
        userClearsTheDateFromTheCalendarFieldAndSelectsTheDateRange();
        userATourForSelectedGuest(ExcursionManagementConstants.BOOK);
        excursionManagementPage.drawSignatureforConfirmation();
        commonSteps.userWaitForSeconds(ExcursionManagementConstants.WAITING_FOR_ELEMENT);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User searches for a created tour guest")
    public void userSearchesForACreatedTourGuest() {
        String firstName = (String) context.getContext("firstguestName");
        String lastName = (String) context.getContext("guestName");
        Assert.assertNotNull(firstName, "First name is null");
        Assert.assertNotNull(lastName, "Last name is null");
        String fullName = firstName + " " + lastName;
        excursionManagementPage.searchSpecialNeedsGuest(firstName, fullName);
    }

    @And("User selects a guest from the checkbox")
    public void userSelectsAGuestFromTheCheckbox() {
        List<WebElement> checkbox = getDriver().findElements(By.cssSelector(".dx-checkbox-icon"));
        checkbox.get(1).click();
    }

    @And("User clears the date from the calendar field and selects the date range")
    public void userClearsTheDateFromTheCalendarFieldAndSelectsTheDateRange() {
        String tomorrow = commonFunctions.getDateIncrementedBy(1, "M/d/yyyy");
        String sixMonthsRange = commonFunctions.getDateIncrementedBy(180, "M/d/yyyy");
        excursionManagementPage.userClearsTheExistDateFromTheCalendarFieldAndSelectsTheDateRange(tomorrow, sixMonthsRange);

    }

    @And("User reselects multiple guest members")
    public void userReselectsMultipleGuestMembers() {
        excursionManagementPage.userSelectsTwoGuestMembers("");
        List<String> names = excursionManagementPage.getGuestNames(4);
        for (int i = 0; i < names.size(); i++) {
            String fullName = names.get(i);
            String[] parts = fullName.split(" ");
            String firstName = parts[0];
            String lastName = parts.length > 1 ? parts[parts.length - 1] : "";
            context.setContext("guest" + (i + 1) + "FirstName", firstName);
            context.setContext("guest" + (i + 1) + "LastName", lastName);
        }
    }

    @And("User clears the exist date from the calendar field and selects the date range")
    public void userClearsTheExistDateFromTheCalendarFieldAndSelectsTheDateRange() {
        String sixMonthsRange = commonFunctions.getDateIncrementedBy(180, "M/d/yyyy");
        String oneYearRange = commonFunctions.getDateIncrementedBy(365, "M/d/yyyy");
        excursionManagementPage.userClearsTheExistDateFromTheCalendarFieldAndSelectsTheDateRange(sixMonthsRange, oneYearRange);
    }

    @And("User {string} a tour for selected multi guest")
    public void userATourForSelectedMultiGuest(String book) {
        excursionManagementPage.clickFullWidthBlock();
        List<String> bookingDate = excursionManagementPage.getBookingDateAndMonth(book);
        String date = bookingDate.get(0);
        context.setContext("bookingDate", date);

        String monthYear = bookingDate.get(1).trim();

        String[] parts = monthYear.split("\\s+");

        if (parts.length < 2) {
            throw new RuntimeException("Invalid monthYear format: " + monthYear);
        }

        String month = parts[0];
        String year = parts[1];

        String actualMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();

        context.setContext("bookingMonth", actualMonth);
        context.setContext("bookingYear", year);
        context.setContext("bookingMonthYear", actualMonth + " " + year);

        excursionManagementPage.clickBookButton(book);
        commonSteps.userClickOn(ExcursionManagementConstants.CONFIRM);
        String[] tourName = excursionManagementPage.getBookTittleNameText().split("/");
        context.setContext("bookTourName", tourName[1].trim());
        String totalValue = excursionManagementPage.getTotalValue();
        context.setContext("TOTAL_VALUE", totalValue);
    }

    @And("User draws signature for multi guest")
    public void userDrawsSignatureForMultiGuest() {
        for (int i = 1; i < 4; i++) {
            boolean handled = excursionManagementPage.drawSignatureIfVisible();
            if (!handled) {
                break;
            }
        }
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User select booked date and click booked tours")
    public void userSelectBookedDateAndClickBookedTours() {
        String date = context.getContext("bookingDate").toString();
        String monthYear = context.getContext("bookingMonthYear").toString();

        excursionManagementPage.selectDateFromCalendar(date, monthYear);

        String bookedTourName = context.getContext("bookTourName").toString();
        excursionManagementPage.clickOnTourName(bookedTourName);

    }

    @And("User view {string} plus button on Tour Activities")
    public void userViewPlusButtonOnTourActivities(String name) {
        excursionManagementPage.userViewPlusButtonOnTourActivities(name);

    }

    @And("User view save button on Tour Activity New")
    public void userViewSaveButtonOnTourActivityNew() {
        excursionManagementPage.userViewSaveButtonOnTourActivityNew();
    }

    @Then("User View language Save button")
    public void userViewLanguageSaveButton() {
        excursionManagementPage.userViewLanguageSaveButton();
    }

    @And("User click language Save button")
    public void userClickLanguageSaveButton() {
        excursionManagementPage.userClickLanguageSaveButton();
        String languageEdit = excursionManagementPage.getLanguage();
        context.setContext("languageValueEdit", languageEdit);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User validates the booking for multiple guests")
    public void userValidatesTheBookingForMultipleGuests() {
        homePage.navigateToService(ExcursionManagementConstants.EXCURSIONS);
        userViewsTheBookingsHandlingItemCard();
        userNavigateItemCard(ExcursionManagementConstants.BOOKINGS_HANDLING_CARDS);
        userSelectBookedDateAndClickBookedTours();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User select the tour and hit Book")
    public void userSelectTheTourAndHitBook() {
        excursionManagementPage.clickFullWidthBlock();

        List<String> bookingDate = excursionManagementPage.getBookingDateAndMonth(ExcursionManagementConstants.BOOK);
        String date = bookingDate.get(0);
        context.setContext("bookingDate", date);

        String monthYear = bookingDate.get(1).trim();

        String[] parts = monthYear.split("\\s+");

        if (parts.length < 2) {
            throw new RuntimeException("Invalid monthYear format: " + monthYear);
        }

        String month = parts[0];
        String year = parts[1];

        String actualMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();

        context.setContext("bookingMonth", actualMonth);
        context.setContext("bookingYear", year);
        context.setContext("bookingMonthYear", actualMonth + " " + year);
        excursionManagementPage.clickBookButton(ExcursionManagementConstants.BOOK);

    }

    @And("User view post button")
    public void userViewPostButton() {
        String[] tourName = excursionManagementPage.getBookTittleNameText().split("/");
        context.setContext("bookTourName", tourName[1].trim());
        String totalValue = excursionManagementPage.getTotalValue();
        context.setContext("TOTAL_VALUE", totalValue);
        excursionManagementPage.clickOnpostButton();
    }

    @Then("User views the respective guest booking information by last name, reservation number, or cabin number")
    public void userViewsTheRespectiveGuestBookingInformationByLastNameReservationNumberOrCabinNumber() {
        List<WebElement> bookingData = excursionManagementPage.guestBookingDetails();
        Assert.assertFalse(bookingData.isEmpty(), "Guest booking information is not displayed");
    }

    @And("User click on bookings guests add button")
    public void userClickOnBookingsGuestsAddButton() {
        excursionManagementPage.userClickOnBookingsGuestsAddButton();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User search for the special guest")
    public void userSearchForTheSpecialGuest() {
        String firstName = (String) context.getContext("guestFirstName");
        guestServicePage.searchUser(firstName);
    }

    @Then("User enter unmandatory fields in  new tour activity")
    public void userEnterUnmandatoryFieldsInNewTourActivity() {
        excursionManagementPage.userEnterUnmandatoryFieldsInNewTourActivity();
    }

    @And("User check the pricing by age group of each tour")
    public void userCheckThePricingByAgeGroupOfEachTour() {
        userVerifiesPricingForAgeGroup(ExcursionManagementConstants.ADULT);
        userVerifiesPricingForAgeGroup(ExcursionManagementConstants.MINOR);
        userVerifiesPricingForAgeGroup(ExcursionManagementConstants.INFANT);
    }

    @Then("User validates the minor tour booking details")
    public void userValidatesTheMinorTourBookingDetails() {
        userValidatesTheBookingForMultipleGuests();
        userSearchGuestFirstName();
        theUserViewsTheBookingInformation();
        context.setContext("SKIP_SCREENSHOT", "true");
    }


    @And("User Book a tour for selected {string} guest")
    public void userBookATourForSelectedGuest(String guestAge) {
        excursionManagementPage.clickFullWidthBlock();
        List<String> bookingDate = excursionManagementPage.getBookingDateAndMonth(ExcursionManagementConstants.BOOK);
        String date = bookingDate.get(0);
        context.setContext("bookingDate", date);

        String monthYear = bookingDate.get(1).trim();

        String[] parts = monthYear.split("\\s+");

        if (parts.length < 2) {
            throw new RuntimeException("Invalid monthYear format: " + monthYear);
        }

        String month = parts[0];
        String year = parts[1];

        String actualMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();

        context.setContext("bookingMonth", actualMonth);
        context.setContext("bookingYear", year);
        context.setContext("bookingMonthYear", actualMonth + " " + year);
        excursionManagementPage.userViewMinorCost(guestAge);
        excursionManagementPage.clickBookButton(ExcursionManagementConstants.BOOK);

        //commonSteps.userClickOn(ExcursionManagementConstants.CONFIRM);
        String[] tourName = excursionManagementPage.getBookTittleNameText().split("/");
        context.setContext("bookTourName", tourName[1].trim());
        String totalValue = excursionManagementPage.getTotalValue();
        context.setContext("TOTAL_VALUE", totalValue);
    }

    @Then("User checks the transportation management language")
    public void userChecksTheTransportationManagementLanguage() {
        userScrollsToTransportationManagement();
        userChecksEditIconAndHandlesLanguage();
        guestServicePage.refreshPage();
        commonSteps.userWaitForSeconds(ExcursionManagementConstants.WAITING_FOR_ELEMENT);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User view delete button icon")
    public void userViewDeleteButtonIcon() {
        excursionManagementPage.userViewDeleteButtonIcon();
    }


    @And("User {string} Add new Tour Activity plus button on Tour Activities")
    public void userAddNewTourActivityPlusButtonOnTourActivities(String action) {
        Assert.assertTrue(excursionManagementPage.isAddNewTourActivityPlusButtonDisplayed(), "Add new Tour Activity plus button is not displayed");

        switch (action.toLowerCase()) {
            case "view":
                excursionManagementPage.viewAddNewTourActivityPlusButton();
                break;
            case "click":
                excursionManagementPage.clickAddNewTourActivityPlusButton();
                context.setContext("SKIP_SCREENSHOT", "true");
                break;

            default: throw new IllegalArgumentException("Invalid action passed : " + action);
        }
    }

    @And("User enters {string} details in the {string} section on Tour Activity")
    public void userEntersDetailsInTheSectionOnTourActivity(String header, String option) {
        String actualHeader = excursionManagementPage.getTabName(header);
        Assert.assertEquals(actualHeader, header, "Expected Header : " + header + " but found : " + actualHeader);
        switch (option.toLowerCase()) {
            case ExcursionManagementConstants.ARTICLE_GENERAL_INFORMATION:
                excursionManagementPage.enterArticleGeneralInformationDetails();
                break;
            case ExcursionManagementConstants.OPERATIONS:
                excursionManagementPage.enterOperationsDetails();
                break;
            case ExcursionManagementConstants.DEPARTURE:
                excursionManagementPage._enterDeparturesDetails(header);
                excursionManagementPage.enterPricesSalesFeesDetails();
                break;
            default: throw new IllegalArgumentException("Invalid action passed : " + option);
        }
    }

    @Then("User save the general details")
    public void userSaveTheGeneralDetails() {
        WebElement tourCodeElement = excursionManagementPage.getTourCode();
        String tourCode = commonFunctions.readToastMessage(tourCodeElement).trim();
        context.setContext("tourCode", tourCode);
        WebElement nameENElement = excursionManagementPage.getNameEN();
        String nameEN = commonFunctions.readToastMessage(nameENElement)
                .replace(",", "")
                .replace("{", "")
                .replace("}", "")
                .trim();

        String cleanNameEN = nameEN.split("\\s+")[0];

        context.setContext("nameEN", cleanNameEN);

        logger.info("Cleaned Name EN : {}", cleanNameEN);

        String countryName = excursionManagementPage.getCountryName();
        context.setContext("countryName", countryName);

        String portName = excursionManagementPage.getPortName();
        context.setContext("portName", portName);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User clicks on a tour from the list after filtering by date")
    public void userClicksOnATourFromTheListAfterFilteringByDate() {
        String startDate = commonFunctions.getDateIncrementedBy(1, "M/d/yyyy");
        String endDate = commonFunctions.getDateIncrementedBy(180, "M/d/yyyy");
        excursionManagementPage.clickTheTourFromList(startDate, endDate);
    }

    @Then("User scrolls down to the Sales section of the page")
    public void userScrollsDownToTheSalesSectionOfThePage() {
        excursionManagementPage.userScrollsDownToTheSalesSectionOfThePage();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User validates that the enabled sales channels are displayed from the Tour Activities setup")
    public void userValidatesThatTheEnabledSalesChannelsAreDisplayedFromTheTourActivitiesSetup() {

        List<String> salesChannels = ExcursionManagementConstants.SALES_CHANNELS;
        for (String channel : salesChannels) {
            WebElement salesChannelName = excursionManagementPage.getSalesChannelName(channel);
            if (salesChannelName == null) {
                logger.info("Sales Channel '{}' is not displayed/enabled, skipping validation.", channel);
                continue;
            }
            seleniumActions.highLighterMethod(salesChannelName);
            Assert.assertTrue(salesChannelName.isDisplayed(), channel + " Sales Channel Name is not displayed");
            WebElement checkbox = excursionManagementPage.getSalesChannelCheckbox(channel);
            seleniumActions.highLighterMethod(checkbox);
            Assert.assertTrue(checkbox.isDisplayed(), channel + " checkbox is not displayed");
        }
    }


    @Then("User changes the guest status to {string}")
    public void userChangesTheGuestStatusTo(String menu) {
        userSearchGuestFirstName();
        commonSteps.userSelectsTheGuestWithRespectToTheAssignedTour();
        excursionManagementPage.selectBookingActionMenu(menu);
        commonSteps.userClickOnThe(menu);
        commonSteps.userViewsTheButton(ExcursionManagementConstants.CONFIRM);
        commonSteps.userClickOnThe(ExcursionManagementConstants.CONFIRM);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User should be able to view the {string} reservations")
    public void userShouldBeAbleToViewTheReservations(String status) {
        WebElement statusElement = excursionManagementPage.getBookingStatusElement(status);
        seleniumActions.highLighterMethod(statusElement);
        Assert.assertTrue(statusElement.isDisplayed(), status + " reservation status is not displayed");
    }

    @Then("User {string} tour for multi guest")
    public void userTourForMultiGuest(String book) {
        homePage.navigateToSelectedService(ExcursionManagementConstants.EXCURSIONS);
        userViewsTheBookingsManagerItemCard();
        excursionManagementPage.userNavigateItemCard(ExcursionManagementConstants.BOOKINGS_MANAGER_CARDS);
        excursionManagementPage.userClickOnBookingsGuestsAddButton();
        commonPage.clickOnLabel(ExcursionManagementConstants.GUESTS);
        userSelectsMultiGuestMembers();
        commonSteps.userClickOnThe(ExcursionManagementConstants.SELECT);
        userClearsTheDateFromTheCalendarFieldAndSelectsTheDateRange();
        userATourForSelectedGuest(book);
        excursionManagementPage.drawSignatureforConfirmation();
        commonSteps.userWaitForSeconds(ExcursionManagementConstants.WAITING_FOR_ELEMENT);
        context.setContext("SKIP_SCREENSHOT", "true");

    }

    @Then("User view on the edit icon for the departure time")
    public void userViewOnTheEditIconForTheDepartureTime() {
        Assert.assertTrue(excursionManagementPage.isEditButtonPresent(), "Edit button is NOT present in Departures & Prices section");
    }

    @And("User click on the edit icon for the departure time")
    public void userClickOnTheEditIconForTheDepartureTime() {
        excursionManagementPage.clickFirstEditButton();
    }

    @And("User add the Actual Agent Counts by age groups for the departure")
    public void userAddTheActualAgentCountsByAgeGroupsForTheDeparture() {
        Map<String, String> counts = excursionManagementPage.enterActualAgentCounts();
        counts.forEach((key, value) -> context.setContext(key, value));
        excursionManagementPage.saveButtonDisplay();
    }

    @Then("User is able to reconcile actual tour attendance numbers")
    public void userIsAbleToReconcileActualTourAttendanceNumbers() {
        Map<String, String> actualCounts = excursionManagementPage.getActualAgentCountsFromTable();
        actualCounts.forEach((key, actualValue) -> {
            String expectedValue = String.valueOf(context.getContext(key));
            Assert.assertEquals(actualValue, expectedValue, "Count mismatch for " + key);
        });
    }

    @And("User click on the full width block")
    public void userClickOnTheFullWidthBlock() {
        excursionManagementPage.clickFullWidthBlock();
    }

    @Then("User should be able to view the tour details including reservation number, price, date, port country code, passenger counts, and persons per group")
    public void userShouldBeAbleToViewTheTourDetailsIncludingReservationNumberPriceDatePortCountryCodePassengerCountsAndPersonsPerGroup() {
        List<String> actualTourDetails = excursionManagementPage.getTourDetails();
        Assert.assertEquals(actualTourDetails.size(), 5, "Expected 5 tour details but found " + actualTourDetails.size());
        Assert.assertTrue(excursionManagementPage.areTourDetailsDisplayed(), "Tour details are not displayed.");
    }

    @And("User enter the required code")
    public void userEnterTheRequiredCode() {
        String randomCode = excursionManagementPage.searchRandomExcursionCode();
        context.setContext("EXCURSION_CODE", randomCode);
    }

    @Then("User verifies the searched excursion code matches the selected code")
    public void userVerifiesTheSearchedExcursionCodeMatchesTheSelectedCode() {
        String expectedCode = context.getContext("EXCURSION_CODE").toString();
        String actualCode = excursionManagementPage.getFirstDisplayedExcursionCode();
        Assert.assertTrue(actualCode.contains(expectedCode), "Excursion code mismatch.");
    }

    @And("User selects the {string} filter, chooses {string}, and adds a future date")
    public void userSelectsTheFilterChoosesAndAddsAFutureDate(String dropdown, String option) {
        userClearsTheDateFromTheCalendarFieldAndSelectsTheDateRange();
        excursionManagementPage.userSelectsTheFilterChoosesAndAddsAFutureDate(dropdown, option);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User clicks the three-dot button on the Bookings List page and view {string}")
    public void userClicksTheThreeDotButtonOnTheBookingsListPageAndView(String menu) {
        excursionManagementPage.userClicksTheThreeDotButtonOnTheBookingsListPage(menu);
    }

    @And("User selects the Post Excursion Booking time")
    public void userSelectsThePostExcursionBookingTime() {
        excursionManagementPage.userSelectsThePostExcursionBookingTime();
    }

    @Then("User view first next button")
    public void userViewFirstNextButton() {
        excursionManagementPage.userViewFirstNextButton();
    }

    @And("User clicks the first Next button and navigates to the second next button")
    public void userClicksTheFirstNextButtonAndNavigatesToTheSecondNextButton() {
        excursionManagementPage.userClicksTheFirstNextButtonAndNavigatesToTheSecondNextButton();
    }

    @And("User clicks the Post Booking radio button")
    public void userClicksThePostBookingRadioButton() {
        excursionManagementPage.userClicksThePostBookingRadioButton();
    }

    @And("User clicks the Confirm button on the Operation Details page")
    public void userClicksTheConfirmButtonOnTheOperationDetailsPage() {
        excursionManagementPage.userClicksTheConfirmButtonOnTheOperationDetailsPage();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User verifies the Success Count and Status")
    public void userVerifiesTheSuccessCountAndStatus() {

        Assert.assertTrue(excursionManagementPage.isSuccessCountDisplayed(),
                "Success Count '1' is not displayed.");

        Assert.assertTrue(excursionManagementPage.isDoneStatusDisplayed(),
                "Status 'Done' is not displayed.");
    }

    @And("User scrolls to the Payer column")
    public void userScrollsToThePayerColumn() {
        Assert.assertTrue(excursionManagementPage.scrollToPayerColumn(), "Payer column is not displayed after scrolling.");
    }

    @Then("User verifies that the payer information is displayed")
    public void userVerifiesThatThePayerInformationIsDisplayed() {
        String guestName = ((String) context.getContext("firstguestName")).trim();
        String payerName = excursionManagementPage.getPayerInformation();
        Assert.assertTrue(payerName.toUpperCase().contains(guestName.toUpperCase()), "Expected guest name '" + guestName + "' was not found in payer information '" + payerName + "'.");
    }

    @Then("User verifies that the Post button is enabled")
    public void userVerifiesThatThePostButtonIsEnabled() {
        excursionManagementPage.clickFullWidthBlock();
        Assert.assertTrue(excursionManagementPage.isBookingTourButtonPresent(), "Booking Tour button is not displayed.");
        Assert.assertTrue(excursionManagementPage.isPostButtonEnabled(), "Post button is disabled.");
    }

    @Then("User verifies the two records created successfully toast message is displayed")
    public void userVerifiesTheRecordsCreatedSuccessfullyToastMessageIsDisplayed() {
        excursionManagementPage.highlightSuccessToastMessage();
        String actualMessage = excursionManagementPage.getSuccessToastMessage().getText();
        Assert.assertEquals(actualMessage, "2 records were created successfully", "Unexpected success toast message.");

        logger.info("Toast Message: {}", actualMessage);
    }

    @And("User changes the date of the selected guest tour")
    public void userChangesTheDateOfTheSelectedGuestTour() {
        String tomorrow = commonFunctions.getDateIncrementedBy(1, ExcursionManagementConstants.M_D_YEAR);
        String sixMonthsRange = commonFunctions.getDateIncrementedBy(180, ExcursionManagementConstants.M_D_YEAR);
        excursionManagementPage.userChangesTheDateOfTheSelectedGuestTour(tomorrow, sixMonthsRange);
    }

    @And("User view the success pop up message")
    public void userViewTheSuccessPopUpMessage() {
        Assert.assertTrue(excursionManagementPage.userViewTheSuccessPopUpMessage(), "Success pop-up message is not displayed.");
    }

    @And("User updates the tour capacity")
    public void userUpdatesTheTourCapacity() {
        excursionManagementPage.userUpdatesTheTourCapacity();

        Map<String, Integer> capacityValues = excursionManagementPage.updateTourCapacity();

        context.setContext("minCapacity", String.valueOf(capacityValues.get("minCapacity")));
        context.setContext("maxCapacity", String.valueOf(capacityValues.get("maxCapacity")));
        context.setContext("availableCapacity", String.valueOf(capacityValues.get("availableCapacity")));

        logger.info("Min Capacity : {}", capacityValues.get("minCapacity"));
        logger.info("Max Capacity : {}", capacityValues.get("maxCapacity"));
        logger.info("Available Capacity : {}", capacityValues.get("availableCapacity"));


    }

    @And("User searches for the updated tour")
    public void userSearchesForTheUpdatedTour() {
        String value = context.getContext("tourCode");
        excursionManagementPage.searchExcursionBookingsOverview(value);

    }

    @Then("User verifies the tour capacity is updated")
    public void userVerifiesTheTourCapacityIsUpdated() {
        excursionManagementPage.expandingTheTourDetails();
        String tourCode = context.getContext("tourCode");

        String actualMinCapacity = excursionManagementPage.getTourCapacityValue(ExcursionManagementConstants.MIN);
        String actualMaxCapacity = excursionManagementPage.getTourCapacityValue(ExcursionManagementConstants.MAX);
        String actualAvailableCapacity = excursionManagementPage.getTourCapacityValue(ExcursionManagementConstants.AVAILABLE);

        Assert.assertNotEquals(actualMinCapacity, context.getContext("minCapacity"), "Min Capacity should not match.");

        Assert.assertNotEquals(actualMaxCapacity, context.getContext("maxCapacity"), "Max Capacity does not match.");

        Assert.assertNotEquals(actualAvailableCapacity, context.getContext("availableCapacity"), "Available Capacity does not match.");

        logger.info("Verified Tour Code        : {}", tourCode);
        logger.info("Verified Min Capacity     : {}", actualMinCapacity);
        logger.info("Verified Max Capacity     : {}", actualMaxCapacity);
        logger.info("Verified Available Capacity : {}", actualAvailableCapacity);
    }

    @And("User view before tour capacity")
    public void userViewBeforeTourCapacity() {
        guestServicePage.openGuestServiceItem(ExcursionManagementConstants.BOOKINGS_MANAGER);
        userClearsTheDateFromTheCalendarFieldAndSelectsTheDateRange();
        excursionManagementPage.clickFullWidthBlock();
        String tourCode = excursionManagementPage.clickTourLinkAndGetTourCode();
        context.setContext("tourCode", tourCode);
        logger.info("Stored Tour Code: {}", tourCode);
        guestServiceRequestPage.switchToNewWindow();
        excursionManagementPage.userViewBeforeTourCapacity();
    }

    @And("User navigate the tour list")
    public void userNavigateTheTourList() {
        excursionManagementPage.tourSaveButton();
        homePage.navigateToSelectedService(ExcursionManagementConstants.EXCURSIONS);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User selects a guest from a different reservation")
    public void userSelectsAGuestFromADifferentReservation() {
       excursionManagementPage.userSelectsAGuestFromADifferentReservation();
    }

    @And("User select a guest person")
    public void userSelectAGuestPerson() {
        excursionManagementPage.userSearchForTheGuest();
    }

    @Then("User navigate Operation Details page")
    public void userNavigateOperationDetailsPage() {
       excursionManagementPage.userNavigateOperationDetailsPage();
    }
    @And("User selects the {string} tab")
    public void userSelectsTheTab(String header) {
        String actualHeader = excursionManagementPage.getTabName(header);
        Assert.assertEquals(actualHeader, header, "Expected Header : " + header + " but found : " + actualHeader);

    }

    @And("User clicks the Plus icon and fills in the {string} dialog details")
    public void userClicksThePlusIconAndFillsInTheDialogDetails(String header) {
        excursionManagementPage.addServicePlusIcon();
        excursionManagementPage.userClicksThePlusIconAndFillsInTheDialogDetails(header);
    }

    @And("User gets the tour details from the booking tour list")
    public void userGetsTheTourDetailsFromTheBookingTourList() {
        userViewsTheBookingsManagerItemCard();
        excursionManagementPage.userNavigateItemCard(ExcursionManagementConstants.BOOKINGS_MANAGER_CARDS);
        userClearsTheDateFromTheCalendarFieldAndSelectsTheDateRange();
        excursionManagementPage.clickFullWidthBlock();
        List<String> bookingDate = excursionManagementPage.getBookingDateAndMonth(ExcursionManagementConstants.BOOK);
        String date = bookingDate.get(0);
        context.setContext("bookingDate", date);
        String monthYear = bookingDate.get(1).trim();
        String[] parts = monthYear.split("\\s+");
        if (parts.length < 2) {
            throw new RuntimeException("Invalid monthYear format: " + monthYear);
        }
        String month = parts[0];
        String year = parts[1];
        String actualMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
        context.setContext("bookingMonth", actualMonth);
        context.setContext("bookingYear", year);
        context.setContext("bookingMonthYear", actualMonth + " " + year);
        homePage.navigateToSelectedService(ExcursionManagementConstants.EXCURSIONS);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User select booked date and click booked tours link")
    public void userSelectBookedDateAndClickBookedToursLink() {
        String date = context.getContext("bookingDate").toString();
        String monthYear = context.getContext("bookingMonthYear").toString();
        excursionManagementPage.selectDateFromCalendar(date,monthYear);
        excursionManagementPage.clickOnTourLink();
    }

    @And("User gets the current Meeting Location dropdown value")
    public void userGetsTheCurrentMeetingLocationDropdownValue() {
        String meetingLocationBefore = excursionManagementPage.getMeetingLocationDropdownValue();
        context.setContext("meetingLocationBefore", meetingLocationBefore);
        logger.info("Meeting Location before change: {}", meetingLocationBefore);
    }

    @And("User clicks on the Meeting Location dropdown")
    public void userClicksOnTheMeetingLocationDropdown() {
        excursionManagementPage.userClicksOnTheMeetingLocationDropdown();

        String meetingLocationAfter = excursionManagementPage.getMeetingLocationDropdownValue();
        context.setContext("meetingLocationAfter", meetingLocationAfter);

        logger.info("Meeting Location after change: {}", meetingLocationAfter);

        Assert.assertNotEquals(context.getContext("meetingLocationBefore"), context.getContext("meetingLocationAfter"), "Meeting Location dropdown value was not changed.");
    }

    @And("User updates the Tour Max Weight in Lbs")
    public void userUpdatesTheTourMaxWeightInLbs() {
        userViewsTheBookingsManagerItemCard();
        excursionManagementPage.userNavigateItemCard(ExcursionManagementConstants.BOOKINGS_MANAGER_CARDS);
        userClearsTheDateFromTheCalendarFieldAndSelectsTheDateRange();
        excursionManagementPage.clickFullWidthBlock();
        String tourCode = excursionManagementPage.clickTourLinkAndGetTourCode();
        context.setContext("tourCode", tourCode);
        logger.info("Stored Tour Code: {}", tourCode);
        guestServiceRequestPage.switchToNewWindow();
        excursionManagementPage.userUpdatesTheTourMaxWeightInLbs();
        String tourMaxWeight = excursionManagementPage.userUpdatesTheTourMaxWeightInLbs();
        context.setContext("tourMaxWeightLbs", tourMaxWeight);
        excursionManagementPage.tourSaveButton();
        homePage.navigateToSelectedService(ExcursionManagementConstants.EXCURSIONS);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User verifies the weight limit is updated")
    public void userVerifiesTheWeightLimitIsUpdated() {
        String tourMaxWeightLbs = context.getContext("tourMaxWeightLbs").toString();

        int expectedKgs = (int) Math.floor(Double.parseDouble(tourMaxWeightLbs) * 0.45359237);

        String updatedMaxWeight = excursionManagementPage.getFirstMaxTotalWeight();
        String weight = updatedMaxWeight.split("/")[0].trim();

        int actualKgs = Integer.parseInt(weight);

        logger.info("Expected Kgs : {}", expectedKgs);
        logger.info("Actual Kgs   : {}", actualKgs);

        Assert.assertEquals(
                actualKgs,
                expectedKgs,
                "Tour Max Weight was not converted correctly from Lbs to Kgs."
        );
    }

    @And("User {string} a tour with a minimum age restriction")
    public void userATourWithAMinimumAgeRestriction(String option) {
        homePage.navigateToSelectedService(ExcursionManagementConstants.EXCURSIONS);
        userViewsTheBookingsManagerItemCard();
        excursionManagementPage.userNavigateItemCard(ExcursionManagementConstants.BOOKINGS_MANAGER_CARDS);
        userClearsTheDateFromTheCalendarFieldAndSelectsTheDateRange();
        excursionManagementPage.clickFullWidthBlock();
        excursionManagementPage.clickLastTourLinkAndGetTourCode();
        guestServiceRequestPage.switchToNewWindow();
        switch (option.toLowerCase()) {
            case ExcursionManagementConstants.UPDATE:
                excursionManagementPage.userATourWithAMinimumAgeRestriction();
                excursionManagementPage.tourSaveButton();
                break;

            case ExcursionManagementConstants.CLEAR:
                excursionManagementPage.userATourWithAMinimumAgeRestrictionClear();
                excursionManagementPage.tourSaveButton();
                break;

            default:
                throw new IllegalArgumentException("Invalid option: " + option);
        }
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User selects the tour with the minimum age restriction and clicks the Book button")
    public void userSelectsTheTourWithTheMinimumAgeRestrictionAndClicksTheBookButton() {
        excursionManagementPage.clickFullWidthBlock();
        excursionManagementPage.scrollToLastTourLink();
        Assert.assertTrue(excursionManagementPage.isBookingTourAgeRestricateButtonPresent(), "Booking Tour button is not displayed.");
        Assert.assertTrue(excursionManagementPage.ageRestricatePopUpErrorMessage(),"Error message not display");
    }

    @And("User close pop up")
    public void userClosePopUp() {
        excursionManagementPage.userClosePopUp();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User verifies the Max and Total Weight details are displayed")
    public void userVerifiesTheMaxTotalWeightDetailsAreDisplayed() {

        String actualLabel = excursionManagementPage.getFirstMaxTotalWeightLabel();
        String actualValue = excursionManagementPage.getFirstMaxTotalWeightValue();

        Assert.assertEquals(actualLabel, "Max / Total Weight", "Max / Total Weight label mismatch.");

        Assert.assertFalse(actualValue.trim().isEmpty(), "Max / Total Weight value should not be empty.");

        String[] values = actualValue.split("/");

        Assert.assertEquals(values.length, 2, "Expected Max / Total Weight to contain 2 values.");

        for (String value : values) {
            Assert.assertFalse(value.trim().isEmpty(), "One of the Max / Total Weight values is empty: " + actualValue);
        }
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User verifies the Min, Max and Available Capacity details are displayed")
    public void userVerifiesTheMinMaxAndAvailableCapacityDetailsAreDisplayed() {
        String actualLabel = excursionManagementPage.getFirstMinMaxAvailableCapacityLabel();
        String actualValue = excursionManagementPage.getFirstMinMaxAvailableCapacityValue();

        Assert.assertEquals(actualLabel, "Min / Max / Available Capacity", "Min / Max / Available Capacity label mismatch.");

        Assert.assertFalse(actualValue.trim().isEmpty(), "Min / Max / Available Capacity value should not be empty.");

        String[] values = actualValue.split("/");

        Assert.assertEquals(values.length, 3, "Expected Min / Max / Available Capacity to contain 3 values.");

        for (String value : values) {
            Assert.assertFalse(value.trim().isEmpty(), "One of the Min / Max / Available Capacity values is empty: " + actualValue);
        }
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User verifies the Available and Booked details are displayed")
    public void userVerifiesTheAvailableAndBookedDetailsAreDisplayed() {
        String actualLabel = excursionManagementPage.getFirstAvailableBookedLabel();
        String actualValue = excursionManagementPage.getFirstAvailableBookedValue();

        Assert.assertFalse(actualValue.trim().isEmpty(), "Available / Booked value should not be empty.");

        String[] values = actualValue.split("/");

        if (actualLabel.equals("Available / Booked")) {

            Assert.assertEquals(actualLabel, "Available / Booked", "Label mismatch.");

            Assert.assertEquals(values.length, 2, "Expected Available / Booked to contain 2 values.");

        } else if (actualLabel.equals("Available / Booked / Waitlist")) {

            Assert.assertEquals(actualLabel, "Available / Booked / Waitlist", "Label mismatch.");

            Assert.assertEquals(values.length, 3, "Expected Available / Booked / Waitlist to contain 3 values.");

        } else {
            Assert.fail("Unexpected label found: " + actualLabel);
        }

        for (String value : values) {
            Assert.assertFalse(value.trim().isEmpty(), "One of the values is empty: " + actualValue);
        }
    }

    @And("User adds a custom fee for discount reasons")
    public void userAddsACustomFeeForDiscountReasons() {
        excursionManagementPage.userAddsACustomFeeForDiscountReasons();
    }

    @And("User clicks the {string} link")
    public void userClicksTheLink(String option) {
        int shoreExcursionCount = excursionManagementPage.getShoreExcursionsCount();

        context.setContext("SHORE_EXCURSION_COUNT", String.valueOf(shoreExcursionCount));

        Assert.assertTrue(shoreExcursionCount > 0, "Shore Excursions count should be greater than 0, but found: " + shoreExcursionCount);
        excursionManagementPage.userClicksTheLink();
    }

    @Then("User validates the respective guest Shore Excursions tours")
    public void userValidatesTheRespectiveGuestShoreExcursionsTours() {
        Assert.assertTrue(excursionManagementPage.clickFirstRow(), "No data row is available in the table.");
    }

    @And("User adds a discount to the searched member")
    public void userAddsADiscountToTheSearchedMember() {
        commonSteps.userClickOn(ExcursionManagementConstants.CONFIRM);
        excursionManagementPage.userAddsADiscountToTheSearchedMember();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User validates the applied discount")
    public void userValidatesTheAppliedDiscount() {
        Assert.assertTrue(excursionManagementPage.isDiscountAppliedCorrectly(), "Discount calculation is incorrect. Expected Debit Amount = Gross Amount - Discount.");
    }


    @Then("User selects the tour from Bookings Manager")
    public void userSelectsTheTourFromBookingsManager() {
        homePage.navigateToSelectedService(ExcursionManagementConstants.EXCURSIONS);
        userViewsTheBookingsManagerItemCard();
        excursionManagementPage.userNavigateItemCard(ExcursionManagementConstants.BOOKINGS_MANAGER_CARDS);
        userClearsTheDateFromTheCalendarFieldAndSelectsTheDateRange();
        excursionManagementPage.clickFullWidthBlock();
        List<String> bookingDate = excursionManagementPage.getLastTourBookingDateAndMonth(ExcursionManagementConstants.BOOK);
        String date = bookingDate.get(0);
        context.setContext("bookingDate", date);
        String monthYear = bookingDate.get(1).trim();
        String[] parts = monthYear.split("\\s+");
        if (parts.length < 2) {
            throw new RuntimeException("Invalid monthYear format: " + monthYear);
        }
        String month = parts[0];
        String year = parts[1];
        String actualMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
        context.setContext("bookingMonth", actualMonth);
        context.setContext("bookingYear", year);
        context.setContext("bookingMonthYear", actualMonth + " " + year);

        String[] tourDetails = excursionManagementPage.getLastTourCode().split("/");
        context.setContext("bookTourName", tourDetails[0].trim());

        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User select booked date and click booked tours code link")
    public void userSelectBookedDateAndClickBookedToursCodeLink() {
        String date = context.getContext("bookingDate").toString();
        String monthYear = context.getContext("bookingMonthYear").toString();
        excursionManagementPage.selectDateFromCalendar(date,monthYear);
        String tourCode = context.getContext("bookTourName").toString();
        excursionManagementPage.clickOnTheTourCodeLink(tourCode);
    }

    @Then("User enters the tour maximum weight and maximum weight per guest under the Operations section")
    public void userEntersTheTourMaximumWeightAndMaximumWeightPerGuestUnderTheOperationsSection() {
        excursionManagementPage.addMaximumWeightAndMaximumWeightPerGuestForSelectedTour();
        excursionManagementPage.tourSaveButton();
    }

    @And("User clicks on the tour that contains the added weight limit per person")
    public void userClicksOnTheTourThatContainsTheAddedWeightLimitPerPerson() {
        excursionManagementPage.clickFullWidthBlock();
        excursionManagementPage.clickOnLastBookedTour();
        commonSteps.userClickOn(ExcursionManagementConstants.CONFIRM);

    }

    @And("User clicks on the {string} tab")
    public void userClicksOnTheTab(String option) {
        excursionManagementPage.userClicksOnTheTab();
    }

    @Then("User enters the weight limit for the selected guest and verifies the error message is displayed")
    public void userEntersTheWeightLimitForTheSelectedGuestAndVerifiesTheErrorMessageIsDisplayed() {
        int guestCount = excursionManagementPage.getGuestCount();
        for (int i = 0; i < guestCount; i++) {
            excursionManagementPage.clickGuestByIndex(i);
            excursionManagementPage.updateWeight();
            excursionManagementPage.clickOnWeightSaveButton();
            context.setContext("SKIP_SCREENSHOT", "false");
            if (i == 1) {
                Assert.assertTrue(excursionManagementPage.isWeightLimitErrorDisplayed(), "Weight limit error message is not displayed.");
            }
        }
        context.setContext("SKIP_SCREENSHOT", "false");
    }
    @Then("User verifies the capacity values for each departure time")
    public void userVerifiesTheCapacityValuesForEachDepartureTime() {
        List<WebElement> countElements = Arrays.asList(excursionManagementPage.getTxtMinCount(), excursionManagementPage.getTxtMaxCount(), excursionManagementPage.getTxtAvailableCount());

        String[] countNames = {ExcursionManagementConstants.MIN, ExcursionManagementConstants.MAX, ExcursionManagementConstants.AVAILABLE};

        for (int i = 0; i < countElements.size(); i++) {

            int count = Integer.parseInt(countElements.get(i).getText().trim());

            Assert.assertTrue(count > 0,
                    String.format("Expected %s count to be greater than 0, but found %d",
                            countNames[i], count));
        }
    }

    @Then("User view Print & Post button")
    public void userViewPrintPostButton() {
        excursionManagementPage.viewPrintPostButton();
    }

    @And("User enters all required fields NCLH page and clicks on the {string} button")
    public void userEntersAllRequiredFieldsNCLHPageAndClicksOnTheButton(String button) {
        String from = commonFunctions.getDateIncrementedBy(-2, "M/d/yyyy");
        String to = commonFunctions.getDateIncrementedBy(-1, "M/d/yyyy");
        excursionManagementPage.nclhPageAndClicksOnTheButton(from, to, button);
    }

    @And("User click on shorex excursion link")
    public void userClickOnShorexExcursionLink() {
        excursionManagementPage.clickONShoreExcursionLink();
    }
    @Then("User verifies the guest status is displayed")
    public void userVerifiesTheGuestStatusIsDisplayed() {
        excursionManagementPage.waitTillBookingDropDownDisplay();
        Assert.assertTrue(excursionManagementPage.isTextDisplayed(), "Guest status is not displayed or the displayed status is not a valid status.");
    }
    @And("User select the date of the desired tour user wants to print ticket for")
    public void userSelectTheDateOfTheDesiredTourUserWantsToPrintTicket() {
        userClearsTheDateFromTheCalendarFieldAndSelectsTheDateRange();
        safetyManagementPage.userAbleToClickInTheDetailTable(ExcursionManagementConstants.CHECKBOX);
        excursionManagementPage.userSelectTheDateOfTheDesiredTourUserWantsToPrintTicket();
    }

    @And("User click on Choose Operation Group tab Next button")
    public void userClickOnChooseOperationGroupTabNextButton() {
        excursionManagementPage.nextButtonChooseOperationGroupTab();
    }

    @And("User selects {string} from the dropdown")
    public void userSelectsFromTheDropdown(String dropdown) {
        excursionManagementPage.userSelectsFromTheDropdown(dropdown);
    }

    @Then("User validate the {int} and print ticket")
    public void userValidateTheAndPrintTicket(int userCount) {
        guestServicePage.refreshPage();
        int successCount;
        successCount = Integer.parseInt(crewInformationPage.validateSuccessCount(userCount));
        Assert.assertEquals(userCount, successCount);
        Assert.assertEquals(crewInformationPage.validateStatus(), "Done");
    }

    @And("User clicks the {string} button in the Operation Details tab")
    public void userClicksTheButtonInTheOperationDetailsTab(String arg0) {
        excursionManagementPage.clicksTheButtonInTheOperationDetailsTab();
    }

    @And("User checks the required date and time from the list below")
    public void userChecksTheRequiredDateAndTimeFromTheListBelow() {
        String tomorrow = commonFunctions.getDateIncrementedBy(1, "M/d/yyyy");
        String sixMonthsRange = commonFunctions.getDateIncrementedBy(180, "M/d/yyyy");
        excursionManagementPage.requiredDateAndTimeFromTheListBelow(tomorrow,sixMonthsRange);
    }

    @Then("User clicks the three-dot button and highlights {string}")
    public void userClicksTheThreeDotButtonAndHighlights(String menu) {
        excursionManagementPage.selectBookingActionMenu(menu);
    }

    @And("User selects the respective tour from the list")
    public void userSelectsTheRespectiveTourFromTheList() {
        excursionManagementPage.selectsTheRespectiveTourFromTheList();
    }

    @And("User enters random text in the comment box")
    public void userEntersRandomTextInTheCommentBox() {
        String randomComment = excursionManagementPage.enterRandomComment();
        context.setContext("randomComment", randomComment);
    }
    @Then("User clicks on the three-dot button and views the {string} option")
    public void userClicksOnTheThreeDotButtonAndViewsTheOption(String menu) {
        excursionManagementPage.selectBookingActionMenu(menu);
    }

    @Then("User verifies the guest status is {string} displayed")
    public void userVerifiesTheGuestStatusIsDisplayed(String expectedStatus) {
        excursionManagementPage.waitTillBookingDropDownDisplay();
        Assert.assertTrue(excursionManagementPage.isWaitlistDisplayed(expectedStatus), "Expected status '" + expectedStatus + "' was not displayed.");
    }

    @Then("User view the {string} button under Sales")
    public void userViewTheButtonUnderSales(String arg0) {
        excursionManagementPage.viewFirstSalesChannelEditButton();
    }

    @And("User click the {string} button under Sales")
    public void userClickTheButtonUnderSales(String arg0) {
        excursionManagementPage.clickFirstSalesChannelEditButton();
        context.setContext("SKIP_SCREENSHOT", "true");
    }
    @And("User verifies that the Active toggle is enabled and displayed in blue color")
    public void userVerifiesThatTheActiveToggleIsEnabledAndDisplayedInBlueColor() {
        String colourCode = excursionManagementPage.toggleColor();
        String actualHexColor = Color.fromString(colourCode).asHex();
        Assert.assertEquals(actualHexColor, "#2c67bf", "Toggle is not displayed in blue color");

        WebElement activeToggle = excursionManagementPage.toggleEnabledInActiveSalesChannel();
        Assert.assertEquals(activeToggle.getAttribute(ExcursionManagementConstants.ARIA_CHECKED), ExcursionManagementConstants.TRUE, "Active toggle is not enabled");
    }

    @And("User after enables the toggle and clicks the {string} button")
    public void userAfterEnablesTheToggleAndClicksTheButton(String text) {
        excursionManagementPage.userAfterEnablesTheToggleAndClicksTheButton();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("Guest must have a posted booked excursion")
    public void guestMustHaveAPostedBookedExcursion() {
        homePage.navigateToSelectedService(ExcursionManagementConstants.EXCURSIONS);
        commonSteps.userClickOn(ExcursionManagementConstants.UNPOSTED_BOOKINGS_HANDLING);
        excursionManagementPage.searchRandomExcursionCode();
        excursionManagementPage.searchExcursionBookingsOverview(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        crewInformationPage.selectCrewForCabinAssign(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        excursionManagementPage.userClicksTheThreeDotButtonOnTheBookingsListPage(ExcursionManagementConstants.PRINT_AND_POST);
        commonSteps.userClickOn(ExcursionManagementConstants.PRINT_AND_POST);
        commonSteps.userClickOn(ExcursionManagementConstants.CONFIRM);
        commonPage.clickOnLabel(ExcursionManagementConstants.CHOOSE_OPERATION_GROUPS);
        excursionManagementPage.nextButtonChooseOperationGroupTab();
        excursionManagementPage.userClicksThePostBookingRadioButton();
        excursionManagementPage.userSelectsFromTheDropdown(ExcursionManagementConstants.DEFAULT_PRINTER);
        userClicksTheConfirmButtonOnTheOperationDetailsPage();
    }

    @Then("User views the Bookings Manager item card")
    public void userViewsTheBookingsManagerItemCard() {
        WebElement bookingsManagerCard = excursionManagementPage.getBookingsManagerCard();
        seleniumActions.highLighterMethod(bookingsManagerCard);
    }

    @Then("User views the Bookings Handling item card")
    public void userViewsTheBookingsHandlingItemCard() {
        WebElement bookingsHandlingCard = excursionManagementPage.getBookingsHandlingCard();

        seleniumActions.highLighterMethod(bookingsHandlingCard);
    }

    @And("User navigate {string} item card")
    public void userNavigateItemCard(String card) {
        excursionManagementPage.userNavigateItemCard(card);

    }
}
