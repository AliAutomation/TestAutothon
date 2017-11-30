package com.sapient.taf.framework.coreclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.sapient.taf.drivermanager.DriverManager;
import io.appium.java_client.AppiumDriver;

public abstract class BaseMobilePage implements BasePage  {

	protected AppiumDriver<?> driver;
	protected Wait<? extends WebDriver> wait;
	protected ObjectRepository objectRepository;

	protected BaseMobilePage() {
		this(DriverManager.getDriver().getMobileDriver());
	}
	
	protected BaseMobilePage(AppiumDriver<?> driver) {
		this(driver, new WebDriverWait(driver, FrameworkConstants.maxWebPageOrWaitTime));
	}
	
	protected BaseMobilePage(AppiumDriver<?> driver, Wait<? extends WebDriver> wait) {
		this.driver = driver;
		this.wait = wait;
	}
}