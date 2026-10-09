package com.nclh.utilities;

import com.nclh.constants.GlobalConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class SeleniumActions {
    private WebDriver driver;
    JavascriptExecutor executor;
    public static final Logger logger
            = LoggerFactory.getLogger(SeleniumActions.class);
    public SeleniumWaits seleniumWaits;

    public SeleniumActions(WebDriver driver) {
        this.driver = driver;
        executor = (JavascriptExecutor) driver;
        seleniumWaits = new SeleniumWaits(driver);
    }

    /**
     * Checks if the element is enabled.
     *
     * @param element The WebElement to check.
     * @return true if the element is enabled, false otherwise.
     */
    public boolean isElementEnabled(WebElement element) {
        boolean flag = false;
        try {
            //highLighterMethod(element);
            flag = element.isEnabled();
            logger.info("Successfully element enabled on the page {}", element);
        } catch (NoSuchElementException e) {
            logger.error("Failed to enable element on the page {}", element);
        }
        return flag;
    }

    /**
     * Right click And ClickOnElement
     *
     * @param rightClickElement
     */
    public void rightClickAndClickOnOption(WebElement rightClickElement, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.contextClick(rightClickElement).click().build().perform();
            sleep(1000);
            element.click();
            logger.info("Successfully right click to element {}", rightClickElement);
        } catch (NoSuchElementException e){
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     *
     * @param elements webElements
     * @param backGround css entity name
     * @return list of css values
     */
    public Set<String> getListOfCssColour(List<WebElement> elements, String backGround) {
        Set<String> listOfElements = new HashSet<>();
        for (int i = 0; i < elements.size(); i++) {
            listOfElements.add(elements.get(i).getCssValue(backGround).trim());
        }
        return listOfElements;
    }

    /**
     * Click on element using javascript executor
     *
     * @param cssSelector cssSelector expression
     */
    public void jsClickQuerySelector(String cssSelector) {
        try {
            executor.executeScript("document.querySelector("+cssSelector+").click();");
            logger.info("Successfully clicked on element {} using query selector", cssSelector);
        } catch (Exception e) {
            logger.info("Failed to click on element {} using query selector", cssSelector, e);
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Drag an item to the another place on the web
     * @param sourceElement - source WebElement
     * @param destinationElement - destination WebElement
     */
    public void dragAndDrop(WebElement sourceElement, WebElement destinationElement){
        Actions action = new Actions(driver);
        action.dragAndDrop(sourceElement, destinationElement).build().perform();
    }

    /**
     * Get html text from css Element using jsExecutor
     *
     * @return message
     */
    public String jsQuerySelectorGetInnerHtml(String cssSelector) {
        String text = null;
        try {
            text = executor.executeScript("document.querySelector(\"" + cssSelector + "\").innerHTML").toString();
            logger.info("Successfully retrieved html from css element {}", cssSelector);
        } catch (Exception e) {
            logger.info("Failed to retrieve html from css element {}", cssSelector);
            throw new NoSuchElementException("Element not found");
        }
        return text;
    }

    /**
     * Getting index value of header value from the table
     *
     * @param elements - list of elements
     * @param item     - column name
     * @return
     */
    public int getIndexValueFromTheList(List<WebElement> elements, String item) {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            items.add(elements.get(i).getText());
        }
        return items.indexOf(item);
    }

    /**
     * Take screenshot of the web element
     *
     * @param image - web element
     * @return screenshot
     */
    public static String takeScreenShotOfElement(WebElement image) throws IOException {
        try {
            File file = image.getScreenshotAs(OutputType.FILE);
            File destFile = new File(System.getProperty("user.dir") + "/testData/screenshot.png");
            FileUtils.copyFile(file, destFile);
            logger.info("Screenshot taken for element located by: {}", image);
            return "screenshot.png";
        } catch (IOException ioe) {
            logger.error("Failed to take screenshot of element located by: {}", image, ioe);
            throw new IOException("File not found");
        }
    }

    /**
     * Double click on web element
     *
     * @param element web element
     */
    public void doubleClickOnElement(WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.doubleClick(element).perform();
            logger.info("Double clicked on element located by: {}", element);
        } catch (NoSuchElementException nse) {
            logger.error("Failed to double click on element located by: {}", element, nse);
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Swipe from one element to another element
     *
     * @param element1  - web element
     * @param element2  - web element
     * @param direction - direction from left to right
     */
    public void swipeOnElement(WebElement element1, WebElement element2, String direction) {
        Actions action = new Actions(driver);
        switch (direction) {
            case "left":
                try {
                    action.clickAndHold(element1);
                    sleep(2000);
                    action.moveToElement(element2).release();
                    action.build().perform();
                    logger.info("Move to element and click on element located by: {}", element1);
                } catch (NoSuchElementException nse) {
                    logger.error("Failed to move and click on element located by: {}", element1, nse);
                    throw new NoSuchElementException("Element not found");
                }
                break;
            case "right":
                try {
                    action.clickAndHold(element2);
                    sleep(2000);
                    action.moveToElement(element1).release();
                    action.build().perform();
                    logger.info("Move to element and click on element located by : {}", element2);
                } catch (NoSuchElementException nse) {
                    logger.error("Failed to move and click on element located by: {}", element2, nse);
                    throw new NoSuchElementException("Element not found");
                }
                break;
        }
    }

    /**
     * Click on element
     *
     * @param by - by class
     */
    public void clickOnElement(By by) {
        WebElement element = driver.findElement(by);
        try {
            highLighterMethod(element);
            element.click();
            logger.info("Successfully clicked on element-by {}", by);
        } catch (NoSuchElementException noSuchElementException) {
            logger.error("Failed to click on element-by {}", by);
            throw new NoSuchElementException("Element not found");
        } catch (ElementNotInteractableException elementNotInteractableException) {
            jsClick(element);
            logger.info("Successfully clicked on element-by {} using jsExecutor", by);
        }
    }

    /**
     * Clear data in the input box
     *
     * @param element
     * @return
     * @throws Exception
     */
    public void clearValue(WebElement element) {

        try {
            element.clear();
            logger.info("Cleared in element located by: {}", element);
        } catch (NoSuchElementException e) {
            logger.error("Failed to clear value in element located by: {}", element, e);
            throw new NoSuchElementException("Element not found");
        }
    }

    public void pressEscapeKey() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.keyPress(KeyEvent.VK_ESCAPE);
        logger.info("Successfully press on ESC key");
    }

    public void pressTabKey() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.keyPress(KeyEvent.VK_TAB);
    }

    public void pressEnterKey() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    public void sleep(int milliSecs) {
        try {
            Thread.sleep(milliSecs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Scroll to element using jsExecutor
     *
     * @param element
     */
    public void scrollInToElement(WebElement element) {
        try {
            executor.executeScript("arguments[0].scrollIntoView(true);", element);
            highLighterMethod(element);
            logger.info("Successfully scroll to element {}", element);
        } catch (NoSuchElementException e) {
            logger.error("Failed to scroll to element {}", element, e);
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Click on element
     *
     * @param element - webElement
     */
    public void clickElement(WebElement element) {
        try {
            try {
                highLighterMethod(element);
                element.click();
                logger.info("Successfully clicked on element {} ", element);
            } catch (ElementNotInteractableException eNie) {
                System.out.println(eNie.toString());
                jsClick(element);
                logger.info("Successfully clicked on element {} using js Executor", element);
            }
        } catch (Exception e) {
            try {
                moveToElementAndClick(element);
            } catch (NoSuchElementException nse) {
                System.out.println(nse.toString());
                logger.error("Failed to click element {}", element);
                throw new NoSuchElementException("Element not found");
            }
        }
    }

    /**
     * Click on element
     *
     * @param element
     */
    public void clickOnElement(WebElement element) {

        try {
//            flashElement(element);
            highLighterMethod(element);
            element.click();
            logger.info("Successfully clicked on element {}", element);
        } catch (NoSuchElementException noSuchElementException) {
            logger.error("Failed to click on element {}", element);
            throw new NoSuchElementException("Element not found");
        } catch (ElementNotInteractableException elementNotInteractableException) {
//            flashElement(element);
            highLighterMethod(element);
            jsClick(element);
            logger.info("Successfully clicked on element {} using jsExecutor", element);
        }
    }

    /**
     * Click on element using javascript executor
     *
     * @param element WebElement
     * @return
     */
    public void jsClick(WebElement element) {
        try {
            executor.executeScript("arguments[0].click();", element);
            logger.info("Successfully clicked on element {} using jsExecutor", element);
        } catch (Exception e) {
            logger.info("Failed to click on element {} using jsExecutor", element, e);
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Type data in the input box
     *
     * @param element
     * @param data
     * @return
     * @throws Exception
     */
    public void typeValue(WebElement element, String data) {

        try {
            element.sendKeys(data);
            logger.info("Entered text '{}' into element located by: {}", data, element);
        } catch (NoSuchElementException e) {
            logger.error("Failed to enter text '{}' into element located by: {}", data, element, e);
            throw new NoSuchElementException("Element not found");
        } catch (ElementNotInteractableException eie) {
            jsType(element, data);
        }
    }

    public void typeAndHighlightValue(WebElement element, String data) {

        try {
            highLighterMethod(element);
            element.clear();
            element.sendKeys(data);
            highLighterMethod(element);

            logger.info("Entered text '{}' into element located by: {}", data, element);
        } catch (NoSuchElementException e) {
            logger.error("Failed to enter text '{}' into element located by: {}", data, element, e);
            throw new NoSuchElementException("Element not found");
        } catch (ElementNotInteractableException eie) {
            highLighterMethod(element);
            jsType(element, data);
            highLighterMethod(element);

            logger.info("Entered text '{}' into element using JavaScript", data);
        }
    }

    /**
     * Type value using java script executor
     *
     * @param element
     * @param data
     */
    public void jsType(WebElement element, String data) {

        try {
            highLighterMethod(element);
            executor.executeScript("arguments[0]. value='" + data + "';", element);
            logger.info("Entered text '{}' into element using js located by: {}", data, element);
        } catch (Exception e) {
            logger.error("Failed to enter text '{}' into element located by: {}", data, element, e);
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * moveToElement And ClickOnElement
     *
     * @param parent
     * @param child
     */
    public void moveToElementAndClickOnElement(WebElement parent, WebElement child) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(parent).build().perform();
            logger.info("Successfully moved on element {}", parent);
            actions.moveToElement(child).click().build().perform();
            logger.info("Successfully moved and clicked on element {}", child);
        } catch (NoSuchElementException e) {
            logger.error("Failed to click on element {}", parent, e);
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * moveToElement
     *
     * @param parent - webElement
     */
    public void moveToElement(WebElement parent) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(parent).build().perform();
            logger.info("Successfully moved to element {}", parent);
        } catch (NoSuchElementException e) {
            logger.error("Failed to click on element {}", parent, e);
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * moveToElement And ClickOnElement
     *
     * @param parent
     */
    public void moveToElementAndClick(WebElement parent) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(parent).click().build().perform();
            logger.info("Successfully moved to element {}", parent);
        } catch (NoSuchElementException e) {
            logger.error("Failed to click on element {}", parent, e);
            throw new NoSuchElementException("Element not found");
        }
    }

    /**
     * Switch to window
     */
    public void switchToOpenedWindow() {
        try {
            Set<String> windows = driver.getWindowHandles();
            for (String window : windows) {
                driver.switchTo().window(window);
                logger.info("Successfully switched to window {}", window);
            }
        } catch (NoSuchWindowException e) {
            logger.info("Failed to switch to window");
            throw new NoSuchWindowException("No Window opened");
        }
    }

    /**
     * Switches the WebDriver's context to the specified iframe.
     *
     * @param element The WebElement representing the iframe to switch to.
     * @throws NoSuchFrameException if the specified iframe is not found.
     */
    public void switchToFrame(WebElement element) {
        try {
            driver.switchTo().frame(element);
        } catch (NoSuchFrameException e) {
            throw new NoSuchFrameException("No frame found");
        }
    }

    /**
     * Opens a new browser tab or window.
     * This method uses the WebDriver's `switchTo().newWindow()` functionality to open a new tab.
     * Throws a `NoSuchWindowException` if the operation fails.
     */
    public void openNewTabOrWindow() {
        try {
            driver.switchTo().newWindow(WindowType.TAB);
        } catch (NoSuchWindowException e) {
            throw new NoSuchWindowException("No open opened");
        }
    }

    /**
     * Highlights a WebElement by applying a red border around it using JavaScript Executor.
     *
     * @param element The WebElement to be highlighted.
     */
    public void highLighterMethod(WebElement element) {
        try {
            //executor.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
            executor.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * is element displayed
     *
     * @param element
     * @return
     */
    public boolean isElementDisplayed(WebElement element) {
        boolean flag = false;
        try {
            highLighterMethod(element);
            flag = element.isDisplayed();
            logger.info("Successfully element displayed on the page {}", element);
        } catch (NoSuchElementException e) {
            logger.error("Failed to display element on the page {}", element);
        }
        return flag;
    }

    /**
     * is element displayed
     *
     * @param elements - list of elements
     * @return
     */

    public boolean areElementsDisplayed(List<WebElement> elements) {
        boolean allDisplayed = true;

        for (WebElement element : elements) {
            try {
                highLighterMethod(element);
                if (!element.isDisplayed()) {
                    allDisplayed = false;
                    logger.info("Element is not displayed on the page {}", element);
                } else {
                    logger.info("Element is displayed on the page {}", element);
                }
            } catch (NoSuchElementException e) {
                allDisplayed = false;
                logger.error("Element not found on the page {}", element);
            }
        }

        return allDisplayed;
    }

    /**
     * Highlight the WebElement (implementation depends on your requirements).
     *
     * @param element WebElement to highlight
     */
    public boolean isElementPresentOnThePage(WebElement element) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        try {
            highLighterMethod(element);
            element.isDisplayed();
            logger.info("Successfully element present on the page {}", element);
            return true;
        } catch (NoSuchElementException e) {
            logger.info("Failed to present element on the page {}", element);
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        }
    }

    /**
     * Get attribute from web element
     *
     * @return message
     */
    public String getAttributeValue(WebElement element) {
        String text = null;
        try {
            highLighterMethod(element);
            text = element.getAttribute("value");
            System.out.println(text);
            logger.info("Successfully retrieved attribute from the element {}", element);
        } catch (Exception e) {
            logger.info("Failed to retrieve attribute from the element {}", element);
            throw new NoSuchElementException("Element not found");
        }
        return text;
    }

    /**
     * Get text from web element
     *
     * @return message
     */
    public String getTextMessage(WebElement element) {
        String text = null;
        try {
            highLighterMethod(element);
            text = element.getText().trim();
            System.out.println(text);
            logger.info("Successfully retrieved text from the element {}", element);
        } catch (Exception e) {
            logger.info("Failed to retrieve text from the element {}", element);
            throw new NoSuchElementException("Element not found");
        }
        return text;
    }

    /**
     * Get text from web element using jsExecutor
     *
     * @return message
     */
    public String jsGetTextMessage(WebElement element) {
        String text = null;
        try {
            text = executor.executeScript("return arguments[0].value", element).toString();
            logger.info("Successfully retrieved text from web element {}", element);
        } catch (Exception e) {
            logger.info("Failed to retrieve text from web element {}", element);
            throw new NoSuchElementException("Element not found");
        }
        return text;
    }

    /**
     * Get value from css Element using jsExecutor
     *
     * @return message
     */
    public String jsQuerySelectorGetValue(String cssSelector) {
        String text = null;
        try {
            text = executor.executeScript("document.querySelector('" + cssSelector + "').value").toString();
            logger.info("Successfully retrieved text from css element {}", cssSelector);
        } catch (Exception e) {
            logger.info("Failed to retrieve text from css element {}", cssSelector);
            throw new NoSuchElementException("Element not found");
        }
        return text;
    }

    /**
     * Get text from css Element using jsExecutor
     *
     * @return message
     */
    public String jsQuerySelectorGetTextMessage(String cssSelector) {
        String text = null;
        try {
            //Example
            //document.querySelector("[icon='error'] + div > span:first-of-type").textContent
            text = executor.executeScript("document.querySelector(\"" + cssSelector + "\").textContent").toString();
            logger.info("Successfully retrieved text from css element {}", cssSelector);
        } catch (Exception e) {
            logger.info("Failed to retrieve text from css element {}", cssSelector);
            throw new NoSuchElementException("Element not found");
        }
        return text;
    }

    /**
     * Get text from web page using jsExecutor
     *
     * @return message
     */
    public String jsGetTextMessageFromPage() {
        String text = null;
        try {
            text = executor.executeScript("return document.documentElement.innerText;").toString();
            logger.info("Successfully retrieved text from the Page");
        } catch (Exception e) {
            logger.info("Failed to retrieve text from the Page");
            throw new NoSuchElementException("Element not found");
        }
        return text;
    }

    /**
     * Retrieves the text content of a list of WebElements and returns it as a list of strings.
     *
     * @param elements List of WebElements to extract text from.
     * @return List of strings containing the trimmed text of each WebElement.
     */
    public List<String> getListOfElementsText(List<WebElement> elements) {
        List<String> listOfElements = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            listOfElements.add(elements.get(i).getText().trim());
        }
        return listOfElements;
    }

    /**
     * get number of elements
     *
     * @param elements - web elements
     * @return - number of web elements
     */
    public int getNumberOfElements(List<WebElement> elements) {
        int numberOfItems = 0;
        try {
            numberOfItems = elements.size();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return numberOfItems;
    }

    public void jsClickById(String locator_value) {

        try {
            // executor.executeScript("document.querySelector('"+locator_value+"').click();");
            executor.executeScript("document.getElementById('" + locator_value + "').click();");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jsTypeQuerySelector(String locator_value, String data, String description) {

        try {
            executor.executeScript("document.querySelector('" + locator_value + "').value='" + data + "'");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get text form multiple elements and maintain in a list
     *
     * @param elements - web elements
     * @return - text from web elements
     */
    public List<String> getTextOfMultipleElementsIntoList(List<WebElement> elements) {
        List<String> allElements = new ArrayList<String>();
        try {

            for (int i = 0; i < elements.size(); i++) {
                allElements.add(elements.get(i).getText());
            }
            System.out.println("allElements ========= " + allElements);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return allElements;
    }

    /**
     * Perform signature
     *
     * @param canvasElement - web element
     */
    public void performSignature(WebElement canvasElement) {
        try {
            Actions builder = new Actions(driver);

            Action signature = builder.contextClick(canvasElement)
                    .clickAndHold()
                    .moveToElement(canvasElement, 20, -50)
                    .moveByOffset(50, 50)
                    .moveByOffset(80, -50)
                    .moveByOffset(100, 50)
                    .release(canvasElement)
                    .build();
            signature.perform();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public void interactWithElementByXpath(String xpath, boolean click) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            seleniumWaits.untilElementIsVisible(element);

            if (click) {
                seleniumWaits.untilElementIsVisible(element);
                element.click();
            }
            if (element.isDisplayed()) {
                logger.info("Element is displayed: " + xpath);
            } else {
                logger.error("Element is not displayed: " + xpath);
            }
        } catch (Exception e) {
            logger.error("Test Failed. Reason: " + e.getMessage());
        }
    }

    public void selectDropdownOption(String label, String optionValue) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By dropdownArrow = By.xpath(
                "//mat-form-field[.//*[normalize-space(text())='" + label +
                        "']]//div[@aria-label='Select' or contains(@class,'mat-select-arrow-wrapper')]"
        );

        WebElement arrow = wait.until(ExpectedConditions.elementToBeClickable(dropdownArrow));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", arrow);
        arrow.click();
        logger.info(label + " dropdown opened");

        sleep(1500);

        String type;

        if (optionValue.matches("\\d+")) {
            type = "INDEX";
        } else if (optionValue.matches("\\d{1,2}:\\d{2}\\s?(AM|PM)")) {
            type = "TIME";
        } else {
            type = "TEXT";
        }

        switch (type) {

            case "INDEX":
                selectByIndex(wait, optionValue);
                break;

            case "TEXT":
                selectByText(wait, optionValue);
                break;

            case "TIME":
                selectByTime(wait, optionValue);
                break;

            default:
                logger.error("Unknown dropdown option type: " + optionValue);
        }
    }

    private void selectByIndex(WebDriverWait wait, String indexStr) {
        int index = Integer.parseInt(indexStr);

        List<WebElement> options = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//mat-option//span[@class='mat-option-text']")
                )
        );

        if (index >= 0 && index < options.size()) {
            WebElement option = options.get(index);
            option.click();
            logger.info("Selected index " + index + ": " + option.getText());
        } else {
            logger.warn("Invalid index: " + index);
        }
    }

    private void selectByText(WebDriverWait wait, String text) {
        try {
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//mat-option//span[normalize-space(text())='" + text + "']")
            ));
            option.click();
            logger.info("Selected text option: " + text);
        } catch (TimeoutException e) {
            logger.error("Text option not found: " + text);
        }
    }

    private void selectByTime(WebDriverWait wait, String timeText) {
        WebElement activityTimeInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@formcontrolname='activityTime']//input[@type='text']")
        ));
        WebElement timeField = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(activityTimeInput));
        timeField.sendKeys(Keys.CONTROL + "a");
        timeField.sendKeys(Keys.DELETE);
        timeField.sendKeys(timeText);
        timeField.sendKeys(Keys.TAB);
        logger.info("Entered Activity Time: " + timeText);
    }
    /**
     * Generates a random alphabetic string of the specified length.
     * Default character range: 'a' to 'z'.
     *
     * @param length the number of characters to generate
     * @return a randomly generated string
     */
    public String getRandomString(int length) {
        return getRandomString(length, 'a', 'z');
    }

    /**
     * Generates a random string of the specified length within a given character range.
     *
     * @param length the number of characters to generate
     * @param min the starting character (inclusive)
     * @param max the ending character (exclusive)
     * @return a randomly generated string using the specified character range
     */
    public String getRandomString(int length, char min, char max) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            char randomChar = (char) ((Math.random() * (max - min)) + min);
            sb.append(randomChar);
        }

        return sb.toString();
    }
    /**
     * Safely enters text into a WebElement.
     * Waits for visibility, clears existing text, and sends the provided value.
     *
     * @param element the WebElement to type into
     * @param value the text to send
     */
    public void sendKeys(WebElement element, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(value);

        } catch (StaleElementReferenceException e) {

            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                element.clear();
                element.sendKeys(value);
            } catch (Exception ex) {
                throw new RuntimeException("Failed to send keys", ex);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to send keys to element: " + element, e);
        }
    }






    /**
     * Clicks a button element that contains a child <span> with the given visible text.
     * <p>
     * This method is useful for Angular Material buttons where the clickable
     * element is a <button> and the visible label is inside a <span class="mat-button-wrapper">.
     * </p>
     *
     * <b>Example:</b>
     * <pre>
     * clickButtonByText(driver, "CREATE CREW RESERVATION");
     * </pre>
     *
     * @param driver WebDriver instance used to interact with the browser
     * @param text   Visible text of the button to be clicked
     *
     * @throws TimeoutException if the element is not clickable within the wait time
     */

    public void clickElementByText(WebDriver driver, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.MINI_WAIT));

        String xpath = String.format(
                "//*[self::button or self::a or self::span][normalize-space()='%s'] | //button[.//*[normalize-space()='%s']]",
                text, text
        );

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        element.click();
    }

    /**
     * Clicks on a div element whose text matches the provided value.
     * <p>
     * The text comparison:
     * <ul>
     *     <li>Ignores leading and trailing whitespace.</li>
     *     <li>Ignores case differences.</li>
     *     <li>Requires an exact text match after normalization.</li>
     * </ul>
     * </p>
     *
     * <p>Example:</p>
     * <pre>
     * clickOnText("Personal Items");
     * </pre>
     *
     * This method first attempts a standard Selenium click. If the element is
     * present but not interactable, it falls back to a JavaScript click.
     *
     * @param text the text of the div element to click; comparison is
     *             case-insensitive and ignores leading/trailing whitespace
     * @throws NoSuchElementException if no matching element is found
     */
    public void clickOnText(String text) {

        String xpath = String.format(
                "//div[translate(normalize-space(text()), " +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ', " +
                        "'abcdefghijklmnopqrstuvwxyz')='%s']",
                text.trim().toLowerCase()
        );

        try {
            WebElement element = driver.findElement(By.xpath(xpath));

            highLighterMethod(element);
            element.click();

            logger.info("Successfully clicked on element with text '{}'", text);

        } catch (NoSuchElementException noSuchElementException) {

            logger.error("Failed to find element with text '{}'", text);
            throw new NoSuchElementException("Element not found with text: " + text);

        } catch (ElementNotInteractableException elementNotInteractableException) {

            WebElement element = driver.findElement(By.xpath(xpath));

            highLighterMethod(element);
            jsClick(element);

            logger.info("Successfully clicked on element with text '{}' using jsExecutor", text);
        }
    }




}
