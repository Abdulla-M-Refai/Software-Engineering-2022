import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import se.elib.Application;
import se.elib.Book;
import se.elib.User;

public class UnregisterUser 
{
	private Application app;
	private ErrorMessage errorMessage;
	private LogFile logFile;
	
	private User user;
	private Book book;
	private int oldSize;
	
	public UnregisterUser(Application app,ErrorMessage errorMessage,LogFile logFile)
	{
		this.app=app;
		this.errorMessage=errorMessage;
		this.logFile=logFile;
	}
	
	@Given("a user is registered with the Elibrary")
	public void a_user_is_registered_with_the_elibrary() 
	{
		user=new User(111,"jihad","jihad@gmail.com","rafidia street","HF99S","Nablus");
		app.login("adminadmin");
		app.registerUser(111,"jihad","jihad@gmail.com","rafidia street","HF99S","Nablus");
	}

	@When("the admin try to unregister that user")
	public void the_admin_try_to_unregister_that_user() 
	{
		oldSize=app.getUsers().size();
		
		try{app.unregisterUser(user);}
			catch(IllegalStateException|IllegalArgumentException ex) 
				{errorMessage.setException(ex); logFile.appendMessage(ex.getMessage());}
	}

	@Then("the user is out of library")
	public void the_user_is_out_of_library() 
	{
	    assertTrue(!app.getUsers().contains(user)&&(app.getUsers().size()==oldSize-1));
	}

	@Given("have some borrowed books")
	public void have_some_borrowed_books() 
	{
		book=new Book("Discrete","Ray","DIS22");
		app.addBook("Discrete","Ray","DIS22");
		app.borrowBook(user, book);
	}

	@Given("has fines to pay")
	public void has_fines_to_pay() 
	{
	    app.getUsers()
	       .get(app.getUsers().indexOf(user))
	       .addFine(30);
	}
}