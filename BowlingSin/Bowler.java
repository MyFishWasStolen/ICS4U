package BowlingSin;

public class Bowler
{
    private final String name;
    private int totalScore;

    public Bowler(String n)
    {
        name=n;
        totalScore=0;
    }

    public void updateScore(int points)
    {
        totalScore+=points;
    }

    public int getScore()
    {
        return totalScore;
    }

    public String getName()
    {
        return name;
    }
}
