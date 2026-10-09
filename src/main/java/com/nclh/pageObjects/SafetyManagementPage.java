package com.nclh.pageObjects;

import com.nclh.constants.GlobalConstants;
import com.nclh.constants.GuestProfileConstants;
import com.nclh.constants.SafetyManagementConstatnts;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumWaits;
import junit.framework.Assert;
import lombok.Getter;
import lombok.Setter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.nclh.utilities.SeleniumActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import com.nclh.constants.ExcursionManagementConstants;
import org.openqa.selenium.interactions.Actions;

import static java.util.Base64.getDecoder;

public class SafetyManagementPage {
    private WebDriver webDriver;
    private SeleniumActions seleniumActions;
    private CommonPage commonPage;
    private SeleniumWaits seleniumWaits;
    private CommonFunctions commonFunctions;
    @Setter
    @Getter
    String safetyNumber;
    public static final Logger logger
            = LoggerFactory.getLogger(SeleniumActions.class);

    public SafetyManagementPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        seleniumActions = new SeleniumActions(webDriver);
        commonPage = new CommonPage(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonFunctions = new CommonFunctions();

    }

    @FindBy(xpath = "//button[@id='complex-filter-label-embarkation.displayDate-open-button']/icon/*[name()='svg']")
    private WebElement validFromArrow;

    @FindBy(xpath = "//button[contains(@id,'date-overlay-clear-button')]/span/icon/*[name()='svg']/*[name()='use']")
    private WebElement clearDateButton;

    @FindBy(css = "[aria-label='Add']")
    private WebElement plusButton;

    @FindBy(xpath = "//span[normalize-space()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//button[@id='complex-filter-label-onboard-open-button']/icon/*[name()='svg']/*[name()='use']")
    private WebElement boardStatusFilter;

    @FindBy(id = "complex-filter-switch-overlay-list-option-true")
    private WebElement boardStatusFilterYesCheckbox;

    @FindBy(xpath = "//span[normalize-space()='Save & Finish']")
    private WebElement saveAndFinishButton;

    @FindBy(xpath = "//table/tbody/tr/td/div[text()]")
    private List<WebElement> tableHeaderRowItems;

    @FindBy(css = "#delete-confirmation-dialog-delete-button > span:first-of-type")
    private WebElement deleteButton;

    @FindBy(css = "#breadcrumbs-title")
    private WebElement breadCrumbs;

    @FindBy(css = "[aria-label='Add New Safety Plan'] > span > icon")
    private WebElement safetyPlanAddButton;

    @FindBy(css = "[placeholder='Classification']")
    private WebElement classificationDropdown;

    @FindBy(css = "[placeholder='Safety Division']")
    private WebElement safetyDivisionDropdown;

    @FindBy(css = "[placeholder='Safety Position']")
    private WebElement safetyPositionDropdown;

    @FindBy(css = "[placeholder='Assembly Station']")
    private WebElement assemblyStationDropdown;

    @FindBy(css = "[placeholder='Lifeboat/Raft']")
    private WebElement liftBoatDropdown;

    @FindBy(css = "[placeholder='Search']")
    private WebElement searchInputBox;

    @FindBy(css = "button[id*='safetyNumber'] > icon")
    private WebElement numberDropdown;

    @FindBy(css = "[placeholder='Number']")
    private WebElement searchNumberInputBox;

    @FindBy(xpath = "(//table/tbody/tr/td[@role='gridcell']/div/a)[1]")
    private WebElement safetyNumberLink;

    @FindBy(xpath = "//mat-row//mat-cell[2]/a")
    private WebElement setupNumberLink;

    @FindBy(xpath = "(//mat-cell[contains(@class,'cabinType')]/span)[1]")
    private WebElement cabinTypeLink;

    @FindBy(xpath = "//mat-icon[normalize-space()='add']")
    private WebElement newCabinAddButton;

    @FindBy(css = "[placeholder='Deck']")
    private WebElement deckDropdown;

    @FindBy(css = "[placeholder='Type']")
    private WebElement typeDropdown;

    @FindBy(css = "[placeholder='Category']")
    private WebElement categoryDropdown;

    @FindBy(css = "[placeholder='Muster Station']")
    private WebElement musterStationDropdown;

    @FindBy(css = "[placeholder='Ship Side']")
    private WebElement shipSideDropdown;

    @FindBy(css = "[placeholder='Location']")
    private WebElement LocationDropdown;

    @FindBy(xpath = "//span[normalize-space()='ADD']")
    private WebElement aDDButton;

    @FindBy(css = "button[id*='code-open-button'] > icon")
    private WebElement numberCabinDropdown;

    @FindBy(css = "mat-checkbox[id^='safety-assignment-list'] > label >span > input:nth-child(1)")
    private WebElement crewCheckBox;

    @FindBy(xpath = "(//td/div/span[contains(@aria-describedby,'describedby-message')])[1]")
    private WebElement crewId;

    @FindBy(xpath = "(//a[starts-with(@id,'safety-assignment-list')])[1]")
    private WebElement crewFullName;

    @FindBy(xpath = "(//td/div/a[starts-with(@href,'/safety-management')])[1]")
    private WebElement safetyNumberInTable;

    @FindBy(xpath = "(//h4)[1]")
    private WebElement cabinNumber;

    @FindBy(xpath = "//button/span[normalize-space()='Assign']")
    private WebElement assignButton;

    @FindBy(xpath = "//button/span[normalize-space()='Unassign']")
    private WebElement unAssignButton;

    @FindBy(xpath = "//h4/a")
    private List<WebElement> crewNamesList;

    @FindBy(xpath = "//span[normalize-space()='Confirm']")
    private WebElement confirmButton;

    @FindBy(css = "#complex-filter-search-input")
    private WebElement searchBox;

    @FindBy(css = "[role='option']")
    private List<WebElement> dropDownList;

    @FindBy(xpath = "//td/div/a[starts-with(@href,'/safety-management')]")
    private List<WebElement> listOfSafetyNumbers;

    @FindBy(xpath = "//a[@class='mat-tooltip-trigger' and ancestor::*[div[contains(text(),'Safety Number')]]]")
    private WebElement safetyNumberText;

    @FindBy(xpath = "//div[@class='text-center']")
    private WebElement profileView;
    @FindBy(css = "[placeholder='First Name']")
    private WebElement firstNameInputField;

    @FindBy(css = "[placeholder='Last Name']")
    private WebElement lastNameInputField;
    @FindBy(css = "#otalio-overview-activity-step3-add-button")
    private WebElement addButtonBox;

    @FindBy(css = "#otalio-overview-activity-finish-button")
    private WebElement finishButtonBox;
    @FindBy(css = "#participants-table-selecting-search-input")
    private WebElement search;
    @FindBy(css = "#overview-calendar-add-activity-button")
    private WebElement addActivity;
    @FindBy(css = "button[id*='save']")
    private WebElement submitButton;
    @FindBy(css = "[id*='complex-filter-search-input']")
    private WebElement activeModeSearchBar;
    @FindBy(css = "button[id*='code'] > icon")
    private WebElement codeDropdown;
    @FindBy(css = "[data-placeholder='Code']")
    private WebElement searchCodeInputBox;

    By assignmentDates = By.xpath("//td[@aria-label=\"Column Date\"]/ancestor::div/following-sibling::div/descendant::tr");
    By assignmentDateRow = By.xpath("//td[@aria-label=\"Column Date\"]/ancestor::div/following-sibling::div/descendant::tr/td[1]");
    By deletePopUpButton = By.xpath("//span[normalize-space(text())='Delete']");
    By yesPopButton = By.xpath("//span[normalize-space(text())='Yes']");
    By CrewName = By.xpath("//table[contains(@class,'table-fixed')]//tr[@aria-selected='false'][not(preceding-sibling::tr[@aria-selected='false'])]//td[3]//a");
    By safetyButton = By.xpath("//mat-expansion-panel-header[@role='button' and @aria-disabled='false']//div[text()='Safety']");
    By safetyNumbers = By.xpath("//a[@class='mat-tooltip-trigger']");
    By profileText = By.xpath("//otalio-profile-card/descendant::h1[contains(text(),'')]");
    By updateButton = By.xpath("//button[contains(@id,'update-button')]");
    By successPopUp = By.xpath("//div[@id='toast-title-success']");
    By assignedCheckBoxes = By.xpath("//label[contains(@class,'checkbox')]");
    String printSafetyCardButton = "//button[@color='primary' and not(@disabled='true')]";
    By calendarViewHeader = By.xpath("//div[@aria-selected='true' and @aria-disabled='false']//div[text()='Calendar View']");
    By toogleCheckBoxAddActivity = By.xpath("//mat-slide-toggle[@formcontrolname='allowOverlap']//input[@type='checkbox' and @aria-checked='true']");
    String addActivityButton = "//button[contains(@id, 'add-activity-button')]";
    By nextButton = By.id("otalio-overview-activity-step1-next-button");
    By selectedAddbutton = By.id("participants-table-selecting-add-selected-button");
    By selectParticipants = By.xpath("//*[@aria-selected='false']//div[text()='Select Participants / Rules']");
    By checkBoxRows = By.xpath(
            "//mat-checkbox[contains(@id,'safety-assignment-list')] | " +
                    "//div[@aria-label='Select row']"
    );
    By checkBoxSelectedName = By.xpath("//*[@class='mat-tooltip-trigger d-flex align-items-center ng-star-inserted']");
    By successToast = By.xpath("//div[@id='toast-title-success']");
    By invalidToast = By.xpath("//div[@id='toast-title-invalid']");
    By inPortManningGroups = By.xpath("//*[normalize-space(text())='In Port Manning Groups:']//following::icon[@icon='arrow-down']");
    By inPortManningGroupsDropdownElement = By.xpath("//*[contains(@class,'-checkbox ng-star-inserted')]");
    By threeVerticalDots = By.id("participant-list-menu-button");
    By lastName = By.xpath("//table/tbody/tr/td[@role='gridcell']/div/a[(contains(@class,'cursor-pointer text-primary') and not(@id)) or contains(@id,'last-name')]");
    By addActivityBtn = By.xpath("//button[contains(@id, 'add-activity-button')]");
    By markasAbsent = By.xpath("//button[@role='menuitem' and contains(text(),'Mark as Absent')]");
    By confirmationBox = By.xpath("//*[contains(text(),'Confirmation')]");
    By commentstextarea = By.id("require-reason-dialog-comments-textarea");
    By confirmButtonBox = By.xpath("//span[normalize-space(text())='Confirm']");
    By assemblyStationBox = By.id("complex-filter-label-assemblyStationLocationId-open-button");
    By addMoreFiltersIcon = By.xpath("//*[@class='add-more-filters-icon']");
    By warningMessage = By.xpath("//*[contains(text(),'already scheduled for another activity for this date and time frame.')]");
    By safetyInstruction = By.xpath("//*[contains(text(),'Specific Safety Instruction')]");
    By emergencyPlan = By.xpath("//*[contains(text(),' Emergency Plan')]/ancestor::*[@role='button' and @aria-disabled='false']//span[contains(@class,'mat-expansion-indicator')]");
    By safetyPlanEmergencyTextBox = By.xpath("//*[contains(@id,'safety-plan-emergency-editor-old-version-safety-comment-0')]");
    By safetyPlanEmergencySaveButton = By.id("safety-plan-emergency-editor-old-version-save-button");
    By safetyNumberElement = By.xpath("//table/tbody/tr/td[@role='gridcell']/div/a[(contains(@class,'cursor-pointer text-primary') and not(@id)) or contains(@id,'')]");
    By profilePage = By.xpath("//otalio-profile-card");
    @FindBy(css = "[class*='complex-search-input']")
    private WebElement searchField;
    By tablecolumn = By.xpath("//tr[@aria-rowindex='1']");

