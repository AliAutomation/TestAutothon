package com.runner;

import java.io.File;

import org.junit.AfterClass;

import com.cucumber.listener.Reporter;
import com.sapient.taf.framework.coreclasses.BaseClassCucumber;

import cucumber.api.CucumberOptions;

@CucumberOptions(features = "src/test/java/com/app/features/soap.feature", glue = "com.soap.stepdefs",
		// tags = {"@Positive"},
		plugin = { "pretty", "html:target/site/cucumber-pretty", "json:target/reports/SoapRunner.json",

				"com.cucumber.listener.ExtentCucumberFormatter:target/output_ExtentReport/SenarioExecutionReport.html" }, monochrome = true)
public class SoapRunner extends BaseClassCucumber {

	@AfterClass
	public static void teardown() {
		Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("os", "Windows 7");
		Reporter.setTestRunnerOutput("SenarioExecutionReport using cucumber extent reporting");
	}
}
