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
      
Scenario: return a book by a registered user
	Given that the book with ISBN:"Kent99" is not available
	And that user with ID:2984754 is registered and the book borrowed by him
	When the user returns that book
	Then the book will be returned successfully
	And the book will be available again

Scenario: return a book by a non registered user
	Given that the book with ISBN:"Kent99" is not available
	And that user with ID:123123 is not registered
	When the user returns that book
	Then the em "User not found" will given

Scenario: return a non existing book by a user
	Given that the book with ISBN:"Aws211" is a non existing book
	And that user with ID:2984754 is a registered user
	When the user returns that book
	Then the error-message "Book not found" will given

Scenario: a user returns a book that is borrowed by another user
	Given that the book with ISBN:"Alu07" is not available and borrrwoed by a user with ID:2984755
	And a second user with ID:2984754 is registered
	When the user returns that book
	Then the book will not be returned
	And the emessage "this book is not borrowed by you" will given