
package com.restassured.lib;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.xml.transform.Source;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;
import org.xmlunit.matchers.EvaluateXPathMatcher;
import org.xmlunit.xpath.JAXPXPathEngine;
import org.xmlunit.xpath.XPathEngine;

import com.restassured.ResponseWrapper;

public class APIUtil {
	
	String apiName;
	private final static Logger Log = Logger.getLogger(APIUtil.class); //for logging

	//Validate API Response - Strictly match complete response
	public static boolean validateAPIResponse(ResponseWrapper response, String responseContentType, String testAPIName){

		String expectedAPIResponseJSON=null;
		String expectedAPIResponseFileXML=null;
		String expectedStatusCode;
		String expectedAPIResponseFilePath_XML="src/test/resources/XML-Responses/";

		//Get expected Status code for this service
		expectedStatusCode = getAPIExpectedStatusCode(testAPIName);

		//Verify Status/Response code
		assertEquals("Validating Status Code", expectedStatusCode, response.getResponseCode());

		//Get expected response value
		if (responseContentType.equals("JSON")) {
			expectedAPIResponseJSON = APIUtil.getAPIExpectedResponseJson(testAPIName);

			//Test JSON responses
			assertTrue(JSONUtil.compareJSONs(expectedAPIResponseJSON, response.getResponseBody()));

		} else if (responseContentType.equals("XML")){

			//Get Path of expected XMl file
			expectedAPIResponseFileXML = expectedAPIResponseFilePath_XML + testAPIName + ".xml";

			//Compare XMl files
			Diff xmlCompareResult = DiffBuilder.compare(Input.fromFile(expectedAPIResponseFileXML)).withTest(response.getResponseBody()).build();
			assertTrue(!xmlCompareResult.hasDifferences());
		}

		return true;
	}

	//Validate API Response - Status Code Only
	public static boolean validateAPIResponse(ResponseWrapper response, String responseContentType, String testAPIName, boolean testStatusCode){

		String expectedAPIResponseJSON=null;
		String expectedAPIResponseFileXML=null;
		String expectedStatusCode;
		String expectedAPIResponseFilePath_XML="src/test/resources/XML-Responses/";

		if (testStatusCode) {
			//Get expected Status code for this service
			expectedStatusCode = getAPIExpectedStatusCode(testAPIName);

			//Verify Status/Response code
			assertEquals("Validating Status Code", expectedStatusCode, response.getResponseCode());
		}
		return true;
	}

	//Validate API Response - Compare specific response keys only
	public static boolean validateAPIResponse(ResponseWrapper response, String responseContentType, String testAPIName, String keyToCompare){

		String expectedAPIResponseJSON=null;
		String expectedAPIResponseFileXML=null;
		String expectedStatusCode;
		String expectedAPIResponseFilePath_XML="src/test/resources/XML-Responses/";

		//Get expected Status code for this service
		expectedStatusCode = getAPIExpectedStatusCode(testAPIName);

		//Verify Status/Response code
		assertEquals("Validating Status Code", expectedStatusCode, response.getResponseCode());

		//Get expected response value
		if (responseContentType.equals("JSON")) {
			expectedAPIResponseJSON = APIUtil.getAPIExpectedResponseJson(testAPIName);

			//Test Specific JSON Value
			assertEquals(JSONUtil.getJsonKeyValue(expectedAPIResponseJSON, keyToCompare), JSONUtil.getJsonKeyValue(response.getResponseBody(),keyToCompare));

		} else if (responseContentType.equals("XML")){

			//Get Path of expected XMl file
			expectedAPIResponseFileXML = expectedAPIResponseFilePath_XML + testAPIName + ".xml";

			//Compare Individual Node values

			//Get Expected value of this node from expected XML response file
			Source sourceXML = Input.fromFile(expectedAPIResponseFileXML).build();
			XPathEngine xpath = new JAXPXPathEngine();
			Iterable<Node> allMatches = xpath.selectNodes(keyToCompare, sourceXML);
			assert allMatches.iterator().hasNext();
			String nodeValue = xpath.evaluate(keyToCompare + "/text()", sourceXML);

			assertThat(response.getResponseBody(), EvaluateXPathMatcher.hasXPath(keyToCompare + "/text()", equalTo(nodeValue)));
		}

		return true;
	}

	//Reads expected status code of API from api.properties file
	public static String getAPIExpectedStatusCode(String sAPIName){
		String statusCode;
		String currEnv;

		currEnv= CommonUtil.getEnv();

		statusCode= CommonUtil.getPropertyValue("service.statusCode." + currEnv + "." + sAPIName);

		return statusCode;
	}

	//Reads expected status code of API from api.properties file
	public static String getAPIExpectedContentType(String sAPIName){
		String contentType;
		String currEnv;

		currEnv= CommonUtil.getEnv();
		contentType= CommonUtil.getPropertyValue("service.contentType." + currEnv + "." + sAPIName);

		return contentType;
	}

	//Returns API's Endpoint from Properties file based on environment
	public static String getAPIEndPoint(String sAPIName){
		String sCurrEnv= CommonUtil.getEnv();
		return CommonUtil.getPropertyValue("service." + sCurrEnv + "." + sAPIName);

	}
	
	public static String getHeaderKey(String sAPIName) {
		String sCurrEnv= CommonUtil.getEnv();
		return CommonUtil.getPropertyValue("service.header.key." + sCurrEnv + "." + sAPIName);
	}
	
	public static String getHeaderValue(String sAPIName) {
		String sCurrEnv= CommonUtil.getEnv();
		return CommonUtil.getPropertyValue("service.header.value." + sCurrEnv + "." + sAPIName);
	}
	
	//Returns API's RequestJson from Properties file based on environment
	public static String getAPIRequestJson(String sAPIName){
		String sCurrEnv= CommonUtil.getEnv();
		return CommonUtil.getPropertyValue("service.request." + sCurrEnv + "." + sAPIName);

	}

	//Returns API's Expected ReponseJson from Properties file based on environment
	public static String getAPIExpectedResponseJson(String sAPIName){
		String sCurrEnv= CommonUtil.getEnv();
		return CommonUtil.getPropertyValue("service.response." + sCurrEnv + "." + sAPIName);
	}


	//Returns API's Expected Charset from Properties file
	public static String getAPIExpectedCharsetValue(){
		String sCurrEnv= CommonUtil.getEnv();
		return CommonUtil.getPropertyValue("charset");
	}

	//Returns API's Expected Charset from Properties file
	public static String getAPIExpectedCharsetValue(String sAPIName){
		String sCurrEnv= CommonUtil.getEnv();
		return CommonUtil.getPropertyValue("service.charset." + sAPIName);
	}

}


