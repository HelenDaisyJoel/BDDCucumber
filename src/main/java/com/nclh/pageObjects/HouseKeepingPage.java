package com.nclh.pageObjects;

import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.nclh.utilities.SeleniumActions;

public class HouseKeepingPage {
    private WebDriver webDriver;
    private SeleniumActions seleniumActions;
    private SeleniumWaits seleniumWaits;
    private CommonPage commonPage;

    public HouseKeepingPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        seleniumActions = new SeleniumActions(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonPage = new CommonPage(webDriver);
    }

    @FindBy(xpath = "(//table/tbody/tr/td[@role='gridcell']/div/a[starts-with(@id,'passenger')])[1]")
    private WebElement guestFirstName;

    @FindBy(xpath = "//otalio-person-board-status/h2/span")
    private List<WebElement> status;

    @FindBy(xpath = "//a[contains(@id,'Fast Section Assignment')]")
    private WebElement fastSectionAssignment;

    @FindBy(xpath = "//button[contains(@id,'by-section')]")
    private WebElement bySection;

    @FindBy(xpath = "//button[contains(@id,'by_crew-button')]")
    private WebElement byCremMember;

    @FindBy(xpath = "(//tbody//tr[@aria-rowindex='1']/td[@aria-colindex='2'])[1]")
    private WebElement firstSection;

    @FindBy(xpath = "(//otalio-housekeeping-section-item//span/a)[1]")
    private WebElement sectionText;

    @FindBy(xpath = "(//otalio-housekeeping-section-item//span/a)[2]")
    private WebElement sectionTextTwo;

    @FindBy(xpath = "//input[@type='checkbox'][contains(@id,'sections0')]")
    private WebElement sectionOne;

    @FindBy(xpath = "//input[@type='checkbox'][contains(@id,'sections1')]")
    private WebElement sectionTwo;

    @FindBy(xpath = "//tbody//tr[@aria-rowindex='1']/td[@aria-colindex='1']")
    private WebElement firstCrewMember;

    @FindBy(xpath = "(//mat-checkbox[@class='mat-checkbox mat-primary'])[1]")
    private WebElement crewNameCheckBox;

    @FindBy(xpath = "//button/span[normalize-space()='Assign']")
    private WebElement assignButton;

    @FindBy(xpath = "//button/span[normalize-space()='Unassign']")
    private WebElement unAssignButton;

    @FindBy(xpath = "//button/div/b[normalize-space()='Public Location:']")
    private WebElement publicLocationDropdown;

    @FindBy(xpath = "(//otalio-section-assignment-crew-item//div/h4/a)[1]")
    private WebElement crewNameForSectionAssignment;

    @FindBy(xpath = "(//otalio-housekeeping-section-item//div/span/a)[1]")
    private WebElement sectionNameText;

    @FindBy(xpath = "(//otalio-section-assignment-crew-item//div/h4/a)[2]")
    private WebElement crewNameForSectionTwoAssignment;

    @FindBy(xpath = "(//otalio-housekeeping-section-item//div/span/a)[1]")
    private WebElement sectionForCrewNameAssignment;

    @FindBy(xpath = "//otalio-section-assignment-crew-item//div/h4/a")
    private List<WebElement> unassignedCrewList;

    @FindBy(xpath = "(//otalio-housekeeping-section-item/descendant::a)[1]")
    private WebElement sectionNameForCrewAssignment;

    @FindBy(xpath = "//otalio-housekeeping-section-item/descendant::a")
    private List<WebElement> sectionListOfAll;

    @FindBy(xpath = "//a[contains(@id,'crew-member')]")
    private List<WebElement> crewListOfAll;

    @FindAll({
            @FindBy(css = "[placeholder^='Search']"),
            @FindBy(xpath = "//input[@id='complex-filter-search-input']")
    })
    private WebElement sectionSearchBox;

    @FindBy(xpath = "//div[contains(text(),'Assigned Crew Members ')]")
    private WebElement assignedCrewMember;

    @FindBy(xpath = "//div[contains(text(),'Assigned Sections ')]")
    private WebElement assignedSections;

    @FindBy(xpath = "//div[contains(text(),'Unassigned Sections')]")
    private WebElement unAssignedSections;

