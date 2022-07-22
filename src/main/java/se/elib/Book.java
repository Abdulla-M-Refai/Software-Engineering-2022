package se.elib;

public class Book 
{
	private String name;
	private String author;
	private String isbn;
	private boolean availability;
	
	public Book(String name,String author,String isbn,boolean availability)
	{
		setName(name);
		setAuthor(author);
		setIsbn(isbn);
		setAvailability(availability);
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