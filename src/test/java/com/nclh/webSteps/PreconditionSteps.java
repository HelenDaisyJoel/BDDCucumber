package com.nclh.webSteps;

import com.nclh.base.BasePage;
import com.nclh.constants.*;
import com.nclh.dataProviders.ExcelReader;
import com.nclh.managers.AllDriverManager;
import com.nclh.managers.FileReaderManager;
import com.nclh.pageObjects.*;
import com.nclh.utilities.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static com.nclh.dataProviders.JsonReader.readUserData;

@Slf4j
public class PreconditionSteps extends AllDriverManager {
    WebDriver webDriver;
    LoginPage loginPage;
    HomePage homePage;
    Context context;
    GuestServicePage guestServicePage;
    CommonPage commonPage;
    SeleniumActions seleniumActions;
    CrewInformationPage crewInformationPage;
    ExcursionManagementPage excursionManagementPage;
    CommonFunctions commonFunctions;
    GuestCheckInCheckOutPage guestCheckInCheckOutPage;
    CreateNewReservationPage createNewReservationPage;
    BasePage basePage;
    GuestServiceRequestPage guestServiceRequestPage;
    GuestProfilePage guestProfilePage;
    PointOfSalePage pointOfSalePage;
    GroupManagementPage groupManagementPage;
    ShipSecurityPage shipSecurityPage;
    OrderPointOfSalePage orderPointOfSalePage;
    EntertainmentPage entertainmentPage;
    SafetyManagementPage safetyManagementPage;
    SeleniumWaits seleniumWaits;
    CommonSteps commonSteps;
    HotelMaintenancePage hotelMaintenancePage;
    ExcursionManagementSteps excursionManagementSteps;

    public PreconditionSteps(Context context) {
        this.webDriver = getDriver();
        loginPage = new LoginPage(webDriver);
        homePage = new HomePage(webDriver);
        this.context = context;
        guestServicePage = new GuestServicePage(webDriver);
        commonPage = new CommonPage(webDriver);
        seleniumActions = new SeleniumActions(webDriver);
        crewInformationPage = new CrewInformationPage(webDriver);
        excursionManagementPage = new ExcursionManagementPage(webDriver);
        commonFunctions = new CommonFunctions();
        guestCheckInCheckOutPage = new GuestCheckInCheckOutPage(webDriver);
        createNewReservationPage = new CreateNewReservationPage(webDriver);
        basePage = new BasePage(webDriver);
        guestServiceRequestPage = new GuestServiceRequestPage(webDriver);
        guestProfilePage = new GuestProfilePage(webDriver);
        pointOfSalePage = new PointOfSalePage(webDriver);
        groupManagementPage =new GroupManagementPage(webDriver);
        shipSecurityPage = new ShipSecurityPage(webDriver);
        orderPointOfSalePage = new OrderPointOfSalePage(webDriver);
        entertainmentPage = new EntertainmentPage(webDriver);
        safetyManagementPage = new SafetyManagementPage(webDriver);
        seleniumWaits = new SeleniumWaits(webDriver);
        hotelMaintenancePage = new HotelMaintenancePage(webDriver);
        excursionManagementSteps = new ExcursionManagementSteps(context);
        commonSteps = new CommonSteps(context);
    }

