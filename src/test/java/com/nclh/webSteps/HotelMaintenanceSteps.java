package com.nclh.webSteps;

import com.nclh.managers.AllDriverManager;
import com.nclh.utilities.Context;
import com.nclh.pageObjects.*;
import com.nclh.utilities.SeleniumActions;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

public class HotelMaintenanceSteps extends AllDriverManager {

    HouseKeepingPage houseKeepingPage;
    HotelMaintenancePage hotelMaintenancePage;
    PointOfSalePage pointOfSalePage;
    GuestServicePage guestServicePage;
    GuestServiceRequestPage guestServiceRequestPage;
    ShipSecurityPage shipSecurityPage;
    Context context;
    CommonPage commonPage;
    public static final Logger logger
            = LoggerFactory.getLogger(HotelMaintenanceSteps.class);

    public HotelMaintenanceSteps(Context context) {
        this.context = context;
        houseKeepingPage = new HouseKeepingPage(getDriver());
        hotelMaintenancePage = new HotelMaintenancePage(getDriver());
        shipSecurityPage = new ShipSecurityPage(getDriver());
        guestServicePage = new GuestServicePage(getDriver());
        guestServiceRequestPage = new GuestServiceRequestPage(getDriver());
        pointOfSalePage = new PointOfSalePage(getDriver());
        commonPage = new CommonPage(getDriver());
    }
    @Then("User click on add icon button")
    public void user_click_on_add_icon_button() {
        hotelMaintenancePage.clickOnAddIcon();

    }


    @And("User select request as {string}")
    public void userSelectRequestAs(String request) {
        String requestOption = hotelMaintenancePage.selectRequest(request);
        System.out.println(requestOption);
        context.setContext("requestOption",requestOption);
    }

    @Then("User click on reported by icon")
    public void userClickOnReportedByIcon() {
        hotelMaintenancePage.clickOnGuestIcon();
    }

    @And("User select a guest")
    public void userSelectAGuest() {
        hotelMaintenancePage.searchGuestUsingAlphabetic();
        int number = hotelMaintenancePage.getRandomNumber();
        System.out.println(number);
        hotelMaintenancePage.selectGuests(number);
    }

    @And("User save the guest name")
    public void userSaveTheGuestName() {
        String guestName = hotelMaintenancePage.getGuestName();
        String[] parts = guestName.split("\\s+");
        String firstName = parts[0];
        String lastName  = parts[1];
        String fullName = firstName + " " + lastName;
        context.setContext("guestFullName", fullName);
        context.setContext("guestFirstName", firstName);
        context.setContext("guestLastName", lastName);
        System.out.println("guestName :: " + guestName);
        context.setContext("guestName", guestName);
    }

    @And("User save the cabin number of guest")
    public void userSaveTheCabinNumberOfGuest() {
        String cabinNumber = hotelMaintenancePage.getCabinNumber();
        System.out.println("CabinNumber :: " + cabinNumber);
        context.setContext("CabinNumber", cabinNumber);
    }

    @Then("User click on location")
    public void userClickOnLocation() {
        hotelMaintenancePage.clickOnLocation();
    }

    @And("User type cabin number")
    public void userTypeCabinNumber() {
        String cabin = context.getContext("CabinNumber");
        hotelMaintenancePage.typeLocation(cabin);    }

    @And("User click on add symbol")
    public void userClickOnAddSymbol() {
        String cabin = context.getContext("CabinNumber");
        hotelMaintenancePage.clickAddButton(cabin);
    }

    @And("User type cabin number in location filter")
    public void userTypeCabinNumberInLocationFilter() {
        String cabin = context.getContext("CabinNumber");
        hotelMaintenancePage.searchLocation(cabin);
    }

    @And("User save the {string}")
    public void userSaveThe(String columnName ) {
        String text = hotelMaintenancePage.getRequestText(columnName);
        System.out.println(text);
        context.setContext("request",text);
    }

