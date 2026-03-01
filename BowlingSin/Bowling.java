package BowlingSin;
//Programmer: Mathew Sinoj
//Description: Simple program to calculate the score of a bowling game
//Date Created: Feb 24 2026
//Date Revised: Feb 28 2026

//import scanner
import java.util.Scanner;


public class Bowling
{

    public static void main (String[] args)
    {
        //initialize objects
        Scanner input = new Scanner(System.in);
        Frame frame = new Frame();

        //ask the number of players in the current bowling game
        System.out.print("Enter number of players: ");

        //use the input from the user create and array to store the bowler objects
        Bowler[] bowlers = new Bowler[input.nextInt()];

        //loop for the number of players in the game
        for(int i = 0; i < bowlers.length; i++)
        {
            //ask the user to input name for each player which is used to initialize bowler instances for the players
            System.out.print("Enter player #" + (i + 1) + "'s name: ");
            String player = input.next();
            bowlers[i] = new Bowler(player);
        }

        //for loop for 10 frames
        for(int i = 0; i <10; i++)
        {

            //prints which frame it is on currently
            System.out.println("Frame: #" + (i+1));
            System.out.println("-----------------");


            //for loop for the number of bowlers
            for(int j = 0; j < bowlers.length; j++)
            {
                //use the frame object to play the frame and return the score
                int frameScore = frame.playFrame();

                //update the players score with the score they got in the current frame
                bowlers[j].updateScore(frameScore);

                //print the score they got in the current frame
                System.out.println(bowlers[j].getName() + " scored " + frameScore + " points");


            }
            //create a separation in the output for each frame
            System.out.println("-----------------\n");
        }

        //print heading for final score
        System.out.println("FINAL SCORE");

        //loop for each bowler
        for(int i = 0; i < bowlers.length; i++)
        {
            //print each bowlers name and their score
            System.out.println(bowlers[i].getName() + ":" + bowlers[i].getScore());
        }

        //close scanner object
        input.close();

    }

}
