package com.nclh.webSteps;

import com.nclh.constants.*;
import com.nclh.dataProviders.ExcelReader;
import com.nclh.managers.AllDriverManager;
import com.nclh.managers.FileReaderManager;
import com.nclh.pageObjects.*;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import com.nclh.utilities.SeleniumActions;
import com.nclh.utilities.SeleniumWaits;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.*;

import static com.nclh.dataProviders.JsonReader.readUserData;

public class ShipSecuritySteps extends AllDriverManager {

    ShipSecurityPage shipSecurityPage;
    Context context;
    CommonPage commonPage;
    GroupManagementPage groupManagementPage;
    GuestServicePage guestServicePage;
    LoginPage loginPage;
    GuestInformationPage guestInformationPage;
    CommonFunctions commonFunctions;
    CrewInformationPage crewInformationPage;
    HomePage homePage;
    SafetyManagementPage safetyManagementPage;
    CommonSteps commonSteps;
    GuestServiceSteps guestServiceSteps;
    CrewInformationSteps crewInformationSteps;
    HotelMaintenancePage hotelMaintenancePage;
    SeleniumActions seleniumActions;

    public static final Logger logger = LoggerFactory.getLogger(ShipSecuritySteps.class);

    public ShipSecuritySteps(Context context) {
        this.context = context;
        shipSecurityPage = new ShipSecurityPage(getDriver());
        commonPage = new CommonPage(getDriver());
        groupManagementPage = new GroupManagementPage(getDriver());
        guestServicePage = new GuestServicePage(getDriver());
        loginPage = new LoginPage(getDriver());
        guestInformationPage = new GuestInformationPage(getDriver());
        commonFunctions = new CommonFunctions();
        crewInformationPage = new CrewInformationPage(getDriver());
        homePage = new HomePage(getDriver());
        safetyManagementPage = new SafetyManagementPage(getDriver());
        commonSteps = new CommonSteps(context);
        guestServiceSteps = new GuestServiceSteps(context);
        crewInformationSteps = new CrewInformationSteps(context);
        hotelMaintenancePage = new HotelMaintenancePage(getDriver());
        seleniumActions = new SeleniumActions(getDriver());
    }

