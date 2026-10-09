package com.nclh.webSteps;

import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.*;
import com.nclh.utilities.Context;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EntertainmentSteps extends AllDriverManager {

    HouseKeepingPage houseKeepingPage;
    HotelMaintenancePage hotelMaintenancePage;
    EntertainmentPage entertainmentPage;
    PointOfSalePage pointOfSalePage;
    ShipSecurityPage shipSecurityPage;
    SafetyManagementPage safetyManagementPage;
    Context context;
    CommonPage commonPage;

    public EntertainmentSteps(Context context) {
        this.context = context;
        houseKeepingPage = new HouseKeepingPage(getDriver());
        entertainmentPage = new EntertainmentPage(getDriver());
        hotelMaintenancePage = new HotelMaintenancePage(getDriver());
        shipSecurityPage = new ShipSecurityPage(getDriver());
        safetyManagementPage = new SafetyManagementPage(getDriver());
        pointOfSalePage = new PointOfSalePage(getDriver());
        commonPage = new CommonPage(getDriver());
    }

    @Then("User add an event")
    public void user_add_an_event() {
        entertainmentPage.clickOnAddButton();
    }


    @And("User enter the code")
    public void userEnterTheCode() {
        int number = entertainmentPage.getRandomNumber();
        System.out.println(number);
        context.setContext("codeNumber",String.valueOf(number));
        entertainmentPage.enterFieldText(number);
    }

    @And("User Enter the Description field as {string}")
    public void userEnterTheDescriptionFieldAs(String text) {
        int number = entertainmentPage.getRandomNumber();
        context.setContext("descriptionValue",text+number);
        entertainmentPage.enterDescriptionField(context.getContext("descriptionValue"));
        System.out.println(context.getContext("descriptionValue"));

    }

    @And("User select category as {string}")
    public void userSelectCategoryAs(String category) {
        entertainmentPage.categoryDropDown(category);
    }

    @And("User switch to Events tab")
    public void userSwitchToEventsTab() {
        entertainmentPage.clickEventsTab();
    }

    @And("User enable  toggle {string}")
    public void userEnableToggle(String toggleName) {
        entertainmentPage.enableToggle(toggleName);
    }

    @Then("user set the default time as {string}")
    public void userSetTheDefaultTimeAs(String time) {
        entertainmentPage.setDefaultSetUpTime(time);
    }

    @Then("user set the default  cleanup time as {string}")
    public void userSetTheDefaultCleanupTimeAs(String time) {
        entertainmentPage.setDefaultCleanUpTime(time);
    }

    @And("User enter the entertainment options as {string}")
    public void userEnterTheEntertainmentOptionsAs(String text) {
        int number = entertainmentPage.getRandomNumber();
        context.setContext("Entertainment",text+number);
        entertainmentPage.enterEntertainmentOptions(context.getContext("Entertainment"));
        System.out.println(context.getContext("Entertainment"));
    }

    @And("User add default required personal as {string}")
    public void userAddDefaultRequiredPersonalAs(String operation) {
        entertainmentPage.clickAddRequiredPersonal(operation);
    }

    @And("User enter the min value as {string} and max value as {string}")
    public void userEnterTheMinValueAsAndMaxValueAs(String min, String max) {
        entertainmentPage.enterMinAndMaxValue(min,max);
    }

    @And("User set the description filter")
    public void userSetTheDescriptionFilter() {
        String value = context.getContext("descriptionValue");
        entertainmentPage.setDescriptionFilter(value);
    }

    @And("User save the parent window id")
    public void userSaveTheParentWindowId() {
        String parentWindow = entertainmentPage.getParentWindowId();
        context.setContext("parent",parentWindow);
    }

    @And("User switch back to parent window")
    public void userSwitchBackToParentWindow() {
        entertainmentPage.switchToParentWindow(context.getContext("parent"));
    }

    @And("User switch back to {string} window")
    public void userSwitchBackToWindow(String windowName) {
        entertainmentPage.switchToWindowUsingWindowTitle(windowName);
    }


    @And("User verify the code")
    public void userVerifyTheCode() {
        String expected = context.getContext("codeNumber");
        String text = safetyManagementPage.getSafetyNumberLinkText();
        System.out.println(text);
        Assert.assertEquals(expected,text);
    }

    @And("User add an icon")
    public void userAddAnIcon() {
        entertainmentPage.switchToChildWindow();
        entertainmentPage.addIcon();
    }

    @And("User enter the venue Requirements as {string}")
    public void userEnterTheVenueRequirementsAs(String text) {
        int number = entertainmentPage.getRandomNumber();
        context.setContext("venue",text+number);
        entertainmentPage.enterVenueRequirements(context.getContext("venue"));
        System.out.println(context.getContext("venue"));

    }

    @And("User click on the code")
    public void userClickOnTheCode() {
        entertainmentPage.clickCode();
    }

    @And("User Verify entertainment options and venue Requirements")
    public void userVerifyEntertainmentOptionsAndVenueRequirements() {
        String venue = context.getContext("venue");
        System.out.println(venue);
        String option = context.getContext("Entertainment");
        System.out.println(option);
        System.out.println("User should be able to  add entertainment options and requirements for an event setup");

    }

    @And("User enter the special Instruction field as {string}")
    public void userEnterTheSpecialInstructionFieldAs(String text) {
        entertainmentPage.enterSpecialInstructions(text);
    }

    @And("User Verify special Instructions")
    public void userVerifySpecialInstructions() {
        entertainmentPage.getSpecialInstruction();
        System.out.println("User should be able to add special instructions for an event setup");
    }

    @And("User Enter the Description EN as {string}")
    public void userEnterTheDescriptionENAs(String text) {
            int number = entertainmentPage.getRandomNumber();
            context.setContext("descriptionEN",text+number);
            entertainmentPage.enterDescriptionEN(context.getContext("descriptionEN"));
            System.out.println(context.getContext("descriptionEN"));
    }

    @And("User set the description EN filter")
    public void userSetTheDescriptionENFilter() {
        String value = context.getContext("descriptionEN");
        entertainmentPage.setDescriptionFilter(value);
    }

    @And("Verify we have row details")
    public void verifyWeHaveRowDetails() {
        System.out.println(entertainmentPage.isItemCodeRowsPresent());
        Assert.assertTrue(entertainmentPage.isItemCodeRowsPresent()>0);
    }

    @And("User add an location")
    public void userAddAnLocation() {
        entertainmentPage.switchToChildWindow();
        entertainmentPage.setAddLocationButton();

    }

    @And("User select location as {string}")
    public void userSelectLocationAs(String location) {
        entertainmentPage.setLocationDropDown(location);
    }

    @And("User select priced by as {string}")
    public void userSelectPricedByAs(String priced) {
        entertainmentPage.setPricedBy(priced);
    }

    @And("User type the Quantity Available as {string}")
    public void userTypeTheQuantityAvailableAs(String number) {
        entertainmentPage.setQuantityAvailable(number);
    }

    @And("User verify the error page is displayed")
    public void userVerifyTheErrorPageIsDisplayed() {
        Assert.assertTrue(entertainmentPage.checkErrorPageIsDisplayed());
    }

    @And("user click on option")
    public void userClickOnOption() {
        entertainmentPage.eventTypeDropDown();
    }

    @And("User select the location dropdown")
    public void userSelectTheLocationDropdown() {
        int number = hotelMaintenancePage.getRandomNumber();
        System.out.println(number);
        entertainmentPage.selectLocation(number);
    }

    @And("User save time of the event")
    public void userSaveTimeOfTheEvent() throws ParseException {
        String time = entertainmentPage.startTimeText();
        System.out.println("time:"+time);
        context.setContext("time",time);
    }

    @And("User get timeList of all events")
    public void userGetTimeListOfAllEvents() {
        List<String> eventList = entertainmentPage.getEventsListOfAll();
        String eventTime = context.getContext("time");
        System.out.println(eventList);
        List<String> trimmedTimes = new ArrayList<>();

        // Process each time entry
        for (String time : eventList) {
            if (time.contains(" - ")) {
                // Split the range and keep only the first part (start time)
                String startTime = time.split(" - ")[0].trim();
                // Trim the AM/PM part to get the pure time (e.g., 5:18)
                String trimmedTime = startTime.split(" ")[0].trim();
                trimmedTimes.add(trimmedTime);
            } else {
                // If it's an empty string or no range, keep it as is
                trimmedTimes.add(time.trim());
            }
        }
        System.out.println(trimmedTimes);  // This will print the trimmed list
       // Assert.assertTrue(trimmedTimes.contains(eventTime));
    }

    @And("User verify user is able to select the {string} option in ticketing type of event while adding the new event.")
    public void userVerifyUserIsAbleToSelectTheOptionInTicketingTypeOfEventWhileAddingTheNewEvent(String expectedText) {
        String actualText = entertainmentPage.getTicketingTypeText();
        System.out.println(actualText);
        Assert.assertEquals(actualText,expectedText);
    }

    @And("User enter the Expected Attendance count as {string}")
    public void userEnterTheExpectedAttendanceCountAs(String count) {
        entertainmentPage.typeExpAttendanceField(count);
    }

    @And("User wait for one minute")
    public void userWaitForOneMinute() {
        entertainmentPage.waitForOneMinute();
    }

    @And("User switch to new window")
    public void userSwitchToNewWindow() {
        entertainmentPage.switchToNewTab();
    }

    @Then("User delete the event")
    public void userDeleteTheEvent() {
        String time = context.getContext("time");
        System.out.println(time);
        if (time.endsWith(":")) {
            time = time.substring(0, time.length() - 1);
        }
        entertainmentPage.rightClickOnEvent(time);
    }

    @Then("User double click on event")
    public void userDoubleClickOnEvent() {
        String time = context.getContext("time");
        System.out.println(time);
        if (time.endsWith(":")) {
            time = time.substring(0, time.length() - 1);
        }
        entertainmentPage.doubleClickOnEvent(time);
    }

    @Then("User click on Notes & History")
    public void userClickOnNotesHistory() {
        entertainmentPage.clickOnNotesHistory();
    }

    @And("User type the note field as {string} and check the client note checkbox")
    public void userTypeTheNoteFieldAsAndCheckTheClientNoteCheckbox(String text) {
        entertainmentPage.clickOnAddNotes(text);
    }

    @And("User click on Participants")
    public void userClickOnParticipants() {
        entertainmentPage.clickOnParticipants();
    }

    @And("User click on {string} for add note")
    public void userClickOnForAddNote(String button1) {
        entertainmentPage.navigateToSave(button1);
    }

    @And("User select location type as {string}")
    public void userSelectLocationTypeAs(String location) {
        entertainmentPage.selectLocationType(location);

    }

    @And("user click on option for category")
    public void userClickOnOptionForCategory() {
        entertainmentPage.eventCategoryDropDown();
    }

    @And("User add the participants")
    public void userAddTheParticipants() {
        entertainmentPage.addParticipant();
    }

    @And("Verify user is able to view the available event locations.")
    public void verifyUserIsAbleToViewTheAvailableEventLocations() {
        List<String> locationList = entertainmentPage.getLocationListOfAll();
        System.out.println(locationList);
        Assert.assertNotNull(locationList);

    }

    @And("Verify user is able to view the available event types.")
    public void verifyUserIsAbleToViewTheAvailableEventTypes() {
        List<String> eventType = entertainmentPage.getEventTypeListOfAll();
        System.out.println(eventType);
        Assert.assertNotNull(eventType);
    }

    @And("user click on option for category as {string}")
    public void userClickOnOptionForCategoryAs(String group) {
        entertainmentPage.eventCategoryDropDownGroup(group);
    }

    @And("User selects a past time should see an error message")
    public void userSelectsAPastTimeShouldSeeAnErrorMessage() {
        entertainmentPage.clickOnDisabledCell();
    }

    @And("User Select an unoccupied square on the calendar view")
    public void userSelectAnUnoccupiedSquareOnTheCalendarView() {
        entertainmentPage.clickOnUnoccupiedCell();
    }

    @And("User click on Items")
    public void userClickOnItems() {
        entertainmentPage.clickOnItems();
    }

    @And("User select Item Dropdown")
    public void userSelectItemDropdown() {
        entertainmentPage.selectItemDropdown();
    }

    @And("User click on Add item")
    public void userClickOnAddItem() {
        entertainmentPage.clickOnAddItemsIcon();
    }

    @And("User select Quantity")
    public void userSelectQuantity() {
        entertainmentPage.selectQuantity();
    }

    @And("user click on option as Tender operation")
    public void userClickOnOptionAsTenderOperation() {
        entertainmentPage.eventTypeTenderOperationDropDown();
    }
}


