package com.restassured.stepdefs;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.sapient.taf.drivermanager.DriverManager;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	public static Scenario scenarioGlobal;

	@Before
	public static void start(Scenario scenario) {
		scenarioGlobal = scenario;
	}

	@After
	public static void end(Scenario scenario) {
		if (!scenario.isFailed()) {
			return;
		}

		WebDriver driver = DriverManager.getDriver().getWebDriver();
		scenario.write("url: " + driver.getCurrentUrl());

		if (driver instanceof TakesScreenshot) {
			TakesScreenshot camera = (TakesScreenshot) driver;
			byte[] screenshot = camera.getScreenshotAs(OutputType.BYTES);

			scenario.embed(screenshot, "image/png");

		}

		// scenario.write(Utils.htmlEscape(driver.getPageSource()));

	}

}
