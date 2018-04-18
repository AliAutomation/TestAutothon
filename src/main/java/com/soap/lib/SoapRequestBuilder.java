package com.soap.lib;



import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.safari.SafariOptions;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jayway.restassured.http.ContentType;
import com.restassured.lib.APIUtil;

public class SoapRequestBuilder {

    public String contentType(String apiName, SoapRequestWrapper request){

        ContentType contentType;

        //Check ContentType required for this API
        String apiExpectedContentType = APIUtil.getAPIExpectedContentType(apiName);

        switch (apiExpectedContentType) {
            case "JSON": {
                contentType = ContentType.JSON;
                break;
            }
            case "XML": {
                contentType = ContentType.XML;
                break;
            }
            default: {
                contentType = ContentType.ANY;
                break;
            }
        }

        //Set ContentType for this request
        request.setContentType(contentType);

        return apiExpectedContentType;
    }

    public void endPoints(String apiName, SoapRequestWrapper request){

        //Get and Set the service endPoint
        String apiEndPoint = SoapUtil.getSoapEndPoint(apiName);
        request.setEndPoint(apiEndPoint);
    }

    public Map<String, String> headers(String apiName, SoapRequestWrapper request) {
    	String headerKey = APIUtil.getHeaderKey(apiName);
    	String headerValue = APIUtil.getHeaderValue(apiName);
    	
    	Map<String, String> headers = new HashMap<String, String>();
    	headers.put(headerKey, headerValue);
    	request.setHeaders(headers);
    	
    	return headers;
    }
    
    public void setRequestBody(String apiName, SoapRequestWrapper request,String inputData) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, TransformerException{

        //Path of JSON request files
        String basePath = request.getReqFilesLocation() + "soapRequests";
        String requestFileLocation = basePath + "/" + apiName + ".xml";
     
       Document doc = null;
       // for(int i=0;i<inputArray.length;i++) {
        doc=	setXMLData(requestFileLocation,inputData);
        //}
        /*String jsonBody = null;

        try {
            jsonBody = generateStringFromResource(requestFile);
            System.out.println("jsonBody for POST request: \n" + jsonBody);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(StringUtils.isNotEmpty(jsonBody));*/
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
 		Transformer transformer = transformerFactory.newTransformer();
 		DOMSource source = new DOMSource(doc);
 		StreamResult result = new StreamResult(new File(requestFileLocation));
 		transformer.transform(source, result);
       
        File requestFile= SoapUtil.getSoapRequestBody(apiName,requestFileLocation);
        
       request.setRequestBody(requestFile);
    }

    
    public Document setXMLData(String sourceFile,String inputData) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
    
    	
    	DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		
		DocumentBuilder builder=factory.newDocumentBuilder();
		
		Document document=builder.parse(sourceFile);
		
		XPath xpath=XPathFactory.newInstance().newXPath();
    	String[] inputArray=inputData.split(";");
    	for(int i=0;i<inputArray.length;i++) {
    	String key=inputArray[i].split("=")[0];
    	String value=inputArray[i].split("=")[1];
    	
    	
		
		NodeList nodeList=	(NodeList) xpath.compile(key+ "//text()").evaluate(document, XPathConstants.NODESET);
	
		//ArrayList<String> expectedList=new ArrayList<String>();
		
		for(int k=0;k<nodeList.getLength();k++) {
			
			
			Node node=nodeList.item(k);
		
			node.setTextContent(value);
		//	expectedList.add(node.getTextContent());
			System.out.println(node.getParentNode().getNodeName() + "Node name " + node.getTextContent() + "text content");
			
		}
    	}
    	return document;
		
    }
    
    public void writeXMLFile(String fileLocation,String source) {
    	
    }
    //Use to set default charset value
    public void setRequestCharset(SoapRequestWrapper request){
        request.setCharset(APIUtil.getAPIExpectedCharsetValue());
    }

    //Use to set API specific charset value
    public void setRequestCharset(SoapRequestWrapper request, String apiName){
        request.setCharset(APIUtil.getAPIExpectedCharsetValue(apiName));
    }

    public String generateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
