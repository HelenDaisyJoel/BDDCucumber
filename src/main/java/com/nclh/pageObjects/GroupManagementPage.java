package com.nclh.pageObjects;

import ch.qos.logback.core.boolex.Matcher;
import com.nclh.constants.ExcursionManagementConstants;
import com.nclh.constants.GlobalConstants;
import com.nclh.constants.GroupManagementConstants;
import com.nclh.constants.SafetyManagementConstatnts;
import com.nclh.managers.FileReaderManager;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v128.filesystem.model.File;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class GroupManagementPage {
    private final WebDriver webDriver;
    private final SeleniumActions seleniumActions;
    private final SeleniumWaits seleniumWaits;
    private final CommonFunctions commonFunctions;
    private CommonPage commonPage;
    public static final Logger logger
            = LoggerFactory.getLogger(SeleniumActions.class);

    @Setter
    @Getter
    String groupCode;

    public GroupManagementPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        seleniumActions = new SeleniumActions(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonFunctions = new CommonFunctions();
        commonPage = new CommonPage(webDriver);
    }

    @FindBy(xpath = "//div[text()='Cabin Number:']/../../../icon")
    private WebElement cabinNumberDropDown;


    @FindAll({
            @FindBy(xpath = "//div/b[text()='Reservation Status:']/../../icon"),
            @FindBy(xpath = "//div[text()='Reservation Status:']/../descendant::div[contains(@class,'text-truncate')]")
    })
    private WebElement reservationStatus;

    @FindBy(xpath = "//div[text()='Reservation Status:']/../../following-sibling::icon")
    private WebElement reservationStatusDropDown;

    @FindBy(css = "[placeholder='Cabin Number']")
    private WebElement cabinNumberSearchBox;

    @FindBy(tagName = "mat-pseudo-checkbox")
    private WebElement cabinNumberCheckbox;

    @FindBy(xpath = "//span[normalize-space()='Guests']")
    private WebElement guestsHeading;

    @FindBy(xpath = "(//td[3][@role='gridcell']/div/a)")
    private List<WebElement> groupNameLinkList;

    @FindBy(css = "[icon*='plus']")
    private WebElement plusButton;

    @FindBy(css = "[class*='pseudo-checkbox-checked']")
    private List<WebElement> checkBoxes;

    @FindBy(xpath = "//h2[text()='Select Accounts']")
    private WebElement selectAccountHeading;

    @FindBy(xpath = "//span[normalize-space()='Group Reservation Search']")
    private WebElement groupReservationHeading;

    @FindBy(css = "span [icon*='plus'][color]")
    private List<WebElement> plusButtonPassenger;

    @FindBy(xpath = "//span[normalize-space()='Select']")
    private WebElement selectButton;

    @FindBy(xpath = "//mat-label[text()='Role']")
    private WebElement roleButton;

    @FindBy(css = "[type='search']")
    private WebElement searchBox;

    @FindBy(xpath = "//span[normalize-space()='Select']/../../../../descendant::otalio-passenger-picture/following-sibling::div/div[contains(@class,'tooltip')]")
    private WebElement selectedUserName;

    @FindBy(css = "[aria-label='Items per page: 100']")
    private WebElement perPage100Button;

    @FindBy(xpath = "//div[text()='Loyalty']")
    private WebElement loyaltyTab;

    @FindBy(xpath = "//otalio-infinite-select[contains(@id,'loyalty-program')]")
    private WebElement loyaltyProgramDropdown;

    @FindBy(xpath = "//otalio-infinite-select[contains(@id,'loyalty-tier')]")
    private WebElement loyaltyTierDropdown;

    @FindBy(css = "[icon='hide-columns']")
    private WebElement hideColumnButton;

    @FindBy(xpath = "//*[contains(text(),'Reservation Status') or text()='Status']")
    private WebElement reservationStatusColumnHeading;

    @FindBy(xpath = "//tbody/tr/td[@role='columnheader']")
    private List<WebElement> tableHeaderItems;

    @FindBy(css = "[data-placeholder='Code']")
    private WebElement codeInputBox;

    @FindBy(css = "[data-placeholder='External Code']")
    private WebElement externalCodeInputBox;

    @FindBy(css = "[data-placeholder='Description']")
    private WebElement descriptionInputBox;

    @FindBy(xpath = "//span[text()='Group Type'][contains(@class,'select')]")
    private WebElement groupTypeDropDown;

    @FindBy(xpath = "//span[text()='Group Category'][contains(@class,'select')]")
    private WebElement groupCategoryDropDown;

    @FindBy(css = "[role='option'] > span > span")
    private List<WebElement> optionsDropDown;

    @FindBy(css = "button[id*='Code'] > icon")
    private List<WebElement> codeDropdown;

    @FindBy(css = "[placeholder='Code']")
    private WebElement searchCodeInputBox;

    @FindBy(css = "mat-option[role='option']")
    private List<WebElement> accountTypeDropDownList;

    @FindBy(css = "otalio-group-card div div h1")
    private WebElement groupName;

    @FindBy(css = "[formcontrolname='recordValue']")
    private List<WebElement> communicationInputBox;

    @FindBy(css = "span.mat-select-min-line")
    private List<WebElement> visibleValueATDropDown;

    @FindBy(css = "[aria-label='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//span[text()='Ship']")
    private WebElement shipDropdown;

    @FindBy(css = "[data-placeholder='Active From']")
    private WebElement activeFromDateInputBox;

    @FindBy(css = "[data-placeholder='Active To']")
    private WebElement activeToDateInputBox;

    @FindBy(css = "[data-placeholder='Estimated Member Count']")
    private WebElement estimatedMemberCount;

    @FindBy(css = "[data-placeholder='Expected Member Count']")
    private WebElement expectedMemberCount;

    @FindBy(xpath = "//span[normalize-space()='Add']")
    private WebElement reservationAddButton;

    @FindBy(xpath = "//mat-row/*/span")
    private List<WebElement> reservationDetails;

    @FindBy(xpath = "(//table/tbody/tr/td[4][@role='gridcell']/div/span)")
    private List<WebElement> cabinNumbers;

    @FindBy(css = "[aria-label='Items per page: 100']")
    private WebElement pagination100;

    @FindBy(css = "[formcontrolname='comment']")
    private WebElement commentInputBox;

    @FindBy(xpath = "(//otalio-payment-method-assignment-form/descendant::input[@formcontrolname='comment'])[2]")
    private WebElement commentBox;

    @FindBy(css = "[icon^='delete']")
    private List<WebElement> deleteButton;

    @FindBy(xpath = "//mat-form-field[.//*[normalize-space(text())='Payment method']]")
    private List<WebElement> paymentMethods;

    @FindBy(css = "[icon^='plus']")
    private WebElement addIcon;

    By profileImage = By.cssSelector(".passenger-picture.cursor-pointer");

    @FindBy(xpath = "//otalio-decode-mask-button/icon")
    private List<WebElement> hideIcon;

    @FindBy(xpath = "//otalio-folio-chooser/div/div")
    private List<WebElement> folioHeaders;

    @FindBy(css = "[id='folio-chooser-add-button']")
    private WebElement addFolioHeader;
    @FindBy(css = "[type='search']")
    private WebElement searchInputBox;
    By crewProfilePic = By.xpath("(//otalio-guest-search-container//otalio-passenger-picture)[1]");
    By addGuestIcon = By.xpath("(//mat-icon[normalize-space()='person']/ancestor::button)[last()]");
    By addGuestSaveButton = By.xpath("//button[@color='primary' and not(@disabled)]//span[normalize-space()='Save']");
    By txtAllFolio = By.xpath("//div[contains(text(),'All Folio')]");
    By txtFolioList = By.xpath("//otalio-folio-chooser//div[contains(text(),'Folio') and not(contains(text(),'Package'))]");
    @FindBy(css = "#folio-chooser-add-button")
    private WebElement btnAddFolio;
    By itemsCount = By.xpath("//div[contains(text(),'items')]");
    By groupGuestFullName = By.xpath("//*[@aria-rowindex='1']//td[position()=2 or position()=3]");
    @FindBy(xpath = "//otalio-passenger-groups//*[contains(text(),'Groups')]")
    private WebElement groupHeader;
    @FindBy(xpath = "//otalio-passenger-groups//div[contains(@class,'align-items-center justify-content')]")
    private List<WebElement> guestGroupList;
    @FindBy(xpath = "//tr[.//icon[contains(@icon,'close')]]//button[@mattooltip='Delete']")
    private List<WebElement> deleteIcons;

    @FindBy(xpath = "//*[@mattooltip='Add New Special Request']")
    private WebElement addNewSpecialRequest;
    @FindBy(id = "breadcrumbs-title")
    private WebElement pageHeader;
    @FindBy(xpath = "//*[contains(text(),'Delete Special request')]")
    private WebElement deleteSpecialRequestHeader;
    @FindBy(css = "[id='confirmation-dialog-reason-textarea']")
    private WebElement reasonTextBox;
    @FindBy(css = "[id='special-request-editor-add-button']")
    private WebElement addButtonSpecialRequest;
    By groupNameList = By.xpath("//*[starts-with(@id,'groups-reservation-list-page-reservation-name-')]");
    @FindBy(xpath = "//input[@placeholder='Search by Group Name']")
    private WebElement searchBoxGroupName;
    @FindBy(xpath = "//*[@formarrayname='contacts']//div[contains(@class,'col')]//div[contains(@class,'row')]")
    private List<WebElement> communicationInfoBoxes;
    @FindBy(css = ".dx-row.dx-data-row.dx-row-lines td div a")
    private List<WebElement> linksList;
    @FindBy(xpath = "//otalio-update-group-page-reservations-container//*[normalize-space(text())='Reservations']")
    private WebElement reservationsHeader;
    @FindBy(xpath = "//*[@mattooltip='Edit']")
    private List<WebElement> reservationsEditPencilIcon;
    @FindBy(xpath = "//*[contains(text(),'Add Reservation')]")
    private WebElement addReservationHeader;
    @FindBy(xpath = "//input[@id='report-overview-search-input']")
    private WebElement txtSearchReport;
    @FindBy(xpath = "//input[@id='complex-filter-search-input']")
    private WebElement searchReport;
    @FindBy(xpath = "//div[@id='otalio-time-picker--field']//input[@class='dx-texteditor-input']")
    private WebElement txtDate;
    @FindBy(xpath = "//button[@id='report-overview-detail-refresh-button' and .//span[normalize-space()='Preview']]")
    private WebElement previewButton;
    @FindBy(id = "report-overview-preview")
    private WebElement reportPreviewFrame;
    @FindBy(id = "toast-message-content")
    private WebElement toastMessage;
    @FindBy(xpath = "//*[local-name()='use' and starts-with(@href, '#close-')]")
    private WebElement closeToastMessageIcon;
    @FindBy(xpath = "//tbody/tr[not(contains(@class,'header-row'))]")
    private List<WebElement> evenLocation;
    @FindBy(xpath = "//div[@otalioeventappointment and contains(@class,'border-color-orange')]")
    private List<WebElement> existingEvents;
    @FindBy(xpath = "//button[contains(normalize-space(), 'Timeline Month')]")
    private WebElement btnMonth;
    @FindBy(xpath = "//otalio-activity-status")
    private WebElement bookingStatusLink;
    @FindBy(xpath = "//input[@placeholder='Accounting']")
    private WebElement accountingInputBox;
    @FindBy(xpath = "//input[@placeholder='Contact']")
    private WebElement contactInputBox;
    @FindBy(xpath = "//*[contains(@class,'time-indicator')]/ancestor::dx-scheduler//table//td[contains(@class,'dx-scheduler-last-group-cell') and not(.//div[contains(@class,'disable-date-gray')])]")
    private List<WebElement> availableTimeSlots;
    @FindBy(xpath = "//button[@mattooltip='Delete']")
    private WebElement deleteEventIcon;

    @FindBy(css = "[id='add-temporary-event-form-confirm-button']")
    private WebElement addButtonEventBox;

    @FindBy(xpath = "//*[@id='event-editor-step1-add-and-finish-button' or @id='event-editor-step1-save-and-finish-button']")
    private WebElement btnSaveAndFinish;
    @FindBy(css = "[id='add-temporary-event-form-quick-add-button']")
    private WebElement btnQuickAdd;
    @FindBy(xpath = "//i[normalize-space()='close']")
    private List<WebElement> closeIcon;
    @FindBy(xpath = "//button[contains(.,'Timeline Day')]")
    private WebElement btnTimelineDay;

    @FindBy(xpath = "//button[contains(.,'Timeline Week')]")
    private WebElement btnTimelineWeek;

    @FindBy(xpath = "//button[contains(.,'Timeline Month')]")
    private WebElement btnTimelineMonth;

    @FindBy(xpath = "//div[contains(@class,'fc-event')]")
    private List<WebElement> calendarEvents;

    @FindBy(xpath = "//textarea[@id='note-form-note-input']")
    private WebElement addNewTextBox;
    @FindBy(css = "[id='items-save-button']")
    private WebElement addButtonNewTextBox;

    @FindBy(css = "[id='event-editor-step3-save-and-finish-button'], [id='event-editor-step3-add-and-finish-button']")
    private WebElement saveAndFinishButtonNewTextBox;

    @FindBy(xpath = "(//table//tbody//tr/td[2])[2]")
    private WebElement txtAddedNote;
    @FindBy(css = "[id='items-list-add-items-button']")
    private WebElement addButtonItem;
    @FindBy(xpath = "//*[@mat-dialog-title and text()='Add items']")
    private WebElement addItemHeader;
    @FindBy(xpath = "//input[@id='items-quantity-input']")
    private WebElement quantityInputBox;

    @FindBy(xpath = "//*[@id='event-editor-step2-add-and-finish-button']")
    private WebElement btnSaveAndFinishInItemPage;
    @FindBy(xpath = "//*[normalize-space()='Report Format']")
    private WebElement reportFormatePopUpBox;
    @FindBy(xpath = "//*[@id='reporting-result-export-confirm-button']")
    private WebElement exportConfirmButton;
    @FindBy(xpath = "//*[normalize-space()='New Message']")
    private WebElement newMessageHeader;
    @FindBy(xpath = "//div[@id='otalio-tag-box-to-dropdown-button']/ancestor::dx-tag-box//input[@role='combobox']")
    private WebElement txtToEmail;

    @FindBy(xpath = "(//div[contains(@class,'dx-item dx-list-item')])[1]")
    private WebElement firstEmailSuggestion;
    @FindBy(xpath = "//*[@id='reporting-email-save-button']")
    private WebElement sendButton;
    private By successPopUpMessage = By.cssSelector("[id='toast-title-success']");
    @FindBy(xpath = "(//mat-pseudo-checkbox)[1]")
    private WebElement firstCheckBox;
    @FindBy(xpath = "//*[@role='option' and normalize-space()='Assigned']")
    private WebElement assignOption;
    @FindBy(xpath = "//button[normalize-space()='Add Event']")
    private WebElement btnAddEvent;
    @FindBy(css = "[id*='mat-dialog-title']")
    private WebElement addNewEvent;
    By successToast = By.xpath("//div[@id='toast-title-success']");
    By deletedOption = By.xpath("//button[@mattooltip='Delete']");







    public void clickOnAddFolioHeader(){
        seleniumActions.clickOnElement(addFolioHeader);
    }

    public int getHeaderCount(){
        seleniumWaits.untilListElementIsVisible(folioHeaders);
        return webDriver.findElements(By.xpath("//otalio-folio-chooser/div/div")).size();
    }

    public boolean checkHideIcon(){
        //seleniumActions.scrollInToElement(hideIcon);
        seleniumActions.scrollInToElement(webDriver.findElement((By.xpath("(//otalio-decode-mask-button/icon)[1]"))));
        return webDriver.findElements((By.xpath("//otalio-decode-mask-button/icon"))).size()>0;
    }


    public void clickOnAddIcon(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.scrollInToElement(addIcon);
        seleniumActions.clickOnElement(addIcon);
        if(webDriver.findElements(By.cssSelector("[icon^='plus']")).size()>0){
            seleniumActions.moveToElementAndClick(addIcon);
        }
    }
    public void deletePaymentMethod(){
        seleniumActions.clickOnElement(deleteButton.get(1));
    }

    public int getNumberOfPaymentMethods(){
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsClickable(paymentMethods.get(0));
        return seleniumActions.getNumberOfElements(paymentMethods);
    }

    public void enterComment(String comment) {
        seleniumActions.clickOnElement(commentBox);
        seleniumActions.typeValue(commentBox, comment);
    }

    public String getCabinNumber(List<String> list) {
        seleniumWaits.untilElementIsClickable(pagination100);
        seleniumActions.clickOnElement(pagination100);
        List<String> withSensitiveData;
        try{
            withSensitiveData = seleniumActions.getListOfElementsText(cabinNumbers);
        }catch (StaleElementReferenceException ste){
            withSensitiveData = seleniumActions.getListOfElementsText(webDriver.findElements(By.xpath("(//table/tbody/tr/td[4][@role='gridcell']/div/span)")));
        }
        System.out.println(withSensitiveData);
        System.out.println(withSensitiveData.size());
        System.out.println(list);
        withSensitiveData.removeAll(list);
        System.out.println(withSensitiveData);
        System.out.println(withSensitiveData.size());

        int randomNumber = commonFunctions.getRandomNumber(0, withSensitiveData.size());
        return withSensitiveData.get(randomNumber);
    }

    public List<String> getReservationDetails() {
        seleniumWaits.untilListElementIsVisible(reservationDetails);
        return seleniumActions.getListOfElementsText(reservationDetails);
    }

    public void clickOnReservationAddButton() {
        seleniumWaits.untilElementIsClickable(reservationAddButton);
        seleniumActions.clickOnElement(reservationAddButton);

        if (webDriver.findElements(By.xpath("//span[normalize-space()='Add']")).size() > 0) {
            seleniumActions.moveToElementAndClick(reservationAddButton);
        }

        if (webDriver.findElements(By.xpath("//span[normalize-space()='Add']")).size() > 0) {
            seleniumActions.pressEnterKey();
        }

        if (webDriver.findElements(By.xpath("//span[normalize-space()='Add']")).size() > 0) {
            seleniumActions.clickOnElement(reservationAddButton);
        }
    }

    public void addReservation(String fromDate, String toDate, String estimate, String expected) {
        seleniumActions.clickOnElement(addButton);
        seleniumWaits.untilElementIsVisible(shipDropdown);
        seleniumActions.clickOnElement(shipDropdown);
        //modifyCommunicationType();
        selectValueFromDropDown();
        seleniumActions.typeValue(activeFromDateInputBox, fromDate);
        seleniumActions.typeValue(activeToDateInputBox, toDate);
        seleniumActions.typeValue(estimatedMemberCount, estimate);
        seleniumActions.typeValue(expectedMemberCount, expected);
    }

    public String getSelectedValueFromCommunicationTypeDropDown() {
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsClickable(visibleValueATDropDown.get(0));
        return seleniumActions.getTextMessage(visibleValueATDropDown.get(0));
    }

    public void modifyCommunicationType() {
        seleniumWaits.untilElementIsClickable(accountTypeDropDownList.get(1));
        seleniumActions.scrollInToElement(accountTypeDropDownList.get(1));
        try {
            seleniumActions.clickOnElement(accountTypeDropDownList.get(1));
        } catch (StaleElementReferenceException ste) {
            webDriver.findElements(By.cssSelector("mat-option[role='option']")).get(1).click();
        }
    }

    public int getNumberOfCommunicationInputs() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return communicationInputBox.size();
    }

    public int getNumberOfHideIcon() {
        seleniumActions.sleep(3000);
        return hideIcon.size();
    }

    public void enterCommunicationValue(String email) {
        int lastIndex = communicationInputBox.size();
        seleniumActions.sendKeys(communicationInputBox.get(lastIndex - 1), email);
    }

    public void clickOnGroupTab(String tab) {
        seleniumActions.clickOnElement(By.xpath("//div[normalize-space()='" + tab + "'][@role='tab']/div"));
    }

    public String getGroupName() {
        return seleniumActions.getTextMessage(groupName);
    }

    public void selectValueFromDropDown() {
        seleniumActions.sleep(4000);
        seleniumWaits.untilElementIsClickable(accountTypeDropDownList.get(1));
        seleniumActions.scrollInToElement(accountTypeDropDownList.get(1));
        try {
            seleniumActions.clickOnElement(accountTypeDropDownList.get(1));
        } catch (StaleElementReferenceException ste) {
            webDriver.findElements(By.cssSelector("mat-option[role='option']")).get(1).click();
        }
    }

    public void selectEmailFromDropDown() {
        seleniumActions.sleep(4000);
        seleniumWaits.untilElementIsClickable(accountTypeDropDownList.get(0));
        seleniumActions.scrollInToElement(accountTypeDropDownList.get(0));
        try {
            seleniumActions.clickOnElement(accountTypeDropDownList.get(0));
        } catch (StaleElementReferenceException ste) {
            webDriver.findElements(By.cssSelector("mat-option[role='option']")).get(0).click();
        }
    }

    public void searchForGroupCode(String groupCodeInputNumber) {
        seleniumActions.clickOnElement(codeDropdown.get(0));
        seleniumActions.typeValue(searchCodeInputBox, groupCodeInputNumber);
        seleniumActions.sleep(5000);
    }

    public void addGroup() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumWaits.untilElementIsVisible(plusButton);
        seleniumActions.clickOnElement(plusButton);
