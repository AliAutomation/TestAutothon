Feature: Test the functionality of the Online Banking Home
	In order to validate that
	login as a Valid user and Select bank account in View Account Details dropdown and click on GO button 
	and then Verify account number on Account Information page
	and also Verify the current date displayed in Balance Detail table
	and when user clicks on transferFundPage_link then user navigates to Transfer Funds Page

#Background: User is on the Test Site
#		Given 	User is on the Test Site "url"
#		Given 	User Enters valid "userName"
#		Given 	User Enters valid "passWord"
#		Given 	User Clicks on "signIn" button
		
@Positive
Scenario Outline:Verify the Account number on Account information page When User Selects bank account in View Account Details dropdown  
		Given 	User is on the Test Site "url"
		When    User Enters valid "userName"
		And  	User Enters valid "passWord"
		And  	User Clicks on "signIn" button
		When  User Selects "<bank_account>" from the drop down
		And   User Clicks on "Go_button" on AccountInformation Page
		Then Verify "<bank_account>" is maching in the history
	Examples:
	|bank_account|
	|800002|
	|800003|
	
