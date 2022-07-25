package se.elib;

import java.util.Calendar;

public class Book 
{
	private String name;
	private String author;
	private String isbn;
	
	private Calendar borrowDate;
	private Calendar dueDate;
	
	private boolean isOverDue;
	private boolean availability;
	public static final int BOOK_FINE=30;
	
	public Book(String name,String author,String isbn)
	{
		setName(name);
		setAuthor(author);
		setIsbn(isbn);
		
		setAvailability(true);
		setOverDue(false);
		
		setBorrowDate(null);
		setDueDate(null);
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getAuthor() 
	{
		return author;
	}

	public void setAuthor(String author) 
	{
		this.author = author;
	}

	public String getIsbn() 
	{
		return isbn;
	}
	
	public void setIsbn(String isbn) 
	{
		this.isbn = isbn;
	}
	
	public boolean getAvailability() 
	{
		return availability;
	}

	public void setAvailability(boolean availability) 
	{
		this.availability = availability;
	}
	
	public Calendar getBorrowDate() 
	{
		return borrowDate;
	}
	
	public void setBorrowDate(Calendar borrowDate) 
	{
		this.borrowDate=borrowDate;
	}
	
	public Calendar getDueDate() 
	{
		return dueDate;
	}

	public void setDueDate(Calendar dueDate) 
	{
		this.dueDate = dueDate;
	}
	
	public boolean getOverDue() 
	{
		return isOverDue;
	}

	public void setOverDue(boolean isOverDue) 
	{
		this.isOverDue = isOverDue;
	}
	
	public boolean checkOverDue(Calendar currentDate) 
	{
		return (!availability&&currentDate.after(dueDate));
	}
	
	@Override
	public boolean equals(Object o) 
	{
		if ((o == null)||(this.getClass() != o.getClass())) {return false;}

		Book book = (Book) o; 
		return ((this.isbn.equals(book.isbn))     &&
				(this.author.equals(book.author)) &&
				(this.name.equals(book.name)));
	}
	
	@Override
    public int hashCode() 
	{
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + isbn.hashCode();
        return result;
    }
}