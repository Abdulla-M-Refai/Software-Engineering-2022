Feature: Notify User 
Scenario: Send reminder email 
	Given that the admin is logged in 
	And there is a user with atleast one late book 
	When the admin sends a reminder email 
	Then then the user should receive an email with subject "Late book(s)" and body "You have n late book(s)"