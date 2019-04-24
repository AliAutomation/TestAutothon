package com.app.web.objectrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public interface MfsLoginRepo {
		By signIn= By.xpath("//span[contains(text(),'Search')]");
		By serachBox = By.xpath("//input[@id='flyoutSearch']");
}