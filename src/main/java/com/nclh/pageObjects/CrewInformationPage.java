package com.nclh.pageObjects;

import com.nclh.constants.*;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumWaits;
import lombok.Getter;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import com.nclh.utilities.SeleniumActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class CrewInformationPage {
    private WebDriver webDriver;
    private SeleniumActions seleniumActions;
    private SeleniumWaits seleniumWaits;
    private CommonFunctions commonFunctions;
    private CommonPage commonPage;
    private WebElement saveButtonButton;
    @FindBy(xpath = "//button[normalize-space()='Save']")

    public static final Logger logger
            = LoggerFactory.getLogger(SeleniumActions.class);
    private ShipSecurityPage shipSecurityPage;

    public CrewInformationPage(WebDriver webDriver){
        this.webDriver = webDriver;
        seleniumActions = new SeleniumActions(webDriver);
        PageFactory.initElements(webDriver, this);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonFunctions = new CommonFunctions();
        commonPage = new CommonPage(webDriver);
        shipSecurityPage = new ShipSecurityPage(webDriver);
    }

    @FindBy(xpath = "//otalio-passenger-picture/../following-sibling::h1")
    private WebElement crewName;

    @FindBy(xpath = "//otalio-passenger-picture/../following-sibling::div/div/div")
    private List<WebElement> countryAndLanguage;

    @FindBy(xpath = "//otalio-person-board-status/h2/span")
    private List<WebElement> status;

    @FindBy(css = "[id*='complex-filter'] > div > div")
    private WebElement cabinFilter;

    @FindBy(css = "[id*='embarkation'] > div + icon")
    private WebElement embarkationDatePicker;

    @FindBy(css = "[href*='close']")
    private List<WebElement> closeIcon;

    @FindBy(xpath = "(//table/tbody/tr/td[@role='gridcell']/div/span)[1]")
    private WebElement crewNameForCabinAssignment;

    @FindBy(xpath = "(//table/tbody/tr/td[@role='gridcell']/div/span)[1]/ancestor::tr/td[1]")
    private WebElement crewNameCheckbox;


    @FindBy(xpath = "(//h4)[1]")
    private WebElement cabinNumber;

    @FindBy(xpath = "(//h4/ancestor::div/div/div/mat-checkbox)[1]")
    private WebElement cabinNumberCheckbox;

    @FindBy(xpath = "//button/span[normalize-space()='Assign']")
    private WebElement assignButton;

    @FindBy(xpath = "//button/span[normalize-space()='Unassign']")
    private WebElement unAssignButton;

    @FindBy(xpath = "(//*[@icon='cabin']/..)[1]")
    private WebElement cabinNumberOnCrewInformationPage;

    @FindBy(xpath = "//icon[@icon='cabin']/../../h2")
    private WebElement cabinNumberOnStatus;

    @Getter
    @FindBy(css = "button[id*='reservationStatus'] > icon")
    private WebElement reservationStatusList;

    @FindBy(css = "button[id*='operationalPosition'] > icon")
    private WebElement operationPosition;

    @FindBy(css = "[class*='pseudo-checkbox-checked']")
    private List<WebElement> checkBoxes;

    @FindBy(xpath = "//b[text()='Assigned:']/../following-sibling::icon/*[name()='svg']")
    private WebElement assignedDropdown;

    @FindBy(xpath = " //mat-pseudo-checkbox")
    private List<WebElement> assignCheckBoxes;

    @FindBy(tagName = "otalio-current-location")
    private WebElement shipLocation;

    @FindBy(css = "[id='crew-list-page-more_vert-mat-icon']")
    private WebElement threeDots;

    @FindBy(xpath = "//button[contains(@id,'bulk-operatoins-button')]")
    private WebElement bulkButton;

    @FindBy(xpath = "(//button/span[normalize-space()='Next'])[1]")
    private WebElement nextButton;

    @FindBy(xpath = "//button[@id='bulk-update-confirm-button']")
    private WebElement confirmButton;

    @FindBy(css = "[icon='menu-dots']>svg")
    private WebElement threeDotsIcon;

    @FindBy(xpath = "(//table/tbody/tr/td[@role='gridcell']/div/a[starts-with(@id,'crew')])[1]")
    private WebElement crewFirstName;

    @FindBy(xpath = "(//table/tbody/tr/td[@role='gridcell'][@aria-colindex=\"4\"]/div/a[starts-with(@id,'crew')])")
    private List<WebElement> crewFirstNames;

    @FindBy(css = "[aria-label='Select all']")
    private WebElement selectedCheckBox;

    @FindBy(css = "[id='list-unselect-all-button']")
    private WebElement unselectButton;

    @FindBy(xpath = "//div[text()='Operation Details']")
    private WebElement clickOp;

    @FindBy(xpath = "//a[@class='ng-star-inserted']")
    private WebElement updateReservation;

    @FindBy(xpath = "//tbody/tr/td/div")
    private List<WebElement> headerItems;

    @FindBy(xpath = "//div/div[contains(@class,'text-truncate')]/child::div")
    private WebElement bulkErrorMessage;

    @FindBy(css = "otalio-person-board-status>h2>span:first-of-type")
    private WebElement userBoardStatus;

    @FindBy(xpath = "(//table/tbody/tr/td[@role='gridcell']/div/a[contains(@href,'crew-services')])")
    private List<WebElement> crewLastAndFirstName;

    @FindAll({
            @FindBy(css = "[placeholder^='Search']"),
            @FindBy(css = "input.complex-search-input")
    })
    private WebElement crewSearchBox;

    @FindBy(css = "otalio-crew-operations-card>div>div>h3")
    private WebElement operations;

    @FindBy(xpath = "//otalio-profile-card/descendant::h1[contains(text(),'')]")
    private WebElement fullName;

    @FindBy(css = "#complex-filter-add-more-button>span>icon>svg>use")
    private WebElement moreFilters;

    @FindBy(css = "#complex-filter-search-add-more-filters")
//    @FindBy(xpath = "(//*[@id='complex-filter-add-more-button'])[2]")
    private WebElement searchFilter;

    @FindBy(xpath = "//mat-pseudo-checkbox[contains(@class,'checkbox')]")
    private WebElement checkBoxFilter;

    @FindBy(css = "[id='complex-filter-label-currentBalance.currentBalance-open-button']")
    private WebElement arrowDown;

    @FindBy(xpath = "//span[text()='Number Range']/../../../label//span/input")
    private WebElement selectRadioButton;

    @FindBy(css = "[placeholder='Min']")
    private WebElement minInputField;

    @FindBy(css = "[placeholder='Max']")
    private WebElement maxInputField;

    @FindBy(xpath = "//span[text()='Crew Members ']")
    private WebElement clickCrewM;

    @FindBy(xpath = "//otalio-profile-card//div[contains(@class,'user-avatar')]/img")
    private WebElement profilePicture;

    @FindBy(xpath = "//input[@id='ashore-denied-mat-slide-toggle-input']")
    private WebElement ashoreDeniedToggleButton;

    @FindBy(css = "[placeholder='Quantity']")
    private WebElement quantityText;

    @FindBy(xpath = "//div[@id='toast-title-error']/following-sibling::div[contains(@class,'text-truncate')]/div")
    private WebElement errorMessage;

    @FindBy(css = "[id='items-equipment-actions-button']")
    private WebElement equipmentActionButton;

    @FindBy(xpath = "//td[@aria-colindex='6'][not(@role='columnheader')]/span")
    private WebElement statusText;

    @FindBy(xpath = "(//span[contains(text(),'Added')]/ancestor::dx-data-grid/descendant::div[contains(@class,'datagrid-content-fixed')]/descendant::div[@aria-label='Select row'])[1]")
    private WebElement selectAddedCrewEffectCheckbox;

    @FindBy(xpath = "//textarea[contains(@id,'mat-input')]")
    private WebElement commentInputField;

    @FindBy(xpath = "//div[contains(text(),'Hide No Prints')]/following-sibling::mat-slide-toggle/descendant::input")
    private WebElement hideNoPrintsToggle;

    @FindBy(xpath = "//input[@id='folio-information-posting-status-mat-toggle-input']")
    private WebElement postingStatusToggle;

    @FindBy(xpath = "//td[@role='columnheader'][not(contains(@aria-colindex,'14'))][not(contains(@aria-label,'Column Same Cabin'))]")
    private List<WebElement> crewColumnData;

    @FindBy(xpath = "//div[@role='tab']")
    private List<WebElement> assignmentsAndContractInformationHeader;

    @FindBy(xpath = "//div[@aria-label='Select all']")
    private WebElement selectAllCheckbox;

    @FindBy(css = "[placeholder='First Name']")
    private WebElement firstNameInputField;

    @FindBy(css = "[placeholder='Last Name']")
    private WebElement lastNameInputField;

    @FindBy(xpath = "(//input[@type='checkbox'])[1]")
    private WebElement selectCabinCheckbox;

    @FindBy(css = "input[id*='document-nr-input']")
    private WebElement documentNrInputField;
    
    @FindBy(css = "[id*='movementDate'] > div + icon")
    private WebElement clearDates;

    @FindBy(css = "[id*='movementDirection'] > div + icon")
    private WebElement actionTypeDropdown;

    @FindBy(xpath = "//span[text()='Date']/ancestor::mat-radio-group/descendant::button[contains(@id,'date-overlay-clear-button')]/span/icon")
    private WebElement closeDate;

    @FindBy(xpath = "//td[@aria-colindex='5'][not(contains(@aria-label,'Column Action Type'))]")
    private List<WebElement> actionTypeListOfAll;

    @FindBy(xpath = "//button[contains(@id,'info-form-disembarkation')]/span/icon")
    private WebElement disembarkationDateInputField;

    @FindBy(xpath = "//otalio-datepicker/descendant::div[contains(@class,'position-relative today')]")
    private WebElement selectDisembarkationDateInputField;
    
    @FindBy(xpath = "//mat-label[text()='First Name']/ancestor::span/preceding-sibling::input[contains(@id,'documents-input')]")
    private WebElement firstNameTextMessage;

    @FindBy(xpath = "//otalio-datepicker-input[contains(@class,'ng-touched')]")
    private WebElement embarkationDateFieldText;

    @FindBy(xpath = "//mat-label[text()='First Name']/ancestor::span/preceding-sibling::input[contains(@class,'ng-touched')]")
    private WebElement firstNameFieldTextMessage;

    @FindBy(xpath = "//mat-label[text()='Last Name']/ancestor::span/preceding-sibling::input[contains(@class,'ng-touched')]")
    private WebElement lastNameFieldTextMessage;

    @FindBy(css = "[aria-label='Actions']")
    private WebElement threeDotsButton;

    @FindBy(css = "[id='person-security-flag-form-denied-reason-info-input']")
    private WebElement reasonInputField;

    @FindBy(xpath = "//span[normalize-space()='Select']/../../../../descendant::otalio-passenger-picture/following-sibling::div/div[contains(@class,'ng-star-inserted')]")
    private WebElement accountName;

    @FindBy(xpath = "(//div[text()='Buyer']/ancestor::div[@aria-label='Data grid']/descendant::td[@role='gridcell']/div/otalio-user-name/span)[1]")
    private WebElement getBuyerName;

    @FindBy(xpath = "//button[@id='person-security-flags-add-button']/span/icon")
    private WebElement flagsButton;

    @FindBy(xpath = "//mat-label[text()='First Name']/ancestor::span/preceding-sibling::input[contains(@id,'first-name-input')]")
    private WebElement firstNameReservationTextMessage;


    @FindBy(xpath = "(//div[contains(text(),'Status:')]/../descendant::div[text()][contains(@class,'text-truncate')])[1]")
    private WebElement reservationStatus;

    @FindBy(xpath = "(//div[contains(text(),'Position:')]/../descendant::div[text()][contains(@class,'text-truncate')])[1]")
    private WebElement operationClick;

    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-reasonId-field']/descendant::span/span")
    private WebElement reasonText;

    @FindBy(xpath = "//button[@aria-label='Add']")
    private WebElement addIcon;
    @FindBy(xpath = "//input[@type='checkbox' and @aria-checked='false' and ancestor::mat-slide-toggle]")
    private WebElement toggleLocator;
    @FindBy(xpath = "//*[contains(@class,'mat-step-label-active')][.='Reservation Data'] | //div[text()='Reservation Data']")
    private WebElement reservationData;
    private By searchBox = By.cssSelector("[placeholder^='Search..']");
    private By expectToday = By.xpath("//span[text()='Expect Today']/../preceding-sibling::mat-pseudo-checkbox");
    @FindBy(css = "[id*='nationality-field']")
    private WebElement nationalityText;
    By searchBar = By.xpath("//input[contains(@data-placeholder,'Search')]");
    private By deleteIconLocator = By.xpath("//table/tbody/tr//button[contains(@id,'delete-mat-icon')]");
    private By editIconLocator = By.xpath("(//button[@id='items-equipment--edit-button'])[1]");

    @FindBy(xpath = "//h1[contains(@class,'mat-tooltip-trigger')]")
    private WebElement fullNameText;

    @FindBy(xpath = "(//div[contains(@class,'text-type-info') and contains(@class,'text-dark')])[1]")
    private WebElement embarkationDateText;

    @FindBy(xpath = "(//div[contains(@class,'text-type-info') and contains(@class,'text-dark')])[2]")
    private WebElement disembarkationDateText;

    @FindBy(xpath = "//h2[contains(@class,'text-center') and contains(@class,'ng-star-inserted')]")
    private WebElement profileStatusText;

    @FindBy(xpath = "//h4[contains(@class,'m-0') and contains(@class,'font-weight-bold') and contains(@class,'text-capitalize')]")
    private WebElement crewMemberDemographics;
    @FindBy(xpath = "//button[@id='discard-confirmation-dialog-confirm-button']" )
    private WebElement yesConfirmButton;
    @FindBy(xpath = "//form//div[contains(@class,'row no-gutters')]")
    private List<WebElement> emergencyContactCards;
    @FindBy(css = "[icon='menu-dots']")
    private WebElement threeDotsMenu;

    @FindBy(css = "button[id*='add-ashore-denied']")
    private WebElement ashoreDeniedButton ;

    @FindBy(css = "[id*='personal-information-general-update-button']")
    private WebElement updateButton;
    @FindBy(xpath = "//otalio-profile-card/descendant::h1[contains(text(),'')]")
    private WebElement guestName;
    @FindBy(id = "otalio-infinite-select-salutation-field")
    private WebElement salutationDropdown;

    @FindBy(xpath = "//div[contains(@class,'search-bar')]//mat-form-field//input")
    private WebElement salutationSearchField;

    @FindBy(xpath = "(//mat-option)[2]")
    private WebElement secondOption;
    @FindBy(xpath = "(//mat-option)[1]")
    private WebElement firstOption;
    @FindBy(xpath = "//otalio-payment-method-assignment-form//form")
    private List<WebElement> paymentMethodAssignmentForms;
    @FindBy(xpath = "//otalio-infinite-select[@formcontrolname='creditSubDepartmentId' and contains(@class,'invalid')]")
    private WebElement financialDepartmentDropDown;
    @FindBy(xpath = "//mat-option[not(contains(@class,'disabled'))]")
    private List<WebElement> enabledOptions;
    @FindBy(xpath = "(//mat-checkbox[contains(@class,'mat-checkbox-disabled')]/ancestor::form//mat-form-field[.//label[contains(.,'Payment method')]]//span)[1]")
    private WebElement disabledPaymentMethod;
    @FindBy(xpath = "//div[normalize-space()='Payment Method']/following-sibling::div[1]")
    private WebElement accountStatementPaymentMethod;
    @FindBy(xpath = "//mat-checkbox[@formcontrolname='active' and contains(@class,'checkbox-disabled')]")
    private WebElement disabledActiveCheckBox;
    @FindBy(xpath = "//mat-checkbox[@formcontrolname='active' and contains(@class,'ng-valid')]")
    private WebElement enabledActive;
    @FindBy(xpath = "//mat-checkbox[@formcontrolname='active' and contains(@class,'ng-valid')]//input")
    private WebElement enabledActiveCheckBox;
    @FindBy(xpath = "//tbody/tr[not(contains(@class,'header-row'))]")
    private List<WebElement>  crewReservationLogsRows;

    @FindBy(xpath = "//button[@disabled='true']//span[normalize-space()='Save']")
    private WebElement disabledSaveButton;
    @FindBy(xpath = "//span[normalize-space()='Save']")
    private WebElement enabledSaveButton;

    @FindBy(xpath = "(//button[@id='check-in-routing-check-in-button'])[1]")
    private WebElement enabledCheckInButton;

    @FindBy(xpath = "(//button[.//span[normalize-space()='Save & Next']])[1]")
    private WebElement saveAndNextButton;

    @FindBy(xpath = "(//button[.//span[normalize-space()='Next']])[1]")
    private List<WebElement> saveButtons;

    @FindBy(xpath = "(//button[.//span[normalize-space()='Save & Next']])[2]")
    private WebElement saveAndNextButton2;

    @FindBy(css = "[id='confirmation-dialog-confirm-button']")
    private List<WebElement> confirmationButton;

    @FindBy(css = "[id*='mat-dialog-title']")
    private WebElement confirmationPopUP;

    @FindBy(xpath = "//icon[@icon='eye-opened']")
    private WebElement openEyeIcon;

    @FindBy(xpath = "//otalio-gangway-movement-container//mat-radio-button[2]//span[@class='mat-radio-outer-circle']")
    private WebElement shoreside;


    @FindBy(xpath = "//mat-checkbox[.//span[text()='Close Security Flag']]")
    private WebElement closeSecurityFlag;
    @FindBy(xpath = "//otalio-bulk-close-security-flag-port//mat-form-field[1]//mat-label[normalize-space()='Ashore/Boarding Denied']")
    private WebElement ashoreBoardingDenied;

    @FindBy(xpath = "//button[@id='create-new-reservation-cabin.skip-and-next-button']")
    private WebElement skipAndNextButton;
    @FindBy(xpath = "//*[@aria-label='Items per page: 100']")
    private WebElement itemsPerPage100;
    @FindBy(xpath = "//otalio-data-table//otalio-passenger-picture//img[contains(@src,'imageType=PROFILE')]")
    private List<WebElement> profileImages;
    @FindBy(css = "[icon='menu-dots']>svg")
    private List<WebElement> bulkOperationActionMenu;
    @FindBy(xpath = "//button[@id='items-equipment-personal-item-add-menu-button' and @disabled='true']")
    private WebElement crewEffectsButton;
    public void editSecurityFlags(String name, String button){
        String editButton = "//span[normalize-space()='"+name+"']/ancestor::tr/descendant::icon[starts-with(@icon,'"+button+"')]";
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath(editButton)));
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(editButton)));
        }catch (StaleElementReferenceException ser) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(editButton)));
        }
    }

    public String getReasonText(){
      return seleniumActions.getTextMessage(reasonText);
    }

    public WebElement getFirstNameTextWebElement(){
        return firstNameReservationTextMessage;
    }

    public void enterSecurity() {
        String dropDownName = "[formcontrolname='reasonId']";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.cssSelector(dropDownName)));
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.cssSelector(dropDownName)));
        String selectEmbarkationPortDropDownText = "(//mat-option[contains(@id,'otalio-infinite-select')])[1]";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectEmbarkationPortDropDownText)));
        String reasonText = "[placeholder='Reason Details']";
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(reasonText)),String.valueOf(commonFunctions.getRandomCharacter()));
    }

    public void addSecurityFlagButton(String button1){
        seleniumWaits.untilElementIsClickable(flagsButton);
        seleniumActions.moveToElementAndClick(flagsButton);
        seleniumActions.sleep(5000);
        String text = "//button[text()='" + button1 + "' or normalize-space()='" + button1 + "']";
        if (webDriver.findElements(By.xpath(text)).size() > 0){
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(text)));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
        }

    }

    public String buyerNameText(){
        seleniumWaits.untilElementInVisible(getBuyerName);
        return seleniumActions.getTextMessage(getBuyerName);
    }

    public void navigateToPreview(String button1) {
        seleniumActions.sleep(5000);
        String text = "//button//span[text()='" + button1 + "' or normalize-space()='" + button1 + "']";
        if (webDriver.findElements(By.xpath(text)).size() > 0){
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(text)));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
        }
    }

    public void clickOnCodeLink(String link){
        String codeLink = "//div[normalize-space()='"+link+"']/../..//following-sibling::td/div/a[contains(@id,'print-link')]";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(codeLink)));
    }

    public void selectFolder(String option){
        String dropDownName = "[placeholder*='Folder Name']";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.cssSelector(dropDownName)));
        String selectDropDownText = "//icon/following-sibling::div[normalize-space()='"+option+"']";
        String searchField = "[placeholder*='Folder Name']";
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(searchField)),option);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectDropDownText)));
    }

    public String getAccountName() {
        return seleniumActions.getTextMessage(accountName);
    }

    public List<String> systemAccountNames(){
        seleniumWaits.untilListElementIsVisible(webDriver.findElements(By.xpath("//td[@aria-colindex='2']/div/otalio-user-name/a")));
        return seleniumActions.getListOfElementsText(webDriver.findElements(By.xpath("//td[@aria-colindex='2']/div/otalio-user-name/a")));
    }

    public void clickPlusButtonInAllowedToPost(String accounts){
        String clickOnPlusButton = "//span[contains(text(),'System Accounts')]/ancestor::otalio-posting-linking-table-container/descendant::button[contains(@id,'posting-allowed-linking')]/span/icon";
        seleniumActions.highLighterMethod((webDriver.findElement(By.xpath(String.format(clickOnPlusButton,accounts)))));
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(clickOnPlusButton,accounts))));
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(clickOnPlusButton,accounts))));
    }

    public void enterReasonDetails(String text){
        seleniumActions.typeValue(reasonInputField,text);
    }

    public void personSecurityFlagsButton(String button) {
        seleniumActions.clickOnElement(threeDotsButton);
        String selectSecurityFlags = "//button[text()='" + button + "' or normalize-space()='" + button + "']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectSecurityFlags)));

    }

    public void navigateToSaveAndNext(String button1) {
        seleniumActions.sleep(5000);
        String text = "//button[contains(@id,'documents.save-and-next')]/span[text()='" + button1 + "' or normalize-space()='" + button1 + "']";
        if (webDriver.findElements(By.xpath(text)).size() > 0){
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(text)));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
        }
    }

    public WebElement getLastNameFieldTextWebElement(){
        return lastNameFieldTextMessage;
    }

    public WebElement getFirstNameFieldTextWebElement(){
        return firstNameFieldTextMessage;
    }

    public WebElement getDateOfBirthInputFieldText(){
        return embarkationDateFieldText;
    }

    public void setDisembarkationToday(String date){
//        seleniumActions.clickOnElement(disembarkationDateInputField);
        String calenderName = "//input[@placeholder='%s']/following-sibling::button[contains(@id,'show-datepicker')]/span/icon";
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath(String.format(calenderName,date))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(calenderName,date))));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        for(int i=0; i<10; i++) {
            if (webDriver.findElements(By.xpath("//otalio-datepicker/descendant::div[contains(@class,'position-relative today')]")).size() > 0) {
                seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//otalio-datepicker/descendant::div[contains(@class,'position-relative today')]")));
                seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("//span[normalize-space()='Save']")));
                break;
            } else{
                seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("//icon[contains(@icon,'arrow-left')][contains(@id,'otalio-datepicker-picker')]/*[name()='svg']/*[name()='use']")));
            }
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public List<String> getActionTypeListOfAll() {
        return actionTypeListOfAll.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void selectActionType(String type){
        seleniumActions.clickOnElement(actionTypeDropdown);
        String dropDown = "//span[text()='%s']/ancestor::div/child::mat-pseudo-checkbox";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(dropDown,type))));

    }

    public void clearDate(){
        seleniumActions.clickOnElement(clearDates);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(closeDate);
    }
    
    public WebElement getCommentTextWebElement(){
        return firstNameTextMessage;
    }

    public void enterDocumentNr(String number){
        seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
        String randomCode = generateRandomCode();
        seleniumActions.typeValue(documentNrInputField,randomCode);
    }

    public void selectCabinNumber(){
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(selectCabinCheckbox);
    }

    public void enterFirstAndLastName(String first,String last){
        seleniumActions.typeValue(firstNameInputField,first);
        seleniumActions.typeValue(lastNameInputField,last);
    }

    public By getInputByPlaceholder(String placeholderText) {
        By locator = By.xpath(String.format("//input[@placeholder='%s']", placeholderText));
        new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.SHORT_WAIT)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return locator;
    }

    public void setDates(String embarkationOrDisembarkation, String date) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date value must not be null or empty.");
        }
        WebElement dateField = webDriver.findElement(getInputByPlaceholder(embarkationOrDisembarkation));
        dateField.clear();
        dateField.sendKeys(date);
        commonPage.waitForDomReady();
    }

    public void setDatesExpiryOrBirth(String expiryOrBirth, String date) {
        String dateInput = "[formcontrolname='%s']> div > input";
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(String.format(dateInput, expiryOrBirth))), date);
        seleniumActions.sleep(10000);
    }


    public void disableTogglePosting(String toggleName){
        String toggleType = "//div[contains(text(),'%s')]/following-sibling::mat-slide-toggle/descendant::input";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))));
        System.out.println(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))).getAttribute("aria-checked"));
        WebElement toggle = webDriver.findElement(By.xpath(String.format(toggleType, toggleName)));
        if (toggle.getAttribute("aria-checked").equals("true")) {
            seleniumActions.clickOnElement(toggle);
        }
    }

    public String hideNoPrintsToggleButtonStatus(String toggleName) {
        String toggleType = "//div[contains(text(),'%s')]/following-sibling::mat-slide-toggle/descendant::input";
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))));
        return webDriver.findElement(By.xpath(String.format(toggleType,toggleName))).getAttribute("aria-checked");
    }

    public void enableTogglePosting(String toggleName) {
        String toggleType = "//div[contains(text(),'%s')]/following-sibling::mat-slide-toggle/descendant::input";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))));
        System.out.println(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))).getAttribute("aria-checked"));
        WebElement toggle = webDriver.findElement(By.xpath(String.format(toggleType, toggleName)));
        if (toggle.getAttribute("aria-checked").equals("false")) {
            seleniumActions.clickOnElement(toggle);
        }

    }

    public void selectAllAccountCheckbox(){
            seleniumActions.clickOnElement(selectAllCheckbox);
    }

    public void clickOnStatusLink(String link) {
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("//a[normalize-space()='" + link + "']")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//a[normalize-space()='" + link + "']")));
    }

    public List<String> getCrewAssignmentsAndContractInformationHeaders() {
        List<String> list = seleniumActions.getListOfElementsText(assignmentsAndContractInformationHeader);
        seleniumActions.sleep(3000);
        List<String> actualList = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            actualList.add(list.get(i));
        }
        return actualList;
    }

    public List<String> getCrewColumnHeaders() {
        List<String> list = seleniumActions.getListOfElementsText(crewColumnData);
        seleniumActions.sleep(3000);
        List<String> actualList = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            actualList.add(list.get(i));
        }
        return actualList;
    }

    public String postingStatusToggleButtonStatus() {
        seleniumWaits.untilElementIsVisible(postingStatusToggle);
        return postingStatusToggle.getAttribute("aria-checked");
    }

    public void enablePostingStatusToggle(String toggleName, String text) {
        String toggleType = "//span[contains(text(),'%s')]/preceding-sibling::span/input";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))));
        System.out.println(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))).getAttribute("aria-checked"));
        WebElement toggle = webDriver.findElement(By.xpath(String.format(toggleType, toggleName)));
        if (toggle.getAttribute("aria-checked").equals("false")) {
            seleniumActions.clickOnElement(toggle);
            seleniumActions.typeValue(commentInputField, text);
            String confirmButton = "//span[normalize-space()='Confirm']";
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(confirmButton))));
        }

    }

