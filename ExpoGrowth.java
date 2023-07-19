public class ExpoGrowth
{
    public static void main(String[] args)
    {
        System.out.print('\u000C');
        double n = 1000;
        double a = .1;
        double dt = .01;
        //double t = 0;
        double tMAX = 10;
        for(double t = 0; t < tMAX; t = t + dt)
        {
            System.out.println("Time: " +((Math.round(t*100))/100.0)+ "\tData: "+n);
   
            n = n + a*n*dt;
            //t = (int)(1000*(t + dt))/1000.0;
        }
    }
}
