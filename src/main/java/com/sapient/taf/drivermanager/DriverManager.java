package com.sapient.taf.drivermanager;



import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.openqa.selenium.Capabilities;

public class DriverManager {

    private static ThreadLocal<Driver<?>> webDriver = new ThreadLocal<>();

    public static Driver<?> getDriver() {
        return webDriver.get();
    }

    public static void setWebDriver(Driver<?> driver) {
        webDriver.set(driver);
    }

    public static Driver<?> getAnotherDriverInstance(String browserName, URL url, String execPath)
            throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException,
            SecurityException, IllegalArgumentException {
        return DriverFactory.createInstance(browserName, url, execPath);
    }

    public static Driver<?> getAnotherDriverInstance(String browserName, URL url, Capabilities cap, String execPath)
            throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException,
            SecurityException, IllegalArgumentException {
        return DriverFactory.createInstance(browserName, url, cap, execPath);
    }
}
