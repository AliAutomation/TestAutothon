package com.app.web.tests;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.app.web.pages.GoogleHomePage;
import com.sapient.taf.reporting.ReportUtil;

public class GoogleFinanceTest3 {

	@Test
	public void searchMethod31() throws IOException {
		GoogleHomePage ghp = new GoogleHomePage();
		ghp.searchText("AAPL stock");
		ReportUtil.logInfo("Test c1 - Step 1");
		ReportUtil.logInfo("Test c1 - Step 2");
		ReportUtil.logInfo("Test c1 - Step 3");
		
		
	}

	@Test
	public void searchMethod32() throws IOException {
		GoogleHomePage ghp = new GoogleHomePage();
		ghp.searchText("AAPL stock");
	}

}
