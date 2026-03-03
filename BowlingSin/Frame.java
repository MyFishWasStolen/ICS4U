package BowlingSin;

import java.util.Random;//import random package
public class Frame
{
    private final Random rand;//create final variable to store random object

    public Frame()
    {

        rand = new Random();//initialize final variable with random object

    }

    public int playFrame()
    {

        int pinsRemaining = 10; //set pins remaining to 10

        int throw1 = rand.nextInt(pinsRemaining+1); // get a random number to calculate 

        if (throw1==10)//if strike award 20
        {
            return 20;
        }

        pinsRemaining-=throw1;//subratct throw one from pins remaining

        int throw2 = rand.nextInt(pinsRemaining+1);//get second throw

        if(throw1+throw2==10)//check if it is a spare if so reward 15 points
        {
            return 15;
        }

        return throw1+throw2; //if niether use throw 1 and throw 2 for total points

    }

}
