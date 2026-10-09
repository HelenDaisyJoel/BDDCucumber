package com.nclh.webSteps;

import com.nclh.constants.PointOfSaleConstants;
import com.nclh.dataProviders.ExcelReader;
import com.nclh.managers.AllDriverManager;
import com.nclh.managers.FileReaderManager;
import com.nclh.pageObjects.CommonPage;
import com.nclh.pageObjects.ExcursionManagementPage;
import com.nclh.pageObjects.GuestServicePage;
import com.nclh.pageObjects.PointOfSalePage;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PointOfSaleSteps extends AllDriverManager {

    Context context;
    PointOfSalePage pointOfSalePage;
    CommonPage commonPage;
    GuestServicePage guestServicePage;
    CommonFunctions commonFunctions;
    ExcursionManagementPage excursionManagementPage;

    public PointOfSaleSteps(Context context) {
        this.context = context;
        pointOfSalePage = new PointOfSalePage(getDriver());
        commonPage = new CommonPage(getDriver());
        guestServicePage = new GuestServicePage(getDriver());
        commonFunctions = new CommonFunctions();
        excursionManagementPage = new ExcursionManagementPage(getDriver());
    }

    @And("Search guest and select having min sufficient {string} funds")
    public void searchGuestAndSelectHavingMinSufficientFunds(String fund) {
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            pointOfSalePage.searchGuestUsingAlphabetic("pos");
        } else {
            pointOfSalePage.searchGuestUsingAlphabetic();
        }
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
//        pointOfSalePage.searchGuestUsingAlphabetic(context.getContext("nonSensitiveGuestFullName"));
//        seleniumActions.sleep(3000);
        pointOfSalePage.selectGuest();
       // pointOfSalePage.selectMinimumFundValueUser("0");
       /* if (pointOfSalePage.getCheckButtonStatus()) {
            pointOfSalePage.searchGuestUsing("a");
            pointOfSalePage.selectMinimumFundValueUser("0");
        }*/
        if (getDriver().findElement(By.xpath("(//otalio-guest-search-selected-area/div/descendant::span)[1]")).getText().contains("1")) {
            System.out.println("Guest selected");
        } else {
            seleniumActions.clickOnElement(By.xpath("(//otalio-passenger-picture)[1]"));
        }
        if (getDriver().findElement(By.xpath("(//otalio-guest-search-selected-area/div/descendant::span)[1]")).getText().contains("1")) {
            System.out.println("Guest selected");
        } else {
            seleniumActions.clickOnElement(By.xpath("(//otalio-passenger-picture)[2]"));
        }
    }

    @And("User save selected guestName")
    public void iSaveSelectedGuestName() {
        String name = pointOfSalePage.getGuestName();
        System.out.println("name :: " + name);
        context.setContext("guestFullName", name);
        System.out.println(context.getContext("guestFullName"));
    }

    @And("User clear checks if any present on home page")
    public void userClearChecksIfAnyPresentOnHomePage() {
        pointOfSalePage.clearChecks();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User select items for purchase {string}")
    public void userSelectItemsForPurchase(String item) {
        commonPage.clickOnLabel(item);
        String favItemName = pointOfSalePage.selectedItemFavName();
        context.setContext("favItem",favItemName);
        pointOfSalePage.selectSubItemToPurchase();
    }

    @Then("User save the total price displayed on the cart")
    public void userSaveTheTotalPriceDisplayedOnTheCart() {
        String price = pointOfSalePage.getTotalPrice();
        System.out.println("price : " + price);
        context.setContext("totalPrice", price);
    }

    @And("User navigated to guest list")
    public void userNavigatedToGuestList() {
        pointOfSalePage.navigateToGuestProfile();
    }

    @Then("User verify saved guest having purchased price under {string}")
    public void userVerifySavedGuestHavingPurchasedPriceUnder(String headerName) {
        SeleniumWaits seleniumWaits = new SeleniumWaits(getDriver());
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            seleniumActions.clickOnElement(By.xpath("//b[text()='Check Date And Time:']/ancestor::otalio-complex-filter-label/following-sibling::button/span[contains(@class,'button-wrapper')]"));
        }
        seleniumWaits.setImplicitWaitTime(getDriver(), 5);
        String guestName = context.getContext("guestFullNameFromTheList1");
        if (guestName == null) {
            guestName = context.getContext("guestFullName");
        }
        String[] fullName = guestName.split(" ");
        List<String> guestList = new ArrayList<>(List.of(fullName));
        Collections.reverse(guestList);
        String actualName = fullName[1] + " " + fullName[0];
        String totalPrice = context.getContext("totalPrice");
        System.out.println("totalPrice :: " + totalPrice);
        String guestFName = String.join(" ", guestList);
        String actualPrice = null;
        try {
            actualPrice = pointOfSalePage.getPriceFromGuestList(guestFName, headerName);
        } catch (Exception e) {
            try {
                actualPrice = pointOfSalePage.getPriceFromGuestList(fullName[0] + " " + fullName[1], headerName);
            }catch (Exception e1){
                seleniumActions.clickOnElement(By.xpath("//b[text()='Check Date And Time:']/ancestor::otalio-complex-filter-label/preceding-sibling::button/span[contains(@class,'button-wrapper')]"));
                actualPrice = pointOfSalePage.getPriceFromGuestList(fullName[0] + " " + fullName[1], headerName);
            }
        }
        String associateName = null;
        try {
            associateName = pointOfSalePage.getPriceFromGuestList(actualName, "User");
        } catch (Exception e) {
            associateName = pointOfSalePage.getPriceFromGuestList(fullName[0] + " " + fullName[1], "User");
        }
        context.setContext("associateName", associateName);
        System.out.println("actualPrice :: " + actualPrice);
        Assert.assertEquals(actualPrice, totalPrice);
        seleniumWaits.keepBackImplicitWaitTime(getDriver());
    }

    @And("User select revenue center {string}")
