
public class ErrorMessage 
{
	private Exception exception;
	
	public ErrorMessage()
	{
		setException(null);
	}

	public Exception getException() 
	{
		return exception;
	}

	public void setException(Exception exception) 
	{
		this.exception = exception;
	}	
}