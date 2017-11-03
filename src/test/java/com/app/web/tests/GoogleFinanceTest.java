package com.app.web.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.sapient.taf.drivermanager.DriverManager;

public class GoogleFinanceTest {
	@Test
	public void dummyTest() throws Exception {
		WebDriver driver = DriverManager.getDriver().getWebDriver();
		driver.get("http://www.google.co.in");
		Thread.sleep(500);
	}
}