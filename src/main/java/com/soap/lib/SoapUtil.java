package com.soap.lib;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;
import org.xmlunit.matchers.EvaluateXPathMatcher;
import org.xmlunit.xpath.JAXPXPathEngine;
import org.xmlunit.xpath.XPathEngine;


import com.restassured.ResponseWrapper;
import com.soap.stepdefs.SoapHooks;

public class SoapUtil {



	
	String apiName;
	private final static Logger Log = Logger.getLogger(SoapUtil.class); //for logging

	//Validate API Response - Strictly match complete response
	public static boolean validateSoapResponse(SoapResponseWrapper response, String testAPIName){

		
		String expectedSoapResponseFileXML=null;
		String actualSoapResponseFileXML=null;
		String expectedStatusCode;
		String expectedSoapResponseFilePath_XML="src/test/resources/soapExpectedResponses/";
		String actualSoapResponseFilePath_XML="src/test/resources/soapActualResponses/";

		//Get expected Status code for this service
		expectedStatusCode = getAPIExpectedStatusCode(testAPIName);

		//Verify Status/Response code
		assertEquals("Validating Status Code", expectedStatusCode, response.getResponseCode());

		
			//Get Path of expected XMl file
			expectedSoapResponseFileXML = expectedSoapResponseFilePath_XML + testAPIName + ".xml";
			
			actualSoapResponseFileXML = actualSoapResponseFilePath_XML + testAPIName + ".xml";


			//Compare XMl files
			Diff xmlCompareResult = DiffBuilder.compare(Input.fromFile(expectedSoapResponseFileXML)).withTest(Input.fromFile(actualSoapResponseFileXML)).build();
			assertTrue(!xmlCompareResult.hasDifferences());
		

		return true;
	}

/*	//Validate API Response - Status Code Only
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
	}*/

	//Validate API Response - Compare specific response keys only
	public static boolean validateSoapResponseKeys(SoapResponseWrapper response, String testAPIName, String keyToCompare,String expectedData) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException, TransformerException{

		String expectedSoapResponseFileXML=null;
		String actualSoapResponseFileXML=null;
		
		String expectedSoapResponseFilePath_XML="src/test/resources/soapExpectedResponses/";
		String actualSoapResponseFilePath_XML="src/test/resources/soapActualResponses/";


		
		//Get Path of expected XMl file
		expectedSoapResponseFileXML = expectedSoapResponseFilePath_XML + testAPIName + ".xml";
		
		actualSoapResponseFileXML = actualSoapResponseFilePath_XML + testAPIName + ".xml";

			
		
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		
		DocumentBuilder builder=factory.newDocumentBuilder();
		
		Document documentExpected=builder.parse(expectedSoapResponseFileXML);
		
		XPath xpath=XPathFactory.newInstance().newXPath();
		
		NodeList nodeListExpected=	(NodeList) xpath.compile(keyToCompare+ "//text()").evaluate(documentExpected, 	XPathConstants.NODESET);
	
		ArrayList<String> expectedList=new ArrayList<String>();
		
		for(int i=0;i<nodeListExpected.getLength();i++) {
			
			
			Node node=nodeListExpected.item(i);
			node.setTextContent(expectedData);
			expectedList.add(node.getTextContent());
			System.out.println(node.getParentNode().getNodeName() + "Node name " + node.getTextContent() + "text content");
			
		}
		
		 TransformerFactory transformerFactory = TransformerFactory.newInstance();
	 		Transformer transformer = transformerFactory.newTransformer();
	 		DOMSource source = new DOMSource(documentExpected);
	 		StreamResult result = new StreamResult(new File(expectedSoapResponseFileXML));
	 		transformer.transform(source, result);
		
		Document documentActual=builder.parse(actualSoapResponseFileXML);
		
		
		
		NodeList nodeListActual=	(NodeList) xpath.compile(keyToCompare+ "//text()").evaluate(documentActual, 	XPathConstants.NODESET);
	
		ArrayList<String> actualList=new ArrayList<String>();
		
		for(int i=0;i<nodeListActual.getLength();i++) {
			
			
			Node node=nodeListActual.item(i);
			
			actualList.add(node.getTextContent());
			System.out.println(node.getParentNode().getNodeName() + "Node name " + node.getTextContent() + "text content");
			
		}
		
		if (expectedList == null && actualList == null) return true;
		if ((expectedList == null && actualList!= null) || (expectedList != null && actualList== null) || (expectedList.size() != actualList.size()) ) {
			return false;
		}
		
		 Collections.sort(expectedList);
		    Collections.sort(actualList); 
		    System.out.println("Expected List" + expectedList);
		    System.out.println("Actual list" + actualList);
		if(expectedList.equals(actualList)) {
			
			SoapHooks.scenarioGlobal.write("Validation passed");
		}
		else {
			Assert.fail("Validation Failed");
			
		}
		return true;
	}

	//Reads expected status code of API from api.properties file
	public static String getAPIExpectedStatusCode(String sAPIName){
		String statusCode;
		String currEnv;

		currEnv= SoapCommonUtil.getEnv();

		statusCode= SoapCommonUtil.getPropertyValue("service.statusCode." + currEnv + "." + sAPIName);

		return statusCode;
	}

	//Reads expected status code of API from api.properties file
	public static String getAPIExpectedContentType(String sAPIName){
		String contentType;
		String currEnv;

		currEnv= SoapCommonUtil.getEnv();
		contentType= SoapCommonUtil.getPropertyValue("service.contentType." + currEnv + "." + sAPIName);

		return contentType;
	}

	//Returns API's Endpoint from Properties file based on environment
	public static String getSoapEndPoint(String sAPIName){
		String sCurrEnv= SoapCommonUtil.getEnv();
		return SoapCommonUtil.getPropertyValue("service." + sCurrEnv + "." + sAPIName);

	}
	
	
	
	//Returns API's RequestJson from Properties file based on environment
	public static File getSoapResponseBody(String sAPIName,String responseFileLocation){
		File responseFile=new File(responseFileLocation);
		return responseFile;
	}

	
	//Returns API's RequestJson from Properties file based on environment
	public static File getSoapRequestBody(String sAPIName,String requestFileLocation){
		File requestFile=new File(requestFileLocation);
		return requestFile;
	}




}
