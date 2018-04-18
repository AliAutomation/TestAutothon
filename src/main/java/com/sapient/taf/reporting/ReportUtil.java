package com.sapient.taf.reporting;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.types.selectors.ExtendSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import com.sapient.taf.drivermanager.DriverFactory;
import com.sapient.taf.drivermanager.DriverManager;
import com.sapient.taf.listeners.TestListener;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;

public class ReportUtil implements IReportComm {

	/**
	 * The Logger logger
	 */
	private final MyLogger logger = LoggerFactory.getLogger(ReportUtil.class);

	public static HashMap<Long, StringBuffer> errorMessageBuffer;

	/**
	 * Add the error message to the error buffer. This error buffer is used to
	 * determine whether the test case is passed when the {@link checkForErrors}
	 * method is called.
	 * 
	 * @param error
	 */
	public void addErrorMessageToBuffer(Error error) {
		try {
			StringBuffer errorBuffer = null;
			if (errorMessageBuffer.get(Thread.currentThread().getId()) == null) {
				errorBuffer = new StringBuffer().append(error + "\n");
			} else {
				errorBuffer = errorMessageBuffer.get(Thread.currentThread().getId()).append(error + "\n");
			}
			errorMessageBuffer.put(Thread.currentThread().getId(), errorBuffer);
		} catch (Exception e) {
			logger.fatal("Adding Error to Error Buffer threw error", e);
		}
	}

	/**
	 * Check if condition is True. If false, marks the step as failed, takes a
	 * warning screenshot and prints message to report
	 * 
	 * @param condition
	 *            Condition to be checked
	 * @param message
	 *            Message to be printed to the report
	 */

	public void assertTrue(boolean condition, String message) {
		try {
			Assert.assertTrue(condition, message);
		} catch (Error e) {
			logger.info("SoftAssert error ", e);
			addErrorMessageToBuffer(e);

		}
	}

	/**
	 * Check if condition is False. If truw, marks the step as failed, takes a
	 * warning screenshot and prints message to report
	 * 
	 * @param condition
	 *            Condition to be checked
	 * @param message;
	 *            Message to be printed to the report
	 */

	public void assertFalse(boolean condition, String message) {
		try {
			Assert.assertFalse(condition, message);
		} catch (Error e) {
			logger.info("SoftAssert error ", e);
			addErrorMessageToBuffer(e);

		}
	}

	/**
	 * Check if actual and expected String values are equal. If not equal, marks the
	 * step as failed, highlights the element and prints message to report
	 * 
	 * @param actual
	 *            Actual String
	 * @param expected
	 *            Expected String
	 * @param message
	 *            Message to be printed to the report
	 */

	public void assertEquals(String actual, String expected, String message) {
		try {
			Assert.assertEquals(actual, expected, message);
		} catch (Error e) {
			logger.info("SoftAssert error ", e);
			addErrorMessageToBuffer(e);

		}
	}

	/**
	 * This method is called whenever a test case passes
	 * 
	 * @param detail
	 *            the detial for passed test case
	 */
	public void logPass(String detail) {
		logger.info("Test Case passes" + ReportTestManager.getChildTest().toString());
		Assert.assertTrue(true, detail);
		ReportTestManager.getChildTest().pass(MarkupHelper.createLabel(detail, ExtentColor.GREEN));
		// ReportTestManager.getTest().addScreenCapture(captureScreenShot());
	}

