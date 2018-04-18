package com.app.web.tests;



import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.app.web.pages.cucumber.GoogleHomePage;
import com.sapient.taf.framework.coreclasses.BaseClass;

import com.sapient.taf.reporting.IReportComm;
import com.sapient.taf.reporting.ReportUtil;


public class GoogleFinanceTest extends BaseClass{


	@Test
	public void searchMethodA1() {
		//IReportComm reportUtil=new ReportUtil();
		GoogleHomePage ghp = new GoogleHomePage();
		System.out.println(reportUtil);
		reportUtil.logInfo("Test A1 - Step 1");
		reportUtil.logInfo("Test A1 - Step 2");
		reportUtil.logInfo("Test A1 - Step 3");
		reportUtil.logFail("Failed");
		
		ghp.searchText("AAPL stock");
		reportUtil.logInfo("Test A - Step 1");
		reportUtil.checkForErrors();
		
	}


	@Test
	public void searchMethodA2()  {
		GoogleHomePage ghp = new GoogleHomePage();
		reportUtil.logInfo("Test A2 - Step 1");
		reportUtil.logInfo("Test A2 - Step 2");
		reportUtil.logInfo("Test A2 - Step 3");
		ghp.searchText("AAPL stock");
		Assert.assertTrue(true);
		reportUtil.checkForErrors();
	}

}
