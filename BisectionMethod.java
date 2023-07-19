
public class BisectionMethod
{
    public static double f(double x)
    {
        return(x*x*x - 3*x + 20);
        //return Math.sin(x);
    }
    public static double fPrime(double x)
    {
        return (3*x*x - 3);
        //return Math.cos(x);
    }
    public static double midpoint(double a, double b)
    {
        return (a+b)/2;
    }
    public static void main(String[] args)
    {
        System.out.print('\u000C');
        double a = -4;
        double b = 1;
        
        double eps = 1e-10;
        int MaxTimes = 100;
        if((f(a) >= 0 && f(b) >= 0) || (f(a) <= 0 && f(b) <= 0))
        {
            System.out.println("f(a) and f(b) have the same sign");
            System.exit(0);
        }
        while(Math.abs(f(midpoint(a,b))) >= eps)
        {
            double c = midpoint(a,b);
            if((f(c) >= 0 && f(a) >= 0) || (f(c) <= 0 && f(a) <= 0))
            {
                a = c;
            }else
            {
                b = c;
            }
            MaxTimes--;
            if(MaxTimes <= 0)
            {
                a = 0;
                b = 0;
            }
            System.out.println(midpoint(a,b));
        }
        
    }
}