//        if (webDriver.findElements(By.cssSelector("[icon*='plus']")).size() > 0) {
//            seleniumActions.clickOnElement(plusButton);
//        }
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        setGroupCode(String.valueOf(commonFunctions.getRandomNumber(1000,9999)));
        seleniumActions.typeValue(codeInputBox, "XXXCODE" + getGroupCode());
        seleniumActions.sleep(1000);
        seleniumActions.typeValue(externalCodeInputBox, "XXXECODE" + String.valueOf(commonFunctions.getRandomNumber(10000, 100000)));        seleniumActions.sleep(1000);
        seleniumActions.typeValue(descriptionInputBox, "XXXDCODE" + String.valueOf(commonFunctions.getRandomNumber(10000, 100000)));        seleniumActions.sleep(1000);
        seleniumActions.clickOnElement(groupTypeDropDown);
        seleniumWaits.untilElementIsVisible(optionsDropDown.get(0));
        seleniumActions.clickOnElement(optionsDropDown.get(0));
        seleniumActions.sleep(1000);
        seleniumActions.clickOnElement(groupCategoryDropDown);
        seleniumActions.sleep(2000);
        seleniumWaits.untilElementIsClickable(optionsDropDown.get(0));
        seleniumActions.clickOnElement(optionsDropDown.get(0));
        seleniumActions.sleep(1000);
        seleniumActions.typeValue(codeInputBox, "1");
    }

    public List<String> getColumnNames() {
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsClickable(tableHeaderItems.get(3));
        return seleniumActions.getListOfElementsText(tableHeaderItems);
    }

    public void dragItemToTheColumnHeader(String name) {
        String dragItem = "//*[@aria-label='" + name + "']";
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath(String.format(dragItem, name))));
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(dragItem, name))));
        seleniumActions.dragAndDrop(
                webDriver.findElement(By.xpath(String.format(dragItem, name))),
                reservationStatusColumnHeading
        );
        seleniumActions.sleep(5000);
    }
