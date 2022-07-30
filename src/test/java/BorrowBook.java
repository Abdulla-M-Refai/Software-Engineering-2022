import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import se.elib.Application;
import se.elib.Book;
import se.elib.User;

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
	    app.getBooks().stream().forEach(e->
	    {
	    	if(e.getIsbn().equals(string))
	    	{
	    		e.setAvailability(available);
	    		book=new Book(e.getName(),e.getAuthor(),e.getIsbn());
	    		book.setAvailability(available);
	    	}
	    });
	}
	
	private void findUser(Integer int1)
	{
		app.getUsers().stream().forEach(e->
	    {
	    	if(e.getId()==int1)
	    	{
	    		user=new User(e.getId(),e.getName(),e.getEmail(),e.getAddress(),e.getPostCode(),e.getCity());
	    	}
	    });
	}
	
	private void calcOldSize() 
	{
		oldSize=(user!=null&&book!=null)?app.getUsers()
	                                        .get(app.getUsers().indexOf(user))
	                                        .getBorrowedBooks()
	                                        .size()            
	                                    :-1;
	}
	
	@Given("these books in the system")
	public void these_books_in_the_system(io.cucumber.datatable.DataTable dataTable) 
	{
		for (int i=0; i < dataTable.height();i++) 
		{
			app.getBooks().add(new Book(dataTable.row(i).get(0),
										dataTable.row(i).get(1),
										dataTable.row(i).get(2)));
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
		calcOldSize();

		try{app.borrowBook(user,book);}
			catch(IllegalStateException|IllegalArgumentException ex) 
				{errorMessage.setException(ex); logFile.appendMessage(ex.getMessage());}
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
	
	@Given("a user with ID:{int} is not registered")
	public void a_user_with_id_is_not_registered(Integer int1) 
	{
		findUser(int1);
	}
	
	@Given("that the book with ISBN:{string} is given")
	public void that_the_book_with_isbn_is_given(String string) 
	{
		findBook(string,true);
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
	    		   .add(e);
	    	}
	    });
	}
	
	@Given("an book with ISBN:{string} is available")
	public void an_book_with_isbn_is_available(String string) 
	{
		findBook(string,true);
	}
	
	@Given("a book with code {string} is in the library")
	public void a_book_with_code_is_in_the_library(String string) 
	{
		book=new Book("DataBase","Ali",string);
		app.login("adminadmin");
		app.addBook("DataBase","Ali",string);
	}
	
	@Given("a user is registered with the library")
	public void a_user_is_registered_with_the_library() 
	{
		user=new User(120,"ahmad","ahmad11@gmail.com","rafidia street","HF99S","Nablus");
		app.login("adminadmin");
		app.registerUser(120,"ahmad","ahmad11@gmail.com","rafidia street","HF99S","Nablus");
	}
	
	@When("the user borrows the book with code {string}")
	public void the_user_borrows_the_book_with_code(String string) 
	{
		calcOldSize();
		
		try{app.checkOverDueBooks(); app.borrowBook(user,book);}
			catch(IllegalStateException|IllegalArgumentException ex) 
				{errorMessage.setException(ex); logFile.appendMessage(ex.getMessage());}
	}
	
	@Then("the book with code {string} is not borrowed by the user")
	public void the_book_with_code_is_not_borrowed_by_the_user(String string) 
	{
		assertEquals(0,app.getUsers()
			               .get(app.getUsers().indexOf(user))
			               .getBorrowedBooks()
			               .stream()
			               .filter(e->e.getIsbn().equals(string))
			               .toList()
			               .size());
	}
	
	@Then("the user has to pay a fine of {int} NIS for that late book")
	public void the_user_has_to_pay_a_fine_of_nis_for_that_late_book(Integer int1)
	{
		assertEquals(int1.intValue(),app.getUsers().get(app.getUsers().indexOf(user)).getTotalFines());
	}
	
	@When("the user returns the book with code {string}")
	public void the_user_returns_the_book_with_code(String string) 
	{
		calcOldSize();

		try {app.checkOverDueBooks(); app.returnBook(user,book);}
			catch(IllegalStateException|IllegalArgumentException ex) 
				{errorMessage.setException(ex); logFile.appendMessage(ex.getMessage());}
	}
}