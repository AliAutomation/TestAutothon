package com.app.web.pages.cucumber;


import java.io.IOException;

import org.junit.Assert;

import com.app.web.objectrepo.OnlineBankingHomeRepo;
import com.restassured.stepdefs.Hooks;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;






public class OnlineBankingHomeActions extends CommonPage implements OnlineBankingHomeRepo {

	final static MyLogger logger = LoggerFactory
			.getLogger(OnlineBankingHomeActions.class);
	


	public void clickButton(String Locator) throws InterruptedException
	{
		Hooks.scenarioGlobal.write("OnlineBankingHomeActions_Click button " + Locator);
		if (Locator.equalsIgnoreCase("go_Button"))
			click(driver.findElement(go_Button));
		if (Locator.equalsIgnoreCase("transferFundPage_link"))
			click(driver.findElement(transferFundPage_link));
	}
	
	public void selectAccount(String Value)
	{
		Hooks.scenarioGlobal.write("OnlineBankingHomeActions_Selecting account with value " + Value);
		selectDropDown(driver.findElement(my_Account_Details_List), Value);
	}

	public void validePageTitle(String Locator) throws InterruptedException
	{	
		Hooks.scenarioGlobal.write("OnlineBankingHomeActions_Validating page title of page " + Locator);
			String pageTitle = verifyPageTitle();
		System.out.println("Page Title" + pageTitle);
		Assert.assertTrue(pageTitle.contains(Locator));
	}
	

	
	public void getAccountDetailText(String Locator)
	{
		Hooks.scenarioGlobal.write("OnlineBankingHomeActions_Getting account details for " + Locator +" Locator ");
		String text = gettext(driver.findElement(account_history));
		Assert.assertTrue(text.contains(Locator));
	}

}
