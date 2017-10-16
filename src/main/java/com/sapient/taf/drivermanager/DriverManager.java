package com.sapient.taf.drivermanager;

public class DriverManager {
	private static ThreadLocal<Driver<?>> webDriver = new ThreadLocal<>();

	public static Driver<?> getDriver() {
		return webDriver.get();
	}

	static void setWebDriver(Driver<?> driver) {
		webDriver.set(driver);
	}
}