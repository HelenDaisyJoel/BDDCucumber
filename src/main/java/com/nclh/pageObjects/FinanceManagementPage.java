package com.nclh.pageObjects;
import com.nclh.constants.GlobalConstants;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinanceManagementPage {
    private WebDriver webDriver;
    private SeleniumActions seleniumActions;
    private SeleniumWaits seleniumWaits;
    private ShipSecurityPage shipSecurityPage;
    private CommonPage commonPage;
    private CommonFunctions commonFunctions;
    private HotelMaintenancePage hotelMaintenancePage;

    public FinanceManagementPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        seleniumActions = new SeleniumActions(webDriver);
        commonPage = new CommonPage(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonFunctions = new CommonFunctions();
    }

    @FindBy(xpath = "//input[@id='finance-card-posting-status-mat-toggle-input']")
    private WebElement toggleButtonStatus;

    @FindBy(xpath = "//input[@id='otalio-finance-card-exclude-from-bulk-settlement-input']")
    private WebElement excludeToggleButton;

    @FindBy(xpath = "//input[@id='finance-card-paperless-invoice-toggle-input']")
    private WebElement paperlessToggleButton;

    @FindBy(xpath = "//input[@id='profile-service-charge-exempted-slide-toggle-input']")
    private WebElement serviceChargeExemptedToggleButton;

    @FindBy(xpath = "//tbody/tr[@class='dx-row dx-data-row dx-row-lines null']")
    private List<WebElement> folioDetailsRow;

    @FindBy(xpath = "//td[@aria-colindex='18'][not(@role='columnheader')]")
    private List<WebElement> statusList;

    @FindBy(xpath = "//td[@aria-colindex='6'][not(@role='columnheader')]/descendant::otalio-chip/div/div[text()]")
    private List<WebElement> postingStatusListText;

    @FindAll({
            @FindBy(css = "[id='passengers-list-page-more-actions']"),
            @FindBy(css = "[id='crew-list-page-more_vert-mat-icon']")
    })
    private WebElement threeDotGuestUser;


    @FindAll({
            @FindBy(css = "[id*='bulk-operations-button']"),
            @FindBy(css = "[id='crew-list-page-bulk-operatoins-button']")
    })
    private WebElement bulkGuestButton;

    @FindBy(css = "bulk-credit-limit-template-selector")
    private WebElement creditLimitTemplateDropDown;

    @FindBy(css = "[id='posting-allowed-select']")
    private WebElement enableOrDisableAccountPostingsDropDown;

    @FindBy(xpath = "//input[@id='posting-status-comment-input']")
    private WebElement commentInputField;

    @FindBy(css = "[id='otalio-infinite-select-subDepartmentId-field']")
    private WebElement subDepartmentsDropDown;

    @FindBy(css = "[id='payments-amount-input']")
    private WebElement amountInputField;

    @FindBy(css = "[id='payments-comments-input']")
    private WebElement paymentCommentInputField;

    @FindBy(css = "[id='disallowed-departments-comment-input']")
    private WebElement disallowedDepartmentsCommentInputField;

    @FindBy(css = "[id='bulk-discount-templates-comment']")
    private WebElement discountTemplatesCommentInputField;

    @FindBy(css = "[id*='selector-list-disallowed-departments-preselection']")
    private WebElement listDisallowedDepartmentsDropdown;

    @FindBy(css = "[id='bulk-dicount-template-selector']")
    private WebElement discountTemplate;

    @FindBy(css = "[icon='add-user']")
    private WebElement payerAccountIcon;

    @FindBy(css = "[placeholder='Route To Folio']")
    private WebElement routeToFolioDropdown;

    @FindBy(css = "[id='routing-comment-input']")
    private WebElement routingCommentInputField;

    @FindBy(css = "[id='bulk-onboard-credit-templates-comment']")
    private WebElement onboardCreditCommentInputField;

    @FindBy(css = "[id='bulk-credit-limit-templates-comment']")
    private WebElement creditLimitCommentInputField;


    @FindBy(css = "[id='bulk-add-posting-comment-input']")
    private WebElement addPostingCommentInputField;

    @FindBy(xpath = "//mat-checkbox[contains(@class,'mat-checkbox-checked')]/descendant::label/span/following-sibling::span/span")
    private WebElement getSelectedCheckBox;

    @FindBy(xpath = "//button[contains(@id,'guest-search-delete')]/span/icon/*[name()='svg']/*[name()='use']")
    private WebElement crossButton;

    @FindBy(css = "[placeholder='Limit amount']")
    private WebElement limitInputField;

    @FindBy(xpath = "//div[contains(text(),'Limit Amount')]/span")
    private WebElement getLimitAmountText;

    @FindBy(xpath = "(//button[@id='credit-limits-delete-credit-limit-button']/span/icon)[1]")
    private WebElement creditLimitDeleteIcon;

    @FindBy(xpath = "(//button[contains(@id,'delete')]/span/icon)[1]")
    private WebElement routingDeleteIcon;

    @FindBy(xpath = "(//icon[contains(@id,'departments-collection-list')]/../descendant::div[@class='mat-list-text']/section)[1]")
    private WebElement setDepartmentsCollection;

    @FindBy(xpath = "(//mat-selection-list/section/icon/*/*[contains(@href,'plus')][name()='use'])[1]")
    private WebElement preSelectionPlusButton;

    @FindBy(xpath = "(//button[contains(@id,'departments-collection')]/../descendant::label/span/following-sibling::span)[1]")
    private WebElement mainDepartmentCollection;

    @FindBy(xpath = "(//button[@id='departments-collection-tree-add-button']/span/mat-icon)[1]")
    private WebElement mainDepartmentPlusButton;

    @FindBy(xpath = "(//button[@id='departments-collection-tree-add-button']/span/mat-icon)[1]")
    private WebElement subDepartmentPlusButton;

    @FindBy(xpath = "//otalio-selected-departments-collection-tree//mat-tree[@role='tree']/descendant::span[@class='mat-checkbox-label']")
    private List<WebElement> selectedDisallowedDepartmentText;

    @FindBy(xpath = "//tbody/tr[contains(@class,'dx-data-row')]/td[@role='gridcell'][not(@aria-colindex='1')]")
    private List<WebElement> listDisallowedDepartmentText;

    @FindBy(xpath = "//button[@id='complex-filter-add-more-button']/span/icon/*[name()='svg']/*[name()='use']")
    private WebElement addMoreFilterButton;

    @FindBy(xpath = "//button[@id='complex-filter-label-currentBalance.currentBalance-open-button']/icon/*[name()='svg']/*[name()='use']")
    private WebElement currentBalanceOpenButton;

    @FindBy(css = "[placeholder='Min']")
    private WebElement minInputBox;

    @FindBy(css = "[placeholder='Max']")
    private WebElement maxInputBox;

    @FindBy(xpath = "//h3[text()='Account Statement']/ancestor::otalio-finance-card/descendant::otalio-chip/div/div")
    private WebElement accountStatementStatus;

    @FindBy(xpath = "//otalio-payment-method-assignment-form/descendant::input[@formcontrolname='comment']")
    private WebElement paymentMethodCommentInputField;

    @FindBy(xpath = "//mat-label[text()='Comment']/ancestor::span/preceding-sibling::input[contains(@class,'ng-touched')]")
    private WebElement commentTextMessage;

    @FindBy(xpath = "//td[@class='dx-cell-focus-disabled']/div/span[text()]")
    private WebElement commentPostingStatusTextMessage;

    @FindBy(xpath = "//td[@role='columnheader'][not(contains(@aria-label,'Column Actions'))][not(contains(@aria-colindex,'9'))]")
    private List<WebElement> deliverablesColumnData;

    @FindBy(xpath = "//td[@aria-colindex='9'][not(contains(@aria-label,'Column Reservation Status'))]")
    private List<WebElement> reservationStatusText;

    @FindBy(xpath = "//button[@id='complex-filter-label-paymentTypes.paymentType-open-button']/icon/*[name()='svg']/*[name()='use']")
    private WebElement paymentTypeDropDown;

    @FindBy(xpath = "//span[contains(text(),'Cash Payment')]/ancestor::otalio-payment-method-assignment-form/descendant::mat-checkbox/label/span/input")
    private WebElement paymentTypeCheckBox;

    @FindBy(css = "[placeholder='Payment Type']")
    private WebElement paymentTypeInputField;

    @FindBy(xpath = "//span[contains(text(),'Cash Payment')]/ancestor::otalio-payment-method-assignment-form/descendant::mat-checkbox/label/span/input")
    private WebElement paymentMethodCheckbox;

    @FindBy(css = "[placeholder='Amount']")
    private WebElement amountfield;

    @FindBy(xpath = "//span[text()='Credit']/parent::div/descendant::otalio-chip/div/div")
    private WebElement creditText;

    @FindBy(xpath = "(//otalio-finance-card/descendant::div/span[@class='text-type-info'])[2]")
    private WebElement paymentMethodRemainingLimitText;

    @FindBy(xpath = "(//otalio-finance-card/descendant::div/span[@class='text-type-info'])[1]")
    private WebElement paymentMethodLimitText;

    @FindBy(xpath = "//button[@id='complex-filter-label-embarkation.displayDate-open-button']/icon/*[name()='svg']/*[name()='use']")
    private WebElement embarkationTypeDropDown;

    @FindBy(xpath = "//otalio-department-table-container[@identifier='disallowed-departments']")
    private WebElement identifierDisallowedDepartment;

    @FindBy(xpath = "//button[@id='complex-filter-label-embarkation.displayDate-open-button']/icon/*[name()='svg']")
    private WebElement embarkationArrowButton;

    @FindBy(xpath = "//mat-label[text()='Equal Date']/ancestor::mat-form-field/descendant::button[@aria-label='Open calendar']")
    private WebElement equalDateCalendarIcon;

    @FindBy(xpath = "//div[contains(@class,'calendar-body-today')]")
    private WebElement todayDate;

    @FindBy(css = "[id*='embarkation'] > div + icon")
    private WebElement embarkationDatePicker;

    @FindBy(css = "[href*='close']")
    private List<WebElement> closeIcon;

    @FindBy(css = "[id='otalio-infinite-select-amenityCodeId-field']")
    private WebElement amenityDropdown;

    @FindBy(css = "[id='amenity-editor-amount'] > input")
    private WebElement amountPaidInputField;

    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-amenityCodeId-field']/descendant::span/span")
    private WebElement amenityCodeIdField;

    @FindBy(xpath = "//*[text()='Other']/following-sibling::div/div/div  ")
    private List<WebElement> otherInfoList;

    @FindBy(css = "[id*='PostingDate'] > div + icon")
    private WebElement postingDateArrowDownButton;

    @FindBy(xpath = "//tbody/tr/td[@role='gridcell' and @aria-colindex='2']/span")
    private List<WebElement> embarkationDateListText;

    @FindBy(xpath = "//td[@role='columnheader'][not(contains(@class,'select'))][not(contains(@class,'events'))]")
    private List<WebElement> postingDateHeaderName;

    @FindBy(xpath = "//b[text()='Embarkation Date:']/ancestor::button/descendant::icon")
    private WebElement embarkationFilterIcon;

    @FindBy(xpath = "//b[text()='Disembarkation Date:']/ancestor::button/descendant::icon")
    private WebElement disembarkationFilterIcon;

    @FindBy(xpath = "//mat-label[text()='Equal Date']/ancestor::mat-form-field/descendant::icon")
    private WebElement clearEqualDate;

    @FindBy(xpath = "//tbody/tr/td[@role='gridcell' and @aria-colindex='10']/span")
    private List<WebElement> embarkationDateHeaderName;

    @FindBy(xpath = "//tbody/tr/td[@role='gridcell' and @aria-colindex='11']/span")
    private List<WebElement> disembarkationDateHeaderName;

    @FindBy(xpath = "//mat-label[text()='Limit Amount']/ancestor::span/preceding-sibling::input[contains(@class,'ng-touched')]")
    private WebElement creditLimitFieldText;

    @FindBy(xpath = "//mat-select[contains(@id,'nonRefundableOnBoardCredit')]/descendant::span/span")
    private WebElement onboardCreditDropdownText;

    @FindBy(xpath = "//mat-select[contains(@id,'finPaymentSubDepartment')]/descendant::span/span")
    private WebElement paymentDepartmentInputFieldText;

    @FindBy(xpath = "//mat-select[contains(@id,'finCollectorSubDepartment')]/descendant::span/span")
    private WebElement collectorDepartmentInputFieldText;

    @FindBy(xpath = "//div[contains(@id,'template-onboard')]/descendant::div[@class='font-weight-bold']/span")
    private List<WebElement> onboardTemplateText;

    @FindBy(xpath = "//span[normalize-space()='Payment Department']/following-sibling::span")
    private WebElement paymentDepartmentText;

    @FindBy(xpath = "//span[normalize-space()='Collector Department']/following-sibling::span")
    private WebElement collectorDepartmentText;

    @FindBy(xpath = "//span[normalize-space()='Credit Amount']/following-sibling::span")
    private WebElement creditAmountLimitText;

    @FindBy(xpath = "//mat-select[@id='amenity-editor-post-automatically-check-in']")
    private WebElement postAutomaticallyDropDown;

    @FindAll({
            @FindBy(css = "[placeholder^='Search']"),
            @FindBy(css = "input.complex-search-input")
    })
    private WebElement crewSearchBox;

    @FindBy(xpath = "(//div[@aria-label='Select row'])[1]")
    private WebElement amenityCheckbox;

    @FindBy(xpath = "//span[text()='START' or normalize-space()='START']")
    private WebElement startButton;

    @FindBy(xpath = "//button[@id='group-profile-list--delete-mat-icon']/span/icon")
    private WebElement groupDeleteIcon;

    @FindBy(css = "[aria-label='Column Code']")
    private WebElement getColumnStatus;

    @FindBy(xpath = "//h2[normalize-space()='Current voyage']")
    private WebElement currentVoyageText;

    @FindBy(xpath = "//h2[normalize-space()='Next voyage']")
    private WebElement nextVoyageText;

    @FindAll({
            @FindBy(xpath = "//span[normalize-space()='Floor Limit']/following-sibling::span"),
            @FindBy(xpath = "//span[normalize-space()='Credit Limit']/following-sibling::span")
    })
    private WebElement floorLimit;

    @FindBy(xpath = "//div[normalize-space()='Payment Method']/following-sibling::div[contains(@class,'mat-body-1')]")
    private WebElement paymentMethodText;

    @FindBy(xpath = "//span[normalize-space()='Remaining Limit']/following-sibling::span")
    private WebElement reminingLimitText;
    @FindBy(xpath = "//td[@role='columnheader']")
    private List<WebElement> columnHeaders;
    @FindBy(xpath = "//div[contains(.,'Category')]/ancestor::otalio-complex-filter-label")
    private List<WebElement> categoryDropdown;
    @FindBy(xpath = "//*[contains(normalize-space(.), 'Checks (Open, In Progress)')]/ancestor::mat-expansion-panel-header//icon[@icon='check-big']")
    private List<WebElement> checksGreenTickIcon;
    @FindBy(xpath = "//*[@class='row ng-untouched ng-pristine ng-valid']")
    private List<WebElement> classificationRows;

    @FindBy(xpath = "(//button[.//*[name()='use' and (contains(@href,'#clean'))]])[last()]")
    private WebElement lastDeleteClassificationIcon;
    @FindBy(xpath = "//input[@id='complex-filter-multi-search-in-options-list-input']")
    private WebElement packageFilterSearchBox;

    @FindBy(xpath = "//*//preceding-sibling::mat-pseudo-checkbox")
    private List<WebElement> packageCheckboxes;
    @FindBy(xpath = "//td[@role='columnheader' and @aria-label]")
    private List<WebElement> columnHeadersText;
    public boolean getReminingLimit(){
        return seleniumActions.isElementDisplayed(reminingLimitText);
    }

    public boolean getPaymentMethod(){
        return seleniumActions.isElementDisplayed(paymentMethodText);
    }

    public boolean getFloorLimitText(){
        return seleniumActions.isElementDisplayed(floorLimit);
    }

    public boolean getNextVoyage(){
        return seleniumActions.isElementDisplayed(nextVoyageText);
    }

    public boolean getCurrentVoyage(){
       return seleniumActions.isElementDisplayed(currentVoyageText);
    }

    public String checkSortColumn() {
        seleniumWaits.untilElementIsVisible(getColumnStatus);
        return getColumnStatus.getAttribute("aria-sort");
    }

    public void sortColumnName(String columnName) {
        String codeType = "//td[contains(@aria-label,'%s')]";
        seleniumActions.sleep(4000);
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath(String.format(codeType, columnName))));
        System.out.println(webDriver.findElement(By.xpath(String.format(codeType, columnName))).getAttribute("aria-sort"));
        WebElement column = webDriver.findElement(By.xpath(String.format(codeType, columnName)));
        if (column.getAttribute("aria-sort").equals("none")) {
            seleniumActions.clickOnElement(column);
        }

    }

    public String getGroupDetails(){
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("//span[contains(@class,'nodata')]")));
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//span[contains(@class,'nodata')]")));
    }

    public void clickGroupDeleteIcon(){
        seleniumActions.clickOnElement(groupDeleteIcon);
    }

    public void startButton(){
        seleniumWaits.untilElementIsVisible(startButton);
        seleniumActions.clickOnElement(startButton);
    }

    public String getStatusText() {
//        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("//td[@aria-colindex='17'][not(contains(@aria-label,'Column Status'))]")));
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//td[@aria-colindex='17'][not(contains(@aria-label,'Column Status'))]")));
    }

    public void selectStatus(String button){
        seleniumActions.sleep(5000);
        seleniumWaits.untilElementIsClickable(amenityCheckbox);
        seleniumActions.jsClick(amenityCheckbox);
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.cssSelector("[id='precruise-postings-overview-menu-button']")));
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.cssSelector("[id='precruise-postings-overview-menu-button']")));
        String selectSecurityFlags = "//button[text()='" + button + "' or normalize-space()='" + button + "']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectSecurityFlags)));
    }

    public void openGuestProfile(String crewFirstName, String lastName) {
        seleniumActions.typeValue(crewSearchBox, crewFirstName);
    }

    public void selectDropDownForPosting(String dropdown,String option){
        String dropDownName = "[placeholder*='"+dropdown+"']";
        seleniumActions.clickOnElement(webDriver.findElement(By.cssSelector(dropDownName)));
        String selectDropDownText = "//span[contains(text(),'"+option+"')]";
        String searchField = "[id='otalio-infinite-select-rvcId-field-search']";
        seleniumActions.sleep(3000);
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(searchField)),option);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectDropDownText)));
    }

    public String getPaymentDepartmentText(){
        return seleniumActions.getTextMessage(paymentDepartmentText);
    }

    public String getCollectorDepartmentText(){
        return seleniumActions.getTextMessage(collectorDepartmentText);
    }

    public String getCreditLimitText(){
        return seleniumActions.getTextMessage(creditAmountLimitText);
    }

    public List<String> getOnboardTemplateText(){
        seleniumActions.sleep(3000);
        seleniumWaits.untilListElementIsVisible(onboardTemplateText);
        return onboardTemplateText.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public String getPaymentDepartmentInputFieldText(){
        return seleniumActions.getTextMessage(paymentDepartmentInputFieldText);
    }

    public String getCollectorDepartmentInputFieldText(){
        return seleniumActions.getTextMessage(collectorDepartmentInputFieldText);
    }

    public WebElement getcreditlimitFieldTextWebElement(){
        return creditLimitFieldText;
    }

    public String getOnboardCreditDropdownText(){
        return seleniumActions.getTextMessage(onboardCreditDropdownText);
    }

    public void enterEmbarkationDateAndDisEmbarkationDate(String embDate,String disEmbDate){
        seleniumActions.clickOnElement(embarkationFilterIcon);
        String dateInput = "[formcontrolname='date']";
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(dateInput)),embDate);
        seleniumActions.sleep(3000);
        seleniumActions.pressEscapeKey();
        seleniumActions.clickOnElement(disembarkationFilterIcon);
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(dateInput)),disEmbDate);
        seleniumActions.sleep(3000);

    }

    public String getEmbarkationDateText(){
        String emb = embarkationDateHeaderName.get(0).getText();
        return emb;
    }

    public String getDisembarkationDateText(){
        String disemb = disembarkationDateHeaderName.get(0).getText();
        return disemb;
    }

    public  void clearEmbarkationAndDisembarkationDate(){
        seleniumActions.clickOnElement(embarkationFilterIcon);
        seleniumActions.moveToElementAndClick(clearEqualDate);
        seleniumActions.sleep(5000);
        seleniumActions.pressEscapeKey();
        seleniumActions.clickOnElement(disembarkationFilterIcon);
        seleniumActions.moveToElementAndClick(clearEqualDate);
        seleniumActions.sleep(5000);
        seleniumActions.pressEscapeKey();
    }

    public List<String> getPostingHeaderName(){
        return seleniumActions.getListOfElementsText(postingDateHeaderName);
    }

    public List<String> getFilterEmbarkationDate(){
//        System.out.println(embarkationDateListText);
//        return seleniumActions.getListOfElementsText(embarkationDateListText);
        List<String> data = new ArrayList<>();
        for(WebElement element : embarkationDateListText){
            data.add(element.getText());
        }
        return data;
    }

    public void enterDates(String startOreEnd, String date) {
        String dateInput = "[formcontrolname='%s']";
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(String.format(dateInput, startOreEnd))), date);
        seleniumActions.sleep(10000);
    }

    public void postingDateFilter(String date){
        seleniumActions.clickOnElement(postingDateArrowDownButton);
        String radioButton = "//span[text()='"+date+"']/ancestor::mat-radio-button/descendant::input[@class='mat-radio-input']";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(radioButton)));
    }

    public List<String> getOtherInfoList(){
        List<String> data = new ArrayList<>();
        for(WebElement element : otherInfoList){
            data.add(element.getText());
        }
        return data;
    }

    public String getAmenityCodeId(){
       return seleniumActions.getTextMessage(amenityCodeIdField);
    }

    public void amenityDetails(){
        try {
            seleniumActions.clickOnElement(amenityDropdown);
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-amenityCodeId-field')])[1]")));

        } catch (Exception e) {
            seleniumActions.selectDropdownOption("Code","1");
        }
        seleniumWaits.untilElementIsClickable(amountPaidInputField);
        seleniumActions.clickOnElement(amountPaidInputField);
        seleniumActions.typeValue(amountPaidInputField, String.valueOf(commonFunctions.randomNumber()));
        seleniumActions.clickOnElement(postAutomaticallyDropDown);
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("//span[text()='NO']")));
    }

    public void clearDisembarkationDate(){
        seleniumActions.clickOnElement(embarkationDatePicker);
        for (int i=0; i< closeIcon.size(); i++){
            seleniumActions.sleep(1000);
            seleniumActions.clickOnElement(closeIcon.get(i));
            seleniumActions.sleep(1000);
        }
    }

    public void embarkationDateToday(){
        seleniumActions.clickOnElement(embarkationArrowButton);
        seleniumActions.moveToElementAndClick(equalDateCalendarIcon);
        seleniumActions.clickOnElement(todayDate);
    }

