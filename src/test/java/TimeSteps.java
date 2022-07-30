import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.stream.Collectors;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import se.elib.Application;
import se.elib.Book;
import se.elib.User;

public class TimeSteps 
{
	private Application app;
	private MockDateHolder mdh;
	
	private User user;
	private Book book;
	private int oldFinesValue;
	
	public TimeSteps(Application app,MockDateHolder mdh)
	{
		this.app=app;
		this.mdh=mdh;
	}
	
	@Given("the user has borrowed a book")
	public void the_user_has_borrowed_a_book() 
	{
		user=new User(119,"ahmad","ahmad@gmail.com","rafidia street","HF99S","Nablus");
		book=new Book("DataBase","Ali","DBA11");
		
		app.login("adminadmin");
		app.registerUser(119,"ahmad","ahmad@gmail.com","rafidia street","HF99S","Nablus");
		app.addBook("DataBase","Ali","DBA11");
		app.borrowBook(user, book);
	}
	
	@Given("{int} days have passed")
	public void days_have_passed(Integer int1) 
	{
		mdh.advanceDateByDays(21);
	}
	
	@Given("the fine for one late book is {int} NIS")
	public void the_fine_for_one_late_book_is_nis(Integer int1) 
	{
		oldFinesValue=app.getUsers()
   	   		             .get(app.getUsers().indexOf(user))
   	   		             .getTotalFines();
		
		app.getUsers()
	       .get(app.getUsers().indexOf(user))
	       .getBorrowedBooks()
	       .stream().forEach(e->
	       {
	    	   if(e.checkOverDue(app.getDateServer().getDate()))
	    	   {
	    		   e.setOverDue(true);
	    		   
	    		   app.getUsers()
		    	      .get(app.getUsers().indexOf(user))
		    	      .addFine(int1);
	    	   }
	       });
	}
	
	@Then("the user has Late books")
	public void the_user_has_late_books() 
	{
		assertNotEquals(app.getUsers()
						   .get(app.getUsers().indexOf(user))
						   .getBorrowedBooks()
						   .stream()
						   .filter(e->e.getOverDue())
						   .collect(Collectors.toList())
						   .size(),0);
	}
	
	@Then("the user has to pay a fine of {int} NIS")
	public void the_user_has_to_pay_a_fine_of_nis(Integer int1) 
	{
		assertEquals(int1.intValue(),(app.getUsers()
					 			         .get(app.getUsers().indexOf(user))
					 			         .getTotalFines()-oldFinesValue));
	}
}