
public class PredatorPrey
{
    public static void main(String[] args)
    {
        System.out.print('\u000C');
        double tMax = 100.0;
        double dt = .01;
        double x = 100; //prey
        double y = 5; //predator
        double alpha = .3; //prey birth rate
        double beta = .0005; //prey death rate
        double delta = .1;//preadator birth rate
        double gamma = .01;//predator death rate
        for(double t = 0; t < tMax; t = t+dt)
        {
          if((int)(t*(1.0/dt))/(1.0/dt)%1.0 == 0)
          {
              System.out.println("\t" + ((Math.round(t*10))/10.0)+ "\t" + x+ "\t" +y );
            }
          x = x + (alpha*x-beta*x*y)*dt;
          y = y + (delta*x*y-gamma*y)*dt;
        }
    }
}

