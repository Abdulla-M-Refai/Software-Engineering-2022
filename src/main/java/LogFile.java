import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogFile 
{
	private static boolean flag=false;
	
	public LogFile()
	{
		if(!flag)
		{
			new File("message.log").delete();
			flag=true;
		}
	}
	
	public void appendMessage(String message)
	{
		try 
		{
			FileWriter fw = new FileWriter("message.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.println(message); 
			pw.flush();
			pw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}