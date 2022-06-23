import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AdminLogin 
{
	public Application app;
	public String password;
	public String realPassword;
	
	public AdminLogin(Application app)
	{
		this.app=app; 
	}
	
	@Given("that the admin is not logged in")
	public void that_the_admin_is_not_logged_in() 
	{
		app.isAdminLogedIn=false;
	}

	@Given("the password is {string}")
	public void the_password_is(String string) 
	{
	    realPassword=app.login(string);
	}
	
	@Then("the admin login succeeds")
	public void the_admin_login_succeeds() 
	{
		String expected="adminadmin";
		assertTrue(expected.equals(realPassword));
	}

	@Then("the admin is logged in")
	public void the_admin_is_logged_in() 
	{
	   assertTrue(app.isAdminLogedIn);
	}

	@Then("the admin login fails")
	public void the_admin_login_fails() 
	{
		String expected="adminadmin";
		assertFalse(expected.equals(realPassword));
	}

	@Then("the admin is not logged in")
	public void the_admin_is_not_logged_in() 
	{
		assertFalse(app.isAdminLogedIn);
	}
}