//    public void dragItemToTheColumnHeader(String name) {
//        try {
//            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
//            String dragItemXpath = "//*[@aria-label='" + name + "']";
//            seleniumActions.scrollInToElement(webDriver.findElement(By.xpath(dragItemXpath)));
//            WebElement source = webDriver.findElement(By.xpath(dragItemXpath));
//            WebElement target = reservationStatusColumnHeading;
//            commonPage.waitForDomReady();
//            Actions actions = new Actions(webDriver);
//            actions.moveToElement(source)
//                    .clickAndHold()
//                    .moveToElement(target)
//                    .release()
//                    .build()
//                    .perform();
//
//        } catch (Exception e) {
//            logger.info("while dragging: " + e.getMessage());
//        }
//        commonPage.waitForDomReady();
//    }

    public void clickOnHideColumnButton() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(hideColumnButton);
    }

    public Set<String> getListOfWebElements() {
        seleniumActions.sleep(3000);
        seleniumActions.scrollInToElement(loyaltyTab);
        seleniumActions.clickOnElement(loyaltyTab);
        seleniumWaits.untilElementIsClickable(loyaltyProgramDropdown);
        seleniumActions.scrollInToElement(loyaltyProgramDropdown);
        seleniumWaits.untilElementIsClickable(loyaltyProgramDropdown);
        Set<String> loyaltyElements = new HashSet<>();
        for (int i = 0; i < 2; i++) {
            loyaltyElements.add(seleniumActions.getTextMessage(loyaltyProgramDropdown));
            loyaltyElements.add(seleniumActions.getTextMessage(loyaltyTierDropdown));
        }
        System.out.println(loyaltyElements);
        return loyaltyElements;
    }

    public String getUserFullName() {
        return seleniumActions.getTextMessage(selectedUserName);
    }

    public boolean isUserFullNamePresent(String firstName, String lastName) {
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsClickable(perPage100Button);
        seleniumActions.clickOnElement(perPage100Button);
        seleniumActions.sleep(3000);
        String path = "//table/tbody/tr/td[@role='gridcell'][text()='%s']/following-sibling::td[text()='%s']";
        return seleniumActions.isElementDisplayed(webDriver.findElement(By.xpath(String.format(path, firstName, lastName))));
    }

    public void searchGuest(String guest) {
        seleniumWaits.untilElementIsVisible(searchBox);
        seleniumActions.typeValue(searchBox, guest);
    }

    public void selectRole(String role) {
        seleniumWaits.untilElementIsClickable(roleButton);
        seleniumActions.clickOnElement(roleButton);
        String roleOption = "//span[normalize-space()='%s']";
        seleniumActions.clickOnElement(By.xpath(String.format(roleOption, role)));
    }

    public void addUser() {
        seleniumWaits.untilElementIsClickable(plusButtonPassenger.get(0));
        seleniumActions.clickOnElement(plusButtonPassenger.get(0));
    }

    public void clickOnSelectButton() {
        seleniumWaits.untilElementIsClickable(selectButton);
        seleniumActions.clickOnElement(selectButton);
    }

    public void selectUser() {
        seleniumWaits.untilElementIsClickable(plusButtonPassenger.get(0));
        seleniumActions.clickOnElement(plusButtonPassenger.get(0));
        seleniumWaits.untilElementIsClickable(selectButton);
        seleniumActions.clickOnElement(selectButton);
    }

    public void clearReservationStatus() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try {
            //seleniumWaits.untilElementIsClickable(reservationStatusDropDown);
            seleniumActions.clickOnElement(reservationStatusDropDown);
        }catch (Exception e){
           // seleniumWaits.untilElementIsClickable(reservationStatus);
            seleniumActions.clickOnElement(reservationStatus);
        }
        for (int i = 0; i < checkBoxes.size(); i++) {
            seleniumActions.sleep(2000);
            seleniumActions.clickOnElement(checkBoxes.get(i));
        }
        if (checkBoxes.size() > 0) {
            checkBoxes.get(0).click();
        }
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(500);
        seleniumActions.pressEscapeKey();
        try {
            seleniumActions.sleep(1000);
            seleniumActions.moveToElement(groupReservationHeading);
            seleniumActions.jsClick(groupReservationHeading);
        }catch (Exception e){
            seleniumActions.jsClick(selectAccountHeading);
            seleniumActions.moveToElement(selectAccountHeading);
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(FileReaderManager.getInstance().getConfigFileReader().getTime()));
    }

    /*public void clickOnTab(String tab) {
        String tabName = "//div[contains(text(),'%s')]";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(tabName, tab))));
        seleniumActions.scrollInToElement(webDriver.findElement(By.xpath(String.format(tabName, tab))));
        seleniumActions.clickOnElement(By.xpath(String.format(tabName, tab)));
    }*/

    public void openGroup() {
        seleniumWaits.untilElementIsClickable(groupNameLinkList.get(0));
        seleniumActions.clickOnElement(groupNameLinkList.get(0));
    }

    /*public void clickOnServiceLink(String link) {
        String linkXpath = "//a[normalize-space()='%s']";
        seleniumWaits.untilElementIsPresent(By.xpath(String.format(linkXpath, link)));
        seleniumActions.clickOnElement(By.xpath(String.format(linkXpath, link)));
    }*/

    public void clickOnCheckBox() {
        seleniumWaits.untilElementIsClickable(cabinNumberCheckbox);
        seleniumActions.sleep(1000);
        seleniumActions.clickOnElement(cabinNumberCheckbox);
        seleniumWaits.untilElementIsClickable(guestsHeading);
        seleniumActions.moveToElementAndClick(guestsHeading);
    }

    public void enterCabinNumber(String cabinNumber) {
        seleniumWaits.untilElementIsClickable(cabinNumberSearchBox);
        seleniumActions.clickOnElement(cabinNumberSearchBox);
        seleniumActions.sleep(1000);
        seleniumActions.typeValue(cabinNumberSearchBox, cabinNumber);
    }

    public void clickOnCabinNumberDropDown() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(cabinNumberDropDown);

    }
    public void updateReservation(String fromDate, String toDate, String estimate, String expected){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        //seleniumActions.sendKeys(activeFromDateInputBox, fromDate);
        seleniumActions.sendKeys(activeToDateInputBox, toDate);
        seleniumActions.sendKeys(estimatedMemberCount, estimate);
        seleniumActions.sendKeys(expectedMemberCount, expected);
    }
    public void searchGuestMemeber(){
        seleniumWaits.untilElementIsVisible(searchBox);
        seleniumActions.typeValue(searchBox, "a");
    }
    public By getMatSelectByLabel(String labelName) {

        String xpath = "//mat-form-field[.//*[normalize-space(text())='" + labelName + "']]//div[@aria-label='Select' or contains(@class,'mat-select-arrow-wrapper')]";

        return By.xpath(xpath);
    }
    public void userClickOnGroupInfoTabAndValueDropdown(String labelName, int index){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        By locator = getMatSelectByLabel(labelName);
        List<WebElement> elements = webDriver.findElements(locator);
        if (elements.isEmpty()) {
            throw new NoSuchElementException("No mat-select found for label: " + labelName);
        }
        WebElement lastElement = elements.get(elements.size() - 1);
        lastElement.click();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<WebElement> options = webDriver.findElements(By.cssSelector("mat-option[role='option']"));

        if (options.isEmpty()) {
            throw new NoSuchElementException("No dropdown options found.");
        }

        if (index < 0 || index >= options.size()) {
            throw new IndexOutOfBoundsException(
                    "Invalid index: " + index + ". Available options: " + options.size());
        }
        WebElement optionToSelect = options.get(index);
        optionToSelect.click();
    }
    public void userClickOnProfile(){
        try{
            seleniumWaits.untilElementIsClickable(webDriver.findElement(profileImage));
            seleniumActions.scrollInToElement(webDriver.findElement(profileImage));
            seleniumActions.clickOnElement(webDriver.findElement(profileImage));
        }catch(Exception ex){
            userViewPlusIconInGroupPage();
            seleniumActions.clickOnElement(plusButton);
            seleniumWaits.untilElementIsVisible(searchInputBox);
            seleniumActions.typeValue(searchInputBox, GlobalConstants.GUEST_PREFIX_NAME);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            seleniumActions.clickOnElement(webDriver.findElement(crewProfilePic));
            seleniumWaits.untilElementIsVisible(selectButton);
            seleniumActions.highLighterMethod(selectButton);
            seleniumActions.clickOnElement(selectButton);
            seleniumWaits.untilElementIsClickable(webDriver.findElement(profileImage));
            seleniumActions.scrollInToElement(webDriver.findElement(profileImage));
            seleniumActions.clickOnElement(webDriver.findElement(profileImage));
        }

    }
    public void userViewPlusIconInGroupPage(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumWaits.untilElementIsVisible(plusButton);
        seleniumActions.highLighterMethod(plusButton);
    }
    public void userViewDeleteIconForPaymentMethod(){
        seleniumActions.highLighterMethod(deleteButton.get(1));
    }
    public void userViewTheAddedGuest(){
        seleniumWaits.untilElementIsClickable(roleButton);
    }
    public void userViewTheAddIcon(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.scrollInToElement(addIcon);
        seleniumActions.clickOnElement(addIcon);
    }
    public void userCheckTheColumnIsDrag(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        List<WebElement> groupsColumnData = webDriver.findElements(By.xpath("//table//tr/td[count(//td[div[text()='Groups']]/preceding-sibling::td)+1]"));

        for (WebElement element : groupsColumnData) {
            seleniumActions.highLighterMethod(element);
        }
    }
    public void userAddingANewGuestInTheGroup(){
        seleniumActions.scrollInToElement(webDriver.findElement(addGuestIcon));
        seleniumActions.highLighterMethod(webDriver.findElement(addGuestIcon));
        seleniumActions.clickOnElement(webDriver.findElement(addGuestIcon));
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.typeValue(searchInputBox, GlobalConstants.GUEST_PREFIX_NAME);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(webDriver.findElement(crewProfilePic));
        seleniumWaits.untilElementIsVisible(selectButton);
        seleniumActions.highLighterMethod(selectButton);
        seleniumActions.clickOnElement(selectButton);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(GroupManagementConstants.GROUP_ROLE,GroupManagementConstants.DROPDOWN_VALUE_ONE);
        seleniumActions.highLighterMethod(webDriver.findElement(addGuestSaveButton));
        seleniumActions.clickOnElement(webDriver.findElement(addGuestSaveButton));
    }

    public String getAllFolioText() {
        WebElement element = webDriver.findElement(txtAllFolio);
        seleniumActions.highLighterMethod(element);
        String actualText = element.getText().trim();
        actualText = actualText.replaceAll("\\s*\\(.*?\\)", "").trim();
        return actualText;
    }

    public int getFolioCount() {
        List<WebElement> folioElements = webDriver.findElements(txtFolioList);
        return folioElements.size();
    }
    public void clickAddFolioButton() {
        seleniumActions.highLighterMethod(btnAddFolio);
        seleniumActions.jsClick(btnAddFolio);
    }
    public boolean isFolioCountIncreased(int beforeCount) {
        List<WebElement> folioElements = webDriver.findElements(txtFolioList);
        int afterCount = folioElements.size();
        logger.info("After Count : " + afterCount);
        if (afterCount == beforeCount + 1) {
            WebElement newFolio = folioElements.get(beforeCount);
            seleniumActions.highLighterMethod(newFolio);
            logger.info("Newly Added Folio : " + newFolio.getText());
            return true;
        }
        return false;
    }
    public int getItemsCount() {
        WebElement items = webDriver.findElement(itemsCount);
        seleniumWaits.untilElementIsVisible(items);
        seleniumActions.scrollInToElement(items);
        seleniumActions.highLighterMethod(items);
        String text = items.getText();
        String count = text.substring(text.indexOf("(") + 1, text.indexOf(" items"));
        return Integer.parseInt(count);
    }
    public String getGroupUserFullName() {
        List<WebElement> elements = webDriver.findElements(groupGuestFullName);
        StringBuilder fullName = new StringBuilder();
        for (WebElement element : elements) {
            seleniumActions.highLighterMethod(element);
            fullName.append(element.getText()).append(" ");
        }
        return fullName.toString().trim();
    }
    public int getGroupGuestListCount() {
        seleniumActions.highLighterMethod(groupHeader);
        for (WebElement guest : guestGroupList) {
            seleniumActions.highLighterMethod(guest);
            logger.info("Guest Name :: " + guest.getText());
        }
        return guestGroupList.size();
    }

    public int clickDeleteIcon() {
        WebElement items = webDriver.findElement(itemsCount);
        seleniumActions.scrollInToElement(items);
        int beforeCount = deleteIcons.size();
        if (beforeCount < 1) {
            seleniumActions.highLighterMethod(addNewSpecialRequest);
            addNewSpecialRequest.click();
            seleniumWaits.untilElementIsVisible(pageHeader);
            seleniumActions.highLighterMethod(pageHeader);
            seleniumActions.selectDropdownOption(GroupManagementConstants.SPECIAL_REQUEST, GroupManagementConstants.DROPDOWN_INDEX);
            seleniumActions.clickOnElement(addButtonSpecialRequest);
        }

        if (deleteIcons.size() >= 1) {
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            WebElement firstDeleteIcon = deleteIcons.get(0);
            seleniumActions.highLighterMethod(firstDeleteIcon);
            seleniumActions.clickOnElement(firstDeleteIcon);
        }
        return beforeCount;
    }
    public void userViewDeletePopUpBox(){
        seleniumWaits.untilElementIsVisible(deleteSpecialRequestHeader);
        seleniumActions.highLighterMethod(deleteSpecialRequestHeader);
        reasonTextBox.sendKeys(GroupManagementConstants.AUTOMATION_TEXT);
        seleniumActions.highLighterMethod(reasonTextBox);
    }

    public String searchRandomGroupName(){
        Random random = new Random();
        String expectedGroupName = groupNameLinkList.get(random.nextInt(groupNameLinkList.size())).getText().trim();
        searchBoxGroupName.sendKeys(expectedGroupName);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        String actualGroupName = seleniumActions.getTextMessage(groupNameLinkList.get(0));
        return actualGroupName;
    }
    public void userViewsTheCreatedCommunicationInfoBoxes(){
        if (communicationInfoBoxes.size() >= 1) {
            for (WebElement box : communicationInfoBoxes) {
                seleniumActions.scrollInToElement(box);
                seleniumActions.highLighterMethod(box);
            }
        }else {
            logger.info("Create a new Communication Info boxes");
        }
    }
    public void userClickOnTheGroupLinkList(){
        seleniumWaits.untilElementIsClickable(linksList.get(0));
        seleniumActions.clickOnElement(linksList.get(0));
    }
    public void userClicksOnTheEditButtonUnderTheReservationsSection(){
        seleniumWaits.untilElementIsVisible(reservationsHeader);
        seleniumActions.scrollInToElement(reservationsHeader);
        seleniumActions.highLighterMethod(reservationsHeader);
        seleniumActions.highLighterMethod(reservationsEditPencilIcon.get(0));
    }
    public void userIsAbleToViewTheAddReservationPageBox(){
        seleniumActions.clickOnElement(reservationsEditPencilIcon.get(0));
        seleniumWaits.untilElementIsVisible(addReservationHeader);
        seleniumActions.highLighterMethod(addReservationHeader);
        seleniumActions.highLighterMethod(activeFromDateInputBox);
        seleniumActions.highLighterMethod(activeToDateInputBox);
    }
    public String updateTheFromAndToDates(String fromDate, String toDate){
        //seleniumActions.sendKeys(activeFromDateInputBox, fromDate);
        seleniumActions.sendKeys(activeToDateInputBox, toDate);
        return seleniumActions.getTextMessage(activeToDateInputBox);
    }
    public String previousReservationDatesOfTheEntireGroup(){
        seleniumWaits.untilElementIsVisible(activeToDateInputBox);
        seleniumActions.scrollInToElement(activeToDateInputBox);
        seleniumActions.highLighterMethod(activeToDateInputBox);
        seleniumActions.highLighterMethod(estimatedMemberCount);
        seleniumActions.highLighterMethod(expectedMemberCount);
        return seleniumActions.getTextMessage(activeToDateInputBox);
    }
    public List<WebElement> getPaymentMethodHistory() {

        return webDriver.findElements(By.xpath("//otalio-payment-method-assignment-form//mat-form-field[@appearance='fill']"));
    }
    public int getSuccessPopupCount() {
        WebElement successPopup = webDriver.findElement(successPopUpMessage);
        seleniumActions.highLighterMethod(successPopup);
        return webDriver.findElements(successPopUpMessage).size();
    }

    public By getServiceLinkLocator(String link) {
        String xpath = String.format("//a[normalize-space()='%s']", link);
        return By.xpath(xpath);
    }

    public void clickOnServiceLink(String link) {
        WebElement serviceLinkElement = webDriver.findElement(getServiceLinkLocator(link));
        seleniumWaits.untilElementIsVisible(serviceLinkElement);
        seleniumActions.scrollInToElement(serviceLinkElement);
        seleniumActions.clickOnElement(serviceLinkElement);
    }
    public By getTabLocator(String tab) {
        String xpath = String.format("//div[@role='tab' and contains(.,'%s')]", tab);
        return By.xpath(xpath);
    }

    public void clickOnTab(String tab) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement tabElement = webDriver.findElement(getTabLocator(tab));
        seleniumWaits.untilElementIsVisible(tabElement);
        seleniumActions.scrollInToElement(tabElement);
        seleniumActions.clickOnElement(tabElement);
    }
    private By reportFolder(String folderName) {
        return By.xpath("//div[contains(@class,'mat-tooltip-trigger')]");
    }

    public void searchAndSelectReportFolder(String folderName) {
        seleniumWaits.untilElementIsVisible(txtSearchReport);
        for (char ch : folderName.toCharArray()) {
            txtSearchReport.sendKeys(String.valueOf(ch));
        }
        WebElement report =  webDriver.findElement(reportFolder(folderName));
        seleniumWaits.untilElementIsVisible(report);
        seleniumActions.highLighterMethod(report);
        seleniumActions.clickOnElement(report);
    }

    private List<WebElement> getReportList(String reportName) {
        By reportLocator = By.xpath("//tr[.//*[contains(normalize-space(),'" + reportName + "')]]//a[contains(@id,'report-overview-list-print-link')]");
        return webDriver.findElements(reportLocator);
    }

    public void searchAndSelectFirstReport(String reportName) {
        seleniumWaits.untilElementIsVisible(searchReport);
        try{
            for (char ch : reportName.toCharArray()) {
            searchReport.sendKeys(String.valueOf(ch));
            }
        } catch (Exception e) {
            for (char ch : reportName.toCharArray()) {
                searchReport.sendKeys(String.valueOf(ch));
            }
        }
        searchReport.sendKeys(Keys.BACK_SPACE);
        searchReport.sendKeys(Keys.chord(Keys.CONTROL, "z"));
        seleniumActions.highLighterMethod(getReportList(reportName).get(0));
        seleniumWaits.untilElementIsClickable(getReportList(reportName).get(0));
    }

    public void clickFirstReport(String reportName){
        WebElement firstReport = getReportList(reportName).get(0);
        seleniumActions.clickOnElement(firstReport);
    }
    private By getButtonByText(String buttonName) {
        return By.xpath("//button//span[normalize-space()='" + buttonName + "']");
    }
    public void enterRequiredFieldsAndClickButton(String date,String buttonName) {
        seleniumActions.selectDropdownOption(GroupManagementConstants.PORT_OF, ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        seleniumWaits.untilElementIsVisible(txtDate);
        seleniumActions.sendKeys(txtDate, date);
        WebElement button = webDriver.findElement(getButtonByText(buttonName));
        seleniumActions.highLighterMethod(button);
        seleniumActions.clickOnElement(button);
    }

    public boolean isReportPreviewDisplayed() {
        seleniumWaits.untilElementIsVisible(reportPreviewFrame);
        seleniumActions.highLighterMethod(reportPreviewFrame);
        return reportPreviewFrame.isDisplayed();
    }
    public void closeToastMessage(){
        try {
            seleniumWaits.untilElementIsVisible(closeToastMessageIcon);
            seleniumActions.clickOnElement(closeToastMessageIcon);
        } catch (Exception e) {
            logger.info("Error pop-up not displayed");
        }
    }

    public int getEventLocationCount() {
        seleniumWaits.untilElementIsVisible(evenLocation.get(0));
        for (WebElement location : evenLocation) {
            seleniumActions.highLighterMethod(location);
        }
        return evenLocation.size();
    }

    public boolean selectExistingEvent() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);

        if (existingEvents.isEmpty()) {
            selectFirstAvailableTimeSlot();
            seleniumActions.jsClick(btnQuickAdd);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            if (existingEvents.isEmpty()) {
                return false;
            }
        }
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.clickOnElement(existingEvents.get(0));
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumWaits.untilElementIsClickable(bookingStatusLink);
        seleniumActions.clickOnElement(bookingStatusLink);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        return true;
    }

    public void enterRequiredEventDetails() {
        seleniumWaits.untilElementIsVisible(contactInputBox);
        seleniumActions.highLighterMethod(contactInputBox);
        String randomNumber = String.valueOf(commonFunctions.getRandomNumber(1000, 9999));
        seleniumActions.sendKeys(contactInputBox, randomNumber);
        seleniumActions.highLighterMethod(accountingInputBox);
        String randomText = seleniumActions.getRandomString(SafetyManagementConstatnts.MIN_CHARACTER);
        seleniumActions.sendKeys(accountingInputBox, randomText);
    }
    public void selectFirstAvailableTimeSlot() {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT_EXIST);
        seleniumWaits.untilElementIsVisible(availableTimeSlots.get(4));
        seleniumActions.highLighterMethod(availableTimeSlots.get(4));
        seleniumActions.moveToElementAndClick(availableTimeSlots.get(4));

        List<WebElement> dropdown = webDriver.findElements(getDropdownArrow(GroupManagementConstants.EVENT_CATEGORY));
        if (dropdown.isEmpty() || !dropdown.get(0).isDisplayed()) {
            seleniumActions.moveToElementAndClick(availableTimeSlots.get(4));
        }
        seleniumWaits.untilElementIsVisible(webDriver.findElement(getDropdownArrow(GroupManagementConstants.EVENT_CATEGORY)));
        seleniumActions.selectDropdownOption(GroupManagementConstants.EVENT_CATEGORY,GroupManagementConstants.DROPDOWN_VALUE_ZERO);
        seleniumWaits.untilElementIsVisible(webDriver.findElement(getDropdownArrow(GroupManagementConstants.EVENT_TYPE)));
        seleniumActions.selectDropdownOption(GroupManagementConstants.EVENT_TYPE,GroupManagementConstants.DROPDOWN_VALUE_ZERO);
        seleniumWaits.untilElementIsVisible(addButtonEventBox);
        seleniumActions.highLighterMethod(addButtonEventBox);
    }

    public void userViewsTheButtonInAnEventPage(){
        seleniumWaits.untilElementIsVisible(btnSaveAndFinish);
        if (!closeIcon.isEmpty() && closeIcon.get(0).isDisplayed()) {
            seleniumActions.clickOnElement(closeIcon.get(0));
        }
        seleniumActions.highLighterMethod(btnSaveAndFinish);
    }

    public void userClickOnTheButtonInAnEventPage(){
        seleniumActions.clickOnElement(btnSaveAndFinish);
    }
    public void selectsAnExistingEventFromTheCalendar(){

        if (existingEvents.isEmpty()) {
            selectFirstAvailableTimeSlot();
            seleniumActions.jsClick(btnQuickAdd);
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        }
        seleniumWaits.untilElementIsClickable(existingEvents.get(0));
        seleniumActions.clickOnElement(existingEvents.get(0));

        List<WebElement> tours = webDriver.findElements(deletedOption);
        if (tours.isEmpty() || !tours.get(0).isDisplayed()) {
            if (!existingEvents.isEmpty()) {
                seleniumActions.moveToElementAndClick(existingEvents.get(0));
            }
        }
        seleniumActions.highLighterMethod(deleteEventIcon);
    }
    public void clickEventDeleteButton(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.jsClick(deleteEventIcon);
    }
    public void clickOnOpenCalendarSlotCurrectTime(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(availableTimeSlots.get(4));
        seleniumActions.doubleClickOnElement(availableTimeSlots.get(4));
        if(!addButtonEventBox.isDisplayed()){
            seleniumActions.doubleClickOnElement(availableTimeSlots.get(4));
        }
    }
    public void userEntersAllRequiredDetailsForTheNewEvent(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(GroupManagementConstants.EVENT_CATEGORY,GroupManagementConstants.DROPDOWN_VALUE_ZERO);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(GroupManagementConstants.EVENT_TYPE,GroupManagementConstants.DROPDOWN_VALUE_ZERO);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(addButtonEventBox);
    }
    public void clickTimelineView(String timelineView) {
       seleniumWaits.untilElementIsVisible(getTimelineButton(timelineView));
        seleniumActions.clickOnElement(getTimelineButton(timelineView));
    }
    public boolean isTimelineViewDisplayed(String timelineView) {
        seleniumActions.highLighterMethod(getTimelineButton(timelineView));
        return getTimelineButton(timelineView).isDisplayed();
    }
    private WebElement getTimelineButton(String timelineView) {

        switch (timelineView) {

            case GroupManagementConstants.TIMELINE_DAY:
                return btnTimelineDay;

            case GroupManagementConstants.TIMELINE_WEEK:
                return btnTimelineWeek;

            case GroupManagementConstants.TIMELINE_MONTH:
                return btnTimelineMonth;

            default:
                throw new IllegalArgumentException("Invalid Timeline View: " + timelineView);
        }
    }

    public WebElement getCalendarEvent() {
        return existingEvents.get(0);
    }

    public boolean isSectionDisplayed(String sectionName) {
        WebElement section = webDriver.findElement(By.xpath(String.format("//div[@class='col' and normalize-space()='%s']", sectionName)));
        seleniumActions.highLighterMethod(section);
        return section.isDisplayed();
    }
    public String getFirstRowBySectionText(String sectionText) {
        seleniumActions.sleep(GlobalConstants.SHORT_WAIT);
        return String.format("//*[.//div[normalize-space()='%s']]//tr[@aria-rowindex='1']", sectionText);
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
    public void clickOnPlusIcon(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.MINI_WAIT));
        wait.until(ExpectedConditions.elementToBeClickable(plusButton));
        seleniumActions.highLighterMethod(plusButton);
        plusButton.click();
    }

   public String enterRequiredDetailsInTheAddNoteBox() {
       seleniumWaits.untilElementIsVisible(addNewTextBox);
       String randomText = seleniumActions.getRandomString(SafetyManagementConstatnts.MIN_CHARACTER);
       seleniumActions.sendKeys(addNewTextBox, randomText);
       seleniumActions.highLighterMethod(addButtonNewTextBox);
       return randomText;
   }
    public void clicksTheButtonInTheAddNoteBox(){
        seleniumActions.clickOnElement(addButtonNewTextBox);
    }
    public void viewsTheButtonInNoteAndHistory(){
        seleniumWaits.untilElementIsVisible(saveAndFinishButtonNewTextBox);
        seleniumActions.highLighterMethod(saveAndFinishButtonNewTextBox);
    }
    public void clickOnTheButtonInNoteAndHistory(){
        seleniumActions.clickOnElement(saveAndFinishButtonNewTextBox);
    }

    public String getAddedNoteText() {
        seleniumWaits.untilElementIsVisible(txtAddedNote);
        seleniumActions.highLighterMethod(txtAddedNote);
        return txtAddedNote.getText().trim();
    }
    public void clickOnPlusIconInItemPage(){
        if (!closeIcon.isEmpty() && closeIcon.get(0).isDisplayed()) {
            seleniumActions.clickOnElement(closeIcon.get(0));
        }
        seleniumWaits.untilElementIsVisible(addButtonItem);
        seleniumActions.highLighterMethod(addButtonItem);
        seleniumActions.clickOnElement(addButtonItem);
    }
    public void fillsInTheRequiredDetailsInTheItemBox(){
        seleniumWaits.untilElementIsVisible(addItemHeader);
        seleniumActions.highLighterMethod(addItemHeader);
        seleniumActions.selectDropdownOption(GroupManagementConstants.ITEM,GroupManagementConstants.DROPDOWN_VALUE_ZERO);

        seleniumWaits.untilElementIsVisible(quantityInputBox,GlobalConstants.SHORT_WAIT);
        seleniumActions.sendKeys(quantityInputBox,GroupManagementConstants.QUANTITY_VALUE);
        seleniumActions.highLighterMethod(addButtonNewTextBox);
        seleniumActions.clickOnElement(addButtonNewTextBox);
    }
    public void viewsTheButtonInAnItemPage(){
        seleniumWaits.untilElementIsVisible(btnSaveAndFinishInItemPage);
        seleniumActions.highLighterMethod(btnSaveAndFinishInItemPage);
    }
    public void clickOnTheButtonInAnItemPage(){
        seleniumActions.clickOnElement(btnSaveAndFinishInItemPage);
    }
    public void viewsTheReportFormatPopUpBox(){
        seleniumWaits.untilElementIsVisible(reportFormatePopUpBox);
        seleniumActions.highLighterMethod(reportFormatePopUpBox);
        seleniumActions.highLighterMethod(exportConfirmButton);
    }
    public void emailAddressInTheNewMessageBox(){
        seleniumWaits.untilElementIsVisible(newMessageHeader);
        seleniumActions.highLighterMethod(newMessageHeader);
        seleniumWaits.untilElementIsVisible(txtToEmail);
        seleniumActions.sendKeys(txtToEmail, "a");
        seleniumWaits.untilElementIsVisible(firstEmailSuggestion);
        seleniumActions.clickOnElement(firstEmailSuggestion);
        seleniumActions.highLighterMethod(sendButton);
    }
    private By getDropdownArrow(String fieldName) {
        return By.xpath("//mat-form-field[.//*[normalize-space()='" + fieldName + "']]//div[@aria-label='Select' or contains(@class,'mat-select-arrow-wrapper')]");
    }
    public void PackagePageAndClicksOnTheButton(String buttonName){
        WebElement reservationStatusDropdownArrow = webDriver.findElement(getDropdownArrow(GroupManagementConstants.RESERVATION_STATUS));
        seleniumWaits.untilElementIsVisible(reservationStatusDropdownArrow);
        seleniumActions.clickOnElement(reservationStatusDropdownArrow);
        seleniumWaits.untilElementIsVisible(firstCheckBox);
        seleniumActions.clickOnElement(firstCheckBox);
        seleniumActions.pressEscapeKey();

        WebElement optionsDropdownArrow = webDriver.findElement(getDropdownArrow(GroupManagementConstants.OPTIONS));
        seleniumWaits.untilElementIsVisible(optionsDropdownArrow);
        seleniumActions.clickOnElement(optionsDropdownArrow);
        seleniumWaits.untilElementIsVisible(assignOption);
        seleniumActions.clickOnElement(assignOption);
        seleniumActions.pressEscapeKey();

        WebElement packageDropdownArrow = webDriver.findElement(getDropdownArrow(GroupManagementConstants.PACKAGE));
        seleniumActions.clickOnElement(packageDropdownArrow);
        seleniumWaits.untilElementIsVisible(firstCheckBox);
        Actions actions = new Actions(webDriver);
        try {
            actions.moveToElement(firstCheckBox).click().perform();
        } catch (Exception e) {
            seleniumActions.clickOnElement(firstCheckBox);
        }
        if(firstCheckBox.isSelected()) {
            seleniumActions.pressEscapeKey();
        }

        WebElement button = webDriver.findElement(getButtonByText(buttonName));
        seleniumActions.highLighterMethod(button);
        seleniumActions.clickOnElement(button);
    }

    public boolean isAddEventButtonDisplayed() {
        seleniumWaits.untilElementIsVisible(btnAddEvent);
        seleniumActions.highLighterMethod(btnAddEvent);
        return btnAddEvent.isDisplayed();
    }
    public void enterAllRequiredEventDetails(){
        seleniumWaits.untilElementIsVisible(addNewEvent);
        seleniumActions.highLighterMethod(addNewEvent);
        userEntersAllRequiredDetailsForTheNewEvent();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.selectDropdownOption(GroupManagementConstants.LOCATION,GroupManagementConstants.DROPDOWN_VALUE_ZERO);
        seleniumActions.highLighterMethod(addButtonEventBox);
    }

    public void closingPopUpMessage(){
        new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.SHORT_WAIT)).until(ExpectedConditions.invisibilityOfElementLocated(successToast));
    }
    public void userEntersDateFieldsAndClicksOnTheButton(String date,String buttonName){
        seleniumWaits.untilElementIsVisible(txtDate);
        seleniumActions.sendKeys(txtDate, date);
        WebElement button = webDriver.findElement(getButtonByText(buttonName));
        seleniumActions.highLighterMethod(button);
        seleniumActions.clickOnElement(button);
    }
    public void selectingTheDropdownValue(){
        seleniumActions.jsClick(webDriver.findElement(By.xpath("(//mat-option[not(contains(@class,'disabled'))])[1]")));
    }
    public WebElement calendarEvent() {
        WebElement calendarEvent = availableTimeSlots.get(4);
        seleniumActions.highLighterMethod(calendarEvent);
        return calendarEvent;
    }

    public void deletingTheExistingEventFromTheCalendar(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT_EXIST);
        seleniumWaits.untilElementIsClickable(existingEvents.get(0));
        seleniumActions.clickOnElement(existingEvents.get(0));
        List<WebElement> tours = webDriver.findElements(deletedOption);
        if (tours.isEmpty() || !tours.get(0).isDisplayed()) {
            if (!existingEvents.isEmpty()) {
                seleniumActions.moveToElementAndClick(existingEvents.get(0));
            }
        }
        seleniumActions.highLighterMethod(deleteEventIcon);
    }
}
