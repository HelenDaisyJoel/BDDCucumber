package com.nclh.webSteps;

import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.CommonPage;
import com.nclh.pageObjects.HomePage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class HomeSteps extends AllDriverManager {

    HomePage homePage;
    CommonPage commonPage;


    public HomeSteps() {
        homePage = new HomePage(getDriver());
        commonPage = new CommonPage(getDriver());
    }

    @And("User navigate to menu {string} service")
    public void homePageWithoutAuthorizations(String service) {
        homePage.navigateToService(service);
    }

    @And("User navigates to {string} page")
    public void userNavigatesToHomePage(String homePage) {
        commonPage.assertUrlContains(homePage);
    }
    @And("Clicks on {string} module")
    public void clicksOnModule(String module) {
        homePage.clickOnModule(module);
    }

    @Then("User navigate to menu {string} services")
    public void userNavigateToMenuServices(String service) {
        homePage.userNavigateToMenuServices(service);

    }
    @Then("User wait for {string} display")
    public void userWaitForDisplay(String cardName) {
        homePage.userWaitForDisplay(cardName);
    }

    @Then("User selects the {string} service from the left-side panel")
    public void userSelectsTheServiceFromTheLeftSidePanel(String service) {
        homePage.navigateToSelectedService(service);
    }
}
