import java.util.Random;

public class GameBooth

{

	private double cost;
	private String firstPrize, consolationPrize;
	
	//Constructor
	//Pre: none
	//Post: A GameBooth object created
	//The cost and prizes are set
	
	public GameBooth(double charge, String p1, String p2)
	
	{
	
		cost = charge;
		firstPrize = p1;
		consolationPrize = p2;
		
	}
	
	//Game is played and prize awarded.
	//Pre: none
	//Post: Player has three tries. Player successful all three times
	//received the first prize. A consolation prize has been awarded
	//otherwise.
	
	public String start()
	
	{
	
		int toss;
		int successes = 0;
		Random rand = new Random();
		
		//play game
		for (int i = 0; i < 3; i++) //player gets three tries
		
		{
		
			toss = rand.nextInt(2);
			
			if (toss == 1)
			
			{
			
				successes += 1; //1 is a successful toss
				
			}
		
		}
		
		//award prize
		if (successes == 3)
		
		{
		
			return(firstPrize);
		
		}
		
		else
		
		{
		
			return(consolationPrize);
		
		}
		
	}
	
	//Returns the cost to play the game
	//Pre: none
	//Post: Cost of the game has been returned
	
	public double getCost()
	
	{
	
		return(cost);
		
	}
	
}