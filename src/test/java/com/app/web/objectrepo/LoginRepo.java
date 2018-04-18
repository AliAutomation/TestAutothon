package com.app.web.objectrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public interface LoginRepo {
		// Login page Xpath
		By signIn= By.xpath("//input[@name='btnSubmit']");
		By userName= By.xpath("//input[@id='uid']");
		By password =By.xpath("//input[@id='passw']");
		By sign_Off=By.xpath("//a[@href='logout.aspx']");
		By login_Fail_Error=By.xpath("//span[@id='_ctl0__ctl0_Content_Main_message']");
	
		By online_Banking_Login_link=By.xpath("//a[@id='_ctl0__ctl0_Content_AccountLink']'");
		
}