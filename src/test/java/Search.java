import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.stream.Collectors;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Search 
{
	private Application app;
	private ArrayList <Book> result;
	
	public Search(Application app)
	{
		this.app=app; 
	}
	
	@Given("these books are contained in the library")
	public void these_books_are_contained_in_the_library(io.cucumber.datatable.DataTable dataTable) 
	{
		for (int i=0; i < dataTable.height();i++) 
		{
			app.getBooks().add(new Book(dataTable.row(i).get(0),
										dataTable.row(i).get(1),
										dataTable.row(i).get(2),
										true));
		}
	}

	@When("the user searches for the text {string}")
	public void the_user_searches_for_the_text(String string) 
	{
	   result = app.search(string);
	}

	@Then("the book with code {string} is found")
	public void the_book_with_code_is_found(String string) 
	{
		assertTrue(result.get(result.size()-1).getIsbn().equals(string));
	}

	@Then("no books are found")
	public void no_books_are_found() 
	{
		assertTrue(result.size()==0);
	}

	@Then("the books with code {string} and {string} are found")
	public void the_books_with_code_and_are_found(String string, String string2) 
	{
		assertTrue
		(
			result.stream()
			  	  .filter(e->(e.getIsbn().equals(string)||e.getIsbn().equals(string2)))
			  	  .collect(Collectors.toList()).size()>1
		);
	}
}