package com.nclh.pageObjects;

import com.nclh.constants.GlobalConstants;
import com.nclh.managers.FileReaderManager;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuestServicePage {

    SeleniumActions seleniumActions;
    CrewInformationPage crewInformationPage;
    CommonFunctions commonFunctions;
    SeleniumWaits seleniumWaits;
    CommonPage commonPage;
    public static final Logger logger
            = LoggerFactory.getLogger(SeleniumActions.class);
    @Setter
    @Getter
    String folioAmountValue;
    private final WebDriver webDriver;
    @FindBy(css = "[data-placeholder^='Search']")
    private WebElement cruiseSearchBox;
    @FindBy(xpath = "//*[@href='#cruise-information']/../../../following-sibling::span/span/following-sibling::span")
    private WebElement searchDataCruise;
    @FindAll({
            @FindBy(css = "[placeholder^='Search']"),
            @FindBy(css = "input.complex-search-input")
    })
    private WebElement crewSearchBox;
    @FindBy(xpath = "//otalio-passenger-status-card/descendant::otalio-chip/div/div[text()]")
    private WebElement statusInformation;
    @FindBy(xpath = "//div[@role='tab' and normalize-space()='Classification']")
    private WebElement classificationTab;
    @FindBy(xpath = "//div[text()='Loyalty']")
    private WebElement loyaltyTab;
    @FindBy(css = "[placeholder='Loyalty Program']")
    private WebElement loyaltyProgramDropdown;
    @FindBy(css = "[placeholder='Loyalty Tier']")
    private WebElement loyaltyTierDropdown;
    @FindBy(xpath = "//otalio-infinite-select[@formcontrolname='loyaltyTierEntityId']//span[text()]")
    private WebElement loyaltyTireText;
    @FindBy(css = "[placeholder='Linked Cabin(s)']")
    private WebElement linkedCabinsDropdown;
    @FindAll({
            @FindBy( css = "[placeholder='Search']"),
            @FindBy(xpath = "//input[contains(@placeholder,'Search') and not(@id='short-list-search-term-input')]")
    })
    private WebElement searchBox;
    @FindBy(xpath = "//span[normalize-space()='Save']")
    private WebElement saveButton;
    @FindBy(xpath = "//button[@id='cabin-move-popup-confirm-button']")
    private WebElement confirmButton;
    @FindBy(css = "[id='cruise-information-current-select-cabin']")
    private WebElement cabinEditButton;
    @FindBy(xpath = "//*[@role='row']/td[@role='gridcell'][1]")
    private List<WebElement> cabinNumbers;
    @FindBy(xpath = "//icon[@icon='cabin']/following-sibling::span")
    private WebElement cabinNumberGuestPage;
    @FindBy(xpath = "//icon[@icon='speaking']/following-sibling::div")
    private WebElement speakingGuestPage;
    @FindBy(css = "[placeholder='Reason']")
    private WebElement reasonInputBox;
    @FindBy(css = "#complex-filter-search-input")
    private WebElement searchCabinInputBox;
    @FindAll({
            @FindBy(css = "[mattooltip='Add more filters'] > span > icon"),
            @FindBy(xpath = "//button[@id='complex-filter-add-more-button']/span")
    } )
    private WebElement moreFiltersIcon;
    @FindBy(css = "[id='complex-filter-search-add-more-filters']")
    private WebElement filterSearchInputBox;
    @FindBy(css = "#complex-filter-collapse-expand-chip > div > div")
    private WebElement hiddenFiltersIcon;
    @FindBy(css = "#breadcrumbs-title")
    private WebElement breadCrumb;
    @FindBy(xpath = "(//table/tbody/tr/td[@role='gridcell']/div/a)[1]")
    private WebElement guestName;
    @FindBy(xpath = "(//table/tbody/tr/td[@role='gridcell']/div/a)[2]")
    private WebElement guestNameTwo;
    @FindBy(xpath = "//otalio-chip/div[contains(@class,'mat-body')]/div")
    private WebElement openOtherFilters;
    @FindBy(css = "#breadcrumbs-title")
    private WebElement breadCrumbTitle;
    @FindBy(xpath = "(//b[text()='Age:']/../span/otalio-chip/div[contains(@class,'notification')])[1]")
    private WebElement ageFilter;
    @FindBy(css = "[placeholder='Min']")
    private WebElement minimumAgeInputBox;
    @FindBy(css = "[placeholder='Max']")
    private WebElement maximumAgeInputBox;
    @FindBy(xpath = "(//table/tbody/tr/td[@role='gridcell']/div/a[starts-with(@id,'passenger')])[1]")
    private WebElement guestFirstName;
    @FindBy(css = "input[id*='cruise-information-qualified']")
    private WebElement qualifiedForCheckInToggle;
    @FindBy(css = "[formcontrolname='documentNr']")
    private WebElement documentInputBox;
    @FindBy(xpath = "//span[text()='Upload']")
    private WebElement uploadLink;
    @FindBy(css = "[placeholder='Disembarkation Port']")
    private WebElement disembarkationPortDropDown;
    @FindBy(css = "[placeholder='Embarkation Port']")
    private WebElement embarkationPortDropDown;
    @FindBy(xpath = "(//span[@class='mat-option-text'])[2]")
    private WebElement firstItemInTheDisembarkationPortDropDown;
    @FindBy(xpath = "(//span[@class='mat-option-text'])[1]")
    private WebElement firstItemInTheEmbarkationPortDropDown;
    @FindBy(xpath = "//span[normalize-space()='Update']")
    private WebElement updateButton;
    @FindBy(tagName = "signature-pad")
    private WebElement signaturePad;
    @FindBy(css = "[class^='status-label']")
    private WebElement profileTag;
    @FindBy(css = "icon[icon='warning'] + div > span")
    private List<WebElement> warningMessage;
    @FindBy(xpath = "(//table/tbody/tr/td[@role='gridcell']/div/a[contains(@href,'passenger')])")
    private List<WebElement> guestLastAndFirstName;
    @FindBy(xpath = "//span[text()='Port Name']/following-sibling::span")
    private WebElement portNameLabel;
    @FindBy(xpath = "//p[normalize-space()='Embarkation / Disembarkation info']")
    private WebElement embarkAndDisembarkLabel;
    @FindBys({
            @FindBy(className = "class1"),
            @FindBy(className = "class2")
    })
    private List<WebElement> elementsWithBoth;
    @FindBy(css = "[class*='pseudo-checkbox-checked']")
    private List<WebElement> checkBoxes;
    @FindBy(xpath = "//otalio-folio-chooser/div/div")
    private List<WebElement> folioHeaders;
    @FindBy(xpath = "//button[@id='cruise-information-current-select-cabin']")
    private WebElement editCurrentCabin;
    @FindBy(xpath = "//tbody/tr/td/div[text()='Cabin Nr']/ancestor::table/tbody/tr/td[@role='columnheader'][not(contains(@class,'select'))]/div[text()]/following::table/tbody/tr[1]/td[not(contains(@class,'select'))][1]")
    private WebElement cabinNumberToUpdate;
    @FindBy(xpath = "//input[@placeholder='Date of Birth']")
    private WebElement dateOfBirth;
    @Getter
    @FindBy(css = "button[id*='reservationStatus'] > icon")
    private WebElement reservationStatusList;
    @FindBy(xpath = "(//div[contains(text(),'Special Requests')])[1]")
    private WebElement specialRequest;
    @FindBy(xpath = "(//div[contains(text(),'Booking Info')])[1]")
    private WebElement bookingInfo;
    @FindBy(xpath = "(//div[contains(text(),'Onboard Credits')])[1]")
    private WebElement onboardCredit;
    @FindBy(xpath = "(//div[contains(text(),'Addresses')])[1]")
    private WebElement addresses;
    @FindBy(xpath = "(//div[contains(text(),'Flight Info')])")
    private WebElement flightInfo;
    @FindBy(xpath = "(//div[contains(text(),'Personal Items')])")
    private WebElement personalItems;
    @FindBy(xpath = "(//div[contains(text(),'Absences')])")
    private WebElement absences;
    @FindBy(xpath = "//span[@class='mat-button-wrapper']/mat-icon")
    private WebElement addIcon;
    @FindBy(css = "[mattooltip='Add New Special Request']")
    private WebElement addSpecialRequest;
    @FindBy(css = "[id='otalio-infinite-select-specialRequestCodesId-field']")
    private WebElement specialRequestDropDown;
    @FindBy(css = "[id='otalio-infinite-select-serviceItemArea-field']")
    private WebElement categoryDropDown;
    @FindBy(css = "[placeholder='Item']")
    private WebElement itemDropDown;
    @FindBy(css = "[placeholder='Payment Department']")
    private WebElement paymentDepartmentDropDown;
    @FindBy(css = "[id='otalio-infinite-select-serviceItemCodeRequest-field']")
    private WebElement codeDropDown;
    @FindBy(css = "[id='otalio-infinite-select-originType-field']")
    private WebElement originDropDown;
    @FindBy(css = "[placeholder='Amount']")
    private WebElement amountfield;
    @FindBy(css = "[placeholder='Collector Department']")
    private WebElement collectorDropDown;
    @FindBy(xpath = "//mat-form-field[.//*[normalize-space(text())='Financial Department']]//div[@aria-label='Select' or contains(@class,'mat-select-arrow-wrapper')]")
    private WebElement financialDepartmentDropDown;
    @FindBy(css = "[placeholder='Non Refundable OnBoard Credits']")
    private WebElement nonRefundableDropDown;
    @FindBy(css = "#otalio-infinite-select-classificationTypeId-field-search")
    private WebElement searchSpecialRequest;
    @FindBy(css = "#otalio-infinite-select-serviceItemCodeRequest-field-search")
    private WebElement searchCode;
    @FindBy(css = "[placeholder='Loyalty Number']")
    private WebElement loyaltyNumber;
    @FindBy(id = "complex-filter-multi-search-in-options-list-input")
    private WebElement lostCabin;
    @FindBy(css = "[placeholder='Limit per Cruise']")
    private WebElement limitPerCruise;
    @FindBy(xpath = "//*[normalize-space()='Special Requests']/following-sibling::otalio-chip-list/descendant::otalio-chip/div/div")
    private List<WebElement> specialRequestList;
    @FindBy(xpath = "//input[@id='complex-filter-search-input']")
    private WebElement cabinReadinessSearch;
    @FindBy(xpath = "//tbody/tr/td/div[contains(@class,'datagrid-group')]")
    private WebElement arrowButton;
    @FindBy(xpath = "//tbody/tr/td/div/a[contains(@id,'cabin-readiness-list')]")
    private List<WebElement> firstNameText;
    @FindBy(xpath = "//tbody/tr/td[contains(text(),'CAB')]")
    private WebElement cabNumber;
    @FindBy(xpath = "//otalio-menu-tree-list-item-card/descendant::div[contains(@class,'mat-body') and not(contains(@class,'chip circle'))]")
    private List<WebElement> specialRequestComplaintList;
    @FindBy(xpath = "//tbody/tr/td[@role='gridcell' and @aria-colindex='5']")
    private List<WebElement> folioDepartmentList;
    @FindBy(xpath = "//tbody/tr/td[@role='columnheader']")
    private List<WebElement> tableHeaderItems;
    @FindBy(xpath = "//tbody/tr/td[@role='columnheader']")
    private List<WebElement> tableHeaderList;
    @FindBy(xpath = "(//tr[@aria-rowindex='1']/td//button[@mattooltip='Edit'])[1]")
    private WebElement editIcon;
    @FindBy(xpath = "//icon[contains(@icon,'plus-lined')]")
    private WebElement plusButton;
    @FindBy(css = "[aria-label='Items per page: 100']")
    private WebElement perPageLink100;
    @FindBy(xpath = "//input[@placeholder='ZIP Code']/../span//label/mat-label")
    private WebElement zipCode;
    @FindBy(xpath = "//otalio-infinite-select[@formcontrolname='printerId']")
    private WebElement printerDropDown;
    @FindBy(xpath = "(//mat-option[contains(@id,'select-printerId')])[1]")
    private WebElement printerOption;
    @FindBy(xpath = "//icon[@icon='eye-opened']/*[name()='svg']/*[name()='use']")
    private WebElement eyeOpened;
    @FindBy(xpath = "//button[@id='cruise-information-flight-info-add']")
    private WebElement addFlightInfo;
    @FindBy(xpath = "//otalio-infinite-select[@formcontrolname='departureAirportId']")
    private WebElement departureAirportId;
    @FindBy(xpath = "(//mat-option/span[@class='mat-option-text'])[2]")
    private WebElement selectAirport;
    @FindBy(xpath = "//otalio-infinite-select[@id='cruise-information-current-linked-cabin']")
    private WebElement linkedCabin;
    @FindBy(xpath = "(//mat-pseudo-checkbox)[1]")
    private WebElement linkedCabin1;
    @FindBy(xpath = "(//mat-pseudo-checkbox/following-sibling::span)[1]")
    private WebElement linkedCabin1Text;
    @FindBy(xpath = "(//mat-pseudo-checkbox)[2]")
    private WebElement linkedCabin2;
    @FindBy(xpath = "(//mat-pseudo-checkbox/following-sibling::span)[2]")
    private WebElement linkedCabin2Text;
    @FindBy(xpath = "//otalio-infinite-select[@id='cruise-information-current-linked-cabin']/descendant::span[contains(@class,'mat-select-min')]")
    private WebElement savedLinkedCabins;
    @FindBy(xpath = "//otalio-infinite-select[@id='personal-info-salutation-select']")
    private WebElement salutationDropdown;
    @FindBy(xpath = "(//mat-option[contains(@id,'salutation')]/child::span)[2]")
    private WebElement salutationDropdownOption;
    @FindBy(xpath = "//otalio-passenger-picture/following::h1[contains(@class,'text-truncate text-capitalize')]")
    private WebElement guestFullName;
    @FindBy(xpath = "//otalio-profile-card/descendant::div/h4[text()]")
    private WebElement guestAge;
    @FindBy(xpath = "//span[text()='Classification Type']")
    private WebElement classificationType;
    @FindBy(xpath = "//span[text()=' Add New Classification '] /../../button[@id='guest-classification-editor-add-item-button'][not(contains(disabled,'true'))]")
    private WebElement addClassification;
    @FindBy(xpath = "//span[text()='Classifications']")
    private WebElement classifications;
    @FindBy(xpath = "(//mat-option/mat-pseudo-checkbox/following-sibling::span)[3]")
    private WebElement classificationIdOption;
    @FindBy(xpath = "(//mat-option[contains(@id,'classificationTypeId')]/span)[1]")
    private WebElement classificationOption;
    @FindBy(xpath = "//otalio-passenger-status-card/h3[contains(text(),'Status')]/../otalio-chip-list/descendant::div[@class='text-truncate']/span")
    private WebElement statusInProfile;
    @FindBy(xpath = "//mat-select[contains(@id,'subDepartmentId-field')]/descendant::span[text()]")
    private WebElement subDepartmentIdFieldText;
    @FindBy(xpath = "//tr[@role='row']/td[not(@aria-label='Column Department')][@aria-colindex='5'][@class='dx-hidden-cell'][text()]")
    private List<WebElement> departmentListText;
    @FindBy(xpath = "//otalio-passenger-status-card/h3[contains(text(),'Status')]/../otalio-chip-list/descendant::div[@class='text-truncate']")
    private WebElement latitudeStatus;
    @FindBy(xpath = "//otalio-passenger-booking-info//div[@class='text-truncate']")
    private WebElement bookingNumber;
    @FindBy(css = "[icon*='plus']")
    private WebElement plusIcon;
    @FindBy(xpath = "//otalio-account-profile-subsection//div[contains(text(),'Lost & Found Items')]")
    private WebElement lostAndFound;
    @FindBy(xpath = "//tr[@aria-rowindex='1']/td/div/span[contains(text(),'L')] ")
    private WebElement lostAndFoundItemInGuestPage;

    @FindBy(xpath = "//tbody/tr[@role='row'][contains(@class,'data-row')]")
    private List<WebElement> documentsData;

    @FindBy(xpath = "//div[@aria-label='Page Navigation']//div[@class='dx-info']")
    private WebElement visitorCountGuestPage;
    @FindBy(xpath = "//input[@placeholder='Disembarkation Date']")
    private WebElement disembarkationDate;
    @FindBy(xpath = "//input[@placeholder='Embarkation Date']")
    private WebElement embarkationDate;
    @FindBy(xpath = "(//button[contains(@id,'edit')])[1]")
    private WebElement editIconLost;
    @FindBy(xpath = "(//button[contains(@id,'menu')])[1]")
    private WebElement kebabButton;
    @FindBy(xpath = "(//button[contains(text(),'Return')])[1]")
    private WebElement returnButton;
    @FindBy(xpath = "//otalio-complex-filter-multi-label-content/div[contains(text(),'Cabin:')]")
    private WebElement cabinNumberFilter;
    @FindBy(css = "[id='otalio-infinite-select-travelDocType-field']")
    private WebElement documentType;

    @FindBy(css = "[id='complex-filter-multi-search-in-options-list-input']")
    private WebElement cabinSearch;

    @FindBy(css = "[id*='complex-filter-label-disembarkation.displayDate-open-button'] > div + icon")
    private WebElement disembarkationDateArrowDownButton;

    @FindBy(css = "[id='personal-info-last-name-input']")
    private WebElement lastNamePersonalInfo;

    @FindBy(xpath = "//span[@class='text-truncate text-uppercase' and text()='INTOX']")
    private WebElement intoxText;

    @FindBy(xpath = "//td[@aria-colindex='9'][not(contains(@aria-label,'Column Reservation Status'))]")
    private List<WebElement> reservationStatusText;

    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-creditSubDepartmentId-field']/div/div/span/span")
    private List<WebElement> paymentsMethods;

    @FindBy(css = "[class*='pseudo-checkbox-checked']")
    private List<WebElement> guestCheckBoxes;

    @FindBy(xpath = "//a[contains(@id,'profile-card')]/following-sibling::div[contains(@class,'flex-center mat-body')]")
    private List<WebElement> GuestSameCabin;

    @FindBy(xpath = "//otalio-related-passenger-profile-card/div/div[not(contains(@class,'mat-tooltip-trigger'))]")
    private List<WebElement> travelWithCabins;

    @FindBy(css = "[id='folio-chooser-add-button']")
    private WebElement folioAddButton;

    @FindBy(xpath = "//otalio-folio-chooser/div/div")
    private List<WebElement> folioCount;

    @FindBy(xpath = "(//*[contains(@href,'delete')])[1]")
    private WebElement deleteIcon;

    @FindBy(xpath = "(//div[@id='toast-message-content'])[2]")
    private WebElement toastMessage;

    @FindBy(xpath = "//tbody/tr[contains(@class,'data')]")
    private List<WebElement> packageData;

    @FindBy(xpath = "//span[contains(text(),'Onboard')]")
    private WebElement onboardToggle;

    @FindBy(xpath = "//span[contains(text(),'Add new Absence')]")
    private WebElement newAbsencesButton;

    @FindBy(xpath = "//otalio-cruise-information//button[contains(@class,'after')]")
    private WebElement cruiseInformationSideButton;

    @FindBy(xpath = "//mat-label[normalize-space()='Excursion']/ancestor::mat-form-field/preceding-sibling::mat-radio-button")
    private WebElement excursionRadioButton;

    @FindBy(xpath = "//h4[contains(text(),'Actions / Related Links')]")
    private WebElement quickLinksHeading;

    @FindBy(xpath = "//td[@aria-colindex='6' and not(@role='columnheader')]")
    private List<WebElement> loyaltyList;

    @FindBy(xpath = "(//button[@mattooltip='Delete'])[1]")
    private WebElement deleteSpecialRequest;

    public void clickOnDeleteSpecialRequest(){
        seleniumWaits.untilElementIsVisible(deleteSpecialRequest);
        seleniumActions.clickOnElement(deleteSpecialRequest);
    }

    public List<String> getLoyaltyListListOfAll() {
        seleniumActions.sleep(3000);
        return loyaltyList.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public boolean verifyQuickLinksHeading(){
        seleniumWaits.untilElementIsVisible(quickLinksHeading);
        return seleniumActions.isElementDisplayed(quickLinksHeading);
    }

    public boolean verifyExcursionRadioButton(){
        seleniumWaits.untilElementIsVisible(excursionRadioButton);
        return seleniumActions.isElementDisplayed(excursionRadioButton);
    }

    public void clickNewAbsencesButton(){
        if(newAbsencesButton.isDisplayed()){
            seleniumActions.clickOnElement(newAbsencesButton);
            seleniumActions.sleep(3000);
        }
    }

    public void clickOnOnboard(){
        seleniumWaits.untilElementIsVisible(onboardToggle);
        seleniumActions.clickOnElement(onboardToggle);
    }


    public boolean getLogsList(){
        return seleniumActions.areElementsDisplayed(packageData);
    }

    public String getSuccessfulToastMessage() {
//        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsVisible(toastMessage);
        return seleniumActions.getTextMessage(toastMessage);
    }

    public void clickOnDeleteAshoreDenied(){
        seleniumWaits.untilElementIsClickable(deleteIcon);
        seleniumActions.clickOnElement(deleteIcon);
    }

    public boolean documentsDataDisplayed(){
        seleniumWaits.untilListElementIsVisible(documentsData);
        return seleniumActions.areElementsDisplayed(documentsData);
    }

    public int getFolioCount(){
        int Count = folioCount.size();
        return Count;
    }

    public void clickOnFolioAddButton(){
        seleniumWaits.untilElementIsClickable(folioAddButton);
        seleniumActions.clickOnElement(folioAddButton);
    }


    public List<String> getCabinsNumbers() {
        List<String> cabins = new ArrayList<>();
        for (int i = 0; i < travelWithCabins.size(); i++) {
            String value = seleniumActions.getTextMessage(travelWithCabins.get(i));
            cabins.add(value);
        }
        System.out.println(cabins.size());
        return cabins;
    }


    public void clearGuestCheckBoxes(){
        for (int i=0; i< guestCheckBoxes.size(); i++){
            seleniumActions.sleep(2000);
            seleniumActions.clickOnElement(guestCheckBoxes.get(i));
        }
        if(guestCheckBoxes.size()>0){
            guestCheckBoxes.get(0).click();
        }
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
    }

    public List<String> clickOnTabAndGetPaymentMethod(String tab) {
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath("//div[contains(text(),'" + tab + "')]")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[contains(text(),'" + tab + "')]")));

        return paymentsMethods.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void editLastName(){
        seleniumActions.clearValue(lastNamePersonalInfo);
        seleniumActions.typeValue(lastNamePersonalInfo," Test");

    }


    public void guestSearchDisembarkationDateFilter(String date){
        //seleniumActions.clickOnElement(addMoreFilterButton);
        seleniumWaits.untilElementIsVisible(disembarkationDateArrowDownButton);
        seleniumActions.clickOnElement(disembarkationDateArrowDownButton);
        String radioButton = "//span[text()='"+date+"']/ancestor::mat-radio-button/descendant::input[@class='mat-radio-input']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(radioButton)));
    }

    public void searchLocationHousekeeping(String cabin) {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(cabinNumberFilter);
        seleniumActions.typeValue(cabinSearch,cabin);
        seleniumActions.sleep(3000);
        String Location = "//span[normalize-space()='" + cabin + "']";
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(Location))));
        seleniumActions.clickOnElement(cabinNumberFilter);
        seleniumActions.sleep(3000);
    }

    public void clickOnEditInLost(){
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsPresent(By.xpath("(//button[contains(@id,'edit')])[1]"));
        seleniumActions.moveToElementAndClick(editIconLost);
    }

    public void clickOnKebabIcon(){
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsPresent(By.xpath("(//button[contains(@id,'menu')])[1]"));
        seleniumActions.moveToElementAndClick(kebabButton);
        seleniumWaits.untilElementIsPresent(By.xpath("(//button[contains(text(),'Return')])[1]"));
        seleniumActions.clickOnElement(returnButton);
    }

    public void clickOnDocumentTab(){
        seleniumActions.clickOnElement(By.xpath("//div[text()='Documents']"));
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("//input[@placeholder='Document Nr']/parent::div/otalio-decode-mask-button/icon")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//input[@placeholder='Document Nr']/parent::div/otalio-decode-mask-button/icon")));
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("//input[@placeholder='Birth Date']/parent::div/icon[@icon='eye-opened']")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//input[@placeholder='Birth Date']/parent::div/icon[@icon='eye-opened']")));
    }



    public String getEmbarkationDate() {
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsVisible(embarkationDate);
        return commonFunctions.readToastMessage(embarkationDate);
    }

    public String getDisembarkationDate() {
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsVisible(disembarkationDate);
        return commonFunctions.readToastMessage(disembarkationDate);
    }


   public boolean lostAndFoundItemDisplayed(){
       //seleniumWaits.untilElementIsVisible(lostAndFoundItemInGuestPage);
       seleniumActions.scrollInToElement(lostAndFoundItemInGuestPage);
       return seleniumActions.isElementDisplayed(lostAndFoundItemInGuestPage);
   }


    public void clickOnLostAndFound(){
        seleniumActions.moveToElement(lostAndFound);
        seleniumActions.moveToElementAndClick(lostAndFound);
    }




    public void clickOnTabAndClickAddRouting(String tab, String button2) {
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[contains(text(),'" + tab + "')]")));

        String buttonXpath = "//span[text()='" + button2 + "' or normalize-space()='" + button2 + "']";
        seleniumActions.sleep(3000);
        seleniumActions.doubleClickOnElement(webDriver.findElement(By.xpath(buttonXpath)));
        if (webDriver.findElements(By.xpath(buttonXpath)).size() > 0) {
            seleniumActions.clickOnElement(By.xpath(buttonXpath));
        }
    }
    public GuestServicePage(WebDriver webDriver) {
        seleniumActions = new SeleniumActions(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        crewInformationPage = new CrewInformationPage(webDriver);
        commonFunctions = new CommonFunctions();
        commonPage = new CommonPage(webDriver);
    }

    public void clickOnPlusButtonInLostAndFound(){
        seleniumWaits.untilElementIsVisible(plusIcon);
        seleniumActions.clickOnElement(plusIcon);
    }

    public String getLoyaltyNumber(){
        seleniumWaits.untilElementIsVisible(latitudeStatus);
        return seleniumActions.getTextMessage(latitudeStatus);
    }

    public String getBookingNumber(){
        seleniumWaits.untilElementIsVisible(bookingNumber);
        return seleniumActions.getTextMessage(bookingNumber);
    }

    public static int randomNumber() {
        Random objGenerator = new Random();
        //int randomNumber = objGenerator.nextInt(20);
        int randomNumber = 0;
        System.out.println("Random No : " + randomNumber);
        return randomNumber;
    }

    public List<String> getDepartmentList() {
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        List<String> deptList = new ArrayList<>();
        seleniumActions.sleep(5000);
        for (int i = 0; i < 20; i++) {
            seleniumActions.sleep(100);
            deptList.add(jse.executeScript("return document.querySelectorAll(\"[aria-colindex='5'][class='dx-hidden-cell']\")[" + i + "].innerHTML").toString());
        }
        System.out.println(deptList);
        return deptList;
    }

    public WebElement getSubDepartmentIdFieldTextInputFieldText() {
        seleniumActions.sleep(3000);
        return subDepartmentIdFieldText;
    }

    public String statusTextInProfile() {
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsVisible(statusInProfile);
        return seleniumActions.getTextMessage(statusInProfile);
    }

    public void removeGuests(){
        for (int i = 0; i < checkBoxes.size(); i++) {
            seleniumActions.sleep(2000);
            seleniumWaits.untilElementIsClickable(checkBoxes.get(i));
            seleniumActions.jsClick(checkBoxes.get(i));
        }

        for (int i = 0; i < checkBoxes.size(); i++) {
            seleniumActions.sleep(2000);
            seleniumWaits.untilElementIsClickable(checkBoxes.get(i));
            seleniumActions.clickOnElement(checkBoxes.get(i));
        }
    }

    public void selectCodeNewspaperDropdown(String dropdown, String code){
        String dropDownName = "//span[text()='Code']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(dropDownName)));
        seleniumActions.typeValue(searchCode,code);
        seleniumActions.sleep(3000);
        String selectEmbarkationPortDropDownText = "(//mat-option[contains(@id,'otalio-infinite-select')])[1]";
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectEmbarkationPortDropDownText)));

        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectEmbarkationPortDropDownText)));
        }
    }

    public String selectClassifications() {
        seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
        seleniumWaits.untilElementIsVisible(classifications);
        seleniumActions.moveToElementAndClick(classifications);
        seleniumWaits.untilElementIsVisible(classificationIdOption);
        String classificationsId = seleniumActions.getTextMessage(classificationIdOption);
        try {
            seleniumActions.moveToElementAndClick(classificationIdOption);
            seleniumActions.sleep(2000);
        } catch (StaleElementReferenceException ste) {
            seleniumActions.moveToElementAndClick(classificationIdOption);
            seleniumActions.sleep(2000);
        }
        seleniumActions.sleep(2000);
        seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
        return classificationsId;
    }

    public String selectClassificationType() {
        if (seleniumActions.isElementDisplayed(addClassification)){
            seleniumActions.clickOnElement(addClassification);
        }

        seleniumActions.sleep(2000);
        seleniumWaits.untilElementIsVisible(classificationType);
        seleniumActions.jsClick(classificationType);
        seleniumActions.sleep(2000);

//        seleniumWaits.untilElementIsVisible(classificationOption);
        String classificationType = seleniumActions.getTextMessage(classificationOption);
        seleniumActions.clickOnElement(classificationOption);
        seleniumActions.sleep(2000);
        //seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
        return classificationType;
    }

    public String selectClassificationTypeVIP() {
        if (seleniumActions.isElementDisplayed(addClassification)){
            seleniumActions.clickOnElement(addClassification);
        }

            seleniumActions.sleep(2000);
            seleniumWaits.untilElementIsVisible(classificationType);
            seleniumActions.jsClick(classificationType);
            seleniumActions.sleep(2000);

//        seleniumWaits.untilElementIsVisible(classificationOption);
        seleniumActions.typeValue(searchSpecialRequest,"VIP");
        seleniumActions.sleep(2000);
        String classificationType = seleniumActions.getTextMessage(classificationOption);
        seleniumActions.clickOnElement(classificationOption);
        seleniumActions.sleep(2000);
       // seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
        return classificationType;
    }

    public String getGuestFullNameSalutaion() {
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsVisible(guestFullName);
        return commonFunctions.readToastMessage(guestFullName);
    }

    public String getGuestAgeGenderText() {
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsVisible(guestAge);
        return commonFunctions.readToastMessage(guestAge);
    }


    public String selectSalutationDropdown() {
        seleniumWaits.untilElementIsVisible(salutationDropdown);
        seleniumActions.clickOnElement(salutationDropdown);
        seleniumWaits.untilElementIsVisible(salutationDropdownOption);
        String salutation = seleniumActions.getTextMessage(salutationDropdownOption);
        seleniumActions.clickOnElement(salutationDropdownOption);
        seleniumActions.sleep(2000);
        return salutation;
    }

    public String getSavedLinkedCabins() {
        seleniumWaits.untilElementIsVisible(savedLinkedCabins);
        return commonFunctions.readToastMessage(savedLinkedCabins);
    }

    public ArrayList<String> clickOnLinkedCabins(int selectCabins) {
        seleniumWaits.untilElementIsVisible(linkedCabin);
        seleniumActions.clickOnElement(linkedCabin);

        ArrayList<String> linkedCabins = new ArrayList<>();
        for (int i = 2; i <= selectCabins; i++) {
            String selectCheckBox = "(//mat-pseudo-checkbox/following-sibling::span)[" + i + "]";
            WebElement checkBox = webDriver.findElement(By.xpath(selectCheckBox));
            if (!checkBox.isSelected()) {
                try {
                    seleniumActions.clickOnElement(checkBox);
                } catch (StaleElementReferenceException ste) {
                    seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-pseudo-checkbox/following-sibling::span)[" + i + "]")));

                }
            }
            // seleniumActions.clickOnElement(checkBox);
            String cabinText = "(//mat-pseudo-checkbox/following-sibling::span)[" + i + "]";
            try {
                String cabins = webDriver.findElement(By.xpath(cabinText)).getText();
                linkedCabins.add(cabins);
            } catch (StaleElementReferenceException ste) {
                String cabins = webDriver.findElement(By.xpath(cabinText)).getText();
                linkedCabins.add(cabins);
            }
        }
        System.out.println(linkedCabins);
        // System.out.println(seleniumActions.getTextMessage(linkedCabin1Text));
        //System.out.println(seleniumActions.getTextMessage(linkedCabin2Text));
        seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
        seleniumActions.sleep(2000);
        return linkedCabins;
    }

    public boolean checkAddFlightInfo() {
        seleniumWaits.untilElementIsVisible(addFlightInfo);
        return seleniumActions.isElementDisplayed(addFlightInfo);
    }

    public void clickOnAddFlightInfo() {
        seleniumWaits.untilElementIsVisible(addFlightInfo);
        seleniumActions.clickOnElement(addFlightInfo);
    }

    public boolean selectDepartureAirport(String airport) {
        seleniumActions.sleep(2000);
        seleniumActions.clickOnElement(By.xpath("//otalio-infinite-select[@formcontrolname='" + airport + "']"));
        seleniumWaits.untilElementIsVisible(selectAirport);
        seleniumActions.clickOnElement(selectAirport);
        System.out.println(seleniumActions.getTextMessage(selectAirport));
        return seleniumActions.isElementDisplayed(selectAirport);
    }

    public boolean checkEyeIsOpened() {
        seleniumWaits.untilElementIsVisible(eyeOpened);
        return seleniumActions.isElementDisplayed(eyeOpened);
    }

    public void clickOnOpenedEye() {
        seleniumActions.clickOnElement(eyeOpened);
    }

    public boolean checkZipCodeIsDisplayed() {
        seleniumWaits.untilElementIsVisible(zipCode);
        return seleniumActions.isElementDisplayed(zipCode);
    }

    public boolean checkDocumentIsDisplayed() {
        seleniumWaits.untilElementIsVisible(documentType);
        return seleniumActions.isElementDisplayed(documentType);
    }

    public void selectPrinterDropDown() {
        seleniumActions.moveToElementAndClick(printerDropDown);
        //WebElement printer = webDriver.findElement(By.xpath("(//span[contains(text(),'" +name+ "')])[1]"));
        seleniumWaits.untilElementIsVisible(printerOption);
        seleniumActions.clickOnElement(printerOption);

    }

    public void clickOnEdit() {
        seleniumActions.clickOnElement(editIcon);
        seleniumWaits.untilElementIsVisible(plusButton);
        seleniumActions.clickOnElement(plusButton);
    }

    public String getDepartmentText(String columnName) {
        int index = getIndexOfColumnName(columnName) + 2;
        System.out.println("index :: " + index);
        String name = "document.querySelectorAll('td[role=\"gridcell\"][aria-colindex=\"5\"]')[0].innerHTML";
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        String data = (String) jse.executeScript(name);
        return name;
    }

    public String getQuantityText(String columnName) {
        int index = getIndexOfColumn(columnName);
        System.out.println("index :: " + index);
        String departmentName = "//div[text()='" + columnName + "']/ancestor::div[@class='dx-datagrid-headers']/following-sibling::div/descendant::td[" + index + "]";
        String name = seleniumActions.getTextMessage(
                webDriver.findElement(By.xpath(departmentName))
        );
        System.out.println("text :: " + name);
        return name;
    }

    public int getIndexOfColumnName(String columnName) {
        List<String> columns = new ArrayList<>();
        for (int i = 1; i < tableHeaderItems.size(); i++) {
            try {
                seleniumActions.sleep(500); //due to table header loading given 500 millisecs
                seleniumWaits.untilElementIsVisible(tableHeaderItems.get(1));
                columns.add(seleniumActions.getTextMessage(tableHeaderItems.get(i)));
            } catch (StaleElementReferenceException ste) {
                By tableHeader = By.xpath("(//table/tbody/tr/td[@role='columnheader']/parent::tr)[1]/td/div[contains(@class,'text-content')]");
                columns.add(seleniumActions.getTextMessage(webDriver.findElements(tableHeader).get(i)));
            }
        }

        int columnIndex = columns.indexOf(columnName);
        return columnIndex;
    }

    public int getIndexOfColumn(String columnName) {
        List<String> columns = new ArrayList<>();
        for (int i = 1; i < tableHeaderList.size(); i++) {
            try {
                seleniumActions.sleep(500); //due to table header loading given 500 millisecs
                columns.add(seleniumActions.getTextMessage(tableHeaderList.get(i)));
            } catch (StaleElementReferenceException ste) {
                By tableHeader = By.xpath("//tbody/tr/td[@role='columnheader'][contains(@style,'min-width')]");
                columns.add(seleniumActions.getTextMessage(webDriver.findElements(tableHeader).get(i)));
            }
        }

        int columnIndex = columns.indexOf(columnName);
        return columnIndex;
    }

    public List<String> getFolioDepartmentList() {
        return folioDepartmentList.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void typeAmount() {
        seleniumActions.isElementDisplayed(amountfield);
        seleniumWaits.untilElementIsVisible(amountfield);
        setFolioAmountValue(String.valueOf(this.getRandomNumber(10, 20)));
        seleniumActions.typeValue(amountfield, getFolioAmountValue());

    }

    public void setFinancialDepartmentDropDown() {
//        seleniumWaits.untilElementIsVisible(financialDepartmentDropDown);
        try {
            seleniumActions.clickOnElement(financialDepartmentDropDown);
        } catch (Exception e) {

        }
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);

        iterateToGetEnabledValue();
        seleniumActions.jsClick(webDriver.findElement(
                By.xpath("(//mat-option[not(contains(@class,'disabled'))])[1]")
        ));
      /* // seleniumActions.clickOnElement(By.xpath("(//mat-option[not(contains(@class,'disabled'))])[1]"));
        seleniumActions.sleep(3000);
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath("(//mat-option[not(contains(@class,'disabled'))])[1]")));
        seleniumActions.clickOnElement(webDriver.findElement(
                By.xpath("(//mat-option[not(contains(@class,'disabled'))])[1]/span")
        ));
        System.out.println("Testing financial department drop down");*/
    }

    public void iterateToGetEnabledValue() {
        int size = 0;
        for (int i = 0; i < 10; i++) {
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            seleniumActions.sleep(3000);
            List<WebElement> options = webDriver.findElements(By.xpath("(//mat-option[(contains(@class,'disabled'))])"));
            size = options.size();
            seleniumActions.sleep(1000);
            try {
                if (webDriver.findElement(
                        By.xpath("(//mat-option[not(contains(@class,'disabled'))])[1]")).isDisplayed()) {
                    seleniumActions.scrollInToElement(webDriver.findElement(By.xpath("(//mat-option[not(contains(@class,'disabled'))])[1]")));
                    return;
                }
            } catch (Exception e) {
                System.out.println("Element not found, retrying...");
                seleniumActions.sleep(2000);
                seleniumActions.scrollInToElement(webDriver.findElement(By.xpath("(//mat-option[(contains(@class,'disabled'))])[" + size + "]")));
                seleniumActions.sleep(1000);
                seleniumActions.scrollInToElement(webDriver.findElement(By.xpath("(//mat-option[(contains(@class,'disabled'))])[" + (size - 10) + "]")));
                seleniumActions.sleep(1000);
                seleniumActions.scrollInToElement(webDriver.findElement(By.xpath("(//mat-option[(contains(@class,'disabled'))])[" + size + "]")));
            }
        }
    }

    public void clickOnFolio(String folio) {
        WebElement folioNumber = webDriver.findElement(By.xpath("//otalio-folio-chooser/descendant::div[normalize-space(text()) = '" + folio + "']"));
        seleniumActions.isElementDisplayed(folioNumber);
        seleniumActions.clickOnElement(folioNumber);

    }

    public String getTextOfPayment(String payment) {
        WebElement PaymentName = webDriver.findElement(By.xpath("//div/span[contains(text(),'" + payment + "')]"));
        return PaymentName.getText().trim();

    }

    public void setPaymentDepartmentDropDownDropDown(String payment) {
        seleniumActions.sleep(3000);
        paymentDepartmentDropDown.click();
        searchSpecialRequest.sendKeys(payment);
        CommonPage commonPage = new CommonPage(webDriver);
        WebElement paymentName = webDriver.findElement(By.xpath("//span[contains(text(),'" + payment + "')]"));
        seleniumActions.clickOnElement(paymentName);
    }

    public void typeLimit(String name) {
        seleniumActions.isElementDisplayed(limitPerCruise);
        seleniumActions.clearValue(limitPerCruise);
        seleniumActions.typeValue(limitPerCruise, name);

    }

    public void setCollectorDropDown(String collector) {
        seleniumActions.sleep(3000);
        collectorDropDown.click();
        searchSpecialRequest.sendKeys(collector);
        CommonPage commonPage = new CommonPage(webDriver);
        WebElement paymentName = webDriver.findElement(By.xpath("(//span[contains(text(),'" + collector + "')])[1]"));
        seleniumActions.clickOnElement(paymentName);
    }

    public List<String> getSpecialRequestComplaintList() {
        try {
            return specialRequestComplaintList.stream()
                    .map(WebElement::getText)
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (StaleElementReferenceException e) {
            System.out.println("StaleElementReferenceException occurred. Retrying...");
            return Collections.emptyList(); // Or handle the retry mechanism if needed
        }

    }

    public void setOrigin() {
        seleniumActions.sleep(3000);
        originDropDown.click();
        //searchSpecialRequest.sendKeys(origin);
        CommonPage commonPage = new CommonPage(webDriver);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-originType')]/span)[1]")));

    }

    public String setCodeDropDown() {
        seleniumActions.sleep(3000);
        codeDropDown.click();
        //searchSpecialRequest.sendKeys(code);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-serviceItemCodeRequest')]/span)[1]")));
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-serviceItemCodeRequest')]/span)[1]")));

    }

    public void setCategorySpecialRequest() {
        seleniumActions.sleep(3000);
        categoryDropDown.click();
        //searchSpecialRequest.sendKeys(category);
        CommonPage commonPage = new CommonPage(webDriver);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-serviceItemArea')]/span)[1]")));
    }

    public void setItemDropdown() {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(itemDropDown);
        //searchSpecialRequest.sendKeys(category);
        CommonPage commonPage = new CommonPage(webDriver);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-itemId')]/span)[1]")));
    }

    public void setItemDropdownAs(String item) {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(itemDropDown);
        //searchSpecialRequest.sendKeys(category);
        CommonPage commonPage = new CommonPage(webDriver);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//mat-option[contains(@id,'otalio-infinite-select-itemId')]/span[contains(text(),'" + item + "')]")));
    }

    public void editItemDropdown() {
        seleniumActions.sleep(3000);
        seleniumActions.moveToElementAndClick(itemDropDown);
        //searchSpecialRequest.sendKeys(category);
        CommonPage commonPage = new CommonPage(webDriver);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-itemId')]/span)[2]")));
    }

    public String getCabNumberText() {
        return cabNumber.getText().trim();
    }

    public List<String> getFirstNameTextList() {
        return firstNameText.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void clickOnArrow() {
        seleniumActions.sleep(4000);
        seleniumActions.isElementDisplayed(arrowButton);
        seleniumActions.clickOnElement(arrowButton);

    }

    public void clickArrow() {
        seleniumActions.sleep(4000);
        seleniumActions.isElementDisplayed(arrowButton);
        seleniumActions.clickOnElement(arrowButton);

    }

    public void typeInSearchBox(String name) {
        seleniumActions.isElementDisplayed(cabinReadinessSearch);
        seleniumActions.typeValue(cabinReadinessSearch, name);
    }

    public List<String> getSpecialRequestList() {
        try {
            return specialRequestList.stream()
                    .map(WebElement::getText)
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (StaleElementReferenceException e) {
            System.out.println("StaleElementReferenceException occurred. Retrying...");
            return Collections.emptyList(); // Handle retry mechanism if needed
        }
    }

    public String setSpecialRequest() {
        seleniumActions.sleep(3000);
        specialRequestDropDown.click();
        //searchSpecialRequest.sendKeys(request);
        CommonPage commonPage = new CommonPage(webDriver);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-specialRequestCodesId')]/span)[2]")));
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-specialRequestCodesId')]/span)[2]")));
    }

    public void setCategory(String category) {
        seleniumActions.sleep(3000);
        categoryDropDown.click();
        searchSpecialRequest.sendKeys(category);
        CommonPage commonPage = new CommonPage(webDriver);
        commonPage.navigateTo(category);
    }

    public void setNonRefundableDropDown(String onBoardCredits) {
        seleniumActions.sleep(3000);
        nonRefundableDropDown.click();
        searchSpecialRequest.sendKeys(onBoardCredits);
        CommonPage commonPage = new CommonPage(webDriver);
        commonPage.navigateTo(onBoardCredits);
    }

    public void clickOnSpecialRequest() {
        seleniumActions.clickOnElement(specialRequest);
    }

    public void clickOnOnboardCredits() {
        seleniumWaits.untilElementIsVisible(onboardCredit);
        seleniumActions.clickOnElement(onboardCredit);
    }

    public void clickOnBookingInfo() {
        seleniumWaits.untilElementIsVisible(bookingInfo);
        seleniumActions.clickOnElement(bookingInfo);
    }

    public void clickOnAddresses() {
        seleniumWaits.untilElementIsVisible(addresses);
        seleniumActions.clickOnElement(addresses);
    }

    public void clickOnFlightInfo() {
        seleniumWaits.untilElementIsVisible(flightInfo);
        seleniumActions.clickOnElement(flightInfo);
    }

    public void clickOnAbsences() {
        seleniumActions.clickOnElement(cruiseInformationSideButton);
        seleniumWaits.untilElementIsVisible(absences);
        seleniumActions.clickOnElement(absences);
    }

    public void clickOnAddIcon() {
        seleniumActions.clickOnElement(addIcon);
    }

    public void clickOnSpecialRequestAdd(){
        seleniumActions.clickOnElement(addSpecialRequest);
    }

    public boolean getDateOfBirth() {
        seleniumWaits.untilElementIsVisible(dateOfBirth);
        return seleniumActions.isElementDisplayed(dateOfBirth);
    }

    public String getCabinNumberToUpdate() {
        seleniumActions.sleep(2000);
        return cabinNumberToUpdate.getText().trim();
    }

    public void cabinChangeReason(String reason) {
        seleniumActions.sleep(3000);
        String inputBox = "[placeholder='%s']";
        WebElement inputField = webDriver.findElement(By.cssSelector(String.format(inputBox, reason)));
        seleniumActions.clearValue(inputField);
        seleniumActions.typeValue(inputField, "Test");
    }

    public void changeCurrentCabin() {
        seleniumActions.clickOnElement(editCurrentCabin);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(cabinNumberToUpdate);
        seleniumActions.sleep(3000);
    }

    public boolean getFolioHeaders() {
        return seleniumActions.areElementsDisplayed(folioHeaders);
    }

    public String saveTheCountry(String country) {
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//span[normalize-space()='" + country + "']")));
    }

    public void changePreferredLanguage(String actual, String updated) {
        String arrow = "[id='personal-info-language-select'] > * > * > div +div";
        webDriver.findElement(By.cssSelector(String.format(arrow, actual))).click();
        seleniumWaits.untilElementIsVisible(searchBox);
        seleniumActions.typeValue(searchBox, updated);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[normalize-space()='" + updated + "']")));

        try {
            String arrow1 = "[placeholder='%s'] > * > * > div +div";
            webDriver.findElement(By.cssSelector(String.format(arrow1, actual))).click();
            seleniumWaits.untilElementIsVisible(searchBox);
            seleniumActions.typeValue(searchBox, updated);
            seleniumActions.sleep(3000);
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[normalize-space()='" + updated + "']")));
            try {
                commonPage.clickOnLabel(updated);
            } catch (StaleElementReferenceException ste) {
                seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[normalize-space()='" + updated + "']")));
            }
            seleniumActions.sleep(4000);
        } catch (Exception e) {
            seleniumActions.selectDropdownOption(actual,updated);
        }
    }

    public void setPort(String embarkOrDisembark) {
        seleniumActions.sleep(5000);
        switch (embarkOrDisembark) {
            case "Embarkation":
                seleniumActions.sleep(3000);
                seleniumWaits.untilElementIsClickable(embarkationPortDropDown);
                seleniumActions.clickOnElement(embarkationPortDropDown);
                seleniumActions.sleep(5000);
                seleniumActions.moveToElementAndClick(embarkAndDisembarkLabel);
                seleniumActions.clickOnElement(embarkationPortDropDown);
                seleniumActions.sleep(5000);
                try {
                    seleniumActions.clickOnElement(firstItemInTheEmbarkationPortDropDown);
                    seleniumActions.sleep(5000);
                } catch (StaleElementReferenceException ste) {
                    seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//span[@class='mat-option-text'])[1]")));
                }
                break;
            case "DisEmbarkation":
                seleniumActions.clickOnElement(disembarkationPortDropDown);
                try {
                    seleniumActions.clickOnElement(firstItemInTheDisembarkationPortDropDown);
                } catch (StaleElementReferenceException ste) {
                    seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//span[@class='mat-option-text'])[1]")));
                }
                break;
        }
    }

    public String getPortName() {
        return seleniumActions.getTextMessage(portNameLabel);
    }

    public String getGuestCount(String onBoardAshore) {
        String path = "//a[text()='%s']/following-sibling::span";

        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(path, onBoardAshore))));
        seleniumWaits.untilElementIsPresent(By.xpath(String.format(path, onBoardAshore)));
        seleniumActions.sleep(5000);
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath(String.format(path, onBoardAshore))));
    }

    public String getVisitorsCount() {
       seleniumWaits.untilElementIsVisible(visitorCountGuestPage, 2000);
       seleniumActions.sleep(5000);
       String Count = seleniumActions.getTextMessage(visitorCountGuestPage);
        return Count;
    }

    public void clickOnSaveButton() {
        //Temporarily using this code due to instability of the application
        seleniumActions.sleep(5000);
        seleniumWaits.untilElementIsClickable(saveButton);
        seleniumActions.clickOnElement(saveButton);

        seleniumActions.sleep(10000);
        seleniumActions.clickOnElement(saveButton);
        /*if(webDriver.findElement(By.xpath("//span[normalize-space()='Save']/parent::button[@disabled='false']")).isDisplayed()){
            seleniumActions.clickOnElement(saveButton);
        }
         seleniumActions.isElementDisplayed(webDriver.findElement(By.xpath("//span[normalize-space()='Save']/parent::button[@disabled='true']")));*/
        seleniumActions.sleep(3000);
    }

    public List<String> getGuestFullName() {
        seleniumActions.sleep(2000);
        seleniumWaits.untilElementIsClickable(guestLastAndFirstName.get(0));
        List<String> names = new ArrayList<String>();
        for (int i = 0; i < 2; i++) {
            names.add(seleniumActions.getTextMessage(guestLastAndFirstName.get(i)));
        }
        System.out.println(names);
        return names;
    }

    public void clickOnMissingFoundButton(String button) {
        String text = "button[id*='%s'][color]";
        seleniumActions.scrollInToElement(webDriver.findElement(By.cssSelector(String.format(text, button))));
        seleniumActions.moveToElement(webDriver.findElement(By.cssSelector(String.format(text, button))));
        seleniumActions.jsClick(webDriver.findElement(By.cssSelector(String.format(text, button))));
    }

    public void enterReasonForDeny(String reason, String reasonType) {
        switch (reason) {
            case "Ashore Denied Reason":
                By subReason = By.xpath("//span[text()='" + reasonType + "' or normalize-space()='" + reasonType + "']");
                if (seleniumActions.getNumberOfElements(webDriver.findElements(subReason)) > 0) {
                    System.out.println("Already reason selected");
                } else {
                    String arrow = "[placeholder='%s'] > * > * > div +div";
                    webDriver.findElement(By.cssSelector(String.format(arrow, reason))).click();
                    seleniumWaits.untilElementIsVisible(searchBox);
                    seleniumActions.typeValue(searchBox, reasonType);
                    try {
                        commonPage.clickOnLabel(reasonType);
                    } catch (StaleElementReferenceException ste) {
                        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[normalize-space()='" + reasonType + "']")));
                    }
                }
                break;
            case "Deny Boarding Reason":
                String inputBox = "[placeholder='%s']";
                WebElement inputField = webDriver.findElement(By.cssSelector(String.format(inputBox, reason)));
                seleniumActions.clearValue(inputField);
                seleniumActions.typeValue(inputField, "Test");
                break;
            default:
                System.out.println("Provide right data!");
        }
    }

    public List<String> getWarningMessage() {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        try {
            seleniumActions.scrollInToElement(warningMessage.get(0));
            seleniumWaits.untilElementIsVisible(warningMessage.get(0));
        } catch (Exception e) {
        }
        seleniumWaits.keepBackImplicitWaitTime(webDriver);
        seleniumActions.sleep(3000);
        return seleniumActions.getListOfElementsText(warningMessage);
    }

    public void disAbleToggle(String toggleName) {
        //String toggleType = "input[id*='%s']";
        String toggleType = "//input[contains(@id,'%s')]";
        seleniumActions.sleep(5000);
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))));
        System.out.println(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))).getAttribute("aria-checked"));
        WebElement toggle = webDriver.findElement(By.xpath(String.format(toggleType, toggleName)));
        if (toggle.getAttribute("aria-checked").equals("true")) {
            seleniumActions.clickOnElement(toggle);
            seleniumActions.sleep(2000);
        }
        seleniumWaits.untilElementIsClickable(saveButton);
        seleniumActions.clickOnElement(saveButton);
    }

    public void enableToggle(String toggleName) {
        String toggleType = "input[id*='%s']";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.cssSelector(String.format(toggleType, toggleName))));
        System.out.println(webDriver.findElement(By.cssSelector(String.format(toggleType, toggleName))).getAttribute("aria-checked"));
        WebElement toggle = webDriver.findElement(By.cssSelector(String.format(toggleType, toggleName)));
        if (toggle.getAttribute("aria-checked").equals("false")) {
            seleniumActions.clickOnElement(toggle);
        }

        seleniumWaits.untilElementIsClickable(saveButton);
        seleniumActions.clickOnElement(saveButton);
