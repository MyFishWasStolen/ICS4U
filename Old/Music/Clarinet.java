//Clarinet class.
public class Clarinet extends Woodwind
{
	//Constructor
	//Pre: none
	//Post: A clarinet has been created.
	public Clarinet(String clarinetist)
	{
		super(clarinetist);
	}
	
	//Returns the sound of the instrument.
	//Pre: none
	//Post: The sound made by the instrument is returned.
	public String makeSound()
	{
		return("squawk");
	}
	
	//Returns a String that represents the instrument.
	//Pre: none
	//Post: A string representing the instrument has been returned.
	public String toString()
	{
		return(super.getMusician() + " plays " + makeSound() + ".");
	}
}