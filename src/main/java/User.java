sundos-saifi
import java.util.ArrayList;

public class User 
{
	private int id;
	private String name,email,address,postCode,city;
	private ArrayList<Book> borrowedBooks;
	
	public User()
	{
		setId(-1);
		setName(null);
		setEmail(null);
		setAddress(null);
		setPostCode(null);
		setCity(null);
		setBorrowedBooks(null);
	}
	
	public User(int id,String name,String email,String address,String postCode,String city)
	{
		setId(id);
		setName(name);
		setEmail(email);
		setAddress(address);
		setPostCode(postCode);
		setCity(city);
		setBorrowedBooks(new ArrayList<Book>());
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
	
	public ArrayList<Book> getBorrowedBooks() 
	{
		return borrowedBooks;
	}

	public void setBorrowedBooks(ArrayList<Book> borrowedBooks) 
	{
		this.borrowedBooks = borrowedBooks;
	}
	
	@Override
	public boolean equals(Object o) 
	{
		User user = (User) o; 
		return ((this.id==user.id)                    &&
				(this.name.equals(user.name))         &&
				(this.email.equals(user.email))       &&
				(this.address.equals(user.address))   &&
				(this.postCode.equals(user.postCode)) &&
				(this.city.equals(user.city)));
	}


public class User 
{
	private int id;
	private String name,email,address,postCode,city;
	
	public User()
	{
		setId(-1);
		setName(null);
		setEmail(null);
		setAddress(null);
		setPostCode(null);
		setCity(null);
	}
	
	public User(int id,String name,String email,String address,String postCode,String city)
	{
		setId(id);
		setName(name);
		setEmail(email);
		setAddress(address);
		setPostCode(postCode);
		setCity(city);
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
	
	@Override
	public boolean equals(Object o) 
	{
		User user = (User) o; 
		return ((this.id==user.id)&&(this.name.equals(user.name))&&(this.email.equals(user.email))&&(this.address.equals(user.address))&&(this.postCode.equals(user.postCode))&&(this.city.equals(user.city)));
	}
main
}