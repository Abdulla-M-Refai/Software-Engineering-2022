package se.elib;

import org.apache.log4j.Logger;

public class EmailServer 
{
	private Logger logger;
	
	public EmailServer()
	{
		logger=Logger.getLogger(EmailServer.class);
	}
	
	public void sendEmail(String email,String subject,String body)
	{
		String mailMessage= "Email: "   +email   +"\n"+
	   		                "Subject: " +subject +"\n"+
	   		                "Body: "    +body;
		
		logger.log(null, mailMessage);
	}
}