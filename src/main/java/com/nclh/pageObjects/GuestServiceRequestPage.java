package com.nclh.pageObjects;

import com.nclh.constants.GlobalConstants;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuestServiceRequestPage {
    private WebDriver webDriver;
    private SeleniumActions seleniumActions;
    private CommonPage commonPage;
    private SeleniumWaits seleniumWaits;
    private CommonFunctions commonFunctions;
    private GuestServiceRequestPage guestServiceRequestPage;

    public GuestServiceRequestPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        seleniumActions = new SeleniumActions(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonPage = new CommonPage(webDriver);
        commonFunctions = new CommonFunctions();
    }

    @FindBy(css = "button[id*='creationDTTM'] > icon")
    private WebElement createDateArrowMark;

    @FindBy(xpath = "//td[@role='columnheader'][contains(@style,'width')][@tabindex][not(@rowspan)]")
    private List<WebElement> columnHeaderForTour;

    @FindBy(xpath = "(//otalio-service-request-item-link/a)[1]")
    private WebElement serviceRequestLink;

    @FindBy(xpath = "//mat-select[contains(@class,'mat-select-empty')]/descendant::span[text()='Code']")
    private WebElement codeDropdown;

    @FindBy (xpath = "//mat-select[contains(@class,'mat-select-empty')]/descendant::span[text()='Category']/../../div/following-sibling::div")
    private WebElement categoryDropdown;

    @FindBy (css = "[placeholder='Search']")
    private WebElement searchField;

    @FindBy (xpath = "//otalio-infinite-select[@placeholder='Request']/descendant::div[contains(@class,'select-arrow-wrapper')]/div")
    private WebElement requestDropdown;

    @FindBy(css = "[placeholder='Search']")
    private WebElement searchInputBox;

    @FindBy(css = "[label='Locations']")
    private WebElement locationDropdown;

    @FindBy(css = "[placeholder='Comment']")
    private WebElement commentInputField;

    @FindBy(xpath = "(//button[contains(@id,'request-status')]/span/div/span)[1]")
    private WebElement statusText;

    @FindBy(xpath = "//input[@formcontrolname='startDate']/../../div/descendant::icon[contains(@icon,'close')]/*[name()='svg']/*[name()='use']")
    private WebElement clearStartDate;

    @FindBy(xpath = "//input[@formcontrolname='endDate']/../../div/descendant::icon[contains(@icon,'close')]/*[name()='svg']/*[name()='use']")
    private WebElement clearEndDate;

    @FindBy(xpath = "//tr[1]/td/div/span[contains(@class,'inserted')]")
    private List<WebElement> getResolution;

    @FindBy(xpath = "(//mat-list-item/descendant::button/span/icon[contains(@icon,'plus')])[4]")
    private WebElement locationPlusButton;

    @FindBy(css = "[placeholder='Team']")
    private WebElement teamDropdown;

    @FindBy(xpath = "(//otalio-maintenance-requests-link/a[normalize-space()='1'])[1]")
    private WebElement priorityLink;

    @FindBy(css = "button[id*='status'] > icon")
    private WebElement statusList;

    @FindBy(xpath = "//div[text()='Operational Department:']")
    private WebElement operationDepartment;

    @FindBy(css = "[class*='pseudo-checkbox-checked']")
    private List<WebElement> statusCheckBoxes;

    @FindBy(xpath = "//td[@role='columnheader'][@aria-label][contains(@style,'left')][not(contains(@title,'Request Detail'))][not(contains(@aria-label,'Column Op Department'))]")
    private List<WebElement> requestColumnData;

    @FindBy(xpath = "(//icon[@icon='edit'])[2]")
    private WebElement editIcon;

    @FindBy(xpath = "//otalio-request-status[@formcontrolname='statusId']/button/span/div/span")
    private WebElement statusIdName;

    @FindBy(css = "#breadcrumbs-title")
    private WebElement breadCrumbTitle;

    @FindBy(xpath = "(//span[text()='Status Update']/following-sibling::span[text()='->']/following-sibling::span/span)[1]")
    private WebElement updateHistory;

    @FindBy(xpath = "//otalio-maintenance-save-error/div/p[contains(text(),'already exists')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//otalio-chip/descendant::div[@class='mat-tooltip-trigger']")
    private WebElement locationField;

    @FindBy(xpath = "//button//span[normalize-space()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "(//tbody/tr/td/div[text()='Number']/ancestor::otalio-data-table/descendant::tbody/tr/td/div/a)[1]")
    private WebElement numberLink;

    @FindBy(css = "[role='columnheader']")
    private List<WebElement> tableHeaderItems;

    @FindBy(xpath = "//span[@id='breadcrumbs-title']")
    private WebElement serviceTitle;

    @FindBy(xpath = "//p[text()='Who']")
    private WebElement whoHeader;

    @FindBy(xpath = "//otalio-request-target-completion-datetime/descendant::icon[@id='otalio-time-picker--dropdown-icon']")
    private WebElement targetCompletionDateTimeField;

    @FindBy(xpath = "//mat-icon[text()='add']")
    private WebElement addIcon;

    @FindBy(xpath = "//mat-select[contains(@id,'serviceItemArea')]/descendant::span/span")
    private WebElement serviceItemAreaDropdownText;

    @FindBy(xpath = "//mat-select[contains(@id,'serviceItemCodeRequest')]/descendant::span/span")
    private WebElement serviceItemCodeRequestDropdownText;

    @FindBy(xpath = "//mat-select[contains(@id,'operationDepartmentId')]/descendant::span/span")
    private WebElement operationDepartmentIdDropdownText;

    @FindBy(xpath = "//mat-select[contains(@id,'operationPositionId')]/descendant::span/span")
    private WebElement operationPositionIdDropdownText;

    @FindBy(xpath = "//mat-select[contains(@id,'originType')]/descendant::span/span")
    private WebElement originTypeDropdownText;

    @FindBy(xpath = "(//otalio-menu-tree-list-item-card/descendant::div[contains(@class,'text-uppercase')])[1]")
    private WebElement itemCardText;

    @FindBy(xpath = "(//mat-select[contains(@id,'serviceItemCodeResolutionId')]/descendant::span/span)[1]")
    private WebElement serviceItemCodeResolutionIdDropdownText;

    @FindBy(xpath = "(//mat-select[contains(@id,'maintenanceRequestCodeId')]/descendant::span/span)[1]")
    private WebElement maintenanceRequestCodeIdIdDropdownText;

    @FindBy(xpath = "(//otalio-item-status-chip/descendant::div/div)[1]")
    private WebElement itemStatusChipText;

    @FindBy(xpath = "//mat-select[@formcontrolname='status']/descendant::span/span")
    private WebElement statusDropdownText;

    @FindBy(css = "[placeholder='Comment']")
    private WebElement commentField;

    @FindBy(xpath = "(//div[contains(@id,'comment-component-comment-textarea')])[1]")
    private WebElement commentText;

    @FindBy(css = "[formcontrolname='status']")
    private WebElement statusDropdown;

    @FindBy(css = "[formcontrolname='statusId']")
    private WebElement statusIdDropdown;

    @FindBy(xpath = "(//otalio-service-request-suggested-maintenance/descendant::div/otalio-chip/div/div)[1]")
    private WebElement itemMaintenanceChipText;

    @FindBy(xpath = "//mat-select[contains(@id,'statusId')]/descendant::span/span")
    private WebElement statusFieldText;

    @FindBy(xpath = "//mat-select[contains(@id,'statusDetailId')]/descendant::span/span")
    private WebElement statusDetailIdText;

    @FindBy(xpath = "//otalio-service-request-obsolete/descendant::input")
    private WebElement toggleButtonStatus;

    public String checkObsoleteToggleButtonStatus() {
        seleniumWaits.untilElementIsVisible(toggleButtonStatus);
        return toggleButtonStatus.getAttribute("aria-checked");
    }

    public String getStatusDetailIdText(){
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(statusDetailIdText);
    }

    public void  clickOnAddedRequest(String request){
        String newRequest = "(//div[contains(text(),'"+request+"')])[1]";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(newRequest)));
    }

    public String getStatusFieldText(){
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(statusFieldText);
    }

    public String getItemMaintenanceChipText(){
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(itemMaintenanceChipText);
    }

    public String getMaintenanceRequestCodeIdIdDropdownText(){
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(maintenanceRequestCodeIdIdDropdownText);
    }

    public void statusSaveButton(){
//        String saveButton = "//otalio-create-service-resolution//span[normalize-space()='Save']";
        String saveButton = "//otalio-create-service-request//span[normalize-space()='Save']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(saveButton)));
    }

    public void selectStatusId(String status){
        seleniumActions.isElementDisplayed(statusIdDropdown);
        seleniumActions.moveToElementAndClick(statusIdDropdown);
        String selectCategoryDropdown = "//mat-option//span[normalize-space()='"+status+"']";
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectCategoryDropdown)));
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectCategoryDropdown)));
        }
    }

    public void selectStatus(String status){
        seleniumActions.isElementDisplayed(statusDropdown);
        seleniumActions.jsClick(statusDropdown);
        String selectCategoryDropdown = "//mat-option//span[normalize-space()='"+status+"']";
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectCategoryDropdown)));
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectCategoryDropdown)));
        }
    }

    public String getCommentText(){
        return seleniumActions.getTextMessage(commentText);
    }

    public void enterCommentInputField(String text) {
        seleniumActions.clearValue(commentField);
        seleniumActions.typeValue(commentField, text);
        String saveButton = "//otalio-comments-service-item//span[normalize-space()='Save']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(saveButton)));
    }

    public String getStatusDropdownText(){
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(statusDropdownText);
    }

    public String getItemStatusChipText(){
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(itemStatusChipText);
    }

    public String getServiceItemCodeResolutionIdDropdownText(){
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(serviceItemCodeResolutionIdDropdownText);
    }

    public String getItemCardName(){
        return seleniumActions.getTextMessage(itemCardText);
    }

    public String getOriginTypeDropdown(){
        return seleniumActions.getTextMessage(originTypeDropdownText);
    }

    public String getOperationPositionIdDropdownText(){
        return seleniumActions.getTextMessage(operationPositionIdDropdownText);
    }

    public String getOperationDepartmentIdDropdownText(){
        return seleniumActions.getTextMessage(operationDepartmentIdDropdownText);
    }

    public String getServiceItemCodeRequestDropdownText(){
        return seleniumActions.getTextMessage(serviceItemCodeRequestDropdownText);
    }

    public String getServiceItemAreaDropdownText(){
        return seleniumActions.getTextMessage(serviceItemAreaDropdownText);
    }

    public void clickOnAddIconButton() {
        seleniumActions.clickOnElement(addIcon);
    }

    public void setTargetCompletionDateTime(){
        seleniumActions.moveToElementAndClick(targetCompletionDateTimeField);
    }

    public void targetDate(String date) {
        String setDate = "(//otalio-time-picker-adapter[contains(@id,'otalio-time-picker-2')]/div/descendant::input)[2]";
        seleniumActions.typeValue(webDriver.findElement(By.xpath(String.format(setDate))), date);
        seleniumActions.moveToElementAndClick(whoHeader);
    }

    public void selectAccountStatementTypeDropdown(){
        String dropDownName = "[id='print-statement-report-type-infinite-select']";
        seleniumActions.clickOnElement(webDriver.findElement(By.cssSelector(dropDownName)));
        String selectEmbarkationPortDropDownText = "(//mat-option[contains(@id,'otalio-infinite-select')])[1]";
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectEmbarkationPortDropDownText)));

        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectEmbarkationPortDropDownText)));
        }
    }

    public void selectEmbarkationPortDropdown(String dropdown){
        String dropDownName = "//mat-select/descendant::span[text()='"+dropdown+"']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(dropDownName)));
        String selectEmbarkationPortDropDownText = "(//mat-option[contains(@id,'otalio-infinite-select')])[1]";
        try {
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(selectEmbarkationPortDropDownText)));

        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectEmbarkationPortDropDownText)));
        }
    }
    public void selectNonRefundableDropdown(String dropdown){
        String dropDownName = "//span[text()='"+dropdown+"']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(dropDownName)));
        seleniumActions.sleep(4000);
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select')])[1]")));
        String selectEmbarkationPortDropDownText = "(//mat-option[contains(@id,'otalio-infinite-select')])[1]";
        // seleniumWaits.untilElementIsVisible((WebElement) By.xpath("(//mat-option[contains(@id,'otalio-infinite-select')])[2]"));
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectEmbarkationPortDropDownText)));

        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectEmbarkationPortDropDownText)));
        }
    }
    public void selectCategoryDropDown(String dropdown,String option){
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[normalize-space()='"+dropdown+"']")));
        String selectDropDownText = "//span[normalize-space()='"+option+"']";
        String searchField = "//input[contains(@placeholder,'Search')]";
        seleniumActions.typeValue(webDriver.findElement(By.xpath(searchField)),option);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectDropDownText)));

    }

    public void selectGenderDropDown(String option){
        String dropDownName = "[id='visitor-info-general-gender-select']";
        seleniumActions.clickOnElement(webDriver.findElement(By.cssSelector(dropDownName)));
        String selectDropDownText = "//span[contains(text(),'"+option+"')]";
        String searchField = "//input[contains(@placeholder,'Search')]";
        seleniumActions.sleep(3000);
        seleniumActions.typeValue(webDriver.findElement(By.xpath(searchField)),option);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectDropDownText)));
    }


    public void selectDropDown(String dropdown,String option){
        String dropDownName = "//mat-select/descendant::span[text()='"+dropdown+"']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(dropDownName)));
        String selectDropDownText = "//span[contains(text(),'"+option+"')]";
        String searchField = "//input[contains(@id,'field-search')]";
        seleniumActions.sleep(3000);
        seleniumActions.typeValue(webDriver.findElement(By.xpath(searchField)),option);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectDropDownText)));
    }

    public void selectDropDowns(String dropdown,String option){
        String dropDownName = "//span[text='"+dropdown+"']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(dropDownName)));
        String selectDropDownText = "//span[contains(text(),'"+option+"')]";
        String searchField = "//input[contains(@placeholder,'Search')]";
        seleniumActions.sleep(3000);
        seleniumActions.typeValue(webDriver.findElement(By.xpath(searchField)),option);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectDropDownText)));
    }

    public void clickOnServiceTitle(){
        seleniumActions.moveToElementAndClick(serviceTitle);
    }

    public void serviceRequestClick(String columnName, String lastName){
        int index = getIndexOfServiceRequestColumnName(columnName) + 2 ;
        System.out.println("index :: " + index);
        String departmentName = "(//span[normalize-space()='"+lastName+"']/../../preceding-sibling::td["+index+"]/div/otalio-service-request-item-link/a)[1]";
        seleniumActions.highLighterMethod(webDriver.findElement(By.xpath(departmentName)));
        seleniumActions.jsClick(webDriver.findElement(By.xpath(departmentName)));
    }

    public int getIndexOfServiceRequestColumnName(String columnName) {
        List<String> columns = new ArrayList<>();
        for (int i = 1; i < tableHeaderItems.size(); i++) {
            try {
                seleniumActions.sleep(500); //due to table header loading given 500 millisecs
                seleniumWaits.untilElementIsVisible(tableHeaderItems.get(1));
                columns.add(seleniumActions.getTextMessage(tableHeaderItems.get(i)));
            } catch (StaleElementReferenceException ste) {
                By tableHeader = By.xpath("[role='columnheader']");
                columns.add(seleniumActions.getTextMessage(webDriver.findElements(tableHeader).get(i)));
            }
        }
        columns.removeAll(Arrays.asList(""));
        int columnIndex = columns.indexOf(columnName);
        return columnIndex;
    }

    public void clickOnRequestNumber(){
        seleniumActions.clickOnElement(numberLink);
    }

    public void clickAddButton(){
        seleniumActions.clickOnElement(addButton);
    }

    public String setLocationNumber(){
        return seleniumActions.getTextMessage(locationField);
    }

    public boolean checkErrorMessage() {
        return seleniumActions.isElementDisplayed(errorMessage);
    }

    public String getUpdateStatusHistory(){
        seleniumActions.scrollInToElement(updateHistory);
        return seleniumActions.getTextMessage(updateHistory);
    }

    public String getStatusId(){
        return seleniumActions.getTextMessage(statusIdName);
    }

    public void clickOnEditIcon(){
        seleniumActions.sleep(2000);
        try {
            seleniumActions.clickOnElement(editIcon);
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(editIcon);
        }
    }
    public void checkOptionInStatusServiceRequest(String option){
        seleniumActions.jsClick(statusList);
//        String checkBox = "//span[text()='%s']/../preceding-sibling::mat-pseudo-checkbox";
        String checkBox = "//div[normalize-space()='%s']/../mat-pseudo-checkbox";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(checkBox, option))));
