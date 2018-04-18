package com.app.web.objectrepo;

import org.openqa.selenium.By;

public interface HerokuAppLoginPageRepo {


	By usernameField=By.id("username");
	
	By passwordField=By.id("password");
	
	By loginButton=By.xpath("//button//i[contains(text(),'Login')]");
	
	
}
