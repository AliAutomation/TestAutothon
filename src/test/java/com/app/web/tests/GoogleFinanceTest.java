package com.app.web.tests;

import java.io.IOException;

import org.testng.annotations.Test;
import com.app.web.pages.GoogleHomePage;
import com.sapient.taf.drivermanager.DriverManager;

public class GoogleFinanceTest {

	@Test
	public void dummyTest() throws IOException {
		GoogleHomePage ghp = new GoogleHomePage(DriverManager.getDriver().getWebDriver());
		ghp.searchText("AAPL stock");
	}
}