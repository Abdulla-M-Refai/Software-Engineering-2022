package se.elib;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;

public class Application
{
	private ArrayList<User> users;
	private ArrayList<Book> books;
	private boolean isAdminLogedIn;
	private Logger logger = Logger.getLogger(Application.class);
	
	public Application()
	{
		setUsers(new ArrayList<>());
		setBooks(new ArrayList<>());
		setLogin(false);
	}
	
	public void setLogin(boolean isAdminLogedIn)
	{
		this.isAdminLogedIn=isAdminLogedIn;
	}
	
	public boolean getLogin()
	{
		return isAdminLogedIn;
	}
	
	public List<User> getUsers() 
	{
		return users;
	}

	public void setUsers(List<User> users) throws IllegalArgumentException
	{
		if(users==null)
			{throw new IllegalArgumentException("users list is null");}
		
		this.users = (ArrayList<User>) users;
	}
	
	public void setBooks(List<Book> books) throws IllegalArgumentException
	{
		if(books==null)
			{throw new IllegalArgumentException("books list is null");}
	
		this.books=(ArrayList<Book>) books;
	}
	
	public List<Book> getBooks()
	{
		return books;
	}
	
	public void login(String password)
	{
		try (InputStream input = new FileInputStream("config.properties")) 
		{
            Properties prop = new Properties();
            prop.load(input);
            isAdminLogedIn=password.equals(prop.getProperty("admin.password"));
        }
		catch (IOException ex) 
		{
			logger.log(null, ex);
        }
	}
	
	public void logout()
	{
		isAdminLogedIn=false;
	}
	
	public void addBook(String name,String author,String isbn) throws IllegalStateException , IllegalArgumentException
	{
		if(!isAdminLogedIn) 
			{throw new IllegalStateException ("Administrator login required");}
		
		else if(name==null||author==null||isbn==null) 
			{throw new IllegalArgumentException("Missing some book information");}
		
		else if(name.isBlank()||author.isBlank()||isbn.isBlank()) 
			{throw new IllegalArgumentException("book information is not valid");}
		
		books.add(new Book(name,author,isbn,true));
	}
	
	public List<Book> search(final String substring) throws IllegalArgumentException
	{
		if(substring==null) 
			{throw new IllegalArgumentException("Missing search key");}
		
		else if(substring.isBlank()) 
			{throw new IllegalArgumentException("Search key is not valid");}
		
		return new ArrayList<>(books.stream()
									.filter(e->(e.getName()
											     .indexOf(substring)!=-1)||
											   (e.getAuthor()
												 .indexOf(substring)!=-1)||
											   (e.getIsbn()
												 .indexOf(substring)!=-1))
									.collect(Collectors.toList()));
	}

	public void registerUser(int id, String name, String email, String address, String postalCode, String city) throws IllegalStateException , IllegalArgumentException
	{
		if(!isAdminLogedIn) 
			{throw new IllegalStateException ("Administrator login required");}
		
		else if(name==null||email==null||address==null||postalCode==null||city==null) 
			{throw new IllegalArgumentException("Missing some user information");}
		
		else if(id<0||name.isBlank()||email.isBlank()||address.isBlank()||postalCode.isBlank()||city.isBlank())
			{throw new IllegalArgumentException("user information is not valid");}
		
		
		if(users.stream()
				.filter(e->(e.getId()==id||e.getEmail().equals(email)))
				.collect(Collectors.toList())
				.size()!=1)
		{users.add(new User(id,name,email,address,postalCode,city));}
		
		else
			{throw new IllegalStateException ("This user is already registered");}
	}

	public void borrowBook(User user, Book book) throws IllegalStateException , IllegalArgumentException
	{
		if(!isAdminLogedIn)
			{throw new IllegalStateException ("Administrator login required");}
		
		else if(user==null) 
			{throw new IllegalArgumentException("User not found");}
	
		else if(book==null) 
			{throw new IllegalArgumentException("Book not found");}
		
		else if(!books.get(books.indexOf(book)).getAvailability())
			{throw new IllegalStateException ("Book is not available");}
		
		else if(users.get(users.indexOf(user)).getBorrowedBooks().size()>=5)
			{throw new IllegalStateException ("you can't borrow more than five books");}
		
		boolean firstCondition  = users.get(users.indexOf(user))
				                       .getBorrowedBooks()
				                       .size()<5;
		
		boolean secondCondition = books.get(books.indexOf(book))
				                       .getAvailability();
		
		if(firstCondition&&secondCondition)
		{
			users.get(users.indexOf(user))
			     .getBorrowedBooks()
			     .add(book);
			
			books.get(books.indexOf(book))
			     .setAvailability(false);
		}
	}

	public void returnBook(User user, Book book) throws IllegalStateException , IllegalArgumentException
	{
		if(!isAdminLogedIn)
			{throw new IllegalStateException ("Administrator login required");}
		
		else if(user==null) 
			{throw new IllegalArgumentException("User not found");}
		
		else if(book==null) 
			{throw new IllegalArgumentException("Book not found");}
		
		else if(users.indexOf(user)==-1)
			{throw new IllegalStateException ("this book is not borrowed by you");}
		
		else if(users.get(users.indexOf(user)).getBorrowedBooks().indexOf(book)==-1)
			{throw new IllegalStateException ("this book is not borrowed by you");}
		
		users.get(users.indexOf(user))
	         .getBorrowedBooks()
	         .remove(users.get(users.indexOf(user)).getBorrowedBooks().indexOf(book));
	
	    books.get(books.indexOf(book)).setAvailability(true);
	}
}