package com.sapient.taf.listeners;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.google.common.base.Splitter;
import com.sapient.taf.drivermanager.DriverFactory;
import com.sapient.taf.drivermanager.DriverManager;
import com.sapient.taf.exceptions.BrowserInitException;
import com.sapient.taf.framework.coreclasses.FrameworkConstants;
import com.sapient.taf.utils.JsonUtils;

public class TestListener implements IInvokedMethodListener {

	@Autowired
	@Qualifier("runConfig")
	private Properties runConfig;
	private String execPath;
	private ApplicationContext appContext;

	@Autowired
	@Qualifier("browserConfigMap")
	private JsonUtils browserConfigMap;

	public TestListener() {
		appContext = new ClassPathXmlApplicationContext(FrameworkConstants.frameworkContextPath);
		runConfig = appContext.getBean("runConfig", Properties.class);
		browserConfigMap = appContext.getBean("browserConfigMap", JsonUtils.class);
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod()) {
			try {
				boolean gridExecution = Boolean
						.valueOf(runConfig.getProperty("framework.execution.remote.value", "false").toLowerCase());
				URL url = new URL(gridExecution ? runConfig.getProperty("framework.execution.remote.hub.url")
						: runConfig.getProperty("framework.appium.url"));
				String browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browser");
				Capabilities cap = getCapabilities(browserName, gridExecution,
						new DesiredCapabilities(Splitter.on(',').withKeyValueSeparator('=').split(runConfig
								.getProperty("framework.execution.additional.capabilities", "cap1=val1,cap2=val2"))));
				DriverManager.setWebDriver(DriverFactory.createInstance(browserName, url, cap, execPath));
			} catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException
					| SecurityException | IllegalArgumentException | MalformedURLException | FileNotFoundException e) {
				throw new BrowserInitException(e);
			}
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if (method.isTestMethod() && DriverManager.getDriver() != null) {
			DriverManager.getDriver().quitDriver();
		}
	}

	private Capabilities getCapabilities(String browserName, boolean grid, Capabilities... moreCapabilities)
			throws FileNotFoundException {
		DesiredCapabilities capabilities = new DesiredCapabilities(moreCapabilities);
		capabilities.setBrowserName(browserName);
		capabilities.setPlatform(grid ? Platform.valueOf(runConfig.getProperty("framework.execution.remote.platform"))
				: Platform.getCurrent());
		setExecPath(browserName, grid);
		return capabilities;
	}

	private void setExecPath(String browserName, boolean grid) throws FileNotFoundException {
		if (grid) {
			execPath = null;
			return;
		} else {
			Map<String, Map<String, String>> params = browserConfigMap.readJson();
			execPath = params.get(Platform.getCurrent()).get(browserName);
		}
	}
}