    @FindBy(xpath = "//div[contains(text(),'Account Type:')]/../descendant::div[text()][contains(@class,'text-truncate')]")
    private WebElement accountType;
    By crewTabledetails = By.xpath("//td[@aria-colindex and text()='Crew']");
    By count = By.xpath("//div[contains(text(),'Total Attendees / Total Absent:')]");
    By columnheader = By.xpath("//td[@role='columnheader']//div[contains(@class,'text')]");
    By guestCount = By.xpath("//div[@class='dx-info']");
    By cancelButton = By.xpath("//span[normalize-space(text())='Cancel']");
    By primaryEnable = By.xpath("//button[@color='primary']");
    By counts = By.xpath("//div[text()='Counts']");
    By countGangwayHeader = By.xpath("//div[@mattooltipposition='below' and contains(text(),'Gangway')]");
    By crewSelectAllCheckBox = By.xpath("//*[@aria-checked='false' and @aria-label='Select all']");
    By bySafetyNumberLog  = By.xpath("(//tr[@aria-rowindex='1'])[2]//td[@role='gridcell']");
    By noDataText = By.xpath("//*[contains(text(),'No Data yet')]");
    By crewSearchBar = By.xpath("//input[contains(@class,'search-input')] | //otalio-person-selection-table[not(@mode='selected')]//input[contains(@placeholder,'Search')] | //input[contains(@id,'search-input')]");
    By currentSafteyAssignmentDataTab = By.xpath("//otalio-current-safety-assignment//div[@class='row ng-star-inserted']");
    By activityTypeDropDown = By.xpath("//mat-form-field[.//*[normalize-space(text())='Activity Type']]//div[@aria-label='Select' or contains(@class,'mat-select-arrow-wrapper')]");
    By trainingCertificate = By.xpath("//mat-form-field[.//*[normalize-space(text())='Training / Certification Type']]//div[@aria-label='Select' or contains(@class,'mat-select-arrow-wrapper')]");
    By searchBar = By.xpath("//input[contains(@data-placeholder,'Search')]");
    By itemsCount = By.xpath("//div[contains(text(),'items')]");
    By addSpecialNeedsBtn = By.xpath("//span[normalize-space()='Add Special Needs']");
    By specialNeedsComments = By.xpath("(//input[contains(@id,'personal-special-needs-comments')])[last()]");
    By trainingCertificationRow = By.xpath("//mat-tab-group[.//div[normalize-space()='Training / Certifications']]//tr[@aria-rowindex='1']");
    By trainingCertificationAddButton = By.id("training-certification-add-button");
    By addTrainingCertificationBtn = By.cssSelector("#training-certification-form-add-model-button");
    By saveAndFinish = By.xpath("//span[normalize-space()='Save & Finish']");
    By confirm = By.xpath("//span[text()='Confirm' or normalize-space()='Confirm']");
    By activity_Overview = By.xpath("//div/a[normalize-space()='Activity Overview']");
    @FindBy(css = "[id*='safety-emergency-drill']")
    private WebElement safetyEmergencyAddButton;
    @FindBy(css = "[id*='-step2-next-button']")
    private WebElement skipButton;
    By activityList = By.cssSelector("[id*='overview-calendar-activity-list'] > div > div:first-of-type");
    By conirmpop_up = By.cssSelector("[id*='dialog-confirm-button']");
    By txtIPMActivity = By.xpath("//otalio-overview-activity//*[contains(text(),'IPM')]");
    By emerTeamLocator = By.xpath("//div[contains(text(),'Emer. Team:')]/../descendant::div[text()][contains(@class,'text-truncate')]");
    By dropdownItemsLocator = By.xpath("//div[@class='mat-list-text']");
    @FindBy(xpath = "//otalio-emergency-teams-chip-list//otalio-chip")
    private WebElement chipElement;
    @FindBy(xpath = "//otalio-emergency-teams-chip-list//otalio-chip")
    private List<WebElement> chipElements;
    By addSpecialNeedsDropDown = By.xpath("(//*[@placeholder='Special Needs'])[last()]");
    @FindBy(xpath = "//img[contains(@src,'imageType=PROFILE')]")
    private List<WebElement> profileImages;
    @FindBy(xpath = "//otalio-profile-card//h1")
    private WebElement guestNameElement;
    @FindBy(xpath = "//otalio-profile-card//div//h4")
    private WebElement guestAgeElement;
    @FindBy(xpath = "(//div[contains(@class,'capitalize')])[1]")
    private WebElement nationalityElement;
    @FindBy(xpath = "(//div[contains(@class,'capitalize')])[2]")
    private WebElement languageElement;
    @FindBy(css = "[id*='personal-info-last-name-input']")
    private WebElement lastNameElement;
    @FindBy(css = "[id*='personal-info-first-name-input']")
    private WebElement firstNameElement;
    @FindBy(css = "[id*='otalio-infinite-select-nationality-field']")
    private WebElement nationalityTextElement;

    @FindBy(css = "[id*='personal-info-language-select']")
    private WebElement languageTextElement;
    @FindBy(xpath = "//otalio-complex-filter-multi-label-content//div[text()='Assembly Station:']")
    private WebElement assemblyStationDropDown;
    @FindBy(xpath = "//mat-list-option//mat-pseudo-checkbox")
    private List<WebElement> checkBoxes;

