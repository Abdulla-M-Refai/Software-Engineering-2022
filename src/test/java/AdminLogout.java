import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminLogout {
	
	public Application app;
	
	public AdminLogout(Application app)
	{
		this.app=app; 
	}
	@Given("that the admin is logged in")
	public void that_the_admin_is_logged_in() {
		app.isAdminLogedIn=true;
	}

	@When("the admin logs out")
	public void the_admin_logs_out() {
		app.logout();
	}
	
	
}
