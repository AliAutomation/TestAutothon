package com.app.web.objectrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public interface OnlineBankingHomeRepo {
	//Online Banking Home Xpath
	By my_Account_Details_List= By.xpath("//select[@id='listAccounts']");
	By go_Button=By.xpath("//input[@id='btnGetAccount']");
	By account_history=By.xpath("//h1");
	By currentDate=By.xpath("//td/table//tr[3]/td[1]");
	By transferFundPage_link=By.xpath("//a[text()='Transfer Funds']");
	
	

}
