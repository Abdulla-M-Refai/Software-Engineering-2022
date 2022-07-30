import static org.mockito.Mockito.mock;

import se.elib.Application;
import se.elib.EmailServer;

public class MockEmailServerHolder 
{
	private EmailServer emailServer;
	
	public MockEmailServerHolder(Application app)
	{
		setEmailServer(mock(EmailServer.class));
		app.setEmailServer(emailServer);
	}

	public EmailServer getEmailServer() 
	{
		return emailServer;
	}

	public void setEmailServer(EmailServer emailServer) 
	{
		this.emailServer = emailServer;
	}
}