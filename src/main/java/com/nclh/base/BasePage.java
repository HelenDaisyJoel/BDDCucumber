package com.nclh.base;

import com.nclh.constants.GlobalConstants;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.UUID;

public class BasePage {
    private WebDriverWait wait;
    protected WebDriver driver;
    protected SeleniumActions seleniumActions;
    protected SeleniumWaits seleniumWaits;
    protected CommonFunctions commonFunctions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.SHORT_WAIT));
        this.seleniumActions = new SeleniumActions(driver);
        this.seleniumWaits = new SeleniumWaits(driver);
        this.commonFunctions = new CommonFunctions();
        PageFactory.initElements(driver, this);
    }

    /**
     * Generates a unique name by appending a random 8-character string
     * to the given name.
     *
     * <p>Example output: {@code John Doe_a1b2c3d4}</p>
     *
     * @param name the name to use
     * @return a unique name string consisting of name and a random 8-character suffix
     */
    public String generateName(String name) {
        String uniqueId = UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 8);

        return name + "-" + uniqueId;
    }

    /**
     * Clicks on a web element identified by the given locator.
     *
     * @param locator By locator of the element
     */
    public void click(By locator) {
        waitForClickability(locator);
        driver.findElement(locator).click();
    }

    /**
     * Sends text input to a web element.
     *
     * @param locator By locator of the element
     * @param text Text to be entered
     */
    public void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Waits until the given WebElement is visible on the page.
     *
     * <p>This method uses an explicit wait to ensure that the provided element
     * is visible before interacting with it. It helps avoid issues related to
     * elements not being present or not yet rendered in the DOM.</p>
     *
     * @param element the WebElement to wait for visibility
     * @return the visible WebElement once it becomes visible
     * @throws "TimeoutException" if the element does not become visible within the defined wait time
     */
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Types text into the given WebElement after waiting for visibility.
     *
     * @param element the WebElement to type into
     * @param text the text to be entered
     */
    public void type(WebElement element, String text) {
        WebElement visibleElement = waitForVisibility(element);
        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    /**
     * Retrieves visible text from a web element.
     *
     * @param locator By locator of the element
     * @return Text of the element
     */
    public String getElementText(By locator) {
        return waitForVisibility(locator).getText();
    }

    /**
     * Retrieves visible text from a WebElement.
     *
     * @param element WebElement whose text needs to be retrieved
     * @return Text of the WebElement
     */
    public String getElementText(WebElement element) {
        // Optionally, you can wait until it is visible
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    /**
     * Waits until the element is visible on the page.
     *
     * @param locator By locator of the element
     * @return WebElement once it is visible
     */
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the element is clickable.
     *
     * @param locator By locator of the element
     */
    public void waitForClickability(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Checks if an element is displayed.
     *
     * @param locator By locator of the element
     * @return true if displayed, false otherwise
     */
    public boolean isDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    /**
     * Selects a value from dropdown by visible text.
     *
     * @param locator By locator of the dropdown element
     * @param text Visible text to select
     */
    public void selectByVisibleText(By locator, String text) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByVisibleText(text);
    }

    /**
     * Gets the current page title.
     *
     * @return Page title as String
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Gets the current page URL.
     *
     * @return Current URL as String
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Navigates back to the previous page.
     */
    public void navigateBack() {
        driver.navigate().back();
    }

    /**
     * Performs click using JavaScript Executor.
     *
     * @param locator By locator of the element
     */
    public void clickUsingJS(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(locator));
    }

    /**
     * Scrolls the page until the element is in view.
     *
     * @param locator By locator of the element
     */
    public void scrollIntoView(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
    }

    /**
     * Accepts an alert popup.
     */
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    /**
     * Switches to a frame using the given locator.
     *
     * @param locator By locator of the frame element
     */
    public void switchToFrame(By locator) {
        driver.switchTo().frame(driver.findElement(locator));
    }

    /**
     * Logs a message to console.
     *
     * @param message Message to log
     */
    public void log(String message) {
        System.out.println(message);
    }

    /**
     * Clicks on span element using visible text.
     *
     * @param text visible text of the span
     */
    public void clickOnText(String text) {
        By locator = By.xpath("//span[normalize-space()='" + text + "']");
        click(locator);
    }


    /**
     * Clicks on a span element matching the given text at the specified index.
     *
     * <p>This method constructs a dynamic XPath using the provided text and index,
     * allowing interaction with a specific occurrence when multiple elements share
     * the same visible text.</p>
     *
     * <p>Note: XPath uses <b>1-based indexing</b>, so:
     * <ul>
     *   <li>index = 1 → first element</li>
     *   <li>index = 2 → second element</li>
     * </ul>
     * Ensure that the provided index is greater than 0 and within the range of
     * matching elements to avoid {@link org.openqa.selenium.NoSuchElementException}.</p>
     *
     * @param text  the visible text of the span element to be clicked
     * @param index the position of the element to click (1-based index)
     */
    public void clickOnMatchingTextByIndex(String text, int index) {
        By locator = By.xpath(String.format("(//span[normalize-space()='%s'])[%d]", text, index));
        click(locator);
    }

    /**
     * Asserts that the text of the element located by the given locator
     * matches the expected text.
     *
     * @param locator the By locator of the element
     * @param expectedText the expected text to validate against
     */
    public void assertElementTextEquals(By locator, String expectedText) {
        WebElement element = waitForVisibility(locator);
        String actualText = element.getText().trim();
        Assert.assertEquals(actualText, expectedText.trim(),
                "Text mismatch! Expected: '" + expectedText + "' but found: '" + actualText + "'");
    }


    /**
     * Asserts that the text of the given WebElement
     * matches the expected text.
     *
     * @param element the WebElement whose text needs to be verified
     * @param expectedText the expected text to validate against
     */
    public void assertElementTextEquals(WebElement element, String expectedText) {
        String actualText = waitForVisibility(element).getText().trim();
        Assert.assertEquals(actualText, expectedText.trim(),
                "Text mismatch! Expected: '" + expectedText + "' but found: '" + actualText + "'");
    }


    /**
     * Asserts that the actual text matches the expected text.
     *
     * @param actualText the actual text retrieved from the application
     * @param expectedText the expected text to validate against
     */
    public void assertElementTextEquals(String actualText, String expectedText) {
        String actual = actualText == null ? "" : actualText.trim();
        String expected = expectedText == null ? "" : expectedText.trim();

        Assert.assertEquals(actual, expected,
                String.format("Text mismatch! Expected: '%s' but found: '%s'", expected, actual));
    }

}