package com.nclh.pageObjects;

import com.nclh.constants.GlobalConstants;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import com.nclh.utilities.SeleniumActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage {
    private final WebDriver webDriver;
    private SeleniumActions seleniumActions;
    private SeleniumWaits seleniumWaits;
    private CommonPage commonPage;
    public static final Logger logger
            = LoggerFactory.getLogger(SeleniumActions.class);

    public HomePage(WebDriver webDriver) {
        seleniumActions = new SeleniumActions(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonPage = new CommonPage(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 15), this);
    }

    @FindBy(xpath = "(//button[contains(@class,'toolbar-button')]/span/icon)[1]")
    private WebElement servicesMenu;

    @FindBy(xpath = "//*[@id='layout-menu-link-excursions-a']")
    WebElement excursionsLink;

    public WebElement getHomeLink(String text) {
//        String xpath = "(//a/div[normalize-space()='" + text + "'])[1]";
        String xpath = "//a/div[contains(text(),'" + text + "')]";

        return webDriver.findElement(By.xpath(xpath));
    }


    public void navigateToService(String service){
        //seleniumActions.clickOnElement(servicesMenu);

//        seleniumActions.sleep(20000); // To avoid page load failure
//        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.xpath("//a/div[normalize-space()='"+service+"']")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//a/div[normalize-space()='"+service+"']")));
//        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//a/div[contains(normalize-space(.),'"+service+"')]")));
        SeleniumWaits.waitForVisibility(webDriver, commonPage.getProfileIconElement());

    }

    public void clickOnModule(String moduleName){
         seleniumActions.clickOnElement(getHomeLink(moduleName));
        SeleniumWaits.waitForVisibility(webDriver, commonPage.getProfileIconElement());

    }
    // Returns the XPath string for a service menu item
    public String serviceNavigationXpath(String service) {
        return "//a/div[contains(normalize-space(.), '" + service + "')]";
    }

    public void userNavigateToMenuServices(String service) {
        By serviceLocator = By.xpath(serviceNavigationXpath(service));
        seleniumWaits.untilElementIsVisible(webDriver.findElement(serviceLocator));
        seleniumActions.highLighterMethod(webDriver.findElement(serviceLocator));
        seleniumActions.doubleClickOnElement(webDriver.findElement(serviceLocator));
        logger.info(service + " is displayed" );
        try{
            seleniumActions.doubleClickOnElement(webDriver.findElement(serviceLocator));
        } catch (Exception e) {

        }
    }
    public void userWaitForDisplay(String serviceName){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT_EXIST);
        seleniumActions.highLighterMethod(webDriver.findElement(By.xpath("//div[normalize-space()='" + serviceName + "']/a")));
    }

    public By getServiceLocator(String service) {
        String xpath = "//a/div[normalize-space()='" + service + "']";
        return By.xpath(xpath);
    }
    public void navigateToSelectedService(String service) {
        WebElement serviceElement = webDriver.findElement(getServiceLocator(service));
        seleniumActions.scrollInToElement(serviceElement);
        seleniumActions.clickOnElement(serviceElement);
        SeleniumWaits.waitForVisibility(webDriver, commonPage.getProfileIconElement());
    }

}
