package com.sapient.taf.exceptions;

public class BrowserInitException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6410812761783420242L;
	private static final String message = "Browser Type mentioned is invalid";

	public BrowserInitException() {
		super(message);
	}

	public BrowserInitException(String message) {
		super(message);

	}

	public BrowserInitException(Throwable cause) {
		super(message, cause);
	}

	public BrowserInitException(String message, Throwable cause) {
		super(message, cause);
	}

	public BrowserInitException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public String toString() {
		return message;
	}
}
