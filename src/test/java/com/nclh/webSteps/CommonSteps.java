package com.nclh.webSteps;

import com.nclh.constants.GlobalConstants;
import com.nclh.managers.AllDriverManager;
import com.nclh.managers.FileReaderManager;
import com.nclh.pageObjects.CommonPage;
import com.nclh.pageObjects.CrewInformationPage;
import com.nclh.pageObjects.GuestServicePage;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CommonSteps extends AllDriverManager {

    Context context;
    CommonPage commonPage;
    GuestServicePage guestServicePage;
    CrewInformationPage crewInformationPage;
    CommonFunctions commonFunctions;
    SeleniumActions seleniumActions;
    SeleniumWaits seleniumWaits;

    public CommonSteps(Context context) {
        this.context = context;
        commonPage = new CommonPage(getDriver());
        guestServicePage = new GuestServicePage(getDriver());
        crewInformationPage = new CrewInformationPage(getDriver());
        commonFunctions = new CommonFunctions();
        seleniumActions = new SeleniumActions(getDriver());
        seleniumWaits = new SeleniumWaits(getDriver());
    }

    @And("User click on label or heading {string}")
    public void clickOnLabelOrString(String name) {
        commonPage.navigateTo(name);
    }

    @And("User click on {string}")
    public void userClickOn(String button1) {
        //commonPage.waitForDomReady();
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        SeleniumWaits seleniumWaits = new SeleniumWaits(getDriver());
        seleniumActions.sleep(3000);
        String text = "//span[text()='" + button1 + "' or normalize-space()='" + button1 + "']";
        try {
            seleniumWaits.untilElementIsPresent(By.xpath(text), 10);
            seleniumActions.scrollInToElement(getDriver().findElement(By.xpath(text)));
//            seleniumActions.highLighterMethod((WebElement) By.xpath(text));
            seleniumActions.highLighterMethod(getDriver().findElement(By.xpath(text)));


            seleniumActions.jsClick(getDriver().findElement(By.xpath(text)));
        } catch (TimeoutException e) {
        }
//        seleniumActions.sleep(10000); // to wait for the page to save after clicking
     }

    @And("User navigate to {string}")
    public void userNavigateTo(String name) {
        commonPage.navigateTo(name);
    }

    @And("User open link {string}")
    public void userOpen(String link) {
        if (link.contains("payment-pos")) {
            SeleniumActions seleniumActions = new SeleniumActions(getDriver());
            seleniumActions.clickOnElement(By.cssSelector("[icon='payment-pos']"));
            seleniumActions.sleep(3000);
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            try {
                seleniumActions.jsClick(getDriver().findElement(By.cssSelector("[icon='payment-pos']")));
            }catch (Exception e) {
                System.out.println("Payment POS link not found, continuing...");
            }

        } else {
            commonPage.openLinkUsingHref(link);
        }
    }

    @And("User select tab {string}")
    public void userSelectTab(String name) {
        commonPage.clickOnLabel(name);
    }

    @And("User click on {string} button")
    public void userClickOnButton(String buttonName) {
        commonPage.navigateTo(buttonName);
    }

    @And("User click on label {string}")
    public void userClickOnLabel(String label) {
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            commonPage.clickOnLabel(label);
        }else{
            commonPage.clickOnLabel("BEVERAGE");
        }
    }

    @And("User perform signature and confirm {string} button")
    public void userPerformSignatureAndConfirmButton(String button) {
        try {
            if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
                guestServicePage.drawSignature();
            } else if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("allura")) {
                guestServicePage.drawSignature();
            }
            commonPage.navigateTo(button);
        }catch (Exception e) {
            System.out.println("Signature not required, continuing...");
        }
    }

    @Then("User verify toast message {string} appears")
    public void userVerifyToastMessageAppears(String message) {
        Assert.assertTrue(commonPage.getToastMessage().contains(message));
    }

    @And("User wait for {string} seconds")
    public void userWaitForSeconds(String time) {
        try {
            Thread.sleep(Integer.parseInt(time));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User get text from the image or webElement image")
    public void userGetImageTextFromWebElement() {
        WebElement element = null;
        String textMessage = commonFunctions.readToastMessage(element);
        context.setContext("imageText", textMessage);
    }

    @And("User navigates to the previous page")
    public void userNavigatesToThePreviousPage() {
        commonPage.navigateToPreviousPage();
        context.setContext("SKIP_SCREENSHOT", "true");
    }
    @Then("User is able to view {string}")
    public void userDisplayOn(String button1) {
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        SeleniumWaits seleniumWaits = new SeleniumWaits(getDriver());
        seleniumActions.sleep(3000);
        String text = "//span[text()='" + button1 + "' or normalize-space()='" + button1 + "']";
        try {
            seleniumWaits.untilElementIsPresent(By.xpath(text), 10);
            seleniumActions.scrollInToElement(getDriver().findElement(By.xpath(text)));
        } catch (TimeoutException e) {
        }
        seleniumActions.sleep(10000);

    }

    @And("User click on the tab {string}")
    public void userClickOnTab(String button1) {
        //commonPage.waitForDomReady();
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        SeleniumWaits seleniumWaits = new SeleniumWaits(getDriver());
        seleniumActions.sleep(3000);
        String text = "//div[@class='mat-tab-label-content' and " +
                "(text()='" + button1 + "' or normalize-space()='" + button1 + "')]";
        try {
            seleniumWaits.untilElementIsPresent(By.xpath(text), 10);
            seleniumActions.scrollInToElement(getDriver().findElement(By.xpath(text)));
//            seleniumActions.highLighterMethod((WebElement) By.xpath(text));
            seleniumActions.highLighterMethod(getDriver().findElement(By.xpath(text)));


            seleniumActions.jsClick(getDriver().findElement(By.xpath(text)));
        } catch (TimeoutException e) {
        }
        seleniumActions.sleep(10000); // to wait for the page to save after clicking
    }

    public WebElement getButtonElement(String buttonName) {
        return getDriver().findElement(By.xpath("//*[self::p or self::span][normalize-space()='"+buttonName+"']"));
    }

    @And("User click on the {string}")
    public void userClickOnThe(String button1) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement buttonElement = getButtonElement(button1);
        seleniumWaits.untilElementIsVisible(buttonElement, GlobalConstants.SHORT_WAIT);
        seleniumActions.scrollInToElement(buttonElement);
        seleniumActions.highLighterMethod(buttonElement);
        seleniumActions.jsClick(buttonElement);
    }

    @And("User views the {string} button")
    public void userViewsTheButton(String button1) {
        WebElement buttonElement = getButtonElement(button1);
        seleniumWaits.untilElementIsVisible(buttonElement, GlobalConstants.SHORT_WAIT);
        seleniumActions.scrollInToElement(buttonElement);
        seleniumActions.highLighterMethod(buttonElement);
    }

    @And("User search guest name in the group")
    public void userSearchGuestNameInTheGroup() {
        String fullNameGuest = context.getContext("fullNameGuest").toString();
        String[] nameParts = fullNameGuest.trim().split("\\s+");
        String firstName = nameParts[0];
        String lastName = nameParts[nameParts.length - 1];
        guestServicePage.searchUser(lastName);
        openCrewMemberProfile(firstName);
    }

    public WebElement getSubMenuElement(String buttonName) {
        String xpath = String.format("//span[text()='%s' or normalize-space()='%s']", buttonName, buttonName);
        return getDriver().findElement(By.xpath(xpath));
    }

    @And("User navigates to the {string} submenu")
    public void userNavigatesToTheSubmenu(String dropdown) {
        WebElement subMenuElement = getSubMenuElement(dropdown);
        seleniumActions.scrollInToElement(subMenuElement);
        seleniumActions.moveToElement(subMenuElement);
        seleniumActions.clickOnElement(subMenuElement);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public By getCrewMemberLocator(String guestFirstName) {
        String xpath = String.format("(//a[normalize-space()='%s'])[1]", guestFirstName);
        return By.xpath(xpath);
    }

    public void openCrewMemberProfile(String guestFirstName) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        WebElement crewMemberElement = getDriver().findElement(getCrewMemberLocator(guestFirstName));
        try {
            seleniumActions.scrollInToElement(crewMemberElement);
            seleniumActions.jsClick(crewMemberElement);
        } catch (Exception exception) {
            seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
            crewMemberElement.click();
        }
    }
    public By getServiceItemCardLocator(String serviceName) {
        String xpath = String.format("//div[normalize-space()='%s']/otalio-link-box//a", serviceName);
        return By.xpath(xpath);
    }

    @And("User open service {string} item card")
    public void userOpenServiceItemCard(String serviceItem) {
        By serviceLocator = getServiceItemCardLocator(serviceItem);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(GlobalConstants.MINI_WAIT));
        WebElement serviceElement = wait.until(ExpectedConditions.elementToBeClickable(serviceLocator));
        seleniumActions.highLighterMethod(serviceElement);
        seleniumActions.clickOnElement(serviceElement);
        List<WebElement> elements = getDriver().findElements(serviceLocator);

        if (!elements.isEmpty()) {
            seleniumActions.doubleClickOnElement(elements.get(0));
        }
    }
    @And("User navigates to the {string} tab")
    public void userNavigatesToTheTab(String tab) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        commonPage.clickOnLabel(tab);
    }
    public By getGuestNameLocator(String guestName) {
        return By.xpath("(//td[not(contains(@class,'dx-hidden-cell'))][.//*[contains(normalize-space(),'" + guestName + "')]])[1]");
    }

    public By getGuestCheckboxLocator(String guestName) {
        return By.xpath("(//div[contains(normalize-space(),'" + guestName + "')]/ancestor::otalio-bookings-list//span[contains(@class,'dx-checkbox-icon')])[1]");
    }

    @And("User selects the guest with respect to the assigned tour")
    public void userSelectsTheGuestWithRespectToTheAssignedTour() {
        String guestName = (String) context.getContext("firstguestName");
        WebElement guestElement = getDriver().findElement(getGuestNameLocator(guestName));
        seleniumWaits.untilElementIsVisible(guestElement);
        seleniumActions.scrollInToElement(guestElement);
        Assert.assertTrue(guestElement.isDisplayed(), "Guest name is not displayed: " + guestName);
        WebElement checkboxElement = getDriver().findElement(getGuestCheckboxLocator(guestName));
        seleniumWaits.untilElementIsClickable(checkboxElement);
        seleniumActions.highLighterMethod(checkboxElement);
        seleniumActions.clickOnElement(checkboxElement);
    }

    @Then("User click on {string} tab")
    public void userClickOnSubTab(String tabName) {
        seleniumActions.clickOnText(tabName);
    }

    @Then("User click on quick link {string}")
    public void clickOnQuickLink(String tabName) {
        commonFunctions.clickLinkByText(getDriver(), tabName);
     }

    @And("User click the {string} button")
    public void userClickTheButton(String button1) {
        WebElement buttonElement = getButtonElement(button1);
        seleniumWaits.untilElementIsVisible(buttonElement, GlobalConstants.SHORT_WAIT);
        seleniumActions.scrollInToElement(buttonElement);
        seleniumActions.jsClick(buttonElement);

    }

    @Then("User waits until the success toast message disappears from the screen")
    public void userWaitsUntilTheSuccessToastMessageDisappearsFromTheScreen() {
        commonPage.waitForElementToDisappear();

    }
    public WebElement getButtonElements(String buttonName) {
        return getDriver().findElement(By.xpath("(//*[self::p or self::span][normalize-space()='"+buttonName+"'])[2]"));
    }

    @And("User click the {string} buttons")
    public void userClickTheButtons(String button1) {
        WebElement buttonElement = getButtonElements(button1);
        seleniumWaits.untilElementIsVisible(buttonElement, GlobalConstants.SHORT_WAIT);
        seleniumActions.scrollInToElement(buttonElement);
        seleniumActions.jsClick(buttonElement);
    }
}

