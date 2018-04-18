package com.app.web.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.app.web.pages.cucumber.GoogleHomePage;
import com.sapient.taf.framework.coreclasses.BaseClass;

public class GoogleFinanceTest2 extends BaseClass {

	@Test
	public void searchMethod21() {
		GoogleHomePage ghp = new GoogleHomePage();
		ghp.searchText("AAPL stock");
		reportUtil.logInfo("Test b1 - Step 1");
		reportUtil.logInfo("Test b1 - Step 2");
		Assert.assertTrue(20<5, "Failed 20<5");
		reportUtil.logInfo("Test b1 - Step 3");
		//throw new SkipException("Test Exception");
		reportUtil.checkForErrors();
		
		// Reporter.
	}

	@Test
	public void searchMethod22()  {
		GoogleHomePage ghp = new GoogleHomePage();
		ghp.searchText("AAPL stock");
		Assert.assertEquals("sd", "jkj","Equal Condition Failed");
		reportUtil.checkForErrors();

	}

}