//    public void selectGuestAndClickOnFinance(){
//        String status ="//h3[text()='Account Statement']/ancestor::otalio-finance-card/descendant::otalio-chip/div/div";
//        if (webDriver.findElement(By.xpath(status)).size()>0){
//            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[text()='Financial Information']")));
//        }else {
//
//        }
//    }

    public boolean checkDisallowedDepartment(){
        seleniumActions.sleep(3000);
        return seleniumActions.isElementDisplayed(identifierDisallowedDepartment);
    }

    public String getPaymentMethodFloorText(){
        return seleniumActions.getTextMessage(paymentMethodLimitText);
    }

    public String getPaymentMethodText(){
        return seleniumActions.getTextMessage(paymentMethodRemainingLimitText);
    }

    public String getCreditText(){
        seleniumActions.sleep(3000);
        return  seleniumActions.getTextMessage(creditText);
    }

    public void typeSaveAmount(String amount){
        seleniumActions.isElementDisplayed(amountfield);
        seleniumActions.typeValue(amountfield,amount);

    }

    public void selectAccountCheckbox(int account){
        for (int i = 1; i <= account; i++) {
            String selectCheckBox = "(//div[@aria-label='Select row'])[" + i + "]";
            WebElement checkBox = webDriver.findElement(By.xpath(selectCheckBox));
            seleniumActions.clickOnElement(checkBox);
        }
    }

    public void selectPaymentMethodCheckbox(){
        seleniumActions.jsClick(paymentTypeCheckBox);
    }

    public String selectedCheckboxStatus() {
        seleniumWaits.untilElementIsVisible(paymentMethodCheckbox);
        return paymentMethodCheckbox.getAttribute("aria-checked");
    }

    public void checkPaymentTypeFilter(String option) {
        seleniumActions.moveToElementAndClick(paymentTypeDropDown);
        seleniumActions.typeValue(paymentTypeInputField,option);
        String checkBox = "//span[contains(text(),'%s')]/../preceding-sibling::mat-pseudo-checkbox";

        try {
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(checkBox, option))));
        } catch (StaleElementReferenceException stale) {
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(checkBox, option))));
            seleniumActions.pressEscapeKey();
            seleniumActions.sleep(500); //after pressing esc key
            seleniumActions.pressEscapeKey();
        }
        seleniumActions.moveToElementAndClick(addMoreFilterButton);

    }

    public void enterMinAndMaxBalance(String number){
        seleniumActions.moveToElementAndClick(paymentTypeDropDown);
        seleniumActions.typeValue(minInputBox,number);
    }

    public List<String> getReservationStatus(){
        seleniumActions.sleep(3000);
        return seleniumActions.getListOfElementsText(reservationStatusText);
    }

    public WebElement getPostingStatus(){
        return postingStatusListText.get(0);
    }

    public List<String> getPostingStatusList(){
        return seleniumActions.getListOfElementsText(postingStatusListText);
    }

    public List<String> getDeliverablesColumnHeaders() {
        try {
            List<String> list = seleniumActions.getListOfElementsText(deliverablesColumnData);
            seleniumActions.sleep(3000);
            List<String> actualList = new ArrayList<>();
            for (int i = 1; i < list.size(); i++) {
                actualList.add(list.get(i));
            }
            return actualList;

        }catch (StaleElementReferenceException exc) {
            List<String> list = seleniumActions.getListOfElementsText(deliverablesColumnData);
            seleniumActions.sleep(3000);
            List<String> actualList = new ArrayList<>();
            for (int i = 1; i < list.size(); i++) {
                actualList.add(list.get(i));
            }
            return actualList;
        }
    }

    public WebElement PostingStatusCommentTextWebElement(){
        return commentPostingStatusTextMessage;
    }

    public WebElement getCommentTextWebElement(){
        return commentTextMessage;
    }

    public void paymentMethodComment(String text) {
        seleniumActions.clearValue(paymentMethodCommentInputField);
        seleniumActions.typeValue(paymentMethodCommentInputField, text);
    }

    public void addRoutingButton(String button) {
        seleniumActions.sleep(1000);

        boolean hasValidationError = webDriver.findElements(By.xpath("//mat-error[@aria-live='polite']")).size() > 0;

        if (hasValidationError) {
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("//button[contains(@id,'guest-search-delete')]/span/icon")));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[normalize-space()='Delete']")));

            String addGuest = "//span[text()='ADD' or normalize-space()='ADD']";
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(addGuest)));
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(addGuest)));

            String searchIconXpath = "//otalio-complex-filter/descendant::icon[@icon='search']";
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(searchIconXpath)));

            WebElement searchInput = webDriver.findElement(By.xpath("//input[@id='guest-search-find-guest-input']"));
            seleniumActions.typeValue(searchInput, "G");

            String guestOptionXpath = "//mat-option";
            seleniumWaits.untilElementIsPresent(By.xpath(guestOptionXpath), 10);

            int number = hotelMaintenancePage.getRandomNumber();
            System.out.println("Random Guest Index: " + number);
            hotelMaintenancePage.selectGuests(number); // You may want to confirm this method actually clicks the option

            String selectGuest = "//span[text()='Select' or normalize-space()='Select']";
            try {
                seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(selectGuest)));
                seleniumActions.scrollInToElement(webDriver.findElement(By.xpath(selectGuest)));
                seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectGuest)));
            } catch (TimeoutException e) {
                System.out.println("Guest selection timed out.");
            }

        }
        else {
            String text = "//span[text()='" + button + "' or normalize-space()='" + button + "']";
            try {
                seleniumWaits.untilElementIsPresent(By.xpath(text), 10);
                seleniumActions.scrollInToElement(webDriver.findElement(By.xpath(text)));
                seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
            } catch (TimeoutException e) {
            }

        }
    }

    public String getAccountStatementStatus(){
        webDriver.navigate().refresh();
        return seleniumActions.getTextMessage(accountStatementStatus);
    }

    public void enterMinAndMaxBalance(String number, String number2){
        seleniumActions.moveToElementAndClick(currentBalanceOpenButton);
        seleniumActions.typeValue(minInputBox,number);
        seleniumActions.typeValue(maxInputBox,number2);
    }

    public void checkOptionInTheAddMoreFilter(String option) {
        seleniumActions.moveToElementAndClick(addMoreFilterButton);
        String checkBox = "//span[text()='%s']/../preceding-sibling::mat-pseudo-checkbox";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(checkBox, option))));
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(500); //after pressing esc key
        seleniumActions.pressEscapeKey();

    }

    public List<String> disallowedDepartmentList() {
        seleniumActions.sleep(5000);
            return seleniumActions.getListOfElementsText(webDriver.findElements(By.xpath("//tbody/tr[contains(@class,'dx-data-row')]/td[@role='gridcell'][not(@aria-colindex='1')]")));
    }

    public List<String> selectedDisallowedDepartmentList() {
        return seleniumActions.getListOfElementsText(selectedDisallowedDepartmentText);
    }

    public void selectSubDepartment() {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(subDepartmentPlusButton);
    }

    public void selectMainDepartment() {
        seleniumActions.clickOnElement(mainDepartmentPlusButton);
    }

    public String mainDepartment() {
        return seleniumActions.getTextMessage(mainDepartmentCollection);
    }

    public void selectPreSelectionGroup() {
        seleniumActions.moveToElementAndClick(preSelectionPlusButton);
    }

    public String preSelectionGroup() {
        return seleniumActions.getTextMessage(setDepartmentsCollection);
    }

    public String limitAmountText() {
        commonPage.waitForDomReady();
        return seleniumActions.getTextMessage(getLimitAmountText);
    }

    public void enterLimitAmount(String text) {
        seleniumActions.clearValue(limitInputField);
        seleniumActions.typeValue(limitInputField, text);
    }

    public void clickCrossButtonOfName(String name) {

        String cross = "//div[normalize-space()='" + name + "']/ancestor::otalio-guest-search-card/descendant::button/span/icon/*[name()='svg']/*[name()='use']";
        try {
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(cross))));
        } catch (StaleElementReferenceException ste){
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(cross))));

        }

    }

    public String getSelectedOperationCheckBoxName() {
        return seleniumActions.getTextMessage(getSelectedCheckBox);
    }

    public String getOperationLog() {
//        System.out.println(webDriver.findElement(By.xpath("(//tbody/tr/td[@role='gridcell' and @aria-colindex='1']//a)[1]")));
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("(//tbody/tr/td[@role='gridcell' and @aria-colindex='1']//a)[1]")));
    }

    public String getFinanceStatusLog() {
        System.out.println(webDriver.findElement(By.xpath("(//tbody/tr/td[@role='gridcell' and @aria-colindex='7']/descendant::otalio-chip/div)[1]")));
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("(//tbody/tr/td[@role='gridcell' and @aria-colindex='7']/descendant::otalio-chip/div)[1]")));
    }

    public String getErrorCount() {
        System.out.println(webDriver.findElement(By.xpath("(//tbody/tr/td[@role='gridcell' and @aria-colindex='4']//a)[1]")));
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("(//tbody/tr/td[@role='gridcell' and @aria-colindex='4']//a)[1]")));
    }

    public String getSuccessCount() {
         seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("(//tbody/tr/td[@role='gridcell' and @aria-colindex='3']//a)[1]")));
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("(//tbody/tr/td[@role='gridcell' and @aria-colindex='3']//a)[1]")));
    }

    public String setFinanceStatusLog() {
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//tbody/tr/td[@role='gridcell' and @aria-colindex='7']/descendant::otalio-chip/div")));
    }

    public String setErrorCount() {
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//tbody/tr/td[@role='gridcell' and @aria-colindex='4']//a")));
    }

    public String setSuccessCount() {
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("//tbody/tr/td[@role='gridcell' and @aria-colindex='3']//a")));
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//tbody/tr/td[@role='gridcell' and @aria-colindex='3']//a")));
    }

    public void addPostingCreditComment(String text) {
        seleniumActions.typeValue(addPostingCommentInputField, text);
    }

    public void onboardCreditComment(String text) {
        seleniumActions.typeValue(onboardCreditCommentInputField, text);
    }

    public void creditLimitComment(String text) {
        seleniumActions.typeValue(creditLimitCommentInputField, text);
    }

    public void enterRoutingComment(String text) {
        seleniumActions.typeValue(routingCommentInputField, text);
    }

    public void selectRouteToFolio(String option) {
        seleniumWaits.untilElementIsVisible(routeToFolioDropdown);
        seleniumActions.clickOnElement(routeToFolioDropdown);
        String selectSubDepartments = "//span[normalize-space()='" + option + "']";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(selectSubDepartments)));
    }

    public void clickPayerAccount() {
        seleniumActions.clickOnElement(payerAccountIcon);
    }

    public void enterDiscountTemplatesComment(String text) {
        seleniumActions.typeValue(discountTemplatesCommentInputField, text);
    }

    public void enterDisallowedDepartmentsComment(String text) {
        seleniumActions.typeValue(disallowedDepartmentsCommentInputField, text);
    }

    public void selectPreSelectionGroups(String add) {
        seleniumActions.clickOnElement(listDisallowedDepartmentsDropdown);
        String addPreSelectionGroups = "//div[normalize-space()='" + add + "']/button[contains(@id,'selected-disallowed-departments')]/span/icon/*[name()='svg']/*[name()='use']";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(addPreSelectionGroups)));
    }

    public void enterPaymentComment(String text) {
        seleniumActions.typeValue(paymentCommentInputField, text);
    }

    public void enterAmount(String amount) {
        seleniumActions.typeValue(amountInputField, amount);
    }

    public void selectSubDepartments(String option) {
        seleniumActions.sleep(3000);
        seleniumActions.moveToElementAndClick(subDepartmentsDropDown);
        String selectSubDepartments = "//span[normalize-space()='" + option + "']";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(selectSubDepartments)));
    }

    public void enterComment(String text) {
        seleniumActions.typeValue(commentInputField, text);
    }

    public void creditLimitTemplate(String option) {
        seleniumActions.moveToElementAndClick(creditLimitTemplateDropDown);
        String selectEnableOrDisableDropdown = "//span[normalize-space()='" + option + "']";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(selectEnableOrDisableDropdown)));
    }


    public void selectEnableOrDisableAccountPostings(String option) {
        seleniumActions.moveToElementAndClick(enableOrDisableAccountPostingsDropDown);
        String selectEnableOrDisableDropdown = "//span[normalize-space()='" + option + "']";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(selectEnableOrDisableDropdown)));
    }

    public void bulkOperationForGuest() {
//        seleniumActions.clickOnElement(threeDotGuestUser);
//        seleniumActions.jsClick(threeDotGuestUser);

        seleniumActions.moveToElementAndClick(threeDotGuestUser);
        seleniumWaits.untilElementIsVisible(bulkGuestButton);
        seleniumActions.moveToElementAndClick(bulkGuestButton);
    }

    public List<String> getStatusListOfAll() {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        seleniumActions.sleep(5000);
        seleniumActions.areElementsDisplayed(statusList);
        System.out.println("failed to post" + statusList);
        return statusList.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public WebElement getStatusElement(){
        seleniumActions.sleep(3000);
        return statusList.get(0);
    }

    public int isFolioDetailsRowPresent() {
        System.out.println(folioDetailsRow);
        return folioDetailsRow.size();
    }

    public String serviceChargeExemptedToggleButtonStatus() {
        seleniumWaits.untilElementIsVisible(serviceChargeExemptedToggleButton);
        return serviceChargeExemptedToggleButton.getAttribute("aria-checked");
    }

    public String checkToggleButtonStatus() {
        seleniumWaits.untilElementIsVisible(toggleButtonStatus);
        return toggleButtonStatus.getAttribute("aria-checked");
    }

    public String checkExcludeToggleButton() {
        seleniumWaits.untilElementIsVisible(excludeToggleButton);
        return excludeToggleButton.getAttribute("aria-checked");
    }

    public String checkPaperlessToggleButtonStatus() {
        seleniumWaits.untilElementIsVisible(paperlessToggleButton);
        return paperlessToggleButton.getAttribute("aria-checked");
    }

    public void deleteCreditLimit() {
        seleniumWaits.untilElementIsVisible(creditLimitDeleteIcon);
        seleniumActions.moveToElementAndClick(creditLimitDeleteIcon);
    }


    public void clickOnTabAndClick(String tab, String button1) {
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("//div[contains(text(),'" + tab + "')]")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[contains(text(),'" + tab + "')]")));

        try{
            seleniumWaits.untilElementIsPresent(By.xpath("(//button[@id='credit-limits-delete-credit-limit-button']/span/icon/*[name()='svg']/*[name()='use'])[1]"),5);
            seleniumActions.jsClick(creditLimitDeleteIcon);
            String delete = "//span[normalize-space()='Delete']";
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(delete)));
        }catch (Exception e){
            String text = "//span[text()='" + button1 + "' or normalize-space()='" + button1 + "']";
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(text)));
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(text)));
        }
    }

    public void clickOnTabAndClickAddRouting(String tab, String button2) {
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[contains(text(),'" + tab + "')]")));
        List<WebElement> deleteIcons = webDriver.findElements(By.xpath("(//button[contains(@id,'delete')]/span/icon/*[name()='svg']/*[name()='use'])[1]"));
        if (!deleteIcons.isEmpty()) {
            try {
                seleniumActions.clickOnElement(routingDeleteIcon);
            } catch (StaleElementReferenceException ste) {
                seleniumActions.clickOnElement(routingDeleteIcon);
            }

            String deleteXpath = "//span[normalize-space()='Delete']";
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(deleteXpath)));
            seleniumActions.sleep(1000); // Optional wait after deletion
        }

        String buttonXpath = "//span[text()='" + button2 + "' or normalize-space()='" + button2 + "']";
        seleniumActions.sleep(3000);
        seleniumActions.doubleClickOnElement(webDriver.findElement(By.xpath(buttonXpath)));
        if (webDriver.findElements(By.xpath(buttonXpath)).size() > 0) {
            seleniumActions.clickOnElement(By.xpath(buttonXpath));
        }
    }

    public void clickOnTabAndClickAddDisallowed(String tab, String button2) {
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("//div[contains(text(),'" + tab + "')]")));
        if (webDriver.findElements(By.cssSelector("[identifier='disallowed-departments']")).size() > 0) {
            String delete = "//button[@id='disallowed-department-delete-button']";
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(delete)));
            String popUpDelete = "//button[@id='delete-confirmation-dialog-delete-button']";
            seleniumActions.sleep(3000);
