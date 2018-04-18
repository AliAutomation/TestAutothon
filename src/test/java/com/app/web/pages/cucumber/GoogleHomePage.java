package com.app.web.pages.cucumber;



import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.app.web.objectrepo.GoogleHomePageRepo;

import com.sapient.taf.reporting.ReportTestManager;
import com.sapient.taf.reporting.ReportUtil;

public class GoogleHomePage extends CommonPage implements GoogleHomePageRepo {
	

	public GoogleHomePage() {
		super();
		
	}
	
	public GoogleHomePage(WebDriver driver)  {
		super(driver);
		
	}
	
	public GoogleHomePage(WebDriver driver, Wait<? extends WebDriver> wait) {
		super(driver, wait);
	
	}

	public void loadPage() {
		openUrl(inputData.getDirectProperty("url"));
		maximizePage();
	}

	public void searchText(String... searchThis) {
		// TODO - replace with Element
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchbox))
				.sendKeys(ArrayUtils.add(searchThis, String.valueOf(Keys.ENTER)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchbox)).click();
		
	}
}