    @FindBy(xpath = "//div[contains(text(), 'Unassigned Crew Members')]")
    private WebElement unAssignedCrewMember;

    @FindBy(xpath = "(//div[text()='Position:']/../span/otalio-chip/div[contains(@class,'notification')])[1]")
    private WebElement positionFilter;

    @FindBy(xpath = "//otalio-complex-filter-multi-label-content/div[text()='Status:']")
    private WebElement statusFilter;

    @FindBy(xpath = "//otalio-complex-filter-multi-label-content/div[text()='Position:']")
    private WebElement positionFilterText;

    @FindBy(css = "[placeholder='Position']")
    private WebElement positionInputBox;

    @FindBy(css = "[icon='excel-export']")
    private WebElement excelExport;

    @FindBy(css = "#breadcrumbs-title")
    private WebElement breadCrumbTitle;

    @FindBy(xpath = "//div[contains(text(),'Cabin Service')]")
    private WebElement cabinService;

    @FindBy(xpath = "//div[contains(text(),'Public Location Service')]")
    private WebElement publicLocation;

    @FindBy(css = "[placeholder='Search by Name']")
    private WebElement searchByName;

    @FindBy(xpath = "//td[@role='columnheader']/div[contains(@class,'dx-datagrid-text-content')]")
    private List<WebElement> crewColumnData;

    @FindBy(xpath = "//otalio-profile-card/descendant::h1")
    private WebElement guestName;

    @FindBy(xpath = "//otalio-passenger-picture/../following-sibling::div/div/div")
    private List<WebElement> countryAndLanguage;

    @FindBy(xpath = "//*[normalize-space()='Amenities']/following-sibling::otalio-chip-list/descendant::otalio-chip/div/div")
    private List<WebElement> amenitiesList;

    @FindBy(xpath = "//*[text()='Dining Info']/following-sibling::div/div/div  ")
    private List<WebElement> diningInfoList;

    @FindBy(xpath = "//tbody/tr/td/div[text()='Loyalty Tier']/ancestor::table/tbody/tr/td[@role='columnheader'][not(contains(@class,'select'))]/div[text()]/following::table/tbody/tr[1]/td[not(contains(@class,'select'))][6]")
    private WebElement loyaltyTire;

    @FindBy(xpath = "//tbody/tr/td/div/otalio-passenger-picture/div/div/img")
    private WebElement guestImage;

    @FindBy(xpath = "//otalio-link-list-item//span[contains(text(),'Report Overview')]")
    private WebElement reportOverView;

    @FindBy(xpath = "(//table/tbody/tr/td[@role='gridcell']/div/a)[2]")
    private WebElement guestNameTwo;

    @FindBy(xpath = "//div[@class='mat-list-text']")
    private List<WebElement> statusList;

    @FindBy(xpath = "//otalio-chip[contains(@aria-describedby,'cdk-describedby-message')]/div/div")
    private WebElement occupancy;

    public String getOccupancyStatus(){
        seleniumWaits.untilElementIsVisible(occupancy);
        return seleniumActions.getTextMessage(occupancy);
    }

    public List<String> getStatusList(){
        List<String> data = new ArrayList<>();
        for(WebElement element :statusList ){
            data.add(element.getText());
        }
        return data;
    }

    public void clickOnStatusFilter(){
        seleniumActions.sleep(3000);
        //seleniumWaits.untilElementInVisible(statusFilter);
        seleniumActions.moveToElementAndClick(statusFilter);
    }

    public void selectCleaningStatusFilter(String filter,String option){
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("//otalio-complex-filter-multi-label-content/div[contains(text(),'" + filter +"')]")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//otalio-complex-filter-multi-label-content/div[contains(text(),'" + filter +"')]")));
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(By.xpath("//mat-pseudo-checkbox/../div/span[contains(text(),'" + option + "')]"));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//otalio-complex-filter-multi-label-content/div[contains(text(),'" + filter +"')]")));

    }

    public String getGuestNameTwo() {
        return guestNameTwo.getText().trim();
    }

    public  void clickOnReportOverView(){
        seleniumActions.clickOnElement(reportOverView);
    }

