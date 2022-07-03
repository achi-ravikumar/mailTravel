package com.utils;

import io.restassured.response.Response;

public class getterSetter {
    public static Response response;

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }


}
