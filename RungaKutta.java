
public class RungaKutta
{
  public static void main(String[] args)
    {
        System.out.print('\u000C');
        double n = 1000;
        double a = .1;
        double dt = .01;
        //double t = 0;
        double tMAX = 10;
        double k1,k2,k3,k4;
        for(double t = 0; t < tMAX; t = t + dt)
        {
            System.out.println("Time: " +((Math.round(t*100))/100.0)+ "\tData: "+n);
            k1 = a * n;
            k2 = a*(n+(k1/2)*dt);
            k3 = a*(n+(k2/2)*dt);
            k4 = a*(n+k3*dt);
            n = n + (1.0/6)*(k1+2*k2+2*k3+k4)*dt;
            //n = n + a*n*dt;
            //t = (int)(1000*(t + dt))/1000.0;
        }
        
    }
}
