package com.restassured.stepdefs;

import org.openqa.selenium.WebDriver;

import com.app.web.pages.cucumber.LoginPageActions;
import com.app.web.pages.cucumber.OnlineBankingHomeActions;
import com.app.web.pages.cucumber.TransferFundsActions;
import com.sapient.taf.log.LoggerFactory;
import com.sapient.taf.log.MyLogger;
import com.sapient.taf.reporting.ReportTestManager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class LoginSteps {
	/**
	 * The Logger logger
	 */
	final static MyLogger logger = LoggerFactory
			.getLogger(LoginSteps.class);
	LoginPageActions loginPage ;
	OnlineBankingHomeActions onlineBankingHomepage;
	TransferFundsActions transferFundActions;
	@Given("^User is on the Test Site \"([^\"]*)\"$")
	public void user_is_on_the_Test_Site(String Value) throws Throwable {
	 loginPage = new LoginPageActions();
	 loginPage.loadPage();
	}
	@When("^User Enters valid \"([^\"]*)\"$")
	public void user_Enters_valid_credentials(String Value) throws Throwable {
		Hooks.scenarioGlobal.write("Entering " + Value);
		loginPage.enterValue(Value);
	}
	
	@When("^User Clicks on \"([^\"]*)\" button$")
	public void user_Clicks_on_button(String Value) throws Throwable {
		Hooks.scenarioGlobal.write("Clicking on button " + Value);
		loginPage.clickSignInButton(Value);
		
	}
	
	@Then("^\"([^\"]*)\" link should be displayed$")
	public void link_should_be_displayed(String Value) throws Throwable {
		Hooks.scenarioGlobal.write("Verify link " + Value + " is present or not");
		loginPage.isSignOffLinkPresent(Value);
	}
	
	@Then("^Verify \"([^\"]*)\" message \"([^\"]*)\"$")
	public void verify_message(String Value , String ActualErrMessage) throws Throwable {
		Hooks.scenarioGlobal.write("Verify " + Value + " message" + ActualErrMessage);
		loginPage.verifyErrorMessage(Value, ActualErrMessage);
	}


}
