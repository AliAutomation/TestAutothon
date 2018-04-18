package com.restassured;

import java.util.HashMap;
import java.util.Map;


import com.jayway.restassured.http.ContentType;

public class RequestWrapper {

    private String endPoint;

    private String requestBody;

    private Map<String, String> cookies = null;

    private String requestMethod;

    private ContentType contentType;

    private String charset;

    private String expected_Request_FilesLocation="src/test/resources/";
    
    private Map<String, String> headers = null;

    public String setReqFilesLocation(String Req_Files_Loc){
        return this.expected_Request_FilesLocation = Req_Files_Loc;
    }

    public String getReqFilesLocation(){
        return this.expected_Request_FilesLocation;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCharset() {
        return charset;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        if (this.cookies == null) {
            this.cookies = new HashMap<String, String>();
        }
        this.cookies.putAll(cookies);
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		if (this.headers == null) {
            this.headers = new HashMap<String, String>();
        }
        this.headers.putAll(headers);
	}

}