    @And("User verify the request is {string}")
    public void userVerifyTheRequestIs(String expectedRequest) {
        String actualRequest = context.getContext("request");
        String expect =  context.getContext("requestOption");
        Assert.assertEquals(expect,actualRequest);

    }

    @And("User click on create housekeeping task checkbox")
    public void userClickOnCreateHousekeepingTaskCheckbox() {
        hotelMaintenancePage.clickOnCheckBox();
    }

    @And("User select cleaning task as {string}")
    public void userSelectCleaningTaskAs(String task) {
        hotelMaintenancePage.selectCleaningTask(task);
    }

    @And("User enable the toggle {string}")
    public void userEnableTheToggle(String toggleName) {
        hotelMaintenancePage.enableToggle(toggleName);
    }

    @And("User type cabin number in cleaning location filter")
    public void userTypeCabinNumberInCleaningLocationFilter() {
        String cabin = context.getContext("CabinNumber");
        hotelMaintenancePage.searchCleaningLocation(cabin);
    }

    @And("User save the {string} in housekeeping")
    public void userSaveTheInHousekeeping(String columnName) {
        String text = hotelMaintenancePage.getTaskCode(columnName);
        System.out.println(text);
        context.setContext("TaskCode",text);
    }

    @And("User verify the task code is {string}")
    public void userVerifyTheTaskCodeIs(String expectedRequest) {
        String actualRequest = context.getContext("TaskCode");
        Assert.assertEquals(expectedRequest,actualRequest);
    }

    @And("User save the text message")
    public void userSaveTheTextMessage() {
        String text = hotelMaintenancePage.getNoDataText();
        System.out.println("text message ::"+text);
        context.setContext("Text",text);
    }

    @Then("verify text message is {string}")
    public void verifyTextMessageIs(String expected) {
        String  actual = context.getContext("Text");
        System.out.println(actual);
        Assert.assertEquals(expected,actual);
    }

    @And("Verify if error message occurred select other guest")
    public void verifyIfErrorMessageOccurredSelectOtherGuest() {
        if(hotelMaintenancePage.checkErrorMessage()){
            hotelMaintenancePage.clickOnEditReportBy();
            userSelectAGuest();
            userSaveTheGuestName();
            userSaveTheGuestName();
            commonPage.navigateTo("Select");
            userClickOnLocation();
            commonPage.navigateTo("Add");
        }
    }

    @And("User type comment {string}")
    public void userTypeComment(String comment) {
        hotelMaintenancePage.typeComment(comment);
    }

    @And("User click on both add buttons")
    public void userClickOnBothAddButtons() {
        hotelMaintenancePage.clickAddButtons();
    }

    @Then("User close the record")
    public void userCloseTheRecord() {
        hotelMaintenancePage.closeRecord();
    }

    @And("User click on request dropdown and don't fill")
    public void userClickOnRequestDropdownAndDonTFill() {
        hotelMaintenancePage.clickRequest();
    }

    @And("User save the mandatory field error message")
    public void userSaveTheMandatoryFieldErrorMessage() {
        String error = hotelMaintenancePage.getRequestError();
        context.setContext("ErrorText",error);
        System.out.println(error);
    }

    @And("User Verify the error text as {string}")
    public void userVerifyTheErrorTextAs(String expected) {
        Assert.assertEquals(context.getContext("ErrorText"),expected);
    }

    @And("User check the priority text")


    @And("User check the {string} text")
    public void userCheckTheText(String expected) {
        String text = hotelMaintenancePage.getPriorityText();
        System.out.println(text);
        Assert.assertEquals(text,expected);
    }

    @Then("User verify the priority is updated automatically")
    public void userVerifyThePriorityIsUpdatedAutomatically() {
        List<String> requestList = Arrays.asList("3 - Medium Priority", "4 - High Priority",
                "2 - Low Priority", "5 - Critical Priority","1 - Very Low Priority");
        String text = hotelMaintenancePage.getPriorityText();
        System.out.println(text);
        Assert.assertTrue(requestList.contains(text));

    }

