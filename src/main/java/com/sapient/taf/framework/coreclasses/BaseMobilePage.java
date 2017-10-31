package com.sapient.taf.framework.coreclasses;

import io.appium.java_client.AppiumDriver;

public class BaseMobilePage implements BasePage  {

	protected AppiumDriver<?> driver;

	protected AppiumDriver<?> getDriver() {
		return driver;
	}

	protected void setDriver(AppiumDriver<?> driver) {
		this.driver = driver;
	}	
}