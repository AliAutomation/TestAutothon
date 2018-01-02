package com.sapient.taf.reporting;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;



public class ReportTestManager {


	/**
	 * The Logger logger
	 */
	final static MyLogger logger = LoggerFactory
			.getLogger(ReportTestManager.class);

	/**
	 * Collection to store ExtentTest
	 */
	static Map<Integer, ExtentTest> extentParentTestMap = new HashMap<Integer, ExtentTest>();
	

	/**
	 * Collection to store ExtentTest
	 */
	static Map<Integer, ExtentTest> extentChildTestMap = new HashMap<Integer, ExtentTest>();


	/**
	 * The ExtentReports reference extent
	 */
	private static ExtentReports extent = ReportManager.getReporter();
	
	/**
	 *  Extent Report Parent Test Reference
	 */
	
	private static ExtentTest parentTest;

	/**
	 * This method returns the ExtentTest reference
	 * 
	 * @return ExtentTest
	 */
	public static synchronized ExtentTest getChildTest() {
		return extentChildTestMap.get((int) (long) (Thread.currentThread().getId()));
	}
	
	public static synchronized ExtentTest getParentTest() {
		return extentParentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}


	/**
	 * This method Ends and prepares the test to be added to the report on
	 * flush()
	 */
	public static synchronized void flushTest() {
		/*extent.(extentTestMap.get((int) (long) (Thread.currentThread()
				.getId())));*/
		extent.flush();
	}

	/**
	 * This method creates and prepared the test to be added to report.
	 * 
	 * @param testName
	 *            the name of test
	 * @return
	 */
	public static synchronized ExtentTest createTest(String testName) {
		
		return createParentTest(testName, "");
	}
	
	
	/**
	 * This method creates and prepared the child test to be added to report.
	 * 
	 * @param methodName
	 *            the name of Method
	 * @return
	 */
	public static synchronized ExtentTest createNode(String methodName) {
		
		return createNode(methodName, "");
	}

	
	/**
	 * This method creates the child test with name and desc
	 * 
	 * @param testName
	 *            the name of test
	 * @param desc
	 *            the desc of test
	 * @return
	 */
	public static synchronized ExtentTest createNode(String methodName, String desc) {
		
		ExtentTest childTest = getParentTest().createNode(methodName, desc);
		extentChildTestMap.put((int) (long) (Thread.currentThread().getId()), childTest);
		

		return childTest;
	}
	

	/**
	 * This method creates the test with name and desc
	 * 
	 * @param testName
	 *            the name of test
	 * @param desc
	 *            the desc of test
	 * @return
	 */
	public static synchronized ExtentTest createParentTest(String testName, String desc) {
		
		parentTest = extent.createTest(testName, desc);
		extentParentTestMap.put((int) (long) (Thread.currentThread().getId()), parentTest);
		

		return parentTest;
	}
}