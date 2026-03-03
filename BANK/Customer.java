package BANK;

public class Customer

{

	private String firstName, lastName, street, city, state, zip;
	
	//Constructor
	//Pre: none
	//Post: A Customer object has been created
	//Customer data has been initialized with parameters
	
	public Customer(String fName, String lName, String str, String c, String s, String z)
	
	{
	
		firstName = fName;
		lastName = lName;
		street = str;
		city = c;
		state = s;
		zip = z;
		
	}
	
	//Returns a String that represents the Customer object
	//Pre: none
	//Post: A string represents the Account object has been returned.
	
	public String toString()
	
	{
	
		String custString;
		
		custString = firstName + " " + lastName + "\n";
		custString += street + "\n";
		custString += city + ", " + state + " " + zip + "\n";
		return(custString);
		
	}
}	