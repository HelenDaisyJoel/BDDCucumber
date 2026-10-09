package com.nclh.pageObjects;

import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.security.cert.X509Certificate;
import java.util.List;

public class CrewInformation2Page {
    private WebDriver webDriver;
    private SeleniumActions seleniumActions;
    private SeleniumWaits seleniumWaits;
    private CommonFunctions commonFunctions;

    public CrewInformation2Page(WebDriver webDriver){
        this.webDriver = webDriver;
        seleniumActions = new SeleniumActions(webDriver);
        PageFactory.initElements(webDriver, this);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonFunctions = new CommonFunctions();
    }

    @FindBy(xpath = "//input/following-sibling::icon[@icon='eye-opened']")
    private WebElement eyeOpenedButton;

    @FindBy(xpath = "//otalio-quick-action-groups-panel/div/div")
    private List<WebElement> quickActionGroupListName;

    @FindAll({
            @FindBy(css = "[placeholder^='Search']"),
            @FindBy(css = "input.complex-search-input")
    })
    private WebElement crewSearchBox;

    @FindBy(xpath = "//div[text()='Crew']")
    private WebElement crewTabButton;

    @FindBy(css = "button[id*='reservationStatus'] > icon")
    private WebElement reservationStatusList;

    public void selectCrewFromSelectAccounts(String crewFirstName){
        seleniumWaits.untilElementIsVisible(crewTabButton);
        seleniumActions.clickOnElement(crewTabButton);
        seleniumWaits.untilElementIsVisible(reservationStatusList);
        try {
            seleniumActions.clickOnElement(reservationStatusList);

        } catch (StaleElementReferenceException exc) {
            seleniumActions.clickOnElement(reservationStatusList);
        }
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[text()='Expect Future']/../preceding-sibling::mat-pseudo-checkbox")));
        } catch (StaleElementReferenceException exc) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[text()='Expect Future']/../preceding-sibling::mat-pseudo-checkbox")));
        }
        seleniumActions.typeValue(crewSearchBox, crewFirstName);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//div[contains(text(),'"+crewFirstName+"')]/ancestor::otalio-guest-search-card/descendant::button/span/icon")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[contains(text(),'Select')]")));
//        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//button[contains(@id,'guest-search-add')]/span/icon")));
    }

    public void searchAndSelectCrew(String crewFirstName) {
        seleniumActions.typeValue(crewSearchBox, crewFirstName);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[contains(text(),'"+crewFirstName+"')]/ancestor::otalio-crew-members-list/descendant::div[@aria-label='Select row']/div/span[@class='dx-checkbox-icon']")));
    }

    public List<String> getQuickActionGroupListName(){
        System.out.println(quickActionGroupListName);
        return seleniumActions.getListOfElementsText(quickActionGroupListName);
    }

    public void clickEyeOpenedButton(){
        seleniumActions.clickOnElement(eyeOpenedButton);
    }

}
