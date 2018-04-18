package com.restassured;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jayway.restassured.http.ContentType;
import com.restassured.lib.APIUtil;
import com.soap.lib.SoapRequestWrapper;
import com.soap.lib.SoapUtil;

public class RequestBuilder {

    public String contentType(String apiName, RequestWrapper request){

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

    public void endPoints(String apiName, RequestWrapper request){

        //Get and Set the service endPoint
        String apiEndPoint = APIUtil.getAPIEndPoint(apiName);
        request.setEndPoint(apiEndPoint);
    }

    public Map<String, String> headers(String apiName, RequestWrapper request) {
    	String headerKey = APIUtil.getHeaderKey(apiName);
    	String headerValue = APIUtil.getHeaderValue(apiName);
    	
    	Map<String, String> headers = new HashMap<String, String>();
    	headers.put(headerKey, headerValue);
    	request.setHeaders(headers);
    	
    	return headers;
    }
    
    public void setRequestBody(String apiName, RequestWrapper request){

        //Path of JSON request files
        String basePath = request.getReqFilesLocation() + "JSON-Requests";
        String requestFile = basePath + "/" + apiName + ".json";
        String jsonBody = null;

        try {
            jsonBody = generateStringFromResource(requestFile);
            System.out.println("jsonBody for POST request: \n" + jsonBody);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(StringUtils.isNotEmpty(jsonBody));
        request.setRequestBody(jsonBody);
    }

    //Use to set default charset value
    public void setRequestCharset(RequestWrapper request){
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
