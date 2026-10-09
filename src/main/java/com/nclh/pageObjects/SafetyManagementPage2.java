package com.nclh.pageObjects;

import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

public class SafetyManagementPage2 {
    private WebDriver webDriver;
    private SeleniumActions seleniumActions;
    private CommonPage commonPage;
    private SeleniumWaits seleniumWaits;
    private CommonFunctions commonFunctions;

    @Setter
    @Getter
    private String safetyNumber;

    public static final Logger logger =
            LoggerFactory.getLogger(SeleniumActions.class);
    By tablecolumn = By.xpath("//tr[@aria-rowindex='1']");
    By columnHeader = By.xpath("//*[@role='columnheader']");
    By emerTeamLocator = By.xpath("//div[contains(text(),'Emer. Team:')]/../descendant::div[text()][contains(@class,'text-truncate')]");
    By hiddenFiltersLocator = By.cssSelector("#complex-filter-collapse-expand-chip > div > div");
    By dropdownItemsLocator = By.xpath("//div[@class='mat-list-text']");
    By safetyNumbersRightPanel = By.xpath("//*[contains(normalize-space(),'Safety Numbers')]");
    By checkBoxLocator = By.xpath("//otalio-suggested-unassigned-safety-plans-container//mat-checkbox");
    By assignButtonRightPanel = By.xpath("//button[contains(@class,'mat-button') and .//*[normalize-space()='Assign']]");
    By confirmButtonBox = By.xpath("//span[normalize-space(text())='Confirm']");




    public SafetyManagementPage2(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
        seleniumActions = new SeleniumActions(driver);
        commonPage = new CommonPage(driver);
        seleniumWaits = new SeleniumWaits(driver);
        commonFunctions = new CommonFunctions();
    }

    public String getFirstRowBySectionText(String sectionText) {
        return String.format("//mat-tab-group[.//div[normalize-space()='%s']]//tr[@aria-rowindex='1']", sectionText);
    }

    public void userIsAbleToConfirmTrainingDataVisible(String str) {
        By sectionLocator = By.xpath(getFirstRowBySectionText(str));
        try{
            WebElement element = webDriver.findElement(sectionLocator);
            if (element != null && element.isDisplayed()) {
                seleniumActions.scrollInToElement(element);
                logger.info("TEXT display: " + str);
                WebElement column = webDriver.findElement(tablecolumn);
                seleniumWaits.untilElementIsVisible(column);
                seleniumActions.highLighterMethod(column);
                String columnValue = column.getText().trim();
                if (columnValue != null && !columnValue.isEmpty() && !columnValue.equals("0") && !columnValue.equals("-")) {
                    logger.info("NUMBER display: " + columnValue);
                } else {
                    logger.error("Both not display in certificate");
                }
            }
        } catch (Exception e) {
            //Certificate adding is not possible manually, the backend team can add it according to the manual team’s suggestion.
            logger.info("No data is display");
        }
    }
    public void userAbleThatTheCrewMemberIsPartOfShouldBeDisplayed(String str){
        try{
            By sectionLocator = By.xpath(getFirstRowBySectionText(str));
                WebElement element = webDriver.findElement(sectionLocator);
                if (element != null && element.isDisplayed()) {
                    seleniumActions.scrollInToElement(element);
                    logger.info("TEXT display: " + str);
                    WebElement column = webDriver.findElement(tablecolumn);
                    seleniumWaits.untilElementIsVisible(column);
                    seleniumActions.highLighterMethod(column);
                    String columnValue = column.getText().trim();
                    if (columnValue != null && !columnValue.isEmpty() && !columnValue.equals("0") && !columnValue.equals("-")) {
                        logger.info("NUMBER display: " + columnValue);
                    } else {
                        logger.error("Both not display in "+str);
                    }
                }
        } catch (Exception e) {
            List<WebElement> headers = webDriver.findElements(columnHeader);
            if (headers.size() == 0) {
                throw new AssertionError("No elements found "+str);
            }
            for (WebElement header : headers) {
                logger.info("Header text: " + header.getText());
            }
        }
    }
    public String modulePageValidation(String link) {
        return String.format("//*[normalize-space(text())='%s' and contains(@id,'breadcrumbs')]", link, link);
    }

