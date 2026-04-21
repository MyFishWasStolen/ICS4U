

public class Bowler
{
    private final String name;//make a unchangable name vairable
    private int totalScore;

    public Bowler(String n)
    {
        name=n;
        totalScore=0;

        //constructor to set name and score to 0
    }

    public void updateScore(int points) // update score with points
    {
        totalScore+=points;
    }

    public int getScore()//return points
    {
        return totalScore;
    }

    public String getName()//return name
    {
        return name;
    }
}
