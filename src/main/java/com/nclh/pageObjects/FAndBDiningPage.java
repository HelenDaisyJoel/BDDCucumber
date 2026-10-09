package com.nclh.pageObjects;

import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.nclh.utilities.SeleniumActions;

public class FAndBDiningPage {
    private WebDriver webDriver;
    SeleniumActions seleniumActions;
    CrewInformationPage crewInformationPage;
    ShipSecurityPage shipSecurityPage;
    CommonFunctions commonFunctions;
    SeleniumWaits seleniumWaits;
    CommonPage commonPage;

    public FAndBDiningPage(WebDriver webDriver) {
        seleniumActions = new SeleniumActions(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        crewInformationPage = new CrewInformationPage(webDriver);
        shipSecurityPage = new ShipSecurityPage(webDriver);
        commonFunctions = new CommonFunctions();
        commonPage = new CommonPage(webDriver);
    }

    @FindBy(xpath = "//div[@class='mat-tab-links']/*[name()='a'][contains(text(),'Map')]")
    private WebElement mapTab;

    @FindBy(xpath = "//div[@class='mat-tab-links']/*[name()='a'][contains(text(),'Reservations')]")
    private WebElement reservationTab;

    @FindBy(xpath = "//button[contains(@id,'location-item-create-reservation-button')]")
    private WebElement inventorySlots;

    @FindBy(css = "[label='Location']")
    private WebElement locationDropDown;

    @FindBy(css = "[placeholder='Amount of Guests']")
    private WebElement amountOfGuests;

    @FindBy(css = "[placeholder='Search...']")
    private WebElement searchOfGuests;

    @FindBy(css = "#gs-select-button")
    private WebElement selectButton;

    @FindBy(css = "[placeholder='Time']")
    private WebElement timeDropDown;

    @FindBy(css = "[placeholder='Location']")
    private WebElement locationSearch;

    @FindBy(xpath = "//span[normalize-space()='Yes']")
    private WebElement yesButton;

    @FindBy(xpath = "//tbody/tr/td/div/descendant::otalio-user-name/span")
    private WebElement reservationGuest;

    @FindBy(xpath = "//otalio-user-name/span")
    private List<WebElement> guestNames;

    @FindBy(xpath = "//otalio-user-name/../../../../button/span/icon/*[name()='svg']/*[name()='use']")
    private List<WebElement> crownIcon;

    @FindBy(xpath = "//otalio-complex-filter-multi-label-content/div[contains(text(),'Location')]")
    private WebElement location;

    @FindBy(xpath = "//button[@id='dining-reservation-edit-button']")
    private WebElement editIcon;

    @FindBy(xpath = "//div[contains(text(),'Tables Selected')]/../descendant::otalio-chip/div/div")
    private WebElement selectedTable;

    @FindBy(xpath = "//tbody/tr/td[@role='columnheader'][contains(@style,'min-width')]/div")
    private List<WebElement> tableHeaderItems;

    @FindBy(xpath = "//otalio-reservation-card-small/..//otalio-user-name")
    private WebElement reservationCard;

    @FindBy(xpath = "//div[text()='Status']/ancestor::otalio-table-info/descendant::div/otalio-chip/div[contains(@class,'purple')]/div")
    private WebElement reservationStatus;

    @FindBy(xpath = "(//div[text()='Status']/ancestor::otalio-table-info/descendant::div/otalio-chip/div[contains(@class,'inline')]/div)[2]")
    private WebElement reservationStatusInHouse;

    @FindBy(xpath = "//div[text()='Status']/ancestor::otalio-table-info/descendant::div/otalio-chip/div")
    private WebElement reservationStatusOpen;

    @FindBy(xpath = "//div[text()='Status']/ancestor::otalio-table-info/descendant::div/otalio-chip/div[contains(@class,'green')]")
    private WebElement reservationStatusOpenGreen;


    @FindBy(xpath = "//otalio-guest-search-card//icon[starts-with(@icon,'plus')]")
    private List<WebElement> plusButtons;

    @FindBy(id = "reservation-editor-product-select")
    private WebElement productDropDown;

    @FindBy(xpath = "//mat-pseudo-checkbox[contains(@class,'checked')]")
    private List<WebElement> checkBoxes;

    @FindBy(xpath = "//h2[text()='Select Crew, Guest or Visitor']")
    private WebElement searchPageHeading;

    @FindBy(xpath = "//b[@class='ng-star-inserted'][contains(text(),'Status:')]")
    private WebElement status;

    @FindBy(css = "#reservation-add-guest-button" )
    private WebElement addButton;

    //@FindBy(xpath = "(//button[contains(@id,'location-item-create-reservation-button-cagney')])[1]")
    @FindBy(xpath = "(//button[contains(@id,'location-item-create-reservation-button-cagney')][.//span[contains(normalize-space(.),'4 / 4')]])[1]")
    private WebElement firstInventorySlot;

    @FindBy(xpath = "//otalio-reservation-item//div/div/div/div")
    private WebElement restaurantsName;

    @FindBy(xpath = "//button[@id='reservations-overview-confirm-button']")
    private WebElement confirmButton;

    @FindBy(xpath = "//otalio-complex-filter-multi-label-content/div[contains(text(),'Location')]")
    private WebElement locationInReservation;

    @FindBy(css = "#breadcrumbs-title")
    private WebElement breadCrumbTitle;

    @FindBy(xpath = "//otalio-user-name/span")
    private WebElement guestNameInTable;

    @FindBy(xpath = "//a[contains(text(),'Dining')]")
    private WebElement dining;

    @FindBy(xpath = "//otalio-complex-filter-multi-label-content/div[contains(text(),'Cabin Number:')]")
    private WebElement cabinNumberFilter;

    @FindBy(css = "[placeholder='Cabin Number']")
    private WebElement cabinSearch;

    @FindBy(xpath = "//otalio-location-list/descendant::div[contains(text(),'Cagney')]")
    private WebElement locationSelect;

    @FindBy(css = "[placeholder='Last Name']")
    private WebElement lastNameInputField;

    @FindBy(xpath = "//*[@href='#dining']/ancestor::otalio-time-line-event/descendant::div[contains(@class,'overflow-hidden')]")
    private List<WebElement> diningList;

    @FindBy(xpath = "//a[contains(text(),'Cagney')]")
    private WebElement cagneyLink;

    @FindBy(css = "[placeholder='Pager Nr']")
    private WebElement pagerNumber;

    @FindBy(xpath = "//td/div/a[contains(text(),'APS')]")
    private WebElement pagerSystem;

    @FindBy(xpath = "//button[contains(text(),'Return Pagers')]")
    private WebElement returnPagers;

    @FindBy(xpath = "//span/a[contains(text(),'Layout Event Scheduler')]")
    private WebElement layoutEventScheduler;

    @FindBy(css = "#table-info-join-tables-button")
    private WebElement tableJoin;

    @FindBy(css = "#table-info-unjoin-all-button")
    private WebElement unjoin;

    @FindBy(css = "button[id*='Status'] > icon")
    private WebElement statusList;

    @FindBy(css = "[class*='pseudo-checkbox-checked']")
    private List<WebElement> statusCheckBoxes;

    @FindBy(xpath = "//span[text()='Table Close Reason']")
    private WebElement reasonDropdown;

    @FindBy(css = "[formcontrolname='tableCloseComment']")
    private WebElement addComment;

    @FindBy(xpath = "//input[contains(@id,'comment-input')]")
    private WebElement addReservationComment;

    @FindBy(xpath = "//td[@aria-colindex='16']//span[contains(@aria-describedby,'describedby-message')]")
    private WebElement commentInTable;

    public boolean checkForGreen(){
        seleniumWaits.untilElementIsVisible(reservationStatusOpenGreen);
        return seleniumActions.isElementDisplayed(reservationStatusOpenGreen);
    }

    public String getCommentText(){
        seleniumWaits.untilElementIsVisible(commentInTable);
        return seleniumActions.getTextMessage(commentInTable);
    }

    public void typeReservationComment(String comment){
        seleniumActions.typeValue(addReservationComment,comment);
    }

    public void typeReasonComment(String comment) {
        //seleniumActions.clickOnElement(addComment);
        seleniumActions.typeValue(addComment, comment);
    }


    public void selectReasonDropdown(String reason){
        seleniumActions.clickOnElement(reasonDropdown);
        seleniumActions.sleep(2000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'"+ reason +"')]")));

    }

