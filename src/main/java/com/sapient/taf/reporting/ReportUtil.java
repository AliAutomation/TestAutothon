package com.sapient.taf.reporting;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.types.selectors.ExtendSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.sapient.taf.assertions.SoftAssertCustom;
import com.sapient.taf.drivermanager.DriverFactory;
import com.sapient.taf.drivermanager.DriverManager;
import com.sapient.taf.listeners.TestListener;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;


public class ReportUtil {

	/**
	 * The Logger logger
	 */
	private final static MyLogger logger = LoggerFactory
			.getLogger(ReportUtil.class);

	/**
	 * This method is called whenever a test case passes
	 * 
	 * @param detail
	 *            the detial for passed test case
	 */
	public static void logPass(String detail) {
		logger.info("Test Case passes" + ReportTestManager.getChildTest().toString());
		Assert.assertTrue(true, detail);
		ReportTestManager.getChildTest().pass( MarkupHelper.createLabel(detail, ExtentColor.GREEN));
		// ReportTestManager.getTest().addScreenCapture(captureScreenShot());
	}

	/**
	 * This method is called whenever a test case fails
	 * 
	 * @param detail
	 *            the detail for failed test case
	 * @throws IOException 
	 */
	public static void logFail(String detail) throws IOException {
		failure(detail);
	}

	public static void verifyEqual(Object actual, Object expected) throws IOException {
		if (!actual.equals(expected)) {
			logFail(actual + " not equals to " + expected);
		} else {
			logPass(actual + " equals to " + expected);
		}

	}

	/**
	 * This method is called whenever a test case is skipped
	 * 
	 * @param detail
	 *            the details for skipped test case
	 */
	public static void logSkipped(String detail) {
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
/*	public static void isElementDisplayed(By by) {
		logger.info("Inside isElementDisplayed");
		WebDriver driver = DriverFactory.getDriver();

		if (driver.findElement(by).isDisplayed()) {
			logPass("Element Displayed Successfully");
		} else {
			logFail("Element not displayed");
		}
	}*/

	/**
	 * This method is called to check if any element is selected or not
	 * 
	 * @param by
	 *            reference element
	 */
	/*public static void isElementSelected(By by) {
		logger.info("Inside isElementSelected");
		WebDriver driver = DriverFactory.getDriver();

		if (driver.findElement(by).isSelected()) {
			logPass("Element is selected");
		} else {
			logFail("Element is not selected");
		}
	}*/

	/**
	 * This method is called to check if the element is enabled or not.
	 * 
	 * @param by
	 *            reference element
	 */
	/*public static void isElementEnabled(By by) {
		logger.info("Inside isElementEnabled");
		WebDriver driver = DriverFactory.getDriver();
		if (driver.findElement(by).isEnabled()) {
			logPass("Element is Enabled");
		} else {
			logFail("Element is not enabled");
		}
	}*/

	/**
	 * capture the screenshot
	 * 
	 * @return the file of capture screenshot.
	 */

	public static void assertEqual(String actual, String expected) {

		if (actual.equals(expected)) {
			logPass(actual + " is equals to " + expected);

		} else {
			Assert.assertEquals(actual, expected);
		}
	}

	public static void assertTrue(boolean flag) {

		if (flag) {
			logPass("Assertion Passes");
		} else {
			//logFail("Assertion Fails, expected True but was false");
			Assert.assertTrue(flag);
		}
	}

	public static void assertFalse(boolean flag) {

		if (flag) {
			//logFail("Assertion Fails");
			Assert.assertFalse(flag);
		} else {
			logPass("Assertion Passes");
		}
	}


	public static void assertTrue(boolean flag, String details) {
		if (flag) {
			logPass(details);
		} else {
			//logFail(details);
			Assert.assertTrue(flag, details);
		}
	}

	public static void assertFalse(boolean flag, String details) throws IOException {

		if (flag) {
			logFail(details);
		} else {
			logPass(details);
		}
	}

	public static void logInfo(String detail) {

		ReportTestManager.getChildTest().info(detail);

	}

	private static void failure(String detail) throws IOException {
		try {
			logger.info("Inside Failure"+ detail);
			ReportTestManager.getChildTest().fail(
					MarkupHelper.createLabel(detail , ExtentColor.RED))
					.addScreenCaptureFromPath(
							captureScreenShot());
			Assert.fail(detail);
			
			// Assert.assertTrue(false);
		} 
		
		
		catch (Error e) {
		
			logger.info("SoftAssert error ", e);
			SoftAssertCustom.addErrorMessageToBuffer(e);}
		
		catch (NullPointerException e) {
			// TODO Auto-generated catch block
			ReportTestManager.getChildTest().fail(detail);
		}

	}

	public static String captureScreenShot() {
		String file = null;
		
			File src = ((TakesScreenshot)((WebDriver) DriverManager.getDriver().getWebDriver())).
					getScreenshotAs(OutputType.FILE);

			// now copy the screenshot to desired location using copyFile method
			file = TestListener.reportLocation+ "\\SnapShots\\"
					+ System.currentTimeMillis() + ".jpeg";
			try {
				FileUtils.copyFile(src, new File(file));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.info("Screenshot not taken", e);
			}
		
		logger.info("Screenshot taken at following location ::" + file);
		return file;

	}

}
