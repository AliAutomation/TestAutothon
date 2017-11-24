package com.app.web.pages;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sapient.taf.framework.coreclasses.BaseWebPage;
import com.sapient.taf.framework.coreclasses.ObjectRepository;

public class GoogleHomePage extends BaseWebPage {
	private ObjectRepository orGoogleHomePage;

	public ObjectRepository getOrGoogleHomePage() {
		return orGoogleHomePage;
	}

	public void setOrGoogleHomePage(ObjectRepository orGoogleHomePage) {
		this.orGoogleHomePage = orGoogleHomePage;
	}

	public GoogleHomePage(WebDriver driver) throws IOException {
		super(driver);
		orGoogleHomePage = new ObjectRepository("googleHomePage.properties");
		loadPage();
	}

	public void loadPage() {
		openUrl(orGoogleHomePage.getDirectProperty("url"));
		maximizePage();
	}

	public void searchText(String... searchThis) {
		// TODO - replace with Element
		wait.until(ExpectedConditions.visibilityOfElementLocated(orGoogleHomePage.getLocator("searchbox")))
				.sendKeys(ArrayUtils.add(searchThis, String.valueOf(Keys.ENTER)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(orGoogleHomePage.getLocator("searchbox"))).click();
	}
}