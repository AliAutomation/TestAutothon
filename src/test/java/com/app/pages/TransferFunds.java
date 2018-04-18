package com.app.pages;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.app.web.objectrepo.TransferFundsRepo;
import com.app.web.pages.cucumber.CommonPage;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;

public class TransferFunds extends CommonPage implements TransferFundsRepo {

	
	final static MyLogger logger = LoggerFactory
			.getLogger(TransferFunds.class);
	
	public void clickOnTransferMoneyButton(String Locator) {
		if (Locator.equalsIgnoreCase("transfer_Money"))
			click(driver.findElement(transfer_Money_button));
		else if (Locator.equalsIgnoreCase("sign_Off"))
			click(driver.findElement(sign_Off));
	}

	public void enterValue(String Locator, String Value) {
		if (Locator.equalsIgnoreCase("from_Account"))
			selectDropDown(driver.findElement(from_Account), Value);
		else if (Locator.equalsIgnoreCase("to_Account"))
			selectDropDown(driver.findElement(to_Account), Value);
		if (Locator.equalsIgnoreCase("amount"))
			type(driver.findElement(amount), Value);
	}
	
	public void verifyTransferFundLabel(String Locator) throws InterruptedException
	{	
		reportUtil.logInfo("OnlineBankingHomeActions_Validating Label of page " + Locator);
		WebElement trans_Label=	driver.findElement(transferFund_Label);
		//System.out.println("Page Title" + pageTitle);
		Assert.assertTrue(trans_Label.getText().contains(Locator));
	}

	public void verifyMessage(String from_Account, String to_Account, String amount) {
		String actualErrorMessage = verifyMessage(driver.findElement(transfer_Money_Message));
		System.out.println(actualErrorMessage + " --------------> actualErrorMessage");
		reportUtil.verifyContains(actualErrorMessage, from_Account,transfer_Money_Message,"From Account verification");
		reportUtil.verifyContains(actualErrorMessage, amount,transfer_Money_Message,"Amount verification");
		/*Assert.assertTrue(actualErrorMessage.contains(from_Account));
		Assert.assertTrue(actualErrorMessage.contains(to_Account));
		System.out.println("Amount " + amount);
		Assert.assertTrue(actualErrorMessage.contains(amount));*/
		
		
	}

	public void verifySignInLink(String Locator) {
		if (Locator.equalsIgnoreCase("sign_In"))
			isElementPresent(driver.findElement(sign_In_Link));
	}
}
