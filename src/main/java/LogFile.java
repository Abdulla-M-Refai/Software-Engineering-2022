import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogFile 
{
	private FileWriter fw;
	private BufferedWriter bw;
	private PrintWriter pw;
	
	public LogFile()
	{
		try 
		{
			new File("message.log").delete();
			fw = new FileWriter("message.log", true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(bw);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void appendMessage(String message)
	{
		pw.println(message); 
		pw.flush();
		pw.close();
	}
}