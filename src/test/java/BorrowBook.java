import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BorrowBook 
{
	private Application app;
	private ErrorMessage errorMessage;
	private LogFile logFile;
	
	private User user;
	private Book book;
	private int oldSize;
	
	public BorrowBook(Application app,ErrorMessage errorMessage,LogFile logFile)
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
	
	@Given("these books in the system")
	public void these_books_in_the_system(io.cucumber.datatable.DataTable dataTable) 
	{
		for (int i=0; i < dataTable.height();i++) 
		{
			app.getBooks().add(new Book(dataTable.row(i).get(0),
										dataTable.row(i).get(1),
										dataTable.row(i).get(2),
										true));
		}
	}

	@Given("those users registered in the system")
	public void those_users_registered_in_the_system(io.cucumber.datatable.DataTable dataTable) 
	{
		for (int i=0; i < dataTable.height();i++)
		{
			app.getUsers().add(new User(Integer.parseInt(dataTable.row(i).get(0)),dataTable.row(i).get(1),
										dataTable.row(i).get(2),dataTable.row(i).get(3),dataTable.row(i).get(4),
										dataTable.row(i).get(5)));
		}
	}
	
	@Given("that the book with ISBN:{string} is available")
	public void that_the_book_with_isbn_is_available(String string) 
	{
		findBook(string,true);
	}

	@Given("a user with ID:{int} is registered")
	public void a_user_with_id_is_registered(Integer int1) 
	{
		findUser(int1);
	}

	@When("the user borrows that book")
	public void the_user_borrows_that_book() 
	{
		oldSize=-1;
		
		if(!(errorMessage.contains("User Not Found")||errorMessage.contains("Book Not Found")))
		{

			oldSize=app.getUsers()
					   .get(app.getUsers().indexOf(user))
					   .getBorrowedBooks()
					   .size();

			oldSize=app.getUsers().get(app.getUsers().indexOf(user)).getBorrowedBooks().size();

		    app.borrowBook(user,book);
		}
	}

	@Then("the book will be borrowed successfully")
	public void the_book_will_be_borrowed_successfully() 
	{
		boolean firstCondition  = app.getUsers()
				                     .get(app.getUsers().indexOf(user))
				                     .getBorrowedBooks()
				                     .contains(book);
		
		boolean secondCondition = app.getUsers()
				                     .get(app.getUsers().indexOf(user))
				                     .getBorrowedBooks()
				                     .size()==oldSize+1;
		
		assertTrue(firstCondition&&secondCondition);
	}

	@Then("the book will not be available")
	public void the_book_will_not_be_available() 
	{
	    assertFalse(app.getBooks()
	    		       .get(app.getBooks().indexOf(book))
	    		       .getAvailability());
	}

	@Given("that the book with ISBN:{string} is unavailable")
	public void that_the_book_with_isbn_is_unavailable(String string) 
	{
		findBook(string,false);
	}
	
	@Then("the book will not be borrowed")
	public void the_book_will_not_be_borrowed() 
	{	
		boolean firstCondition  = app.getUsers()
                				     .get(app.getUsers().indexOf(user))
                				     .getBorrowedBooks()
                				     .contains(book);
		
		boolean secondCondition = app.getUsers()
	                				 .get(app.getUsers().indexOf(user))
	                                 .getBorrowedBooks()
	                                 .size()==oldSize+1;
		
		assertFalse(firstCondition&&secondCondition);
	}
	
	@Given("a user with ID:{int} is not registered")
	public void a_user_with_id_is_not_registered(Integer int1) 
	{
		findUser(int1);
	}
	
	@Then("the error msg {string} will given")
	public void the_error_msg_will_given(String string) 
	{
		boolean result=(app.getUsers().indexOf(user)==-1)?false:app.getUsers()
	            												   .get(app.getUsers().indexOf(user))
	            												   .getBorrowedBooks()
	            												   .contains(book);
		
		assertFalse(result);
		if(!result) {logFile.appendMessage(string);}
	}
	
	@Given("that the book with ISBN:{string} is given")
	public void that_the_book_with_isbn_is_given(String string) 
	{
	    findBook(string,true);
	}

	@Then("the emsg {string} will given")
	public void the_emsg_will_given(String string) 
	{
		boolean result=(app.getUsers()
				           .get(app.getUsers().indexOf(user))
				           .getBorrowedBooks()
				           .indexOf(book)==-1)?false:app.getUsers()
   														.get(app.getUsers().indexOf(user))
														.getBorrowedBooks()
														.contains(book);

		assertFalse(result);
		if(!result) {logFile.appendMessage(string);}
	}
	
	@Given("that a user with ID:{int} is registered and borrowed these books with ISBNs:{string} {string} {string} {string} {string}")
	public void that_a_user_with_id_is_registered_and_borrowed_these_books_with_isb_ns(Integer int1, String string, String string2, String string3, String string4, String string5) 
	{
		findUser(int1);
		
		app.getBooks().stream().forEach(e->
	    {
	    	if(e.getIsbn().equals(string)  ||e.getIsbn().equals(string2)||
	    	   e.getIsbn().equals(string3) ||e.getIsbn().equals(string4)||
	    	   e.getIsbn().equals(string5))
	    	{
	    		e.setAvailability(false);
	    		app.getUsers()
	    		   .get(app.getUsers().indexOf(user))
	    		   .getBorrowedBooks()
	    		   .add(new Book(e.getName(),e.getAuthor(),e.getIsbn(),e.getAvailability()));
	    	}
	    });
		
		if(user.getBorrowedBooks().size()!=5) 
		{
			errorMessage.appendError("Book Not Found");
		}
		else
		{
			errorMessage.removeError("Book Not Found");
		}
	}

	@Given("an book with ISBN:{string} is available")
	public void an_book_with_isbn_is_available(String string) 
	{
		findBook(string,true);
	}

	@Then("the error with message {string} will given")
	public void the_error_with_message_will_given(String string) 
	{
	    boolean result=app.getUsers()
	    		   		  .get(app.getUsers().indexOf(user))
	    		          .getBorrowedBooks()
	    		          .size()==oldSize+1;
	    
	    assertFalse(result);
	    if(!result) {logFile.appendMessage(string);}
	}
}