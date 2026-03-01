package BowlingSin;
import java.util.Scanner;
public class Bowling
{

    public static void main (String[] args)
    {
        Scanner input = new Scanner(System.in);
        Frame frame = new Frame();
        System.out.print("Enter number of players: ");
        int n = input.nextInt();


        Bowler[] bowlers = new Bowler[n];

        for(int i = 0; i < n; i++)
        {
            System.out.print("Enter player #" + (i + 1) + "'s name: ");
            String player = input.next();
            bowlers[i] = new Bowler(player);
        }

        for(int i = 0; i <10; i++)
        {

            System.out.println("Frame: #" + (i+1));
            System.out.println("-----------------");

            for(int j = 0; j < bowlers.length; j++)
            {

                int frameScore = frame.playFrame();

                bowlers[j].updateScore(frameScore);

                System.out.println(bowlers[j].getName() + " scored " + frameScore + " points");
                //System.out.println(bowlers[j].getName() + "'s total score is " + bowlers[j].getScore() + " points");

            }

            System.out.println("-----------------\n");
        }

        System.out.println("FINAL SCORE");
        for(int i = 0; i < bowlers.length; i++)
        {
            System.out.println(bowlers[i].getName() + ":" + bowlers[i].getScore());
        }

        input.close();



    }

}
