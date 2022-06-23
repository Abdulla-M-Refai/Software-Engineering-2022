import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddBook 
{
	public Application app;
	public Book book;
	public int oldSize;
	
	public AddBook(Application app)
	{
		this.app=app;
	}
	
	@Given("that the administrator is logged in")
	public void that_the_administrator_is_logged_in() 
	{
	   app.isAdminLogedIn=true;
	   oldSize=app.books.size();
	}

	@Given("there is a book with title {string}, author {string}, and signature {string}")
	public void there_is_a_book_with_title_author_and_signature(String string, String string2, String string3) 
	{
	    book=new Book(string,string2,string3);
	}

	@When("the book is added to the library")
	public void the_book_is_added_to_the_library() 
	{
	   app.addBook(book);
	}

	@Then("the book with title {string}, author {string}, and signature {string} is contained in the library")
	public void the_book_with_title_author_and_signature_is_contained_in_the_library(String string, String string2, String string3) 
	{
		assertTrue((app.books.size()==oldSize+1)&&(this.app.books.contains(new Book(string,string2,string3))));
	}

	@Given("that the administrator is not logged in")
	public void that_the_administrator_is_not_logged_in() 
	{
		app.isAdminLogedIn=false;
		oldSize=app.books.size();
	}

	@Then("the error message {string} is given")
	public void the_error_message_is_given(String string) 
	{
		boolean result=(app.books.size()==oldSize+1)&&(this.app.books.contains(book));
		assertFalse(result);
		
		if(!result)
		{
			System.out.println(string);
		}
	}
}