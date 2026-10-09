package com.nclh.webSteps;

import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.GuestInformationPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import java.util.*;

public class GuestInformationSteps extends AllDriverManager {

    GuestInformationPage guestInformationPage;

    public GuestInformationSteps() {
        guestInformationPage = new GuestInformationPage(getDriver());
    }

    @When("Verify guest details")
    public void verifyCrewDetails(DataTable dataTable) {
        SoftAssert softAssert = new SoftAssert();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Map<String, String>> dataRow = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> dataMap : dataRow) {
            String name = dataMap.get("Name");
            String country = dataMap.get("Country");
            String language = dataMap.get("Language");

            softAssert.assertEquals(guestInformationPage.getCrewName(), name);
            softAssert.assertEquals(guestInformationPage.getCountryAndLanguage(), Arrays.asList(country, language));
            System.out.println("row header name " + name);
            System.out.println("row index 1 --- " + dataMap.get("Country"));
            System.out.println("row index 2 --- " + dataMap.get("Language"));

            softAssert.assertAll();
            //softAssert.assertEquals(crewInformationPage.getStatus(), status_check.addAll(status_onboard));
        }
    }
}
