import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class AdminLogout 
{
	private Application app;
	private ErrorMessage errorMessage;
	
	public AdminLogout(Application app,ErrorMessage errorMessage)
	{
		this.app=app;
		this.errorMessage=errorMessage;
	}
	
	@Given("that the admin is logged in")
	public void that_the_admin_is_logged_in()
	{
		app.setLogin(true);
		errorMessage.removeError("Administrator login required");
	}

	@When("the admin logs out")
	public void the_admin_logs_out() 
	{
		app.logout();
	}
}