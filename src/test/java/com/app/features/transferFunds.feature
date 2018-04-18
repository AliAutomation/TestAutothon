Feature: Test the functionality of the Transfer Funds page
	In order to validate that
	Transfer Funds page is working fine
	User should is able to login with valid credentials
	

	
		
Background:	User is on the Transfer Funds Page
		Given 	User is on the Test Site "url"
		When    User Enters valid "userName"
		And  	User Enters valid "passWord"
		And  	User Clicks on "signIn" button
		And  	User Clicks on "transferFundPage_link" on AccountInformation Page
		Then    User is on "Transfer Funds" Page
		
		

@Positive
Scenario: Verify Fund is transferred successfully  when user selects
  From Account, To Account and Amount to Transfer and click on Transfer Money button  
		Given User is on "Transfer Funds" Page
		When  User Selects from_Account,to_Account and enters amount
		|from_Account|to_Account|amount|
		|800002|800003|1000|
		And   User Clicks on "transfer_Money"
		Then  Verify transfer_Money_Message
		|from_Account|to_Account|amount|
		|800002|800003|1000|
		
@Negative
Scenario: Verify message when user clicks sign_Off then user will be able to logOff from the application properly and Close browser
		Given User is on "Transfer Funds" Page
		When  User Clicks on "sign_Off"
		Then  User is on SignOff Page

		
		

	