package com.nclh.pageObjects;

import com.nclh.base.BasePage;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewReservationPage extends BasePage {
    private WebDriver webDriver;
    SeleniumActions seleniumActions;
    CommonFunctions commonFunctions;
    SeleniumWaits seleniumWaits;

    @FindBy(xpath = "//input[@id='personal-info-first-name-input']")
    private WebElement firstNameText;

    @FindBy(xpath = "//input[@id='personal-info-last-name-input']")
    private WebElement lastNameText;

    public CreateNewReservationPage(WebDriver webDriver) {
        super(webDriver);
        commonFunctions = new CommonFunctions();
    }

    public String getFirstName(){
        return getElementText(firstNameText);
    }

    public By cabinCell(String cabinNumber) {
        return By.xpath("//td[@aria-describedby='dx-col-1' and normalize-space()='" + cabinNumber + "']");
    }

    public void clickCabinCell(String cabinNumber) {
        WebElement element = waitForVisibility(cabinCell(cabinNumber));
        element.click();
    }

}