//        seleniumActions.isElementDisplayed(webDriver.findElement(By.xpath("//span[normalize-space()='Save']/parent::button[@disabled='true']")));
    }

    public String getFlagOnUserProfile() {
        seleniumWaits.untilElementIsVisible(profileTag);
        return seleniumActions.getTextMessage(profileTag).trim();
    }

    public boolean getFlag() {
        commonPage.waitForDomReady();
        try {
            seleniumWaits.untilElementIsVisible(breadCrumbTitle);
            seleniumWaits.untilElementIsPresent((By) breadCrumbTitle);
        } catch (Exception e) {
        }
        return seleniumActions.isElementPresentOnThePage(profileTag);
    }

    public void drawSignature() {
        seleniumActions.sleep(4000);
        seleniumWaits.untilElementIsVisible(signaturePad);
        seleniumWaits.untilElementIsClickable(signaturePad);
        seleniumActions.performSignature(signaturePad);
        seleniumWaits.untilElementIsVisible(signaturePad);
        seleniumActions.pressEscapeKey();
    }

    public void selectDisembarkationPort() {

        seleniumActions.clickOnElement(embarkationPortDropDown);
        try {
            seleniumActions.clickOnElement(firstItemInTheEmbarkationPortDropDown);
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//span[@class='mat-option-text'])[1]")));
        }

        seleniumActions.clickOnElement(disembarkationPortDropDown);
        try {
            seleniumActions.clickOnElement(firstItemInTheDisembarkationPortDropDown);
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//span[@class='mat-option-text'])[1]")));
        }
        seleniumActions.clickOnElement(saveButton);
    }

    public void setExpiryDate(String expiry, String date) {
        seleniumActions.clearValue(documentInputBox);
        seleniumActions.typeValue(documentInputBox, String.valueOf(commonFunctions.randomNumber()));
        String dateInput = "[placeholder='%s Date'] > div > input";
        seleniumActions.clearValue(webDriver.findElement(By.cssSelector(String.format(dateInput, expiry))));
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(String.format(dateInput, expiry))), date);
        seleniumWaits.untilElementIsClickable(updateButton);
        seleniumActions.clickOnElement(updateButton);
    }

    public void upLoadDocument() throws AWTException {
        try {
            seleniumActions.clickOnElement(uploadLink);
        } catch (Exception e) {
            System.out.println("Already uploaded");
            System.out.println(e);
        }
        seleniumActions.sleep(500);
        Robot robot = new Robot();

        StringSelection file = new StringSelection(System.getProperty("user.dir") + "\\testData\\passport.PNG");
        seleniumActions.sleep(500);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file, null);
        seleniumActions.sleep(500);

        robot.keyPress(KeyEvent.VK_CONTROL);
        seleniumActions.sleep(500);
        robot.keyPress(KeyEvent.VK_V);
        seleniumActions.sleep(500);
        robot.keyRelease(KeyEvent.VK_V);
        seleniumActions.sleep(500);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        seleniumActions.sleep(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        seleniumActions.sleep(500);
        robot.keyRelease(KeyEvent.VK_ENTER);
        seleniumActions.sleep(2000);
    }

    public void enableCheckInToggle() {
        seleniumWaits.untilElementIsVisible(qualifiedForCheckInToggle);
        System.out.println(qualifiedForCheckInToggle.getAttribute("aria-checked"));
        if (qualifiedForCheckInToggle.getAttribute("aria-checked").equals("false")) {
            seleniumActions.clickOnElement(qualifiedForCheckInToggle);
        }
        seleniumWaits.untilElementIsClickable(saveButton);
        seleniumActions.clickOnElement(saveButton);
    }

    public void checkInToggle(String yesOrNo) {
        seleniumWaits.untilElementIsVisible(qualifiedForCheckInToggle);
        System.out.println(qualifiedForCheckInToggle.getAttribute("aria-checked"));
        switch (yesOrNo) {
            case "enable":
                if (qualifiedForCheckInToggle.getAttribute("aria-checked").equals("false")) {
                    seleniumActions.clickOnElement(qualifiedForCheckInToggle);
                }
                break;
            case "disAble":
                if (qualifiedForCheckInToggle.getAttribute("aria-checked").equals("true")) {
                    seleniumActions.clickOnElement(qualifiedForCheckInToggle);
                }
                break;
        }

        seleniumWaits.untilElementIsClickable(saveButton);
        seleniumActions.clickOnElement(saveButton);
    }

    public String getGuestFirstName() {
        try {
            seleniumWaits.untilElementIsClickable(guestFirstName);
        } catch (Exception e) {
        }
        return seleniumActions.getTextMessage(guestFirstName);
    }

    public void openGuest() {
        seleniumActions.clickOnElement(guestFirstName);
    }

    public void clearDates(String embarkationOrDisembarkation) {
        String closeButton = "//input[@placeholder='%s Date']/../button/span/icon/*[name()='svg']/*[contains(@href,'close')]";
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath(String.format(closeButton, embarkationOrDisembarkation))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(closeButton, embarkationOrDisembarkation))));
    }

    public void setDates(String embarkationOrDisembarkation, String date) {
        clearDates(embarkationOrDisembarkation);
        String dateInput = "[placeholder='%s Date'] > div > input";
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(String.format(dateInput, embarkationOrDisembarkation))), date);
        seleniumActions.sleep(10000);
    }

    public void setDateAndTime(String date) {
        String dateInput = "#otalio-time-picker-lastSeenWhen-field input[type='text']";
        seleniumActions.clearValue(webDriver.findElement(By.cssSelector(dateInput)));
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(dateInput)), date);
    }

    public void setAgeFilterToAdult() {
        seleniumActions.moveToElement(ageFilter);
        seleniumActions.jsClick(ageFilter);
        seleniumActions.clickOnElement(minimumAgeInputBox);
        seleniumActions.clearValue(minimumAgeInputBox);
        seleniumActions.typeValue(minimumAgeInputBox, "22");
        seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
        seleniumActions.sleep(2000);
    }

    public void setAgeFilterToBe(String age) {
        seleniumActions.moveToElement(ageFilter);
        seleniumActions.jsClick(ageFilter);
        switch (age) {
            case "adult":
                seleniumActions.clickOnElement(minimumAgeInputBox);
                seleniumActions.clearValue(minimumAgeInputBox);
                seleniumActions.typeValue(minimumAgeInputBox, "22");
                break;
            case "child":
                seleniumActions.clickOnElement(maximumAgeInputBox);
                seleniumActions.clearValue(maximumAgeInputBox);
                seleniumActions.typeValue(maximumAgeInputBox, "17");
                seleniumActions.sleep(10000);
                break;
            case "minor":
                seleniumActions.clickOnElement(minimumAgeInputBox);
                seleniumActions.clearValue(minimumAgeInputBox);
                seleniumActions.typeValue(minimumAgeInputBox, "8");

                seleniumActions.clickOnElement(maximumAgeInputBox);
                seleniumActions.clearValue(maximumAgeInputBox);
                seleniumActions.typeValue(maximumAgeInputBox, "17");
                break;
            default:
                throw new IllegalArgumentException("Invalid age type: " + age);
        }

        seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
        seleniumActions.sleep(2000);
    }
    public String checkBox(String option) {
        return String.format("//span[normalize-space()='%s']/../preceding-sibling::mat-pseudo-checkbox", option);
    }

    public void checkOptionInTheFilter(String option) {
        seleniumActions.clickOnElement(crewInformationPage.getReservationStatusList());
        //String checkBoxxpath = checkBox("Checked In");
        //seleniumActions.interactWithElementByXpath(checkBoxxpath,false);
        String checkBox = "//span[text()='%s']/../preceding-sibling::mat-pseudo-checkbox";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(checkBox, option))));
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(500); //after pressing esc key
        seleniumActions.pressEscapeKey();
