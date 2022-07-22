import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import se.elib.Application;

public class AdminLogout 
{
	private Application app;
	
	public AdminLogout(Application app)
	{
		this.app=app;
	}
	
	@Given("that the admin is logged in")
	public void that_the_admin_is_logged_in()
	{
		app.setLogin(true);
	}

	@When("the admin logs out")
	public void the_admin_logs_out() 
	{
		app.logout();
	}
}