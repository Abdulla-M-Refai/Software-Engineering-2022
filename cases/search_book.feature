Feature: Searching for books based on title,author or code
	Description: A user or admin search for books by the title, author, or code
	Actors: user

Background: The library has a set of books 
	Given that the admin is logged in 
	And these books are contained in the library 
		| XP Programming Book | Kent Beck    | Kent99  |
		| C++ Development     | Alu and Sami | Alu07   |
		| Cucumber Java       | Seb Rose     | Rose54  |
		| programming C++     | Deitel       | Deitel4 |
	And the admin logs out 
	
Scenario: Searching for a book by the code 
	Given that the admin is not logged in 
	When the user searches for the text "99" 
	Then the book with code "Kent99" is found 
	
Scenario: Searching for a book by the title 
	Given that the admin is not logged in 
	When the user searches for the text "XP" 
	Then the book with code "Kent99" is found
	
Scenario: Searching for a book by the author 
	Given that the admin is not logged in 
	When the user searches for the text "Seb" 
	Then the book with code "Rose54" is found
	
Scenario: Searching when the adminis logged in 
	Given that the admin is logged in
	When the user searches for the text "Alu" 
	Then the book with code "Alu07" is found
	
Scenario: No books found
	When the user searches for the text "Sofa" 
	Then no books are found 
	
Scenario: Find more than one book 
	When the user searches for the text "C++" 
	Then the books with code "Alu07" and "Deitel4" are found
	
	