package com.app.pages;


import java.io.IOException;

import org.junit.Assert;

import com.app.web.objectrepo.OnlineBankingHomeRepo;
import com.app.web.pages.cucumber.CommonPage;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;






public class OnlineBankingHome extends CommonPage implements OnlineBankingHomeRepo {

	final static MyLogger logger = LoggerFactory
			.getLogger(OnlineBankingHome.class);
	


	public void clickButton(String Locator) throws InterruptedException
	{
		reportUtil.logInfo("OnlineBankingHomeActions_Click button " + Locator);
		if (Locator.equalsIgnoreCase("go_Button"))
			click(driver.findElement(go_Button));
		if (Locator.equalsIgnoreCase("transferFundPage_link"))
			click(driver.findElement(transferFundPage_link));
	}
	
	public void selectAccount(String Value)
	{
		reportUtil.logInfo("OnlineBankingHomeActions_Selecting account with value " + Value);
		selectDropDown(driver.findElement(my_Account_Details_List), Value);
	}

	public void validePageTitle(String Locator) throws InterruptedException
	{	
		reportUtil.logInfo("OnlineBankingHomeActions_Validating Current URL of page " + Locator);
			String pageTitle = verifyCurrentURL();
		//System.out.println("Page Title" + pageTitle);
		Assert.assertTrue(pageTitle.contains(Locator));
	}
	
	
	
	public void getAccountDetailText(String Locator)
	{
		reportUtil.logInfo("OnlineBankingHomeActions_Getting account details for " + Locator +" Locator ");
		String text = gettext(driver.findElement(account_history));
		Assert.assertTrue(text.contains(Locator));
	}

}
