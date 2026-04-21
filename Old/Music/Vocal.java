//Vocal class.
public class Vocal extends Instrument
{
	//Constructor
	//Pre: none
	//Post: A singer has been created.
	public Vocal(String singerName)
	{
		super(singerName);
	}
	
	//Returns the sound of the instrument.
	//Pre: none
	//Post: The sound make by the singer.
	public String makeSound()
	{
		return("LaLaLa");
	}
	
	//Returns a String that represents the instrument.
	//Pre: none
	//Post: A string representing the singer.
	public String toString()
	{
		return(super.getMusician() + " sings " + makeSound() + ".");
	}
}