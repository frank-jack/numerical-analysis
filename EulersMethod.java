import java.util.*;
public class EulersMethod
{
    //private static double x = 0;
    //private static double y = 1;
    //private static double xFinal = 2;
    //private static int stepNum = 4;
    //private static double stepSize = xFinal/(double)stepNum;
    
    public static double dydx(double x,double y)
    {
        return 2*x-y;
    }
    public static double euler(double x,double y,double stepSize)
    {
        double m = dydx(x,y);
        return m * ((x + stepSize) - x) + y;
    }
    public static void main(String[] args)
    {
        System.out.print('\u000C');
        Scanner sc = new Scanner(System.in);
        System.out.println("Starting x-value?");
        double x = sc.nextDouble();
        System.out.println("Starting y-value?");
        double y = sc.nextDouble();
        System.out.println("Final x-value?");
        double xFinal = sc.nextDouble();
        System.out.println("Number of Steps?");
        int stepNum = sc.nextInt();
        double stepSize = xFinal/(double)stepNum;
        for(int i = 0; i < stepNum; i++)
        {
            y = euler(x,y,stepSize);
            x = x + stepSize;
        }
        System.out.println("f(" + xFinal + ") = " + y);
    }
}
