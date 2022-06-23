import java.util.ArrayList;

public class Application
{
	public ArrayList<Book> books=new ArrayList<Book>();
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
	
	public void addBook(String name,String author,String isbn)
	{
		if(this.isAdminLogedIn)
		{
			books.add(new Book(name,author,isbn));
		}
	}
	
	public void addBook(Book book)
	{
		if(this.isAdminLogedIn)
		{
			books.add(book);
		}
	}

	public ArrayList<Book> search(String substring) 
	{
		ArrayList <Book> result = new ArrayList<Book>();
		
		for (Book book : books) 
		{
			if ((book.getName().indexOf(substring)!=-1)||(book.getAuthor().indexOf(substring)!=-1)||(book.getIsbn().indexOf(substring)!=-1)) 
			{
				result.add(book);
			}
		}
		
		return result;
	}
}