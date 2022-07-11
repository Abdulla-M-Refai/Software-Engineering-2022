Feature: Add Book to an e-library
	Description:add an book to the library
	Actors: Admin

Scenario: Add a book successfully
 	Given that the administrator is logged in
 	And there is a book with title "Head First Java", author "Ali Ahmad", and signature "Ali99"
 	When the book is added to the library
 	Then the book with title "Head First Java", author "Ali Ahmad", and signature "Ali99" is contained in the library
 	
Scenario: Add a book when the adminstrator is not logged in
	Given that the administrator is not logged in
 	And there is a book with title "Extreme Programming", author "Kent Beck", and signature "Beck99"
	When the book is added to the library
	Then the error message "Administrator login required" is given