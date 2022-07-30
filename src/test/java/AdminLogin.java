import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import se.elib.Application;

public class AdminLogin 
{
	private Application app;
	
	private String realPassword;
	private String expected="adminadmin";
	
	public AdminLogin(Application app)
	{
		this.app=app; 
	}
	
	@Given("that the admin is not logged in")
	public void that_the_admin_is_not_logged_in() 
	{
		app.logout();
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
		assertEquals(expected,realPassword);
	}

	@Then("the admin is logged in")
	public void the_admin_is_logged_in() 
	{
	   assertTrue(app.getLogin());
	}

	@Then("the admin login fails")
	public void the_admin_login_fails() 
	{
		assertNotEquals(expected,realPassword);
	}

	@Then("the admin is not logged in")
	public void the_admin_is_not_logged_in() 
	{
		assertFalse(app.getLogin());
	}
}