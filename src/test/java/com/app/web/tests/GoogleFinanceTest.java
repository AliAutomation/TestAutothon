package com.app.web.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import com.app.web.pages.GoogleHomePage;

public class GoogleFinanceTest {

	@Test
	public void dummyTest() throws IOException {
		GoogleHomePage ghp = new GoogleHomePage();
		ghp.searchText("AAPL stock");
	}
}