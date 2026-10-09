package com.nclh.pageObjects;

import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class

EntertainmentPage {

    private WebDriver webDriver;
    SeleniumActions seleniumActions;
    CrewInformationPage crewInformationPage;
    PointOfSalePage pointOfSalePage;
    CommonFunctions commonFunctions;
    SeleniumWaits seleniumWaits;
    private ShipSecurityPage shipSecurityPage;
    CommonPage commonPage;

    public EntertainmentPage(WebDriver webDriver) {
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

//    @FindBy(css = "[routerlink='new'] span[class*='button-ripple']")
//    private WebElement addIcon;

    @FindBy(css = "[placeholder='Code']")
    private WebElement codeField;

    @FindBy(css = "[placeholder='Description']")
    private WebElement descriptionField;

    @FindBy(css = "[placeholder='Category']")
    private WebElement categoryField;

    @FindBy(css = "[placeholder='Description (EN)']")
    private WebElement descriptionEN;

    @FindBy(xpath = "//otalio-time-picker-adapter[contains(@mattooltip,'time required to set up')]//descendant ::div/input[@autocomplete='off']")
    private WebElement defaultSetupTime;

    @FindBy(xpath = "//otalio-time-picker-adapter[contains(@mattooltip,'time required to clean up')]//descendant ::div/input[@autocomplete='off']")
    private WebElement defaultCleanupTime;

    @FindBy(css = "[placeholder='Search']")
    private WebElement searchField;

    @FindBy(css = "[placeholder='Entertainment Options']")
    private WebElement entertainmentOptions;

    @FindBy(xpath = "//div[@role='tab']/div[contains(text(),'Events')]")
    private WebElement eventsTab;

    @FindBy(xpath = "//otalio-required-personnel-table//button")
    private WebElement addRequiredPersonal;

    @FindBy(css = "[placeholder='Operational Position']")
    private WebElement operationalPosition;

    @FindBy(css = "[placeholder='Minimum']")
    private WebElement minimum;

    @FindBy(css = "[placeholder='Maximum']")
    private WebElement maximum;

    @FindBy(css = "[placeholder='Location']")
    private WebElement locationDropdown;

    @FindBy(css = "[placeholder='Location Type']")
    private WebElement locationTypeDropdown;

    @FindBy(xpath = "//input[contains(@placeholder,'Search')]")
    private WebElement search;

    @FindBy(css = "[placeholder='Description']")
    private WebElement description;

    @FindBy(css = "[placeholder='Location']")
    private WebElement locationDropDown;

    @FindBy(css = "[placeholder='Priced By']")
    private WebElement pricedBy;

    @FindBy(css = "[placeholder='Quantity Available']")
    private WebElement quantityAvailable;

    @FindBy(css = "[placeholder='Venue Requirements']")
    private WebElement venueRequirements;

    @FindBy(css = "[placeholder='Special Instructions/Requirements']")
    private WebElement specialInstructions;

    @FindBy(xpath = "//otalio-complex-filter-label//div/strong[contains(text(),'Description')]")
    private WebElement descriptionFilter;

    @FindBy(xpath = "//tbody/tr/td[@role='columnheader'][contains(@style,'min-width')]/div")
    private List<WebElement> tableHeaderItems;

    @FindBy(css = "#item-codes-list-add-button span icon")
    private  WebElement addButton;

    @FindBy(xpath = "//button[@id='locations-list-add-button']")
    private WebElement addLocationButton;

    @FindBy(xpath = "(//table/tbody/tr/td[@role='gridcell']/div/a)[1]")
    private WebElement code;

    @FindBy(xpath = "//*[@mattooltip='Clear All Filters']")
    private WebElement resetFilter;

    @FindBy(css = "a[id*='item-codes']")
    private List<WebElement> itemCodeRows;

    @FindBy(xpath = "//img[@class='img-fluid']")
    private WebElement errorPage;

    @FindBy(xpath = "//otalio-time-picker-adapter[@placeholder='Start Date Time']//dx-date-box//div/input[@type='hidden']")
    private WebElement startTime;

    @FindBy(xpath = "//div[contains(@class,'scheduler-appointment-content')]/div/div//div/div/p")
    private List<WebElement> eventListOfAll;

    @FindBy(xpath = "//mat-select[@formcontrolname='ticketingType']/div/div/span/span")
    private WebElement ticketingTypeText;

    @FindBy(css = "[placeholder='Exp. Attendance']")
    private WebElement expAttendanceField;

    @FindBy(xpath = "//div[contains(text(),'Delete Event')]")
    private WebElement deleteEvent;

    @FindBy(xpath = "//mat-step-header/descendant::div[contains(text(),'Notes & History')]")
    private WebElement notesHistory;

    @FindBy(xpath = "//mat-step-header/descendant::div[contains(text(),'Participants')]")
    private WebElement participants;

    @FindBy(xpath = "//mat-step-header/descendant::div[contains(text(),'Items')]")
    private WebElement items;

    @FindBy(xpath = "//button[@id='notes-list-add-items-button']")
    private WebElement addNotesButton;

    @FindBy(css = "[placeholder='Note']")
    private WebElement noteField;

    @FindBy(xpath = "//mat-checkbox//span[@class='mat-checkbox-inner-container']")
    private WebElement clientNoteCheckBox;

//    @FindBy(xpath = "(//span[text()='Crew']/ancestor::otalio-data-table//td[@aria-colindex='1']/div[@aria-label='Select row'])[1]")
    @FindBy(xpath = "(//td[@aria-colindex='1']/div[@aria-label='Select row'])[1]")
    private WebElement addParticipantCheckBox;

    @FindBy(xpath = "//td[@aria-colindex='9' and not(@aria-label='Column Location Type')]")
    private List<WebElement> locationTypeText;

    @FindBy(xpath = "//td[@aria-colindex='2' and not(@aria-label='Column Description')]")
    private List<WebElement> eventTypeText;

    @FindBy(xpath = "(//td[contains(@class,'dx-scheduler-date-table-cell')]//div[contains(@class,'dx-template-wrapper') and not(contains(@class,'disable-date-gray'))])[3]")
    private WebElement unoccupiedCell;

    @FindBy(xpath = "(//div[contains(@class, 'disable-date-gray')])[1]")
    private WebElement disabledCell;

    @FindBy(css = "[id='items-list-add-items-button']")
    private WebElement addItemsIcon;

    @FindBy(css = "[placeholder='Item']")
    private WebElement itemDropdown;

    @FindBy(css = "[placeholder='Quantity']")
    private WebElement quantity;

    public void selectQuantity(){
        seleniumActions.clickOnElement(quantity);
        seleniumActions.typeValue(quantity,"1");
    }

    public void selectItemDropdown(){
        seleniumWaits.untilElementIsVisible(itemDropdown);
        seleniumActions.clickOnElement(itemDropdown);
        seleniumWaits.untilElementIsVisible((webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-itemCodeEntityId')])[1]"))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(("(//mat-option[contains(@id,'otalio-infinite-select-itemCodeEntityId')])[1]"))));
    }

    public void clickOnAddItemsIcon(){
        seleniumWaits.untilElementIsVisible(addItemsIcon);
        seleniumActions.clickOnElement(addItemsIcon);
    }

    public void clickOnDisabledCell(){
        seleniumActions.clickOnElement(disabledCell);
    }

    public void clickOnUnoccupiedCell(){
        // seleniumWaits.untilElementIsClickable(unoccupiedCell);
        seleniumActions.doubleClickOnElement(unoccupiedCell);
    }

    public void clickOnItems(){
        seleniumWaits.untilElementIsVisible(items);
        seleniumActions.clickOnElement(items);
    }


    public List<String> getEventTypeListOfAll() {
        seleniumActions.sleep(3000);
        return eventTypeText.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public List<String> getLocationListOfAll() {
        seleniumActions.sleep(3000);
        return locationTypeText.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void addParticipant(){
        seleniumWaits.untilElementIsVisible(addParticipantCheckBox);
        seleniumActions.clickOnElement(addParticipantCheckBox);
    }

    public void clickOnAddNotes(String text){
        seleniumWaits.untilElementIsVisible(addNotesButton);
        seleniumActions.clickOnElement(addNotesButton);
        seleniumWaits.untilElementIsVisible(noteField);
        seleniumActions.typeValue(noteField,text);
        seleniumActions.clickOnElement(clientNoteCheckBox);
        seleniumActions.sleep(2000);
    }

    public void clickOnNotesHistory(){
        seleniumWaits.untilElementIsVisible(notesHistory);
        seleniumActions.clickOnElement(notesHistory);
    }

    public void clickOnParticipants(){
        seleniumWaits.untilElementIsVisible(participants);
        seleniumActions.clickOnElement(participants);
    }

    public void switchToNewTab(){
        seleniumActions.sleep(4000);
        seleniumActions.switchToOpenedWindow();
        seleniumActions.sleep(2000);
    }

    public void waitForOneMinute(){
        seleniumActions.sleep(60000);
    }

    public void typeExpAttendanceField(String count){
        seleniumActions.clickOnElement(expAttendanceField);
        seleniumActions.clearValue(expAttendanceField);
        seleniumActions.typeValue(expAttendanceField,count);
    }


    public String getTicketingTypeText(){
        seleniumActions.sleep(2000);
        return seleniumActions.getTextMessage(ticketingTypeText);
    }

    public List<String> getEventsListOfAll() {
        seleniumActions.sleep(3000);
        return eventListOfAll.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public String startTimeText() throws ParseException {
        // Assume startTime.getAttribute("value") returns a string like "2025-02-03T17:38:38"
        String timex = startTime.getAttribute("value");
        System.out.println("=======================>" + timex);

        // Extract the time part of the string (HH:mm:ss format)
        assert timex != null;
        String[] parts = timex.split("T");
        if (parts.length != 2) {
            throw new ParseException("Invalid time format", 0);
        }
        // Get the time part (HH:mm:ss format)
        String t24HrFormat = parts[1];
        System.out.println("=======================>" + t24HrFormat);

        // Convert the 24-hour time to 12-hour format
        return commonFunctions.timeStampConverter(t24HrFormat);
    }

    public void selectLocation(int number){
        seleniumActions.clickOnElement(locationDropdown);
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-locationId')]/span)['"+ number + "']")));
        } catch (StaleElementReferenceException stale) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-locationId')]/span)['"+ number + "']")));
        }
    }

    public void selectLocationType(String location){
        seleniumActions.clickOnElement(locationTypeDropdown);
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + location + "')]")));
        } catch (StaleElementReferenceException stale) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + location + "')]")));
        }
    }

    public void eventCategoryDropDownGroup(String group){
        seleniumActions.typeValue(search,group);
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsVisible((webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-activityCategoryId')])[1]"))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(("(//mat-option[contains(@id,'otalio-infinite-select-activityCategoryId')])[1]"))));
    }

    public void eventCategoryDropDown(){
        seleniumWaits.untilElementIsVisible((webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-activityCategoryId')])[1]"))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(("(//mat-option[contains(@id,'otalio-infinite-select-activityCategoryId')])[1]"))));
    }

    public void eventTypeDropDown(){
        seleniumWaits.untilElementIsVisible((webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-activityTypeEntityId-field')]/span)[1]"))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-activityTypeEntityId-field')]/span)[1]")));
    }

    public void eventTypeTenderOperationDropDown(){
        seleniumWaits.untilElementIsVisible((webDriver.findElement(By.xpath("(//mat-option/span[contains(text(),'Tender')])[1]"))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option/span[contains(text(),'Tender')])[1]")));
    }

    public boolean checkErrorPageIsDisplayed(){
        seleniumWaits.untilElementIsVisible(errorPage);
        return seleniumActions.isElementDisplayed(errorPage);
    }

    public void setQuantityAvailable(String number){
        seleniumActions.typeValue(quantityAvailable,number);
    }

    public void doubleClickOnEvent(String time){
        seleniumActions.sleep(3000);
        String presentEvent = "//div[contains(@class,'scheduler-appointment-content')]/div/div//div/div/p[contains(text(),'"+time+"')]";
       try {
           seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(presentEvent))));
           seleniumActions.doubleClickOnElement(webDriver.findElement(By.xpath(String.format(presentEvent))));

       }catch (StaleElementReferenceException stale) {
           seleniumActions.doubleClickOnElement(webDriver.findElement(By.xpath(String.format(presentEvent))));
           seleniumActions.sleep(2000);
       }
    }

    public void rightClickOnEvent(String time){
        seleniumActions.sleep(2000);
        String presentEvent = "//div[contains(@class,'scheduler-appointment-content')]/div/div//div/div/p[contains(text(),'"+time+"')]";
        seleniumActions.rightClickAndClickOnOption(webDriver.findElement(By.xpath(String.format(presentEvent))),deleteEvent);
        seleniumActions.sleep(2000);

    }

    public void setPricedBy(String priced){
        seleniumActions.clickOnElement(pricedBy);
        seleniumWaits.untilElementIsVisible((webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + priced + "')]"))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + priced + "')]")));
    }

    public void setLocationDropDown(String location){
        seleniumActions.clickOnElement(locationDropdown);
        seleniumActions.typeValue(search,location);
        seleniumWaits.untilElementIsVisible((webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + location + "')]"))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + location + "')]")));
    }

    public int isItemCodeRowsPresent() {
        seleniumActions.sleep(10000);
        try{
        return itemCodeRows.size();}
        catch (Exception e){
            return webDriver.findElements(By.cssSelector("a[id*='item-codes']")).size();
        }
    }

    public void enterSpecialInstructions(String text){
        seleniumActions.typeValue(specialInstructions,text);
    }

    public String getSpecialInstruction(){
        return seleniumActions.getTextMessage(specialInstructions);
    }

    public String getVenueText(){
        return seleniumActions.getTextMessage(venueRequirements);
    }

    public String getEntertainmentOptions(){
        return seleniumActions.getTextMessage(entertainmentOptions);
    }

    public void clickCode(){seleniumActions.clickOnElement(code);}

    public void addIcon(){
      seleniumActions.clickOnElement(addButton);
    }

    public void setAddLocationButton(){
        seleniumActions.clickOnElement(addLocationButton);
    }

    public void switchToChildWindow(){
        seleniumActions.switchToOpenedWindow();
    }

    public void switchToWindowUsingWindowTitle(String title){
        seleniumActions.sleep(2000);
        System.out.println(webDriver.getWindowHandle());
        Set<String> windowIds = webDriver.getWindowHandles();
        System.out.println("windowIds :: " + windowIds);
        for (String window : windowIds) {
            seleniumActions.sleep(2000);
            webDriver.switchTo().window(window);
            System.out.println(webDriver.getTitle());
            if(webDriver.getTitle().contains(title)) {
                System.out.println("You are on right window");
                break;
            }
        }
    }

    public void switchToParentWindow(String windowId){
        webDriver.switchTo().window(windowId);
    }

    public String getParentWindowId(){
        return webDriver.getWindowHandle();
    }

    public String getDescriptionCode(String columnName) {
        int index = getIndexOfColumnName(columnName) + 1;
        System.out.println("index :: " + index);
        String departmentName = "//tbody/tr/td/div[contains(text(),'" + columnName + "')]/ancestor::table/tbody/tr/td[@role='columnheader'][not(contains(@class,'select'))]/div[text()]/following::table/tbody/tr[1]/td[not(contains(@class,'select'))][" + index + "]";
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
                seleniumActions.sleep(1000); //due to table header loading given 500 millisecs
                seleniumWaits.untilElementIsClickable(tableHeaderItems.get(1));
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

    public void setDescriptionFilter(String value){
        //seleniumWaits.untilElementIsVisible(descriptionFilter);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(descriptionFilter);
        seleniumActions.typeValue(description,value);
        seleniumActions.clickOnElement(descriptionFilter);
    }

    public void enterMinAndMaxValue(String min,String max){
        seleniumActions.typeValue(minimum,min);
        seleniumActions.typeValue(maximum,max);

    }

    public void clickAddRequiredPersonal(String operation){
        seleniumActions.clickOnElement(addRequiredPersonal);
        seleniumWaits.untilElementIsVisible(operationalPosition);
        seleniumActions.clickOnElement(operationalPosition);
        seleniumWaits.untilElementIsVisible((webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + operation + "')]"))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + operation + "')]")));
    }

    public void enterEntertainmentOptions(String text){
        seleniumActions.typeValue(entertainmentOptions,text);
    }

    public void enterVenueRequirements(String text){
        seleniumActions.typeValue(venueRequirements,text);
    }


    public void setDefaultSetUpTime(String time){
        seleniumActions.clickOnElement(defaultSetupTime);
        seleniumActions.sleep(2000);
        seleniumActions.typeValue(defaultSetupTime,time);
        //seleniumWaits.untilElementIsClickable((webDriver.findElement(By.xpath("(//div[@role='option'][@aria-selected='false']/div[contains(text(),'" + time + "')])[2]"))));
        //seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//div[@role='option'][@aria-selected='false']/div[contains(text(),'" + time + "')])[2]")));
    }

    public void setDefaultCleanUpTime(String time){
        seleniumActions.clickOnElement(defaultCleanupTime);
        seleniumActions.sleep(3000);
        seleniumActions.typeValue(defaultCleanupTime,time);
        seleniumActions.clickOnElement(defaultCleanupTime);
        //seleniumWaits.untilElementIsVisible((webDriver.findElement(By.xpath("//div[@aria-selected='false'][@role='option']/div[contains(text(),'" + time + "')]"))));
        //seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[@aria-selected='false'][@role='option']/div[contains(text(),'" + time + "')]")));
    }

    public void enableToggle(String toggleName) {
        String toggleType = "//span[contains(text(),'%s')]/preceding-sibling::span/input";
        seleniumActions.sleep(4000);
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))));
        System.out.println(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))).getAttribute("aria-checked"));
        WebElement toggle = webDriver.findElement(By.xpath(String.format(toggleType, toggleName)));
        if (toggle.getAttribute("aria-checked").equals("false")) {
            seleniumActions.clickOnElement(toggle);
        }

    }

    public void clickEventsTab(){
        seleniumActions.clickOnElement(eventsTab);
    }

    public void categoryDropDown(String category){
        seleniumActions.clickOnElement(categoryField);
        seleniumActions.typeValue(searchField, category);
        seleniumWaits.untilElementIsVisible((webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + category + "')]"))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + category + "')]")));
    }

    public  void enterDescriptionField(String text){
        seleniumActions.typeValue(descriptionField,text);
    }

    public void enterDescriptionEN(String text){
        seleniumActions.typeValue(descriptionEN,text);
    }

    public int getRandomNumber() {
        return commonFunctions.getRandomNumber(999, 9999);
    }

    public void enterFieldText(int number){
        seleniumActions.typeValue(codeField,String.valueOf(number));
    }

    public void clickOnAddButton(){
        // Element not working so opening new window and navigating to url
       // seleniumActions.clickOnElement(addIcon);
        seleniumActions.openNewTabOrWindow();
        webDriver.get("https://otalioshiplab-qa.apps-otalio-ship-vmc-qa.eksa-vmc.nclh.io/configuration/task-types/new");

    }

    public void navigateToSave(String button1) {
        seleniumActions.sleep(5000);
        String text = "//button[@id='items-save-button']//span[text()='" + button1 + "' or normalize-space()='" + button1 + "']";
        if (webDriver.findElements(By.xpath(text)).size() > 0){
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(text)));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
        }
    }
}