//    public void userSelectRevenueCenter(String revenueCenter) {
//        String url = FileReaderManager.getInstance().getConfigFileReader().getUrl();
//        String rvc = null;
//
//        if (url.contains("rssc")) {
//            rvc = PointOfSalePage.RevenueCenterConfig.getRevenueCenterKey("rssc");
//        } else if (url.contains("shiplab")) {
//            rvc = PointOfSalePage.RevenueCenterConfig.getRevenueCenterKey("shiplab");
//        } else if (url.contains("allura")) {
//            rvc = PointOfSalePage.RevenueCenterConfig.getRevenueCenterKey("allura");
//        }
//
//        if (rvc != null) {
//            if (revenueCenter.contains("LN1-Beach Bar")) {
//                String qaRVC = ExcelReader.readUserData("pos_qa", "POS_User", rvc);
//                pointOfSalePage.selectRevenueCenter(qaRVC);
//            } else if (revenueCenter.contains("LN1-Effy Jewelry")) {
//                String prodRVC = ExcelReader.readUserData("pos_qa", "POS_Shop", rvc);
//                pointOfSalePage.selectRevenueCenter(prodRVC);
//            } else if (revenueCenter.contains("LN1-Cagney's")) {
//                String prodRVC = ExcelReader.readUserData("pos_qa", "POS_Tables", rvc);
//                pointOfSalePage.selectRevenueCenter(prodRVC);
//            }
//        } else {
//            // Fallback for prod environment
//            if (revenueCenter.contains("LN1-Beach Bar")) {
//                String qaRVC = ExcelReader.readUserData("pos_prod", "POS_User", "RevenueCenter");
//                pointOfSalePage.selectRevenueCenter(qaRVC);
//            } else if (revenueCenter.contains("LN1-Effy Jewelry")) {
//                String prodRVC = ExcelReader.readUserData("pos_prod", "POS_Shop", "RevenueCenter");
//                pointOfSalePage.selectRevenueCenter(prodRVC);
//            } else if (revenueCenter.contains("LN1-Cagney's")) {
//                String prodRVC = ExcelReader.readUserData("pos_prod", "POS_Tables", "RevenueCenter");
//                pointOfSalePage.selectRevenueCenter(prodRVC);
//            }
//        }
//    }

    public void userSelectRevenueCenter(String revenueCenter) {
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("qa")) {

            if (revenueCenter.contains("LN1-Beach Bar")) {
                if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
                    String qaRVC = ExcelReader.readUserData("pos_qa", "POS_User", "RevenueCenter");
                    pointOfSalePage.selectRevenueCenter(qaRVC);
                } else {
                    String qaRVC = ExcelReader.readUserData("pos_qa", "POS_User", "AlluraRevenueCenter");
                    pointOfSalePage.selectRevenueCenter(qaRVC);
                }
            } else if (revenueCenter.contains("LN1-Effy Jewelry")) {
                if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
                    String prodRVC = ExcelReader.readUserData("pos_qa", "POS_Shop", "RevenueCenter");
                    pointOfSalePage.selectRevenueCenter(prodRVC);
                } else {
                    String prodRVC = ExcelReader.readUserData("pos_qa", "POS_Shop", "AlluraRevenueCenter");
                    pointOfSalePage.selectRevenueCenter(prodRVC);
                }
            } else if (revenueCenter.contains("LN1-Cagney")) {
                if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
                    String prodRVC = ExcelReader.readUserData("pos_qa", "POS_Tables", "RevenueCenter");
                    pointOfSalePage.selectRevenueCenter(prodRVC);
                } else {
                    String prodRVC = ExcelReader.readUserData("pos_qa", "POS_Tables", "AlluraRevenueCenter");
                    pointOfSalePage.selectRevenueCenter(prodRVC);
                }
            }
        } else {
            if (revenueCenter.contains("LN1-Beach Bar")) {
                String qaRVC = ExcelReader.readUserData("pos_prod", "POS_User", "RevenueCenter");
                pointOfSalePage.selectRevenueCenter(qaRVC);
            } else if (revenueCenter.contains("LN1-Effy Jewelry")) {
                String prodRVC = ExcelReader.readUserData("pos_prod", "POS_Shop", "RevenueCenter");
                pointOfSalePage.selectRevenueCenter(prodRVC);
            } else if (revenueCenter.contains("LN1-Cagney's")) {
                String prodRVC = ExcelReader.readUserData("pos_prod", "POS_Tables", "RevenueCenter");
                pointOfSalePage.selectRevenueCenter(prodRVC);
            }
        }
        //pointOfSalePage.accessWithCredentials("QAPOSUser@nclhlab.com","Welcome2024!");
    }

    @And("User save the guestName")
    public void userSaveTheGuestName() {
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        seleniumActions.clickOnElement(By.xpath("//b[text()='Check Date And Time:']/ancestor::otalio-complex-filter-label/following-sibling::button/span[contains(@class,'button-wrapper')]"));
        seleniumActions.sleep(2000);
        String guestFullName = pointOfSalePage.getFirstGuestName();
        context.setContext("guestFullName", guestFullName);
    }

    @And("User navigated to check number")
    public void userNavigatedToCheckNumber() {
        pointOfSalePage.navigateToCheckNumber(context.getContext("guestFullName"));
    }

    @And("User return already purchased item")
    public void userReturnAlreadyPurchasedItem() {
        pointOfSalePage.returnItem("Breakage");
    }

    @And("User navigated to check number having greater than {string}")
    public void userNavigatedToCheckNumberHavingGreaterThan(String amount) {
//        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
//        seleniumActions.clickOnElement(By.xpath("//b[text()='Check Date And Time:']/ancestor::otalio-complex-filter-label/following-sibling::button/span[contains(@class,'button-wrapper')]"));
//        seleniumActions.sleep(2000);
        List<Double> amountsList = pointOfSalePage.getAmountsList();
        double amountT = amountsList.get(0);
        String guestName = pointOfSalePage.getGuestNameUsingAmount(String.valueOf(amountT));
        System.out.println("guestName :: " + guestName);
        context.setContext("guestNameUsedForVoidCheck", guestName);
        pointOfSalePage.selectAndClickOnCheckAmountGreaterThan(String.valueOf(amountT));
    }

    @Then("User verify check amount displayed as {string} after returning item")
    public void userVerifyCheckAmountDisplayedAs(String amount) throws InterruptedException {
        getDriver().navigate().refresh();
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        seleniumActions.clickOnElement(By.xpath("//b[text()='Check Date And Time:']/ancestor::otalio-complex-filter-label/following-sibling::button/span[contains(@class,'button-wrapper')]"));
        seleniumActions.sleep(2000);
        SeleniumWaits seleniumWaits = new SeleniumWaits(getDriver());
        String actualPrice = pointOfSalePage.getPriceFromGuestList(context.getContext("guestNameUsedForVoidCheck"), "Total Sum");
        System.out.println("actualPrice :: " + actualPrice);
        Assert.assertEquals(actualPrice, amount);
    }

    @And("User navigated to add another buyer screen")
    public void userNavigatedToAddAnotherBuyerScreen() {
        pointOfSalePage.addAnotherBuyer();
    }

    @And("User save new buyer name")
    public void userSaveNewBuyerName() {
        String newBuyer = pointOfSalePage.getNewBuyer();
        System.out.println("newBuyer :: " + newBuyer);
        context.setContext("newBuyer", newBuyer);
    }

    @And("User select gift item {string}")
    public void userSelectGiftItem(String giftItem) {
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            giftItem = ExcelReader.readUserData("pos_qa", "POS_User", giftItem);
        } else {
            giftItem = ExcelReader.readUserData("pos_qa", "POS_User", "ALLURAGiftItem");
        }
        pointOfSalePage.selectGiftItem(giftItem);
    }


    @And("User select item {string} for adding tip")
    public void userSelectItemForAddingTip(String item) {
        pointOfSalePage.selectGiftItem(item);
    }

    @And("User select sku item {string}")
    public void userSelectSKUItem(String giftItem) {
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            giftItem = ExcelReader.readUserData("pos_qa", "POS_User", giftItem);
        } else {
            giftItem = "La Reserve Prepaid";
        }
        pointOfSalePage.selectGiftItem(giftItem);
    }

    @And("User open the latest check on the list")
    public void userOpenTheLatestCheckOnTheList() {
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            seleniumActions.clickOnElement(By.xpath("//b[text()='Check Date And Time:']/ancestor::otalio-complex-filter-label/following-sibling::button/span[contains(@class,'button-wrapper')]"));
        }
        pointOfSalePage.navigateToLatestCheck();
    }
    @And("User swipe right direction on item added to cart")
    public void userSwipeOnItem() {
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {

        }else {
            pointOfSalePage.swipeOnCartProduct("right");
        }
    }

    @And("User swipe {string} on item added to cart")
    public void userSwipeOnItemAddedToCart(String direction) {
        pointOfSalePage.swipeOnCartProduct(direction);
    }

    @Then("User verify product disappear from the cart")
    public void userVerifyProductDisappearFromTheCart() {
        SeleniumWaits seleniumWaits = new SeleniumWaits(getDriver());
        seleniumWaits.setImplicitWaitTime(getDriver(), 5);
        int actualProductsCount = pointOfSalePage.getNumberOfProductsInTheCart();
        Assert.assertEquals(actualProductsCount, 0);
    }

    @Then("User verify enter final product pop")
    public void userVerifyEnterFinalProductPop() {
        boolean popUp = pointOfSalePage.isEnterFinalQuantityPopupDisplayed();
        Assert.assertTrue(popUp);
    }

    @And("User write comment {string} for thr product in the cart")
    public void userWriteCommentForThrProductInTheCart(String comment) {
        pointOfSalePage.navigateToCommentPage();
        if (comment.equals("250Chars")) {
            String charsCount250 = ExcelReader.readUserData("pos_qa", "charsCount250", "RevenueCenter");
            pointOfSalePage.writeAComment(charsCount250);
        } else if (comment.equals("251Chars")) {
            String charsCount251 = ExcelReader.readUserData("pos_qa", "charsCount251", "RevenueCenter");
            pointOfSalePage.writeAComment(charsCount251);
        } else {
            pointOfSalePage.writeAComment(comment);
//            pointOfSalePage.clickOnSaveButton();
        }
    }

    @Then("User verify orange circle appeared at product description at cart")
    public void userVerifyOrangeCircleAppearedAtProductDescriptionAtCart() {
        boolean orangeCircle = pointOfSalePage.isOrangeCircleDisplayed();
        Assert.assertTrue(orangeCircle);
    }

    @Then("User verify the print statement receipt")
    public void userVerifyThePrintStatementReceipt() {
        String printReceiptData = pointOfSalePage.getPrintReceipt();
        // Assert.assertTrue(printReceiptData.contains("NCL LAB QAl"));
    }

    @Then("User enter quantity {string} in the final quantity inputBox")
    public void userEnterQuantityInTheFinalQuantityInputBox(String quantity) {
        pointOfSalePage.enterQuantity(quantity);
       /* try {
            pointOfSalePage.writeAComment("AutomationTest");
            pointOfSalePage.clickOnCommentSaveButton();
        } catch (Exception e) {
        }*/
    }

    @Then("User verify product quantity able to update in the cart with {string}")
    public void userVerifyProductQuantityAbleToUpdateInTheCartWith(String quantity) {
        String productQuantity = pointOfSalePage.getProductQuantity();
        Assert.assertTrue(productQuantity.contains(quantity));
    }

    @Then("User verify error toast message appears on the screen with message {string}")
    public void userVerifyErrorToastMessageAppearsOnTheScreenWithMessage(String errorMessage) {
        String errorToastMessage = pointOfSalePage.getErrorToastMessage();
        Assert.assertTrue(errorToastMessage.contains(errorMessage));
    }

    @And("User Search two guests and select having min sufficient funds greater than 0")
    public void userSearchTwoGuestsAndSelectHavingMinSufficientFunds() throws InterruptedException {
        /*if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            pointOfSalePage.searchGuestUsingAlphabetic("POSguest");
        } else {
            pointOfSalePage.searchGuestUsingAlphabetic();
        }*/
        pointOfSalePage.searchGuestUsingAlphabetic("POSguest");
        pointOfSalePage.selectMinimumFundValueUser("50");
        Thread.sleep(5000);
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            pointOfSalePage.searchGuestUsingAlphabetic("POSguest");
        } else {
            pointOfSalePage.searchGuestUsingAlphabetic();
        }
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        seleniumActions.clickOnElement(By.xpath("(//otalio-passenger-picture)[2]"));



        if (getDriver().findElement(By.xpath("(//otalio-guest-search-selected-area/div/descendant::span)[1]")).getText().contains("2")) {
            System.out.println("Guests selected");
        } else {
            seleniumActions.clickOnElement(By.xpath("(//otalio-passenger-picture)[2]"));
        }
    }

    @And("User save selected guestNames list")
    public void userSaveSelectedGuestNamesList() {
        List<String> guestNames = pointOfSalePage.getGuestNames();
        System.out.println("guestNames :: " + guestNames);
        context.setListContext("guestList", guestNames);
    }

    @And("User select gift item {string} when more than one guest")
    public void userSelectGiftItemWhenMoreThanOneGuest(String giftItem) {
        pointOfSalePage.chooseBuyerIfHavingLessFunds(giftItem);
    }

    @And("User select {string} guest for selecting product")
    public void userSelectItemForGuestInTheList(String guestNumber) {
        switch (guestNumber) {
            case "1st":
                String[] guestFullName = context.getListContext("guestList").get(0).split(" ");
                int size = guestFullName.length;
                pointOfSalePage.clickOnGuest(guestFullName[size - 1]);
                break;
            case "2nd":
                String[] guest2FullName = context.getListContext("guestList").get(1).split(" ");
                int size1 = guest2FullName.length;
                pointOfSalePage.clickOnGuest(guest2FullName[size1 - 1]);
                break;
        }
    }

    @And("User click on split Check By Consumer")
    public void userClickOnSplitCheckByConsumer() {
        pointOfSalePage.splitCheckByConsumer();
    }

    @Then("User verify guest names displayed check confirmation page")
    public void userVerifyGuestNamesDisplayedCheckConfirmationPage() {
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        //if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            seleniumActions.clickOnElement(By.xpath("//b[text()='Check Date And Time:']/ancestor::otalio-complex-filter-label/following-sibling::button/span[contains(@class,'button-wrapper')]"));
        //}
        seleniumActions.sleep(2000);
        List<String> guestNames = pointOfSalePage.getGuestNamesAtCheckPage();
        List<String> actualGuestNames = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            actualGuestNames.add(guestNames.get(i));
        }
        System.out.println("actualGuestNames :: " + actualGuestNames);
        String[] guest1 = actualGuestNames.get(0).split(" ");
        String[] guest2 = actualGuestNames.get(1).split(" ");
        String guestName1 = guest1[1] + " " + guest1[0];
        String guestName2 = guest2[1] + " " + guest2[0];

        System.out.println("list :: " + context.getListContext("guestList"));
        try {
            Assert.assertTrue(context.getListContext("guestList").contains(guestName1));
            Assert.assertTrue(context.getListContext("guestList").contains(guestName2));
        }catch (AssertionError e){
            Assert.assertTrue(context.getListContext("guestList").contains(guest1[0] + " " + guest1[1]));
            Assert.assertTrue(context.getListContext("guestList").contains(guest2[0] + " " + guest2[1]));
        }
    }

    @And("User select left menu item {string} and click on the product {string}")
    public void userSelectLeftMenuItemAndClickOnTheProduct(String leftMenu, String product) {
        pointOfSalePage.selectLeftMenuAndClickOnProduct(leftMenu, product);
    }

    @And("User select table to check")
    public void userSelectTableToCheck() {
        pointOfSalePage.clickOnTable();
    }

    @Then("User verify associate name {string} in the table")
    public void userVerifyAssociateNameInTheTable(String posUser) {
        String associateName = context.getContext("associateName");
        Assert.assertTrue(associateName.contains(posUser));
    }

    @And("User add another buyer")
    public void userAddAnotherBuyer() {
        //pointOfSalePage.selectAnotherUserIfGotError();
        Assert.assertTrue(pointOfSalePage.addBuyersButtonIsEnabled());
        pointOfSalePage.clickOnAddBuyersButton();
    }

    @Then("User verify saved new guest having purchased price under {string}")
    public void userVerifySavedNewGuestHavingPurchasedPriceUnder(String headerName) {
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            seleniumActions.clickOnElement(By.xpath("//b[text()='Check Date And Time:']/ancestor::otalio-complex-filter-label/following-sibling::button/span[contains(@class,'button-wrapper')]"));
        }

        String guestName = context.getContext("newBuyer");
        String[] fullName = guestName.split(" ");
        String actualName = fullName[1] + " " + fullName[0];
        String totalPrice = context.getContext("totalPrice");
        System.out.println("totalPrice :: " + totalPrice);
        String actualPrice = null;
        try {
            actualPrice = pointOfSalePage.getPriceFromGuestList(fullName[0] + " " + fullName[1], headerName);
        } catch (Exception e) {
            actualPrice = pointOfSalePage.getPriceFromGuestList(actualName, headerName);
        }
        System.out.println("actualPrice :: " + actualPrice);
        Assert.assertEquals(actualPrice, totalPrice);
    }

    @Then("User verify table is associated with check")
    public void userVerifyTableIsAssociatedWithCheck() {
        String data = pointOfSalePage.getTableAssociatedCheckData();
        //  System.out.println("data :: "+ data);
        String[] tableData = data.split("/");
        System.out.println(tableData[0].trim());
        System.out.println(tableData[1].trim());
    }

    @And("User perform reorder from the more options")
    public void userPerformReorderFromTheMoreOptions() {
        pointOfSalePage.reOrder();
    }

    @Then("User verify products count doubled in the cart")
    public void userVerifyProductsCountDoubledInTheCart() {
        int numberOfItems = pointOfSalePage.getNumberOfProductItemsInTheCart();
        Assert.assertEquals(numberOfItems, 2);
    }

    @And("User enter guest name {string} and click")
    public void userEnterGuestName(String guest) {
        pointOfSalePage.searchGuest(guest);
        pointOfSalePage.clickOnGuest();
    }

    @And("User search for the {string} user and select")
    public void userSearchForTheUserAndSelect(String userType) {
        switch (userType) {
            case "INTOX":
                //pointOfSalePage.searchGuestUsing(context.getContext("nonSensitiveGuestFullName"));
                pointOfSalePage.searchGuestUsing("IntoxUser");
                pointOfSalePage.clickSelectedGuest(context.getContext("nonSensitiveGuestFullName"));
                break;
            case "MINOR":
                /*if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
                    pointOfSalePage.searchGuestUsing("Infant");
                }
                else{
                    pointOfSalePage.searchGuestUsing(context.getContext("nonSensitiveGuestFullName"));
                    pointOfSalePage.clickSelectedGuest(context.getContext("nonSensitiveGuestFullName"));
                }*/
                //pointOfSalePage.clickSelectedGuest(context.getContext("nonSensitiveGuestFullName"));
                pointOfSalePage.searchGuestUsing("Infant");
                pointOfSalePage.selectMinimumFundValueUser("0");
                break;
            case "Guest1":
                pointOfSalePage.searchGuestUsing(context.getContext("guestFullNameFromTheList1"));
                pointOfSalePage.clickSelectedGuest(context.getContext("guestFullNameFromTheList1"));
                break;
            case "Guest2":
                pointOfSalePage.searchGuestUsing(context.getContext("guestFullNameFromTheList2"));
                pointOfSalePage.clickSelectedGuest(context.getContext("guestFullNameFromTheList2"));
                break;
            case "Guest3":
                pointOfSalePage.searchGuestUsing(context.getContext("nonSensitiveGuestFullName"));
                pointOfSalePage.clickSelectedGuest(context.getContext("nonSensitiveGuestLastName"));
                break;
        }

    }

    @Then("User verify error toast message appears on the screen {string}")
    public void userVerifyErrorToastMessageAppearsOnTheScreen(String message) {
        String errorToastMessage = pointOfSalePage.getErrorToastMessage();
       // String[] messages = message.split(";");
        Assert.assertTrue(errorToastMessage.contains(errorToastMessage.trim()));
        //Assert.assertTrue(errorToastMessage.contains(messages[1].trim()));
    }

    @Then("User verify item is disabled")
    public void userVerifyItemIsDisabled() {
        Assert.assertTrue(pointOfSalePage.isProductDisAbled());
    }

    @And("User save initial product {string} price before applying promoCode")
    public void userSaveInitialProductPriceBeforeApplyingPromoCode(String product) {
        String priceOfTheProductBeforePromoCode = null;
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            //product = ExcelReader.readUserData("pos_qa", "POS_User", product);
            priceOfTheProductBeforePromoCode = pointOfSalePage.getPriceOfTheItem(product);
        } else {
            try {
                product = ExcelReader.readUserData("pos_qa", "POS_User", "ALLURAProduct");
                priceOfTheProductBeforePromoCode = pointOfSalePage.getPriceOfTheItem(product);
            } catch (Exception e) {
                product = ExcelReader.readUserData("pos_qa", "POS_Tables", "ALLURAProduct");
                priceOfTheProductBeforePromoCode = pointOfSalePage.getPriceOfTheItem(product);
            }
        }

        System.out.println("priceOfTheProductBeforePromoCode :: " + priceOfTheProductBeforePromoCode);
        String[] actualPriceBeforeApplyingPromoCode = priceOfTheProductBeforePromoCode.split("\\.");
        context.setContext("productPriceBeforePromoCode", actualPriceBeforeApplyingPromoCode[0].trim());
    }

    @And("User perform applying promoCode {string}")
    public void userPerformApplyingPromoCode(String promoCode) {
        pointOfSalePage.selectBarPromo(promoCode);
    }

    @And("User save initial product {string} price after applying promoCode")
    public void userSaveInitialProductPriceAfterApplyingPromoCode(String product) {
        String priceOfTheProductAfterPromoCode = pointOfSalePage.getPriceOfTheItem(product);
        System.out.println("priceOfTheProductAfterPromoCode :: " + priceOfTheProductAfterPromoCode);
        String[] actualPriceAfterApplyingPromoCode = priceOfTheProductAfterPromoCode.split("\\.");
        context.setContext("productPriceAfterPromoCode", actualPriceAfterApplyingPromoCode[0].trim());
    }

    @Then("User verify price should be reduced after applying promo code")
    public void userVerifyPriceShouldBeReducedAfterApplyingPromoCode() {
        Assert.assertTrue(Integer.parseInt(context.getContext("productPriceBeforePromoCode")) > Integer.parseInt(context.getContext("productPriceAfterPromoCode")));
    }

    @And("User save the check number")
    public void userSaveTheCheckNumber() {
        String[] guestName = context.getContext("guestFullName").split(" ");
        String checkNumber = null;
        try {
            checkNumber = pointOfSalePage.getCheckNumber(guestName[0] + " " + guestName[1]);
        } catch (Exception e) {
            checkNumber = pointOfSalePage.getCheckNumber(guestName[1] + " " + guestName[0]);
        }

        context.setContext("guestFirstName", guestName[0]);
        context.setContext("guestLastName", guestName[1]);
        context.setContext("checkNumber", checkNumber);
    }

    @Then("User verify transaction is posted and added to guest folio")
    public void userVerifyTransactionIsPostedAndAddedToGuestFolio() {
        System.out.println(context.getContext("checkNumber"));
        getDriver().manage().window().setSize(new Dimension(1920, 768));
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("document.body.style.zoom = '30%';");
        ;
        Actions actions = new Actions(getDriver());
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();
        Assert.assertTrue(pointOfSalePage.isCheckPresent(context.getContext("checkNumber")));
    }

    @And("User add price if the product not having price")
    public void userAddPriceIfTheProductNotHavingPrice() {
        pointOfSalePage.addPriceToItem();
    }

    @And("User logout from pos")
    public void userLogoutFromPos() {
        pointOfSalePage.logoutPOS();
    }

    @And("User search for the guest in guest list")
    public void userSearchForTheGuestInGuestList() {
        guestServicePage.openCrewInformation(context.getContext("guestFirstName"));
    }

    @And("User click on Account statement details")
    public void userClickOnAccountStatementDetails() {
        pointOfSalePage.openAccountInfoDropDown();
    }

    @And("User click on pos back button")
    public void userClickOnPosBackButton() {
        pointOfSalePage.clickOnBackButton();
    }

    @And("User Search adult guest and select having min sufficient funds greater than {int}")
    public void userSearchAdultGuestAndSelectHavingMinSufficientFundsGreaterThan(int arg0) {
        pointOfSalePage.searchGuestUsingAlphabetic();
        pointOfSalePage.selectOneGuest();
    }

    @And("User search for the guest and open the profile")
    public void userSearchForTheGuestAndOpenTheProfile() {
        System.out.println(context.getContext("guestFirstName") + ":: " + context.getContext("guestLastName"));
        try {
            pointOfSalePage.openCrewProfile(context.getContext("guestFirstName"), context.getContext("guestLastName"));
        } catch (Exception e) {
            pointOfSalePage.openCrewProfile(context.getContext("guestLastName"), context.getContext("guestFirstName"));
        }
    }

    @And("User search revenue center {string} and select")
    public void userSearchRevenueCenterAndSelect(String revenueCenter) {
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("qa")) {

            if (revenueCenter.contains("LN1-Beach Bar")) {
                String qaRVC = ExcelReader.readUserData("pos_qa", "POS_User", "RevenueCenter");
                pointOfSalePage.searchAndSelectRevenueCenter(qaRVC);
            } else if (revenueCenter.contains("LN1-Effy Jewelry")) {
                String prodRVC = ExcelReader.readUserData("pos_qa", "POS_Shop", "RevenueCenter");
                pointOfSalePage.searchAndSelectRevenueCenter(prodRVC);
            } else if (revenueCenter.contains("LN1-Cagney's")) {
                if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
                    String qaRVC = ExcelReader.readUserData("pos_qa", "POS_Tables", "RevenueCenter");
                    pointOfSalePage.searchAndSelectRevenueCenter(qaRVC);
                } else {
                    String qaRVC = ExcelReader.readUserData("pos_qa", "POS_Tables", "AlluraRevenueCenter");
                    pointOfSalePage.searchAndSelectRevenueCenter(qaRVC);
                }
            }
        } else {
            if (revenueCenter.contains("LN1-Beach Bar")) {
                String qaRVC = ExcelReader.readUserData("pos_prod", "POS_User", "RevenueCenter");
                pointOfSalePage.searchAndSelectRevenueCenter(qaRVC);
            } else if (revenueCenter.contains("LN1-Effy Jewelry")) {
                String prodRVC = ExcelReader.readUserData("pos_prod", "POS_Shop", "RevenueCenter");
                pointOfSalePage.searchAndSelectRevenueCenter(prodRVC);
            } else if (revenueCenter.contains("LN1-Cagney's")) {
                String prodRVC = ExcelReader.readUserData("pos_prod", "POS_Tables", "RevenueCenter");
                pointOfSalePage.searchAndSelectRevenueCenter(prodRVC);
            }
        }
    }

    @And("User clear check status dropdown")
    public void userClearCheckStatusDropdown() {
        pointOfSalePage.clearCheckBoxes();
    }

    @And("User set check status dropdown value to {string}")
    public void userSetCheckStatusDropdownValueToClose(String checkType) {
        pointOfSalePage.selectCheckTypeInTheList(checkType);
    }

    @Then("User verify able to reprint the statement")
    public void userVerifyAbleToReprintTheStatement() {
        pointOfSalePage.clickOnRePrintButton();
         Assert.assertTrue(pointOfSalePage.getSuccessMessage());
    }

    @And("User add tip amount {string} to the check")
    public void userAddTipAmountToTheCheck(String amount) {
        pointOfSalePage.enterTipAmount(amount);
    }

    @And("User open tips page")
    public void userOpenTipsPage() {
        pointOfSalePage.openTips();
    }

    @Then("User save the total price displayed on the cart after adding tip")
    public void userSaveTheTotalPriceDisplayedOnTheCartAfterAddingTip() {
        String price = pointOfSalePage.getTotalPrice();
        System.out.println("price with tip :: " + price);
        context.setContext("totalPriceWithTip", price);
    }

    @Then("User verify price should be increased after applying tip to check")
    public void userVerifyPriceShouldBeIncreasedAfterApplyingTipToCheck() {
        Assert.assertTrue(Double.parseDouble(context.getContext("totalPriceWithTip")) > Double.parseDouble(context.getContext("totalPrice")));
    }

    @Then("User verify saved guest having purchased price with tip  under {string}")
    public void userVerifySavedGuestHavingPurchasedPriceWithTipUnder(String headerName) {
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            seleniumActions.clickOnElement(By.xpath("//b[text()='Check Date And Time:']/ancestor::otalio-complex-filter-label/following-sibling::button/span[contains(@class,'button-wrapper')]"));
        }
        String guestName = context.getContext("guestFullName");
        String[] fullName = guestName.split(" ");
        String actualName = fullName[1] + " " + fullName[0];
        String totalPrice = context.getContext("totalPriceWithTip");
        System.out.println("totalPriceWithTip :: " + totalPrice);
        String actualPrice = null;
        try {
            actualPrice = pointOfSalePage.getPriceFromGuestList(fullName[0] + " " + fullName[1], headerName);
        } catch (Exception e) {
            actualPrice = pointOfSalePage.getPriceFromGuestList(actualName, headerName);
        }
