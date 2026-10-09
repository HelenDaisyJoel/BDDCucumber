package com.nclh.managers;

import com.nclh.utilities.APIClient;
import com.nclh.utilities.APIException;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestRailManager {
    public static String TEST_RUN_ID = FileReaderManager.getInstance().getConfigFileReader().getTestRailId();
    public static String TESTRAIL_USERNAME = "mbhakuni@ncl.com";
    public static String TESTRAIL_PASSWORD = "Frodo@2025##";
    public static String TESTRAIL_URL = "https://nclholdings.testrail.io/";

    public static void addResultForTestCase(int testCaseId, int status, String error, String imgPath) throws APIException, IOException, APIException {
        String testRunId = TEST_RUN_ID;
        APIClient apiClient = new APIClient(TESTRAIL_URL);
        apiClient.setUser(TESTRAIL_USERNAME);
        apiClient.setPassword(TESTRAIL_PASSWORD);
        Map<String, Object> data = new HashMap<>();
        data.put("status_id", status);
        data.put("comment", "This test is executed via NCLH Automation framework " + error);
        //apiClient.sendPost("add_result_for_case/"+testRunId+"/"+testCaseId, data);
        //apiClient.sendPost("add_attachment_to_result/" +TEST_RESULT_ID, imgPath);

        JSONObject testResultForTestRun = (JSONObject) apiClient.sendPost("add_result_for_case/" +testRunId+"/" +testCaseId, data);

        String testResultId = testResultForTestRun.get("id").toString();

        apiClient.sendPost("add_attachment_to_result/" +testResultId, imgPath);
    }
}
