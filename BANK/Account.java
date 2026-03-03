package BANK;

import java.text.NumberFormat;

public class Account 
{

    private double balance;
	private Customer cust;
	
	//Constructor
	//Pre: none
	//Post: An account created. Balance and customer data initialized with parameters.
	
	public Account (double bal, String fName, String lName, String str, String city, String st, String zip)
	
	{
	
		balance = bal;
		cust = new Customer(fName, lName, str, city, st, zip);
		
	}
	
	//Returns the current balance.
	//Pre: none
	//Post: the account balance has been returned
	
	public double getBalance()
	
	{
	
		return (balance);
		
	}
	
	//A deposit is made to the account
	//Pre: none
	//Post: the balance has been increased by the amount of the deposit.
	
	public void deposit(double amt)
	
	{
	
		balance += amt;
		
	}
	
	//A withdrawal is made from the account if there is enough money.
	//Pre: none
	//Post: The balance has been decreased by the amount withdrawn.
	
	public void withdrawal(double amt)
	
	{
	
		if (amt <= balance)
		
		{
		
			balance -= amt;
			
		}
		
		else
		
		{
		
			System.out.println("Not enough money in account");
			
		}
		
	}

	//Returns a String that represents the Account object.
	//Pre: none
	//Post: a string representing the account object has been returned.
	
	public String toString()
	
	{
	
		String accountString;
		NumberFormat money = NumberFormat.getCurrencyInstance();
		
		accountString = cust.toString();
		accountString += "Current balance is " + money.format(balance);
		return(accountString);
    }    

}
