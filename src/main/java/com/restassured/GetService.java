package com.restassured;

import static com.jayway.restassured.RestAssured.given;

import java.util.Map;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;


public class GetService {

	private ResponseWrapper response = new ResponseWrapper();
	private final static MyLogger logger = LoggerFactory.getLogger(GetService.class);

	public ResponseWrapper get(RequestWrapper request) {

		String endPoint = request.getEndPoint();
		ContentType contentType = request.getContentType();
		Response response;

		//fetch get response
		if (request.getCookies() == null) {
			response = given().contentType(contentType).get(endPoint);
			
		} else {
			response = given().cookies(request.getCookies()).contentType(contentType).get(endPoint);
		}

		String responseAsString = response.body().asString();
		logger.info("\n Printing Response as String for get request\n" + responseAsString);

		this.response.setResponseCode(String.valueOf(response.getStatusCode()));
		this.response.setResponseBody(response.getBody().asString());

		return this.response;
	}
	
	public ResponseWrapper get(RequestWrapper request, Map<String, String> headers) {

		String endPoint = request.getEndPoint();
		ContentType contentType = request.getContentType();
		Response response;

		//fetch get response
		if (request.getCookies() == null) {
			response = given().headers(headers).contentType(contentType).get(endPoint);
		} else {
			response = given().cookies(request.getCookies()).headers(headers).contentType(contentType).get(endPoint);
		}

		String responseAsString = response.body().asString();
		logger.info("\n Printing Response as String for get request\n" + responseAsString);

		this.response.setResponseCode(String.valueOf(response.getStatusCode()));
		this.response.setResponseBody(response.getBody().asString());

		return this.response;
	}

	
}
