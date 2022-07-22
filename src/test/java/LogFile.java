
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

public class LogFile 
{
	public LogFile() throws IOException
	{
		if(!Helper.logFileFlag)
		{
			Helper.logFileFlag=Files.deleteIfExists(Path.of("message.log"));
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