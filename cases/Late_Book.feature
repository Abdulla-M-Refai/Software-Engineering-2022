Feature: Late Book
	Scenario: Late book after 21 days
	Given the user has borrowed a book
	And 21 days have passed
	And the fine for one late book is 30 NIS
	Then the user has Late books
	And the user has to pay a fine of 30 NIS