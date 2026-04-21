//Instrument class

abstract class Instrument
{
	String musician;
	
	//Constructor
	//Pre: none
	//Post: A musician has been assigned to the instrument.
	public Instrument(String name)
	{
		musician = name;
	}
	
	//Returns the name of the musician
	//Pre: none
	//Post: The name of the musician playing the instrument has been returned.
	public String getMusician()
	{
		return(musician);
	}
	
	//Should return the sound of the instrument.
	//Pre: none
	//Post: The sound made by the instrument is returned.
	abstract String makeSound();

}