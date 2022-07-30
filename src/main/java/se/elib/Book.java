package se.elib;

public class Book extends Item
{
	public Book(String name,String author,String isbn)
	{
		super(name,author,isbn);
	}
	
	@Override
	public int getFine()
	{
		return 30;
	}
}