import java.util.*;
public class ProbabilityGame
{
    public static void main(String[] args)
    {
        System.out.print('\u000C');
        System.out.println(smallGame());
        System.out.println(game());
    }
    public static double smallGame()
    {
        double sum = 0;
        int times = 0;
        for(int i = 0; i< 100000; i++)
        {
            double lSum = 0;
            int c = 0;
            while(lSum != 1)
            {
                double r = Math.random();
                if(r < .5)
                {
                    lSum += .25;
                }
                c++;
            }
            sum += c;
            times++;
        }
        return sum/times;
    }
    public static double game()
    {
        double sum = 0;
        int times = 0;
        for(int i = 0; i< 100000; i++)
        {
            double lSum = 0;
            int c = 0;
            while(lSum < 1)
            {
                double r = Math.random();
                lSum += r;
                c++;
            }
            sum += c;
            times++;
        }
        return sum/times;
    }
}
