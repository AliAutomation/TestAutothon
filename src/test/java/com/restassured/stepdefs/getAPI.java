package com.restassured.stepdefs;

import com.restassured.GetService;
import com.restassured.RequestBuilder;
import com.restassured.RequestWrapper;
import com.restassured.ResponseWrapper;
import com.restassured.lib.APIUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class getAPI {

    private RequestWrapper request = new RequestWrapper();
    private RequestBuilder requestBuilder = new RequestBuilder();
    private GetService getService = new GetService();
    private ResponseWrapper response;
    private String apiExpectedContentType;
    private String testAPIName;

    @Given("^I create GET request for \"([^\"]*)\"$")
    public void iCreateGETRequestFor(String APIName) throws Throwable {

        //Name of the service being tested in current test
        testAPIName = APIName;

        //Set content type for this request
        apiExpectedContentType = requestBuilder.contentType(testAPIName,request);

        //Set End Points
        requestBuilder.endPoints(testAPIName, request);

    }

    @When("^I send GET request to the API$")
    public void iSendGETRequestTo() throws Throwable {
        //Get response of service under test with set params
        response = getService.get(request);
        
        Hooks.scenarioGlobal.write(" ----RESPONSE BODY ----" + response.getResponseBody());
        
        Hooks.scenarioGlobal.write(" ----RESPONSE CODE----" + response.getResponseCode());
    }
    @Then("^I validate StatusCode of the received response$")
    public void iValidateStatusCodeOfTheReceivedResponse() throws Throwable {

        boolean result = APIUtil.validateAPIResponse(response, apiExpectedContentType, testAPIName, true);
        assertEquals("API Response Validation Passed", true, result);
        Hooks.scenarioGlobal.write("API Response Validation Passed");
    }

    @Then("^I validate jsonbody of the received response$")
    public void iValidateJsonbodyOfTheReceivedResponse() throws Throwable {

        boolean result = APIUtil.validateAPIResponse(response, apiExpectedContentType, testAPIName);
        assertEquals("API Response Validation Passed", true, result);
    }

    @Then("^I validate jsonKey \"([^\"]*)\" of the received response$")
    public void iValidateOfTheReceivedResponse(String jsonKey) throws Throwable {
        boolean result = APIUtil.validateAPIResponse(response, apiExpectedContentType, testAPIName, jsonKey);
        assertEquals("API Response Validation Passed", true, result);
        Hooks.scenarioGlobal.write("API Response ArgValue Validation Passed");
    }

    @Then("^I validate xmlNode \"([^\"]*)\" of the received response$")
    public void iValidateXmlNodeOfTheReceivedResponse(String xmlNode) throws Throwable {
        boolean result = APIUtil.validateAPIResponse(response, apiExpectedContentType, testAPIName, xmlNode);
        assertEquals("API Response Validation Passed", true, result);
    }
}
