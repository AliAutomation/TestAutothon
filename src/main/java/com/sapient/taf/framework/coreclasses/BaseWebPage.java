package com.sapient.taf.framework.coreclasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseWebPage implements BasePage {

	protected WebDriver driver;
	protected Wait<WebDriver> wait;

	protected WebDriver getDriver() {
		return driver;
	}

	protected void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public Wait<?> getWait() {
		return wait;
	}

	public void setWait(Wait<WebDriver> wait) {
		this.wait = wait;
	}

	protected BaseWebPage(WebDriver driver, List<Class<? extends Exception>> exceptionsToIgnore) {
		this.driver = driver;
		wait = new WebDriverWait(driver, FrameworkConstants.maxWebPageOrWaitTime).ignoreAll(exceptionsToIgnore);
	}
	
	protected BaseWebPage(WebDriver driver, boolean noWaitInit) {
		this.driver = driver;
	}

	protected BaseWebPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, FrameworkConstants.maxWebPageOrWaitTime);
	}

	protected BaseWebPage(WebDriver driver, Class<? extends Wait<?>> typeOfWait) {
		this.driver = driver;
		// TODO
	}

	protected void maximizePage() {
		this.driver.manage().window().maximize();
	}

	protected void openUrl(String url) {
		this.driver.get(url);
	}
}