package com.sapient.taf.drivermanager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;

public class Driver<T extends WebDriver> {
	private T driverClass;
	private WebDriver driver;
	private final Class<? extends WebDriver> type;	

	public Driver(Capabilities capabilities, Class<? extends WebDriver> type) throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		this.type = type;
		Constructor<? extends WebDriver> driverConstructor = type.getConstructor(Capabilities.class);
		this.driver = driverConstructor.newInstance(capabilities);		
	}

	public Driver(URL remoteUrl, Capabilities capabilities, Class<? extends WebDriver> type) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.type = type;
		Constructor<? extends WebDriver> driverConstructor = type.getConstructor(URL.class,
				Capabilities.class);
		this.driver = driverConstructor.newInstance(remoteUrl, capabilities);		
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public AppiumDriver<?> getMobileDriver() {
		if (driver instanceof AppiumDriver) {
			return (AppiumDriver<?>) driver;
		} else {
			throw new RuntimeException("Driver initialized is not of Mobile Driver type, Driver Type - "
					+ driverClass.getClass().getName());
		}
	}

	public void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	public void closeDriver() {
		if (driver != null) {
			driver.close();
		}
	}

	@Override
	public String toString() {
		return "Driver [driverClass=" + driverClass + ", driver=" + driver + ", hashcode=" + this.hashCode() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result + ((driverClass == null) ? 0 : driverClass.hashCode());
		return result;
	}

	public Class<? extends WebDriver> getType() {
		return type;
	}
}