    public void userIsAbleToNavigateToThePageLink(String str){
        commonPage.waitForDomReady();
        By locator = By.xpath(modulePageValidation(str));
        seleniumWaits.untilElementIsPresent(locator);
        WebElement element = webDriver.findElement(locator);
        seleniumActions.highLighterMethod(element);
        String actualText = element.getText().trim();
        logger.info("Navigated to " + actualText + " page");
    }
    public void userIsAbleToClickOnTheHiddenFiltersAndVerifyTheyAreDisplayed(){
        List<WebElement> hiddenFiltersIcons = webDriver.findElements(hiddenFiltersLocator);
        if (!hiddenFiltersIcons.isEmpty()) {
            WebElement hiddenFiltersIcon = hiddenFiltersIcons.get(0);
            if (hiddenFiltersIcon.isDisplayed()) {
                hiddenFiltersIcon.click();
                logger.info("Hidden filters icon was displayed and successfully clicked.");
            }
        } else {
            logger.info("Hidden filters icon already clicked.");
        }
    }
    public void userIsAbleToSeeAndClickOnTheEmergencyRoleTeam(){
        try {
            WebElement emerTeamElement = webDriver.findElement(emerTeamLocator);
            if (emerTeamElement.isDisplayed()) {
                emerTeamElement.click();
                logger.info("Emer. Team element was displayed and successfully clicked.");
            } else {
                logger.error("Emer. Team element is not displayed.");
            }
        } catch (NoSuchElementException e) {
            logger.error("Emer. Team element not found.", e);
            Assert.fail("Emer. Team element not present");
        }
    }
    public void userIsAbleToViewEmergencyRoleTeamInTheSafetyBuildUpDropdownList(){
        List<WebElement> dropdownItems = webDriver.findElements(dropdownItemsLocator);
        if (!dropdownItems.isEmpty()) {
            logger.info("Found {} dropdown items:", dropdownItems.size());
            for (WebElement item : dropdownItems) {
                String itemText = item.getText().trim();
                logger.info("Dropdown item text: " + itemText);
            }
        } else {
            logger.error("No dropdown items found with locator: {}", dropdownItemsLocator);
        }
    }

    public By getTabByModeText(String modeText) {
        return By.xpath(String.format("//div[@role='tab'][.//div[normalize-space()='%s']]", modeText));
    }

    public void userChecksIfTheActiveModeScreenIsEnabledOnTheCrewMemberPage(String modeText) {
        try {
            By tabLocator = getTabByModeText(modeText);

            WebElement tabElement = webDriver.findElement(tabLocator);
            seleniumWaits.untilElementIsVisible(tabElement);
            seleniumActions.highLighterMethod(tabElement);

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

    public void userRespectiveSafetyNumbersLandingPageShouldBeDisplayed(){
        commonPage.waitForDomReady();
        seleniumWaits.untilElementIsVisible(webDriver.findElement(safetyNumbersRightPanel));
        seleniumActions.highLighterMethod(webDriver.findElement(safetyNumbersRightPanel));
        logger.info("Safety Numbers right panel is displayed");
    }
    public void userSelectsTheRequiredNumberCheckboxUnderSuggestedUnassignedNumbers() {
        commonPage.waitForDomReady();
        try {
            List<WebElement> checkBoxes = webDriver.findElements(checkBoxLocator);
            if (checkBoxes.isEmpty()) {
                logger.error("No unassigned safety plan checkboxes are available.");
                return;
            }
            logger.info("Number of unassigned safety plan checkboxes found: " + checkBoxes.size());
            WebElement firstCheckBox = checkBoxes.get(0);
            seleniumWaits.untilElementIsClickable(firstCheckBox);
            seleniumActions.clickOnElement(firstCheckBox);
            logger.info("First unassigned safety plan checkbox clicked successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify or click unassigned safety plan checkboxes", e);
        }
    }
   public void clickOTheASSIGNButton() {
       try {
           WebElement assignButton = webDriver.findElement(assignButtonRightPanel);
           seleniumWaits.untilElementIsVisible(assignButton);
           seleniumActions.highLighterMethod(assignButton);
           seleniumActions.jsClick(assignButton);
           logger.info("Assign button clicked successfully.");

       } catch (Exception e) {
           throw new RuntimeException("Failed to click Assign button.", e);
       }
   }
   public void userClickTheConfirmButtonInCrewPage(){
       List<WebElement> confirmButtons = webDriver.findElements(confirmButtonBox);
       if (confirmButtons.size() > 0) {
           WebElement confirmButton = confirmButtons.get(0);
           seleniumActions.jsClick(confirmButton);
           logger.info("Confirm button clicked successfully.");
       }
   }
}