    public void clickOnOverRide(String text){
        if(webDriver.findElements(By.xpath("//span[contains(text(),'" + text + "')]")).size()>0) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[contains(text(),'" + text + "')]")));

        }
    }


    public void clearStatus(){
        seleniumActions.clickOnElement(statusList);
        for (int i=0; i< statusCheckBoxes.size(); i++){
            seleniumActions.sleep(2000);
            seleniumActions.clickOnElement(statusCheckBoxes.get(i));
        }
        if(statusCheckBoxes.size()>0){
            statusCheckBoxes.get(0).click();
        }
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(2000);
        seleniumActions.pressEscapeKey();
    }

    public void clickOnUnJoin(){
        seleniumWaits.untilElementIsVisible(unjoin);
        seleniumActions.sleep(2000);
        seleniumActions.clickOnElement(unjoin);
        seleniumActions.sleep(2000);
    }

    public void tableJoin(String number){
        seleniumActions.clickOnElement(tableJoin);
        seleniumActions.sleep(2000);
        try {
            seleniumActions.moveToElement(webDriver.findElement(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g'][@id='table_" + number + "']")));
            seleniumActions.clickOnElement(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g'][@id='table_" + number + "']"));
        } catch (StaleElementReferenceException ste) {
            seleniumActions.moveToElement(webDriver.findElement(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g'][@id='table_" + number + "']")));
            seleniumActions.clickOnElement(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g'][@id='table_" + number + "']"));
        }

    }

    public void clickLayoutEvent(String event){
        if(webDriver.findElements(By.xpath("//span/a[contains(text(),'" + event + "')]")).size()>0) {
            seleniumActions.clickOnElement(layoutEventScheduler);

        }
    }

    public void clickOnReturnPagers(){
        seleniumWaits.untilElementIsClickable(returnPagers);
        seleniumActions.clickOnElement(returnPagers);
    }

    public void openPagerSystem(){
        seleniumWaits.untilElementIsClickable(pagerSystem);
        seleniumActions.moveToElementAndClick(pagerSystem);
        seleniumWaits.untilElementIsClickable((webDriver.findElement( By.xpath("//td[@role='columnheader']/div[@role='checkbox']/div/span"))));
        seleniumActions.moveToElementAndClick((webDriver.findElement( By.xpath("//td[@role='columnheader']/div[@role='checkbox']/div/span"))));
    }


    public void changeStatusAndFilterDining(String filterName, String status) {
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[contains(text(),'" + filterName + "')]/following-sibling::span/../../following-sibling::icon/*[name()='svg']")));

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
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[contains(text(),'" + filterName + "')]/following-sibling::span/../../following-sibling::icon/*[name()='svg']")));
        seleniumActions.sleep(2000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[normalize-space()='" + status + "']/..//preceding-sibling::mat-pseudo-checkbox")));
    }

    public void  clickOnReservation(String name){
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("//otalio-user-name/span[contains(text(),'" + name + "')]")));
        seleniumActions.doubleClickOnElement(webDriver.findElement(By.xpath("//otalio-user-name/span[contains(text(),'" + name + "')]")));
    }

    public void selectPager(){
        seleniumWaits.untilElementIsVisible(pagerNumber);
        seleniumActions.clickOnElement(pagerNumber);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(By.xpath("//mat-option[2]"));
    }

    public void clickOnCagneyLink(){
        seleniumActions.moveToElementAndClick(cagneyLink);
        seleniumActions.sleep(3000);
    }

    public List<String> getDiningList() {
        return diningList.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void clickOnLocationSelect(){
        seleniumWaits.untilElementIsVisible(locationSelect);
        seleniumActions.clickOnElement(locationSelect);
    }

    public void clickOnGuestNameInTable(){
        seleniumActions.clickOnElement(guestNameInTable);
    }


    public void searchLocation(String cabin) {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(cabinNumberFilter);
        seleniumActions.typeValue(cabinSearch, cabin);
        seleniumActions.sleep(3000);
        String Location = "//span[normalize-space()='" + cabin + "']";
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(Location))));
        seleniumActions.clickOnElement(cabinNumberFilter);
        seleniumActions.sleep(3000);
    }

    public void searchLastName(String name) {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(lastNameInputField);
        seleniumActions.typeValue(lastNameInputField, name);
        seleniumActions.sleep(3000);
       /* String Location = "//span[normalize-space()='" + name + "']";
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(Location))));
        seleniumActions.clickOnElement(lastNameInputField);
        seleniumActions.sleep(3000);*/
    }

    public void clickOnDining(){
        seleniumActions.clickOnElement(dining);
    }

    public String getGuestNameInTable(){
        seleniumWaits.untilElementIsVisible(guestNameInTable);
        return seleniumActions.getTextMessage(guestNameInTable);
    }

    public void checkOptionInTheFilter(String option) {
        seleniumActions.clickOnElement(crewInformationPage.getReservationStatusList());
        String checkBox = "//div[text()='%s']/./preceding-sibling::mat-pseudo-checkbox";
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath(String.format(checkBox, option))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(checkBox, option))));
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(500); //after pressing esc key
        // seleniumActions.pressEscapeKey();
        // seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
    }


    public void selectLocationInReservation(String location) {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(locationInReservation);
        seleniumActions.clickOnElement(locationInReservation);
        String editMeetingLocation = "//span[normalize-space()=\"" + location + "\"]";
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(editMeetingLocation))));
        seleniumActions.pressEscapeKey();
        seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
    }

    public void clickOnConfirmButton(){
        seleniumActions.clickOnElement(confirmButton);
    }

    public String getRestaurantsName(){
        seleniumWaits.untilElementIsVisible(restaurantsName);
        return seleniumActions.getTextMessage(restaurantsName);
    }
    public void clickOnFirstInventorySlot(){
        seleniumActions.sleep(6000);
        seleniumWaits.untilElementIsClickable(firstInventorySlot);
        try {
            seleniumActions.clickOnElement(firstInventorySlot);
        } catch (StaleElementReferenceException sele) {
            seleniumActions.clickOnElement(firstInventorySlot);
        }
    }

    public void clickOnAddButton(){
        seleniumWaits.untilElementIsClickable(addButton);
        seleniumActions.clickOnElement(addButton);
    }

    public void applyFilter(String name) {
       seleniumWaits.untilElementIsVisible(status);
       seleniumActions.clickOnElement(status);

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

        seleniumActions.clickOnElement(status);
        String selectCheckBox = "//div[normalize-space()='%s']/preceding-sibling::mat-pseudo-checkbox";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(selectCheckBox, name))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(selectCheckBox, name))));
    }

    public void selectProductDropDown(String product){
        seleniumActions.moveToElementAndClick(productDropDown);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[contains(text(),'"+product+"')]")));
    }

    public void selectGuests() {
        seleniumActions.clickOnElement(plusButtons.get(0));
        seleniumActions.clickOnElement(plusButtons.get(1));
        seleniumActions.sleep(3000);
    }

    public String getReservationStatus(){
        seleniumActions.sleep(2000);
        return seleniumActions.getTextMessage(reservationStatus);
    }

    public String getReservationStatusInHouse(){
        seleniumActions.sleep(10000);
        return seleniumActions.getTextMessage(reservationStatusInHouse);
    }

    public String getReservationStatusOpen(){
        seleniumActions.sleep(10000);
        seleniumActions.scrollInToElement(reservationStatusOpen);
        return seleniumActions.getTextMessage(reservationStatusOpen);
    }

    public void clickOnReservationCard(){
        seleniumActions.clickOnElement(reservationCard);
    }

    public String getTableNumberText(String columnName) {
        int index = getIndexOfColumnName(columnName) + 2;
        System.out.println("index :: " + index);
        String departmentName = "//tbody/tr/td/div[contains(text(),'" + columnName + "')]/ancestor::table/tbody/tr/td[@role='columnheader'][not(contains(@class,'select'))]/div[text()]/following::table/tbody/tr[1]/td[not(contains(@class,'select'))][" + index + "]";
        String name = seleniumActions.getTextMessage(
                webDriver.findElement(By.xpath(departmentName))
        );
        System.out.println("table :: " + name);
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

        columns.removeAll(Arrays.asList(""));
        int columnIndex = columns.indexOf(columnName);
        return columnIndex;
    }

    public String getTableNumber(){
        return seleniumActions.getTextMessage(selectedTable);
    }

    public void clickOnEdit(){
        seleniumActions.clickOnElement(editIcon);
    }

    public int getNumberOfGuests(String name){
        seleniumWaits.untilElementInVisible(webDriver.findElement(By.xpath("//table/tbody/tr[@role='row']/td/div/a/otalio-user-name/span[contains(text(),'"+name+"' )]")));
        return webDriver.findElements(By.xpath("//table/tbody/tr[@role='row']/td/div/a/otalio-user-name/span[contains(text(),'"+name+"' )]")).size();
    }

    public void clickOnGuest(String name) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        for (int i = 0; i < 10; i++) {
            if (webDriver.findElements(By.xpath("//table/tbody/tr[@role='row']/td/div/a/otalio-user-name")).size() > 0) {

                break;
            } else {
                seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("//icon[contains(@icon,'arrow-right')]/*[name()='svg']/*[name()='use']")));
            }
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try {
            seleniumActions.moveToElement(webDriver.findElement(By.xpath("//table/tbody/tr[@role='row']/td/div/a/otalio-user-name/span[contains(text(),'"+name+"' )]")));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//table/tbody/tr[@role='row']/td/div/a/otalio-user-name/span[contains(text(),'"+name+"' )]")));
        } catch (StaleElementReferenceException ste) {
            seleniumActions.moveToElement(webDriver.findElement(By.xpath("//table/tbody/tr[@role='row']/td/div/a/otalio-user-name/span[contains(text(),'"+name+"' )]")));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//table/tbody/tr[@role='row']/td/div/a/otalio-user-name/span[contains(text(),'"+name+"' )]")));
        }

    }

    public String getGuestName(String name) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        for (int i = 0; i < 10; i++) {
            if (webDriver.findElements(By.xpath("//table/tbody/tr[@role='row']/td/div/a/otalio-user-name")).size() > 0) {

                break;
            } else {
                seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("//icon[contains(@icon,'arrow-right')]/*[name()='svg']/*[name()='use']")));
            }
        }

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String textMessage = null;

        try {
            seleniumActions.moveToElement(webDriver.findElement(By.xpath("//table/tbody/tr[@role='row']/td/div/a/otalio-user-name/span[contains(text(),'"+name+"' )]")));
            textMessage = seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//table/tbody/tr[@role='row']/td/div/a/otalio-user-name/span[contains(text(),'"+name+"' )]")));
        } catch (StaleElementReferenceException ste) {
            seleniumActions.moveToElement(webDriver.findElement(By.xpath("//table/tbody/tr[@role='row']/td/div/a/otalio-user-name/span[contains(text(),'"+name+"' )]")));
            textMessage = seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//table/tbody/tr[@role='row']/td/div/a/otalio-user-name/span[contains(text(),'"+name+"' )]")));
        }
        return textMessage;


    }

    public void selectLocationInList(String name){
        seleniumActions.clickOnElement(location);
        seleniumActions.typeValue(locationSearch,name);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div/span[contains(text(),'"+name+"')]")));

    }

    public String getGuestHavingColorCrown(String guest){
        String element = "//otalio-user-name/span[contains(text(),'"+guest+"')]/../../../../../button/span/icon/*[name()='svg']/*[name()='use']";
        String guestName = null;
        for (int i=0; i<2; i++){
            String colour = webDriver.findElement(By.xpath(String.format(element,guest))).getCssValue("color");
            String hexaColorCode = Color.fromString(colour).asHex();
            System.out.println("hexaColorCode :: " + hexaColorCode);

            if(hexaColorCode.equals("#e56100")){
                guestName = guest;

            }
        }
        System.out.println("guestName :: "+ guestName);
        return guestName;
    }


    public List<String> getGuestNames(){
        List<String> data = new ArrayList<>();
        for(WebElement element : guestNames){
            data.add(element.getText());
        }
        return data;
    }

    public void userConfirmPopup(){
        seleniumActions.sleep(3000);
        for(int i=0;i<2;i++){
            if(seleniumActions.isElementDisplayed(yesButton)){
                seleniumActions.clickOnElement(yesButton);
            }
        }
    }

    public void clickOnAdd(String add){
        seleniumActions.isElementDisplayed(webDriver.findElement(By.xpath("//span[text()='" + add + "' or normalize-space()='" + add + "']")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[text()='" + add + "' or normalize-space()='" + add + "']")));

    }

    public void selectTable(String table) {
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g'][@id='table_" + table + "']")));
    }

    public void clicOnSaveInSelectTable(String save) {
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//otalio-reservation-select-table//span[text()='" + save + "' or normalize-space()='" + save + "']")));
        seleniumActions.sleep(3000);
    }

    public void clickOnInventorySlot() {
        seleniumActions.clickOnElement(inventorySlots);
    }

    public void selectTime() {
        seleniumActions.moveToElementAndClick(timeDropDown);
        /*seleniumActions.isElementDisplayed(webDriver.findElement(By.xpath("//span/span[contains(text(),'"+time+"')]")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span/span[contains(text(),'"+time+"')]")));*/
        seleniumActions.isElementDisplayed(webDriver.findElement(By.xpath("(//mat-option/span/span)[1]")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option/span/span)[1]")));
    }

    public void clickOnSelect() {
        seleniumActions.clickOnElement(selectButton);
    }

    public void searchGuestUsingAlphabetic() {
        try {
            seleniumActions.clickOnElement(shipSecurityPage.getSearchIcon());
        } catch (JavascriptException je) {
            seleniumActions.moveToElementAndClickOnElement(shipSecurityPage.getSearchIcon(), shipSecurityPage.getSearchIcon());
        }
        seleniumActions.typeValue(shipSecurityPage.getSearchInputBox(), String.valueOf(commonFunctions.getRandomCharacter()));
    }

   /* public void selectGuests() {
        seleniumActions.isElementDisplayed(searchOfGuests);
        seleniumActions.typeValue(searchOfGuests, "a");
        seleniumActions.sleep(3000);
        for (int i = 6; i < 8; i++) {
            seleniumActions.clickOnElement(webDriver.findElements(By.xpath("//icon[starts-with(@icon,'plus')]")).get(i));
        }


    }*/

    public void typeAmountOfGuests(String number) {
        seleniumActions.isElementDisplayed(amountOfGuests);
        seleniumActions.clickOnElement(amountOfGuests);
        seleniumActions.clearValue(amountOfGuests);
        seleniumActions.typeValue(amountOfGuests, number);

    }

    public void selectLocation(String location) {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(locationDropDown);
        seleniumActions.clickOnElement(locationDropDown);
        String editMeetingLocation = "//mat-option/descendant::span[contains(text(),'Cagney')]";
        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(editMeetingLocation))));
    }

    public void clickOnReservationTab() {
        seleniumActions.clickOnElement(reservationTab);
        seleniumActions.clickOnElement(reservationTab);
        seleniumActions.sleep(4000);
    }

    public void clickOnMapTab() {
        seleniumActions.clickOnElement(mapTab);
        seleniumActions.clickOnElement(mapTab);
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsClickable(locationDropDown);
    }

    public void assignTable(String number){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        try {
            seleniumActions.moveToElement(webDriver.findElement(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g'][@id='table_" + number + "']")));
            seleniumActions.clickOnElement(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g'][@id='table_" + number + "']"));
        } catch (StaleElementReferenceException ste) {
            seleniumActions.moveToElement(webDriver.findElement(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g'][@id='table_" + number + "']")));
            seleniumActions.clickOnElement(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g'][@id='table_" + number + "']"));
        }

    }

    public boolean checkStatusAsBlocked(String table){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return seleniumActions.isElementDisplayed(webDriver.findElement(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g'][@id='table_" + table + "']/descendant::*[contains(@href,'icon_BLOCKED')]")));
    }

    public boolean checkTableIsJoined(String table){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return seleniumActions.isElementDisplayed(webDriver.findElement(By.xpath("//div[contains(text(),' Table Join')]/../descendant::div[contains(text(),'" + table + "')]")));
    }

    public void selectionOfTable(String number) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        for (int i = 0; i < 10; i++) {
            if (webDriver.findElements(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g']")).size() > 0) {

                break;
            } else {
                seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("//icon[contains(@icon,'arrow-right')]/*[name()='svg']/*[name()='use']")));
            }
        }

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try {
            seleniumActions.moveToElement(webDriver.findElement(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g'][@id='table_" + number + "']")));
            seleniumActions.clickOnElement(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g'][@id='table_" + number + "']"));
        } catch (StaleElementReferenceException ste) {
            seleniumActions.moveToElement(webDriver.findElement(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g'][@id='table_" + number + "']")));
            seleniumActions.clickOnElement(By.xpath("//otalio-table-map-canvas-table//*[name()='svg'] /*[name()='g'][@id='table_" + number + "']"));
        }


    }

}
