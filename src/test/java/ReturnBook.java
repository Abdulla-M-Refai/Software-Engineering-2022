import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReturnBook 
{
	private Application app;
	private ErrorMessage errorMessage;
	private LogFile logFile;
	
	private User user;
	private Book book;
	private int oldSize;
	
	public ReturnBook(Application app,ErrorMessage errorMessage,LogFile logFile)
	{
		this.app=app;
		this.errorMessage=errorMessage;
		this.logFile=logFile;
	}
	
	private void findBook(String string,boolean available)
	{
		boolean []flag= {false};
		
	    app.getBooks().stream().forEach(e->
	    {
	    	if(e.getIsbn().equals(string))
	    	{
	    		e.setAvailability(available);
	    		book=new Book(e.getName(),e.getAuthor(),e.getIsbn(),available);
	    		errorMessage.removeError("Book Not Found");
	    		flag[0]=true;
	    	}
	    });
	    
	    if(!flag[0]) {errorMessage.appendError("Book Not Found");}
	}
	
	private void findUser(Integer int1)
	{
		boolean []flag= {false};
		
		app.getUsers().stream().forEach(e->
	    {
	    	if(e.getId()==int1)
	    	{
	    		user=new User(e.getId(),e.getName(),e.getEmail(),e.getAddress(),e.getPostCode(),e.getCity());
	    		errorMessage.removeError("User Not Found");
	    		flag[0]=true;
	    	}
	    });
		
		if(!flag[0]) {errorMessage.appendError("User Not Found");}
	}
	
	private void checkIfBookIsBorrowed()
	{
		if(user!=null&&book!=null)
		{
			if(app.getUsers()
					  .get(app.getUsers().indexOf(user))
					  .getBorrowedBooks()
					  .contains(book))
			{
				errorMessage.removeError("this book is not borrowed by you");
			}
			else
			{
				errorMessage.appendError("this book is not borrowed by you");
			}
		}
	}
	
	@Given("that the book with ISBN:{string} is not available")
	public void that_the_book_with_isbn_is_not_available(String string) 
	{
	    findBook(string,false);
	}

	@Given("that user with ID:{int} is registered and the book borrowed by him")
	public void that_user_with_id_is_registered_and_the_book_borrowed_by_him(Integer int1) 
	{
		user=null;
	    findUser(int1);
	    app.getUsers()
	       .get(app.getUsers().indexOf(user))
	       .getBorrowedBooks()
	       .add(book);
	    checkIfBookIsBorrowed();
	}

	@When("the user returns that book")
	public void the_user_returns_that_book() 
	{
		oldSize=-1;
		
		if(!(errorMessage.contains("User Not Found")||
			 errorMessage.contains("Book Not Found")||
			 errorMessage.contains("this book is not borrowed by you")))
		{
			oldSize=app.getUsers()
					   .get(app.getUsers().indexOf(user))
					   .getBorrowedBooks()
					   .size();
			
			app.returnBook(user,book);
		}
	}

	@Then("the book will be returned successfully")
	public void the_book_will_be_returned_successfully() 
	{
		boolean firstCondition=app.getUsers()
				                  .get(app.getUsers().indexOf(user))
				                  .getBorrowedBooks()
				                  .size()==oldSize-1;
		
		boolean secondCondition=app.getUsers()
                				   .get(app.getUsers().indexOf(user))
                				   .getBorrowedBooks()
                				   .contains(book);
				
		assertTrue(firstCondition && !secondCondition);
	}

	@Then("the book will be available again")
	public void the_book_will_be_available_again() 
	{
	    assertTrue(app.getBooks()
	    		      .get(app.getBooks().indexOf(book))
	    		      .getAvailability());
	}
	
	@Given("that user with ID:{int} is not registered")
	public void that_user_with_id_is_not_registered(Integer int1) 
	{
		user=null;
	    findUser(int1);
	    
	    if(user!=null)
	    {
	    	user.getBorrowedBooks().add(book);
	    }
	    
	    checkIfBookIsBorrowed();
	}

	@Then("the em {string} will given")
	public void the_em_will_given(String string) 
	{
		boolean result=app.getUsers().contains(user);
		assertFalse(result);
		if(!result) {logFile.appendMessage(string);}
	}
	
	@Given("that the book with ISBN:{string} is a non existing book")
	public void that_the_book_with_isbn_is_a_non_existing_book(String string) 
	{
	    book=null;
	    findBook(string,false);
	}

	@Given("that user with ID:{int} is a registered user")
	public void that_user_with_id_is_a_registered_user(Integer int1) 
	{
	    findUser(int1);
	    
	    if(book!=null)
	    {
	    	user.getBorrowedBooks().add(book);
	    }
	    
	    checkIfBookIsBorrowed();
	}

	@Then("the error-message {string} will given")
	public void the_error_message_will_given(String string) 
	{
		boolean result=app.getBooks().contains(book);
	    assertFalse(result);
	    if(!result) {logFile.appendMessage(string);}
	}
	
	@Given("that the book with ISBN:{string} is not available and borrrwoed by a user with ID:{int}")
	public void that_the_book_with_isbn_is_not_available_and_borrrwoed_by_a_user_with_id(String string, Integer int1) 
	{
	    findBook(string,false);
	    findUser(int1);
	    user.getBorrowedBooks().add(book);
	}
	
	@Given("a second user with ID:{int} is registered")
	public void a_second_user_with_id_is_registered(Integer int1) 
	{
	    findUser(int1);
	    checkIfBookIsBorrowed();
	}
	
	@Then("the book will not be returned")
	public void the_book_will_not_be_returned() 
	{
		boolean firstCondition=app.getUsers()
                				  .get(app.getUsers().indexOf(user))
                				  .getBorrowedBooks()
                				  .size()==oldSize-1;

		boolean secondCondition=app.getUsers()
						   		   .get(app.getUsers().indexOf(user))
						   		   .getBorrowedBooks()
						   		   .contains(book);

		assertFalse(firstCondition && secondCondition);
	}

	@Then("the emessage {string} will given")
	public void the_emessage_will_given(String string) 
	{
		boolean result=app.getBooks()
				          .get(app.getBooks().indexOf(book))
				          .getAvailability();
		
	    assertFalse(result);
	    if(!result) {logFile.appendMessage(string);}
	}
}