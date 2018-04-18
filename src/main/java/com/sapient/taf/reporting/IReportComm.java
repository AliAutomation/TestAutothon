package com.sapient.taf.reporting;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;

public interface IReportComm {

	

	void assertTrue(boolean condition, String message);
	
	void assertFalse(boolean condition, String message);
	
	void assertEquals(String actual, String expected, String message);
	
	void logPass(String detail);
	
	void logFail  (String detail);
	
	void logSkipped(String detail);
	
	void logInfo(String detail);
	
	void addErrorMessageToBuffer(Error error) ;
	
	void verifyEqual(Object actual, Object expected,By locator,String message);
	
	void verifyContains(String actual, String expected,By locator,String message);
	
	void checkForErrors();
	
	
	
	
	
	
	
}
