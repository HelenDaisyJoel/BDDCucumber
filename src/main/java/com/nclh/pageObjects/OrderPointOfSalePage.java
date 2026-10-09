package com.nclh.pageObjects;

import com.nclh.constants.GuestProfileConstants;
import com.nclh.constants.PointOfSaleConstants;
import com.nclh.constants.YearConstants;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderPointOfSalePage {
    private WebDriver webDriver;
    private SeleniumActions seleniumActions;
    private SeleniumWaits seleniumWaits;
    private CommonFunctions commonFunctions;

    public OrderPointOfSalePage(WebDriver webDriver){
        this.webDriver = webDriver;
        seleniumActions = new SeleniumActions(webDriver);
        PageFactory.initElements(webDriver, this);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonFunctions = new CommonFunctions();
    }

    @FindBy(xpath = "//input[@placeholder='Date of Birth']")
    private WebElement dateOfBirthInputFiled;

    @FindBy(css = "[placeholder='Golden ID']")
    private WebElement goldenIdInputField;

    @FindBy(css = "[placeholder='Last Name']")
    private WebElement lastNameInputField;

    @FindBy(css = "[placeholder='First Name']")
    private WebElement firstNameInputField;

    @FindBy(xpath = "(//otalio-guest-profile-card/descendant::h2[contains(text(),'')])[1]")
    private WebElement getFullNameText;

    @FindBy(xpath = "//otalio-guest-item/descendant::div[contains(@class,'text-body')]")
    private WebElement getCabinText;

    @FindBy(xpath = "//otalio-guest-search-person-attributes/descendant::otalio-chip-list/following-sibling::div[contains(@class,'star-inserted')]")
    private WebElement getAccountTypeText;

    @FindBy(xpath = "(//otalio-guest-profile-card/descendant::otalio-chip/div//span)[2]")
    private WebElement getAgeGroup;

    @FindBy(xpath = "//otalio-guest-profile-card/descendant::icon/*[name()='svg']/*[name()='use'][contains(@href,'birthday')]")
    private WebElement getBirthDayIcon;

    @FindBy(xpath = "(//otalio-guest-profile-card/descendant::h2[contains(text(),'')])[2]")
    private WebElement getCabinFormGuestProfile;

    @FindBy(xpath = "//otalio-guest-profile-card/descendant::otalio-chip/div//span")
    private List<WebElement> listOfAccountType;

    @FindBy(xpath = "//otalio-chip-list/descendant::icon/following-sibling::div")
    private WebElement getPostingStatus;

    @FindBy(xpath = "//div[text()='Special Needs']/following-sibling::otalio-chip-list/descendant::otalio-chip/div/div")
    private WebElement getSpecialNeedsDetails;

    @FindBy(css = "[id*='mat-input']")
    private WebElement commentInputField;

    @FindBy(xpath = "//span[normalize-space()='Confirm']")
    private WebElement confirmButton;

    @FindBy(xpath = "//otalio-chip/div/icon[@icon='close-12']")
    private WebElement postingStatusClosed;

//    @FindBy(xpath = "(//mat-button-toggle/button/span[@class='mat-button-toggle-label-content'])[1]")
    @FindBy(xpath = "(//mat-button-toggle/button/span[@class='mat-button-toggle-label-content'])[3]")
    private WebElement tableNumber;

    @FindBy(xpath = "(//mat-button-toggle/button/span[@class='mat-button-toggle-label-content']/div)[3]/div[contains(@class,'mat-h3')]")
    private WebElement tableNumberText;

    @FindBy(xpath = "//otalio-passenger-picture[@size='medium']/descendant::div[contains(@class,'rounded-circle')]/img")
    private WebElement getProfilePicture;

    @FindBy(xpath = "(//otalio-guest-profile-card/descendant::h2)[1]")
    private WebElement getGuestName;

    @FindBy(xpath = "//div[@role='tab']/div")
    private List<WebElement> listOfPackages;

    public List<String> getListOfPackages(){
        return seleniumActions.getListOfElementsText(listOfPackages);
    }

    public String getGuestFullName(){
        seleniumActions.sleep(4000);
        seleniumActions.highLighterMethod(getGuestName);
        return seleniumActions.getTextMessage(getGuestName);
    }

    public boolean profilePicture(){
        seleniumActions.highLighterMethod(getProfilePicture);
        return seleniumActions.isElementDisplayed(getProfilePicture);
    }

    public String getTableNumber(){
        return seleniumActions.getTextMessage(tableNumberText);
    }

    public void selectTable(){
        seleniumActions.sleep(4000);
        seleniumActions.highLighterMethod(tableNumber);
        seleniumActions.clickOnElement(tableNumber);
    }

    public boolean getPostingStatusClosed(){
        seleniumWaits.untilElementIsVisible(postingStatusClosed);
        return seleniumActions.isElementDisplayed(postingStatusClosed);
    }

    public void disableToggle(String toggleName) {
        String toggleType = "//span[contains(text(),'%s')]/preceding-sibling::span/input";
        seleniumActions.sleep(4000);
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))));
        System.out.println(webDriver.findElement(By.xpath(String.format(toggleType, toggleName))).getAttribute("aria-checked"));
        WebElement toggle = webDriver.findElement(By.xpath(String.format(toggleType, toggleName)));
        if (toggle.getAttribute("aria-checked").equals("true")) {
            seleniumActions.clickOnElement(toggle);
        }
        if (webDriver.findElements(By.xpath("//mat-dialog-container[@id='mat-dialog-0']")).size() > 0) {
            seleniumActions.typeValue(commentInputField, PointOfSaleConstants.COMMENT);
            seleniumActions.clickOnElement(confirmButton);
        }

        }

    public boolean specialNeedsDetails(){
        seleniumActions.highLighterMethod(getSpecialNeedsDetails);
        return seleniumActions.isElementDisplayed(getSpecialNeedsDetails);
    }

    public boolean postingStatus(){
        seleniumActions.highLighterMethod(getPostingStatus);
        return seleniumActions.isElementDisplayed(getPostingStatus);
    }

    public String getAgeGroup(){
        return seleniumActions.getTextMessage(getAgeGroup);
    }

    public String cabinNumberFormGuestProfile(){
        return seleniumActions.getTextMessage(getCabinFormGuestProfile);
    }

    public boolean birthdayIcon(){
        seleniumActions.highLighterMethod(getBirthDayIcon);
        return seleniumActions.isElementDisplayed(getBirthDayIcon);
    }

    public String getAccountType(){
        return seleniumActions.getTextMessage(getAccountTypeText);
    }

    public String getCabinNumber(){
        return seleniumActions.getTextMessage(getCabinText);
    }

    public void doubleClickOnGuestProfile(String guestName){
        String guestProfileName = "//otalio-reservation-person-name/span[contains(text(),'"+guestName+"')]";
        seleniumActions.doubleClickOnElement(webDriver.findElement(By.xpath(String.format(guestProfileName))));
    }

    public WebElement getLastNameText(){
        return lastNameInputField;
    }

    public WebElement getFirstNameText(){
        return firstNameInputField;
    }

    public void updateFirstNameAndLastName(){
        seleniumActions.clearValue(firstNameInputField);
        seleniumActions.typeValue(firstNameInputField, GuestProfileConstants.GUEST_First_NAME+String.valueOf(commonFunctions.getRandomNumber(1,100)));
        seleniumActions.clearValue(lastNameInputField);
        seleniumActions.typeValue(lastNameInputField, GuestProfileConstants.LAST_NAME+String.valueOf(commonFunctions.getRandomNumber(1,100)));
    }

    public void enterDateOfBirth(int year){
        seleniumActions.clearValue(dateOfBirthInputFiled);
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();
        int currentMonth = currentDate.getMonthValue();
        LocalDate newDate = LocalDate.of(year, currentMonth, currentDay);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YearConstants.DATE_FORMATTER);
        String formattedDate = newDate.format(formatter);
        System.out.println("Formatted Date with Changed Year: " + formattedDate);
        seleniumActions.typeValue(dateOfBirthInputFiled,formattedDate);
        seleniumActions.clickOnElement(goldenIdInputField);
    }


}
