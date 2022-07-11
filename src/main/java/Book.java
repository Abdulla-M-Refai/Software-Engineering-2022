
public class Book 
{
	private String name;
	private String author;
	private String isbn;
	
	public Book(String name,String author,String isbn)
	{
		setName(name);
		setAuthor(author);
		setIsbn(isbn);
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
	
	@Override
	public boolean equals(Object o) 
	{
		Book book = (Book) o; 
		return ((this.isbn.equals(book.isbn))&&(this.author.equals(book.author))&&(this.name.equals(book.name)));
	}
}