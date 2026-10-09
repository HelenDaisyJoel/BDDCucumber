package com.nclh.webSteps;

import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.LoginPage;
import com.nclh.pageObjects.SettingsManagementPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;


public class NegativeRoleTestingSteps extends AllDriverManager {
    LoginPage loginPage;
    SettingsManagementPage settingsManagementPage;

    public NegativeRoleTestingSteps() {
        loginPage = new LoginPage(getDriver());
        settingsManagementPage = new SettingsManagementPage(getDriver());
    }


    @Given("User logs in with valid {} and {}")
    public void userLogsInWithValidAnd(String username, String password) {
        loginPage.enterUserCredsAndClickLoginBtn(username, password);


    }

    @Then("User should get the validation message {}")
    public void userShouldGetTheValidationMessage(String expectedMessage) {
        Assert.assertEquals(settingsManagementPage.getAccessText(), expectedMessage.trim());
    }
}
