package com.nclh.pageObjects;

import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsManagementPage {
    private WebDriver webDriver;
    private SeleniumActions seleniumActions;
    private SeleniumWaits seleniumWaits;
    private CommonPage commonPage;
    private CommonFunctions commonFunctions;




    public SettingsManagementPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        seleniumActions = new SeleniumActions(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonPage = new CommonPage(webDriver);
        commonFunctions = new CommonFunctions();

    }

    @FindBy(xpath = "//h2[contains(normalize-space(), 'access')]")
    private WebElement accessText;

    public String getAccessText(){
        return seleniumActions.getTextMessage(accessText);
    }
}
