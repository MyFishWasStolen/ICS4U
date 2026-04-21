//Programmer: Mathew Sinoj
//Description: Old.Coin class that simulates flipping a coin
//Date Created: April 19, 2026
//Date Revised: April 19, 2026

import java.util.Random;

public class Coin

{

	private int faceUp; //0 = heads, 1 = tails

	//Constructor
	//Pre: none
	//Post: A Old.Coin object created. faceUp initialized to 0 (heads).
	public Coin()

	{

		faceUp = 0;

	}

	//Returns whether the coin is heads up or tails up.
	//Pre: none
	//Post: 0 has been returned if the coin is heads up, 1 if tails up.
	public int showFace()

	{

		return(faceUp);

	}

	//Flips the coin and returns the result.
	//Pre: none
	//Post: faceUp has been assigned a random 0 or 1 and returned.
	public int flipCoin()

	{

		Random rand = new Random();
		faceUp = rand.nextInt(2);
		return(faceUp);

	}

}