    @Given("A Guest user is already created")
    public void createGuestUser(){
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.GUESTSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.GUESTSERVICE_MODULE_TXT.trim());
        guestServicePage.openGuestServiceItem(PreconditionConstants.CHECK_IN_TXT);
        commonPage.openLinkUsingHref(GlobalConstants.QUICK_LINKS_TXT);
        seleniumActions.clickElementByText(webDriver, PreconditionConstants.CREATE_GUEST_RESERVATION_TXT);
        //Fill name and date
        PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME = basePage.generateName(PreconditionConstants.FM_FIRST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME = basePage.generateName(PreconditionConstants.FM_LAST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME = PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME+" "+PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME;

        crewInformationPage.enterFirstName(PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME);
        crewInformationPage.enterLastName(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        log.info("#################################################");
        log.info("Created guest user full name is: {}", PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME);
        log.info("#################################################");
        basePage.type(commonPage.dateInput(PreconditionConstants.EMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDateByDays(5));
        basePage.type(commonPage.dateInput(PreconditionConstants.DISEMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDate(PreconditionConstants.DISEMBARKATION_DATE_MONTH));
        basePage.clickOnText(PreconditionConstants.SAVE_NEXT_OPTIONAL_TXT);
        // Assign cabin number
        commonPage.searchFor(PreconditionConstants.FM_CABIN_NUMBER);
//        createNewReservationPage.clickCabinCell(PreconditionConstants.FM_CABIN_NUMBER);
        basePage.clickOnMatchingTextByIndex(PreconditionConstants.SAVE_NEXT_OPTIONAL_TXT, 2);
        // Add document details
        commonPage.enterDate(PreconditionConstants.DOC_EXPIRY_DATE_TXT, DateTimeUtils.getDate(PreconditionConstants.DOC_EXPITY_DATE_MONTH_VALUE));
        commonPage.enterDate(PreconditionConstants.BIRTH_DATE, DateTimeUtils.getDateYearsAgo(PreconditionConstants.GUEST_BIRTH_DATE_MONTH));
        guestServiceRequestPage.selectCategoryDropDown(PreconditionConstants.GENDER_DROPDOWN_TXT,PreconditionConstants.MALE_FIELD_TXT);
        crewInformationPage.enterDocumentNr(String.valueOf(excursionManagementPage.getRandomNumber()));
        basePage.clickOnMatchingTextByIndex(PreconditionConstants.SAVE_AND_FINISH_TXT, 3);

        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);

        // Marking guest checkin from profile
        commonPage.openLinkUsingHref(GlobalConstants.QUICK_LINKS_TXT);
        seleniumActions.clickElementByText(webDriver, PreconditionConstants.PROFILE_CHECK_IN_TXT);

        // User logs out from application
        guestProfilePage.closeQuickLinks();
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");

    }

    @Given("A routing guest user is already created")
    public void     creatRoutingGuestUser(){
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.GUESTSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.GUESTSERVICE_MODULE_TXT.trim());
        guestServicePage.openGuestServiceItem(PreconditionConstants.CHECK_IN_TXT);
        commonPage.openLinkUsingHref(GlobalConstants.QUICK_LINKS_TXT);
        seleniumActions.clickElementByText(webDriver, PreconditionConstants.CREATE_GUEST_RESERVATION_TXT);
        //Fill name and date
        PreconditionConstants.NEWLY_CREATED_ROUTING_GUEST_FIRSTNAME = basePage.generateName(PreconditionConstants.ROUTING_FIRST_NAME);
        PreconditionConstants.NEWLY_CREATED_ROUTING_GUEST_LASTNAME = basePage.generateName(PreconditionConstants.FM_LAST_NAME);
        PreconditionConstants.NEWLY_CREATED_ROUTING_GUEST_FULLNAME = PreconditionConstants.NEWLY_CREATED_ROUTING_GUEST_FIRSTNAME+" "+PreconditionConstants.NEWLY_CREATED_ROUTING_GUEST_LASTNAME;

        crewInformationPage.enterFirstName(PreconditionConstants.NEWLY_CREATED_ROUTING_GUEST_FIRSTNAME);
        crewInformationPage.enterLastName(PreconditionConstants.NEWLY_CREATED_ROUTING_GUEST_LASTNAME);
        log.info("#################################################");
        log.info("Created guest user full name is: {}", PreconditionConstants.NEWLY_CREATED_ROUTING_GUEST_FULLNAME);
        log.info("#################################################");
        basePage.type(commonPage.dateInput(PreconditionConstants.EMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDate());
        basePage.type(commonPage.dateInput(PreconditionConstants.DISEMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDate(PreconditionConstants.DISEMBARKATION_DATE_MONTH));
        basePage.clickOnText(PreconditionConstants.SAVE_NEXT_OPTIONAL_TXT);
        // Assign cabin number
        commonPage.searchFor(PreconditionConstants.FM_CABIN_NUMBER);
//        createNewReservationPage.clickCabinCell(PreconditionConstants.FM_CABIN_NUMBER);
        basePage.clickOnMatchingTextByIndex(PreconditionConstants.SAVE_NEXT_OPTIONAL_TXT, 2);
        // Add document details
        commonPage.enterDate(PreconditionConstants.DOC_EXPIRY_DATE_TXT, DateTimeUtils.getDate(PreconditionConstants.DOC_EXPITY_DATE_MONTH_VALUE));
        commonPage.enterDate(PreconditionConstants.BIRTH_DATE, DateTimeUtils.getDateYearsAgo(PreconditionConstants.GUEST_BIRTH_DATE_MONTH));
        guestServiceRequestPage.selectCategoryDropDown(PreconditionConstants.GENDER_DROPDOWN_TXT,PreconditionConstants.MALE_FIELD_TXT);
        crewInformationPage.enterDocumentNr(String.valueOf(excursionManagementPage.getRandomNumber()));
        basePage.clickOnMatchingTextByIndex(PreconditionConstants.SAVE_AND_FINISH_TXT, 3);

        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);

        // Marking guest checkin from profile
        commonPage.openLinkUsingHref(GlobalConstants.QUICK_LINKS_TXT);
        seleniumActions.clickElementByText(webDriver, PreconditionConstants.PROFILE_CHECK_IN_TXT);

        // User logs out from application
        guestProfilePage.closeQuickLinks();
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");

    }


    @And("Search the guest user")
    public void searchTheGuestUser() {
        commonPage.searchFor(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);

    }

    @And("Navigates to user profile")
    public void navigatesToUserProfile() {
        commonPage.passengerProfileLink(PreconditionConstants.FM_FIRST_NAME).click();

    }

    @And("Navigates to boarding denied crew profile")
    public void navigatesToBoardingDeniedCrewProfile() {
//        commonPage.crewProfileLink(CrewInformationConstants.BOARDING_DENIED_CREW).click();
        seleniumActions.jsClick(commonPage.crewProfileLink(CrewInformationConstants.BOARDING_DENIED_CREW));
        seleniumActions.sleep(6000);

    }

    @And("Navigates to ashore denied crew profile")
    public void navigatesToAshoreDeniedCrewProfile() {
//        commonPage.crewProfileLink(CrewInformationConstants.ASHORE_DENIED_CREW).click();
        seleniumActions.jsClick( commonPage.crewProfileLink(CrewInformationConstants.ASHORE_DENIED_CREW));

        seleniumActions.sleep(6000);

    }

    @And("Navigates to onboard guest profile")
    public void navigatesToOnboardGuestProfile() {
//        commonPage.crewProfileLink(CrewInformationConstants.ASHORE_DENIED_CREW).click();
        seleniumActions.jsClick( commonPage.guestProfileLink(GuestCheckInCheckOutConstants.CHECKED_IN_GUEST_USER));

        seleniumActions.sleep(6000);

    }

    @And("Navigates to unassigned cabin crew member")
    public void navigatesToUnassignedCabinCrewProfile() {
        seleniumActions.jsClick(commonPage.crewProfileLink(CrewInformationConstants.BOARDING_DENIED_CREW));
        seleniumActions.sleep(6000);

    }

    @And("Navigates to checked out crew profile")
    public void navigatesToCheckedOutCrewProfile() {
        seleniumActions.jsClick(commonPage.crewProfileLink(CrewManagementConstants.CHECKED_OUT_CREW));
        seleniumActions.sleep(6000);

    }

    @And("Navigates to reassigned cabin crew profile")
    public void navigatesToReassignedCabinCrewProfile() {
        seleniumActions.jsClick(commonPage.crewProfileLink(CrewManagementConstants.ASSIGNED_CABIN_CREW));
        seleniumActions.sleep(6000);

    }

    @And("Navigates to checked in crew profile")
    public void navigatesToCheckedInCrewProfile() {
        seleniumActions.jsClick(commonPage.crewProfileLink(CrewManagementConstants.LEAVING_TODAY_CREW));
        seleniumActions.sleep(6000);
    }

    @And("Navigates to checked in ashore crew profile")
    public void navigatesToCheckedInAshoreCrewProfile() {
        seleniumActions.jsClick(commonPage.crewProfileLink(CrewManagementConstants.CHECKED_IN_ASHORE_CREW));
        seleniumActions.sleep(6000);
    }

    @And("Navigates to expected future crew profile")
    public void navigatesToExpectedFutureCrewProfile() {
        seleniumActions.jsClick(commonPage.crewProfileLink(CrewManagementConstants.EXPECTED_FUTURE_CREW));
        seleniumActions.sleep(6000);
    }

    @And("Select the guest user for routing")
    public void selectTheGuestUserForRouting() {
    }

    @Given("Create a closed check for guest")
    public void createAClosedCheckForGuest() {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.POS_MANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.POINT_OF_SALE_MODULE_TXT.trim());
        pointOfSalePage.clearChecks();
        if (GlobalConstants.REVENUE_CENTER_POS_USER.contains("LN1-Beach Bar")) {
            if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
                String qaRVC = ExcelReader.readUserData("pos_qa", "POS_User", "RevenueCenter");
                pointOfSalePage.selectRevenueCenter(qaRVC);
            } else {
                String qaRVC = ExcelReader.readUserData("pos_qa", "POS_User", "AlluraRevenueCenter");
                pointOfSalePage.selectRevenueCenter(qaRVC);
            }
        }
        commonPage.navigateTo(PointOfSaleConstants.ADD_CHECK_BUTTON);
        commonPage.clickOnLabel(PointOfSaleConstants.TAB_NAME);
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            pointOfSalePage.searchGuestUsingAlphabetic("POSguest");
        } else {
            pointOfSalePage.searchGuestUsingAlphabetic();
        }
        pointOfSalePage.selectGuest();
        String name = pointOfSalePage.getGuestName();
        System.out.println("name :: " + name);
        context.setContext("guestFullName", name);
        System.out.println(context.getContext("guestFullName"));
        commonPage.navigateTo(PointOfSaleConstants.CREATE_CHECK_BUTTON);
        commonPage.clickOnLabel(PointOfSaleConstants.LABEL_NAME);
        pointOfSalePage.selectGiftItem(PointOfSaleConstants.ITEM_NAME);
        if (PointOfSaleConstants.LINK_NAME.contains("payment-pos")) {
            SeleniumActions seleniumActions = new SeleniumActions(getDriver());
            seleniumActions.clickOnElement(By.cssSelector("[icon='payment-pos']"));
            seleniumActions.sleep(3000);
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            try {
                seleniumActions.jsClick(getDriver().findElement(By.cssSelector("[icon='payment-pos']")));
            }catch (Exception e) {
                log.info("Payment POS link not found, continuing...");
            }

        } else {
            commonPage.openLinkUsingHref(PointOfSaleConstants.LINK_NAME);
        }
        try {
            if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
                guestServicePage.drawSignature();
            } else if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("allura")) {
                guestServicePage.drawSignature();
            }
            commonPage.navigateTo(PointOfSaleConstants.POST_BUTTON);
        }catch (Exception e) {
            log.info("Signature not required, continuing...");
        }
        pointOfSalePage.clickUserProfile();
        pointOfSalePage.clickLogoutPOS();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Given("Create a guest for today's birthday")
    public void createAGuestForTodaySBirthday() {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.GUESTSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.GUESTSERVICE_MODULE_TXT.trim());
        guestServicePage.openGuestServiceItem(GlobalConstants.GUEST_SEARCH);
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
        shipSecurityPage.openProfile(context.getContext("nonSensitiveGuestFirstName"), context.getContext("nonSensitiveGuestLastName"));
        entertainmentPage.enableToggle(PointOfSaleConstants.TOGGLE_BUTTON_NAME);
        guestServicePage.openSubMenu(GlobalConstants.SUB_MENU_NAME);
        if (guestServicePage.checkEyeIsOpened()) {
            guestServicePage.clickOnOpenedEye();
        }
        orderPointOfSalePage.enterDateOfBirth(YearConstants.YEAR_TEXT);
        commonPage.clickOnElement(GlobalConstants.SAVE_BUTTON);
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Given("Create a closed check with tip for guest")
    public void createAClosedCheckWithTipForGuest() {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.POS_MANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.POINT_OF_SALE_MODULE_TXT.trim());
        pointOfSalePage.clearChecks();
        if (GlobalConstants.REVENUE_CENTER_POS_USER.contains("LN1-Beach Bar")) {
            if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
                String qaRVC = ExcelReader.readUserData("pos_qa", "POS_User", "RevenueCenter");
                pointOfSalePage.selectRevenueCenter(qaRVC);
            } else {
                String qaRVC = ExcelReader.readUserData("pos_qa", "POS_User", "AlluraRevenueCenter");
                pointOfSalePage.selectRevenueCenter(qaRVC);
            }
        }
        commonPage.navigateTo(PointOfSaleConstants.ADD_CHECK_BUTTON);
        commonPage.clickOnLabel(PointOfSaleConstants.TAB_NAME);
        if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
            pointOfSalePage.searchGuestUsingAlphabetic("POSguest");
        } else {
            pointOfSalePage.searchGuestUsingAlphabetic();
        }
        pointOfSalePage.selectGuest();
        String name = pointOfSalePage.getGuestName();
        System.out.println("name :: " + name);
        context.setContext("guestFullName", name);
        System.out.println(context.getContext("guestFullName"));
        commonPage.navigateTo(PointOfSaleConstants.CREATE_CHECK_BUTTON);
        commonPage.clickOnLabel(PointOfSaleConstants.LABEL_NAME);
        pointOfSalePage.selectGiftItem(PointOfSaleConstants.ITEM_NAME);
        if (PointOfSaleConstants.LINK_NAME.contains("payment-pos")) {
            SeleniumActions seleniumActions = new SeleniumActions(getDriver());
            seleniumActions.clickOnElement(By.cssSelector("[icon='payment-pos']"));
            seleniumActions.sleep(3000);
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            try {
                seleniumActions.jsClick(getDriver().findElement(By.cssSelector("[icon='payment-pos']")));
            }catch (Exception e) {
                log.info("Payment POS link not found, continuing...");
            }

        } else {
            commonPage.openLinkUsingHref(PointOfSaleConstants.LINK_NAME);
        }
        pointOfSalePage.enterTipAmount(PointOfSaleConstants.TIP_AMOUNT);
        String price = pointOfSalePage.getTotalPrice();
        System.out.println("price with tip :: " + price);
        context.setContext("totalPriceWithTip", price);
        try {
            if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("shiplab")) {
                guestServicePage.drawSignature();
            } else if (FileReaderManager.getInstance().getConfigFileReader().getUrl().contains("allura")) {
                guestServicePage.drawSignature();
            }
            commonPage.navigateTo(PointOfSaleConstants.POST_BUTTON);
        }catch (Exception e) {
            log.info("Signature not required, continuing...");
        }
        pointOfSalePage.clickUserProfile();
        pointOfSalePage.clickLogoutPOS();
        context.setContext("SKIP_SCREENSHOT", "true");
    }


    @And("Navigates to guest profile for add flight info")
    public void navigatesToFlightInfoGuestProfile() {
        seleniumActions.jsClick( commonPage.guestProfileLink(GuestCheckInCheckOutConstants.CHECKED_IN_FLIGHT_INFO_GUEST_USER));

    }

    @And("User search unassigned cabin crew member")
    public void searchUnassignedCrewFromCabinAssignment() {
        commonPage.searchCabinAssignmentCrew(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
    }

    @And("User search assigned cabin crew member")
    public void searchAssignedCrewFromCabinAssignment() {
        commonPage.searchCabinAssignmentCrew(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
    }

    @And("User search checked out crew user")
    public void searchCheckedOutCrew() {
        commonPage.searchForCrew(CrewManagementConstants.CHECKED_OUT_CREW);

    }

    @And("User search the profile photo crew user")
    public void userSearchTheProfilePhotoCrewUser() {
        crewInformationPage.userSearchTheProfilePhotoCrewUser();
    }

    @And("User search the checked in ashore crew user")
    public void searchAshoreCrew() {
        commonPage.searchForCrew(CrewManagementConstants.CHECKED_IN_ASHORE_CREW);

    }


    @Given("Create a guest with posting status is disabled")
    public void createAGuestWithPostingStatusIsDisabled() {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.GUESTSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.GUESTSERVICE_MODULE_TXT.trim());
        guestServicePage.openGuestServiceItem(GlobalConstants.GUEST_SEARCH);
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
        shipSecurityPage.openProfile(context.getContext("nonSensitiveGuestFirstName"), context.getContext("nonSensitiveGuestLastName"));
        orderPointOfSalePage.disableToggle(PointOfSaleConstants.TOGGLE_BUTTON_NAME);
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");
    }


    @Given("A new guest user is created {string}")
    public void aNewGuestUserIsCreated(String Status) {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.GUESTSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.GUESTSERVICE_MODULE_TXT.trim());
        guestServicePage.openGuestServiceItem(PreconditionConstants.CHECK_IN_TXT);
        commonPage.openLinkUsingHref(GlobalConstants.QUICK_LINKS_TXT);
        seleniumActions.clickElementByText(webDriver, PreconditionConstants.CREATE_GUEST_RESERVATION_TXT);
        //Fill name and date
        PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME = basePage.generateName(PreconditionConstants.FM_FIRST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME = basePage.generateName(PreconditionConstants.FM_LAST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME = PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME+" "+PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME;

        crewInformationPage.enterFirstName(PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME);
        crewInformationPage.enterLastName(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        log.info("#################################################");
        log.info("Created guest user full name is: {}", PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME);
        log.info("#################################################");
        basePage.type(commonPage.dateInput(PreconditionConstants.EMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDateByDays(5));
        basePage.type(commonPage.dateInput(PreconditionConstants.DISEMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDate(PreconditionConstants.DISEMBARKATION_DATE_MONTH));
        basePage.clickOnText(PreconditionConstants.SAVE_NEXT_OPTIONAL_TXT);
        // Assign cabin number
        guestCheckInCheckOutPage.guestCabinNumber();


        basePage.clickOnMatchingTextByIndex(PreconditionConstants.SAVE_NEXT_OPTIONAL_TXT, 2);
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
        // Add document details
        commonPage.enterDate(PreconditionConstants.DOC_EXPIRY_DATE_TXT, DateTimeUtils.getDate(PreconditionConstants.DOC_EXPITY_DATE_MONTH_VALUE));
        commonPage.enterDate(PreconditionConstants.BIRTH_DATE, DateTimeUtils.getDateYearsAgo(PreconditionConstants.GUEST_BIRTH_DATE_MONTH));
        guestServiceRequestPage.selectCategoryDropDown(PreconditionConstants.GENDER_DROPDOWN_TXT,PreconditionConstants.MALE_FIELD_TXT);
        crewInformationPage.enterDocumentNr(String.valueOf(excursionManagementPage.getRandomNumber()));
        basePage.clickOnMatchingTextByIndex(PreconditionConstants.SAVE_AND_FINISH_TXT, 3);

        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);

        // Marking guest checkin from profile
        commonPage.openLinkUsingHref(GlobalConstants.QUICK_LINKS_TXT);
        seleniumActions.clickElementByText(webDriver, PreconditionConstants.PROFILE_CHECK_IN_TXT);
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);

        if(Status.contains(GuestCheckInCheckOutConstants.ONBOARD)) {
            guestServicePage.openSubMenu(PreconditionConstants.CRUISE_INFORMATION);
            guestCheckInCheckOutPage.onBoardingTheUser();
            commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
            guestCheckInCheckOutPage.saveButton();
        }

        // User logs out from application
        guestProfilePage.closeQuickLinks();
        if(Status.contains(GuestCheckInCheckOutConstants.ONBOARD)) {
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);}
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");
    }


    @Given("A new crew user is created {string}")
    public void aNewCrewUserIsCreated(String status) {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.CREWSERVICE, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.CREW_SERVICES);
        crewInformationPage.clickOnStatusLink(PreconditionConstants.NEW_RESERVATION);

        //Fill name and date
        PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME = basePage.generateName(PreconditionConstants.FM_FIRST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME = basePage.generateName(PreconditionConstants.FM_LAST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME = PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME+" "+PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME;

        crewInformationPage.enterFirstName(PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME);
        crewInformationPage.enterLastName(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        log.info("#################################################");
        log.info("Created guest user full name is: {}", PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME);
        log.info("#################################################");

        guestServiceRequestPage.selectDropDown(PreconditionConstants.GENDER_DROPDOWN_TXT,PreconditionConstants.MALE_FIELD_TXT);

        commonPage.enterDate(PreconditionConstants.DATE_OF_BIRTH, DateTimeUtils.getDateYearsAgo(PreconditionConstants.GUEST_BIRTH_DATE_MONTH));
        seleniumActions.selectDropdownOption(PreconditionConstants.NATIONALITY,ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        crewInformationPage.userAbleEnableTheToggleSliderInNewProfile();
        crewInformationPage.userAbleClickTheTabOptions();
        crewInformationPage.clickSaveButton();

        basePage.type(commonPage.dateInput(PreconditionConstants.EMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDateByDays(5));
        basePage.type(commonPage.dateInput(PreconditionConstants.DISEMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDate(PreconditionConstants.DISEMBARKATION_DATE_MONTH));
        crewInformationPage.userAbleClickTheTabOptions();

        crewInformationPage.clickSaveAndNextButton();

        // Assign cabin number
        guestCheckInCheckOutPage.guestCabinNumber();
        crewInformationPage.clickSaveAndNextButtonInAssignCabin();
        crewInformationPage.cabinBookingConformationPopUp();

        // Add document details
        commonPage.enterDate(PreconditionConstants.DOC_EXPIRY_DATE_TXT, DateTimeUtils.getDate(PreconditionConstants.DOC_EXPITY_DATE_MONTH_VALUE));
        crewInformationPage.enterDocumentNr(String.valueOf(excursionManagementPage.getRandomNumber()));
        crewInformationPage.clickOnEyeIcon();


        crewInformationPage.clickSaveAndNextButtonInAssignCabin();
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
        crewInformationPage.navigateToSaveAndOpenProfile(PreconditionConstants.SAVE_AND_OPEN_PROFILE);

        // Marking guest checkin from profile
        commonPage.openLinkUsingHref(GlobalConstants.QUICK_LINKS_TXT);
        seleniumActions.clickElementByText(webDriver, PreconditionConstants.PROFILE_CHECK_IN_TXT);
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);

        shipSecurityPage.isBoardStatusDisplayed(PreconditionConstants.CHECKED_IN_ON_BOARD);
        if(status.contains(PreconditionConstants.SHORESIDE)){
            webDriver.navigate().refresh();
            guestServicePage.openSubMenu(PreconditionConstants.ASSIGNMENTS_AND_CONTRACT_INFORMATION);
            safetyManagementPage.currentSafetyAssignmentSection(PreconditionConstants.GANGWAY_MOVEMENT);
            crewInformationPage.clikeShoreside();
            commonPage.clickIfPresent(PreconditionConstants.SAVE);
        }

        // User logs out from application
        guestProfilePage.closeQuickLinks();
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");

    }

    @And("User search the crew user")
    public void userSearchTheCrewUser() {
        commonPage.searchForCrew(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
    }

    @Given("Add special need for guest")
    public void addSpecialNeedForGuest() {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.GUESTSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.GUESTSERVICE_MODULE_TXT.trim());
        guestServicePage.openGuestServiceItem(GlobalConstants.GUEST_SEARCH);
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
        shipSecurityPage.openProfile(context.getContext("nonSensitiveGuestFirstName"), context.getContext("nonSensitiveGuestLastName"));
        guestServicePage.openSubMenu(GlobalConstants.SUB_MENU_NAME);


    }
    @Given("a new guest user is created without being checked in")
    public void aNewGuestUserIsCreatedWithoutBeingCheckedIn() {

        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.GUESTSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.GUESTSERVICE_MODULE_TXT.trim());
        guestServicePage.openGuestServiceItem(PreconditionConstants.CHECK_IN_TXT);
        commonPage.openLinkUsingHref(GlobalConstants.QUICK_LINKS_TXT);
        seleniumActions.clickElementByText(webDriver, PreconditionConstants.CREATE_GUEST_RESERVATION_TXT);
        //Fill name and date
        PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME = basePage.generateName(PreconditionConstants.FM_FIRST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME = basePage.generateName(PreconditionConstants.FM_LAST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME = PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME+" "+PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME;

        crewInformationPage.enterFirstName(PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME);
        crewInformationPage.enterLastName(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        log.info("#################################################");
        log.info("Created guest user full name is: {}", PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME);
        log.info("#################################################");
        basePage.type(commonPage.dateInput(PreconditionConstants.EMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDateByDays(5));
        basePage.type(commonPage.dateInput(PreconditionConstants.DISEMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDate(PreconditionConstants.DISEMBARKATION_DATE_MONTH));
        basePage.clickOnText(PreconditionConstants.SAVE_NEXT_OPTIONAL_TXT);
        // Assign cabin number
        guestCheckInCheckOutPage.guestCabinNumber();


        basePage.clickOnMatchingTextByIndex(PreconditionConstants.SAVE_NEXT_OPTIONAL_TXT, 2);
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
        // Add document details
        commonPage.enterDate(PreconditionConstants.DOC_EXPIRY_DATE_TXT, DateTimeUtils.getDate(PreconditionConstants.DOC_EXPITY_DATE_MONTH_VALUE));
        commonPage.enterDate(PreconditionConstants.BIRTH_DATE, DateTimeUtils.getDateYearsAgo(PreconditionConstants.GUEST_BIRTH_DATE_MONTH));
        guestServiceRequestPage.selectCategoryDropDown(PreconditionConstants.GENDER_DROPDOWN_TXT,PreconditionConstants.MALE_FIELD_TXT);
        crewInformationPage.enterDocumentNr(String.valueOf(excursionManagementPage.getRandomNumber()));
        basePage.clickOnMatchingTextByIndex(PreconditionConstants.SAVE_AND_FINISH_TXT, 3);

        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
        // User logs out from application
        guestProfilePage.closeQuickLinks();
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");
    }
    @And("Search for the bulk operation guest user")
    public void searchForTheBulkOperationGuestUser() {
        commonPage.bulkOperationsearchFor(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
    }
    @Given("A new crew user is created without being checked in")
    public void aNewCrewUserIsCreatedWithoutBeingCheckedIn() {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.CREWSERVICE, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.CREW_SERVICES);
        crewInformationPage.clickOnStatusLink(PreconditionConstants.NEW_RESERVATION);

        //Fill name and date
        PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME = basePage.generateName(PreconditionConstants.FM_FIRST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME = basePage.generateName(PreconditionConstants.FM_LAST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME = PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME + " " + PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME;

        crewInformationPage.enterFirstName(PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME);
        crewInformationPage.enterLastName(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        log.info("#################################################");
        log.info("Created guest user full name is: {}", PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME);
        log.info("#################################################");

        guestServiceRequestPage.selectDropDown(PreconditionConstants.GENDER_DROPDOWN_TXT, PreconditionConstants.MALE_FIELD_TXT);

        commonPage.enterDate(PreconditionConstants.DATE_OF_BIRTH, DateTimeUtils.getDateYearsAgo(PreconditionConstants.GUEST_BIRTH_DATE_MONTH));
        seleniumActions.selectDropdownOption(PreconditionConstants.NATIONALITY, ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        crewInformationPage.userAbleEnableTheToggleSliderInNewProfile();
        crewInformationPage.userAbleClickTheTabOptions();
        crewInformationPage.clickSaveButton();

        basePage.type(commonPage.dateInput(PreconditionConstants.EMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDateByDays(5));
        basePage.type(commonPage.dateInput(PreconditionConstants.DISEMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDate(PreconditionConstants.DISEMBARKATION_DATE_MONTH));
        crewInformationPage.userAbleClickTheTabOptions();

        crewInformationPage.clickSaveAndNextButton();

        // Assign cabin number
        guestCheckInCheckOutPage.guestCabinNumber();
        crewInformationPage.clickSaveAndNextButtonInAssignCabin();
        crewInformationPage.cabinBookingConformationPopUp();

        // Add document details
        commonPage.enterDate(PreconditionConstants.DOC_EXPIRY_DATE_TXT, DateTimeUtils.getDate(PreconditionConstants.DOC_EXPITY_DATE_MONTH_VALUE));
        crewInformationPage.enterDocumentNr(String.valueOf(excursionManagementPage.getRandomNumber()));
        crewInformationPage.clickOnEyeIcon();


        crewInformationPage.clickSaveAndNextButtonInAssignCabin();
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
        crewInformationPage.navigateToSaveAndOpenProfile(PreconditionConstants.SAVE_AND_OPEN_PROFILE);
        shipSecurityPage.userViewTheProfile();
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");

    }
    @Given("A new crew user is created without assigning a cabin number")
    public void aNewCrewUserIsCreatedWithoutAssigningACabinNumber() {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.CREWSERVICE, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.CREW_SERVICES);
        crewInformationPage.clickOnStatusLink(PreconditionConstants.NEW_RESERVATION);

        //Fill name and date
        PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME = basePage.generateName(PreconditionConstants.FM_FIRST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME = basePage.generateName(PreconditionConstants.FM_LAST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME = PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME+" "+PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME;

        crewInformationPage.enterFirstName(PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME);
        crewInformationPage.enterLastName(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        log.info("#################################################");
        log.info("Created guest user full name is: {}", PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME);
        log.info("#################################################");

        guestServiceRequestPage.selectDropDown(PreconditionConstants.GENDER_DROPDOWN_TXT,PreconditionConstants.MALE_FIELD_TXT);

        commonPage.enterDate(PreconditionConstants.DATE_OF_BIRTH, DateTimeUtils.getDateYearsAgo(PreconditionConstants.GUEST_BIRTH_DATE_MONTH));
        seleniumActions.selectDropdownOption(PreconditionConstants.NATIONALITY,ExcursionManagementConstants.DROPDOWN_VALUE_ONE);
        crewInformationPage.userAbleEnableTheToggleSliderInNewProfile();
        crewInformationPage.userAbleClickTheTabOptions();
        crewInformationPage.clickSaveButton();

        basePage.type(commonPage.dateInput(PreconditionConstants.EMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDateByDays(5));
        basePage.type(commonPage.dateInput(PreconditionConstants.DISEMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDate(PreconditionConstants.DISEMBARKATION_DATE_MONTH));
        crewInformationPage.userAbleClickTheTabOptions();

        crewInformationPage.clickSaveAndNextButton();

        // SKIP cabin number
        crewInformationPage.clickSkipAndNextButton();
        // Add document details
        commonPage.enterDate(PreconditionConstants.DOC_EXPIRY_DATE_TXT, DateTimeUtils.getDate(PreconditionConstants.DOC_EXPITY_DATE_MONTH_VALUE));
        crewInformationPage.enterDocumentNr(String.valueOf(excursionManagementPage.getRandomNumber()));
        crewInformationPage.clickOnEyeIcon();


        crewInformationPage.clickSaveAndNextButtonInAssignCabin();
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
        crewInformationPage.navigateToSaveAndOpenProfile(PreconditionConstants.SAVE_AND_OPEN_PROFILE);
        shipSecurityPage.userViewTheProfile();
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");

    }

    @Then("User {string} a tour for today's date for the guest")
    public void userTourForTodayDateForTheGuest(String book) {
        homePage.navigateToSelectedService(ExcursionManagementConstants.EXCURSIONS);
        guestServicePage.userOpenServiceItemCard(ExcursionManagementConstants.BOOKINGS_MANAGER);
        excursionManagementPage.userClickOnBookingsGuestsAddButton();
        commonPage.clickOnLabel(ExcursionManagementConstants.GUESTS);
        excursionManagementPage.userSearchForTheGuest();
        excursionManagementSteps.userSaveSelectedGuestNameFromTheGuestSearch();
        commonSteps.userClickOnThe(ExcursionManagementConstants.SELECT);
        boolean bookableExcursionAvailable = excursionManagementPage.userSelectsTodaysDateForBooking();
        Assert.assertTrue(bookableExcursionAvailable, "No bookable excursions are available for today.");
        excursionManagementSteps.userATourForSelectedGuest(book);
        excursionManagementPage.drawSignatureforConfirmation();
        context.setContext("SKIP_SCREENSHOT", "true");
    }
    public void createGuestUser(String Status) {

        homePage.navigateToService(GlobalConstants.GUESTSERVICE_MODULE_TXT.trim());
        guestServicePage.openGuestServiceItem(PreconditionConstants.CHECK_IN_TXT);
        commonPage.openLinkUsingHref(GlobalConstants.QUICK_LINKS_TXT);
        seleniumActions.clickElementByText(webDriver, PreconditionConstants.CREATE_GUEST_RESERVATION_TXT);
        PreconditionConstants.NEWLY_CREATED_MULTI_GUEST_FIRSTNAME = basePage.generateName(PreconditionConstants.FM_FIRST_NAME);
        PreconditionConstants.NEWLY_CREATED_MULTI_GUEST_LASTNAME = basePage.generateName(PreconditionConstants.FM_LAST_NAME);

        crewInformationPage.enterFirstName(PreconditionConstants.NEWLY_CREATED_MULTI_GUEST_FIRSTNAME);
        crewInformationPage.enterLastName(PreconditionConstants.NEWLY_CREATED_MULTI_GUEST_LASTNAME);

        basePage.type(commonPage.dateInput(PreconditionConstants.EMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDateByDays(5));
        basePage.type(commonPage.dateInput(PreconditionConstants.DISEMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDate(PreconditionConstants.DISEMBARKATION_DATE_MONTH));
        basePage.clickOnText(PreconditionConstants.SAVE_NEXT_OPTIONAL_TXT);
        guestCheckInCheckOutPage.guestCabinNumber();
        basePage.clickOnMatchingTextByIndex(PreconditionConstants.SAVE_NEXT_OPTIONAL_TXT, 2);
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
        commonPage.enterDate(PreconditionConstants.DOC_EXPIRY_DATE_TXT, DateTimeUtils.getDate(PreconditionConstants.DOC_EXPITY_DATE_MONTH_VALUE));
        commonPage.enterDate(PreconditionConstants.BIRTH_DATE, DateTimeUtils.getDateYearsAgo(PreconditionConstants.GUEST_BIRTH_DATE_MONTH));
        guestServiceRequestPage.selectCategoryDropDown(PreconditionConstants.GENDER_DROPDOWN_TXT, PreconditionConstants.MALE_FIELD_TXT);
        crewInformationPage.enterDocumentNr(String.valueOf(excursionManagementPage.getRandomNumber()));
        basePage.clickOnMatchingTextByIndex(PreconditionConstants.SAVE_AND_FINISH_TXT, 3);
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
        commonPage.openLinkUsingHref(GlobalConstants.QUICK_LINKS_TXT);
        seleniumActions.clickElementByText(webDriver, PreconditionConstants.PROFILE_CHECK_IN_TXT);
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
        if (Status.contains(GuestCheckInCheckOutConstants.ONBOARD)) {
            guestServicePage.openSubMenu(PreconditionConstants.CRUISE_INFORMATION);
            guestCheckInCheckOutPage.onBoardingTheUser();
            commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
            guestCheckInCheckOutPage.saveButton();
        }
        guestProfilePage.closeQuickLinks();
        commonPage.clickIfPresent(PreconditionConstants.YES);
    }

    @Given("multiple new guest users are created as {string}")
    public void multipleNewGuestUsersAreCreatedAs(String Status) {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.GUESTSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);

// Create Guest 1
        createGuestUser(Status);
        PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME = PreconditionConstants.NEWLY_CREATED_MULTI_GUEST_LASTNAME;
// Create Guest 2
        createGuestUser(Status);
        PreconditionConstants.NEWLY_CREATED_GUEST2_LASTNAME = PreconditionConstants.NEWLY_CREATED_MULTI_GUEST_LASTNAME;

        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");
    }

    @Given("a new minor guest user is created with status {string}")
    public void aNewMinorGuestUserIsCreatedWithStatus(String Status) {
        loginPage.enterUserCredsAndClickLoginBtn(GlobalConstants.GUESTSERVICEMANAGER, GlobalConstants.OTALIOSHIPLAB_PWD);
        homePage.navigateToService(GlobalConstants.GUESTSERVICE_MODULE_TXT.trim());
        guestServicePage.openGuestServiceItem(PreconditionConstants.CHECK_IN_TXT);
        commonPage.openLinkUsingHref(GlobalConstants.QUICK_LINKS_TXT);
        seleniumActions.clickElementByText(webDriver, PreconditionConstants.CREATE_GUEST_RESERVATION_TXT);
        //Fill name and date
        PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME = basePage.generateName(PreconditionConstants.FM_FIRST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME = basePage.generateName(PreconditionConstants.FM_LAST_NAME);
        PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME = PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME+" "+PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME;

        crewInformationPage.enterFirstName(PreconditionConstants.NEWLY_CREATED_GUEST_FIRSTNAME);
        crewInformationPage.enterLastName(PreconditionConstants.NEWLY_CREATED_GUEST_LASTNAME);
        log.info("#################################################");
        log.info("Created guest user full name is: {}", PreconditionConstants.NEWLY_CREATED_GUEST_FULLNAME);
        log.info("#################################################");
        basePage.type(commonPage.dateInput(PreconditionConstants.EMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDateByDays(5));
        basePage.type(commonPage.dateInput(PreconditionConstants.DISEMBARKATION_DATE_LABEL_TXT), DateTimeUtils.getDate(PreconditionConstants.DISEMBARKATION_DATE_MONTH));
        basePage.clickOnText(PreconditionConstants.SAVE_NEXT_OPTIONAL_TXT);
        // Assign cabin number
        guestCheckInCheckOutPage.guestCabinNumber();


        basePage.clickOnMatchingTextByIndex(PreconditionConstants.SAVE_NEXT_OPTIONAL_TXT, 2);
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
        // Add document details
        commonPage.enterDate(PreconditionConstants.DOC_EXPIRY_DATE_TXT, DateTimeUtils.getDate(PreconditionConstants.DOC_EXPITY_DATE_MONTH_VALUE));
        commonPage.enterDate(PreconditionConstants.BIRTH_DATE, DateTimeUtils.getDateYearsAgo(PreconditionConstants.MINOR_GUEST_BIRTH_DATE_MONTH));
        guestServiceRequestPage.selectCategoryDropDown(PreconditionConstants.GENDER_DROPDOWN_TXT,PreconditionConstants.MALE_FIELD_TXT);
        crewInformationPage.enterDocumentNr(String.valueOf(excursionManagementPage.getRandomNumber()));
        basePage.clickOnMatchingTextByIndex(PreconditionConstants.SAVE_AND_FINISH_TXT, 3);

        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);

        // Marking guest checkin from profile
        commonPage.openLinkUsingHref(GlobalConstants.QUICK_LINKS_TXT);
        seleniumActions.clickElementByText(webDriver, PreconditionConstants.PROFILE_CHECK_IN_TXT);
        commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);

        if(Status.contains(GuestCheckInCheckOutConstants.ONBOARD)) {
            guestServicePage.openSubMenu(PreconditionConstants.CRUISE_INFORMATION);
            guestCheckInCheckOutPage.onBoardingTheUser();
            commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
            guestCheckInCheckOutPage.saveButton();
        }

        // User logs out from application
        guestProfilePage.closeQuickLinks();
        if(Status.contains(GuestCheckInCheckOutConstants.ONBOARD)) {
            commonPage.clickIfPresent(PreconditionConstants.CONFIRM_BTN_TXT);
        }
        loginPage.logout();
        context.setContext("SKIP_SCREENSHOT", "true");
    }
}