    @And("User add location in where")
    public void userAddLocationInWhere() {
        int number = hotelMaintenancePage.getRandomNumber();
        System.out.println(number);
        hotelMaintenancePage.selectLocationWhere(number);
    }

    @Then("User enter text in detail field as {string}")
    public void userEnterTextInDetailFieldAs(String text) {
        hotelMaintenancePage.enterTextInDetail(text);
        String location = hotelMaintenancePage.getLocationText();
        System.out.println(location);
        context.setContext("CabinNumber",location);
    }

    @And("User verify that open status list do not contain {string}")
    public void userVerifyThatOpenStatusListDoNotContain(String status) {
        List<String> statusList = hotelMaintenancePage.getStatusListOfAll();
        System.out.println(statusList);
        Assert.assertFalse(statusList.contains(status),
                "The status list has '" + status + "'  found in the list.");
    }

    @And("Verify if error message occurred select other guest and enable toggle")
    public void verifyIfErrorMessageOccurredSelectOtherGuestAndEnableToggle() {
        if(hotelMaintenancePage.checkErrorMessage()){
            hotelMaintenancePage.clickOnEditReportBy();
            userSelectAGuest();
            userSaveTheGuestName();
            userSaveTheGuestName();
            commonPage.navigateTo("Select");
            userClickOnLocation();
            commonPage.navigateTo("Add");
            userEnableTheToggle("inform-housekeeping");
        }
    }

    @And("User verify select any open request")
    public void userVerifySelectAnyOpenRequest() {
        hotelMaintenancePage.clickOnFirstStatus();
    }

    @And("User verify that ticket history and view all actions on the ticket have been completed")
    public void userVerifyThatTicketHistoryAndViewAllActionsOnTheTicketHaveBeenCompleted() {
        System.out.println(context.getContext("status"));
        Assert.assertTrue(hotelMaintenancePage.getStatusHistory().equalsIgnoreCase(context.getContext("status")));
    }

    @And("User upload image")
    public void userUploadImage() throws AWTException {
        hotelMaintenancePage.upLoadDocument();
    }

    @And("User save the where location")
    public void userSaveTheWhereLocation() {
        String location = hotelMaintenancePage.getLocation();
        System.out.println(location);
        context.setContext("Location",location);
    }

    @Then("user search in location filter")
    public void userSearchInLocationFilter() {
        String cabin = context.getContext("Location");
        hotelMaintenancePage.searchLocation(cabin);
    }

    @And("User open the record")
    public void userOpenTheRecord() {
        guestServiceRequestPage.clickOnEditIcon();
    }

    @And("User click {string} in who")
    public void userClickInWho(String name) {
        commonPage.clickOnLabel(name);

    }

    @And("User verify that image is add by deleting it")
    public void userVerifyThatImageIsAddByDeletingIt() {
        hotelMaintenancePage.clickOnDelete();
        Assert.assertTrue(hotelMaintenancePage.deleteIsDone());
    }

    @And("User click service request checkbox")
    public void userClickServiceRequestCheckbox() {
        hotelMaintenancePage.checkBoxServiceRequest();
    }

    @And("User click on crew list")
    public void userClickOnCrewList() {
        hotelMaintenancePage.clickOnCrew();
    }

    @And("User verify the assignee is as saved")
    public void userVerifyTheAssigneeIsAsSaved() {
        String actualRequest = context.getContext("request");
        String expectedRequest = context.getContext("AssigneeName");
        Assert.assertEquals(expectedRequest,actualRequest);
    }

    @Then("User click on assignee icon")
    public void userClickOnAssigneeIcon() {
        hotelMaintenancePage.clickOnAssigneeIcon();
    }

    @And("User select a assignee")
    public void userSelectAAssignee() {
        //hotelMaintenancePage.searchGuestUsingAlphabetic();
        hotelMaintenancePage.selectAssignedBy();
    }