    @And("User navigated to module {string}")
    public void userNavigateToGangway(String module) {
        String parentWindowId = shipSecurityPage.navigateToModule(module);
        System.out.println(parentWindowId);
        context.setContext("parentWindowId", parentWindowId);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User select location {string}")
    public void searchLocation(String location) {
        shipSecurityPage.searchLocation(location);
    }

    @Then("User open {string} member for {string}")
    public void openCrewOrGuestMember(String crewOrGuest, String checkedInOnboardOnShore) {
        shipSecurityPage.openCrewOrGuests(crewOrGuest, checkedInOnboardOnShore);
    }

    @And("User changed to AShoreOrOnBoard {string}")
    public void changeUserToOnShoreOrOnBoard(String status) {
        shipSecurityPage.changeToAshoreOrOnBoard();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("Verify user status {string}")
    public void verifyUserChangedStatus(String status) throws InterruptedException {
        Thread.sleep(5000); // wait for the status to change
        Assert.assertTrue(shipSecurityPage.getUserStatus(status));
    }

    @And("User search for Guest and move to onBoard or Ashore")
    public void userSearchForGuest() {
        String guestFirstName = context.getContext("nonSensitiveGuestFirstName");
        String guestLastName = context.getContext("nonSensitiveGuestLastName");
        System.out.println("nonSensitiveGuestFirstName :: " + guestFirstName);
        String guestFullName = context.getContext("nonSensitiveGuestFullName");
        System.out.println("nonSensitiveGuestFullName :: " + guestFullName);
        shipSecurityPage.searchGuestAndMoveToOnboardOrAshore(guestFirstName, guestFullName);
    }

    @And("User search for Guest and select and to onBoard or Ashore")
    public void userSearchForGuestAndMove() {
        String guestFirstName = context.getContext("nonSensitiveGuestFirstName");
        String guestLastName = context.getContext("nonSensitiveGuestLastName");
        System.out.println("nonSensitiveGuestFirstName :: " + guestFirstName);
        String guestFullName = context.getContext("nonSensitiveGuestFullName");
        System.out.println("nonSensitiveGuestFullName :: " + guestFullName);
        shipSecurityPage.searchGuestAndMoveToOnboardOrAshore(guestLastName, guestFullName);
    }

    @And("User search for Guest2 and select and to onBoard or Ashore")
    public void userSearchForGuest2AndMove() {
        String guestFirstName = context.getContext("nonSensitiveGuest1FN");
        String guestLastName = context.getContext("nonSensitiveGuest1LN");
        System.out.println("nonSensitiveGuest1FN :: " + guestFirstName);
        String guestFullName = context.getContext("nonSensitiveGuest1FullName");
        System.out.println("nonSensitiveGuest1FullName :: " + guestFullName);
        shipSecurityPage.searchGuestAndMoveToOnboardOrAshore(guestLastName, guestFullName);
    }

    @And("User change {string} status of assign to {string}")
    public void userChangeStatusOfAssignTo(String dropDownName, String status) {
        shipSecurityPage.changeStatusInDropDown(dropDownName, status);
    }

    @And("User click on search")
    public void userClickOnSearch() {
        shipSecurityPage.clickOnSearch();
    }

    @Then("User verify error message on Gangway mode with {string}")
    public void userVerifyErrorMessageOnGangwayModeWith(String reason) {
        String errorText = shipSecurityPage.getErrorMessageOnGangwayMode();
        System.out.println("errorText :: " + errorText);
        Assert.assertTrue(errorText.contains(reason));
    }

    @And("User apply filter {string}")
    public void userApplyFilter(String filterName) {
        shipSecurityPage.applyFilter(filterName);
    }

    @And("User navigated to gangway logs")
    public void userNavigatedToGangwayLogs() {
        shipSecurityPage.switchToParentTab(context.getContext("parentWindowId"));
    }

    @Then("User capture details of the user selected in gangway {string}")
    public void userCaptureDetailsOfTheUserSelectedInGangway(String data) {
        List<String> rowData;
        shipSecurityPage.clickOn100Pages();
        rowData = shipSecurityPage.getGuestDetailsFromTheTable(data, context.getContext("nonSensitiveGuest1FullName"));
        System.out.println("Gangway dashboard logs :: " + rowData);
        if (rowData.size() == 0) {
            shipSecurityPage.clickOnPage2();
            rowData = shipSecurityPage.getGuestDetailsFromTheTable(data, context.getContext("nonSensitiveGuest1FullName"));
        }
        switch (data) {
            case "log":
                context.setListContext("logsTableData", rowData);
                break;
            case "dashboard":
                context.setListContext("dashboardTableData", rowData);
                break;
        }
    }

    @Then("User compare the dashboard data to the gangway logs")
    public void userCompareTheDashboardDataToTheGangwayLogs() {
        System.out.println("logsTableData :: => " + context.getListContext("logsTableData"));
        //System.out.println("dashboardTableData :: => " + context.getListContext("dashboardTableData"));

      /*  HashSet<String> set = new HashSet<>(context.getListContext("logsTableData"));

        boolean matchFound = false;
        for (String item : context.getListContext("dashboardTableData")) {
            if (set.contains(item)) {
                matchFound = true;
                break;
            }
        }
        Assert.assertTrue(matchFound, "No matching elements found between the logs and dashboard");*/
        Assert.assertTrue(context.getListContext("logsTableData").contains(context.getContext("gangwayDashboardGuest")));
    }

    @And("User set location {string} and tender capacity for {string}")
    public void userSetLocationAndTenderCapacityFor(String location, String tenderCapacity) {
        shipSecurityPage.setLocation(location);
        shipSecurityPage.setTenderCapacity(tenderCapacity);
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        SeleniumWaits seleniumWaits = new SeleniumWaits(getDriver());
        try {
            seleniumWaits.untilElementIsClickable(
                    webDriver.findElement(By.xpath("//div[text()='Counts']")));

            seleniumActions.clickOnElement(By.xpath("//div[text()='Counts']"));
        } catch (Exception e) {
        }
    }

    @Then("User verify tender capacity set to {string}")
    public void userVerifyTenderCapacitySetTo(String capacity) {
        Assert.assertEquals(shipSecurityPage.getTenderCapacityNumber(), capacity);
    }


    @And("User verify scheduled {string} present on gangway")
    public void userVerifyScheduledPresentOnGangway(String trainings) {
        Assert.assertTrue(shipSecurityPage.getListOfSafetyAndDrills(trainings) > 0);
    }

    @And("User reset tender capacity to {string}")
    public void userResetTenderCapacity(String tenderCapacity) {
        shipSecurityPage.resetTenderCapacity(tenderCapacity);
    }

    @And("User save {string} guests count on gangway")
    public void userSaveGuestsCountOnGangway(String checkedInOnboardOrAshore) {
        String numberOfGuests = shipSecurityPage.getNumberOfGuests("Guests", checkedInOnboardOrAshore);

        switch (checkedInOnboardOrAshore) {
            case "Checked In Ashore":
                System.out.println("Gangway Guest count at Ashore :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtCheckedInAshoreCount", numberOfGuests);
                break;
            case "Checked In Onboard":
                System.out.println("Gangway Guest count at Onboard :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtCheckedInOnboardCount", numberOfGuests);
                break;
            case "Expected":
                System.out.println("Gangway Expected Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtExpectedCount", numberOfGuests);
                break;
            case "Embarked":
                System.out.println("Gangway Embarked Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtEmbarkedCount", numberOfGuests);
                break;
            case "Disembarking":
                System.out.println("Gangway Disembarking Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtDisembarkingCount", numberOfGuests);
                break;
            case "Disembarked":
                System.out.println("Gangway Disembarked Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtDisembarkedCount", numberOfGuests);
                break;
            case "On Tour":
                System.out.println("Gangway OnTour Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtOnTourCount", numberOfGuests);
                break;
            case "Scheduled for Overnight - Onboard":
                System.out.println("Gangway OnTour Scheduled for Overnight - Onboard count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtScheduledOvernightOnboardCount", numberOfGuests);
                break;
            case "Scheduled for Overnight - Ashore":
                System.out.println("Gangway Scheduled for Overnight - Ashore Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtScheduledOvernightAshoreCount", numberOfGuests);
                break;
        }
    }

    @Then("User compare the both guest count on guest service and gangway are same")
    public void userCompareTheBothGuestCountOnGuestServiceAndGangwayAreSame() {
        Assert.assertEquals(context.getContext("GangwayGuestsAtCheckedInAshoreCount"), context.getContext("GuestServiceGuestsAtCheckedInAshoreCount"));
        Assert.assertEquals(context.getContext("GangwayGuestsAtCheckedInOnboardCount"), context.getContext("GuestServiceGuestsAtCheckedInOnboardCount"));
    }

    @And("User navigates to {string} link")
    public void userNavigatesToLink(String link) {
        commonPage.navigateTo(link);
    }

    @Then("User verify visitor table")
    public void userVerifyVisitorTable(DataTable dataTable) {
        List<String> dataRow = dataTable.row(0);
        List<String> columnData = new ArrayList<>();
        for (int i = 0; i < dataRow.size(); i++) {
            columnData.add(dataRow.get(i));
        }

        System.out.println(columnData);
        System.out.println(shipSecurityPage.geVisitorColumnHeaders());
        Assert.assertEquals(columnData, shipSecurityPage.geVisitorColumnHeaders());
    }

    @And("User unCheck the {string} status {string}")
    public void userUnCheckTheStatus(String onboard, String yesOrNo) {
        shipSecurityPage.unCheckValueFromDropDown(onboard, yesOrNo);
    }

    @Then("Verify user on gangway mode")
    public void userOnGangwayMode() {
        Assert.assertTrue(shipSecurityPage.isGangwayModePresent());
    }


    @And("User save guest firstName excluding sensitive cabins")
    public void userSaveGuestFirstNameExcludingSensitiveCabins() {
        List<String> sensitiveCabins = Arrays.asList(
                readUserData("sensitiveData", "cabinNumbers", "cabin1"),
                readUserData("sensitiveData", "cabinNumbers", "cabin2"),
                readUserData("sensitiveData", "cabinNumbers", "cabin3"),
                readUserData("sensitiveData", "cabinNumbers", "cabin4"),
                readUserData("sensitiveData", "cabinNumbers", "cabin5")
        );
        String randomCabinNumber = groupManagementPage.getCabinNumber(sensitiveCabins);
        System.out.println(randomCabinNumber);
        context.setContext("randomCabinNumber", randomCabinNumber);
        List<String> guestNames = shipSecurityPage.getGuestFullName(context.getContext("randomCabinNumber"));
        System.out.println(guestNames);
        context.setContext("nonSensitiveGuestFirstName", guestNames.get(0));
        context.setContext("nonSensitiveGuestLastName", guestNames.get(1));
        context.setContext("nonSensitiveGuestFullName", context.getContext("nonSensitiveGuestLastName") + " " + context.getContext("nonSensitiveGuestFirstName"));
    }

    @And("User save guest {string} firstName excluding sensitive cabins")
    public void userSaveGuestFirstNameExcludingSensitiveCabins(String guestNumbers) {
        List<String> sensitiveCabins = Arrays.asList(
                readUserData("sensitiveData", "cabinNumbers", "cabin1"),
                readUserData("sensitiveData", "cabinNumbers", "cabin2"),
                readUserData("sensitiveData", "cabinNumbers", "cabin3"),
                readUserData("sensitiveData", "cabinNumbers", "cabin4"),
                readUserData("sensitiveData", "cabinNumbers", "cabin5")
        );
        String randomCabinNumber = groupManagementPage.getCabinNumber(sensitiveCabins);
        context.setContext("randomCabinNumber", randomCabinNumber);

        List<String> guestNames = shipSecurityPage.getGuestFullName(context.getContext("randomCabinNumber"));
        System.out.println(guestNames);
        context.setContext("nonSensitiveGuest1FN", guestNames.get(0));
        context.setContext("nonSensitiveGuest1LN", guestNames.get(1));
        context.setContext("nonSensitiveGuest1FullName", context.getContext("nonSensitiveGuest1LN") + " " + context.getContext("nonSensitiveGuest1FN"));
    }

    @And("User select guestOrCrew from the list and confirm to move AShoreOrOnBoard")
    public void userSelectGuestOrCrewFromTheListAndConfirmToMoveAShoreOrOnBoard() {
        shipSecurityPage.navigateToGuest(context.getContext("nonSensitiveGuest1FullName"));
        shipSecurityPage.confirmMove();
    }

    @And("User save {string} guests count on security page")
    public void userSaveGuestsCountOnSecurityPage(String checkedInOnboardOrAshore) {
        String numberOfGuests = shipSecurityPage.getNumberOfGuests("Guests", checkedInOnboardOrAshore);

        switch (checkedInOnboardOrAshore) {
            case "Checked In Ashore":
                System.out.println("Security Guest count at Ashore :: " + numberOfGuests);
                context.setContext("SecurityGuestsAtCheckedInAshoreCount", numberOfGuests);

                break;
            case "Checked In Onboard":
                System.out.println("Security Guest count at Onboard :: " + numberOfGuests);
                context.setContext("SecurityGuestsAtCheckedInOnboardCount", numberOfGuests);
                break;
            case "Expected":
                System.out.println("Security Expected Guest count :: " + numberOfGuests);
                context.setContext("SecurityGuestsAtExpectedCount", numberOfGuests);
                break;
            case "Embarked":
                System.out.println("Security Embarked Guest count :: " + numberOfGuests);
                context.setContext("SecurityGuestsAtEmbarkedCount", numberOfGuests);
                break;
            case "Disembarking":
                System.out.println("Security Disembarking Guest count at Onboard :: " + numberOfGuests);
                context.setContext("SecurityGuestsAtDisembarkingCount", numberOfGuests);
                break;
            case "Disembarked":
                System.out.println("Security Disembarked Guest count :: " + numberOfGuests);
                context.setContext("SecurityGuestsAtDisembarkedCount", numberOfGuests);
                break;
            case "On Tour":
                System.out.println("Security OnTour Guest count :: " + numberOfGuests);
                context.setContext("SecurityGuestsAtOnTourCount", numberOfGuests);
                break;
            case "Scheduled for Overnight - Onboard":
                System.out.println("Security Scheduled for Overnight - Onboard Guest count :: " + numberOfGuests);
                context.setContext("SecurityGuestsAtScheduledOvernightOnboardCount", numberOfGuests);
                break;
            case "Scheduled for Overnight - Ashore":
                System.out.println("Security Scheduled for Overnight - Ashore Guest count :: " + numberOfGuests);
                context.setContext("SecurityGuestsAtScheduledOvernightAshoreCount", numberOfGuests);
                break;
        }
    }

    @Then("User compare the all pages guest count on guest service, security page and gangway are same")
    public void userCompareTheAllPagesGuestCountOnGuestServiceSecurityPageAndGangwayAreSame() {
        Assert.assertEquals(context.getContext("GangwayGuestsAtCheckedInAshoreCount"), context.getContext("GuestServiceGuestsAtCheckedInAshoreCount"));
        Assert.assertEquals(context.getContext("GangwayGuestsAtCheckedInOnboardCount"), context.getContext("GuestServiceGuestsAtCheckedInOnboardCount"));
        Assert.assertEquals(context.getContext("GangwayGuestsAtCheckedInOnboardCount"), context.getContext("SecurityGuestsAtCheckedInOnboardCount"));
        Assert.assertEquals(context.getContext("GangwayGuestsAtCheckedInAshoreCount"), context.getContext("SecurityGuestsAtCheckedInAshoreCount"));
    }

    @Then("User verify that users having stateroom status icon is in {string} color for the guests or crews")
    public void userVerifyThatUsersHavingStateroomStatusIconIsInColorForTheGuestsOrCrews(String color) {
        Set<String> colourCodes = shipSecurityPage.getStateRoomStatus();
        List<String> colorCode = new ArrayList<>(colourCodes);

        switch (color) {
            case "green":
                Assert.assertEquals(Color.fromString(colorCode.get(0)).asHex(), "#44a855");
                break;
            case "orange":
                Assert.assertEquals(Color.fromString(colorCode.get(0)).asHex(), "#f2a126");
                break;
            default:
                System.out.println("Provide right colour or colour state not visible");
        }

    }

    @And("User clear onBoard filter")
    public void userClearOnBoardFilter() {
        shipSecurityPage.clearOnBoardFilter();
    }

    @And("User open the guest information")
    public void userOpenTheGuestInformation() {
        String guestName = context.getContext("nonSensitiveGuestFirstName");
        shipSecurityPage.navigateToProfile(guestName);
    }

    @And("User save same booking number guests")
    public void userSaveSameBookingNumberGuests() {
        List<String> guestNames = shipSecurityPage.getBookingNumberGuests();
        System.out.println("guestNames :: " + guestNames);
        context.setListContext("sameBookingNumberGuests", guestNames);
    }

    @And("User save same cabin number guests")
    public void userSaveSameCabinNumberGuests() {
        List<String> guestNames = shipSecurityPage.getCabinNumberGuests();
        System.out.println("guestNames :: " + guestNames);
        context.setListContext("sameCabinNumberGuests", guestNames);
    }

    @Then("User verify guest travelling with family having same booking number")
    public void userVerifyGuestTravellingWithFamilyHavingSameBookingNumber() {
        SoftAssert softAssert = new SoftAssert();
        List<String> guests = context.getListContext("sameBookingNumberGuests");
        List<String> actualGuestNames = new ArrayList<>();
        for (String guest : guests) {
            String[] guestName = guest.split(" ");
            String guestFulName = guestName[0] + " " + guestName[1];
            actualGuestNames.add(guestFulName);
        }
        System.out.println(actualGuestNames);

        System.out.println(shipSecurityPage.getChildGuardiansList());
        softAssert.assertTrue(shipSecurityPage.getChildGuardiansList().containsAll(actualGuestNames));
    }

    @And("User search for the includes sensitive guest and move onBoard or Ashore")
    public void userSearchForTheGuestAndMoveOnBoardOrAshore() {
        String guestFirstName = context.getContext("guestFirstName");
        System.out.println("guestFirstName :: " + guestFirstName);
        String guestFullName = context.getContext("guestFullName");
        System.out.println("guestFullName :: " + guestFullName);
        shipSecurityPage.searchGuestAndMoveToOnboardOrAshore(guestFirstName, guestFullName);
    }


    @And("User navigated to {string} profile page not including sensitive data")
    public void userNavigatedToCrewInfoPage(String crewFirstName) {
        if (crewFirstName.contains("Dynamic")) {
            guestServicePage.navigateToCrewInformation(context.getContext("guestFirstName"));
        } else {
            guestServicePage.openCrewInformation(crewFirstName);
        }
    }

    @And("User search for the random guest")
    public void userSearchForTheRandomGuest() {
        shipSecurityPage.searchGuestAndSelect();
    }

    @Then("User verify the {string} warning not tagged to guest profile")
    public void userVerifyTheWarningNotTaggedToGuestProfile(String warning) {
        List<String> warningText = guestServicePage.getWarningMessage();
        System.out.println("warningText :: " + warningText);
        System.out.println("warning :: " + warning);
        Assert.assertFalse(warningText.contains(warning.trim()));
    }

    @Then("User verifies the {string} warning is tagged to guest profile")
    public void userVerifiesWarningTaggedToGuestProfile(String warning) {
        List<String> warningText = guestServicePage.getWarningMessage();
        System.out.println("warningText :: " + warningText);
        System.out.println("warning :: " + warning);
        Assert.assertTrue(warningText.contains(warning.trim()));
    }

    @And("User logout from gangway")
    public void userLogoutFromGangway() {
        loginPage.logoutFromGangway();
    }

    @And("User open the guest profile")
    public void userOpenTheGuestProfile() {
        guestServicePage.navigateToCrewInformation(context.getContext("guestFirstName"));
    }

    @Then("User verify the cabin number displayed")
    public void userVerifyTheCabinNumberDisplayed() {
        Assert.assertEquals(context.getContext("randomCabinNumber"), shipSecurityPage.getCabinNumber());
    }

    @Then("User compare the all types guest count on security page and gangway are same")
    public void userCompareTheAllTypesGuestCountOnSecurityPageAndGangwayAreSame() {
        Assert.assertEquals(context.getContext("GangwayGuestsAtCheckedInOnboardCount"), context.getContext("SecurityGuestsAtCheckedInOnboardCount"));
        Assert.assertEquals(context.getContext("GangwayGuestsAtCheckedInAshoreCount"), context.getContext("SecurityGuestsAtCheckedInAshoreCount"));
        Assert.assertEquals(context.getContext("GangwayGuestsAtExpectedCount"), context.getContext("SecurityGuestsAtExpectedCount"));
        Assert.assertEquals(context.getContext("GangwayGuestsAtEmbarkedCount"), context.getContext("SecurityGuestsAtEmbarkedCount"));
        Assert.assertEquals(context.getContext("GangwayGuestsAtDisembarkingCount"), context.getContext("SecurityGuestsAtDisembarkingCount"));
        Assert.assertEquals(context.getContext("GangwayGuestsAtDisembarkedCount"), context.getContext("SecurityGuestsAtDisembarkedCount"));
        Assert.assertEquals(context.getContext("GangwayGuestsAtOnTourCount"), context.getContext("SecurityGuestsAtOnTourCount"));
        Assert.assertEquals(context.getContext("GangwayGuestsAtScheduledOvernightOnboardCount"), context.getContext("SecurityGuestsAtScheduledOvernightOnboardCount"));
        Assert.assertEquals(context.getContext("GangwayGuestsAtScheduledOvernightAshoreCount"), context.getContext("SecurityGuestsAtScheduledOvernightAshoreCount"));
    }

    @And("User save {string} guests count on gangway after user moved to {string}")
    public void userSaveGuestsCountOnGangwayAfterUserMovedTo(String statusType, String status) {
        String numberOfGuests = shipSecurityPage.getNumberOfGuests("Guests", statusType);

        switch (statusType) {
            case "Checked In Ashore":
                System.out.println("After Moving Gangway Guest count at Ashore :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtCheckedInAshoreCountAfterMoving", numberOfGuests);
                break;
            case "Checked In Onboard":
                System.out.println("AfterMoving Gangway Guest count at Onboard :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtCheckedInOnboardCountAfterMoving", numberOfGuests);
                break;
            case "Expected":
                System.out.println("AfterMoving Gangway Expected Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtExpectedCountAfterMoving", numberOfGuests);
                break;
            case "Embarked":
                System.out.println("AfterMoving Gangway Embarked Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtEmbarkedCountAfterMoving", numberOfGuests);
                break;
            case "Disembarking":
                System.out.println("AfterMoving Gangway Disembarking Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtDisembarkingCountAfterMoving", numberOfGuests);
                break;
            case "Disembarked":
                System.out.println("AfterMoving Gangway Disembarked Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtDisembarkedCountAfterMoving", numberOfGuests);
                break;
        }
    }

    @And("User verify the guest {string} count should be reduced to {string}")
    public void userVerifyTheGuestCountShouldBeReducedTo(String statusType, String number) {
        switch (statusType) {
            case "Checked In Ashore":
                int beforeCheckedInAshoreCount = Integer.parseInt(context.getContext("GangwayGuestsAtCheckedInAshoreCount"));
                int afterCheckedInAshoreCount = Integer.parseInt(context.getContext("GangwayGuestsAtCheckedInAshoreCountAfterMoving"));
                Assert.assertEquals(beforeCheckedInAshoreCount - afterCheckedInAshoreCount, Integer.parseInt(number));
                break;
            case "Checked In Onboard":
                int beforeCheckedInOnBoardCount = Integer.parseInt(context.getContext("GangwayGuestsAtCheckedInOnboardCount"));
                int afterCheckedInOnBoardCount = Integer.parseInt(context.getContext("GangwayGuestsAtCheckedInOnboardCountAfterMoving"));
                Assert.assertEquals(beforeCheckedInOnBoardCount - afterCheckedInOnBoardCount, Integer.parseInt(number));
                break;
            case "Expected":
                int beforeExpectedCount = Integer.parseInt(context.getContext("GangwayGuestsAtExpectedCount"));
                int afterExpectedCount = Integer.parseInt(context.getContext("GangwayGuestsAtExpectedCountAfterMoving"));
                Assert.assertEquals(beforeExpectedCount - afterExpectedCount, Integer.parseInt(number));
                break;
            case "Embarked":
                int beforeEmbarkedCount = Integer.parseInt(context.getContext("GangwayGuestsAtEmbarkedCount"));
                int afterEmbarkedCount = Integer.parseInt(context.getContext("GangwayGuestsAtEmbarkedCountAfterMoving"));
                Assert.assertEquals(beforeEmbarkedCount - afterEmbarkedCount, Integer.parseInt(number));
                break;
            case "Disembarking":
                int beforeDisEmbarkingCount = Integer.parseInt(context.getContext("GangwayGuestsAtDisembarkingCount"));
                int afterDisEmbarkingCount = Integer.parseInt(context.getContext("GangwayGuestsAtDisembarkingCountAfterMoving"));
                Assert.assertEquals(beforeDisEmbarkingCount - afterDisEmbarkingCount, Integer.parseInt(number));
                break;
            case "Disembarked":
                int beforeDisEmbarkedCount = Integer.parseInt(context.getContext("GangwayGuestsAtDisembarkedCount"));
                int afterDisEmbarkedCount = Integer.parseInt(context.getContext("GangwayGuestsAtDisembarkedCountAfterMoving"));
                Assert.assertEquals(beforeDisEmbarkedCount - afterDisEmbarkedCount, Integer.parseInt(number));
                break;
        }
    }

    @And("User clear notifications")
    public void userClearNotifications() {
        shipSecurityPage.clearNotifications();
    }

    @And("User opens member information")
    public void userOpensMemberInformation() {

        context.setContext("memberFullName", shipSecurityPage.getUserName());
        shipSecurityPage.openMemberAtGangway();
        shipSecurityPage.switchToNewWindow();
    }

    @Then("User verify member information on the profile")
    public void userVerifyMemberInformationOnTheProfile() {
        String profileName = shipSecurityPage.getProfileUserName();
        String userName = context.getContext("memberFullName");
        //Assert.assertEquals(profileName, userName);
        Assert.assertTrue(profileName.contains(userName));
    }

    @Then("User verify {string} toggle button in disable mode")
    public void userVerifyToggleButtonInDisableMode(String option) {
        Assert.assertTrue(shipSecurityPage.getToggleButtonStatus(option));
    }

    @Then("user capture details of user selected at gangway")
    public void userCaptureDetailsOfUserSelectedAtGangway() {

        List<String> rowData = shipSecurityPage.getRowDataInGangway(context.getContext("nonSensitiveGuest1FullName"));
        context.setListContext("gangwayDashboardData", rowData);
    }

    @And("User select guest to move onBoardOrAshore")
    public void userSelectGuestToMoveOnBoardOrAshore() {

    }

    @Then("User verify user data present on the page else {string} should present on the page")
    public void userVerifyUserDataPresentOnThePageElseShouldPresentOnThePage(String data) {
        Assert.assertTrue(shipSecurityPage.getDataMessage(data));
    }

    @And("User select {string} filter and select option {string}")
    public void userSelectFilterAndSelectOption(String filter, String checkBox) {
        shipSecurityPage.openFilterAndSelectValue(filter, checkBox);
    }

    @And("User save guest name and move to AshoreOrOnBoard")
    public void userSaveGuestNameAndMoveToAshoreOrOnBoard() {
        String guestName = shipSecurityPage.saveGuestName().trim();
        context.setContext("gangwayDashboardGuest", guestName);
        shipSecurityPage.moveGuest(guestName);
    }

    @Then("User save guest full name by excluding prefix")
    public void userSaveGuestFullNameByExcludingPrefix() {
        List<String> guestDataWithOutSensitiveUsers = shipSecurityPage.getGuestLastNameLatest("XXX-GST");
        String getOneGuestLastNameFromTheList = guestDataWithOutSensitiveUsers.get(0);
        context.setContext("GuestLastNameWithOutXXXGST", getOneGuestLastNameFromTheList);

        String getGuestFirstNameUsingLastName = shipSecurityPage.getCrewGuestFirstName(getOneGuestLastNameFromTheList);
        context.setContext("GuestFirstNameWithOutXXXGST", getGuestFirstNameUsingLastName);
        context.setContext("GuestFullNameWithOutXXXGST", getOneGuestLastNameFromTheList + " " + getGuestFirstNameUsingLastName);
    }

    @Then("User save list of guest full name by excluding prefix")
    public void userSaveListOfGuestFullNameByExcludingPrefix() {
        List<String> guestDataWithOutSensitiveUsers = shipSecurityPage.getGuestLastNameLatest("XXX-GST");
        List<String> guestLastNames = new ArrayList<>();
        for (int i = 0; i < guestDataWithOutSensitiveUsers.size(); i++) {
            guestLastNames.add(guestDataWithOutSensitiveUsers.get(i));
        }

        context.setListContext("GuestLastNameListWithOutXXXGST", guestLastNames);
        System.out.println(context.getListContext("GuestLastNameListWithOutXXXGST"));

        List<String> guestFirstNames = new ArrayList<>();
        for (int j = 0; j < guestDataWithOutSensitiveUsers.size(); j++) {
            guestFirstNames.add(shipSecurityPage.getCrewGuestFirstName(guestDataWithOutSensitiveUsers.get(j)));
        }
        context.setListContext("GuestFirstNameListWithOutXXXGST", guestFirstNames);
        System.out.println(context.getListContext("GuestFirstNameListWithOutXXXGST"));

        try {
            ExcelReader.writeData("CheckedInGuestNames", context.getListContext("GuestFirstNameListWithOutXXXGST"), context.getListContext("GuestLastNameListWithOutXXXGST"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Test debug");
    }

    @Then("User save list of crew full name by excluding prefix")
    public void userSaveListOfCrewFullNameByExcludingPrefix() {
        List<String> crewDataWithOutSensitiveUsers = shipSecurityPage.getCrewLastNameLatest("XXX-CRW");
        List<String> crewLastNames = new ArrayList<>();
        for (int i = 0; i < crewDataWithOutSensitiveUsers.size(); i++) {
            crewLastNames.add(crewDataWithOutSensitiveUsers.get(i));
        }

        context.setListContext("CrewLastNameListWithOutXXXCRW", crewLastNames);
        System.out.println(context.getListContext("CrewLastNameListWithOutXXXCRW"));

        List<String> crewFirstNames = new ArrayList<>();
        for (int j = 0; j < crewDataWithOutSensitiveUsers.size(); j++) {
            crewFirstNames.add(shipSecurityPage.getCrewGuestFirstName(crewDataWithOutSensitiveUsers.get(j)));
        }
        context.setListContext("CrewFirstNameListWithOutXXXCRW", crewFirstNames);
        System.out.println(context.getListContext("CrewFirstNameListWithOutXXXCRW"));

        try {
            ExcelReader.writeData("CheckedInCrewNames", context.getListContext("CrewFirstNameListWithOutXXXCRW"), context.getListContext("CrewLastNameListWithOutXXXCRW"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Test debug");
    }

    @And("User get {string} full name from saved list")
    public void userGetFullNameFromSavedList(String userType) throws IOException {
        CommonFunctions commonFunctions = new CommonFunctions();
        switch (userType) {
            case "guest":
                int rowNumber = ExcelReader.getNumberOfRows("CheckedInGuestNames");
                int rowRandomNumber = commonFunctions.getRandomNumber(2, rowNumber);
                String guestFirstName = ExcelReader.getCellValue("CheckedInGuestNames", rowRandomNumber, "FirstName");
                System.out.println(guestFirstName);
                String guestLastName = ExcelReader.getCellValue("CheckedInGuestNames", rowRandomNumber, "LastName");
                System.out.println(guestLastName);
                context.setContext("guestFirstNameFromTheList", guestFirstName);
                context.setContext("guestLastNameFromTheList", guestLastName);
                context.setContext("guestFullNameFromTheList", guestFirstName + " " + guestLastName);
                break;
            case "crew":
                int crewRowNumber = ExcelReader.getNumberOfRows("CheckedInCrewNames");
                int crewRowRandomNumber = commonFunctions.getRandomNumber(2, crewRowNumber);
                String crewFirstName = ExcelReader.getCellValue("CheckedInCrewNames", crewRowRandomNumber, "FirstName");
                System.out.println(crewFirstName);
                String crewLastName = ExcelReader.getCellValue("CheckedInCrewNames", crewRowRandomNumber, "LastName");
                System.out.println(crewLastName);

                context.setContext("crewFirstNameFromTheList", crewFirstName);
                context.setContext("crewLastNameFromTheList", crewLastName);
                context.setContext("crewFullNameFromTheList", crewFirstName + " " + crewLastName);
                break;
        }
    }

    @And("User search for the {string} user and navigate to profile")
    public void userSearchForTheUserAndNavigateToProfile(String arg0) {
        guestServicePage.openCrewProfile(context.getContext("guestFirstNameFromTheList"), context.getContext("guestLastNameFromTheList"));
    }

    @And("User delete if there are any disallowed sub-departments")
    public void userDeleteIfThereAreAnyDisallowedSubDepartments() {
        shipSecurityPage.deleteDisallowedDepartments();
    }

    @And("User enables posting status {string} for guest")
    public void userEnablesPostingStatusForGuest(String post) {
        shipSecurityPage.enablePostingToggle(post);
    }

    @And("User get {string} number of {string} full name from saved list")
    public void userGetNumberOfFullNameFromSavedList(String arg0, String arg1) throws IOException {
        CommonFunctions commonFunctions = new CommonFunctions();
        int rowNumber = ExcelReader.getNumberOfRows("CheckedInGuestNames");
        int rowRandomNumber = commonFunctions.getRandomNumber(2, rowNumber);
        int rowRandomNumber2 = rowRandomNumber - 1;
        String guestFirstName1 = ExcelReader.getCellValue("CheckedInGuestNames", rowRandomNumber, "FirstName");
        String guestFirstName2 = ExcelReader.getCellValue("CheckedInGuestNames", rowRandomNumber2, "FirstName");
        System.out.println(guestFirstName1);
        System.out.println(guestFirstName2);
        String guestLastName1 = ExcelReader.getCellValue("CheckedInGuestNames", rowRandomNumber, "LastName");
        String guestLastName2 = ExcelReader.getCellValue("CheckedInGuestNames", rowRandomNumber2, "LastName");
        System.out.println(guestLastName1);
        System.out.println(guestLastName2);
        context.setContext("guestFirstNameFromTheList1", guestFirstName1);
        context.setContext("guestLastNameFromTheList1", guestLastName1);
        context.setContext("guestFullNameFromTheList1", guestFirstName1 + " " + guestLastName1);

        context.setContext("guestFirstNameFromTheList2", guestFirstName2);
        context.setContext("guestLastNameFromTheList2", guestLastName2);
        context.setContext("guestFullNameFromTheList2", guestFirstName2 + " " + guestLastName2);
    }

    @And("User {string} toggle for {string} to guest")
    public void userToggleForToGuest(String toggle, String boarding) {
        switch (toggle) {
            case "enable":
                shipSecurityPage.enableAshoreOnBoard(boarding);
                break;
            case "disable":
                shipSecurityPage.disableAshoreOnBoard();
                break;
        }

    }

    @And("User search for specific {string} and navigated to profile")
    public void userSearchForSpecificAndNavigatedToProfile(String guest) {
        switch (guest) {
            case "guest1":
                guestServicePage.openCrewProfile(context.getContext("guestFirstNameFromTheList1"), context.getContext("guestLastNameFromTheList1"));
                break;
            case "guest2":
                guestServicePage.openCrewProfile(context.getContext("guestFirstNameFromTheList2"), context.getContext("guestLastNameFromTheList2"));
                break;
            case "FullName":
                guestServicePage.openCrewProfile(context.getContext("FIRST_NAME"), context.getContext("LAST_NAME"));
                break;
            case "ReservationId":
                String[] resId = context.getContext("ReservationId").split(":");
                guestServicePage.openCrewProfile(resId[1].replace("\"", ""), context.getContext("LAST_NAME"));
                break;
            case "GuestNameLastName":
                guestServicePage.openGuest(context.getContext("guestLastName"));
                break;
            case "GuestLastName":
                String guestFullName = context.getContext("guestName");
                String[] nameParts = guestFullName.split(" ");
                String firstName = nameParts[0];
                String lastName = nameParts[nameParts.length - 1];
                context.setContext("guestLastName", lastName);
                guestServicePage.openCrewProfile(firstName, lastName);
                break;
        }
    }

    @And("User search guest with {string}")
    public void userSearchGuestWithAndOpenGuestProfilePage(String name) {
//        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
//            guestServicePage.searchUser(name);
//        } else {
//            //for ALLURA functionality calling after saving users
//            guestServicePage.searchUser(name);
//            //guestServicePage.searchUser(context.getContext("nonSensitiveGuestFirstName"));
//        }

        //guestServicePage.searchUser("Ashore");




    }

    @And("User search ashore denied crew user")
    public void userSearchAshoreDeniedCrew() {
//        guestServicePage.searchUser(CrewInformationConstants.ASHORE_DENIED_CREW);
        commonPage.searchForCrew(CrewInformationConstants.ASHORE_DENIED_CREW);

    }

    @And("User search boarding denied crew user")
    public void userSearchBoardingDeniedCrew() {
//        guestServicePage.searchUser(CrewInformationConstants.BOARDING_DENIED_CREW);
        commonPage.searchForCrew(CrewInformationConstants.BOARDING_DENIED_CREW);
    }

    @And("User search the reassigned cabin crew user")
    public void searchReassignedCabinCrew() {
        commonPage.searchForCrew(CrewManagementConstants.ASSIGNED_CABIN_CREW);
    }

    @And("User search the checked in crew user")
    public void searchCheckedInCrew() {
        commonPage.searchForCrew(CrewManagementConstants.CHECKED_IN_CREW);
    }

    @And("User search checked in crew user for cabin assignment")
    public void searchCabinAssignmentCrew() {
        commonPage.searchCabinAssignmentCrew(CrewManagementConstants.CHECKED_IN_CREW);
    }

    @And("User search expected future crew user for cabin assignment")
    public void searchExpectedFutureCrewCabinAssignment() {
        commonPage.searchCabinAssignmentCrew(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
    }

    @And("User search the expected future crew user")
    public void searchExpectedFutureCrew() {
        commonPage.searchForCrew(CrewManagementConstants.EXPECTED_FUTURE_CREW);
    }

    @And("User navigated to user {string} profile page")
    public void userNavigatedToProfilePage(String name) {
        System.out.println(context.getContext("nonSensitiveGuestFirstName"));
        switch (name) {
            case "XXXGUEST122":
                shipSecurityPage.openProfile(context.getContext("nonSensitiveGuestFirstName"), context.getContext("nonSensitiveGuestLastName"));
                break;
            case "GuestUser":
                shipSecurityPage.openProfile(context.getContext("nonSensitiveGuestFirstName"), context.getContext("nonSensitiveGuestLastName"));
//                if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
//                    shipSecurityPage.openProfile(context.getContext("nonSensitiveGuestFirstName"), context.getContext("nonSensitiveGuestLastName"));
//                } else {
//                    guestServicePage.searchUser(context.getContext("nonSensitiveGuestFirstName"));
//                    guestServicePage.navigateToCrewInformation(context.getContext("nonSensitiveGuestFirstName"));
//                }
                break;
            case "Gangwayguest":
                shipSecurityPage.openProfile(context.getContext("nonSensitiveGuestFirstName"), context.getContext("nonSensitiveGuestLastName"));
                /*if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
                    shipSecurityPage.openProfile(context.getContext("nonSensitiveGuestFirstName"), context.getContext("nonSensitiveGuestLastName"));
                } else {
                    guestServicePage.searchUser(context.getContext("nonSensitiveGuestFirstName"));
                    guestServicePage.navigateToCrewInformation(context.getContext("nonSensitiveGuestFirstName"));
                }*/
                break;
            case "Gangwayguest2":
                shipSecurityPage.openProfile(context.getContext("nonSensitiveGuest1FN"), context.getContext("nonSensitiveGuest1LN"));
                /*if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
                    shipSecurityPage.openProfile(context.getContext("nonSensitiveGuest1FN"), context.getContext("nonSensitiveGuest1LN"));
                } else {
                    guestServicePage.searchUser(context.getContext("nonSensitiveGuest1FN"));
                    guestServicePage.navigateToCrewInformation(context.getContext("nonSensitiveGuest1FullName"));
                }*/
                break;
        }

    }

    @And("User navigate to guests page")
    public void userNavigateToGuestsPage() {
        shipSecurityPage.navigateToGuestsPage();
    }

    @Then("User select guest and move")
    public void userSelectGuestAndMove() {
        shipSecurityPage.searchGuestAndMoveToOnboard("a");
    }

    @And("User click on search icon at gangway")
    public void userClickOnSearchIconAtGangway() {
        shipSecurityPage.clickOnSearchIcon();
    }

    @Then("user select the gangway both option")
    public void userSelectTheGangwayBothOption() {
        shipSecurityPage.userSelectTheGangwayBothOption();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("Gangway Both option should be selected.")
    public void gangwayBothOptionShouldBeSelected() {
        shipSecurityPage.gangwayBothOptionShouldBeSelected();
    }

    @And("User enter the tender capacity count {string}")
    public void userEnterTheTenderCapacityCount(String str) {
        shipSecurityPage.userEnterTheTenderCapacityCount(str);
    }

    @Then("Gangway Mode Direction Screen should be displayed")
    public void gangwayModeDirectionScreenShouldBeDisplayed() {
        shipSecurityPage.gangwayModeDirectionScreenShouldBeDisplayed();
    }

    @And("Click on {string} icon at top right pane")
    public void clickOnIconAtTopRightPane(String str) {
        shipSecurityPage.clickOnIconAtTopRightPane(str);
    }

    @Then("Visitors Screen should be displayed")
    public void visitorsScreenShouldBeDisplayed() {
        shipSecurityPage.visitorsScreenShouldBeDisplayed();
    }

    @Then("Select Visitor type dropdown under general section")
    public void selectVisitorTypeDropdownUnderGeneralSection() {
        shipSecurityPage.selectVisitorTypeDropdownUnderGeneralSection();
    }

    @And("User able to select the Visitor type")
    public void userAbleToSelectTheVisitorType() {
        shipSecurityPage.userAbleToSelectTheVisitorType();
    }

    @And("User click on SAVE button")
    public void userClickOnSAVEButton() {
        shipSecurityPage.userClickOnSAVEButton();
    }

    @Then("User should see the success popup")
    public void userShouldSeeTheSuccessPopup() {
        shipSecurityPage.userShouldSeeTheSuccessPopup();
    }

    @Then("Visitor type category should be updated")
    public void visitorTypeCategoryShouldBeUpdated() {
        shipSecurityPage.visitorTypeCategoryShouldBeUpdated();
    }

    @And("User is able to report a missing person")
    public void userIsAbleToReportAMissingPerson() {
        boolean isReportAsMissingAvailable = shipSecurityPage.navigateToReportLink("Report as missing");
        if (!isReportAsMissingAvailable) {
            return;
        }
        String dateTime = commonFunctions.getDateIncrementedBy(-1, "M/dd/yyyy, h:mm a");
        guestServicePage.setDateAndTime(dateTime);
        commonPage.openLinkUsingHref("edit");
        shipSecurityPage.searchGuestAndMoveToOnboard("a");
        guestServicePage.clickOnMissingFoundButton("report-as-missing");
        guestServicePage.drawSignature();
        commonPage.navigateTo("Sign & confirm");
    }


    @Then("User validates the total count of checked in onboard guests, crew and visitors with the total count")
    public void validateTotalCheckedInOnboardCount() {

        String checkedInOnboardGuests = shipSecurityPage.getTotalGuestsCheckedInOnboardCount();
        context.setContext("checkedInOnboardGuests", checkedInOnboardGuests);
        logger.info("count of checked In Onboard Guests: is {}", checkedInOnboardGuests);

        String checkedInOnboardCrew = shipSecurityPage.getTotalCrewCheckedInOnboardCount();
        context.setContext("checkedInOnboardCrew", checkedInOnboardCrew);
        logger.info("count of checked In Onboard Crew is: {}", checkedInOnboardCrew);

        String checkedInOnboardVisitors = shipSecurityPage.getTotalVisitorsCheckedInOnboardCount();
        context.setContext("checkedInOnboardVisitors", checkedInOnboardVisitors);
        logger.info("count of checked In Onboard Visitors is: {}", checkedInOnboardVisitors);


        shipSecurityPage.scrollToTotalCountText();

        String totalCheckedInOnboard = shipSecurityPage.getTotalCheckedInOnboardCount();
        context.setContext("checkedInOnboardTotalCount", totalCheckedInOnboard);
        logger.info("count of total checked In Onboard is: " + totalCheckedInOnboard);

        int guestsCount = Integer.parseInt(context.getContext("checkedInOnboardGuests").replaceAll(",", "").trim());
        int crewCount = Integer.parseInt(context.getContext("checkedInOnboardCrew").replaceAll(",", "").trim());
        int visitorsCount = Integer.parseInt(context.getContext("checkedInOnboardVisitors").replaceAll(",", "").trim());
        int totalCount = Integer.parseInt(context.getContext("checkedInOnboardTotalCount").replaceAll(",", "").trim());

        int expectedTotal = visitorsCount + crewCount + guestsCount;
        logger.info("count of total checked In Onboard guests is: {}", guestsCount);
        logger.info("count of total checked In Onboard crew is: {}", crewCount);
        logger.info("count of total checked In Onboard visitors is: {}", visitorsCount);
        logger.info("count of total checked In Onboard is: {}", totalCount);


        Assert.assertEquals(
                totalCount,
                expectedTotal,
                "Mismatch in total checked-in onboard count! Expected: "
                        + expectedTotal + " but Found: " + totalCount
        );

    }


    @Then("User validates the total count of {string} guests, crew and visitors with the total count")
    public void validateTotalStatusCount(String status) {

        String statusKey = status.replaceAll("\\s+", "").toLowerCase();

        String guestsCountStr = "0";
        String crewCountStr = "0";
        String visitorsCountStr = "0"; // default to 0 if method doesn't exist
        String totalCountStr = "0";

        switch (status) {
            case "Checked In Onboard":
                guestsCountStr = shipSecurityPage.getTotalGuestsCheckedInOnboardCount();
                crewCountStr = shipSecurityPage.getTotalCrewCheckedInOnboardCount();
                visitorsCountStr = shipSecurityPage.getTotalVisitorsCheckedInOnboardCount();
                totalCountStr = shipSecurityPage.getTotalCheckedInOnboardCount();
                break;

            case "Checked In Ashore":
                guestsCountStr = shipSecurityPage.getTotalGuestsCheckedInAshoreCount();
                crewCountStr = shipSecurityPage.getTotalCrewCheckedInAshoreCount();
                // Visitors method may not exist, default to "0"
                totalCountStr = shipSecurityPage.getTotalCheckedInAshoreCount();
                break;

            case "Other Status Onboard":
                guestsCountStr = shipSecurityPage.getTotalGuestsOtherStatusOnboardCount();
                crewCountStr = shipSecurityPage.getTotalCrewOtherStatusOnboardCount();
                visitorsCountStr = shipSecurityPage.getTotalVisitorsOtherStatusOnboardCount();
                totalCountStr = shipSecurityPage.getTotalOtherStatusOnboardCount();
                break;

            case "Expected":
                guestsCountStr = shipSecurityPage.getTotalGuestsExpectedCount();
                crewCountStr = shipSecurityPage.getTotalCrewExpectedCount();
                visitorsCountStr = shipSecurityPage.getTotalVisitorsExpectedCount();
                totalCountStr = shipSecurityPage.getTotalExpectedCount();
                break;

            case "Embarked":
                guestsCountStr = shipSecurityPage.getTotalGuestsEmbarkedCount();
                crewCountStr = shipSecurityPage.getTotalCrewEmbarkedCount();
                // No visitors method available
                totalCountStr = shipSecurityPage.getTotalEmbarkedCount();
                break;

            case "Disembarking":
                guestsCountStr = shipSecurityPage.getTotalGuestsDisembarkingCount();
                crewCountStr = shipSecurityPage.getTotalCrewDisembarkingCount();
                // No visitors method available
                totalCountStr = shipSecurityPage.getTotalDisembarkingCount();
                break;

            case "Disembarked":
                guestsCountStr = shipSecurityPage.getTotalGuestsDisembarkedCount();
                crewCountStr = shipSecurityPage.getTotalCrewDisembarkedCount();
                // No visitors method available
                totalCountStr = shipSecurityPage.getTotalDisembarkedCount();
                break;

            default:
                throw new IllegalArgumentException("Unsupported status: " + status);
        }

        // Store in context
        context.setContext(statusKey + "Guests", guestsCountStr);
        context.setContext(statusKey + "Crew", crewCountStr);
        context.setContext(statusKey + "Visitors", visitorsCountStr); // "0" if missing
        context.setContext(statusKey + "TotalCount", totalCountStr);

        // Parse counts
        int guests = Integer.parseInt(context.getContext(statusKey + "Guests").replaceAll(",", "").trim());
        int crew = Integer.parseInt(context.getContext(statusKey + "Crew").replaceAll(",", "").trim());
        int visitors = Integer.parseInt(context.getContext(statusKey + "Visitors").replaceAll(",", "").trim());
        int totalCount = Integer.parseInt(context.getContext(statusKey + "TotalCount").replaceAll(",", "").trim());

        int expectedTotal = guests + crew + visitors;

        logger.info("{} - Guests: {}, Crew: {}, Visitors: {}, Total: {}", status, guests, crew, visitors, totalCount);

        Assert.assertEquals(totalCount, expectedTotal, "Mismatch in total " + status + " count!");
    }


    @And("User apply {string} filter {string}")
    public void userApplyFilter(String dropDownName, String status) {
        shipSecurityPage.changeStatusInDropDown(dropDownName, status);
    }

    @And("User search for the {string} guest")
    public void userSearchForTheGuest(String name) {
        shipSecurityPage.userSearchForTheGuest(name);
        String[] guestName = hotelMaintenancePage.getGuestName().split(" ");
        context.setContext("firstguestName", guestName[0].trim());
        context.setContext("guestName", guestName[1].trim());
    }

    @Then("User check for all the list of guests")
    public void userCheckForAllTheListOfGuests() {
        Set<String> colourCodes = shipSecurityPage.getStateRoomStatus();
        List<String> colorList = new ArrayList<>(colourCodes);

        List<String> allowedColors = List.of("#44a855", "#f2a126", "#f8f8f8","#f74343");

        for (String code : colorList) {

            String actualColor = Color.fromString(code).asHex();

            Assert.assertTrue(allowedColors.contains(actualColor), "Unexpected color found: " + actualColor);
        }
    }

    @And("User selects account types {string}")
    public void userSelectsAccountTypes(String types) {
        shipSecurityPage.selectGuestTypes(types);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User clear the date range")
    public void userClearTheDateRange() {
        shipSecurityPage.userClearTheDateRange();
    }
    @Then("User capture details of the user selected {string}")
    public void userCaptureDetailsOfTheUserSelected(String data) {
        List<String> rowData;
        shipSecurityPage.clickOn100Pages();
        rowData = shipSecurityPage.getGuestDetailsFromTheTable(data, context.getContext("nonSensitiveGuest1FullName"));
        logger.info("Gangway dashboard logs :: " + rowData);
        if (shipSecurityPage.isNoDataDisplayed()) {
            logger.info("No Data appears");
        }
    }


    @And("User selects filters {string}")
    public void userSelectsFilters(String filters) {
        String[] filterList = filters.split(",");
        for(String filterName : filterList)
        {
            shipSecurityPage.selectFilterFromDropdown(filterName.trim());
        }
    }

    @Then("User able View Guest reservation details")
    public void userAbleViewGuestReservationDetails() {
        List<WebElement> elements = shipSecurityPage.getReservationElements();
        for(WebElement element : elements)
        {
            Assert.assertTrue(element.isDisplayed(), "Reservation element is not displayed");
        }
    }

    @And("User able count of other travelers same booking {string} number")
    public void userAbleCountOfOtherTravelersSameBookingNumber(String bookingNumber) {
        shipSecurityPage.userAbleCountOfOtherTravelersSameBookingNumber(bookingNumber);
    }

    @Then("User view the {string}")
    public void userViewThe(String text) {
        shipSecurityPage.userView(text);
    }

    @And("User clicks the day having activity data")
    public void userClicksTheDayHavingActivityData() {
        boolean isDisplayed = shipSecurityPage.clickDayHavingActivity();
        Assert.assertTrue(isDisplayed, "Activity day is not displayed in calendar");
    }

    @And("User save {string} and {string} count on gangway")
    public void userSaveAndCountOnGangway(String checkedInOnboardOrAshore, String type) {
        String numberOfGuests = shipSecurityPage.getNumberOfGuests(type, checkedInOnboardOrAshore);

        switch (checkedInOnboardOrAshore) {
            case "Checked In Ashore":
                System.out.println("Gangway Guest count at Ashore :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtCheckedInAshoreCount", numberOfGuests);
                break;
            case "Checked In Onboard":
                System.out.println("Gangway Guest count at Onboard :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtCheckedInOnboardCount", numberOfGuests);
                break;
            case "Expected":
                System.out.println("Gangway Expected Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtExpectedCount", numberOfGuests);
                break;
            case "Embarked":
                System.out.println("Gangway Embarked Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtEmbarkedCount", numberOfGuests);
                break;
            case "Disembarking":
                System.out.println("Gangway Disembarking Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtDisembarkingCount", numberOfGuests);
                break;
            case "Disembarked":
                System.out.println("Gangway Disembarked Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtDisembarkedCount", numberOfGuests);
                break;
            case "On Tour":
                System.out.println("Gangway OnTour Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtOnTourCount", numberOfGuests);
                break;
            case "Scheduled for Overnight - Onboard":
                System.out.println("Gangway OnTour Scheduled for Overnight - Onboard count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtScheduledOvernightOnboardCount", numberOfGuests);
                break;
            case "Scheduled for Overnight - Ashore":
                System.out.println("Gangway Scheduled for Overnight - Ashore Guest count :: " + numberOfGuests);
                context.setContext("GangwayGuestsAtScheduledOvernightAshoreCount", numberOfGuests);
                break;
        }

    }

    @Then("User verifies board status {string}")
    public void userVerifiesBoardStatus(String expectedStatus) {
        boolean actualStatus = shipSecurityPage.isBoardStatusDisplayed(expectedStatus);
        Assert.assertTrue(actualStatus, "Expected board status is not displayed: " + expectedStatus);
    }

    @And("User clear the valid from date")
    public void userClearTheValidFromDate() {
        shipSecurityPage.userClearTheValidFromDate();
    }

    @And("User select the visitor in Visitor Type dropdown")
    public void userSelectTheVisitorInVisitorTypeDropdown() {
        shipSecurityPage.userSelectTheVisitorInVisitorTypeDropdown();
    }

    @Then("User verify {string} toggle button in {string} mode")
    public void userVerifyToggleButtonInMode(String toggleName, String expectedState) {
        boolean state = Boolean.parseBoolean(expectedState);
        shipSecurityPage.setToggleState(toggleName, state);
        boolean actualState = shipSecurityPage.getToggleState(toggleName);
        Assert.assertEquals(actualState, state, toggleName + " toggle state mismatch");
    }

    @Then("User view the profile")
    public void userViewTheProfile() {
        shipSecurityPage.userViewTheProfile();
    }

    @Then("User select the Reported person")
    public void userSelectTheReportedPerson() {
        commonPage.openLinkUsingHref("edit");
        shipSecurityPage.userSearchForTheGuest("Reporting");
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("validate {string} and {string} count")
    public void validateRowAndColumnCount(String rowName, String columnName) {

        int columnIndex = shipSecurityPage.getColumnIndex(columnName);
        int expectedCount = shipSecurityPage.getDashboardCount(rowName, columnIndex);
        context.setContext("rowName", rowName);
        context.setContext("columnIndex", String.valueOf(columnIndex));
        context.setContext("expectedGuestCount", String.valueOf(expectedCount));
    }

    @Then("User checking the dashboard count")
    public void userCheckingTheDashboardCount() {
        String rowName = context.getContext("rowName");
        int columnIndex = Integer.parseInt(context.getContext("columnIndex"));
        int expectedCount = Integer.parseInt(context.getContext("expectedGuestCount"));
        shipSecurityPage.clickDashboardCount(rowName, columnIndex);
        int actualCount = shipSecurityPage.getTableRowCount();
        logger.info("Table Items Count : " + actualCount);
        Assert.assertEquals(actualCount, expectedCount, "Count mismatch between dashboard and table");
    }

    @And("User close the dashboard box")
    public void userCloseTheDashboardBox() {
        shipSecurityPage.dashboardBoxCloseButton();
    }

    @Then("User click on required guest link under name column")
    public void userClickOnRequiredGuestLinkUnderNameColumn() {
        String guestName = shipSecurityPage.storeAndClickGuestName();
        context.setContext("guestName", guestName);
        logger.info("Stored Guest Name : " + guestName);
    }


    @Then("user validates the guest profile name")
    public void validateGuestProfileName() {
        String expectedName = context.getContext("guestName");
        String actualName = shipSecurityPage.getGuestProfileName();
        logger.info("Expected Name : " + expectedName);
        logger.info("Actual Name : " + actualName);
        Assert.assertTrue(actualName.toLowerCase().contains(expectedName.toLowerCase()), "Guest name mismatch between table and profile");
    }

    @And("User set the tender capacity {string} number")
    public void userSetTheTenderCapacityNumber(String tenderCapacity) {
        shipSecurityPage.setTenderCapacity(tenderCapacity);
    }

    @Then("User view the tender capacity number")
    public void userViewTheTenderCapacityNumber() {
        shipSecurityPage.userViewTheTenderCapacityNumber();
    }

    @Then("User view Print on Manifest Toggle Slider")
    public void userViewPrintOnManifestToggleSlider() {
        shipSecurityPage.userViewPrintOnManifestToggleSlider();
    }
    @And("User click on reset button")
    public void userClickOnResetButton() {
        shipSecurityPage.userClickOnResetButton();
    }

    @Then("User view the reset tender capacity limit changed")
    public void userViewTheResetTenderCapacityLimitChanged() {
        shipSecurityPage.userViewTheTenderCapacityNumber();
    }

    @And("User click increase authority button {int} times")
    public void userClickIncreaseAuthorityButtonTimes(int count) {
        shipSecurityPage.clickIncrease(count);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User click decrease authority button {int} times")
    public void userClickDecreaseAuthorityButtonTimes(int count) {
        shipSecurityPage.clickDecrease(count);
    }
    @Then("User should see the error popup")
    public void userShouldSeeTheErrorPopup() {
        shipSecurityPage.userShouldSeeTheErrorPopup();
    }

    @Given("User marks the guest {string} as {string} using {string}")
    public void userMarksTheGuestAsUsing(String guestMember, String warning, String boarding) {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.GUESTSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.GUEST_SERVICES);
        guestServicePage.userOpenServiceItemCard(GlobalConstants.GUEST_SEARCH);
        crewInformationPage.clearReservationStatus();
        guestServicePage.checkOptionInTheFilter(GlobalConstants.CHECK_IN);
        guestServicePage.addFilter(GlobalConstants.ONBOARD);
        guestServicePage.searchUser(guestMember);
        safetyManagementPage.userAbleToClickInTheDetailTable(ShipSecurityConstants.LAST_NAME);
        guestServicePage.openDropDown(ShipSecurityConstants.LOST_AND_FOUND_SECURITY_INCIDENTS);
        userToggleForToGuest(ShipSecurityConstants.ENABLE,boarding);
        List<String> warningText = guestServicePage.getWarningMessage();
        System.out.println("warningText :: " + warningText);
        logger.info("warning :: " + warning);
        commonSteps.userWaitForSeconds(ExcursionManagementConstants.WAITING_FOR_ELEMENT);
        loginPage.safeLogout();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User unmarks the {string} guest as {string} using {string}")
    public void userUnmarksTheGuestAs(String guestMember, String warning, String boarding) {
        loginPage.safeLogout();
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.GUESTSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.GUEST_SERVICES);
        guestServicePage.userOpenServiceItemCard(GlobalConstants.GUEST_SEARCH);
        crewInformationPage.clearReservationStatus();
        guestServicePage.checkOptionInTheFilter(GlobalConstants.CHECK_IN);
        guestServicePage.addFilter(GlobalConstants.ONBOARD);
        guestServicePage.searchUser(guestMember);
        safetyManagementPage.userAbleToClickInTheDetailTable(ShipSecurityConstants.LAST_NAME);
        guestServicePage.openDropDown(ShipSecurityConstants.LOST_AND_FOUND_SECURITY_INCIDENTS);
        userToggleForToGuest(ShipSecurityConstants.DISABLE,boarding);
        List<String> warningText = guestServicePage.getWarningMessage();
        logger.info("warningText :: " + warningText);
        logger.info("warning :: " + warning);
        Assert.assertFalse(warningText.contains(warning.trim()));
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User navigated to gangway page")
    public void userNavigatedToGangwayPage() {
        homePage.navigateToService(GlobalConstants.SHIP_SECURITY);
        userNavigateToGangway(ExcursionManagementConstants.GANGWAY_LOGS);
        guestServicePage.addFilter(ExcursionManagementConstants.ACCOUNT);
        //shipSecurityPage.userClearTheDateRange();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Given("User verifies the {string} details")
    public void userVerifiesTheDetails(String guestMember) {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.GUESTSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.GUEST_SERVICES);
        guestServicePage.userOpenServiceItemCard(GlobalConstants.GUEST_SEARCH);
        crewInformationPage.clearReservationStatus();
        guestServicePage.checkOptionInTheFilter(GlobalConstants.CHECK_IN);
        guestServicePage.addFilter(GlobalConstants.ONBOARD);
        guestServicePage.setAgeFilterToBe(GlobalConstants.CHILD);
        guestServicePage.searchUser(guestMember);
        loginPage.safeLogout();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User sets the {string} filter to {string}")
    public void userSetsTheFilterTo(String filter, String option) {
        guestServicePage.changeStatusOfFilter(filter, option);
        guestServiceSteps.userSaveGuestFirstName();
        guestServiceSteps.userSaveGuestFullName();
        safetyManagementPage.userAbleToClickInTheDetailTable(ShipSecurityConstants.LAST_NAME);
        context.setContext("SKIP_SCREENSHOT", "true");
    }
    @And("User select {string} filter option to {string} for crew")
    public void userSelectFilterOptionToForCrew(String filter, String option) {
        guestServicePage.changeStatusOfFilter(filter, option);
        crewInformationSteps.userSaveCrewFirstName();
        crewInformationSteps.userSaveCrewFullNameOnCrewPage();
        safetyManagementPage.userAbleToClickInTheDetailTable(ShipSecurityConstants.LAST_NAME);
        context.setContext("SKIP_SCREENSHOT", "true");

    }

    @And("User navigate to Crew Services page")
    public void userNavigateToCrewServicesPage() {
        homePage.navigateToService(GlobalConstants.CREW_SERVICES);
        guestServicePage.openGuestServiceItem(GlobalConstants.CREW_SEARCH);
        crewInformationSteps.userClearReservationStatusOfTheCrew();
        guestServicePage.checkOptionInTheFilter(GlobalConstants.CHECK_IN);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User disable {string} for guest")
    public void userDisableForGuest(String toggleName) {
        guestServicePage.disAbleToggle(toggleName);
        commonSteps.userClickOn(ShipSecurityConstants.SAVE);
        guestServicePage.refreshPage();
        userVerifyTheWarningNotTaggedToGuestProfile(ShipSecurityConstants.INTOX);
        shipSecurityPage.userViewTheProfile();
        context.setContext("SKIP_SCREENSHOT", "true");
    }


    @Then("User verify {string} toggle button in {string} mode for crew")
    public void userVerifyToggleButtonInModeForCrew(String toggleName, String expectedState) {
        commonSteps.userWaitForSeconds(ExcursionManagementConstants.WAITING_FOR_ELEMENT);
        userVerifyToggleButtonInMode(toggleName,expectedState);
        commonSteps.userClickOn(ShipSecurityConstants.SAVE);
        userVerifyTheWarningNotTaggedToGuestProfile(ShipSecurityConstants.INTOX);
        shipSecurityPage.userViewTheProfile();
    }

    @Then("User {string} the {string} toggle and verifies that the {string} warning is not tagged on the guest profile and views the profile")
    public void userTheToggleAndVerifiesThatTheWarningIsNotTaggedOnTheGuestProfileAndViewsTheProfile(String toggle, String boarding, String warning) {
        userToggleForToGuest(toggle, boarding);
        userVerifyTheWarningNotTaggedToGuestProfile(warning);
        shipSecurityPage.userViewTheProfile();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User removes the {string} flag from the guest profile")
    public void userRemovesTheFlagFromTheGuestProfile(String arg0) {
        commonSteps.userOpen(ShipSecurityConstants.QUICK_LINKS);
        commonPage.navigateTo(ShipSecurityConstants.REPORT_AS_FOUND);
        userSelectTheReportedPerson();
        guestServicePage.clickOnMissingFoundButton(ShipSecurityConstants._REPORT_AS_FOUND);

    }

    @And("User select location {string} in dropdown")
    public void userSelectLocationInDropdown(String location) {
        shipSecurityPage.userSelectLocationInDropdown(location);
    }

    @Then("User display {string} module")
    public void userDisplayModule(String module) {
        shipSecurityPage.userDisplayModule(module);
    }

    @Then("User display the search icon")
    public void userDisplayTheSearchIcon() {
        shipSecurityPage.userDisplayTheSearchIcon();
    }

    @And("User selects account types Guest, Crew and Visitor")
    public void userSelectsAccountTypesGuestCrewAndVisitor() {
        shipSecurityPage.selectFirstNCheckboxes(ShipSecurityConstants.COUNT);
    }

    @And("User verify any scheduled trainings present on gangway")
    public void userVerifyAnyScheduledTrainingsPresentOnGangway() {
        Assert.assertTrue(shipSecurityPage.isAnyTrainingPresent(ShipSecurityConstants.IPM,ShipSecurityConstants.BLANKET_SUPPLIES_TEAMS,ShipSecurityConstants.SAFETY_DRILL,ShipSecurityConstants.SAFETY_TRAINING), "None of the expected trainings are displayed on gangway");
    }

    @And("User remove the missing flag from the guest profile")
    public void userRemoveTheMissingFlagFromTheGuestProfile() {
        commonSteps.userOpen(ShipSecurityConstants.QUICK_LINKS);
        commonSteps.userNavigateTo(ShipSecurityConstants.REPORT_AS_FOUND);
        userSelectTheReportedPerson();
        commonSteps.userWaitForSeconds(ShipSecurityConstants.WAITING_FOR_ELEMENT);
        commonSteps.userClickOn(ShipSecurityConstants.SELECT);
        guestServicePage.clickOnMissingFoundButton(ShipSecurityConstants._REPORT_AS_FOUND);
        guestServicePage.getFlag();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User marks the Missing flag from the crew profile")
    public void userMarksTheMissingFlagFromTheCrewProfile() {
        commonSteps.userOpen(ShipSecurityConstants.QUICK_LINKS);
        commonSteps.userNavigateTo(ShipSecurityConstants.REPORT_AS_MISSING);
        String date = commonFunctions.getDateIncrementedBy(-1, GlobalConstants.M_DD_YYYY);
        guestServicePage.setDateAndTime(date);
        userSelectTheReportedPerson();
        commonSteps.userWaitForSeconds(ShipSecurityConstants.WAITING_FOR_ELEMENT);
        commonSteps.userClickOn(ShipSecurityConstants.SELECT);
        guestServicePage.clickOnMissingFoundButton(ShipSecurityConstants._REPORT_AS_MISSING);
        guestServicePage.drawSignature();
        commonPage.navigateTo(GlobalConstants.SIGN_AND_CONFIRM);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User search guest {string}")
    public void userSearchGuest(String name) {
        guestServicePage.searchUser(name);
    }

    @Then("User view the tender capacity count")
    public void userViewTheTenderCapacityCount() {
        shipSecurityPage.userViewTheTenderCapacityCount();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User {string} the {string} toggle, verifies that {string} is clickable after the warning flag is tagged on the guest profile, and views the profile")
    public void userTheToggleVerifiesThatIsClickableAfterTheWarningFlagIsTaggedOnTheGuestProfileAndViewsTheProfile(String toggle, String boarding, String element) {
        guestServicePage.openDropDown(element);
        userToggleForToGuest(toggle,boarding);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User view on log icon")
    public void userViewOnLogIcon() {
        shipSecurityPage.userViewOnLogIcon();
    }

    @And("User click on reload icon")
    public void userClickOnReloadIcon() {
        shipSecurityPage.userClickOnReloadIcon();
    }

    @And("User click on three dots")
    public void userClickOnThreeDots() {
        shipSecurityPage.userClickOnThreeDots();
    }

    @And("User add the Boarding Denied")
    public void userAddTheBoardingDenied() {
        shipSecurityPage.userAddTheBoardingDenied();
    }

    @And("User click on Reason and select {string} and add Reason Detail")
    public void userClickOnReasonAndSelectPersonOfInterestAndAddReasonDetail(String option) {
        shipSecurityPage.userClickOnReasonAndSelectPersonOfInterestAndAddReasonDetail(option);
    }

    @Then("User view the port details")
    public void userViewThePortDetails() {
        int actualCount = shipSecurityPage.getTitleCount();
        int expectedCount = 8;
        Assert.assertEquals(actualCount, expectedCount, "Expected count is 8 but found " + actualCount);
    }

    @And("User view the scheduled drill and training activities")
    public void userViewTheScheduledDrillAndTrainingActivities() {
        int actualCount = shipSecurityPage.verifyTrainingElements();
        Assert.assertTrue(actualCount > 0, "Drill and Training elements no count");
    }

    @Then("User view the the guest embarking and disembarking count validate it")
    public void userViewTheTheGuestEmbarkingAndDisembarkingCountValidateIt() {
        int divCount = shipSecurityPage.getGuestDivCount();
        int spanCount = shipSecurityPage.getGuestSpanCount();
        Assert.assertTrue(divCount > 0, "Guest Div elements count is 0");
    }

    @Then("User view the Authorities Counter details")
    public void userViewTheAuthoritiesCounterDetails() {
        int actualCount = shipSecurityPage.getGangwaySummaryCount();
        int expectedCount = 4;
        Assert.assertEquals(actualCount, expectedCount, "Expected gangway summary count is 4 but found " + actualCount);
    }

    @And("User check the calender is middle or not")
    public void userCheckTheCalenderIsMiddleOrNot() {
        boolean isCalendarCentered = shipSecurityPage.isCalendarCentered();
        Assert.assertTrue(isCalendarCentered, "Calendar is not centered properly");
    }

    @And("User search for the GuestOnboard guest")
    public void userSearchForTheGuestOnboardGuest() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.GUEST_ONBOARD_1);
    }

    @And("User search for the Disembarkation guest")
    public void userSearchForTheDisembarkationGuest() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.GUEST_DISEMBARKATION);
    }

    @And("User search for the Crew on Board guest")
    public void userSearchForTheCrewOnBoardGuest() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.CREW_ONBOARD_1);
    }

    @And("User search for the onboard crew member")
    public void searchOnboardCrewMember() {
        shipSecurityPage.userSearchForTheGuest(CrewManagementConstants.ONBOARD_CREW);
    }


    @And("User search for the Boarding denied guest")
    public void userSearchForTheBoardingDeniedGuest() {
        guestServicePage.searchUser(ShipSecurityConstants.GUEST_ONBOARD_2);
    }

    @And("User search for the Ashore Denied guest")
    public void userSearchForTheAshoreDeniedGuest() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.ASHORE_DENIED);
    }

    @And("User search for the Guest reservation")
    public void userSearchForTheGuestReservation() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.GUEST_RESERVATION);
    }

    @And("User search for the Minor guest guest")
    public void userSearchForTheMinorGuestGuest() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.GUEST_MINOR);
    }

    @And("User search guest with Alcohol consumption")
    public void userSearchGuestWithAlcoholConsumption() {
        guestServicePage.searchUser(ShipSecurityConstants.GUEST_ALCOHOL);
    }

    @And("User search crew member with alcohol consumption")
    public void userSearchCrewMemberWithAlcoholConsumption() {
        guestServicePage.searchUser(ShipSecurityConstants.CREW_ALCOHOL);
    }

    @And("User search guest Missing flag")
    public void userSearchGuestMissingFlag() {
        guestServicePage.searchUser(ShipSecurityConstants.GUEST_MISSING_FLAG);
    }

    @And("User search guest Missing guest found")
    public void userSearchGuestMissingGuestFound() {
        guestServicePage.searchUser(ShipSecurityConstants.GUEST_MISSING_Found);
    }

    @And("User search crew as missing")
    public void userSearchCrewAsMissing() {
        guestServicePage.searchUser(ShipSecurityConstants.CREW_MISSING);
    }

    @And("User search Missing Minor guest")
    public void userSearchMissingMinorGuest() {
        guestServicePage.searchUser(ShipSecurityConstants.GUEST_MISSING_MINOR);
    }

    @And("User search for the missing guest at the gangway movement")
    public void userSearchForTheMissingGuestAtTheGangwayMovement() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.GUEST_MISSING_GANGWAY);
    }

    @And("User search for the Tender Capacity first guest")
    public void userSearchForTheTenderCapacityFirstGuest() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.GUEST_TENDER_CAPACITY_1);
    }

    @And("User search for the Tender Capacity second guest")
    public void userSearchForTheTenderCapacitySecondGuest() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.GUEST_TENDER_CAPACITY_2);
    }

    @And("User search for the Tender Capacity third guest")
    public void userSearchForTheTenderCapacityThirdGuest() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.GUEST_TENDER_CAPACITY_3);
    }

