package com.nclh.webSteps;

import com.nclh.managers.AllDriverManager;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import com.nclh.pageObjects.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class GuestServiceRequestSteps extends AllDriverManager {

    Context context;
    GuestServiceRequestPage guestServiceRequestPage;
    HotelMaintenancePage hotelMaintenancePage;
    GuestServicePage guestServicePage;
    CommonFunctions commonFunctions;
    CommonPage commonPage;

    public GuestServiceRequestSteps(Context context){
        this.context = context;
        guestServiceRequestPage = new GuestServiceRequestPage(getDriver());
        guestServicePage = new GuestServicePage(getDriver());
        commonPage = new CommonPage(getDriver());
        commonFunctions = new CommonFunctions();
        hotelMaintenancePage = new HotelMaintenancePage(getDriver());
    }

    @Then("User set date for service request")
    public void user_set_date_for_service_request() {
       String startDate = commonFunctions.getDateIncrementedBy(-30, "M/d/yyyy");
       String endDate = commonFunctions.getDateIncrementedBy(1, "M/d/yyyy");
        guestServiceRequestPage.setDateForServiceRequest(startDate,endDate);
        guestServicePage.openHiddenFilters();
        guestServiceRequestPage.clickOnServiceTitle();
    }

    @And("User click on service request")
    public void userClickOnServiceRequest() {
        guestServiceRequestPage.getRowValuesForServiceRequest();
        List<String> resolutionDetails = guestServiceRequestPage.getRowValuesForServiceRequest();
        String guestCabin = resolutionDetails.get(resolutionDetails.size() - 1);
        context.setContext("cabin",guestCabin);
        guestServiceRequestPage.clickServiceRequest();
    }

    @Then("User change the resolutions code as {string}")
    public void userChangeTheResolutionsCodeAs(String code) {
        guestServiceRequestPage.selectCode(code);
    }

    @Then("User select the category {string} on resolution")
    public void userSelectTheCategoryOnResolution(String category) {
        guestServiceRequestPage.selectCategory(category);
    }

    @And("User search for cabin number")
    public void userSearchForCabinNumber() {
        String number = context.getContext("cabin");
        guestServiceRequestPage.typeCabinNumber(number);
    }

    @Then("User click on {string} action box")
    public void userClickOnActionBox(String action) {
        guestServiceRequestPage.clickFastCreation(action);
    }

    @And("User click on {string} status and select {string}")
    public void userClickOnStatusAndSelect(String dropDown, String item) {
        guestServiceRequestPage.getRowValuesForRequestOverview();
        List<String> requestOverview = guestServiceRequestPage.getRowValuesForRequestOverview();
        System.out.println(requestOverview);
       guestServiceRequestPage.selectStatusDropdown(dropDown,item);
    }

    @Then("User enter comments {string} and click on {string} button")
    public void userEnterCommentsAndClickOnButton(String comment, String name) {
        guestServiceRequestPage.enterComment(comment);
        commonPage.navigateTo(name);
    }

    @And("User verify the status")
    public void userVerifyTheStatus() {
        String status = guestServiceRequestPage.getStatusText();
        System.out.println(status);
        Assert.assertEquals(status,context.getContext("statusName"));
    }

    @And("User verify the status is {string}")
    public void userVerifyTheStatusIs(String name) {
        String status = guestServiceRequestPage.getStatusText();
        Assert.assertTrue(status.equalsIgnoreCase(name));
    }

    @Then("User switch window to add new workorder")
    public void userSwitchWindowToAddNewWorkorder() {
        guestServiceRequestPage.switchToNewWindow();
    }

    @And("User Verify the resolution status")
    public void userVerifyTheResolutionStatus() {
        List<String> detailsResolution = guestServiceRequestPage.getRowDetails();
        List<String> resolutionDetails = guestServiceRequestPage.getRowValuesForServiceRequest();
        Assert.assertEquals( detailsResolution, resolutionDetails);
    }


    @And("User select the location")
    public void userSelectTheLocation() {
        guestServiceRequestPage.clickOnPlusButtonLocation();
    }

    @Then("User select team as {string}")
    public void userSelectTeamAs(String team) {
        guestServiceRequestPage.selectTeam(team);
    }

    @Then("User select my assigned request as priority")
    public void userSelectMyAssignedRequestAsPriority() {
        guestServiceRequestPage.selectPriorityAssignedRequest();
    }

    @Then("User verify the request overview table")
    public void userVerifyTheRequestOverviewTable(DataTable dataTable) {
            List<String> dataRow = dataTable.row(0);
            List<String> columnData = new ArrayList<>();
            for (int i=0; i<dataRow.size(); i++){
                columnData.add(dataRow.get(i));
            }
            System.out.println(columnData);
            System.out.println(guestServiceRequestPage.getRequestOverviewColumnHeaders());
            Assert.assertEquals(columnData, guestServiceRequestPage.getRequestOverviewColumnHeaders());
    }

    @Then("User clear status of the request overview")
    public void userClearStatusOfTheRequestOverview() {
        guestServiceRequestPage.clearStatus();
    }

    @And("User select {string} status on request overview")
    public void userSelectStatusOnRequestOverview(String option) {
        guestServiceRequestPage.checkOptionInStatus(option);
    }

    @Then("User click edit icon on request overview")
    public void userClickEditIconOnRequestOverview() {
        guestServiceRequestPage.clickOnEditIcon();
        String statusId = guestServiceRequestPage.getStatusId();
        System.out.println(statusId);
        context.setContext("status",statusId);
    }

    @And("User click {string} on request overview")
    public void userClickOnRequestOverview(String name) {
//        guestServiceRequestPage.clickOnEditIcon();
        String statusId = guestServiceRequestPage.getStatusId();
        System.out.println(statusId);
        context.setContext("status",statusId);
        commonPage.clickOnLabel(name);
    }

    @And("User select the location and click on {string}")
    public void userSelectTheLocationAndClickOn(String name) {
        guestServiceRequestPage.clickOnPlusButtonLocation();
        String location = guestServiceRequestPage.setLocationNumber();
        context.setContext("location",location);
        commonPage.navigateTo(name);
        if (guestServiceRequestPage.checkErrorMessage()){
            guestServiceRequestPage.clickAddButton();
        }
    }

    @And("User click on location filter and select location")
    public void userClickOnLocationFilterAndSelectLocation() {
        String cabin = context.getContext("CabinNumber");
        hotelMaintenancePage.searchLocation(cabin);
    }

    @Then("User click on request number on request overview")
    public void userClickOnRequestNumberOnRequestOverview() {
        guestServiceRequestPage.clickOnRequestNumber();
    }

    @And("User click on {string} status and select {string} on request overview")
    public void userClickOnStatusAndSelectOnRequestOverview(String dropDown, String item) {
        guestServiceRequestPage.selectStatusDropdown(dropDown,item);
        String statusId = guestServiceRequestPage.getStatusId();
        System.out.println(statusId);
        context.setContext("status",statusId);
    }

    @And("User verify the status on request overview")
    public void userVerifyTheStatusOnRequestOverview() {
        String status = guestServiceRequestPage.getStatusText();
        System.out.println(status);
        Assert.assertEquals(status,context.getContext("status"));
    }

    @And("User verify that ticket history and view that all actions on the ticket have been completed")
    public void userVerifyThatTicketHistoryAndViewThatAllActionsOnTheTicketHaveBeenCompleted() {
        System.out.println(context.getContext("status"));
        Assert.assertTrue(guestServiceRequestPage.getUpdateStatusHistory().equalsIgnoreCase(context.getContext("status")));
    }

    @Then("User get {string} index")
    public void userGetIndex(String columnName) {
        guestServiceRequestPage.getIndexOfServiceRequestColumnName(columnName);
    }

    @Then("User get {string} index and select last name {string}")
    public void userGetIndexAndSelectLastName(String columnName, String lastName) {
        guestServiceRequestPage.getRowValuesForServiceRequest();
        List<String> resolutionDetails = guestServiceRequestPage.getRowValuesForServiceRequest();
        String guestCabin = resolutionDetails.get(resolutionDetails.size() - 1);
        context.setContext("cabin",guestCabin);
        guestServiceRequestPage.getIndexOfServiceRequestColumnName(columnName);
        guestServiceRequestPage.serviceRequestClick(columnName,lastName);
    }

    @Then("User select the {string} as {string}")
    public void userSelectTheAs(String dropdown, String option) {
        guestServiceRequestPage.selectDropDown(dropdown,option);
    }

    @Then("User set {string} date for add new workorder")
    public void userSetDateForAddNewWorkorder(String featureDate) {
        String date = null;
        date = commonFunctions.getDateIncrementedBy(1, "M/d/yyyy");
        context.setContext("futureDate", date);
        guestServiceRequestPage.targetDate(date+", 12:00 AM");
    }

    @And("User set Target Completion date for add new workorder")
    public void userSetTargetCompletionDateForAddNewWorkorder() {
        guestServiceRequestPage.setTargetCompletionDateTime();
    }

    @Then("User select the {string} and select port")
    public void userSelectTheAndSelectPort(String dropdown) {
        guestServiceRequestPage.selectEmbarkationPortDropdown(dropdown);
    }

    @Then("User click on add icon button in service request tab")
    public void userClickOnAddIconButtonInServiceRequestTab() {
        guestServiceRequestPage.clickOnAddIconButton();
    }

    @And("user click on {string} in add service request")
    public void userClickOnInAddServiceRequest(String name) {
        commonPage.navigateTo(name);
    }

    @Then("User save all details in add service request page")
    public void userSaveAllDetailsInAddServiceRequestPage() {
        String serviceItemAreaField = guestServiceRequestPage.getServiceItemAreaDropdownText();
        context.setContext("serviceItemArea",serviceItemAreaField);
        String serviceRequestItemCodeField = guestServiceRequestPage.getServiceItemCodeRequestDropdownText();
        context.setContext("serviceRequestItemCode",serviceRequestItemCodeField);
        String serviceItemCodeField = guestServiceRequestPage.getServiceItemCodeRequestDropdownText().toUpperCase();
        context.setContext("serviceItemCode",serviceItemCodeField);
        String originTypeField = guestServiceRequestPage.getOriginTypeDropdown();
        context.setContext("originType",originTypeField);
        String operationDepartmentIdField = guestServiceRequestPage.getOperationDepartmentIdDropdownText();
        context.setContext("operationDepartmentId",operationDepartmentIdField);
//        String operationPositionIdField = guestServiceRequestPage.getOperationPositionIdDropdownText();
//        context.setContext("operationPositionId",operationPositionIdField);
    }

    @Then("User select the {string} and select code")
    public void userSelectTheAndSelectCode(String dropdown) {
        guestServiceRequestPage.selectEmbarkationPortDropdown(dropdown);
    }

    @Then("User select the {string} and select origin")
    public void userSelectTheAndSelectOrigin(String dropdown) {
        guestServiceRequestPage.selectEmbarkationPortDropdown(dropdown);
    }

    @Then("User verify the added service request details")
    public void userVerifyTheAddedServiceRequestDetails() {
        String requestName = guestServiceRequestPage.getItemCardName().toUpperCase();
        context.setContext("serviceRequestText",requestName);
//        String getItemCodeName = context.getContext("serviceItemCode");
//        Assert.assertEquals(requestName,getItemCodeName);
//        String serviceItemAreaName = guestServiceRequestPage.getServiceItemAreaDropdownText();
//        String getServiceItemAreaName = context.getContext("serviceItemArea");
//        Assert.assertEquals(serviceItemAreaName,getServiceItemAreaName);
//        String originFieldName = guestServiceRequestPage.getOriginTypeDropdown();
//        String getOriginFieldName = context.getContext("originType");
//        Assert.assertEquals(originFieldName,getOriginFieldName);
//        String operationDepartmentIdFieldName = guestServiceRequestPage.getOperationDepartmentIdDropdownText();
//        String getOperationDepartmentIdFieldName = context.getContext("operationDepartmentId");
//        Assert.assertEquals(operationDepartmentIdFieldName,getOperationDepartmentIdFieldName);
    }

    @Then("User save the added new resolution details")
    public void userSaveTheAddedNewResolutionDetails() {
        String resolutionsCodeText = guestServiceRequestPage.getServiceItemCodeResolutionIdDropdownText();
        context.setContext("resolutionsCode",resolutionsCodeText);
        String itemStatusText = guestServiceRequestPage.getItemStatusChipText();
        context.setContext("itemStatus",itemStatusText);
    }

    @And("User click on added new resolution")
    public void userClickOnAddedNewResolution() {
        commonPage.clickOnLabel(context.getContext("resolutionsCode"));
    }

    @Then("User verify the added new resolution details")
    public void userVerifyTheAddedNewResolutionDetails() {
        String status = guestServiceRequestPage.getStatusDropdownText();
        String getStatus = context.getContext("itemStatus");
        Assert.assertEquals(status,getStatus);
    }

    @Then("User type the comments as {string}")
    public void userTypeTheCommentsAs(String text) {
        guestServiceRequestPage.enterCommentInputField(text);
    }

    @And("User save the update status details")
    public void userSaveTheUpdateStatusDetails() {
        String status = guestServiceRequestPage.getStatusDropdownText();
        context.setContext("statusText",status);
        String comment = guestServiceRequestPage.getCommentText();
        context.setContext("commentText",comment);
        guestServiceRequestPage.statusSaveButton();
    }

    @Then("User verify updated status in resolution")
    public void userVerifyUpdatedStatusInResolution() {
        String itemStatusText = guestServiceRequestPage.getItemStatusChipText();
        String getStatus = context.getContext("statusText");
        Assert.assertEquals(itemStatusText,getStatus);
    }

    @And("User click on added service request details")
    public void userClickOnAddedServiceRequestDetails() {
        commonPage.clickOnLabel(context.getContext("serviceItemCode"));
    }

    @Then("User select the status as {string} on resolution")
    public void userSelectTheStatusAsOnResolution(String status) {
        guestServiceRequestPage.selectStatus(status);
    }

    @Then("User save the added new maintenance request details")
    public void userSaveTheAddedNewMaintenanceRequestDetails() {
        String maintenanceRequestText = guestServiceRequestPage.getMaintenanceRequestCodeIdIdDropdownText();
        System.out.println(maintenanceRequestText);
        context.setContext("maintenanceRequest",maintenanceRequestText);
        String itemMaintenanceText = guestServiceRequestPage.getItemMaintenanceChipText();
        context.setContext("maintenanceItem",itemMaintenanceText);
    }

    @And("User click on added new maintenance request")
    public void userClickOnAddedNewMaintenanceRequest() {
        guestServiceRequestPage.clickOnAddedRequest(context.getContext("maintenanceRequest"));
//        commonPage.clickOnLabel(context.getContext("maintenanceRequest"));
    }

    @Then("User verify the added new maintenance request details")
    public void userVerifyTheAddedNewMaintenanceRequestDetails() {
        String maintenanceStatus = guestServiceRequestPage.getStatusFieldText();
        String status = context.getContext("maintenanceItem");
        Assert.assertEquals(maintenanceStatus,status);
    }

    @Then("User select the status as {string} on maintenance request")
    public void userSelectTheStatusAsOnMaintenanceRequest(String status) {
        guestServiceRequestPage.selectStatus(status);
    }

    @Then("User save the update status details in maintenance request")
    public void userSaveTheUpdateStatusDetailsInMaintenanceRequest() {
        String maintenanceStatus = guestServiceRequestPage.getStatusFieldText();
         context.setContext("maintenanceRequestText",maintenanceStatus);
         String statusDetailsText = guestServiceRequestPage.getStatusDetailIdText();
         context.setContext("statusDetails",statusDetailsText);
        guestServiceRequestPage.clickOnAddedRequest(context.getContext("serviceRequestItemCode"));
    }

    @And("User verify updated maintenance request")
    public void userVerifyUpdatedMaintenanceRequest() {
        String itemMaintenanceText = guestServiceRequestPage.getItemMaintenanceChipText();
        String maintenanceStatus = context.getContext("maintenanceRequestText");
        Assert.assertEquals(itemMaintenanceText,maintenanceStatus);
    }

    @Then("User verify the Obsolete toggle button is on")
    public void userVerifyTheObsoleteToggleButtonIsOn() {
        String toggleStatus = guestServiceRequestPage.checkObsoleteToggleButtonStatus();
        Assert.assertEquals(toggleStatus,"true");
    }

    @Then("User select the gender dropdown as {string}")
    public void userSelectTheGenderDropdownAs(String option) {
        guestServiceRequestPage.selectGenderDropDown(option);
    }

    @Then("User select the Account Statement Type and select port")
    public void userSelectTheAccountStatementTypeAndSelectPort() {

        guestServiceRequestPage.selectAccountStatementTypeDropdown();
    }

    @Then("User select the statusId as {string} on maintenance request")
    public void userSelectTheStatusIdAsOnMaintenanceRequest(String status) {
        guestServiceRequestPage.selectStatusId(status);
    }

    @Then("User clear Operation Department filter")
    public void userClearOperationDepartmentFilter() {
        guestServiceRequestPage.clearOperationDepartment();
    }

    @Then("User verify updated staus in service request")
    public void userVerifyUpdatedStausInServiceRequest() {
        String itemStatusText = guestServiceRequestPage.getStatusDropdownText();
        String getStatus = context.getContext("statusText");
        Assert.assertEquals(itemStatusText,getStatus);
    }

    @And("User select {string} status on service request overview")
    public void userSelectStatusOnServiceRequestOverview(String option) {
        guestServiceRequestPage.checkOptionInStatusServiceRequest(option);
    }

    @And("User click on service request link")
    public void userClickOnServiceRequestLink() {
        guestServiceRequestPage.clickServiceRequest();
    }
}