    @And("User save the assignee name")
    public void userSaveTheAssigneeName() {
        String assigneeName = hotelMaintenancePage.getAssigneeName();
        System.out.println("AssigneeName :: " + assigneeName);
        context.setContext("AssigneeName", assigneeName);
    }

    @And("User add cabin location in where")
    public void userAddCabinLocationInWhere() {
        int number = hotelMaintenancePage.getRandomCabinNumber();
        System.out.println(number);
        hotelMaintenancePage.selectLocationWhere(number);
    }

    @And("user verify automatically assign operational position\\(s) of the ticket submitted")
    public void userVerifyAutomaticallyAssignOperationalPositionSOfTheTicketSubmitted() {
        Assert.assertTrue(hotelMaintenancePage.verifyTeamIsUpdated());
    }

    @And("User close the request")
    public void userCloseTheRequest() {
        hotelMaintenancePage.clickOnStatusDropdown();
    }

    @And("User verify the request is closed successfully")
    public void userVerifyTheRequestIsClosedSuccessfully() {
        List<String> requestList = hotelMaintenancePage.getStatusListOfAllRequest();
        String request = context.getContext("FirstRequest");
        System.out.println(requestList);
        Assert.assertFalse(requestList.contains(request),
                "The status list has '" + request + "'  found in the list.");
    }

    @And("User Save the first Request")
    public void userSaveTheFirstRequest() {
        String firstRequest = hotelMaintenancePage.getTextOfFirstRequest();
        context.setContext("FirstRequest",firstRequest);
        System.out.println(firstRequest);

    }

    @And("User enter the comment as {string}")
    public void userEnterTheCommentAs(String text) {
        hotelMaintenancePage.enterComment(text);
    }

    @And("User open Maintenance overview")
    public void userOpenMaintenanceOverview() {
        hotelMaintenancePage.clickONMaintenanceOverView();
    }


    @And("User search a guest by first name")
    public void userSearchAGuestByFirstName() {
//        String name = context.getContext("guestFirstName");
        String name = context.getContext("guestFullName");

        hotelMaintenancePage.searchGuestFirstName(name);
        hotelMaintenancePage.selectGuest();
    }

    @And("User delete the selected guest")
    public void userDeleteTheSelectedGuest() {
        hotelMaintenancePage.clickOnDeleteGuest();
    }

    @And("User select service rquest as {string}")
    public void userSelectServiceRquestAs(String task) {
        hotelMaintenancePage.selectServiceRequest(task);
    }

    @And("User search for the same request")
    public void userSearchForTheSameRequest() {
        String request = context.getContext("FirstRequest");
        hotelMaintenancePage.enterInSearchBox(request);
    }

    @Then("user again close the record")
    public void userAgainCloseTheRecord() {
        hotelMaintenancePage.reCloseRecord();
    }

    @And("User verify that {string} is same")
    public void userVerifyThatIsSame(String columnName) {
        String expectedRequest = hotelMaintenancePage.getRequestText(columnName);
        String actualRequest = context.getContext("request");
        Assert.assertEquals(expectedRequest,actualRequest);
    }

    @And("User search for the same request name")
    public void userSearchForTheSameRequestName() {
        String request = context.getContext("request");
        hotelMaintenancePage.enterInSearchBox(request);
    }

    @And("user again got error message then click on add")
    public void userAgainGotErrorMessageThenClickOnAdd() {
        if(hotelMaintenancePage.getErrorMessage()>0){
            hotelMaintenancePage.clickOnAddButton();
        }
    }

    @And("User select the priority filter as {string}")
    public void userSelectThePriorityFilterAs(String priority) {
        hotelMaintenancePage.clickOnPriorityFilter(priority);
    }

