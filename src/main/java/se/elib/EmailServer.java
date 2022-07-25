package se.elib;

public class EmailServer 
{
	public void sendEmail(String email,String subject,String body)
	{
		System.out.println(		"Email: "   +email   +"\n"+
						   		"Subject: " +subject +"\n"+
						   		"Body: "    +body
				          );
	}
}