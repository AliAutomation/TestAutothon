package com.app.web.tests;

import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import com.sapient.taf.drivermanager.DriverManager;

@ContextConfiguration("classpath:spring/frameworkCtx.xml")
public class SampleTest extends AbstractTestNGSpringContextTests {
	@Test
	public void dummyTest() throws Exception {
		WebDriver driver = DriverManager.getDriver().getWebDriver();
		driver.get("http://www.google.co.in");
		Thread.sleep(10000);
	}
}
