import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Search 
{
	public Application app;
	public ArrayList <Book> result;
	
	public Search(Application app)
	{
		this.app=app; 
	}
	
	@Given("these books are contained in the library")
	public void these_books_are_contained_in_the_library(io.cucumber.datatable.DataTable dataTable) 
	{
		for (int i=0; i < dataTable.height();i++) 
		{
			app.books.add(new Book(dataTable.row(i).get(0),dataTable.row(i).get(1),dataTable.row(i).get(2)));
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
		assertTrue(this.result.get(this.result.size()-1).getIsbn().equals(string));
	}

	@Then("no books are found")
	public void no_books_are_found() 
	{
		assertTrue(this.result.size()==0);
	}

	@Then("the books with code {string} and {string} are found")
	public void the_books_with_code_and_are_found(String string, String string2) 
	{
		boolean flag = true ;
		
		for (int i=0 ; i<result.size();i++) 
		{
			if (!((result.get(i).getIsbn().equals(string))||(result.get(i).getIsbn().equals(string2)))) 
			{
				flag = false;
			}
		}
		
		assertTrue(flag);
	}
}