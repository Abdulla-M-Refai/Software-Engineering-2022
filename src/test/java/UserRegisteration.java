import static org.junit.Assert.assertTrue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import se.elib.Application;
import se.elib.User;

public class UserRegisteration 
{
	private Application app;
	private ErrorMessage errorMessage;
	private LogFile logFile;
	
	private User user;
	private int oldSize;
	
	public UserRegisteration(Application app,ErrorMessage errorMessage,LogFile logFile)
	{
		this.app=app;
		this.errorMessage=errorMessage;
		this.logFile=logFile;
	}
	
	@Given("those users are registered in the system")
	public void those_users_are_registered_in_the_system(io.cucumber.datatable.DataTable dataTable) 
	{
		for (int i=0; i < dataTable.height();i++)
		{
			app.getUsers().add(new User(Integer.parseInt(dataTable.row(i).get(0)),dataTable.row(i).get(1),
										dataTable.row(i).get(2),dataTable.row(i).get(3),dataTable.row(i).get(4),
										dataTable.row(i).get(5)));
		}
	}

	@Given("there is a user with ID:{int} Name:{string} Email:{string} Address:{string} Postal Code:{string} City:{string}")
	public void there_is_a_user_with_id_name_email_address_postal_code_city(Integer int1, String string, String string2, String string3, String string4, String string5) 
	{
	    user=new User(int1,string,string2,string3,string4,string5);
	    oldSize=app.getUsers().size();
	}

	@When("the user is registered")
	public void the_user_is_registered() 
	{
		try {app.registerUser(user.getId(),user.getName(),user.getEmail(),user.getAddress(),user.getPostCode(),user.getCity());}
			catch(IllegalStateException|IllegalArgumentException ex) 
				{errorMessage.setException(ex); logFile.appendMessage(ex.getMessage());}
	}

	@Then("the user with ID:{int} Name:{string} Email:{string} Address:{string} Postal Code:{string} City:{string} registered successfully")
	public void the_user_with_id_name_email_address_postal_code_city_registered_successfully(Integer int1, String string, String string2, String string3, String string4, String string5) 
	{
	   assertTrue(app.getUsers().contains(new User(int1,string,string2,string3,string4,string5))&&app.getUsers().size()==oldSize+1);
	}
}