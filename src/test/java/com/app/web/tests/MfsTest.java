package com.app.web.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import com.app.pages.LoginPage;
import com.app.pages.MfsLoginPage;
import com.app.pages.OnlineBankingHome;
import com.app.pages.TransferFunds;
import com.app.web.pages.cucumber.LoginPageActions;
import com.sapient.taf.framework.coreclasses.BaseClass;

public class MfsTest extends BaseClass {

	@Test
	public void search() throws IOException, InterruptedException {

		MfsLoginPage loginPage = new MfsLoginPage();
		loginPage.loadPage();

		loginPage.enterSearchValue();
	}

}
