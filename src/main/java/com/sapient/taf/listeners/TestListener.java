package com.sapient.taf.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.IResultListener;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sapient.taf.drivermanager.DriverFactory;
import com.sapient.taf.drivermanager.DriverManager;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;
import com.sapient.taf.reporting.ReportManager;
import com.sapient.taf.reporting.ReportTestManager;
import com.sapient.taf.reporting.ReportUtil;

import com.sapient.taf.utils.TimestampUtils;

public class TestListener implements IInvokedMethodListener {
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			try {
				DriverManager.setWebDriver(DriverFactory
						.createInstance(method.getTestMethod().getXmlTest().getLocalParameters().get("browser")));
			} catch (MalformedURLException | InvocationTargetException | InstantiationException | IllegalAccessException
					| NoSuchMethodException | SecurityException | IllegalArgumentException | FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod() && DriverManager.getDriver().getWebDriver() != null) {
			DriverManager.getDriver().quitDriver();
		}
	}
}