package com.sapient.taf.drivermanager;



import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.AppiumDriver;

public class Driver<T extends WebDriver> {
    private WebDriver driver;
    private final Class<? extends WebDriver> type;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Driver(Capabilities capabilities, Class<? extends WebDriver> type)
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException,
            IllegalArgumentException, InvocationTargetException {
        logger.info("Initializing Driver Object for type {} and capabilities {}", type.getName(),
                capabilities.toString());
        this.type = type;
        Constructor<? extends WebDriver> driverConstructor = type.getConstructor(Capabilities.class);
        this.driver = driverConstructor.newInstance(capabilities);
        logger.info("Driver initialization successful");
        logger.debug("Driver created as {}", this.toString());
    }

    public Driver(URL remoteUrl, Capabilities capabilities, Class<? extends WebDriver> type)
            throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        logger.info("Initializing Driver Object for type {} and capabilities {}", type.getName(),
                capabilities.toString());
        this.type = type;
        Constructor<? extends WebDriver> driverConstructor = type.getConstructor(URL.class, Capabilities.class);
        this.driver = driverConstructor.newInstance(remoteUrl, capabilities);
        logger.info("Driver initialization successful");
        logger.debug("Driver created as {}", this.toString());
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public boolean isMobileDriver() {
        return driver instanceof AppiumDriver;
    }

    public AppiumDriver<?> getMobileDriver() {
        if (driver instanceof AppiumDriver) {
            return (AppiumDriver<?>) driver;
        } else {
            RuntimeException e = new RuntimeException(
                    "Driver initialized is not of Mobile Driver type, Driver Type - " + type.getName());
            logger.error("Error  - ", e);
            throw e;
        }
    }

    public void quitDriver() {
        logger.info("Quitting Driver, driver = {}", driver);
        if (driver != null) {
            driver.quit();
            logger.info("Driver Quit successfully");
        }
    }

    public void closeDriver() {
        logger.info("Closing Driver, driver = {}", driver);
        if (driver != null) {
            driver.close();
            logger.info("Driver closed successfully");
        }
    }

    @Override
    public String toString() {
        return "Driver [driver=" + driver + ", type=" + type + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((driver == null) ? 0 : driver.hashCode());
        return result;
    }

    public Class<? extends WebDriver> getType() {
        return type;
    }
}