	/**
	 * This method is called whenever a test case fails
	 * 
	 * @param detail
	 *            the detail for failed test case
	 * @throws IOException
	 */
	public void logFail(String detail) {
		try {
			failure(detail);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyEqual(Object actual, Object expected,By locator,String message) {
        if (!actual.equals(expected)) {
            highlightFailure(locator);
            logFail(message + "  verification failed");
            logInfo(actual + " not equals to " + expected);
        } else {
            logPass(message + " verification passed successfully");
            logInfo(actual + " equals to " + expected);
        }

    }
	
	public static void highlightFailure(By locator){
        WebDriver driver=DriverManager.getDriver().getWebDriver();
        JavascriptExecutor drivercapt = (JavascriptExecutor) driver;
        drivercapt.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;')", driver.findElement(locator));
	}

	public void verifyContains(String actual, String expected,By locator,String message) {
		if (!actual.contains(expected)) {
			  highlightFailure(locator);
	          logFail(message + "  verification failed");
			logInfo(actual + " not contains " + expected); 
		
		} else {
			logPass(message + " verification passed successfully");
			logInfo(actual + " contains " + expected);
		}

	}


	/**
	 * This method is called whenever a test case is skipped
	 * 
	 * @param detail
	 *            the details for skipped test case
	 */
	public void logSkipped(String detail) {
		logger.info("Test Case skipped");
		ReportTestManager.getChildTest().skip(MarkupHelper.createLabel(detail, ExtentColor.YELLOW));
		// ReportTestManager.getTest().
	}

	/**
	 * This method is called to check if the element is displayed or not
	 * 
	 * @param by
	 *            reference element
	 */
	/*
	 * public void isElementDisplayed(By by) {
	 * logger.info("Inside isElementDisplayed"); WebDriver driver =
	 * DriverFactory.getDriver();
	 * 
	 * if (driver.findElement(by).isDisplayed()) {
	 * logPass("Element Displayed Successfully"); } else {
	 * logFail("Element not displayed"); } }
	 */

	/**
	 * This method is called to check if any element is selected or not
	 * 
	 * @param by
	 *            reference element
	 */
	/*
	 * public void isElementSelected(By by) {
	 * logger.info("Inside isElementSelected"); WebDriver driver =
	 * DriverFactory.getDriver();
	 * 
	 * if (driver.findElement(by).isSelected()) { logPass("Element is selected"); }
	 * else { logFail("Element is not selected"); } }
	 */

	/**
	 * This method is called to check if the element is enabled or not.
	 * 
	 * @param by
	 *            reference element
	 */
	/*
	 * public void isElementEnabled(By by) { logger.info("Inside isElementEnabled");
	 * WebDriver driver = DriverFactory.getDriver(); if
	 * (driver.findElement(by).isEnabled()) { logPass("Element is Enabled"); } else
	 * { logFail("Element is not enabled"); } }
	 */

	public void logInfo(String detail) {

		ReportTestManager.getChildTest().info(detail);

	}

	private void failure(String detail) throws IOException {
		try {
			logger.info("Inside Failure" + detail);
			ReportTestManager.getChildTest().fail(MarkupHelper.createLabel(detail, ExtentColor.RED))
					.addScreenCaptureFromPath(captureScreenShot());
			Assert.fail(detail);

			// Assert.assertTrue(false);
		}

		catch (Error e) {

			logger.info("SoftAssert error ", e);
			addErrorMessageToBuffer(e);
		}

		catch (NullPointerException e) {
			// TODO Auto-generated catch block
			ReportTestManager.getChildTest().fail(detail);
		}

	}

	public String captureScreenShot() {
		String file = null;

		File src = ((TakesScreenshot) ((WebDriver) DriverManager.getDriver().getWebDriver()))
				.getScreenshotAs(OutputType.FILE);

		// now copy the screenshot to desired location using copyFile method
		file = TestListener.reportLocation + "\\SnapShots\\" + System.currentTimeMillis() + ".jpeg";
		try {
			FileUtils.copyFile(src, new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("Screenshot not taken", e);
		}

		logger.info("Screenshot taken at following location ::" + file);
		return file;

	}

	/**
	 * Checks whether any soft assert has failed. If failed, prints message to
	 * Report and fails test
	 * 
	 * @throws Exception
	 */
	public void checkForErrors() {
		if (errorMessageBuffer.get(Thread.currentThread().getId()) != null) {
			String errorMessages = errorMessageBuffer.get(Thread.currentThread().getId()).toString();
			errorMessageBuffer.remove(Thread.currentThread().getId());

			if (!"".equals(errorMessages)) {
				logger.fatal("Test Failed due to SoftAsserts \n" + errorMessages);

				Assert.fail("Overall test failed because of the following errors : \n" + errorMessages);
			}
		}
	}
}
