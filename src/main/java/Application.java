
public class Application
{
	public boolean isAdminLogedIn=false;
	
	public String login(String password)
	{
		if(password.equals("adminadmin"))
		{
			this.isAdminLogedIn=true;
			return "adminadmin";
		}
		
		this.isAdminLogedIn=false;
		return password;
		
	}
	
	public void logout()
	{
		this.isAdminLogedIn=false;
	}
}