package com.nclh.pageObjects;

import com.nclh.constants.GlobalConstants;
import com.nclh.constants.SafetyManagementConstatnts;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.nclh.utilities.SeleniumActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ActivitiesPlannerPage {
    private WebDriver webDriver;
    private SeleniumActions seleniumActions;
    private SeleniumWaits seleniumWaits;
    private CommonPage commonPage;
    private CommonFunctions commonFunctions;
    public ActivitiesPlannerPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        seleniumActions = new SeleniumActions(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonPage = new CommonPage(webDriver);
        commonFunctions = new CommonFunctions();
    }

    @FindBy(css = "#otalio-overview-activity-step3-add-button")
    private WebElement addButton;

    @FindBy(xpath = "//span[normalize-space()='add activity']" )
    private WebElement addActivityButton;

    @FindBy(xpath = "//button[@id='overview-calendar-edit-activity-button']" )
    private WebElement editActivityButton;

    @FindBy(xpath = "//span[normalize-space()='Delete Activity']" )
    private WebElement deleteActivityButton;

    @FindBy(xpath = "//button[@id='discard-confirmation-dialog-confirm-button']" )
    private WebElement editActivityConfirmButton;

    @FindBy(xpath = "//input[@placeholder='Activity Date']" )
    private WebElement activityDateField;

    @FindBy(xpath = "//otalio-time-picker-adapter[@placeholder='Activity Time']" )
    private WebElement activityTime;

    @FindBy(xpath = "//div[contains(@id,'overview-calendar-activity-list')]" )
    private List<WebElement> listOfActivities;

    @FindBy(css = "#overview-calendar-add-activity-button")
    private WebElement addActivityBtn;
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

    public void openActivity(){
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("//span[normalize-space()='add activity']")));
        seleniumActions.scrollInToElement(addActivityButton);
        seleniumWaits.untilElementIsVisible(addActivityButton);
        seleniumActions.sleep(1000);
        seleniumActions.clickElement(addActivityButton);
    }

    public void addActivity(String activityCategory, String activityType){
        String text = "//span[text()='Activity Category' or normalize-space()='Activity Category']";
        commonPage.clickOnElement(SafetyManagementConstatnts.ACTIVITY_CATEGORY_TEXT);
      /*  if(!webDriver.findElements(By.xpath(text)).isEmpty()){
            commonPage.clickOnElement("Activity Category");
        }
        try {
            commonPage.clickOnElement(activityCategory);
        }catch (StaleElementReferenceException ste){
            webDriver.findElement(By.xpath("//span[text()='"+activityCategory+"' or normalize-space()='"+activityCategory+"']"));
        }*/
        commonPage.clickOnElement(activityCategory);
        commonPage.clickOnElement(SafetyManagementConstatnts.ACTIVITY_TYPE_TEXT);
        String xpath = "//span[text()='" + activityType + "' or normalize-space()='" + activityType + "']";
        seleniumActions.sleep(2000);
        seleniumActions.typeValue(webDriver.findElement(By.cssSelector("[placeholder='Search']")),activityType);
        commonPage.clickOnElement(activityType);
        commonPage.clickOnElement(SafetyManagementConstatnts.NEXT_TEXT);
        commonPage.clickOnElement(SafetyManagementConstatnts.SKIP_TEXT);
    }

    public void finishAddingActivity(){
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//div[contains(@class,'checkbox-container')])[2]")));
        }catch (StaleElementReferenceException ste){
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//div[contains(@class,'checkbox-container')])[3]")));
        }
        seleniumWaits.untilElementIsClickable(addButton);
        seleniumActions.clickElement(addButton);
        commonPage.clickOnElement(SafetyManagementConstatnts.FINISH_TEXT);
    }

    public void openEditActivityScreen() {
        seleniumActions.scrollInToElement(editActivityButton);
        seleniumActions.sleep(3000);
        seleniumActions.moveToElementAndClick(editActivityButton);
        seleniumActions.clickElement(editActivityConfirmButton);
        seleniumActions.sleep(3000);
    }

    public void getCurrentActivityDate(){
        seleniumWaits.untilElementIsClickable(activityDateField);
        seleniumActions.clickElement(activityDateField);

    }
    public WebElement getActivityDateText(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.highLighterMethod(activityDateField);
        return activityDateField;
    }

    public WebElement getActivitytime(){
        return activityTime;
    }

    public void updateActivityDate(){
        seleniumActions.sleep(2000);
        seleniumActions.clearValue(activityDateField);
        seleniumActions.sleep(2000);
        seleniumActions.typeValue(activityDateField, commonFunctions.getDateIncrementedBy(SafetyManagementConstatnts.DAYS_TO_BE_INCREMENTED_BY, SafetyManagementConstatnts.DATE_FORMAT_MDY));
        seleniumActions.pressTabKey();
    }

    public void updatedActivityTime(){
        seleniumActions.sleep(2000);
        seleniumActions.clearValue(activityTime);
        seleniumActions.sleep(2000);
        seleniumActions.clickOnElement(activityTime);
        seleniumActions.pressEnterKey();
    }

    public void finishEditingActivity(){
        commonPage.clickOnElement(SafetyManagementConstatnts.SAVE_AND_FINISH_TEXT);
    }

    public void cancelEditingActivity(){
        commonPage.clickOnElement(SafetyManagementConstatnts.CANCEL_TEXT);
    }

    public void deleteActivity(){
        seleniumActions.scrollInToElement(deleteActivityButton);
        seleniumActions.sleep(3000);
        seleniumActions.moveToElementAndClick(deleteActivityButton);
        seleniumActions.clickElement(editActivityConfirmButton);
        seleniumActions.sleep(3000);
    }

    public String getActivitiesCount(){
        seleniumActions.sleep(2000);
        return String.valueOf(listOfActivities.size());
    }
    public void userClickTheActivityButton(){
        seleniumWaits.untilElementIsClickable(addActivityBtn);
        seleniumActions.scrollInToElement(addActivityBtn);
        commonPage.waitForDomReady();
        seleniumActions.highLighterMethod(addActivityBtn);
        seleniumActions.clickElement(addActivityBtn);
        try {
            wait.until(ExpectedConditions.visibilityOf(addActivityBtn));
            if (addActivityBtn.isDisplayed()) {
                seleniumActions.doubleClickOnElement(addActivityBtn);
            }
        } catch (Exception ignored) {
        }
    }
}

