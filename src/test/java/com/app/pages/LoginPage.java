package com.app.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import com.app.web.objectrepo.LoginRepo;
import com.app.web.pages.cucumber.CommonPage;
import com.restassured.stepdefs.OnlineBankingHomeSteps;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;

import java.io.IOException;

import org.junit.Assert;

public class LoginPage extends CommonPage implements LoginRepo{

	final static MyLogger logger = LoggerFactory
			.getLogger(LoginPage.class);
	
	
	public LoginPage() throws IOException  {
		super();
		
		
	}
	
	public LoginPage(WebDriver driver) throws IOException {
		super(driver);
		
	}
	
	public LoginPage(WebDriver driver, Wait<? extends WebDriver> wait) throws IOException {
		super(driver, wait);
		
	}

	public void loadPage() {
		openUrl(inputData.getDirectProperty("url"));
		maximizePage();
	}
	
	
	
	public void enterValue(String Locator)
	{	
		System.out.println("login " + reportUtil);
		reportUtil.logInfo("LoginPageActions_Entering value in " + Locator + " Element");
		if (Locator.equalsIgnoreCase("userName"))
			type(driver.findElement(userName), inputData.getDirectProperty(Locator));
		else if (Locator.equalsIgnoreCase("passWord"))
			type(driver.findElement(password), inputData.getDirectProperty(Locator));
		else if (Locator.equalsIgnoreCase("Invalid_userName"))
			type(driver.findElement(userName), inputData.getDirectProperty(Locator));
	}
	public void clickSignInButton(String Locator)
	{
		reportUtil.logInfo("LoginPageActions_Clicking on " + Locator + " Element");
		if (Locator.equalsIgnoreCase("signIn"))
			click(driver.findElement(signIn));
		
	}
	
	public void isSignOffLinkPresent(String Locator)
	{
		reportUtil.logInfo("LoginPageActions_Verify that " + Locator + " is present or not");
		isElementPresent(driver.findElement(sign_Off));
	}
	
	public void verifyErrorMessage(String Locator , String expectedErrorMessage)
	{	
		reportUtil.logInfo("LoginPageActions_Verify Error Message " + expectedErrorMessage + " appears or not");
		String actualErrorMessage = verifyMessage(driver.findElement(login_Fail_Error));
		Assert.assertTrue(actualErrorMessage.equalsIgnoreCase(expectedErrorMessage));
	}
	

}
