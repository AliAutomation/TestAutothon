package com.restassured;

import static com.jayway.restassured.RestAssured.preemptive;

import com.jayway.restassured.RestAssured;

public class Authentication {

	public static void authenticate(String username, String password) {

		RestAssured.authentication = preemptive().basic(username, password);

	}
}
