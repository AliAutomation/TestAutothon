package com.app.web.pages.cucumber;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.app.web.objectrepo.TransferFundsRepo;
import com.restassured.stepdefs.Hooks;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;
import com.sapient.taf.reporting.ReportUtil;

public class TransferFundsActions extends CommonPage implements TransferFundsRepo {

	
	final static MyLogger logger = LoggerFactory
			.getLogger(TransferFundsActions.class);
	
	public void clickOnTransferMoneyButton(String Locator) {
		if (Locator.equalsIgnoreCase("transfer_Money"))
			click(driver.findElement(transfer_Money_button));
		else if (Locator.equalsIgnoreCase("sign_Off"))
			click(driver.findElement(sign_Off));
	}

	
	public void verifySignOffLabel() {
		
	if(driver.findElement(By.xpath("//font")).getText().contains("Sign Off")) {
		Hooks.scenarioGlobal.write("Sign Off validation passed");
	}
	else {
		ReportUtil.highlightFailure(By.xpath("//font"));
		Assert.fail("Sign off valdation failed");
	}
		//Assert.assertTrue(driver.findElement(By.xpath("//font")).getText().contains("Sign Off"));

			
			
		
	}
	public void enterValue(String Locator, String Value) {
		if (Locator.equalsIgnoreCase("from_Account"))
			selectDropDown(driver.findElement(from_Account), Value);
		else if (Locator.equalsIgnoreCase("to_Account"))
			selectDropDown(driver.findElement(to_Account), Value);
		if (Locator.equalsIgnoreCase("amount"))
			type(driver.findElement(amount), Value);
	}

	public void verifyMessage(String from_Account, String to_Account, String amount) {
		String actualErrorMessage = verifyMessage(driver.findElement(transfer_Money_Message));
		System.out.println(actualErrorMessage + " --------------> actualErrorMessage");
		Assert.assertTrue(actualErrorMessage.contains(from_Account));
		Assert.assertTrue(actualErrorMessage.contains(to_Account));
		Assert.assertTrue(actualErrorMessage.contains(amount));
	}
	
	public void verifyTransferFundLabel(String Locator) throws InterruptedException
	{	
		Hooks.scenarioGlobal.write("Validating Label of page " + Locator);
		WebElement trans_Label=	driver.findElement(transferFund_Label);
		//System.out.println("Page Title" + pageTitle);
		Assert.assertTrue(trans_Label.getText().contains(Locator));
	}


	public void verifySignInLink(String Locator) {
		if (Locator.equalsIgnoreCase("sign_In"))
			isElementPresent(driver.findElement(sign_In_Link));
	}
}