    public void clickOnModule(String moduleName) {
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("//icon[@icon='folder']/following-sibling::div[contains(text(), '"+moduleName+"')]")));
        seleniumActions.sleep(5000);
    }

    public void clickOnExcel(){
        seleniumActions.clickOnElement(excelExport);
    }


    public List<String> getdiningInfoList(){
        List<String> data = new ArrayList<>();
        for(WebElement element : diningInfoList){
            data.add(element.getText());
        }
        return data;
    }

    public List<String> getamenitiesList(){
        List<String> data = new ArrayList<>();
        for(WebElement element : amenitiesList){
            data.add(element.getText());
        }
        return data;
    }


    public List<String> getCountryAndLanguage(){
        List<String> data = new ArrayList<>();
        for(WebElement element : countryAndLanguage){
            data.add(element.getText());
        }
        return data;
    }

    public String getGuestName(){
        return guestName.getText().trim();
    }


    public List<String> getUnassignedCrewNames() {
        return unassignedCrewList.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public String getSectionNameForCrewAssignment(){
        seleniumActions.sleep(3000);
        return sectionNameForCrewAssignment.getText().trim();
    }

    public List<String> getSectionListOfAll() {
        return sectionListOfAll.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public List<String> getCrewListOfAll() {
        return crewListOfAll.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void clickOnAssignedSection(){
        seleniumActions.clickOnElement(assignedSections);
        seleniumActions.sleep(3000);
    }

    public void clickOnUnAssignedSection(){
        seleniumActions.clickOnElement(unAssignedSections);
        seleniumActions.sleep(3000);
    }

    public void clickOnFirstCrewMember(){
        seleniumActions.clickOnElement(firstCrewMember);
        seleniumActions.sleep(3000);
    }

    public String getCrewMemberName(){
        return firstCrewMember.getText().trim();
    }

    public List<String> getCrewColumnHeaders(){
        List<String> list = seleniumActions.getListOfElementsText(crewColumnData);
        List<String> actualList = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            actualList.add(list.get(i));
        }
        return actualList;
    }

    public void crewSearchByName(String crewFirstName) {
        seleniumActions.typeValue(searchByName,crewFirstName);
        seleniumWaits.untilElementIsClickable(crewNameCheckBox);
        seleniumActions.clickOnElement(crewNameCheckBox);
    }

    public void crewSearchName(String crewFirstName) {
        seleniumActions.typeValue(searchByName,crewFirstName);
        seleniumWaits.untilElementIsClickable(crewNameCheckBox);
    }

    public void clickOnCabinService(){
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(cabinService);
    }

    public void clickOnPublicLocation(){
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(publicLocation);
    }

    public void checkOptionOnTheFilter(String option) {
        String checkBox = "//span[text()='%s']/../preceding-sibling::mat-pseudo-checkbox";
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(String.format(checkBox, option))));
        seleniumActions.pressEscapeKey();
        seleniumActions.sleep(500); //after pressing esc key
        seleniumActions.pressEscapeKey();
        seleniumActions.moveToElementAndClickOnElement(breadCrumbTitle, breadCrumbTitle);
        seleniumActions.sleep(3000);
    }

    public void setPositionFilter() {
        seleniumActions.moveToElement(positionFilter);
        seleniumActions.jsClick(positionFilter);
        seleniumActions.typeValue(positionInputBox, "CABIN STEWARD/ESS");
    }

    public String getUnAssignedCrewMember(String text){
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("//div[contains(text(),'"+text+"')]")));
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//div[contains(text(),'"+text+"')]")));
    }

    public String getFilter(String text){
        seleniumActions.sleep(3000);
        seleniumActions.moveToElement(positionFilterText);
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("//otalio-complex-filter-multi-label-content/div[text()='" + text + "']")));
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//otalio-complex-filter-multi-label-content/div[text()='" + text + "']")));
    }

    public void clickOnAssignedCrewMember(){
        seleniumActions.clickOnElement(assignedCrewMember);
        seleniumActions.sleep(3000);
    }

    public void clickOnUnAssignedCrewMember(){
        seleniumActions.clickOnElement(unAssignedCrewMember);
        seleniumActions.sleep(3000);
    }

    public void openSectionInformation(String sectionName) {
        seleniumActions.clearValue(sectionSearchBox);
        seleniumActions.typeValue(sectionSearchBox,sectionName);
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(firstSection);
    }

    public boolean verifyDataIsVisible(){
        seleniumActions.sleep(3000);
        return seleniumActions.isElementDisplayed(firstSection);
    }

    public String getCrewNameForSectionAssignment(){
        seleniumActions.sleep(3000);
        return crewNameForSectionAssignment.getText().trim();
    }

    public String getSectionText(){
        seleniumActions.sleep(3000);
        return crewNameForSectionAssignment.getText().trim();
    }

    public String getSectionOneText(){
        seleniumActions.sleep(3000);
        return sectionNameText.getText().trim();
    }

    public String getCrewNameForSectionTwoAssignment(){
        seleniumActions.sleep(3000);
        return crewNameForSectionTwoAssignment.getText().trim();
    }

    public String getSectionForCrewNameAssignment(){
        seleniumActions.sleep(3000);
        return sectionForCrewNameAssignment.getText().trim();
    }

    public void clickOnAssignButton(){
        seleniumActions.clickOnElement(assignButton);
    }

    public void checkBoxCrew(){
        seleniumActions.clickOnElement(crewNameCheckBox);
    }

    public  void checkBoxMultipleCrew(){
        seleniumActions.clickOnElement(crewNameCheckBox);
        seleniumActions.sleep(2000);
        seleniumActions.clickOnElement(crewNameCheckBox);
    }

    public void clickOnFirstSection(){
        seleniumActions.clickOnElement(firstSection);
        seleniumActions.sleep(3000);
    }

    public void clickOnSectionOne(){
        seleniumActions.clickOnElement(sectionOne);
        seleniumActions.sleep(3000);
    }

    public void clickOnSectionTwo(){
        seleniumActions.clickOnElement(sectionTwo);
        seleniumActions.sleep(3000);
    }

    public String getSectionName(){
        return firstSection.getText().trim();
    }

    public String getSectionNameText(){
        return sectionText.getText().trim();
    }


    public String getSectionTextTwo(){
        return sectionTextTwo.getText().trim();
    }

    public void clickOnBySection(){
        try {
            seleniumActions.clickOnElement(bySection);
        }catch (StaleElementReferenceException ste){
            webDriver.findElement(By.xpath("//button[contains(@id,'by-section')]")).click();
        }
    }

    public void clickOnBySelect(String select){
        seleniumActions.sleep(3000);
        try {
            seleniumActions.clickOnElement(By.xpath("//button[contains(@id,'"+select+"')]"));
        }catch (StaleElementReferenceException ste){
            webDriver.findElement(By.xpath("//button[contains(@id,'"+select+"')]")).click();
        }
    }

    public void clickOnByCrewMember(){
        try {
            seleniumActions.clickOnElement(byCremMember);
        } catch (StaleElementReferenceException staleElementReferenceException){
        seleniumActions.clickOnElement(byCremMember);
        }
    }

    public void clickOnSearch() {
        seleniumWaits.untilElementIsClickable(fastSectionAssignment);
        seleniumActions.sleep(2000);
        seleniumActions.jsClick(fastSectionAssignment);
    }

    public void clickOnTheSearch() {
        String css = "span[id='link-list-item-Fast Section Assignment']";
        By fastSectionAssignment = By.cssSelector("span[id='link-list-item-Fast Section Assignment']");
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.cssSelector(css)));
        seleniumActions.moveToElementAndClick(webDriver.findElement(By.xpath("//otalio-link-list//span[contains(normalize-space(text()),'Fast Section Assignment')]")));
        seleniumActions.sleep(3000);
        if(webDriver.findElements(fastSectionAssignment).size()>0){
            seleniumActions.jsClick(webDriver.findElement(fastSectionAssignment));
        }
    }


    public String getGuestFirstName() {
        seleniumWaits.untilElementIsClickable(guestFirstName);
        return seleniumActions.getTextMessage(guestFirstName);
    }

    public boolean getOperation(){
        return seleniumActions.isElementDisplayed(guestFirstName);
    }

    public boolean getLoyalty(){
        return seleniumActions.isElementDisplayed(loyaltyTire);
    }

    public boolean getImage(){
        return seleniumActions.isElementDisplayed(guestImage);
    }

    public List<String> getStatus(){
        List<String> data = new ArrayList<>();
        for(int i=0; i<status.size(); i++){
            data.add(status.get(i).getText());
        }
        return data;
    }


}
