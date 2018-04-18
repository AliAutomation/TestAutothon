package com.runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import com.cucumber.listener.Reporter;
import com.sapient.taf.framework.coreclasses.BaseClassCucumber;

@CucumberOptions(features = "src/test/java/com/app/features/transferFunds.feature", glue = "com.restassured.stepdefs",
		// tags = {"@Positive"},
		plugin = {
				// "pretty", "html:target/site/cucumber-pretty",
				"json:target/reports/TransferFundsRunner.json",

				"pretty",
				"com.cucumber.listener.ExtentCucumberFormatter:target/output_ExtentReport/SenarioExecutionReport.html" }, monochrome = true)
public class TransferFundsRunner extends BaseClassCucumber {
	@AfterClass
	public static void teardown() {
		Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("os", "Windows 7");
		Reporter.setTestRunnerOutput("SenarioExecutionReport using cucumber extent reporting");
	}
}