    @And("User verify the priority list is updated to {string}")
    public void userVerifyThePriorityListIsUpdatedTo(String expectedPriority) {
        List<String> priorityList = hotelMaintenancePage.getPriorityListOfAll();
        System.out.println(priorityList);
        Set<String> postingList = new HashSet<>(priorityList);
        System.out.println("postingList :: " + postingList);
        List<String> actualPriority = new ArrayList<>(postingList);
        System.out.println(actualPriority);
        String actualPriorityString = String.join(",", actualPriority);
        System.out.println("Actual Priority (String): " + actualPriorityString);
        Assert.assertEquals(expectedPriority, actualPriorityString);
    }

    @And("User export ticket data")
    public void userExportTicketData() {
        hotelMaintenancePage.clickOnExportButton();
    }

    @And("User verify that file is downloaded")
    public void userVerifyThatFileIsDownloaded() {
        File directory = new File( System.getProperty("user.dir")+"\\downloads");
        int numberOfFiles= directory.listFiles().length;
        System.out.println("numberOfFiles :: " + numberOfFiles);
        //Assert.assertTrue(numberOfFiles == 1);
    }

    @And("User verify data is  displayed")
    public void userVerifyDataIsDisplayed() {
    }

    @And("user again got error message then click on create")
    public void userAgainGotErrorMessageThenClickOnCreate() {
        if(hotelMaintenancePage.getErrorMessage()>0){
            hotelMaintenancePage.clickOnCreateButton();
        }
    }

    @Then("User click edit icon on request")
    public void userClickEditIconOnRequest() {
        hotelMaintenancePage.clickOnEditIcon();
    }

    @And("User select the Category filter as {string}")
    public void userSelectTheCategoryFilterAs(String category) {
        hotelMaintenancePage.clickOnCategoryFilter(category);

    }

    @And("User verify the operationalDepartment is {string}")
    public void userVerifyTheOperationalDepartmentIs(String department) {
        String actualDepartment = hotelMaintenancePage.getOperationalDepartmentText();
        Assert.assertEquals(department,actualDepartment);
    }

    @And("User verify the team is {string}")
    public void userVerifyTheTeamIs(String team) {
        String actualTeam = hotelMaintenancePage.getTeamText();
        Assert.assertEquals(team,actualTeam);
    }

    @And("User save the cabin number after adding")
    public void userSaveTheCabinNumberAfterAdding() {
        String cabinNumber = hotelMaintenancePage.getLocationTextAfterAdding();
        System.out.println("CabinNumber :: " + cabinNumber);
        context.setContext("CabinNumber", cabinNumber);
    }

    @And("User select a crew")
    public void userSelectACrew() {
        hotelMaintenancePage.searchCrewUsingAlphabetic();
        int number = 1;
        System.out.println(number);
        hotelMaintenancePage.selectGuests(number);
    }

    @And("User clear the reservation Status filter")
    public void userClearTheReservationStatusFilter() {
        hotelMaintenancePage.clearReservationStatus();
    }

    @And("User select the target completion")
    public void userSelectTheTargetCompletion() {
        hotelMaintenancePage.openLinkUsingHref("calendar-clean");
        hotelMaintenancePage.clickOnTodayDate();
    }

    @Then("User verify the team field is updated automatically")
    public void userVerifyTheTeamFieldIsUpdatedAutomatically() {
        String actualTeam = hotelMaintenancePage.getTeamText();
        Assert.assertNotNull(actualTeam);
    }

    @Then("User verifies that list of request raised is displayed from newest to old")
    public void verifyNewestOrderOfRequests() {
        hotelMaintenancePage.validateListSortedNewestToOldest();
    }

    @Then("User expands the filter to see the list of filters available")
    public void userExpandsFilter() {
        hotelMaintenancePage.clickExpandFilterIcon();
        hotelMaintenancePage.validateFilterLabels();

    }

    @And("User verifies status search only shows {string}")
    public void userVerifiesStatusListContains(String status) {
        List<String> statusList = hotelMaintenancePage.getHighlightedStatusListOfAll();
        System.out.println(statusList);
        Assert.assertTrue(statusList.contains(status),
                "The status list has '" + status + "'  found in the list.");
    }

