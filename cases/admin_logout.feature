Feature: Admin logout
	Description: The admin logs out from the e-library 
	Actor: Admin
	
Scenario: Admin logs out
	Given that the admin is logged in
	When the admin logs out
	Then the admin is not logged in