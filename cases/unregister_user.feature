Feature: unregister user
  Description: admin can unregister user if they dont have any borrowed books or fines
  Actors: admin
  
Scenario: Unregister a user 
	Given a user is registered with the Elibrary 
	And that the admin is logged in 
	When the admin try to unregister that user 
	Then the user is out of library

Scenario: Unregister a user when admin is not loggen in
	Given a user is registered with the Elibrary 
	And that the admin is not logged in 
	When the admin try to unregister that user 
	Then the error message "Administrator login required" is given

Scenario: Unregister a user with borrowed books
	Given a user is registered with the Elibrary
	And have some borrowed books
	And that the admin is logged in 
	When the admin try to unregister that user 
	Then the error message "you can't unregister this user because he have some borrowed books to return" is given
	
Scenario: Unregister a user that have a fines
	Given a user is registered with the Elibrary
	And has fines to pay
	And that the admin is logged in 
	When the admin try to unregister that user 
	Then the error message "you can't unregister this user because he has fines to pay" is given