package com.nclh.managers;

import com.nclh.pageObjects.*;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private final WebDriver webDriver;
    private HomePage homePage;
    private LoginPage loginPage;
    private GuestServicePage guestServicePage;
    private GuestInformationPage guestInformationPage;
    private CrewInformationPage crewInformationPage;
    private ShipSecurityPage shipSecurityPage;
    private SafetyManagementPage safetyManagementPage;
    private CommonPage commonPage;
    private PointOfSalePage pointOfSalePage;
    private ActivitiesPlannerPage activitiesPlannerPage;
    private HouseKeepingPage houseKeepingPage;
    private ExcursionManagementPage excursionManagementPage;
    private FAndBDiningPage fAndBDinningPage;
    private GuestServiceRequestPage guestServiceRequestPage;
    private GroupManagementPage groupManagementPage;
    private HotelMaintenancePage hotelMaintenancePage;
    private EntertainmentPage entertainmentPage;
    private GuestCheckInCheckOutPage guestCheckInCheckOutPage;
    private FinanceManagementPage financeManagementPage;

    public PageObjectManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Short Hand If...Else
    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(webDriver) : homePage;
    }

    //General If...Else
    public LoginPage getLoginPage() {

        if (loginPage == null) {
            loginPage = new LoginPage(webDriver);
        }
        return loginPage;
    }

    public ActivitiesPlannerPage getActivitiesPlannerPage() {
        return (activitiesPlannerPage == null) ? activitiesPlannerPage = new ActivitiesPlannerPage(webDriver) : activitiesPlannerPage;
    }

    public PointOfSalePage getPointOfSalePage() {
        return (pointOfSalePage == null) ? pointOfSalePage = new PointOfSalePage(webDriver) : pointOfSalePage;
    }

    public CommonPage getCommonPage() {
        return (commonPage == null) ? commonPage = new CommonPage(webDriver) : commonPage;
    }

    public SafetyManagementPage getSafetyManagementPage() {
        return (safetyManagementPage == null) ? safetyManagementPage = new SafetyManagementPage(webDriver) : safetyManagementPage;
    }

    public GuestServicePage getGuestServicePage() {
        return (guestServicePage == null) ? guestServicePage = new GuestServicePage(webDriver) : guestServicePage;
    }

    public GuestInformationPage getGuestInformationPage() {
        return (guestInformationPage == null) ? guestInformationPage = new GuestInformationPage(webDriver) : guestInformationPage;
    }

    public CrewInformationPage getCrewInformationPage() {
        return (crewInformationPage == null) ? crewInformationPage = new CrewInformationPage(webDriver) : crewInformationPage;
    }

    public ShipSecurityPage getShipSecurityPage() {
        return (shipSecurityPage == null) ? shipSecurityPage = new ShipSecurityPage(webDriver) : shipSecurityPage;
    }

    public HouseKeepingPage getSHousekeepingPage() {
        return (houseKeepingPage == null) ? houseKeepingPage = new HouseKeepingPage(webDriver) : houseKeepingPage;
    }

    public ExcursionManagementPage getExcursionManagementPage() {
        return (excursionManagementPage == null) ? excursionManagementPage = new ExcursionManagementPage(webDriver) : excursionManagementPage;
    }

    public FAndBDiningPage getFAndBDiningPage() {
        return (fAndBDinningPage == null) ? fAndBDinningPage = new FAndBDiningPage(webDriver) : fAndBDinningPage;
    }

    public GuestServiceRequestPage getGuestServiceRequestPage() {
        return (guestServiceRequestPage == null) ? guestServiceRequestPage = new GuestServiceRequestPage(webDriver) : guestServiceRequestPage;
    }

    public GroupManagementPage getGroupManagementPage(){
        return (groupManagementPage == null) ? groupManagementPage = new GroupManagementPage(webDriver): groupManagementPage;
    }

    public HotelMaintenancePage getHotelMaintenancePage(){
        return (hotelMaintenancePage == null) ? hotelMaintenancePage = new HotelMaintenancePage(webDriver): hotelMaintenancePage;
    }

    public EntertainmentPage getEntertainmentPage(){
        return (entertainmentPage == null) ? entertainmentPage = new EntertainmentPage(webDriver): entertainmentPage;
    }

    public GuestCheckInCheckOutPage getGuestCheckInCheckOutPage() {
        return (guestCheckInCheckOutPage == null) ? guestCheckInCheckOutPage = new GuestCheckInCheckOutPage(webDriver): guestCheckInCheckOutPage;
    }

    public FinanceManagementPage getFinanceManagementPage(){
        return (financeManagementPage == null) ? financeManagementPage = new FinanceManagementPage(webDriver): financeManagementPage;
    }
}
