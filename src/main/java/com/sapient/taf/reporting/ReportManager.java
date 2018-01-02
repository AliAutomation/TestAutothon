package com.sapient.taf.reporting;

import java.io.File;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;

public class ReportManager {

	/**
	 * The Logger logger
	 */
	final static MyLogger logger = LoggerFactory.getLogger(ReportManager.class);


	
	/**
	 * The ExtentReports extent
	 */
	private static ExtentReports extent;
	
	/**
	 *  Extent HTML Reporter
	 */
	
	private static ExtentHtmlReporter extentHTMLReporter;

	/**
	 * This method gets the extent reporter instance with the
	 * attachment of  extentHTML Reporter 
	 * 
	 * 
	 * @param filePath
	 *            the filepath to store report
	 * @return
	 */
	public synchronized static ExtentReports getReporter(ExtentHtmlReporter extentHTMLReporter) {
		logger.info("Get the Extent reporter");
		if (extent == null) {	
			
			extent=new ExtentReports();
			extent.attachReporter(extentHTMLReporter);
		}
		return extent;
	}
	
	/**
	 * 
	 * This method sets and returns the Extent HTML Reporter instance
	 * @param filepath
	 * 
	 * @return 
	 */

	public synchronized static ExtentHtmlReporter getHTMLReporter(String filePath) {
		logger.info("Get the HTML Reporter");
		if(extentHTMLReporter==null) {
			extentHTMLReporter = new ExtentHtmlReporter(filePath);	
			getReporter(extentHTMLReporter);
		}
		
		return extentHTMLReporter;
		
	}
	
	public synchronized static ExtentReports getReporter() {
		return extent;
	}
	
	public synchronized static ExtentHtmlReporter getHTMLReporter() {
		return extentHTMLReporter;
	}
}