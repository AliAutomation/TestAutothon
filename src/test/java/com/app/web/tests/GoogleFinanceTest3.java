package com.app.web.tests;



import org.testng.SkipException;
import org.testng.annotations.Test;

import com.app.web.pages.cucumber.GoogleHomePage;
import com.sapient.taf.framework.coreclasses.BaseClass;


public class GoogleFinanceTest3 extends BaseClass{

	@Test
	public void searchMethod31()  {
		GoogleHomePage ghp = new GoogleHomePage();
		ghp.searchText("AAPL stock");
		reportUtil.logInfo("Test c1 - Step 1");
		reportUtil.logInfo("Test c1 - Step 2");
		reportUtil.logInfo("Test c1 - Step 3");
		
		
	}

	@Test
	public void searchMethod32() {
		GoogleHomePage ghp = new GoogleHomePage();
		ghp.searchText("AAPL stock");
	}

}
