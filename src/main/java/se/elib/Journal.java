package se.elib;

public class Journal extends Item
{
	public Journal(String name,String author,String isbn)
	{
		super(name,author,isbn);
	}
	
	@Override
	public int getFine()
	{
		return 15;
	}
}