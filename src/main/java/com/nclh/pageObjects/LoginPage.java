package com.nclh.pageObjects;

import com.nclh.constants.GlobalConstants;
import com.nclh.constants.LoginConstants;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.nclh.utilities.SeleniumActions;
import org.testng.Assert;

import java.util.List;

public class LoginPage {
    private SeleniumActions seleniumActions;
    private WebDriver webDriver;
    private SeleniumWaits seleniumWaits;
    private CommonFunctions commonFunctions;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        seleniumActions = new SeleniumActions(webDriver);
        PageFactory.initElements(webDriver, this);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonFunctions = new CommonFunctions();
    }

    @FindBy(css = "[alt='Otalio']")
    private WebElement loginHeaderTitle;

    @FindBy(css = "[placeholder='Email']")
    private WebElement userNameInputField;

    @FindBy(css = "[placeholder='Password']")
    private WebElement passwordInputField;

    @FindBy(css = "[aria-label='Login']")
    private WebElement signInButton;

    @FindBy(css = "mat-select[class^='mat-select']")
    private WebElement userProfileList;

    @FindBy(xpath = "//input[contains(@placeholder,'Search')]")
    private WebElement searchBox;

    @FindBy(xpath = "//icon[@icon='user-photo']")
    private WebElement userPhoto;

    @FindBy(xpath = "//span[normalize-space()='Sign out']")
    private WebElement signOutButton;

    @FindBy(xpath = "//otalio-login/descendant::h3")
    private WebElement loginLabelText;

    @FindBy(id = "toast-message-content")
    private WebElement toastMessage;

    @FindBy(xpath = "//*[local-name()='use' and starts-with(@href, '#close-')]")
    private WebElement closeToastMessageIcon;

    @FindBy(xpath = "(//div[@id='toast-message-content' and contains(text(),'Cannot read properties of null')])[2]/ancestor::div[contains(@class,'d-flex')]/following-sibling::div//button")
    private List<WebElement> closeToastButtons;

    public void closeToastMessage(){
        seleniumWaits.untilElementInVisible(toastMessage);
       // seleniumActions.clickOnElement(closeToastMessageIcon);
    }

    public void closeErrorToastIfDisplayed() {

        if (!closeToastButtons.isEmpty()) {

            WebElement closeButton = closeToastButtons.get(0);

            if (closeButton.isDisplayed()) {
                seleniumActions.highLighterMethod(closeButton);
                seleniumActions.clickOnElement(closeButton);
            }
        }
    }

    public String getLoginText(){
        //closeToastMessage();
        //commonFunctions.closeErrorPopups(webDriver);
        closeErrorToastIfDisplayed();
        return seleniumActions.getTextMessage(loginLabelText);
    }



    public void logout(){
        seleniumActions.sleep(3000);
        try {
            try {
                seleniumActions.moveToElementAndClickOnElement(userPhoto, userPhoto);
            }catch (Exception se){
                seleniumActions.clickOnElement(By.xpath("(//div[starts-with(@class,'user-avatar')]/img)[1]"));
            }
        }catch (NoSuchElementException ne){
            seleniumActions.jsClick(webDriver.findElement(By.cssSelector("[href='#user-photo']")));
        }
        seleniumActions.clickOnElement(signOutButton);
        seleniumActions.sleep(5000);
    }

    public void logoutFromGangway(){
        try {
            seleniumActions.moveToElementAndClickOnElement(userPhoto, userPhoto);
        }catch (NoSuchElementException ne){
            seleniumActions.jsClick(webDriver.findElement(By.cssSelector("[href='#user-photo']")));
        }
        seleniumActions.clickOnElement(signOutButton);
        seleniumActions.sleep(5000);
        seleniumActions.clickOnElement(By.xpath("//span[normalize-space()='Confirm']"));
        seleniumActions.sleep(5000);
    }

    public void selectUserProfile(String userProfile){
        seleniumActions.sleep(1000);
        seleniumActions.clickOnElement(userProfileList);
        seleniumActions.sleep(1000);
        seleniumActions.typeValue(searchBox, userProfile);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//span[@class='mat-option-text']/div/div[contains(normalize-space(.), '" + userProfile + "')]")));
        seleniumActions.clickOnElement(signInButton);
    }

    public boolean verifyInputBoxPresent(){
        return userNameInputField.isDisplayed();
    }

    public void login(String userName, String pwd){
        seleniumActions.sleep(5000);
        seleniumWaits.untilElementIsVisible(userNameInputField);
        seleniumActions.typeValue(userNameInputField, userName);
        seleniumActions.typeValue(passwordInputField, pwd);
    }

    public boolean emailLoginPageIsDisplayed() {
        loginHeaderTitle.isDisplayed();
        return true;
    }

    public String getLoginHeaderTitle() {
        loginHeaderTitle.isDisplayed();
        return loginHeaderTitle.getText();
    }

    public void validateLoginTextIsPresent() {
        Assert.assertEquals(getLoginText(), LoginConstants.LOGIN_LABEL_TEXT, "Expected login text is " + LoginConstants.LOGIN_LABEL_TEXT + " , but the login text found is: "+getLoginText());
    }

    public void enterUserCredsAndClickLoginBtn(String username, String password){
        this.login(username, password);
        seleniumActions.clickOnElement(signInButton);

    }
    public void safeLogout(){
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        try {
            try {
                seleniumActions.moveToElementAndClickOnElement(userPhoto, userPhoto);
            }catch (Exception se){
                seleniumActions.clickOnElement(By.xpath("(//div[starts-with(@class,'user-avatar')]/img)[1]"));
            }
        }catch (NoSuchElementException ne){
            seleniumActions.jsClick(webDriver.findElement(By.cssSelector("[href='#user-photo']")));
        }
        seleniumActions.clickOnElement(signOutButton);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement confirmBtn = webDriver.findElements(By.xpath("//span[contains(normalize-space(),'Confirm')]")).stream().findFirst().orElse(null);

        if (confirmBtn != null) {
            confirmBtn.click();
        }
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }


}
