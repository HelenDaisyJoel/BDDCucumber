package com.nclh.pageObjects;

import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CashBookAndCurrencyPage {
    private final WebDriver webDriver;
    private final SeleniumActions seleniumActions;
    private final SeleniumWaits seleniumWaits;
    private final CommonFunctions commonFunctions;


    public CashBookAndCurrencyPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        seleniumActions = new SeleniumActions(webDriver);
        PageFactory.initElements(webDriver, this);
        seleniumWaits = new SeleniumWaits(webDriver);
        commonFunctions = new CommonFunctions();
    }


    @FindBy(xpath = "(//table/tbody/tr/td[@role='columnheader']/parent::tr)[1]/td/div[contains(@class,'text-content')]")
    private List<WebElement> tableHeaderItems;

    @FindAll
    ({
            @FindBy(xpath = "//input[contains(@placeholder,'Search')]"),
            @FindBy(css = "[placeholder=\"Search\"]")
    })
    private WebElement searchBox;

    @FindBy(css = "span.mat-option-text")
    private WebElement optionValueInDropdown;

    @FindBy(css = "[placeholder='Amount']")
    private WebElement amountInputBox;

    @FindBy(css = "[role='row']:not([class*='header']) > td")
    private List<WebElement> cashBookList;

    @FindBy(xpath = "//span[normalize-space()='PRINT']")
    private WebElement printButton;

    @FindBy(xpath = "//td[@role='columnheader']")
    private List<WebElement> visitorColumnData;

    @FindBy(xpath = "//mat-radio-button[@value='cashOut']")
    private WebElement cashOutRadioButton;

    @FindBy(xpath = "//mat-radio-button/descendant::input[@value='transferOut']")
    private WebElement transferOutRadioButton;

    @FindBy(xpath = "//mat-radio-button[@value='cashIn']")
    private WebElement cashInRadioButton;

    @FindBy(xpath = "(//td[@aria-colindex='8']/div/div/div)[1]")
    private WebElement transactionType;

    @FindBy(xpath = "//a[@id='cashbook-cashier-session-list-add-item-button']")
    private WebElement addButton;

    @FindBy(xpath = "//mat-icon[contains(text(),'clear')]")
    private WebElement closeIcon;

    @FindBy(xpath = "//mat-option/span")
    private List<WebElement> availableFilters;

    @FindBy(xpath = "(//icon[@icon='edit'])[1]")
    private WebElement firstEditButton;

    @FindBy(css = "[placeholder='Comments']")
    private WebElement comments;

    @FindBy(xpath = "//strong[contains(text(),'Void Id')]")
    private WebElement voidIdText;

    @FindBy(xpath = "(//div[@class='text-truncate'])[1]")
    private WebElement currentBalanceText;

    @FindBy(xpath = "//div[contains(text(),'Cashbook Transactions')]")
    private WebElement cashBookTransactionsTab;

    @FindBy(xpath = "(//a[@id='cashbook-cashier-session-list--cashbook-code-link'])[1]")
    private WebElement openCashier;

    @FindBy(xpath = "//button[@id='complex-filter-label-code-open-button']/div")
    private WebElement codeFilter;

    @FindBy(css = "[placeholder='Code']")
    private WebElement codeSearch;

    @FindBy(css = "[id='complex-filter-add-more-button']")
    private WebElement moreFiltersIcon;

    @FindBy(css = "[id='complex-filter-search-add-more-filters']")
    private WebElement filterSearchInputBox;

    @FindBy(css = "#breadcrumbs-title")
    private WebElement breadCrumb;

    @FindBy(css = "[placeholder='Code']")
    private WebElement codeType;

    @FindBy(css = "[placeholder='Description (EN)']")
    private WebElement description;

    @FindBy(css = "[id='void-return-reason-code-list-add-new-mat-icon']")
    private WebElement addCashBook;

    @FindBy(xpath = "//otalio-current-open-cashbook-session/div")
    private  WebElement openCashierText;

    @FindBy(xpath = "//otalio-current-open-cashbook-session/div/div[contains(text(),'Cashbook')]")
    private WebElement cashbookName;

    public void enterCodeAndDescription(String first,String last){
        seleniumActions.typeValue(codeType,first);
        seleniumActions.typeValue(description,last);
    }

    public int getRandomCodeNumber(){
        return commonFunctions.getRandomNumber(10,99);
    }

    public void clickONAddCashbook(){
        seleniumWaits.untilElementIsVisible(addCashBook);
        seleniumActions.clickOnElement(addCashBook);
    }


    public String nameOfCashbook() {
        seleniumWaits.untilElementIsVisible(cashbookName);
        return seleniumActions.getTextMessage(cashbookName);
    }

    public String nameOfOpenCashierSessionCrew() {
        String text = "//otalio-current-open-cashbook-session/div";
        String name = seleniumActions.getTextMessage(
                webDriver.findElement(By.xpath(text))
        );

        String firstLine = name.split("\\n")[0];

        System.out.println(firstLine);
        //seleniumWaits.untilElementIsVisible(openCashierText);
        return firstLine;
    }


    public void addFilter(String filterName) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        if (webDriver.findElements(By.xpath("//div[contains(text(),'" + filterName + "')]/following-sibling::span")).size() == 0) {
            seleniumActions.clickOnElement(moreFiltersIcon);
            if (webDriver.findElements(By.cssSelector("[id='complex-filter-search-add-more-filters']")).size() > 0) {
                System.out.println("Search box displayed");
            } else {
                seleniumActions.jsClick(moreFiltersIcon);
            }
            seleniumActions.typeValue(filterSearchInputBox, filterName);
            if (webDriver.findElement(By.xpath("//span[@class='mat-option-text']/span[text()='" + filterName + "']/../preceding-sibling::*")).getAttribute("class").contains("checked")) {
                System.out.println("filterName :: " + filterName);
            } else {
                seleniumActions.jsClick(webDriver.findElement(By.xpath("//span[@class='mat-option-text']/span[text()='" + filterName + "']/../preceding-sibling::*")));
            }
            seleniumWaits.untilElementIsVisible(breadCrumb);
            seleniumActions.moveToElementAndClickOnElement(breadCrumb, breadCrumb);
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void searchCashBook(String open) {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(codeFilter);
        seleniumActions.typeValue(codeSearch, open);
//        String code = "//span[normalize-space()='" + open + "']";
//        seleniumActions.clickElement(webDriver.findElement(By.xpath(String.format(code))));
//        seleniumActions.clickOnElement(codeFilter);

    }

    public void clickOnFinanceManagement() {
        seleniumActions.clickOnElement(By.xpath("//a[contains(text(), 'Financial Management')]"));
        seleniumActions.sleep(3000);
    }

    public String nameOfOpenCashierSession() {
        seleniumWaits.untilElementIsVisible(openCashier);
        return seleniumActions.getTextMessage(openCashier);
    }

    public void clickOnCashBookTransactionsTab() {
        seleniumWaits.untilElementIsClickable(cashBookTransactionsTab);
        seleniumActions.clickOnElement(cashBookTransactionsTab);
    }

    public String getCurrentBalanceText() {
        seleniumWaits.untilElementIsVisible(currentBalanceText);
        return seleniumActions.getTextMessage(currentBalanceText);
    }

    public String getVoidIdText() {
        seleniumWaits.untilElementIsVisible(voidIdText);
        return seleniumActions.getTextMessage(voidIdText);
    }

    public void enterComments(String text) {
        seleniumWaits.untilElementIsVisible(comments);
        seleniumActions.typeValue(comments, text);
    }

    public void clickOnEdit() {
        seleniumWaits.untilElementIsVisible(firstEditButton);
        seleniumActions.clickOnElement(firstEditButton);
    }

    public List<String> getFilterListOfAll() {
        return availableFilters.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void clickOnClose() {
        seleniumWaits.untilElementIsVisible(closeIcon);
        seleniumActions.clickOnElement(closeIcon);
    }

    public void selectSessionDropdown(String dropdown) {
        String dropDownName = "[formcontrolname='sessionName']";
        seleniumActions.jsClick(webDriver.findElement(By.cssSelector(dropDownName)));
        String selectEmbarkationPortDropDownText = "(//span[contains(@id,'cashbook-cashier-session-editor')])[1]";
        try {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectEmbarkationPortDropDownText)));

        } catch (StaleElementReferenceException ste) {
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath(selectEmbarkationPortDropDownText)));
        }
    }

    public void enableToggle() {
        String toggleType = "//input[contains(@id,'cashbook-cashier-session-editor-is-open')]";
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath(String.format(toggleType))));
        System.out.println(webDriver.findElement(By.xpath(String.format(toggleType))).getAttribute("aria-checked"));
        WebElement toggle = webDriver.findElement(By.xpath(String.format(toggleType)));
        if (toggle.getAttribute("aria-checked").equals("false")) {
            seleniumActions.clickOnElement(toggle);
        }
    }

    public void clickOnAddButton() {
        seleniumActions.clickOnElement(addButton);
    }

    public String getTransactionTypeText() {
        seleniumWaits.untilElementIsVisible(transactionType);
        return seleniumActions.getTextMessage(transactionType);
    }

    public void clickOnTransferOut() {
        seleniumActions.sleep(3000);
        seleniumActions.jsClick(transferOutRadioButton);
    }

    public void clickOnCashOut() {
        seleniumWaits.untilElementIsClickable(cashOutRadioButton);
        seleniumActions.clickOnElement(cashOutRadioButton);
    }

    public void clickOnCashIn() {
        seleniumWaits.untilElementIsClickable(cashInRadioButton);
        seleniumActions.clickOnElement(cashInRadioButton);
    }

    public List<String> geVisitorColumnHeaders() {
        List<String> list = seleniumActions.getListOfElementsText(visitorColumnData);
        seleniumActions.sleep(3000);
        List<String> actualList = new ArrayList<>();
        for (int i = 1; i < list.size() - 1; i++) {
            actualList.add(list.get(i));
        }
        return actualList;
    }

    public void printCashBook() {

        webDriver.manage().window().maximize();
        seleniumActions.sleep(3000);
        try {
            seleniumWaits.untilElementIsClickable(cashBookList.get(0));
        } catch (Exception e) {

        }
        try {
            seleniumActions.clickOnElement(cashBookList.get(0));
        } catch (StaleElementReferenceException ste) {
            By ele = By.cssSelector("[role='row']:not([class*='header']) > td");
            seleniumActions.clickOnElement(ele);
        }
        seleniumWaits.untilElementIsClickable(printButton);
        seleniumActions.clickOnElement(printButton);
    }

    public void openCashBookRecord() {

        webDriver.manage().window().maximize();
        seleniumActions.sleep(3000);
        try {
            seleniumWaits.untilElementIsClickable(cashBookList.get(0));
        } catch (Exception e) {

        }
        try {
            seleniumActions.clickOnElement(cashBookList.get(0));
        } catch (StaleElementReferenceException ste) {
            By ele = By.cssSelector("[role='row']:not([class*='header']) > td");
            seleniumActions.clickOnElement(ele);
        }
    }

    public void enterAmount(String amount) {
        seleniumActions.clearValue(amountInputBox);
        seleniumActions.typeValue(amountInputBox, amount);
    }

    public void selectFromDropdown(String dropdown, String value) {
        seleniumActions.clickOnElement(By.cssSelector("[placeholder='" + dropdown + "']"));
        seleniumWaits.untilElementIsClickable(searchBox);
        seleniumActions.typeValue(searchBox, value);
        seleniumActions.sleep(3000); // for searching value  in dropdown
        seleniumWaits.untilElementIsClickable(optionValueInDropdown);
        seleniumActions.clickOnElement(optionValueInDropdown);
        seleniumActions.sleep(3000);
    }

    public void selectCashBookFromDropdown(String dropdown, String value) {
        seleniumActions.clickOnElement(By.cssSelector("[placeholder='" + dropdown + "']"));
        seleniumWaits.untilElementIsClickable(searchBox);
        seleniumActions.typeValue(searchBox, value);
        seleniumActions.sleep(3000); // for searching value  in dropdown
        seleniumWaits.untilElementIsClickable(optionValueInDropdown);
        seleniumActions.clickOnElement(optionValueInDropdown);
        seleniumActions.sleep(3000);
    }

    public String selectValueFromDropdown(String dropdown, String value) {
        seleniumActions.clickOnElement(By.cssSelector("[placeholder='" + dropdown + "']"));
        seleniumWaits.untilElementIsClickable(searchBox);
        seleniumActions.typeValue(searchBox, value);
        seleniumActions.sleep(3000); // for searching value  in dropdown
        seleniumWaits.untilElementIsClickable(optionValueInDropdown);
        seleniumActions.clickOnElement(optionValueInDropdown);
        seleniumActions.sleep(3000);
        return seleniumActions.getTextMessage(webDriver.findElement(By.xpath("//mat-select[@id='otalio-infinite-select-transactionTypeId-field']")));
    }
    public void selectFromDropdown(String dropdown) {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//otalio-infinite-select[@placeholder='Transaction Code']")));
        //seleniumWaits.untilElementIsClickable(searchBox);
        //seleniumActions.typeValue(searchBox, value);
        seleniumActions.sleep(3000); // for searching value  in dropdown
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transactionCodeId')]/span)[1]")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transactionCodeId')]/span)[1]")));
        seleniumActions.sleep(3000);
    }

    public void selectCashbookFromDropdown(String dropdown) {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(webDriver.findElement(By.cssSelector("[placeholder='" + dropdown + "']")));
        //seleniumWaits.untilElementIsClickable(searchBox);
        //seleniumActions.typeValue(searchBox, value);
        seleniumActions.sleep(3000); // for searching value  in dropdown
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.cssSelector("[id='cashbook-editor-type-option-CHECK-IN']")));
        seleniumActions.clickOnElement(webDriver.findElement(By.cssSelector("[id='cashbook-editor-type-option-CHECK-IN']")));
        seleniumActions.sleep(3000);
    }

    public String getSecondOpenSession(){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("(//a[@id='cashbook-cashier-session-list--cashbook-code-link'])[2]")));
        String second = seleniumActions.getTextMessage(webDriver.findElement(By.xpath("(//a[@id='cashbook-cashier-session-list--cashbook-code-link'])[2]")));

        WebElement firstElement = webDriver.findElement(By.xpath("(//a[@id='cashbook-cashier-session-list--cashbook-code-link'])[2]"));
        boolean isDisabled = firstElement.getAttribute("class").contains("disabled");

        if (isDisabled) {
            for (int i = 1; i < 10; i++) {
                int index = i + 2;
                try {
                    WebElement option = webDriver.findElement(By.xpath("(//a[@id='cashbook-cashier-session-list--cashbook-code-link'])[" + index + "]"));
                    seleniumWaits.untilElementIsClickable(option);
                    second = seleniumActions.getTextMessage(option);
                    break;
                } catch (Exception e) {
                    second = webDriver.findElement(By.xpath("(//a[@id='cahbook-cashier-session-list--cashbook-code-link'])[" + index + "]")).getText();
                    break;
                }
            }
        }
        return second;

    }

    public void clickOnToCashBook(String dropdown) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        seleniumActions.clickOnElement(By.cssSelector("[placeholder='" + dropdown + "']"));
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transferToCashbook-field')])[1]")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transferToCashbook-field')])[1]")));
        WebElement firstElement = webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transferToCashbook-field')])[1]"));
        boolean isDisabled = firstElement.getAttribute("class").contains("disabled");

        if (isDisabled) {
            for (int i = 1; i < 10; i++) {
                int index = i + 1;
                try {
                    WebElement option = webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transferToCashbook-field')])[" + index + "]"));
                    seleniumWaits.untilElementIsClickable(option);
                    seleniumActions.clickOnElement(option);
                } catch (Exception e) {
                    webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transferToCashbook-field')])[" + index + "]")).click();
                }
            }
        }
    }
        public void clickOnToRandomCashBook(String dropdown, int number){
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            seleniumActions.clickOnElement(By.cssSelector("[placeholder='" + dropdown + "']"));
            seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transferToCashbook-field')])[" + number + "]")));
            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transferToCashbook-field')])[" + number + "]")));
            WebElement firstCashBook = webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transferToCashbook-field')])[" + number + "]"));
            boolean disabled = firstCashBook.getAttribute("class").contains("disabled");

            if (disabled) {
                for (int i = 1; i < 10; i++) {
                    int index = i + 1;
                    try {
                        WebElement option = webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transferToCashbook-field')])[" + index + "]"));
                        seleniumWaits.untilElementIsClickable(option);
                        seleniumActions.clickOnElement(option);
                    } catch (Exception e) {
                        webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transferToCashbook-field')])[" + index + "]")).click();
                    }
                }
            }

//        for (int i = 1; i < 10; i++) {
//           // seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//otalio-infinite-select[@placeholder='Cashbook']")));
//            if (webDriver.findElements(
//                    By.xpath("(//mat-option[not(contains(@class,'disabled'))])[1]")).size()>0) {
//                int index = i + 1;
//                try {
//                    seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transferToCashbook-field')])[" + index + "]")));
//                    seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transferToCashbook-field')])[" + index + "]")));
//                } catch (Exception e) {
//                    webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-transferToCashbook-field')])[" + index + "]")).click();
//                }
//
//            }
//


        }


    public int getRandomNumber() {
        return commonFunctions.getRandomNumber(2, 9);
    }
    public void selectCashBookDropdown(String dropdown) {
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        seleniumWaits.untilElementIsClickable((webDriver.findElement(By.xpath("//otalio-infinite-select[@formcontrolname='cashbookId']"))));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//otalio-infinite-select[@formcontrolname='cashbookId']")));
        seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-cashbookId-field')]/span)[1]")));
        seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-cashbookId-field')]/span)[1]")));
//        for (int i = 1; i < 10; i++) {
//            seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//otalio-infinite-select[@formcontrolname='cashbookId']")));
//            if (webDriver.findElements(
//                    By.xpath("(//mat-option[not(contains(@class,'disabled'))])[1]")).size()>0) {
//                int index = i + 1;
//                try {
//                    seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-cashbookId-field')]/span)[" + index + "]")));
//                    seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-cashbookId-field')]/span)[" + index + "]")));
//                } catch (Exception e) {
//                    webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-cashbookId-field')]/span)[" + index + "]")).click();
//                }
//
//            }
//        }

//        for (int i = 0; i < 10; i++) {
//            if (webDriver.findElements(By.xpath("//div[contains(text(),'No crew member')]")).size() > 0) {
//
//                break;
//            } else {
//                seleniumWaits.untilElementIsClickable((webDriver.findElement(By.xpath("//otalio-infinite-select[@formcontrolname='cashbookId']"))));
//                seleniumActions.clickOnElement(webDriver.findElement(By.xpath("//otalio-infinite-select[@formcontrolname='cashbookId']")));
//                seleniumWaits.untilElementIsClickable(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-cashbookId-field')]/span)[" + i + "]")));
//                seleniumActions.clickOnElement(webDriver.findElement(By.xpath("(//mat-option[contains(@id,'otalio-infinite-select-cashbookId-field')]/span)[" + i + "]")));
//                seleniumActions.sleep(3000);
//            }
//        }
    }

