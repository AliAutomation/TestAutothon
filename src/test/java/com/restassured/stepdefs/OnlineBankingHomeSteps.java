package com.restassured.stepdefs;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.app.web.pages.cucumber.OnlineBankingHomeActions;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class OnlineBankingHomeSteps {

	final static MyLogger logger = LoggerFactory
			.getLogger(OnlineBankingHomeSteps.class);
	OnlineBankingHomeActions onlineBankingHomeActions;
	
	public  OnlineBankingHomeSteps() throws IOException {
		// TODO Auto-generated constructor stub
		onlineBankingHomeActions=new OnlineBankingHomeActions();
	}
	
		
	@When("^User Selects \"([^\"]*)\" from the drop down$")
	public void user_Selects_from_the_drop_down(String Value) throws Throwable {
		Hooks.scenarioGlobal.write("User is on " + Value + " Page");
		onlineBankingHomeActions.selectAccount(Value);
	}
	
	@When("^User Clicks on \"([^\"]*)\" on AccountInformation Page$")
	public void user_Clicks_on_on_AccountInformation_Page(String Value) throws Throwable {
		Hooks.scenarioGlobal.write("User clicks on " + Value);
		onlineBankingHomeActions.clickButton(Value);
		
	}
	
	@Then("^Verify \"([^\"]*)\" is maching in the history$")
	public void verify_is_maching_in_the_history(String Value) throws Throwable {
		//SeleniumDriver.log.debug("Verify account " + Value + " is maching in the history");
		onlineBankingHomeActions.getAccountDetailText(Value);
	}
	
	
	
}
