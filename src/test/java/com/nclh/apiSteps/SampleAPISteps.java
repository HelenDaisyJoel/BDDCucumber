package com.nclh.apiSteps;

import com.nclh.apiCommons.APIFunctions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.List;
import java.util.Map;

public class SampleAPISteps {
    public APIFunctions apiFunctions;
    @Given("I want to test on Baseurl {string}")
    public void i_want_to_test_on_baseurl(String endPoint) {
        apiFunctions = new APIFunctions();
        apiFunctions.getEndPoint(endPoint);
    }
    @Given("I navigated to resourcePath {string} and send request {string}")
    public void i_navigated_to_resource_path(String path, String methodType) {
        apiFunctions.sendRequest(path, methodType);
    }
    @Given("I send request with following payload")
    public void i_send_request_with_following_payload(DataTable dataTable) {
        List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);

        //Use for...loop if you have multiple data table
        for (Map<String, String> dataMap : dataRow) {
         /*   String email = dataMap.get("Email");
            String password = dataMap.get("Password");

            System.out.println("row header email " + email);
            System.out.println("row header value " + password);
            System.out.println("row index 1 --- " + dataMap.get("Password"));
            System.out.println("row index 2 --- " + dataMap.get("Account Type"));*/
           // dataMap.put(dataMap.get("key"), dataMap.get("value"));
            System.out.println(dataMap.get("key"));
            System.out.println(dataMap.get("value"));
        }

    }
    @Then("I should get response {string}")
    public void i_should_get_response(String string) {
    }
    @Then("Body should not be Null")
    public void body_should_not_be_null() {
    }
    @Then("Body should contain {string}")
    public void body_should_contain(String string) {
    }


}
