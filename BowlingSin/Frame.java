package BowlingSin;

import java.util.Random;
public class Frame
{
    Random rand;

    public Frame()
    {

        rand = new Random();

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
