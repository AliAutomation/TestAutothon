package com.sapient.taf.exceptions;

public class BrowserTypeInvalidException extends RuntimeException {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 4510456820608846896L;
	private static final String message = "Browser Type mentioned is invalid";

	public BrowserTypeInvalidException() {
		super();
	}

	public BrowserTypeInvalidException(String message) {
		super(message);
	}

	public BrowserTypeInvalidException(Throwable t) {
		super(t);
	}

	public BrowserTypeInvalidException(String message, Throwable t) {
		super(message, t);
	}

	@Override
	public String toString() {
		return message;
	}
}