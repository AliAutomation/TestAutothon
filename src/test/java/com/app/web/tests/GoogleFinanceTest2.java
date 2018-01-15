package com.app.web.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.app.web.pages.GoogleHomePage;
import com.sapient.taf.reporting.ReportUtil;

public class GoogleFinanceTest2 {

	@Test
	public void searchMethod21() throws IOException {
		GoogleHomePage ghp = new GoogleHomePage();
		ghp.searchText("AAPL stock");
		ReportUtil.logInfo("Test b1 - Step 1");
		ReportUtil.logInfo("Test b1 - Step 2");
		ReportUtil.assertTrue(20<5, "Failed 20<5");
		ReportUtil.logInfo("Test b1 - Step 3");
		//throw new SkipException("Test Exception");
		ReportUtil.checkForErrors();
		
		// Reporter.
	}

	@Test
	public void searchMethod22() throws IOException {
		GoogleHomePage ghp = new GoogleHomePage();
		ghp.searchText("AAPL stock");
		ReportUtil.assertEquals("sd", "jkj","Equal Condition Failed");
		ReportUtil.checkForErrors();

	}

}
