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
      
Scenario: borrow an available book by a registered user
	Given that the book with ISBN:"Kent99" is available
	And a user with ID:2984754 is registered
	When the user borrows that book
	Then the book will be borrowed successfully
	And the book will not be available
	
Scenario: borrow an unavailable book by a registered user
	Given that the book with ISBN:"Zainpy55" is unavailable
	And a user with ID:2984754 is registered
	When the user borrows that book
	Then the error message "Book is not available" is given

Scenario: borrow an book by a non registered user
	Given that the book with ISBN:"Alu07" is available
	And a user with ID:123432 is not registered
	When the user borrows that book
	Then the error message "User not found" is given

Scenario: borrow an non existing book by registered user
	Given that the book with ISBN:"AAR32" is given
	And a user with ID:2984754 is registered
	When the user borrows that book
	Then the error message "Book not found" is given
	
Scenario: Borrow more than the maximum allowed by a registered user
	Given that a user with ID:2984755 is registered and borrowed these books with ISBNs:"Kent99" "Alu07" "Rose54" "Deitel4" "Duckket21"
	And an book with ISBN:"Zainpy55" is available
	When the user borrows that book
	Then the error message "you can't borrow more than five books" is given

Scenario: User cannot borrow books if he has late books 
	Given a book with code "xyz" is in the library 
	And a book with code "Beck99" is in the library 
	And a user is registered with the library 
	When the user borrows the book with code "Beck99" 
	And 21 days have passed 
	And the user borrows the book with code "xyz" 
	Then the book with code "xyz" is not borrowed by the user 
	And the error message "You can't borrow any new book because you have an overdue books" is given
	
Scenario: User cannot borrow books if he has fines
	Given a book with code "Beck99" is in the library 
	And a book with code "xyz" is in the library 
	And a user is registered with the library 
	When the user borrows the book with code "Beck99" 
	And 21 days have passed
	When the user returns the book with code "Beck99" 
	Then the user has to pay a fine of 30 NIS for that late book
	When the user borrows the book with code "xyz" 
	Then the book with code "xyz" is not borrowed by the user 
	And the error message "Can't borrow book you have fines" is given