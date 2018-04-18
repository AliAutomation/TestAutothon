package com.app.pages;


import com.app.web.objectrepo.HerokuAppLoginPageRepo;
import com.app.web.pages.cucumber.CommonPage;

public class HerokuAppLoginPage extends CommonPage implements HerokuAppLoginPageRepo{

	
	public void enterUsername(String username) {
		driver.findElement(usernameField).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}
	
	public void clickLogin() {
		
		driver.findElement(loginButton).click();
		
	}
	
	public void launchApp(String url) {
		driver.get(url);
	}
	public void loginIntoApp(String username,String password) {
		
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}
}
