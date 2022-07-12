import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddBook 
{
	private Application app;
	private ErrorMessage errorMessage;
	private LogFile logFile;
	
	private Book book;
	private int oldSize;
	
	public AddBook(Application app,ErrorMessage errorMessage,LogFile logFile)
	{
		this.app=app;
		this.errorMessage=errorMessage;
		this.logFile=logFile;
	}
	
	@Given("that the administrator is logged in")
	public void that_the_administrator_is_logged_in() 
	{
	   app.setLogin(true);
	   oldSize=app.getBooks().size();
	   errorMessage.removeError("Administrator login required");
	}

	@Given("there is a book with title {string}, author {string}, and signature {string}")
	public void there_is_a_book_with_title_author_and_signature(String string, String string2, String string3) 
	{
	    book=new Book(string,string2,string3,true);
	}

	@When("the book is added to the library")
	public void the_book_is_added_to_the_library() 
	{
		if(!errorMessage.contains("Administrator login required"))
		{
			app.addBook(book);
		}
	}

	@Then("the book with title {string}, author {string}, and signature {string} is contained in the library")
	public void the_book_with_title_author_and_signature_is_contained_in_the_library(String string, String string2, String string3) 
	{
		assertTrue(this.app.getBooks().contains(new Book(string,string2,string3,true)));
	}

	@Given("that the administrator is not logged in")
	public void that_the_administrator_is_not_logged_in() 
	{
		app.setLogin(false);
		oldSize=app.getBooks().size();
		errorMessage.appendError("Administrator login required");
	}

	@Then("the error message {string} is given")
	public void the_error_message_is_given(String string) 
	{
		boolean result=(app.getBooks().size()==oldSize+1);
		assertFalse(result);
		if(!result) {logFile.appendMessage(string);}
	}
}