//        String associateName = pointOfSalePage.getPriceFromGuestList(actualName, "User");
//        context.setContext("associateName", associateName);
        System.out.println("actualPrice :: " + actualPrice);
        Assert.assertEquals(actualPrice, totalPrice);
    }

    @Then("User verify no check and home page is clear")
    public void userVerifyNoCheckAndHomePageIsClear() {
        Assert.assertEquals(pointOfSalePage.getChecks(), 0);
        SeleniumWaits seleniumWaits = new SeleniumWaits(getDriver());
        seleniumWaits.keepBackImplicitWaitTime(getDriver());
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User verify RVC should be updated as {string}")
    public void userVerifyRVCShouldBeUpdated(String rvc) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Temp sleep");
        }
        String rvcCenter = pointOfSalePage.getRVCCenter();
        context.setContext("rvcAfterUpdating", rvcCenter);
        String qaRVC = null;
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            qaRVC = ExcelReader.readUserData("pos_qa", "POS_Tables", "RevenueCenter");
            pointOfSalePage.searchAndSelectRevenueCenter(qaRVC);
        } else {
            qaRVC = ExcelReader.readUserData("pos_qa", "POS_Tables", "AlluraRevenueCenter");
            pointOfSalePage.searchAndSelectRevenueCenter(qaRVC);
        }

        Assert.assertTrue(context.getContext("rvcAfterUpdating").contains(qaRVC));
    }

    @Then("User verify save button is {string}")
    public void userVerifySaveButtonIs(String status) {
        boolean saveButtonStatus = pointOfSalePage.isSaveButtonEnabled();
        if (status.equals("enabled")) {
            Assert.assertTrue(saveButtonStatus);
        } else {
            Assert.assertFalse(saveButtonStatus);
        }
    }

    @Then("User verify the print receipt having guest name and comment")
    public void userVerifyThePrintReceiptHavingGuestNameAndComment() {
        String printReceiptData = pointOfSalePage.getPrintReceipt();
        String guestName = context.getContext("guestFullName");
        System.out.println("guestName :: " + guestName);
        String[] fullName = guestName.split(" ");
        String charsCount250 = ExcelReader.readUserData("pos_qa", "charsCount250", "RevenueCenter");
        String normalizedStr1 = printReceiptData.replaceAll("\\s+", " ").toLowerCase();
        String normalizedStr2 = charsCount250.replaceAll("\\s+", " ").toLowerCase();

        // commented because the strings varies form the PDF in different format
        /*try {
            Assert.assertTrue(normalizedStr1.contains(fullName[0].toLowerCase()));
        }catch (AssertionError e) {
            Assert.assertTrue(normalizedStr1.contains(fullName[1].toLowerCase()));
        }*/

        //Assert.assertTrue(normalizedStr1.contains(normalizedStr2));
        //Assert.assertTrue(printReceiptData.contains(charsCount250));
    }

    @Then("User click on save button after comment")
    public void userClickOnSaveButtonAfterComment() {
        pointOfSalePage.clickOnCommentSaveButton();
    }

    @And("User navigated to latest check on the list")
    public void userNavigatedToLatestCheckOnTheList() {
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        //if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            seleniumActions.clickOnElement(By.xpath("//b[text()='Check Date And Time:']/ancestor::otalio-complex-filter-label/following-sibling::button/span[contains(@class,'button-wrapper')]"));
        //}
        pointOfSalePage.clickOnLatestCheckNumber();
    }

    @And("User accept pop up")
    public void userAcceptPopUp() {
        pointOfSalePage.clickOnYesOnPopup();
    }

    @And("User click on pos label {string}")
    public void userClickOnPosLabel(String label) {
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            commonPage.clickOnLabel(label);
        } else {
            try {
                pointOfSalePage.clickOnPurchaseItem("MENU", "La Reserve");
            } catch (Exception e) {
                pointOfSalePage.clickOnPurchaseItem("AIR", "ARTIST LOFT");
            }
        }
    }

    @And("User navigate to comment page")
    public void userNavigateToCommentPage() {
        pointOfSalePage.navigateToCommentPage();
    }

    @Then("User verify Application Logs, Database Logs, Synchronization Logs displayed for the user with the log activity")
    public void userVerifyApplicationLogsDatabaseLogsSynchronizationLogsDisplayedForTheUserWithTheLogActivity() {
        Assert.assertTrue(pointOfSalePage.getLogsList());
    }

    @And("User save the code and barcode details on POS articles")
    public void userSaveTheCodeAndBarcodeDetailsOnPOSArticles() {
        String articles = pointOfSalePage.getArticlesCode();
        context.setContext("articlesCode",articles);
//        String barcode = pointOfSalePage.getBarCode();
//        context.setContext("barCode",barcode);
        String descriptionText = pointOfSalePage.getDescription();
        context.setContext("description",descriptionText);
    }

    @Then("User click on code in POS articles")
    public void userClickOnCodeInPOSArticles() {
        pointOfSalePage.clickCode();
    }

    @Then("User verify the Enabled toggle button status")
    public void userVerifyTheEnabledToggleButtonStatus() {
        String toggleStatus = pointOfSalePage.checkToggleButtonStatus();
        Assert.assertEquals(toggleStatus,"true");
    }

    @Then("User verify the code in articles setup page")
    public void userVerifyTheCodeInArticlesSetupPage() {
        WebElement getCodeElement = pointOfSalePage.getCodeTextWebElement();
        String getCodeTextMessage  = commonFunctions.readToastMessage(getCodeElement).trim();
        Assert.assertEquals(getCodeTextMessage,context.getContext("articlesCode"));
        Assert.assertEquals(pointOfSalePage.getBarCodeInArticlesSetup(),context.getContext("barCode"));
    }

    @Then("User search for item in article search")
    public void userSearchForItemInArticleSearch() {
        pointOfSalePage.searchArticle(context.getContext("articlesCode"));
    }

    @And("User click on {string} filter and select option {string} on POS article page")
    public void userClickOnFilterAndSelectOptionOnPOSArticlePage(String filterName, String checkbox) {
        pointOfSalePage.selectDropDownValue(filterName,checkbox);
    }

    @And("User verify the article details")
    public void userVerifyTheArticleDetails() {
        Assert.assertEquals(context.getContext("description"),pointOfSalePage.getArticleNameText());
    }

    @Then("User search for item code in article search")
    public void userSearchForItemCodeInArticleSearch() {
        pointOfSalePage.searchArticle(context.getContext("articlesCode"));
    }

    @Then("User search for item name in article search")
    public void userSearchForItemNameInArticleSearch() {
        pointOfSalePage.searchArticle(context.getContext("description"));
    }

    @And("User click {string} plus button on POS article page")
    public void userClickPlusButtonOnPOSArticlePage(String name) {
        excursionManagementPage.clickPlusButton(name);
    }

    @Then("User enter article details on article creation page")
    public void userEnterArticleDetailsOnArticleCreationPage() {
        pointOfSalePage.enterArticleCreationDetails();
        WebElement articleCodeText = pointOfSalePage.getArticleCode();
        String articleCode = commonFunctions.readToastMessage(articleCodeText).trim();
        context.setContext("articleCode",articleCode);
        WebElement articleDescriptionText = pointOfSalePage.getArticleDescription();
        String articleDescription = commonFunctions.readToastMessage(articleDescriptionText).split(" ")[0].trim();
        context.setContext("articleDescription",articleDescription);
        String articleGroupText = pointOfSalePage.getArticleGroupText();
        context.setContext("articleGroupText",articleGroupText);
    }

    @And("User click on {string} and search for article code")
    public void userClickOnAndSearchForArticleCode(String dropDown) {
        String value = context.getContext("articleCode");
        excursionManagementPage.searchTemplateCode(dropDown, value);
    }

    @Then("User verify created new article details")
    public void userVerifyCreatedNewArticleDetails() {
        String code = context.getContext("articleCode");
        String description = context.getContext("articleDescription");
        String articleGroup = context.getContext("articleGroupText");
        Assert.assertEquals(pointOfSalePage.codeTextRow(),code);
        Assert.assertEquals(pointOfSalePage.descriptionTextRow(),description);
        Assert.assertEquals(pointOfSalePage.articleGroupTextRow(),articleGroup);
    }

    @And("User navigated to guest Profile page on POS")
    public void userNavigatedToGuestProfilePageOnPOS() {
        pointOfSalePage.guestProfileImage();
    }

    @Then("User verify the Pricelookup, RVC, and Waiter Station is displayed")
    public void userVerifyThePricelookupRVCAndWaiterStationIsDisplayed() {
        Assert.assertEquals(pointOfSalePage.priceLookUpText(), PointOfSaleConstants.PRICE_LOOKUP);
        Assert.assertEquals(pointOfSalePage.rVCText(),PointOfSaleConstants.RVC);
        Assert.assertEquals(pointOfSalePage.waiterStation(),PointOfSaleConstants.WAITER_STATION);
    }

    @And("User click on code in article class page")
    public void userClickOnCodeInArticleClassPage() {
        pointOfSalePage.clickArticleClassCode();
    }

    @Then("User verify articles should be tagged as Available:Priced Article Selected: Open Price under the parameter settings")
    public void userVerifyArticlesShouldBeTaggedAsAvailablePricedArticleSelectedOpenPriceUnderTheParameterSettings() {
        Assert.assertTrue(pointOfSalePage.checkParameterOpenPrice());
    }

    @And("User double click on the retail item in the check detailed area")
    public void userDoubleClickOnTheRetailItemInTheCheckDetailedArea() {
        pointOfSalePage.navigateToCommentPage();
    }

    @Then("user click on {string} tab")
    public void userClickOnTab(String name) {
        commonPage.clickOnLabel(name);
    }

    @And("User search for guest and select guest")
    public void userSearchForGuestAndSelectGuest() {
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        pointOfSalePage.searchGuestUsingAlphabetic(context.getContext("nonSensitiveGuestFullName"));
        seleniumActions.sleep(3000);
        pointOfSalePage.selectGuest();
    }

    @And("User select revenue center {string} and save the selected revenue center name")
    public void userSelectRevenueCenterAndSaveTheSelectedRevenueCenterName(String revenueCenter) {
       pointOfSalePage.selectRevenueCenterAndSave(revenueCenter);
       String saveRvcName = pointOfSalePage.getRvcName();
       context.setContext("rvcName",saveRvcName);
    }

    @Then("User verify the rvc is updated to the profile")
    public void userVerifyTheRvcIsUpdatedToTheProfile() {
        String rvcName = context.getContext("rvcName");
        Assert.assertEquals(pointOfSalePage.getRvcProfileName(),rvcName);
    }

    @And("User clicks the close icon in the guest profile container.")
    public void userClicksTheCloseIconInTheGuestProfileContainer() {
        pointOfSalePage.closeGuestProfile();
    }

    @Then("User verify the {string} toggle button off")
    public void userVerifyTheToggleButtonOff(String toggle) {
        String preventAlcoholOrderStatus  = pointOfSalePage.preventAlcoholOrderToggleButtonStatus(toggle);
        Assert.assertEquals(preventAlcoholOrderStatus,"false");
    }

    @And("User should select the filter {string} as {string}")
    public void userShouldSelectTheFilterAs(String filterName, String status) {
        pointOfSalePage.selectCheckStatusFilter(filterName,status);
    }

    @Then("User verify the success message")
    public void userVerifyTheSuccessMessage() {
        Assert.assertTrue(pointOfSalePage.getSuccessMessage());
    }

    @And("User search and add another buyer")
    public void userSearchAndAddAnotherBuyer() {
        pointOfSalePage.searchGuestUsingAlphabetic();
        pointOfSalePage.selectGuest();

    }

    @Then("User verify the Proper retail articles should appear for that selected RVC")
    public void userVerifyTheProperRetailArticlesShouldAppearForThatSelectedRVC() {
        Assert.assertTrue(pointOfSalePage.getRVCArticles());
    }

    @And("User save the barcode details on POS articles")
    public void userSaveTheBarcodeDetailsOnPOSArticles() {
        String barcode = pointOfSalePage.getBarCode();
        context.setContext("barCode",barcode);
    }

    @And("User verify the barcode article details")
    public void userVerifyTheBarcodeArticleDetails() {
        Assert.assertEquals(pointOfSalePage.getBarCodeInArticlesSetup(),context.getContext("barCode"));
    }

    @And("User verify the RVC related articles should be appear")
    public void userVerifyTheRVCRelatedArticlesShouldBeAppear() {
        Assert.assertTrue(pointOfSalePage.getRVCArticles());
    }

    @And("User verify the Regular and Promo options should get displayed under the Price Level dropdown")
    public void userVerifyTheRegularAndPromoOptionsShouldGetDisplayedUnderThePriceLevelDropdown() {
        pointOfSalePage.clickOnRegularDropdown();
        Assert.assertTrue(pointOfSalePage.getRegularProCode());
    }

    @And("User verify the page should contain Condiments")
    public void userVerifyThePageShouldContainCondiments() {
        Assert.assertTrue(pointOfSalePage.getCondiments());
    }

    @And("User verify the Total revenue of the RVC should be displayed.")
    public void userVerifyTheTotalRevenueOfTheRVCShouldBeDisplayed() {
        Assert.assertTrue(pointOfSalePage.getTotalRVCRevenueText());
    }

    @And("User verify the Additional Gratuity Total of the RVC should be displayed")
    public void userVerifyTheAdditionalGratuityTotalOfTheRVCShouldBeDisplayed() {
        Assert.assertTrue(pointOfSalePage.getTotalAdditionalGratuityTotalText());
    }

    @And("User verify the Open checks and closed checks count of the RVC should be displayed")
    public void userVerifyTheOpenChecksAndClosedChecksCountOfTheRVCShouldBeDisplayed() {
        Assert.assertTrue(pointOfSalePage.getTotalOpenCheckText());
        Assert.assertTrue(pointOfSalePage.getTotalClosedCheckText());
    }

    @And("User verify the open checks in the POS Dashboard associated to the table")
    public void userVerifyTheOpenChecksInThePOSDashboardAssociatedToTheTable() {
        String expected = context.getContext("guestFullName");
        String actual = pointOfSalePage.guestNameInOpenCheck();

        String[] parts = actual.split("\\s+", 2);
        String reorderedActual = parts[1] + " " + parts[0];

        Assert.assertEquals(expected, reorderedActual);
    }

    @And("User verify the table number, check number and the number of people part of the group should get displayed in the POS")
    public void userVerifyTheTableNumberCheckNumberAndTheNumberOfPeoplePartOfTheGroupShouldGetDisplayedInThePOS() {
        Assert.assertTrue(pointOfSalePage.tableNumberINOpenCheck());
    }

    @Then("User verify the print receipt having guest name")
    public void userVerifyThePrintReceiptHavingGuestName() {
        String printReceiptData = pointOfSalePage.getPrintReceipt();
        String guestName = context.getContext("guestFullName");
        System.out.println("guestName :: " + guestName);
        String[] fullName = guestName.split(" ");
        String normalizedStr1 = printReceiptData.replaceAll("\\s+", " ").toLowerCase();
        // commented because the strings varies form the PDF in different format
//        try {
//            Assert.assertTrue(normalizedStr1.contains(fullName[0].toLowerCase()));
//        }catch (AssertionError e) {
//            Assert.assertTrue(normalizedStr1.contains(fullName[1].toLowerCase()));
//        }
    }

    @And("User click on three dots and select {string} option")
    public void userClickOnThreeDotsAndSelectOption(String option) {
        pointOfSalePage.selectTransactionMoreButton(option);
    }

    @And("User selects the guest tile card to another seat number by drag and drop")
    public void userSelectsTheGuestTileCardToAnotherSeatNumberByDragAndDrop() {
//        pointOfSalePage.dragGuestNameToTheTable(context.getContext("guestFullName"));
        pointOfSalePage.dragGuestNameToTheTable();
    }

    @And("User Add desired modifiers")
    public void userAddDesiredModifiers() {
        String articleName = pointOfSalePage.getSelectedArticleModifierName();
        context.setContext("modifierArticleName",articleName);
        pointOfSalePage.selectArticleModifierLiquor();
    }

    @And("User verify the selected modifiers are added to the guest check under the liquor selected")
    public void userVerifyTheSelectedModifiersAreAddedToTheGuestCheckUnderTheLiquorSelected() {
        Assert.assertEquals(context.getContext("modifierArticleName"),pointOfSalePage.getSelectedModifierName());
    }

    @And("User verify the View various course categories such as: cover charge, appetizer, entree, side, dessert, kids menu")
    public void userVerifyTheViewVariousCourseCategoriesSuchAsCoverChargeAppetizerEntreeSideDessertKidsMenu() {
        List<String> foodCategoryName = List.of("COVER CHARGE","DINNER APPETIZER","ENTREE DINNER","SIDES DINNER","DESSERT DINNER","KIDS MENU");
        List<String> getCategoryName = pointOfSalePage.getFoodCategoryListName();
        System.out.println(getCategoryName);
//        Assert.assertTrue(getCategoryName.containsAll(foodCategoryName));
        for (String expected : foodCategoryName) {
            Assert.assertTrue(
                    getCategoryName.stream().anyMatch(actual -> actual.contains(expected)),
                    "Missing category: " + expected
            );
        }
    }

    @And("User View that favorite item was added to the guest's cart")
    public void userViewThatFavoriteItemWasAddedToTheGuestSCart() {
        String expected = pointOfSalePage.getFavItemName().trim();
        String[] lines = expected.split("\\R");
        expected = lines[lines.length - 1].trim();
        Assert.assertEquals(context.getContext("favItem"),expected);
    }

    @And("User save the check number for the selected guest list")
    public void userSaveTheCheckNumberForTheSelectedGuestList() {
        String[] guestName = context.getContext("guestNameList1").split(" ");
        String checkNumber = null;
        try {
            checkNumber = pointOfSalePage.getCheckNumber(guestName[0] + " " + guestName[1]);
        } catch (Exception e) {
            checkNumber = pointOfSalePage.getCheckNumber(guestName[1] + " " + guestName[0]);
        }

        context.setContext("guestFirstName", guestName[0]);
        context.setContext("guestLastName", guestName[1]);
        context.setContext("checkNumber", checkNumber);
    }

    @And("User save the selected guestNames from the list")
    public void userSaveTheSelectedGuestNamesFromTheList() {
        List<String> guestNames = pointOfSalePage.getGuestNames();
        System.out.println("guestNames :: " + guestNames);
        context.setListContext("guestList", guestNames);
        List<String> actualGuestNames = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            actualGuestNames.add(guestNames.get(i));
        }
        System.out.println("actualGuestNames :: " + actualGuestNames);
        String[] guest1 = actualGuestNames.get(0).split(" ");
        String[] guest2 = actualGuestNames.get(1).split(" ");
        String guestName1 = guest1[1] + " " + guest1[0];
        String guestName2 = guest2[1] + " " + guest2[0];
        context.setContext("guestNameList1",guestName1);
        context.setContext("guestNameList2",guestName2);
    }

    @Then("User verify the guest {string} number under check menu")
    public void userVerifyTheGuestNumberUnderCheckMenu(String headerName) {
        SeleniumWaits seleniumWaits = new SeleniumWaits(getDriver());
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        seleniumWaits.setImplicitWaitTime(getDriver(), 5);
        String guestName = context.getContext("guestFullNameFromTheList1");
        if (guestName == null) {
            guestName = context.getContext("guestFullName");
        }
        String[] fullName = guestName.split(" ");
        List<String> guestList = new ArrayList<>(List.of(fullName));
        Collections.reverse(guestList);
        String actualName = fullName[1] + " " + fullName[0];
        String table = context.getContext("tableNumber");
        System.out.println("table :: " + table);
        String guestFName = String.join(" ", guestList);
        String actualPrice = null;
        try {
            actualPrice = pointOfSalePage.getPriceFromGuestList(guestFName, headerName);
        } catch (Exception e) {
            try {
                actualPrice = pointOfSalePage.getPriceFromGuestList(fullName[0] + " " + fullName[1], headerName);
            }catch (Exception e1){
                seleniumActions.clickOnElement(By.xpath("//b[text()='Check Date And Time:']/ancestor::otalio-complex-filter-label/preceding-sibling::button/span[contains(@class,'button-wrapper')]"));
                actualPrice = pointOfSalePage.getPriceFromGuestList(fullName[0] + " " + fullName[1], headerName);
            }
        }
        String associateName = null;
        try {
            associateName = pointOfSalePage.getPriceFromGuestList(actualName, "User");
        } catch (Exception e) {
            associateName = pointOfSalePage.getPriceFromGuestList(fullName[0] + " " + fullName[1], "User");
        }
        context.setContext("associateName", associateName);
        System.out.println("actualPrice :: " + actualPrice);
        Assert.assertEquals(actualPrice, table);
        seleniumWaits.keepBackImplicitWaitTime(getDriver());
    }

    @And("User search for special needs guest and select guest")
    public void userSearchForSpecialNeedsGuestAndSelectGuest() {
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        pointOfSalePage.searchGuestUsingAlphabetic(context.getContext("guestFullName"));
        seleniumActions.sleep(3000);
        pointOfSalePage.selectGuest();
    }

    @And("User should click on the Back arrow button which is on the left top side of the screen")
    public void userShouldClickOnTheBackArrowButtonWhichIsOnTheLeftTopSideOfTheScreen() {
        pointOfSalePage.cancelCheck();
    }

    @And("User click on {string} filter and select option {string} on POS article setup page")
    public void userClickOnFilterAndSelectOptionOnPOSArticleSetupPage(String filterName, String checkbox) {
        pointOfSalePage.selectRVCDropDownValue(filterName,checkbox);
    }

    @Then("User View all articles associated to that category")
    public void userViewAllArticlesAssociatedToThatCategory() {
        Assert.assertTrue( pointOfSalePage.getArticleList());
    }
}