    @FindBy(xpath = "//mat-list-option//mat-pseudo-checkbox/following-sibling::div//span")
    private List<WebElement> stationNames;
    @FindBy(xpath = "//td[@role='columnheader' and contains(@id,'dx-col')]")
    private List<WebElement> headers;
    @FindBy(xpath = "//table//tbody//tr//td[3]//a")
    private List<WebElement> crewNameList;
    @FindBy(xpath = "//td/div/a[starts-with(@href,'/safety-management')]")
    private List<WebElement> safteyNumberList;
    @FindBy(xpath = "//*[contains(@id,'user-name')]")
    private List<WebElement> UserName;
    @FindBy(xpath = "(//mat-tab-group[.//div[normalize-space()='Upcoming Drills']]//tr[@aria-rowindex]//td[2])[last()]")
    private WebElement upcomingDrillTrainingDate;
    @FindBy(xpath = "//div[contains(text(),'Safety Drill')]")
    private List<WebElement> safetyDrillList;
    @FindBy(xpath = "//input[@placeholder='Activity Date']" )
    private WebElement activityDateField;
    @FindBy(xpath = "//otalio-user-name")
    private List<WebElement> crewMemberNames;
    @FindBy(xpath = "//tr[@aria-rowindex]//td[@aria-colindex='13']//span")
    private List<WebElement> positionValues;
    @FindBy(css = "[placeholder*='Safety Positions']")
    private WebElement safetyPositionsSearch;
    @FindBy(xpath = "//div[@class='dx-scrollable-scrollbar dx-widget dx-scrollbar-horizontal dx-scrollbar-hoverable']//div[@class='dx-scrollable-scroll']")
    private List<WebElement> horizontalSlider;
    @FindBy(xpath = "//tbody/tr/td[count(//tr[td[normalize-space()='Embark Date']][1]/td[normalize-space()='Embark Date']/preceding-sibling::td)+1]")
    private List<WebElement> embarkDates;
    @FindBy(xpath = "//div[@aria-label='Select all' and not(contains(@class,'dx-state-invisible'))]")
    private WebElement selectAllCheckBox;
    @FindBy(css = "#list-select-page-button")
    private WebElement selectPageButton;
    @FindBy(xpath = "//otalio-person-selection-table[@mode='selected']//td[@aria-colindex='3']//span[normalize-space()!='']")
    private List<WebElement> safetyNumberColumn;
    @FindBy(css = "[id*='participants-table-selected-search-input']")
    private WebElement searchBoxForSelectedCrew;
    @FindBy(css = "[id='otalio-infinite-select-handicapTypeId-field']")
    private List<WebElement> specialNeedsOptions;
    @FindBy(xpath = "(//button[.//*[name()='use' and (contains(@href,'#clean') or contains(@href,'delete'))]])[last()]")
    private WebElement deleteIconSpecialNeed;
    @FindBy(xpath = "//span[normalize-space()='Save']")
    WebElement saveButton;
    @FindBy(xpath = "//otalio-person-selection-table[@mode='selected']//td[@aria-colindex='8']//span[normalize-space()!='']")
    private List<WebElement> crewIdNumberColumn;
    @FindBy(xpath = "(//h4)[1]")
    private WebElement cabinNumberText;
    @FindBy(xpath = "//mat-panel-title[normalize-space(text())='In Port Manning']")
    private WebElement inPortManningHeader;
    @FindBy(css = "[id='safety-plan-port-manning-editor-save-and-close-button']")
    private WebElement saveAndFinishButtonInPortMinning;
    @FindBy(css = "[id='participants-table-selected-remove-all-button']")
    private WebElement removeAllButton;
    @FindBy(xpath = "//*[contains(@id,'participants-table-selected-table-list-')]")
    private List<WebElement> selectedGuestList;
    @FindBy(xpath = "(//*[contains(@id,'participants-table-selecting-table-list-')])[1]")
    private WebElement firstGuest;
    @FindBy(css = "[id*='participants-table-selecting-table-list-'][id$='-revert-icon']")
    private WebElement revertIcon;
    @FindBy(xpath = "//otalio-activity-participants-container")
    private List<WebElement> safetyGuestData;
    @FindBy(xpath = "//div[number(@aria-rowcount) > 1]//div[@role='checkbox' and @aria-label='Select all']")
    private  List<WebElement> selectAllCheckboxes;
    @FindBy(css = "[data-placeholder='Safety Number']")
    private WebElement safetyNumberTexts;
    @FindBy(xpath = "//*[@id='participants-table-selecting-table-list-lisa--test-revert-icon']")
    private  List<WebElement> undoIcons;
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.SHORT_WAIT));

    public void clearPastAssignments() {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        int numberOfRows = seleniumActions.getNumberOfElements(webDriver.findElements(assignmentDates));
        if (numberOfRows > 0) {
            for (int i = 0; i < numberOfRows; i++) {
                try {
                    seleniumActions.clickOnElement(assignmentDateRow);
                } catch (StaleElementReferenceException ste) {
                    webDriver.findElement(assignmentDateRow).click();
                }
                seleniumActions.clickOnElement(deletePopUpButton);
                seleniumActions.clickOnElement(yesPopButton);
            }
        }
    }

    public void clickOnSafetyNumberLink() {
        seleniumActions.clickOnElement(safetyNumberLink);
    }

    public void enterSafetyNumber(String safetyNumber) {
        seleniumActions.typeValue(searchBox, safetyNumber);
    }

    public int getCrewName(String crewName) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return seleniumActions.getNumberOfElements(webDriver.findElements(By.xpath("//a[normalize-space()='" + crewName + "']")));
    }

    public int getCrewId(String crewId) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return seleniumActions.getNumberOfElements(webDriver.findElements(By.xpath("//span[normalize-space()='" + crewId + "']")));
    }

    public void selectCheckBoxOfTheCrew(String crewName, int index) {
        int index1 = index + 1;
        String checkbox = "//h4/a[normalize-space()='%s']/ancestor::div[starts-with(@class,'row')]/div/mat-checkbox";
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath(String.format(checkbox, crewName))));
        seleniumActions.clickOnElement(By.xpath(String.format(checkbox, crewName)));
    }

    public List<String> getListOfCrewNames() {
        try {
            return seleniumActions.getListOfElementsText(crewNamesList);
        } catch (StaleElementReferenceException ste) {
            List<String> listOfElements = new ArrayList<>();
            for (int i = 0; i < webDriver.findElements(By.xpath("//h4/a")).size(); i++) {
                listOfElements.add(webDriver.findElements(By.xpath("//h4/a")).get(i).getText().trim());
            }
            return listOfElements;
        }
    }

    public String getCrewNameFromTable(String safetyNumber) {
        String crewName = "//a[normalize-space()='%s']/../../following-sibling::td/descendant::a";
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath(String.format(crewName, safetyNumber))));
    }

    public void selectCabinNumber(String cabinNumber) {
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//a[contains(text(),'" + cabinNumber + "')]/ancestor::div/div/div/mat-checkbox)[1]")));
        } catch (StaleElementReferenceException ste) {
            webDriver.findElement(By.xpath("(//a[contains(text(),'" + cabinNumber + "')]/ancestor::div/div/div/mat-checkbox)[1]")).click();
        }
    }

    public void clickOnUnAssignButton() {
        seleniumActions.clickOnElement(unAssignButton);
    }

    public void clickOnAssignButton() {
        seleniumActions.clickOnElement(assignButton);
        seleniumActions.sleep(3000);
        if (webDriver.findElements(By.xpath("//span[normalize-space()='Confirm']")).size() > 0) {
            seleniumActions.clickOnElement(confirmButton);
        }
    }

    public String getSafetyNumberInTable() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        try {
            return seleniumActions.getTextMessage(safetyNumberInTable);
        } catch (Exception e) {
            return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("(//td/div/a[starts-with(@href,'/safety-management')])[1]")));
        }
    }

    public List<WebElement> getListOfSafetyNumbersInTable() {
        return listOfSafetyNumbers;
    }

    public String getCabinNumber() {
        return cabinNumber.getText().trim();
    }

    public String switchToPDFPageAndGetPDFContent() {
        String crewDetails = null;
        seleniumActions.sleep(5000);
        //Object[] windowHandles=webDriver.getWindowHandles().toArray();
        //webDriver.switchTo().window((String) windowHandles[1]);
        String parentWindowId = webDriver.getWindowHandle();
        Set<String> windowIds = webDriver.getWindowHandles();
        System.out.println("windowIds :: " + windowIds);
        for (String window : windowIds) {

            System.out.println(webDriver.getTitle());
            webDriver.switchTo().window(window);
            String regex = "[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(webDriver.getTitle());
            if (webDriver.getTitle().contains("Safety Management Service")) {
                System.out.println("Not PDF Page");
            } else {
                try {
                    crewDetails = getPDFText(webDriver.getCurrentUrl());
                    System.out.println("crewDetails :: " + crewDetails);

                } catch (Exception nse) {
                    System.out.println(nse.toString());
                }
            }
        }
        return crewDetails;
    }

    public String getPDFText(String blobUrl) throws IOException {
        String text = null;
        String message = null;
        String script = "var xhr = new XMLHttpRequest();" +
                "xhr.open('GET', '" + blobUrl + "', true);" +
                "xhr.responseType = 'blob';" +
                "xhr.onload = function() {" +
                "    var blob = xhr.response;" +
                "    var reader = new FileReader();" +
                "    reader.onloadend = function() {" +
                "        window.blobContent = reader.result;" +
                "    };" +
                "    reader.readAsDataURL(blob);" +
                "};" +
                "xhr.send();";

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript(script);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String base64Content = (String) js.executeScript("return window.blobContent;");
        // System.out.println("Base64 PDF Content: " + base64Content);
        String[] base64 = base64Content.split(",");
        System.out.println(base64[1]);
        String base64Pdf = base64[1];
        //System.out.println("base64Pdf ::" + base64Pdf);
        if (base64Pdf.startsWith("data:application/pdf;base64,")) {
            base64Pdf = base64Pdf.substring("data:application/pdf;base64,".length());
        }
        base64Pdf = base64Pdf.replace('-', '+').replace('_', '/');
        seleniumActions.sleep(1000);
        try {
            byte[] pdfData = getDecoder().decode(base64Pdf);

            try (InputStream inputStream = new ByteArrayInputStream(pdfData);
                 //PDDocument document = Loader.loadPDF(inputStream.readAllBytes())) {
                 PDDocument document = PDDocument.load(inputStream.readAllBytes())) {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                text = pdfStripper.getText(document);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid Base64 string.");
            e.printStackTrace();
        }
        return text;
    }

    public void clickOnCheckBox(String crew) {
        String checkBox1 = "//a[normalize-space()='%s']/ancestor::td/preceding-sibling::td/div/mat-checkbox/label/span/input";

        String checkBox = "(//a[normalize-space()='%s']/ancestor::td/preceding::td/div/div/span)[1]";

        try {
            seleniumActions.clickOnElement(webDriver.findElement(
                    By.xpath(String.format(checkBox1, crew))
            ));
        } catch (NoSuchElementException nse) {
            seleniumActions.clickOnElement(webDriver.findElement(
                    By.xpath(String.format(checkBox, crew))));
        } catch (StaleElementReferenceException ste) {
            try {
                webDriver.findElement(By.xpath("//a[normalize-space()='" + crew + "']/ancestor::td/preceding-sibling::td/div/mat-checkbox/label/span/input")).click();
            } catch (ElementClickInterceptedException eie) {
                seleniumActions.jsClick(webDriver.findElement(
                        By.xpath(String.format(checkBox, crew))));
            }
        }
    }
    public String getCrewId() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return seleniumActions.getTextMessage(crewId);
    }

    public String crewFullName() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return seleniumActions.getTextMessage(crewFullName);
    }

    public String getCabinSetUpNumberLinkText() {
        return seleniumActions.getTextMessage(setupNumberLink);
    }

    public String getSafetyCabinTypeLinkText() {
        return seleniumActions.getTextMessage(cabinTypeLink);
    }

    public void searchForCabinSafetyNumber(String safetyInputNumber) {
        seleniumActions.clickOnElement(numberCabinDropdown);
        seleniumActions.typeValue(searchNumberInputBox, safetyInputNumber);
        seleniumActions.sleep(5000);
    }

    public void clickOnADDButton() {
        seleniumActions.clickOnElement(aDDButton);
        seleniumActions.sleep(3000);
    }

    public void addCabinSafetyNumber(String name) {
        seleniumActions.clickOnElement(newCabinAddButton);
        setSafetyNumber(String.valueOf(getRandomNumber(10000, 90000)));
        commonPage.typeValueInPlaceHolderCssInput("Number", getSafetyNumber());
        commonPage.typeValueInPlaceHolderCssInput("Description (EN)", "Test" + String.valueOf(getRandomNumber(1000, 2000)));
        selectSafetyDropDown(deckDropdown);
        selectSafetyDropDown(typeDropdown, name);
        selectSafetyDropDown(categoryDropdown);
        commonPage.typeValueInPlaceHolderCssInput("Number of Beds", String.valueOf(getRandomNumber(1, 9)));
        commonPage.typeValueInPlaceHolderCssInput("Phone Number 1", "1234567890");
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath("//span[text()='Safety and additional attributes']")));
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsClickable(musterStationDropdown);
        selectSafetyDropDown(musterStationDropdown);
        selectSafetyDropDown(shipSideDropdown);
        selectSafetyDropDown(LocationDropdown);
    }

    public String getSafetyNumberLinkText() {
        return seleniumActions.getTextMessage(safetyNumberLink);
    }

    public void searchForSafetyNumber(String safetyInputNumber) {
        seleniumActions.clickOnElement(numberDropdown);
        seleniumActions.sendKeys(searchNumberInputBox, safetyInputNumber);
        seleniumActions.sleep(5000);
    }

    public void clickOnAddButton() {
        seleniumActions.clickOnElement(addButton);
        seleniumActions.sleep(2000);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        if (webDriver.findElements(By.xpath("//span[normalize-space()='Save & Finish']")).size() > 0) {
            seleniumActions.clickOnElement(saveAndFinishButton);
        }

        if (webDriver.findElements(By.xpath("//span[normalize-space()='Save & Finish']")).size() > 0) {
            seleniumActions.moveToElementAndClick(saveAndFinishButton);
        }
    }

    public void clickOnLink(String link) {
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("//div/a[normalize-space()='" + link + "']")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div/a[normalize-space()='" + link + "']")));
    }

    public void selectSafetyDropDown(WebElement element) {
        seleniumActions.sleep(2000);
        seleniumWaits.untilElementIsClickable(element);
        seleniumActions.clickOnElement(element);
        seleniumActions.sleep(3000);
        try {
            seleniumWaits.untilElementIsClickable(dropDownList.get(0));
            seleniumActions.clickOnElement(dropDownList.get(0));
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(webDriver.findElements(By.cssSelector("[role='option']")).get(0));
        }
    }

    public void selectSafetyDropDown(WebElement element, String data) {
        seleniumActions.sleep(1000);
        seleniumWaits.untilElementIsClickable(element);
        seleniumActions.clickOnElement(element);
        if (webDriver.findElements(By.cssSelector("[placeholder='Search']")).size() > 0) {
            try {
                seleniumActions.typeValue(searchInputBox, data);
                seleniumActions.sleep(3000);
            } catch (StaleElementReferenceException ste) {
                webDriver.findElement(By.cssSelector("[placeholder='Search']")).sendKeys(data);
                seleniumActions.sleep(3000);
            }
        }
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(
                "//span[@class='mat-option-text'][normalize-space()='" + data + "']"
        )));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(
                "//span[@class='mat-option-text'][normalize-space()='" + data + "']"
        )));
    }

    public void addAddSafetyNumber(String rank, String classification, String safetyDivision, String
            safetyPosition, String assemblyStation, String lifeBoat) {
        seleniumActions.clickOnElement(safetyPlanAddButton);
        setSafetyNumber(String.valueOf(getRandomNumber(10000, 90000)));
        commonPage.typeValueInPlaceHolderCssInput("Safety Number", getSafetyNumber());
        commonPage.typeValueInPlaceHolderCssInput("Rank", rank);
        commonPage.typeValueInPlaceHolderCssInput("Comment", "Test" + String.valueOf(getRandomNumber(1000, 2000)));
        try {
            selectSafetyDropDown(classificationDropdown, classification);
            selectSafetyDropDown(safetyDivisionDropdown, safetyDivision);
            selectSafetyDropDown(safetyPositionDropdown, safetyPosition);
            selectSafetyDropDown(assemblyStationDropdown, assemblyStation);
            selectSafetyDropDown(liftBoatDropdown, lifeBoat);
        } catch (Exception e) {
            seleniumActions.pressEscapeKey();
        }
    }

    public void deleteRecord(String itemName) {
        if (webDriver.findElements(By.xpath("//a[normalize-space()='" + itemName + "']/ancestor::tr/descendant::button/span/icon")).size() > 0) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//a[normalize-space()='" + itemName + "']/ancestor::tr/descendant::button/span/icon")));
            seleniumWaits.untilElementIsClickable(deleteButton);
            seleniumActions.clickOnElement(deleteButton);
        }
    }

    public int getRecord(String itemName) {
        seleniumActions.sleep(3000);
        return webDriver.findElements(By.xpath("//a[normalize-space()='" + itemName + "']/ancestor::tr/descendant::button/span/icon")).size();
    }

    public int getTableHeaderRowItemIndex(String item) {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < tableHeaderRowItems.size(); i++) {
            items.add(tableHeaderRowItems.get(i).getText());
        }
        return items.indexOf(item);
    }

    public List<String> getAddedItemInTheTable(String itemName) {
        seleniumActions.sleep(3000);
        List<String> data = new ArrayList<>();
        switch (itemName) {
            case "Code":
                int codeIndex = getTableHeaderRowItemIndex(itemName) + 1;
                data.add(webDriver.findElement(By.xpath("(//table/tbody/tr/td/div[text()='" + itemName + "']/following::tbody/tr/td/div/a)[" + codeIndex + "]")).getText());
                System.out.println("code :: " + data);
                break;
            case "Description":
                int descIndex = getTableHeaderRowItemIndex(itemName) + 1;
                data.add(webDriver.findElement(By.xpath("(//table/tbody/tr/td/div[text()='" + itemName + "']/following::table/tbody/tr/td)[" + descIndex + "]")).getText());
                System.out.println("desc :: " + data);
                break;
        }
        return data;
    }

    public void openSafetyManagementItem(String itemName) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath("//a[normalize-space()='" + itemName + "']")));
        seleniumActions.highLighterMethod(webDriver.findElement(By.xpath("//a[normalize-space()='" + itemName + "']")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//a[normalize-space()='" + itemName + "']")));
        if (webDriver.findElements(By.cssSelector("#breadcrumbs-title")).size() > 0) {
        } else {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//a[normalize-space()='" + itemName + "']")));
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void addEmergencyDrillType(String code, String description, String listingNumber) {
        seleniumActions.clickOnElement(plusButton);
        List<String> items = Arrays.asList("code", "description", "listingNumber");
        int number = getRandomNumber(10, 20);
        List<String> data = Arrays.asList(code, description, String.valueOf(number));

        for (int i = 0; i < items.size(); i++) {
            webDriver.findElement(By.cssSelector("[formcontrolname='" + items.get(i) + "']")).clear();
            seleniumActions.typeValue(webDriver.findElement(By.cssSelector("[formcontrolname='" + items.get(i) + "']")), data.get(i));
        }
        seleniumActions.clickOnElement(addButton);

        // (//div[text()='Emergency drill type with Listing Number 10 already exists'][@id='toast-message-content'])[2]
        for (int i = 1; i < 5; i++) {
            if (webDriver.findElements(By.xpath("(//div[text()='Emergency drill type with Listing Number " + number + " already exists'][@id='toast-message-content'])[2]")).size() > 0) {
                webDriver.findElement(By.cssSelector("[placeholder='Listing Number']")).clear();
                webDriver.findElement(By.cssSelector("[placeholder='Listing Number']")).sendKeys(String.valueOf(number + i));
                seleniumActions.clickOnElement(addButton);
            } else {
                break;
            }
        }
    }

    public void clearBoardStatusFilter() {
        seleniumWaits.untilElementIsClickable(boardStatusFilter);
        seleniumActions.moveToElementAndClick(boardStatusFilter);
        seleniumWaits.untilElementIsVisible(boardStatusFilterYesCheckbox);
        seleniumActions.moveToElementAndClick(boardStatusFilterYesCheckbox);
    }

    public void clearValidFromFilter() {
        seleniumActions.clickOnElement(validFromArrow);
        seleniumWaits.untilElementIsClickable(clearDateButton);
        seleniumActions.clickOnElement(clearDateButton);
        commonPage.waitForDomReady();
        try{
            seleniumActions.isElementDisplayed(clearDateButton);
            webDriver.navigate().refresh();
            seleniumWaits.untilElementIsClickable(validFromArrow);
        } catch (Exception e) {
            Assert.fail("Failed while refreshing the page and validating the element: " + e.getMessage());
        }
    }

    public String getCrewProfileText() {
        String crewProfileText = seleniumActions.getTextMessage(webDriver.findElement(profileText));
        logger.info("Crew Profile user Name : " + crewProfileText);
        return crewProfileText;
    }

    public void clickingTheSafetyTile() {
        seleniumWaits.untilElementIsVisible(webDriver.findElement((safetyButton)));
        seleniumActions.scrollInToElement(webDriver.findElement((safetyButton)));
        seleniumActions.clickOnElement(safetyButton);
    }

    public void userSafetyNumberValidation() {
        try {
            seleniumWaits.untilElementIsVisible(webDriver.findElement(safetyNumbers));
            seleniumActions.scrollInToElement(webDriver.findElement(safetyNumbers));
            String safetyNumberText = seleniumActions.getTextMessage(webDriver.findElement(safetyNumbers));
            logger.info("Safety Number Text: " + safetyNumberText);
            if (safetyNumberText == null || safetyNumberText.isEmpty()) {
                throw new RuntimeException("No data available.");
            }
            Assert.assertTrue("Safety number is not numeric  " + safetyNumberText, safetyNumberText.matches("\\d+"));
            logger.info("Safety number is valid  " + safetyNumberText);
        } catch (NoSuchElementException e) {
            logger.error("Element not found: " + e.getMessage());
        } catch (TimeoutException e) {
            logger.error("Element not visible in time: " + e.getMessage());
        }
    }

    /*public void changingTheFirstAndLastNames() {
        seleniumActions.clearValue(firstNameInputField);
        String firstName = GuestProfileConstants.GUEST_First_NAME + commonFunctions.getRandomCharacter();
        seleniumActions.typeValue(firstNameInputField, firstName);
        logger.info(firstName);
        seleniumActions.clearValue(lastNameInputField);
        String lastName = GuestProfileConstants.LAST_NAME + commonFunctions.getRandomCharacter();
        seleniumActions.typeValue(lastNameInputField, lastName);
        logger.info(lastName);
        seleniumActions.scrollInToElement(webDriver.findElement(updateButton));
        seleniumActions.highLighterMethod(webDriver.findElement(updateButton));
        seleniumActions.clickOnElement(updateButton);
    }*/

/*    public void updatedProfileData() {
        commonPage.waitForDomReady();
        String actualText = getCrewProfileText();
        logger.info("actual Text: " + actualText);
        changingTheFirstAndLastNames();
        try {
            seleniumWaits.untilElementIsVisible(webDriver.findElement(successPopUp));
            logger.info("Success popup is displayed.");
        } catch (TimeoutException e) {
            seleniumWaits.untilElementInVisible(webDriver.findElement(successPopUp));
            logger.info("Success popup has disappeared.");
        }
        webDriver.navigate().refresh();
        seleniumWaits.untilElementIsVisible(webDriver.findElement(profileText));
        seleniumActions.scrollInToElement(webDriver.findElement(profileText));
        String UdatecrewProfileText = seleniumActions.getTextMessage(webDriver.findElement(profileText));
        logger.info("UdatecrewProfileText Text: " + UdatecrewProfileText);
        Assert.assertTrue("Texts should be different!", !actualText.equals(UdatecrewProfileText));
    }*/

    public void clickPrintSafetyCardButton() {
        List<WebElement> checkboxes = webDriver.findElements(assignedCheckBoxes);
        logger.info("Total checkboxes found: " + checkboxes.size());
        try {
            if (!checkboxes.isEmpty()) {
                wait.until(ExpectedConditions.elementToBeClickable(checkboxes.get(0)));
                seleniumActions.highLighterMethod(checkboxes.get(0));
                checkboxes.get(0).click();
            }
            seleniumActions.interactWithElementByXpath(printSafetyCardButton, false);
            seleniumActions.highLighterMethod(webDriver.findElement(By.xpath(printSafetyCardButton)));
        } catch (Exception ex) {
            logger.error("No checkboxes found" + ex);
        }
    }

    public void clickLastNameOfRequiredGuest() {
        commonPage.waitForDomReady();
        try {
            List<WebElement> lastNameCells = webDriver.findElements(lastName);

            int count = lastNameCells.size();
            logger.info("Total 'Last Name' elements found: " + count);

            if (count > 0) {
                WebElement firstElement = lastNameCells.get(0);
                try {
                    seleniumActions.highLighterMethod(firstElement);
                    firstElement.click();
                    logger.info("Clicked the first 'Last Name' element successfully.");
                } catch (ElementClickInterceptedException e) {
                    logger.error("Click was intercepted: " + e.getMessage());
                } catch (Exception e) {
                    logger.error("Unexpected error while clicking: " + e.getMessage());
                }
            } else {
                logger.error("No matching elements found.");
            }

        } catch (NoSuchElementException e) {
            logger.error("XPath did not match any elements.");
        }
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(profileText)));
    }

    public String textValidation(String element) {
        return "//div[@aria-selected='false']//div[text()='" + element + "']";
    }

    public void goToSpecialNeedsSection(String element) {
        String elementXpath = textValidation(element);
        seleniumActions.interactWithElementByXpath(elementXpath, true);
    }

    private String saftyAssignmentSection(String elementName) {
        return "//div[(@aria-selected='false' or @aria-selected='true') and @aria-disabled='false']//div[text()='" + elementName + "']";
    }

    public void currentSafetyAssignmentSection(String element) {
        WebElement sectionElement = webDriver.findElement(By.xpath(saftyAssignmentSection(element)));
        seleniumActions.scrollInToElement(sectionElement);
        seleniumActions.highLighterMethod(sectionElement);
        seleniumActions.clickOnElement(sectionElement);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    private String sliderElement(String elementName) {
        return "//form[@autocomplete='off']//*[contains(normalize-space(text()), '" + elementName + "')]";
    }

    public void navigateToCabinInfoSection(String element) {
        String elementXpath = sliderElement(element);
        seleniumActions.interactWithElementByXpath(elementXpath, true);
    }

    public void fillingTheRequiredFields(boolean doSelectTime, boolean doToggleCheckbox) {

        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_CATEGORY_TEXT, SafetyManagementConstatnts.ACTIVITY_CATEGORY_SAFETY);
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_TYPE_TEXT, SafetyManagementConstatnts.ACTIVITY_TYPE_INDEX);
        if (doSelectTime) {
            seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_TYPE_ACTIVITY_TIME, SafetyManagementConstatnts.ACTIVITY_TYPE_TIME);
            logger.info("Activity time selected.");
        } else {
            logger.info("Skipping Activity Time selection.");
        }

        if (doToggleCheckbox) {
            try {
                seleniumActions.clickOnElement(webDriver.findElement(toogleCheckBoxAddActivity));
                logger.info("Checkbox was checked. Clicked to uncheck it.");
            } catch (NoSuchElementException e) {
                logger.info("Checkbox is already unchecked. No action taken.");
            }
        } else {
            logger.info("Skipping checkbox toggle action.");
        }
        seleniumWaits.untilElementIsVisible(webDriver.findElement(nextButton));
        seleniumActions.highLighterMethod(webDriver.findElement(nextButton));
        seleniumActions.clickOnElement(nextButton);

        seleniumWaits.untilElementIsVisible(webDriver.findElement(selectParticipants));
        seleniumActions.highLighterMethod(webDriver.findElement(selectParticipants));
        seleniumActions.jsClick(webDriver.findElement(selectParticipants));
    }

    public void navigatesToTheActivitiesOverviewSubModuleAndFillsInTheRequiredFields() {
        seleniumActions.switchToOpenedWindow();
        seleniumWaits.untilElementIsVisible(webDriver.findElement(calendarViewHeader));
        try {
            seleniumActions.jsClick(webDriver.findElement(addActivityBtn));
            logger.info("calendar view header is displayed");
        } catch (Exception ex) {
            if (webDriver.findElement(addActivityBtn).isDisplayed()) {
                seleniumActions.jsClick(webDriver.findElement(addActivityBtn));
            }
        }
        fillingTheRequiredFields(true, true);
        String elementXpath = filterName(SafetyManagementConstatnts.ACTIVITY_TYPE_ACCOUNT_TYPE);
        seleniumActions.interactWithElementByXpath(elementXpath, true);
        String statuselement = status(SafetyManagementConstatnts.ACTIVITY_TYPE_CREW);
        seleniumActions.interactWithElementByXpath(statuselement, true);
        pageloaded();
        String name = firstCheckboxAndGetName();
        clickFinalButton();
        seleniumActions.jsClick(finishButtonBox);

        webDriver.navigate().refresh();

        seleniumWaits.untilElementIsVisible(addActivity);
        logger.info("calendar view header is displayed");
        try {
            seleniumActions.jsClick(webDriver.findElement(addActivityBtn));
            logger.info("calendar view header is displayed");
        } catch (Exception ex) {
            if (webDriver.findElement(addActivityBtn).isDisplayed()) {
                seleniumActions.interactWithElementByXpath(addActivityButton, true);
            }
        }
        fillingTheRequiredFields(true, true);
        seleniumActions.typeValue(search, name);
        firstCheckboxAndGetName();
        clickFinalButton();
    }

    public void clickFinalButton() {
        seleniumWaits.untilElementIsClickable(webDriver.findElement(selectedAddbutton));
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.jsClick(webDriver.findElement(selectedAddbutton));

        logger.info("Clicked addButtonBox");
        seleniumActions.highLighterMethod(addButtonBox);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.jsClick(addButtonBox);
        try {
            new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.MINI_WAIT))
                    .until(ExpectedConditions.visibilityOfElementLocated(successToast));

            logger.info("Success toast is visible");

        } catch (TimeoutException ex) {

            new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.MINI_WAIT))
                    .until(ExpectedConditions.invisibilityOfElementLocated(successToast));

            logger.info("Success toast disappeared");
        }
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public String firstCheckboxAndGetName() {
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(checkBoxRows)));
        List<WebElement> rows = webDriver.findElements(checkBoxRows);

        logger.info("Number of 'Select row' elements: " + rows.size());
        if (!rows.isEmpty()) {
            WebElement firstRow = rows.get(0);
            seleniumActions.highLighterMethod(firstRow);
            firstRow.click();
            logger.info("Clicked the first 'Select row' element.");
        } else {
            logger.error("No 'Select row' elements found.");
        }

        List<WebElement> nameElements = webDriver.findElements(checkBoxSelectedName);

        if (!nameElements.isEmpty()) {
            String selectedName = nameElements.get(0).getText().trim();
            logger.info("Selected name: " + selectedName);
            return selectedName;
        } else {
            logger.warn("No elements found for locator: " + checkBoxSelectedName);
            return null;
        }
    }

    public void userIsAbleToSeeTheInvalidMessage() {
        try {
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumWaits.untilElementIsPresent(warningMessage);
            seleniumActions.highLighterMethod(webDriver.findElement(warningMessage));
            WebElement warningMessageElement = webDriver.findElement(warningMessage);

            String actualMessage = warningMessageElement.getText().trim();
            String expectedCoreMessage = "already scheduled for another activity for this date and time frame.";

            if (actualMessage.contains(expectedCoreMessage)) {
                logger.info(" Warning message displayed correctly: " + actualMessage);
            } else {
                logger.error(" Warning message mismatch. Expected to contain: "
                        + expectedCoreMessage + " | Actual: " + actualMessage);
            }

        } catch (TimeoutException e) {
            logger.error(" Warning message element was not found on the page.");
        }
    }

    public void userAbleClickOnTheADDACTIVITYButton() {
        seleniumActions.switchToOpenedWindow();
        seleniumWaits.untilElementIsVisible(webDriver.findElement(calendarViewHeader));
        seleniumActions.interactWithElementByXpath(addActivityButton, true);
        logger.info("calendar view header is displayed");
    }

    public void userAbleClickOnTheInPortManningGroupsDropdownAndSelectTheRequiredGroup() {

        String elementXpath = filterName(SafetyManagementConstatnts.ACTIVITY_TYPE_ACCOUNT_TYPE);
        seleniumActions.interactWithElementByXpath(elementXpath, true);
        String statuselement = status(SafetyManagementConstatnts.ACTIVITY_TYPE_CREW);
        seleniumActions.interactWithElementByXpath(statuselement, true);
        pageloaded();

        String element = status(SafetyManagementConstatnts.ACTIVITY_TYPE_IN_PORT_MANNING_GROUPS);
        seleniumActions.interactWithElementByXpath(element, true);
        String status = status(SafetyManagementConstatnts.ACTIVITY_TYPE_GREEN);
        seleniumActions.interactWithElementByXpath(status, true);
        pageloaded();
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(checkBoxRows)));
        List<WebElement> rows = webDriver.findElements(checkBoxRows);

        logger.info("Number of 'Select row' elements: " + rows.size());
        if (!rows.isEmpty()) {
            WebElement firstRow = rows.get(0);
            firstRow.click();
            logger.info("Clicked the first 'Select row' element.");
        } else {
            logger.error("No 'Select row' elements found.");
        }
    }

    public void clickOnTheRequiredCrewCheckboxesAndClickOnThreeVerticalDots() {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        seleniumActions.switchToOpenedWindow();
        if (!webDriver.findElements(checkBoxRows).isEmpty() && webDriver.findElement(checkBoxRows).isDisplayed()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(checkBoxRows));
            logger.info("CrewName list is visible.");
            List<WebElement> rows = webDriver.findElements(checkBoxRows);
            logger.info("Number of 'Select row' elements: " + rows.size());

            if (!rows.isEmpty()) {
                WebElement firstRow = rows.get(0);
                seleniumActions.highLighterMethod(firstRow);
                firstRow.click();
                logger.info("Clicked the first 'Select row' element.");
            } else {
                logger.error("No 'Select row' elements found.");
            }

            seleniumActions.jsClick(webDriver.findElement(threeVerticalDots));
        } else {
            logger.info("CrewName element not found, Need to add the crew member.");
            seleniumActions.interactWithElementByXpath(addActivityButton, true);
            logger.info("calendar view header is displayed");
            fillingTheRequiredFields(false, false);
            changeStatusAndFilter("Account Type", "Crew");
            wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(checkBoxRows)));
            List<WebElement> rows = webDriver.findElements(checkBoxRows);

            logger.info("Number of 'Select row' elements: " + rows.size());
            if (!rows.isEmpty()) {
                WebElement firstRow = rows.get(0);
                seleniumActions.highLighterMethod(firstRow);
                firstRow.click();
                logger.info("Clicked the first 'Select row' element.");
            } else {
                logger.error("No 'Select row' elements found.");
            }
            seleniumActions.jsClick(webDriver.findElement(threeVerticalDots));
        }

    }

    private String filterName(String filterName) {
        return "//div[contains(text(),'" + filterName + "')]/following-sibling::span/../../following-sibling::icon/*[name()='svg']";
    }

    private String status(String status) {
        return "//span[normalize-space()='" + status + "']/..//preceding-sibling::mat-pseudo-checkbox";
    }

    public void pageloaded() {
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
    }

    public void changeStatusAndFilter(String filterName, String status) {
        String elementXpath = filterName(filterName);
        seleniumActions.interactWithElementByXpath(elementXpath, true);
        String statuselement = status(status);
        seleniumActions.interactWithElementByXpath(statuselement, true);
        pageloaded();
        String name = firstCheckboxAndGetName();
        clickFinalButton();
        seleniumActions.jsClick(finishButtonBox);
    }

    public String dropdownText(String name) {
        return String.format("//button[@role='menuitem' and contains(text(),'%s')]", name);
    }

    public void selectingDropdownExpectedText(String Name) {
        By locator = By.xpath(dropdownText(Name));
        try {
            WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(locator));
            finishBtn.click();
        } catch (Exception e) {
            seleniumActions.jsClick(webDriver.findElement(locator));
        }
    }

    public void confirmPopUpAndClickTheConfirmButton() {
        seleniumWaits.untilElementIsVisible(webDriver.findElement(confirmationBox));
        seleniumActions.highLighterMethod(webDriver.findElement(confirmationBox));
        try {
            if (!webDriver.findElements(commentstextarea).isEmpty() && webDriver.findElements(commentstextarea).get(0).isDisplayed()) {

                seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_TYPE_ABSENCEREASON, SafetyManagementConstatnts.ACTIVITY_TYPE_INPORTMANNING);
            }
        } catch (Exception ex) {
            logger.info("Comment box not is displayed");
        }
        seleniumActions.jsClick(webDriver.findElement(confirmButtonBox));
        logger.info("Confirm button clicked");

    }

    private List<WebElement> getFilterButtonElements() {
        return webDriver.findElements(assemblyStationBox);
    }

    public void clickOnAddMoreFiltersIcon() {
        List<WebElement> filterButtonElements = getFilterButtonElements();

        if (filterButtonElements.isEmpty()) {
            WebElement addMoreFilters = wait.until(ExpectedConditions.elementToBeClickable(addMoreFiltersIcon));
            addMoreFilters.click();
            logger.info("Clicked on 'Add More Filters' icon as no filter button was detected.");

            String statusElement = status(SafetyManagementConstatnts.ACTIVITY_TYPE_ASSEMBLY_STATION);
            seleniumActions.interactWithElementByXpath(statusElement, true);
            pageloaded();
        }
    }

    public void isAbleToFilterAssemblyStation() {
        List<WebElement> filterButtonElements = getFilterButtonElements();
        if (!filterButtonElements.isEmpty()) {
            seleniumActions.highLighterMethod(filterButtonElements.get(0));
            logger.info(SafetyManagementConstatnts.ACTIVITY_TYPE_ASSEMBLY_STATION + " filter button element found.");
        } else {
            logger.error("No " + SafetyManagementConstatnts.ACTIVITY_TYPE_ASSEMBLY_STATION + " filter button element found.");
        }
    }

    public void scrollDownToSeeSpecificSafetyInstructionTab() {
        try {
            commonPage.waitForDomReady();
            seleniumWaits.untilElementIsPresent(safetyPlanEmergencyTextBox);
            WebElement safetyElement = this.webDriver.findElement(safetyPlanEmergencyTextBox);
            if (safetyElement.isDisplayed()) {
                seleniumActions.highLighterMethod(safetyElement);
                logger.info("Safety Instruction is visible.");
            }
        } catch (TimeoutException e) {
            logger.info("Safety Instruction not visible. Expanding Emergency Plan...");
            seleniumWaits.untilElementIsVisible(webDriver.findElement(emergencyPlan));
            seleniumActions.clickOnElement(emergencyPlan);
            seleniumWaits.untilElementIsPresent(safetyPlanEmergencyTextBox);
            logger.info("Safety Instruction is now visible.");
        }
    }

    public void enterMaximumLimitOfCharsInInstructionsField() {
        commonPage.waitForDomReady();
        seleniumWaits.untilElementIsVisible(webDriver.findElement(safetyPlanEmergencyTextBox));
        String characters = seleniumActions.getRandomString(SafetyManagementConstatnts.MAX_CHARACTER);
        seleniumActions.sendKeys(webDriver.findElement(safetyPlanEmergencyTextBox), characters);
    }

    public void userClicksTheSaveButtonAndAPopUpAppears() {
        seleniumWaits.untilElementIsVisible(webDriver.findElement(safetyPlanEmergencySaveButton));
        seleniumActions.highLighterMethod(webDriver.findElement(safetyPlanEmergencySaveButton));
        seleniumActions.clickOnElement(safetyPlanEmergencySaveButton);
        try {
            seleniumWaits.untilElementIsVisible(webDriver.findElement(successPopUp));
            logger.info("Success popup is displayed.");
        } catch (TimeoutException e) {
            seleniumWaits.untilElementInVisible(webDriver.findElement(successPopUp));
            logger.info("Success popup has disappeared.");
        }
    }

    public void clickOnTheRequiredSafetyNumberLink() {
        commonPage.waitForDomReady();
        List<WebElement> elements = webDriver.findElements(safetyNumberElement);
        logger.info("Element count: " + elements.size());
        if (!elements.isEmpty()) {
            elements.get(0).click();
        }
    }

