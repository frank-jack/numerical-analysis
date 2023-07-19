
public class ThreeDRiemannSum
{
    static double dx = .1;
    static double dy = .1;
    static double xMin = 0;
    static double xMax = 4;
    static double yMin = 0;
    static double yMax = 3;
    public static double f(double x,double y)
    {
        return Math.sin(x + y) + 1;
    }
    
    public static double ThreeDRec(double dx,double dy,double xMin,double xMax,double yMin,double yMax)
    {
        double volRec = 0;
        for(double x = xMin; x < xMax; x = x + dx)
        {
            for(double y = yMin; y < yMax; y = y + dy)
            {
                volRec = volRec + dx*dy*f(x,y);
            }
        }
        return volRec;
    }
    public static double ThreeDTrap(double dx,double dy,double xMin,double xMax,double yMin,double yMax)
    {
        double volTrap = 0;
        for(double x = xMin; x < xMax; x = x + dx)
        {
            for(double y = yMin; y < yMax; y = y + dy)
            {
                volTrap = volTrap + dx*dy*.25*
                (f(x,y) + f(x + dx,y) +
                f(x,y + dy) + f(x + dx,y + dy));
            }
        }
        return volTrap;
    }
    
    public static void main(String[] args)
    {
        System.out.print('\u000C');
        System.out.println("Rectangle Volume: " + ThreeDRec(dx,dy,xMin,xMax,yMin,yMax));
        System.out.println("Trapazoid Volume: " + ThreeDTrap(dx,dy,xMin,xMax,yMin,yMax));
    }
}
