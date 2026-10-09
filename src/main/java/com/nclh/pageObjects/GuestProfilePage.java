package com.nclh.pageObjects;

import com.nclh.base.BasePage;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.stream.Collectors;

public class GuestProfilePage {
    private final WebDriver webDriver;
    private final SeleniumActions seleniumActions;
    private final SeleniumWaits seleniumWaits;
    private final CommonPage commonPage;
    private final CommonFunctions commonFunctions;
    protected BasePage basePage;
    @FindBy(xpath = "//h1[contains(@class,'text-truncate') and normalize-space()='%s']")
    private WebElement guestFullNameText;

    @FindBy(xpath = "(//button[@id='close-sidebar-panel-button'])[1]")
    private WebElement closeQuickLinksIcon;

    public GuestProfilePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        seleniumActions = new SeleniumActions(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonPage = new CommonPage(webDriver);
        commonFunctions = new CommonFunctions();
        basePage = new BasePage(webDriver);

    }

    public List<String> getColumnValues(Map<String, String> data, String... columnName) {
        List<String> values = new ArrayList<>();
        for (Map.Entry<String, String> row : data.entrySet()) {
            if (row.getKey().equals(columnName)) {
                values.add(row.getValue());
            }
        }
        return values;
    }

    public String getValue(String text) {
        if (text.contains("Middle Name") || text.contains("First Name") || text.contains("Last Name") || text.contains("Profession") || text.contains("Golden ID")) {
            seleniumActions.scrollInToElement(webDriver.findElement(By.cssSelector("[placeholder*='" + text + "']")));
            String value = seleniumActions.getAttributeValue(webDriver.findElement(By.cssSelector("[placeholder*='" + text + "']")));
            if (value.isEmpty()) {
                value = null;
            }
            return value;
        } else if (text.contains("Date of Birth")) {
            seleniumActions.scrollInToElement(webDriver.findElement(By.cssSelector("[placeholder*='" + text + "']")));

            String value = seleniumActions.getTextMessage(
                    webDriver.findElement(By.xpath("//*[@placeholder='" + text + "']/div/span"))
            );

            if (value.isEmpty()) {
                value = null;
            }
            return value;
        } else if (text.contains("Title")) {
            seleniumActions.scrollInToElement(webDriver.findElement(By.cssSelector("[placeholder*='" + text + "']")));

            String value = seleniumActions.getTextMessage(
                    webDriver.findElement(By.xpath("//*[@placeholder='" + text + "']/mat-select/div/div/span"))
            );
            if (value.isEmpty()) {
                value = null;
            }
            return value.toUpperCase();
        } else if (text.contains("Birth Place")) {
            seleniumActions.clickOnElement(By.xpath("//div[text()='Documents']"));
            seleniumActions.sleep(2000);
            seleniumActions.scrollInToElement(webDriver.findElement(By.cssSelector("[placeholder*='" + text + "']")));
            String value = seleniumActions.getAttributeValue(webDriver.findElement(By.cssSelector("[placeholder*='" + text + "']")));
            seleniumActions.clickOnElement(By.xpath("//div[text()='General']"));
            seleniumActions.sleep(2000);
            if (value.isEmpty()) {
                value = null;
            }
            return removeTrailingComma(value);
        } else if (text.contains("Nationality")) {
            seleniumActions.scrollInToElement(webDriver.findElement(By.cssSelector("[placeholder*='" + text + "']")));
            String value = seleniumActions.getTextMessage(
                    webDriver.findElement(By.xpath("//*[@placeholder='" + text + "']/mat-select/div/div/span/span"))
            );
            return getCountryCode(value);
        }
        else if(text.contains("Preferred Language")){
            seleniumActions.scrollInToElement(webDriver.findElement(By.cssSelector("[placeholder*='" + text + "']")));
            String value = seleniumActions.getTextMessage(
                    webDriver.findElement(By.xpath("//*[@placeholder='" + text + "']/mat-select/div/div/span/span"))
            );

            if (value.equals("Female")) {
                value = "F";
            } else if (value.equals("Male")) {
                value = "M";
            }

            if (value.isEmpty()) {
                value = null;
            }

            return getLanguageCode(value).toUpperCase();
        }
        else if(text.contains("Loyalty Tier")){
            seleniumActions.clickOnElement(By.xpath("//div[text()='Loyalty']"));
            seleniumActions.sleep(2000);
            seleniumActions.scrollInToElement(webDriver.findElement(By.cssSelector("[placeholder*='" + text + "']")));
            String value = null;

            try{
               value = seleniumActions.getTextMessage(
                        webDriver.findElement(By.xpath("//*[@placeholder='" + text + "']/mat-select/div/div/span/span"))
                );
            }catch (NoSuchElementException nse){
                value = null;
            }

            return value;
        }
        else if(text.contains("Membership ID") || text.contains("Points Total")) {
            seleniumActions.clickOnElement(By.xpath("//div[text()='Loyalty']"));
            seleniumActions.sleep(2000);
            seleniumActions.scrollInToElement(webDriver.findElement(By.cssSelector("[placeholder*='" + text + "']")));
            String value = seleniumActions.getAttributeValue(webDriver.findElement(By.cssSelector("[placeholder*='" + text + "']")));
            if (value.isEmpty()) {
                value = null;
            }
            return value;
        }
        else {
            seleniumActions.scrollInToElement(webDriver.findElement(By.cssSelector("[placeholder*='" + text + "']")));
            String value = seleniumActions.getTextMessage(
                    webDriver.findElement(By.xpath("//*[@placeholder='" + text + "']/mat-select/div/div/span/span"))
            );

            if (value.equals("Female")) {
                value = "F";
            } else if (value.equals("Male")) {
                value = "M";
            }

            if (value.isEmpty()) {
                value = null;
            }

            return value;
        }
    }

    public String maskYear(String date) {
        if (date == null || !date.contains("-")) {
            return date;
        }
        int firstDash = date.indexOf("-");
        return "****" + date.substring(firstDash);
    }

    public String removeTrailingComma(String input) {
        if (input != null && input.endsWith(",")) {
            return input.substring(0, input.length() - 1).trim();
        }
        return input != null ? input.trim() : null;
    }


    public String getCountryCode(String countryName) {
        if(countryName.equals("United States Of America")){
            countryName = "United States";
        }
        for (String iso : Locale.getISOCountries()) {
            Locale locale = new Locale("", iso);
            if (locale.getDisplayCountry().equalsIgnoreCase(countryName)) {
                try {
                    return locale.getISO3Country();
                } catch (MissingResourceException e) {
                    return null;
                }
            }
        }
        return null;
    }

    public List<String> cleanList(List<String> list) {
        return list.stream()
                .filter(item -> item != null && !"null".equals(item))
                .collect(Collectors.toList());
    }

    public String getLanguageCode(String languageName) {
        for (Locale locale : Locale.getAvailableLocales()) {
            if (locale.getDisplayLanguage(Locale.ENGLISH).equalsIgnoreCase(languageName)) {
                return locale.getLanguage();
            }
        }
        return null; // Not found
    }

    public void closeQuickLinks(){
        try{
            seleniumWaits.untilElementIsClickable(closeQuickLinksIcon);
            seleniumActions.clickOnElement(closeQuickLinksIcon);
        } catch (Exception e) {
            System.out.println("right panel is closed");
        }
    }
}
