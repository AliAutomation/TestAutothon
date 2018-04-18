package com.app.web.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import com.app.pages.LoginPage;
import com.app.pages.OnlineBankingHome;
import com.app.pages.TransferFunds;
import com.sapient.taf.framework.coreclasses.BaseClass;

public class TransferFundsTestNegative extends BaseClass {

	@Test
	public void transferFunds_Negative() throws IOException, InterruptedException {

		LoginPage loginPage = new LoginPage();
		loginPage.loadPage();

		loginPage.enterValue("userName");
		loginPage.enterValue("passWord");

		loginPage.clickSignInButton("signIn");

		OnlineBankingHome onlineBankingHome = new OnlineBankingHome();
		onlineBankingHome.clickButton("transferFundPage_link");
		

		TransferFunds transferFunds = new TransferFunds();
		transferFunds.verifyTransferFundLabel("Transfer Funds");
		transferFunds.enterValue("from_Account", inputData.getDirectProperty("from_Account"));
		transferFunds.enterValue("to_Account", inputData.getDirectProperty("to_Account"));
		transferFunds.enterValue("amount", inputData.getDirectProperty("amount"));
		transferFunds.clickOnTransferMoneyButton("transfer_Money");
		transferFunds.verifyMessage(inputData.getDirectProperty("from_Account"),
				inputData.getDirectProperty("to_Account"), "888999");
		
		reportUtil.checkForErrors();

	}
}
