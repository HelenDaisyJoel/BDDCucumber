package com.nclh.webSteps;

import com.nclh.dataProviders.ExcelReader;
import com.nclh.managers.AllDriverManager;
import com.nclh.pageObjects.GuestProfilePage;
import com.nclh.utilities.CommonFunctions;
import com.nclh.utilities.Context;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GuestProfileSteps extends AllDriverManager {

    Context context;
    CommonFunctions commonFunctions;
    GuestProfilePage guestProfilePage;

    public GuestProfileSteps(Context context) {
        this.context = context;
        commonFunctions = new CommonFunctions();
        guestProfilePage = new GuestProfilePage(getDriver());
    }

    @When("User get the required data from the excel sheet")
    public void user_get_required_data() throws IOException {
        /*List<Map<String, String>> nvsData = ExcelReader.getNVSData(
                "Guest_Profile","FIRST_NAME","LAST_NAME","MIDDLE_NAME","TITLE","GENDER",
                "PROFESSION","BIRTH_DATE","BIRTH_PLACE","NATIONALITY","LOYALTY_CARD_NUM","LOYALTY_CRUISE_POINTS","LOYALTY_TOTAL_POINTS",
                "EXTERNAL_CD","FAMILY_GROUP_CD","PRIMARY_LANGUAGE_CD","LOYALTY_CARD_TYPE","GOLDEN_ID");
        System.out.println(nvsData.get(1));*/

        ;
        List<Map<String, String>> nvsData = ExcelReader.getSeaWareData(
                "Reservations","Guest_Profile","RES_ID","PROFILE_ID","PROFILE_ID");

//       List<String> data =  guestProfilePage.getColumnValues(nvsData.get(0), "FIRST_NAME","LAST_NAME","MIDDLE_NAME","TITLE","GENDER",
//                "PROFESSION","BIRTH_DATE","BIRTH_PLACE","NATIONALITY","LOYALTY_CARD_NUM","LOYALTY_CRUISE_POINTS","LOYALTY_TOTAL_POINTS",
//                "EXTERNAL_CD","FAMILY_GROUP_CD","PRIMARY_LANGUAGE_CD","LOYALTY_CARD_TYPE","GOLDEN_ID");
//        System.out.println(data);

        Map<String, String> firstRecord = nvsData.get(1);
        System.out.println(firstRecord);

        context.setContext("ReservationId", firstRecord.get("ReservationId"));

        for (Map.Entry<String, String> name : firstRecord.entrySet()){
                context.setContext(name.getKey(), name.getValue());
            System.out.println(context.getContext(name.getKey()));
        }
    }

    @Then("User verify user personal data")
    public void userVerifyUserPersonalData() {
        List<String> fieldData = Arrays.asList("Last Name", "First Name","Middle Name","Title","Gender","Profession","Date of Birth","Birth Place","Nationality","Golden ID","Preferred Language");

        List<String> actualFieldData = new ArrayList<>();
        for(int i=0; i<fieldData.size(); i++){
            actualFieldData.add(guestProfilePage.getValue(fieldData.get(i)));
        }

        List<String> expectedFieldData = Arrays.asList(
                context.getContext("LAST_NAME"),context.getContext("FIRST_NAME"),context.getContext("MIDDLE_NAME"),
                context.getContext("TITLE"),context.getContext("GENDER"),context.getContext("PROFESSION"),guestProfilePage.maskYear(context.getContext("BIRTH_DATE")),
                context.getContext("BIRTH_PLACE"),context.getContext("NATIONALITY"), context.getContext("GOLDEN_ID"),context.getContext("PRIMARY_LANGUAGE_CD"));
        System.out.println("******************************");
        System.out.println(guestProfilePage.cleanList(actualFieldData));

        List<String> filteredList = expectedFieldData.stream()
                .filter(item -> !"null".equals(item))
                .collect(Collectors.toList());
        System.out.println(filteredList);
        filteredList.removeIf(item -> "null".equals(item));
        Assert.assertEquals(guestProfilePage.cleanList(expectedFieldData), guestProfilePage.cleanList(actualFieldData), "Lists do not match after removing 'null' values and null references!");
    }

    @Then("User verify user loyalty data")
    public void userVerifyUserLoyaltyData() {
        List<String> fieldData = Arrays.asList("Membership ID", "Loyalty Tier", "Points Total");

        List<String> actualFieldData = new ArrayList<>();
        for(int i=0; i<fieldData.size(); i++){
            actualFieldData.add(guestProfilePage.getValue(fieldData.get(i)));
        }

        List<String> expectedFieldData = Arrays.asList(
                context.getContext("LOYALTY_CARD_NUM"),context.getContext("LOYALTY_CARD_TYPE"), context.getContext("LOYALTY_TOTAL_POINTS"));

        System.out.println(guestProfilePage.cleanList(expectedFieldData));
        System.out.println(guestProfilePage.cleanList(actualFieldData));
    }

    @Then("User verify user personal data for")
    public void userVerifyUserPersonalDataFor(DataTable dataTable) {
        List<String> fieldData = Arrays.asList("Last Name", "First Name","Middle Name","Title","Gender","Profession","Date of Birth","Birth Place","Nationality","Golden ID","Preferred Language");

        List<String> actualFieldData = new ArrayList<>();
        for(int i=0; i<fieldData.size(); i++){
            actualFieldData.add(guestProfilePage.getValue(fieldData.get(i)));
        }

        List<String> resID = Arrays.asList(context.getContext("RES_ID"));
        List<String> expectedFieldData = Arrays.asList(
                context.getContext("LAST_NAME"),context.getContext("FIRST_NAME"),context.getContext("MIDDLE_NAME"),
                context.getContext("TITLE"),context.getContext("GENDER"),context.getContext("PROFESSION"),guestProfilePage.maskYear(context.getContext("BIRTH_DATE")),
                context.getContext("BIRTH_PLACE"),context.getContext("NATIONALITY"), context.getContext("GOLDEN_ID"),context.getContext("PRIMARY_LANGUAGE_CD"));
        System.out.println("******************************");
        System.out.println(guestProfilePage.cleanList(actualFieldData));

        List<String> filteredList = expectedFieldData.stream()
                .filter(item -> !"null".equals(item))
                .collect(Collectors.toList());
        System.out.println(filteredList);
        filteredList.removeIf(item -> "null".equals(item));
        Assert.assertEquals(guestProfilePage.cleanList(expectedFieldData), guestProfilePage.cleanList(actualFieldData), "Lists do not match after removing 'null' values and null references!");
    }

    @Then("User verify user loyalty data for")
    public void userVerifyUserLoyaltyDataFor(DataTable dataTable) {
        List<String> fieldData = Arrays.asList("Membership ID", "Loyalty Tier", "Points Total");
        List<String> expectedFieldData = Arrays.asList(
                context.getContext("LOYALTY_CARD_NUM"),context.getContext("LOYALTY_CARD_TYPE"), context.getContext("LOYALTY_TOTAL_POINTS"));
        System.out.println(expectedFieldData);
        List<String> actualFieldData = new ArrayList<>();
        for(int i=0; i<fieldData.size(); i++){
            actualFieldData.add(guestProfilePage.getValue(fieldData.get(i)));
        }

        System.out.println(guestProfilePage.cleanList(expectedFieldData));
        System.out.println(guestProfilePage.cleanList(actualFieldData));
        Assert.assertEquals(guestProfilePage.cleanList(expectedFieldData), guestProfilePage.cleanList(actualFieldData));
    }


}
