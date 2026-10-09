package com.nclh.apiSteps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;

public class UserAPISteps {

    private Response response;
    private User user;

    @Given("the user ID is {string}")
    public void theUserIdIs(String userId) {
        RestAssured.requestSpecification.pathParam("userId", userId);
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        response = RestAssured.get(endpoint);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @Then("the response should contain user ID {string}")
    public void theResponseShouldContainUserId(String userId) {
        User user = response.as(User.class);
        Assert.assertEquals(user.getId(), userId);
    }

    @Given("the user details are:")
    public void theUserDetailsAre(Map<String, String> userDetails) {
        user = new User();
        user.setName(userDetails.get("name"));
        user.setEmail(userDetails.get("email"));
    }

    @When("I send a POST request to {string}")
    public void iSendAPOSTRequestTo(String endpoint) {
        response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(user)
                .post(endpoint);
    }

    @Then("the response should contain the user name {string}")
    public void theResponseShouldContainTheUserName(String userName) {
        User createdUser = response.as(User.class);
        Assert.assertEquals(createdUser.getName(), userName);
    }
}
