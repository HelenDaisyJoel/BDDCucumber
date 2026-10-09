package com.nclh.utilities;

import com.nclh.constants.GlobalConstants;
import com.nclh.managers.FileReaderManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class SeleniumWaits {

    private WebDriver webDriver;
    long time = FileReaderManager.getInstance().getConfigFileReader().getTime();

    public SeleniumWaits(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    private static void until(WebDriver webDriver, Duration timeOutInSeconds, Function<WebDriver, Boolean> waitCondition) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeOutInSeconds);
        try {
            webDriverWait.until(waitCondition);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void untilAjaxCallIsDone(WebDriver webDriver, Duration timeOutInSeconds) {
        until(webDriver, timeOutInSeconds, (function) -> {
            Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) webDriver).executeScript("return jQuery.active==0");
            if (!isJqueryCallDone) System.out.println("jQuery call is in progress");
            return isJqueryCallDone;
        });
    }

    public static void untilPageReadyState(WebDriver webDriver, Duration timeOutInSeconds) {
        until(webDriver, timeOutInSeconds, (function) -> {
            String isPageLoaded = String.valueOf(((JavascriptExecutor) webDriver).executeScript("return document.readyState"));
            if (isPageLoaded.equals("complete")) {
                return true;
            } else {
                System.out.println("Document is loading");
                return false;
            }
        });
    }

    public void setImplicitWaitTime(int timeSecs){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeSecs));
    }

    public void keepBackImplicitWaitTime(){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    public void setImplicitWaitTime(WebDriver driver, int timeSecs){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeSecs));
    }

    public void keepBackImplicitWaitTime(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    public void untilElementIsVisible(WebElement webElement) {
        new WebDriverWait(webDriver, Duration.ofSeconds(time)).until(ExpectedConditions.visibilityOf(webElement));
    }

    public void untilElementIsVisible(WebElement webElement, int duration) {
        new WebDriverWait(webDriver, Duration.ofSeconds(duration)).until(ExpectedConditions.visibilityOf(webElement));
    }
    public boolean untilElementInVisible(WebElement webElement) {
        return new WebDriverWait(webDriver, Duration.ofSeconds(time)).until(ExpectedConditions.invisibilityOf(webElement));
    }

    public void untilElementIsPresent(By webElement) {
        new WebDriverWait(webDriver,  Duration.ofSeconds(time)).until(ExpectedConditions.presenceOfElementLocated(webElement));
    }

    public void untilElementIsPresent(By webElement, int duration) {
        new WebDriverWait(webDriver,  Duration.ofSeconds(duration)).until(ExpectedConditions.presenceOfElementLocated(webElement));
    }

    public void untilElementIsClickable(WebElement webElement) {
        new WebDriverWait(webDriver, Duration.ofSeconds(time)).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void untilListElementIsVisible(List<WebElement> webElements) {
        new WebDriverWait(webDriver,  Duration.ofSeconds(time)).until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    public void untilListElementIsVisible(List<WebElement> webElements, int duration) {
        new WebDriverWait(webDriver,  Duration.ofSeconds(duration)).until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    public void untilUrlContains(WebDriver driver, int duration, String textToValidate){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.urlContains(textToValidate));

    }

//    public static void waitForElementToBeVisible(WebDriver driver, WebElement webElement, int timeoutInSeconds) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
//        wait.until(ExpectedConditions.visibilityOfElementLocated((By) webElement));
//    }

    public static void waitForVisibility(WebDriver driver, WebElement element) {
        waitForVisibility(driver, element, GlobalConstants.SHORT_WAIT);
    }

    public static void waitForVisibility(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
