package com.app.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import com.app.web.objectrepo.LoginRepo;
import com.app.web.objectrepo.UploadToBerkeleyPageRepo;
import com.app.web.pages.cucumber.CommonPage;
import com.restassured.stepdefs.OnlineBankingHomeSteps;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;

import java.io.IOException;

import org.junit.Assert;

public class UploadToBerkeleyPage extends CommonPage implements UploadToBerkeleyPageRepo{
	
	final static MyLogger logger = LoggerFactory
			.getLogger(UploadToBerkeleyPage.class);
	
	public UploadToBerkeleyPage() throws IOException  {
		super();
	}
	
	public UploadToBerkeleyPage(WebDriver driver) throws IOException {
		super(driver);
	}
	
	public UploadToBerkeleyPage(WebDriver driver, Wait<? extends WebDriver> wait) throws IOException {
		super(driver, wait);
	}

	public void loadPage() {
		openUrl(inputData.getDirectProperty("uploadToBerkeley"));
		maximizePage();
	}
	
	public void uploadFile() throws IOException {
		
		reportUtil.logInfo("***** About to upload File *****");
		driver.findElement(inpChooseFile).click();
//		.sendKeys(inputData.getDirectProperty("uploadToBerkeleyPath"));
//		driver.findElement(arg0)
//		Runtime.getRuntime().exec(inputData.getDirectProperty("uploadToBerkeleyPath"));
		
		
	}
	
	public void enterNotes()
	{
		driver.findElement(inpNote).sendKeys(inputData.getDirectProperty("uploadToBerkeleyMessage"));
	}
	 
	public void submit() {
		driver.findElement(inpSubmit).click();
	}
	

}
