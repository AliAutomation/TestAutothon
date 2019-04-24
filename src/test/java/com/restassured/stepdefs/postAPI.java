package com.restassured.stepdefs;

import com.restassured.GetService;
import com.restassured.PostService;
import com.restassured.RequestBuilder;
import com.restassured.RequestWrapper;
import com.restassured.ResponseWrapper;
import com.restassured.lib.APIUtil;
import com.restassured.lib.JSONUtil;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class postAPI {

	private RequestWrapper request = new RequestWrapper();
	private RequestBuilder requestBuilder = new RequestBuilder();
	private PostService postService = new PostService();
	private ResponseWrapper response;
	private String apiExpectedContentType;
	private String testAPIName;

	@Given("^I create POST request for \"([^\"]*)\"$")
	public void iCreatePOSTRequestFor(String APIName) throws Throwable {

		// Name of the service being tested in current test
		testAPIName = APIName;

		// Set content type for this request
		apiExpectedContentType = requestBuilder.contentType(testAPIName, request);

		// Set End Points
		requestBuilder.endPoints(testAPIName, request);

		// Set POST Body
		requestBuilder.setRequestBody(testAPIName, request);

		// Set CharSet
		requestBuilder.setRequestCharset(request);

	}

	@When("^I send POST request to the API$")
	public void iSendPOSTRequestTo() throws Throwable {
		// Get response of service under test with set params
		response = postService.post(request);

		System.out.println(" ----RESPONSE BODY ----" + response.getResponseBody());

		System.out.println(" ----RESPONSE CODE----" + response.getResponseCode());
	}

	@Then("^I validate StatusCode of the received response$")
	public void iValidateStatusCodeOfTheReceivedResponse() throws Throwable {

		boolean result = APIUtil.validateAPIResponse(response, apiExpectedContentType, testAPIName, true);
		assertEquals("API Response Validation Passed", true, result);
		System.out.println("API Response Validation Passed");
	}

	public void iValidatePresenseOfJsonTagTheReceivedResponse(String tagName) {
		System.out.println( "Presence of " + tagName + " = " + JSONUtil.verifyPresenseOfElement(response.getResponseBody().toString(), tagName));
	}
	
	public void iFetchJsonValue(String tagName) {
		System.out.println("Value for the Tag " + tagName + " is - " + JSONUtil.getJsonKeyValue(response.getResponseBody().toString(), tagName));
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
		System.out.println("API Response ArgValue Validation Passed");
	}

	@Then("^I validate xmlNode \"([^\"]*)\" of the received response$")
	public void iValidateXmlNodeOfTheReceivedResponse(String xmlNode) throws Throwable {
		boolean result = APIUtil.validateAPIResponse(response, apiExpectedContentType, testAPIName, xmlNode);
		assertEquals("API Response Validation Passed", true, result);
	}
}
