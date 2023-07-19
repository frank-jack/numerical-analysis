import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.*;
import java.util.*;
import java.awt.Dialog;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.io.*;
public class CubicSpline extends Frame implements ActionListener, MouseListener, MouseMotionListener, WindowListener
{
    int width = 800;
    int height = 800;
    int xOffset = width/2;
    int yOffset = height/2;
    double xScale = xOffset/10;
    double yScale = yOffset/10;
    double xStart = -xOffset/xScale;
    double xEnd = xOffset/xScale;
    int n = 5;
    double h = (xEnd-xStart)/n;
    double xMax,yMax;
    ArrayList<ArrayList<Double>> matrix = new ArrayList<ArrayList<Double>>();
    ArrayList<Double> a = new ArrayList<Double>();
    ArrayList<Double> b = new ArrayList<Double>();
    ArrayList<Double> c = new ArrayList<Double>();
    ArrayList<Double> d = new ArrayList<Double>();
    JFrame frame = new JFrame();
    static int num;
    String func;
    int x1,x2,y1,y2;
    public static double f(double x)
    {
        if(num == 1)
        {
            return x;
        }else if(num == 2)
        {
            return Math.floor(x);
        }else if(num == 3)
        {
            return Math.sin(x);
        }else if(num == 4)
        {
            return Math.tan(x);
        }else if(num == 5)
        {
            return Math.exp(x);
        }else if(num == 6)
        {
            return Math.abs(x);
        }else if(num == 7)
        {
            return 1/(x+1);
        }else
        {
            return 0;
        }
    }
    public CubicSpline(String title)
    {
        super(title);
        setMenus();
        setLayout(null);
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    public void setMenus()
    {
        MenuBar mBar = new MenuBar();
        Menu menu1 = new Menu("File");
        menu1.add("Save");
        menu1.add("Exit");
        Menu menu2 = new Menu("Function Menu");
        menu2.add("Current Function");
        menu2.add("Function 1: x");
        menu2.add("Function 2: [x]");
        menu2.add("Function 3: sin(x)");
        menu2.add("Function 4: tan(x)");
        menu2.add("Function 5: e^x");
        menu2.add("Function 6: |x|");
        menu2.add("Function 7: 1/(x+1)");
        menu2.add("Erase");
        Menu menu3 = new Menu("Scale");
        menu3.add("n Value");
        menu3.add("x Scale");
        menu3.add("y Scale");
        mBar.add(menu1);
        mBar.add(menu2);
        mBar.add(menu3);
        menu1.addActionListener(this);
        menu2.addActionListener(this);
        menu3.addActionListener(this);
        setMenuBar(mBar);
    }
    public void actionPerformed(ActionEvent ae)
    {
        Object s = ae.getActionCommand();
        if(s.equals("Exit"))
        {
            System.exit(0);
        }
        if(s.equals("Save"))
        {
            try
            {
                saveFile();
                System.out.println("Saved.");
            }catch(Exception e)
            {
                System.out.println("Error Saving.");
            }
        }
        if(s.equals("Current Function"))
        {
            num = 0;
            func = "";
        }
        if(s.equals("Function 1: x"))
        {
            num = 1;
            func = "x";
        }
        if(s.equals("Function 2: [x]"))
        {
            num = 2;
            func = "[x]";
        }
        if(s.equals("Function 3: sin(x)"))
        {
            num = 3;
            func = "sin(x)";
        }
        if(s.equals("Function 4: tan(x)"))
        {
            num = 4;
            func = "tan(x)";
        }
        if(s.equals("Function 5: e^x"))
        {
            num = 5;
            func = "e^x";
        }
        if(s.equals("Function 6: |x|"))
        {
            num = 6;
            func = "|x|";
        }
        if(s.equals("Function 7: 1/(x+1)"))
        {
            num = 7;
            func = "1/(x+1)";
        }
        if(s.equals("n Value"))
        {
            String temp = JOptionPane.showInputDialog(frame,"Input n");
            n = (int)Double.parseDouble(temp);
            h = (xEnd-xStart)/n;
        }
        if(s.equals("x Scale"))
        {
            String temp = JOptionPane.showInputDialog(frame,"Input x");
            xScale = xOffset/(int)Double.parseDouble(temp);
            graphGrid();
            xStart = -xOffset/xScale;
            xEnd = xOffset/xScale;
            h = (xEnd-xStart)/n;
        }
        if(s.equals("y Scale"))
        {
            String temp = JOptionPane.showInputDialog(frame,"Input y");
            yScale = yOffset/(int)Double.parseDouble(temp);
            graphGrid();
        }
        if(s.equals("Erase"))
        {
            System.out.println("Erased.");
            n = 10;
            xScale = xOffset/10;
            yScale = yOffset/10;
            graphGrid();
        }else
        {
            calculateSpline();
            graph();
            print();
        }
    }
    public static void main(String[] args)
    {
        System.out.print('\u000C');
        CubicSpline spline = new CubicSpline("Cubic Splines");
        spline.setSize(spline.width,spline.height);
        spline.setVisible(true);
        spline.setResizable(false);
        spline.graphGrid();
    }
    public void calculateSpline() 
    {
        matrix.clear();
        a.clear();
        b.clear();
        c.clear();
        d.clear();
        matrix.add(0,new ArrayList<Double>());
        for(int i = 0; i < n + 2; i++)
        {
            if(i == 0)
            {
                matrix.get(0).add(i,1.0);
            }else
            {
                matrix.get(0).add(i,0.0);
            }
        }
        for(int i = 1; i < n; i++)
        {
            matrix.add(i,new ArrayList<Double>());
            for(int j = 0; j < n + 2; j++)
            {
                if(j == i - 1 || j == i + 1)
                {
                    matrix.get(i).add(j,h);
                }else if(j == i)
                {
                    matrix.get(i).add(j,4*h);
                }else if(j == n + 1)
                {
                    matrix.get(i).add(j,(3.0/h*(f(xStart+(i-1)*h) + -2.0*f(xStart+(i)*h) + f(xStart+(i+1)*h))));
                }else
                {
                    matrix.get(i).add(j,0.0);
                }
            }
        }
        matrix.add(n,new ArrayList<Double>());
        for(int i = 0; i < n + 2; i++)
        {
            if(i == n)
            {
                matrix.get(n).add(i,1.0);
            }else
            {
                matrix.get(n).add(i,0.0);
            }
        }
        System.out.print('\u000C');
        GaussianElimination.printThis(matrix);
        c = GaussianElimination.gaussianElimination(matrix,n + 1);
        for(int i = 0; i < n + 1; i++)
        {
            a.add(f(xStart+i*h));
        }
        for(int i = 0; i < n; i++)
        {
            b.add((a.get(i+1)-a.get(i))/h - h*(c.get(i+1)+2.0*c.get(i))/3.0);
            d.add((c.get(i+1)-c.get(i))/(3.0*h));
        }
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }
    public double getSplineValue(double x)
    {
        for(int i = 0; i < n; i++)
        {
            if(x >= xStart+i*h && x < (xStart+(i*h+h)))
            {
                return a.get(i)+ 
                b.get(i)*(x - (xStart + i*h))+ 
                c.get(i)*(x - (xStart + i*h))*(x - (xStart + i*h))+
                d.get(i)*(x - (xStart + i*h))*(x - (xStart + i*h))*(x - (xStart + i*h));
            }
        }
        return 0;
    }
    public void print()
    {
        for(int i = 0; i < n; i++)
        {
            System.out.println("Spline from x = "+String.format("%,.4f",(xStart + i*h))+
            " to x = "+String.format("%,4f",(xStart + (i+1)*h))+": "+
            String.format("%,.4f",a.get(i))+" + "+
            String.format("%,.4f",b.get(i))+"*"+"(x - "+String.format("%,.4f",(xStart + i*h))+") + "+
            String.format("%,.4f",c.get(i))+"*"+"(x - "+String.format("%,.4f",(xStart + i*h))+")^2 + "+
            String.format("%,.4f",d.get(i))+"*"+"(x - "+String.format("%,.4f",(xStart + i*h))+")^3");
        }
    }
    public void graphGrid()
    {
        Graphics g = getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,width,height);
        g.setColor(Color.black);
        g.fillRect(0,height/2-2,width,4);
        g.fillRect(width/2-2,0,4,height);
        g.setColor(Color.gray);
        xMax = width/xScale/2;
        yMax = height/yScale/2;
        for(int x = -1*(int)xMax; x <= (int)xMax; x++)
        {
            g.drawLine(width/2+(int)xScale*x,0,width/2+(int)xScale*x,height);
        }
        for(int y = -1*(int)yMax; y <= (int)yMax; y++)
        {
            g.drawLine(0,height/2+(int)yScale*y,width,height/2+(int)yScale*y);
        }
    }
    public void graph()
    {
        Graphics g = getGraphics();
        graphGrid();
        for(double x = xStart; x < xEnd; x += .001)
        {
            g.setColor(Color.blue);
            g.drawOval((int)(x*xScale+xOffset),(int)(yOffset-f(x)*yScale),4,4);
            g.setColor(Color.red);
            g.drawOval((int)(x*xScale+xOffset),(int)(yOffset-getSplineValue(x)*yScale),2,2);
        }
    }
    public void saveFile() throws IOException
    {
        String fileName = "(" + func + ")with(" + n + ")splinesfrom(" + xStart + ")to(" + xEnd + ").txt";
        File outFile = new File(fileName);
        FileWriter fw = new FileWriter(outFile);
        PrintWriter pw = new PrintWriter(fw);
        for(int i = 0; i < n; i++)
        {
            pw.println("Spline from x = "+String.format("%,.4f",(xStart + i*h))+
            " to x = "+String.format("%,4f",(xStart + (i+1)*h))+": "+
            String.format("%,.4f",a.get(i))+" + "+
            String.format("%,.4f",b.get(i))+"*"+"(x - "+String.format("%,.4f",(xStart + i*h))+") + "+
            String.format("%,.4f",c.get(i))+"*"+"(x - "+String.format("%,.4f",(xStart + i*h))+")^2 + "+
            String.format("%,.4f",d.get(i))+"*"+"(x - "+String.format("%,.4f",(xStart + i*h))+")^3");
        }
        pw.close();
    }
    public void mouseClicked(MouseEvent me)
    {
        Graphics g = getGraphics();
        x1 = me.getX();
        y2 = me.getY();
        g.drawString("("+String.valueOf((x1-xOffset)/xScale)+","+String.valueOf((y1-yOffset)/yScale)+")",x1,y1);
    }
    public void mouseDragged(MouseEvent me)
    {
        x2 = me.getX();
        y2 = me.getY();
        if(x2 > x1)
        {
            xStart = (x1-xOffset)/xScale;
            xEnd = (x2-xOffset)/xScale;
        }else
        {
            xEnd = (x1-xOffset)/xScale;
            xStart = (x2-xOffset)/xScale;
        }
        if(xEnd - xStart < .0001)
        {
            xStart = -xOffset/xScale;
            xEnd = xOffset/xScale;
        }
        h = (xEnd-xStart)/n;
        calculateSpline();
        graph();
        print();
    }
    public void mousePressed(MouseEvent me)
    {
        x1 = me.getX();
        y1 = me.getY();
    }
    public void mouseReleased(MouseEvent me)
    {
        x2 = me.getX();
        y2 = me.getY();
        if(x2 > x1)
        {
            xStart = (x1-xOffset)/xScale;
            xEnd = (x2-xOffset)/xScale;
        }else
        {
            xEnd = (x1-xOffset)/xScale;
            xStart = (x2-xOffset)/xScale;
        }
        if(xEnd - xStart < .0001)
        {
            xStart = -xOffset/xScale;
            xEnd = xOffset/xScale;
        }
        h = (xEnd-xStart)/n;
        calculateSpline();
        graph();
        print();
    }
    public void windowClosing(WindowEvent we)
    {
        System.exit(0);
    }
    public void mouseMoved(MouseEvent me){}
    public void mouseExited(MouseEvent me){}
    public void mouseEntered(MouseEvent me){}
    public void windowActivated(WindowEvent we){}
    public void windowOpened(WindowEvent we){}
    public void windowIconified(WindowEvent we){}
    public void windowClosed(WindowEvent we){}
    public void windowDeactivated(WindowEvent we){}
    public void windowDeiconified(WindowEvent we){}
}