//    public void selectCheckBoxAddedCrew(){
//        String selectCheckBox = "(//div[@aria-label='Select row'])[1]";
//        WebElement checkBox = webDriver.findElement(By.xpath(selectCheckBox));
//        seleniumActions.moveToElementAndClick(checkBox);
//
//    }

    public String getStatusText(){
        seleniumActions.sleep(3000);
        return  seleniumActions.getTextMessage(statusText);
    }

    public void selectActionOption(String option){
        seleniumActions.sleep(3000);
        seleniumActions.moveToElementAndClick(selectAddedCrewEffectCheckbox);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(equipmentActionButton);
        String actionOption = "//button[normalize-space()='"+option+"']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(actionOption))));
    }

    public String getErrorMessageOnPersonalItems(){
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(errorMessage);
    }

    public void setQuantity(String number){
        seleniumActions.clickOnElement(quantityText);
        seleniumActions.typeValue(quantityText,number);
    }

    public String ashoreDeniedToggleButtonStatus() {
        return ashoreDeniedToggleButton.getAttribute("aria-checked");
    }

    public boolean crewProfilePicture(){
        commonPage.waitForDomReady();
        seleniumWaits.untilElementIsVisible(profilePicture);
        return seleniumActions.isElementDisplayed(profilePicture);
    }

    public List<WebElement> getSubheadingDataElements() {
        String operationData ="//otalio-crew-operations-details/descendant::div[contains(@class,'mb')]/div/following-sibling::div";
        return webDriver.findElements(By.xpath(operationData));
    }

    public List<String> getSubheadingDataTexts() {
        return getSubheadingDataElements().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public int getSubheadingDataCount() {
        return getSubheadingDataElements().size();
    }

    public List<WebElement> getSubheadingElements() {
        String operationList ="//otalio-crew-operations-details/descendant::div[contains(@class,'mb')]/div[contains(@class,'truncate')]";
        return webDriver.findElements(By.xpath(operationList));
    }

    public List<String> getSubheadingTexts() {
        return getSubheadingElements().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void selectDropDown(String filterName,String checkbox){
        String selectFilter = "//button//b[contains(text(),'"+filterName+"')]/../following-sibling::icon/*[name()='svg']/*";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(selectFilter))));
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(selectFilter))));
        String selectCheckBox ="//div[normalize-space()='" + checkbox + "']/preceding-sibling::mat-pseudo-checkbox";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(selectCheckBox))));
        seleniumActions.jsClick(webDriver.findElement(By.xpath(String.format(selectCheckBox))));
    }

    public void setBalance(String min,String max){
        seleniumActions.jsClick(arrowDown);
//        seleniumWaits.untilElementIsClickable(selectRadioButton);
//        seleniumActions.clickOnElement(selectRadioButton);
        seleniumActions.typeValue(minInputField,min);
        seleniumActions.typeValue(maxInputField,max);
        seleniumActions.moveToElementAndClick(clickCrewM);
    }

    public void searchFilters(String filter){
        seleniumActions.clickOnElement(searchFilter);
        seleniumActions.typeValue(searchFilter,filter);
        seleniumActions.clickOnElement(checkBoxFilter);
//        seleniumActions.clickOnElement(clickCrewM);
    }

    public void addMoreFilters(){
        seleniumActions.moveToElementAndClick(moreFilters);
        seleniumActions.sleep(3000);
    }

    public boolean getOperation(){
        return seleniumActions.isElementDisplayed(operations);
    }

    public void clickAndNavigateToCrewInformation(String crewFirstName) {
        seleniumActions.sleep(1000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//a[normalize-space()='" + crewFirstName + "']")));
    }

    public void openCrewDetails(String crewFirstName) {
        seleniumActions.typeValue(crewSearchBox, crewFirstName);
        clickAndNavigateToCrewInformation(crewFirstName);
    }

    public void clickNavigateToCrewInformation(String crewFirstName) {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//a[normalize-space()='" + crewFirstName + "'])[1]")));
    }

    public List<String> getcrewFullName() {
        seleniumWaits.untilElementIsVisible(crewLastAndFirstName.get(0));
        List<String> names = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            WebElement element = crewLastAndFirstName.get(i); // Get the WebElement from the list
            names.add(seleniumActions.getTextMessage(element)); // Add the text of the element to the list
        }
        System.out.println(names);
        return names;
    }
    public String getFullName(){
        return seleniumActions.getTextMessage(fullName);
    }

    public List<String> getAllCrewsInThePage(){
        seleniumActions.clickOnElement(By.cssSelector("[aria-label='Items per page: 100']"));
        seleniumActions.sleep(5000);
        seleniumWaits.untilListElementIsVisible(crewFirstNames);
        return seleniumActions.getListOfElementsText(crewFirstNames);
    }

    public String getCrewFirstName() {
        int index = commonFunctions.getRandomNumber(0,crewFirstNames.size());
        System.out.println("index :: " + index);
        String crewName = getAllCrewsInThePage().get(index);
        seleniumWaits.untilElementIsVisible(crewFirstName);
        //return seleniumActions.getTextMessage(crewFirstName);
        return crewName;
    }

    public String getCrewStatus(){
        seleniumActions.sleep(5000);
        return seleniumActions.getTextMessage(userBoardStatus);
    }

    public boolean getUserStatus(){
        return seleniumActions.isElementDisplayed(userBoardStatus);
    }

    public String getErrorMessageOnBulkOperation(){
        seleniumWaits.untilElementIsVisible(bulkErrorMessage);
        return seleniumActions.getTextMessage(bulkErrorMessage);
    }

    public void clickOnStart(String button1) {
        String text = "//button[text()='" + button1 + "' or normalize-space()='" + button1 + "']";
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath(text)));
        if (webDriver.findElements(By.xpath(text)).size() > 0){
            seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath(text)));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
        }
    }

    public int getIndexOfColumnName(String columnName) {
        List<String> columns = new ArrayList<>();
        for (int i = 1; i < headerItems.size(); i++) {
            try {
                seleniumActions.sleep(500); //due to table header loading given 500 millisecs
                seleniumWaits.untilElementIsClickable(headerItems.get(1));
                columns.add(seleniumActions.getTextMessage(headerItems.get(i)));
            } catch (StaleElementReferenceException ste) {
                By tableHeader = By.xpath("(//table/tbody/tr/td[@role='columnheader']/parent::tr)[1]/td/div[contains(@class,'text-content')]");
                columns.add(seleniumActions.getTextMessage(webDriver.findElements(tableHeader).get(i)));
            }
        }

        int columnIndex = columns.indexOf(columnName);
        return columnIndex;
    }

    public String getDataOfTheSpecificColumnData(String columnName) {
        int index = getIndexOfColumnName(columnName) + 2;
        System.out.println("index :: " + index);
        WebElement cg = webDriver.findElement(By.xpath("" +
                "//tbody/tr/td/div[text()='" + columnName + "']/ancestor::table/tbody/tr/td[@role='columnheader'][not(contains(@class,'select'))]/div[text()]/following::table/tbody/tr[1]/td[not(contains(@class,'select'))][" + index + "]"));
        System.out.println("Data" + cg.getText());
        return cg.getText();
    }

    public String getDataOfStatus(String columnName) {
        int index = getIndexOfColumnName(columnName) + 12;
        System.out.println("index :: " + index);
        WebElement cg = webDriver.findElement(By.xpath("" +
                "//tbody/tr/td/div[text()='" + columnName + "']/ancestor::table/tbody/tr/td[@role='columnheader'][not(contains(@class,'select'))]/div[text()]/following::table/tbody/tr[1]/td[not(contains(@class,'select'))][" + index + "]"));
        System.out.println("Data" + cg.getText());
        return cg.getText();
    }

    public void updatePersonReservation() {
        seleniumActions.clickOnElement(updateReservation);
    }

    public void updateDate(String date) {
        String setDate = "(//otalio-datepicker-input[contains(@class,'touched')]/div/input)[2]";
        seleniumActions.typeValue(webDriver.findElement(By.xpath(String.format(setDate))), date);
//        seleniumActions.clickOnElement(clickOp);
        if(commonPage.untilElementIsVisible(clickOp))
        {
            seleniumActions.clickOnElement(clickOp);
        }
    }

    public String validateStatus() {
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//tbody/tr/td[@role='gridcell' and @aria-colindex='7']/descendant::otalio-chip/div/div")));
    }

    public String validateErrorCount(int errorCount) {
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//tbody/tr/td[@role='gridcell' and @aria-colindex='4']")));
    }

    public String validateSuccessCount(int userCount) {
        webDriver.navigate().refresh();
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//tbody/tr/td[@role='gridcell' and @aria-colindex='3']//a")));
    }

