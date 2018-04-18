package com.app.web.objectrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public interface TransferFundsRepo {
	// Transfer fund page Xpath
	By from_Account= By.xpath("//select[@id='fromAccount']");
	By to_Account=By.xpath("//select[@id='toAccount']");
	By amount=By.xpath("//input[@id='transferAmount']");
	By transfer_Money_button=By.xpath("//input[@id='transfer']");
	By transfer_Money_Message=By.xpath("//span[@id='_ctl0__ctl0_Content_Main_postResp']/span");
	By sign_Off=By.xpath("//a//font[contains(text(),'Sign Off')]");
	By sign_In_Link=By.xpath("//a[@id='_ctl0__ctl0_LoginLink']");
	By transferFund_Label=By.xpath("//form[@id='tForm']//h1");
}
