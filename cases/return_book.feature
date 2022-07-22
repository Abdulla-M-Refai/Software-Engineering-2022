Feature: return a book
  Description: A user can return a book if its borrowed by that user
  Actors: user

Background: The library has a set of books and users
		Given these books in the system
      | XP Programming Book    | Kent Beck    | Kent99    |
			| C++ Development        | Alu and Sami | Alu07     |
			| Cucumber Java          | Seb Rose     | Rose54    |
    And those users registered in the system
    	| 2984754 | abdullah refai | abdullah@gmail.com  | Nablus street  | QY77SW | Nablus  |
			| 2984755 | sundos saifi   | sundos@gmail.com    | Tell Street    | A1TY25 | Tell    |
      
Scenario: return a book by a registered user[admin is logged in]
	Given that the book with ISBN:"Kent99" is not available
	And that user with ID:2984754 is registered and the book borrowed by him
	And that the admin is logged in
	When the user returns that book
	Then the book will be returned successfully
	And the book will be available again

Scenario: return a book by a registered user[admin is not logged in]
	Given that the book with ISBN:"Kent99" is not available
	And that user with ID:2984754 is registered and the book borrowed by him
	And that the admin is not logged in
	When the user returns that book
	Then the error message "Administrator login required" is given

Scenario: return a book by a non registered user[admin is logged in]
	Given that the book with ISBN:"Kent99" is not available
	And that user with ID:123123 is not registered
	And that the admin is logged in
	When the user returns that book
	Then the error message "User not found" is given

Scenario: return a non existing book by a user[admin is logged in]
	Given that the book with ISBN:"Aws211" is a non existing book
	And that user with ID:2984754 is a registered user
	And that the admin is logged in
	When the user returns that book
	Then the error message "Book not found" is given

Scenario: a user returns a book that is borrowed by another user[admin is logged in]
	Given that the book with ISBN:"Alu07" is not available and borrrwoed by a user with ID:2984755
	And a second user with ID:2984754 is registered
	And that the admin is logged in
	When the user returns that book
	Then the book will not be returned
	And the error message "this book is not borrowed by you" is given