Feature: Register User
	Description: The admin registers a user in the system
	Additional Info: Users should have an ID, name, email, address, postal code and city
	Actor: Admin

Background: The system has a set of users
	Given that the admin is logged in 
	And those users are registered in the system 
		| 2984754 | abdullah refai | abdullah@gmail.com  | Nablus street | QY77SW | Nablus |
		| 2984755 | sundos saifi | sundos@gmail.com  | Tell Street | A1TY25 | Tell |
		| 2984766 | yaqeen yaseen | yaqeen@gmail.com  | Aseerah street | YZ21T3 | Aseerah |
	And the admin logs out 
	
Scenario: admin is logged in
	Given that the admin is logged in
	And there is a user with ID:2984744 Name:"Ahmad Ali" Email:"ahm@gmail.com" Address:"Nablus street" Postal Code:"H3H1T5" City:"Nablus"
	When the user is registered
	Then the user with ID:2984744 Name:"Ahmad Ali" Email:"ahm@gmail.com" Address:"Nablus street" Postal Code:"H3H1T5" City:"Nablus" registered successfully
	
Scenario: admin is logged in
	Given that the admin is logged in
	And there is a user with ID:2984766 Name:"yaqeen yaseen" Email:"yaqeen@gmail.com" Address:"Aseerah street" Postal Code:"YZ21T3" City:"Aseerah"
	When the user is registered
	Then the error "This user is already registered" is given
	
Scenario: admin is not logged in
	Given that the admin is not logged in
	And there is a user with ID:2984777 Name:"mohammed Ali" Email:"moh@gmail.com" Address:"Rojeeb street" Postal Code:"HGH12F" City:"Rojeeb"
	When the user is registered
	Then the error "Admin login is required" is given