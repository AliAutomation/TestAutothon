package com.soap.lib;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SoapResponseWrapper {

	 private String responseCode;
	 


	    private Map<String, String> cookies;

	    private File responseBody;
	    private String expected_Response_FilesLocation=System.getProperty("user.dir") +"/src/test/resources/";
	    
	  

	    public String setReqFilesLocation(String Res_Files_Loc){
	        return this.expected_Response_FilesLocation = Res_Files_Loc;
	    }

	    public String getReqFilesLocation(){
	        return this.expected_Response_FilesLocation;
	    }

	    public String getResponseCode() {

	        return responseCode;
	    }

	    public File getResponseBody() {
	        return responseBody;
	    }
	    public void setResponseCode(String responseCode) {

	        this.responseCode = responseCode;
	    }

	    public Map<String, String> getCookies() {
	        if (this.cookies == null) {
	            this.cookies = new HashMap<String, String>();
	        }
	        return this.cookies;
	    }

	    public void setCookies(Map<String, String> cookies) {
	        if (this.cookies == null) {
	            this.cookies = new HashMap<String, String>();
	        }
	        this.cookies.putAll(cookies);

	    }

	  

	    public void setResponseBody(File responseBody) {

	        this.responseBody = responseBody;
	    }

}
