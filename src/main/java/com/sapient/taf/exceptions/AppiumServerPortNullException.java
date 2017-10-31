package com.sapient.taf.exceptions;

public class AppiumServerPortNullException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 732305107836047867L;
	private static final String message = "Cannot instantiate browser when appium server url is null";

	public AppiumServerPortNullException() {
		super(message);
	}

	public AppiumServerPortNullException(String message) {
		super(message);
	}

	public AppiumServerPortNullException(Throwable cause) {
		super(message, cause);
	}

	public AppiumServerPortNullException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppiumServerPortNullException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public String toString() {
		return message;
	}
}
