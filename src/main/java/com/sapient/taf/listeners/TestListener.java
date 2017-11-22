package com.sapient.taf.listeners;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import com.sapient.taf.drivermanager.DriverFactory;
import com.sapient.taf.drivermanager.DriverManager;

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