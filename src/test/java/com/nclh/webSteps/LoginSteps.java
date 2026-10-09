package com.nclh.webSteps;

import com.nclh.constants.GlobalConstants;
import com.nclh.constants.LoginConstants;
import com.nclh.dataProviders.ExcelReader;
import com.nclh.managers.AllDriverManager;
import com.nclh.managers.FileReaderManager;
import com.nclh.pageObjects.HomePage;
import com.nclh.pageObjects.LoginPage;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginSteps extends AllDriverManager {
    LoginPage loginPage;
    HomePage homePage;
    Context context;
    CommonFunctions commonFunctions;

    public LoginSteps(Context context) {
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
        commonFunctions = new CommonFunctions();
        this.context = context;

    }


    @Then("Login page is displayed")
    public void loginPageIsDisplayed() {
//        getDriver().navigate().refresh();
        Assert.assertEquals(loginPage.getLoginText(), LoginConstants.LOGIN_LABEL_TEXT);

    }

    @When("Input {string} as email, {string} as password, {string} as account type")
    public void inputAsEmailAsPasswordAsAccountType(String email, String password, String account) {
        System.out.println("---" + email);
        System.out.println("---" + password);
        System.out.println("---" + account);
    }



@When("Input credentials to login into nclh")
public void inputCredentialsToLoginWithoutHeaders(DataTable dataTable) {

//    context.setContext("SKIP_SCREENSHOT", "true");

    List<String> dataRow = dataTable.row(0);
    String role = dataRow.get(0);
    String userProfile = dataRow.get(2);

    String username = null;

    // Default password
    String password = ExcelReader.readUserData(
            "Guest Services",
            GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
    );

    switch (role) {

        case "Reservation Mgmt & Guest Services":
            username = ExcelReader.readUserData(
                    "Reservation Mgmt & Guest Services",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Reservation Mgmt & Guest Services",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Guest Services":
            username = ExcelReader.readUserData(
                    "Guest Services",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Guest Services",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Crew Services":
            username = ExcelReader.readUserData(
                    "Personnel HR",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Personnel HR",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Housekeeping":
            username = ExcelReader.readUserData(
                    "Housekeeping",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Housekeeping",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Sandbox":
            username = ExcelReader.readUserData("users", "Sandbox", "Email");

            break;


        case "Safety":
            username = ExcelReader.readUserData(
                    "Safety",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Safety",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Security":
            username = ExcelReader.readUserData(
                    "Security",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Security",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "POS":
            username = ExcelReader.readUserData(
                    "POS Shop",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            break;

        case "POS User":
            username = ExcelReader.readUserData(
                    "POS User",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "POS User",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Excursion Management":
            username = ExcelReader.readUserData(
                    "Shore excursion/ Destination Services",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Shore excursion/ Destination Services",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "F&B dining":
            username = ExcelReader.readUserData(
                    "Dining",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Dining",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Hotel Maintenance":
            username = ExcelReader.readUserData(
                    "Hotel Maintenance",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Hotel Maintenance",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Entertainment":
            username = ExcelReader.readUserData(
                    "Events/Entertainment",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Events/Entertainment",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Financial Management":
            username = ExcelReader.readUserData(
                    "Financial Management",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Financial Management",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Demo User":
            username = ExcelReader.readUserData(
                    "Demo User",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            break;

        case "Security Officer":
            username = ExcelReader.readUserData(
                    "Security Officer",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Security Officer",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Group Service Coordinator":
            username = ExcelReader.readUserData(
                    "Group Service Coordinator",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Group Service Coordinator",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Security Guard":
            username = ExcelReader.readUserData(
                    "Security Guard",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Security Guard",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Safety Officer":
            username = ExcelReader.readUserData(
                    "Safety Officer",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Safety Officer",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "HR Operations Manager":
            username = ExcelReader.readUserData(
                    "HR Operations Manager",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "HR Operations Manager",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Guest Service Representative":
            username = ExcelReader.readUserData(
                    "Guest Service Representative",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Guest Service Representative",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Guest Service Manager":
            username = ExcelReader.readUserData(
                    "Guest Service Manager",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Events/Entertainment",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Shorex Manager":
            username = ExcelReader.readUserData(
                    "Shorex Manager",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Shorex Manager",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Shorex Staff":
            username = ExcelReader.readUserData(
                    "Shorex Staff",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Shorex Staff",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Assistant Financial Accountant":
            username = ExcelReader.readUserData(
                    "Assistant Financial Accountant",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Assistant Financial Accountant",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;


        case "Waiter":
            username = ExcelReader.readUserData(
                    "Waiter",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Waiter",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;


            case "Receptionist":
            username = ExcelReader.readUserData(
                    "Receptionist",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Receptionist",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;
            case "Crew Purser":
            username = ExcelReader.readUserData(
                    "Crew Purser",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Crew Purser",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        case "Concierge":
            username = ExcelReader.readUserData(
                    "Concierge",
                    GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
            );
            password = ExcelReader.readUserData(
                    "Concierge",
                    GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
            );
            break;

        default:
            throw new IllegalStateException("Unexpected role: " + role);
    }

    loginPage.login(username, password);
    loginPage.selectUserProfile(userProfile);
}

    @When("Input credentials to login into nclh portal")
    public void inputCredentialsToLoginportal(DataTable dataTable) {

        List<List<String>> rows = dataTable.asLists(String.class);

        List<String> selectedRow = null;

        // Find matching row based on CLIENT_TYPE
        for (List<String> row : rows) {

            String clientTypeFromFeature = row.get(0).trim();

            if (clientTypeFromFeature.equalsIgnoreCase(GlobalConstants.CLIENT_TYPE)) {
                selectedRow = row;
                break;
            }
        }

        // If only one row exists and no match found, use that row
        if (selectedRow == null && rows.size() == 1) {
            selectedRow = rows.get(0);
        }

        // If still null, throw error
        if (selectedRow == null) {
            throw new IllegalStateException(
                    "No matching CLIENT_TYPE found in feature file for: "
                            + GlobalConstants.CLIENT_TYPE
            );
        }

        // Read values from selected row
        String clientType = selectedRow.get(0);
        String role = selectedRow.get(1);
        String passwordFromFeature = selectedRow.get(2);
        String userProfile = selectedRow.get(3);

        String username = null;

        // Default password
        String password = ExcelReader.readUserData(
                "Guest Services",
                GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
        );

        switch (role) {

            case "Reservation Mgmt & Guest Services":
                username = ExcelReader.readUserData(
                        "Reservation Mgmt & Guest Services",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Reservation Mgmt & Guest Services",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Guest Services":
                username = ExcelReader.readUserData(
                        "Guest Services",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Guest Services",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Chief Purser":
                username = ExcelReader.readUserData(
                        "Chief Purser",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Chief Purser",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Crew Services":
                username = ExcelReader.readUserData(
                        "Personnel HR",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Personnel HR",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Housekeeping":
                username = ExcelReader.readUserData(
                        "Housekeeping",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Housekeeping",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Engineer":
                username = ExcelReader.readUserData(
                        "Engineer",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Engineer",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Sandbox":
                username = ExcelReader.readUserData("users", "Sandbox", "Email");

                break;


            case "Safety":
                username = ExcelReader.readUserData(
                        "Safety",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Safety",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Security":
                username = ExcelReader.readUserData(
                        "Security",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Security",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "POS":
                username = ExcelReader.readUserData(
                        "POS Shop",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "POS Shop",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Boutique Manager":
                username = ExcelReader.readUserData(
                        "Boutique Manager",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Boutique Manager",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;
            case "Beverage Manager":
                username = ExcelReader.readUserData(
                        "Beverage Manager",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Beverage Manager",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "POS User":
                username = ExcelReader.readUserData(
                        "POS User",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "POS User",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Excursion Management":
                username = ExcelReader.readUserData(
                        "Shore excursion/ Destination Services",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Shore excursion/ Destination Services",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "F&B dining":
                username = ExcelReader.readUserData(
                        "Dining",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Dining",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Hotel Maintenance":
                username = ExcelReader.readUserData(
                        "Hotel Maintenance",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Hotel Maintenance",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Entertainment":
                username = ExcelReader.readUserData(
                        "Events/Entertainment",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Events/Entertainment",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Financial Management":
                username = ExcelReader.readUserData(
                        "Financial Management",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Financial Management",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Demo User":
                username = ExcelReader.readUserData(
                        "Demo User",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                break;

            case "Security Officer":
                username = ExcelReader.readUserData(
                        "Security Officer",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Security Officer",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Concierge":
                username = ExcelReader.readUserData(
                        "Concierge",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Concierge",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Security Guard":
                username = ExcelReader.readUserData(
                        "Security Guard",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Security Guard",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Safety Officer":
                username = ExcelReader.readUserData(
                        "Safety Officer",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Safety Officer",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "HR Operations Manager":
                username = ExcelReader.readUserData(
                        "HR Operations Manager",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "HR Operations Manager",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Guest Service Representative":
                username = ExcelReader.readUserData(
                        "Guest Service Representative",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Guest Service Representative",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Guest Service Manager":
                username = ExcelReader.readUserData(
                        "Guest Service Manager",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Events/Entertainment",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Shorex Manager":
                username = ExcelReader.readUserData(
                        "Shorex Manager",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Shorex Manager",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Shorex Staff":
                username = ExcelReader.readUserData(
                        "Shorex Staff",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Shorex Staff",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Assistant Financial Accountant":
                username = ExcelReader.readUserData(
                        "Assistant Financial Accountant",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Assistant Financial Accountant",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;


            case "Sales Associate":
                username = ExcelReader.readUserData(
                        "Sales Associate",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Sales Associate",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Bartender":
                username = ExcelReader.readUserData(
                        "Bartender",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Bartender",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Restaurant Manager":
                username = ExcelReader.readUserData(
                        "Restaurant Manager",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Restaurant Manager",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "Waiter":
                username = ExcelReader.readUserData(
                        "Waiter",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Waiter",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;


            case "Receptionist":
                username = ExcelReader.readUserData(
                        "Receptionist",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Receptionist",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;
            case "Crew Purser":
                username = ExcelReader.readUserData(
                        "Crew Purser",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Crew Purser",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            case "2nd Purser Pax":
                username = ExcelReader.readUserData(
                        "2nd Purser Pax",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "2nd Purser Pax",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;
            case "Group Service Coordinator":
                username = ExcelReader.readUserData(
                        "Group Service Coordinator",
                        GlobalConstants.TEST_ENVIRONMENT.getEmailColumn()
                );
                password = ExcelReader.readUserData(
                        "Group Service Coordinator",
                        GlobalConstants.TEST_ENVIRONMENT.getPasswordColumn()
                );
                break;

            default:
                throw new IllegalStateException("Unexpected role: " + role);
        }

        loginPage.login(username, password);
        loginPage.selectUserProfile(userProfile);

    }

    @When("Input credentials to login with headers table")
    public void inputCredentialsToLoginWithHeadersTable(DataTable dataTable) {
        List<Map<String,String>> dataRow = dataTable.asMaps(String.class,String.class);

        //Use for...loop if you have multiple data table
        for (Map<String, String> dataMap : dataRow) {
            String email = dataMap.get("Email");
            String password = dataMap.get("Password");
            loginPage.login(email, password);
            System.out.println("row header email " + email);
            System.out.println("row index 1 --- " + dataMap.get("Password"));
            System.out.println("row index 2 --- " + dataMap.get("Account Type"));
        }
    }

    @And("User logout from nclh")
    public void userLogoutFromNclh() {
        loginPage.logout();
    }


    @When("Input credentials to login into nclh web client")
    public void inputUserCredentialsToLoginWithoutHeaders(DataTable dataTable) {

        List<String> row = dataTable.row(0);
        String role = row.get(0);
        String userProfile = row.get(1);

        boolean isShiplab = FileReaderManager.getInstance()
                .getConfigFileReader()
                .getUrl()
                .contains("shiplab");

        // Role → Sheet name mapping
        Map<String, String> sheetMap = Map.ofEntries(
                Map.entry("Guest Services", "Guest Services"),
                Map.entry("Crew Services", "Personnel HR"),
                Map.entry("Housekeeping", "Housekeeping"),
                Map.entry("Sandbox", "users"),
                Map.entry("Safety", "Safety"),
                Map.entry("Security", "Security"),
                Map.entry("POS", "POS Shop"),
                Map.entry("POS User", "POS User"),
                Map.entry("Excursion Management", "Shore excursion/ Destination Services "),
                Map.entry("F&B dining", "Dining"),
                Map.entry("Hotel Maintenance", "Hotel Maintenance"),
                Map.entry("Entertainment", "Events/Entertainment"),
                Map.entry("Financial Management", "Financial Management"),
                Map.entry("Demo User", "Demo User"),
                Map.entry("Bar Management", "Bar Management"),
                Map.entry("Guest Relations", "Guest Relations"),
                Map.entry("Basic Guest And Crew", "Basic Guest And Crew"),
                Map.entry("Innovation Technology", "Innovation Technology"),
                Map.entry("Charter/Sixthman", "Charter/Sixthman"),
                Map.entry("NCLH - Read Only", "NCLH - Read Only"),
                Map.entry("Casino Slots", "Casino Slots"),
                Map.entry("Concierge", "Concierge"),
                Map.entry("Casino Cage Manager", "Casino Cage Manager"),
                Map.entry("Crew Enrichment", "Crew Enrichment"),
                Map.entry("Casino Management", "Casino Management"),
                Map.entry("Culinary", "Culinary"),
                Map.entry("Culinary - Speciality Chefs", "Culinary - Speciality Chefs"),
                Map.entry("Deck - Bridge Team", "Deck - Bridge Team"),
                Map.entry("Deck - Chief Officer", "Deck - Chief Officer"),
                Map.entry("Embarkation Pier Security", "Embarkation Pier Security"),
                Map.entry("Engine - Communications", "Engine - Communications"),
                Map.entry("Engine Management", "Engine Management"),
                Map.entry("Entertainment Youth Staff", "Entertainment Youth Staff"),
                Map.entry("Hotel Maintenance - Staff", "Hotel Maintenance - Staff"),
                Map.entry("Housekeeping - Butlers", "Housekeeping - Butlers"),
                Map.entry("Housekeeping - Linenkeeper", "Housekeeping - Linenkeeper"),
                Map.entry("Housekeeping Staff", "Housekeeping Staff"),
                Map.entry("Housekeeping Supervisor", "Housekeeping Supervisor"),
                Map.entry("Laundry", "Laundry"),
                Map.entry("Onboard Revenue - Art", "Onboard Revenue - Art"),
                Map.entry("Onboard Revenue - Gift Shop - Dufry - Staff", "Onboard Revenue - Gift Shop - Dufry - Staff"),
                Map.entry("Onboard Revenue - Internet", "Onboard Revenue - Internet"),
                Map.entry("Onboard Revenue - Photo", "Onboard Revenue - Photo"),
                Map.entry("Onboard Revenue - ShoreX Staff", "Onboard Revenue - ShoreX Staff"),
                Map.entry("Onboard Revenue - Spa", "Onboard Revenue - Spa"),
                Map.entry("Otalio No Config / All Functionalities", "Otalio No Config / All Functionalities"),
                Map.entry("Otalio No Config / Reporting Team", "Otalio No Config / Reporting Team"),
                Map.entry("Port Shopping Guide", "Port Shopping Guide"),
                Map.entry("Reservation Future Cruise Sales", "Reservation Future Cruise Sales"),
                Map.entry("Security/Surveillance - Staff", "Security/Surveillance - Staff"),
                Map.entry("Yard Contractors/Ferry Operation", "Yard Contractors/Ferry Operation"),
                Map.entry("Crew/Personnel Management", "Crew/Personnel Management"),
                Map.entry("Cruise Management", "Cruise Management"),
                Map.entry("Embarkation Management", "Embarkation Management"),
                Map.entry("Entertainment Management", "Entertainment Management"),
                Map.entry("Financial Management/Senior", "Financial Management/Senior"),
                Map.entry("Food & Beverage Management", "Food & Beverage Management"),
                Map.entry("Hotel Maintenance - Management", "Hotel Maintenance - Management"),
                Map.entry("Hotel Management", "Hotel Management"),
                Map.entry("Medical Management", "Medical Management"),
                Map.entry("Onboard Revenue - Gift Shop - Effy - Management", "Onboard Revenue - Gift Shop - Effy - Management"),
                Map.entry("Onboard Revenue - ShoreX Management", "Onboard Revenue - ShoreX Management"),
                Map.entry("Reservation Mgmt & Guest Services", "Reservation Mgmt & Guest Services"),
                Map.entry("Restaurant Management", "Restaurant Management"),
                Map.entry("Restaurant Management (AMD)", "Restaurant Management (AMD)"),
                Map.entry("Security/Surveillance - Management", "Security/Surveillance - Management"),
                Map.entry("Ship/Senior Management", "Ship/Senior Management"),
                Map.entry("Ship/Senior Management w/Security", "Ship/Senior Management w/Security"),
                Map.entry("Shipboard IT Helpdesk", "Shipboard IT Helpdesk"),
                Map.entry("Entertainment Staff", "Entertainment Staff"),
                Map.entry("Guest Services & Onboard Finance", "Guest Services & Onboard Finance"),
                Map.entry("Housekeeping Management", "Housekeeping Management"),
                Map.entry("Onboard Revenue - Gift Shop - Diamond International - Staff", "Onboard Revenue - Gift Shop - Diamond International - Staff"),
                Map.entry("Onboard Revenue - Gift Shop - Na Hoku - Management", "Onboard Revenue - Gift Shop - Na Hoku - Management"),
                Map.entry("Bar Staff", "Bar Staff"),
                Map.entry("Bar Tenders", "Bar Tenders"),
                Map.entry("Butlers", "Butlers"),
                Map.entry("Executive Concierge", "Executive Concierge"),
                Map.entry("Hotel Controller", "Hotel Controller"),
                Map.entry("Night Audit", "Night Audit"),
                Map.entry("Group Service Coordinator", "Group Service Coordinator")
        );

        if (!sheetMap.containsKey(role)) {
            throw new IllegalStateException("Unexpected role: " + role);
        }

        String sheet = sheetMap.get(role);

        // Default column selection
        String emailColumn = isShiplab ? "Email" : "AlluraEmail";
        String passwordColumn = isShiplab ? "Password" : "AlluraPassword";

        // Handle special cases for password overrides
        if (role.equals("Guest Services")) {
            // already handled by default logic
        } else if (role.equals("Housekeeping") || role.equals("Entertainment")) {
            passwordColumn = isShiplab ? "Password" : "AlluraPassword";
        }

        // Sandbox has different structure
        String username;
        String password;

        if (role.equals("Sandbox")) {
            username = ExcelReader.readUserData("users", "Sandbox", "Email");
            password = ExcelReader.readUserData("Guest Services", passwordColumn); // if needed adjust
        } else {
            username = ExcelReader.readUserData(sheet, emailColumn);
            password = ExcelReader.readUserData(sheet, passwordColumn);
        }

        loginPage.login(username, password);
        loginPage.selectUserProfile(userProfile);
    }

    @When("User logs into the Otalio portal")
    public void userLogsIntoPortal(DataTable dataTable) {
        List<String> dataRow = dataTable.row(0);
        String emailKey = dataRow.get(0);     // e.g. "Guest Services"
        boolean isShiplab = FileReaderManager.getInstance()
                .getConfigFileReader()
                .getUrl()
                .contains("shiplab");

        String username;
        String password;

        // Default password logic
        password = isShiplab
                ? ExcelReader.readUserData("Guest Services", "Password")
                : ExcelReader.readUserData("Guest Services", "AlluraPassword");

        switch (emailKey) {

            case "Guest Services":
                username = isShiplab
                        ? ExcelReader.readUserData("Guest Services", "Email")
                        : ExcelReader.readUserData("Guest Services", "AlluraEmail");
                break;

            case "Crew Services":
                username = isShiplab
                        ? ExcelReader.readUserData("Personnel HR", "Email")
                        : ExcelReader.readUserData("Personnel HR", "AlluraEmail");
                break;

            case "Housekeeping":
                username = isShiplab
                        ? ExcelReader.readUserData("Housekeeping", "Email")
                        : ExcelReader.readUserData("Housekeeping", "AlluraEmail");

                password = isShiplab
                        ? ExcelReader.readUserData("Housekeeping", "Password")
                        : ExcelReader.readUserData("Housekeeping", "AlluraPassword");
                break;

            case "Sandbox":
                username = ExcelReader.readUserData("users", "Sandbox", "Email");
                break;

            case "Safety":
                username = isShiplab
                        ? ExcelReader.readUserData("Safety", "Email")
                        : ExcelReader.readUserData("Safety", "AlluraEmail");
                break;

            case "Security":
                username = isShiplab
                        ? ExcelReader.readUserData("Security", "Email")
                        : ExcelReader.readUserData("Security", "AlluraEmail");
                break;

            case "POS":
                username = isShiplab
                        ? ExcelReader.readUserData("POS Shop", "Email")
                        : ExcelReader.readUserData("POS Shop", "AlluraEmail");
                break;

            case "POS User":
                username = isShiplab
                        ? ExcelReader.readUserData("POS User", "Email")
                        : ExcelReader.readUserData("POS User", "AlluraEmail");
                break;

            case "Excursion Management":
                username = isShiplab
                        ? ExcelReader.readUserData("Shore excursion/ Destination Services ", "Email")
                        : ExcelReader.readUserData("Shore excursion/ Destination Services ", "AlluraEmail");
                break;

            case "F&B dining":
                username = isShiplab
                        ? ExcelReader.readUserData("Dining", "Email")
                        : ExcelReader.readUserData("Dining", "AlluraEmail");
                break;

            case "Hotel Maintenance":
                username = isShiplab
                        ? ExcelReader.readUserData("Hotel Maintenance", "Email")
                        : ExcelReader.readUserData("Hotel Maintenance", "AlluraEmail");
                break;

            case "Entertainment":
                username = isShiplab
                        ? ExcelReader.readUserData("Events/Entertainment", "Email")
                        : ExcelReader.readUserData("Events/Entertainment", "AlluraEmail");

                password = ExcelReader.readUserData("Events/Entertainment", "Password");
                break;

            case "Financial Management":
                username = isShiplab
                        ? ExcelReader.readUserData("Financial Management", "Email")
                        : ExcelReader.readUserData("Financial Management", "AlluraEmail");
                break;

            case "Demo User":
                username = isShiplab
                        ? ExcelReader.readUserData("Demo User", "Email")
                        : ExcelReader.readUserData("Demo User", "AlluraEmail");
                break;

            case "Group Service Coordinator":
                username = isShiplab
                        ? ExcelReader.readUserData("Group Service Coordinator", "Email")
                        : ExcelReader.readUserData("Group Service Coordinator", "AlluraEmail");
                break;

            case "Concierge":
                username = isShiplab
                        ? ExcelReader.readUserData("Concierge", "Email")
                        : ExcelReader.readUserData("Concierge", "AlluraEmail");
                break;



            default:
                throw new IllegalStateException("Unexpected value: " + emailKey);
        }

        loginPage.enterUserCredsAndClickLoginBtn(username, password);

    }
}