//    public void selectRelatedCrew(int userCount) {
//        seleniumWaits.untilElementIsVisible(selectedCheckBox);
//        seleniumActions.clickOnElement(selectedCheckBox);
//        seleniumWaits.untilElementIsVisible(unselectButton);
//        seleniumActions.clickOnElement(unselectButton);
//        ArrayList<String> firstNames = new ArrayList<>();
//        for (int i = 1; i <= userCount; i++) {
//            String selectCheckBox = "(//div[@aria-label='Select row'])[" + i + "]";
//            WebElement checkBox = webDriver.findElement(By.xpath(selectCheckBox));
//            if (!checkBox.isSelected()) {
//                seleniumActions.clickOnElement(checkBox);
//            }
//            String getFirstName = "(//tbody/tr/td[@role='gridcell' and @aria-colindex='3'])[" + i + "]";
//            String firstNameText = webDriver.findElement(By.xpath(getFirstName)).getText();
//            firstNames.add(firstNameText);
//        }
//        for (String eachName : firstNames) {
//            System.out.println(eachName);
//        }
//    }

    public void selectRandomRelatedCrew(int userCount) {
        seleniumWaits.untilElementIsVisible(selectedCheckBox);
        try {
            seleniumActions.clickOnElement(selectedCheckBox);
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(selectedCheckBox);
        }
        seleniumWaits.untilElementIsClickable(unselectButton);
        try {
            seleniumActions.moveToElementAndClick(unselectButton);
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(unselectButton);
        }
        List<WebElement> checkboxes = webDriver.findElements(By.xpath("//div[@aria-label='Select row']"));
        int totalUsers = checkboxes.size();
        if (userCount > totalUsers) {
            System.out.println("Requested user count exceeds available users.");
            return;
        }
        Set<Integer> selectedIndices = new HashSet<>();
        Random random = new Random();
        while (selectedIndices.size() < userCount) {
            int randomIndex = random.nextInt(totalUsers);
            selectedIndices.add(randomIndex);
        }
        ArrayList<String> firstNames = new ArrayList<>();
        for (int index : selectedIndices) {
            WebElement checkBox = checkboxes.get(index);
            if (!checkBox.isSelected()) {
                seleniumActions.clickOnElement(checkBox);
            }
            String firstNameXpath = "(//tbody/tr/td[@role='gridcell' and @aria-colindex='3'])[" + (index + 1) + "]";
            String firstNameText = webDriver.findElement(By.xpath(firstNameXpath)).getText();
            firstNames.add(firstNameText);
        }
        for (String eachName : firstNames) {
            System.out.println("Second name::" +eachName);
            System.out.println("first name::" +firstNames);
        }
    }

    public void selectCrewFirstName() {
        try {
            seleniumActions.clickOnElement(crewFirstName);
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//tbody/tr/td[@aria-colindex='4']/div/a)[1]")));
        }
    }

    public void threeDotsIconButton() {
//        seleniumWaits.untilElementIsVisible(threeDotsIcon);
        seleniumActions.moveToElementAndClick(threeDotsIcon);

 /*       try {
            seleniumWaits.untilElementIsVisible(threeDotsIcon);
            seleniumActions.moveToElementAndClick(threeDotsIcon);
        }catch (Exception e) {
            List<WebElement> dots = webDriver.findElements(By.cssSelector("table icon[icon='menu-dots']"));
            dots.get(1).click();
        }
*/

    }

    public void clickOnConfirmButton() {
        seleniumActions.sleep(3000);
        seleniumActions.moveToElementAndClick(confirmButton);
    }

    public void clickOnCheckBox(String checkBox) {
        String clickCheckbox = "//span[text()='"+checkBox+"']/../../label/span/input[@type='checkbox']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(clickCheckbox))));
    }

    public void selectRadioButton(String radio) {
        String radioButton = "//span[contains(text(),'%s')]/../../label/span/input[@class='mat-radio-input']";
        WebElement clickRadioButton = webDriver.findElement(By.xpath(String.format(radioButton, radio)));
        if (clickRadioButton.isSelected()) {
            System.out.println("The radio button is already selected.");
        } else {
            System.out.println("The radio button is not selected.");
            seleniumActions.clickOnElement(clickRadioButton);
        }
    }

    public void clickNextButton() {
        seleniumWaits.untilElementIsClickable(nextButton);
        seleniumActions.clickOnElement(nextButton);
    }

    public void bulkOperation() {
        seleniumActions.clickOnElement(threeDots);
        seleniumActions.clickOnElement(bulkButton);
    }

    public String getCrewCount(String onBoardAshore){
        String path = "//a[text()='%s']/following-sibling::span";

        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(path,onBoardAshore))));
        seleniumWaits.untilElementIsPresent(By.xpath(String.format(path,onBoardAshore)));
        seleniumActions.sleep(5000);
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath(String.format(path,onBoardAshore))));
    }

    public void changeAssignedStatus(String status){
        seleniumActions.sleep(3000);
        seleniumActions.moveToElementAndClick(assignedDropdown);
        seleniumActions.sleep(2000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//*[contains(@class,'checked')]")));
        if(webDriver.findElement(By.xpath("//div[normalize-space()='"+status+"']/preceding-sibling::mat-pseudo-checkbox")).getAttribute("class").contains("checked")) {
            System.out.println("CheckBox :: " + status);
        }else {
            seleniumActions.jsClick(webDriver.findElement(By.xpath("//div[normalize-space()='"+status+"']/preceding-sibling::mat-pseudo-checkbox")));
        }

        seleniumActions.sleep(3000);
        seleniumActions.pressEscapeKey();
    }

    public void clearReservationStatus(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        if (seleniumActions.getTextMessage(reservationStatus).contains("All")) {
            System.out.println("Reservation Status is already set to All");
        } else {
            seleniumActions.clickOnElement(reservationStatusList);
            seleniumActions.clickOnElement(By.xpath("//span[text()='Clear previous selection']"));
            seleniumActions.sleep(2000);
        }
//        seleniumActions.doubleClickOnElement(
//                webDriver.findElement(By.xpath("//span[normalize-space()='Guests' or normalize-space()='Crew Members' or contains(text(),'Group Reservation Search') or contains(text(),'Bulk Crew Cabin Assignment')]")));
    }

    public void clearOperationPosition(){
        if (seleniumActions.getTextMessage(operationClick).contains("All")) {
            System.out.println("Reservation Status is already set to All");
        } else {
            seleniumActions.clickOnElement(operationPosition);
            seleniumActions.clickOnElement(By.xpath("//span[text()='Clear previous selection']"));
            seleniumActions.sleep(2000);
        }
        seleniumActions.pressEscapeKey();
    }

    public String getCabinNumber(){
//        seleniumWaits.untilElementIsVisible(cabinNumber);
        commonPage.waitForDomReady();
        seleniumWaits.untilElementIsVisible(cabinNumber);
        seleniumActions.highLighterMethod(cabinNumber);
        return cabinNumber.getText().trim();
    }

    public void selectCabinNumber(String cabinNumber){
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//h4[normalize-space()='"+cabinNumber+"']/ancestor::div/div/div/mat-checkbox/label/span/input)[1]")));
    }

    public String getCrewNameForCabinAssignment(){
        seleniumWaits.untilElementIsVisible(crewNameForCabinAssignment,GlobalConstants.SHORT_WAIT);
        seleniumActions.highLighterMethod(crewNameForCabinAssignment);
        return crewNameForCabinAssignment.getText().trim();
    }

    public void clearFilters(){
        try {
            if (cabinFilter.isDisplayed()) {
                seleniumActions.clickOnElement(cabinFilter);
            }
        }catch (Exception e){
            System.out.println("No cabin filter");
        }
        seleniumActions.clickOnElement(embarkationDatePicker);
        for (int i=0; i< closeIcon.size(); i++){
            seleniumActions.sleep(1000);
            seleniumActions.clickOnElement(closeIcon.get(i));
            seleniumActions.sleep(1000);
        }

        if(closeIcon.size()>0){
            closeIcon.get(0).click();
        }
        seleniumActions.pressEscapeKey();
        seleniumActions.moveToElementAndClickOnElement(shipLocation, shipLocation);
    }

    public void selectCrewForCabinAssign(String crewName){
//        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//*[text()='"+crewName+"']/ancestor::tr/td[1]")),"Checkbox");
//        seleniumActions.jsClick(webDriver.findElement(By.xpath("//span[normalize-space()='"+crewName+"']/ancestor::td/../td[1]")));
       seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//span[@class='dx-checkbox-icon'])[2]")));
        seleniumActions.highLighterMethod(webDriver.findElement(By.xpath("(//span[@class='dx-checkbox-icon'])[2]")));

        seleniumActions.sleep(5000);
    }
    public void selectCabinForCrew(String cabinNumber){
        selectCabinNumber(cabinNumber);
        seleniumActions.highLighterMethod(assignButton);
        seleniumActions.sleep(5000);
        seleniumActions.clickOnElement(assignButton);
//        seleniumActions.sleep(5000);
    }

    public void unAssignCabinForCrew(){
        seleniumActions.highLighterMethod(unAssignButton);
        seleniumActions.sleep(5000);
        seleniumActions.clickOnElement(unAssignButton);
//        seleniumActions.sleep(5000);
    }

    public String getCabinNumberOnStatus(){
        seleniumActions.sleep(3000);
        return cabinNumberOnStatus.getText().trim();
    }

    public void navigateToSaveAndOpenProfile(String button1) {
        seleniumActions.sleep(3000);
        String text = "//button[contains(@id,'photo.submit-button')]//span[normalize-space()='"+button1+"']";
        if (webDriver.findElements(By.xpath(text)).size() > 0){
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(text)));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
        }
    }

    public String getCabinNumberOnCrewInformationPage(){
        commonPage.waitForDomReady();
        seleniumWaits.untilElementIsVisible(cabinNumberOnCrewInformationPage);
        seleniumActions.highLighterMethod(cabinNumberOnCrewInformationPage);
        return cabinNumberOnCrewInformationPage.getText().trim();
    }

    public List<String> getStatus(){
        List<String> data = new ArrayList<>();
        for(int i=0; i<status.size(); i++){
            data.add(status.get(i).getText());
        }
        return data;
    }

    public List<String> getCountryAndLanguage(){
        List<String> data = new ArrayList<>();
        for(int i=0; i<countryAndLanguage.size(); i++){
            data.add(countryAndLanguage.get(i).getText());
        }
        return data;
    }
    public String getCrewName(){
        return crewName.getText().trim();
    }

    private By getCrewIdCellByFullName(String firstName, String lastName) {
        return By.xpath(String.format(
                "//tr[td[normalize-space()='%s'] and td[normalize-space()='%s']]/td[3]//div//a",
                lastName, firstName
        ));
    }

    public void clickCrewFirstName(String firstName, String lastName) {
        commonPage.waitForDomReady();
        List<WebElement> elements = webDriver.findElements(getCrewIdCellByFullName(firstName, lastName));

        if (!elements.isEmpty()) {
            WebElement element = elements.get(0);
            seleniumActions.highLighterMethod(element);
            seleniumActions.sleep(5000);
            Actions actions = new Actions(webDriver);
            actions.moveToElement(element)
                    .pause(Duration.ofMillis(2000))
                    .click()
                    .build()
                    .perform();
        }
    }
    public void userShouldNotBeAbleToAddCrewEffectsToCheckedOutCrewMembers(){
        seleniumActions.scrollInToElement(addIcon);
        seleniumActions.moveToElement(addIcon);
        seleniumActions.highLighterMethod(addIcon);
        String disabledAttribute = addIcon.getAttribute("disabled");
        Assert.assertEquals(disabledAttribute, "true", "Add button is not disabled as expected.");
       logger.info("Add button has disabled attribute");
    }
    public void userAbleEnableTheToggleSliderInNewProfile(){
        Actions actions = new Actions(webDriver);

        seleniumWaits.untilElementIsVisible(toggleLocator);
        seleniumActions.scrollInToElement(toggleLocator);

        Assert.assertTrue(toggleLocator.isDisplayed(), "Toggle is not displayed");
        Assert.assertTrue(toggleLocator.isEnabled(), "Toggle is not enabled");

        try {
            actions.moveToElement(toggleLocator).click().perform();
        } catch (Exception ex) {
            seleniumActions.jsClick(toggleLocator);
        }
    }
    public void userAbleClickTheTabOptions(){
        seleniumActions.highLighterMethod(reservationData);
        seleniumActions.clickOnElement(reservationData);
        commonPage.waitForDomReady();
    }
    public void searchGuestUsingAlphabetic(){
        try {
            seleniumActions.clickOnElement(shipSecurityPage.getSearchIcon());
        } catch (JavascriptException je) {
            seleniumActions.moveToElementAndClickOnElement(shipSecurityPage.getSearchIcon(), shipSecurityPage.getSearchIcon());
        }
        seleniumActions.typeValue(shipSecurityPage.getSearchInputBox(), String.valueOf(commonFunctions.getRandomCharacter()));
    }