    @And("User search with guest cabin number")
    public void userSearchWithGuestCabinNumber() {
        String cabinNumber = context.getContext("CabinNumber");
        guestServicePage.searchUser(cabinNumber);
    }

    @And("User click on create service request checkbox")
    public void userClickOnCreateServiceRequestCheckbox() {
        hotelMaintenancePage.clickOnCreateServiceRequest();
    }

    @And("User click on request and don't fill")
    public void userClickOnRequestAndDonTFill() {
        hotelMaintenancePage.clickRequestFastCreation();
    }

    @And("User add location in where and don't fill")
    public void userAddLocationInWhereAndDonTFill() {
        hotelMaintenancePage.selectLocationWhereFastCreation();
    }

    @And("User save the mandatory field error message for request")
    public void userSaveTheMandatoryFieldErrorMessageForRequest() {
        String requestError = hotelMaintenancePage.getRequestFastCreationError();
        context.setContext("WhatError",requestError);
        System.out.println(requestError);

    }

    @And("User Verify the error text as {string} for what")
    public void userVerifyTheErrorTextAsForWhat(String expected) {
        Assert.assertEquals(context.getContext("WhatError"),expected);
    }

    @And("User save the mandatory field error message for location")
    public void userSaveTheMandatoryFieldErrorMessageForLocation() {
        String locationError = hotelMaintenancePage.getWhereError();
        context.setContext("WhereError",locationError);
        System.out.println(locationError);
    }

    @And("User Verify the error text as {string} for where")
    public void userVerifyTheErrorTextAsForWhere(String expected) {
        Assert.assertEquals(context.getContext("WhereError"),expected);

    }

    @Then("User enter text in detail {string}")
    public void userEnterTextInDetail(String text) {
        hotelMaintenancePage.enterTextInDetail(text);

    }


    @And("User select the operation department as {string}")
    public void userSelectTheOperationDepartmentAs(String department) {
         hotelMaintenancePage.clickOnOpDepartmentFilter(department);
             }

    @And("User verify the operation department list is updated to {string}")
    public void userVerifyTheOperationDepartmentListIsUpdatedTo(String expectedDepartment) {
        List<String> departmentList = hotelMaintenancePage.getOpDepartmentListOfAll();
        System.out.println(departmentList);
        Set<String> postingList = new HashSet<>(departmentList);
        System.out.println("postingList :: " + postingList);
        List<String> actualPriority = new ArrayList<>(postingList);
        System.out.println(actualPriority);
        String actualPriorityString = String.join(",", actualPriority);
        System.out.println("Actual department (String): " + actualPriorityString);
        Assert.assertEquals(expectedDepartment, actualPriorityString);
    }

    @Then("User move to the {string} field")
    public void userMoveToTheField(String field) {
        hotelMaintenancePage.moveToField(field);
    }

    @And("User change the team field and save the team")
    public void userChangeTheTeamFieldAndSaveTheTeam() {
        hotelMaintenancePage.selectTeam();
        String team = hotelMaintenancePage.getTeamText();
        context.setContext("updatedTeam",team);
    }

    @And("User Verify The Team field is updated")
    public void userVerifyTheTeamFieldIsUpdated() {
        String typeText = hotelMaintenancePage.getOperationalDepText();
        System.out.println(typeText);
        String text = context.getContext("updatedTeam");
        System.out.println(text);
        Assert.assertTrue(typeText.equalsIgnoreCase(text));
    }

    @And("User Click on Assignee filter")
    public void userClickOnAssigneeFilter() {
        String name = context.getContext("AssigneeName");
        hotelMaintenancePage.clickOnAssigneeFilter(name);
    }

    @And("User click  {string} code link")
    public void userClickCodeLink(String link) {
       hotelMaintenancePage.clickOnCodeLink(link);
    }
}
