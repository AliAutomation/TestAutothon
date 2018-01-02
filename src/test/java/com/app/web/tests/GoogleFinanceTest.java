package com.app.web.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.app.web.pages.GoogleHomePage;
import com.sapient.taf.assertions.SoftAssertCustom;
import com.sapient.taf.reporting.ReportUtil;

public class GoogleFinanceTest {

	@Test
	public void searchMethodA1() throws IOException {
		GoogleHomePage ghp = new GoogleHomePage();
		ReportUtil.logInfo("Test A1 - Step 1");
		ReportUtil.logInfo("Test A1 - Step 2");
		ReportUtil.logInfo("Test A1 - Step 3");
	
		
		ghp.searchText("AAPL stock");
		ReportUtil.logInfo("Test A - Step 1");
		SoftAssertCustom.checkForErrors();
		Assert.assertTrue(false);
		//Reporter.
	}
	
	@Test
	public void searchMethodA2() throws IOException {
		GoogleHomePage ghp = new GoogleHomePage();
		ReportUtil.logInfo("Test A2 - Step 1");
		ReportUtil.logInfo("Test A2 - Step 2");
		ReportUtil.logInfo("Test A2 - Step 3");
		ghp.searchText("AAPL stock");
		Assert.assertTrue(true);
	}
	
	
}