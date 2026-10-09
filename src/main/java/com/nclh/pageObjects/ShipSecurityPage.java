package com.nclh.pageObjects;

import com.nclh.constants.ExcursionManagementConstants;
import com.nclh.constants.GlobalConstants;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nclh.constants.ShipSecurityConstants;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class ShipSecurityPage {
    private final WebDriver webDriver;
    private final SeleniumActions seleniumActions;
    private final SeleniumWaits seleniumWaits;
    private final CommonPage commonPage;
    private final CommonFunctions commonFunctions;
    public static final Logger logger = LoggerFactory.getLogger(ShipSecurityPage.class);

    public ShipSecurityPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        seleniumActions = new SeleniumActions(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonPage = new CommonPage(webDriver);
        commonFunctions = new CommonFunctions();
    }

    @FindBy(xpath = "//otalio-link-box/div[normalize-space()='Start Gangway']/a")
    private WebElement startGangWayButton;
    @FindBy(css = "[placeholder='Location']")
    private WebElement locationDropDown;
    @FindBy(css = "[id='otalio-infinite-select-locationId-field-search']")

    private WebElement searchLocation;
    @FindBy(css = "button[id*='save']")
    private WebElement submitButton;
    @FindBy(xpath = "(//table/tbody/tr/td[@role='columnheader']/parent::tr)[1]/td/div[contains(@class,'text-content')]")
    private List<WebElement> tableHeaderItems;
    @FindBy(xpath = "(//div[contains(@class,'checkbox-container')])[2]")
    private WebElement guestCrewDashBoardCheckbox;
    @FindBy(xpath = "//span[contains(text(),'Change')]")
    private WebElement changeButton;
    @Getter
    @FindBy(xpath = "//*[@id='main-screen-open-guest-search-button'] | //*[@id='guest-search-find-guest-input'] | //input[contains(@placeholder,'Search')]")
    private WebElement searchIcon;
    @Getter
    @FindBy(css = "[type='search']")
    private WebElement searchInputBox;
    @FindBy(css = "#gs-select-button")
    private WebElement selectButton;
    @FindBy(xpath = "//icon[@icon='error']/../div/span")
    private WebElement errorMessage;
    @FindBy(css = "#complex-filter-collapse-expand-chip > div > div")
    private WebElement filterButton;
    @FindBy(xpath = "//mat-pseudo-checkbox[contains(@class,'checked')]")
    private List<WebElement> checkBoxes;
    @FindBy(xpath = "//h2[text()='Select Crew, Guest or Visitor']")
    private WebElement searchPageHeading;
    @FindBy(xpath = "//div[@class='dx-datagrid-text-content']")
    private List<WebElement> guestColumnHeader;
    @FindBy(css = "[href*='gangway-log']")
    private WebElement gangwayLogLink;
    @FindBy(css = "[formcontrolname='tenderCapacity']")
    private WebElement tenderCapacityInputBox;
    @FindBy(xpath = "//div[text()='Tender Capacity']/../div/otalio-chip/div/div")
    private WebElement tenderCapacityNumber;
    @FindBy(xpath = "//h4[normalize-space()='Drill and Training']")
    private WebElement drillAndTrainingHeading;
    @FindBy(xpath = "//td[@role='columnheader']")
    private List<WebElement> visitorColumnData;
    @FindBy(xpath = "//div[contains(text(),'Gangway')]")
    private WebElement gangwayMode;
    @FindBy(xpath = "//otalio-passenger-picture/descendant::div[contains(@class,'reservation-status')]")
    private List<WebElement> userImageIcons;
    @FindBy(css = "[id^='booking-passengers'] + div")
    private List<WebElement> passengersListSameBookingNumber;
    @FindBy(xpath = "//div[contains(text(),'Same Booking Number')]")
    private WebElement sameBookingNumberMenu;
    @FindBy(xpath = "//div[contains(text(),'Same Cabin')]")
    private WebElement sameCabinNumberMenu;
    @FindBy(xpath = "//div[contains(text(),'')]/preceding-sibling::mat-pseudo-checkbox")
    private List<WebElement> selectedCheckBoxes;
    @FindBy(xpath = "//div[contains(text(),'Same Cabin')]/following::otalio-related-passenger-container/descendant::otalio-passenger-picture/../following-sibling::div[contains(@class,'tooltip')]")
    private List<WebElement> passengersListSameCabinNumber;
    @FindBy(xpath = "(//otalio-travel-with-person/descendant::div[contains(@class,'text-headline')])")
    private List<WebElement> childGuardiansList;
    @FindBy(xpath = "//h3[text()='Booking Nr']/ancestor::otalio-passenger-booking-info/following-sibling::otalio-passenger-cabin-card/h2/span")
    private WebElement cabinNumber;
    @FindBy(xpath = "(//a[contains(text(),'')]/../../preceding-sibling::td[contains(@class,'select')]/following-sibling::td[@aria-colindex='3']/div/a)[1]")
    private WebElement gangwayMember;
    @FindBy(xpath = "//table/tbody/tr/td[@role='gridcell']/div/a[not(starts-with(@id,'passenger'))]")
    private List<WebElement> guestLastNames;
    @FindBy(xpath = "//table/tbody/tr/td[@role='gridcell']/div/a[contains(@id,'last-name')]")
    private List<WebElement> crewLastNames;
    @FindBy(xpath = "//textarea[contains(@class,'input')]")
    private WebElement postingStatusInputBox;
    @FindBy(css = "[icon='menu-dots']")
    private WebElement threeDotsMenu;
    @FindBy(xpath = "//span[text()='Reason']")
    private WebElement reasonDropDown;
    @FindBy(xpath = "//mat-option[not(contains(@class,'disabled'))]")
    private List<WebElement> reasonOptions;
    @FindBy(xpath = "//mat-option[not(contains(@class,'disabled'))]/span[contains(text(),' Person of Interest ')]")
    private WebElement reasonPOI;
    @FindBy(css = "[placeholder='Reason Details']")
    private WebElement reasonDetailsInputBox;
    @FindBy(xpath = "//span[normalize-space()='Save']")
    private WebElement saveButton;
    @FindBy(xpath = "(//icon[starts-with(@icon,'delete')])")
    private List<WebElement> deleteButton;

    @FindBy(xpath = "//div[text()='Notifications']")
    private WebElement notificationsPopUp;

    @FindBy(xpath = "//span[contains(text(),'Confirm')]")
    private WebElement confirmButton;
    @FindBy(css = "[id='complex-filter-search-input']")
    private WebElement searchFilter;
    @FindBy(css = "#start-up-form-BOTH-radio-button")
    private WebElement bothRadioButton;
    @FindBy(xpath = "//*[@mattooltipposition='below']")
    private WebElement gangwayModeDirection;
    @FindBy(css = "[aria-label='Items per page: 100']")
    private WebElement perPageLink100;

    @FindBy(xpath = "(//div[normalize-space()='Total'])[1]")
    private WebElement totalCountHeaderText;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-PAX-log-button']/span[@class='mat-button-wrapper'])[1]")
    private WebElement totalGuestsCheckedInOnboardCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-CRW-log-button']/span[@class='mat-button-wrapper'])[1]")
    private WebElement totalCrewCheckedInOnboardCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-VIS-log-button']/span[@class='mat-button-wrapper'])[1]")
    private WebElement totalVisitorsCheckedInOnboardCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-all-log-button']/span[@class='mat-button-wrapper'])[1]")
    private WebElement totalCheckedInOnboardCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-PAX-log-button']/span[@class='mat-button-wrapper'])[2]")
    private WebElement totalGuestsCheckedInAshoreCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-CRW-log-button']/span[@class='mat-button-wrapper'])[2]")
    private WebElement totalCrewCheckedInAshoreCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-VIS-log-button']/span[@class='mat-button-wrapper'])[2]")
    private WebElement totalVisitorsCheckedInAshoreCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-all-log-button']/span[@class='mat-button-wrapper'])[2]")
    private WebElement totalCheckedInAshoreCount;


    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-PAX-log-button']/span[@class='mat-button-wrapper'])[3]")
    private WebElement totalGuestsOtherStatusOnboardCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-CRW-log-button']/span[@class='mat-button-wrapper'])[3]")
    private WebElement totalCrewOtherStatusOnboardCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-VIS-log-button']/span[@class='mat-button-wrapper'])[2]")
    private WebElement totalVisitorsOtherStatusOnboardCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-all-log-button']/span[@class='mat-button-wrapper'])[3]")
    private WebElement totalOtherStatusOnboardCount;



    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-PAX-log-button']/span[@class='mat-button-wrapper'])[4]")
    private WebElement totalGuestsExpectedCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-CRW-log-button']/span[@class='mat-button-wrapper'])[4]")
    private WebElement totalCrewExpectedCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-VIS-log-button']/span[@class='mat-button-wrapper'])[3]")
    private WebElement totalVisitorsExpectedCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-all-log-button']/span[@class='mat-button-wrapper'])[4]")
    private WebElement totalExpectedCount;



    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-PAX-log-button']/span[@class='mat-button-wrapper'])[5]")
    private WebElement totalGuestsEmbarkedCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-CRW-log-button']/span[@class='mat-button-wrapper'])[5]")
    private WebElement totalCrewEmbarkedCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-VIS-log-button']/span[@class='mat-button-wrapper'])[5]")
    private WebElement totalVisitorsEmbarkedCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-all-log-button']/span[@class='mat-button-wrapper'])[5]")
    private WebElement totalEmbarkedCount;


    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-PAX-log-button']/span[@class='mat-button-wrapper'])[6]")
    private WebElement totalGuestsDisembarkingCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-CRW-log-button']/span[@class='mat-button-wrapper'])[6]")
    private WebElement totalCrewDisembarkingCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-VIS-log-button']/span[@class='mat-button-wrapper'])[6]")
    private WebElement totalVisitorsDisembarkingCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-all-log-button']/span[@class='mat-button-wrapper'])[6]")
    private WebElement totalDisembarkingCount;


    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-PAX-log-button']/span[@class='mat-button-wrapper'])[7]")
    private WebElement totalGuestsDisembarkedCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-CRW-log-button']/span[@class='mat-button-wrapper'])[7]")
    private WebElement totalCrewDisembarkedCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-VIS-log-button']/span[@class='mat-button-wrapper'])[7]")
    private WebElement totalVisitorsDisembarkedCount;

    @FindBy(xpath = "(//button[@id='main-screen-open-onboard-all-log-button']/span[@class='mat-button-wrapper'])[7]")
    private WebElement totalDisembarkedCount;
    @FindBy(css = "[id*='accountType'] > div + icon")
    private WebElement accountTypeDropDown;

    @FindBy(css = "[id*='movementDateTime'] > div + icon")
    private WebElement dateDropDown;
    @FindBy(xpath = "//span[normalize-space()='Date Range']/ancestor::mat-radio-button//input")
    private WebElement dateRange;
    @FindBy(xpath = "//span[text()='No Data']")
    private WebElement noData;
    @FindBy(css = "[id='complex-filter-add-more-button']")
    private WebElement moreFiltersIcon;
    @FindBy(css = "[id*='-embarkation.displayDate-'] > div + icon")
    private WebElement validFromDropDown;
    @FindBy(css = "[id*='visitorDetails.visitorTypeId'] > div + icon")
    private WebElement visitorTypeDropDown;
    @FindBy(css = "[id*='close-button']")
    private WebElement dashboardBoxCloseButton;
    @FindBy(css = "[id*='tender-capacity-reset-button']")
    private WebElement resetButton;


    By visitorHeaderText = By.xpath("//*[.='Select Crew, Guest or Visitor']");
    By visitorTypDropdown = By.xpath("//*[@formcontrolname='visitorTypeId']");
    By visitorTypDropdownText = By.xpath("//mat-option//span[@class='mat-option-text']");
    By successToast = By.xpath("//div[@id='toast-title-success']");
    By crewProfilePic = By.xpath("(//otalio-guest-search-container//otalio-passenger-picture)[1]");
    By visitorLastName = By.xpath("//table/tbody/tr/td[@role='gridcell']/div/a");
    By bookingNumber = By.xpath("//div[contains(@class,'row no-gutters text-type-info')]");
    By countryDetails = By.xpath("//div[contains(@class,'font-weight-bold row')]");
    By guestNameText = By.xpath("//div[contains(@class,'items-center justify-content-center')]");
    By statusOfGuest = By.xpath("//div[contains(@class,'items-end ng-star')]");
    By dayWithActivity = By.xpath("//div[contains(@class,'day') and contains(@class,'activity')]//div[contains(@class,'number')]");
    By clearDateButton = By.cssSelector("#complex-filter-date-overlay-clear-button");
    By profileText = By.xpath("//otalio-profile-card/descendant::h1[contains(text(),'')]");
    By itemsCount = By.xpath("//div[contains(text(),'items')]");
    By guestNameLink = By.xpath("//mat-dialog-container//tbody/tr[1]/td[2]//a");
    By guestProfileName = By.xpath("//h1[contains(@class,'mat-tooltip-trigger text-center')]");
    By unselectedCheckBoxes = By.xpath("//mat-list-option//mat-pseudo-checkbox");
    By tenderCapacityBox = By.xpath("//otalio-chip[@fill='true']/ancestor::div[@class='d-flex flex-column']");
    By printOnManifestToggleSlider  = By.xpath("//otalio-passenger-manifest-form//mat-slide-toggle[1]");
    By authorityCount  = By.xpath("//otalio-authority-movement");
    By increaseBtn = By.cssSelector("[id*='increase-count-button']");
    By decreaseBtn = By.cssSelector("[id*='decrease-count-button']");
    private String selectedVisitorTypeText;
    By checkboxOptions = By.xpath("//mat-list-option");
    By tenderOperation = By.xpath("//span[normalize-space(text())='Tender Operation']");
    @FindBy(css = "[id*='tender-capacity']")
    private WebElement tenderCapacity;
    @FindBy(css = "[id*='reload']")
    private WebElement reloadIcon;
    By titleElements = By.xpath("//mat-tab-group//div[contains(@class,'title d-')]");
    By trainingElements = By.xpath("//otalio-landing-link-list//div//a[contains(@id,'landing-router-link-drill-and-training')]/parent::div");
    By guestDivElements = By.xpath("//a[contains(@id,'landing-router-link-guest')]/parent::div");
    By guestSpanElements = By.xpath("//a[contains(@id,'landing-router-link-guest')]/parent::div//span");
    By gangwaySummaryElements = By.xpath("//otalio-gangway-summary-data-container//div[contains(@class,'row')]");
    By calendarElement = By.xpath("//otalio-scheduler-month//div[@class='border-left cursor-pointer']");

    @FindBy(css = "[id='main-screen-actions-button']")
    private WebElement actionsButton;
    @FindBy(css = "[id='main-screen-visitor-management-link']")
    private WebElement visitorManagement;
    @FindBy(xpath = "//*[text()='Counts']")
    private List<WebElement> countsElement;
    @FindBy(xpath = "//otalio-scheduler-month//*[name()='use' and @href='#calendar-arrival']/ancestor::div[contains(@class,'cell') and contains(@class,'border-right')]//*[@matbadgeoverlap='true']")
    private List<WebElement> arrivalList;

    @FindBy(xpath = "//otalio-scheduler-month//*[name()='use' and @href='#lifesaver']/ancestor::div[contains(@class,'cell') and contains(@class,'border-right')]//*[@matbadgeoverlap='true']")
    private List<WebElement> scheduledActivitiesList ;
    @FindBy(xpath = "//table//tbody//tr/td[4][normalize-space()!='']")
    private List<WebElement> cabinNumbers;
    @FindBy(css = "[placeholder*='Cabin']")
    private WebElement cabinDropDownSeachBar;

    @FindBy(css = "button[id*='add-boarding-denied']")
    private WebElement addBoardingDenied;
    @FindBy(xpath = "//table//tbody//tr/td[15][normalize-space(.)!='']")
    private List<WebElement> denyReasonsColumn;
    @FindBy(xpath = "(//div[normalize-space()='In Port Manning']/following::otalio-chip[1]//div[contains(@class,'text-truncate')])[2]")
    private WebElement txtInPortManningValue;
    @FindBy(css = "[id='report-as-missing-form-clothes-input']")
    private WebElement clothesInput;

    public void checkIfThereIsNotifications() {

    }

    public void clickOnSearchIcon() {
        seleniumWaits.untilElementIsVisible(searchIcon);
        seleniumActions.clickOnElement(searchIcon);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        if (webDriver.findElements(By.xpath("(//icon[@icon='search'])[1]")).size() > 0) {
            seleniumActions.jsClick(webDriver.findElement(By.xpath("(//icon[@icon='search'])[1]")));
        }

        try {
            webDriver.findElement(By.xpath("(//icon[@icon='search'])[1]")).click();
        } catch (Exception e) {

        }

    }

    public void navigateToGuestsPage() {
        List<WebElement> linkElements = webDriver.findElements(By.xpath("//a[normalize-space()='Guests']"));
        linkElements.get(0).click();
    }

    public String getProfileXPath(String firstName, String lastName) {
        return "//a[normalize-space()='" + lastName + "']" + "/ancestor::td/preceding-sibling::td/div/a[normalize-space()='" + firstName + "']";
    }

    public void openProfile(String firstName, String lastname) {
/*        try {
            seleniumActions.clickOnElement(By.xpath("//a[normalize-space()='" + lastname + "']/ancestor::td/preceding-sibling::td/div/a[normalize-space()='" + firstName + "']"));
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(By.xpath("//a[normalize-space()='" + lastname + "']/ancestor::td/preceding-sibling::td/div/a[normalize-space()='" + firstName + "']"));
        }
        seleniumActions.sleep(2000);
*/
        String text = getProfileXPath(firstName, lastname);
        if(commonPage.untilElementIsVisible(perPageLink100)){
            seleniumActions.clickOnElement(perPageLink100);
        }

        try {
            seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath(text)));
            commonPage.waitForDomReady();
            seleniumActions.clickOnElement(By.xpath(text));
            seleniumActions.sleep(2000);
        } catch (Exception e) {
            webDriver.navigate().refresh();
            seleniumWaits.untilElementIsVisible(searchFilter);
            seleniumActions.sendKeys(searchFilter, firstName);
            seleniumActions.clickOnElement(By.xpath(text));
            seleniumActions.sleep(2000);
        }
        //  seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("//a[normalize-space()='" + lastname + "']/ancestor::td/preceding-sibling::td/div/a[normalize-space()='" + firstName + "']")));
        //    seleniumActions.clickOnElement(By.xpath("//a[normalize-space()='" + lastname + "']/ancestor::td/preceding-sibling::td/div/a[normalize-space()='" + firstName + "']"));
        //      seleniumActions.sleep(2000);

    }

    public void disableAshoreOnBoard() {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try {
            seleniumWaits.untilListElementIsVisible(deleteButton, 5);
            int deleteButtonsSize = deleteButton.size();
            for (int i = 0; i < deleteButtonsSize / 2; i++) {
                By deleteXpath = By.xpath("(//icon[starts-with(@icon,'delete')])");
                seleniumActions.clickOnElement(webDriver.findElements(deleteXpath).get(i));
                seleniumActions.sleep(2000);
                commonPage.clickOnElement("Confirm");
            }
        } catch (Exception e) {
        }
        try {
            seleniumActions.clickOnElement(webDriver.findElements(By.xpath("(//icon[starts-with(@icon,'delete')])")).get(0));
            seleniumActions.sleep(2000);
            commonPage.clickOnElement("Confirm");
        } catch (Exception e1) {
        }
        seleniumActions.sleep(2000);
        seleniumWaits.keepBackImplicitWaitTime(webDriver);
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath("(//otalio-passenger-picture)[1]")));
    }

    public void enableAshoreOnBoard(String name) {
        seleniumActions.clickOnElement(threeDotsMenu);
        seleniumActions.clickOnElement(By.cssSelector("button[id*='" + name + "']"));
        seleniumWaits.untilElementIsVisible(reasonDropDown);
        seleniumActions.clickOnElement(reasonDropDown);
        try {
            seleniumActions.sleep(2000);
            seleniumWaits.untilElementIsVisible(reasonOptions.get(1));
            seleniumActions.clickOnElement(reasonOptions.get(1));
        } catch (Exception ste) {
            seleniumActions.moveToElementAndClick(webDriver.findElements(By.cssSelector("[placeholder='Reason Details']")).get(0));
        }
        seleniumActions.typeValue(reasonDetailsInputBox, "Test");
        seleniumWaits.untilElementIsClickable(saveButton);
        seleniumActions.clickOnElement(saveButton);
    }

    public void enableAshoreOnBoardReasonPOI(String name) {
        seleniumActions.clickOnElement(threeDotsMenu);
        seleniumActions.clickOnElement(By.cssSelector("button[id*='" + name + "']"));
        seleniumWaits.untilElementIsVisible(reasonDropDown);
        seleniumActions.clickOnElement(reasonDropDown);
        try {
            seleniumActions.sleep(2000);
            seleniumWaits.untilElementIsVisible(reasonPOI);
            seleniumActions.clickOnElement(reasonPOI);
        } catch (Exception ste) {
            seleniumActions.moveToElementAndClick(webDriver.findElements(By.cssSelector("[placeholder='Reason Details']")).get(0));
        }
        seleniumActions.typeValue(reasonDetailsInputBox, "Test");
        seleniumWaits.untilElementIsClickable(saveButton);
        seleniumActions.clickOnElement(saveButton);
    }

    public void deleteDisallowedDepartments() {
        try {
            seleniumWaits.untilElementIsPresent(By.xpath("//span[normalize-space()='DELETE']/parent::button[@disabled='true']"), 5);
        } catch (TimeoutException e) {
            commonPage.clickOnElement("DELETE");
            commonPage.clickOnElement("Delete");
            seleniumActions.sleep(5000);
        }
    }

    public void enablePostingToggle(String toggleName) {
        String toggleType = "input[id*='%s']";
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.cssSelector(String.format(toggleType, toggleName))), 15);
        System.out.println(webDriver.findElement(By.cssSelector(String.format(toggleType, toggleName))).getAttribute("aria-checked"));
        WebElement toggle = webDriver.findElement(By.cssSelector(String.format(toggleType, toggleName)));
        if (toggle.getAttribute("aria-checked").equals("false")) {
            seleniumActions.clickOnElement(toggle);
            seleniumWaits.untilElementIsClickable(postingStatusInputBox);
            seleniumActions.typeValue(postingStatusInputBox, "Test");
            commonPage.clickOnElement("Confirm");
        }
    }

    public List<String> getCrewLastNameLatest(String name) {
        List<String> userLastName = seleniumActions.getListOfElementsText(crewLastNames);
        List<String> actualLastNameUsers = new ArrayList<>();
        for (int i = 0; i < userLastName.size(); i++) {
            if (userLastName.get(i).contains(name)) {

            } else {
                actualLastNameUsers.add(userLastName.get(i));
            }
        }
        System.out.println(actualLastNameUsers);
        return actualLastNameUsers;
    }

    public List<String> getGuestLastNameLatest(String name) {
        this.clickOn100Pages();
        List<String> userLastName = seleniumActions.getListOfElementsText(guestLastNames);
        List<String> actualLastNameUsers = new ArrayList<>();
        for (int i = 0; i < userLastName.size(); i++) {
            if (userLastName.get(i).contains(name)) {

            } else {
                actualLastNameUsers.add(userLastName.get(i));
            }
        }
        System.out.println(actualLastNameUsers);
        return actualLastNameUsers;
    }

    public String getCrewGuestFirstName(String crewGuestLast) {
        String crewXpath = "//a[contains(text(),'%s')]/../../following-sibling::td/div/a";
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath(String.format(crewXpath, crewGuestLast))));
    }

    public String saveGuestName() {
        try {
            String xpath = "(//a[contains(text(),'')]/../../preceding-sibling::td[contains(@class,'select')]/following-sibling::td[@aria-colindex='3']/div/a)[1]";
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(xpath)));
            WebElement element = webDriver.findElement(By.xpath(xpath));
            return seleniumActions.getTextMessage(element);
        } catch (Exception e) {
            return seleniumActions.getTextMessage(webDriver.findElement(
                    By.xpath("(//a[contains(text(),'')])/parent::otalio-user-name")
            ));
        }
    }

    public void moveGuest(String guestName) {
        //seleniumActions.jsClick(webDriver.findElement(By.xpath("//a[contains(text(),'"+guestName+"')]/../../preceding-sibling::td[2]")));
        WebElement checkBox = webDriver.findElement(By.xpath("//tr[@aria-rowindex='1']/td/div/div"));
        checkBox.click();
    }

    public void openFilterAndSelectValue(String filter, String value) {
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("//div[contains(text(),'" + filter + "')]/../../following-sibling::icon/*[name()='svg']")));
        seleniumActions.clickOnElement(By.xpath("//div[contains(text(),'" + filter + "')]/../../following-sibling::icon/*[name()='svg']"));
        seleniumActions.sleep(2000);
        seleniumActions.clickOnElement(By.xpath("//span[contains(text(),'" + value + "')]/../preceding-sibling::mat-pseudo-checkbox"));
    }

    public boolean getDataMessage(String data) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<WebElement> visitors = webDriver.findElements(visitorLastName);
        if (!visitors.isEmpty() && visitors.get(0).isDisplayed()) {
            seleniumActions.highLighterMethod(visitors.get(0));
            return true;
        }
        String dataMessage = "//span[text()='%s']";
        seleniumActions.highLighterMethod(webDriver.findElement(By.xpath(String.format(dataMessage, data))));
        return false;
    }

    public void clickOnPage2() {
        seleniumActions.clickOnElement(By.cssSelector("[aria-label='Page 2']"));
        seleniumActions.sleep(3000);
    }

    public void clickOn100Pages() {

        seleniumActions.clickOnElement(By.cssSelector("[aria-label='Items per page: 100']"));
        seleniumActions.sleep(3000);
    }

    public boolean getToggleButtonStatus(String option) {
        seleniumActions.sleep(5000);
        String xpath = "//span[contains(text(),'" + option + "')] /../span/input[@disabled]";
        seleniumWaits.untilElementIsPresent(By.xpath(xpath));
        WebElement element = webDriver.findElement(By.xpath(xpath));
        System.out.println(seleniumActions.isElementDisplayed(element));
        return seleniumActions.isElementDisplayed(element);
    }

    public String getUserName() {
        try {
            return seleniumActions.getTextMessage(gangwayMember);
        } catch (Exception e) {
            try {
                return seleniumActions.getTextMessage(
                        webDriver.findElement(By.xpath("(//a[contains(text(),'')]/../../preceding-sibling::td[contains(@class,'select')]/following-sibling::td[@aria-colindex='3']/div)[1]")));
            } catch (Exception e1) {
                return seleniumActions.getTextMessage(webDriver.findElement(
                        By.xpath("(//a[contains(text(),'')])/parent::otalio-user-name")
                ));
            }
        }

    }

    public String getProfileUserName() {
        seleniumActions.sleep(2000);
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("//otalio-profile-card/descendant::h1")));
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//otalio-profile-card/descendant::h1")));
    }

    public void openMemberAtGangway() {

        try {
            seleniumWaits.untilElementIsClickable(gangwayMember);
            seleniumActions.clickOnElement(gangwayMember);
        } catch (Exception ste) {
            try {
                seleniumActions.clickOnElement(By.xpath("(//a[contains(text(),'')]/../../preceding-sibling::td[contains(@class,'select')]/following-sibling::td[@aria-colindex='3']/div/a)[1]"));
            } catch (Exception e) {
                try {
                    seleniumActions.clickOnElement(By.xpath("(//a[contains(text(),'')]/../../preceding-sibling::td[contains(@class,'select')]/following-sibling::td[@aria-colindex='3']/div)[1]"));
                } catch (Exception e1) {
                    (webDriver.findElement(
                            By.xpath("(//a[contains(text(),'')])/parent::otalio-user-name")
                    )).click();
                }
            }
        }
    }

    public void switchToNewWindow() {
        seleniumActions.switchToOpenedWindow();
    }

    public void clearNotifications() {
        if (webDriver.findElements(By.xpath("//div[text()='Notifications']")).size() > 0) {
            seleniumActions.clickOnElement(By.xpath("//div[text()='Notifications']"));
            seleniumActions.clickOnElement(By.xpath("//span[normalize-space()='Override']"));
            seleniumActions.clickOnElement(By.xpath("//span[normalize-space()='Yes']"));
            seleniumActions.clickOnElement(By.xpath("//span[normalize-space()='Confirm']"));
        }
    }

    public String getCabinNumber() {
        seleniumWaits.untilElementIsVisible(cabinNumber);
        return seleniumActions.getTextMessage(cabinNumber);
    }

    public List<String> getChildGuardiansList() {
//seleniumActions.clickOnElement(By.xpath("(//div[contains(text(),'Travel With')])[1]"));
        return seleniumActions.getListOfElementsText(childGuardiansList);
    }

    public List<String> getBookingNumberGuests() {
        return seleniumActions.getListOfElementsText(passengersListSameCabinNumber);
    }

    public List<String> getCabinNumberGuests() {
        return seleniumActions.getListOfElementsText(passengersListSameCabinNumber);
    }

    public void navigateToProfile(String guestFirstName) {
        seleniumActions.sleep(2000);
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//a[normalize-space()='" + guestFirstName + "'])[1]")));

        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//a[normalize-space()='" + guestFirstName + "'])[1]")));
        }
    }

    public Set<String> getStateRoomStatus() {
        seleniumActions.sleep(10000);
        return seleniumActions.getListOfCssColour(userImageIcons, "background-color");
    }

    public void clearOnBoardFilter() {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//b[contains(text(),'Onboard:')]/../following-sibling::icon/*[name()='svg']")));
        for (WebElement element : selectedCheckBoxes) {
            element.click();
        }
        seleniumActions.sleep(500); // before press esc key
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(3000);
    }

    public void navigateToGuest(String guestName) {
        String[] gn = guestName.split(" ");
        String gnFull = gn[1] + " " + gn[0];
        System.out.println("gnFull :: " + gnFull);

        //seleniumActions.sleep(3000);
        // seleniumActions.clickOnElement(By.cssSelector("[aria-label='Items per page: 100']"));
        seleniumActions.sleep(3000);
        String guestFullName = "//a[normalize-space()='" + guestName + "']/../../preceding-sibling::td[contains(@class,'select')]";
        String guestNames = "//a[contains(text(),'')]/../../preceding-sibling::td[contains(@class,'select')]/following-sibling::td[@aria-colindex='3']/div/a";
        List<WebElement> gNames = webDriver.findElements(By.xpath(guestNames));
        List<String> listOfGuestNames = seleniumActions.getTextOfMultipleElementsIntoList(gNames);
        System.out.println("listOfGuestNames :: " + listOfGuestNames);
        int index = listOfGuestNames.indexOf(guestName) + 1;
        System.out.println(index);
        WebElement checkBox = webDriver.findElement(By.xpath("//tr[@aria-rowindex='" + index + "']/td/div/div"));
        //WebElement element = webDriver.findElement(By.xpath(guestFullName));
        seleniumActions.scrollInToElement(checkBox);
        try {
            seleniumActions.highLighterMethod(checkBox);
            seleniumActions.jsClick(checkBox);
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(checkBox);
        }
    }

    public void confirmMove() {
        seleniumActions.clickOnElement(changeButton);
    }

    public List<String> getGuestFullName(String cabinNumber) {
        String guestNames = "(//table/tbody/tr/td[@role='gridcell']/div/span[normalize-space()='" + cabinNumber + "'])/../../ancestor::tr/td/div/a";
        List<WebElement> guestFullNames = webDriver.findElements(By.xpath(guestNames));
        try {
            return seleniumActions.getListOfElementsText(guestFullNames);
        } catch (StaleElementReferenceException ste) {
            return seleniumActions.getListOfElementsText(webDriver.findElements(By.xpath(guestNames)));
        }
    }

    public boolean isGangwayModePresent() {
        seleniumWaits.untilElementIsVisible(gangwayMode);
        return seleniumActions.isElementDisplayed(gangwayMode);
    }

    public List<String> geVisitorColumnHeaders() {
        List<String> list = seleniumActions.getListOfElementsText(visitorColumnData);
        seleniumActions.sleep(3000);
        List<String> actualList = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            actualList.add(list.get(i));
        }
        return actualList;
    }

    public String getNumberOfGuests(String columnName, String onBoardOnShore) {
        int index = getIndexOfColumnName(columnName) + 1;
        WebElement cg = webDriver.findElement(By.xpath("(//table/tbody/tr/td[@role='columnheader']/div[text()='" + columnName + "']/following::table/tbody/tr/td[normalize-space()='" + onBoardOnShore + "']/../td/div/button/span[@class='mat-button-wrapper'])[" + index + "]"));
        seleniumActions.highLighterMethod(cg);
        System.out.println(cg.getText());
        return cg.getText();
    }

    public void resetTenderCapacity(String capacity) {
        commonPage.clickOnElement("Reset");
        tenderCapacityInputBox.clear();
        tenderCapacityInputBox.sendKeys(capacity);
        commonPage.clickOnElement("Confirm");
        seleniumActions.sleep(3000);
    }

    public int getListOfSafetyAndDrills(String safetyTraining) {
        seleniumActions.scrollInToElement(drillAndTrainingHeading);
        seleniumWaits.untilElementIsClickable(webDriver.findElements(By.xpath("//span[normalize-space()='" + safetyTraining + "']")).get(0));
        System.out.println("Number of elements :: " + seleniumActions.getNumberOfElements(
                webDriver.findElements(By.xpath("//span[normalize-space()='" + safetyTraining + "']"))
        ));
        return seleniumActions.getNumberOfElements(
                webDriver.findElements(By.xpath("//span[normalize-space()='" + safetyTraining + "']"))
        );
    }

    public void switchToParentTab(String windowId) {
        webDriver.switchTo().window(windowId);
        seleniumWaits.untilElementIsClickable(gangwayLogLink);
        seleniumActions.clickOnElement(gangwayLogLink);
        seleniumActions.sleep(3000);
    }

    public List<String> getColumnHeaderNames() {
        List<String> columnNames = new ArrayList<>();
        for (int i = 0; i < guestColumnHeader.size(); i++) {
            columnNames.add(seleniumActions.getTextMessage(guestColumnHeader.get(i)));
        }
        return columnNames;
    }

    public int getIndexOfColumnNames(String name) {
        return getColumnHeaderNames().indexOf(name);
    }

    public List<String> getRowDataInGangway(String guestName) {
        String xpath = "//a[contains(text(),'" + guestName + "')]/../../preceding-sibling::td/parent::tr[1]/td[contains(@class,'select')]/following-sibling::td";
        return seleniumActions.getListOfElementsText(webDriver.findElements(By.xpath(xpath)));
    }

    public List<String> getGuestDetailsFromTheTable(String data, String guestName) {

        List<WebElement> tableData = List.of();
        List<String> rowData = new ArrayList<>();
        switch (data) {
            case "log":
                tableData = webDriver.findElements(By.xpath("(//table/tbody/tr/td[@role='columnheader'][not(contains(@class,'select'))]/div[text()]/following::table/tbody/tr[1]/td[not(contains(@class,'select'))])"));
                break;
            case "dashboard":
                String guestNames = "//a[contains(text(),'')]/../../preceding-sibling::td[contains(@class,'select')]/following-sibling::td[@aria-colindex='3']/div/a";
                List<WebElement> gNames = webDriver.findElements(By.xpath(guestNames));
                List<String> listOfGuestNames = seleniumActions.getTextOfMultipleElementsIntoList(gNames);
                int index = listOfGuestNames.indexOf(guestName) + 1;
                //tableData = webDriver.findElements(By.xpath("//div[contains(text(),'List of Guests Checked')]/following::table/following::table/tbody/tr[1]/td[not(contains(@class,'select'))][@role='gridcell']"));
                tableData = webDriver.findElements(By.xpath("//div[contains(text(),'List of Guests Checked')]/following::table/following::table/tbody/tr[" + index + "]/td[not(contains(@class,'select'))][@role='gridcell']"));
                break;
        }

        for (int i = 0; i < tableData.size(); i++) {
            if (i == 2 && data.equals("dashboard"))
                System.out.println("Don't add to list"); // it is the time only without date
            else
                rowData.add(seleniumActions.getTextMessage(tableData.get(i)));
        }
        System.out.println(rowData);
        return rowData;
    }

    public void applyFilter(String name) {
        try {
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            if (filterButton.isDisplayed()) {
                seleniumActions.clickOnElement(filterButton);
            }
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            logger.info("filter button not present");
        }
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("document.querySelectorAll('icon[icon=\"arrow-down\"]')[1].click();");

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
        seleniumWaits.untilElementIsVisible(searchPageHeading);
        seleniumActions.moveToElementAndClick(searchPageHeading);
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(2000);
        javascriptExecutor.executeScript("document.querySelectorAll('icon[icon=\"arrow-down\"]')[1].click();");
        String selectCheckBox = "//div[normalize-space()='%s']/preceding-sibling::mat-pseudo-checkbox";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(selectCheckBox, name))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(selectCheckBox, name))));
    }

    public String getErrorMessageOnGangwayMode() {
        try {
            return seleniumActions.getTextMessage(errorMessage);
        } catch (Exception e) {
            searchGuestAndMoveToOnboard("c");
            return seleniumActions.getTextMessage(errorMessage);
        }
    }

    public void unCheckValueFromDropDown(String dropDownName, String status) {
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.cssSelector("icon[icon=\"arrow-down\"]")));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("document.querySelector('icon[icon=\"arrow-down\"]').click();");
        seleniumActions.jsClick(webDriver.findElement(By.xpath("//div[normalize-space()='" + status + "']/preceding-sibling::mat-pseudo-checkbox")));
        seleniumActions.pressEscapeKey();
    }

    public void changeStatusInDropDown(String dropDownName, String status) {
        try {
            seleniumWaits.untilElementIsVisible(webDriver.findElement(By.cssSelector("icon[icon='arrow-down']")));
        /*JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("document.querySelector('icon[icon='arrow-down']').click();");
*/
            seleniumActions.jsClick(webDriver.findElement(By.cssSelector("icon[icon='arrow-down']")));
            if (webDriver.findElement(By.xpath("//div[normalize-space()='" + status + "']/preceding-sibling::mat-pseudo-checkbox")).getAttribute("class").contains("checked")) {
                System.out.println("CheckBox :: " + status);
            } else {
                seleniumActions.jsClick(webDriver.findElement(By.xpath("//div[normalize-space()='" + status + "']/preceding-sibling::mat-pseudo-checkbox")));
            }
            seleniumWaits.untilElementIsVisible(searchPageHeading);
            seleniumActions.moveToElementAndClick(searchPageHeading);
            seleniumWaits.untilElementIsVisible(searchPageHeading);
            seleniumActions.moveToElementAndClick(searchPageHeading);


        } catch (Exception ex) {
        }
        //seleniumActions.pressEscapeKey();
    }

    public void clickOnSearch() {
        try {
            seleniumWaits.untilElementIsClickable(searchIcon);
            seleniumActions.moveToElementAndClick(searchIcon);
        } catch (Exception ex) {
            webDriver.navigate().refresh();
            seleniumWaits.untilElementIsClickable(searchIcon);
            seleniumActions.highLighterMethod(searchIcon);
            seleniumActions.jsClick(searchIcon);
        }
    }

    public void searchGuestAndMoveToOnboardOrAshore(String firstName, String fullName) {
        seleniumWaits.untilElementIsVisible(searchInputBox);
        seleniumActions.typeValue(searchInputBox, firstName);
        if (firstName.equals("a")) {
            String crewProfilePic = "(//otalio-passenger-picture/div/div/img)[2]";
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(crewProfilePic)));
        } else {
            String guestName = "(//div[contains(text(),'%s')])[1]";
            seleniumActions.sleep(2000);
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(guestName, fullName))));
            seleniumActions.jsClick(webDriver.findElement(By.xpath(String.format(guestName, fullName))));
        }
        try {
            seleniumWaits.untilElementIsClickable(selectButton);
        } catch (TimeoutException te) {
            String guestName = "(//div[contains(text(),'%s')]/../../following-sibling::div/button/span)[1]";
            seleniumActions.jsClick(webDriver.findElement(By.xpath(String.format(guestName, fullName))));
        }
        seleniumActions.jsClick(selectButton);
    }

    public void searchGuestAndMoveToOnboard(String name) {
        seleniumActions.sleep(2000);
        changeStatusInDropDown("Onboard", "Yes");
        seleniumWaits.untilElementIsVisible(searchInputBox);
        seleniumActions.typeValue(searchInputBox, name);
        seleniumActions.sleep(2000);
        if (name.equals("a")) {
            int index = commonFunctions.getRandomNumber(2, 6);
            String crewProfilePic = "(//icon[contains(@icon,'plus')])[" + index + "]";
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(crewProfilePic)));
        } else if (name.contains("Gangwayguest")) {
            String guestName = "(//*[contains(@class,'person-picture')])[1]";
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(guestName, name))));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(guestName, name))));
        } else {
            String guestName = "(//div[contains(text(),'%s')])[1]";
            seleniumActions.jsClick(webDriver.findElement(By.xpath(String.format(guestName, name))));
        }
        seleniumWaits.untilElementIsClickable(selectButton);
        seleniumActions.jsClick(selectButton);
    }

    public void searchGuestAndSelect() {
        changeStatusInDropDown("Onboard", "Yes");
        seleniumWaits.untilElementIsVisible(searchInputBox);
        String name = String.valueOf(commonFunctions.getRandomCharacter());
        seleniumActions.typeValue(searchInputBox, name);
        String crewProfilePic = "(//otalio-passenger-picture/div/div/img)[2]";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(crewProfilePic)));
        seleniumWaits.untilElementIsVisible(selectButton);
        seleniumActions.jsClick(selectButton);
    }

    public void changeToAshoreOrOnBoard() {
        try {
            seleniumActions.clickOnElement(guestCrewDashBoardCheckbox);
        } catch (ElementClickInterceptedException eie) {
            seleniumActions.jsClick(guestCrewDashBoardCheckbox);
        } catch (StaleElementReferenceException se) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//div[contains(@class,'checkbox-container')])[2]")));
        }
        seleniumActions.highLighterMethod(changeButton);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    private By userStatusLocator(String onShoreOnBoard) {
        return By.xpath("//span[normalize-space()='" + onShoreOnBoard + "']");
    }

    public Boolean getUserStatus(String onShoreOnBoard) {
        WebElement element = webDriver.findElement(userStatusLocator(onShoreOnBoard));
        seleniumWaits.untilElementIsVisible(element);
        seleniumActions.highLighterMethod(element);
        return seleniumActions.isElementDisplayed(element);
    }

    public int getIndexOfColumnName(String columnName) {
        List<String> columns = new ArrayList<>();
        for (int i = 1; i < tableHeaderItems.size(); i++) {
            try {
                seleniumActions.sleep(1000); //due to table header loading given 500 millisecs
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

    public void openCrewOrGuests(String columnName, String onBoardOnShore) {
        seleniumActions.sleep(3000);
        /*seleniumWaits.untilElementIsClickable(
                webDriver.findElement(By.xpath("//div[text()='Counts']")));

        seleniumActions.clickOnElement(By.xpath("//div[text()='Counts']"));*/
        int index = getIndexOfColumnName(columnName) + 1;
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//table/tbody/tr/td[@role='columnheader']/div[text()='" + columnName + "']/following::table/tbody/tr/td[normalize-space()='" + onBoardOnShore + "']/../td/div/button/span[@class='mat-button-wrapper'])[" + index + "]")));
    }

    public String navigateToModule(String module) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//a[normalize-space()='" + module + "']")));
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        String parentWindowId = webDriver.getWindowHandle();
        Set<String> windowIds = webDriver.getWindowHandles();
        System.out.println("windowIds :: " + windowIds);
        SeleniumWaits.waitForVisibility(webDriver, commonPage.getProfileIconElement());

        for (String window : windowIds) {
            webDriver.switchTo().window(window);
        }
        return parentWindowId;
    }

    public void searchLocation(String location) {
        setLocation(GlobalConstants.GANGWAY_LOCATION);
        seleniumActions.highLighterMethod(submitButton);
        seleniumActions.clickOnElement(submitButton);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        try {
            seleniumWaits.untilElementIsClickable(
                    webDriver.findElement(By.xpath("//div[text()='Counts']")));

            seleniumActions.clickOnElement(By.xpath("//div[text()='Counts']"));
        } catch (Exception e) {
        }
    }

    public void setLocation(String location) {
        seleniumWaits.untilElementIsClickable(locationDropDown);
        seleniumActions.clickOnElement(locationDropDown);
        seleniumActions.typeValue(searchLocation, location);
        CommonPage commonPage = new CommonPage(webDriver);
        commonPage.navigateTo(location);
    }

    public void setTenderCapacity(String capacity) {
        if (webDriver.findElement
                        (By.xpath("//span[normalize-space(text())='Tender Operation']/../span/input"))
                .getAttribute("aria-checked").equals("false")) {
            seleniumActions.clickOnElement(
                    webDriver.findElement(By.xpath("//span[normalize-space(text())='Tender Operation']/../span/input"))
            );
        }

        tenderCapacityInputBox.clear();
        tenderCapacityInputBox.sendKeys(capacity);
        seleniumActions.clickOnElement(submitButton);
    }

    public String getTenderCapacityNumber() {
        String[] number = seleniumActions.getTextMessage(tenderCapacityNumber).split("/");
        return number[1].trim();
    }

    public void userSelectTheGangwayBothOption() {
        seleniumWaits.untilElementIsVisible(bothRadioButton);
        seleniumActions.highLighterMethod(bothRadioButton);
        JRadioButton bothRadioButton = new JRadioButton("Both");
        bothRadioButton.setSelected(true);
        if (bothRadioButton.isSelected()) {
            logger.info("both Radio Button is selected.");
        } else {
            logger.error("both Radio Button is NOT selected.");
        }
    }

    public void gangwayBothOptionShouldBeSelected() {
        seleniumActions.isElementDisplayed(bothRadioButton);
    }
    public void userEnterTheTenderCapacityCount(String capacity){
        tenderCapacityInputBox.clear();
        tenderCapacityInputBox.sendKeys(capacity);
    }
    public void gangwayModeDirectionScreenShouldBeDisplayed(){
        seleniumWaits.untilElementIsVisible(gangwayModeDirection);
        seleniumActions.isElementDisplayed(gangwayModeDirection);
    }
    public void clickOnIconAtTopRightPane(String modeText){
        try {
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            By tabLocator = getTabByModeText(modeText);

            WebElement tabElement = webDriver.findElement(tabLocator);
            seleniumWaits.untilElementIsVisible(tabElement);

            String ariaSelected = tabElement.getAttribute("aria-selected");

            if ("false".equalsIgnoreCase(ariaSelected)) {
                seleniumActions.clickOnElement(tabElement);
                logger.info(modeText + " was disabled and has been enabled successfully.");
            } else if ("true".equalsIgnoreCase(ariaSelected)) {
                logger.info(modeText + " is already enabled. No action required.");
            } else {
                logger.warn("Unable to determine state for " + modeText + ". aria-selected value: " + ariaSelected);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to verify or enable mode: " + modeText, e);
        }
    }
    public By getTabByModeText(String modeText) {
        return By.xpath(String.format("//div[@role='tab'][.//div[normalize-space()='%s']]", modeText));
    }
    public void visitorsScreenShouldBeDisplayed(){
        seleniumWaits.untilElementIsVisible(webDriver.findElement(visitorHeaderText));
        seleniumActions.isElementDisplayed(webDriver.findElement(visitorHeaderText));
    }
    public void selectVisitorTypeDropdownUnderGeneralSection(){
        seleniumActions.scrollInToElement(webDriver.findElement(visitorTypDropdown));
        seleniumActions.isElementDisplayed(webDriver.findElement(visitorTypDropdown));
    }

    public By getDropdownArrowByLabel(String label) {
        return By.xpath(String.format("//mat-form-field[.//*[normalize-space(text())='%s']]//div[@aria-label='Select' or contains(@class,'mat-select-arrow-wrapper')]", label));
    }

    public void userAbleToSelectTheVisitorType() {
        seleniumActions.clickOnElement(webDriver.findElement(getDropdownArrowByLabel(ShipSecurityConstants.VISITOR_TYPE)));
        commonPage.waitForDomReady();
        List<WebElement> options = webDriver.findElements(visitorTypDropdownText);
        logger.info("Number of options found: " + options.size());

        if (!options.isEmpty()) {
            WebElement selectedOption = options.get(2);
            selectedVisitorTypeText = selectedOption.getText().trim();
            logger.info("Selected option text: " + selectedVisitorTypeText);
            selectedOption.click();
        } else {
            logger.error("No enabled dropdown options found.");
        }
    }
    public void userClickOnSAVEButton(){
        seleniumWaits.untilElementIsClickable(saveButton);
        seleniumActions.clickOnElement(saveButton);
    }

    public By getVisitorTypeByText(String visitorTypeText) {
        return By.xpath(String.format("//*[@class='text-truncate' and normalize-space(text())='%s']", visitorTypeText));
    }
    public void visitorTypeCategoryShouldBeUpdated() {
        WebElement updatedElement = webDriver.findElement(getVisitorTypeByText(selectedVisitorTypeText));
        seleniumActions.scrollInToElement(updatedElement);
        String updatedText = updatedElement.getText().trim();
        if (updatedText.equals(selectedVisitorTypeText)) {
            logger.info("Text is updated" + updatedText);
        } else {
            logger.error("Expected '" + selectedVisitorTypeText + "' but found '" + updatedText + "'");
        }
    }
    public void userShouldSeeTheSuccessPopup(){
        try {
            new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(successToast));
            logger.info("Success toast is visible");
        } catch (TimeoutException ex) {
            new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(successToast));
            logger.info("Success toast disappeared");
        }
    }
    public String getSpanXPathByText(String buttonText) {
        return "//span[text()='" + buttonText + "' or normalize-space()='" + buttonText + "']";
    }

    public boolean navigateToReportLink(String buttonText) {
        try {
            String xpath = getSpanXPathByText(buttonText);

            seleniumWaits.untilElementIsPresent(By.xpath(xpath), 10);

            WebElement element = webDriver.findElement(By.xpath(xpath));
            seleniumActions.scrollInToElement(element);
            seleniumActions.clickOnElement(element);
            commonPage.waitForDomReady();
            return true;

        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public void scrollToTotalCountText(){
        seleniumActions.scrollInToElement(totalCountHeaderText);
    }


    public String getTotalGuestsCheckedInOnboardCount(){
        return seleniumActions.getTextMessage(totalGuestsCheckedInOnboardCount);
    }

    public String getTotalCrewCheckedInOnboardCount(){
        return seleniumActions.getTextMessage(totalCrewCheckedInOnboardCount);
    }

    public String getTotalVisitorsCheckedInOnboardCount(){
        return seleniumActions.getTextMessage(totalVisitorsCheckedInOnboardCount);
    }

    public String getTotalCheckedInOnboardCount(){
        return seleniumActions.getTextMessage(totalCheckedInOnboardCount);
    }

    public String getTotalGuestsCheckedInAshoreCount(){
        return seleniumActions.getTextMessage(totalGuestsCheckedInAshoreCount);
    }

    public String getTotalCrewCheckedInAshoreCount(){
        return seleniumActions.getTextMessage(totalCrewCheckedInAshoreCount);
    }

    public String getTotalVisitorsCheckedInAshoreCount(){
        return seleniumActions.getTextMessage(totalVisitorsCheckedInAshoreCount);
    }

    public String getTotalCheckedInAshoreCount(){
        return seleniumActions.getTextMessage(totalCheckedInAshoreCount);
    }

    public String getTotalGuestsOtherStatusOnboardCount(){
        return seleniumActions.getTextMessage(totalGuestsOtherStatusOnboardCount);
    }

    public String getTotalCrewOtherStatusOnboardCount(){
        return seleniumActions.getTextMessage(totalCrewOtherStatusOnboardCount);
    }

    public String getTotalVisitorsOtherStatusOnboardCount(){
        return seleniumActions.getTextMessage(totalVisitorsOtherStatusOnboardCount);
    }

    public String getTotalOtherStatusOnboardCount(){
        return seleniumActions.getTextMessage(totalOtherStatusOnboardCount);
    }


    public String getTotalGuestsExpectedCount(){
        return seleniumActions.getTextMessage(totalGuestsExpectedCount);
    }

    public String getTotalCrewExpectedCount(){
        return seleniumActions.getTextMessage(totalCrewExpectedCount);
    }

    public String getTotalVisitorsExpectedCount(){
        return seleniumActions.getTextMessage(totalVisitorsExpectedCount);
    }

    public String getTotalExpectedCount(){
        return seleniumActions.getTextMessage(totalExpectedCount);
    }

    public String getTotalGuestsEmbarkedCount(){
        return seleniumActions.getTextMessage(totalGuestsEmbarkedCount);
    }

    public String getTotalCrewEmbarkedCount(){
        return seleniumActions.getTextMessage(totalCrewEmbarkedCount);
    }

    public String getTotalVisitorsEmbarkedCount(){
        return seleniumActions.getTextMessage(totalVisitorsEmbarkedCount);
    }

    public String getTotalEmbarkedCount(){
        return seleniumActions.getTextMessage(totalEmbarkedCount);
    }


    public String getTotalGuestsDisembarkingCount(){
        return seleniumActions.getTextMessage(totalGuestsDisembarkingCount);
    }

    public String getTotalCrewDisembarkingCount(){
        return seleniumActions.getTextMessage(totalCrewDisembarkingCount);
    }

    public String getTotalVisitorsDisembarkingCount(){
        return seleniumActions.getTextMessage(totalVisitorsDisembarkingCount);
    }

    public String getTotalDisembarkingCount(){
        return seleniumActions.getTextMessage(totalDisembarkingCount);
    }


    public String getTotalGuestsDisembarkedCount(){
        return seleniumActions.getTextMessage(totalGuestsDisembarkedCount);
    }

    public String getTotalCrewDisembarkedCount(){
        return seleniumActions.getTextMessage(totalCrewDisembarkedCount);
    }

    public String getTotalVisitorsDisembarkedCount(){
        return seleniumActions.getTextMessage(totalVisitorsDisembarkedCount);
    }

    public String getTotalDisembarkedCount(){
        return seleniumActions.getTextMessage(totalDisembarkedCount);
    }

    public void userSearchForTheGuest(String name){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumWaits.untilElementIsVisible(searchInputBox);
        seleniumActions.typeValue(searchInputBox, name);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(webDriver.findElement(crewProfilePic));
        seleniumWaits.untilElementIsVisible(selectButton);
        seleniumActions.highLighterMethod(selectButton);
    }
    public void selectGuestTypes(String types)
    {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(accountTypeDropDown);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);

        String xpath = "//span[normalize-space()='" + types.trim() + "']/ancestor::mat-list-option//mat-pseudo-checkbox";

        WebElement checkbox = webDriver.findElement(By.xpath(xpath));

        String state = checkbox.getAttribute("class");

        if(!state.contains("mat-pseudo-checkbox-checked"))
        {
            checkbox.click();
        }
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.pressEscapeKey();
    }
    public void userClearTheDateRange(){
        seleniumActions.clickOnElement(dateDropDown);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(webDriver.findElement(clearDateButton));
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.pressEscapeKey();
    }

    public boolean isNoDataDisplayed() {
        try {
            seleniumActions.highLighterMethod(noData);
            return noData.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void selectFilterFromDropdown(String filterName) {
        seleniumActions.jsClick(moreFiltersIcon);
        String xpath = "//span[@class='mat-option-text']/span[contains(text(),'" + filterName + "')]/../preceding-sibling::*";

        WebElement option = webDriver.findElement(By.xpath(xpath));

        seleniumActions.highLighterMethod(option);
        option.click();
    }

    public List<WebElement> getReservationElements() {
        List<WebElement> elements = Arrays.asList(
                webDriver.findElement(bookingNumber),
                webDriver.findElement(countryDetails),
                webDriver.findElement(guestNameText),
                webDriver.findElement(statusOfGuest)
        );
        for (WebElement element : elements) {
            seleniumActions.highLighterMethod(element);
        }
        return elements;
    }
    public void userAbleCountOfOtherTravelersSameBookingNumber(String bookingNumber){
        seleniumWaits.untilElementIsVisible(searchInputBox);
        seleniumActions.typeValue(searchInputBox, GlobalConstants.BOOKING_ID);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(webDriver.findElement(crewProfilePic));
    }
    public By getDynamicTextLocator(String text)
    {
        return By.xpath("//div[contains(text(),'" + text + "')]");
    }

    public void userView(String text)
    {
        WebElement element = webDriver.findElement(getDynamicTextLocator(text));
        seleniumWaits.untilElementIsVisible(element);
        seleniumActions.highLighterMethod(element);
    }
    public boolean clickDayHavingActivity()
    {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<WebElement> elements = webDriver.findElements(dayWithActivity);

        if(!elements.isEmpty())
        {
            WebElement element = elements.get(elements.size() - 2);
            seleniumActions.highLighterMethod(element);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.jsClick(element);
            return true;
        }
        return false;
    }
    public By getBoardStatus(String status)
    {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return By.xpath(String.format("//otalio-person-board-status//h2[normalize-space()='%s']", status));
    }

    public boolean isBoardStatusDisplayed(String status)
    {
        try
        {
            seleniumWaits.untilElementIsVisible((webDriver.findElement(getBoardStatus(status))));
            seleniumActions.highLighterMethod(webDriver.findElement(getBoardStatus(status)));
            return webDriver.findElement(getBoardStatus(status)).isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public void userClearTheValidFromDate(){
        seleniumWaits.untilElementIsVisible(validFromDropDown);
        seleniumActions.jsClick(validFromDropDown);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(webDriver.findElement(clearDateButton));
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.pressEscapeKey();
    }
    public void userSelectTheVisitorInVisitorTypeDropdown(){
        seleniumWaits.untilElementIsVisible(visitorTypeDropDown);
        seleniumActions.jsClick(visitorTypeDropDown);
        List<WebElement> checkBoxes = webDriver.findElements(unselectedCheckBoxes);
        if (!checkBoxes.isEmpty()) {
            checkBoxes.get(0).click();
        }
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.pressEscapeKey();
    }

    public WebElement getToggleCheckbox(String toggleName) {

        return webDriver.findElement(By.xpath("//mat-slide-toggle//span[contains(text(),'" + toggleName + "')]/ancestor::mat-slide-toggle//input"));
    }

    public WebElement getToggleLabel(String toggleName) {

        return webDriver.findElement(By.xpath("//mat-slide-toggle//span[contains(text(),'" + toggleName + "')]/ancestor::mat-slide-toggle//label"));
    }

    public void setToggleState(String toggleName, boolean expectedState) {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement checkbox = wait.until(ExpectedConditions.visibilityOf(getToggleCheckbox(toggleName)));

        boolean currentState = Boolean.parseBoolean(checkbox.getAttribute("aria-checked"));

        if (currentState != expectedState) {

            WebElement toggleLabel = getToggleLabel(toggleName);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);

            wait.until(ExpectedConditions.elementToBeClickable(toggleLabel)).click();

            wait.until(ExpectedConditions.attributeToBe(checkbox, "aria-checked", String.valueOf(expectedState)));
        }
    }

    public boolean getToggleState(String toggleName) {
        WebElement checkbox = getToggleCheckbox(toggleName);
        return Boolean.parseBoolean(checkbox.getAttribute("aria-checked"));
    }
    public void userViewTheProfile(){
        WebElement profileTextElement = webDriver.findElement(profileText);
        seleniumWaits.untilElementIsVisible(profileTextElement);
        seleniumActions.scrollInToElement(profileTextElement);
        seleniumActions.highLighterMethod(profileTextElement);
    }
    public int getColumnIndex(String columnName) {

        switch(columnName.toLowerCase()) {

            case "guests":
                return 1;

            case "crew":
                return 2;

            case "visitors":
                return 3;

            case "total":
                return 4;

            default:
                throw new RuntimeException("Invalid column name");
        }
    }
    private WebElement getDashboardElement(String rowName, int columnIndex) {
        String xpath = "//td[text()='" + rowName + "']/following-sibling::td[" + columnIndex + "]//button";
        return webDriver.findElement(By.xpath(xpath));
    }

    public int getDashboardCount(String rowName, int columnIndex) {
        WebElement element = getDashboardElement(rowName, columnIndex);
        seleniumWaits.untilElementIsVisible(element);
        seleniumActions.highLighterMethod(element);
        String count = element.getText().trim();
        return Integer.parseInt(count);
    }

    public void clickDashboardCount(String rowName, int columnIndex) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement element = getDashboardElement(rowName, columnIndex);
        seleniumActions.highLighterMethod(element);
        element.click();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public int getTableRowCount() {
        WebElement items = webDriver.findElement(itemsCount);
        seleniumActions.highLighterMethod(items);
        String text = items.getText().trim();
        String count = text.substring(text.indexOf("(") + 1, text.indexOf(" items"));
        logger.info("Items Count : " + count);
        return Integer.parseInt(count);
    }
    public void dashboardBoxCloseButton(){
        seleniumActions.jsClick(dashboardBoxCloseButton);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public String storeAndClickGuestName() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement guestElement = webDriver.findElement(guestNameLink);
        seleniumActions.highLighterMethod(guestElement);
        String guestName = guestElement.getText().trim();
        guestElement.click();
        return guestName;
    }

    public String getGuestProfileName() {
        WebElement profileName = webDriver.findElement(guestProfileName);
        seleniumWaits.untilElementIsVisible(profileName);
        seleniumActions.highLighterMethod(profileName);
        return profileName.getText().trim();
    }
    public void userViewTheTenderCapacityNumber(){
        seleniumWaits.untilElementIsVisible(webDriver.findElement(tenderCapacityBox));
        seleniumActions.highLighterMethod(webDriver.findElement(tenderCapacityBox));
    }
    public void userViewPrintOnManifestToggleSlider(){
        seleniumActions.highLighterMethod(webDriver.findElement(printOnManifestToggleSlider));
    }
    public void userClickOnResetButton(){
        seleniumActions.highLighterMethod(resetButton);
        seleniumActions.clickOnElement(resetButton);
    }

    public void clickIncrease(int times) {
        for (int i = 0; i < times; i++) {
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            webDriver.findElement(increaseBtn).click();
        }
        seleniumActions.highLighterMethod(webDriver.findElement(authorityCount));
    }

    public void clickDecrease(int times) {
        for (int i = 0; i < times; i++) {
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            webDriver.findElement(decreaseBtn).click();
        }
    }
    public void userShouldSeeTheErrorPopup(){
        seleniumWaits.untilElementIsVisible(webDriver.findElement(authorityCount));
        seleniumActions.highLighterMethod(webDriver.findElement(authorityCount));
    }
    public void userSelectLocationInDropdown(String location){
        setLocation(GlobalConstants.GANGWAY_LOCATION);
        seleniumActions.highLighterMethod(submitButton);
    }
    public void userDisplayModule(String module){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(webDriver.findElement(By.xpath("//a[normalize-space()='" + module + "']")));
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public void userDisplayTheSearchIcon(){
        seleniumWaits.untilElementIsClickable(searchIcon);
        seleniumActions.highLighterMethod(searchIcon);
    }


    public void selectFirstNCheckboxes(int count) {
        List<WebElement> options = webDriver.findElements(checkboxOptions);
        int limit = Math.min(count, options.size());

        for (int i = 0; i < limit; i++) {
            WebElement option = options.get(i);

            String state = option.getAttribute("class");
            if (!state.contains("mat-selected")) {
                seleniumActions.highLighterMethod(option);
                option.click();
                seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            }
        }
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public boolean isAnyTrainingPresent(String... trainings) {
        seleniumActions.scrollInToElement(drillAndTrainingHeading);
        for (String training : trainings) {

            List<WebElement> elements = webDriver.findElements(By.xpath("//span[normalize-space()='" + training + "']"));

            int count = elements.size();
            logger.info("Training: " + training + " Count: " + count);

            if (count > 0) {
                WebElement element = elements.get(0);
                seleniumActions.highLighterMethod(element);

                return true;
            }
        }
        return false;
    }
    public void userViewTheTenderCapacityCount(){
        WebElement tenderOpElement = webDriver.findElement(tenderOperation);
        seleniumWaits.untilElementIsVisible(tenderOpElement);
        seleniumActions.highLighterMethod(tenderOpElement);
        seleniumActions.highLighterMethod(tenderCapacity);
    }
    public void userViewOnLogIcon(){
        seleniumWaits.untilElementIsVisible(gangwayLogLink);
        seleniumActions.highLighterMethod(gangwayLogLink);
    }
    public void userClickOnReloadIcon(){
        seleniumWaits.untilElementIsVisible(reloadIcon);
        seleniumActions.highLighterMethod(reloadIcon);
        seleniumActions.clickOnElement(reloadIcon);
    }
    public void userClickOnThreeDots(){
        seleniumActions.scrollInToElement(threeDotsMenu);
        seleniumActions.highLighterMethod(threeDotsMenu);
    }
    public void userAddTheBoardingDenied(){
        seleniumActions.clickOnElement(threeDotsMenu);
        seleniumActions.highLighterMethod(webDriver.findElement(By.cssSelector("button[id*='add-boarding-denied']")));
    }
    public void userClickOnReasonAndSelectPersonOfInterestAndAddReasonDetail(String option) {
        seleniumActions.clickOnElement(webDriver.findElement(By.cssSelector("button[id*='add-boarding-denied']")));
        seleniumActions.selectDropdownOption("Reason", "Person of Interest");
        seleniumActions.typeValue(reasonDetailsInputBox, "Test");
        seleniumActions.highLighterMethod(saveButton);

    }

    public int getTitleCount()
    {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<WebElement> elements = webDriver.findElements(titleElements);

        for (int i = 0; i < elements.size(); i++)
        {
            WebElement ele = elements.get(i);
            seleniumActions.highLighterMethod(ele);
        }
        return elements.size();
    }

    public int verifyTrainingElements()
    {
        List<WebElement> elements = webDriver.findElements(trainingElements);
        for (int i = 0; i < elements.size(); i++)
        {
            WebElement ele = elements.get(i);
            seleniumActions.highLighterMethod(ele);
        }
        return elements.size();
    }

    public int getGuestDivCount()
    {
        List<WebElement> divElements = webDriver.findElements(guestDivElements);
        for (int i = 0; i < divElements.size(); i++)
        {
            WebElement ele = divElements.get(i);
            seleniumActions.highLighterMethod(ele);
        }
        return divElements.size();
    }

    public int getGuestSpanCount()
    {
        List<WebElement> spanElements = webDriver.findElements(guestSpanElements);
        for (int i = 0; i < spanElements.size(); i++)
        {
            WebElement ele = spanElements.get(i);
            seleniumActions.highLighterMethod(ele);
        }
        return spanElements.size();
    }

    public int getGangwaySummaryCount()
    {
        List<WebElement> elements = webDriver.findElements(gangwaySummaryElements);
        for (int i = 0; i < elements.size(); i++)
        {
            WebElement ele = elements.get(i);
            seleniumActions.highLighterMethod(ele);
        }
        return elements.size();
    }

    public boolean isCalendarCentered()
    {
        WebElement calendar = webDriver.findElement(calendarElement);
        seleniumActions.highLighterMethod(calendar);
        int browserWidth = webDriver.manage()
                .window()
                .getSize()
                .getWidth();
        int calendarStartX = calendar.getLocation().getX();

        int calendarWidth = calendar.getSize().getWidth();

        int calendarCenterPosition = calendarStartX + (calendarWidth / 2);

        int browserCenterPosition = browserWidth / 2;
        int centerDifference = Math.abs(
                calendarCenterPosition - browserCenterPosition);
        int tolerance = 113;
        return centerDifference <= tolerance;
    }
    public void actionButtionToBeClick(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(actionsButton);
        actionsButton.click();
        seleniumActions.highLighterMethod(visitorManagement);
    }
    public void navigateToCountTab(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        if (!countsElement.isEmpty()) {
            seleniumActions.scrollInToElement(countsElement.get(0));
            seleniumActions.highLighterMethod(countsElement.get(0));
            seleniumActions.jsClick(countsElement.get(0));
        }
    }
    public void clickOnTheVisitorManagementText() {
        seleniumActions.jsClick(visitorManagement);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        String parentWindowId = webDriver.getWindowHandle();
        Set<String> windowIds = webDriver.getWindowHandles();
        System.out.println("windowIds :: " + windowIds);
        SeleniumWaits.waitForVisibility(webDriver, commonPage.getProfileIconElement());
        for (String window : windowIds) {
            webDriver.switchTo().window(window);
        }
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }

    public boolean clickArrivalCalendarElement() {
        if (!arrivalList.isEmpty()) {
            seleniumActions.highLighterMethod(arrivalList.get(0));
            seleniumWaits.untilElementIsClickable(arrivalList.get(0));
            arrivalList.get(0).click();
            return true;
        }
        return false;
    }

    public boolean clickScheduledActivities() {
        if (!scheduledActivitiesList.isEmpty()) {
            seleniumActions.highLighterMethod(scheduledActivitiesList.get(0));
            seleniumWaits.untilElementIsClickable(scheduledActivitiesList.get(0));
            scheduledActivitiesList.get(0).click();
            return true;
        }
        return false;
    }

    public WebElement getTopLeftDetailElement(String detail) {

        return webDriver.findElement(By.xpath("//otalio-gangway-summary-data-container//div[contains(@class,'row')]//span[text()='" + detail + "']/following::otalio-chip[1]"));
    }

    public boolean verifyTopLeftSideDetails(String... details) {

        try {

            for (String detail : details) {

                WebElement element = getTopLeftDetailElement(detail);

                seleniumActions.highLighterMethod(element);

                String value = element.getText().trim();

                if (!element.isDisplayed() || value.isEmpty()) {
                    return false;
                }

                if (!value.matches("^\\d+(?::\\d{2})?$")) {
                    return false;
                }
            }
            return true;

        } catch (Exception e) {

            return false;
        }
    }
    public List<WebElement> getGangwayMovementCharts() {

        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);

        return webDriver.findElements(By.xpath("//otalio-chart-bar"));
    }

    public WebElement getButtonElement(String buttonName) {
        return webDriver.findElement(By.xpath("//*[self::p or self::span][normalize-space()='"+buttonName+"']"));
    }
    public void userClickTheButton(String button1){
        WebElement buttonElement = getButtonElement(button1);
        seleniumWaits.untilElementIsVisible(buttonElement, GlobalConstants.SHORT_WAIT);
        seleniumActions.jsClick(buttonElement);

    }


    public String getRandomCabinNumber() {
        List<String> cabins = cabinNumbers.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(text -> !text.isEmpty())
                .filter(text -> text.matches("\\d+"))
                .collect(Collectors.toList());
        if (cabins.isEmpty()) {
            throw new RuntimeException("No valid cabin numbers found.");
        }
        return cabins.get(new Random().nextInt(cabins.size()));
    }

    private String filterName(String filterName) {
        return "//div[contains(text(),'" + filterName + "')]/following-sibling::span/../../following-sibling::icon/*[name()='svg']";
    }

    private String status(String status) {
        return "//span[normalize-space()='" + status + "']/..//preceding-sibling::mat-pseudo-checkbox";
    }

    public boolean selectCrewByCabinNumber(String filterName,String reservationStatus) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        try {
            seleniumActions.clickOnElement(By.xpath(filterName(filterName)));
            cabinDropDownSeachBar.clear();
            cabinDropDownSeachBar.sendKeys(reservationStatus);
            seleniumActions.clickOnElement(By.xpath(status(reservationStatus)));
            seleniumActions.pressEscapeKey();
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.pressEscapeKey();
            return true;
        } catch (Exception e) {
            return false;}
    }

    public String getDisplayedCabinNumber() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        if (cabinNumbers.isEmpty()) {
            throw new RuntimeException("No cabin number found.");
        }
        seleniumActions.highLighterMethod(cabinNumbers.get(1));
        return cabinNumbers.get(1).getText().trim();
    }
    public boolean isDirectionDisplayed(String mode) {
        WebElement direction = webDriver.findElement(By.xpath("//mat-label[.//*[local-name()='use' and @href='#gangway-" + mode.toLowerCase() + "']]"));
        seleniumActions.highLighterMethod(direction);
        return direction.isDisplayed();
    }

    public boolean isAgeGroupToggleDisplayed(String ageGroup) {
        WebElement toggle = webDriver.findElement(By.xpath("//mat-slide-toggle[contains(@id,'" + ageGroup + "')]"));
        seleniumActions.highLighterMethod(toggle);
        return toggle.isDisplayed();
    }

    public void locationInStartlocationGate(){
        seleniumActions.selectDropdownOption(ShipSecurityConstants.LOCATION,ShipSecurityConstants.DROPDOWN_VALUE_ONE);
        seleniumActions.highLighterMethod(submitButton);
    }
    public void userAddTheMedicalFlag(){
        seleniumActions.clickOnElement(addBoardingDenied);
        seleniumActions.selectDropdownOption(GlobalConstants.REASON, ShipSecurityConstants.MEDICAL_REASON);
        seleniumActions.typeValue(reasonDetailsInputBox, ExcursionManagementConstants.COMMENT_MESSAGE);
        seleniumActions.highLighterMethod(saveButton);
    }

    private String statusCheckbox(String value) {
        return "//div[normalize-space()='" + value + "']/..//preceding-sibling::mat-pseudo-checkbox";
    }

    private String checkedStatusCheckbox(String value) {
        return "//div[normalize-space()='" + value + "']/..//preceding-sibling::mat-pseudo-checkbox[contains(@class,'checkbox-checked')]";
    }

    private String successDropdownFilter(String value) {
        return "//button[contains(@id,'complex-filter-label-" + value + "')]//icon";
    }

    public void selectSuccessFilter(String filterName, String value) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(By.xpath(successDropdownFilter(filterName)));
        List<WebElement> checkedYes = webDriver.findElements(By.xpath(checkedStatusCheckbox(GlobalConstants.YES)));
        if (!checkedYes.isEmpty()) {
            checkedYes.get(0).click();
        }
        WebElement option = webDriver.findElement(By.xpath(statusCheckbox(value)));
        option.click();
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.pressEscapeKey();
    }

    public List<String> getDenyReasons() {

        List<String> denyReasons = new ArrayList<>();

        for (WebElement element : denyReasonsColumn) {
            String reason = element.getText().trim();

            if (!reason.isEmpty()) {
                seleniumActions.highLighterMethod(element);
                denyReasons.add(reason);
            }
        }
        return denyReasons;
    }
    public String getInPortManningValue() {
        seleniumWaits.untilElementIsVisible(txtInPortManningValue);
        return seleniumActions.getTextMessage(txtInPortManningValue).trim();
    }

    public String enterRandomClothesText() {
        String randomClothesText = "Clothes_" + System.currentTimeMillis();
        seleniumWaits.untilElementIsVisible(clothesInput);
        clothesInput.clear();
        clothesInput.sendKeys(randomClothesText);
        seleniumActions.highLighterMethod(clothesInput);
        return randomClothesText;
    }

    public void userClickOnAndSelectAndAddReasonDetail(String dropdown, String text){
        seleniumActions.selectDropdownOption(dropdown, text);
        seleniumActions.typeValue(reasonDetailsInputBox, "Test");
        seleniumActions.highLighterMethod(saveButton);
    }
    public void userClickOnThreeDotsIcon(){
        seleniumActions.scrollInToElement(threeDotsMenu);
        seleniumActions.highLighterMethod(threeDotsMenu);
        seleniumActions.clickOnElement(threeDotsMenu);
    }


}
