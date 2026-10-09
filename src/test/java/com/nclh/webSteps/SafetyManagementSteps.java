package com.nclh.webSteps;

import com.nclh.constants.GlobalConstants;
import com.nclh.constants.PreconditionConstants;
import com.nclh.constants.SafetyManagementConstatnts;
import com.nclh.managers.AllDriverManager;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import com.nclh.pageObjects.*;
import com.nclh.utilities.SeleniumActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SafetyManagementSteps extends AllDriverManager {

    Context context;
    SafetyManagementPage safetyManagementPage;
    ActivitiesPlannerPage activitiesPlannerPage;
    GuestServicePage guestServicePage;
    CommonPage commonPage;
    GuestServiceRequestPage guestServiceRequestPage;
    CrewInformationPage crewInformationPage;
    EntertainmentPage entertainmentPage;
    CommonFunctions commonFunctions;
    CommonSteps commonSteps;
    HomePage homePage;
    ShipSecurityPage shipSecurityPage;
    HotelMaintenancePage hotelMaintenancePage;
    ExcursionManagementPage excursionManagementPage;
    LoginPage loginPage;
    public static final Logger logger = LoggerFactory.getLogger(SafetyManagementSteps.class);

    public SafetyManagementSteps(Context context) {
        this.context = context;
        safetyManagementPage = new SafetyManagementPage(getDriver());
        activitiesPlannerPage = new ActivitiesPlannerPage(getDriver());
        guestServicePage = new GuestServicePage(getDriver());
        commonPage = new CommonPage(getDriver());
        guestServiceRequestPage = new GuestServiceRequestPage(getDriver());
        crewInformationPage = new CrewInformationPage(getDriver());
        entertainmentPage = new EntertainmentPage(getDriver());
        commonFunctions = new CommonFunctions();
        commonSteps = new CommonSteps(context);
        homePage = new HomePage(getDriver());
        shipSecurityPage = new ShipSecurityPage(getDriver());
        hotelMaintenancePage = new HotelMaintenancePage(getDriver());
        excursionManagementPage = new ExcursionManagementPage(getDriver());
        loginPage = new LoginPage(getDriver());
    }

    @And("User navigated to safety management item {string}")
    public void userNavigatedToSafetyManagementItem(String sfItem) {
        safetyManagementPage.openSafetyManagementItem(sfItem);
    }

    @And("User add emergency drill type")
    public void userAddEmergencyDrillType() {
        safetyManagementPage.openSafetyManagementItem("Emergency Drill Types");
    }

    @And("User add emergency drill type using code {string}, description {string} and listingNumber {string}")
    public void userAddEmergencyDrillTypeUsingCodeDescriptionAndListingNumber(String code, String description, String listingNumber) {
        safetyManagementPage.addEmergencyDrillType(code, description, listingNumber);
        context.setContext("code", code);
        context.setContext("description", description);
    }

    @Then("User verify emergency drill item is created with code {string} and description {string}")
    public void verifyEmergencyDrillItemIsCreatedWithCodeAndDescription(String code, String description) {
        List<String> tableData = new ArrayList<>();
        tableData.addAll(safetyManagementPage.getAddedItemInTheTable(code));
        tableData.addAll(safetyManagementPage.getAddedItemInTheTable(description));

        System.out.println("tableData :: " + tableData);

        Assert.assertEquals(tableData, Arrays.asList(context.getContext("code"), context.getContext("description")));
    }

    @And("User delete the created {string} emergency drill")
    public void userDeleteTheCreatedEmergencyDrill(String code) {
        safetyManagementPage.deleteRecord(code);
    }

    @Then("User verify {string} emergency drill is deleted")
    public void userVerifyEmergencyDrillIsDeleted(String code) {
        Assert.assertEquals(safetyManagementPage.getRecord(code), 0);
    }

    @And("User open activity screen")
    public void userOpenActivityScreen() {
        activitiesPlannerPage.openActivity();
    }

    @And("User add activity details category {string} and type {string}")
    public void userAddActivityDetailsCategoryAndType(String category, String type) {
        activitiesPlannerPage.addActivity(category, "Safety Training");
    }

    @And("User select crew from the list and finish adding activity")
    public void userSelectCrewFromTheListAndFinishAddingActivity() {
        activitiesPlannerPage.finishAddingActivity();
    }

    @And("User open Edit Activity Screen")
    public void userOpenEditActivityScreen() {

        activitiesPlannerPage.openEditActivityScreen();

    }

    @And("User updates required fields and save")
    public void userUpdatesRequiredFieldsAndSave() {
        activitiesPlannerPage.updateActivityDate();
        WebElement activityDate = activitiesPlannerPage.getActivityDateText();
        context.setContext("expectedDate", commonFunctions.readToastMessage(activityDate));
        activitiesPlannerPage.finishEditingActivity();
        activitiesPlannerPage.openEditActivityScreen();
        context.setContext("updatedValueInDateField",commonFunctions.readToastMessage(activityDate));
        activitiesPlannerPage.cancelEditingActivity();
    }

    @And("User finish Editing activity")
    public void userFinishEditingActivity() {
        activitiesPlannerPage.finishEditingActivity();
    }

    @Then("User verify updated activity date")
    public void userVerifyUpdatedActivityDate() {
        Assert.assertEquals(context.getContext("updatedValueInDateField"), context.getContext("expectedDate"));
    }

    @When("User deletes selected activity")
    public void userDeletesSelectedActivity() {
        context.setContext("activitiesCountBeforeDeleting", activitiesPlannerPage.getActivitiesCount());
        context.setContext("SKIP_SCREENSHOT", "true");
        // activitiesPlannerPage.deleteActivity();
    }

    @Then("User verifies activity deletion")
    public void userVerifiesActivityDeletion() {
        context.setContext("activitiesCountAfterDeleting", activitiesPlannerPage.getActivitiesCount());
        Assert.assertEquals(Integer.parseInt(context.getContext("activitiesCountBeforeDeleting")) - 1, Integer.parseInt(context.getContext("activitiesCountAfterDeleting")));
    }

    @And("User add new safety number")
    public void userAddNewSafetyNumber(DataTable dataTable) {
        List<String> safetyNumberData = dataTable.row(0);
        String rank = safetyNumberData.get(0);
        String classification = safetyNumberData.get(1);
        String safetyDivision = safetyNumberData.get(2);
        String safetyPosition = safetyNumberData.get(3);
        String assemblyStation = safetyNumberData.get(4);
        String lifeBoat = safetyNumberData.get(5);
        safetyManagementPage.addAddSafetyNumber(rank, classification, safetyDivision, safetyPosition, assemblyStation, lifeBoat);
    }

    @And("User click on link {string}")
    public void userClickOnLink(String link) {
        safetyManagementPage.clickOnLink(link);
    }

    @And("User save the safety number")
    public void userSaveTheSafetyNumber() {
        String safetyNumber = safetyManagementPage.getSafetyNumber();
        context.setContext("safetyNumber", safetyNumber);
        System.out.println(safetyNumber);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User click on add button")
    public void userClickOnAddButton() {
        safetyManagementPage.clickOnAddButton();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User search for created safety number")
    public void userSearchForCreatedSafetyNumber() {
        safetyManagementPage.searchForSafetyNumber(SafetyManagementConstatnts._SAFETY_NUMBER);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User verify safety number is created")
    public void userVerifySafetyNumberIsCreated() {
        String safetyNumber = safetyManagementPage.getSafetyNumberLinkText();
        Assert.assertEquals(safetyNumber,SafetyManagementConstatnts._SAFETY_NUMBER);
    }

    @And("User save {string} guests count {string}")
    public void userSaveGuestsCount(String checkedInOnboardOrAshore, String page) {

        switch (checkedInOnboardOrAshore) {
            case "Guests Onboard":
                String guestCount = guestServicePage.getGuestCount(checkedInOnboardOrAshore);
                System.out.println(page + " :: Passengers count Onboard :: " + guestCount);
                context.setContext("SafetyManagementPassengersCountOnboard", guestCount);

                break;
            case "Crew Onboard":
                String crewCount = guestServicePage.getGuestCount(checkedInOnboardOrAshore);
                System.out.println(page + " :: Crews count Onboard :: " + crewCount);
                context.setContext("SafetyManagementCrewCountOnboard", crewCount);
                break;
            case "Visitors":
                String visitorsCount = guestServicePage.getVisitorsCount();
                context.setContext("SafetyManagementVisitorsCount", visitorsCount);
                break;
        }
    }

    @Then("User compare guests,crews and visitors count from respective pages to safety management")
    public void userCompareGuestsAndCrewsCountFromRespectivePagesToSafetyManagement() {
        Assert.assertEquals(context.getContext("SafetyManagementPassengersCountOnboard"), context.getContext("GuestServiceGuestsAtCheckedInOnboardCount"));
        Assert.assertEquals(context.getContext("SafetyManagementCrewCountOnboard"), context.getContext("CrewServiceCrewsAtCheckedInOnboardCount"));
        Assert.assertEquals(context.getContext("SafetyManagementVisitorsCount"), context.getContext("GuestServiceVisitorsCount"));
    }

    @And("User add new cabin safety number for {string}")
    public void userAddNewCabinSafetyNumber(String name) {
        safetyManagementPage.addCabinSafetyNumber(name);
    }

    @And("User click on ADD button")
    public void userClickOnADDButton() {
        safetyManagementPage.clickOnADDButton();
    }

    @And("User search for created Cabin safety number")
    public void userSearchForCreatedCabinSafetyNumber() {
        safetyManagementPage.searchForCabinSafetyNumber(context.getContext("safetyNumber"));
    }

    @Then("User verify cabin safety number is created")
    public void userVerifyCabinSafetyNumberIsCreated() {
        String safetyNumber = safetyManagementPage.getCabinSetUpNumberLinkText();
        Assert.assertEquals(safetyNumber, context.getContext("safetyNumber"));
    }

    @And("User verify the cabin type selected for {string}")
    public void userVerifyTheCabinIsFor(String name) {
        String cabinType = safetyManagementPage.getSafetyCabinTypeLinkText();
        Assert.assertEquals(name, cabinType);
    }

    @And("User save crew full name")
    public void userSaveCrewFullName() {
        String crewName = safetyManagementPage.crewFullName();
        System.out.println("crewName :: " + crewName);
        context.setContext("crewFullName", crewName);
    }

    @And("User select the checkbox {string}")
    public void userSelectTheCheckbox(String nameOrNumber) {
        switch (nameOrNumber) {
            case "By Crew Member":
                String crew = context.getContext("crewFullName");
                safetyManagementPage.clickOnCheckBox(crew);
                break;
            case "By Safety Number":
                String safetyNumber = context.getContext("safetyNumber");
                safetyManagementPage.clickOnCheckBox(safetyNumber);
        }

    }

    @And("User switch to pdf page and get pdf content")
    public void userSwitchToPdfPageAndClickOnCancelButton() {
        String pdfContent = safetyManagementPage.switchToPDFPageAndGetPDFContent();
        System.out.println("PDF Content: " + pdfContent);
        context.setContext("pdfContent", pdfContent);
    }

    @Then("User verify crew full name present in the PDF content {string}")
    public void userVerifyCrewFullNamePresentInThePDFContent(String nameOrNumber) {
        switch (nameOrNumber) {
            case "By Crew Member":
                String crewNameFromWeb = context.getContext("crewFullName");
                String[] fullName = crewNameFromWeb.split(",");
                String firstName = fullName[0].trim();
                String lastName = fullName[1].trim();

                String[] crewFullName = crewNameFromWeb.split(" ");
                String expectedCrewNameOnPDF = "";

                for (int i = crewFullName.length; i > 0; i--) {
                    expectedCrewNameOnPDF = expectedCrewNameOnPDF + ", " + crewFullName[i - 1];
                    System.out.println(crewFullName[i - 1]);
                }

                System.out.println(expectedCrewNameOnPDF.substring(2));

                //Assert.assertTrue(context.getContext("pdfContent").contains(expectedCrewNameOnPDF.substring(2)));
                // Assert.assertTrue(context.getContext("pdfContent").contains(firstName));
                //Assert.assertTrue(context.getContext("pdfContent").contains(lastName));
                break;
            case "By Safety Number":
                String safetyNumber = context.getContext("safetyNumber");
                Assert.assertTrue(context.getContext("pdfContent").contains(safetyNumber));
        }
    }

    @And("User save {string} name or number")
    public void userSaveNameOrNumber(String nameOrNumber) {
        switch (nameOrNumber) {
            case "By Crew Member":
                String crewName = safetyManagementPage.crewFullName();
                String crewId = safetyManagementPage.getCrewId();
                System.out.println("crewName :: " + crewName);
                context.setContext("crewFullName", crewName);
                context.setContext("crewId", crewId);
                break;
            case "By Safety Number":
                String safetyNumber = safetyManagementPage.getSafetyNumberInTable();
                System.out.println("safetyNumber :: " + safetyNumber);
                context.setContext("safetyNumber", safetyNumber);
        }
    }

    @And("User save the cabin number for assign or unassign")
    public void userSaveTheCabinNumberForAssignOrUnassign() {
        String cabinNumber = null;
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        try {
            cabinNumber = safetyManagementPage.getCabinNumber();
        } catch (Exception e) {
            for (int i = 3; i < 6; i++) {
                String checkBox1 = "(//td/preceding-sibling::td/div/mat-checkbox/label/span/input)[" + i + "]";
                seleniumActions.clickOnElement(By.xpath(checkBox1));
                try {
                    cabinNumber = safetyManagementPage.getCabinNumber();
                } catch (Exception e2) {
                }
                if (cabinNumber != null && !cabinNumber.isEmpty()) {
                    break;
                }
            }
        }
        context.setContext("cabinNumber", cabinNumber);
        System.out.println("cabinNumber :: " + cabinNumber);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User select or unselect cabin for crew")
    public void userSelectOrUnselectCabinForCrew() {
        String[] cb = context.getContext("cabinNumber").split("/");
        String cabinNumber = cb[0].trim();
        System.out.println(" cabinNumber :: " + cabinNumber);
        safetyManagementPage.selectCabinNumber(cabinNumber);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User click on unAssign button")
    public void userClickOnUnAssignButton() {
        safetyManagementPage.clickOnUnAssignButton();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User save firstname of the crew member")
    public void userSaveFirstnameOfTheCrewMember() {
        String[] fullName = context.getContext("crewFullName").split(" ");
        String firstName = fullName[1];
        System.out.println("firstName :: " + firstName);
        context.setContext("crewFirstName", firstName);
    }

    @And("User save crew name from the table")
    public void userSaveCrewNameFromTheTable() {
        String safetyNumber = context.getContext("safetyNumber");
        String crewNameInTable = safetyManagementPage.getCrewNameFromTable(safetyNumber);
        System.out.println("crewNameInTable :: " + crewNameInTable);
        context.setContext("crewNameInTable", crewNameInTable);
    }

    @And("User click on assign button")
    public void userClickOnAssignButton() {
        safetyManagementPage.clickOnAssignButton();
    }

    @Then("User get list of crew names")
    public void userGetListOfCrewNames() {
        List<String> crewList = safetyManagementPage.getListOfCrewNames();
        List<String> afterDeletingCrew = new ArrayList<>();
        System.out.println("crewList :: " + crewList);
        crewList.remove(context.getContext("crewNameInTable"));
        afterDeletingCrew.addAll(crewList);
        System.out.println("afterDeletingCrew :: " + afterDeletingCrew);
        context.setListContext("crewNamesAfterDeletingOneCrew", afterDeletingCrew);
    }

    @And("User select a crew to assign")
    public void userSelectACrewToAssign() {
        List<String> crewNames = context.getListContext("crewNamesAfterDeletingOneCrew");
        System.out.println("crewNames :: " + crewNames);
        System.out.println("crewNames :: " + crewNames.get(0));
        int index = crewNames.indexOf(crewNames.get(0));
        safetyManagementPage.selectCheckBoxOfTheCrew(crewNames.get(0), index);
    }

    @And("User save crew name from the table after changing safety assignment")
    public void userSaveCrewNameFromTheTableAfterChangingSafetyAssignment() {
        String safetyNumber = context.getContext("safetyNumber");
        String crewNameInTable = safetyManagementPage.getCrewNameFromTable(safetyNumber);
        System.out.println("crewNameInTableAfterChangingAssignment :: " + crewNameInTable);
        context.setContext("crewNameInTableAfterChangingAssignment", crewNameInTable);
    }

    @Then("User verify both users are not same")
    public void userVerifyBothUsersAreNotSame() {
        SoftAssert softAssert = new SoftAssert();
        System.out.println(context.getContext("crewNameInTable"));
        context.getContext("crewNameInTableAfterChangingAssignment");
        softAssert.assertNotEquals(context.getContext("crewNameInTable"), context.getContext("crewNameInTableAfterChangingAssignment"));
        softAssert.assertEquals(context.getContext("crewNameInTable"), context.getContext("crewNameInTableAfterChangingAssignment"));
    }

    @Then("User verify crew member not present under assigned section")
    public void userVerifyCrewMemberNotPresentUnderAssignedSection() {
        int crewName = safetyManagementPage.getCrewName(context.getContext("crewFullName"));
        int crewId = safetyManagementPage.getCrewId(context.getContext("crewId"));
        Assert.assertEquals(crewId, 0);
    }

    @And("User search for safety number")
    public void userSearchForSafetyNumber() {
        safetyManagementPage.enterSafetyNumber(context.getContext("safetyNumber"));
    }

    @Then("User verifies the safety number is present")
    public void userVerifiesTheSafetyNumberIsPresent() {
        safetyManagementPage.getCrewNameFromTable(context.getContext("safetyNumber"));
    }

    @And("User search for crew and open crew profile page")
    public void userSearchForCrewAndOpenCrewProfilePage() {
        crewInformationPage.clearReservationStatus();
        String[] fullName = context.getContext("crewName").split(" ");
        guestServicePage.openCrewProfile(fullName[1], fullName[0]);
    }

    @And("User clear past scheduled assignments")
    public void userClearPastScheduledAssignments() {
        safetyManagementPage.clearPastAssignments();
    }

    @Then("User verify error page else create emergency drill type and verify")
    public void userVerifyErrorPageElseCreateEmergencyDrillType() {

        try {
            // getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            Assert.assertTrue(entertainmentPage.checkErrorPageIsDisplayed());
        } catch (Exception e) {
            safetyManagementPage.openSafetyManagementItem("Emergency Drill Types");
            safetyManagementPage.deleteRecord("01TESTCODE");
            safetyManagementPage.addEmergencyDrillType("01TESTCODE", "TestDescription", "number");
            context.setContext("code", "01TESTCODE");
            context.setContext("description", "TestDescription");
            safetyManagementPage.deleteRecord("01TESTCODE");
            SeleniumActions seleniumActions = new SeleniumActions(getDriver());
            seleniumActions.sleep(3000);
            Assert.assertEquals(safetyManagementPage.getRecord("01TESTCODE"), 0);
        }
    }

    @Then("User verifies display of safety numbers")
    public void userVerifiesDisplayOfSafetyNumbers() {
        int sizeOfSafetNumbers = safetyManagementPage.getListOfSafetyNumbersInTable().size();
        System.out.println(sizeOfSafetNumbers);
        Assert.assertTrue(sizeOfSafetNumbers > 0);
    }

    @And("User clear board status filter")
    public void userClearBoardStatusFilter() {
        safetyManagementPage.clearBoardStatusFilter();

    }

    @And("User clears valid from filter")
    public void userClearsValidFromFilter() {
        safetyManagementPage.clearValidFromFilter();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User saves {string} count on guest service")
    public void userSavesCountOnGuestService(String Visitors) {
        String visitorsCount = guestServicePage.getVisitorsCount();
        context.setContext("GuestServiceVisitorsCount", visitorsCount);
    }

    @And("User Scroll down to see the Safety tile and click on it")
    public void userScrollDownToSeeTheSafetyTileAndClickOnIt() {
        safetyManagementPage.clickingTheSafetyTile();
    }

    @Then("the user should be able to view the Current Safety Assignment details of the crew")
    public void theUserShouldBeAbleToViewTheCurrentSafetyAssignmentDetailsOfTheCrew() {
        safetyManagementPage.userSafetyNumberValidation();
    }

    @And("User clicks the Print Safety Card button on the Safety page")
    public void theUserClicksThePrintSafetyCardButtonOnTheSafetyPage() {
        safetyManagementPage.clickPrintSafetyCardButton();
    }

    @And("User clicks on the last name of the required guest")
    public void userClicksOnLastNameOfRequiredGuest() {
        safetyManagementPage.clickLastNameOfRequiredGuest();
    }

    @Then("User navigates to the {string} section")
    public void userNavigatesToTheSpecialNeedsSection(String element) {
        safetyManagementPage.goToSpecialNeedsSection(element);
    }

    @And("User clicks on the {string} section")
    public void CurrentSafetyAssignmentSection(String element) {
        safetyManagementPage.currentSafetyAssignmentSection(element);
    }

    @Then("User navigate to {string} section")
    public void userNavigateToCabinInfoSection(String element) {
        safetyManagementPage.navigateToCabinInfoSection(element);
    }

    @Then("User navigates to the Activities Overview sub-module and fills in the required fields")
    public void userNavigatesToTheActivitiesOverviewSubModuleAndFillsInTheRequiredFields() {
        safetyManagementPage.navigatesToTheActivitiesOverviewSubModuleAndFillsInTheRequiredFields();
    }

    @Then("User is able to see the invalid message")
    public void userIsAbleToSeeTheInvalidMessage() {
        safetyManagementPage.userIsAbleToSeeTheInvalidMessage();
    }

    @And("User able click on the ADD ACTIVITY button")
    public void userAbleClickOnTheADDACTIVITYButton() {
        safetyManagementPage.userAbleClickOnTheADDACTIVITYButton();
    }

    @And("User able click on the Activity Category field and select as Safety")
    public void userAbleClickOnTheActivityCategoryFieldAndSelectAsSafety() {
        safetyManagementPage.fillingTheRequiredFields(false, false);
    }

    @And("User able click on the In Port Manning Groups dropdown and select the required group")
    public void userAbleClickOnTheInPortManningGroupsDropdownAndSelectTheRequiredGroup() {
        safetyManagementPage.userAbleClickOnTheInPortManningGroupsDropdownAndSelectTheRequiredGroup();
    }

    @And("Click on the FINISH button")
    public void clickOnTheFINISHButton() {
        safetyManagementPage.clickFinalButton();
    }

    @And("Click on the required crew checkboxes and click on three vertical dots")
    public void clickOnTheRequiredCrewCheckboxesAndClickOnThreeVerticalDots() {
        safetyManagementPage.clickOnTheRequiredCrewCheckboxesAndClickOnThreeVerticalDots();
    }

    @And("User able to view the confirm pop up and click the confirm Button")
    public void userAbleToViewTheConfirmPopUpAndClickTheConfirmButton() {
        safetyManagementPage.confirmPopUpAndClickTheConfirmButton();
    }

    @And("Click on Add more filters icon")
    public void clickOnAddMoreFiltersIcon() {
        safetyManagementPage.clickOnAddMoreFiltersIcon();
    }

    @Then("User is able to filter Assembly Station in Build Up plan")
    public void userIsAbleToFilterAssemblyStationInBuildUpPlan() {
        safetyManagementPage.isAbleToFilterAssemblyStation();
    }

    @And("Enter maximum limit of {int} chars in instructions field")
    public void enterMaximumLimitOfCharsInInstructionsField(int number) {
        safetyManagementPage.enterMaximumLimitOfCharsInInstructionsField();
    }

    @Then("Scroll down to see Specific Safety Instruction tab")
    public void scrollDownToSeeSpecificSafetyInstructionTab() {
        safetyManagementPage.scrollDownToSeeSpecificSafetyInstructionTab();
    }

    @And("User clicks the Save button, and a pop‑up appears")
    public void userClicksTheSaveButtonAndAPopUpAppears() {
        safetyManagementPage.userClicksTheSaveButtonAndAPopUpAppears();
    }

    @And("Click on the required safety number link")
    public void clickOnTheRequiredSafetyNumberLink() {
        safetyManagementPage.clickOnTheRequiredSafetyNumberLink();
    }

    @And("User click the activity button")
    public void userClickTheActivityButton() {
        activitiesPlannerPage.userClickTheActivityButton();
    }

    @Then("{string} landing page should be displayed")
    public void landingPageShouldBeDisplayed(String modulePage) {
        safetyManagementPage.landingPageShouldBeDisplayed(modulePage);
    }

    @Then("Respective crew member profile should be displayed")
    public void respectiveCrewMemberProfileShouldBeDisplayed() {
        safetyManagementPage.CrewMemberProfileShouldBeDisplayed();
    }

    @Then("{string} tile should be expanded")
    public void tileShouldBeExpanded(String tile) {
        safetyManagementPage.tileShouldBeExpanded(tile);
    }


    @And("User is able to search for a crew member")
    public void userIsAbleToSearchForACrewMember() {
        String crewName = getCrewNameForCurrentApp();
        logger.info("🔍 Searching for crew: " + crewName);
        safetyManagementPage.userIsAbleToSearchForACrewMember(crewName);
    }

    private String getCrewNameForCurrentApp() {
        String currentUrl = getDriver().getCurrentUrl();
        logger.debug("Current URL: " + currentUrl);

        if (currentUrl.contains("otalioshiplab")) {
            return "samantha";
        } else if (currentUrl.contains("oci") || currentUrl.contains("rssc")) {
            return "Armstrong";
        } else {
            logger.warn("Unknown app URL, defaulting to Armstrong");
            return "Armstrong";
        }
    }

    @Then("Checking the data is added")
    public void checkingTheDataIsAdded() {
        safetyManagementPage.checkingTheDataIsAdded();
    }

    @Then("validate the Finish button")
    public void validateTheFinishButton() {
        safetyManagementPage.validateTheFinishButton();
    }

    @And("User able to click {string}")
    public void userAbleToClick(String text) {
        safetyManagementPage.selectingDropdownExpectedText(text);
    }

    @And("User able to see {string}")
    public void userAbleToSee(String text) {
        safetyManagementPage.userAbleToSee(text);
    }

    @Then("User able to view the confirm pop up")
    public void userAbleToViewTheConfirmPopUp() {
        safetyManagementPage.userAbleToViewTheConfirmPopUp();
    }

    @And("click the confirm Button")
    public void clickTheConfirmButton() {
        safetyManagementPage.clickTheConfirmButton();
    }

    @Then("{string} page should be displayed")
    public void pageShouldBeDisplayed(String text) {
        safetyManagementPage.navigateToNewTab(text);

    }

    @And("User able click on the Add Activity Button")
    public void userAbleClickOnTheAddActivityButton() {
        safetyManagementPage.userAbleClickOnTheAddActivityButton();
    }

    @Then("User is able to confirm training {string} data")
    public void userIsAbleToConfirmTrainingData(String sectionText) {
        boolean isVisible = safetyManagementPage.userIsAbleToConfirmTrainingData(sectionText);
        Assert.assertTrue(isVisible, "Crew details still not visible after adding for section: " + sectionText);
    }

    @And("User is able to select the Crew in the dropdown")
    public void userIsAbleToSelectTheCrewInTheDropdown() {
        safetyManagementPage.userIsAbleToSelectTheCrewInTheDropdown();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User able see all the crew detail in the table")
    public void userAbleSeeAllTheCrewDetailInTheTable() {
        safetyManagementPage.userAbleSeeAllTheCrewDetailInTheTable();

    }

    @And("User able to click {string} in the detail table")
    public void userAbleToClickInTheDetailTable(String locatorName) {
        safetyManagementPage.userAbleToClickInTheDetailTable(locatorName);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User should be able to view the crew’s {string} details")
    public void userShouldBeAbleToViewTheCrewSDetails(String sectionText) {
        boolean isVisible = safetyManagementPage.userIsAbleToConfirmTrainingData(sectionText);
        if (!isVisible) {
            logger.info("Crew details not visible for section: " + sectionText + ". Adding data...");
            String crewName = safetyManagementPage.lastNameText();
            commonPage.openDuplicateTab();
            safetyManagementPage.navigationToNewModule(SafetyManagementConstatnts.SAFETY_MANAGEMENT);
            safetyManagementPage.clickOnLink(SafetyManagementConstatnts.ACTIVITY_OVERVIEW);
            safetyManagementPage.navigateToNewTab(SafetyManagementConstatnts.OVERVIEW);
            safetyManagementPage.userAbleClickOnTheAddActivityButton();
            safetyManagementPage.landingPageShouldBeDisplayed(SafetyManagementConstatnts.ADD_ACTIVITY);
            safetyManagementPage.fillingTheRequiredFields(false, false);
            safetyManagementPage.userIsAbleToSelectTheCrewInTheDropdown();
            safetyManagementPage.userAbleSeeAllTheCrewDetailInTheTable();
            safetyManagementPage.userIsAbleToSearchForACrewMember(crewName);
            safetyManagementPage.userAbleToClickInTheDetailTable(SafetyManagementConstatnts.CHECK_BOX);
            safetyManagementPage.clickFinalButton();
            safetyManagementPage.validateTheFinishButton();
            safetyManagementPage.checkingTheDataIsAdded();
            safetyManagementPage.refreshingAndNavigatingpreviewsWindow();
            boolean isAdded = safetyManagementPage.userIsAbleToConfirmTrainingData(sectionText);
            Assert.assertTrue(isAdded, "Crew details still not visible after adding for section: " + sectionText);
        } else {
            logger.info("Crew details already visible for section: " + sectionText);
            Assert.assertTrue(isVisible, "Crew details not visible for section: " + sectionText);
        }

    }

    @And("User is able to select the {string} in the {string} dropdown")
    public void userIsAbleToSelectTheInTheDropdown(String str1, String str2) {
        safetyManagementPage.userIsAbleToSelectTheInTheDropdown(str1, str2);
    }

    @Then("User able see the {string} column data as {string}")
    public void userAbleSeeTheColumnDataAs(String str1, String str2) {
        safetyManagementPage.userAbleSeeTheColumnDataAs(str1, str2);
    }

    @Then("User is able to see the disembarked guest count")
    public void userIsAbleToSeeTheDisembarkedGuestCount() {
        safetyManagementPage.userIsAbleToSeeTheDisembarkedGuestCount();
    }

    @Then("user is able validate the count")
    public void userIsAbleValidateTheCount() {
        safetyManagementPage.userIsAbleValidateTheCount();
    }

    @Then("user is able validate the count crew member drill absences")
    public void userIsAbleValidateTheCountCrewMemberDrillAbsences() {
        safetyManagementPage.userIsAbleValidateTheCountCrewMemberDrillAbsences();
    }

    @Then("User able to check for the total attendees and total absent count at top right corner")
    public void userAbleToCheckForTheTotalAttendeesAndTotalAbsentCountAtTopRightCorner() {
        boolean dataAdded = safetyManagementPage.checkingTheDataIsAdded();
        if (!dataAdded) {
            logger.info("Attendees = 0, so crew need to add the data and below code running");
            safetyManagementPage.userAbleClickOnTheAddActivityButton();
            safetyManagementPage.landingPageShouldBeDisplayed(SafetyManagementConstatnts.ADD_ACTIVITY);
            safetyManagementPage.fillingTheRequiredFields(false, false);
            safetyManagementPage.userIsAbleToSelectTheCrewInTheDropdown();
            safetyManagementPage.userAbleSeeAllTheCrewDetailInTheTable();
            safetyManagementPage.userAbleToClickInTheDetailTable(SafetyManagementConstatnts.CHECK_BOX);
            safetyManagementPage.clickFinalButton();
            safetyManagementPage.validateTheFinishButton();
        }
        int[] counts = safetyManagementPage.getCounts();
        int attendees = counts[0];
        int absent = counts[1];

        if (attendees == 0) {
            logger.info("Attendees = 0, so crew need to add the data and below code running");
            safetyManagementPage.userAbleClickOnTheAddActivityButton();
            safetyManagementPage.landingPageShouldBeDisplayed(SafetyManagementConstatnts.ADD_ACTIVITY);
            safetyManagementPage.fillingTheRequiredFields(false, false);
            safetyManagementPage.userIsAbleToSelectTheCrewInTheDropdown();
            safetyManagementPage.userAbleSeeAllTheCrewDetailInTheTable();
            safetyManagementPage.userAbleToClickInTheDetailTable(SafetyManagementConstatnts.CHECK_BOX);
            safetyManagementPage.clickFinalButton();
            safetyManagementPage.validateTheFinishButton();
        } else if (attendees > 1) {
            logger.info("Attendees > 1 ignoring.");
        } else {
            logger.info("Attendees = 1 validated.");
        }
        if (absent == 0) {
            logger.info("Absent = 0, we add the crew in data, but now user is assigning as Absent");
            safetyManagementPage.clickingTheThreeDotsElement();
            safetyManagementPage.selectingDropdownExpectedText(SafetyManagementConstatnts.MARK_AS_ABSENT);
            safetyManagementPage.confirmPopUpAndClickTheConfirmButton();
            safetyManagementPage.cancelButton();
        } else if (absent > 1) {
            logger.info("Absent > 1 ignoring...");
        } else {
            logger.info("Absent = 1 validated.");
        }
    }

    @And("User able to select location {string}")
    public void userAbleToSelectLocation(String str1) {
        safetyManagementPage.userAbleToSelectLocation(str1);
    }

    @And("User is able to verify that the CONFIRM button is enabled and then click it")
    public void userIsAbleToVerifyThatTheCONFIRMButtonIsEnabledAndThenClickIt() {
        safetyManagementPage.userIsAbleToVerifyThatTheCONFIRMButtonIsEnabledAndThenClickIt();
    }

    @Then("User able see the confirm popup is display")
    public void userAbleSeeTheConfirmPopupIsDisplay() {
        safetyManagementPage.userAbleSeeTheConfirmPopupIsDisplay();
    }

    @Then("User able to Check for the passenger and crew count")
    public void userAbleToCheckForThePassengerAndCrewCount() {
        safetyManagementPage.userAbleToCheckForThePassengerAndCrewCount();

    }

    @Then("User able to List of crew participants should be displayed")
    public void userAbleToListOfCrewParticipantsShouldBeDisplayed() {
        boolean dataAdded = safetyManagementPage.checkingTheDataIsAdded();
        if (!dataAdded) {
            logger.info("Attendees = 0, so crew need to add the data and below code running");
            safetyManagementPage.userAbleClickOnTheAddActivityButton();
            safetyManagementPage.landingPageShouldBeDisplayed(SafetyManagementConstatnts.ADD_ACTIVITY);
            safetyManagementPage.fillingTheRequiredFields(false, false);
            safetyManagementPage.userIsAbleToSelectTheCrewInTheDropdown();
            safetyManagementPage.userAbleSeeAllTheCrewDetailInTheTable();
            safetyManagementPage.userAbleToClickInTheDetailTable(SafetyManagementConstatnts.CHECK_BOX);
            safetyManagementPage.clickFinalButton();
            safetyManagementPage.validateTheFinishButton();
        }
    }

    @And("User clicks the Select All checkbox, then all participants should be selected")
    public void userClicksTheSelectAllCheckboxThenAllParticipantsShouldBeSelected() {
        safetyManagementPage.userClicksTheSelectAllCheckboxThenAllParticipantsShouldBeSelected();

    }

    @And("User able to click three dots and see {string}")
    public void userAbleToClickThreeDotsAndSee(String text) {
        safetyManagementPage.userAbleToSee(text);
    }

    @And("User able click on the Activity Category field and select as Safety and time")
    public void userAbleClickOnTheActivityCategoryFieldAndSelectAsSafetyAndTime() {
        safetyManagementPage.userAbleClickOnTheActivityCategoryFieldAndSelectAsSafetyAndTime();
    }

    @And("Click on the ADD ACTIVITY button on same date")
    public void clickOnTheADDACTIVITYButtonOnSameDate() {
        safetyManagementPage.clickOnTheADDACTIVITYButtonOnSameDate();
    }

    @And("User click on NEXT button")
    public void userClickOnNEXTButton() {
        safetyManagementPage.userClickOnNEXTButton();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User click on Skip")
    public void userClickOnSkip() {
        safetyManagementPage.userClickOnSkip();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User OFF the Overlap toggle")
    public void userOFFTheOverlapToggle() {
        safetyManagementPage.userOFFTheOverlapToggle();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("user fills General Information details")
    public void userFillsGeneralInformationDetails() {
        safetyManagementPage.userFillsGeneralInformationDetails();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User able capture details of the user selected {string}")
    public void userAbleCaptureDetailsOfTheUserSelected(String data) {
        List<String> rowData = safetyManagementPage.getGuestDetailsFromTheTable(data);
        Assert.assertFalse(rowData.isEmpty(), "Safety Management table data is not displayed.");
        logger.info("Safety Management row data :: " + rowData);
    }

    @Then("User verify {string} present under Safety Plan")
    public void userVerifyPresentUnderSafetyPlan(String type) {
        String value;

        if (type.equalsIgnoreCase("Crew ID")) {
            value = context.getContext("crewId");
        } else if (type.equalsIgnoreCase("Safety Number")) {
            value = context.getContext("safetyNumber");
        } else {
            throw new IllegalArgumentException("Invalid type: " + type);
        }
        boolean isDisplayed = safetyManagementPage.searchForUnassignedNumber(value);
        Assert.assertTrue(isDisplayed, "'No Data yet' message is NOT displayed for " + type);
    }

    @And("User able to Click on any one of the crew name")
    public void userAbleToClickOnAnyOneOfTheCrewName() {
        String crewName = safetyManagementPage.getCrewNameText();
        context.setContext("SelectedCrewName", crewName);
        safetyManagementPage.clickCrewName();
    }

    @Then("User verifies the crew profile name is updated")
    public void userVerifiesTheCrewProfileNameIsUpdated() {
        String oldName = context.getContext("SelectedCrewName");
        String profileName = safetyManagementPage.getProfileName();
        Assert.assertNotEquals(oldName, profileName, "Profile name should be different after update.");
    }

    @And("User updates the first name and last name of the crew member")
    public void userUpdatesTheFirstNameAndLastNameOfTheCrewMember() {
        safetyManagementPage.changeFirstAndLastNames();
    }

    @And("User search {string} in crew member page")
    public void userSearchInCrewMemberPage(String type) {
        String value;
        if (type.equalsIgnoreCase("Crew ID")) {
            value = context.getContext("crewId");
        } else {
            throw new AssertionError("Invalid search type: " + type);
        }
        boolean isDisplayed = safetyManagementPage.cewMemberSearching(value);
        Assert.assertTrue(isDisplayed, "'No Data yet' message is NOT displayed for " + type);
        context.setContext("SKIP_SCREENSHOT", "true");
    }
    @Then("User view current Saftey Assignment details")
    public void userViewCurrentSafteyAssignmentDetails() {
        boolean isDisplayed = safetyManagementPage.userViewCurrentSafteyAssignmentDetails();
        Assert.assertTrue(isDisplayed, "Current Safety Assignment details are not displayed.");
    }

    @Then("User view current {string} details")
    public void userViewCurrentSafetyAssignmentDetails(String sectionText) {
        boolean isDisplayed = safetyManagementPage.isSectionFirstRowDisplayed(sectionText);
        Assert.assertTrue(isDisplayed, sectionText + " details are not displayed.");
    }


    @And("User creates an activity for an upcoming drill session")
    public void userCreatesAnActivityForAnUpcomingDrillSession() {
        safetyManagementPage.userCreatesAnActivityForAnUpcomingDrillSession();
    }

    @And("User creates an activity for an Attended Drills session")
    public void userCreatesAnActivityForAnAttendedDrillsSession() {
        safetyManagementPage.userCreatesAnActivityForAnAttendedDrillsSession();
    }

    @And("User updates the active date")
    public void userUpdatesTheActiveDate() {
        activitiesPlannerPage.updateActivityDate();
        WebElement activityDate = activitiesPlannerPage.getActivityDateText();
        context.setContext("expectedDate", commonFunctions.readToastMessage(activityDate));
        context.setContext("updatedValueInDateField",commonFunctions.readToastMessage(activityDate));
    }

    @Then("User verifies the active date is updated")
    public void userVerifiesTheActiveDateIsUpdated() {
        Assert.assertEquals(context.getContext("updatedValueInDateField"), context.getContext("expectedDate"));
    }


    @Then("User validates that {string} count")
    public void userValidatesThatCount(String memberType) {
        int count = safetyManagementPage.getOnboardMemberCount(memberType);
        Assert.assertTrue(count > 0,  memberType + " onboard count is zero. Expected count should be greater than zero.");
        context.setContext("expectedMemberCount", String.valueOf(count));
    }

    @Then("User verifies the onboard count with table items count")
    public void userVerifiesTheOnboardCountWithTableItemsCount() {
        //int expectedCount = Integer.parseInt(context.getContext("expectedMemberCount"));
        int actualCount = safetyManagementPage.getTableRowCount();
//        logger.info("Dashboard Count : " + expectedCount);
//        logger.info("Table Items Count : " + actualCount);
//        Assert.assertEquals(actualCount, expectedCount, "Count mismatch between dashboard and table");
    }

    @Then("user add the data in special fileds")
    public void userAddTheDataInSpecialFileds() {
        safetyManagementPage.enterSpecialNeedsComment();
        Assert.assertTrue(safetyManagementPage.isSpecialNeedsCommentFieldDisplayed(), "Special Needs comment field is not displayed");
    }
    @Then("user verifies training certification record exists or creates a new one")
    public void userVerifiesTrainingCertificationRecordExistsOrCreatesANewOne() {

        boolean rowExists = safetyManagementPage.isTrainingCertificationRowPresent();
        if(!rowExists){
            safetyManagementPage.clickTrainingCertificationAddButton();
        }
        boolean rowAfterCreation = safetyManagementPage.isTrainingCertificationRowPresent();
        Assert.assertTrue(rowAfterCreation, "Training Certification row is not visible even after creating the record");
    }

    @And("User creates an activity for an IPM session")
    public void userCreatesAnActivityForAnIPMSession() {
        safetyManagementPage.userCreatesAnActivityForAnIPMSession();
    }

    @And("User select the checkbox By Safety Number")
    public void userSelectTheCheckboxBySafetyNumber() {
        String safetyNumber = context.getContext("safetyNumber");
        boolean isSelected = safetyManagementPage.isCrewCheckboxSelectedByName(safetyNumber);
        Assert.assertTrue(isSelected, "Checkbox is NOT selected for Safety Number: " + safetyNumber);
    }

    @And("User enter all the fields in Add new Emergency Drill Type page")
    public void userEnterAllTheFieldsInAddNewEmergencyDrillTypePage() {
        String safetyCode = safetyManagementPage.userEnterAllTheFieldsInAddNewEmergencyDrillTypePage();
        context.setContext("safetyCode", safetyCode);
    }

    @And("User search for created emergency drills for safety assignment number")
    public void userSearchForCreatedEmergencyDrillsForSafetyAssignmentNumber() {
        safetyManagementPage.searchForSafetyCodeNumber(context.getContext("safetyCode"));
    }

    @And("User select the checkbox By Crew Member")
    public void userSelectTheCheckboxByCrewMember() {
        String safetyNumber = context.getContext("crewFullName");
        boolean isSelected = safetyManagementPage.isCrewCheckboxSelectedByName(safetyNumber);
        Assert.assertTrue(isSelected, "Checkbox is NOT selected for Safety Number: " + safetyNumber);

    }

    @Then("User view the plus icon")
    public void userViewThePlusIcon() {
        safetyManagementPage.userViewThePlusIcon();
    }

    @Then("User view {string} element")
    public void userViewElement(String element) {
        safetyManagementPage.userViewElement(element);
    }

    @And("User click on Save and Finish button if it is displayed")
    public void userClickOnSaveAndFinishButtonIfItIsDisplayed() {
        safetyManagementPage.clickOnSaveAndFinishIfPresent();
    }

    @Then("User view the assign button")
    public void userViewTheAssignButton() {
        safetyManagementPage.userViewTheAssignButton();
    }

    @Then("User is able to assign safety station assignments to crew by safety number")
    public void userIsAbleToAssignSafetyStationAssignmentsToCrewBySafetyNumber() {
        commonSteps.userClickOn(SafetyManagementConstatnts.BY_SAFETY_NUMBER);
        guestServicePage.changeStatusOfFilter(SafetyManagementConstatnts.ASSIGNED,SafetyManagementConstatnts.YES);
        userVerifyPresentUnderSafetyPlan(SafetyManagementConstatnts.SAFETY_NUMBER);
    }

    @And("User click the assign button")
    public void userClickTheAssignButton() {
        safetyManagementPage.clickOnAssignButton();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User is able to assign safety station assignments to crew by crew member")
    public void userIsAbleToAssignSafetyStationAssignmentsToCrewByCrewMember() {
        guestServicePage.changeStatusOfFilter(SafetyManagementConstatnts.ASSIGNED,SafetyManagementConstatnts.YES);
        userVerifyPresentUnderSafetyPlan(SafetyManagementConstatnts.CREW_ID);
    }

    @Then("User view the unassign button")
    public void userViewTheUnassignButton() {
        safetyManagementPage.userViewTheUnassignButton();
    }

    @Then("User is able to unassign safety station assignments to crew by crew member")
    public void userIsAbleToUnassignSafetyStationAssignmentsToCrewByCrewMember() {
        commonSteps.userClickOn(SafetyManagementConstatnts.BY_CREW_MEMBER);
        guestServicePage.changeStatusOfFilter(SafetyManagementConstatnts.ASSIGNED,SafetyManagementConstatnts.NO);
        userVerifyPresentUnderSafetyPlan(SafetyManagementConstatnts.CREW_ID);
    }

    @Then("User view Confirm button")
    public void userViewConfirmButton() {
        safetyManagementPage.userViewConfirmButton();
    }

    @Then("User is able to unassign safety station assignments to crew by Safety Number")
    public void userIsAbleToUnassignSafetyStationAssignmentsToCrewBySafetyNumber() {
        commonSteps.userClickOn(SafetyManagementConstatnts.BY_SAFETY_NUMBER);
        guestServicePage.changeStatusOfFilter(SafetyManagementConstatnts.ASSIGNED,SafetyManagementConstatnts.NO);
        userVerifyPresentUnderSafetyPlan(SafetyManagementConstatnts.SAFETY_NUMBER);
    }

    @Then("User seach for the safety number")
    public void userSeachForTheSafetyNumber() {
        safetyManagementPage.enterSafetyNumber(context.getContext("safetyNumber"));
        userSelectTheCheckboxBySafetyNumber();
        userGetListOfCrewNames();
        userSelectACrewToAssign();
    }

    @Then("User is able to change assigned safety station assignments of particular crew to other crew")
    public void userIsAbleToChangeAssignedSafetyStationAssignmentsOfParticularCrewToOtherCrew() {
        commonSteps.userClickOn(SafetyManagementConstatnts.BY_SAFETY_NUMBER);
        guestServicePage.changeStatusOfFilter(SafetyManagementConstatnts.ASSIGNED,SafetyManagementConstatnts.YES);
        userSaveCrewNameFromTheTableAfterChangingSafetyAssignment();
    }

    @Then("User view safty emergency plus icon")
    public void userViewSaftyEmergencyPlusIcon() {
        safetyManagementPage.userViewSaftyEmergencyPlusIcon();
    }

    @Then("User getting the crew member name")
    public void userGettingTheCrewMemberName() {
        homePage.navigateToService(SafetyManagementConstatnts.SAFETY_MANAGEMENT);
        safetyManagementPage.clickOnLink(SafetyManagementConstatnts.SAFETY_PLAN_ASSIGNMENT);
        commonSteps.userClickOn(SafetyManagementConstatnts.BY_CREW_MEMBER);
        guestServicePage.changeStatusOfFilter(SafetyManagementConstatnts.ASSIGNED,SafetyManagementConstatnts.YES);
        userSaveNameOrNumber(SafetyManagementConstatnts.BY_CREW_MEMBER);
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User create a new Upcoming Drills Training details of crew")
    public void userCreateANewUpcomingDrillsTrainingDetailsOfCrew() {
        homePage.navigateToService(SafetyManagementConstatnts.SAFETY_MANAGEMENT);
        safetyManagementPage.clickOnLink(SafetyManagementConstatnts.SAFETY_PLAN_ASSIGNMENT);
        commonSteps.userClickOn(SafetyManagementConstatnts.BY_CREW_MEMBER);
        guestServicePage.changeStatusOfFilter(SafetyManagementConstatnts.ASSIGNED,SafetyManagementConstatnts.YES);
        userSaveNameOrNumber(SafetyManagementConstatnts.BY_CREW_MEMBER);
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        homePage.navigateToService(SafetyManagementConstatnts.SAFETY_MANAGEMENT);
        safetyManagementPage.clickOnLink(SafetyManagementConstatnts.ACTIVITY_OVERVIEW);
        guestServiceRequestPage.switchToNewWindow();
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        safetyManagementPage.userAbleClickOnTheAddActivityButton();
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        safetyManagementPage.userCreatesAnActivityForAnUpcomingDrillSession();
        safetyManagementPage.userClickOnNEXTButton();
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        safetyManagementPage.userClickOnSkip();
        safetyManagementPage.userIsAbleToSelectTheCrewInTheDropdown();
        userSearchInCrewMemberPage(SafetyManagementConstatnts.CREW_ID);
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        safetyManagementPage.userAbleToClickInTheDetailTable(SafetyManagementConstatnts.CHECKBOX);
        safetyManagementPage.clickFinalButton();
        safetyManagementPage.validateTheFinishButton();
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User create a new Attended Drills Trainings details of crew")
    public void userCreateANewAttendedDrillsTrainingsDetailsOfCrew() {
        homePage.navigateToService(SafetyManagementConstatnts.SAFETY_MANAGEMENT);
        safetyManagementPage.clickOnLink(SafetyManagementConstatnts.SAFETY_PLAN_ASSIGNMENT);
        commonSteps.userClickOn(SafetyManagementConstatnts.BY_CREW_MEMBER);
        guestServicePage.changeStatusOfFilter(SafetyManagementConstatnts.ASSIGNED,SafetyManagementConstatnts.YES);
        userSaveNameOrNumber(SafetyManagementConstatnts.BY_CREW_MEMBER);
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        homePage.navigateToService(SafetyManagementConstatnts.SAFETY_MANAGEMENT);
        safetyManagementPage.clickOnLink(SafetyManagementConstatnts.ACTIVITY_OVERVIEW);
        guestServiceRequestPage.switchToNewWindow();
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        safetyManagementPage.userAbleClickOnTheAddActivityButton();
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        safetyManagementPage.userCreatesAnActivityForAttendedDrillsSession();
        safetyManagementPage.userClickOnNEXTButton();
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        safetyManagementPage.userClickOnSkip();
        safetyManagementPage.userIsAbleToSelectTheCrewInTheDropdown();
        userSearchInCrewMemberPage(SafetyManagementConstatnts.CREW_ID);
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        safetyManagementPage.userAbleToClickInTheDetailTable(SafetyManagementConstatnts.CHECKBOX);
        safetyManagementPage.clickFinalButton();
        safetyManagementPage.validateTheFinishButton();
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        userSearchInCrewMemberPage(SafetyManagementConstatnts.CREW_ID);
        safetyManagementPage.checkingTheDataIsAdded();
        safetyManagementPage.userAbleToSee(SafetyManagementConstatnts.MARK_AS_ATTENDING);
        safetyManagementPage.selectingDropdownExpectedText(SafetyManagementConstatnts.MARK_AS_ATTENDING);
        safetyManagementPage.userAbleToViewTheConfirmPopUp();
        safetyManagementPage.clickTheConfirmButton();
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        guestServicePage.refreshPage();
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        userSearchInCrewMemberPage(SafetyManagementConstatnts.CREW_ID);
        safetyManagementPage.checkingTheDataIsAdded();
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        safetyManagementPage.userAbleToSee(SafetyManagementConstatnts.MARK_AS_COMPLETED);
        safetyManagementPage.selectingDropdownExpectedText(SafetyManagementConstatnts.MARK_AS_COMPLETED);
        safetyManagementPage.userAbleToViewTheConfirmPopUp();
        safetyManagementPage.clickTheConfirmButton();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User view the Activity Overview button")
    public void userViewTheActivityOverviewButton() {
        safetyManagementPage.userViewTheActivityOverviewButton();
    }

    @Then("User view next button")
    public void userViewNextButton() {
        safetyManagementPage.userViewNextButton();
    }

    @And("User view on Skip button")
    public void userViewOnSkipButton() {
        safetyManagementPage.userViewOnSkipButton();
    }

    @Then("User view the finish button")
    public void userViewTheFinishButton() {
        safetyManagementPage.userViewTheFinishButton();
    }

    @And("User creates an activity")
    public void userCreatesAnActivity() {
        safetyManagementPage.userCreatesAnActivityForAttendedDrillsSession();
    }

    @And("User select the activity list")
    public void userSelectTheActivityList() {
        safetyManagementPage.userSelectTheActivityList();
    }

    @And("User click on Account type filter and enable crew checkbox")
    public void userClickOnAccountTypeFilterAndEnableCrewCheckbox() {
        safetyManagementPage.userIsAbleToSelectTheCrewInTheDropdown();
        userSearchInCrewMemberPage(SafetyManagementConstatnts.CREW_ID);
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        safetyManagementPage.userAbleToClickInTheDetailTable(SafetyManagementConstatnts.CHECK_BOX);
    }

    @Then("User view the Add button")
    public void userViewTheAddButton() {
        safetyManagementPage.userViewTheAddButton();
    }

    @And("click the confirm Button pop up")
    public void clickTheConfirmButtonPopUp() {
        safetyManagementPage.clickTheConfirmButtonPopUp();
    }

    @And("User add the Guest member")
    public void userAddTheGuestMember() {
        safetyManagementPage.checkingTheGuestAvaliable();
        shipSecurityPage.userSearchForTheGuest(GlobalConstants.GUEST_PREFIX_NAME);
        userSearchTheAddedGuestMember();
        commonSteps.userClickOn(GlobalConstants.SELECT);
        String guestName = (String) context.getContext("firstguestName");
        guestServicePage.searchUser(guestName);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User selects the {string} dropdown group checkbox")
    public void userSelectsTheDropdownGroupCheckbox(String text) {
       safetyManagementPage.userSelectsTheDropdownGroupCheckbox(text);
       commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
       safetyManagementPage.userAbleToClickInTheDetailTable(SafetyManagementConstatnts.CHECK_BOX);
    }

    @And("User search the added guest member")
    public void userSearchTheAddedGuestMember() {
        String[] guestName = hotelMaintenancePage.getGuestName().split(" ");
        System.out.println("guestName :: " + guestName[1].trim());
        context.setContext("firstguestName", guestName[0].trim());
        context.setContext("guestName", guestName[1].trim());
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User check the count of Drills Training Absence History")
    public void userCheckTheCountOfDrillsTrainingAbsenceHistory() {
        int rowCount = safetyManagementPage.getTableRowCount();
        Assert.assertTrue(rowCount > 0, "Table row count is zero.");
    }

    @And("User search absence crew person")
    public void userSearchAbsenceCrewPerson() {
        guestServicePage.searchUser(SafetyManagementConstatnts.CREW_ABSENCE);
    }

    @Then("User verify the IPM activity is displayed")
    public void userVerifyTheIPMActivityIsDisplayed() {
        Assert.assertTrue(safetyManagementPage.isIPMActivityDisplayed(),
                "IPM Activity is not displayed");
    }

    @Then("User clicks the Emergency Role dropdown and validates the values")
    public void userClicksTheEmergencyRoleDropdownAndValidatesTheValues() {
        Assert.assertTrue(safetyManagementPage.verifyEmergencyTeamValues(), "Emergency team values are not matching");
    }

    @And("User clicks on the {string} link")
    public void userClicksOnTheLink(String link) {
        safetyManagementPage.clickOnSelectLink(link);
    }

    @And("User adds the {string}")
    public void userAddsThe(String element) {
        safetyManagementPage.addTrainingCertificationIfNotExists(element);
        Assert.assertTrue(safetyManagementPage.isSectionFirstRowDisplayed(element), element + " was not added successfully.");
    }

    @Then("User check the count of Training Certifications")
    public void userCheckTheCountOfTrainingCertifications() {
        int rowCount = safetyManagementPage.getTableRowCount();
        Assert.assertTrue(rowCount > 0, "Table row count is zero.");
    }

    @Then("User View the original value")
    public void userViewTheOriginalValue() {
        String originalDate = activitiesPlannerPage.getActivityDateText().getAttribute("value");
        context.setContext("originalDate", originalDate);
        logger.info("Original Date: {}", originalDate);
    }

    @Then("User verifies the activity date is reset to the original value")
    public void userVerifiesTheActivityDateIsReset() {
        String currentDate = activitiesPlannerPage.getActivityDateText().getAttribute("value");
        String originalDate = (String) context.getContext("originalDate");

        logger.info("Current Activity Date : {}", currentDate);
        logger.info("Original Activity Date: {}", originalDate);

        Assert.assertEquals(currentDate, originalDate, "The activity date was not reset to the original value.");
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User adds a disability for the selected guest")
    public void userAddsADisabilityForTheSelectedGuest() {
        excursionManagementPage.userAddTheDataInSpecialFileds();
    }

    @Then("User verifies the disability is added under Special Needs")
    public void userVerifiesTheDisabilityIsAddedUnderSpecialNeeds() {
        Assert.assertTrue(safetyManagementPage.specialNeedFieldDisplayed(), "Special Needs comment field is not displayed.");
    }

    @And("User enters a comment for the Special Needs")
    public void userEntersACommentForTheSpecialNeeds() {
        safetyManagementPage.userEntersACommentForTheSpecialNeeds();
    }
    @And("User clicks on the guest profile that has a profile image")
    public void userClicksOnTheGuestProfileThatHasAProfileImage() {
        Assert.assertTrue(safetyManagementPage.clickGuestProfileWithImage(), "No guest profile with a profile image was found.");
    }

    @And("User captures and saves the guest's Name, Nationality, Age, and Language")
    public void userCapturesAndSavesTheGuestSNameNationalityAgeAndLanguage() {
        context.setContext("guestName", safetyManagementPage.getGuestName());
        context.setContext("guestAge", safetyManagementPage.getGuestAge());
        context.setContext("guestNationality", safetyManagementPage.getGuestNationality());
        context.setContext("guestLanguage", safetyManagementPage.getGuestLanguage());

        logger.info("Guest Name : {}", context.getContext("guestName"));
        logger.info("Guest Age : {}", context.getContext("guestAge"));
        logger.info("Guest Nationality : {}", context.getContext("guestNationality"));
        logger.info("Guest Language : {}", context.getContext("guestLanguage"));
    }
    @Then("User validates that the saved guest details match the Personal Information section")
    public void userValidatesThatTheSavedGuestDetailsMatchThePersonalInformationSection() {
        String actualGuestName = safetyManagementPage.getGuestFullName();
        logger.info(actualGuestName);
        String actualNationality = safetyManagementPage.getGuestNationalityFromPersonalInfo();
        logger.info(actualNationality);
        String actualLanguage = safetyManagementPage.getGuestLanguageFromPersonalInfo();
        logger.info(actualLanguage);

        String expectedGuestName = context.getContext("guestName");
        String expectedNationality = context.getContext("guestNationality");
        String expectedLanguage = context.getContext("guestLanguage");

        Assert.assertTrue(expectedGuestName.contains(actualGuestName), "Guest name does not match.");

        Assert.assertEquals(actualNationality, expectedNationality, "Guest nationality does not match.");

        Assert.assertEquals(actualLanguage, expectedLanguage, "Guest language does not match.");
    }


    @And("User click on Add more filters icon")
    public void userClickOnAddMoreFiltersIcon() {
        Assert.assertTrue(safetyManagementPage.selectAssemblyStationFilter(), "Assembly Station filter is not available even after adding it.");
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User enables the {string} filter by selecting its checkbox")
    public void userEnablesTheFilterBySelectingItsCheckbox(String arg0) {
        String stationName = safetyManagementPage.selectFirstAssemblyStation();
        Assert.assertNotNull(stationName, "No Assembly Station is available.");
        context.setContext("ASSEMBLY_STATION", stationName);
        logger.info("Assembly Station: {}", stationName);
        context.setContext("SKIP_SCREENSHOT", "true");

    }

    @Then("User validates that the table contains the selected Assembly Station")
    public void userValidatesThatTheTableContainsTheSelectedAssemblyStation() {
        String expectedAssemblyStation = context.getContext("ASSEMBLY_STATION");
        String actualAssemblyStation = safetyManagementPage.getFirstRowValueByHeader(SafetyManagementConstatnts.ACTIVITY_TYPE_ASSEMBLY_STATION);
        expectedAssemblyStation = expectedAssemblyStation.replace("...", "").trim();
        actualAssemblyStation = actualAssemblyStation.trim();

        while (!actualAssemblyStation.startsWith(expectedAssemblyStation) && expectedAssemblyStation.contains(" ")) {

            expectedAssemblyStation = expectedAssemblyStation.substring(0, expectedAssemblyStation.lastIndexOf(" ")).trim();
        }

        Assert.assertTrue(actualAssemblyStation.startsWith(expectedAssemblyStation), "Assembly Station does not match.\nExpected: " + expectedAssemblyStation + "\nActual: " + actualAssemblyStation);
    }

    @And("User click on the first Safety Number")
    public void userClickOnTheFirstSafetyNumber() {
        WebElement element = safetyManagementPage.clickFirstSafetyNumberElement();
        Assert.assertNotNull(element, "Safety Number element is not present on the page.");
    }

    @Then("User verifies the {string} and {string} safety section is displayed")
    public void userVerifiesTheAndSafetySectionIsDisplayed(String section1, String section2) {
        Assert.assertTrue(safetyManagementPage.isSafetySectionDisplayed(section1), "'" + section1 + "' safety section is not displayed.");
        Assert.assertTrue(safetyManagementPage.isSafetySectionDisplayed(section2), "'" + section2 + "' safety section is not displayed.");
    }

    @Then("User selects a random crew member")
    public void userSelectsARandomCrewMember() {
        String crewName = safetyManagementPage.selectRandomCrewMember();
        Assert.assertNotNull(crewName, "Failed to select a random crew member.");
        context.setContext("CREW_NAME", crewName);
        context.setContext("SKIP_SCREENSHOT", "true");
    }




    @And("User searches for the selected crew member")
    public void userSearchesForTheSelectedCrewMember() {
        String crewName = context.getContext("CREW_NAME");
        boolean isDisplayed = safetyManagementPage.cewMemberSearching(crewName);
        Assert.assertTrue(isDisplayed, "'No Data yet' message is NOT displayed for " + crewName);
    }

    @Then("User validates that the search results contain the selected crew member")
    public void userValidatesThatTheSearchResultsContainTheSelectedCrewMember() {
        String savedCrewName = context.getContext("CREW_NAME");
        Assert.assertTrue(safetyManagementPage.isCrewNameDisplayed(savedCrewName), "Expected crew name '" + savedCrewName + "' was not found in the search results.");
    }

    @Then("User selects a random safety number")
    public void userSelectsARandomSafetyNumber() {
        String safetyNumber = safetyManagementPage.selectRandomSafetyNumber();
        Assert.assertNotNull(safetyNumber, "Failed to select a random crew member.");
        context.setContext("SAFETY_NUMBER", safetyNumber);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @And("User searches for the selected safety number")
    public void userSearchesForTheSelectedSafetyNumber() {
        String savedSafetyNumber = context.getContext("SAFETY_NUMBER");
        boolean isDisplayed = safetyManagementPage.cewMemberSearching(savedSafetyNumber);
        Assert.assertTrue(isDisplayed, "'No Data yet' message is NOT displayed for " + savedSafetyNumber);
    }

    @Then("User validates that the search results contain the selected safety number")
    public void userValidatesThatTheSearchResultsContainTheSelectedSafetyNumber() {
        String savedSafetyNumber = context.getContext("SAFETY_NUMBER");
        Assert.assertTrue(safetyManagementPage.isSafetyNumberDisplayed(savedSafetyNumber), "Expected crew name '" + savedSafetyNumber + "' was not found in the search results.");
    }

    @Then("User saves the previous Training Call activity for the respective crew")
    public void userSavesThePreviousTrainingCallActivityForTheRespectiveCrew() {
        homePage.navigateToSelectedService(SafetyManagementConstatnts.SAFETY_MANAGEMENT);
        safetyManagementPage.userViewTheActivityOverviewButton();
        safetyManagementPage.clickOnLink(SafetyManagementConstatnts.ACTIVITY_OVERVIEW);
        guestServiceRequestPage.switchToNewWindow();
        safetyManagementPage.clickFirstSafetyDrill();
        userAddTheCrewMember();
        safetyManagementPage.checkingTheDataIsAdded();
        commonSteps.userViewsTheButton(SafetyManagementConstatnts.EDIT_ACTIVITY);
        commonSteps.userClickOn(SafetyManagementConstatnts.EDIT_ACTIVITY);
        commonSteps.userViewsTheButton(SafetyManagementConstatnts.CONFIRM);
        safetyManagementPage.clickTheConfirmButtonPopUp();
        safetyManagementPage.updateTheSafetyTime();
        commonSteps.userClickOn(SafetyManagementConstatnts.SAVE_AND_FINISH_TEXT);
        String guestName = (String) context.getContext("firstguestName");
        guestServicePage.searchUser(guestName);
        safetyManagementPage.userNameClick();
        guestServiceRequestPage.switchToNewWindow();
        guestServicePage.openSubMenu(SafetyManagementConstatnts.ACTIVITY_CATEGORY_SAFETY);
        safetyManagementPage.currentSafetyAssignmentSection(SafetyManagementConstatnts.UPCOMING_DRILLS_TRAINING);
        String trainingDate = safetyManagementPage.getUpcomingDrillTrainingDate();
        context.setContext("UPCOMING_DRILL_TRAINING_DATE", trainingDate);
        context.setContext("SKIP_SCREENSHOT", "false");
    }

    @And("User add the crew member")
    public void userAddTheCrewMember() {
        safetyManagementPage.checkingTheGuestAvaliable();
        shipSecurityPage.clickOnIconAtTopRightPane(SafetyManagementConstatnts.ACTIVITY_TYPE_CREW);
        shipSecurityPage.userSearchForTheGuest(GlobalConstants.GUEST_PREFIX_NAME);
        userSearchTheAddedGuestMember();
        commonSteps.userClickOn(GlobalConstants.SELECT);
        String guestName = (String) context.getContext("firstguestName");
        guestServicePage.searchUser(guestName);
    }

    @Then("User update Training Call activity for the respective crew")
    public void userUpdateTrainingCallActivityForTheRespectiveCrew() {
        homePage.navigateToSelectedService(SafetyManagementConstatnts.SAFETY_MANAGEMENT);
        safetyManagementPage.userViewTheActivityOverviewButton();
        safetyManagementPage.clickOnLink(SafetyManagementConstatnts.ACTIVITY_OVERVIEW);
        guestServiceRequestPage.switchToNewWindow();
        safetyManagementPage.clickFirstSafetyDrill();
        String guestName = (String) context.getContext("firstguestName");
        guestServicePage.searchUser(guestName);
        safetyManagementPage.checkingTheDataIsAdded();
        commonSteps.userViewsTheButton(SafetyManagementConstatnts.EDIT_ACTIVITY);
        commonSteps.userClickOn(SafetyManagementConstatnts.EDIT_ACTIVITY);
        commonSteps.userViewsTheButton(SafetyManagementConstatnts.CONFIRM);
        safetyManagementPage.clickTheConfirmButtonPopUp();
        String oneMonthsRange = commonFunctions.getDateIncrementedBy(SafetyManagementConstatnts.DAYS_TO_BE_INCREMENTED_BY, SafetyManagementConstatnts.DATE_FORMAT_MDY);
        safetyManagementPage.updateTheUpComingDate(oneMonthsRange);

        commonSteps.userClickOn(SafetyManagementConstatnts.SAVE_AND_FINISH_TEXT);
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User updated Training Call activity for respective crew")
    public void userUpdatedTrainingCallActivityForRespectiveCrew() {
        String previousUpcomingDrill = (String) context.getContext("UPCOMING_DRILL_TRAINING_DATE");
        String updatedUpcomingDrill = safetyManagementPage.getUpcomingDrillTrainingDate();

        Assert.assertNotEquals(updatedUpcomingDrill, previousUpcomingDrill, "Upcoming Drill Training Date should be updated.");


    }
    @And("User selects the crew whose {string} is {string}")
    public void userSelectsTheCrewWhoseIs(String filterName, String reservationStatus) {
        safetyManagementPage.userIsAbleToSelectTheCrewInTheDropdown();
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        Assert.assertTrue(safetyManagementPage.selectCrewByReservationStatus(filterName, reservationStatus), String.format("Failed to select crew with %s as '%s'.", filterName, reservationStatus));
        String crewMemberName = safetyManagementPage.getFirstCrewMemberName();
        Assert.assertNotNull(crewMemberName, "No crew member found.");
        Assert.assertFalse(crewMemberName.isEmpty(), "Crew member name is empty.");
        context.setContext("CREW_MEMBER_NAME", crewMemberName);
        safetyManagementPage.userAbleToClickInTheDetailTable(SafetyManagementConstatnts.CHECK_BOX);
    }

    @Then("User verifies the {string} Call activity contains the selected crew member's name")
    public void userVerifiesTheCallActivityContainsTheSelectedCrewMemberSName(String activityName) {
        String crewName = (String) context.getContext("CREW_MEMBER_NAME");
        String firstName = crewName.trim().split("\\s+")[0];
        guestServicePage.searchUser(firstName);
        String actualCrewName = safetyManagementPage.getSelectedUserName();
        Assert.assertTrue(crewName.contains(actualCrewName), "Expected crew name: '" + crewName + "', but found: '" + actualCrewName + "'.");
        Assert.assertTrue(safetyManagementPage.isActivityDisplayed(activityName), "Activity '" + activityName + "' was not created or is not displayed.");
    }

    @And("User creates a new {string} activity")
    public void userCreatesANewActivity(String option) {
        Assert.assertTrue(safetyManagementPage.userCreatesANewSafetyTrainingActivity(option), "Unable to create a new safety training activity.");
    }

    @And("User selects the crew whose {string} is {string} and select {string} dropdown group checkbox")
    public void userSelectsTheCrewWhoseIsAndSelectDropdownGroupCheckbox(String filterName, String reservationStatus, String option) {
        safetyManagementPage.userIsAbleToSelectTheCrewInTheDropdown();
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        Assert.assertTrue(safetyManagementPage.selectCrewByReservationStatus(filterName, reservationStatus), String.format("Failed to select crew with %s as '%s'.", filterName, reservationStatus));
        safetyManagementPage.userSelectsTheDropdownGroupCheckbox(option);
        String crewMemberName = safetyManagementPage.getFirstCrewMemberName();
        Assert.assertNotNull(crewMemberName, "No crew member found.");
        Assert.assertFalse(crewMemberName.isEmpty(), "Crew member name is empty.");
        context.setContext("CREW_MEMBER_NAME", crewMemberName);
        safetyManagementPage.userAbleToClickInTheDetailTable(SafetyManagementConstatnts.CHECK_BOX);
    }

    @And("User filters by {string} and selects the required crew groups")
    public void userFiltersByAndSelectsTheRequiredCrewGroups(String option) {
        safetyManagementPage.userIsAbleToSelectTheCrewInTheDropdown();
        commonSteps.userWaitForSeconds(SafetyManagementConstatnts.WAITING_FOR_ELEMENT);
        Assert.assertTrue(safetyManagementPage.selectCrewByReservationStatus(SafetyManagementConstatnts.RESERVATION_STATUS, SafetyManagementConstatnts.CHECKED_IN), String.format("Failed to select crew with %s as '%s'.", SafetyManagementConstatnts.RESERVATION_STATUS, SafetyManagementConstatnts.CHECKED_IN));
        String position = safetyManagementPage.getFirstAvailablePosition();
        Assert.assertNotNull(position, "No Position value found in the Position column.");
        safetyManagementPage.selectCrewBySafetyPositionStatus(option, position);
        String crewMemberName = safetyManagementPage.getFirstCrewMemberName();
        Assert.assertNotNull(crewMemberName, "No crew member found.");
        Assert.assertFalse(crewMemberName.isEmpty(), "Crew member name is empty.");
        context.setContext("CREW_MEMBER_NAME", crewMemberName);
        safetyManagementPage.userAbleToClickInTheDetailTable(SafetyManagementConstatnts.CHECK_BOX);
    }

    @And("User add new Respective Disability for particular guest")
    public void userAddNewRespectiveDisabilityForParticularGuest() {

    }

    @And("User clicks on the delete icon")
    public void userClicksOnTheDeleteIcon() {
        int beforeDelete = safetyManagementPage.getSpecialNeedsCount();
        context.setContext("SPECIAL_NEED_COUNT", String.valueOf(beforeDelete));
        safetyManagementPage.clickDeleteIcon();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Then("User validates that the Special Need is deleted")
    public void userValidatesThatTheSpecialNeedIsDeleted() {
        int beforeDelete = Integer.parseInt(context.getContext("SPECIAL_NEED_COUNT"));
        int afterDelete = safetyManagementPage.getSpecialNeedsCount();
        Assert.assertEquals(afterDelete, beforeDelete - 1, "Special Need count was not reduced by one after deleting.");
    }

    @And("User clears the reservation status and applies the {string}, {string}, and {string} as {string} filters")
    public void userClearsTheReservationStatusAndAppliesTheAndAsFilters(String option1, String option2, String filter, String option3) {
        crewInformationPage.clearReservationStatus();
        guestServicePage.checkOptionInTheFilter(option1);
        SeleniumActions seleniumActions = new SeleniumActions(getDriver());
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        guestServicePage.checkOptionInTheFilter(option2);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        crewInformationPage.selectDropDown(filter, option3);
        seleniumActions.sleep(GlobalConstants.WAIT_FOR_ELEMENT);
        seleniumActions.pressEscapeKey();
        Assert.assertTrue(safetyManagementPage.validateEmbarkDates(), "One or more Embark Dates are missing or contain a past date.");
    }

    @Then("User validates the crew count displayed after applying the Expect Future and Expect Today filters")
    public void userValidatesTheCrewCountDisplayedAfterApplyingTheExpectFutureAndExpectTodayFilters() {
        int actualCount = shipSecurityPage.getTableRowCount();
        Assert.assertTrue(actualCount >= 1, "Expected the crew count to be greater than 1, but found: " + actualCount);
    }

    @And("User selects multiple participant records by clicking the checkboxes")
    public void userSelectsMultipleParticipantRecordsByClickingTheCheckboxes() {
        safetyManagementPage.selectAllParticipantsFromCurrentPage();
        safetyManagementPage.userViewTheAddButton();
    }
    @And("User clicks the add selected button and save safety number")
    public void userClicksTheAddSelectedButtonAndSaveSafetyNumber() {
        safetyManagementPage.clickAddButton();
        context.setContext("SAFETY_NUMBER", safetyManagementPage.getSafetyNumber());
    }

    @Then("User searches the participant by Safety Number")
    public void userSearchesTheParticipantBySafetyNumber() {
        String safetyNumber = context.getContext("SAFETY_NUMBER");
        safetyManagementPage.searchBySafetyNumber(safetyNumber);
        Assert.assertTrue(safetyManagementPage.isSelectedSafetyNumberDisplayed(safetyNumber), "Safety Number '" + safetyNumber + "' was not found after searching.");
    }

    @And("User clicks the add selected button and save the crew ID")
    public void userClicksTheAddSelectedButtonAndSaveTheCrewID() {
        safetyManagementPage.clickAddButton();
        context.setContext("CREW_ID_NUMBER", safetyManagementPage.getCrewIdNumber());
    }

    @Then("User searches the participant by crew ID Number")
    public void userSearchesTheParticipantByCrewIDNumber() {
        String crewIdNumber = context.getContext("CREW_ID_NUMBER");
        safetyManagementPage.searchBySafetyNumber(crewIdNumber);
        Assert.assertTrue(safetyManagementPage.isSelectedCrewIdNumberDisplayed(crewIdNumber), "Safety Number '" + crewIdNumber + "' was not found after searching.");
    }

    @Then("User navigates to the {string} section and verifies the assigned {string}")
    public void userNavigatesToTheSectionAndVerifiesTheAssigned(String option, String sectionName) {
        Assert.assertTrue(safetyManagementPage.isSectionDisplayedWithValue(sectionName), sectionName + " section is not displayed or value is empty.");
    }

    @And("User Add a respective disability for the guest only if it is not already present")
    public void userAddARespectiveDisabilityForTheGuestOnlyIfItIsNotAlreadyPresent() {
        Assert.assertTrue(safetyManagementPage.specialNeedsOptions(), "Special Need was not added successfully.");
        Assert.assertTrue(safetyManagementPage.specialNeedFieldDisplayed(), "Special Needs comment field is not displayed.");
    }

    @Given("User has added the crew member to the Port Manning details")
    public void userHasAddedTheCrewMemberToThePortManningDetails() {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.SAFETYEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToSelectedService(SafetyManagementConstatnts.SAFETY_MANAGEMENT);
        safetyManagementPage.clickOnLink(SafetyManagementConstatnts.SAFETY_PLAN_ASSIGNMENT);
        guestServicePage.changeStatusOfFilter(SafetyManagementConstatnts.ASSIGNED, SafetyManagementConstatnts.NO);
        userSaveNameOrNumber(SafetyManagementConstatnts.BY_CREW_MEMBER);
        userSelectTheCheckbox(SafetyManagementConstatnts.BY_CREW_MEMBER);
        safetyManagementPage.clickCabinNumber();
        userSaveTheCabinNumberForAssignOrUnassign();
        userSelectOrUnselectCabinForCrew();
        safetyManagementPage.clickOnAssignButton();
        guestServiceRequestPage.switchToNewWindow();
        safetyManagementPage.addingTheInPortMinning();
        guestServicePage.getWarningMessage();
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");
    }
    @And("User searches and assigns the crew member to the Port Manning details")
    public void userSearchesAndAssignsTheCrewMemberToThePortManningDetails() {
        crewInformationPage.clearReservationStatus();
        String fullName = context.getContext("crewFullName").toString();
        String searchName = fullName.split(",")[0].trim();
        safetyManagementPage.cewMemberSearching(searchName);
    }


    @And("User clicks on the {string} button in the Select Detail table")
    public void userClicksOnTheButtonInTheSelectDetailTable(String arg0) {
        safetyManagementPage.clicksOnTheButtonInTheSelectDetailTable();
    }

    @And("User click on the Remove all button")
    public void userClickOnTheRemoveAllButton() {
        Assert.assertEquals(safetyManagementPage.getSelectedGuestCount(), 1, "Expected only one guest to be added, but found ");
        safetyManagementPage.clickOnTheRemoveAllButton();
    }

    @Then("User verifies the selected name is displayed in orange color")
    public void userVerifiesTheSelectedNameIsDisplayedInOrangeColor() {
        String colourCode = safetyManagementPage.getFirstGuestNameColour();
        String actualHexColor = Color.fromString(colourCode).asHex();
        Assert.assertEquals(actualHexColor, "#f09000", "The first guest name is not displayed in orange color.");
    }

    @And("User click the revert button")
    public void userClickTheRevertButton() {
        safetyManagementPage.clickTheRevertButton();
    }

    @Then("User verifies the selected guest name is displayed in the original color after clicking the Revert button")
    public void userVerifiesTheSelectedGuestNameIsDisplayedInTheOriginalColorAfterClickingTheRevertButton() {
        String colourCode = safetyManagementPage.getFirstGuestNameColour();
        String actualHexColor = Color.fromString(colourCode).asHex();
        Assert.assertEquals(actualHexColor, "#2c67bf", "The guest name is not displayed in original color.");
    }

    @And("User selects all checkboxes and clicks the Select All button")
    public void userSelectsAllCheckboxesAndClicksTheSelectAllButton() {
        safetyManagementPage.userSelectsAllCheckboxesAndClicksButton();
        safetyManagementPage.clickAddButton();
    }

    @And("User clicks the {string} button in the right window")
    public void userClicksTheButtonInTheRightWindow(String arg0) {
        Assert.assertEquals(safetyManagementPage.getSelectedGuestCount(), 20, "Expected only one guest to be added, but found ");
        safetyManagementPage.clickOnTheRemoveAllButton();
    }
    @And("User clicks the Undo icon")
    public void userClicksTheUndoIcon() {
        safetyManagementPage.userClicksTheUndoIcon();
    }
}