//        seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
    }

    public String getStateRoomStatus(String firstName) {
        String cssColour = webDriver
                .findElement(By.xpath("//h1[contains(text(),'" + firstName + "')]/ancestor::*/descendant::otalio-profile-card/descendant::div[contains(@class,'reservation-status')]"))
                .getCssValue("background-color");

        System.out.println(cssColour);
        return cssColour;
    }

    public void clickOnGuestName(String name) {
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//a[contains(@id,'breadcrumbs-link')][contains(text(),'" + name + "')]")));
    }
    public String getGuestName() {
        return guestName.getText().trim();
    }

    public String getGuestNameTwo() {
        return guestNameTwo.getText().trim();
    }

    public void openHiddenFilters() {
        List<WebElement> elements = webDriver.findElements(
                By.cssSelector("#complex-filter-collapse-expand-chip > div > div")
        );

        if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
            seleniumActions.jsClick(hiddenFiltersIcon);
            seleniumActions.sleep(1000);
        }
    }

    public void typeLoyaltyNumber(String filterName,String number){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//b[contains(text(),'" + filterName + "')]/../following-sibling::icon/*[name()='svg']")));
        seleniumActions.typeValue(loyaltyNumber,number);
        seleniumActions.sleep(3000);
    }

    public void typeLostCabinNumber(String filterName,String number){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[contains(text(),'" + filterName + "')]")));
        seleniumActions.typeValue(lostCabin,number);
        String Location = "//mat-pseudo-checkbox/../div/span[normalize-space()='" + number + "']";
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(Location))));
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[contains(text(),'" + filterName + "')]")));
        seleniumActions.pressEscapeKey();
    }

    public void changeStatusOfFilter(String filterName, String status) {
        seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.MINI_WAIT));
        switch (filterName) {
            case "Denied":
                if (webDriver.findElement(By.xpath("//b[contains(text(),'" + filterName + "')]/../following-sibling::icon/*[name()='svg']")).isDisplayed()) {
                    System.out.println("Assigned filter displayed");
                } else {
                    try {
                        seleniumActions.clickOnElement(hiddenFiltersIcon);
                    } catch (StaleElementReferenceException ste) {
                        webDriver.findElement(By.cssSelector("#complex-filter-collapse-expand-chip > div > div")).click();
                    } catch (NoSuchElementException nse) {
                        seleniumActions.clickOnElement(hiddenFiltersIcon);
                    }
                }
                seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//b[contains(text(),'" + filterName + "')]/../following-sibling::icon/*[name()='svg']")));
                for (WebElement checkBox : checkBoxes) {
                    try {
                        checkBox.click();
                    } catch (Exception e) {

                    }
                }
                break;

            case "Status":
                if (webDriver.findElement(By.xpath("//b[contains(text(),'" + filterName + "')]/../following-sibling::icon/*[name()='svg']")).isDisplayed()) {
                    System.out.println("Assigned filter displayed");
                } else {
                    try {
                        seleniumActions.clickOnElement(hiddenFiltersIcon);
                    } catch (StaleElementReferenceException ste) {
                        webDriver.findElement(By.cssSelector("#complex-filter-collapse-expand-chip > div > div")).click();
                    } catch (NoSuchElementException nse) {
                        seleniumActions.clickOnElement(hiddenFiltersIcon);
                    }
                }
                seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//b[contains(text(),'" + filterName + "')]/../following-sibling::icon/*[name()='svg']")));
                for (WebElement checkBox : checkBoxes) {
                    try {
                        checkBox.click();
                    } catch (Exception e) {

                    }
                }
                break;
            case "Cash Activity":
                try {
                    seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[contains(text(),'" + filterName + "')]/ancestor::button/descendant::icon/*[name()='svg']")));
                }catch (Exception e){

                    seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[contains(text(),'" + filterName + "')]/ancestor::button/descendant::icon/*[name()='svg']")));
                }

                for (WebElement checkBox : checkBoxes) {
                    try {
                        checkBox.click();
                    } catch (Exception e) {

                    }
                }
                break;

            case "Board Status":
                if (webDriver.findElement(By.xpath("//b[contains(text(),'" + filterName + "')]/../following-sibling::icon/*[name()='svg']")).isDisplayed()) {
                    System.out.println("Assigned filter displayed");
                } else {
                    try {
                        seleniumActions.clickOnElement(hiddenFiltersIcon);
                    } catch (StaleElementReferenceException ste) {
                        webDriver.findElement(By.cssSelector("#complex-filter-collapse-expand-chip > div > div")).click();
                    } catch (NoSuchElementException nse) {
                        seleniumActions.clickOnElement(hiddenFiltersIcon);
                    }
                }
                seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//b[contains(text(),'" + filterName + "')]/../following-sibling::icon/*[name()='svg']")));
                for (WebElement checkBox : checkBoxes) {
                    checkBox.click();
                }
                break;

            case "Onboard":
                if (webDriver.findElement(By.xpath("//b[contains(text(),'" + filterName + "')]/../following-sibling::icon/*[name()='svg']")).isDisplayed()) {
                    System.out.println("Onboard filter displayed");
                } else {
                    seleniumActions.clickOnElement(hiddenFiltersIcon);
                }
                seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//b[contains(text(),'" + filterName + "')]/../following-sibling::icon/*[name()='svg']")));

                break;

            case "Reservation Status":
                if (webDriver.findElement(By.xpath("//div[contains(text(),'" + filterName + "')]/following-sibling::span/../../following-sibling::icon/*[name()='svg']")).isDisplayed()) {
                    System.out.println("Reservation filter displayed");
                } else {
                    seleniumActions.clickOnElement(hiddenFiltersIcon);
                }
                seleniumActions.clickOnElement(
                        webDriver.findElement(By.xpath(
                                "//div[contains(text(),'" + filterName + "')]/following-sibling::span/../../following-sibling::icon/*[name()='svg']"
                        ))
                );
                break;
            case "Loyalty Tier":
                if (webDriver.findElement(By.xpath("//div[contains(text(),'" + filterName + "')]/ancestor::button/descendant::icon/*[name()='svg']")).isDisplayed()) {
                    System.out.println("Reservation filter displayed");
                } else {
                    seleniumActions.clickOnElement(hiddenFiltersIcon);
                }
                seleniumActions.clickOnElement(
                        webDriver.findElement(By.xpath(
                                "//div[contains(text(),'" + filterName + "')]/ancestor::button/descendant::icon/*[name()='svg']"
                        ))
                );
                break;
            case "Assigned":
                if (webDriver.findElement(By.xpath("//b[contains(text(),'" + filterName + "')]/../following-sibling::icon/*[name()='svg']")).isDisplayed()) {
                    System.out.println("Assigned filter displayed");
                } else {
                    try {
                        seleniumActions.clickOnElement(hiddenFiltersIcon);
                    } catch (StaleElementReferenceException ste) {
                        webDriver.findElement(By.cssSelector("#complex-filter-collapse-expand-chip > div > div")).click();
                    } catch (NoSuchElementException nse) {
                        seleniumActions.clickOnElement(hiddenFiltersIcon);
                    }
                }
                seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//b[contains(text(),'" + filterName + "')]/../following-sibling::icon/*[name()='svg']")));
                for (WebElement checkBox : checkBoxes) {
                    checkBox.click();
                }
                break;
        }

        if (webDriver.findElement(By.xpath("//div[contains(normalize-space(.), '" + status + "')]/preceding-sibling::mat-pseudo-checkbox")).getAttribute("class").contains("checked")) {
            System.out.println("CheckBox :: " + status);
        } else {
            seleniumActions.jsClick(webDriver.findElement(By.xpath("//div[contains(normalize-space(.), '" + status + "')]/preceding-sibling::mat-pseudo-checkbox")));
        }
        seleniumActions.sleep(500); // before press esc key
        seleniumActions.pressEscapeKey();
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(2000);
    }

    public void addFilter(String filterName) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.MINI_WAIT));
        if (webDriver.findElements(By.xpath("//*[contains(text(),'" + filterName + "')]/following-sibling::span")).size() == 0) {
            seleniumActions.jsClick(moreFiltersIcon);
            if (webDriver.findElements(By.cssSelector("[id='complex-filter-search-add-more-filters']")).size() > 0) {
                System.out.println("Search box displayed");
            } else {
                seleniumActions.jsClick(moreFiltersIcon);
            }
            seleniumActions.typeValue(filterSearchInputBox, filterName);
            seleniumActions.sleep(2000);
            if (webDriver.findElement(By.xpath("//span[@class='mat-option-text']/span[contains(text(),'" + filterName + "')]/../preceding-sibling::*")).getAttribute("class").contains("checked")) {
                System.out.println("filterName :: " + filterName);
            } else {
                seleniumActions.sleep(2000);
                seleniumActions.jsClick(webDriver.findElement(By.xpath("//span[@class='mat-option-text']/span[contains(text(),'" + filterName + "')]/../preceding-sibling::*")));
            }
            seleniumWaits.untilElementIsVisible(breadCrumb);
            seleniumActions.moveToElementAndClickOnElement(breadCrumb, breadCrumb);
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void addLoyaltyFilter(String filterName) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        if (webDriver.findElements(By.xpath("//*[contains(text(),'" + filterName + "')]/following-sibling::span")).size() == 0) {
            seleniumActions.jsClick(moreFiltersIcon);
            if (webDriver.findElements(By.cssSelector("[id='complex-filter-search-add-more-filters']")).size() > 0) {
                System.out.println("Search box displayed");
            } else {
                seleniumActions.jsClick(moreFiltersIcon);
            }
            seleniumActions.typeValue(filterSearchInputBox, filterName);
            if (webDriver.findElement(By.xpath("(//span[@class='mat-option-text']/span[contains(text(),'" + filterName + "')]/../preceding-sibling::*)[1]")).getAttribute("class").contains("checked")) {
                System.out.println("filterName :: " + filterName);
            } else {
                seleniumActions.sleep(2000);
                seleniumActions.jsClick(webDriver.findElement(By.xpath("(//span[@class='mat-option-text']/span[contains(text(),'" + filterName + "')]/../preceding-sibling::*)[1]")));
            }
            seleniumWaits.untilElementIsVisible(breadCrumb);
            seleniumActions.moveToElementAndClickOnElement(breadCrumb, breadCrumb);
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void addLoyaltyTireFilter(String filterName) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        if (webDriver.findElements(By.xpath("//*[contains(text(),'" + filterName + "')]/following-sibling::span")).size() == 0) {
            seleniumActions.jsClick(moreFiltersIcon);
            if (webDriver.findElements(By.cssSelector("[id='complex-filter-search-add-more-filters']")).size() > 0) {
                System.out.println("Search box displayed");
            } else {
                seleniumActions.jsClick(moreFiltersIcon);
            }
            seleniumActions.typeValue(filterSearchInputBox, filterName);
            if (webDriver.findElement(By.xpath("(//span[@class='mat-option-text']/span[contains(text(),'" + filterName + "')]/../preceding-sibling::*)[2]")).getAttribute("class").contains("checked")) {
                System.out.println("filterName :: " + filterName);
            } else {
                seleniumActions.sleep(2000);
                seleniumActions.jsClick(webDriver.findElement(By.xpath("(//span[@class='mat-option-text']/span[contains(text(),'" + filterName + "')]/../preceding-sibling::*)[2]")));
            }
            seleniumWaits.untilElementIsVisible(breadCrumb);
            seleniumActions.moveToElementAndClickOnElement(breadCrumb, breadCrumb);
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void addDisembarkationFilter(String search,String filterName) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        if (webDriver.findElements(By.xpath("//div[contains(text(),'" + filterName + "')]/following-sibling::span")).size() == 0) {
            seleniumActions.jsClick(moreFiltersIcon);
            if (webDriver.findElements(By.cssSelector("[id='complex-filter-search-add-more-filters']")).size() > 0) {
                System.out.println("Search box displayed");
            } else {
                seleniumActions.jsClick(moreFiltersIcon);
            }
            seleniumActions.typeValue(filterSearchInputBox, search);
            if (webDriver.findElement(By.xpath("//span[@class='mat-option-text']/span[contains(text(),'" + filterName + "')]/../preceding-sibling::*")).getAttribute("class").contains("checked")) {
                System.out.println("filterName :: " + filterName);
            } else {
                seleniumActions.sleep(2000);
                seleniumActions.jsClick(webDriver.findElement(By.xpath("//span[@class='mat-option-text']/span[text()='" + filterName + "']/../preceding-sibling::*")));
            }
            seleniumWaits.untilElementIsVisible(breadCrumb);
            seleniumActions.moveToElementAndClickOnElement(breadCrumb, breadCrumb);
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public String getCabinNumberOnGuestPage() {
        seleniumActions.sleep(3000);
        return cabinNumberGuestPage.getText();
    }

    public String getLanguageSpeaking() {
        seleniumActions.sleep(3000);
        return speakingGuestPage.getText();
    }

    public String getCabinNumber() {
        seleniumActions.sleep(3000);
        return cabinNumbers.get(randomNumber()).getText();
    }

    public void searchAndSelectCabin(String cabinNumber) {
        seleniumActions.sleep(3000);
        seleniumActions.typeValue(searchCabinInputBox, cabinNumber);
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsVisible(cabinNumbers.get(0));
        seleniumActions.clickOnElement(cabinNumbers.get(0));
        commonPage.navigateTo("SAVE");
        seleniumWaits.untilElementIsVisible(reasonInputBox);
        seleniumActions.typeValue(reasonInputBox, "TestAutomation");
        seleniumActions.highLighterMethod(confirmButton);
        seleniumWaits.untilElementIsClickable(confirmButton);
        seleniumActions.clickOnElement(confirmButton);
        if (webDriver.findElements(By.xpath("//button[@id='cabin-move-popup-confirm-button']")).size() > 0) {
            seleniumActions.jsClick(confirmButton);
        }

        if (webDriver.findElements(By.xpath("//button[@id='cabin-move-popup-confirm-button']")).size() > 0) {
            seleniumActions.moveToElementAndClick(confirmButton);
        }
        seleniumActions.sleep(3000);
        seleniumActions.sleep(3000);


    }

    public void reEditConfirm(String cabinNumber) {
        seleniumActions.clickOnElement(cabinEditButton);
        seleniumActions.clickOnElement(cabinNumbers.get(0));
        commonPage.navigateTo("SAVE");
        seleniumWaits.untilElementIsVisible(reasonInputBox);
        seleniumActions.typeValue(reasonInputBox, "TestAutomation");
        seleniumActions.clickOnElement(confirmButton);
    }

    public void editCabinInformation() {
        seleniumActions.clickOnElement(cabinEditButton);
    }

    public String getLoyaltyTireText() {
        seleniumActions.sleep(2000);
        seleniumActions.clickOnElement(classificationTab);
        seleniumWaits.untilElementIsVisible(loyaltyTab);
        seleniumActions.clickOnElement(loyaltyTab);
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(loyaltyTireText);
    }

    public void changeStatus(String status, String loyaltyProgram, String loyaltyTier) {

        openDropDown(status);
        seleniumActions.clickOnElement(classificationTab);
        seleniumWaits.untilElementIsVisible(loyaltyTab);
        seleniumActions.clickOnElement(loyaltyTab);
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath("//div[normalize-space()='" + status + "']")));
        seleniumActions.clickOnElement(loyaltyProgramDropdown);
        seleniumActions.typeValue(searchBox, loyaltyProgram);
        commonPage.navigateTo(loyaltyProgram);
        seleniumActions.clickOnElement(loyaltyTierDropdown);

        try {
            seleniumActions.typeValue(searchBox, loyaltyTier);
        } catch (StaleElementReferenceException ste) {
            seleniumActions.typeValue(webDriver.findElement(By.cssSelector("[placeholder='Search']")), loyaltyTier);
        }
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//mat-option/span[contains(normalize-space(), '" + loyaltyTier + "')]")));
        seleniumWaits.untilElementIsVisible(saveButton);
        seleniumActions.clickOnElement(saveButton);
        seleniumActions.sleep(2000);
    }

    /*public void openDropDown(String dropdown) {
        seleniumActions.sleep(5000);
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath("(//div[normalize-space()='" + dropdown + "'][not(h3)])[1]")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//div[normalize-space()='" + dropdown + "'][not(h3)])[1]")));
    }
*/
    public void openSubMenu(String dropdown) {
        /*seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("//div[text()='" + dropdown + "']")));
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("//div[text()='" + dropdown + "']")));*/
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("//div[text()='" + dropdown + "']")));
        seleniumActions.moveToElement(webDriver.findElement(By.xpath("//div[text()='" + dropdown + "']")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[text()='" + dropdown + "']")));
        seleniumActions.sleep(3000);
    }

    public void refreshPage() {
        webDriver.navigate().refresh();
        seleniumActions.sleep(10000); // after refresh load
    }

    public String getStatus() {
        seleniumActions.sleep(2000);
        return statusInformation.getText().trim();
    }

    public void openGuestServiceItem(String serviceName) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("//div[normalize-space()='" + serviceName + "']/a")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[normalize-space()='" + serviceName + "']/a")));
        try {
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            if (webDriver.findElements(By.xpath("//div[normalize-space()='" + serviceName + "']/a")).size() > 0) {
                seleniumActions.clickOnElement(By.xpath("//div[normalize-space()='" + serviceName + "']/a"));
            }
        } catch (Exception exc) {
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(FileReaderManager.getInstance().getConfigFileReader().getTime()));
    }

    public void openCreateMaintenanceRequest(String serviceName) {
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("//div[normalize-space()='" + serviceName + "']/button")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[normalize-space()='" + serviceName + "']/button")));
        try {
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            if (webDriver.findElements(By.xpath("//div[normalize-space()='" + serviceName + "']/button")).size() > 0) {
                seleniumActions.clickOnElement(By.xpath("//div[normalize-space()='" + serviceName + "']/button"));
            }
        } catch (Exception exc) {
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(FileReaderManager.getInstance().getConfigFileReader().getTime()));
    }

    public void navigateToCrewInformation(String guestFirstName) {
        seleniumActions.sleep(1000);
        seleniumActions.clickOnElement(perPageLink100);

        try {
            seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("(//a[normalize-space()='" + guestFirstName + "'])[1]")), 15);
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("(//a[normalize-space()='" + guestFirstName + "'])[1]")));
        } catch (Exception ste) {
            seleniumActions.sleep(3000);
            webDriver.findElement(By.xpath("(//a[normalize-space()='" + guestFirstName + "'])[1]")).click();
        }
    }

    public void searchCruise(String cruiseName) {
        cruiseSearchBox.sendKeys(cruiseName);
        seleniumActions.clickOnElement(searchDataCruise);
    }

    public void searchUser(String userName){
        crewSearchBox.clear();
        seleniumActions.typeValue(crewSearchBox, userName);
        seleniumActions.clickOnElement(perPageLink100);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public void openCrewInformation(String crewFirstName) {
        seleniumActions.typeValue(crewSearchBox, crewFirstName);
        navigateToCrewInformation(crewFirstName);
    }

    public void openCrewProfile(String crewFirstName, String lastName) {
        seleniumActions.typeValue(crewSearchBox, crewFirstName);
        seleniumActions.sleep(2000);
        navigateToCrewInformation(lastName);
    }

    public void openGuest(String lastName) {
        navigateToCrewInformation(lastName);
    }

    public String getIntoxText(){
        return seleniumActions.getTextMessage(intoxText);
    }

    public List<String> getHighlightedReservationStatusListOfAll() {
        return reservationStatusText.stream()
                .map(element -> {
                    // Highlight each element while extracting text
                    seleniumActions.highLighterMethod(element);
                    return element.getText().trim();
                })
                .collect(Collectors.toList());
    }

    private void setOptionInFilter(String option, boolean shouldBeChecked) {
        seleniumActions.clickOnElement(crewInformationPage.getReservationStatusList());

        String checkBox = "//span[text()='%s']/../preceding-sibling::mat-pseudo-checkbox";
        WebElement checkboxElement = webDriver.findElement(By.xpath(String.format(checkBox, option)));

        boolean isChecked = checkboxElement.getAttribute("class")
                .contains("mat-pseudo-checkbox-checked");

        if (isChecked != shouldBeChecked) {
            seleniumActions.clickOnElement(checkboxElement);
        }

        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(500);
        seleniumActions.pressEscapeKey();
        seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
    }

    public void checkOptionFromTheFilter(String option) {
        setOptionInFilter(option, true);
    }

    public void uncheckOptionFromTheFilter(String option) {
        setOptionInFilter(option, false);
    }
    public void userOpenServiceItemCard(String serviceName) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        By serviceLocator = By.xpath("//div[normalize-space()='" + serviceName + "']/otalio-link-box//a");

        try {
            WebElement serviceElement = wait.until(ExpectedConditions.elementToBeClickable(serviceLocator));
            seleniumActions.highLighterMethod(serviceElement);
            serviceElement.click();
        } catch (TimeoutException e) {
            seleniumActions.highLighterMethod(webDriver.findElement(serviceLocator));
            seleniumActions.jsClick(webDriver.findElement(serviceLocator));
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        }
    }
    public By getDropDownLocator(String dropdown) {

        String xpath = String.format("(//div[normalize-space()='%s'][not(h3)])[1]", dropdown);
        return By.xpath(xpath);
    }

    public void openDropDown(String dropdown) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement dropDownElement = webDriver.findElement(getDropDownLocator(dropdown));
        seleniumWaits.untilElementIsVisible(dropDownElement);
        seleniumActions.scrollInToElement(dropDownElement);
        seleniumActions.clickOnElement(dropDownElement);
    }

    public void clickOnPersonalItems() {
        seleniumWaits.untilElementIsVisible(personalItems);
        seleniumActions.clickOnElement(personalItems);
    }
}
