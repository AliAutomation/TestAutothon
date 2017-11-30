package com.app.mobile.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import com.app.mobile.objectrepo.GoogleMobileHomePageRepo;
import com.sapient.taf.framework.coreclasses.BaseMobilePage;

import io.appium.java_client.AppiumDriver;

public class GoogleMobileHomePage extends BaseMobilePage implements GoogleMobileHomePageRepo {
	public GoogleMobileHomePage(AppiumDriver<?> driver) {
		super(driver);
	}

	public GoogleMobileHomePage() {
		super();
	}

	public GoogleMobileHomePage(AppiumDriver<?> driver, Wait<? extends WebDriver> wait) {
		super(driver, wait);
	}
}