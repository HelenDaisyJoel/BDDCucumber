package com.nclh.pageObjects;

import com.nclh.constants.PointOfSaleConstants;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.nclh.utilities.SeleniumActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PointOfSalePage {
    private final WebDriver webDriver;
    private final SeleniumActions seleniumActions;
    private final SeleniumWaits seleniumWaits;
    private final ShipSecurityPage shipSecurityPage;
    private final CommonPage commonPage;
    private final CommonFunctions commonFunctions;

    public PointOfSalePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        seleniumActions = new SeleniumActions(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        shipSecurityPage = new ShipSecurityPage(webDriver);
        commonPage = new CommonPage(webDriver);
        commonFunctions = new CommonFunctions();
    }

    @FindBy(xpath = "//div[normalize-space()='Adult']/../../descendant::icon[@icon='pos-management']/following-sibling::span")
    private List<WebElement> userFundValue;

    @FindBy(xpath = "//span[normalize-space()='CREATE CHECK']/../../../../../descendant::otalio-passenger-picture/following-sibling::div/div")
    private WebElement guestFullName;

    @FindBy(tagName = "otalio-passenger-picture")
    private List<WebElement> checksOnHomePage;

    @FindBy(css = "[icon='back-pos']")
    private WebElement posBackButton;

    @FindBy(xpath = "(//div[contains(@class,'thumbnail rounded-circle')]/../following-sibling::div)")
    private List<WebElement> subItemsToPurchase;

    @FindBy(css = "otalio-check-list-footer-total > div > h4 + h4")
    private WebElement totalPrice;

    @FindBy(css = "[icon='user-photo']")
    private WebElement userPhoto;

    @FindBy(css = "[icon='logout-pos']")
    private WebElement logoutPOS;


    @FindBy(css = "#profile-check-search-button")
    private WebElement profileDetailsMenu;

    @FindBy(xpath = "//table/tbody/tr/td[@role='columnheader']/div[contains(@class,'text-content')]")
    private List<WebElement> columnHeaderData;

    @FindBy(css = "[data-placeholder*='Search']")
    private WebElement searchBox;

    @FindBy(xpath = "//span[normalize-space()='Save']")
    private WebElement saveButton;

    @FindBy(css = "[placeholder='Revenue Center'] > mat-select[class^='mat-select']")
    private WebElement revenueCenterDropDown;

    @FindBy(xpath = "(//otalio-reservation-person-name)[1]")
    private WebElement guestNames;

    @FindBy(css = "[icon='edit-filled']")
    private WebElement editIcon;

    @FindBy(xpath = "//span[normalize-space()='VOID CHECK']")
    private WebElement voidCheckButton;

    @FindBy(xpath = "//td[@aria-colindex='6']")
    private List<WebElement> amountsList;

    @FindBy(css = "span > icon[icon='crown']")
    private WebElement crownIcon;

    @FindBy(xpath = "//span[normalize-space()='ADD BUYER']")
    private WebElement addBuyerButton;

    @FindBy(xpath = "(//otalio-passenger-picture/following-sibling::div/div[contains(@class,'body')])")
    private List<WebElement> userNames;

    @FindBy(xpath = "//a[contains(@id,'check-search-check')][1]")
    private WebElement firstCheck;

    @FindBy(xpath = "//otalio-transaction-article-list/descendant::span")
    private List<WebElement> productItem;

    @FindBy(xpath = "//div[text()='Enter Final Quantity']")
    private WebElement enterFinalQuantityPopup;

    @FindBy(css = "[formcontrolname='comment']")
    private WebElement messageArea;

    @FindBy(css = "[icon='comment-circle']")
    private WebElement orangeCircle;

    @FindBy(css = "[alt='signature']")
    private WebElement printReceipt;

    @FindBy(css = "[formcontrolname='number']")
    private WebElement quantityInputBox;

    @FindBy(css = "span [icon*='close']")
    private WebElement closeButton;

    @FindBy(xpath = "//div[text()='ADD']")
    private WebElement addButton;

    @FindBy(xpath = "(//div[@id='toast-message-content'])[2]")
    private WebElement toastMessage;

    @FindBy(xpath = "//div[normalize-space()='Adult']/../../descendant::icon/following-sibling::span[not(text()='0.00')]/ancestor::otalio-guest-search-person-attributes/../descendant::icon[contains(@icon,'plus')]")
    private List<WebElement> plusButtons;

    @FindBy(xpath = "//span[normalize-space()='CREATE CHECK']/../../../../../descendant::otalio-passenger-picture/following-sibling::div/div[contains(@class,'tooltip')]")
    private List<WebElement> guestNamesList;

    @FindBy(xpath = "//h4[text()='Choose Buyer / Credit Card']")
    private List<WebElement> chooseBuyerHeading;

    @FindBy(xpath = "//otalio-alternative-payer-dialog-container/descendant::otalio-passenger-picture")
    private WebElement alternateBuyer;

    @FindBy(xpath = "//span[normalize-space()='Confirm']")
    private WebElement confirmButton;

    @FindBy(xpath = "//span[normalize-space()='SPLIT BY CONSUMER']")
    private WebElement splitByConsumerButton;

    @FindBy(xpath = "//otalio-reservation-person-name")
    private List<WebElement> guestNamesAtCheckPage;

    @FindBy(css = "[routerlink*='profile/rvc']")
    private WebElement rvcProfileLink;

    @FindBy(xpath = "(//button[contains(@id,'table-manager')]/span/div)[1]")
    private WebElement tableButton;

    @FindBy(xpath = "(//div[contains(text(),'Guests')]/../../div/div)[1]")
    private WebElement tableAndCheckData;

    @FindBy(css = "#transaction-more-button span.mat-button-wrapper")
    private WebElement moreThreeDotButton;

    @FindBy(xpath = "//span[text()='Reorder']")
    private WebElement reOrderButton;

    @FindBy(xpath = "//otalio-transaction-article-list/child::div")
    private List<WebElement> productsList;

    @FindBy(css = "[class*='cursor-disallowed']")
    private WebElement disabledProduct;

    @FindBy(css = "span[class*='select-value']")
    private WebElement barPromoDropDown;

    @FindBy(css = "[icon='logout-pos']")
    private WebElement logoutIcon;

    @FindBy(xpath = "//div[contains(text(),'Enter tips')]")
    private WebElement enterTipsButton;

    @FindBy(css = "button[id*='checkStatus'] > icon")
    private WebElement checkStatusDropDown;

    @FindBy(css = "[class*='pseudo-checkbox-checked']")
    private List<WebElement> checkBoxes;

    @FindBy(css = "[aria-label='Items per page: 100']")
    private WebElement perPageLink100;

    @FindAll({
            @FindBy(css = "[placeholder^='Search']"),
            @FindBy(css = "input.complex-search-input")
    })
    private WebElement crewSearchBox;

    @FindBy(xpath = "//span[normalize-space()='REPRINT']")
    private WebElement rePrintButton;

    @FindBy(xpath = "//div[normalize-space(text())='SUCCESS !']")
    private WebElement successMessage;

    @FindBy(css = "[icon='tips']")
    private WebElement tipsIcon;

    @FindBy(css = "[id*='check-button'][disabled='true']")
    private WebElement checkButton;

    @FindBy(css = "[routerlink='/profile/rvc-and-waiter-station-settings']")
    private WebElement rvcCenterText;

    @FindBy(css = "[placeholder=\"Email\"]")
    private WebElement emailInputBox;

    @FindBy(css = "[placeholder=\"Password\"]")
    private WebElement passwordInputBox;

    @FindBy(css = "#login-login-button")
    private WebElement loginButton;

    @FindBy(css = "button[id*='confirm-button']")
    private WebElement commentSaveButton;

    @FindBy(css = "[id*='check-search-check']")
    private List<WebElement> latestCheck;

    @FindBy(css = "[icon=\"arrow-down\"]")
    private WebElement reservationDropdown;

    @FindBy(xpath = "//span[text()='Clear previous selection']")
    private WebElement clearPreviousSelection;

    @FindBy(xpath = "//div[text()='Reservation Status:']/../descendant::div[text()][contains(@class,'text-truncate')]")
    private WebElement reservationStatus;

    @FindBy(css = "button[id*='reservationStatus'] > icon")
    private WebElement reservationStatusList;

    @FindBy(xpath = "//mat-tab-header//div[@role='tab'][not(contains(@id,'mat-tab-label-3-0'))]/div")
    private List<WebElement> logList;

    @FindBy(xpath = "//tr[contains(@class,'row-lines')]/td/div/span/a[not(contains(@id,'article-group'))]")
    private List<WebElement> articleCodeList;

    @FindBy(xpath = "//tr[contains(@class,'row-lines')]/td[@aria-colindex='3']/div/span")
    private List<WebElement> descriptionList;

    @FindBy(xpath = "//tr[contains(@class,'row-lines')]/td/div/otalio-chip/div/div")
    private List<WebElement> barCodeList;

    @FindBy(xpath = "//input[@id='user-type-editor-enabled-mat-slide-toggle-input']")
    private WebElement toggleEnabledButtonStatus;

    @FindBy(css = "[placeholder='Code']")
    private WebElement codeInputField;

    @FindBy(xpath = "//tr[contains(@class,'row-lines')]/td/div/descendant::otalio-chip/div/div")
    private List<WebElement> barCodeListInArticlesSetup;

    @FindBy(css = "[id='complex-filter-multi-search-in-options-list-input']")
    private WebElement articleCheckBoxInputField;

    @FindBy(xpath = "//div[text()='Article Categories']")
    private WebElement articleCategories;

    @FindBy(xpath = "//div[contains(@id,'transaction-article')]/div/div[contains(@class,'text')]")
    private WebElement getArticleName;

    @FindBy(css = "[id='article-setup-editor-code-input']")
    private WebElement articleCodeInputField;

    @FindBy(css = "[id='description-en-input']")
    private WebElement articleDescriptionInputField;

    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-articleGroup-field']/descendant::div/div/div[contains(@class,'mat-select-arrow')]")
    private WebElement articleGroupDropdown;

    @FindBy(css = "[role='option'] > span")
    private List<WebElement> optionsDropDown;

    @FindBy(xpath = "//input[@id='article-setup-editor-code-input']")
    WebElement articleCodeText;

    @FindBy(css = "[id='description-en-input']")
    private WebElement articleDescriptionText;

    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-articleGroup-field']/descendant::span/span")
    private WebElement articleGroupText;

    @FindBy(xpath = "//td[@role='columnheader']")
    private List<WebElement> columnHeader;

    @FindBy(xpath = "//mat-panel-title/span[normalize-space()='Revenue Center Categories']")
    private WebElement revenueCenterCategories;

    @FindBy(xpath = "//div[normalize-space()='Category - Bar']/button/span/icon")
    private WebElement categoriesBar;

    @FindBy(xpath = "//tr/td/div/span/a[contains(@class,'mat-tooltip-trigger')]")
    private WebElement getCodeTextRow;

    @FindBy(xpath = "//tr/td/div/span/a[not(contains(@class,'mat-tooltip-trigger'))]")
    private WebElement getArticleGroupTextRow;

    @FindBy(xpath = "//tr/td/div/span[contains(@class,'multiline-ellipsis')]")
    private WebElement getDescriptionTextRow;

    @FindBy(xpath = "//button[@id='dashboard-price-lookup']/span[@class='mat-button-wrapper']")
    private WebElement getPriceLookUpText;

    @FindBy(xpath = "//otalio-chip/../div/div[contains(@class,'text-truncate')]")
    private WebElement getRVCText;

    @FindBy(xpath = "//otalio-chip/../div/div[not(contains(@class,'text-truncate'))]")
    private WebElement getWaiterStationText;

    @FindBy(xpath = "//a[contains(@id,'article-class-list')]")
    private List<WebElement> articleClassCodeList;

    @FindBy(xpath = "//mat-list-item/descendant::div/div[normalize-space()='Available: Priced Article / Selected: Open Price']")
    private WebElement parameterOpenPrice;

    @FindBy(xpath = "//otalio-guest-profile-container/descendant::icon[@icon='close-16']")
    private WebElement closeIconGuestProfile;

    @FindBy(css = "[id='complex-filter-collapse-expand-chip']")
    private WebElement expandFilter;

    @FindBy(xpath = "//div[normalize-space()='Checks Menu'][contains(@class,'items-center')]")
    private WebElement headerName;

    @FindBy(xpath = "//div[@class='mat-tab-body-wrapper']//otalio-transaction-article-select-cards")
    private WebElement rvcArticles;

    @FindBy(xpath = "(//div[@role='listbox']/mat-option)[1]")
    private WebElement regularProCode;

    @FindBy(xpath = "//div[text()='Condiments']")
    private WebElement condimentsText;

    @FindBy(xpath = "//div[contains(text(),'Total Revenue')]/following-sibling::div")
    private WebElement totalRVCRevenueText;

    @FindBy(xpath = "//div[contains(text(),'Additional Gratuity Total')]/following-sibling::div")
    private WebElement totalAdditionalGratuityTotalText;

    @FindBy(xpath = "//div[contains(text(),'Opened Checks')]/following-sibling::div")
    private WebElement totalOpenCheckText;

    @FindBy(xpath = "//div[contains(text(),'Closed Checks')]/following-sibling::div")
    private WebElement totalClosedCheckText;

    @FindBy(xpath = "//otalio-user-name/span")
    private WebElement getGuestNameINOpenCheck;

    @FindBy(xpath = "(//*[@href='#table-pos'])[2]/ancestor::icon/following-sibling::div)")
    private WebElement getTableNumberINOpenCheck;

    @FindBy(css = "[id='transaction-more-button']")
    private WebElement transactionMoreButton;

    @FindBy(xpath = "(//div[@cdkdroplist])[5]")
    private WebElement seatNumber;

    @FindBy(xpath = "(//otalio-condiment-article-cards//div[contains(@id,'transaction-article')])[1]")
    private WebElement selectArticleModifier;

    @FindBy(xpath = "(//otalio-condiment-article-cards//div[contains(@id,'transaction-article')])[1]/descendant::div[contains(@class,'text-truncate')]")
    private WebElement selectedArticleModifierName;

    @FindBy(xpath = "//otalio-transaction-article-list/descendant::div[contains(@class,'d-flex justify-content-between pl-5')]/descendant::div[contains(@class,'ng-star-inserted')]/following-sibling::div")
    private WebElement selectedModifierName;

    @FindBy(xpath = "//otalio-transaction-article-select-container/descendant::div[contains(@class,'text-truncate')]")
    private List<WebElement> foodCategoryList;

    @FindBy(xpath = "//otalio-transaction-article-list/descendant::div[contains(@class,'mat-body-1')][not(contains(@class,'align-items-center'))]")
    private WebElement favItemNameView;

    @FindBy(xpath = "//a[contains(@id,'pos-article-list')]")
    private List<WebElement> articleList;

    public boolean getArticleList(){
        return seleniumActions.areElementsDisplayed(articleList);
    }

    public String getFavItemName(){
        return seleniumActions.getTextMessage(favItemNameView);
    }

    public List<String> getFoodCategoryListName(){
        return seleniumActions.getListOfElementsText(foodCategoryList);
    }

    public String getSelectedModifierName(){
        return seleniumActions.getTextMessage(selectedModifierName);
    }

    public String getSelectedArticleModifierName(){
        return seleniumActions.getTextMessage(selectedArticleModifierName);
    }

    public void selectArticleModifierLiquor(){
        seleniumActions.clickOnElement(selectArticleModifier);
    }


    public void dragGuestNameToTheTable(String name){
        String guestName = "//span[text()='" + name + "' or normalize-space()='" + name + "']";
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath(String.format(guestName, name))));
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(guestName, name))));
        seleniumActions.dragAndDrop(
                webDriver.findElement(By.xpath(String.format(guestName, name))),
                seatNumber
        );
        seleniumActions.sleep(5000);

    }

    public void dragGuestNameToTheTable(){
//        String guestName = "//otalio-guest-item[contains(@class,'cdk-drag seat-holder ng-star-inserted')]";
//        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath(String.format(guestName))));
//        seleniumActions.sleep(3000);
//        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(guestName))));
//        seleniumActions.dragAndDrop(
//                webDriver.findElement(By.xpath(String.format(guestName))),
//                seatNumber
//        );
//        seleniumActions.sleep(5000);
        WebElement guest = webDriver.findElement(By.xpath("//otalio-guest-item[@cdkdrag]"));
        Actions actions = new Actions(webDriver);

        actions.clickAndHold(guest).perform();

        for (int i = 0; i < 15; i++) {
            actions.moveByOffset(10, 5).perform();
        }

        actions.moveToElement(seatNumber, 40, 40).perform();
        actions.release().perform();
    }


    public void selectTransactionMoreButton(String option){
        seleniumActions.clickOnElement(transactionMoreButton);
        String transactionOptions = "//span[text()='" + option + "' or normalize-space()='" + option + "']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(transactionOptions)));
    }

    public boolean tableNumberINOpenCheck(){
        return seleniumActions.isElementDisplayed(getTableNumberINOpenCheck);
    }

    public String guestNameInOpenCheck(){
        return seleniumActions.getTextMessage(getGuestNameINOpenCheck);
    }

    public boolean getTotalClosedCheckText() {
        return seleniumActions.isElementDisplayed(totalClosedCheckText);
    }

    public boolean getTotalOpenCheckText(){
        return seleniumActions.isElementDisplayed(totalOpenCheckText);
    }

    public boolean getTotalAdditionalGratuityTotalText(){
        return seleniumActions.isElementDisplayed(totalAdditionalGratuityTotalText);
    }

    public boolean getTotalRVCRevenueText(){
        return seleniumActions.isElementDisplayed(totalRVCRevenueText);
    }

    public boolean getCondiments(){
        return seleniumActions.isElementDisplayed(condimentsText);
    }

    public boolean getRegularProCode(){
        return seleniumActions.isElementDisplayed(regularProCode);
    }

    public boolean getRVCArticles(){
        return seleniumActions.isElementDisplayed(rvcArticles);
    }

    public void clickLogoutPOS(){
        try {
            seleniumActions.moveToElementAndClickOnElement(logoutPOS, logoutPOS);
        }catch (NoSuchElementException ne){
            seleniumActions.jsClick(webDriver.findElement(By.cssSelector("[href='#logout-pos']")));
        }

    }

    public void clickUserProfile(){
        try {
            seleniumActions.moveToElementAndClickOnElement(userPhoto, userPhoto);
        }catch (NoSuchElementException ne){
            seleniumActions.jsClick(webDriver.findElement(By.cssSelector("[href='#user-photo']")));
        }
    }

    public void selectCheckStatusFilter(String filterName,String status){
        seleniumActions.clickOnElement(expandFilter);
        String selectFilter = "//div[contains(text(),'"+filterName+"')]/ancestor::button/descendant::icon/*[name()='svg']";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(selectFilter))));
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(selectFilter))));
        String checkboxStatus = "//div[normalize-space()='%s']/preceding-sibling::mat-pseudo-checkbox/ancestor::mat-list-option";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(checkboxStatus, status))));
        WebElement checkbox = webDriver.findElement(By.xpath(String.format(checkboxStatus, status)));
        if (checkbox.getAttribute("aria-selected").equals("false")) {
            seleniumActions.clickOnElement(checkbox);
        }
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(selectFilter))));
        seleniumActions.highLighterMethod(webDriver.findElement(By.xpath("//div[contains(text(),'Check Status')]/ancestor::button/descendant::otalio-chip/div/div")));
    }

    public String preventAlcoholOrderToggleButtonStatus(String toggleName) {
        String toggleType = "//span[contains(text(),'%s')]/../span/input";
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))));
        return webDriver.findElement(By.xpath(String.format(toggleType,toggleName))).getAttribute("aria-checked");
    }

    public void closeGuestProfile(){
        seleniumActions.clickOnElement(closeIconGuestProfile);
    }

    public boolean checkParameterOpenPrice(){
        return seleniumActions.isElementDisplayed(parameterOpenPrice);
    }

    public void clickArticleClassCode(){
        seleniumActions.clickOnElement(articleClassCodeList.get(0));
    }

    public String waiterStation(){
        return seleniumActions.getTextMessage(getWaiterStationText);
    }

    public String rVCText(){
        return seleniumActions.getTextMessage(getRVCText);
    }

    public String priceLookUpText(){
        return seleniumActions.getTextMessage(getPriceLookUpText).toLowerCase();
    }

    public void guestProfileImage(){
        seleniumActions.clickOnElement(userPhoto);
    }

    public String descriptionTextRow(){
        return seleniumActions.getTextMessage(getDescriptionTextRow);
    }

    public String articleGroupTextRow(){
        return seleniumActions.getTextMessage(getArticleGroupTextRow);
    }

    public String codeTextRow(){
        return seleniumActions.getTextMessage(getCodeTextRow);
    }

    public String getArticleGroupText(){
        return seleniumActions.getTextMessage(articleGroupText);
    }

    public WebElement getArticleDescription(){
        return articleDescriptionText;
    }

    public WebElement getArticleCode(){
        return articleCodeText;
    }

    public void enterArticleCreationDetails(){
        seleniumActions.typeValue(articleCodeInputField, PointOfSaleConstants.CODE+String.valueOf(commonFunctions.getRandomNumber(1000,9999)));
        seleniumActions.typeValue(articleDescriptionInputField,PointOfSaleConstants.DESCRIPTION+String.valueOf(commonFunctions.getRandomNumber(1000,9999)));
        seleniumActions.clickOnElement(articleGroupDropdown);
        String searchField = "[placeholder='Search']";
        seleniumActions.sleep(3000);
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(searchField)),PointOfSaleConstants.CODE);
        seleniumActions.clickOnElement(optionsDropDown.get(0));
        seleniumActions.clickOnElement(revenueCenterCategories);
        seleniumActions.clickOnElement(categoriesBar);

    }

    public String getArticleNameText(){
        return seleniumActions.getTextMessage(getArticleName);
    }

    public void searchArticle(String userName){
        seleniumActions.typeValue(crewSearchBox, userName);
    }

    public void selectRVCDropDownValue(String filterName,String checkbox){
        seleniumActions.clickOnElement(expandFilter);
        String seleckFilter = "//div[contains(text(),'" + filterName + "')]/..//../following-sibling::icon";
        seleniumActions.jsClick(webDriver.findElement(By.xpath(String.format(seleckFilter))));
        seleniumActions.clickOnElement(clearPreviousSelection);
        String seleckCheckBox ="//span[normalize-space()='"+checkbox+"']/../preceding-sibling::mat-pseudo-checkbox";
        seleniumActions.typeValue(articleCheckBoxInputField,checkbox);
        seleniumActions.jsClick(webDriver.findElement(By.xpath(String.format(seleckCheckBox))));
    }

    public void selectDropDownValue(String filterName,String checkbox){
        String seleckFilter = "//div[contains(text(),'" + filterName + "')]/..//../following-sibling::icon";
        seleniumActions.jsClick(webDriver.findElement(By.xpath(String.format(seleckFilter))));
        String seleckCheckBox ="//span[normalize-space()='"+checkbox+"']/../preceding-sibling::mat-pseudo-checkbox";
        seleniumActions.typeValue(articleCheckBoxInputField,checkbox);
        seleniumActions.jsClick(webDriver.findElement(By.xpath(String.format(seleckCheckBox))));
        seleniumWaits.untilElementIsVisible(articleCategories);
        seleniumActions.clickOnElement(articleCategories);

    }

    public String getBarCodeInArticlesSetup(){
        seleniumActions.scrollInToElement(barCodeListInArticlesSetup.get(0));
        return seleniumActions.getTextMessage(barCodeListInArticlesSetup.get(0));
    }

    public WebElement getCodeTextWebElement(){
        return codeInputField;
    }

    public String checkToggleButtonStatus() {
        seleniumWaits.untilElementIsVisible(toggleEnabledButtonStatus);
        return toggleEnabledButtonStatus.getAttribute("aria-checked");
    }


    public void clickCode(){
        seleniumActions.clickOnElement(articleCodeList.get(0));
    }

    public String getDescription(){
        return seleniumActions.getTextMessage(descriptionList.get(0));
    }

    public String getBarCode(){
        return seleniumActions.getTextMessage(barCodeList.get(0));
    }

    public String getArticlesCode(){
        return seleniumActions.getTextMessage(articleCodeList.get(0));
    }

    public boolean getLogsList(){
        return seleniumActions.areElementsDisplayed(logList);
    }

    public void clickOnPurchaseItem(String name, String name1) {
        String text = "//div[text()='" + name + "' or normalize-space()='" + name + "' or text()='" + name1 + "' or normalize-space()='" + name1 + "']";
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath(text)), 5);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
    }

    public void clearReservation() {
        seleniumActions.clickOnElement(reservationDropdown);
        seleniumActions.clickOnElement(clearPreviousSelection);
    }

    public void clickOnYesOnPopup() {
        seleniumActions.sleep(3000);
        if (webDriver.findElements(By.xpath("//button/span[contains(text(),'Yes')]")).size() > 0) {
            seleniumActions.clickOnElement(By.xpath("//button/span[contains(text(),'Yes')]"));
        }
    }

    public void clickOnLatestCheckNumber() {
        seleniumActions.sleep(4000);
        seleniumWaits.untilElementIsClickable(latestCheck.get(0));
        seleniumActions.clickOnElement(latestCheck.get(0));
    }

    public void clickOnCommentSaveButton() {
        try {
            seleniumActions.clickOnElement(commentSaveButton);
        } catch (Exception e) {
            try {
                webDriver.findElement(By.xpath("//button[@id='confirm-button']")).click();
            } catch (Exception e1) {
            }
        }
    }

    public boolean isSaveButtonEnabled() {
        return seleniumActions.isElementEnabled(commentSaveButton);
    }

    public void accessWithCredentials(String un, String pwd) {
        seleniumActions.sleep(2000);
        if (webDriver.findElements(By.xpath("//div[text()='Access By Credentials']")).size() > 0) {
            seleniumActions.clickOnElement(By.xpath("//div[text()='Access By Credentials']"));
            seleniumActions.sleep(3000);
            seleniumActions.typeValue(emailInputBox, un);
            seleniumActions.typeValue(passwordInputBox, pwd);
            seleniumActions.clickOnElement(loginButton);
        }
    }

    public String getRVCCenter() {
        return seleniumActions.getTextMessage(rvcCenterText);
    }

    public boolean getCheckButtonStatus() {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return seleniumActions.isElementDisplayed(checkButton);
    }

    public void clickOnRePrintButton() {
        seleniumActions.clickOnElement(rePrintButton);
    }

    public boolean getSuccessMessage() {
        boolean flag = false;
        if (seleniumActions.isElementDisplayed(successMessage)) {
            flag = true;
        } else {
            clickOnRePrintButton();
            clickOnRePrintButton();
            if (seleniumActions.isElementDisplayed(successMessage)) {
                flag = true;
            }
        }
        return flag;
    }


    public void selectRVC(String revenueCenter) {
        clickOnRevenueCenter(revenueCenter);
    }

    public void clickOnRevenueCenter(String text) {
        seleniumActions.sleep(4000);
        WebElement element = webDriver.findElement(By.xpath("//span[@class='mat-option-text'][normalize-space()=\"" + text + "\"]"));
        element.click();
    }

    public void searchAndSelectRevenueCenter(String revenueCenter) {
        seleniumWaits.untilElementIsClickable(rvcProfileLink);
        seleniumActions.sleep(2000);
        seleniumActions.clickOnElement(rvcProfileLink);
        seleniumActions.clickOnElement(revenueCenterDropDown);
        seleniumActions.sleep(2000);
        if (webDriver.findElements(By.cssSelector("[placeholder^='Search']")).size() > 1) {
            seleniumActions.typeValue(webDriver.findElement(By.xpath("(//input[@placeholder=\"Search\"])[2]")), revenueCenter);
        } else {
            seleniumActions.typeValue(searchBox, revenueCenter);
        }

        seleniumActions.sleep(2000);
        try {
            selectRVC(revenueCenter);
        } catch (StaleElementReferenceException staleElementReferenceException) {
            WebElement element = webDriver.findElement(By.xpath("//span[@class='mat-option-text'][normalize-space()=\"" + revenueCenter + "\"]"));
            element.click();
        }
        seleniumActions.clickOnElement(saveButton);
    }

    public void openGuestProfile(String firstName, String guestLastName) {
        seleniumActions.sleep(1000);
        try {
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("//a[contains(text(),'" + firstName + "')]/../../../td[2]/div/a[contains(text(),'" + guestLastName + "')]")));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//a[contains(text(),'" + firstName + "')]/../../../td[2]/div/a[contains(text(),'" + guestLastName + "')]")));
        } catch (Exception e) {
            webDriver.findElement(By.xpath("//a[contains(text(),'" + firstName + "')]/../../../td[2]/div/a[contains(text(),'" + guestLastName + "')]")).click();
        }

    }

    public void openCrewProfile(String crewFirstName, String lastName) {
        try {
            seleniumActions.clickOnElement(reservationStatusList);
            seleniumActions.clickOnElement(By.xpath("//span[text()='Clear previous selection']"));
            seleniumActions.sleep(1000);
        }catch (Exception e){}
        seleniumActions.doubleClickOnElement(webDriver.findElement(By.xpath("//span[normalize-space()='Guests']")));
        seleniumActions.clearValue(crewSearchBox);
        seleniumActions.typeValue(crewSearchBox, crewFirstName);
        seleniumActions.sleep(5000);
        navigateToCrewInformation(lastName);
    }

    public void navigateToCrewInformation(String guestFirstName) {
        seleniumActions.sleep(1000);
        seleniumActions.clickOnElement(perPageLink100);
        seleniumActions.sleep(2000);
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//a[normalize-space()='" + guestFirstName + "'])[1]")));

        } catch (Exception ste) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//a[contains(text(),'" + guestFirstName + "')])[1]")));
        }
    }

    public void openGuestInformation(String firstName, String lastName) {
        seleniumActions.clearValue(crewSearchBox);
        seleniumActions.typeValue(crewSearchBox, firstName);
        openGuestProfile(firstName, lastName);
    }

    public void clickOnBackButton() {
        seleniumWaits.untilElementIsClickable(posBackButton);
        seleniumActions.clickOnElement(posBackButton);
    }

    public void selectCheckTypeInTheList(String checkType) {
        seleniumWaits.untilElementIsVisible(checkStatusDropDown);
        seleniumActions.clickOnElement(checkStatusDropDown);
        seleniumActions.clickOnElement(By.xpath("//span[text()='" + checkType + "']/../preceding-sibling::mat-pseudo-checkbox"));
        seleniumActions.clickOnElement(profileDetailsMenu);
    }

    public void clearCheckBoxes() {
        GuestServicePage guestServicePage = new GuestServicePage(webDriver);
        guestServicePage.openHiddenFilters();
        seleniumWaits.untilElementIsVisible(checkStatusDropDown);
        seleniumActions.clickOnElement(checkStatusDropDown);
        for (int i = 0; i < checkBoxes.size(); i++) {
            seleniumActions.sleep(2000);
            seleniumActions.clickOnElement(checkBoxes.get(i));
        }
        if (checkBoxes.size() > 0) {
            checkBoxes.get(0).click();
        }
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
        seleniumActions.clickOnElement(profileDetailsMenu);
        seleniumActions.moveToElementAndClick(profileDetailsMenu);
    }

    public void openTips() {
        seleniumWaits.untilElementIsClickable(tipsIcon);
        try {
            seleniumActions.clickOnElement(tipsIcon);
        } catch (Exception e) {
            seleniumActions.clickOnElement(By.cssSelector("[icon='tips']"));
        }
    }

    public void enterTipAmount(String tip) {
        seleniumActions.clickOnElement(enterTipsButton);
        seleniumActions.typeValue(quantityInputBox, tip);
        seleniumWaits.untilElementIsClickable(addButton);
        seleniumActions.clickOnElement(addButton);
    }

    public boolean isDisplayedCheckStatusIsClosed(String type, String status) {
        String xpath = "//div[contains(text(),'" + type + "')]/following-sibling::*/descendant::div[text()='" + status + "']";
        return seleniumActions.isElementDisplayed(webDriver.findElement(By.xpath(xpath)));
    }

    public void setStatus(String type) {
        By xpath = By.xpath("//div[contains(text(),'" + type + "')]/../../following-sibling::icon");
        seleniumActions.clickOnElement(xpath);

    }

    public void openAccountInfoDropDown() {
        seleniumActions.clickOnElement(By.xpath("//icon/following-sibling::div[text()='Account Statement']"));
    }

    public void logoutPOS() {
        seleniumActions.sleep(3000);
        // seleniumActions.clickOnElement(posBackButton);
//        seleniumWaits.untilElementIsClickable(userPhoto);
//        seleniumActions.clickOnElement(userPhoto);
        seleniumWaits.untilElementIsClickable(logoutIcon);
        seleniumActions.clickOnElement(logoutIcon);
        seleniumActions.sleep(10000);
    }

    public void addPriceToItem() {
        seleniumWaits.setImplicitWaitTime(webDriver,5);
        try {
            int number = seleniumActions.getNumberOfElements(webDriver.findElements(By.xpath("//div[text()='Please enter price for Article']")));
            if (number > 0) {
                enterQuantity("1");
            }
            writeAComment("AutomationTest");
        } catch (Exception e) {
        }
        seleniumWaits.keepBackImplicitWaitTime(webDriver);
    }

    public boolean isCheckPresent(String check) {
        webDriver.manage().window().setSize(new Dimension(1920, 768));
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript("document.body.style.zoom = '30%';");
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath("//span[contains(text(),'" + check + "')]")));
        return seleniumActions.isElementDisplayed(webDriver.findElement(By.xpath("//span[contains(text(),'" + check + "')]")));
    }

    public String getCheckNumber(String name) {
        return seleniumActions.getTextMessage(
                webDriver.findElement(By.xpath("//otalio-reservation-person-name/span[contains(text(),'" + name + "')]/ancestor::td/following-sibling::td/div/a"))
        );
    }

    public String getPriceOfTheItem(String product) {
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("(//div[contains(@id,'transaction-article')][contains(@class,'specials-card')]/descendant::span)[1]")));
        //return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//div[normalize-space()='" + product + "' or contains(text(),'Gin Martini')]/following-sibling::div/span")));
    }

    public void clickOnRegularDropdown() {
        seleniumActions.clickOnElement(barPromoDropDown);
    }

    public void selectBarPromo(String promoCode) {
        //seleniumActions.clickOnElement(posBackButton);
        seleniumActions.clickOnElement(barPromoDropDown);
        seleniumActions.clickOnElement(By.xpath("//span[text()='" + promoCode + "']"));
    }

    public boolean isProductDisAbled() {
        return seleniumActions.isElementDisplayed(disabledProduct);
    }

    public void reOrder() {
        seleniumActions.sleep(2000);
        seleniumActions.clickOnElement(moreThreeDotButton);
       // seleniumActions.clickOnElement(reOrderButton);
        /*if (webDriver.findElements(By.xpath("//span[text()='Reorder']")).size() > 0) {
            seleniumActions.clickOnElement(reOrderButton);
        }

        if (webDriver.findElements(By.xpath("//span[text()='Reorder']")).size() > 0) {
            seleniumActions.moveToElementAndClick(reOrderButton);
        }*/
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        if (webDriver.findElements(By.xpath("//span[text()='Reorder']")).size() > 0) {
            seleniumActions.jsClick(reOrderButton);
        }else {
            seleniumActions.jsClick(moreThreeDotButton);
            seleniumActions.sleep(2000);
            seleniumActions.jsClick(reOrderButton);
        }
        seleniumWaits.keepBackImplicitWaitTime(webDriver);
    }

    public int getNumberOfProductItemsInTheCart() {
        return seleniumActions.getNumberOfElements(productsList);
    }

    public String getTableAssociatedCheckData() {
        return seleniumActions.getTextMessage(tableAndCheckData);
    }

    public void clickOnTable() {
        seleniumActions.clickOnElement(tableButton);
    }

    public void selectLeftMenuAndClickOnProduct(String leftMenu, String product) {
        String leftMenuItem = "//div[normalize-space()='" + leftMenu + "'][contains(@class,'mat-tooltip-trigger')]";
        String productItem = "//div[normalize-space()='" + product + "']";

        seleniumActions.clickOnElement(By.xpath(String.format(leftMenuItem, leftMenu)));
        try {
            seleniumActions.sleep(3000);
            seleniumActions.clickOnElement(By.xpath(String.format(productItem, product)));
        } catch (StaleElementReferenceException ste) {
            webDriver.findElement(By.xpath(String.format(leftMenuItem, leftMenu))).click();
        }
    }

    public List<String> getGuestNamesAtCheckPage() {
        return seleniumActions.getListOfElementsText(guestNamesAtCheckPage);
    }

    public void splitCheckByConsumer() {
        seleniumActions.highLighterMethod(splitByConsumerButton);
        seleniumActions.clickOnElement(splitByConsumerButton);
    }

    public void clickOnGuest(String guest) {
        String guestName = "(//otalio-reservation-person-name/span[contains(text(),'" + guest + "')])[1]";

        try {
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(guestName, guest))));
            seleniumActions.clickOnElement(By.xpath(String.format(guestName, guest)));
        } catch (StaleElementReferenceException ste) {
            webDriver.findElement(By.xpath(String.format(guestName, guest))).click();
        }
    }

    public void chooseBuyerIfHavingLessFunds(String gift) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        selectGiftItem(gift);
        seleniumActions.sleep(2000);
        if (chooseBuyerHeading.size() > 0) {
            seleniumActions.clickOnElement(alternateBuyer);
            seleniumActions.clickOnElement(confirmButton);
        }
    }

    public List<String> getGuestNames() {
        return seleniumActions.getListOfElementsText(guestNamesList);
    }

    public void selectOneGuest() {
        seleniumActions.clickOnElement(plusButtons.get(0));
        seleniumActions.sleep(3000);
    }

    public void selectGuests() {
        seleniumActions.clickOnElement(plusButtons.get(0));
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsClickable(plusButtons.get(0));
        seleniumActions.clickOnElement(plusButtons.get(0));
    }

    public String getErrorToastMessage() {
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsVisible(toastMessage);
        return seleniumActions.getTextMessage(toastMessage);
    }

    public String getProductQuantity() {
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsVisible(productItem.get(0));
        return seleniumActions.getTextMessage(productItem.get(0));
    }

    public void enterQuantity(String quantity) {
        seleniumWaits.untilElementIsVisible(quantityInputBox);
        seleniumActions.typeValue(quantityInputBox, quantity);
        seleniumActions.sleep(2000);
        seleniumWaits.untilElementIsClickable(addButton);
        seleniumActions.clickOnElement(addButton);
    }

    public void clickOnSave() {
        seleniumActions.sleep(5000);
        seleniumWaits.untilElementIsClickable(saveButton);
        seleniumActions.clickOnElement(saveButton);
    }

    public String getPrintReceipt() {
        seleniumWaits.untilElementIsVisible(printReceipt);
        return commonFunctions.readToastMessage(printReceipt);
    }

    public void navigateToCommentPage() {
        seleniumActions.sleep(2000);
        seleniumWaits.untilElementIsClickable(productItem.get(0));
        seleniumActions.doubleClickOnElement(productItem.get(0));
    }

    public void writeAComment(String comment) {
        try {
            seleniumActions.clickOnElement(By.xpath("//*[contains(text(),'Comment')]"));
            seleniumWaits.untilElementIsVisible(messageArea, 5);
            seleniumActions.clearValue(messageArea);
            seleniumActions.sleep(1000);
            seleniumActions.typeValue(messageArea, comment);
            seleniumActions.sleep(1000);
//            seleniumActions.clickOnElement(commentSaveButton);
        } catch (Exception e) {
        }
    }

    public void clickOnSaveButton() {
        try {
            seleniumActions.clickOnElement(saveButton);
        } catch (StaleElementReferenceException e) {
            try {
                webDriver.findElement(By.xpath("//span[normalize-space()='Save']")).click();
            } catch (Exception e1) {
            }
        }
    }

    public boolean isOrangeCircleDisplayed() {
        return seleniumActions.isElementDisplayed(orangeCircle);
    }

    public boolean isEnterFinalQuantityPopupDisplayed() {
        return seleniumActions.isElementDisplayed(enterFinalQuantityPopup);
    }

    public int getNumberOfProductsInTheCart() {
        return seleniumActions.getNumberOfElements(productItem);
    }

    public void swipeOnCartProduct(String direction) {
        seleniumActions.sleep(2000);
        seleniumActions.swipeOnElement(productItem.get(0), productItem.get(1), direction);
        seleniumActions.sleep(2000);
       /* if (direction.equals("left")) {
            try {
                seleniumWaits.untilElementInVisible(productItem.get(0));
            } catch (Exception e) {
                System.out.println("Element not visible");
            }
        }*/
    }

    public void selectAnotherUserIfGotError() {
        //if(webDriver.findElements(By.xpath("//div[@id='toast-title-error']/../div[contains(@class,'text')]/div")).size()>0){
        seleniumActions.clearValue(shipSecurityPage.getSearchInputBox());
        seleniumActions.typeValue(shipSecurityPage.getSearchInputBox(), String.valueOf(commonFunctions.getRandomCharacter()));

        try {
            //seleniumActions.clickOnElement(userFundValue.get(0));
            seleniumActions.clickOnElement(plusButtons.get(2));
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(By.xpath("//div[normalize-space()='Adult']/../../descendant::icon/following-sibling::span[not(text()='0.00')]/ancestor::otalio-guest-search-person-attributes/../descendant::icon[contains(@icon,'plus')]"));
        } catch (Exception e) {
            seleniumActions.clickOnElement(By.xpath("(//div[normalize-space()='Adult'])[2]"));
        }
        seleniumActions.sleep(3000);
    }

    public boolean addBuyersButtonIsEnabled() {
        String text = "//span[text()='ADD BUYERS' or normalize-space()='ADD BUYERS']";
        return webDriver.findElement(By.xpath(text)).isEnabled();
    }

    public void clickOnAddBuyersButton() {
        String text = "//span[text()='ADD BUYERS' or normalize-space()='ADD BUYERS']";
        seleniumActions.clickOnElement(By.xpath(text));
    }

    public void navigateToLatestCheck() {
        try {
            seleniumActions.clickOnElement(firstCheck);
        } catch (Exception e) {
            seleniumActions.clickOnElement(By.xpath("//a[contains(@id,'check-search-check')][1]"));
        }
    }

    public void selectGiftItem(String name) {
        seleniumActions.sleep(1000);
        String giftMenu1 = "//div/div/div[normalize-space()='%s' and contains(@class,'mat-tooltip')]";
        String giftMenu = "//div/div/div[contains(normalize-space(),'%s') and contains(@class,'mat-tooltip') or contains(text(),'LIQUOR CB')]";
        try {
            seleniumActions.clickOnElement(By.xpath(String.format(giftMenu, name)));
        } catch (NoSuchElementException nse) {
            // webDriver.findElements(By.xpath("//div/div/div[normalize-space()='"+name+"']")).get(0).click();
        }
        this.selectSubItemToPurchase();
    }

    public String getNewBuyer() {
        seleniumActions.sleep(3000);
        List<String> names = new ArrayList<>();
        for (int i = 0; i < userNames.size(); i++) {
            names.add(seleniumActions.getTextMessage(userNames.get(i)));
        }
        System.out.println(names.size());
        return names.get(0);
    }

    public void addAnotherBuyer() {
        seleniumActions.clickOnElement(crownIcon);
        seleniumActions.clickOnElement(addBuyerButton);
        if (webDriver.findElements(By.xpath("//span[normalize-space()='ADD BUYER']")).size() > 0) {
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("//span[normalize-space()='ADD BUYER']")));
        }
    }

    public List<Double> getAmountsList() {
        List<Double> amounts = new ArrayList<>();
        for (int i = 1; i < amountsList.size(); i++) {
            double amount = Double.valueOf(amountsList.get(i).getText().trim());
            if (amount > 0.0) {
                amounts.add(amount);
            }
        }
        //Collections.sort(amounts, Collections.reverseOrder());
        System.out.println("amounts :: " + amounts);
        return amounts;
    }

    public String getGuestNameUsingAmount(String amount) {
        WebElement element = webDriver.findElement(By.xpath("//td[contains(text(),'" + amount + "')]/preceding-sibling::td/descendant::otalio-reservation-person-name/span"));
        return seleniumActions.getTextMessage(element);
    }

    public void selectAndClickOnCheckAmountGreaterThan(String amount) {
        String amountCheck = "(//td[contains(text(),'%s')]/preceding-sibling::td/div/a)";
        seleniumActions.clickOnElement(By.xpath("(//td[contains(text(),'" + amount + "')]/preceding-sibling::td/div/a)"));
    }

    public void returnItem(String reason) {
        seleniumActions.clickOnElement(editIcon);
        String reasonRadioButton = "//label/span[contains(text(),'%s') or contains(text(),'spill') or contains(text(),'Void')]";
        try {
            seleniumActions.clickOnElement(By.xpath(String.format(reasonRadioButton, reason)));
        } catch (Exception e) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//span[contains(text(),'Void')])[1]")));
        }
        seleniumActions.clickOnElement(voidCheckButton);
    }

    public void navigateToCheckNumber(String guestName) {
        String checkNumber = "//span[normalize-space()='%s']/ancestor::td/following-sibling::td[1]/div/a";
        seleniumActions.clickOnElement(By.xpath(String.format(checkNumber, guestName)));
    }

    public String getFirstGuestName() {
        return seleniumActions.getTextMessage(guestNames);
    }

