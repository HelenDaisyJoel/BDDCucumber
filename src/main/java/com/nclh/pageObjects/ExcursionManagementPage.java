package com.nclh.pageObjects;

import com.nclh.constants.*;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import junit.framework.Assert;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ExcursionManagementPage {
    private WebDriver webDriver;
    private SeleniumActions seleniumActions;
    private CommonPage commonPage;
    private SeleniumWaits seleniumWaits;
    private CommonFunctions commonFunctions;
    public static final Logger logger
            = LoggerFactory.getLogger(SeleniumActions.class);


    public ExcursionManagementPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        seleniumActions = new SeleniumActions(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonPage = new CommonPage(webDriver);
        commonFunctions = new CommonFunctions();
    }

    @FindBy(css = "[icon*='plus']>svg>use")
    WebElement plusButton;

    @FindBy(css = "[icon='edit']>svg>use:first-of-type")
    WebElement editButton;

    @FindBy(xpath = "//span[@id='breadcrumbs-title']")
    WebElement templateCode;

    @FindBy(css = "[placeholder='External Template Description']")
    WebElement externalDescription;

    @FindBy(css = "[placeholder='Article Class']")
    WebElement articleClass;

    @FindBy(css = "[placeholder='Name (EN)']")
    WebElement enterNameEN;

    @FindBy(css = "[placeholder='External Code']")
    WebElement typeExternalCode;

    @FindBy(css = "[placeholder='Description (EN)']")
    WebElement typeDescriptionEN;

    @FindBy(css = "[placeholder='Brochure ID']")
    WebElement typeBrochureID;

    @FindBy(css = "[placeholder='Languages']")
    WebElement typeLanguages;

    @FindBy(css = "[placeholder='Search']")
    WebElement searchLanguages;

    @FindBy(css = "[placeholder='Age Group']")
    WebElement typeAgeGroup;

    @FindBy(css = "[placeholder='Price']")
    WebElement typePrice;

    @FindBy(xpath = "//otalio-time-picker-adapter[@placeholder='Departure Time']//input[@class='dx-texteditor-input']")
    WebElement typeDeparture;

    @FindBy(xpath = "//otalio-time-picker-adapter[@placeholder='Meeting Time']//input[@class='dx-texteditor-input']")
    WebElement typeMeeting;

    @FindBy(css = "[placeholder='Meeting Location']")
    WebElement typeMeetingLocation;

    @FindBy(css = "[placeholder='Tour Template Code']")
    WebElement typeTourCode;

    @FindBy(css = "[placeholder='Tour Template Description']")
    WebElement typeTourDescription;

    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-area-field']/descendant::div/div/div[contains(@class,'mat-select-arrow')]")
    WebElement areaOperation;

    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-countryId-field']/descendant::div/div/div[contains(@class,'mat-select-arrow')]")
    WebElement countryOperation;

    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-portId-field']/descendant::div/div/div[contains(@class,'mat-select-arrow')]")
    WebElement portOperation;

    @FindBy(xpath = "//span[normalize-space()='Save']")
    WebElement saveButton;

    @FindBy(xpath = "//div[normalize-space()='Prices for Age Groups']")
    WebElement ageGroups;

    @FindBy(css = "[placeholder='Code']")
    private WebElement codeSearchBox;

    @FindBy(xpath = "//div[normalize-space()='Code']/ancestor::div[contains(@class,'headers')]/following-sibling::div[contains(@class,'rowsview')]/descendant::tr/td[6]")
    private WebElement getCode;

    @FindBy(xpath = "//td[@role='columnheader']")
    private List<WebElement> columnHeader;

    @FindBy(xpath = "//td[@role='columnheader'][contains(@style,'width')][@tabindex][not(@rowspan)]")
    private List<WebElement> columnHeaderForTour;

    @FindBy(xpath = "//span[normalize-space()='Add new Age Group']")
    private WebElement addNewAgeGroup;

    @FindBy(xpath = "//div[normalize-space()='NCL Lab QA1']//otalio-current-location")
    private WebElement priceForAge;

    @FindBy(xpath = "//span[text()='Age Group']/ancestor::mat-select")
    private WebElement typeNewAgeGroup;

    @FindBy(xpath = "//input[@id='departure-editor-form-price-1-input']")
    private WebElement typeNewPrice;

    @FindBy(xpath = "//input[@id='departure-editor-form-price-2-input']")
    private WebElement typeMultiPrice;

    @FindBy(xpath = "//button[contains(@id,'tour-activity-transportation')]")
    private WebElement addButton;

    @FindBy(xpath = "(//div[text()='Language']/ancestor::div[@aria-label='Data grid']/descendant::td[@role='gridcell']/div/span[contains(@class,'text-primary')])[1]")
    private List<WebElement> languagesData;

    @FindBy(xpath = "//h3[text()='Booking Nr']/ancestor::otalio-passenger-booking-info/descendant::otalio-chip/div/div")
    private WebElement bookingNrInformation;

    @FindBy(xpath = "//otalio-embark-disembark-card//span[text()='Disembarkation']/../../div[@class='text-type-info text-dark']")
    private WebElement disembarkationDateInformation;

    @FindBy(xpath = "//otalio-embark-disembark-card//span[text()='Embarkation']/../../../div/div[@class='text-type-info text-dark']")
    private  WebElement embarkationDateInformation;

    @FindBy(css = "[data-placeholder='Embarkation Date']")
    private WebElement embarkationDetails;

    @FindBy(css = "[data-placeholder='Disembarkation Date']")
    private WebElement disembarkationDetails;

    @FindBy(xpath = "//input[@placeholder='Booking Nr']")
    private WebElement bookingDetails;

    @FindBy(xpath = "//button[@id='tour-activity-creation-save-button']")
    private WebElement disabledButton;

    @FindBy(xpath = "//otalio-account-profile-subsection//div[contains(text(),'Special Needs')]")
    private WebElement specialNeeds;

    @FindBy(xpath = "//span[text()='Special Needs']/../../../../../otalio-infinite-select")
    private WebElement specialNeedsDropDown;

    @FindBy(xpath = "(//*[contains(text(),'Special Needs')]/following-sibling::*/descendant::otalio-chip/div/div)[1]")
    private List<WebElement> specialNeedsList;

//    @FindBy(css = "[placeholder='Search']")
    @FindBy(xpath = "//*[contains(@placeholder, 'Search')]")
    private WebElement searchInputField;

    @FindBy(xpath = "//button[@id='complex-filter-label-date-open-button']/child::icon[@icon='arrow-down']/*[name()='svg']/*[name()='use']")
    private WebElement dateArrowDownIcon;

    @FindBy(xpath = "//button[contains(@id,'date-overlay-clear-button')]/span/icon/*[name()='svg']/*[name()='use']")
    private WebElement clearDateButton;

    @FindBy(xpath = "//h4[normalize-space()='Excursion Bookings Overview']")
    private WebElement excursionHeaderTitle;

    @FindBy(css = "[placeholder='Min Capacity']")
    private WebElement minCapacityInputField;

    @FindBy(css = "[placeholder='Max Capacity']")
    private WebElement maxCapacityInputField;

    @FindBy(css = "[placeholder='Available Capacity']")
    private WebElement availableCapacityInputField;

    @FindBy(xpath = "//input[@placeholder='Min Capacity']/ancestor::mat-form-field/descendant::mat-error")
    private WebElement minInputFieldErrorMessage;

    @FindBy(xpath = "//input[@placeholder='Max Capacity']/ancestor::mat-form-field/descendant::mat-error")
    private WebElement maxInputFieldErrorMessage;

    @FindBy(xpath = "//input[@placeholder='Available Capacity']/ancestor::mat-form-field/descendant::mat-error")
    private WebElement availableInputFieldErrorMessage;

    @FindBy(xpath = "//button[@id='bookings-guests-add-button' or @id='tour-guests-add-button']")
    private WebElement bookingsGuestsAddButton;

    @FindAll({
            @FindBy(xpath = "//h2[text()='Guest Search']/ancestor::otalio-guest-search-side-wrapper//button[@id='complex-filter-add-more-button']"),
            @FindBy(xpath = "//*/ancestor::otalio-guest-search-side-wrapper//button[@id='complex-filter-add-more-button']")
    })
    private WebElement complexFilterAddMoreButton;

    @FindBy(css = "[id*='activeTo-open-button'] div + icon, [id*='date-open-button'] div + *")
    private WebElement disembarkationDateArrowDownButton;

    @FindBy(css = "[id='complex-filter-collapse-expand-chip']")
    private WebElement addMoreFilterButton;

    @FindBy(css = "[id*='date-open-button'] > div + icon")
    private WebElement dateArrowDownButton;

    @FindBy(xpath = "//mat-label[text()='Equal Date']/ancestor::mat-form-field/descendant::button[contains(@id,'clear-button')]")
    private WebElement clearEqualDate;

    @FindBy(css = "[icon='full-width-blocks']")
    private WebElement fullWidthBlock;

//    @FindBy(xpath = "(//span[normalize-space()='%s'])[1]")
//    private WebElement bookButton;

    @FindBy(xpath = "(//div[contains(@class,'booking-title')]/span)[last()]")
    private WebElement getBookTittleName;

    @FindBy(xpath = "//span[normalize-space()='Post']")
    private WebElement postButton;

    @FindBy(xpath = "(//span[normalize-space()='Book']/ancestor::otalio-bookable-item/descendant::div[contains(@class,'dashed-border')])[1]/div/div")
    private List<WebElement> getBookingDate;

    @FindBy(css = "[placeholder*='Search by Participant']")
    private WebElement participantSearchField;

    @FindBy(xpath = "//button[@id='tour-guests-add-button']")
    private WebElement tourGuestsAddButton;

    @FindBy(css = "[placeholder='Tour Max Weight']")
    private WebElement tourMaxWeightInputField;

    @FindBy(css = "[placeholder='Max Weight per Guest']")
    private WebElement tourMaxWeighPerGuestInputField;

    @FindBy(css = "[id='bookings-actions-menu-button']")
    private WebElement bookingActionMenuButton;

    @FindBy(css = "#complex-filter-collapse-expand-chip > div > div")
    private WebElement hiddenFiltersIcon;

    @Getter
    @FindBy(xpath = "(//div[text()='Participant']/ancestor::dx-data-grid/descendant::td[@role='gridcell'][@aria-colindex='3'][@class='dx-hidden-cell'])[1]")
    private WebElement participantNameText;

    @FindBy(css = "[class*='pseudo-checkbox-checked']")
    private List<WebElement> checkBoxes;

    @FindBy(css = "button[id*='bookingStatus'] > icon")
    private WebElement bookingStatusList;

    @FindBy(css = "#departures-card-creation-button")
    private WebElement departurePlusButton;

    @FindBy(css = "[role='option'] > span")
    private List<WebElement> optionsDropDown;

    @FindBy(css = "[role='option'] > span > span")
    private List<WebElement> optionsListDropDown;

    @FindBy(css = "[placeholder='Operator']")
    private WebElement operationDropDown;

    @FindBy(xpath = "//mat-select[@placeholder='Age Group'][contains(@class,'mat-select-empty')]")
    private WebElement ageGroupDropDown;

    @FindBy(xpath = "//otalio-decimal-amount-control[@placeholder='Price']/input[contains(@class,'untouched')]")
    private WebElement priceInputField;

    @FindBy(xpath = "//input[@id='tour-article-information-code-input']")
    private WebElement toutCodeText;

    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-countryId-field']/descendant::span/span")
    private WebElement getCountryNameText;

    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-portId-field']/descendant::span/span")
    private WebElement getPortNameText;

    @FindBy(xpath = "//textarea[@id='description-en-input']")
    private WebElement nameENText;

    @FindBy(xpath = "(//a[@id='quick-booking-item-tour-link'])[1]")
    private  WebElement gridTourCode;

    @FindBy(xpath = "(//div[contains(@class, 'tour-description')])[1]")
    private WebElement gridTourDescription;

    @FindBy(xpath = "(//tr[@role='row'])[3]//td[3]")
    private  WebElement searchedTourCode;

    @FindBy(xpath = "(//tr[@role='row'])[3]//td[4]")
    private WebElement searchedTourName;


    @FindBy(xpath = "//*[@id='complex-filter-search-input']")
    private WebElement bookingsOverviewSearchBox;
    By searchBar = By.xpath("//*[contains(@data-placeholder,'Search')]");
    By countryDropDown = By.xpath("//mat-form-field[.//*[normalize-space(text())='Country']]//div[@aria-label='Select' or contains(@class,'mat-select-arrow-wrapper')]");
    By editButtonIcon = By.xpath("//table//tbody//tr[3]//a[@mattooltip='Edit']");
    By transportationManagement = By.xpath("//*[contains(text(),'Transportation Management - Languages')]");
    By operationsHeader = By.xpath("//*[text()='Operations']");

    @FindBy(css = "[mattooltip='Add Language']")
    private WebElement addLanguagePlusIcon;
    @FindAll({
            @FindBy(css = "[placeholder^='Search']"),
            @FindBy(xpath = "//input[@id='complex-filter-search-input']")
    })
    private WebElement sectionSearchBox;

    @FindBy(id = "tour-language-dialog-save-button")
    private WebElement addLanguageSaveButton;
    private By editIcon = By.xpath("//table//tbody//tr[1]//button[@mattooltip='Edit']");
    private By deleteIcon = By.xpath("//table//tbody//tr[1]//button[@mattooltip='Delete']");
    private By languageCell = By.xpath("(//table)[2]//tbody//tr[1]/td[count((//table)[1]//th[normalize-space()='Language']/preceding-sibling::th)+1]");
    private By languageDropdown = By.xpath("//*[@placeholder='Languages']");
    private By deleteButtons = By.xpath("//button[@mattooltip='Delete']");
    private By totalValueLocator = By.xpath("//*[normalize-space()='Total']/following-sibling::*[1]");
    private By grossAmountFirstRowLocator = By.xpath("//tr[@aria-rowindex='1']//td[@aria-colindex='6' and not(contains(@class,'dx-hidden-cell'))]");
    private By bookableItemsLocator = By.xpath("//otalio-bookable-item-container");
    public WebElement getNameEN(){
        return nameENText;
    }
    By crewProfilePic = By.xpath("//otalio-guest-search-container//otalio-passenger-picture");
    @FindBy(css = "[type='search']")
    private WebElement searchInputBox;
    @FindBy(xpath = "//span[normalize-space()='Select']/../../../../descendant::otalio-passenger-picture/following-sibling::div/div[contains(@class,'tooltip')]")
    private List<WebElement> guestNames;
    @FindAll({
            @FindBy(css = "[placeholder^='Search']"),
            @FindBy(css = "input.complex-search-input")
    })
    private WebElement crewSearchBox;
    By specialNeedsComments = By.xpath("(//input[contains(@id,'personal-special-needs-comments')])[last()]");
    By addBtn = By.xpath("//button[@type='button']//span[normalize-space()='Add Special Needs']");
    By addSpecialNeedsBtn = By.xpath("//span[normalize-space()='Add Special Needs']");
    By addSpecialNeedsDropDown = By.xpath("(//mat-form-field[.//*[normalize-space(text())='Special Needs']]//div[@aria-label='Select' or contains(@class,'mat-select-arrow-wrapper')])[last()]");
    By specialNeedsIcon = By.xpath("//otalio-guest-search-selected-area//otalio-special-request-icons");
    By bookingConfirmationHeader = By.xpath("//*[contains(text(),'Booking Confirmation')]");
    By signaturePadConfirmationButton = By.xpath("//form//button//*[contains(text(),'Confirm')]");
    @FindBy(xpath = "//form//signature-pad")
    private WebElement signaturePad;
    @FindBy(css = "input[id*='search-input']")
    private WebElement searchBookingsOverview;
    By excursionBookingsOverview = By.xpath("//div//*[contains(text(),'Excursion Bookings Overview')]");
    @FindBy(css = "[id*='bookings-overview--date']")
    private List<WebElement> bookingsOverviewDateLinks;
    @FindBy(xpath = "//div[@class='info-row']//a")
    private WebElement bookingCodeElement;
    By nextArrow = By.xpath("//button[contains(@class,'btn-next')]//icon[@icon='arrow-right']");
    By dateRangeRadioButton = By.xpath("//input[@type='radio' and @value='range']");
    @FindBy(css = "[id*='date-open-button'] > div + icon")
    private WebElement dateDropDown;
    @FindBy(xpath = "//input[@formcontrolname='startDate']/../../div/descendant::icon[contains(@icon,'close')]/*[name()='svg']/*[name()='use']")
    private WebElement clearStartDate;

    @FindBy(xpath = "//input[@formcontrolname='endDate']/../../div/descendant::icon[contains(@icon,'close')]/*[name()='svg']/*[name()='use']")
    private WebElement clearEndDate;
    String startDateInput = "[formcontrolname='startDate']";
    String endDateInput = "//mat-label[text()='Equal or less Date']//ancestor::mat-form-field//input[@data-placeholder='m/d/yyyy']";
    @FindBy(xpath = "//a[@mattooltip='Add new Tour Activity']")
    private WebElement btnAddNewTourActivityPlusButton;
    By articleGeneralInformation = By.xpath("//*[text()='Article General Information']");
    By departure = By.xpath("//*[text()='Departure']");
    String selectDropDownText = "//span[contains(text(),'Greece')]";
    String searchField = "#otalio-infinite-select-languages-field-search";
    @FindBy(css = "[id*='otalio-datepicker-transportation-management-datepicker-month-select-button']")
    private WebElement monthCalender;
    @FindBy(css = "[id*='otalio-datepicker-transportation-management-datepicker-year-select-button']")
    private WebElement yearCalender;
    @FindBy(xpath = "//div[contains(@class,'dx-datagrid-content-fixed')]//tr[1]/td")
    private List<WebElement> bookingOverData;
    @FindBy(css = "[id*='bookings-overview--description-a']")
    private List<WebElement> tourDetails;

    @FindBy(xpath = "//otalio-tour-sales-card-container//div[.='Sales']")
    private WebElement salesHeader;

    @FindBy(xpath = "//*[normalize-space()='Departures & Prices']/ancestor::otalio-departures-card//tr[@aria-rowindex]//a[@mattooltip='Edit']")
    private List<WebElement> editPencileIcon;

    @FindBy(xpath = "//*[normalize-space()='Departures & Prices']/ancestor::otalio-departures-card//tr[@aria-rowindex]//td[1]")
    private List<WebElement> dropdownIcon;
    @FindBy(xpath = "(//span[normalize-space()='Book']/ancestor::otalio-bookable-item/descendant::div[contains(@class,'dashed-border')])[1]/div/div")
    private List<WebElement> bookTourDetails;
    @FindBy(xpath = "(//div[contains(@class,'port-row')]//div[1])[1]")
    private WebElement portCountryCode;
    @FindBy(xpath = "(//div[contains(@class,'between align-items-end')])[1]")
    private WebElement passengerDetails;
    @FindBy(css = "#complex-filter-search-input")
    private WebElement searchCabinInputBox;
    @FindBy(xpath = "(//table[contains(@class,'dx-datagrid-table')]//tbody//tr/td[3])[position()>2]")
    private List<WebElement> excursionCodeList;
    @FindBy(css = "#bookings-list-menu-button")
    private WebElement actionButton;
    @FindBy(xpath = "//div[normalize-space()='Select Participants']/ancestor::mat-step-header/following::button[.//span[normalize-space()='Next']][1]")
    private WebElement firstNextButton;
    @FindBy(xpath = "//div[normalize-space()='Choose Operation Group']/ancestor::mat-step-header/following::button[.//span[normalize-space()='Next']][2]")
    private WebElement secondNextButton;
    @FindBy(xpath = "//otalio-bulk-update-form//input[@class='mat-radio-input']")
    private WebElement postBookingRadioButton;
    @FindBy(xpath = "//otalio-bulk-update-form//mat-radio-button[@color='primary']")
    private WebElement postBookingText;
    @FindBy(css = "#bulk-update-confirm-button")
    private WebElement operationDetailsPageConfirmButton;
    By successCount = By.xpath("//a[contains(@class,'text-success') and normalize-space()='1']");
    By status = By.xpath("//*[contains(@class,'mat') and normalize-space()='Done']");
    private By horizontalSlider = By.xpath("//div[@class='dx-scrollable-scrollbar dx-widget dx-scrollbar-horizontal dx-scrollbar-hoverable']//div[@class='dx-scrollable-scroll']");
    private By payerColumn = By.xpath("//*[normalize-space()='Payer']");
    private By payerInfo = By.xpath("//otalio-user-name");
    private By bookingTourButton = By.xpath("//button[@color='primary' and contains(@id,'book-item-actions-book')]");
    private By postButtonEnable = By.xpath("//button[@id='bookings-list-post-button' and not(@disabled)]");
    @FindBy(xpath = "(//div[@id='toast-message-content' and contains(normalize-space(),'records were created successfully')])[2]")
    private WebElement successToastMessage;
    @FindBy(css = "[id*='bookings-overview-expand--button']")
    private List<WebElement> bookingsExpandButton;
    @FindBy(xpath = "//mat-checkbox[1]")
    private WebElement bookingCheckBox;
    @FindBy(css = "[id*='toast-title-success']")
    private WebElement successPopUpMessage;
    @FindBy(xpath = "//a[contains(text(),'/')]")
    private WebElement tourLink;

    @FindBy(css = "[placeholder='Min Capacity']")
    private WebElement minCapacityTextBox;

    @FindBy(css = "[placeholder='Max Capacity']")
    private WebElement maxCapacityTextBox;

    @FindBy(css = "[placeholder='Available Capacity']")
    private WebElement availableCapacityTextBox;
    @FindBy(css = "[id*='tour-operations-card-save-button']")
    private WebElement tourSaveButton;
    @FindBy(css = "[id*='otalio-infinite-select-reason-field']")
    private WebElement moveReasonDropdown;
    @FindBy(css = "[id*='tour-cost-details-add-service-type-button']")
    private WebElement addServicePlusIcon;
    @FindBy(xpath = "//*[contains(text(),'Add Cost Service Type & Validity Periods')]")
    private WebElement addCostServiceTypeAndValidityPeriodsHeader;
    @FindBy(css = "[id*='description-en-input']")
    private WebElement descriptionInputTextBox;
    @FindBy(xpath = "//div[@class='mat-tab-label-content' and normalize-space()='Services & Costs Details']")
    private WebElement servicesAndCostsDetailsTab;
    @FindBy(xpath = "(//table//tbody//tr//td[1]//a)[1]")
    private WebElement tourLinkText;
    @FindBy(xpath = "//mat-select[@id='otalio-infinite-select-meetingLocationId-field']//*[text()]")
    private WebElement meetingLocationDropdownValue;
    @FindBy(css = "[id*='measurement-control-tourMaxWeight-value']")
    private WebElement tourMaxWeightTextBox;
    @FindBy(xpath = "//div[normalize-space()='Max / Total Weight']/following-sibling::span")
    private List<WebElement> maxTotalWeightValues;
    @FindBy(xpath = "(//a[contains(text(), '/')])[last()]")
    private WebElement lastTourLinks;
    @FindBy(css = "[data-placeholder*='Minimum Age']")
    private WebElement minimumAgeTextBox;
    @FindBy(xpath = "(//div[contains(@class,'shadow-xl') and contains(@class,'border-danger')]//div[2])[1]")
    private WebElement ageRestrictionErrorMessage;
    @FindBy(xpath = "(//*[local-name()='use' and starts-with(@href, '#close-')])[2]")
    private WebElement ageRestrictionErrorMessageClose;
    @FindBy(xpath = "//div[normalize-space()='Max / Total Weight']")
    private List<WebElement> maxTotalWeightLabels;
    @FindBy(xpath = "//div[normalize-space()='Min / Max / Available Capacity']")
    private List<WebElement> minMaxAvailableCapacityLabels;
    @FindBy(xpath = "//div[normalize-space()='Min / Max / Available Capacity']/following-sibling::span")
    private List<WebElement> minMaxAvailableCapacityValues;
    @FindBy(xpath = "//div[@class='text-type-info' and contains(normalize-space(),'Available / Booked')]")
    private List<WebElement> availableBookedLabels;
    @FindBy(xpath = "//div[@class='text-type-info' and contains(normalize-space(),'Available / Booked')]/following-sibling::div")
    private List<WebElement> availableBookedValues;
    @FindBy(xpath = "//*[contains(text(),'Discount Excursion Bookings')]")
    private WebElement discountExcursionBookingsHeader;
    @FindBy(css = "[data-placeholder*='Discount Value']")
    private WebElement discountValue;
    @FindBy(css = "[id*='booking-discount-comments-input']")
    private WebElement discountComments;
    @FindBy(xpath = "//div[normalize-space()='Other']")
    private WebElement otherHeader;
    @FindBy(xpath = "//otalio-chip-list//otalio-chip-link//span")
    private WebElement shoreExcursionsLink;
    @FindBy(xpath = "(//table//tr[contains(@class,'dx-data-row')])[2]")
    private WebElement shoreExcursionsGuestTourList;
    @FindBy(xpath = "//span[contains(text(),'by Guest')]")
    private WebElement byGuestMemeberCount;
    @FindBy(xpath = "//mat-tab-header//div[text()='Discounts']")
    private WebElement discountHeader;
    @FindBy(xpath = "//input[@placeholder='Discount Value']")
    private WebElement discountCommentsBox;
    @FindBy(css = "[id*='booking-details-discounts-save-button")
    private WebElement discountSaveButton;
    @FindBy(xpath = "//tr[@aria-rowindex='1']//td[@aria-colindex='6' and not(contains(@class,'dx-hidden-cell'))]")
    private WebElement grossAmount;
    @FindBy(xpath = "//tr[@aria-rowindex='1']//td[@aria-colindex='7' and not(contains(@class,'dx-hidden-cell'))]")
    private WebElement discountAmount;
    @FindBy(xpath = "//tr[@aria-rowindex='1']//td[@aria-colindex='9' and not(contains(@class,'dx-hidden-cell'))]")
    private WebElement debitAmount;
    @FindBy(css = "[aria-label='Items per page: 100']")
    private WebElement perPage100Button;
    @FindBy(css = "[id*='measurement-control-maxWeightPerGuest-value']")
    private WebElement getTourMaxWeightInputField;
    @FindBy(xpath = "(//span[normalize-space()='Book']/ancestor::otalio-bookable-item)[last()]//div[normalize-space()='Max / Total Weight']/following-sibling::span")
    private WebElement lastTourMaxWeight;
    @FindBy(xpath = "//mat-tab-header//div[text()='Language']")
    private WebElement languageHeader;
    @FindBy(css = "input[placeholder='Weight']")
    private WebElement weightInputText;
    @FindBy(css = "[id*='booking-details-language-save-button']")
    private WebElement weightSaveButton;
    @FindBy(xpath = "(//div[@id='toast-message-content' and contains(text(),'Tour Max Weight')])[2]")
    private WebElement errorMessageMaxTourWeight;
    @FindBy(xpath = "(//otalio-bookable-item//span[normalize-space()='Book'])[last()]")
    private WebElement bookingButton;

    @FindBy(css = "[id*='bookings-list-print-and-post']")
    private WebElement printAndPostButton;
    @FindBy(css = "[id*='confirmation-dialog-confirm-button']")
    private List<WebElement> confirmPopUpButton;

    @FindBy(xpath = "//otalio-time-picker-adapter[@id='otalio-time-picker-1']//*[@class='dx-texteditor-input']")
    private WebElement fromDateCalender;
    @FindBy(xpath = "//otalio-time-picker-adapter[@id='otalio-time-picker-2']//*[@class='dx-texteditor-input']")
    private WebElement toDateCalender;

    @FindBy(xpath = "(//tr[contains(@class,'dx-data-row')])[1]/td[@aria-colindex='5']")
    private WebElement txtMinCount;

    @FindBy(xpath = "(//tr[contains(@class,'dx-data-row')])[1]/td[@aria-colindex='6']")
    private WebElement txtMaxCount;

    @FindBy(xpath = "(//tr[contains(@class,'dx-data-row')])[1]/td[@aria-colindex='7']")
    private WebElement txtAvailableCount;
    @FindBy(xpath = "//div[text()='Booking Status:']")
    private WebElement bookingStatus;
    @FindBy(xpath = "//span[text()='No Data']")
    private WebElement noData;
    private By horizontalSliderScroller = By.xpath("//otalio-data-table//div[@class='dx-scrollable-scroll']");
    @FindBy(xpath = "//table//tr[1]//td[@aria-colindex='10' and not(@role='columnheader')]")
    private WebElement guestStatus;
    @FindBy(css = "[id='bookings-list-menu-button']")
    private WebElement bookingListMenuButton;

    @FindBy(css = "[id='bookings-list-print-action-button']")
    private WebElement bookingListPrintActionButton;

    @FindBy(css = "[aria-label='Select row']")
    private List<WebElement> checkBoxesSelect;
    @FindBy(xpath = "(//button/span[normalize-space()='Next'])[2]")
    private WebElement nextButton;

    @FindBy(css = "[id='bulk-update-confirm-button']")
    private WebElement bulkUpdateConfirmButton ;
    @FindBy(id = "excursion-sales-channel-editor-form-active-toggle-input")
    private WebElement activeToggleInput;
    @FindBy(xpath = "//input[@id='excursion-sales-channel-editor-form-active-toggle-input']/ancestor::mat-slide-toggle")
    private WebElement activeToggle;
    @FindBy(css = "mat-slide-toggle#excursion-sales-channel-editor-form-active-toggle .mat-slide-toggle-thumb-container")
    private WebElement activeToggleThumbContainer;
    @FindBy(css = "#excursion-sales-channel-editor-dialog-save-button")
    private WebElement saveButtonEditSaleChannel;
    @FindBy(css = "[id='tour-sales-channels-list-edit--button']")
    private List<WebElement> salesChannelEditButtons;
    private final By bookingsHandlingCard = By.id("link-box-bookings-handling");
    private final By bookingsManagerCard = By.id("link-box-bookings-manager");

    public String getPortName(){
        return seleniumActions.getTextMessage(getPortNameText);
    }

    public String getCountryName(){
        return seleniumActions.getTextMessage(getCountryNameText);
    }

    public WebElement getTourCode(){
        return toutCodeText;
    }

    public void enterPricesSalesFeesDetails(){
        seleniumActions.clickOnElement(ageGroupDropDown);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(optionsListDropDown.get(0));
        seleniumActions.clickOnElement(priceInputField);
        seleniumActions.typeValue(priceInputField,String.valueOf(commonFunctions.getRandomNumber(10,30)));
    }

    public void enterDeparturesDetails(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.scrollInToElement(webDriver.findElement(departure));
        seleniumActions.typeValue(typeDeparture,ExcursionManagementConstants.FOUR_AM);
        seleniumActions.clickOnElement(typeMeeting);
        seleniumActions.typeValue(typeMeeting,ExcursionManagementConstants.THREE_AM);
        seleniumActions.clickOnElement(typeMeetingLocation);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(optionsDropDown.get(1));
        seleniumActions.clickOnElement(typeLanguages);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector( searchField)),ExcursionManagementConstants.GREECE);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectDropDownText)));
    }

    public void clickDeparturePlusButton(){
        seleniumActions.clickOnElement(departurePlusButton);
    }

    public String getParticipantName(){
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        String name = jse.executeScript("return document.querySelectorAll(\"[aria-colindex='3'][class='dx-hidden-cell']\")[1].innerHTML").toString();
        return name;
    }

    public void clearBookingStatus(){
        seleniumActions.clickOnElement(bookingStatusList);
        for (int i=0; i< checkBoxes.size(); i++){
            seleniumActions.sleep(2000);
            seleniumActions.clickOnElement(checkBoxes.get(i));
        }
        if(checkBoxes.size()>0){
            checkBoxes.get(0).click();
        }
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
    }

    public void changeStatusAndFilter(String filterName, String status) {
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[contains(text(),'" + filterName + "')]/following-sibling::span/../../following-sibling::icon/*[name()='svg']")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[normalize-space()='" + status + "']/..//preceding-sibling::mat-pseudo-checkbox")));
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
    }

    public WebElement getTourMaxWeighPerGuestTextWebElement(){
        return tourMaxWeighPerGuestInputField;
    }

    public WebElement getTourMaxWeightTextWebElement(){
        return tourMaxWeightInputField;
    }

    public int getRandomNumberMaxWeight(){
        return commonFunctions.getRandomNumber(1,999);
    }

    public int getRandomNumberMaxWeightPerPerson(){
        return commonFunctions.getRandomNumber(1,10);
    }

    public void enterMaxWeight(String tourWeight){
        seleniumActions.clearValue(tourMaxWeightInputField);
        seleniumActions.typeValue(tourMaxWeightInputField,tourWeight);
    }

    public void enterMaxWeightPerPerson(String tourPerPersonWeight){
        seleniumActions.clearValue(tourMaxWeighPerGuestInputField);
        seleniumActions.typeValue(tourMaxWeighPerGuestInputField,tourPerPersonWeight);
    }

    public void clickTourGuestAddButton(){
        seleniumActions.clickOnElement(tourGuestsAddButton);
    }

    public By getTourNameLocator(String cleanedName) {
        return By.xpath("(//td[.//span[contains(normalize-space(.),'" + cleanedName + "')]])[1]");
    }
    public void clickOnTourName(String bookedTourName) {
        String cleanedName = cleanTourText(bookedTourName);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement element = webDriver.findElement(getTourNameLocator(cleanedName));
        try {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            seleniumWaits.untilElementIsClickable(element);
            seleniumActions.highLighterMethod(element);
            seleniumActions.jsClick(element);
        } catch (Exception ex) {
            try {
                seleniumActions.highLighterMethod(element);
                seleniumActions.jsClick(element);
            } catch (Exception e) {
                throw new RuntimeException("Failed to click on booked tour: " + bookedTourName, e);
            }
        }
    }

    public void searchParticipantOrCabin(String participant){
        seleniumActions.typeValue(participantSearchField,participant);
        String participantCheckbox = "(//div[contains(text(),'"+participant+"')]/ancestor::otalio-bookings-list/descendant::div[@aria-label='Select row']/div/span[@class='dx-checkbox-icon'])[1]";
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(participantCheckbox)));
    }

   /* public void clickOnBookedTour(String date, String month) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        String selectedDate = "//span[normalize-space()='" + month + "']//ancestor::otalio-datepicker/descendant::div[contains(@class,'rounded-circle text-body')][normalize-space()='" + date + "']";
        //String arrowRight = "//icon[contains(@icon,'arrow-right')][contains(@id,'transportation-management-datepicker-icon-right')]/*[name()='svg']/*[name()='use']";
        String arrowRight = "//icon[contains(@icon,'arrow-left')][contains(@id,'transportation-management-datepicker-icon-left')]/*[name()='svg']/*[name()='use']";
        for (int i = 0; i < 12; i++) {
            if (!webDriver.findElements(By.xpath(selectedDate)).isEmpty()) {
                break;
            }
            seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(arrowRight)));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectedDate)));
    }*/


    public void clickOnBookedTour(String date, String monthYear) {

        logger.info("Date: " + date);
        logger.info("MonthYear: " + monthYear);

        LocalDate targetDate = getTargetDate(date, monthYear);
        LocalDate today = LocalDate.now();

        String selectedDateXpath = "//span[normalize-space()='" + monthYear + "']//ancestor::otalio-datepicker" + "/descendant::div[contains(@class,'rounded-circle text-body')][normalize-space()='" + date + "']";

        String arrowLeft = "//icon[contains(@icon,'arrow-left')][contains(@id,'transportation-management-datepicker-icon-left')]";
        String arrowRight = "//icon[contains(@icon,'arrow-right')][contains(@id,'transportation-management-datepicker-icon-right')]";

        boolean isFuture = targetDate.isAfter(today);
        boolean isPast = targetDate.isBefore(today);

        for (int i = 0; i < 12; i++) {

            if (!webDriver.findElements(By.xpath(selectedDateXpath)).isEmpty()) {
                break;
            }
            if (isFuture) {
                seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(arrowRight)));
            } else if (isPast) {
                seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(arrowLeft)));
            } else {
                break; // today
            }

            seleniumActions.sleep(3000);
        }

        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath(selectedDateXpath)));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectedDateXpath)));
    }

    private LocalDate getTargetDate(String date, String monthYear) {

        int day = Integer.parseInt(date.trim());

        try {
            return LocalDate.parse("01 " + monthYear, DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH)).withDayOfMonth(day);

        } catch (Exception e) {

            return LocalDate.parse("01 " + monthYear, DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH)).withDayOfMonth(day);
        }
    }

