package com.runner;

import java.io.File;

import org.junit.AfterClass;

import com.cucumber.listener.Reporter;
import com.sapient.taf.framework.coreclasses.BaseClassCucumber;

import cucumber.api.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/com/app/features/onlineBankingHome.feature",
        glue = "com.restassured.stepdefs",
        tags = {"@Positive"},
        plugin={
        		//"pretty", "html:target/site/cucumber-pretty",
                "json:target/reports/OnlineBankingHomeRunner.json",
        		
        		"pretty","com.cucumber.listener.ExtentCucumberFormatter:target/output_ExtentReport/SenarioExecutionReport.html"
        		},
        monochrome = true 
        )

public class OnlineBankingHomeRunner extends BaseClassCucumber{

	@AfterClass
    public static void teardown() {
        Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Windows 7");
        Reporter.setTestRunnerOutput("SenarioExecutionReport using cucumber extent reporting");
    }
}
