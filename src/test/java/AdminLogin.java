import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AdminLogin 
{
	private Application app;
	private ErrorMessage errorMessage;
	
	private String realPassword;
	private String expected="adminadmin";
	
	public AdminLogin(Application app,ErrorMessage errorMessage)
	{
		this.app=app; 
		this.errorMessage=errorMessage;
	}
	
	@Given("that the admin is not logged in")
	public void that_the_admin_is_not_logged_in() 
	{
		app.setLogin(false);
		errorMessage.appendError("Administrator login required");
	}

	@Given("the password is {string}")
	public void the_password_is(String string) 
	{
		realPassword=string;
	    app.login(string);
	}
	
	@Then("the admin login succeeds")
	public void the_admin_login_succeeds() 
	{
		assertTrue(expected.equals(realPassword));
	}

	@Then("the admin is logged in")
	public void the_admin_is_logged_in() 
	{
	   assertTrue(app.getLogin());
	}

	@Then("the admin login fails")
	public void the_admin_login_fails() 
	{
		assertFalse(expected.equals(realPassword));
	}

	@Then("the admin is not logged in")
	public void the_admin_is_not_logged_in() 
	{
		assertFalse(app.getLogin());
	}
}