//        seleniumActions.pressEscapeKey();
//        seleniumActions.sleep(500); //after pressing esc key
//        seleniumActions.pressEscapeKey();
        seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
        seleniumActions.sleep(3000);
    }

    public void checkOptionInStatus(String option){
        seleniumActions.jsClick(statusList);
        String checkBox = "//span[text()='%s']/../preceding-sibling::mat-pseudo-checkbox";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(checkBox, option))));
//        seleniumActions.pressEscapeKey();
//        seleniumActions.sleep(500); //after pressing esc key
//        seleniumActions.pressEscapeKey();
        seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
        seleniumActions.sleep(3000);
    }

    public void clearStatus(){
        seleniumActions.clickOnElement(statusList);
        for (int i=0; i< statusCheckBoxes.size(); i++){
            seleniumActions.sleep(2000);
            seleniumActions.clickOnElement(statusCheckBoxes.get(i));
        }
        if(statusCheckBoxes.size()>0){
            statusCheckBoxes.get(0).click();
        }
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
    }
    public void clearOperationDepartment(){
        seleniumActions.clickOnElement(operationDepartment);
        for (int i=0; i< statusCheckBoxes.size(); i++){
            seleniumActions.sleep(2000);
            seleniumActions.clickOnElement(statusCheckBoxes.get(i));
        }
        if(statusCheckBoxes.size()>0){
            statusCheckBoxes.get(0).click();
        }
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
    }

    public List<String> getRowValuesForRequestOverview(){
        List<String> data = Arrays.asList("Number","Request","Location","Priority");
        List<String> rowData = new ArrayList<>();
        for (int i=0; i<data.size(); i++) {
            int index = seleniumActions.getIndexValueFromTheList(columnHeaderForTour, data.get(i)) + 1;
            rowData.add(seleniumActions.getTextMessage(webDriver.findElement(
                    By.xpath("//div[normalize-space()='" + data.get(i) + "']/ancestor::div[contains(@class,'headers')]/following-sibling::div[contains(@class,'rowsview')]/descendant::tr/td[" + index + "]")
            )));
            System.out.println(index);
            System.out.println(rowData);
        }
        return rowData;
    }

    public List<String> getRequestOverviewColumnHeaders(){
        List<String> list = seleniumActions.getListOfElementsText(requestColumnData);
        seleniumActions.sleep(3000);
        List<String> actualList = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            actualList.add(list.get(i));
        }
        return actualList;
    }

    public void selectPriorityAssignedRequest(){
        seleniumActions.clickOnElement(priorityLink);
    }

    public void selectTeam(String team){
        seleniumActions.clickOnElement(teamDropdown);
        seleniumActions.typeValue(searchField,team);
        seleniumWaits.untilElementIsVisible((webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'"+team+"')]"))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'"+team+"')]")));
    }

    public void clickOnPlusButtonLocation(){
        seleniumActions.clickOnElement(locationPlusButton);
    }

    public List<String> getRowDetails(){
        return seleniumActions.getListOfElementsText(getResolution);
    }

    public void switchToNewWindow(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.switchToOpenedWindow();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public String getStatusText(){
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(statusText);
    }

    public void enterComment(String comment){
        seleniumActions.typeValue(commentInputField,comment);
    }

    public void selectStatusDropdown(String dropDown, String item){
        String status = "(//otalio-request-status//span[normalize-space()= '%s']/ancestor::button/descendant::icon[contains(@icon,'arrow-down')]/*[name()='svg']/*[name()='use'])[1]";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(status,dropDown))));
        String selectStatus = "//div/button[normalize-space()='"+item+"']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(selectStatus))));

    }

    public void selectRequestDropdown (String request){
        seleniumActions.jsClick(requestDropdown);
        try {
            seleniumActions.typeValue(searchInputBox,request);
        } catch (StaleElementReferenceException ser) {
            seleniumActions.typeValue(searchInputBox, request);
        }
        String selectDropdown = "//mat-select[@role='combobox']/..//span[text()='"+request+"']";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(selectDropdown))));

    }

    public void selectLocationDropdown(String data){
        seleniumActions.clickOnElement(locationDropdown);
        String location = "//div[normalize-space()='"+data+"']/../../div/descendant::icon[contains(@icon,'plus')]/*[name()='svg']/*[name()='use']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(location))));
    }

    public void clickFastCreation(String action){
        String actionBox = "//otalio-action-box/div[normalize-space()='"+action+"']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(actionBox))));
        seleniumActions.switchToOpenedWindow();
    }

    public void typeCabinNumber(String number) {
        try {
            seleniumActions.typeValue(searchField, number);
        } catch (StaleElementReferenceException sete) {
            seleniumActions.typeValue(searchField, number);
        }
    }

    public void selectCategory(String category){
        seleniumActions.isElementDisplayed(categoryDropdown);
        seleniumActions.clickOnElement(categoryDropdown);
        String selectCategoryDropdown = "//mat-option//span[normalize-space()='"+category+"']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectCategoryDropdown)));
    }

    public void selectCode(String code){
        seleniumActions.moveToElementAndClick(codeDropdown);
        String selectCodeDropdown = "(//mat-option[@role='option']/span)[1]";
        seleniumActions.sleep(2000);
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(selectCodeDropdown)));
    }

    public void clickServiceRequest(){
        seleniumWaits.untilElementIsClickable(serviceRequestLink);
        seleniumActions.clickOnElement(serviceRequestLink);
    }

    public void setDateForServiceRequest(String startDate, String endDate) {
        seleniumActions.clickOnElement(createDateArrowMark);
        String startDateInput = "[formcontrolname='startDate']";
        seleniumActions.clickOnElement(clearStartDate);
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(startDateInput)), startDate);
        String endDateInput = "//mat-label[text()='Equal or less Date']//ancestor::mat-form-field//input[@data-placeholder='m/d/yyyy']";
        seleniumActions.clickOnElement(clearEndDate);
        seleniumActions.typeValue(webDriver.findElement(By.xpath(endDateInput)), endDate);
    }

    public List<String> getRowValuesForServiceRequest(){
        List<String> data = Arrays.asList("Guest Cabin","Last Name","First Name");
        List<String> rowData = new ArrayList<>();
        for (int i=0; i<data.size(); i++) {
            int index = seleniumActions.getIndexValueFromTheList(columnHeaderForTour, data.get(i)) + 1;
            rowData.add(seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//div[normalize-space()='" + data.get(i) + "']/ancestor::div[contains(@class,'headers')]/following-sibling::div[contains(@class,'rowsview')]/descendant::tr/td[" + index + "]"))));
            System.out.println("index: " + index);
            System.out.println("rowData: "+ rowData);
        }
        return rowData;
    }
}
