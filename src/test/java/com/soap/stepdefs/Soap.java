package com.soap.stepdefs;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Scanner;

import com.restassured.GetService;
import com.restassured.RequestBuilder;
import com.restassured.RequestWrapper;
import com.restassured.ResponseWrapper;
import com.restassured.lib.APIUtil;
import com.restassured.stepdefs.Hooks;
import com.soap.SoapResponse;
import com.soap.lib.SoapRequestBuilder;
import com.soap.lib.SoapRequestWrapper;
import com.soap.lib.SoapResponseBuilder;
import com.soap.lib.SoapResponseWrapper;
import com.soap.lib.SoapUtil;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Soap {

	private String testAPIName;
	private String testInputData;
	private SoapRequestWrapper request = new SoapRequestWrapper();
	private SoapRequestBuilder requestBuilder = new SoapRequestBuilder();
	private SoapResponseBuilder responseBuilder = new SoapResponseBuilder();
	private SoapResponse soapResponse = new SoapResponse();
	private SoapResponseWrapper responseWrapper;

	@Given("^I create request for \"([^\"]*)\" With data \"([^\"]*)\"$")
	public void iCreateGETRequestFor(String APIName, String inpuData) throws Throwable {

		// Name of the service being tested in current test
		testAPIName = APIName;

		testInputData = inpuData;

		/*
		 * //Set content type for this request apiExpectedContentType =
		 * requestBuilder.contentType(testAPIName,request);
		 */

		// Set End Points
		requestBuilder.setRequestBody(testAPIName, request, testInputData);

		requestBuilder.endPoints(testAPIName, request);

	}

	@When("^I send request to the API$")
	public void iSendGETRequestTo() throws Throwable {
		// Get response of service under test with set params
		responseWrapper = soapResponse.getResponse(request, testAPIName);

		responseBuilder.setResponseBody(testAPIName, responseWrapper);

	}

	@Then("^I validate Soap Response and Status Code$")
	public void validateResponse() throws Throwable {
		// Get response of service under test with set params
		boolean result = SoapUtil.validateSoapResponse(responseWrapper, testAPIName);
		assertEquals("API Response Validation Passed", true, result);
		SoapHooks.scenarioGlobal.write("API Response Validation Passed");
		Scanner in = new Scanner(responseWrapper.getResponseBody());
		String text = null;
		while (in.hasNextLine()) {
			text = in.nextLine();
			SoapHooks.scenarioGlobal.write(" ----RESPONSE BODY ----" + text);
		}

		SoapHooks.scenarioGlobal.write(" ----RESPONSE CODE----" + responseWrapper.getResponseCode());

	}

	@Then("^I validate soapKey \"([^\"]*)\" of the received response With Expected Data \"([^\"]*)\"$")
	public void iValidateOfTheReceivedResponse(String soapKey, String expectedData) throws Throwable {
		boolean result = SoapUtil.validateSoapResponseKeys(responseWrapper, testAPIName, soapKey, expectedData);
		assertEquals("API Response Validation Passed", true, result);
		SoapHooks.scenarioGlobal.write("API Response ArgValue Validation Passed");

		Scanner in = new Scanner(responseWrapper.getResponseBody());
		String text = null;
		while (in.hasNextLine()) {
			text = in.nextLine();
			SoapHooks.scenarioGlobal.write(" ----RESPONSE BODY ----" + text);
		}

	}

}
