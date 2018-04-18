package com.app.web.pages.cucumber;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.sapient.taf.drivermanager.DriverManager;
import com.sapient.taf.framework.coreclasses.BaseClass;
import com.sapient.taf.framework.coreclasses.BaseClassCucumber;
import com.sapient.taf.framework.coreclasses.FrameworkConstants;
import com.sapient.taf.reporting.IReportComm;
import com.sapient.taf.reporting.ReportUtil;



public class CommonPage extends BaseClass{
	protected WebDriver driver;
    protected Wait<? extends WebDriver> wait;


    protected CommonPage() {

        this(DriverManager.getDriver().getWebDriver());

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }


    protected CommonPage(WebDriver driver) {


        this(driver, new WebDriverWait(driver, FrameworkConstants.maxWebPageOrWaitTime));

    }


    protected CommonPage(WebDriver driver, Wait<? extends WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

	
	
	protected void maximizePage() {
		this.driver.manage().window().maximize();
	}

	protected void openUrl(String url) {
		this.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		this.driver.get(url);
	}
	
	public void type(WebElement locator , String Value){
		locator.clear();
		locator.sendKeys(Value);
	}
	public void click(WebElement locator){
		locator.click();
	}
	public void isElementPresent(WebElement locator){
		Assert.assertTrue(locator.isDisplayed());
		//Assert.assertTrue("Element is not present", condition);
	}
	public String verifyMessage(WebElement locator){
		String Message = locator.getText();
		return Message;
	}	
	public String verifyPageTitle(){		
		String pageTitle = this.driver.getTitle();
		return pageTitle;
	}
	
	public String verifyCurrentURL(){		
		String currentUrl = this.driver.getCurrentUrl();
		return currentUrl;
	}
	public void selectDropDown(WebElement locator , String Accountnumber){		
		Select select = new Select(locator);
		select.selectByValue(Accountnumber);
	}
	
	public String gettext(WebElement locator){		
		String text = locator.getText();
		return text;
	}
	
	public Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
	}
}