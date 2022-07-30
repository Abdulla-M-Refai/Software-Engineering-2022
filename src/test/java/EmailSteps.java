import static org.mockito.Mockito.verify;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import se.elib.Application;
import se.elib.Book;
import se.elib.User;

public class EmailSteps 
{
	private Application app;
	private MockEmailServerHolder mesh;
	
	private User user;
	private Book book;
	
	public EmailSteps(Application app,MockEmailServerHolder mesh)
	{
		this.app=app;
		this.mesh=mesh;
	}
	
	@Given("there is a user with atleast one late book")
	public void there_is_a_user_with_atleast_one_late_book() 
	{
		user=new User(121,"jaber","jaber@gmail.com","rafidia street","HF99S","Nablus");
		book=new Book("Data Science","alexander","DSA212");
		
		app.login("adminadmin");
		app.registerUser(121,"jaber","jaber@gmail.com","rafidia street","HF99S","Nablus");
		app.addBook("Data Science","alexander","DSA212");
		app.borrowBook(user, book);
		app.getUsers()
		   .get(app.getUsers().indexOf(user))
		   .getBorrowedBooks()
		   .get(app.getUsers().get(app.getUsers().indexOf(user)).getBorrowedBooks().indexOf(book))
		   .setOverDue(true);
	}

	@When("the admin sends a reminder email")
	public void the_admin_sends_a_reminder_email() 
	{
	    app.sendReminder();
	}

	@Then("then the user should receive an email with subject {string} and body {string}")
	public void then_the_user_should_receive_an_email_with_subject_and_body(String string, String string2) 
	{
	    verify(mesh.getEmailServer()).sendEmail(user.getEmail(), string, string2);
	}
}