//    public void clickOnBookedTour(String date,String month){
//        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//        for(int i=0; i<12; i++) {
//            if (webDriver.findElements(By.xpath("//span[normalize-space()='"+month+"']//ancestor::otalio-datepicker/descendant::div[contains(@class,'rounded-circle text-body')][normalize-space()='"+date+"']")).size() > 0) {
//                break;
//            } else{
//                seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("//icon[contains(@icon,'arrow-left')][contains(@id,'transportation-management-datepicker-icon-left')]/*[name()='svg']/*[name()='use']")));
//            }
//            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        }
//        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[normalize-space()='"+month+"']//ancestor::otalio-datepicker/descendant::div[contains(@class,'rounded-circle text-body')][normalize-space()='"+date+"']")));
//    }

    public List<String> getBookingDateAndMonth(String book) {
        String bookingDetails = "(//span[normalize-space()='"+book+"']/ancestor::otalio-bookable-item/descendant::div[contains(@class,'dashed-border')])[1]/div/div";
        List<WebElement> bookingDetailsText = webDriver.findElements(By.xpath(bookingDetails));
        seleniumWaits.untilElementIsVisible(bookingDetailsText.get(0));
        List<String> names = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            WebElement element = bookingDetailsText.get(i);
            names.add(seleniumActions.getTextMessage(element));
        }
        System.out.println(names);
        return names;
    }

    public void clickPostButton(){
        seleniumActions.clickOnElement(postButton);
    }

    public String getBookTittleNameText(){
        return seleniumActions.getTextMessage(getBookTittleName);
    }

    public void clickBookButton(String book){
        String bookButton = "(//span[normalize-space()='"+book+"'])[1]";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(bookButton)));
    }

    public void clickWaitListButton(){
        String waitListButton = "//button[normalize-space()='waitlist']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(waitListButton)));
    }

    public void clickFullWidthBlock(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(fullWidthBlock);
        seleniumActions.doubleClickOnElement(fullWidthBlock);
    }

    public  void clearTodayDate() {
        seleniumActions.clickOnElement(dateArrowDownButton);
        seleniumActions.clickOnElement(clearDateButton);
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public void guestSearchDisembarkationDateFilter(String date){
        //seleniumActions.clickOnElement(addMoreFilterButton);
        seleniumWaits.untilElementIsVisible(disembarkationDateArrowDownButton);
        seleniumActions.clickOnElement(disembarkationDateArrowDownButton);
        String radioButton = "//span[text()='"+date+"']/ancestor::mat-radio-button/descendant::input[@class='mat-radio-input']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(radioButton)));
    }

    public void guestSearchComplexFilterAddMoreButton(){
        seleniumActions.clickOnElement(complexFilterAddMoreButton);
    }

    public void viewBookingGuestsAddButton(){
        seleniumWaits.untilElementIsVisible(bookingsGuestsAddButton);
        seleniumActions.highLighterMethod(bookingsGuestsAddButton);
    }

    public String getAvailableInputFieldErrorMessage(){
        return seleniumActions.getTextMessage(availableInputFieldErrorMessage);
    }

    public String getMaxInputFieldErrorMessage(){
        return seleniumActions.getTextMessage(maxInputFieldErrorMessage);
    }

    public String getMinInputFieldErrorMessage(){
        return seleniumActions.getTextMessage(minInputFieldErrorMessage);
    }

    public WebElement getAvailableCapacityTextWebElement(){
        return availableCapacityInputField;
    }

    public WebElement getMaxCapacityTextWebElement(){
        return maxCapacityInputField;
    }

    public WebElement getMinCapacityTextWebElement(){
        return minCapacityInputField;
    }

    public void deleteLanguage(String languages){
        String languageXPath = String.format("//span[text()='%s']", languages);
        String deleteButtonXPath = languageXPath + "/ancestor::tr/descendant::div/button[@id='tour-language-list-delete-button']/span/icon";
        WebElement deleteButton = webDriver.findElement(By.xpath(deleteButtonXPath));
        seleniumActions.clickOnElement(deleteButton);
    }

    public int getRandomNumberForAboveCapacity(){
        return commonFunctions.getRandomNumber(9999,99999);
    }

    public int getRandomNumberForCapacity(){
        return commonFunctions.getRandomNumber(1,500);
    }

    public void enterMinCapacity(String min){
        seleniumActions.clearValue(minCapacityInputField);
        seleniumActions.typeValue(minCapacityInputField,min);
    }

    public void enterMaxCapacity(String max){
        seleniumActions.clearValue(maxCapacityInputField);
        seleniumActions.typeValue(maxCapacityInputField,max);
    }

    public void enterAvailableCapacity(String available){
        seleniumActions.clearValue(availableCapacityInputField);
        seleniumActions.typeValue(availableCapacityInputField,available);
    }

    public void deleteLanguageIfPresent(String language) {
        String languageXPath = String.format("//span[text()='%s']", language);
        String deleteButtonXPath = languageXPath + "/ancestor::tr/descendant::div/button[@id='tour-language-list-delete-button']/span/icon";
        try {
            WebElement existLanguage = webDriver.findElement(By.xpath(languageXPath));
            if (existLanguage.isDisplayed()) {
                System.out.println(language + " is displayed.");
                WebElement deleteButton = webDriver.findElement(By.xpath(deleteButtonXPath));
                seleniumActions.clickOnElement(deleteButton);
            }
        } catch (NoSuchElementException e) {
            System.out.println(language + " is not found. Clicking the add button instead.");
            seleniumActions.moveToElementAndClick(addButton);
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void clickClearDateButton(){
        commonPage.waitForDomReady();
        seleniumActions.clickOnElement(clearDateButton);
        seleniumActions.moveToElementAndClick(excursionHeaderTitle);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//tbody/tr/td/div/a)[2]")));
    }

    public void clickDateArrowDownIcon(){
        seleniumWaits.untilElementIsClickable(dateArrowDownIcon);
        seleniumActions.moveToElementAndClick(dateArrowDownIcon);
    }

    public boolean saveButtonDisabled(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.scrollInToElement(disabledButton);
        seleniumActions.highLighterMethod(disabledButton);
        String disabledAttribute = disabledButton.getAttribute("disabled");
        return disabledAttribute != null;
    }

    public List<String> getRowValuesForTour() {
        seleniumActions.sleep(3000);
        List<String> headers = Arrays.asList("Date", "Port", "Code", "Name");
        List<String> rowData = new ArrayList<>();
        for (String header : headers) {
            int index = seleniumActions.getIndexValueFromTheList(columnHeaderForTour, header) + 1;
            String cellData = seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//div[normalize-space()='" + header + "']/ancestor::div[contains(@class,'headers')]/following-sibling::div[contains(@class,'rowsview')]/descendant::tr/td[" + index + "]")));
            System.out.println("Index for " + header + ": " + index);
            System.out.println("Cell data for " + header + ": " + cellData);
            rowData.add(cellData);
        }
        return rowData;
    }

    public void selectTourForExcursion(){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        for(int i=0; i<10; i++) {
            if (webDriver.findElements(By.xpath("//otalio-datepicker/descendant::div[contains(@class,'position-relative today')]")).size() > 0) {
                break;
            } else{
                seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("//icon[contains(@icon,'arrow-left')][contains(@id,'otalio-datepicker-picker')]/*[name()='svg']/*[name()='use']")));
            }
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }

    }

    public String getBookingNrDetails(){
        return seleniumActions.getTextMessage(bookingDetails);
    }

    public String getDisembarkationDateDetails(){
        return seleniumActions.getTextMessage(disembarkationDetails);
    }

    public String getEmbarkationDateDetails(){
        return seleniumActions.getTextMessage(embarkationDetails);
    }

    public String getEmbarkationDateInformation(){
        return seleniumActions.getTextMessage(embarkationDateInformation);
    }

    public String getDisembarkationDateInformation(){
        return seleniumActions.getTextMessage(disembarkationDateInformation);
    }

    public String getBookingNrInformation(){
        return seleniumActions.getTextMessage(bookingNrInformation);
    }

    public List<String> getLanguageData() {
        return seleniumActions.getListOfElementsText(languagesData);
    }

    public void addPlusButton(){
        seleniumActions.clickOnElement(addButton);
    }

//    public void functionName(){
//        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
//        for(int i=0; i<10; i++) {
//            if (webDriver.findElements(By.xpath("(//tbody/tr/td/div/a)[2]")).size() > 0) {
//                break;
//            } else{
//                seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("//icon[contains(@icon,'arrow-right')]/*[name()='svg']/*[name()='use']")));
//            }
//            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        }
//        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//tbody/tr/td/div/a)[2]")));
//
//    }

    public void clickPriceForAge(){
        seleniumActions.jsClick(priceForAge);
    }

    public void clickOnAddNewGroup(){
        seleniumActions.clickOnElement(addNewAgeGroup);
    }

    public List<String> getRowValues(){
        List<String> data = Arrays.asList("Country","Port","Code","Name");
        List<String> rowData = new ArrayList<>();
        for (int i=0; i<data.size(); i++) {
            int index = seleniumActions.getIndexValueFromTheList(columnHeader, data.get(i));
            rowData.add(seleniumActions.getTextMessage(webDriver.findElement(
                    By.xpath("//div[normalize-space()='" + data.get(i) + "']/ancestor::div[contains(@class,'headers')]/following-sibling::div[contains(@class,'rowsview')]/descendant::tr/td[" + (index+1) + "]")
            )));
        }
        return rowData;
    }

    public void clickOnPriceForAgeGroupe(){
        seleniumActions.moveToElementAndClick(ageGroups);
    }

    public void clickOnSave(){
        seleniumActions.jsClick(saveButton);
    }

    public void selectPort(String port){
        seleniumActions.clickOnElement(portOperation);
        try {
            seleniumActions.typeValue(searchLanguages,port);
        } catch (StaleElementReferenceException sere) {
            seleniumActions.typeValue(searchLanguages, port);
        }
        String editArticle ="//mat-option[@role='option']/span[normalize-space()='"+port+"']";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(editArticle))));
    }

    public void selectCountry(String country){
        seleniumActions.clickOnElement(countryOperation);
        try {
            seleniumActions.typeValue(searchLanguages,country);
        } catch (StaleElementReferenceException sere) {
            seleniumActions.typeValue(searchLanguages, country);
        }
        String editArticle ="//mat-option[@role='option']/span[normalize-space()='"+country+"']";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(editArticle))));
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(editArticle))));
    }

    public void selectArea(String area) {
        seleniumActions.clickOnElement(areaOperation);
        seleniumActions.typeValue(searchLanguages, area);
        String editArticle = "//mat-option[@role='option']/span[normalize-space()='" + area + "']";
        seleniumActions.sleep(3000);
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(editArticle))));
    }
    
    public List<String> specialNeedsListList(){
        seleniumActions.areElementsDisplayed(specialNeedsList);
        List<String> data = new ArrayList<>();
        for(WebElement element : specialNeedsList){
            data.add(element.getText());
        }
        return data;
    }

    public void setSpecialNeedsDropDown(String need) {
        seleniumActions.sleep(3000);
        specialNeedsDropDown.click();
        CommonPage commonPage = new CommonPage(webDriver);
        WebElement departmentName = webDriver.findElement(By.xpath("//span[contains(text(),'" + need + "')]"));
        seleniumActions.clickOnElement(departmentName);
    }

    public void clickOnSpecialNeeds(){
        seleniumActions.clickOnElement(specialNeeds);
    }

    public void enterDescriptionEN(String descriptionEN){
        seleniumActions.clickOnElement(typeDescriptionEN);
        seleniumActions.typeValue(typeDescriptionEN,descriptionEN);
    }

    public void enterName(String nameEN){
        seleniumActions.clickOnElement(enterNameEN);
        seleniumActions.typeValue(enterNameEN,nameEN);
    }

    public void enterTourDescription(String tourDescription) {
        seleniumActions.clickOnElement(typeTourDescription);
        seleniumActions.typeValue(typeTourDescription, tourDescription);
    }

    public int getRandomNumber(){
        return commonFunctions.getRandomNumber(10000,99999);
    }

    public void enterTourCode(String templateCode) {
        seleniumActions.clickOnElement(typeTourCode);
        seleniumActions.typeValue(typeTourCode, templateCode);
    }

    public void enterExternalDescription(String description){
        seleniumActions.clickOnElement(externalDescription);
        seleniumActions.typeValue(externalDescription,description);
    }

    public void selectMeetingLocation(String meetingLocation) {
        seleniumActions.clickOnElement(typeMeetingLocation);
        String editMeetingLocation ="//span[normalize-space()='"+meetingLocation+"']";
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(editMeetingLocation))));
    }

    public void enterDepartureTime(String departureTime){
        seleniumActions.typeValue(typeDeparture,departureTime);
    }

    public void enterMeetingTime(String meetingTime){
        seleniumActions.clickOnElement(typeMeeting);
        seleniumActions.typeValue(typeMeeting,meetingTime);
    }

    public void saveButton(String button){
        String clickSave = "//h4[text()='"+button+"'or normalize-space()='" + button + "']/../../../div/descendant::span[normalize-space()='Save']";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(clickSave,button))));
    }

    public void clickPlusButton(String name){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        String clickOnPlusButton = "//a[@mattooltip='"+name+"']//icon[contains(@icon,'plus')]/*[name()='svg']/*[name()='use']";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(clickOnPlusButton,name))));
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(clickOnPlusButton,name))));
    }

    public void plusButton(String name){
        String clickOnPlusButton = "//h4[normalize-space()='"+name+"']/../../div/descendant::icon[contains(@icon,'plus')]/*[name()='svg']/*[name()='use']";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(clickOnPlusButton,name))));
    }

    public void enterPrice(String price) {
        seleniumActions.clickOnElement(priceInputField);
//        seleniumActions.clearValue(typePrice);
        seleniumActions.sleep(3000);
        seleniumActions.typeValue(priceInputField, price);
    }

    public void selectAgeGroup(int dropdownIndex, String ageGroup) {
        seleniumActions.clickOnElement(By.xpath("(//mat-select[@placeholder='Age Group'])[" + dropdownIndex + "]"));
        String ageGroupXpath = String.format("//span[normalize-space()='%s']", ageGroup);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(ageGroupXpath)));
    }

    public void selectPrice(int dropdownIndex, String price) {
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//input[@placeholder='Price'])[" + dropdownIndex + "]")));
        seleniumActions.clearValue(webDriver.findElement(By.xpath("(//input[@placeholder='Price'])[" + dropdownIndex + "]")));
        seleniumActions.typeValue(webDriver.findElement(By.xpath("(//input[@placeholder='Price'])[" + dropdownIndex + "]")), price);
    }

    public void selectAgeGroup(String ageGroup){
        seleniumActions.clickOnElement(typeAgeGroup);
        String editAgeGroup ="//span[normalize-space()='"+ageGroup+"']";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(editAgeGroup))));
    }

    public void editLanguages(String languages){
        seleniumWaits.untilElementIsVisible(typeLanguages);
        seleniumActions.clickOnElement(typeLanguages);
        String selectLanguages ="//span[normalize-space()='"+languages+"']";
        try {
            seleniumActions.typeValue(searchLanguages,languages);
        } catch (StaleElementReferenceException sere) {
            seleniumActions.typeValue(searchLanguages,languages);
        }
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(selectLanguages))));

    }

    public void enterBrochureID(String brochureID){
        seleniumActions.clearValue(typeBrochureID);
        seleniumActions.typeValue(typeBrochureID,brochureID);
    }

    public void enterExternalCode(String externalCode){
        seleniumActions.clearValue(typeExternalCode);
        seleniumActions.typeValue(typeExternalCode,externalCode);
    }

    public void editDescriptionEN(String descriptionEN){
        seleniumActions.clearValue(typeDescriptionEN);
        seleniumActions.typeValue(typeDescriptionEN,descriptionEN);
    }

    public void editName(String nameEN){
        seleniumActions.clearValue(enterNameEN);
        seleniumActions.typeValue(enterNameEN,nameEN);
    }

    public void selectArticle(String article){
        seleniumActions.clickOnElement(articleClass);
        seleniumActions.typeValue(searchInputField,article);
        String editArticle ="//mat-option[@role='option']/span[normalize-space()='"+article+"']";
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath(String.format(editArticle))));
    }

    public void editExternalDescription(String description){
        seleniumActions.clearValue(externalDescription);
        seleniumActions.typeValue(externalDescription,description);
    }

    public String getTourTemplateCode(){
        try {
            seleniumWaits.untilElementIsClickable(templateCode);
        } catch (StaleElementReferenceException ste) {
            seleniumWaits.untilElementIsClickable(templateCode);
        }
        return seleniumActions.getTextMessage(templateCode);
    }

    public void clickOnEdit(){
        seleniumActions.moveToElementAndClick(editButton);
    }

    public void clickOnPlusButton(){
        seleniumActions.sleep(3000);
        try {
            seleniumActions.clickOnElement(plusButton);
        } catch (StaleElementReferenceException stale) {
            seleniumActions.moveToElementAndClick(plusButton);
        }
    }

    public void  searchTemplateCode(String dropDown,String value){
        String arrow = "//button[contains(@id,'%s')]//icon[@icon='arrow-down']";
        webDriver.findElement(By.xpath(String.format(arrow,dropDown))).click();
        seleniumWaits.untilElementIsVisible(codeSearchBox);
        seleniumActions.typeValue(codeSearchBox,value);
        seleniumActions.sleep(3000);
    }

    public String getTemplateCode(){
        return getCode.getText().trim();
    }


    public String getGridTourCode(){
        return gridTourCode.getText().trim();
    }

    public String getGridTourDescription(){
        return gridTourDescription.getText().trim();
    }

    public void searchExcursionBookingsOverview(String searchString){
        seleniumActions.sendKeys(bookingsOverviewSearchBox,searchString);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public String getSearchedTourCode(){
        return searchedTourCode.getText().trim();
    }

    public String getSearchedTourName(){
        return searchedTourName.getText().trim();
    }
    public void userAbleToEnterDeparturesDetailsOnTourActivityNewLangauge(String languages){
        seleniumWaits.untilElementIsVisible(typeLanguages);
        seleniumActions.typeValue(typeLanguages,languages);
    }
    private String linkSelector(String linkText) {
        return String.format("//a[contains(normalize-space(.), '%s')]", linkText);
    }
    public void userAbleClickOnThe(String linkText) {
        String xpath = linkSelector(linkText);
        By locator = By.xpath(xpath);
        WebElement element = webDriver.findElement(locator);
        seleniumWaits.untilElementIsVisible(element);
        seleniumActions.scrollInToElement(element);
        commonPage.waitForDomReady();
        seleniumActions.jsClick(element);
        try {
            if(seleniumActions.isElementDisplayed(element)){
                seleniumActions.jsClick(element);
            }
        } catch (Exception e) {
        }
    }
    public By getDropdownOption(String optionText) {
        String xpath = String.format("//span[@class='mat-option-text' and contains(text(),'%s')]", optionText);
        return By.xpath(xpath);
    }
    public void tourGeneralDetails(){
        String randomCode = generateRandomCode();
        seleniumActions.typeValue(typeTourCode, ExcursionManagementConstants.CODE + randomCode);
        seleniumWaits.untilElementIsVisible(typeTourDescription);
        seleniumActions.typeValue(typeTourDescription, ExcursionManagementConstants.DESCRIPTION + randomCode);

        seleniumWaits.untilElementIsVisible(externalDescription);
        seleniumActions.typeValue(externalDescription, ExcursionManagementConstants.EXTERNAL + randomCode);

        seleniumActions.selectDropdownOption(
                ExcursionManagementConstants.ARTICAL_CLASS, ExcursionManagementConstants.DROPDOWN_VALUE_ONE);

        seleniumWaits.untilElementIsVisible(enterNameEN);
        seleniumActions.typeValue(enterNameEN, ExcursionManagementConstants.NAMEEN + randomCode);

        seleniumWaits.untilElementIsVisible(typeDescriptionEN);
        seleniumActions.typeValue(typeDescriptionEN, ExcursionManagementConstants.DESCRIPTIONEN + randomCode);


        seleniumActions.scrollInToElement(webDriver.findElement(operationsHeader));
        seleniumActions.sleep(GlobalConstants.SHORT_WAIT);

        seleniumActions.selectDropdownOption(ExcursionManagementConstants.OPERATION, ExcursionManagementConstants.DROPDOWN_VALUE_ONE);

        seleniumActions.selectDropdownOption(ExcursionManagementConstants.AREA, ExcursionManagementConstants.DROPDOWN_VALUE_ONE);

        try {
            seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
            seleniumActions.clickOnElement(webDriver.findElement(countryDropDown));
            WebElement searchBox = webDriver.findElement(searchBar);
            seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
            searchBox.sendKeys(ExcursionManagementConstants.ITALY);
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.MINI_WAIT));
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(getDropdownOption(ExcursionManagementConstants.ITALY)));
            option.click();
        }catch (Exception ex){
            WebElement searchBox = webDriver.findElement(searchBar);
            seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
            searchBox.sendKeys(ExcursionManagementConstants.ITALY);
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.MINI_WAIT));
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(getDropdownOption(ExcursionManagementConstants.ITALY)));
            option.click();
        }


        seleniumActions.selectDropdownOption(ExcursionManagementConstants.PORT, ExcursionManagementConstants.DROPDOWN_VALUE_ONE);

    }
    public String generateRandomCode() {
        return String.valueOf(commonFunctions.getRandomNumber(GlobalConstants.INITIAL_NUMBER, GlobalConstants.END_NUMBER));
    }
    public boolean isClickEditButton() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        try {
            WebElement element = webDriver.findElement(editButtonIcon);
            seleniumActions.highLighterMethod(element);
            element.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void scrollToTransportationManagement() {
        WebElement element = webDriver.findElement(transportationManagement);
        seleniumActions.scrollInToElement(element);
        seleniumActions.highLighterMethod(element);
    }
    public void userAddANewLanguageInTransportationManagement(){
        seleniumActions.clickOnElement(addLanguagePlusIcon);
        seleniumActions.selectDropdownOption(ExcursionManagementConstants.LANGUAGE,ExcursionManagementConstants.FIRST_LANGUAGE);
        seleniumActions.clickOnElement(addLanguageSaveButton);
    }

    public void userClicksOnEditPencilIcon(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(webDriver.findElement(editIcon));
    }
    public void userEditsTheSelectedLanguage(){
        seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
        int languageNumber = commonPage.getRandomNumber(1, 10);
        String languageValue = String.valueOf(languageNumber);
        seleniumActions.selectDropdownOption(ExcursionManagementConstants._LANGUAGE,languageValue);

       /* seleniumActions.clickOnElement(languageDropdown);
        WebElement searchBox = webDriver.findElement(searchBar);
        searchBox.sendKeys(ExcursionManagementConstants.EDIT_LANGUAGE);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.MINI_WAIT));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(getDropdownOption(ExcursionManagementConstants.EDIT_LANGUAGE)));
        option.click();
        seleniumActions.clickOnElement(addLanguageSaveButton);*/
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public boolean isEditIconPresent() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return !webDriver.findElements(editIcon).isEmpty();
    }
    public String getLanguage() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(webDriver.findElement(languageCell));
        return webDriver.findElement(languageCell).getText().trim();
    }
    public int getDeleteButtonCount() {
        return webDriver.findElements(deleteButtons).size();
    }
    public void userClickOnDeleteIcon(){
        seleniumActions.clickOnElement(webDriver.findElement(deleteIcon));
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public void userSearchTheGuestInBooking(String guest){
        seleniumWaits.untilElementIsVisible(sectionSearchBox);
        seleniumActions.typeValue(sectionSearchBox, guest);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public String getGuestTypeText(String type) {
        String xpath = String.format(
                "(//div[normalize-space()='%s']/following::div[contains(@class,'text-truncate')][1])[1]", type);
        WebElement element = webDriver.findElement(By.xpath(xpath));
        return element.getText().trim();
    }
    public String getTotalValue() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return webDriver.findElement(totalValueLocator).getText().trim();
    }
    public String getGrossAmountFirstRow() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.scrollInToElement(webDriver.findElement(grossAmountFirstRowLocator));
        seleniumActions.highLighterMethod(webDriver.findElement(grossAmountFirstRowLocator));
        return webDriver.findElement(grossAmountFirstRowLocator).getText().trim();
    }
    public void clearDateIfBookableItemsVisible() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<WebElement> bookableItems = webDriver.findElements(bookableItemsLocator);
        if (bookableItems.isEmpty()) {
            clearTodayDate();
        }
    }

    public void userSearchForTheGuest() {
        seleniumWaits.untilElementIsVisible(searchInputBox);
        //seleniumActions.typeValue(searchInputBox, ExcursionManagementConstants.GUEST_PREFIX_NAME);
        seleniumActions.typeValue(searchInputBox, PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<WebElement> crewList = webDriver.findElements(crewProfilePic);
        if (crewList.isEmpty()) {
            throw new RuntimeException("No crew members found in search results");
        }
        int randomIndex = new Random().nextInt(crewList.size());
        WebElement randomCrew = crewList.get(randomIndex);
        seleniumActions.scrollInToElement(randomCrew);
        seleniumActions.clickOnElement(randomCrew);
        seleniumActions.scrollInToElement(searchInputBox);
       logger.info("Clicked crew index: " + randomIndex);
    }
    public WebElement getTotalValueLocator(String totalValue) {
        return webDriver.findElement(By.xpath("(//td[not(contains(@class,'dx-hidden-cell')) and .//span[normalize-space()='" + totalValue + "']])[1]"));
    }

    public boolean isTotalValueDisplayed(String totalValue) {
        WebElement element = getTotalValueLocator(totalValue);
        try {
            seleniumWaits.untilElementIsVisible(element);
            seleniumActions.highLighterMethod(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void userSelectsTwoGuestMembers(String multiGuest){
        seleniumWaits.untilElementIsVisible(searchInputBox);
        searchInputBox.clear();
        seleniumActions.sendKeys(searchInputBox, multiGuest);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<WebElement> crew = webDriver.findElements(crewProfilePic);
        seleniumWaits.untilElementIsVisible(crew.get(0));
        seleniumActions.clickOnElement(crew.get(0));
    }
    public List<String> getGuestNames() {

        List<String> names = new ArrayList<>();

        if (guestNames.size() < 2) {
            throw new RuntimeException("Less than 2 guest names found");
        }
        for (WebElement guest : guestNames) {
            if (guest.isDisplayed()) {
                names.add(guest.getText().trim());
            }
            if (names.size() == 2) {
                break;
            }
        }
        return names;
    }
    public void searchUser(String userName){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.sendKeys(crewSearchBox, userName);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public void userAddTheDataInSpecialFileds(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<WebElement> elements = webDriver.findElements(addBtn);
        if (elements.size() != 1 && elements.size() > 0) {
            seleniumActions.selectDropdownOption(ExcursionManagementConstants.SPECIAL_NEEDS,ExcursionManagementConstants.DROPDOWN_VALUE_TWO);

            WebElement commentField = webDriver.findElement(specialNeedsComments);
            commentField.clear();
            commentField.sendKeys(""+commonFunctions.getRandomCharacter());
        }else{
            seleniumActions.clickOnElement(addSpecialNeedsBtn);
            try {
                seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
                seleniumActions.clickOnElement(addSpecialNeedsDropDown);
                WebElement searchBox = webDriver.findElement(searchBar);
                searchBox.clear();
                searchBox.sendKeys(ExcursionManagementConstants.DROPDOWN_TEXT);
                WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
                WebElement option = wait.until(ExpectedConditions.elementToBeClickable(getDropdownOption(ExcursionManagementConstants.DROPDOWN_TEXT)));
                option.click();

            } catch (Exception ex) {
                Assert.fail("Unable to select activity type: " + ExcursionManagementConstants.DROPDOWN_TEXT);
            }
        }
    }
    public void searchSpecialNeedsGuest(String firstName, String fullName) {

        seleniumWaits.untilElementIsVisible(searchInputBox);
        seleniumActions.sendKeys(searchInputBox, firstName);
        By guestImage = getGuestImageLocator(fullName);
        seleniumWaits.untilElementIsVisible(webDriver.findElement(guestImage));
        seleniumActions.clickOnElement(webDriver.findElement(guestImage));
        seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
    }
    private By getGuestImageLocator(String fullName) {
        return By.xpath("//*[normalize-space()='" + fullName + "']/parent::div/preceding-sibling::otalio-passenger-picture");
    }
    public boolean isSpecialNeedsIconDisplayed() {
        try {
            WebElement element = webDriver.findElement(specialNeedsIcon);
            seleniumWaits.untilElementIsVisible(element, GlobalConstants.SHORT_WAIT);
            seleniumActions.highLighterMethod(element);
            seleniumActions.moveToElement(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void drawSignatureforConfirmation(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        boolean isBookingConfirmationDisplayed = webDriver.findElements(bookingConfirmationHeader).size() > 0;
        if (isBookingConfirmationDisplayed) {
            seleniumWaits.untilElementIsClickable(signaturePad);
            seleniumActions.performSignature(signaturePad);
            seleniumWaits.untilElementIsVisible(signaturePad);
            seleniumActions.clickOnElement(signaturePadConfirmationButton);
            seleniumActions.pressEscapeKey();
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        }
    }
    public By getPriceLocatorByAgeGroup(String ageGroup) {
        return By.xpath("//otalio-bookable-item//div[normalize-space()='" + ageGroup + "']" + "/following::div[contains(@class,'text-truncate')][1]//div");
    }

    public List<WebElement> getPriceElementsByAgeGroup(String ageGroup) {
        return webDriver.findElements(getPriceLocatorByAgeGroup(ageGroup));
    }
    public WebElement getFirstPriceElementByAgeGroup(String ageGroup) {
        List<WebElement> elements = getPriceElementsByAgeGroup(ageGroup);

        if (elements.isEmpty()) {
            throw new RuntimeException("No price element found for: " + ageGroup);
        }
        seleniumActions.highLighterMethod(elements.get(0));
        return elements.get(0);
    }

    public Map<String, List<Double>> getPricingByAgeGroup(String ageGroup) {

        List<WebElement> priceElements = getPriceElementsByAgeGroup(ageGroup);

        if (priceElements.isEmpty()) {
            throw new RuntimeException("No pricing found for age group: " + ageGroup);
        }

        List<Double> prices = new ArrayList<>();

        for (WebElement element : priceElements) {

            String priceText = element.getText().trim();
            logger.info("Captured Price for " + ageGroup + " : " + priceText);

            if (!priceText.isEmpty()) {
                String normalizedPrice = priceText.replaceAll("[^0-9.]", "");
                prices.add(Double.parseDouble(normalizedPrice));
            }
        }

        Map<String, List<Double>> pricingMap = new HashMap<>();
        pricingMap.put(ageGroup, prices);

        return pricingMap;
    }
    public By getGuestTypeLocator(String guestType) {
        return By.xpath("//otalio-guest-search-selected-area//div[normalize-space()='" + guestType + "']");
    }
    public boolean isGuestTypeDisplayed(String guestType) {

        List<WebElement> elements = webDriver.findElements(getGuestTypeLocator(guestType));

        if (elements.isEmpty()) {
            return false;
        }
        seleniumActions.highLighterMethod(elements.get(0));
        return elements.get(0).isDisplayed();
    }
    public WebElement getTableValueElement(String tableValue) {
        By locator = By.xpath("(//td[not(contains(@class,'dx-hidden-cell'))]" + "[.//*[normalize-space()='" + tableValue + "']])[1]");
        WebElement element = webDriver.findElement(locator);
        seleniumActions.highLighterMethod(element);
        return element;
    }

    public void userVerifiesTheExcursionsBookingOverviewAndInputsThePortName(String portName){
        WebElement element = webDriver.findElement(excursionBookingsOverview);
        seleniumWaits.untilElementIsVisible(element);
        seleniumActions.highLighterMethod(element);
        seleniumActions.sendKeys(searchBookingsOverview, portName);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public String getFirstAvailableDate() {
        if (bookingsOverviewDateLinks.isEmpty()) {
            List<WebElement> bookableItems = webDriver.findElements(bookableItemsLocator);
            if (bookableItems.isEmpty()) {
                clearTodayDate();
            }
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        }
        return seleniumActions.getTextMessage(bookingsOverviewDateLinks.get(0));
    }

    public void userChoosesADateFromTheListOfAvailableTours(){
        seleniumActions.clickOnElement(bookingsOverviewDateLinks.get(0));
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public String getDetailsPageDate() {
        WebElement dateElement = webDriver.findElement(By.xpath("//*[normalize-space()='Date & Time']/following-sibling::*[1]"));
        return seleniumActions.getTextMessage(dateElement);
    }

    public boolean isLabelDisplayed(String label) {

        try {
            WebElement element = webDriver.findElement(By.xpath("//*[normalize-space()='" + label + "']/ancestor::div[1]"));

            seleniumWaits.untilElementIsVisible(element);
            seleniumActions.highLighterMethod(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getBookingCode() {
        seleniumWaits.untilElementIsVisible(bookingCodeElement);
        String fullText = seleniumActions.getTextMessage(bookingCodeElement);
        String bookingCode;
        if (fullText.contains("/")) {
            bookingCode = fullText.split("/")[0].trim();
        } else {
            bookingCode = fullText;
        }
        return bookingCode;
    }

    public String cleanTourText(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {

            if (words[i].contains("'")) {
                if (i == 0) {
                    continue;
                } else {
                    break;
                }
            }

            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(words[i]);
        }
        return result.toString().trim();
    }

    public List<String> getGuestNames(int count) {
        List<String> names = new ArrayList<>();
        for (WebElement guest : guestNames) {
            if (guest.isDisplayed()) {
                names.add(guest.getText().trim());
            }
            if (names.size() == count) {
                break;
            }
        }

        if (names.size() < count) {
            throw new RuntimeException("Less than " + count + " guest names found");
        }

        return names;
    }


    public void userClearsTheExistDateFromTheCalendarFieldAndSelectsTheDateRange(String startDate, String endDate){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<WebElement> bookableItems = webDriver.findElements(bookableItemsLocator);
        //if (bookableItems.isEmpty()) {
            seleniumActions.clickOnElement(dateDropDown);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.clickOnElement(clearDateButton);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.clickOnElement(dateRangeRadioButton);
            seleniumActions.clickOnElement(clearStartDate);
            seleniumActions.typeValue(webDriver.findElement(By.cssSelector(startDateInput)), startDate);
            seleniumActions.clickOnElement(clearEndDate);
            seleniumActions.typeValue(webDriver.findElement(By.xpath(endDateInput)), endDate);
       // }
    }
    public boolean drawSignatureIfVisible() {
        try {
            boolean isDisplayed = webDriver.findElements(bookingConfirmationHeader).size() > 0;
            if (isDisplayed) {
                seleniumWaits.untilElementIsClickable(signaturePad);
                seleniumActions.performSignature(signaturePad);
                seleniumWaits.untilElementIsVisible(signaturePad);
                seleniumActions.clickOnElement(signaturePadConfirmationButton);
                seleniumActions.pressEscapeKey();
                seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    public WebElement getYearLocator(String year) {
        return webDriver.findElement(By.xpath("//button[@id='otalio-datepicker-year-view-select-" + year + "']"));
    }

    public WebElement getMonthLocator(String monthNumber) {
        return webDriver.findElement(By.xpath("//button[@id='otalio-datepicker-year-view-select-" + monthNumber + "']"));
    }

    public WebElement getDateLocator(String monthNumber, String date) {
        return webDriver.findElement(By.xpath("//div[@id='otalio-datepicker-month-view-select-" + monthNumber + "-" + date + "']"));
    }

    public void selectDateFromCalendar(String date, String monthYear) {
        logger.info("Selecting Date: " + date + " MonthYear: " + monthYear);
        String month = monthYear.split(" ")[0];
        String year = monthYear.split(" ")[1];

        String monthNumber = String.valueOf(getMonthNumber(month));

        seleniumActions.clickOnElement(monthCalender);
        seleniumActions.clickOnElement(yearCalender);

        seleniumWaits.untilElementIsVisible(getYearLocator(year));
        seleniumActions.clickOnElement(getYearLocator(year));

        seleniumWaits.untilElementIsVisible(getMonthLocator(monthNumber));
        seleniumActions.clickOnElement(getMonthLocator(monthNumber));

        seleniumWaits.untilElementIsVisible(getDateLocator(monthNumber, date));
        seleniumActions.clickOnElement(getDateLocator(monthNumber, date));
    }
    private int getMonthNumber(String month) {

        switch (month.toLowerCase()) {
            case "jan": return 1;
            case "feb": return 2;
            case "mar": return 3;
            case "apr": return 4;
            case "may": return 5;
            case "jun": return 6;
            case "jul": return 7;
            case "aug": return 8;
            case "sep": return 9;
            case "oct": return 10;
            case "nov": return 11;
            case "dec": return 12;
            default:
                throw new RuntimeException("Invalid month: " + month);
        }
    }

    public void userViewPlusButtonOnTourActivities(String name){
        String clickOnPlusButton = "//a[@mattooltip='"+name+"']";
        try {
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.highLighterMethod((webDriver.findElement(By.xpath(String.format(clickOnPlusButton,name)))));
        }catch (Exception ex){
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.highLighterMethod((webDriver.findElement(By.xpath(String.format(clickOnPlusButton,name)))));
        }
    }
    public void userViewSaveButtonOnTourActivityNew(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(saveButton);
    }
    public void userViewLanguageSaveButton(){
        seleniumActions.highLighterMethod(addLanguageSaveButton);
    }
    public void userClickLanguageSaveButton(){
        seleniumActions.clickOnElement(addLanguageSaveButton);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public List<WebElement> guestBookingDetails() {
        for (WebElement element : bookingOverData) {
            seleniumActions.highLighterMethod(element);
        }

        return bookingOverData;
    }
    public void clickOnpostButton(){
        seleniumActions.highLighterMethod(postButton);
    }
    public void userClickOnBookingsGuestsAddButton(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.jsClick(bookingsGuestsAddButton);
    }
    public By getDynamicInputField(String fieldName) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return By.cssSelector("[id*='" + fieldName + "-input']");
    }

   public void userEnterUnmandatoryFieldsInNewTourActivity() {
       enterRandomTextInField(ExcursionManagementConstants.GENERAL_INFORMATION);
       enterRandomTextInField(ExcursionManagementConstants.INTERNAL_COMMENT);
       enterRandomTextInField(ExcursionManagementConstants.RECOMMENDATION_RANKING);
   }

    private void enterRandomTextInField(String fieldName) {
        WebElement inputField = webDriver.findElement(getDynamicInputField(fieldName));
        String randomText = seleniumActions.getRandomString(SafetyManagementConstatnts.MIN_CHARACTER);
        seleniumActions.sendKeys(inputField, randomText);
        seleniumActions.highLighterMethod(inputField);
    }
    public By getPassengerTypePrice(String passengerType) {
        return By.xpath("((//span[normalize-space()='Book']/ancestor::otalio-bookable-item)[1]//div[normalize-space()='" + passengerType + "']/following::div[contains(@class,'text-truncate')][1]//div)");
    }
    public void userViewMinorCost(String guest){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(webDriver.findElement(getPassengerTypePrice(guest)));
    }
    public void userViewDeleteButtonIcon(){
        seleniumActions.highLighterMethod(webDriver.findElement(deleteIcon));
    }


    public boolean isAddNewTourActivityPlusButtonDisplayed() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return btnAddNewTourActivityPlusButton.isDisplayed();
    }

    public void viewAddNewTourActivityPlusButton() {
        seleniumActions.highLighterMethod(btnAddNewTourActivityPlusButton);
    }

    public void clickAddNewTourActivityPlusButton() {
        seleniumWaits.untilElementIsClickable(btnAddNewTourActivityPlusButton);
        seleniumActions.scrollInToElement(btnAddNewTourActivityPlusButton);
        seleniumActions.moveToElementAndClick(btnAddNewTourActivityPlusButton);
    }
    public String getTabName(String tabName){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//div[@class='mat-tab-label-content' and normalize-space()='" + tabName.trim() + "']")));
    }
    public void enterArticleGeneralInformationDetails(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.scrollInToElement(webDriver.findElement(articleGeneralInformation));
        String randomCode = generateRandomCode();
        seleniumActions.typeValue(typeTourCode, ExcursionManagementConstants.CODE + randomCode);
        seleniumWaits.untilElementIsVisible(typeTourDescription);
        seleniumActions.typeValue(typeTourDescription, ExcursionManagementConstants.DESCRIPTION + randomCode);

        seleniumWaits.untilElementIsVisible(externalDescription);
        seleniumActions.typeValue(externalDescription, ExcursionManagementConstants.EXTERNAL + randomCode);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(
                ExcursionManagementConstants.ARTICAL_CLASS, ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumWaits.untilElementIsVisible(enterNameEN);
        seleniumActions.typeValue(enterNameEN, ExcursionManagementConstants.NAMEEN + randomCode);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumWaits.untilElementIsVisible(typeDescriptionEN);
        seleniumActions.typeValue(typeDescriptionEN, ExcursionManagementConstants.DESCRIPTIONEN + randomCode);
    }

    public void enterOperationsDetails(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.scrollInToElement(webDriver.findElement(operationsHeader));
        seleniumActions.selectDropdownOption(ExcursionManagementConstants.OPERATION, ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(ExcursionManagementConstants.AREA, ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        try {
            seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
            seleniumActions.clickOnElement(webDriver.findElement(countryDropDown));
            WebElement searchBox = webDriver.findElement(searchBar);
            seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
            searchBox.sendKeys(ExcursionManagementConstants.ITALY);
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.MINI_WAIT));
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(getDropdownOption(ExcursionManagementConstants.ITALY)));
            option.click();
        }catch (Exception ex){
            WebElement searchBox = webDriver.findElement(searchBar);
            seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
            searchBox.sendKeys(ExcursionManagementConstants.ITALY);
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.MINI_WAIT));
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(getDropdownOption(ExcursionManagementConstants.ITALY)));
            option.click();
        }
        seleniumActions.selectDropdownOption(ExcursionManagementConstants.PORT, ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
    }
    public void clickTheTourFromList(String startDate, String endDate) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        if (tourDetails.isEmpty() || !tourDetails.get(0).isDisplayed()) {
            seleniumActions.clickOnElement(dateDropDown);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.clickOnElement(clearDateButton);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.clickOnElement(dateRangeRadioButton);
            seleniumActions.clickOnElement(clearStartDate);
            seleniumActions.typeValue(webDriver.findElement(By.cssSelector(startDateInput)), startDate);
            seleniumActions.clickOnElement(clearEndDate);
            seleniumActions.typeValue(webDriver.findElement(By.xpath(endDateInput)), endDate);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.pressEscapeKey();
        }
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement element = tourDetails.get(0);
        seleniumWaits.untilElementIsClickable(element);
        seleniumActions.clickOnElement(element);
    }
    public void userScrollsDownToTheSalesSectionOfThePage(){
        seleniumActions.scrollInToElement(salesHeader);
    }

    public WebElement getSalesChannelName(String channelName) {
        By salesChannelLocator = By.xpath("//td[normalize-space()='" + channelName + "']");
        List<WebElement> elements = webDriver.findElements(salesChannelLocator);
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(0);
    }

    public WebElement getSalesChannelCheckbox(String channelName) {
        return webDriver.findElement(By.xpath("//td[normalize-space()='" + channelName + "']" + "/following-sibling::td[1]"));
    }

    public WebElement getBookingActionMenuLocator(String menu) {
        return webDriver.findElement(By.xpath("//span[text()='" + menu + "' or normalize-space()='" + menu + "']"));
    }

    public void selectBookingActionMenu(String menu){
        seleniumActions.clickOnElement(bookingActionMenuButton);
        seleniumActions.highLighterMethod(getBookingActionMenuLocator(menu));
    }
    public WebElement getBookingStatusElement(String status) {
        return webDriver.findElement(By.xpath("//td[normalize-space()='" + status + "']"));
    }
    public void _enterDeparturesDetails(String header) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        commonPage.clickOnLabel(header);
        seleniumActions.scrollInToElement(webDriver.findElement(departure));
        seleniumActions.typeValue(typeDeparture, ExcursionManagementConstants.FOUR_AM);
        seleniumActions.clickOnElement(typeMeeting);
        seleniumActions.typeValue(typeMeeting, ExcursionManagementConstants.THREE_AM);
        seleniumActions.clickOnElement(typeMeetingLocation);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(optionsDropDown.get(1));
        seleniumActions.clickOnElement(typeLanguages);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(searchField)), ExcursionManagementConstants.GREECE);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectDropDownText)));
    }

    public boolean isEditButtonPresent() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(editPencileIcon.get(0));
        return !editPencileIcon.isEmpty();
    }
    public void clickFirstEditButton(){
        editPencileIcon.get(0).click();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

   public WebElement getActualAgentCountField(String type) {
       seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
       String xpath = "//*[normalize-space()='" + type + "']/following::input[@placeholder='Actual Agent Counts'][1]";
       logger.info("XPath Used : " + xpath);
       return webDriver.findElement(By.xpath(xpath));
   }

    public Map<String, String> enterActualAgentCounts() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        String[] passengerTypes = {ExcursionManagementConstants.ADULT, ExcursionManagementConstants.MINOR, ExcursionManagementConstants.INFANT};

        Map<String, String> counts = new HashMap<>();
        Random random = new Random();

        for (String type : passengerTypes) {
            int randomValue;
            if (type.equalsIgnoreCase(ExcursionManagementConstants.INFANT)) {
                randomValue = random.nextInt(20) + 1;
            } else if (type.equalsIgnoreCase(ExcursionManagementConstants.MINOR)) {
                randomValue = random.nextInt(40) + 10;
            } else {
                randomValue = random.nextInt(83) + 30;
            }

            WebElement element = getActualAgentCountField(type);
            element.clear();
            element.sendKeys(String.valueOf(randomValue));
            seleniumActions.highLighterMethod(element);
            counts.put(type.toLowerCase() + "ActualAgentCount", String.valueOf(randomValue));
            logger.info(type + " = " + randomValue);
        }

        return counts;
    }

    public void saveButtonDisplay(){
        seleniumActions.highLighterMethod(saveButton);
    }

    public String getActualAgentCount(String passengerType) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement element = webDriver.findElement(By.xpath("//otalio-data-table[@identifier='departureTimes']//tr[td[normalize-space()='" + passengerType + "']]/td[3]"));
        seleniumActions.highLighterMethod(element);
        return element.getText().trim();
    }

    public Map<String, String> getActualAgentCountsFromTable() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(dropdownIcon.get(0));
        dropdownIcon.get(0).click();
        Map<String, String> actualCounts = new HashMap<>();
        actualCounts.put(ExcursionManagementConstants.INFANTACTUALAGENTCOUNT, getActualAgentCount(ExcursionManagementConstants.INFANT.toUpperCase()));
        actualCounts.put(ExcursionManagementConstants.MINORACTUALAGENTCOUNT, getActualAgentCount(ExcursionManagementConstants.MINOR.toUpperCase()));
        actualCounts.put(ExcursionManagementConstants.ADULTACTUALAGENTCOUNT, getActualAgentCount(ExcursionManagementConstants.ADULT.toUpperCase()));
        return actualCounts;
    }
    public List<String> getTourDetails() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumWaits.untilElementIsVisible(bookTourDetails.get(0));
        List<String> tourDetails = new ArrayList<>();
        for (WebElement element : bookTourDetails) {
            seleniumActions.highLighterMethod(element);
            tourDetails.add(seleniumActions.getTextMessage(element).trim());
        }
        return tourDetails;
    }

    public boolean areTourDetailsDisplayed() {
        seleniumActions.highLighterMethod(portCountryCode);
        seleniumActions.highLighterMethod(passengerDetails);
        return portCountryCode.isDisplayed() && passengerDetails.isDisplayed();
    }

    public String searchRandomExcursionCode() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        if (tourDetails.isEmpty() || !tourDetails.get(0).isDisplayed()) {
            seleniumActions.clickOnElement(dateDropDown);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.clickOnElement(clearDateButton);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.pressEscapeKey();
        }

        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<String> excursionCodes = new ArrayList<>();

        for (WebElement codeElement : excursionCodeList) {
            excursionCodes.add(codeElement.getText().trim());
        }
        String randomCode = excursionCodes.get(new Random().nextInt(excursionCodes.size()));
        logger.info("Selected Random Code: " + randomCode);

        seleniumActions.highLighterMethod(searchCabinInputBox);
        seleniumActions.sendKeys(searchCabinInputBox, randomCode);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return randomCode;
    }

    public String getFirstDisplayedExcursionCode() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumWaits.untilElementIsVisible(excursionCodeList.get(0));
        seleniumActions.highLighterMethod(excursionCodeList.get(0));

        return excursionCodeList.get(0).getText().trim();
    }
    public void selectFilter(String filterName) {
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//*[normalize-space()='" + filterName + "']/following::span[normalize-space()='All'][1]/following::*[name()='svg'][1]")));
    }

    public void selectStatus(String status) {
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(
                "//div[normalize-space()='" + status + "']/..//preceding-sibling::mat-pseudo-checkbox")));
    }

    public void userSelectsTheFilterChoosesAndAddsAFutureDate(String filterName, String status) {
        seleniumActions.pressEscapeKey();
        selectFilter(filterName);
        selectStatus(status);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.pressEscapeKey();
    }
    public void userClicksTheThreeDotButtonOnTheBookingsListPage(String menu){
        seleniumActions.highLighterMethod(actionButton);
        seleniumActions.clickOnElement(actionButton);
        seleniumActions.highLighterMethod(getBookingActionMenuLocator(menu));
    }
    public void userSelectsThePostExcursionBookingTime(){
        seleniumActions.selectDropdownOption(ExcursionManagementConstants.DEPARTURE_TIME,ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        seleniumActions.highLighterMethod(getBookingActionMenuLocator(ExcursionManagementConstants.CONFIRM));
    }
    public void userViewFirstNextButton(){
        seleniumActions.highLighterMethod(firstNextButton);
    }
    public void userClicksTheFirstNextButtonAndNavigatesToTheSecondNextButton(){
        seleniumActions.clickOnElement(firstNextButton);
        seleniumActions.highLighterMethod(secondNextButton);
    }
    public void userClicksThePostBookingRadioButton(){
        seleniumActions.clickOnElement(postBookingRadioButton);
        seleniumActions.highLighterMethod(postBookingText);
        seleniumActions.highLighterMethod(operationDetailsPageConfirmButton);
    }
    public  void userClicksTheConfirmButtonOnTheOperationDetailsPage(){
        seleniumActions.scrollInToElement(operationDetailsPageConfirmButton);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.doubleClickOnElement(operationDetailsPageConfirmButton);
    }
    public boolean isSuccessCountDisplayed() {
        seleniumWaits.untilElementIsVisible(webDriver.findElement(successCount));
        seleniumActions.highLighterMethod(webDriver.findElement(successCount));
        return !webDriver.findElements(successCount).isEmpty();
    }

    public boolean isDoneStatusDisplayed() {
        seleniumActions.highLighterMethod(webDriver.findElement(status));
        return !webDriver.findElements(status).isEmpty();
    }


    public boolean scrollToPayerColumn() {

        WebElement slider = webDriver.findElement(horizontalSlider);
        new Actions(webDriver)
                .moveToElement(slider)
                .clickAndHold()
                .moveByOffset(300, 0)
                .pause(Duration.ofMillis(500))
                .release()
                .perform();

        List<WebElement> payerElements = webDriver.findElements(payerColumn);

        if (!payerElements.isEmpty()) {
            seleniumActions.highLighterMethod(payerElements.get(0));
            return true;
        }
        return false;
    }

    public String getPayerInformation() {
        String payerName = seleniumActions.getTextMessage(webDriver.findElement(payerInfo));
        return payerName.trim();
    }

    public boolean isBookingTourButtonPresent() {

        List<WebElement> bookingButtons = webDriver.findElements(bookingTourButton);
        if (!bookingButtons.isEmpty()) {
            seleniumWaits.untilElementIsVisible(bookingButtons.get(0));
            seleniumActions.highLighterMethod(bookingButtons.get(0));
            seleniumActions.clickOnElement(bookingButtons.get(0));
            return true;
        }
        return false;
    }

    public boolean isPostButtonEnabled() {
        WebElement button = webDriver.findElement(postButtonEnable);
        seleniumActions.highLighterMethod(button);
        return button.isEnabled();
    }
    public WebElement getSuccessToastMessage() {
        seleniumWaits.untilElementIsVisible(successToastMessage);
        return successToastMessage;
    }
    public void highlightSuccessToastMessage() {
        seleniumWaits.untilElementIsVisible(successToastMessage);
        seleniumActions.highLighterMethod(successToastMessage);
    }
    public boolean userViewTheSuccessPopUpMessage() {
        try {
            seleniumWaits.untilElementIsVisible(successPopUpMessage);
            seleniumActions.highLighterMethod(successPopUpMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String clickTourLinkAndGetTourCode() {
        seleniumWaits.untilElementIsVisible(tourLink);
        seleniumActions.highLighterMethod(tourLink);
        String fullText = tourLink.getText().trim();
        String tourCode = fullText.split("/")[0].trim();
        tourLink.click();
        return tourCode;
    }
    public void userUpdatesTheTourCapacity() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        int randomMinCapacity = commonFunctions.getRandomNumber(10, 30);
        minCapacityTextBox.clear();
        minCapacityTextBox.sendKeys(String.valueOf(randomMinCapacity));

        int randomMaxCapacity = commonFunctions.getRandomNumber(40, 90);

        maxCapacityTextBox.clear();
        maxCapacityTextBox.sendKeys(String.valueOf(randomMaxCapacity));

        availableCapacityTextBox.clear();
        availableCapacityTextBox.sendKeys(String.valueOf(randomMaxCapacity));
    }

    public Map<String, Integer> updateTourCapacity() {
        Map<String, Integer> capacityValues = new HashMap<>();
        capacityValues.put("minCapacity", ExcursionManagementConstants.MIN_CAPACITY);
        capacityValues.put("maxCapacity", ExcursionManagementConstants.MAX_CAPACITY);
        capacityValues.put("availableCapacity", ExcursionManagementConstants.MAX_CAPACITY);
        return capacityValues;
    }
    public String getTourCapacityValue(String capacityType) {

        String xpath = String.format("(//div[contains(@class,'%s-capacity')])[1]", capacityType.toLowerCase());
        WebElement capacityElement = webDriver.findElement(By.xpath(xpath));
        seleniumWaits.untilElementIsVisible(capacityElement);
        seleniumActions.highLighterMethod(capacityElement);
        return capacityElement.getText().trim();
    }
    public void tourSaveButton(){
        seleniumWaits.untilElementIsVisible(tourSaveButton);
        seleniumActions.scrollInToElement(tourSaveButton);
        seleniumActions.jsClick(tourSaveButton);
    }
    public void userChangesTheDateOfTheSelectedGuestTour(String sixMonthsRange,String oneYearRange){
        userClearsTheExistDateFromTheCalendarFieldAndSelectsTheDateRange(sixMonthsRange,oneYearRange);
        seleniumActions.pressEscapeKey();
        seleniumActions.selectDropdownOption(ExcursionManagementConstants.MOVING_REASON,ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        seleniumActions.highLighterMethod(moveReasonDropdown);
        seleniumWaits.untilElementIsVisible(bookingsExpandButton.get(0));
        seleniumActions.clickOnElement(bookingsExpandButton.get(0));
        seleniumActions.clickOnElement(bookingCheckBox);
    }
    public void userViewBeforeTourCapacity(){
        seleniumWaits.untilElementIsVisible(minCapacityTextBox);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.scrollInToElement(minCapacityTextBox);
        seleniumActions.highLighterMethod(minCapacityTextBox);
        seleniumActions.highLighterMethod(maxCapacityTextBox);
        seleniumActions.highLighterMethod(availableCapacityTextBox);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public void expandingTheTourDetails(){
        seleniumActions.pressEscapeKey();
        seleniumWaits.untilElementIsVisible(bookingsExpandButton.get(0));
        seleniumActions.clickOnElement(bookingsExpandButton.get(0));
        seleniumActions.clickOnElement(bookingCheckBox);
    }
    public void userSelectsAGuestFromADifferentReservation(){
        seleniumWaits.untilElementIsVisible(searchInputBox);
        seleniumActions.sendKeys(searchInputBox, PreconditionConstants.NEWLY_CREATED_GUEST2_LASTNAME);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<WebElement> crewList = webDriver.findElements(crewProfilePic);
        if (crewList.isEmpty()) {
            throw new RuntimeException("No crew members found in search results");
        }
        int randomIndex = new Random().nextInt(crewList.size());
        WebElement randomCrew = crewList.get(randomIndex);
        seleniumActions.scrollInToElement(randomCrew);
        seleniumActions.clickOnElement(randomCrew);
        seleniumActions.scrollInToElement(searchInputBox);
        logger.info("Clicked crew index: " + randomIndex);
    }
    public void userNavigateOperationDetailsPage(){
        seleniumActions.clickOnElement(secondNextButton);
    }
    public void addServicePlusIcon(){
        seleniumActions.clickOnElement(servicesAndCostsDetailsTab);
        seleniumWaits.untilElementIsVisible(addServicePlusIcon);
        seleniumActions.highLighterMethod(addServicePlusIcon);
    }

    public void userClicksThePlusIconAndFillsInTheDialogDetails(String header){
        seleniumActions.clickOnElement(addServicePlusIcon);
        seleniumActions.highLighterMethod(addCostServiceTypeAndValidityPeriodsHeader);
        seleniumActions.selectDropdownOption(ExcursionManagementConstants.SERVICE_TYPE_CODE,ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        seleniumActions.selectDropdownOption(ExcursionManagementConstants.OPERATION,ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        seleniumActions.sendKeys(descriptionInputTextBox,ExcursionManagementConstants.COMMENT_MESSAGE);
    }

    public void clickOnTourLink() {
        Actions actions = new Actions(webDriver);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(tourLinkText);
        actions.keyDown(Keys.CONTROL).click(tourLinkText).keyUp(Keys.CONTROL).perform();
    }
    public String getMeetingLocationDropdownValue() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(meetingLocationDropdownValue);
        String value = meetingLocationDropdownValue.getText().trim();
        return value.isEmpty() ? "" : value;
    }
    public void userClicksOnTheMeetingLocationDropdown(){
        seleniumActions.selectDropdownOption(ExcursionManagementConstants.MEETING_LOCATION,ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
    }

    public String userUpdatesTheTourMaxWeightInLbs() {
        int randomWeight = commonFunctions.getRandomNumber(10, 30);
        seleniumWaits.untilElementIsVisible(tourMaxWeightTextBox);
        tourMaxWeightTextBox.clear();
        seleniumActions.sendKeys(tourMaxWeightTextBox, String.valueOf(randomWeight));
        logger.info("Tour Max Weight (Lbs): {}", randomWeight);
        return String.valueOf(randomWeight);
    }

    public String getFirstMaxTotalWeight() {
        seleniumActions.highLighterMethod(maxTotalWeightValues.get(0));
        return maxTotalWeightValues.get(0).getText().trim();
    }


    public void clickLastTourLinkAndGetTourCode(){
        seleniumWaits.untilElementIsVisible(lastTourLinks);
        seleniumActions.scrollInToElement(lastTourLinks);
        seleniumActions.clickOnElement(lastTourLinks);
    }
    public void userATourWithAMinimumAgeRestriction(){
        seleniumWaits.untilElementIsVisible(minimumAgeTextBox);
        seleniumActions.scrollInToElement(minimumAgeTextBox);
        seleniumActions.sendKeys(minimumAgeTextBox,ExcursionManagementConstants.MINIMUM_AGE_Restriction);
    }
    public void userATourWithAMinimumAgeRestrictionClear(){
        seleniumWaits.untilElementIsVisible(minimumAgeTextBox);
        seleniumActions.scrollInToElement(minimumAgeTextBox);
        minimumAgeTextBox.clear();
    }
    public void scrollToLastTourLink(){
        seleniumWaits.untilElementIsVisible(lastTourLinks);
        seleniumActions.scrollInToElement(lastTourLinks);
    }

    public boolean isBookingTourAgeRestricateButtonPresent() {

        List<WebElement> bookingButtons = webDriver.findElements(bookingTourButton);

        if (!bookingButtons.isEmpty()) {
            WebElement lastBookingButton = bookingButtons.get(bookingButtons.size() - 1);

            seleniumWaits.untilElementIsVisible(lastBookingButton);
            seleniumActions.highLighterMethod(lastBookingButton);
            seleniumActions.clickOnElement(lastBookingButton);
            return true;
        }
        return false;
    }
    public boolean ageRestricatePopUpErrorMessage(){
        try {
            seleniumActions.highLighterMethod(ageRestrictionErrorMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void userClosePopUp(){
        seleniumActions.clickOnElement(ageRestrictionErrorMessageClose);
    }

    public String getFirstMaxTotalWeightLabel() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement value = maxTotalWeightLabels.get(0);
        seleniumActions.highLighterMethod(value);
        return value.getText().trim();
    }

    public String getFirstMaxTotalWeightValue() {
        WebElement value = maxTotalWeightValues.get(0);
        seleniumActions.highLighterMethod(value);
        return value.getText().trim();
    }
    public String getFirstMinMaxAvailableCapacityLabel() {
        WebElement value = minMaxAvailableCapacityLabels.get(0);
        seleniumActions.highLighterMethod(value);
        return value.getText().trim();
    }

    public String getFirstMinMaxAvailableCapacityValue() {
        WebElement value = minMaxAvailableCapacityValues.get(0);
        seleniumActions.highLighterMethod(value);
        return value.getText().trim();
    }
    public String getFirstAvailableBookedLabel() {
        WebElement label = availableBookedLabels.get(0);
        seleniumActions.highLighterMethod(label);
        return label.getText().trim();
    }

    public String getFirstAvailableBookedValue() {
        WebElement value = availableBookedValues.get(0);
        seleniumActions.highLighterMethod(value);
        return value.getText().trim();
    }
    public void userAddsACustomFeeForDiscountReasons(){
        seleniumActions.highLighterMethod(discountExcursionBookingsHeader);
        seleniumActions.selectDropdownOption(ExcursionManagementConstants.DISCOUNT_TYPE,ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        seleniumActions.sendKeys(discountValue,ExcursionManagementConstants.DISCOUNT_VALUE);
        seleniumActions.selectDropdownOption(ExcursionManagementConstants.REASON,ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        discountComments.sendKeys("A".repeat(100));

    }
    public int getShoreExcursionsCount() {
        seleniumWaits.untilElementIsVisible(otherHeader);
        seleniumActions.highLighterMethod(otherHeader);
        seleniumActions.highLighterMethod(shoreExcursionsLink);

        String text = shoreExcursionsLink.getText().trim();

        String count = text.replaceAll("\\D+", "");

        if (count.isEmpty()) {
            throw new AssertionError("Unable to retrieve Shore Excursions count from text: " + text);
        }

        return Integer.parseInt(count);
    }
    public void userClicksTheLink(){
        seleniumActions.clickOnElement(shoreExcursionsLink);
    }
    public boolean clickFirstRow() {
        seleniumWaits.untilElementIsVisible(shoreExcursionsGuestTourList);
        seleniumActions.highLighterMethod(shoreExcursionsGuestTourList);
        if (!shoreExcursionsGuestTourList.isDisplayed()) {
            return false;
        }
        return true;
    }

    public void userAddsADiscountToTheSearchedMember(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(byGuestMemeberCount);
        seleniumActions.clickOnElement(getBookTittleName);
        seleniumWaits.untilElementIsVisible(discountHeader);
        seleniumActions.clickOnElement(discountHeader);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(ExcursionManagementConstants.DISCOUNT_TYPE,ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        seleniumActions.sendKeys(discountCommentsBox,ExcursionManagementConstants.DISCOUNT_AMOUUNT);
        seleniumActions.selectDropdownOption(ExcursionManagementConstants.REASON,ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        seleniumActions.clickOnElement(discountSaveButton);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public boolean isDiscountAppliedCorrectly() {
        seleniumActions.scrollInToElement(grossAmount);
        seleniumActions.highLighterMethod(grossAmount);
        seleniumActions.highLighterMethod(discountAmount);
        seleniumActions.highLighterMethod(debitAmount);

        double gross = Double.parseDouble(grossAmount.getText().trim());
        double discount = Double.parseDouble(discountAmount.getText().trim());
        double debit = Double.parseDouble(debitAmount.getText().trim());

        double expectedDebit = Math.round((gross - discount) * 100.0) / 100.0;

        return Double.compare(expectedDebit, debit) == 0;
    }

    public static String getLastTourBookingDateAndMonthLocator(String book) {
        return "(//span[normalize-space()='" + book + "']/ancestor::otalio-bookable-item//div[contains(@class,'dashed-border')])[last()]/div/div";
    }

    public List<String> getLastTourBookingDateAndMonth(String book) {

        String bookingDetails = getLastTourBookingDateAndMonthLocator(book);

        List<WebElement> bookingDetailsText = webDriver.findElements(By.xpath(bookingDetails));
        WebElement lastBookingDetail = bookingDetailsText.get(0);

        seleniumWaits.untilElementIsVisible(lastBookingDetail);
        seleniumActions.scrollInToElement(lastBookingDetail);


        List<String> names = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            names.add(seleniumActions.getTextMessage(bookingDetailsText.get(i)));
        }

        return names;
    }
    public static String getLastTourLinkLocator() {
        return "(//span[normalize-space()='Book']/ancestor::otalio-bookable-item)[last()]//a";
    }
    public String getLastTourCode() {

        WebElement tourLink = webDriver.findElement(By.xpath(getLastTourLinkLocator()));

        seleniumWaits.untilElementIsVisible(tourLink);
        seleniumActions.scrollInToElement(tourLink);
        seleniumActions.highLighterMethod(tourLink);

        return seleniumActions.getTextMessage(tourLink).trim();
    }
    public static String getTourCodeLinkLocator(String code) {
        return "//a[contains(@id,'transportation-management') and normalize-space()='" + code + "']";
    }
    public void clickOnTheTourCodeLink(String code) {
        seleniumActions.clickOnElement(perPage100Button);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement tourCodeLink = webDriver.findElement(By.xpath(getTourCodeLinkLocator(code)));
        seleniumActions.scrollInToElement(tourCodeLink);
        seleniumActions.highLighterMethod(tourCodeLink);
        seleniumActions.jsClick(tourCodeLink);
    }
    public void addMaximumWeightAndMaximumWeightPerGuestForSelectedTour(){
        seleniumWaits.untilElementIsVisible(tourMaxWeightTextBox);
        seleniumActions.scrollInToElement(tourMaxWeightTextBox);
        tourMaxWeightTextBox.clear();
        seleniumActions.sendKeys(tourMaxWeightTextBox,ExcursionManagementConstants.MAX_WEIGHT);
        getTourMaxWeightInputField.clear();
        seleniumActions.sendKeys(getTourMaxWeightInputField,ExcursionManagementConstants.MAX_WEIGHT_PER_GUEST);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public void clickOnLastBookedTour(){
        String bookingDetails = getLastTourBookingDateAndMonthLocator(ExcursionManagementConstants.BOOK);
        WebElement bookingElement = webDriver.findElement(By.xpath(bookingDetails));
        seleniumWaits.untilElementIsClickable(bookingElement);
        seleniumActions.scrollInToElement(bookingElement);
        seleniumActions.highLighterMethod(bookingElement);
        seleniumActions.clickOnElement(bookingElement);
        seleniumActions.highLighterMethod(lastTourMaxWeight);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.jsClick(bookingButton);

    }

    public void userClicksOnTheTab(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(byGuestMemeberCount);
    }
    private List<WebElement> getGuestElements() {
        return webDriver.findElements(By.xpath("//otalio-booking-guest-container//span[contains(text(),'/')]"));
    }

    public int getGuestCount() {
        return getGuestElements().size();
    }

    public void clickGuestByIndex(int index) {
        WebElement guest = getGuestElements().get(index);
        seleniumActions.scrollInToElement(guest);
        seleniumActions.highLighterMethod(guest);
        seleniumActions.clickOnElement(guest);
    }
    public void updateWeight(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.jsClick(languageHeader);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.sendKeys(weightInputText,ExcursionManagementConstants.MAX_WEIGHT_PER_GUEST);
        seleniumActions.highLighterMethod(weightSaveButton);
    }
    public void clickOnWeightSaveButton(){
        seleniumActions.jsClick(weightSaveButton);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public boolean isWeightLimitErrorDisplayed() {
        seleniumWaits.untilElementIsVisible(errorMessageMaxTourWeight);
        seleniumActions.highLighterMethod(errorMessageMaxTourWeight);
        return errorMessageMaxTourWeight.isDisplayed();
    }
    public WebElement getTxtMinCount() {
        seleniumWaits.untilElementIsVisible(txtMinCount);
        seleniumActions.highLighterMethod(txtMinCount);
        return txtMinCount;
    }

    public WebElement getTxtMaxCount() {
        seleniumActions.highLighterMethod(txtMaxCount);
        return txtMaxCount;
    }

    public WebElement getTxtAvailableCount() {
        seleniumActions.highLighterMethod(txtAvailableCount);
        return txtAvailableCount;
    }

    public void viewPrintPostButton(){
        if (!confirmPopUpButton.isEmpty() && confirmPopUpButton.get(0).isDisplayed()) {
            confirmPopUpButton.get(0).click();
        }
        seleniumActions.selectDropdownOption(ExcursionManagementConstants.DEFAULT_PRINTER,ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        seleniumWaits.untilElementIsVisible(printAndPostButton);
        seleniumActions.highLighterMethod(printAndPostButton);
    }
    private By getButtonByText(String buttonName) {
        return By.xpath("//button//span[normalize-space()='" + buttonName + "']");
    }
    public void nclhPageAndClicksOnTheButton(String from, String to, String buttonName){

        seleniumWaits.untilElementIsVisible(fromDateCalender);
        seleniumActions.sendKeys(fromDateCalender, from);

        seleniumWaits.untilElementIsVisible(toDateCalender);
        seleniumActions.sendKeys(toDateCalender, to);

        WebElement button = webDriver.findElement(getButtonByText(buttonName));
        seleniumActions.highLighterMethod(button);
        seleniumActions.clickOnElement(button);
    }

    public void clickONShoreExcursionLink(){
        seleniumWaits.untilElementIsVisible(otherHeader);
        seleniumActions.highLighterMethod(shoreExcursionsLink);
        seleniumActions.clickOnElement(shoreExcursionsLink);
    }

    public void moveHorizontalSlider() {

        seleniumWaits.untilElementIsVisible(webDriver.findElement(horizontalSliderScroller));
        WebElement slider = webDriver.findElement(horizontalSlider);

        new Actions(webDriver)
                .moveToElement(slider)
                .clickAndHold()
                .moveByOffset(300, 0)
                .pause(Duration.ofMillis(500))
                .release()
                .perform();
    }


    public boolean isTextDisplayed() {
        moveHorizontalSlider();
        try {
            seleniumWaits.untilElementIsVisible(guestStatus);

            String actualStatus = guestStatus.getText().trim();

            logger.info("Guest Status displayed in UI :: " + actualStatus);

            boolean isValidStatus = ExcursionManagementConstants.VALID_GUEST_STATUSES.stream().anyMatch(status -> status.equalsIgnoreCase(actualStatus));

            if (isValidStatus) {
                seleniumActions.highLighterMethod(guestStatus);
                return true;
            }
            logger.error("Invalid Guest Status displayed :: " + actualStatus);
            return false;
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Guest status element was not found or not visible.");
            return false;
        }
    }

    public void waitTillBookingDropDownDisplay(){
        seleniumWaits.untilElementIsVisible(bookingStatus);
        seleniumActions.highLighterMethod(bookingStatus);
    }

    public void userSelectTheDateOfTheDesiredTourUserWantsToPrintTicket(){
        seleniumActions.highLighterMethod(bookingListMenuButton);
        seleniumActions.clickOnElement(bookingListMenuButton);
        seleniumActions.highLighterMethod(bookingListPrintActionButton);
        seleniumActions.clickOnElement(bookingListPrintActionButton);
    }

    public void nextButtonChooseOperationGroupTab(){
        seleniumWaits.untilElementIsVisible(nextButton);
        seleniumActions.clickOnElement(nextButton);
    }
    public void userSelectsFromTheDropdown(String dropdown){
        seleniumActions.selectDropdownOption(dropdown,ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
    }
    public void clicksTheButtonInTheOperationDetailsTab(){
        seleniumWaits.untilElementIsClickable(bulkUpdateConfirmButton);
        seleniumActions.clickOnElement(bulkUpdateConfirmButton);
    }
    public void requiredDateAndTimeFromTheListBelow(String startDate, String endDate){
        seleniumActions.clickOnElement(dateDropDown);
        seleniumWaits.untilElementIsClickable(clearDateButton);

        seleniumWaits.untilElementIsPresent(dateRangeRadioButton,20);
        seleniumActions.jsClick(webDriver.findElement(dateRangeRadioButton));

        seleniumActions.clickOnElement(clearStartDate);
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector(startDateInput)), startDate);

        seleniumActions.clickOnElement(clearEndDate);
        seleniumActions.typeValue(webDriver.findElement(By.xpath(endDateInput)), endDate);
        seleniumActions.pressEscapeKey();
        seleniumActions.clickOnElement(bookingActionMenuButton);
        seleniumActions.pressEscapeKey();
    }

    public void selectsTheRespectiveTourFromTheList(){
        seleniumWaits.untilElementIsClickable(checkBoxesSelect.get(1));
        seleniumActions.clickOnElement(checkBoxesSelect.get(1));
    }

    @FindBy(id = "booking-details-comments-textarea")
    private WebElement commentTextBox;

    public String enterRandomComment() {
        String randomComment = "Test Comment " + UUID.randomUUID();
        seleniumWaits.untilElementIsVisible(commentTextBox);
        seleniumActions.typeValue(commentTextBox, randomComment);
        return randomComment;
    }

    public boolean isWaitlistDisplayed(String expectedStatus) {
        moveHorizontalSlider();

        try {
            seleniumWaits.untilElementIsVisible(guestStatus);

            String actualStatus = guestStatus.getText().trim();

            logger.info("Guest Status displayed in UI :: " + actualStatus);

            if (expectedStatus.equalsIgnoreCase(actualStatus)) {
                seleniumActions.scrollInToElement(guestStatus);
                return true;
            }

            logger.error("Expected Guest Status 'Waitlist' but found :: " + actualStatus);
            return false;

        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Guest status element was not found or not visible.");
            return false;
        }
    }

    public void viewFirstSalesChannelEditButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.SHORT_WAIT));
        WebElement firstEditButton = salesChannelEditButtons.get(0);
        wait.until(ExpectedConditions.visibilityOf(firstEditButton));
        seleniumActions.scrollInToElement(firstEditButton);
        wait.until(ExpectedConditions.elementToBeClickable(firstEditButton));
    }

    public void clickFirstSalesChannelEditButton(){
        WebElement firstEditButton = salesChannelEditButtons.get(0);
        seleniumActions.clickOnElement(firstEditButton);
    }

    public WebElement  toggleEnabledInActiveSalesChannel() {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.SHORT_WAIT));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("excursion-sales-channel-editor-form-active-toggle-input")));

        wait.until(ExpectedConditions.attributeToBe(activeToggleInput, ExcursionManagementConstants.ARIA_CHECKED, ExcursionManagementConstants.TRUE));
        seleniumActions.scrollInToElement(activeToggle);
        seleniumActions.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(activeToggle));
        activeToggle.click();
        wait.until(ExpectedConditions.attributeToBe(activeToggleInput, ExcursionManagementConstants.ARIA_CHECKED, ExcursionManagementConstants.FALSE));

        wait.until(ExpectedConditions.elementToBeClickable(activeToggle));

        activeToggle.click();
        wait.until(ExpectedConditions.attributeToBe(activeToggleInput, ExcursionManagementConstants.ARIA_CHECKED, ExcursionManagementConstants.TRUE));
        seleniumWaits.untilElementIsVisible(activeToggleInput);
        seleniumActions.highLighterMethod(saveButtonEditSaleChannel);
        return activeToggleInput;
    }

    public String toggleColor(){
        return activeToggleThumbContainer.getCssValue("color");
    }
    public void userAfterEnablesTheToggleAndClicksTheButton(){
        seleniumActions.clickOnElement(saveButtonEditSaleChannel);
    }
    public boolean userSelectsTodaysDateForBooking() {
        List<WebElement> bookableItems = webDriver.findElements(bookableItemsLocator);
        seleniumWaits.untilElementIsVisible(bookableItems.get(0));
        if (bookableItems.isEmpty()) {
            return false;
        }
        return true;
    }

    public WebElement getBookingsHandlingCard() {
        seleniumWaits.untilElementIsVisible(webDriver.findElement(bookingsHandlingCard));
        return webDriver.findElement(bookingsHandlingCard);
    }
    public WebElement getBookingsManagerCard(){
        seleniumWaits.untilElementIsVisible(webDriver.findElement(bookingsManagerCard));
        return webDriver.findElement(bookingsManagerCard);
    }
    public WebElement getServiceIcon(String iconName) {
        By iconLocator = By.xpath("//*[local-name()='svg']/*[local-name()='use' and @href='#"+iconName+"']/parent::*");
        return webDriver.findElement(iconLocator);
    }

    public void userNavigateItemCard(String cards){
        WebElement serviceIcon = getServiceIcon(cards);
        seleniumWaits.untilElementIsVisible(serviceIcon);
        seleniumActions.clickOnElement(serviceIcon);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        try{
            seleniumWaits.untilElementIsVisible(serviceIcon);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.doubleClickOnElement(serviceIcon);
        } catch (Exception e) {

        }

    }



}
