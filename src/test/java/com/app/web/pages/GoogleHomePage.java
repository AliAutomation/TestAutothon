package com.app.web.pages;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.sapient.taf.framework.coreclasses.BaseWebPage;
import com.sapient.taf.framework.coreclasses.ObjectRepository;

public class GoogleHomePage extends BaseWebPage {
	
	private static final String orFileName = "googleHomePage.properties";

	public GoogleHomePage() throws IOException {
		super();
		objectRepository = new ObjectRepository(orFileName);
		loadPage();
	}
	
	public GoogleHomePage(WebDriver driver) throws IOException {
		super(driver);
		objectRepository = new ObjectRepository(orFileName);
		loadPage();
	}
	
	public GoogleHomePage(WebDriver driver, Wait<? extends WebDriver> wait) throws IOException {
		super(driver, wait);
		objectRepository = new ObjectRepository(orFileName);
		loadPage();
	}

	public void loadPage() {
		openUrl(objectRepository.getDirectProperty("url"));
		maximizePage();
	}

	public void searchText(String... searchThis) {
		// TODO - replace with Element
		wait.until(ExpectedConditions.visibilityOfElementLocated(objectRepository.getLocator("searchbox")))
				.sendKeys(ArrayUtils.add(searchThis, String.valueOf(Keys.ENTER)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(objectRepository.getLocator("searchbox"))).click();
	}
}