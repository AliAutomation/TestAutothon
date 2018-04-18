package com.app.web.tests;

import org.testng.annotations.Test;

import com.app.pages.HerokuAppDashboardPage;
import com.app.pages.HerokuAppLoginPage;
import com.sapient.taf.framework.coreclasses.BaseClass;


public class VerifyHerokuLogin extends BaseClass {
	
	@Test
	public void verifyLogin() {
		
		
		HerokuAppLoginPage herokuAppLoginPage=new HerokuAppLoginPage();
		herokuAppLoginPage.launchApp(inputData.getDirectProperty("urlHeroku"));
		herokuAppLoginPage.loginIntoApp(inputData.getDirectProperty("usernameHeroku"), inputData.getDirectProperty("passwordHeroku"));
	
		
		HerokuAppDashboardPage herokuAppDashboardPage=new HerokuAppDashboardPage();
		herokuAppDashboardPage.verifyLoginSuccess("logged into a secure area12");
		
	}

}
