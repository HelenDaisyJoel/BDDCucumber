package com.nclh.pageObjects;

import com.nclh.constants.GlobalConstants;
import com.nclh.constants.GuestCheckInCheckOutConstants;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class GuestCheckInCheckOutPage {
    private WebDriver webDriver;
    private SeleniumActions seleniumActions;
    private SeleniumWaits seleniumWaits;
    private CommonPage commonPage;
    private CommonFunctions commonFunctions;

    public GuestCheckInCheckOutPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        seleniumActions = new SeleniumActions(webDriver);
        commonPage = new CommonPage(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonFunctions = new CommonFunctions();
    }

    @FindBy(css = "[placeholder='Last Name']")
    private WebElement lastNameInputField;

    @FindBy(xpath = "//otalio-profile-card/descendant::h1[contains(text(),'')]")
    private WebElement getLastNameText;

    @FindBy(xpath = "//div[text()='Reservation Status:']/parent::otalio-complex-filter-multi-label-content/descendant::otalio-chip/div/div")
    private WebElement getReservationStatusText;

    @FindBy(xpath = "//otalio-person-board-status/h2/span[@class='ng-star-inserted']")
    private WebElement getGuestStatusText;

    @FindBy(xpath = "//div[contains(@class,'align-items-center justify-content-end px')]/div")
    private WebElement getNationalityText;

    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-preferredLanguage-field']/descendant::span/span")
    private WebElement getPreferredLanguageText;

    @FindBy(xpath = "//icon[@icon='speaking']/following-sibling::div")
    private WebElement getSpeakingText;

    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-nationality-field']/descendant::span/span")
    private  WebElement nationalityText;

    @FindBy(xpath = "//span[contains(text(),'%s')]/ancestor::mat-radio-button/descendant::input")
    private WebElement selectRoutingRadioButton;

    @FindBy(xpath = "//otalio-guest-search-card/descendant::otalio-passenger-picture/following-sibling::div/div[contains(@aria-describedby,'cdk-describedby-message')]")
    private WebElement getAddRoutingGuestNameText;

    @FindBy(xpath = "//otalio-guest-search-card/descendant::otalio-passenger-picture/following-sibling::div/div/icon/following-sibling::div")
    private WebElement getAddRoutingGuestCabinText;

    @FindBy(xpath = "//span[contains(text(),'Do not auto check-out at Gangway')]/ancestor::label/span/input[@aria-checked='false']")
    private WebElement toggleButtonStatus;

    @FindBy(xpath = "//input[@id='otalio-do-not-auto-check-out-gangway-guest-toggle-input']")
    private WebElement getToggleButton;

    @FindBy(xpath = "//h3[text()='Booking Nr']/ancestor::otalio-passenger-booking-info/descendant::otalio-chip/div/div")
    private WebElement getBookingNrText;

    @FindBy(xpath = "//span[text()='Disembarkation']/ancestor::div[@class='text-type-info']/following-sibling::div")
    private WebElement getDisembarkationDateText;

    @FindBy(xpath = "//div[text()='Muster Station']/following-sibling::otalio-chip-list/descendant::otalio-chip/div/div")
    private  WebElement getMusterStationText;

    @FindBy(xpath = "//input[@placeholder='Disembarkation Date']")
    private WebElement disembarkationDateInputFieldText;

    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-alternateMusterstationId-field']/descendant::div/span/span")
    private WebElement musterStationInputFieldText;

    @FindBy(xpath = "//input[@placeholder='Booking Nr']")
    private WebElement bookingNrDateInputFieldText;

    @FindBy(xpath = "//otalio-gangway-movement/descendant::div[contains(@class,'flex-column')]/div[contains(@class,'text-type-info')]")
    private WebElement getLastGangwaysMovementText;

    @FindBy(xpath = "//div[text()='Last Gangway movement']/following-sibling::div/span")
    private List<WebElement> lastGangwayText;

    @FindBy(xpath = "//span[text()='Embarkation']/../../div/following-sibling::div")
    private WebElement embarkationDateText;

    @FindBy(xpath = "//span[text()='Embarkation']/../../../div/following-sibling::div/icon/following-sibling::h3")
    private WebElement embarkationPortText;

    @FindBy(xpath = "//mat-select[contains(@id,'EmbarkationPortId-field')]/descendant::span[text()]")
    private WebElement embarkationPortFieldText;

    @FindBy(xpath = "//otalio-datepicker-input[contains(@class,'ng-touched')]")
    private WebElement embarkationDateFieldText;

    @FindBy(xpath = "//mat-step-header/descendant::div[contains(text(),'Add Document')]")
    private WebElement addDocumentTab;

    @FindBy(css = "[formcontrolname='direction']")
    private WebElement directionDropdown;

    @FindBy(css = "[formcontrolname='flightNumber']")
    private WebElement flightNumberInputField;

    @FindBy(xpath = "(//table[@aria-label='Calendar']//tr/td/span)[1]")
    private WebElement selectTodayDate;

    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-direction-field']/descendant::span/span")
    private WebElement directionText;
    @FindBy(css = "[id*='cruise-information-flight-info-delete']")
    private List<WebElement> deleteButtons;
    @FindBy(css = "#complex-filter-collapse-expand-chip > div > div")
    private WebElement hiddenFiltersIcon;
    By lastName = By.xpath("//table/tbody/tr/td[@role='gridcell']/div/a[(contains(@class,'cursor-pointer text-primary') and not(@id)) or contains(@id,'last-name')]");

    @FindBy(css = "[id='passengers-list-page-more-actions']")
    private WebElement kebabButton;

    @FindBy(xpath = "//button[contains(@id,'bulk-operations-button')]")
    private WebElement bulkButton;
    @FindBy(css = "[id*='mat-dialog-title']")
    private WebElement confirmationHeader;
    @FindBy(xpath = "//button[@id='confirmation-dialog-confirm-button']" )
    private WebElement yesConfirmButton;

    @FindBy(xpath = "//button[@id='board-card-print-reason-form-save-note-button']" )
    private WebElement printerConfirmButton;
    @FindBy(xpath = "(//div[@id='toast-message-content'])[2]")
    private WebElement toastMessage;
    @FindBy(xpath = "//table//tr[1]/td[@aria-colindex='1' and not(@role='columnheader')]")
    private WebElement firstCell;

    @FindBy(xpath = "//mat-radio-button[1]//span[@class='mat-radio-outer-circle']")
    private WebElement onBoardRadioButton;

    @FindBy(css = "[id='cruise-information-current-save-btn']")
    private WebElement saveOnboardButton;
    @FindBy(xpath = " //button[contains(@class,'mat-focus-indicator mat-icon-button mat-button-base')]//icon")
    private WebElement errorPopMessage;
    @FindBy(css = "[aria-label='Select all']")
    private WebElement selectedCheckBox;

    @FindBy(css = "[id='list-unselect-all-button']")
    private WebElement unselectButton;
    @FindBy(xpath = "//div[@aria-label='Select row']")
    private List<WebElement> checkboxes;
    @FindBy(xpath = "//otalio-profile-card/descendant::h1[contains(text(),'')]")
    private WebElement profileText;




    private By getFilterDropdown(String filterName) {
        return By.xpath("//b[contains(text(),'" + filterName + "')]/../following-sibling::icon/*[name()='svg']");
    }

    private By getFilterCheckbox(String checkBox) {
        return By.xpath("//*[normalize-space()='" + checkBox + "']/preceding-sibling::mat-pseudo-checkbox");
    }

    public WebElement flightNumberText(){
        return flightNumberInputField;
    }

    public String getDirectionText(){
        seleniumActions.highLighterMethod(directionText);
        return seleniumActions.getTextMessage(directionText);
    }

    public void clickOnTodayDate(){
        seleniumWaits.untilElementIsVisible(selectTodayDate);
        seleniumActions.clickOnElement(selectTodayDate);
    }

    public void enterFlightNumber(){
        seleniumActions.sendKeys(flightNumberInputField,"XX" + String.valueOf(commonFunctions.getRandomNumber(GlobalConstants.INITIAL_NUMBER, GuestCheckInCheckOutConstants.END_NUMBER)));
    }

    public void selectDirectionDropdown(){
        /*seleniumActions.clickOnElement(directionDropdown);
        String text = "(//div[@id='otalio-infinite-select-direction-field-panel']/descendant::mat-option)[1]";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(text)));*/
        seleniumWaits.untilElementIsVisible(webDriver.findElement(getMatSelectByLabel(GuestCheckInCheckOutConstants.DIRECTION)));
        seleniumActions.selectDropdownOption(GuestCheckInCheckOutConstants.DIRECTION, GuestCheckInCheckOutConstants.DROPDOWN_INDEX_VALUE);
    }

    private String reservationFinishButtonXpath(String buttonText) {
        return String.format("//button[@id='create-new-reservation-save-and-finish-button' or normalize-space(text())='%s']", buttonText);
    }

    /* The method Xpath is not working and  this method id using only in 2 scripts only
    public void reservationFinishButton(String button) {
        seleniumActions.sleep(5000);
        String text = "//button[@id='create-new-reservation-save-and-finish-button']/span[text()='" + button + "' or normalize-space()='" + button + "']";
        if (webDriver.findElements(By.xpath(text)).size() > 0){
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(text)));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
        }
    }
    the xpath and code is not working properly
    */
    public void reservationFinishButton(String buttonText) {
        commonPage.waitForDomReady();
        String xpath = reservationFinishButtonXpath(buttonText);
        WebElement buttonElement = webDriver.findElement(By.xpath(xpath));
        seleniumWaits.untilElementIsVisible(buttonElement);
        seleniumWaits.untilElementIsClickable(buttonElement);
        seleniumActions.clickOnElement(buttonElement);
        try {
            seleniumActions.jsClick(buttonElement);
        } catch (Exception m ) {
        }
    }

    public void photoSubmitNextButton(String button) {
        seleniumActions.sleep(5000);
        String text = "//button[@id='create-new-reservation-page-photo.submit-next-button']/span[text()='" + button + "' or normalize-space()='" + button + "']";
        if (webDriver.findElements(By.xpath(text)).size() > 0){
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(text)));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
        }
    }

    public void clickAddDocumentTab(){
        seleniumActions.sleep(5000);
        seleniumActions.clickOnElement(addDocumentTab);
        seleniumActions.clickOnElement(addDocumentTab);
    }

    public void setReservationsDates(String embarkationOrDisembarkation, String date) {
        String dateInput = "[placeholder='%s']";
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(String.format(dateInput, embarkationOrDisembarkation))), date);
        seleniumActions.sleep(10000);
    }

    public WebElement getEmbarkationDateInputFieldText(){
        seleniumActions.sleep(3000);
        return embarkationDateFieldText;
    }

    public WebElement getEmbarkationPortInputFieldText(){
        seleniumActions.sleep(3000);
        return embarkationPortFieldText;
    }

    public String getEmbarkationPortText(){
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(embarkationPortText);
    }

    public String getEmbarkationDateText(){
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(embarkationDateText);
    }

    public boolean checkLastGangwayText(){
        System.out.println(lastGangwayText.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList()));
        return seleniumActions.areElementsDisplayed(lastGangwayText);
    }

    public String lastGangwaysMovementText(){
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(getLastGangwaysMovementText);
    }

    public boolean checkAddRoutingUser(){
        seleniumActions.sleep(3000);
        return seleniumActions.isElementDisplayed(getAddRoutingGuestNameText);
    }

    public String musterStationInputField(){
        return seleniumActions.getTextMessage(musterStationInputFieldText);
    }

    public String bookingNrInputField(){
        return seleniumActions.getTextMessage(bookingNrDateInputFieldText);
    }

    public String disembarkationDateInputField(){
        return seleniumActions.getTextMessage(disembarkationDateInputFieldText);
    }

    public String getMusterStation(){
        return seleniumActions.getTextMessage(getMusterStationText);
    }

    public String getDisembarkationDate(){
        return seleniumActions.getTextMessage(getDisembarkationDateText);
    }

    public String getBookingNr(){
        return seleniumActions.getTextMessage(getBookingNrText);
    }

    public String toggleButtonStatus(){
        seleniumWaits.untilElementIsVisible(getToggleButton);
        return getToggleButton.getAttribute("aria-checked");
    }

    public void clickDoNotAutoCheckoutAtGangway(){
         seleniumActions.clickOnElement(toggleButtonStatus);
    }

    public String addRoutingGuestCabinText(){
        return seleniumActions.getTextMessage(getAddRoutingGuestCabinText);
    }

    public String addRoutingGuestName(){
        seleniumWaits.untilElementIsVisible(getAddRoutingGuestNameText);
        seleniumActions.scrollInToElement(getAddRoutingGuestNameText);
        return seleniumActions.getTextMessage(getAddRoutingGuestNameText);
    }

    public void selectRouting(String name){
        String selectRoutingRadioButton = "//span[contains(text(),'"+name+"')]/ancestor::mat-radio-button/descendant::input[@type='radio']";
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(selectRoutingRadioButton))));
    }

    public String guestNationality(){
        return seleniumActions.getTextMessage(nationalityText);
    }

    public String getSpeaking(){
        return seleniumActions.getTextMessage(getSpeakingText);
    }

    public String getPreferredLanguage(){
        return seleniumActions.getTextMessage(getPreferredLanguageText);
    }

    public String getNationality(){
        return seleniumActions.getTextMessage(getNationalityText);
    }

    public String getGuestStatus(){
        return seleniumActions.getTextMessage(getGuestStatusText).replace(" /","");
    }

    public String getReservationStatus(){
        return seleniumActions.getTextMessage(getReservationStatusText);
    }

    public void updateLastName(String lastName){
        seleniumActions.clearValue(lastNameInputField);
        seleniumActions.typeValue(lastNameInputField,lastName);
    }

    public String getLastName(){
        return seleniumActions.getTextMessage(getLastNameText).replace("Mrs Honorable","").trim();
    }
    public void clickOnTabAndClick(String tab, String button1) {
        WebElement tabElement = webDriver.findElement(By.xpath("//div[contains(text(),'" + tab + "')]"));
        seleniumWaits.untilElementIsClickable(tabElement);
        seleniumActions.clickOnElement(tabElement);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);

        if (deleteButtons.size() == 0) {
            WebElement element = webDriver.findElement(By.xpath("//span[text()='" + button1 + "' or normalize-space()='" + button1 + "']"));
            seleniumWaits.untilElementIsClickable(element);
            seleniumActions.moveToElementAndClick(element);
        }
    }

    public void userSelectsTheDropdownFilterOptionAs(String filterName, String checkBox) {

        seleniumActions.pressEscapeKey();

        WebElement filterElement = webDriver.findElement(getFilterDropdown(filterName));

        seleniumWaits.untilElementIsVisible(filterElement);
        seleniumActions.clickOnElement(filterElement);


        WebElement checkBoxElement = webDriver.findElement(getFilterCheckbox(checkBox));

        seleniumWaits.untilElementIsVisible(checkBoxElement);
        seleniumActions.clickOnElement(checkBoxElement);
    }

    public List<WebElement> getLastNames() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.MINI_WAIT));
        List<WebElement> lastNames = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(lastName));
        if (!lastNames.isEmpty()) {
            seleniumActions.highLighterMethod(lastNames.get(0));
        }
        return lastNames;
    }

    public void userClickOnThreeDotsAndSelectBulkOperation(){
        seleniumWaits.untilElementIsVisible(kebabButton);
        seleniumActions.highLighterMethod(kebabButton);
        seleniumActions.clickOnElement(kebabButton);
        seleniumActions.clickOnElement(bulkButton);
    }

    public void userViewsTheConfirmationPopUpAndClicksConfirm(){
        seleniumWaits.untilElementIsVisible(confirmationHeader);
        seleniumActions.highLighterMethod(confirmationHeader);
        seleniumActions.clickOnElement(confirmationHeader);
        seleniumActions.clickOnElement(yesConfirmButton);
    }

    public By getMatSelectByLabel(String labelName) {

        String xpath = "//mat-form-field[.//*[normalize-space(text())='" + labelName + "']]//div[@aria-label='Select' or contains(@class,'mat-select-arrow-wrapper')]";

        return By.xpath(xpath);
    }

    public void userSelectThePrinterTypeInPrinterDropdown(){
        seleniumWaits.untilElementIsVisible(webDriver.findElement(getMatSelectByLabel(GuestCheckInCheckOutConstants.BOARDCARD_PRINTER)));
        seleniumActions.selectDropdownOption(GuestCheckInCheckOutConstants.BOARDCARD_PRINTER, GuestCheckInCheckOutConstants.DROPDOWN_INDEX_VALUE);
        seleniumActions.clickOnElement(printerConfirmButton);
        seleniumWaits.untilElementIsVisible(toastMessage);

    }
    public void guestCabinNumber(){
        seleniumWaits.untilElementIsVisible(firstCell);
        seleniumActions.clickOnElement(firstCell);
    }
    public void onBoardingTheUser(){
        seleniumWaits.untilElementIsVisible(onBoardRadioButton);
        seleniumActions.clickOnElement(onBoardRadioButton);
    }
    public void saveButton(){
        seleniumWaits.untilElementIsVisible(saveOnboardButton);
        seleniumActions.clickOnElement(saveOnboardButton);
    }
    public void userCloseThePopErrorMessage(){
        seleniumActions.clickOnElement(errorPopMessage);

    }
    public void unselectAllGuestMembers(){
        seleniumWaits.untilElementIsVisible(selectedCheckBox);
        try {
            seleniumActions.clickOnElement(selectedCheckBox);
        } catch (Exception e) {
            seleniumActions.clickOnElement(selectedCheckBox);
        }
        seleniumWaits.untilElementIsClickable(unselectButton);
        try {
            seleniumActions.moveToElementAndClick(unselectButton);
        } catch (Exception ste) {
            seleniumActions.clickOnElement(unselectButton);
        }
    }

    public void selectsOneGuestMember(){
        if (!checkboxes.isEmpty()) {
            checkboxes.get(0).click();
        }
    }

    public String userGetsTheGuestNameText(){
        seleniumWaits.untilElementIsVisible(profileText);
        return seleniumActions.getTextMessage(profileText);
    }



}