/*    public String getFirstRowBySectionText(String sectionText) {
        return String.format("//mat-tab-group[.//div[normalize-space()='%s']]//tr[@aria-rowindex='1']", sectionText);
    }*/

    public String modulePageValidation(String link) {
        return String.format("//*[normalize-space(text())='%s' and contains(@id,'breadcrumbs') or normalize-space(text())='%s']", link, link);
    }

    public void landingPageShouldBeDisplayed(String modulePage) {
        commonPage.waitForDomReady();
        By locator = By.xpath(modulePageValidation(modulePage));
        seleniumWaits.untilElementIsPresent(locator);
        WebElement element = webDriver.findElement(locator);
        seleniumActions.highLighterMethod(element);
        String actualText = element.getText().trim();
        logger.info("Navigated to " + actualText + " page");
        Assert.assertEquals("Landing page text does not match!", modulePage, actualText);
    }

    public void CrewMemberProfileShouldBeDisplayed() {
        seleniumWaits.untilElementIsPresent(profilePage);
        seleniumActions.highLighterMethod(webDriver.findElement(profilePage));
        List<WebElement> elements = webDriver.findElements(profilePage);
        int count = elements.size();
        logger.info("ProfilePage element count: " + count);
        Assert.assertEquals("Expected exactly 1 profilePage element, but found " + count, 1, count);
    }

    public String tileTheDropDown(String tile) {
        return String.format("//mat-expansion-panel-header[.='%s']//ancestor::*[contains(@id,'crew-summary')]//descendant::mat-tab-header", tile);
    }

    public void tileShouldBeExpanded(String tile) {
        By locator = By.xpath(tileTheDropDown(tile));
        List<WebElement> elements = webDriver.findElements(locator);
        int count = elements.size();
        logger.info("ProfilePage element count: " + count);
        Assert.assertEquals("Expected exactly 1 profilePage element, but found " + count, 1, count);
    }

    public void userIsAbleToSearchForACrewMember(String crewName) {
        WebElement activeSearchField = getActiveSearchField();
        seleniumActions.typeValue(activeSearchField, crewName);
        pageloaded();
        commonPage.waitForDomReady();
    }

    private WebElement getActiveSearchField() {
        WebDriverWait shortWait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.MINI_WAIT));

        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(search));
            logger.debug(" Using ID selector");
            return search;
        } catch (Exception e) {
            logger.info("ID selector not available, trying class selector");
        }

        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(searchField));
            logger.debug("Using class selector");
            return searchField;
        } catch (Exception e) {
            logger.info("Class selector not available");
        }
        throw new NoSuchElementException("No clickable search field found");
    }

    public boolean checkingTheDataIsAdded() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.MINI_WAIT));
        seleniumActions.switchToOpenedWindow();
        if (!webDriver.findElements(checkBoxRows).isEmpty() && webDriver.findElement(checkBoxRows).isDisplayed()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(checkBoxRows));
            logger.info("CrewName list is visible.");
            List<WebElement> rows = webDriver.findElements(checkBoxRows);
            logger.info("Number of 'Select row' elements: " + rows.size());
            if (!rows.isEmpty()) {
                WebElement firstRow = rows.get(0);
                seleniumActions.highLighterMethod(firstRow);
                firstRow.click();
                logger.info("Clicked the first 'Select row' element.");
                return true;
            } else {
                logger.error("No 'Select row' elements found.");
                return false;
            }
        }
        return false;
    }

    public void validateTheFinishButton() {
        try {
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.jsClick(finishButtonBox);
        } catch (RuntimeException e) {
            Assert.fail("Failed to click on the Finish button. Exception: " + e.getMessage());
        }
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public void userAbleToSee(String Name) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.jsClick(webDriver.findElement(threeVerticalDots));
        By locator = By.xpath(dropdownText(Name));
        seleniumWaits.untilElementIsVisible(webDriver.findElement(locator));
        seleniumActions.highLighterMethod(webDriver.findElement(locator));
    }

    public void userAbleToViewTheConfirmPopUp() {
        List<WebElement> confirmationPopups = webDriver.findElements(confirmationBox);
        if (confirmationPopups.size() > 0) {
            WebElement confirmationPopup = confirmationPopups.get(0);
            seleniumWaits.untilElementIsVisible(webDriver.findElement(confirmationBox));
            seleniumActions.highLighterMethod(confirmationPopup);
            logger.info("Confirmation popup is displayed.");
        }
    }

    public void clickTheConfirmButton() {
        try {
            if (!webDriver.findElements(commentstextarea).isEmpty() && webDriver.findElements(commentstextarea).get(0).isDisplayed()) {
                seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_TYPE_ABSENCEREASON, SafetyManagementConstatnts.ACTIVITY_TYPE_INPORTMANNING);
            }
        } catch (Exception ex) {
            logger.info("Comment box not is displayed");
        }
        seleniumActions.jsClick(webDriver.findElement(confirmButtonBox));
        logger.info("Confirm button clicked");
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public void navigateToNewTab(String text) {
        seleniumActions.switchToOpenedWindow();
        landingPageShouldBeDisplayed(text);
    }

    public void userAbleClickOnTheAddActivityButton() {
        seleniumWaits.untilElementIsVisible(webDriver.findElement(calendarViewHeader));
        List<WebElement> visibleSafetyGuests = new ArrayList<>();

        for (WebElement guest : safetyGuestData) {
            if (guest.isDisplayed()) {
                visibleSafetyGuests.add(guest);
                seleniumActions.highLighterMethod(guest);
            }
        }

        try {
            seleniumActions.jsClick(webDriver.findElement(addActivityBtn));
            seleniumActions.highLighterMethod(webDriver.findElement(addActivityBtn));

            logger.info("Calendar view header is displayed");

        } catch (Exception ex) {

            if (webDriver.findElement(addActivityBtn).isDisplayed()) {
                seleniumActions.highLighterMethod(webDriver.findElement(addActivityBtn));
                seleniumActions.jsClick(webDriver.findElement(addActivityBtn));
            }
        }
        logger.info("calendar view header is displayed");
    }

    public boolean userIsAbleToConfirmTrainingData(String sectionText) {
        try {
            By sectionLocator = By.xpath(getFirstRowBySectionText(sectionText));
            WebElement element = webDriver.findElement(sectionLocator);
            seleniumWaits.untilElementIsVisible(element);
            seleniumActions.scrollInToElement(element);
            logger.info("TEXT found: " + sectionText);
            return true;
        } catch (Exception e1) {
            logger.info("Text not found, checking table column: " + e1.getMessage());
            try {
                WebElement column = webDriver.findElement(tablecolumn);
                seleniumWaits.untilElementIsVisible(column);
                seleniumActions.highLighterMethod(column);
                String columnValue = column.getText().trim();
                logger.info("NUMBER found: " + columnValue);

                return (columnValue != null && !columnValue.isEmpty() &&
                        !columnValue.equals("0") && !columnValue.equals("-"));
            } catch (Exception e2) {
                logger.error("Table column FAILED: " + e2.getMessage());
            }
        }
        logger.error("BOTH TEXT & NUMBER validations FAILED");
        return false;
    }

    public void userIsAbleToSelectTheCrewInTheDropdown() {
        commonPage.waitForDomReady();
        String currentAccountType = seleniumActions.getTextMessage(accountType);
        if (currentAccountType.contains("Crew")) {
            logger.info("Account type is already set to Crew");
            return;
        }
        logger.info("Updating account type to Crew...");
        seleniumActions.interactWithElementByXpath(filterName("Account Type"), true);
        seleniumActions.interactWithElementByXpath(status("Crew"), true);
        pageloaded();
        commonPage.waitForDomReady();
        logger.info("Account type successfully updated to Crew");
    }

    public void userAbleSeeAllTheCrewDetailInTheTable() {
        commonPage.waitForDomReady();
        List<WebElement> crewDetails = webDriver.findElements(crewTabledetails);
        String firstCrewText = crewDetails.get(0).getText();
        commonPage.waitForDomReady();
        logger.info(firstCrewText.trim());
        Assert.assertEquals("Validation failed: Expected 'Crew' but found: ", firstCrewText.trim(), "Crew");
        logger.info("Assertion passed: First crew detail is correctly set to Crew");
    }

    public void userAbleToClickInTheDetailTable(String locatorName) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        By locator;
        switch (locatorName) {
            case "checkBox":
                locator = checkBoxRows;
                break;
            case "lastName":
                locator = lastName;
                break;
            default:
                throw new IllegalArgumentException("Unknown locator: " + locatorName);
        }
        List<WebElement> elements = webDriver.findElements(locator);
        if (!elements.isEmpty()) {
            try {
                seleniumActions.jsClick(elements.get(0));
                commonPage.waitForDomReady();
                seleniumActions.sleep(SafetyManagementConstatnts.MINIMUM_PAGE_LOAD_WAIT);
            } catch (Exception e) {
                if (elements.size() > 1) {
                    seleniumActions.jsClick(elements.get(1));
                } else {
                    throw new RuntimeException("No clickable element found for locator: " + locatorName);
                }
            }
        } else {
            throw new RuntimeException("No elements found for locator: " + locatorName);
        }
    }

    public String lastNameText() {
        seleniumActions.scrollInToElement(webDriver.findElement(profileText));
        String actualText = getCrewProfileText();
        logger.info("actual Text: " + actualText);

        String[] parts = actualText.trim().split("\\s+");
        String lastName = parts[parts.length - 1];

        logger.info("Last Name: " + lastName);
        return lastName;
    }

    public String leftpanel(String moduleName) {
        return String.format("//a//div[normalize-space(text())='%s']", moduleName);
    }

    public void navigationToNewModule(String moduleName) {
        WebElement locator = webDriver.findElement(By.xpath(leftpanel(moduleName)));
        seleniumWaits.untilElementIsVisible(locator);
        seleniumActions.clickOnElement(locator);
        commonPage.waitForDomReady();
    }

    public void refreshingAndNavigatingpreviewsWindow() {
        commonPage.switchBackToOriginalTab();
        webDriver.navigate().refresh();
        commonPage.waitForDomReady();
    }

    public void userIsAbleValidateTheCount() {
        WebElement element = webDriver.findElement(count);
        seleniumWaits.untilElementIsVisible(element);
        String actualText = element.getText().trim();
        logger.info("Captured text: " + actualText);
        if (actualText.contains("1 / 0")) {
            logger.info("Text updated to 1 / 0");
        } else {
            logger.error("Expected 1 / 0 but found " + actualText);
        }
        Assert.assertTrue("Expected '1 / 0' but found: " + actualText, actualText.contains("1 / 0"));
    }

    private String dropdownSelector(String dropdownName) {
        return String.format("//div[contains(text(),'%s')]/../descendant::div[text()][contains(@class,'text-truncate')]", dropdownName);
    }

    private String cellSelector(int columnIndex) {
        return String.format("//td[@aria-colindex='%d' and not(@role='columnheader')]", columnIndex);
    }

    public void userIsAbleToSelectTheInTheDropdown(String selectedName, String dropdownName) {
        commonPage.waitForDomReady();
        String xpath = dropdownSelector(dropdownName);
        String currentAccountType = seleniumActions.getTextMessage(webDriver.findElement(By.xpath(xpath)));

        if (currentAccountType.contains(selectedName)) {
            logger.info(selectedName + " is already set to " + dropdownName);
            return;
        }
        logger.info("Updating account type to Crew...");
        seleniumActions.interactWithElementByXpath(filterName(dropdownName), true);
        seleniumActions.interactWithElementByXpath(status(selectedName), true);
        pageloaded();
        commonPage.waitForDomReady();
        logger.info(selectedName + " is successfully updated to " + dropdownName);
    }

    private By parentTdLocator() {
        return By.xpath("./ancestor::td[@role='columnheader']");
    }


    public void userAbleSeeTheColumnDataAs(String headerText, String expectedCellText) {
        List<WebElement> headers = webDriver.findElements(columnheader);

        int columnIndex = -1;
        for (WebElement header : headers) {
            if (header.getText().trim().equalsIgnoreCase(headerText)) {
                WebElement parentTd = header.findElement(parentTdLocator());
                columnIndex = Integer.parseInt(parentTd.getAttribute("aria-colindex"));
                break;
            }
        }

        if (columnIndex == -1) {
            throw new IllegalStateException("Header with text '" + headerText + "' not found.");
        }

        String cellXpath = cellSelector(columnIndex);
        List<WebElement> cells = webDriver.findElements(By.xpath(cellXpath));

        if (cells.isEmpty()) {
            throw new IllegalStateException("No cells found in column index " + columnIndex);
        }
        String actualCellText = cells.get(0).getText().trim();
        if (actualCellText.equalsIgnoreCase(expectedCellText)) {
            logger.info("First cell text matches '" + expectedCellText + "'");
        } else {
            logger.error("Expected '" + expectedCellText + "', but found '" + actualCellText + "'");
        }
    }

    public void userIsAbleToSeeTheDisembarkedGuestCount() {
        WebElement infoElement = webDriver.findElement(guestCount);
        String infoText = infoElement.getText().trim();
        if (infoText.isEmpty()) {
            throw new IllegalStateException("Pagination info text is empty!");
        }
        if (!infoText.startsWith("Page 1 of")) {
            throw new IllegalStateException("Pagination info does not start with 'Page 1 of'. Found: " + infoText);
        }
        if (!infoText.contains("items")) {
            throw new IllegalStateException("Pagination info does not contain 'items'. Found: " + infoText);
        }
        logger.info("Pagination info validated successfully: " + infoText);
    }

    public void userIsAbleValidateTheCountCrewMemberDrillAbsences() {
        WebElement element = webDriver.findElement(count);
        seleniumWaits.untilElementIsVisible(element);
        String actualText = element.getText().trim();
        logger.info("Captured text: " + actualText);
    }

    public int[] getCounts() {
        WebElement element = webDriver.findElement(count);
        seleniumWaits.untilElementIsVisible(element);
        String actualText = element.getText().trim();

        Matcher matcher = Pattern.compile("\\d+").matcher(actualText);
        List<Integer> numbers = new ArrayList<>();
        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }

        if (numbers.size() < 2) {
            throw new IllegalStateException("Could not parse both Attendees and Absent counts from: " + actualText);
        }

        int attendees = numbers.get(0);
        int absent = numbers.get(1);

        logger.info("Attendees count : " + attendees);
        logger.info("Absent count : " + absent);

        return new int[]{attendees, absent};
    }

    public void clickingTheThreeDotsElement() {
        try {
            seleniumWaits.untilElementIsVisible(webDriver.findElement(threeVerticalDots));
            seleniumActions.jsClick(webDriver.findElement(threeVerticalDots));
        } catch (Exception e) {
            if (!webDriver.findElements(checkBoxRows).isEmpty() && webDriver.findElement(checkBoxRows).isDisplayed()) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(checkBoxRows));
                logger.info("CrewName list is visible.");
                List<WebElement> rows = webDriver.findElements(checkBoxRows);
                logger.info("Number of 'Select row' elements: " + rows.size());
                if (!rows.isEmpty()) {
                    WebElement firstRow = rows.get(0);
                    seleniumActions.highLighterMethod(firstRow);
                    firstRow.click();
                    logger.info("Clicked the first 'Select row' element.");
                } else {
                    logger.error("No 'Select row' elements found.");
                }
            }
            seleniumActions.jsClick(webDriver.findElement(threeVerticalDots));
        }
    }

    public void cancelButton() {
        try {
            seleniumWaits.untilElementIsVisible(webDriver.findElement(cancelButton));
            seleniumActions.jsClick(webDriver.findElement(cancelButton));
        } catch (Exception m) {
            logger.info("cancelButton is not clicked");
        }
    }

    public void userAbleToSelectLocation(String Str) {
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.LOCATION, Str);
    }

    public void userIsAbleToVerifyThatTheCONFIRMButtonIsEnabledAndThenClickIt() {
        WebElement confirmButton = webDriver.findElement(primaryEnable);
        seleniumWaits.untilElementIsVisible(confirmButton);
        if (confirmButton.isDisplayed() && confirmButton.isEnabled()) {
            logger.info("Confirm button is enabled.");
        } else {
            logger.error("Confirm button is not enabled or not visible.");
        }
        seleniumActions.jsClick(submitButton);
    }

    public void userAbleSeeTheConfirmPopupIsDisplay() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.MINI_WAIT));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
            logger.info("Success toast is visible.");
        } catch (TimeoutException ex) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(successToast));
            logger.info("Success toast disappeared.");
        }
    }
    public void userAbleToCheckForThePassengerAndCrewCount() {
        try {
            seleniumWaits.untilElementIsVisible(webDriver.findElement(counts));
            seleniumActions.jsClick(webDriver.findElement(counts));
        } catch (Exception e) {

        }
        if (commonPage.untilElementIsVisible(webDriver.findElement(countGangwayHeader))) {
            logger.info("count is page is visible");
        } else {
            logger.error("count is page is not visible");
        }
        commonPage.waitForDomReady();
    }

    public void userClicksTheSelectAllCheckboxThenAllParticipantsShouldBeSelected() {
        List<WebElement> selectAllElements = webDriver.findElements(crewSelectAllCheckBox);
        if (!selectAllElements.isEmpty()) {
            WebElement selectAllCheckbox = selectAllElements.get(0);
            selectAllCheckbox.click();
            logger.info("Select All checkbox clicked to select all crew members.");
        } else {
            logger.info("All crew members are already selected.");
        }
    }

    public void userAbleClickOnTheActivityCategoryFieldAndSelectAsSafetyAndTime() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_CATEGORY_TEXT, SafetyManagementConstatnts.ACTIVITY_CATEGORY_SAFETY);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_TYPE_TEXT, SafetyManagementConstatnts.ACTIVITY_TYPE_INDEX);
        //seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_TYPE_ACTIVITY_TIME, SafetyManagementConstatnts.ACTIVITY_TYPE_TIME);
        //seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        logger.info("Activity time selected.");
    }

    public void userOFFTheOverlapToggle() {
        try {
            seleniumActions.clickOnElement(webDriver.findElement(toogleCheckBoxAddActivity));
            logger.info("Checkbox was checked. Clicked to uncheck it.");
        } catch (NoSuchElementException e) {
            logger.info("Checkbox is already unchecked. No action taken.");
        }
    }
    public void clickOnTheADDACTIVITYButtonOnSameDate(){
        webDriver.navigate().refresh();

        seleniumWaits.untilElementIsVisible(addActivity);
        logger.info("calendar view header is displayed");
        try {
            seleniumActions.jsClick(webDriver.findElement(addActivityBtn));
            logger.info("calendar view header is displayed");
        } catch (Exception ex) {
            if (webDriver.findElement(addActivityBtn).isDisplayed()) {
                seleniumActions.interactWithElementByXpath(addActivityButton, true);
            }
        }
    }
    public void userClickOnNEXTButton(){
        seleniumActions.clickOnElement(nextButton);
    }
    public void userClickOnSkip(){
        seleniumWaits.untilElementIsVisible(webDriver.findElement(selectParticipants));
        seleniumActions.highLighterMethod(webDriver.findElement(selectParticipants));
        seleniumActions.jsClick(webDriver.findElement(selectParticipants));
    }
    public void userFillsGeneralInformationDetails(){
        seleniumActions.clickOnElement(safetyPlanAddButton);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        SafetyManagementConstatnts._SAFETY_NUMBER = String.valueOf(getRandomNumber(GlobalConstants.INITIAL_NUMBER, GlobalConstants.END_NUMBER));
        commonPage.typeValueInPlaceHolderCssInput(SafetyManagementConstatnts.SAFTETY_NUMBER, SafetyManagementConstatnts._SAFETY_NUMBER);
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ASSEMBBLY_STATION,SafetyManagementConstatnts.ACTIVITY_TYPE_INDEX);
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.LIFEBOAT_RAFT,SafetyManagementConstatnts.ACTIVITY_TYPE_INDEX);
    }
    public List<String> getGuestDetailsFromTheTable(String data) {

        List<WebElement> tableData = List.of();
        List<String> rowData = new ArrayList<>();

        switch (data) {

            case "By Safety Number":
                tableData = webDriver.findElements(bySafetyNumberLog);
                break;

            default:
                throw new AssertionError("Invalid table type passed: " + data);
        }

        for (WebElement element : tableData) {
            rowData.add(seleniumActions.getTextMessage(element));
        }

        return rowData;
    }
    public boolean searchForUnassignedNumber(String text) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumWaits.untilElementIsVisible(activeModeSearchBar);
        activeModeSearchBar.clear();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        activeModeSearchBar.sendKeys(text);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        boolean isDisplayed = webDriver.findElements(noDataText).size() > 0;
        if (isDisplayed) {
            seleniumActions.highLighterMethod(webDriver.findElement(noDataText));
        }
        return isDisplayed;
    }
    public String getCrewNameText() {
        WebElement crewElement = webDriver.findElement(CrewName);
        seleniumWaits.untilElementIsVisible(crewElement);
        return crewElement.getText().trim();
    }
    public void clickCrewName() {
        seleniumWaits.untilElementIsVisible(webDriver.findElement(CrewName));
        seleniumActions.highLighterMethod(webDriver.findElement(CrewName));
        seleniumActions.jsClick(webDriver.findElement(CrewName));
        logger.info("Clicking the crew name");
    }
    public String getProfileName() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement element = webDriver.findElement(profileText);
        seleniumWaits.untilElementIsVisible(element);
        seleniumActions.scrollInToElement(element);
        seleniumActions.highLighterMethod(element);
        seleniumWaits.untilElementIsVisible(element);
        return element.getText().trim();
    }
    public void changeFirstAndLastNames() {

        String newFirstName = GuestProfileConstants.GUEST_First_NAME + commonFunctions.getRandomCharacter();
        String newLastName  = GuestProfileConstants.LAST_NAME + commonFunctions.getRandomCharacter();
        updateTextField(firstNameInputField, newFirstName);
        logger.info("Updated First Name: {}", newFirstName);
        updateTextField(lastNameInputField, newLastName);
        logger.info("Updated Last Name: {}", newLastName);

        WebElement updateBtn = webDriver.findElement(updateButton);
        seleniumActions.highLighterMethod(updateBtn);
        seleniumActions.clickOnElement(updateButton);

        logger.info("Clicked Update button to save the changes");
    }
    private void updateTextField(WebElement locator, String value) {
        seleniumActions.clearValue(locator);
        seleniumActions.typeValue(locator, value);
    }
    public boolean cewMemberSearching(String text){
        text = text.trim();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement element= webDriver.findElement(crewSearchBar);
        seleniumWaits.untilElementIsVisible(element);
        element.clear();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        element.sendKeys(text);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return true;
    }

    public boolean userViewCurrentSafteyAssignmentDetails(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.scrollInToElement(webDriver.findElement(currentSafteyAssignmentDataTab));
        seleniumWaits.untilElementIsVisible(webDriver.findElement(currentSafteyAssignmentDataTab));
        boolean isDisplayed = webDriver.findElements(currentSafteyAssignmentDataTab).size() > 0;
        if (isDisplayed) {
            seleniumActions.highLighterMethod(webDriver.findElement(currentSafteyAssignmentDataTab));
        } return isDisplayed;
    }

    public String getFirstRowBySectionText(String sectionText) {
        seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
        return String.format("//mat-tab-group[.//div[normalize-space()='%s']]//tr[@aria-rowindex='1']", sectionText);
    }

    public boolean isSectionFirstRowDisplayed(String sectionText) {
        By sectionRow = By.xpath(getFirstRowBySectionText(sectionText));

        List<WebElement> elements = webDriver.findElements(sectionRow);

        if (!elements.isEmpty()) {
            WebElement element = elements.get(0);
            seleniumWaits.untilElementIsVisible(element);
            seleniumActions.highLighterMethod(element);
            return true;
        }
        return false;
    }
    public By getDropdownOption(String optionText) {
        String xpath = String.format("//span[@class='mat-option-text' and contains(text(),'%s')]", optionText);
        return By.xpath(xpath);
    }
    public void userCreatesAnActivityForAnUpcomingDrillSession(){
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_CATEGORY_TEXT, SafetyManagementConstatnts.ACTIVITY_CATEGORY_SAFETY);
        try {
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.clickOnElement(webDriver.findElement(activityTypeDropDown));
            WebElement searchBox = webDriver.findElement(searchBar);
            searchBox.clear();
            searchBox.sendKeys(SafetyManagementConstatnts.SAFETY_DRILL);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(getDropdownOption(SafetyManagementConstatnts.SAFETY_DRILL)));
            option.click();

        } catch (Exception ex) {
            Assert.fail("Unable to select activity type: " + SafetyManagementConstatnts.SAFETY_DRILL);
        }
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.EMERGENCY_DRILL_TYPE, SafetyManagementConstatnts.ACTIVITY_TYPE_INDEX);
    }

    public void userCreatesAnActivityForAnAttendedDrillsSession(){
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_CATEGORY_TEXT, SafetyManagementConstatnts.ACTIVITY_CATEGORY_SAFETY);
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_TYPE_TEXT, SafetyManagementConstatnts.ACTIVITY_TYPE_INDEX);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_TYPE_ACTIVITY_TIME, SafetyManagementConstatnts.ACTIVITY_TYPE_TIME);
    }
    private By onboardMemberCountLocator(String memberType) {
        return By.xpath(String.format("//a[normalize-space()='%s']/following-sibling::span", memberType));
    }
    public int getOnboardMemberCount(String memberType) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement element = webDriver.findElement(onboardMemberCountLocator(memberType));
        seleniumActions.highLighterMethod(element);
        seleniumActions.highLighterMethod(webDriver.findElement(By.xpath("//div/a[normalize-space()='" + memberType + "']")));

        String countText = element.getText().trim();
        return Integer.parseInt(countText.replaceAll("[^0-9]", ""));
    }
    public int getTableRowCount() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement items = webDriver.findElement(itemsCount);
        seleniumActions.scrollInToElement(items);

        seleniumActions.highLighterMethod(items);

        String text = items.getText().trim();

        String count = text.substring(text.indexOf("(") + 1, text.indexOf(" items"));

        logger.info("Items Count : " + count);

        return Integer.parseInt(count);
    }
    public void enterSpecialNeedsComment() {
        seleniumActions.selectDropdownOption("Special Needs","2");

        List<WebElement> addBtn = webDriver.findElements(addSpecialNeedsBtn);
        if(addBtn.size() > 0){
            addBtn.get(0).click();
        }
        WebElement commentField = webDriver.findElement(specialNeedsComments);
        commentField.clear();
        commentField.sendKeys(""+commonFunctions.getRandomCharacter());
    }

    public boolean isSpecialNeedsCommentFieldDisplayed() {
        return webDriver.findElement(specialNeedsComments).isDisplayed();
    }

    public boolean isTrainingCertificationRowPresent() {
        List<WebElement> rows = webDriver.findElements(trainingCertificationRow);
        if (rows.size() > 0) {
            WebElement firstRow = rows.get(0);
            seleniumActions.highLighterMethod(firstRow);
            return firstRow.isDisplayed();
        }
        return false;
    }

    public void clickTrainingCertificationAddButton(){

        webDriver.findElement(trainingCertificationAddButton).click();
        try {
            seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
            seleniumActions.clickOnElement(webDriver.findElement(trainingCertificate));
            WebElement searchBox = webDriver.findElement(searchBar);
            searchBox.clear();
            searchBox.sendKeys(SafetyManagementConstatnts.MEDICAL);
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(getDropdownOption(SafetyManagementConstatnts.MEDICAL)));
            option.click();

        } catch (Exception ex) {
            Assert.fail("Unable to select activity type: " + SafetyManagementConstatnts.MEDICAL);
        }
        seleniumActions.selectDropdownOption("Code","0");
        seleniumActions.clickOnElement(webDriver.findElement(addTrainingCertificationBtn));
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public void userCreatesAnActivityForAnIPMSession(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_CATEGORY_TEXT, SafetyManagementConstatnts.ACTIVITY_CATEGORY_SAFETY);
        try {
            seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
            seleniumActions.clickOnElement(webDriver.findElement(activityTypeDropDown));
            WebElement searchBox = webDriver.findElement(searchBar);
            searchBox.clear();
            searchBox.sendKeys(SafetyManagementConstatnts.IPM);
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(getDropdownOption(SafetyManagementConstatnts.IPM)));
            option.click();

        } catch (Exception ex) {
            Assert.fail("Unable to select activity type: " + SafetyManagementConstatnts.IPM);
        }
    }
    public boolean isCrewCheckboxSelectedByName(String crew) {
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            String checkBoxXpath = String.format("//*[normalize-space()='%s']/ancestor::td/preceding-sibling::td/div/mat-checkbox/label/span/input", crew);

            try {
                WebElement checkBoxElement = webDriver.findElement(By.xpath(checkBoxXpath));

                seleniumActions.highLighterMethod(checkBoxElement);
                seleniumActions.clickOnElement(checkBoxElement);

                return checkBoxElement.isSelected();

            } catch (NoSuchElementException e) {
                logger.error("Checkbox not found for Safety Number: " + crew);
                return false;
            }

    }

    public String userEnterAllTheFieldsInAddNewEmergencyDrillTypePage() {

        String safetyNumber = String.valueOf(getRandomNumber(10000, 90000));
        String description = "Test" + getRandomNumber(1000, 2000);
        commonPage.typeValueInPlaceHolderCssInput(SafetyManagementConstatnts.CODE, safetyNumber);
        commonPage.typeValueInPlaceHolderCssInput(SafetyManagementConstatnts.DESCRIPTION, description);
        return safetyNumber;
    }
    public void searchForSafetyCodeNumber(String safetyInputNumber){
        seleniumActions.clickOnElement(codeDropdown);
        seleniumActions.typeValue(searchCodeInputBox, safetyInputNumber);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public void userViewThePlusIcon(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(safetyPlanAddButton);
    }
    public void userViewElement(String element){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(webDriver.findElement(By.xpath("//*[normalize-space()='" + element + "']")));
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public void clickOnSaveAndFinishIfPresent() {
        seleniumActions.clickOnElement(addButton);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        if (webDriver.findElements(saveAndFinish).size() > 0) {
            try {
                seleniumActions.clickOnElement(saveAndFinishButton);
            } catch (Exception e) {
                seleniumActions.moveToElementAndClick(saveAndFinishButton);
            }
        }
    }
    public void userViewTheAssignButton(){
        seleniumActions.highLighterMethod(assignButton);
    }
    public void userViewTheUnassignButton(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(unAssignButton);
    }
    public void userViewConfirmButton(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(webDriver.findElement(confirm));
    }
    public void userViewSaftyEmergencyPlusIcon(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(safetyEmergencyAddButton);
    }
    public void userCreatesAnActivityForAttendedDrillsSession(){
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_CATEGORY_TEXT, SafetyManagementConstatnts.ACTIVITY_CATEGORY_SAFETY);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_TYPE_TEXT, SafetyManagementConstatnts.ACTIVITY_TYPE_INDEX);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public void userViewTheActivityOverviewButton(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(webDriver.findElement(activity_Overview));
    }
    public void userViewNextButton(){
        seleniumWaits.untilElementIsVisible(webDriver.findElement(nextButton));
        seleniumActions.highLighterMethod(webDriver.findElement(nextButton));
    }
    public void userViewOnSkipButton(){
        seleniumWaits.untilElementIsVisible(skipButton);
        seleniumActions.isElementPresentOnThePage(skipButton);
        seleniumActions.highLighterMethod(skipButton);
    }
    public void userViewTheFinishButton(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(finishButtonBox);
    }
    public void userSelectTheActivityList(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<WebElement> elements = webDriver.findElements(activityList);

        if (!elements.isEmpty()) {
            seleniumActions.highLighterMethod(elements.get(0));
            seleniumActions.clickOnElement(elements.get(0));
        }
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public void userViewTheAddButton() {
        seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
        seleniumActions.highLighterMethod(webDriver.findElement(selectedAddbutton));
    }
    public void clickTheConfirmButtonPopUp(){
        seleniumActions.jsClick(webDriver.findElement(conirmpop_up));
    }

    public void checkingTheGuestAvaliable() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.jsClick(webDriver.findElement(threeVerticalDots));
        By locator = By.xpath(dropdownText(SafetyManagementConstatnts.ADD_PARTICIPANTS));
        seleniumWaits.untilElementIsVisible(webDriver.findElement(locator));
        seleniumActions.highLighterMethod(webDriver.findElement(locator));
        seleniumActions.jsClick(webDriver.findElement(locator));
        seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
    }
    public void userSelectsTheDropdownGroupCheckbox(String text){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.interactWithElementByXpath(filterName(text), true);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.MINI_WAIT));

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//mat-pseudo-checkbox)[1]")));
        option.click();
        pageloaded();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public boolean isIPMActivityDisplayed() {
        WebElement element = webDriver.findElement(txtIPMActivity);
        seleniumActions.highLighterMethod(element);
        return element.isDisplayed();
    }


    public boolean verifyEmergencyTeamValues() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        try {

            WebElement emerTeamElement = webDriver.findElement(emerTeamLocator);

            seleniumWaits.untilElementIsClickable(emerTeamElement);

            seleniumActions.clickOnElement(emerTeamElement);

            List<WebElement> dropdownItems = webDriver.findElements(dropdownItemsLocator);

            List<String> dropdownTexts = new ArrayList<>();

            for (WebElement item : dropdownItems) {

                String dropdownText = seleniumActions.getTextMessage(item).trim();

                if (!dropdownText.isEmpty()) {

                    dropdownTexts.add(dropdownText);

                    logger.info("Dropdown Text : " + dropdownText);

                    seleniumActions.clickOnElement(item);
                }
            }


            List<String> chipTexts = new ArrayList<>();

            for (WebElement chip : chipElements) {

                String chipText = seleniumActions.getTextMessage(chip);

                if (!chipText.isEmpty()) {

                    chipTexts.add(chipText);

                    logger.info("Chip Text : " + chipText);
                }
            }

            logger.info("Dropdown Values : " + dropdownTexts);

            logger.info("Chip Values : " + chipTexts);

            for (String chipText : chipTexts) {

                if (dropdownTexts.contains(chipText)) {

                    logger.info("Matched Value : " + chipText);
                }
            }

            return true;

        } catch (Exception e) {

            logger.error("Failed to validate emergency team values", e);

            return false;
        }
    }
    public By getLinkLocator(String link) {
        String xpath = String.format("//div/a[normalize-space()='%s']", link);
        return By.xpath(xpath);
    }

    public void clickOnSelectLink(String link) {
        WebElement linkElement = webDriver.findElement(getLinkLocator(link));
        seleniumActions.scrollInToElement(linkElement);
        seleniumActions.clickOnElement(linkElement);
    }

    public void addTrainingCertificationIfNotExists(String sectionText) {
        if (!isSectionFirstRowDisplayed(sectionText)) {
            webDriver.findElement(trainingCertificationAddButton).click();
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.selectDropdownOption(SafetyManagementConstatnts.TRAINING_AND_CERTIFICATION, SafetyManagementConstatnts.MEDICAL);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.selectDropdownOption(SafetyManagementConstatnts.CODE, SafetyManagementConstatnts.DROPDOWN_INDEX_VALUE);
            seleniumActions.clickOnElement(webDriver.findElement(addTrainingCertificationBtn));
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        }
    }

    public boolean specialNeedFieldDisplayed() {
        WebElement element = webDriver.findElement(addSpecialNeedsDropDown);
        seleniumActions.highLighterMethod(element);
        return element.isDisplayed();
    }

    public void userEntersACommentForTheSpecialNeeds(){
        WebElement element = webDriver.findElement(specialNeedsComments);
        seleniumActions.sendKeys(element,SafetyManagementConstatnts.TESTING);
        seleniumActions.highLighterMethod(element);
    }

    public boolean clickGuestProfileWithImage() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        if (profileImages.isEmpty()) {
            return false;
        }
        WebElement profileImage = profileImages.get(0);
        profileImage.click();
        return true;
    }

    public String getGuestName() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(guestNameElement);
        return guestNameElement.getText().trim();
    }

    public String getGuestAge() {
        seleniumActions.highLighterMethod(guestAgeElement);
        String ageText = guestAgeElement.getText().trim();
        return ageText.replaceAll("\\D+", "");
    }

    public String getGuestNationality() {
        seleniumActions.highLighterMethod(nationalityElement);
        return nationalityElement.getText().trim();
    }

    public String getGuestLanguage() {
        seleniumActions.highLighterMethod(languageElement);
        return languageElement.getText().trim();
    }
    public String getGuestFirstName() {
        seleniumActions.highLighterMethod(firstNameElement);
        return firstNameElement.getText().trim();
    }

    public String getGuestLastName() {
        seleniumActions.scrollInToElement(lastNameElement);
        seleniumActions.highLighterMethod(lastNameElement);
        return lastNameElement.getText().trim();
    }

    public String getGuestFullName() {
        return getGuestFirstName() + " " + getGuestLastName();
    }

    public String getGuestNationalityFromPersonalInfo() {
        seleniumActions.highLighterMethod(nationalityTextElement);
        return nationalityTextElement.getText().trim();
    }

    public String getGuestLanguageFromPersonalInfo() {
        seleniumActions.highLighterMethod(languageTextElement);
        return languageTextElement.getText().trim();
    }
    public boolean selectAssemblyStationFilter() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);

        if (!assemblyStationDropDown.isDisplayed()) {

            WebElement addMoreFilters = wait.until(ExpectedConditions.elementToBeClickable(addMoreFiltersIcon));
            addMoreFilters.click();
            logger.info("Clicked on 'Add More Filters' icon.");

            String statusElement = status(SafetyManagementConstatnts.ACTIVITY_TYPE_ASSEMBLY_STATION);
            seleniumActions.interactWithElementByXpath(statusElement, true);
            seleniumActions.pressEscapeKey();
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);

            if (!assemblyStationDropDown.isDisplayed()) {
                return false;
            }
        }
        seleniumActions.highLighterMethod(assemblyStationDropDown);

        return true;
    }

    public String selectFirstAssemblyStation() {

        seleniumActions.clickOnElement(assemblyStationDropDown);

        if (checkBoxes.isEmpty() || stationNames.isEmpty()) {
            return null;
        }
        String stationName = stationNames.get(0).getText().trim();

        checkBoxes.get(0).click();

        logger.info("Selected Assembly Station: {}", stationName);
        seleniumActions.clickOnElement(assemblyStationDropDown);
        seleniumActions.pressEscapeKey();

        return stationName;
    }

    public String getFirstRowValueByHeader(String headerName) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);

        int columnIndex = -1;

        for (WebElement header : headers) {

            String headerText = header.getText().trim();

            if (headerText.equalsIgnoreCase(headerName)) {
                String id = header.getAttribute("id");
                columnIndex = Integer.parseInt(id.replace("dx-col-", ""));
                break;
            }
        }

        if (columnIndex == -1) {
            throw new AssertionError("Column '" + headerName + "' was not found.");
        }


        List<WebElement> firstRowCells = webDriver.findElements(
                By.xpath("//td[@aria-describedby='dx-col-" + columnIndex + "']"));

        if (firstRowCells.isEmpty()) {
            throw new AssertionError("No data found under column: " + headerName);
        }

        WebElement firstCell = firstRowCells.get(0);
        seleniumActions.scrollInToElement(firstCell);
        seleniumActions.highLighterMethod(firstCell);
        String actualValue = firstCell.getText().trim();
        return actualValue;
    }

    public WebElement clickFirstSafetyNumberElement() {
        List<WebElement> safetyNumberElements = webDriver.findElements(safetyNumberElement);

        if (safetyNumberElements.isEmpty()) {
            return null;
        }
        WebElement firstElement = safetyNumberElements.get(0);
        seleniumActions.highLighterMethod(firstElement);
        firstElement.click();
        return firstElement;
    }

    public WebElement headerTabs(String link) {
        String header = String.format("//mat-panel-title[contains(normalize-space(.), '%s')]", link);
        return webDriver.findElement(By.xpath(header));
    }

    public boolean isSafetySectionDisplayed(String sectionName) {
        try {
            WebElement element = headerTabs(sectionName);
            seleniumActions.highLighterMethod(element);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String selectRandomCrewMember() {
        Random random = new Random();
        WebElement randomCrew = crewNameList.get(random.nextInt(crewNameList.size()));
        seleniumActions.highLighterMethod(randomCrew);
        String crewName = randomCrew.getText().trim();
        String searchName = crewName.split(",")[0].trim();
        return searchName;
    }

    public boolean isCrewNameDisplayed(String savedCrewName) {
        String actualCrewName = crewNameList.get(0).getText().trim();
        seleniumActions.highLighterMethod(crewNameList.get(0));
        return actualCrewName.contains(savedCrewName);
    }

    public void updateTheUpComingDate(String oneMonthsRange){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clearValue(activityDateField);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.typeValue(activityDateField, oneMonthsRange);
        seleniumActions.pressTabKey();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.jsClick(saveAndFinishButton);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public String selectRandomSafetyNumber() {
        Random random = new Random();
        WebElement randomSafetyNumber = safteyNumberList.get(random.nextInt(safteyNumberList.size()));
        seleniumActions.highLighterMethod(randomSafetyNumber);
        String safetyNumber = randomSafetyNumber.getText().trim();
        String searchSafetyNumber= safetyNumber.split(",")[0].trim();
        return searchSafetyNumber;
    }

    public boolean isSafetyNumberDisplayed(String savedSafetyNumber) {
        String actualSafetyNumber = safteyNumberList.get(0).getText().trim();
        seleniumActions.highLighterMethod(safteyNumberList.get(0));
        return actualSafetyNumber.contains(savedSafetyNumber);
    }


    public String getUpcomingDrillTrainingDate() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(upcomingDrillTrainingDate);
        return upcomingDrillTrainingDate.getText().trim();
    }


    public void clickFirstSafetyDrill() {
        seleniumWaits.untilElementIsVisible(safetyDrillList.get(0));
        safetyDrillList.get(0).click();
    }

    public void updateTheSafetyTime(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_TYPE_ACTIVITY_TIME, SafetyManagementConstatnts.ACTIVITY_TYPE_TIME);
    }

    public void userNameClick(){
        seleniumActions.clickOnElement(UserName.get(0));
    }
    public By getMatSelectByLabel(String labelName) {
        String xpath = "//mat-form-field[.//*[normalize-space(text())='" + labelName + "']]//div[@aria-label='Select' or contains(@class,'mat-select-arrow-wrapper')]";
        return By.xpath(xpath);
    }

    public boolean userCreatesANewSafetyTrainingActivity(String option) {
        seleniumWaits.untilElementIsVisible(webDriver.findElement(getMatSelectByLabel(SafetyManagementConstatnts.ACTIVITY_CATEGORY_TEXT)));
        seleniumActions.selectDropdownOption(SafetyManagementConstatnts.ACTIVITY_CATEGORY_TEXT, SafetyManagementConstatnts.ACTIVITY_CATEGORY_SAFETY);
        try {
            seleniumWaits.untilElementIsVisible(webDriver.findElement(activityTypeDropDown));
            seleniumActions.clickOnElement(webDriver.findElement(activityTypeDropDown));
            WebElement searchBox = webDriver.findElement(searchBar);
            try {
                //seleniumWaits.untilElementIsVisible(searchBox);
                searchBox.sendKeys(option);
                seleniumWaits.untilElementIsClickable(webDriver.findElement(getDropdownOption(option)));
                webDriver.findElement(getDropdownOption(option)).click();
            } catch (Exception e) {
                seleniumWaits.untilElementIsClickable(webDriver.findElement(getDropdownOption(option)));
                webDriver.findElement(getDropdownOption(option)).click();
            }

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean selectCrewByReservationStatus(String filterName, String reservationStatus) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        try {
            seleniumActions.clickOnElement(By.xpath(filterName(filterName)));
            seleniumActions.clickOnElement(By.xpath(status(reservationStatus)));
            pageloaded();
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            pageloaded();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getFirstCrewMemberName() {
        if (crewMemberNames.isEmpty()) {
            return null;
        }
        seleniumActions.highLighterMethod(crewMemberNames.get(0));
        return crewMemberNames.get(0).getText().trim();
    }
    private By activityName(String activityName) {
        return By.xpath("//otalio-overview-activity//*[contains(text(),'" + activityName + "')]");
    }
    public boolean isActivityDisplayed(String activityName) {
        try {
            seleniumActions.highLighterMethod(webDriver.findElement(activityName(activityName)));
            return webDriver.findElement(activityName(activityName)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public String getSelectedUserName() {
        seleniumActions.highLighterMethod(UserName.get(0));
        return UserName.get(0).getText().trim();
    }

    public String getFirstAvailablePosition() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        new Actions(webDriver).moveToElement(horizontalSlider.get(0)).clickAndHold().moveByOffset(300, 0).pause(Duration.ofMillis(500)).release().perform();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        for (WebElement position : positionValues) {
            try {
                seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
                seleniumActions.highLighterMethod(position);
                String text = position.getText().trim();
                if (!text.isEmpty()) {
                    return text;
                }
            } catch (StaleElementReferenceException e) {
            }
        }
        Assert.fail("No Position value found in the Position column.");
        return null;
    }

    public boolean selectCrewBySafetyPositionStatus(String filterName,String reservationStatus) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        try {
            seleniumActions.clickOnElement(By.xpath(filterName(filterName)));
            safetyPositionsSearch.clear();
            safetyPositionsSearch.sendKeys(reservationStatus);
            seleniumActions.clickOnElement(By.xpath(status(reservationStatus)));
            pageloaded();
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            pageloaded();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateEmbarkDates() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormat.forPattern(SafetyManagementConstatnts.DATE_FORMAT_MDY);
        int validatedCount = 0;
        for (WebElement embarkDateElement : embarkDates) {
            String embarkDateText = embarkDateElement.getText().trim();
            if (embarkDateText.equalsIgnoreCase(SafetyManagementConstatnts.EMBARK_DATE)) {
                continue;
            }
            if (validatedCount == 10) {
                break;
            }
            seleniumActions.highLighterMethod(embarkDateElement);
            if (embarkDateText.isEmpty()) {
                return false;
            }
            LocalDate embarkDate = formatter.parseLocalDate(embarkDateText);
            if (embarkDate.isBefore(today)) {
                return false;
            }
            validatedCount++;
        }
        return true;
    }

    public void selectAllParticipantsFromCurrentPage() {
        List<WebElement> elements = webDriver.findElements(checkBoxRows);
        seleniumActions.jsClick(elements.get(0));
        seleniumActions.clickOnElement(selectAllCheckBox);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.SHORT_WAIT));
        wait.until(ExpectedConditions.elementToBeClickable(selectPageButton));
        seleniumActions.clickOnElement(selectPageButton);
    }
    public boolean specialNeedsOptions() {
        if (specialNeedsOptions.isEmpty()) {
            seleniumActions.clickOnElement(addSpecialNeedsBtn);

            seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
            seleniumActions.clickOnElement(addSpecialNeedsDropDown);

            WebElement searchBox = webDriver.findElement(searchBar);
            searchBox.clear();
            searchBox.sendKeys(ExcursionManagementConstants.DROPDOWN_TEXT);

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.SHORT_WAIT));
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                    getDropdownOption(ExcursionManagementConstants.DROPDOWN_TEXT)));
            option.click();
            seleniumActions.jsClick(saveButton);

        }
        return !specialNeedsOptions.isEmpty();
    }

    public int getSpecialNeedsCount() {
        return specialNeedsOptions.size();
    }

    public void clickDeleteIcon() {
        seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
        seleniumActions.jsClick(deleteIconSpecialNeed);

    }

    public void clickAddButton(){
        seleniumWaits.untilElementIsClickable(webDriver.findElement(selectedAddbutton));
        seleniumActions.isElementPresentOnThePage(webDriver.findElement(selectedAddbutton));
        seleniumActions.jsClick(webDriver.findElement(selectedAddbutton));
    }
    public String getSafetyNumber() {
        seleniumWaits.untilElementIsVisible(safetyNumberTexts);
        return safetyNumberTexts.getText().trim();
    }
    public void searchBySafetyNumber(String safetyNumber) {
        seleniumActions.sendKeys(searchBoxForSelectedCrew, safetyNumber);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public boolean isSelectedSafetyNumberDisplayed(String expectedSafetyNumber) {
        for (WebElement element : safetyNumberColumn) {
            seleniumActions.highLighterMethod(element);
            if (element.getText().trim().equals(expectedSafetyNumber)) {
                return true;
            }
        }
        return false;
    }
    public String getCrewIdNumber() {
        return crewIdNumberColumn.get(0).getText().trim();
    }
    public boolean isSelectedCrewIdNumberDisplayed(String expectedSafetyNumber) {
        for (WebElement element : crewIdNumberColumn) {
            seleniumActions.highLighterMethod(element);
            if (element.getText().trim().equals(expectedSafetyNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSectionDisplayedWithValue(String sectionName) {
        WebElement sectionBox = webDriver.findElement(By.xpath("//otalio-infinite-select[@id='cruise-information-current-" + sectionName + "']//ancestor::mat-form-field"));
        WebElement sectionValue = webDriver.findElement(By.xpath("//otalio-infinite-select[@id='cruise-information-current-" + sectionName + "']//span[contains(@class,'mat-select-min-line')]"));
        seleniumActions.highLighterMethod(sectionBox);
        seleniumActions.highLighterMethod(sectionValue);
        return sectionBox.isDisplayed() && !sectionValue.getText().trim().isEmpty();
    }

    public void clicksOnTheButtonInTheSelectDetailTable(){
        seleniumWaits.untilElementIsClickable(webDriver.findElement(selectedAddbutton));
        seleniumActions.jsClick(webDriver.findElement(selectedAddbutton));
        logger.info("Clicked addButtonBox");
    }
    public void clickCabinNumber() {
        seleniumWaits.untilElementIsVisible(cabinNumberText);
        seleniumActions.clickOnElement(cabinNumberText);
    }
    public void addingTheInPortMinning(){
        seleniumWaits.untilElementIsVisible(inPortManningHeader);
        seleniumActions.clickOnElement(inPortManningHeader);
        seleniumWaits.untilElementIsVisible(saveAndFinishButtonInPortMinning);
        seleniumActions.clickOnElement(saveAndFinishButtonInPortMinning);
    }


    public int getSelectedGuestCount() {
        seleniumWaits.untilElementIsVisible(selectedGuestList.get(0));
        return selectedGuestList.size();
    }

    public void clickOnTheRemoveAllButton() {
        seleniumActions.clickOnElement(removeAllButton);
    }
    public String getFirstGuestNameColour() {
        seleniumWaits.untilElementIsVisible(firstGuest);
        return firstGuest.getCssValue("color");
    }
    public void clickTheRevertButton(){
        seleniumActions.clickOnElement(revertIcon);

    }
    public void userSelectsAllCheckboxesAndClicksButton(){

        seleniumWaits.untilListElementIsVisible(selectAllCheckboxes, GlobalConstants.SHORT_WAIT);

        for (WebElement checkbox : selectAllCheckboxes) {
            if (checkbox.isDisplayed()) {
                seleniumActions.highLighterMethod(checkbox);
                seleniumActions.jsClick(checkbox);
                break;
            }
        }
    }

    public void userClicksTheUndoIcon(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        if (!undoIcons.isEmpty()) {
            seleniumActions.clickOnElement(undoIcons.get(0));
        }
    }

}
