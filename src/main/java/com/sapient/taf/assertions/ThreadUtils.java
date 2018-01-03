package com.sapient.taf.assertions;

public class ThreadUtils {

	/**
	 * This class is used for getting the thread id and is open for additional
	 * thread related operations Class has default access modifier so that it can be
	 * used within the package (support-libs) only.
	 * 
	 * @author harora
	 * 
	 */
	
		public static Long getThreadId() {
			return Thread.currentThread().getId();
		}
	}
	