//    public class RevenueCenterConfig {
//        private static Properties properties = new Properties();
//
//        static {
//            try (InputStream input = new FileInputStream("D:/NCL_2026/otalio-platform-automation/config/configuration.properties")) {
//                properties.load(input);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//
//        public static String getRevenueCenterKey(String urlKeyword) {
//            return properties.getProperty(urlKeyword);
//        }
//    }

    public String getRvcProfileName(){
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//div[@routerlink='/profile/rvc-and-waiter-station-settings']")));
    }

    public String getRvcName(){
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//mat-select[@id='otalio-infinite-select-rvcId-field']/descendant::span/span")));
    }

    public void selectRevenueCenterAndSave(String revenueCenter) {
        seleniumWaits.untilElementIsClickable(rvcProfileLink);
        seleniumActions.clickOnElement(rvcProfileLink);
        seleniumActions.clickOnElement(revenueCenterDropDown);
        seleniumActions.clearValue(searchBox);
        seleniumActions.typeValue(searchBox, revenueCenter);
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[@class='mat-option-text'][normalize-space()='" + revenueCenter + "']")));
        } catch (StaleElementReferenceException staleElementReferenceException) {
            seleniumActions.clickOnElement(By.xpath("//span[@class='mat-option-text'][normalize-space()='" + revenueCenter + "']"));
        }
    }

    public void selectRevenueCenter(String revenueCenter) {
        seleniumWaits.untilElementIsClickable(rvcProfileLink);
        seleniumActions.clickOnElement(rvcProfileLink);
        seleniumActions.clickOnElement(revenueCenterDropDown);
        seleniumActions.clearValue(searchBox);
        seleniumActions.typeValue(searchBox, revenueCenter);
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + revenueCenter + "')]")));
        } catch (StaleElementReferenceException staleElementReferenceException) {
            seleniumActions.jsClick(webDriver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + revenueCenter + "')]")));
        }
        seleniumActions.clickOnElement(saveButton);
    }

    public void navigateToGuestProfile() {
        seleniumActions.sleep(2000);
        seleniumWaits.setImplicitWaitTime(webDriver,10);
        try {
            seleniumActions.clickOnElement(userPhoto);
        } catch (Exception e) {
        }
        seleniumWaits.untilElementIsClickable(profileDetailsMenu);
        seleniumActions.clickOnElement(profileDetailsMenu);
        seleniumWaits.keepBackImplicitWaitTime(webDriver);
    }

    public String getIndexOfHeaderValue(String value) {
        List<String> data = new ArrayList<>();
        try {
            seleniumWaits.untilElementIsVisible(columnHeaderData.get(0));
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(By.xpath("//table/tbody/tr/td[@role='columnheader']/div[contains(@class,'text-content')]"));
        }

        for (int i = 0; i < columnHeaderData.size(); i++) {
            try {
                seleniumActions.sleep(200);
                data.add(seleniumActions.getTextMessage(columnHeaderData.get(i)));
            } catch (StaleElementReferenceException ste) {
                data.add(webDriver.findElements(By.xpath("//table/tbody/tr/td[@role='columnheader']/div[contains(@class,'text-content')]")).get(i).getText());
            }
        }
        return String.valueOf(data.indexOf(value));
    }

    public String getPriceFromGuestList(String name, String headerName) {
        String price = "//span[normalize-space()='%s']/ancestor::td/following-sibling::td[" + getIndexOfHeaderValue(headerName) + "]";
        try {
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(price, name))));
            return seleniumActions.getTextMessage(webDriver.findElement(By.xpath(String.format(price, name))));
        }catch (Exception e){
            String price1 = "//span[contains(normalize-space(),'%s')]/ancestor::td/following-sibling::td[" + getIndexOfHeaderValue(headerName) + "]";
            return webDriver.findElement(By.xpath(String.format(price1, name))).getText();
        }
    }

    public String selectedItemFavName() {

        for (int i = 0; i < 3; i++) {
            try {
                return seleniumActions.getTextMessage(subItemsToPurchase.get(0));
            } catch (StaleElementReferenceException e) {
                PageFactory.initElements(webDriver, this);
            }
        }

        throw new NoSuchElementException("Unable to get favorite item name.");
    }

    public void selectSubItemToPurchase() {
        try {
            seleniumActions.clickOnElement(By.xpath("//div[contains(@class,'thumbnail rounded-circle')]/../following-sibling::div"));
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(subItemsToPurchase.get(0));
        }
    }

    public String getTotalPrice() {
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsVisible(totalPrice);
        return seleniumActions.getTextMessage(totalPrice).trim();
    }

    public int getChecks() {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return checksOnHomePage.size();
    }

    public void cancelCheck(){
        seleniumActions.clickOnElement(posBackButton);
        commonPage.navigateTo("Yes");
    }

    public void clearChecks() {
        seleniumWaits.setImplicitWaitTime(webDriver,10);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        try {
            seleniumActions.clickOnElement(posBackButton);
            commonPage.navigateTo("Yes");
/*
            String button1 = "Add Check";
            String text = "//span[text()='" + button1 + "' or normalize-space()='" + button1 + "']";
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(text)));
*/
        } catch (Exception e) {

        }
        seleniumWaits.keepBackImplicitWaitTime(webDriver);
    }

    public void selectGuest(){
        seleniumWaits.untilElementIsPresent(By.xpath("(//otalio-passenger-picture)[1]"));
        seleniumActions.clickOnElement(By.xpath("(//otalio-passenger-picture)[1]"));
    }

    public void selectMinimumFundValueUser(String fundValue) {
        int actualFund = Integer.valueOf(fundValue);
        List<Double> funds = new ArrayList<>();
        Double d = 0.0;
        for (int i = 0; i < userFundValue.size(); i++) {
            try {
                try {
                    d = Double.parseDouble(userFundValue.get(i).getText().replace(",", ""));
                } catch (StaleElementReferenceException ste) {
                    By xpath = By.xpath("//div[normalize-space()='Adult']/../../descendant::icon[@icon='pos-management']/following-sibling::span");
                    d = Double.parseDouble(webDriver.findElements(xpath).get(i).getText().replace(">", ""));
                } catch (IndexOutOfBoundsException ib) {

                }
            } catch (NumberFormatException nfe) {
                System.out.println(nfe.toString());
            }
            funds.add(d.doubleValue());
        }
        System.out.println("funds :: " + funds);

        for (int j = 0; j < funds.size(); j++) {
            if (funds.get(j) >= actualFund) {
                try {
                    seleniumActions.clickOnElement(userFundValue.get(j));
                } catch (IndexOutOfBoundsException ie) {
                    seleniumActions.clickOnElement(userFundValue.get(1));
                }
                break;
            }
        }

        if (webDriver.findElement(By.xpath("(//otalio-guest-search-selected-area/div/descendant::span)[1]")).getText().contains("1")) {
            System.out.println("Guest selected");
        } else {
            seleniumActions.clickOnElement(By.xpath("(//otalio-passenger-picture)[1]"));
        }
    }

    public String getGuestName() {
        String fullName = guestFullName.getText().trim();
        return fullName;
    }

    public void searchGuest(String guest) {
        seleniumActions.typeValue(shipSecurityPage.getSearchInputBox(), guest);
    }

    public void clickSelectedGuest(String guestName) {
        try {
            seleniumWaits.untilElementIsClickable(webDriver.findElement(
                    By.xpath("(//otalio-passenger-picture/following-sibling::div/div[contains(text(),'" + guestName + "')])[1]")
            ));
        } catch (Exception e) {
        }
        try {
            seleniumActions.clickOnElement(By.xpath("(//otalio-passenger-picture/following-sibling::div/div[contains(text(),'" + guestName + "')])[1]"));
        } catch (Exception e) {
            seleniumActions.clickOnElement(By.xpath("(//otalio-passenger-picture)[1]"));
        }
    }

    public void clickOnGuest() {
        seleniumWaits.untilElementIsClickable(userFundValue.get(0));
        seleniumActions.clickOnElement(userFundValue.get(0));
    }

    public void searchGuestUsingAlphabetic(String name) {
        try {
            seleniumActions.clickOnElement(shipSecurityPage.getSearchIcon());
        } catch (JavascriptException je) {
            seleniumActions.moveToElementAndClickOnElement(shipSecurityPage.getSearchIcon(), shipSecurityPage.getSearchIcon());
        }
        seleniumActions.sleep(2000);
        seleniumActions.clearValue(shipSecurityPage.getSearchInputBox());
        seleniumActions.typeValue(shipSecurityPage.getSearchInputBox(), name);
    }

    public void searchGuestUsingAlphabetic() {
        try {
            seleniumActions.clickOnElement(shipSecurityPage.getSearchIcon());
        } catch (JavascriptException je) {
            seleniumActions.moveToElementAndClickOnElement(shipSecurityPage.getSearchIcon(), shipSecurityPage.getSearchIcon());
        }
        seleniumActions.sleep(2000);
        seleniumActions.clearValue(shipSecurityPage.getSearchInputBox());
        seleniumActions.typeValue(shipSecurityPage.getSearchInputBox(), String.valueOf(commonFunctions.getRandomCharacter()));
    }

    public void searchGuestUsing(String user) {
        try {
            seleniumActions.clickOnElement(shipSecurityPage.getSearchIcon());
        } catch (JavascriptException je) {
            seleniumActions.moveToElementAndClickOnElement(shipSecurityPage.getSearchIcon(), shipSecurityPage.getSearchIcon());
        }
        seleniumActions.sleep(1000);
        seleniumActions.clearValue(shipSecurityPage.getSearchInputBox());
        seleniumActions.typeValue(shipSecurityPage.getSearchInputBox(), user);
        seleniumActions.sleep(1000);
    }
}