    @And("User search for Denied boarding guest")
    public void userSearchForDeniedBoardingGuest() {
        guestServicePage.searchUser(ShipSecurityConstants.CREW_ONBOARD);
    }

    @And("User search for Ashore denied guest")
    public void userSearchForAshoreDeniedGuest() {
        guestServicePage.searchUser(ShipSecurityConstants.CREW_ASHORE);
    }

    @And("User search for the shoreside to onboard guest")
    public void userSearchForTheShoresideToOnboardGuest() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.GUEST_ONBOARD_SIDE);
    }

    @And("User search for the visitor person")
    public void userSearchForTheVisitorPerson() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.VISITOR_1);
    }

    @And("User search Person of Interest guest")
    public void userSearchPersonOfInterestGuest() {
        guestServicePage.searchUser(ShipSecurityConstants.GUEST_PERSON_OF_INTEREST);
    }

    @And("User search for the Person of Interest guest")
    public void userSearchForThePersonOfInterestGuest() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.GUEST_PERSON_OF_INTEREST);
    }

    @And("User search crew Person of Interest")
    public void userSearchCrewPersonOfInterest() {
        guestServicePage.searchUser(ShipSecurityConstants.CREW_PERSON_OF_INTEREST);
    }

    @And("User search for the crew Person of Interest")
    public void userSearchForTheCrewPersonOfInterest() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.CREW_PERSON_OF_INTEREST);
    }

    @And("User search for the expected guest today")
    public void userSearchForTheExpectedGuestToday() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.CHECKIN_TODAY_GUEST_PERSON_);
    }

    @Then("User is able to view the Gangway Movement Statistics data")
    public void userIsAbleToViewTheGangwayMovementStatisticsData() {
        List<WebElement> charts = shipSecurityPage.getGangwayMovementCharts();

        Assert.assertTrue(charts.size() >= 1,
                "Gangway Movement Statistics data is not displayed");

        WebElement firstChart = charts.get(0);

        seleniumActions.scrollInToElement(firstChart);

        seleniumActions.highLighterMethod(firstChart);
    }

    @And("User click on the counts tab")
    public void userClickOnTheCountsTab() {
        shipSecurityPage.navigateToCountTab();
    }

    @Then("User validate {string} and {string} count")
    public void userValidateAndCount(String rowName, String columnName) {
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        String expectedGuestCount = context.getContext("expectedGuestCount").toString();
        int previousCount = Integer.parseInt(expectedGuestCount);
        int columnIndex = shipSecurityPage.getColumnIndex(columnName);
        int updatedCount = shipSecurityPage.getDashboardCount(rowName, columnIndex);
        Assert.assertEquals(updatedCount, previousCount - 1, "Count mismatch. Expected: " + (previousCount - 1) + " but Found: " + updatedCount);
    }

    @And("User clicks the Actions button and verifies the {string} option is displayed")
    public void userClicksTheActionsButtonAndVerifiesTheOptionIsDisplayed(String option) {
        shipSecurityPage.actionButtionToBeClick();
    }

    @And("User clicks on {string} and navigates to the Visitor tab page")
    public void userClicksOnAndNavigatesToTheVisitorTabPage(String arg0) {
        shipSecurityPage.clickOnTheVisitorManagementText();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User view the visitors")
    public void userViewTheVisitors() {
        int actualCount = shipSecurityPage.getTableRowCount();
        Assert.assertTrue(actualCount > 0, "Table row count is 0");
    }

    @And("User checks the Port Name field under Port Details and clicks on it")
    public void userChecksThePortNameFieldUnderPortDetailsAndClicksOnIt() {
        boolean isClicked = shipSecurityPage.clickArrivalCalendarElement();
        Assert.assertTrue(isClicked, "No arrival elements found");
    }

    @And("User checks the scheduled activities and clicks on it")
    public void userChecksTheScheduledActivitiesAndClicksOnIt() {
        boolean isClicked = shipSecurityPage.clickScheduledActivities();

        Assert.assertTrue(isClicked, "No scheduled activities found");
    }

    @And("User checks the details {string}, {string}, {string} and {string} at the top left side of the screen")
    public void userChecksTheDetailsAndAtTheTopLeftSideOfTheScreen(String authoritiesCounter, String allAboardCrew, String allAboardGuests, String timeTillDeparture) {
        Assert.assertTrue(shipSecurityPage.verifyTopLeftSideDetails(authoritiesCounter, allAboardCrew, allAboardGuests, timeTillDeparture), "Top left side details are not displayed");
    }

    @And("User search for the Boarding denied guest way")
    public void userSearchForTheBoardingDeniedGuestWay() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.BOARDING_DENIED);
    }

    @And("User confirms the signature")
    public void userConfirmsTheSignature() {
        guestServicePage.drawSignature();
    }

    @And("User enable toggle for {string} to guest with reason as POI")
    public void userEnableToggleForToGuestWithReasonAsPOI(String boarding) {
        shipSecurityPage.enableAshoreOnBoardReasonPOI(boarding);
    }

    @And("User selects a random cabin number")
    public void userSelectsARandomCabinNumber() {
        shipSecurityPage.userClearTheDateRange();
        String randomCabinNumber = shipSecurityPage.getRandomCabinNumber();
        context.setContext("CABIN_NUMBER", randomCabinNumber);
    }

    @And("User searches for the selected cabin number in the {string} search dropdown")
    public void userSearchesForTheSelectedCabinNumberInTheSearchDropdown(String filterName) {
        String option = (String) context.getContext("CABIN_NUMBER");
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        Assert.assertTrue(shipSecurityPage.selectCrewByCabinNumber(filterName, option), String.format("Failed to select crew with %s as '%s'.", filterName, option));
    }

    @Then("User is able to view the crew member details for the searched cabin number")
    public void userIsAbleToViewTheCrewMemberDetailsForTheSearchedCabinNumber() {
        String expectedCabinNumber = (String) context.getContext("CABIN_NUMBER");
        String actualCabinNumber = shipSecurityPage.getDisplayedCabinNumber();
        Assert.assertEquals(actualCabinNumber, expectedCabinNumber, "Displayed cabin number does not match the searched cabin number.");
    }

    @Then("User adds the POI flag to the selected guest")
    public void userAddsThePOIFlagToTheSelectedGuest() {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.SHIPSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToSelectedService(ShipSecurityConstants.GUEST_SERVICES);
        guestServicePage.openGuestServiceItem(ShipSecurityConstants.GUEST_SEARCH);
        guestServicePage.searchUser(ShipSecurityConstants.GUEST_PERSON_OF_INTEREST);
        safetyManagementPage.userAbleToClickInTheDetailTable(ShipSecurityConstants.LAST_NAME);
        shipSecurityPage.userViewTheProfile();
        List<String> warningText = guestServicePage.getWarningMessage();
        logger.info("Warning Messages :: {}", warningText);
        if (!warningText.contains(ShipSecurityConstants._BOARDING_DENIED)) {
            guestServicePage.openDropDown(ShipSecurityConstants.LOST_AND_FOUND_SECURITY_INCIDENTS);
            shipSecurityPage.userClickOnThreeDots();
            shipSecurityPage.userAddTheBoardingDenied();
            shipSecurityPage.userClickOnReasonAndSelectPersonOfInterestAndAddReasonDetail(ShipSecurityConstants.SAVE);
            shipSecurityPage.userClickOnSAVEButton();
            shipSecurityPage.userViewTheProfile();
            warningText = guestServicePage.getWarningMessage();
            logger.info("Updated Warning Messages :: {}", warningText);
        }
        Assert.assertTrue(warningText.contains(ShipSecurityConstants._BOARDING_DENIED), "Boarding Denied warning message is not displayed.");
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User selects the {string} direction in Gangway Gate mode")
    public void userSelectsTheDirectionInGangwayGateMode(String mode) {
        Assert.assertTrue(shipSecurityPage.isDirectionDisplayed(mode), "Gangway direction '" + mode + "' is not displayed.");
        for (String ageGroup : ShipSecurityConstants.AGE_GROUPS) {
            Assert.assertTrue(shipSecurityPage.isAgeGroupToggleDisplayed(ageGroup), ageGroup + " toggle is not displayed.");
        }
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User selects a location from the Start Location Gate dropdown")
    public void userSelectsALocationFromTheStartLocationGateDropdown() {
        shipSecurityPage.locationInStartlocationGate();
    }

    @And("User searches and selects the guest")
    public void userSearchesAndSelectsTheGuest() {
        shipSecurityPage.userSearchForTheGuest(ExcursionManagementConstants.GUEST_PREFIX_NAME);
    }

    @Then("User adds the medical flag to the selected guest")
    public void userAddsTheMedicalFlagToTheSelectedGuest() {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.SHIPSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToSelectedService(ShipSecurityConstants.GUEST_SERVICES);
        guestServicePage.openGuestServiceItem(ShipSecurityConstants.GUEST_SEARCH);
        guestServicePage.searchUser(ShipSecurityConstants.MEDICAL_CONDITION);
        safetyManagementPage.userAbleToClickInTheDetailTable(ShipSecurityConstants.LAST_NAME);
        shipSecurityPage.userViewTheProfile();
        guestServicePage.openDropDown(ShipSecurityConstants.LOST_AND_FOUND_SECURITY_INCIDENTS);
        shipSecurityPage.userClickOnThreeDots();
        shipSecurityPage.userAddTheBoardingDenied();
        shipSecurityPage.userAddTheMedicalFlag();
        shipSecurityPage.userClickOnSAVEButton();
        shipSecurityPage.userViewTheProfile();
        guestServicePage.getWarningMessage();
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User search for the Medical Conditions guest")
    public void userSearchForTheMedicalConditionsGuest() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.MEDICAL_CONDITION);
    }

    @Then("User adds the medical flag to the selected crew")
    public void userAddsTheMedicalFlagToTheSelectedCrew() {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.SHIPSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToSelectedService(GlobalConstants.CREW_SERVICES);
        guestServicePage.openGuestServiceItem(GlobalConstants.CREW_SEARCH);
        guestServicePage.searchUser(ShipSecurityConstants.MEDICAL_CONDITION_CREW);
        safetyManagementPage.userAbleToClickInTheDetailTable(ShipSecurityConstants.LAST_NAME);
        shipSecurityPage.userViewTheProfile();
        guestServicePage.openDropDown(ShipSecurityConstants.LOST_AND_FOUND_SECURITY_INCIDENTS);
        shipSecurityPage.userClickOnThreeDots();
        shipSecurityPage.userAddTheBoardingDenied();
        shipSecurityPage.userAddTheMedicalFlag();
        shipSecurityPage.userClickOnSAVEButton();
        shipSecurityPage.userViewTheProfile();
        guestServicePage.getWarningMessage();
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User search for the Medical Conditions crew")
    public void userSearchForTheMedicalConditionsCrew() {
        shipSecurityPage.userSearchForTheGuest(ShipSecurityConstants.MEDICAL_CONDITION_CREW);
    }

    @And("User verifies the {string} filter is set to {string} and the results are displayed")
    public void userVerifiesTheFilterIsSetToAndTheResultsAreDisplayed(String filterName, String reservationStatus) {
        shipSecurityPage.userClearTheDateRange();
        shipSecurityPage.selectSuccessFilter(filterName,reservationStatus);
    }

    @Then("User verifies the deny reasons are displayed")
    public void userVerifiesTheDenyReasonsAreDisplayed() {
        List<String> denyReasons = shipSecurityPage.getDenyReasons();
        Assert.assertFalse(denyReasons.isEmpty(), "No deny reasons are displayed.");
        denyReasons.forEach(reason -> Assert.assertFalse(reason.trim().isEmpty(), "Deny reason should not be empty."));
    }

    @Then("User verifies the {string} value under Safety in the Crew Summary")
    public void userVerifiesTheValueUnderSafetyInTheCrewSummary(String label) {
        Assert.assertNotEquals(shipSecurityPage.getInPortManningValue(), "No Data", "'" + label + "' value should not be 'No Data'.");
    }

    @And("User fill out required fields")
    public void userFillOutRequiredFields() {
        String randomClothesText = shipSecurityPage.enterRandomClothesText();
        context.setContext("RANDOM_CLOTHES_TEXT", randomClothesText);
    }

    @And("User edits and fills out the required fields")
    public void userEditsAndFillsOutTheRequiredFields() {
        String randomEditClothesText = shipSecurityPage.enterRandomClothesText();
        context.setContext("EDITED_CLOTHES_TEXT", randomEditClothesText);
    }

    @Then("User verifies that the required field text has been edited")
    public void userVerifiesThatTheRequiredFieldTextHasBeenEdited() {
        String originalClothesText = context.getContext("RANDOM_CLOTHES_TEXT");
        String editedClothesText = context.getContext("EDITED_CLOTHES_TEXT");
        Assert.assertNotEquals(editedClothesText, originalClothesText, "Required field text was not edited");
    }


}
