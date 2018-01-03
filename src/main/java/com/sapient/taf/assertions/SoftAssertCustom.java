package com.sapient.taf.assertions;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.sapient.taf.reporting.ReportUtil;




public class SoftAssertCustom {
	private static final Logger LOGGER = Logger.getLogger(SoftAssertCustom.class);

	private static HashMap<Long, StringBuffer> errorMessageBuffer = new HashMap<Long, StringBuffer>();

	/**
	 * Add the error message to the error buffer. This error buffer is used to
	 * determine whether the test case is passed when the {@link checkForErrors}
	 * method is called.
	 * 
	 * @param error
	 */
	public static void addErrorMessageToBuffer(Error error) {
		try {
			StringBuffer errorBuffer = null;
			if (errorMessageBuffer.get(ThreadUtils.getThreadId()) == null) {
				errorBuffer = new StringBuffer().append(error + "\n");
			} else {
				errorBuffer = errorMessageBuffer.get(ThreadUtils.getThreadId())
						.append(error + "\n");
			}
			errorMessageBuffer.put(ThreadUtils.getThreadId(), errorBuffer);
		} catch (Exception e) {
			LOGGER.warn("Adding Error to Error Buffer threw error", e);
		}
	}

	/**
	 * Marks the step as failed, takes a warning screenshot and prints message
	 * to report
	 * 
	 * @param message
	 *            Message to be printed to the report
	 */
	public static void fail(String message) {
		try {
			Assert.fail(message);
			ReportUtil.captureScreenShot();
		} catch (Error e) {
			LOGGER.warn("SoftAssert error ", e);
			addErrorMessageToBuffer(e);}
		
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
	

	public static void assertTrue(boolean condition, String message) {
		try {
			Assert.assertTrue(condition, message);
		} catch (Error e) {
			LOGGER.warn("SoftAssert error ", e);
			addErrorMessageToBuffer(e);
			
		}
	}

	/**
	 * Check if actual and expected String values are equal. If not equal, marks
	 * the step as failed, highlights the element
	 * and prints message to report
	 * 
	 * @param actual
	 *            Actual String
	 * @param expected
	 *            Expected String
	 * @param message
	 *            Message to be printed to the report
	 */
	

	public static void assertEquals(String actual, String expected,
			String message) {
		try {
			Assert.assertEquals(actual, expected, message);
		} catch (Error e) {
			LOGGER.warn("SoftAssert error ", e);
			addErrorMessageToBuffer(e);
			
		}
	}

	/**
	 * Checks whether any soft assert has failed. If failed, prints message to
	 * Report and fails test
	 * 
	 * @throws Exception
	 * */
	public static void checkForErrors() {
		if (errorMessageBuffer.get(ThreadUtils.getThreadId()) != null) {
			String errorMessages = errorMessageBuffer.get(
					ThreadUtils.getThreadId()).toString();
			errorMessageBuffer.remove(ThreadUtils.getThreadId());

			if (!"".equals(errorMessages)) {
				LOGGER.fatal("Test Failed due to SoftAsserts \n"
						+ errorMessages);
				
				Assert.fail("Overall test failed because of the following errors : \n"
						+ errorMessages);
			}
		}
	}
}
