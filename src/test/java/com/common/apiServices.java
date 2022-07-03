package com.common;


import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class apiServices {
    private static String ENDPOINT = "https://dummy.restapiexample.com/api/v1";

    public Response executePostRequest(JSONObject json, String service) {
        int retryCount = 5;
        Response response = null;

        for (int i = 0; i < retryCount; i++) {
            response = given().log().all()
                    .header("Content-Type", "application/json")
                    .body(json.toString())
                    .post(ENDPOINT + service.trim());

            if (response.getStatusCode() == 200) {
                break;
            }
        }

        return response;

    }

    public Response executeGetRequest(String id) {
//        int retryCount = 5;
        Response response = null;

//        for (int i = 0; i < retryCount; i++) {
            response = given().log().all()
                    .header("Content-Type", "application/json")
                    .get(ENDPOINT + "/employee/" + id);

//            if (response.getStatusCode() == 200) {
//                break;
//            }
//            System.out.println(i);
//        }
        return response;
    }

    public Response executeDeleteRequest(String id) {
        int retryCount = 5;
        Response response = null;

        for (int i = 0; i < retryCount; i++) {
            response = given().log().all()
                    .header("Content-Type", "application/json")
                    .delete(ENDPOINT + "/delete/" + id);

            if (response.getStatusCode() == 200) {
                break;
            }
            System.out.println(i);
        }
        return response;
    }


}
