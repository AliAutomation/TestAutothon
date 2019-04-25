package com.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import com.app.web.objectrepo.LoginRepo;
import com.app.web.objectrepo.MfsLoginRepo;
import com.app.web.pages.cucumber.CommonPage;
import com.restassured.stepdefs.OnlineBankingHomeSteps;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;

import java.io.IOException;

import org.junit.Assert;

public class MfsLoginPage extends CommonPage implements MfsLoginRepo {

	final static MyLogger logger = LoggerFactory.getLogger(MfsLoginPage.class);

	public MfsLoginPage() throws IOException {
		super();
	}

	public MfsLoginPage(WebDriver driver) throws IOException {
		super(driver);
	}

	public MfsLoginPage(WebDriver driver, Wait<? extends WebDriver> wait) throws IOException {
		super(driver, wait);
	}

	public void loadPage() {
		openUrl(inputData.getDirectProperty("url"));
		maximizePage();
	}

	public void enterSearchValue() {
		driver.findElement(signIn).click();
		driver.findElement(serachBox).sendKeys("abc");
		System.out.println("Test");
	}

}
