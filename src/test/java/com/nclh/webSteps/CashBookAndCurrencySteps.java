package com.nclh.webSteps;

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
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CashBookAndCurrencySteps extends AllDriverManager {

    Context context;
    CommonPage commonPage;
    FinanceManagementPage financeManagementPage;
    CommonFunctions commonFunctions;
    CashBookAndCurrencyPage cashBookAndCurrencyPage;
    HotelMaintenancePage hotelMaintenancePage;

    public CashBookAndCurrencySteps(Context context) {
        this.context = context;
        financeManagementPage = new FinanceManagementPage(getDriver());
        commonPage = new CommonPage(getDriver());
        commonFunctions = new CommonFunctions();
        hotelMaintenancePage = new HotelMaintenancePage(getDriver());
        cashBookAndCurrencyPage = new CashBookAndCurrencyPage(getDriver());
    }

    @Then("User open {string} link")
    public void user_open_link(String link) {
        cashBookAndCurrencyPage.clickOnLink(link);
    }

    @And("User verify the column {string} is {string} in the table")
    public void userVerifyTheColumnIsInTheTable(String column, String data) {
//        if{
//            Assert.assertTrue(cashBookAndCurrencyPage.getDataMessage("No Data"));
//
//        }
        cashBookAndCurrencyPage.getTableDataForGivenColumn(column, data);
    }

    @And("User navigated to cashbook over view")
    public void userNavigatedToCashbookOverView() {
        System.out.println(context.getContext("checkNumber"));
        getDriver().manage().window().setSize(new Dimension(1920, 768));
        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        jse.executeScript("document.body.style.zoom = '67%';");

        Actions actions = new Actions(getDriver());
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();
        String open = context.getContext("openCashier");
        cashBookAndCurrencyPage.searchCashBook(open);
        cashBookAndCurrencyPage.clickOnCashBookOverView(open);
    }

    @And("User goto dropdown {string} and select value {string}")
    public void userGotoDropdownAndSelectValue(String list, String value) {
        String type = cashBookAndCurrencyPage.selectValueFromDropdown(list, value);
        System.out.println(type);
        context.setContext("type",type);
    }

    @And("User enter amount {string}")
    public void userEnterAmount(String amount) {
        cashBookAndCurrencyPage.enterAmount(amount);
    }

    @And("User select the cashbook record from the table")
    public void userSelectTheCashbookRecordFromTheTable() {
        cashBookAndCurrencyPage.printCashBook();
    }

    @Then("User verify pdf content of cash receipt")
    public void userVerifyPdfContentOfCashReceipt() {
        Assert.assertTrue(context.getContext("pdfContent").contains("Cash Receipt"));
    }

    @Then("User verify chasBook cashier session table")
    public void userVerifyChasBookCashierSessionTable(DataTable dataTable) {
        getDriver().manage().window().setSize(new Dimension(1920, 768));
        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        jse.executeScript("document.body.style.zoom = '67%';");

        Actions actions = new Actions(getDriver());
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();
        List<String> dataRow = dataTable.row(0);
        List<String> columnData = new ArrayList<>();
        for (int i = 0; i < dataRow.size(); i++) {
            columnData.add(dataRow.get(i));
        }

        System.out.println(columnData);
        System.out.println(cashBookAndCurrencyPage.geVisitorColumnHeaders());
        Assert.assertEquals(columnData, cashBookAndCurrencyPage.geVisitorColumnHeaders());
    }

    @And("User click on cash out")
    public void userClickOnCashOut() {
        cashBookAndCurrencyPage.clickOnCashOut();
    }

    @And("User verify that transaction type as {string}")
    public void userVerifyThatTransactionTypeAs(String type) {
        String typeText = cashBookAndCurrencyPage.getTransactionTypeText();
        String text = context.getContext("type");
        Assert.assertTrue(typeText.equalsIgnoreCase(text));
    }

    @And("User click on Add cashier button")
    public void userClickOnAddCashierButton() {
        cashBookAndCurrencyPage.clickOnAddButton();
    }

    @And("User enables  Open Linked Session toggle for guest")
    public void userEnablesOpenLinkedSessionToggleForGuest() {
        cashBookAndCurrencyPage.enableToggle();
    }

    @Then("User select the dropdown {string} and select session")
    public void userSelectTheDropdownAndSelectSession(String dropdown) {
        cashBookAndCurrencyPage.selectSessionDropdown(dropdown);
    }

    @And("User select the cashbook record from the table to open")
    public void userSelectTheCashbookRecordFromTheTableToOpen() {
        cashBookAndCurrencyPage.openCashBookRecord();
    }

    @Then("User close the transaction screen")
    public void userCloseTheTransactionScreen() {
        cashBookAndCurrencyPage.clickOnClose();
    }

    @Then("USer verify that cashbook has the  filter")
    public void userVerifyThatCashbookHasTheFilter(DataTable dataTable) {
        List<String> filter = cashBookAndCurrencyPage.getFilterListOfAll();
        List<String> dataRow = dataTable.row(0);
        List<String> columnData = new ArrayList<>();
        for (int i = 0; i < dataRow.size(); i++) {
            columnData.add(dataRow.get(i));
        }

        System.out.println(columnData);
        System.out.println(filter);
        //Assert.assertTrue(columnData.contains(filter));
        Assert.assertEquals(columnData,filter);

    }

    @And("User click on first edit icon")
    public void userClickOnFirstEditIcon() {
        System.out.println(context.getContext("checkNumber"));
        getDriver().manage().window().setSize(new Dimension(1920, 768));
        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        jse.executeScript("document.body.style.zoom = '67%';");

        Actions actions = new Actions(getDriver());
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();
        String open = context.getContext("openCashier");
        cashBookAndCurrencyPage.searchCashBook(open);
        cashBookAndCurrencyPage.clickOnEdit();
    }

    @And("User enter the comments field as {string}")
    public void userEnterTheCommentsFieldAs(String text) {
        cashBookAndCurrencyPage.enterComments(text);
    }

    @And("User verify that record has {string}")
    public void userVerifyThatRecordHas(String text) {
        String voidText = cashBookAndCurrencyPage.getVoidIdText();
        Assert.assertEquals(text,voidText);
    }

    @And("User navigated to cashier session overview")
    public void userNavigatedToCashierSessionOverview() {
        System.out.println(context.getContext("checkNumber"));
        getDriver().manage().window().setSize(new Dimension(1920, 768));
        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        jse.executeScript("document.body.style.zoom = '67%';");

        Actions actions = new Actions(getDriver());
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();
        cashBookAndCurrencyPage.clickOnCashierSessionOverView();
    }

    @And("User click on cash in")
    public void userClickOnCashIn() {
        cashBookAndCurrencyPage.clickOnCashIn();
    }

    @And("User save the current balance")
    public void userSaveTheCurrentBalance() {
        String oldBalance = cashBookAndCurrencyPage.getCurrentBalanceText();
        context.setContext("oldBalance",oldBalance);
        System.out.println(oldBalance);
    }

    @And("User verify that balance is updated")
    public void userVerifyThatBalanceIsUpdated() {
        String newBalance = cashBookAndCurrencyPage.getCurrentBalanceText();
        String oldBalance = context.getContext("oldBalance");
        System.out.println(newBalance);
        System.out.println(oldBalance);
        Assert.assertNotEquals(newBalance,oldBalance);
    }

    @Then("User get the {string} details")
    public void userGetTheDetails(String text) {
        String details = cashBookAndCurrencyPage.getDetails(text);
        System.out.println(text+":"+details);
    }

    @Then("User get {string} details")
    public void userGetDetails(String text) {
        String details = cashBookAndCurrencyPage.getInvoiceNo(text);
        System.out.println(text+":"+details);
    }

    @Then("User get the {string}")
    public void userGetThe(String text) {
        String details = cashBookAndCurrencyPage.getUser(text);
        System.out.println(text+":"+details);
    }

    @And("User click on cashbook transaction")
    public void userClickOnCashbookTransaction() {
        cashBookAndCurrencyPage.clickOnCashBookTransactionsTab();
    }


    @And("User select dropdown {string}")
    public void userSelectDropdown(String text) {
        cashBookAndCurrencyPage.selectFromDropdown(text);
    }

    @And("User goto dropdown {string} and select  {string}")
    public void userGotoDropdownAndSelect(String list, String value) {
        cashBookAndCurrencyPage.selectFromDropdown(list, value);
    }

    @And("User goto dropdown {string} select")
    public void userGotoDropdownSelect(String dropdown) {
        cashBookAndCurrencyPage.selectCashBookDropdown(dropdown);
    }

    @And("User save the open cashier session")
    public void userSaveTheOpenCashierSession() {
        String sessionName = cashBookAndCurrencyPage.nameOfOpenCashierSession();
        String openCashier = sessionName.split(" ")[0];
        context.setContext("openCashier",openCashier);
        System.out.println(openCashier);
        String secondSessionName = cashBookAndCurrencyPage.getSecondOpenSession();
        String openSecondCashier = secondSessionName.substring(secondSessionName.indexOf(" ") + 1);
        String secondCashbook = secondSessionName.split(" ")[0];
        context.setContext("secondCashbook",secondCashbook);

        context.setContext("openSecondCashier",openSecondCashier);
        System.out.println(openSecondCashier);

        cashBookAndCurrencyPage.clickOnFinanceManagement();
    }

    @And("User save open session")
    public void userSaveOpenSession() {
        String sessionName = cashBookAndCurrencyPage.nameOfOpenCashierSession();
        String openCashier = sessionName.split(" ")[0];
        context.setContext("openCashier",openCashier);
        System.out.println(openCashier);
    }

    @And("User goto dropdown {string} and select cashbook")
    public void userGotoDropdownAndSelectCashbook(String dropdown){
        cashBookAndCurrencyPage.clickOnToCashBook(dropdown);
    }

    @And("User add {string} filter")
    public void userAddFilter(String filterType) {
        getDriver().manage().window().setSize(new Dimension(1920, 768));
        JavascriptExecutor jse = (JavascriptExecutor)getDriver();
        jse.executeScript("document.body.style.zoom = '67%';");

        Actions actions = new Actions(getDriver());
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();
        //cashBookAndCurrencyPage.addFilter(filterType);
    }

    @And("User goto dropdown {string} and select open cashbook")
    public void userGotoDropdownAndSelectOpenCashbook(String list) {
        String value = context.getContext("openSecondCashier");
        System.out.println(value);
        cashBookAndCurrencyPage.selectValueFromDropdown(list, value);
    }

    @And("User goto dropdown {string} and select random cashbook")
    public void userGotoDropdownAndSelectRandomCashbook(String dropdown) {
        //int number = cashBookAndCurrencyPage.getRandomNumber();
        int number = 7;
        System.out.println(number);
        cashBookAndCurrencyPage.clickOnToRandomCashBook(dropdown,number);

    }

    @And("User click on transfer out")
    public void userClickOnTransferOut() {
        cashBookAndCurrencyPage.clickOnTransferOut();
    }

    @And("User goto dropdown {string} and select from cashbook")
    public void userGotoDropdownAndSelectFromCashbook(String list) {
        String value = context.getContext("openCashier");
        System.out.println(value);
        cashBookAndCurrencyPage.selectCashBookFromDropdown(list, value);
    }

    @And("User goto dropdown {string} and select to cashbook")
    public void userGotoDropdownAndSelectToCashbook(String list) {
        String value = context.getContext("secondCashbook");
        System.out.println(value);
        cashBookAndCurrencyPage.selectCashBookFromDropdown(list, value);
    }

    @Then("User get the open cashier session and cashbook name from top blue ribbon")
    public void userGetTheOpenCashierSessionAndCashbookNameFromTopBlueRibbon() {
        String openCashierText = cashBookAndCurrencyPage.nameOfOpenCashierSessionCrew();
        String cashbookName = cashBookAndCurrencyPage.nameOfCashbook();
        System.out.println(openCashierText);
        System.out.println(cashbookName);

    }

    @And("User click on Add cashbook")
    public void userClickOnAddCashbook() {
        cashBookAndCurrencyPage.clickONAddCashbook();
    }

    @Then("User enter code and description fields")
    public void userEnterCodeAndDescriptionFields() {
        int num = cashBookAndCurrencyPage.getRandomCodeNumber();
        cashBookAndCurrencyPage.enterCodeAndDescription("QAAUTO"+String.valueOf(num),"TESTCODE"+String.valueOf(num));
    }

    @And("User goto dropdown {string} and select option")
    public void userGotoDropdownAndSelectOption(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
