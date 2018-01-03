package com.sapient.taf.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampUtils {

	/**
	 * @param args
	 */
	public static String getTimeStamp() {
		String fileName = new SimpleDateFormat("dd-MMM-yy HHmmss")
				.format(new Date());
		return fileName;
	}
	

	
	

}
