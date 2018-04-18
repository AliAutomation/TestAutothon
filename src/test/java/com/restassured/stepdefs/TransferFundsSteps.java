package com.restassured.stepdefs;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.app.web.pages.cucumber.OnlineBankingHomeActions;
import com.app.web.pages.cucumber.TransferFundsActions;
import com.sapient.taf.drivermanager.DriverManager;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.Utils;


public class TransferFundsSteps {
	
	final static MyLogger logger = LoggerFactory
			.getLogger(TransferFundsSteps.class);


	
	private static final OutputType BYTES = null;
	
	
	TransferFundsActions transferFundsActions;
	public  TransferFundsSteps() throws IOException {
		// TODO Auto-generated constructor stub
		 transferFundsActions = new TransferFundsActions();
		
	}
	@When("^User Selects from_Account,to_Account and enters amount$")
	public void user_Selects_from_Account_to_Account_and_enters_amount(DataTable table) throws Throwable {
		 List<Map<String , String>> data= table.asMaps(String.class, String.class);
		 for(Map<String, String> Value: data){
			 transferFundsActions.enterValue("from_Account", Value.get("from_Account"));
			 transferFundsActions.enterValue("to_Account", Value.get("to_Account"));
			 transferFundsActions.enterValue("amount", Value.get("amount"));
		 }
	}
	@When("^User Clicks on \"([^\"]*)\"$")
	public void user_Clicks_on(String Value) throws Throwable {
		transferFundsActions.clickOnTransferMoneyButton(Value);
	}
	
	@Given("^User is on \"([^\"]*)\" Page$")
	public void user_is_on_the_Page(String Value) throws Throwable {
		
		logger.info("User is on " + Value + " Page");
		transferFundsActions.verifyTransferFundLabel(Value);
	}
	
	@Given("^User is on SignOff Page$")
	public void user_is_on_the_Page() throws Throwable {
		
		
		transferFundsActions.verifySignOffLabel();
	}
	
	@Then("^Verify transfer_Money_Message$")
	public void verify_transfer_Money_Message(DataTable table) throws Throwable {
	
		List<Map<String , String>> data= table.asMaps(String.class, String.class);
		 for(int i = 0;i<=data.size() - 1;i++){
			 transferFundsActions.verifyMessage(data.get(i).get("from_Account"), data.get(i).get("to_Account"), data.get(i).get("amount"));
		 }
	}
	
	@Then("^Verify \"([^\"]*)\" link is displayed$")
	public void verify_link_is_displayed(String locator) throws Throwable {
		transferFundsActions.verifySignInLink(locator);
	}

	
	/*@Before
	public static void start(Scenario scenario) {
		
	}
	@After
	public static void end(Scenario scenario) {
		if (!scenario.isFailed()) {
            return;
        }

		WebDriver driver=DriverManager.getDriver().getWebDriver();
        scenario.write("url: " + driver.getCurrentUrl());

        if (driver instanceof TakesScreenshot) {
            TakesScreenshot camera = (TakesScreenshot) driver;
            byte[] screenshot =  camera.getScreenshotAs(OutputType.BYTES);
      
        
            scenario.embed(screenshot, "image/png");
            
         
        }

      //  scenario.write(Utils.htmlEscape(driver.getPageSource()));
		
	}*/
	
}
