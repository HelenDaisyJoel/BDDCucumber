package com.nclh.managers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class TestRailClient {

    private static final String BASE_URL = "https://nclholdings.testrail.io/";
    private static final String API_USERNAME = "mmadhav@ncl.com";
    private static final String API_TOKEN = "nhFA/weDMKJt8RgLthzp-EOGysqbUh35crA6MSEeP";
    private static final String API_PASSWORD = "Merwan@2502";

    private static String getAuthHeader() {
        String auth = API_USERNAME + ":" + API_TOKEN;
        return Base64.getEncoder().encodeToString(auth.getBytes());
    }

    public static Response addTestResult(int runId, int caseId, int statusId, String comment) {
        String url = BASE_URL + "/index.php?/api/v2/add_result_for_case/" + runId + "/" + caseId;

        Map<String, Object> body = new HashMap<>();
        body.put("status_id", statusId);  // 1=Passed, 2=Blocked, 5=Failed, etc.
        body.put("comment", comment);

        return RestAssured.given()
                .header("Authorization", "Basic " + getAuthHeader())
                .contentType(ContentType.JSON)
                .body(body)
                .post(url);
    }

    public static Response createTestRun(int projectId, String runName, int[] caseIds) {
        String url = BASE_URL + "/index.php?/api/v2/add_run/" + projectId;

        Map<String, Object> body = new HashMap<>();
        body.put("name", runName);
        body.put("case_ids", caseIds);  // List of case IDs

        return RestAssured.given()
                .header("Authorization", "Basic " + getAuthHeader())
                .contentType(ContentType.JSON)
                .body(body)
                .post(url);
    }
}
