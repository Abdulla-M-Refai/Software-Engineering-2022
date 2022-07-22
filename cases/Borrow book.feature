Feature: borrow a book
  Description: A user can borrow a book if its available and the user is registered in the system
  Actors: user

Background: The library has a set of books and users
		Given these books in the system
      | XP Programming Book    | Kent Beck    | Kent99    |
			| C++ Development        | Alu and Sami | Alu07     |
			| Cucumber Java          | Seb Rose     | Rose54    |
			| programming C++        | Deitel       | Deitel4   |
			| programming javascript | John         | Duckket21 |
			| programming python     | Zain         | Zainpy55  |
    And those users registered in the system
    	| 2984754 | abdullah refai | abdullah@gmail.com  | Nablus street  | QY77SW | Nablus  |
			| 2984755 | sundos saifi   | sundos@gmail.com    | Tell Street    | A1TY25 | Tell    |
      
Scenario: borrow an available book by a registered user[admin is logged in]
	Given that the book with ISBN:"Kent99" is available
	And that the admin is logged in
	And a user with ID:2984754 is registered
	When the user borrows that book
	Then the book will be borrowed successfully
	And the book will not be available

Scenario: Borrow a Book[admin is not logged in]
	Given that the book with ISBN:"Kent99" is available
	And a user with ID:2984754 is registered
	And that the admin is not logged in
	When the user borrows that book
	Then the error message "Administrator login required" is given
	
Scenario: borrow an unavailable book by a registered user[admin is logged in]
	Given that the book with ISBN:"Zainpy55" is unavailable
	And a user with ID:2984754 is registered
	And that the admin is logged in
	When the user borrows that book
	Then the error message "Book is not available" is given

Scenario: borrow an book by a non registered user[admin is logged in]
	Given that the book with ISBN:"Alu07" is available
	And a user with ID:123432 is not registered
	And that the admin is logged in
	When the user borrows that book
	Then the error message "User not found" is given

Scenario: borrow an non existing book by registered user[admin is logged in]
	Given that the book with ISBN:"AAR32" is given
	And a user with ID:2984754 is registered
	And that the admin is logged in
	When the user borrows that book
	Then the error message "Book not found" is given
	
Scenario: Borrow more than the maximum allowed by a registered user[admin is logged in]
	Given that a user with ID:2984755 is registered and borrowed these books with ISBNs:"Kent99" "Alu07" "Rose54" "Deitel4" "Duckket21"
	And an book with ISBN:"Zainpy55" is available
	And that the admin is logged in
	When the user borrows that book
	Then the error message "you can't borrow more than five books" is given