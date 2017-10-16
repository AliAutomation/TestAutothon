package com.sapient.taf.drivermanager;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import com.sapient.taf.exceptions.BrowserTypeInvalidException;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverFactory {
	static Driver<?> createInstance(String browserName, URL url) throws InvocationTargetException, InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException {
		Driver<?> driver = null;
		Capabilities myCap = new DesiredCapabilities();
		switch (DriverType.valueOf(browserName.trim().toUpperCase())) {
		case ANDROID:
			myCap.merge(DesiredCapabilities.android());
			driver = new Driver<AndroidDriver<MobileElement>>(url, myCap);
			break;
		case CHROME:
			myCap.merge(DesiredCapabilities.chrome());
			driver = new Driver<ChromeDriver>(myCap);
			break;
		case EDGE:
			myCap.merge(DesiredCapabilities.edge());
			driver = new Driver<EdgeDriver>(myCap);
			break;
		case FIREFOX:
			myCap.merge(DesiredCapabilities.firefox());
			driver = new Driver<FirefoxDriver>(myCap);
			break;
		case HEADLESS:
			driver = new Driver<PhantomJSDriver>(myCap);
			break;
		case IE:
			myCap.merge(DesiredCapabilities.internetExplorer());
			driver = new Driver<InternetExplorerDriver>(myCap);
			break;
		case IPHONE:
			myCap.merge(DesiredCapabilities.iphone());
			driver = new Driver<IOSDriver<MobileElement>>(url, myCap);
			break;
		case SAFARI:
			myCap.merge(DesiredCapabilities.safari());
			driver = new Driver<SafariDriver>(myCap);
			break;
		default:
			throw new BrowserTypeInvalidException();
		}
		return driver;
	}
}
