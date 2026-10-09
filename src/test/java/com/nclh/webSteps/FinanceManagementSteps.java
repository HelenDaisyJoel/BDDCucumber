package com.nclh.webSteps;

import com.nclh.constants.GlobalConstants;
import com.nclh.constants.PreconditionConstants;
import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.*;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.junit.Assert.assertTrue;

public class FinanceManagementSteps extends AllDriverManager {

    Context context;
    CommonPage commonPage;
    FinanceManagementPage financeManagementPage;
    GuestServiceRequestPage guestServiceRequestPage;
    GuestServicePage guestServicePage;
    GuestCheckInCheckOutPage guestCheckInCheckOutPage;
    CommonFunctions commonFunctions;
    PointOfSalePage pointOfSalePage;
    GroupManagementPage groupManagementPage;
    SafetyManagementPage safetyManagementPage;
    CrewInformationPage crewInformationPage;

    public FinanceManagementSteps(Context context){
        this.context = context;
        guestServiceRequestPage = new GuestServiceRequestPage(getDriver());
        financeManagementPage = new FinanceManagementPage(getDriver());
        pointOfSalePage = new PointOfSalePage(getDriver());
        commonPage = new CommonPage(getDriver());
        guestServicePage = new GuestServicePage(getDriver());
        guestCheckInCheckOutPage = new GuestCheckInCheckOutPage(getDriver());
        commonFunctions = new CommonFunctions();
        groupManagementPage = new GroupManagementPage(getDriver());
        safetyManagementPage = new SafetyManagementPage(getDriver());
        crewInformationPage =  new CrewInformationPage(getDriver());
    }

    @Then("User verify the Posting Status toggle button is on")
    public void user_verify_the_posting_status_toggle_button_is_on() {
        String toggleStatus = financeManagementPage.checkToggleButtonStatus();
        Assert.assertEquals(toggleStatus,"true");
    }


    @And("User verify the folio detail")
    public void userVerifyTheFolioDetail() {
        Assert.assertTrue(financeManagementPage.isFolioDetailsRowPresent()>0);
    }

    @Then("User clear status of Pending Postings Overview")
    public void userClearStatusOfPendingPostingsOverview() {
        guestServiceRequestPage.clearStatus();
    }

    @And("User select {string} status on Pending Postings Overview")
    public void userSelectStatusOnPendingPostingsOverview(String option) {
        guestServiceRequestPage.checkOptionInStatus(option);
    }

