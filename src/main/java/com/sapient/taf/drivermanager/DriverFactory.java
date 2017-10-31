package com.sapient.taf.drivermanager;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import com.sapient.taf.exceptions.AppiumServerPortNullException;
import com.sapient.taf.exceptions.BrowserInitException;
import com.sapient.taf.exceptions.BrowserTypeInvalidException;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverFactory {

	public static Driver<?> createInstance(String browserName, URL url, Capabilities cap, String execPath)
			throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException {
		Driver<?> driver = null;
		Capabilities myCap;

		if (cap != null) {
			myCap = new DesiredCapabilities(cap);
		} else {
			myCap = new DesiredCapabilities();
		}

		switch (browserName.trim().toUpperCase()) {
		case BrowserType.ANDROID:
			if (url == null)
				throw new AppiumServerPortNullException();
			if (cap == null)
				myCap.merge(DesiredCapabilities.android());
			driver = new Driver<AndroidDriver<MobileElement>>(url, myCap);
			break;
		case BrowserType.IPHONE:
			if (url == null)
				throw new AppiumServerPortNullException();
			if (cap == null)
				myCap.merge(DesiredCapabilities.iphone());
			driver = new Driver<IOSDriver<MobileElement>>(url, myCap);
			break;
		case BrowserType.CHROME:
			if (cap == null)
				myCap.merge(DesiredCapabilities.chrome());
			if (execPath == null)
				throw new BrowserInitException(new RuntimeException("Driver executable path invalid - " + execPath));
			else
				System.setProperty("webdriver.chrome.driver", execPath);
			driver = new Driver<ChromeDriver>(myCap);
			break;
		case BrowserType.EDGE:
			if (cap == null)
				myCap.merge(DesiredCapabilities.edge());
			if (execPath == null)
				throw new BrowserInitException(new RuntimeException("Driver executable path invalid - " + execPath));
			else
				System.setProperty("webdriver.edge.driver", execPath);
			driver = new Driver<EdgeDriver>(myCap);
			break;
		case BrowserType.FIREFOX:
			if (cap == null)
				myCap.merge(DesiredCapabilities.firefox());
			if (execPath != null)
				System.setProperty("webdriver.gecko.driver", execPath);
			driver = new Driver<FirefoxDriver>(myCap);
			break;
		case BrowserType.PHANTOMJS:
			if (execPath == null)
				throw new BrowserInitException(new RuntimeException("Driver executable path invalid - " + execPath));
			else {
				DesiredCapabilities phan = new DesiredCapabilities();
				phan.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, execPath);
				myCap.merge(phan);
			}
			driver = new Driver<PhantomJSDriver>(myCap);
			break;
		case BrowserType.IE:
			if (cap == null)
				myCap.merge(DesiredCapabilities.internetExplorer());
			if (execPath == null)
				throw new BrowserInitException(new RuntimeException("Driver executable path invalid - " + execPath));
			else
				System.setProperty("webdriver.ie.driver", execPath);
			driver = new Driver<InternetExplorerDriver>(myCap);
			break;
		case BrowserType.SAFARI:
			if (cap == null)
				myCap.merge(DesiredCapabilities.safari());
			if (execPath == null)
				throw new BrowserInitException(new RuntimeException("Driver executable path invalid - " + execPath));
			else
				System.setProperty("webdriver.safari.driver", execPath);
			driver = new Driver<SafariDriver>(myCap);
			break;
		case "GRID":
			if (cap == null)
				throw new BrowserInitException(
						"Cannot instantiate remote web browser without proper capabilities, please provide capabilities. Capabilities provided = ["
								+ cap + "]");
			if (url == null)
				throw new BrowserInitException(
						"Cannot instantiate remote web browser without url for hub, please provide the same. Url provided = ["
								+ url + "]");
			driver = new Driver<RemoteWebDriver>(url, myCap);
		case BrowserType.HTMLUNIT:
		default:
			throw new BrowserTypeInvalidException();
		}
		return driver;
	}

	public static Driver<?> createInstance(String browserName, URL url, String execPath)
			throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException {
		return createInstance(browserName, url, null, execPath);
	}

	public static Driver<?> createInstance(String browserName, String execPath)
			throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException {
		return createInstance(browserName, null, null, execPath);
	}

	public static Driver<?> createInstance(String browserName, Capabilities cap, String execPath)
			throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException {
		return createInstance(browserName, null, cap, execPath);
	}
}