//    public void enableIntoxicatedToggle() {
//
//        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
//
//        By intoxicatedToggle = By.xpath(
//                "//span[normalize-space()='Intoxicated']" +
//                        "/ancestor::label//input[@type='checkbox']"
//        );
//
//        WebElement toggleInput = wait.until(
//                ExpectedConditions.elementToBeClickable(intoxicatedToggle)
//        );
//
//        if (!toggleInput.isSelected()) {
//
//            WebElement label = toggleInput.findElement(By.xpath("./ancestor::label"));
//            label.click();
//
//            wait.until(ExpectedConditions.attributeToBe(toggleInput, "aria-checked", "true"));
//        }
//
//        Assert.assertTrue(toggleInput.isSelected(), "Intoxicated toggle should be enabled.");
//    }

    public void enableIntoxicatedToggle() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        // Locate the checkbox input inside the Intoxicated toggle
        By intoxicatedCheckbox = By.xpath(
                "//label[.//span[contains(normalize-space(), 'Intoxicated')]]//input[@type='checkbox']"
        );

        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(intoxicatedCheckbox));

        // Check current state using aria-checked attribute
        String isChecked = checkbox.getAttribute("aria-checked");

        if ("false".equals(isChecked)) {
            // Click the label (more reliable for mat-slide-toggle)
            WebElement toggleLabel = webDriver.findElement(
                    By.xpath("//label[.//span[contains(normalize-space(), 'Intoxicated')]]")
            );
            toggleLabel.click();

            wait.until(ExpectedConditions.attributeToBe(checkbox, "aria-checked", "true"));
        }
