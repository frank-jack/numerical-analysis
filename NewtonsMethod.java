public class NewtonsMethod
{
    public static double f(double x)
    {
        //return(x*x*x - 3*x + 20);
        return Math.sin(x);
    }
    public static double fPrime(double x)
    {
        //return (3*x*x - 3);
        return Math.cos(x);
    }
    public static double nMethod(double x)
    {
        return x - f(x)/fPrime(x);
    }
    public static void main(String[] args)
    {
        System.out.print('\u000C');
        double x = 10;
        double eps = 1e-10;
        double xOld = x+10*eps;
        int MaxTimes = 100;
        while(Math.abs(x-xOld) > eps)
        {
            xOld = x;
            x = nMethod(x);
            System.out.println(x + " " + xOld);
            MaxTimes--;
            if(MaxTimes <= 0)
            {
                x = 0;
                xOld = 0;
            }
        } 
        
    }
}
