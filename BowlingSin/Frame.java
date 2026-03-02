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

        int pinsRemaining = 10;

        int throw1 = rand.nextInt(pinsRemaining+1);

        if (throw1==10)
        {
            return 20;
        }

        pinsRemaining-=throw1;

        int throw2 = rand.nextInt(pinsRemaining+1);

        if(throw1+throw2==10)
        {
            return 15;
        }

        return throw1+throw2;

    }

}
