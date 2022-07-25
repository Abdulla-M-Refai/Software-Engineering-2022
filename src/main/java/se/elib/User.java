package se.elib;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class User 
{
	private int id;
	private int totalFines;
	
	private String name;
	private String email;
	private String address;
	private String postCode;
	private String city;
	
	private ArrayList<Book> borrowedBooks;
	
	public User(int id,String name,String email,String address,String postCode,String city)
	{
		setId(id);
		setName(name);
		setEmail(email);
		setAddress(address);
		setPostCode(postCode);
		setCity(city);
		setBorrowedBooks(new ArrayList<>());
		setTotalFines(0);
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getPostCode() 
	{
		return postCode;
	}

	public void setPostCode(String postCode) 
	{
		this.postCode = postCode;
	}

	public String getCity() 
	{
		return city;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}
	
	public List<Book> getBorrowedBooks() 
	{
		return borrowedBooks;
	}

	public void setBorrowedBooks(List<Book> borrowedBooks) 
	{
		this.borrowedBooks = (ArrayList<Book>) borrowedBooks;
	}
	
	public void borrowBook(Book book,Calendar borrowDate)
	{
		Calendar dueDate = new GregorianCalendar();
		dueDate.setTime(borrowDate.getTime());
		dueDate.add(Calendar.DAY_OF_YEAR, 21);
		
		borrowedBooks.add(book);
		book.setAvailability(false);
		
		book.setBorrowDate(borrowDate);
		book.setDueDate(dueDate);
		book.setOverDue(false);
	}
	
	public void returnBook(Book book) 
	{
		borrowedBooks.remove(book);
		book.setAvailability(true);
		
		book.setBorrowDate(null);
		book.setDueDate(null);
		book.setOverDue(false);
	}
	
	public int getTotalFines() 
	{
		return totalFines;
	}

	public void setTotalFines(int totalFines) 
	{
		this.totalFines = totalFines;
	}
	
	public void addFine(int value)
	{
		totalFines+=value;
	}
	
	@Override
	public boolean equals(Object o) 
	{
		if ((o == null)||(this.getClass() != o.getClass())) {return false;}
		
		User user = (User) o; 
		return ((this.id==user.id)                    &&
				(this.name.equals(user.name))         &&
				(this.email.equals(user.email))       &&
				(this.address.equals(user.address))   &&
				(this.postCode.equals(user.postCode)) &&
				(this.city.equals(user.city)));
	}
	
	@Override
    public int hashCode() 
	{
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + id;
        result = 31 * result + email.hashCode();
        return result;
    }
}