//            seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath(popUpDelete)));
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(popUpDelete)));
        }
            String text = "//span[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = translate('"+button2+"', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') or translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = translate('"+button2+"', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')]";
            seleniumActions.sleep(3000);
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(text)));
    }


    public int getColumnIndexByName(String columnName) {

        for (int i = 0; i < columnHeaders.size(); i++) {

            String headerText = columnHeaders.get(i).getText().trim();

            if (headerText.equalsIgnoreCase(columnName)) {
                return i + 1;
            }
        }

        throw new RuntimeException("Column '" + columnName + "' was not found.");
    }

    public List<WebElement> getColumnCells(String columnName) {

        int columnIndex = getColumnIndexByName(columnName);

        String xpath = "//td[@aria-colindex='" + columnIndex
                + "' and not(@role='columnheader')]";

        List<WebElement> columnCells = webDriver.findElements(By.xpath(xpath));

        for (WebElement cell : columnCells) {
            seleniumActions.highLighterMethod(cell);
        }

        return columnCells;
    }

    public boolean isCategoryDropdownDisplayed() {
        seleniumActions.highLighterMethod(categoryDropdown.get(0));
        return !categoryDropdown.isEmpty() && categoryDropdown.get(0).isDisplayed();
    }

    public boolean isChecksGreenTickPresent() {
        seleniumActions.highLighterMethod(checksGreenTickIcon.get(0));
        return !checksGreenTickIcon.isEmpty() && checksGreenTickIcon.get(0).isDisplayed();
    }

    public int getClassificationCount() {
        return classificationRows.size();
    }
    public void deleteLastClassification() {
        seleniumActions.scrollInToElement(lastDeleteClassificationIcon);
        seleniumActions.jsClick(lastDeleteClassificationIcon);
    }
    public void scrollClassificationRows(){
        seleniumActions.scrollInToElement(classificationRows.get(0));
    }
    private String filterName(String filterName) {
        return "//div[contains(text(),'" + filterName + "')]/following-sibling::span/../../following-sibling::icon/*[name()='svg']";
    }
    private String status(String status) {
        return "//span[normalize-space()='" + status + "']/..//preceding-sibling::mat-pseudo-checkbox";
    }
    public void userSelectFilterAndSearch(String filterName, String searchText){
        seleniumActions.clickOnElement(By.xpath(filterName(filterName)));
        seleniumActions.sendKeys(packageFilterSearchBox,searchText);
    }

    public void selectThePackageCheckboxes(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        if (!packageCheckboxes.isEmpty()) {
            packageCheckboxes.get(0).click();
        }
    }

    public int getColumnIndex(String columnName) {

        for (int i = 0; i < columnHeadersText.size(); i++) {

            String headerText = columnHeadersText.get(i).getText().trim();

            if (headerText.equalsIgnoreCase(columnName)) {
                return i + 1;
            }
        }

        throw new RuntimeException("Column '" + columnName + "' was not found.");
    }

    public List<WebElement> validateTheColumnText(String columnName) {
        int columnIndex = getColumnIndex(columnName);
        System.out.println(columnIndex);
        String xpath = "//td[@aria-describedby='dx-col-" + columnIndex + "' and not(@role='columnheader')]";
        List<WebElement> columnCells = webDriver.findElements(By.xpath(xpath));
        for (WebElement cell : columnCells) {
            seleniumActions.highLighterMethod(cell);
        }
        return columnCells;
    }

}
