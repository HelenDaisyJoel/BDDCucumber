package com.nclh.pageObjects;

import com.nclh.base.BasePage;
import com.nclh.constants.GlobalConstants;
import com.nclh.constants.SafetyManagementConstatnts;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.nclh.utilities.SeleniumActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class CommonPage {
    private WebDriver webDriver;
    SeleniumActions seleniumActions;
    CommonFunctions commonFunctions;
    SeleniumWaits seleniumWaits;
    public static final Logger logger
            = LoggerFactory.getLogger(SeleniumActions.class);
    protected WebDriverWait wait;
    BasePage basePage;


    public CommonPage(WebDriver webDriver) {
        seleniumActions = new SeleniumActions(webDriver);
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        commonFunctions = new CommonFunctions();
        seleniumWaits = new SeleniumWaits(webDriver);
        basePage = new BasePage(webDriver);

        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.SHORT_WAIT));

    }

    @FindBy(css = "#toast-title-success")
    private WebElement toastMessage;

    @FindBy(xpath = "(//*[@href='#user-photo'] | //img[contains(@src,'imageType=PROFILE')])[1]")
    private WebElement getProfileIcon;

    @FindBy(xpath = "//div[contains(@class,'dx-info')]")
    private WebElement totalItemsText;

    @FindBy(id = "complex-filter-search-input")
    private WebElement searchBox;

    @FindBy(xpath ="//input[@placeholder='Search by Cabin Nr, Last name, First name, Booking Nr, Group name, Travel Doc Nr, Crew ID, Safety Plan Nr']")
    private WebElement crewSearchBox;

    @FindBy(xpath ="//input[@placeholder='Search by Name, Crew ID']")
    private WebElement cabinAssignmentCrewSearchBox;
    By successToast = By.xpath("//div[@id='toast-title-success']");
    @FindBy(css = "[class*='complex-search-input']")
    private WebElement bulkOperationSearchBox;

    public String getToastMessage() {
        return seleniumActions.getTextMessage(toastMessage);
    }

    public void typeValueInPlaceHolderCssInput(String placeHolder, String data) {
        seleniumWaits.untilElementIsVisible(webDriver.findElement(By.cssSelector("[placeholder='" + placeHolder + "']")));
        webDriver.findElement(By.cssSelector("[placeholder='" + placeHolder + "']")).sendKeys(data);
    }

    public void openLinkUsingHref(String link) {
        String name = "//*[contains(@href,'%s')]";
        try {
            seleniumActions.sleep(3000);
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(name, link))));
            seleniumActions.highLighterMethod(webDriver.findElement(By.xpath(String.format(name, link))));

            seleniumActions.moveToElementAndClickOnElement(
                    webDriver.findElement(By.xpath(String.format(name, link))),
                    webDriver.findElement(By.xpath(String.format(name, link)))
            );
        } catch (Exception e) {
            seleniumActions.clickOnElement(By.xpath(String.format(name, link)));
        }
    }

    public void navigateTo(String button1) {
        seleniumActions.sleep(1000);
        String text = "//span[text()='" + button1 + "' or normalize-space()='" + button1 + "']";
        try {
            seleniumWaits.untilElementIsPresent(By.xpath(text), 10);
            seleniumActions.highLighterMethod(webDriver.findElement(By.xpath(text)));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
            seleniumActions.sleep(3000);
        } catch (TimeoutException e) {
        }
    }

    public void clickOnButton(String button1) {
        String text = "//button[text()='" + button1 + "' or normalize-space()='" + button1 + "']";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(text)));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
    }

    public void jsClickOnButton(String button1) {
        String text = "//span[text()='" + button1 + "' or normalize-space()='" + button1 + "']";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(text)));
        seleniumActions.jsClick(webDriver.findElement(By.xpath(text)));
    }


    public void clickOnTab(String name) {
        String text = "//div[@role='tab']/div/div[text()='" + name + "' or normalize-space()='" + name + "']";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(text)));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
    }
    private By getLabel(String name) {
        return By.xpath("(//div[text()='" + name + "' or normalize-space()='" + name + "'])[1]");
    }

    public void clickOnLabel(String name) {
        seleniumWaits.untilElementIsPresent(getLabel(name),GlobalConstants.SHORT_WAIT);
        WebElement element = webDriver.findElement(getLabel(name));
        seleniumWaits.untilElementIsVisible(element);
        seleniumWaits.untilElementIsClickable(element);
        seleniumActions.clickOnElement(element);
    }

    public void clickOnElement(String name) {
        String text = "//span[text()='" + name + "' or normalize-space()='" + name + "']";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(text)));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath(text)));
    }

    public void assertUrlEndsWith(String expectedText) {
        seleniumWaits.untilUrlContains(webDriver, GlobalConstants.SHORT_WAIT, expectedText);
        String currentUrl = webDriver.getCurrentUrl();
        assert currentUrl != null;
        Assert.assertTrue(
                currentUrl.endsWith(expectedText),
                "URL does not end with the expected path. Expected to end with: "
                        + expectedText + " but got: " + currentUrl
        );
    }

   public void assertUrlContains(String expectedText) {
       WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.SHORT_WAIT));

       wait.until(driver -> {
           String currentUrl = driver.getCurrentUrl();
           if (currentUrl == null) return false;
           return normalize(currentUrl).contains(normalize(expectedText));
       });

       String currentUrl = webDriver.getCurrentUrl();
       assert currentUrl != null;

       boolean match = normalize(currentUrl).contains(normalize(expectedText));

       Assert.assertTrue(match, "URL does not contain the expected text. Expected to contain text: " + expectedText + " but got: " + currentUrl);

       if (match) {
          logger.info("URL contains the expected text: " + expectedText);
       }
   }

    private String normalize(String input) {
        return input.toLowerCase().replaceAll("[\\s-]", "");
    }

    public void waitForDomReady() {
        seleniumActions.sleep(SafetyManagementConstatnts.MAX_CHARACTER);
        /*seleniumActions.sleep(3000);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        wait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));

        wait.until(d -> {
            try {
                Long active = (Long) ((JavascriptExecutor) d)
                        .executeScript("return window.jQuery ? jQuery.active : 0");
                return active == 0;
            } catch (Exception e) {
                return true;
            }
        });*/
    }
    private String originalWindowHandle;
    /**
     * Opens a duplicate of the current browser tab.
     * Stores the original window handle so you can switch back later.
     */
    public void openDuplicateTab() {
        try {
            originalWindowHandle = webDriver.getWindowHandle();

            String currentUrl = webDriver.getCurrentUrl();

            webDriver.switchTo().newWindow(WindowType.TAB);
            webDriver.get(currentUrl);

            waitForDomReady();
            logger.info("Opened duplicate tab with URL: {}", currentUrl);

        } catch (Exception e) {
            logger.error("Failed to open duplicate tab", e);
            throw e;
        }
    }

    /**
     * Switches back to the original tab that was active before opening the duplicate.
     */
    public void switchBackToOriginalTab() {
        if (originalWindowHandle != null) {
            webDriver.switchTo().window(originalWindowHandle);
            waitForDomReady();
            logger.info("Switched back to original tab");
        } else {
            logger.warn("No original window handle stored. Cannot switch back.");
        }
    }
    public boolean untilElementIsVisible(WebElement webElement) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.SHORT_WAIT));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public WebElement getProfileIconElement() {
        return getProfileIcon;
    }

    public int getTotalItemsCount() {
        seleniumActions.highLighterMethod(totalItemsText);
        return CommonFunctions.extractNumberFromText(totalItemsText.getText(), "\\((\\d+) items\\)");
    }
    public void navigateToPreviousPage(){
        webDriver.navigate().back();
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Enters the given search text into the search box.
     *
     *
     * @param searchString the text to be entered into the search box
     */
    public void searchFor(String searchString) {
        basePage.type(searchBox, searchString);

    }

    public void searchForCrew(String searchString) {
        basePage.type(crewSearchBox, searchString);

    }

    public void searchCabinAssignmentCrew(String searchString) {
        cabinAssignmentCrewSearchBox.clear();
        basePage.type(cabinAssignmentCrewSearchBox, searchString);

    }

    /**
     * Returns a locator for the date hint element associated with a given input field placeholder.
     *
     * <p>This method dynamically builds an XPath based on the provided placeholder text
     * (e.g., "Expiry Date", "Issue Date") and locates the corresponding hint element
     * (typically displaying date format like "m/d/yyyy") within the same
     * {@code mat-form-field} container.</p>
     *
     * <p>Useful for validating date format hints in Angular Material date picker fields
     * without hardcoding specific placeholders.</p>
     *
     * @param placeholder the value of the {@code data-placeholder} attribute of the input field
     * @return a {@link By} locator pointing to the associated date hint element
     */
    public By dateHint(String placeholder) {
        return By.xpath(
                String.format(
                        "//input[@data-placeholder='%s']/ancestor::mat-form-field//div[contains(@class,'mat-hint')]",
                        placeholder
                )
        );
    }

    public By dateInput(String placeholder) {
        return By.xpath(String.format("//input[@data-placeholder='%s']", placeholder));
    }


    public void enterDate(String placeholder, String date) {
        By locator = dateInput(placeholder);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        element.click();

        try {
            element.clear();
            element.sendKeys(date);
        } catch (InvalidElementStateException e) {
            // fallback for date picker
            System.out.println("Field is not editable, use calendar selection instead");
        }
    }

    /**
     * Returns a dynamic locator for buttons like Confirm / Cancel based on visible text.
     *
     * @param buttonText the visible text of the button (e.g., "Confirm", "Cancel")
     * @return By locator for the matching button
     */
    public By getButtonByText(String buttonText) {
        return By.xpath(String.format("//button[.//span[normalize-space()='%s']]", buttonText));
    }

    /**
     * Clicks on the button only if it is present in the DOM.
     * If the element is not found, the method skips the action without throwing an exception.
     *
     * @param buttonText the visible text of the button (e.g., "Confirm", "Cancel")
     */
    public void clickIfPresent(String buttonText) {
        By locator = getButtonByText(buttonText);

        List<WebElement> elements = webDriver.findElements(locator);

        if (!elements.isEmpty()) {
            WebElement element = elements.get(0);
            waitForVisibility(By.xpath(String.format("//button[.//span[normalize-space()='%s']]", buttonText)));
            element.click();
        } else {
            System.out.println("Element with text '" + buttonText + "' not present. Skipping click.");
        }
    }

    /**
     * Waits until the element located by the given locator is visible.
     *
     * @param locator the By locator of the element
     * @return the visible WebElement
     */
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement passengerProfileLink(String name){
        By locator = By.xpath(
                "//a[contains(@id,'passenger-profile-') and contains(normalize-space(),'" + name + "')]"
        );
        return waitForVisibility(locator); // preferred
    }

    public WebElement crewProfileLink(String name){
        By locator = By.xpath(
                "//a[contains(@id,'crew-list-page-first-name-') and contains(normalize-space(),'" + name + "')]"
        );
        return waitForVisibility(locator);
    }



    public WebElement guestProfileLink(String name){
        By locator = By.xpath(
                "//a[contains(@id,'passenger-profile-') and contains(normalize-space(),'" + name + "')]"
        );
        return waitForVisibility(locator);
    }

    public void waitForElementToDisappear() {
        new WebDriverWait(webDriver, Duration.ofSeconds(GlobalConstants.SHORT_WAIT)).until(ExpectedConditions.invisibilityOf(webDriver.findElement(successToast)));
    }

    public WebElement getElementByText(String text) {
        return webDriver.findElement(By.xpath("//*[normalize-space(.)='" + text + "']"));
    }
    public void bulkOperationsearchFor(String searchString) {
        basePage.type(bulkOperationSearchBox, searchString);
        seleniumWaits.untilElementIsVisible(getElementByText(searchString));
        seleniumActions.highLighterMethod(getElementByText(searchString));
    }
}

