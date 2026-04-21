//Woodwind class.

abstract class Woodwind extends Instrument
{
	//Constructor
	//Pre: none
	//Post: A woodwind instrument has been created.
	public Woodwind(String player)
	{
		super(player);
	}
	
	//Retruns the sound of the instrument.
	//Pre: none
	//Post: The sound made by the instrument is returned.
	public String makeSound()
	{
		return("toot");
	}
}