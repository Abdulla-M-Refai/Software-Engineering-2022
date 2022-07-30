import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

import se.elib.Application;
import se.elib.DateServer;

public class MockDateHolder 
{
	DateServer dateServer;
	
	public MockDateHolder(Application app)
	{
		dateServer=mock(DateServer.class);
		app.setDateServer(dateServer);
		when(dateServer.getDate()).thenReturn(Calendar.getInstance());
	}
	
	public void setDate(Calendar calendar)
	{
		when(dateServer.getDate()).thenReturn(calendar);
	}
	
	public void advanceDateByDays(int days)
	{
		Calendar advancedDate = new GregorianCalendar();
		advancedDate.setTime(dateServer.getDate().getTime());
		
		advancedDate.add(Calendar.DAY_OF_YEAR, days);
		advancedDate.add(Calendar.MINUTE, 1);
		setDate(advancedDate);
	}
}