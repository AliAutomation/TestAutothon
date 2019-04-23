package com.restassured.testAutoThon;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;
import com.restassured.stepdefs.getAPI;
import com.restassured.stepdefs.postAPI;

public class TestFlow {
	@Test(description="Use GET API")
	public void testFlow1() {
		getAPI objGetAPI = new getAPI();
		try {
			objGetAPI.iCreateGETRequestFor("TestAPI0");
			objGetAPI.iSendGETRequestTo();
			objGetAPI.iValidateStatusCodeOfTheReceivedResponse();
		} catch (Throwable e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test(description="Use POST API")
	public void testFlow2() {
		postAPI objPostAPI = new postAPI();
		try {
			objPostAPI.iCreatePOSTRequestFor("TestAPI_Post3");
			objPostAPI.iSendPOSTRequestTo();
			objPostAPI.iValidateStatusCodeOfTheReceivedResponse();
		} catch (Throwable e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
