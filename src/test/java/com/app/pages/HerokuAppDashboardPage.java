package com.app.pages;

import org.openqa.selenium.WebElement;

import com.app.web.objectrepo.HerokuAppDashboardPageRepo;
import com.app.web.pages.cucumber.CommonPage;

public class HerokuAppDashboardPage extends CommonPage implements HerokuAppDashboardPageRepo {

	
	public void verifyLoginSuccess(String expectedMessage) {
		WebElement loginLabelElement=driver.findElement(loginLabel);
		
		reportUtil.verifyContains(loginLabelElement.getText(), expectedMessage, loginLabel, "Login Label");
	/*	if(loginLabelElement.getText().contains("logged into a secure area")) {
			reportUtil.logPass("Logged into Heroku App successfully");
		}
		else {
			reportUtil.logFail("Login Failed for Heroku App");
		}
	}*/
}
	
}
