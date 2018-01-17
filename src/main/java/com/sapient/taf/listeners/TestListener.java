package com.sapient.taf.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.HashMap;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;
import org.testng.internal.IResultListener;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sapient.taf.drivermanager.DriverFactory;
import com.sapient.taf.drivermanager.DriverManager;
import com.sapient.taf.framework.coreclasses.BaseClass;
import com.sapient.taf.framework.coreclasses.BaseWebPage;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;
import com.sapient.taf.reporting.IReportComm;
import com.sapient.taf.reporting.ReportManager;
import com.sapient.taf.reporting.ReportTestManager;
import com.sapient.taf.reporting.ReportUtil;


import com.sapient.taf.utils.TimestampUtils;

public class TestListener extends BaseClass implements IInvokedMethodListener, ISuiteListener, ITestListener, IResultListener {


	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		
		System.out.println("Inside");
		if (method.isTestMethod()) {
			try {
				DriverManager.setWebDriver(DriverFactory
						.createInstance(method.getTestMethod().getXmlTest().getLocalParameters().get("browser")));
			
			
			} catch (MalformedURLException | InvocationTargetException | InstantiationException | IllegalAccessException
					| NoSuchMethodException | SecurityException | IllegalArgumentException | FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

		
	
		
		if (testResult.getStatus() == ITestResult.SUCCESS) {
			logger.info(testResult.getMethod().getMethodName() + " Passed");
			

			reportUtil.logPass(testResult.getMethod().getMethodName() + " Passes");
		}

		else if (testResult.getStatus() == ITestResult.FAILURE) {
			if (testResult.getThrowable() != null) {
				logger.error(testResult.getMethod() + testResult.getThrowable().getMessage());
				
					reportUtil.logFail(
							"Test Case Fails with following message:: " + testResult.getThrowable().getMessage());
			

			}
		}

		else if (testResult.getStatus() == ITestResult.SKIP) {

			logger.info(testResult.getMethod().getMethodName() + "SKIPPED");
			reportUtil.logSkipped("Test Case Skipped");

		}

	   		DriverManager.getDriver().quitDriver();
			 
	}

	final static MyLogger logger = LoggerFactory.getLogger(TestListener.class);
	public static String reportLocation = null;

	/**
	 * This method is called at test start.
	 */
	public void onTestStart(ITestResult result) {

		ReportUtil.errorMessageBuffer = new HashMap<Long, StringBuffer>();
		ExtentTest child = ReportTestManager.createNode(result.getMethod().getMethodName());

		/*
		 * ReportTestManager.getTest().assignCategory(
		 * result.getMethod().getRealClass().getSimpleName(), getBrowserName());
		 */

	}

	/**
	 * This method is called on test success
	 */
	public void onTestSuccess(ITestResult result) {

	}

	/**
	 * This method is called on test failure
	 */
	public void onTestFailure(ITestResult result) {

		// TODO

	}

	/**
	 * This method is called when a test is skipped
	 */
	public void onTestSkipped(ITestResult result) {

	}

	/**
	 * This method is called when a test case fails but with certain success
	 * percentage
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	/**
	 * This method is called on start of test start
	 */
	public void onStart(ITestContext context) {
		try {

			ExtentTest parent = ReportTestManager.createTest(context.getName());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method is called when a test finishes
	 */
	public void onFinish(ITestContext context) {
		
	//	System.out.println("Test Case ends");
	}

	/**
	 * This method is called on configuration success
	 */
	public void onConfigurationSuccess(ITestResult itr) {

	}

	/**
	 * This method is called on configuration failure
	 */
	public void onConfigurationFailure(ITestResult itr) {

	}

	/**
	 * This method is called in configuration skip
	 */
	public void onConfigurationSkip(ITestResult itr) {

	}

	/*
	 * public String getBrowserName() { String browserName = null;
	 * 
	 * Capabilities cap = ((RemoteWebDriver) DriverFactory.getDriver());
	 * .getCapabilities(); browserName = cap.getBrowserName();
	 * 
	 * return browserName; }
	 */

	@Override
	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub

		ReportManager.getReporter().flush();
	}

	@Override
	//@Parameters({"report"})
	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
/*		String reportFormat=arg0.getXmlSuite().getParameter("report");
		
		System.out.println("Report Class" + reportFormat);
		
		switch(reportFormat) {
		
		
		case"Extent" :
			System.out.println("Inside");
		//	reportUtil=new ReportUtil();
			System.out.println(reportUtil);
		break;
		
		
		}
		
		String ReporterName;*/

		reportLocation = System.getProperty("user.dir") + "\\Reports\\"  +  TimestampUtils.getTimeStamp() ;
		
		File f =new File(reportLocation);
		if(f.exists()) {
			System.out.println(" File directory exists");
			
		}
		
		else {
			f.mkdir();
			
			System.out.println("File directory created");
		}

		
		ExtentHtmlReporter htmlReporter = ReportManager.getHTMLReporter(reportLocation  + "\\" + arg0.getName() + ".html");

	
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName(arg0.getName());
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);


	
		
	}
}