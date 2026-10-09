package com.nclh.pageObjects;

import com.nclh.constants.GlobalConstants;
import com.nclh.constants.HotelMaintenanceConstants;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotelMaintenancePage {
    SeleniumActions seleniumActions;
    CrewInformationPage crewInformationPage;
    PointOfSalePage pointOfSalePage;
    CommonFunctions commonFunctions;
    SeleniumWaits seleniumWaits;
    CommonPage commonPage;
    private final WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(HotelMaintenancePage.class);
    private final ShipSecurityPage shipSecurityPage;
    @FindBy(css = "[mattooltip='Add a new Maintenance Request']")
    private WebElement addIcon;
    @FindBy(xpath = "//span[text()='Request']")
    private WebElement requestDropDown;
    @FindBy(css = "[placeholder='Search']")
    private WebElement searchField;
    @FindBy(css = "[placeholder='Team']")
    private WebElement teamDropdown;
    @FindBy(css = "[placeholder='Detail']")
    private WebElement detailField;
    @FindBy(id = "request-editor-reported-by-select-button")
    private WebElement reportedByGuestIcon;
    @FindBy(id = "request-editor-assignee-select-button")
    private WebElement assigneeGuestIcon;
    @FindBy(css = "[placeholder='Search...']")
    private WebElement searchOfGuests;
    @FindBy(xpath = "//otalio-guest-search-card//icon[starts-with(@icon,'plus')]")
    private List<WebElement> plusButtons;
    @FindBy(xpath = "//otalio-guest-search-card//icon[starts-with(@icon,'plus')]")
    private WebElement plusButton;
    @FindBy(xpath = "//span[normalize-space()='Select']/../../../../descendant::otalio-passenger-picture/following-sibling::div/div[contains(@class,'tooltip')]")
    private WebElement guestName;
    @FindBy(xpath = "//span[normalize-space()='Select']/../../../../descendant::otalio-passenger-picture/following-sibling::div/div[contains(@class,'tooltip-trigger mat-body')]")
    private WebElement assigneeName;
    @FindBy(xpath = "//span[normalize-space()='Select']/../../../../descendant::otalio-passenger-picture/following-sibling::div/div/div[contains(@class,'info')]")
    private WebElement cabinNumber;
    @FindBy(xpath = "//mat-expansion-panel-header/descendant::span[contains(text(),'Locations')]")
    private WebElement location;
    @FindBy(css = "[placeholder='Available Locations']")
    private WebElement availableLocations;
    @FindBy(xpath = "(//mat-list-item/descendant::button)[1]")
    private WebElement addButton;
    @FindBy(xpath = "//otalio-complex-filter-multi-label-content/div[contains(text(),'Location:')]")
    private WebElement locationFilter;
    @FindBy(css = "[placeholder='Location']")
    private WebElement locationSearch;
    @FindBy(css = "[placeholder='Name']")
    private WebElement nameSearch;
    @FindBy(xpath = "//span[text()='Location']")
    private WebElement locationInFast;
    @FindBy(xpath = "//otalio-infinite-select[@formcontrolname='cleaningLocationId']/mat-select//div/span/span")
    private WebElement locationTextInFast;
    @FindBy(css = "[placeholder='Cleaning Location']")
    private WebElement cleaningLocationSearch;
    @FindBy(xpath = "//input[contains(@placeholder,'Search')]")
    private WebElement cleaningTaskSearch;
    @FindBy(css = "[placeholder='Add Comment']")
    private WebElement addComment;
    @FindBy(xpath = "//tbody/tr/td[@role='columnheader'][contains(@style,'min-width')]/div")
    private List<WebElement> tableHeaderItems;
    @FindBy(xpath = "//mat-checkbox/descendant::span[contains(text(),'Create Housekeeping Task ')]")
    private WebElement createHouseKeepingTaskCheckBox;
    //    @FindBy(xpath = "//mat-checkbox/descendant::span[contains(text(),'Create Service Request ')]")
//    private WebElement createServiceRequestCheckBox;
    @FindBy(xpath = "//mat-select/descendant::span[contains(text(),'Cleaning Task')]")
    private WebElement cleaningTaskDropDown;
    @FindBy(xpath = "//mat-select/descendant::span[contains(text(),'Service Request')]")
    private WebElement serviceRequestDropDown;
    @FindBy(xpath = "//span[contains(@class,'nodata')]")
    private WebElement noDataText;
    @FindBy(xpath = "//otalio-maintenance-save-error/div/p[contains(text(),'already exists')]")
    private List<WebElement> errorMessage;
    @FindBy(xpath = "//button[contains(@id,'request-editor-reported-by-select')]")
    private WebElement editReportBy;
    @FindBy(xpath = "//button[contains(@id,'request-editor-add-comment-button')]/span[contains(text(),'Add')]")
    private List<WebElement> addButtons;
    @FindBy(xpath = "//span[@class='mat-option-text']")
    private List<WebElement> cabinNumbers;
    @FindBy(xpath = "//span[contains(text(),'Open')]")
    private WebElement openButton;
    @FindBy(xpath = "//span[contains(text(),'Closed')]")
    private WebElement closedButton;
    @FindBy(xpath = "//span[contains(text(),'Confirm')]")
    private WebElement confirmButton;
    @FindBy(xpath = "//span[contains(text(),'Priority')]")
    private WebElement priority;
    @FindBy(xpath = "//otalio-infinite-select[@placeholder='Team']/mat-select/div/div/span/span")
    private WebElement teamText;
    @FindBy(xpath = "//button[contains(text(),'Closed')]")
    private WebElement closed;
    @FindBy(xpath = "//otalio-request-editor-general/div/p[contains(text(),'What')]")
    private WebElement what;
    @FindBy(xpath = "//p[text()=' Create a Maintenance Request ']")
    private WebElement heading;
    @FindBy(xpath = "//mat-error[@aria-live='polite']")
    private WebElement requestError;
    @FindBy(xpath = "(//mat-error[@aria-live='polite'])[1]")
    private WebElement requestFastCreationError;
    @FindBy(xpath = "(//mat-error[@aria-live='polite'])[2]")
    private WebElement whereFastCreationError;
    @FindBy(css = "button[id*='status.'] > icon")
    private WebElement statusList;
    @FindBy(css = "[class*='pseudo-checkbox-checked']")
    private List<WebElement> statusCheckBoxes;
    @FindBy(xpath = "//otalio-request-status")
    private List<WebElement> statusListOfAll;
    @FindBy(xpath = "//td[@aria-colindex='6' and not(@role='columnheader')]")
    private List<WebElement> opDepartmentList;
    @FindBy(css = ".mat-tooltip-trigger.text-truncate.ng-star-inserted.dx-template-wrapper")
    private List<WebElement> priorityListOfAll;
    @FindBy(css = "#breadcrumbs-title")
    private WebElement breadCrumbTitle;
    @FindBy(xpath = "(//button[contains(@id,'request-status')]/span/div/span)[1]")
    private WebElement firstStatus;
    @FindBy(xpath = "//span[@class='mat-tooltip-trigger ng-star-inserted'][normalize-space()='Open']")
    private WebElement updateHistory;
    @FindBy(xpath = "//span[normalize-space(text()) = 'Add file']")
    private WebElement addFile;
    @FindBy(xpath = "//span[normalize-space(text()) = 'Upload file']")
    private WebElement uploadFile;
    @FindBy(xpath = "//div[@class='mat-tooltip-trigger']")
    private WebElement whereLocation;
    @FindBy(xpath = "//div[@class='dx-gallery-wrapper']/descendant::img")
    private WebElement image;
    @FindBy(xpath = "//span//icon[contains(@icon,'delete')]")
    private WebElement delete;
    @FindBy(xpath = "//div[@class='formats text-type-info']")
    private WebElement afterDelete;
    @FindBy(xpath = "//span[contains(text(),'Create Service Request ')]/preceding-sibling::span[@class='mat-checkbox-inner-container']")
    private WebElement createServiceRequestCheckBox;
    @FindBy(xpath = "//div[@class='mat-tab-label-content']/div[contains(text(),'Crew')]")
    private WebElement crewList;
    @FindBy(xpath = "//otalio-infinite-select[@placeholder='Team']/descendant::span[contains(@class,'mat-select-min-line')]")
    private WebElement team;
    @FindBy(xpath = "(//otalio-request-status/descendant::icon[@icon='arrow-down'])[1]")
    private WebElement statusDropdown;
    @FindBy(xpath = "//button[@id='request-status-request-status-closed-menu-button']")
    private WebElement closeOption;
    @FindBy(xpath = "//td[@aria-colindex='2']//div[@class='dx-template-wrapper']//a[contains(@id,'maintenance-requests-overview')]")
    private List<WebElement> listOfRequest;
    @FindBy(xpath = "(//td[@aria-colindex='2']//div[@class='dx-template-wrapper']//a[contains(@id,'maintenance-requests-overview')])[1]")
    private WebElement firstRequest;
    @FindBy(css = "[placeholder='Comment']")
    private WebElement Comment;
    @FindBy(css = "placeholder=['Last Name']")
    private WebElement lastName;
    @FindBy(xpath = "//a[@id='link-box-maintenance-overview']")
    private WebElement maintenanceOverView;
    @FindBy(xpath = "//button[contains(@id,'guest-search-delete')]")
    private WebElement deleteButton;
    @FindBy(id = "complex-filter-search-input")
    private WebElement searchBox;
    @FindBy(xpath = "//otalio-complex-filter-multi-label-content/div[contains(text(),'Priority')]")
    private WebElement priorityFilter;
    @FindBy(xpath = "//otalio-complex-filter-multi-label-content/div[contains(text(),'Department')]")
    private WebElement opDepartment;
    @FindBy(xpath = "//otalio-complex-filter-multi-label-content/div[contains(text(),'Category')]")
    private WebElement categoryFilter;
    @FindBy(xpath = "//input[@id='complex-filter-multi-search-in-options-list-input'][@data-placeholder='Priority']")
    private WebElement priorityFilterSearch;
    @FindBy(xpath = "//input[@id='complex-filter-multi-search-in-options-list-input'][@data-placeholder='Op Department']")
    private WebElement departmentFilterSearch;
    @FindBy(xpath = "//button[@id='list-export-button']//span/icon/*[name()='svg']/*[name()='use']")
    private WebElement exportButton;
    @FindBy(xpath = "//button[@id='list-export-page-button']")
    private WebElement exportPage;
    @FindBy(xpath = "//button[@id='list-export-all-button']")
    private WebElement exportAll;
    @FindBy(xpath = "(//icon[@icon='edit'])[1]")
    private WebElement editIcon;
    @FindBy(xpath = "//td[@role='columnheader']/div[contains(text(),'Start Time')]")
    private WebElement startTime;
    @FindBy(xpath = "(//otalio-infinite-select[@placeholder='Operational Department']/descendant::span)[2]")
    private WebElement operationalDepartmentText;
//    @FindBy(xpath = "(//otalio-infinite-select[@placeholder='Team']/descendant::span)[2]")
//    private WebElement teamText;
    @FindBy(xpath = "//div[@class='text-truncate']/div")
    private WebElement locationText;
    @FindBy(css = "button[id*='reservationStatus'] > icon")
    private WebElement reservationStatusList;

    @FindBy(xpath = "(//td[contains(@class,'today')])[1]")
    private WebElement selectTodayDate;

    @FindBy(css = "tr.dx-data-row")
    private WebElement requestFirstRow;

    @FindBy(xpath = "//*[@id='complex-filter-collapse-expand-chip']/div/div")
    private WebElement expandFlterIcon;

    @FindBy(css = "otalio-complex-filter")
    private WebElement requestOverviewFilterContainer ;

    @FindBy(xpath = "//otalio-complex-filter-account-item/div[contains(text(),'+')]")
    private WebElement assigneeFilter;

    public void clickOnCodeLink(String link){
        String codeLink = "(//td[normalize-space()='" + link + "']/../..//following-sibling::td/div/a[contains(@href,'request')])[1]";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(codeLink)));
    }

    public void clickOnAssigneeFilter(String name){
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(assigneeFilter);
        seleniumActions.typeValue(nameSearch, name);
        String Location = "//span[normalize-space()='" + name + "']";
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(Location))));
        seleniumActions.clickOnElement(assigneeFilter);
    }

    public void openLinkUsingHref(String link) {
        String name = "(//*[contains(@href,'%s')])[2]";
        try {
            seleniumActions.sleep(3000);
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(name, link))));
            seleniumActions.moveToElementAndClickOnElement(
                    webDriver.findElement(By.xpath(String.format(name, link))),
                    webDriver.findElement(By.xpath(String.format(name, link)))
            );
        } catch (Exception e) {
            seleniumActions.clickOnElement(By.xpath(String.format(name, link)));
        }
    }

    public void clickOnTodayDate(){
        seleniumWaits.untilElementIsVisible(selectTodayDate);
        seleniumActions.clickOnElement(selectTodayDate);
    }

    public HotelMaintenancePage(WebDriver webDriver) {
        seleniumActions = new SeleniumActions(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        shipSecurityPage = new ShipSecurityPage(webDriver);
        crewInformationPage = new CrewInformationPage(webDriver);
        pointOfSalePage = new PointOfSalePage(webDriver);
        commonFunctions = new CommonFunctions();
        commonPage = new CommonPage(webDriver);
    }

    public void clearReservationStatus() {
        seleniumActions.clickOnElement(reservationStatusList);
        seleniumActions.clickOnElement(By.xpath("//span[text()='Clear previous selection']"));
        seleniumActions.sleep(1000);
    }

    public void moveToField(String field){
        String text = "//p[text()='" + field + "']";
        seleniumActions.moveToElement(webDriver.findElement(By.xpath(text)));
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(text)));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
    }

    public String getLocationTextAfterAdding() {
        seleniumWaits.untilElementIsVisible(locationText);
        return seleniumActions.getTextMessage(locationText);
    }

    public String getOperationalDepartmentText() {
        return seleniumActions.getTextMessage(operationalDepartmentText);
    }

    public String getTeamText() {
        return seleniumActions.getTextMessage(teamText);
    }


    public void clickOnEditIcon() {
        seleniumActions.sleep(2000);
        try {
            seleniumActions.clickOnElement(editIcon);
        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(editIcon);
        }
    }

    public void clickOnExportButton() {
        seleniumWaits.untilElementIsVisible(exportButton);
        WebElement ele = webDriver.findElement(By.xpath("//button[@id='list-export-button']//span/icon/*[name()='svg']/*[name()='use']"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("document.querySelector('[id=\"list-export-button\"]>span[class=\"mat-button-wrapper\"]').click();");
        seleniumWaits.untilElementIsVisible(exportPage);
        seleniumActions.sleep(4000);
        webDriver.findElement(By.xpath("//button[@id='list-export-page-button']")).click();
        seleniumActions.sleep(30000);
    }

    public void clickOnPriorityFilter(String priority) {
        seleniumWaits.untilElementIsVisible(priorityFilter);
        seleniumActions.clickOnElement(priorityFilter);
        seleniumActions.typeValue(priorityFilterSearch, priority);
        String Location = "//div[@class='mat-list-text']//span[contains(text(),'" + priority + "')]";
        try {
            seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(Location))));
        } catch (StaleElementReferenceException est) {
            seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(Location))));
        }
        seleniumActions.clickOnElement(priorityFilter);
    }

    public void clickOnCategoryFilter(String Category) {
        seleniumWaits.untilElementIsVisible(categoryFilter);
        seleniumActions.clickOnElement(categoryFilter);
        seleniumActions.typeValue(categoryFilter, Category);
        String Location = "//div[@class='mat-list-text']//span[contains(text(),'" + Category + "')]";
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(Location))));
        seleniumActions.clickOnElement(categoryFilter);
    }

    public void clickOnOpDepartmentFilter(String department) {
        seleniumWaits.untilElementIsVisible(opDepartment);
        seleniumActions.clickOnElement(opDepartment);
        seleniumActions.typeValue(departmentFilterSearch, department);
        String Operation = "(//div[@class='mat-list-text']//span[contains(text(),'" + department + "')])[1]";
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(Operation))));
        seleniumActions.clickOnElement(opDepartment);
    }

    public void enterInSearchBox(String request) {
        seleniumActions.sleep(2000);
        seleniumActions.typeValue(searchBox, request);
        seleniumActions.sleep(2000);

    }

    public void clickOnDeleteGuest() {
        seleniumActions.moveToElementAndClick(deleteButton);
        seleniumActions.sleep(3000);
    }

    public void clickONMaintenanceOverView() {
        seleniumActions.jsClick(maintenanceOverView);
        try {
            seleniumWaits.untilElementIsPresent(By.xpath("//a[@id='link-box-maintenance-overview']"));
            seleniumActions.moveToElementAndClick(maintenanceOverView);
        } catch (Exception e) {
        }
    }

    public void enterComment(String text) {
        seleniumActions.typeValue(confirmButton, text);
    }

    public String getTextOfFirstRequest() {
        return seleniumActions.getTextMessage(firstRequest);
    }

    public List<String> getStatusListOfAllRequest() {
        seleniumActions.sleep(3000);
        return listOfRequest.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void clickOnStatusDropdown() {
        seleniumActions.clickOnElement(statusDropdown);
        seleniumWaits.untilElementIsVisible(closeOption);
        seleniumActions.clickOnElement(closeOption);

    }

    public boolean verifyTeamIsUpdated() {
        return seleniumActions.isElementDisplayed(team);
    }

    public void clickOnCrew() {
        seleniumActions.sleep(3000);
        seleniumActions.moveToElementAndClick(crewList);
    }

    public void checkBoxServiceRequest() {
        seleniumActions.clickOnElement(createServiceRequestCheckBox);
    }

    public boolean deleteIsDone() {
        return seleniumActions.isElementDisplayed(afterDelete);
    }

    public void clickOnDelete() {
        seleniumActions.moveToElement(image);
        seleniumActions.clickOnElement(delete);
    }

    public String getLocation() {
        return seleniumActions.getTextMessage(whereLocation);
    }

    public void upLoadDocument() throws AWTException {
        try {
            seleniumActions.clickOnElement(addFile);
            seleniumActions.clickOnElement(uploadFile);
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

    public String getStatusHistory() {
        seleniumActions.moveToElement(updateHistory);
        return seleniumActions.getTextMessage(updateHistory);
    }

    public void clickOnFirstStatus() {
        seleniumWaits.untilElementIsClickable(firstStatus);
    }

    public List<String> getStatusListOfAll() {
        return statusListOfAll.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public List<String> getPriorityListOfAll() {
        seleniumActions.sleep(3000);
        return priorityListOfAll.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public List<String> getOpDepartmentListOfAll() {
        seleniumActions.sleep(3000);
        return opDepartmentList.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void checkOptionInStatus(String option) {
        seleniumActions.clickOnElement(statusList);
        String checkBox = "//span[text()='%s']/../preceding-sibling::mat-pseudo-checkbox";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(checkBox, option))));
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(500); //after pressing esc key
        seleniumActions.pressEscapeKey();
        seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
    }


    public void clearStatus() {
        seleniumActions.clickOnElement(statusList);
        for (int i = 0; i < statusCheckBoxes.size(); i++) {
            seleniumActions.sleep(2000);
            seleniumActions.clickOnElement(statusCheckBoxes.get(i));
        }
        if (statusCheckBoxes.size() > 0) {
            statusCheckBoxes.get(0).click();
        }
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
    }

    public void enterTextInDetail(String text) {
        seleniumActions.clickOnElement(detailField);
        seleniumActions.typeValue(detailField, text);
    }

    public String getPriorityText() {
        seleniumWaits.untilElementIsVisible(priority);
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(priority);
    }

    public String getRequestError() {
        return seleniumActions.getTextMessage(requestError);
    }

    public String getRequestFastCreationError() {
        return seleniumActions.getTextMessage(requestFastCreationError);
    }
    public String getWhereError() {
        return seleniumActions.getTextMessage(whereFastCreationError);
    }

    public void closeRecord() {
        seleniumActions.clickOnElement(openButton);
        seleniumActions.isElementDisplayed(closed);
        seleniumActions.clickOnElement(closed);
        seleniumActions.moveToElementAndClick(confirmButton);
    }

    public void reCloseRecord() {
        seleniumActions.clickOnElement(closedButton);
        seleniumActions.isElementDisplayed(closed);
        seleniumActions.clickOnElement(closed);
        seleniumActions.typeValue(confirmButton, "test");
        seleniumActions.moveToElementAndClick(confirmButton);
    }

    public void clickAddButtons() {
        for (int i = 0; i < addButtons.size(); i++) {
            seleniumActions.clickOnElement(addButtons.get(i));
        }
    }

    public void typeComment(String comment) {
        //seleniumActions.clickOnElement(addComment);
        seleniumActions.typeValue(addComment, comment);
    }

    public void clickOnEditReportBy() {
        seleniumActions.jsClick(editReportBy);
    }

    public boolean checkErrorMessage() {
        return seleniumActions.isElementDisplayed(errorMessage.get(0));
    }

    public void clickOnAddButton() {
        seleniumActions.clickOnElement(By.xpath("//span[text()='Add' or normalize-space(text())='Add'][contains(@class,'button')]"));
    }

    public void clickOnCreateButton() {
        seleniumActions.clickOnElement(By.xpath("//span[text()='Create' or normalize-space(text())='Create'][contains(@class,'button')]"));
    }

    public int getErrorMessage() {
        seleniumActions.sleep(3000);
        return seleniumActions.getNumberOfElements(errorMessage);
    }

    public String getNoDataText() {
        return seleniumActions.getTextMessage(noDataText);
    }

    public void enableToggle(String toggleName) {
        String toggleType = "//input[contains(@id,'%s')]";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))));
        System.out.println(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))).getAttribute("aria-checked"));
        WebElement toggle = webDriver.findElement(By.xpath(String.format(toggleType, toggleName)));
        if (toggle.getAttribute("aria-checked").equals("false")) {
            seleniumActions.clickOnElement(toggle);
        }

    }

    public void clickOnCheckBox() {
        seleniumActions.clickOnElement(createHouseKeepingTaskCheckBox);
    }

    public void clickOnCreateServiceRequest() {
        seleniumActions.clickOnElement(createServiceRequestCheckBox);
    }

    public String getTaskCode(String columnName) {
        int index = getIndexOfColumnName(columnName) + 1;
        System.out.println("index :: " + index);
        String departmentName = "//tbody/tr/td/div[contains(text(),'" + columnName + "')]/ancestor::table/tbody/tr/td[@role='columnheader'][not(contains(@class,'select'))]/div[text()]/following::table/tbody/tr[1]/td/div/a[" + index + "]";
        String name = seleniumActions.getTextMessage(
                webDriver.findElement(By.xpath(departmentName))
        );
        System.out.println("text :: " + name);
        return name;
    }

    public String getRequestText(String columnName) {
        int index = getIndexOfColumnName(columnName) + 1;
        System.out.println("index :: " + index);
        String departmentName = "//tbody/tr/td/div[contains(text(),'" + columnName + "')]/ancestor::table/tbody/tr/td[@role='columnheader'][not(contains(@class,'select'))]/div[text()]/following::table/tbody/tr[1]/td[not(contains(@class,'select')) and not(@aria-colindex='14')][" + index + "]";
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
                By tableHeader = By.xpath("//tbody/tr/td[@role='columnheader'][contains(@style,'min-width')]");
                columns.add(seleniumActions.getTextMessage(webDriver.findElements(tableHeader).get(i)));
            }
        }

        columns.removeAll(List.of(""));
        int columnIndex = columns.indexOf(columnName);
        return columnIndex;
    }

    public void searchLocation(String cabin) {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(locationFilter);
        seleniumActions.typeValue(locationSearch, cabin);
        String Location = "//span[normalize-space()='" + cabin + "']";
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(Location))));
        seleniumActions.clickOnElement(locationFilter);

    }

    public void searchCleaningLocation(String cabin) {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(locationFilter);
        seleniumActions.typeValue(cleaningLocationSearch, cabin);
        String Location = "//span[normalize-space()='" + cabin + "']";
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(Location))));
        seleniumActions.clickOnElement(locationFilter);

    }

    public void selectCleaningTask(String task) {
        seleniumActions.moveToElementAndClick(cleaningTaskDropDown);
        seleniumActions.clickOnElement(cleaningTaskDropDown);
        seleniumActions.sleep(3000);
        seleniumActions.typeValue(cleaningTaskSearch, task);
        String cleaningTask = "//span[normalize-space()='" + task + "']";
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(cleaningTask))));
        seleniumActions.sleep(3000);
    }

    public void selectServiceRequest(String task) {
        //seleniumActions.moveToElementAndClick(serviceRequestDropDown);
        seleniumActions.clickOnElement(serviceRequestDropDown);
        seleniumActions.sleep(3000);
        seleniumActions.typeValue(cleaningTaskSearch, task);
        String cleaningTask = "//span[normalize-space()='" + task + "']";
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(cleaningTask))));
        seleniumActions.sleep(3000);
    }

    public void clickAddButton(String cabin) {
        seleniumActions.clickOnElement(By.xpath("//div[normalize-space(text())='"+cabin+"']/following-sibling::button[contains(@id,'selector-list-set-selected-request')]"));

       // seleniumActions.clickOnElement(addButton);
    }

    public void typeLocation(String cabin) {
        seleniumActions.clickOnElement(availableLocations);
        seleniumActions.typeValue(availableLocations, cabin);
    }

    public void clickOnLocation() {
        seleniumActions.clickOnElement(location);
    }

    public String getCabinNumber() {
        return seleniumActions.getTextMessage(cabinNumber);
    }

    public String getGuestName() {
        return seleniumActions.getTextMessage(guestName);
    }

    public String getAssigneeName() {
        return seleniumActions.getTextMessage(assigneeName);
    }

    public void searchGuestUsingAlphabetic() {
        try {
            seleniumActions.clickOnElement(shipSecurityPage.getSearchIcon());
        } catch (JavascriptException je) {
            seleniumActions.moveToElementAndClickOnElement(shipSecurityPage.getSearchIcon(), shipSecurityPage.getSearchIcon());
        }
       // seleniumActions.typeValue(shipSecurityPage.getSearchInputBox(), "Bray");
        seleniumActions.typeValue(shipSecurityPage.getSearchInputBox(), String.valueOf(commonFunctions.getRandomCharacter()));

    }

    public void searchCrewUsingAlphabetic() {
        try {
            seleniumActions.clickOnElement(shipSecurityPage.getSearchIcon());
        } catch (StaleElementReferenceException je) {
            seleniumActions.moveToElementAndClickOnElement(shipSecurityPage.getSearchIcon(), shipSecurityPage.getSearchIcon());
        }
        seleniumActions.typeValue(shipSecurityPage.getSearchInputBox(), "a");

        //seleniumActions.typeValue(shipSecurityPage.getSearchInputBox(), "HKUser");

//        seleniumActions.typeValue(shipSecurityPage.getSearchInputBox(), String.valueOf(commonFunctions.getRandomCharacter()));
    }

    public void searchGuestUsingAlphabeticInDining() {
        try {
            seleniumActions.clickOnElement(shipSecurityPage.getSearchIcon());
        } catch (JavascriptException je) {
            seleniumActions.moveToElementAndClickOnElement(shipSecurityPage.getSearchIcon(), shipSecurityPage.getSearchIcon());
        }
        seleniumActions.typeValue(shipSecurityPage.getSearchInputBox(), "a");
    }

    public void searchGuestFirstName(String name) {
        try {
            seleniumActions.clickOnElement(shipSecurityPage.getSearchIcon());
        } catch (JavascriptException je) {
            seleniumActions.moveToElementAndClickOnElement(shipSecurityPage.getSearchIcon(), shipSecurityPage.getSearchIcon());
        }
        seleniumActions.typeValue(shipSecurityPage.getSearchInputBox(), name);
    }

    public int getRandomNumber() {
        return commonFunctions.getRandomNumber(0, 1);
    }

    public int getRandomCabinNumber() {
        return commonFunctions.getRandomNumber(10, 20);
    }

    public String getLocationText() {
        return seleniumActions.getTextMessage(locationTextInFast);
    }

    public void selectLocationWhere(int number) {
        seleniumActions.clickOnElement(locationInFast);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(cabinNumbers.get(number));
    }

    public void selectGuest() {
        seleniumActions.clickOnElement(plusButton);
    }

    public void selectGuests(int number) {
        seleniumActions.clickOnElement(plusButtons.get(number));
        seleniumActions.sleep(3000);
    }

    public void selectAssignedBy() {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(plusButtons.get(0));
    }

    public void clickOnGuestIcon() {
        seleniumActions.clickOnElement(reportedByGuestIcon);
    }

    public void clickOnAssigneeIcon() {
        seleniumActions.jsClick(assigneeGuestIcon);
    }

    public void selectTeam(){
        seleniumActions.clickOnElement(teamDropdown);
        seleniumActions.sleep(5000);
        String requestOption = "(//mat-option[contains(@id,'otalio-infinite-select')])[2]";
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(requestOption)));

        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(requestOption)));
        }
    }
    public String getOperationalDepText() {
        String department = "(//td[@aria-colindex='7'])[2]";
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath(department)));
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath(department)));
    }


        public String selectRequest(String request) {
        seleniumActions.clickOnElement(requestDropDown);
        //seleniumActions.typeValue(searchField, request);
        seleniumActions.sleep(5000);
        String requestOption = "(//mat-option[contains(@id,'otalio-infinite-select')])[1]";
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(requestOption)));

        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(requestOption)));
        }
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath(requestOption)));
//        try {
//            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + request + "')]")));
//        } catch (StaleElementReferenceException ste) {
//            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + request + "')]")));
//        }
    }

    public void clickRequest() {
        seleniumActions.clickOnElement(requestDropDown);
        seleniumActions.moveToElementAndClick(what);
    }

    public void clickRequestFastCreation(){
        seleniumActions.clickOnElement(requestDropDown);
        seleniumActions.moveToElementAndClick(heading);
    }

    public void selectLocationWhereFastCreation() {
        seleniumActions.clickOnElement(locationInFast);
        seleniumActions.moveToElementAndClick(heading);

    }


    public void clickOnAddIcon() {
        seleniumWaits.untilElementIsVisible(addIcon);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(addIcon);
    }


    public void validateListSortedNewestToOldest() {

        seleniumWaits.untilElementIsVisible(requestFirstRow);

        // Now safely find all request number links in column 2
        List<WebElement> requestElements = webDriver.findElements(
                By.cssSelector("tr.dx-data-row td[aria-colindex='2'] a"));

        List<Integer> actualNumbers = new ArrayList<>();

        // Extract numeric part from text like LUN-10534
        for (WebElement element : requestElements) {
            String fullText = element.getText().trim();         // Example: LUN-10534
            String numericOnly = fullText.replaceAll("\\D+", ""); // Remove all non-digits
            actualNumbers.add(Integer.parseInt(numericOnly));
        }

        // Log the first and second numbers if they exist
        if (actualNumbers.size() >= 2) {
            logger.info("First number: {}", actualNumbers.get(0));
            logger.info("Second number: {}", actualNumbers.get(1));
                    } else if (actualNumbers.size() == 1) {
            logger.info("Only one number found: {}", actualNumbers.get(0));
        } else {
            logger.warn("No numbers found in the list.");
        }

        // Create expected sorted list in descending order
        List<Integer> expectedNumbers = new ArrayList<>(actualNumbers);
        expectedNumbers.sort(Collections.reverseOrder());

        // Assertion
        Assert.assertEquals(actualNumbers, expectedNumbers,
                "Request list is NOT sorted from newest to oldest.");
    }

    public void clickExpandFilterIcon(){
        seleniumActions.clickOnElement(expandFlterIcon);

    }

    public void validateFilterLabels() {

        validateLabelsInContainer(requestOverviewFilterContainer, HotelMaintenanceConstants.EXPECTED_REQUESTOVERVIEW_LABELS);
    }

    /**
     * Generic method to validate labels inside a given container
     * @param container WebElement representing the section containing labels
     * @param expectedLabels List of expected label strings
     */


    private void validateLabelsInContainer(WebElement container, List<String> expectedLabels) {
        List<WebElement> labelElements = container.findElements(
                By.cssSelector("div.text-nowrap.font-weight-bold")
        );

        System.out.println("Found labels on page:");
        labelElements.forEach(e -> System.out.println("  - " + e.getText().trim()));

        for (String expectedLabel : expectedLabels) {
            boolean found = false;

            for (WebElement labelElement : labelElements) {
                // Normalize text: trim and replace non-breaking spaces
                String actualText = labelElement.getText().trim().replace("\u00A0", " ");

                if (actualText.equals(expectedLabel)) {
                    // Highlight the element
                    seleniumActions.highLighterMethod(labelElement);

                    System.out.println("Validating label: Expected='" + expectedLabel + "' -> FOUND, Actual='" + actualText + "'");
                    found = true;
                    break;
                }
            }

            if (!found) {
                String allActual = labelElements.stream()
                        .map(e -> e.getText().trim().replace("\u00A0", " "))
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("No labels found");
                System.out.println("Validating label: Expected='" + expectedLabel + "' -> NOT FOUND! Actual labels: [" + allActual + "]");
            }

            Assert.assertTrue(found, "Label not found: " + expectedLabel);
        }
    }

    public List<String> getHighlightedStatusListOfAll() {
        return statusListOfAll.stream()
                .map(element -> {
                    // Highlight each element while extracting text
                    seleniumActions.highLighterMethod(element);
                    return element.getText().trim();
                })
                .collect(Collectors.toList());
    }

}