//    public void openQuickLinkAndClickOnButton(String button) {
//        seleniumActions.clickOnElement(By.cssSelector("[title='" + button + "']"));
//    }

    public void clickOnCashBookOverView(String open) {
        seleniumActions.sleep(3000);
        seleniumWaits.untilElementIsPresent(By.xpath("(//a[contains(text(),'" + open + "')]/ancestor::td/following-sibling::td[@aria-colindex=\"14\"]/descendant::icon[@mattooltip='Cashbook Overview'])[1]"));
        seleniumActions.clickOnElement(By.xpath("(//a[contains(text(),'" + open + "')]/ancestor::td/following-sibling::td[@aria-colindex=\"14\"]/descendant::icon[@mattooltip='Cashbook Overview'])[1]"));
    }

    public String getDetails(String text) {
        WebElement details = webDriver.findElement(By.xpath("//strong[contains(text(),'" + text + "')]/following-sibling::div"));
        seleniumWaits.untilElementIsPresent(By.xpath("//strong[contains(text(),'" + text + "')]/following-sibling::div"));
        return details.getText();
    }

    public String getInvoiceNo(String text) {
        WebElement invoiceNo = webDriver.findElement((By.xpath("//strong[contains(text(),'" + text + "')]/following-sibling::span")));
        seleniumWaits.untilElementIsPresent(By.xpath("//strong[contains(text(),'" + text + "')]/following-sibling::span"));
        return invoiceNo.getText();
    }

    public String getUser(String text) {
        WebElement user = webDriver.findElement(By.xpath("//strong[contains(text(),'" + text + "')]/following-sibling::div/otalio-user-name/span"));
        seleniumWaits.untilElementIsPresent(By.xpath("//strong[contains(text(),'" + text + "')]/following-sibling::div/otalio-user-name/span"));
        return user.getText();
    }

    public void clickOnCashierSessionOverView() {
        seleniumWaits.untilElementIsPresent(By.xpath("(//button[@id='cashbook-cashier-session-list--edit-mat-button'])[1]"));
        seleniumActions.clickOnElement(By.xpath("(//button[@id='cashbook-cashier-session-list--edit-mat-button'])[1]"));
        seleniumActions.sleep(3000);
    }

    public void clickOnLink(String link) {
        seleniumActions.sleep(3000);
        seleniumActions.clickOnElement(By.xpath("//span[normalize-space()='" + link + "']"));
    }

    public int getIndexOfColumnName(String columnName) {
        List<String> columns = new ArrayList<>();
        for (int i = 1; i < tableHeaderItems.size(); i++) {
            try {
                seleniumActions.sleep(1000); //due to table header loading given 500 millisecs
                seleniumWaits.untilElementIsVisible(tableHeaderItems.get(1));
                columns.add(seleniumActions.getTextMessage(tableHeaderItems.get(i)));
            } catch (StaleElementReferenceException ste) {
                By tableHeader = By.xpath("(//table/tbody/tr/td[@role='columnheader']/parent::tr)[1]/td/div[contains(@class,'text-content')]");
                columns.add(seleniumActions.getTextMessage(webDriver.findElements(tableHeader).get(i)));
            }
        }

        int columnIndex = columns.indexOf(columnName);
        return columnIndex;
    }

    public boolean getDataMessage(String data) {
        String dataMessage = "//span[text()='%s']";
        return seleniumActions.isElementDisplayed(webDriver.findElement(By.xpath(String.format(dataMessage, data))));
    }

    public String getTableDataForGivenColumn(String columnName, String status) {
        int index = getIndexOfColumnName(columnName) + 1;
        WebElement cg = webDriver.findElement(By.xpath("//table/tbody/tr/td[@role='columnheader']/div[text()='" + columnName + "']/following::table/tbody/tr/td[normalize-space()='" + status + "']/div"));
        System.out.println(cg.getText());
        return cg.getText();
    }

}
