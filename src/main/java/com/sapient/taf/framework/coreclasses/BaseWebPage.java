package com.sapient.taf.framework.coreclasses;

import org.openqa.selenium.WebDriver;

public class BaseWebPage implements BasePage {

	protected WebDriver driver;

	protected WebDriver getDriver() {
		return driver;
	}

	protected void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}