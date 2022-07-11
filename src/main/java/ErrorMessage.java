import java.util.ArrayList;
import java.util.stream.Collectors;

public class ErrorMessage 
{
	private ArrayList<Exception> exceptions;
	
	public ErrorMessage()
	{
		setExceptions(new ArrayList<Exception>());
	}
	
	public ArrayList<Exception> getExceptions() 
	{
		return exceptions;
	}

	public void setExceptions(ArrayList<Exception> exceptions) 
	{
		this.exceptions = exceptions;
	}
	
	public void appendError(String message)
	{
		exceptions.add(new Exception(message));
	}
	
	public void removeError(final String message)
	{
		exceptions.removeIf(e->e.getMessage().equals(message));
	}

	public boolean contains(final String string) 
	{
		if(exceptions.stream()
				  .filter(e->e.getMessage().equals(string))
				  .collect(Collectors.toList()).size()==1)
		{
			return true;
		}
		
		return false;
	}
}