package com.nclh.webSteps;

import com.nclh.constants.PointOfSaleConstants;
import com.nclh.constants.YearConstants;
import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.*;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

@Log4j2

public class OrderPointOfSaleSteps extends AllDriverManager {
    Context context;
    OrderPointOfSalePage orderPointOfSalePage;
    CommonFunctions commonFunctions;
    CommonPage commonPage;
    GuestCheckInCheckOutPage guestCheckInCheckOutPage;
    FinanceManagementPage financeManagementPage;


    public OrderPointOfSaleSteps(Context context) {
        this.context = context;
        commonPage = new CommonPage(getDriver());
        commonFunctions = new CommonFunctions();
        orderPointOfSalePage = new OrderPointOfSalePage(getDriver());
        guestCheckInCheckOutPage = new GuestCheckInCheckOutPage(getDriver());
        financeManagementPage = new FinanceManagementPage(getDriver());
    }

    @Then("User update the Date of Birth")
    public void user_update_the_date_of_birth() {
        orderPointOfSalePage.enterDateOfBirth(YearConstants.YEAR_TEXT);
    }

    @Then("User update the first name and last name")
    public void userUpdateTheFirstNameAndLastName() {
        orderPointOfSalePage.updateFirstNameAndLastName();
        WebElement firstNameTextText = orderPointOfSalePage.getFirstNameText();
        String firstName = commonFunctions.readToastMessage(firstNameTextText).trim();
        System.out.println(firstName);
        context.setContext("firstName",firstName);
        WebElement lastNameTextText = orderPointOfSalePage.getLastNameText();
        String lastName = commonFunctions.readToastMessage(lastNameTextText).trim();
        System.out.println(lastName);
        context.setContext("lastName",lastName);
        String fullName = firstName + " " + lastName;
        System.out.println(fullName);
        context.setContext("guestFullName", fullName);
    }

    @And("User verify name in profile page")
    public void userVerifyNameInProfilePage() {
        String getLastName = guestCheckInCheckOutPage.getLastName();
        System.out.println("getLastName :: " + getLastName);
        Assert.assertTrue(getLastName.contains(context.getContext("guestFullName")));
    }

    @And("User double click on guest profile")
    public void userDoubleClickOnGuestProfile() {
        orderPointOfSalePage.doubleClickOnGuestProfile(context.getContext("guestFullName"));
    }

    @Then("User verify the guest details as birthday,special requests,posting status,age group on the order taking device")
    public void userVerifyTheGuestDetailsAsBirthdaySpecialRequestsPostingStatusAgeGroupOnTheOrderTakingDevice() {
        Assert.assertTrue(orderPointOfSalePage.birthdayIcon());
        String cabinNumber = orderPointOfSalePage.cabinNumberFormGuestProfile();
        Assert.assertEquals(cabinNumber,context.getContext("cabin"));
        Assert.assertEquals(orderPointOfSalePage.getAgeGroup(),context.getContext("accountType"));
        Assert.assertTrue(orderPointOfSalePage.postingStatus());
        Assert.assertTrue(orderPointOfSalePage.specialNeedsDetails());

    }

    @Then("User save the selected guest Details")
    public void userSaveTheSelectedGuestDetails() {
        String cabinText = orderPointOfSalePage.getCabinNumber();
        context.setContext("cabin",cabinText);
        String accountTypeText = orderPointOfSalePage.getAccountType();
        context.setContext("accountType", accountTypeText);
    }

    @And("User disable toggle for {string}")
    public void userDisableToggleFor(String toggleName) {
        orderPointOfSalePage.disableToggle(toggleName);
    }

    @Then("User verify the Posting Status toggle button is disable")
    public void userVerifyThePostingStatusToggleButtonIsDisable() {
        String toggleStatus = financeManagementPage.checkToggleButtonStatus();
        Assert.assertEquals(toggleStatus,"false");
    }

    @Then("User verify the posting status is closed")
    public void userVerifyThePostingStatusIsClosed() {
        Assert.assertTrue(orderPointOfSalePage.getPostingStatusClosed());
    }

    @Then("User select the table and click on add check button")
    public void userSelectTheTableAndClickOnAddCheckButton() {
        orderPointOfSalePage.selectTable();
        String tableNumberText = orderPointOfSalePage.getTableNumber();
        context.setContext("tableNumber",tableNumberText);
        commonPage.navigateTo(PointOfSaleConstants.ADD_CHECK_BUTTON_FOR_TABLE);
    }

    @Then("User verify the guest information such as guest photo, guest name, cabin details, and packages")
    public void userVerifyTheGuestInformationSuchAsGuestPhotoGuestNameCabinDetailsAndPackages() {
//        Assert.assertTrue(orderPointOfSalePage.profilePicture());
        String guestName = context.getContext("nonSensitiveGuestFullName");
        log.info("Actual name is: {}", guestName);
        String profileName = orderPointOfSalePage.getGuestFullName();
        log.info("Expected name is: {}", profileName);
        Assert.assertEquals(guestName,profileName);
        String cabinNumber = orderPointOfSalePage.cabinNumberFormGuestProfile();
        Assert.assertEquals(cabinNumber,context.getContext("cabin"));
        List<String> expectedValues = Arrays.asList("Personal Packages","Shared Packages","Invitation Packages","Package History");
        log.info("Expected list values are: {}", expectedValues);
        log.info("Actual list values are: {}", orderPointOfSalePage.getListOfPackages());
        Assert.assertEquals(orderPointOfSalePage.getListOfPackages(),expectedValues);

    }

    @Then("User verify the guest guest if there are any special needs on the order taking device")
    public void userVerifyTheGuestGuestIfThereAreAnySpecialNeedsOnTheOrderTakingDevice() {
        Assert.assertTrue(orderPointOfSalePage.specialNeedsDetails());
    }

    @Then("User verify the guest is able to view the posting status indicator on the order taking device")
    public void userVerifyTheGuestIsAbleToViewThePostingStatusIndicatorOnTheOrderTakingDevice() {
        Assert.assertTrue(orderPointOfSalePage.postingStatus());
    }
}