//        seleniumWaits.untilElementIsClickable(saveButtonButton);
//
//        webDriver.findElement(By.xpath("//button[normalize-space()='Save']"));
//        seleniumActions.clickOnElement(saveButtonButton);
    }
    public List<WebElement> getSecondRowEmptyCabinCell() {
        return webDriver.findElements(By.xpath("(//tr/td[last()][not(normalize-space())])[2]"));
    }
    public int getTabCount(String tab) {
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[contains(text(),'" + tab + "')]"));
        if (!elements.isEmpty()) {
            seleniumActions.scrollInToElement(elements.get(0));
        }
        return elements.size();
    }
    public void searchCabinNumber(String cabinNumber) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement search = webDriver.findElement(searchBox);
        search.clear();
        search.sendKeys(cabinNumber);
    }
    public String generateRandomCode() {
        return String.valueOf(commonFunctions.getRandomNumber(GlobalConstants.INITIAL_NUMBER, GlobalConstants.END_NUMBER));
    }
    public void userSelectsTheFromTheDropdown(String dropdownText){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(dropdownText,CrewInformationConstants.DROPDOWN_VALUE_ONE);
    }
    public void userClearTheReservationStatusOfTheCrew(){
        seleniumActions.clickOnElement(reservationStatusList);
        seleniumActions.clickOnElement(webDriver.findElement(expectToday));
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
        seleniumActions.pressEscapeKey();
    }
    public void userSelectThe(String dropdown){
        try {
            seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
            seleniumActions.clickOnElement(nationalityText);
            WebElement searchBox = webDriver.findElement(searchBar);
            searchBox.sendKeys(CrewInformationConstants.COUNTRY_TEXT);
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.MINI_WAIT));
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(getDropdownOption(CrewInformationConstants.COUNTRY_TEXT)));
            option.click();

        } catch (Exception ex) {
            junit.framework.Assert.fail("Unable to select activity type: " + CrewInformationConstants.COUNTRY_TEXT);
        }
    }
    public By getDropdownOption(String optionText) {
        String xpath = String.format("//span[@class='mat-option-text' and contains(text(),'%s')]", optionText);
        return By.xpath(xpath);
    }
    public List<WebElement> getDeleteIcons() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return webDriver.findElements(deleteIconLocator);
    }

    public List<WebElement> getEditIcons() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return webDriver.findElements(editIconLocator);
    }
    public void clickFirstDeleteIcon() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<WebElement> elements = getDeleteIcons();
        seleniumActions.jsClick(elements.get(0));
    }

    public void clickFirstEditIcon() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<WebElement> elements = getEditIcons();
        seleniumActions.jsClick(elements.get(0));
    }
    public int getDeleteIconCount() {
        return getDeleteIcons().size();
    }

    public int getEditIconCount() {
        return getDeleteIcons().size();
    }

    public void addingThedata(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(CrewInformationConstants.ITEM_TYPE,CrewInformationConstants.DROPDOWN_VALUE_ONE);
        seleniumActions.selectDropdownOption(CrewInformationConstants.CODE,CrewInformationConstants.DROPDOWN_VALUE_ONE);
    }

    public void enterFirstName(String firstName){
        seleniumActions.typeValue(firstNameInputField,firstName);

    }

    public void enterLastName(String lastName){
        seleniumActions.typeValue(lastNameInputField,lastName);
    }


    public String getFullNameText(){
        seleniumActions.sleep(3000);
        return  seleniumActions.getTextMessage(fullNameText);
    }

    public String getEmbarkationDateText(){
        seleniumActions.sleep(3000);
        return  seleniumActions.getTextMessage(embarkationDateText);
    }

    public String getDisembarkationDateText(){
        seleniumActions.sleep(3000);
        return  seleniumActions.getTextMessage(disembarkationDateText);
    }

    public String getProfileStatusText(){
        seleniumActions.sleep(3000);
        return  seleniumActions.getTextMessage(profileStatusText);
    }

    public String getCrewMemberDemographicsText(){
        return  seleniumActions.getTextMessage(crewMemberDemographics);
    }
    public int verifyEmergencyContactInformationIsDisplayed() {
        seleniumActions.clickOnElement(yesConfirmButton);
        int count = emergencyContactCards.size();
        for (WebElement emergencyContactCard : emergencyContactCards) {
            seleniumActions.highLighterMethod(emergencyContactCard);
        }
        return count;
    }
    public void userAddTheAshoreDenied(){
        seleniumActions.clickOnElement(threeDotsMenu);
        seleniumActions.highLighterMethod(ashoreDeniedButton);
    }
    public void userShouldBeAbleToSelectNoneAsTheDropdown(String salutation){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(salutationDropdown);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.sendKeys(salutationSearchField,salutation);
        seleniumActions.clickOnElement(firstOption);
        seleniumActions.clickOnElement(updateButton);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public String getGuestProfileName() {
        seleniumActions.scrollInToElement(guestName);
        seleniumActions.highLighterMethod(guestName);
        return guestName.getText().trim();
    }
    public void selectSalutation(String salutation) {
        seleniumActions.scrollInToElement(salutationDropdown);
        seleniumActions.clickOnElement(salutationDropdown);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.sendKeys(salutationSearchField,salutation);
        seleniumActions.clickOnElement(secondOption);
    }

    public List<WebElement> getPaymentMethodAssignmentForms() {
        return paymentMethodAssignmentForms;
    }

    public void highlightPaymentMethodAssignmentForms() {
        for (WebElement form : paymentMethodAssignmentForms) {
            seleniumActions.highLighterMethod(form);
        }
    }
    public void financialDepartmentDropDown(){
        seleniumActions.clickOnElement(financialDepartmentDropDown);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public void clickOnEnabledOptionText(){
        if (!enabledOptions.isEmpty()) {
            seleniumActions.jsClick(enabledOptions.get(0));
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        }
    }

    public String getDisabledPaymentMethod() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return disabledPaymentMethod.getText().trim();
    }

    public String getAccountStatementPaymentMethod() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumWaits.untilElementIsVisible(accountStatementPaymentMethod);
        seleniumActions.scrollInToElement(accountStatementPaymentMethod);
        return accountStatementPaymentMethod.getText().trim();
    }
    public void displayDisabledActiveCheckBox() {
        seleniumActions.highLighterMethod(disabledActiveCheckBox);
    }
    public void clickEnableActiveCheckBox() {
        seleniumActions.highLighterMethod(enabledActive);
        seleniumActions.jsClick(enabledActiveCheckBox);
    }

    public int highlightCrewReservationLogs() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        for (WebElement crewLog : crewReservationLogsRows) {
            seleniumActions.highLighterMethod(crewLog);
        }
        return crewReservationLogsRows.size();
    }
    public WebElement middleNameMandatoryText() {
        return webDriver.findElement(By.xpath("(//input[@data-placeholder='Middle Name'])[1]"));
    }

    public WebElement lastNameMandatoryText() {
        return webDriver.findElement(By.xpath("(//input[@placeholder='Last Name' and @aria-required='true'])[1]"));
    }

    public WebElement genderMandatoryText() {
        return webDriver.findElement(By.xpath("//mat-select[@aria-haspopup='true' and contains(@id,'gender')]"));
    }

    public WebElement nationalityMandatoryText() {
        return webDriver.findElement(By.xpath("//mat-select[@aria-haspopup='true' and contains(@id,'nationality')]"));
    }
    public List<String> getNonMandatoryFields() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement middleName = middleNameMandatoryText();
        seleniumActions.sendKeys(middleName," ");

        List<String> nonMandatoryFields = new ArrayList<>();

        try {
            WebElement lastName = lastNameMandatoryText();
            seleniumActions.highLighterMethod(lastName);
        } catch (Exception e) {
            nonMandatoryFields.add("Last Name");
        }

        try {
            WebElement gender = genderMandatoryText();
            seleniumActions.highLighterMethod(gender);
        } catch (Exception e) {
            seleniumActions.selectDropdownOption(CrewInformationConstants.GENDER, ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        }

        try {
            WebElement nationality = nationalityMandatoryText();
            seleniumActions.highLighterMethod(nationality);
        } catch (Exception e) {
            seleniumActions.selectDropdownOption(CrewInformationConstants.NATIONALITY, ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        }
        return nonMandatoryFields;
    }
    public boolean isSaveButtonEnabled() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        try {
            seleniumActions.highLighterMethod(enabledSaveButton);
            seleniumActions.clickOnElement(enabledSaveButton);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.highLighterMethod(enabledCheckInButton);
            return true;
        } catch (NoSuchElementException e) {
            seleniumActions.highLighterMethod(disabledSaveButton);
            return true;
        }
    }
    public void userClickOnButtonInCheckinPage(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.jsClick(enabledCheckInButton);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public By getQuickLinkLocator(String quickLinkName) {
        return By.xpath("//div[normalize-space()='" + quickLinkName + "']/a");
    }

    public void clickQuickLink(String quickLinkName) {

        WebElement quickLink = webDriver.findElement(getQuickLinkLocator(quickLinkName));
        seleniumWaits.untilElementIsClickable(quickLink);
        seleniumActions.highLighterMethod(quickLink);
        seleniumActions.clickOnElement(quickLink);
    }
    private By getButtonByText(String buttonName) {
        return By.xpath("//button//span[normalize-space()='" + buttonName + "']");
    }

    public void userClicksOnTheButton(String buttonName) {
        WebElement button = webDriver.findElement(getButtonByText(buttonName));
        seleniumActions.highLighterMethod(button);
        seleniumActions.clickOnElement(button);
    }
    public void clickSaveButton(){
        if (!saveButtons.isEmpty() && saveButtons.get(0).isDisplayed()) {
            seleniumActions.moveToElementAndClick(saveButtons.get(0));
        }
    }

    public void clickSaveAndNextButton(){
        seleniumWaits.untilElementIsVisible(saveAndNextButton);
        seleniumActions.highLighterMethod(saveAndNextButton);
        seleniumActions.clickOnElement(saveAndNextButton);
    }
    public void clickSaveAndNextButtonInAssignCabin(){
        seleniumWaits.untilElementIsVisible(saveAndNextButton2);
        seleniumActions.highLighterMethod(saveAndNextButton2);
        seleniumActions.clickOnElement(saveAndNextButton2);
    }
    public void cabinBookingConformationPopUp(){
        seleniumWaits.untilElementIsVisible(confirmationPopUP);
        if (!confirmationButton.isEmpty() && confirmationButton.get(0).isDisplayed()) {
            seleniumActions.clickOnElement(confirmationButton.get(0));
        }
    }
    public void clickOnEyeIcon(){
        seleniumWaits.untilElementIsVisible(openEyeIcon);
        seleniumActions.clickOnElement(openEyeIcon);
    }
    public void clikeShoreside(){
        seleniumWaits.untilElementIsVisible(shoreside);
        seleniumActions.clickOnElement(shoreside);
    }
    public By getDropdownArrowByLabel(String label) {
        return By.xpath(String.format("//mat-form-field[.//*[normalize-space(text())='%s']]//div[@aria-label='Select' or contains(@class,'mat-select-arrow-wrapper')]", label));
    }

    public void selectTemplate(String templateName){
        seleniumWaits.untilElementIsVisible(webDriver.findElement(getDropdownArrowByLabel(CrewInformationConstants.TEMPLATE)));
        seleniumActions.selectDropdownOption(CrewInformationConstants.TEMPLATE,templateName);
    }
    public void selectPastEmbarkationDates() {
        List<WebElement> dateFields = webDriver.findElements(By.xpath("//input[@role='combobox']"));

        String fromDate = commonFunctions.getDateIncrementedBy(-5, "M/d/yyyy");
        String toDate = commonFunctions.getDateIncrementedBy(-1, "M/d/yyyy");

        String[] dates = {fromDate, toDate};

        for (int i = 0; i < dateFields.size(); i++) {
            seleniumActions.clickOnElement(dateFields.get(i));
            seleniumActions.sendKeys(dateFields.get(i), dates[i]);
        }
    }
    public void userScrollDownToCloseSecurityFlag(){
        seleniumWaits.untilElementIsVisible(closeSecurityFlag);
        seleniumActions.scrollInToElement(ashoreBoardingDenied);
    }
    public By getSecurityFlagOption(String expectedValue) {
        return By.cssSelector(String.format("[id^='person-security-flag-'][id$='.%s-select']", expectedValue.toLowerCase(Locale.ROOT)));
    }
    public By getShoreBoardingDropdownArrowByLabel(String label) {
        return By.xpath(String.format("//otalio-bulk-close-security-flag-port//mat-form-field[.//*[normalize-space(text())='%s']]//div[@aria-label='Select' or contains(@class,'mat-select-arrow-wrapper')]", label));
    }

    public boolean isOptionDisplayed(String optionName, String expectedValue) {

        WebElement dropdownArrow = webDriver.findElement(getShoreBoardingDropdownArrowByLabel(optionName));
        WebElement option = null;

        try {
            seleniumActions.clickOnElement(dropdownArrow);
            option = webDriver.findElement(getSecurityFlagOption(expectedValue));

            if (!option.isDisplayed()) {
                seleniumActions.jsClick(dropdownArrow);
                option = webDriver.findElement(getSecurityFlagOption(expectedValue));
                seleniumActions.highLighterMethod(option);
                option.click();
            }

        } catch (Exception e) {
            seleniumActions.jsClick(dropdownArrow);
            option = webDriver.findElement(getSecurityFlagOption(expectedValue));
            seleniumWaits.untilElementInVisible(option);
            seleniumActions.highLighterMethod(option);
            option.click();
        }
        return option.isDisplayed();
    }

    public boolean isCrewMemberPresent(Path excelFile, String firstName, String lastName, String crewId){


        try (InputStream inputStream = Files.newInputStream(excelFile);
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            Row header = sheet.getRow(sheet.getFirstRowNum());
            int lastNameCol = findColumn(header, "Last Name", formatter);
            int firstNameCol = findColumn(header, "First Name", formatter);
            int crewIdCol = findColumn(header, "Crew ID", formatter);

            if (firstNameCol < 0 || lastNameCol < 0 || crewIdCol < 0) {
                throw new IllegalArgumentException("Required columns not found. Required: First Name, Last Name, Crew ID");
            }

            for (int rowNum = sheet.getFirstRowNum() + 1;
                 rowNum <= sheet.getLastRowNum(); rowNum++) {

                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }

                String excelFirstName = value(row.getCell(firstNameCol), formatter);
                String excelLastName = value(row.getCell(lastNameCol), formatter);
                String excelCrewId = value(row.getCell(crewIdCol), formatter);

                if (excelFirstName.equalsIgnoreCase(firstName.trim())
                        && excelLastName.equalsIgnoreCase(lastName.trim())
                        && excelCrewId.equals(crewId.trim())) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    private static int findColumn(Row header, String expected, DataFormatter formatter) {
        if (header == null) {
            return -1;
        }

        for (Cell cell : header) {
            if (formatter.formatCellValue(cell).trim().equalsIgnoreCase(expected)) {
                return cell.getColumnIndex();
            }
        }
        return -1;
    }

    private static String value(Cell cell, DataFormatter formatter) {
        return cell == null ? "" : formatter.formatCellValue(cell).trim();
    }

    public String getFirstCrewFirstName() {
        WebElement element = webDriver.findElements(By.cssSelector("[id*='crew-list-page-first-name']")).get(0);
        seleniumActions.highLighterMethod(element);
        return element.getText().trim();
    }

    public String getFirstCrewLastName() {
        WebElement element = webDriver.findElements(By.cssSelector("[id*='crew-list-page-last-name']")).get(0);
        seleniumActions.highLighterMethod(element);
        return element.getText().trim();
    }

    public String getFirstCrewId() {
        WebElement element = webDriver.findElements(By.xpath("(//td[@aria-colindex='5'])[position() > 1]")).get(0);
        seleniumActions.highLighterMethod(element);
        return element.getText().trim();
    }

    public void clickSkipAndNextButton() {
        seleniumWaits.untilElementIsVisible(skipAndNextButton,GlobalConstants.SHORT_WAIT);
        seleniumWaits.untilElementIsClickable(skipAndNextButton);
        seleniumActions.jsClick(skipAndNextButton);
    }
    public void userSearchTheProfilePhotoCrewUser(){
        seleniumWaits.untilElementIsVisible(itemsPerPage100);
        seleniumActions.jsClick(itemsPerPage100);
        if (!profileImages.isEmpty()) {
            profileImages.get(0);
        }
    }
    public void navigatesToProfilePhotoCabinCrewProfile(){
        seleniumActions.highLighterMethod(profileImages.get(0));
        seleniumActions.clickOnElement(profileImages.get(0));
    }
    public boolean isCrewMemberNameDisplayed() {
        By crewMemberName = By.xpath(" //otalio-profile-card/descendant::h1[contains(text(),'')]");
        seleniumActions.highLighterMethod(webDriver.findElement(crewMemberName));
        return !webDriver.findElements(crewMemberName).isEmpty();
    }
    public String getCrewInfoText() {
        By crewInfoText = By.xpath("//otalio-profile-card//div[contains(@class,'text-type-info')]");
        seleniumActions.highLighterMethod(webDriver.findElement(crewInfoText));
        return webDriver.findElement(crewInfoText).getText().trim();
    }
    public boolean isCrewBoardStatusDisplayed() {
        By boardStatus = By.xpath("//otalio-person-board-status");
        seleniumActions.highLighterMethod(webDriver.findElement(boardStatus));
        return !webDriver.findElements(boardStatus).isEmpty();
    }

    public List<String> getEmbarkationAndDisembarkationDetails() {
        By embarkDisembarkDetails = By.xpath("//otalio-embark-disembark-card//div[contains(@class,'text-type-info')]");
        List<WebElement> elements = webDriver.findElements(embarkDisembarkDetails);
        return elements.stream().map(WebElement::getText).map(String::trim).collect(Collectors.toList());
    }

    public boolean isCrewEffectsDisabled() {
        seleniumWaits.untilElementIsVisible(crewEffectsButton);
        seleniumActions.scrollInToElement(crewEffectsButton);
        return !crewEffectsButton.isEnabled();
    }


}
