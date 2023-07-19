public class NumericalIntegration
{
    public static double f(double x)
    {
        return x*x;
    }
    public static double leftRiemannSum(double dx, double xMin,double xMax)
    {
        double out = 0;
        for(double i = xMin; i < xMax; i += dx)
        {
            out += f(i);
        }
        out *= dx;
        return out;
    }
    public static double trapazoidalSum(double dx, double xMin,double xMax)
    {
        double out = 0;
        for(double i = xMin; i <= xMax; i += dx)
        {
            if(i == xMin || i == xMin)
            {
                out += f(i);
            }else
            {
                out += 2*f(i);
            }
        }
        out *= dx/2;
        return out;
    }
    public static double simpsonsOneThirdRule(double dx, double xMin,double xMax)
    {
        double out = 0;
        int index = 0;
        for(double i = xMin; i <= xMax; i += dx)
        {
            if(i == xMin || i == xMin)
            {
                out += f(i);
            }else if(index % 2 == 0)
            {
                out += 2*f(i);
            }else
            {
                out += 4*f(i);
            }
            index++;
        }
        out *= dx/3;
        return out;
    }
    public static double simpsonsThreeEightsRule(double dx, double xMin,double xMax)
    {
        double out = 0;
        int index = 0;
        for(double i = xMin; i <= xMax; i += dx)
        {
            if(i == xMin || i == xMin)
            {
                out += f(i);
            }else if(index % 3 == 0)
            {
                out += 2*f(i);
            }else
            {
                out += 3*f(i);
            }
            index++;
        }
        out *= 3*dx/8;
        return out;
    }
    public static double simpsonsTwoFortyFifthsRule(double dx, double xMin,double xMax)
    {
        double out = 0;
        int index = 0;
        for(double i = xMin; i <= xMax; i += dx)
        {
            if(i == xMin || i == xMin)
            {
                out += 7*f(i);
            }else if(index %  2 == 0)
            {
                out += 32*f(i);
            }else if(index % 4 == 1)
            {
                out += 14*f(i);
            }else
            {
                out += 12*f(i);
            }
            index++;
        }
        out *= 2*dx/45;
        return out;//May be wrong
    }
    public static void main(String[] args)
    {
        System.out.print('\u000C');
        double actualIntegral = 8.0/3;
        double dx = .01;
        double xMin = 0;
        double xMax = 2;
        System.out.println("Left Riemann Sum: " +
        leftRiemannSum(dx,xMin,xMax));
        System.out.println("Trapazoidal Sum: " +
        trapazoidalSum(dx,xMin,xMax));
        System.out.println("Simpson's 1/3 Rule: " +
        simpsonsOneThirdRule(dx,xMin,xMax));
        System.out.println("Simpson's 3/8 Rule: " +
        simpsonsThreeEightsRule(dx,xMin,xMax));
        System.out.println("Simpson's 2/45 Rule: " +
        simpsonsTwoFortyFifthsRule(dx,xMin,xMax));
        System.out.println("Actual Integral: " +
        actualIntegral);
    }
}

