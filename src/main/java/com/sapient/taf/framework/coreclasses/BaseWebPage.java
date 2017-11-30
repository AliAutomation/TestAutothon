package com.sapient.taf.framework.coreclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.sapient.taf.drivermanager.DriverManager;

public class BaseWebPage implements BasePage {

	protected WebDriver driver;
	protected Wait<? extends WebDriver> wait;
	protected ObjectRepository objectRepository;

	protected BaseWebPage() {
		this(DriverManager.getDriver().getWebDriver());
	}
	
	protected BaseWebPage(WebDriver driver) {
		this(driver, new WebDriverWait(driver, FrameworkConstants.maxWebPageOrWaitTime));
	}
	
	protected BaseWebPage(WebDriver driver, Wait<? extends WebDriver> wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	protected void maximizePage() {
		this.driver.manage().window().maximize();
	}

	protected void openUrl(String url) {
		this.driver.get(url);
	}
}