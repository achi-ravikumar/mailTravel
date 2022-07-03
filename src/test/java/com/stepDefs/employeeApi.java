package com.stepDefs;

import com.common.apiServices;
import com.utils.getterSetter;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class employeeApi {
    apiServices apiSer = new apiServices();

    getterSetter getSet = new getterSetter();


    @When("I create a Employee with below details")
    public void iCreateAEmployeeWithBelowDetails(DataTable employeeDetails) throws IOException {
        Response response = null;
        int responseCode = 0;
        List<Map<String, String>> emp = employeeDetails.asMaps(String.class, String.class);
        emp.get(0).get("name");
        emp.get(0).get("salary");
        emp.get(0).get("age");

        JSONObject emp1 = new JSONObject();
        emp1.put("name", emp.get(0).get("name"));
        emp1.put("salary", emp.get(0).get("salary"));
        emp1.put("age", emp.get(0).get("age"));

//        int retryCount = 5;
//        for (int i = 0; i < retryCount; i++) {
//            try {
        response = apiSer.executePostRequest(emp1, "/create");
        Assert.assertEquals(200, response.getStatusCode());

//            } catch (AssertionError e) {
//                response = apiSer.executePostRequest(emp1, "/create");
//            }
//
//            if (response.getStatusCode() == 200){
        getSet.setResponse(response);
//                break;
//            }
//        }
    }

    @Then("I should see below response data for Create employee")
    public void iShouldSeeBelowResponseData(DataTable expResponse) {
        JsonPath jsonPath = getSet.getResponse().jsonPath();
        List<Map<String, String>> emp = expResponse.asMaps(String.class, String.class);
        Assert.assertEquals(jsonPath.get("status"), emp.get(0).get("status"));
        Assert.assertEquals(jsonPath.get("data.name"), emp.get(0).get("name"));
        Assert.assertEquals(jsonPath.get("data.salary"), emp.get(0).get("salary"));
        Assert.assertEquals(jsonPath.get("data.age"), emp.get(0).get("age"));
    }

    @When("I fetch a Employee with id {string}")
    public void iFetchAEmployeeWithId(String id) {
        Response response = apiSer.executeGetRequest(id);
        getSet.setResponse(response);
    }

    @Then("I should see below response data for Get Request")
    public void iShouldSeeBelowResponseDataForGetRequest(DataTable expResponse) {
        JsonPath jsonPath = getSet.getResponse().jsonPath();
        List<Map<String, String>> emp = expResponse.asMaps(String.class, String.class);
        Assert.assertEquals(200, getSet.getResponse().getStatusCode());
        Assert.assertEquals(jsonPath.get("status"), emp.get(0).get("status"));
        Assert.assertEquals((Integer) jsonPath.get("data.id"), Integer.parseInt(emp.get(0).get("id")));
        Assert.assertEquals(jsonPath.get("data.employee_name"), emp.get(0).get("employee_name"));
        Assert.assertEquals((Integer) jsonPath.get("data.employee_salary"), Integer.parseInt(emp.get(0).get("employee_salary")));
        Assert.assertEquals((Integer) jsonPath.get("data.employee_age"), Integer.parseInt(emp.get(0).get("employee_age")));
    }

    @When("I delete a Employee with id {string}")
    public void iDeleteAEmployeeWithId(String id) {
        Response response = apiSer.executeDeleteRequest(id);
        getSet.setResponse(response);
    }

    @Then("I should see below response data for Delete Request")
    public void iShouldSeeBelowResponseDataForDeleteRequest(DataTable expResponse) {
        JsonPath jsonPath = getSet.getResponse().jsonPath();
        List<Map<String, String>> emp = expResponse.asMaps(String.class, String.class);
        Assert.assertEquals(200, getSet.getResponse().getStatusCode());
        Assert.assertEquals(jsonPath.get("status"), emp.get(0).get("status"));
        Assert.assertEquals(jsonPath.get("data"), emp.get(0).get("data"));
        Assert.assertEquals(jsonPath.get("message"), emp.get(0).get("message"));
    }
}
