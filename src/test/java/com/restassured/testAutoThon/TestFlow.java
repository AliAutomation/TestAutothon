package com.restassured.testAutoThon;

import org.testng.annotations.Test;
import com.restassured.stepdefs.getAPI;

public class TestFlow {
	@Test
	public void testFlow1() {
		getAPI objGetAPI = new getAPI();
		try {
			objGetAPI.iCreateGETRequestFor("TestAPI0");
			objGetAPI.iSendGETRequestTo();
			objGetAPI.iValidateStatusCodeOfTheReceivedResponse();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
}