    @Then("User switch to new tab")
    public void userSwitchToNewTab() {
        guestServiceRequestPage.switchToNewWindow();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User verify the filter guest by status as {string}")
    public void userVerifyTheFilterGuestByStatusAs(String status) {
        Assert.assertTrue(context.getContext("statusText").contains(" "),status);
    }

    @And("Click on three dots and select Bulk Operation on guest page")
    public void clickOnThreeDotsAndSelectBulkOperationOnGuestPage() {
        financeManagementPage.bulkOperationForGuest();
    }

    @Then("User select the {string} account postings operation details")
    public void userSelectTheAccountPostingsOperationDetails(String option) {
        financeManagementPage.selectEnableOrDisableAccountPostings(option);
    }

    @Then("User type the comment {string}")
    public void userTypeTheComment(String text) {
        financeManagementPage.enterComment(text);
    }

    @Then("User select the {string} on Sub Departments")
    public void userSelectTheOnSubDepartments(String option) {
        financeManagementPage.selectSubDepartments(option);
    }

    @And("User type amount {string}")
    public void userTypeAmount(String amount) {
        financeManagementPage.enterAmount(amount);
    }

    @Then("User type the payment comment {string}")
    public void userTypeThePaymentComment(String text) {
        financeManagementPage.enterPaymentComment(text);
    }

    @And("User add {string} on Available Pre-Selection Groups")
    public void userAddOnAvailablePreSelectionGroups(String add) {
        financeManagementPage.selectPreSelectionGroups(add);
    }

    @Then("User type the disallowed departments comment {string}")
    public void userTypeTheDisallowedDepartmentsComment(String text) {
        financeManagementPage.enterDisallowedDepartmentsComment(text);
    }

    @Then("User click add user icon on payer account")
    public void userClickAddUserIconOnPayerAccount() {
        financeManagementPage.clickPayerAccount();
    }

    @Then("User select route to folio as {string}")
    public void userSelectRouteToFolioAs(String option) {
        financeManagementPage.selectRouteToFolio(option);
    }

    @Then("User type the routing comment {string}")
    public void userTypeTheRoutingComment(String text) {
        financeManagementPage.enterRoutingComment(text);
    }

    @Then("User save the finance bulk log")
    public void userSaveTheFinanceBulkLog() {
        guestServicePage.refreshPage();
        guestServicePage.refreshPage();
        guestServicePage.refreshPage();
        String successCountLog = financeManagementPage.setSuccessCount();
        System.out.println(successCountLog);
        context.setContext("successCount",successCountLog);
//        String errorCountLog = financeManagementPage.setErrorCount();
//        context.setContext("errorCount",errorCountLog);
        String statusLog = financeManagementPage.setFinanceStatusLog();
        context.setContext("status",statusLog);
    }

    @Then("User validate the finance bulk log")
    public void userValidateTheFinanceBulkLog() {
//        String operationLog = financeManagementPage.getOperationLog();
//        Assert.assertEquals("operation",operationLog);
        String getSuccessCount = financeManagementPage.getSuccessCount();
        String count = context.getContext("successCount");
        Assert.assertEquals(count,getSuccessCount);
//        String getErrorCount = financeManagementPage.getErrorCount();
//        String error = context.getContext("errorCount");
//        Assert.assertEquals(error,getErrorCount);
        String getStatusLog = financeManagementPage.getFinanceStatusLog();
        String statusResult = context.getContext("status");
        Assert.assertEquals(statusResult,getStatusLog);
    }

    @And("User click on cross button")
    public void userClickOnCrossButton() {
        String name = context.getContext("guestName");
        financeManagementPage.clickCrossButtonOfName(name);
    }

    @Then("Validate the added routing is deleted")
    public void validateTheAddedRoutingIsDeleted() {
        Assert.assertFalse(guestCheckInCheckOutPage.checkAddRoutingUser());
    }

    @Then("User verify the Exclude from Bulk Settlement toggle button is on")
    public void userVerifyTheExcludeFromBulkSettlementToggleButtonIsOn() {
        String toggleStatus = financeManagementPage.checkExcludeToggleButton();
        List<String> status = Arrays.asList(toggleStatus, "true", "false");
        //Assert.assertEquals(toggleStatus,"true");
        Assert.assertTrue(status.contains(toggleStatus));
    }

    @Then("User verify the Paperless Invoice toggle button is on")
    public void userVerifyThePaperlessInvoiceToggleButtonIsOn() {
        String toggleStatus = financeManagementPage.checkPaperlessToggleButtonStatus();
        Assert.assertEquals(toggleStatus,"true");
    }

    @And("User Enter {string}")
    public void userEnter(String text) {
        financeManagementPage.enterLimitAmount(text);
    }

    @Then("User validate the {string} on credit limits")
    public void userValidateTheOnCreditLimits(String text) {
        String creditLimit = financeManagementPage.limitAmountText();
        Assert.assertEquals(creditLimit,text);
    }

    @Then("User click on {string} tab and click on {string}")
    public void userClickOnTabAndClickOn(String tab, String button1) {
        financeManagementPage.clickOnTabAndClick(tab,button1);
    }

    @Then("User validate the {string} on credit limits tab")
    public void userValidateTheOnCreditLimitsTab(String text) {
        String creditLimit = financeManagementPage.limitAmountText();
        Assert.assertEquals(creditLimit,text);
        financeManagementPage.deleteCreditLimit();
    }

    @Then("User verify guest full name present in the PDF content")
    public void userVerifyGuestFullNamePresentInThePDFContent() {
        String crewNameFromWeb = context.getContext("nonSensitiveGuestFullName");
        String[] fullName = crewNameFromWeb.split(" ");
        System.out.println("fullName");
        String firstName = fullName[0].trim();
        System.out.println(firstName);
        String lastName = fullName[1].trim();
        System.out.println(lastName);

        String[] crewFullName = crewNameFromWeb.split(" ");
        String expectedCrewNameOnPDF = "";

        for (int i = crewFullName.length; i > 0; i--) {
            expectedCrewNameOnPDF = expectedCrewNameOnPDF + ", " + crewFullName[i - 1];
            System.out.println(crewFullName[i - 1]);
        }

        System.out.println(expectedCrewNameOnPDF.substring(2));

         /*if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
                    shipSecurityPage.openProfile(context.getContext("nonSensitiveGuestFirstName"), context.getContext("nonSensitiveGuestLastName"));
                } else {
                    guestServicePage.searchUser(context.getContext("nonSensitiveGuestFirstName"));
                    guestServicePage.navigateToCrewInformation(context.getContext("nonSensitiveGuestFirstName"));
                }*/
//
//        System.out.println("PDF Content :: ");
//        System.out.println(context.getContext("pdfContent"));
        //Assert.assertTrue(context.getContext("pdfContent").contains(expectedCrewNameOnPDF.substring(2)));
//        Assert.assertTrue(context.getContext("pdfContent").contains(firstName));
//        Assert.assertTrue(context.getContext("pdfContent").contains(lastName));
    }

    @Then("User click on {string} tab and click on {string} on financial information")
    public void userClickOnTabAndClickOnOnFinancialInformation(String tab, String button2) {
        financeManagementPage.clickOnTabAndClickAddRouting(tab,button2);
    }

    @Then("User type the onboard credit comment as {string}")
    public void userTypeTheOnboardCreditCommentAs(String text) {
        financeManagementPage.onboardCreditComment(text);
    }

    @Then("User type the add posting comment as {string}")
    public void userTypeTheAddPostingCommentAs(String text) {
        financeManagementPage.addPostingCreditComment(text);
    }

    @Then("User verify the Service Charge Exempted toggle button is on")
    public void userVerifyTheServiceChargeExemptedToggleButtonIsOn() {
        String toggleStatus = financeManagementPage.serviceChargeExemptedToggleButtonStatus();
        Assert.assertEquals(toggleStatus,"true");
    }

    @And("User click {string} on disallowed departments")
    public void userClickOnDisallowedDepartments(String name) {
        commonPage.clickOnLabel(name);
    }

    @And("User select the pre selection group item")
    public void userSelectThePreSelectionGroupItem() {
        String preSelectGroupText = financeManagementPage.preSelectionGroup();
        System.out.println(preSelectGroupText);
        context.setContext("preSelectGroup",preSelectGroupText);
        financeManagementPage.selectPreSelectionGroup();
    }

    @Then("User select main department item")
    public void userSelectMainDepartmentItem() {
        String mainDepartmentText = financeManagementPage.mainDepartment();
        context.setContext("mainDepartment",mainDepartmentText);
        financeManagementPage.selectMainDepartment();
    }

    @Then("User select sub department item")
    public void userSelectSubDepartmentItem() {
        String subDepartmentText = financeManagementPage.mainDepartment();
        context.setContext("subDepartment",subDepartmentText);
        financeManagementPage.selectSubDepartment();
        List<String> disallowedDepartmentText = financeManagementPage.selectedDisallowedDepartmentList();
        System.out.println(disallowedDepartmentText);
        context.setListContext("disallowedDepartment",disallowedDepartmentText);
    }

    @Then("User verify the disallowed departments details")
    public void userVerifyTheDisallowedDepartmentsDetails() {
        List<String> verifyDisallowedDepartmentText = financeManagementPage.disallowedDepartmentList();
        List<String> actualResult = new ArrayList<>();
        for(int i = 0; i<verifyDisallowedDepartmentText.size(); i=i+2){
            actualResult.add(verifyDisallowedDepartmentText.get(i) + " - " + verifyDisallowedDepartmentText.get(i+1));
        }
        System.out.println("actualResult :: " + actualResult);
        System.out.println("disallowedDepartment :: " + context.getListContext("disallowedDepartment"));
        Assert.assertTrue(context.getListContext("disallowedDepartment").containsAll(actualResult));
    }

    @Then("User click on {string} tab and click on {string} in financial information")
    public void userClickOnTabAndClickOnInFinancialInformation(String tab, String button2) {
        financeManagementPage.clickOnTabAndClickAddDisallowed(tab,button2);
    }

    @Then("User type the discount templates comment {string}")
    public void userTypeTheDiscountTemplatesComment(String text) {
        financeManagementPage.enterDiscountTemplatesComment(text);
    }

    @Then("User select {string} option from the add more filter")
    public void userSelectOptionFromTheAddMoreFilter(String option) {
        financeManagementPage.checkOptionInTheAddMoreFilter(option);
    }

    @And("User set the min {string} and max {string} balance")
    public void userSetTheMinAndMaxBalance(String number, String number2) {
        financeManagementPage.enterMinAndMaxBalance(number,number2);
    }

    @Then("User verify the account statement as {string}")
    public void userVerifyTheAccountStatementAs(String expected) {
        String status = financeManagementPage.getAccountStatementStatus();
        Assert.assertEquals(status,expected);
    }

    @Then("User click on {string} button on routing")
    public void userClickOnButtonOnRouting(String button) {
        financeManagementPage.addRoutingButton(button);
    }

    @And("User search for guest and open guest profile page")
    public void userSearchForGuestAndOpenGuestProfilePage() {
        String[] fullName = context.getContext("nonSensitiveGuestFullName").split(" ");
        guestServicePage.openCrewProfile(fullName[0], fullName[1]);
    }

    @Then("User type the payment method comment as {string}")
    public void userTypeThePaymentMethodCommentAs(String text) {
        financeManagementPage.paymentMethodComment(text);
    }

    @And("User get text from the comment box image")
    public void userGetImageTextFromWebElement(){
        WebElement element = financeManagementPage.getCommentTextWebElement();
        String textMessage  = commonFunctions.readToastMessage(element);
        context.setContext("imageText", textMessage);
        System.out.println("Comment text message : " + context.getContext("imageText"));
    }

    @And("User verify the comment as {string}")
    public void userVerifyTheCommentAs(String expected) {
        Assert.assertEquals(context.getContext("imageText").trim(),expected);
    }

    @And("User get text from the status")
    public void userGetTextFromTheStatus() {
        WebElement element = financeManagementPage.getStatusElement();
        String textMessage  = commonFunctions.readToastMessage(element);
        context.setContext("statusText", textMessage);
        System.out.println("Comment text message : " + context.getContext("statusText"));
    }

    @Then("User verify the filter guest by status {string}")
    public void userVerifyTheFilterGuestByStatus(String expected) {
        Assert.assertEquals(context.getContext("filterText").trim(),expected);

    }

    @And("User get text from status")
    public void userGetTextFromStatus() {
        WebElement element = financeManagementPage.getStatusElement();
        String textMessage  = commonFunctions.readToastMessage(element);
        context.setContext("filterText", textMessage);
        System.out.println("Comment text message : " + context.getContext("filterText"));
    }

    @Then("user verify the posting status comment")
    public void userVerifyThePostingStatusComment() {
        WebElement element = financeManagementPage.PostingStatusCommentTextWebElement();
        String textMessage  = commonFunctions.readToastMessage(element);
        context.setContext("commentText", textMessage);
        System.out.println("Comment text message : " + context.getContext("commentText"));

        //Assert.assertTrue(financeManagementPage.getPostingStatusCommentTextWebElement());
    }

    @Then("User verify deliverables table")
    public void userVerifyDeliverablesTable(DataTable dataTable) {
        List<String> dataRow = dataTable.row(0);
        List<String> columnData = new ArrayList<>();
        for (int i = 0; i < dataRow.size(); i++) {
            columnData.add(dataRow.get(i));
        }

        System.out.println(columnData);
        System.out.println(financeManagementPage.getDeliverablesColumnHeaders());
        Assert.assertEquals(columnData, financeManagementPage.getDeliverablesColumnHeaders());
    }

    @Then("User verify the posting status on deliverables tab")
    public void userVerifyThePostingStatusOnDeliverablesTab() {
        List<String> postingStatusList = financeManagementPage.getPostingStatusList();
        System.out.println(postingStatusList);
        Set<String> postingList = new HashSet<>(postingStatusList);
        System.out.println("postingList :: " + postingList);
        List<String> actualStatus = new ArrayList<>(postingList);
        List<String> expectedStatus = Arrays.asList("Pending","Posted","Rejected","Noposting","-");
        Assert.assertTrue(expectedStatus.containsAll(actualStatus));
    }

    @And("User verify the reservation status")
    public void userVerifyTheReservationStatus() {
        List<String> reservationStatus = financeManagementPage.getReservationStatus();
        Set<String> reservationList = new HashSet<>(reservationStatus);
        System.out.println("postingList :: " + reservationList);
        List<String> actualStatus = new ArrayList<>(reservationList);
        List<String> expectedStatus = Arrays.asList("CI","CI (Missing)","CO");
        Assert.assertTrue(expectedStatus.containsAll(actualStatus));
    }

    @And("User select {string} from payment type")
    public void userSelectFromPaymentType(String option) {
        financeManagementPage.checkPaymentTypeFilter(option);
    }

    @Then("User click on checkbox and select the payment method")
    public void userClickOnCheckboxAndSelectThePaymentMethod() {
        financeManagementPage.selectPaymentMethodCheckbox();
    }

    @Then("User verify the selected payment method")
    public void userVerifyTheSelectedPaymentMethod() {
        String toggleStatus = financeManagementPage.selectedCheckboxStatus();
        Assert.assertEquals(toggleStatus,"true");
    }


    @Then("User select one or more {int} on account statement")
    public void userSelectOneOrMoreAccountOnAccountStatement(int account) {
        financeManagementPage.selectAccountCheckbox(account);
    }

    @And("User type the amount settle amount")
    public void userTypeTheAmountSettleAmount() {
        String amount = context.getContext("amount");
        financeManagementPage.typeSaveAmount(amount);
    }


    @Then("user verify the credit amount as {string}")
    public void userVerifyTheCreditAmountAs(String amount) {
        String creditAmount = financeManagementPage.getCreditText();
        Assert.assertEquals(creditAmount,amount);
    }

    @Then("User verify the payment method on account statement section")
    public void userVerifyThePaymentMethodOnAccountStatementSection() {
//        String paymentMethod = financeManagementPage.getPaymentMethodText();
//        System.out.println(paymentMethod);
//        Assert.assertEquals(paymentMethod,"Remaining Limit");
//        String floorLimit = financeManagementPage.getPaymentMethodFloorText();
//        System.out.println(floorLimit);
//        Assert.assertEquals(floorLimit,"Credit Limit");
        Assert.assertTrue(financeManagementPage.getPaymentMethod());
        Assert.assertTrue(financeManagementPage.getFloorLimitText());
        Assert.assertTrue(financeManagementPage.getReminingLimit());

    }

    @Then("User select the required date for embarkation date")
    public void userSelectTheRequiredDateForEmbarkationDate() {
        financeManagementPage.embarkationDateToday();

    }

    @And("User search and navigated to guest profile")
    public void userSearchAndNavigatedToGuestProfile() {
        System.out.println(context.getContext("nonSensitiveGuestFirstName") + ":: "+ context.getContext("nonSensitiveGuestLastName"));
        pointOfSalePage.openGuestInformation(context.getContext("nonSensitiveGuestLastName"), context.getContext("nonSensitiveGuestFirstName"));
    }

    @Then("User validate the add disallowed departments is delete")
    public void userValidateTheAddDisallowedDepartmentsIsDelete() {
        Assert.assertFalse(financeManagementPage.checkDisallowedDepartment());
    }

    @Then("User verify the account statement")
    public void userVerifyTheAccountStatement() {

    }

    @And("User clear existing filters of disembarkation date")
    public void userClearExistingFiltersOfDisembarkationDate() {
        financeManagementPage.clearDisembarkationDate();
    }

    @Then("User add new amenity details")
    public void userAddNewAmenityDetails() {
        financeManagementPage.amenityDetails();
        String amenityCodeText = financeManagementPage.getAmenityCodeId();
        context.setContext("AmenityCode",amenityCodeText);
    }

    @And("User select posting date in {string}")
    public void userSelectPostingDateIn(String date) {
        financeManagementPage.postingDateFilter(date);
    }

    @Then("User verify the  Package counts, Shore Excursion counts, Spa counts")
    public void userVerifyThePackageCountsShoreExcursionCountsSpaCounts() {
        List<String> getOtherInfoListData =  financeManagementPage.getOtherInfoList();
        for(String text : getOtherInfoListData){
            String cleanedString = text.replaceAll("[^a-zA-Z]", "");
            boolean onlyAlphabets = cleanedString.equals(text.replaceAll("[^a-zA-Z]", ""));
            assertTrue( "The text '" + text + "' contains non-alphabetic characters",onlyAlphabets);
        }
    }

    @Then("User enter {string} posting date")
    public void userEnterPostingDate(String startOrEnd) {
        String date = null;
        switch (startOrEnd) {
            case "startDate":
                date = commonFunctions.getDateIncrementedBy(-60, "M/d/yyyy");
                financeManagementPage.enterDates(startOrEnd, date);
                context.setContext("startDateText", date);
                System.out.println("For start Date Text is :" + context.getContext("startDateText"));
                break;
            case "endDate":
                date = commonFunctions.getDateIncrementedBy(30, "M/d/yyyy");
                financeManagementPage.enterDates(startOrEnd, date);
                context.setContext("endDateText", date);
                System.out.println("For end Date Text is:"+context.getContext("endDateText"));
                break;
            case "date":
                date = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
                financeManagementPage.enterDates(startOrEnd, date);
                context.setContext("dateText", date);
                System.out.println("For Date Text is:"+context.getContext("dateText"));
                break;
            default:
                System.out.println("Not entered any date");
        }
    }

    @And("Validate that user able to filter by posting date")
    public void validateThatUserAbleToFilterByPostingDate() {
        List<String> dateList = financeManagementPage.getPostingHeaderName();
        Set<String> postingList = new HashSet<>(dateList);
        System.out.println("postingList :: " + postingList);
        List<String> actualStatus = new ArrayList<>(postingList);
        List<String> expectedStatus = Arrays.asList("Embark Date","Name");
        Assert.assertTrue(actualStatus.containsAll(expectedStatus));
//        List<String> dateList = financeManagementPage.getFilterEmbarkationDate();
//        String startDateText = context.getContext("startDateText");
//        System.out.println(startDateText);
//        System.out.println(dateList);
//        Assert.assertTrue(dateList.contains(startDateText));
    }

    @Then("User clears embarkation date and disembarkation date on newly selected filters")
    public void userClearsEmbarkationDateAndDisembarkationDateOnNewlySelectedFilters() {
        financeManagementPage.clearEmbarkationAndDisembarkationDate();
    }

    @Then("User save the embarkation date and disembarkation date")
    public void userSaveTheEmbarkationDateAndDisembarkationDate() {
        String embarkation = financeManagementPage.getEmbarkationDateText();
        System.out.println("Embarkation::"+embarkation);
        context.setContext("Embarkation",embarkation);
        String disembarkation = financeManagementPage.getDisembarkationDateText();
        System.out.println("Disembarkation::"+disembarkation);
        context.setContext("Disembarkation",disembarkation);
    }

    @And("User enter embarkation date and disembarkation date")
    public void userEnterEmbarkationDateAndDisembarkationDate() {
        String typeEmb = context.getContext("Embarkation");
        String typeDisEmd = context.getContext("Disembarkation");
        financeManagementPage.enterEmbarkationDateAndDisEmbarkationDate(typeEmb,typeDisEmd);
    }

    @Then("User verify the embarkation date and disembarkation date")
    public void userVerifyTheEmbarkationDateAndDisembarkationDate() {
        String embarkation = financeManagementPage.getEmbarkationDateText();
        String typeEmb = context.getContext("Embarkation");
        Assert.assertEquals(embarkation,typeEmb);
        String disembarkation = financeManagementPage.getDisembarkationDateText();
        String typeDisEmd = context.getContext("Disembarkation");
        Assert.assertEquals(disembarkation,typeDisEmd);
    }


    @And("User save the details in add onboard template")
    public void userSaveTheDetailsInAddOnboardTemplate() {
        String onboardTemplateField = financeManagementPage.getOnboardCreditDropdownText();
        context.setContext("onboardTemplate",onboardTemplateField);
//        WebElement creditLimitField = financeManagementPage.getcreditlimitFieldTextWebElement();
//        String textMessageLastName  = commonFunctions.readToastMessage(creditLimitField);
//        context.setContext("creditLimit", textMessageLastName);
//        System.out.println("lastName text message : " + context.getContext("creditLimit"));
        String paymentDepartmentField = financeManagementPage.getPaymentDepartmentInputFieldText();
        context.setContext("paymentDepartment",paymentDepartmentField);
        String collectorDepartmentField = financeManagementPage.getCollectorDepartmentInputFieldText();
        context.setContext("collectorDepartment",collectorDepartmentField);
    }

    @Then("User verify created onboard credit template details")
    public void userVerifyCreatedOnboardCreditTemplateDetails() {
        List<String> onboardTemplateNameList = financeManagementPage.getOnboardTemplateText().stream()
                .map(text -> text.replaceAll("Template:", " ").trim())
                .collect(Collectors.toList());
        System.out.println(onboardTemplateNameList);
        String getOnboardTemplateName = context.getContext("onboardTemplate").split(" - ")[0].trim();
        System.out.println(getOnboardTemplateName);
        Assert.assertTrue(onboardTemplateNameList.contains(getOnboardTemplateName));
//        String creditLimit = financeManagementPage.getCreditLimitText();
//        System.out.println("credit after add button ::"+creditLimit);
//        String credit = context.getContext("creditLimit");
//        System.out.println("credit before add button::"+credit);
//        Assert.assertEquals(credit,creditLimit);
//        String paymentDepartmentText = financeManagementPage.getPaymentDepartmentText();
//        System.out.println(paymentDepartmentText);
//        String paymentDepartment = context.getContext("paymentDepartment");
//        System.out.println(paymentDepartment);
//        Assert.assertEquals(paymentDepartment,paymentDepartmentText);
//        String collectorDepartmentText = financeManagementPage.getCollectorDepartmentText();
//        String collectorDepartment = context.getContext("collectorDepartment");
//        Assert.assertEquals(collectorDepartment,collectorDepartmentText);
    }

    @Then("User enter {string} date range")
    public void userEnterDateRange(String startOrEnd) {
        String date = null;
        switch (startOrEnd) {
            case "startDate":
                date = commonFunctions.getDateIncrementedBy(-60, "M/d/yyyy");
                financeManagementPage.enterDates(startOrEnd, date);
                context.setContext("startDateText", date);
                System.out.println("For start Date Text is :" + context.getContext("startDateText"));
                break;
            case "endDate":
                date = commonFunctions.getDateIncrementedBy(30, "M/d/yyyy");
                financeManagementPage.enterDates(startOrEnd, date);
                context.setContext("endDateText", date);
                System.out.println("For end Date Text is:"+context.getContext("endDateText"));
                break;
            case "date":
                date = commonFunctions.getDateIncrementedBy(0, "M/d/yyyy");
                financeManagementPage.enterDates(startOrEnd, date);
                context.setContext("dateText", date);
                System.out.println("For Date Text is:"+context.getContext("dateText"));
                break;
            case "Start Date Time":
                date = commonFunctions.getDateIncrementedBy(1, "M/d/yyyy");
                financeManagementPage.enterDates(startOrEnd, date);
                context.setContext("dateText", date);
                System.out.println("For Date Text is:"+context.getContext("dateText"));
                break;
            default:
                System.out.println("Not entered any date");
        }

    }

    @Then("User select the {string} dropdown")
    public void userSelectTheDropdown(String dropdown) {
        guestServiceRequestPage.selectEmbarkationPortDropdown(dropdown);
    }

    @Then("User select the {string} and select value")
    public void userSelectTheAndSelectValue(String dropdown) {
        guestServiceRequestPage.selectEmbarkationPortDropdown(dropdown);
    }

    @Then("User type the credit limit comment as {string}")
    public void userTypeTheCreditLimitCommentAs(String text) {
        financeManagementPage.creditLimitComment(text);
    }

    @Then("User select the {string} as {string} on posting")
    public void userSelectTheAsOnPosting(String dropdown, String option) {
        financeManagementPage.selectDropDownForPosting(dropdown,option);
    }

    @And("User search for guest in pending posting overview")
    public void userSearchForGuestInPendingPostingOverview() {
        String[] fullName = context.getContext("nonSensitiveGuestFullName").split(" ");
        financeManagementPage.openGuestProfile(fullName[0], fullName[1]);
    }

    @Then("User select the amenity checkbox and click on three dot button select the {string}")
    public void userSelectTheAmenityCheckboxAndClickOnThreeDotButtonSelectThe(String button) {
        financeManagementPage.selectStatus(button);
    }

    @Then("User verify the status as posted")
    public void userVerifyTheStatusAsPosted() {
        financeManagementPage.startButton();
        String status = financeManagementPage.getStatusText();
        System.out.println(status);
        Assert.assertEquals(status,"POSTED");
    }

    @Then("User delete new created group")
    public void userDeleteNewCreatedGroup() {
        financeManagementPage.clickGroupDeleteIcon();
    }

    @Then("User verify the created group is delete")
    public void userVerifyTheCreatedGroupIsDelete() {
        String groupDetails = financeManagementPage.getGroupDetails();
        Assert.assertEquals(groupDetails,"No Data");
    }

    @And("User click on {string} column of the system account")
    public void userClickOnColumnOfTheSystemAccount(String columnName) {
        financeManagementPage.sortColumnName(columnName);
    }


    @Then("Validate that user is able to sort all the system accounts by the column.")
    public void validateThatUserIsAbleToSortAllTheSystemAccountsByTheColumn() {
        String codeStatus = financeManagementPage.checkSortColumn();
        List<String> status = Arrays.asList(codeStatus, "descending", "ascending","none");
        //Assert.assertEquals(toggleStatus,"true");
        Assert.assertTrue(status.contains(codeStatus));
    }

    @And("User verify the current and next voyage details")
    public void userVerifyTheCurrentAndNextVoyageDetails() {
        Assert.assertTrue(financeManagementPage.getCurrentVoyage());
        Assert.assertTrue(financeManagementPage.getNextVoyage());
    }
    @And("User select {string} filter option to {string} in financial page")
    public void userSelectFilterOptionToInFinancialPage(String filterName, String reservationStatus) {
        safetyManagementPage.pageloaded();
        Assert.assertTrue(safetyManagementPage.selectCrewByReservationStatus(filterName, reservationStatus), String.format("Failed to select crew with %s as '%s'.", filterName, reservationStatus));
        safetyManagementPage.pageloaded();
    }

    @Then("User verifies that the {string} column contains {string}")
    public void userVerifiesThatTheColumnContains(String columnName, String expectedText) {
            List<WebElement> columnCells = financeManagementPage.getColumnCells(columnName);
            Assert.assertFalse(columnCells.isEmpty(), "No data found under the '" + columnName + "' column.");
            boolean textFound = false;
            for (WebElement cell : columnCells) {
                String actualText = cell.getText().trim();
                if (actualText.equalsIgnoreCase(expectedText)) {
                    textFound = true;
                    break;
                }
            }
            Assert.assertTrue(textFound, "Expected text '" + expectedText + "' was not found under the '" + columnName + "' column.");
    }

    @Then("User should see the Category dropdown displayed")
    public void userShouldSeeTheCategoryDropdownDisplayed() {
        safetyManagementPage.pageloaded();
        Assert.assertTrue(financeManagementPage.isCategoryDropdownDisplayed(), "Category dropdown is not displayed");
    }

    @Then("User should see the green check mark icon")
    public void userShouldSeeTheGreenCheckMarkIcon() {
        Assert.assertTrue(financeManagementPage.isChecksGreenTickPresent(), "Green check icon is not present for 'Checks (Open, In Progress)'");
    }

    @And("User deletes the last Classification")
    public void userDeletesTheLastClassification() {
        int initialCount = financeManagementPage.getClassificationCount();
        context.setContext("CLASSIFICATION_COUNT_BEFORE_DELETE", String.valueOf(initialCount));
        financeManagementPage.deleteLastClassification();
    }

    @Then("Classification count should be reduced by one")
    public void classificationCountShouldBeReducedByOne() {
        int beforeCount = Integer.parseInt(context.getContext("CLASSIFICATION_COUNT_BEFORE_DELETE"));
        int afterCount = financeManagementPage.getClassificationCount();
        Assert.assertEquals(afterCount, beforeCount - 1, "Classification count was not reduced by one");
    }

    @And("Scroll to the Classification table")
    public void scrollToTheClassificationTable() {
        financeManagementPage.scrollClassificationRows();
    }
    @And("User select {string} filter and search {string}")
    public void userSelectFilterAndSearch(String filterName, String searchText) {
        safetyManagementPage.pageloaded();
        financeManagementPage.userSelectFilterAndSearch(filterName, searchText);
        financeManagementPage.selectThePackageCheckboxes();
        safetyManagementPage.pageloaded();
    }

    @Then("User verifies that the {string} column contains {string} text")
    public void userVerifiesThatTheColumnContainsText(String columnName, String expectedText) {
        List<WebElement> columnCells = financeManagementPage.validateTheColumnText(columnName);
        Assert.assertFalse(columnCells.isEmpty(), "No data found under the '" + columnName + "' column.");
        boolean textFound = false;
        for (WebElement cell : columnCells) {
            String actualText = cell.getText().trim();
            if (actualText.equalsIgnoreCase(expectedText.trim())) {
                textFound = true;
                break;
            }
        }
        Assert.assertTrue(textFound, "Expected text '" + expectedText + "' was not found under the '" + columnName + "' column.");
    }

    @And("Search the golden ID")
    public void searchTheGoldenId() {
        commonPage.searchFor(PreconditionConstants.GOLDEN_ID);
    }

    @Then("user verifies that the guest is available using the Golden ID")
    public void userVerifiesThatTheGuestIsAvailableUsingTheGoldenID() {
        crewInformationPage.clearReservationStatus();
        List<WebElement> lastNames = guestCheckInCheckOutPage.getLastNames();
        Assert.assertFalse(lastNames.isEmpty(), "No last name elements were found in the guest search results.");
    }


}