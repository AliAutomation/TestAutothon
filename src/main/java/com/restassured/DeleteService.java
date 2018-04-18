package com.restassured;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;


public class DeleteService {

	private ResponseWrapper response = new ResponseWrapper();

	public ResponseWrapper delete(RequestWrapper request) {

		//Get Params
		String result=null;
		String endPoint = request.getEndPoint();
		ContentType contentType = request.getContentType();
		Response response;

		//fetch get response
		if (request.getCookies() == null) {
			response = given().contentType(contentType).when().delete(endPoint).thenReturn();
		} else {
			response = given().cookies(request.getCookies()).contentType(contentType).when().delete(endPoint).thenReturn();
		}

		this.response.setResponseCode(String.valueOf(response.getStatusCode()));
		this.response.setResponseBody(response.getBody().asString());

		return this.response;
	}


}
