package com.restassured;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;



public class PutService {

	private ResponseWrapper response = new ResponseWrapper();
	private final static MyLogger logger = LoggerFactory.getLogger(PutService.class);

	public ResponseWrapper put(RequestWrapper request) {

		//Get Params
		String result=null;
		String endPoint = request.getEndPoint();
		ContentType contentType = request.getContentType();
		String jsonBody = request.getRequestBody();
		String charset = ";charset="+request.getCharset();
		Response response;

		//fetch post response
		if (request.getCookies() == null) {
			response = given().contentType(contentType+charset).body(jsonBody).when().put(endPoint).thenReturn();
		} else {
			response = given().cookies(request.getCookies()).contentType(ContentType.JSON).body(jsonBody).when().put(endPoint).thenReturn();
		}

		String responseAsString = response.body().asString();
		logger.info("\n Printing Response as String for get request\n" + responseAsString);

		this.response.setResponseCode(String.valueOf(response.getStatusCode()));
		this.response.setResponseBody(response.getBody().asString());

		return this.response;
	}

}
