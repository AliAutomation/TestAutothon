package com.soap.lib;

import java.io.File;

public class SoapResponseBuilder {

	public void setResponseBody(String apiName, SoapResponseWrapper response){

        //Path of JSON request files
        String basePath = response.getReqFilesLocation() + "soapActualResponses";
        String responseFileLocation = basePath + "/" + apiName + ".xml";
        /*String jsonBody = null;

        try {
            jsonBody = generateStringFromResource(requestFile);
            System.out.println("jsonBody for POST request: \n" + jsonBody);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(StringUtils.isNotEmpty(jsonBody));*/
       File responseFile= SoapUtil.getSoapResponseBody(apiName,responseFileLocation);
        
       response.setResponseBody(responseFile);
    }
}
