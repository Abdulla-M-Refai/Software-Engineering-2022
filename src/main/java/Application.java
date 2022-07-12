import java.util.ArrayList;
import java.util.stream.Collectors;

public class Application
{
	private ArrayList<User> users;
	private ArrayList<Book> books;
	private boolean isAdminLogedIn;
	
	public Application()
	{
		setUsers(new ArrayList<User>());
		setBooks(new ArrayList<Book>());
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
	
	public ArrayList<User> getUsers() 
	{
		return users;
	}

	public void setUsers(ArrayList<User> users) 
	{
		this.users = users;
	}
	
	public void setBooks(ArrayList<Book> books)
	{
		this.books=books;
	}
	
	public ArrayList<Book> getBooks()
	{
		return books;
	}
	
	public void login(String password)
	{
		isAdminLogedIn=password.equals("adminadmin")?true:false;
	}
	
	public void logout()
	{
		isAdminLogedIn=false;
	}
	
	public void addBook(String name,String author,String isbn)
	{
sundos-saifi
		books.add(new Book(name,author,isbn,true));
=======
		books.add(new Book(name,author,isbn));
main
	}
	
	public void addBook(Book book)
	{
		books.add(book);
	}

	public ArrayList<Book> search(final String substring) 
	{
		return new ArrayList<Book>(books.stream()
sundos-saifi
										.filter(e->(e.getName()
												     .indexOf(substring)!=-1)||
												   (e.getAuthor()
													 .indexOf(substring)!=-1)||
												   (e.getIsbn()
													 .indexOf(substring)!=-1))
										.collect(Collectors.toList()));
	}

	public void registerUser(final User user)
	{
		if(!(users.stream()
			 .filter(e->(e.equals(user)||e.getId()==user.getId()||e.getEmail()==user.getEmail()))
			 .collect(Collectors.toList())
			 .size()==1))
		{
			users.add(user);
		}
	}

	public void borrowBook(User user, Book book)
	{
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

										.filter(e->(e.getName().indexOf(substring)!=-1)
												||(e.getAuthor().indexOf(substring)!=-1)
												||(e.getIsbn().indexOf(substring)!=-1))
										.collect(Collectors.toList()));
	}

	public void registerUser(final User user)
	{
		if(!(users.stream()
			 .filter(e->(e.equals(user)||e.getId()==user.getId()||e.getEmail()==user.getEmail()))
			 .collect(Collectors.toList())
			 .size()==1))
		{
			users.add(user);
main
		}
	}
}