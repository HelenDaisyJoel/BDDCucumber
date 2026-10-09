package com.nclh.apiCommons;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class APIFunctions {

    public String getEndPoint(String uri) {
        //data reading from JSON
        return uri;
    }

    public void sendRequest(String methodType, String resourcePath) {
        Response response;
        switch (methodType) {
            case "GET":
                response =
                        when()
                                .get("https://reqres.in/api/users");
            case "POST":
                response =
                        when()
                                .post("https://reqres.in/api/login");

            case "PUT":
            case "PATCH":
            case "DELETE":
        }
    }


}
