public class Player

{

	private double spendingMoney;
	private String prizesWon;
	
	//Constructor
	//Pre: none
	//Post: A Player object is created. Spending money given to player.
	//The prizes won set to none.
	
	public Player(double money)
	
	{
	
		spendingMoney = money;
		prizesWon = "";
	
	}
	
	//Player pays for and then plays a game.
	//Pre: none
	//Post: Player's spending money decreased by cost of game.
	//The player has a new prize added to existing prizes.
	
	public String play(GameBooth game)
	
	{
	
		String newPrize;
		
		if (game.getCost() > spendingMoney)
		
		{
		
			return ("Sorry, not enough money to play.");
		
		}
		
		else
		
		{
		
			spendingMoney -= game.getCost(); //pay for game
			newPrize = game.start(); //play game
			prizesWon = newPrize + ", " + prizesWon;
			return("prize won: " + newPrize);
		
		}
	
	}
	
	//Returns the list of prizes won.
	//Pre: none
	//Post: the list of prizes has been returned
	
	public String showPrizes()
	
	{
	
		return(prizesWon);
		
	}

}