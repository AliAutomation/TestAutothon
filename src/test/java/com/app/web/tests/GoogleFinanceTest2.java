package com.app.web.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.app.web.pages.GoogleHomePage;
import com.sapient.taf.framework.coreclasses.BaseClass;
import com.sapient.taf.framework.coreclasses.BaseWebPage;


public class GoogleFinanceTest2 extends BaseClass {

	@Test
	public void searchMethod21() throws IOException {
		GoogleHomePage ghp = new GoogleHomePage();
		ghp.searchText("AAPL stock");
		reportUtil.logInfo("Test b1 - Step 1");
		reportUtil.logInfo("Test b1 - Step 2");
		reportUtil.assertTrue(20<5, "Failed 20<5");
		reportUtil.logInfo("Test b1 - Step 3");
		//throw new SkipException("Test Exception");
		reportUtil.checkForErrors();
		
		// Reporter.
	}

	@Test
	public void searchMethod22() throws IOException {
		GoogleHomePage ghp = new GoogleHomePage();
		ghp.searchText("AAPL stock");
		reportUtil.assertEquals("sd", "jkj","Equal Condition Failed");
		reportUtil.checkForErrors();

	}

}
