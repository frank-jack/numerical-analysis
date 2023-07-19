import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.*;
import java.util.*;
import java.awt.Dialog;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.io.*;
public class GaussianElimination 
{
    ArrayList<ArrayList<Double>> matrix = new ArrayList<ArrayList<Double>>();
    int termNum = 3;
    public GaussianElimination()
    {
        
    }
    public static void main(String[] args)
    {
        System.out.print('\u000C');
        GaussianElimination ge = new GaussianElimination();
        Scanner sc = new Scanner(System.in);
        System.out.println("Select 1 for Random and 0 for Visual.");
        int temp = sc.nextInt();
        int c = 0;
        while(true && c == 0)
        {
            if(temp == 1)
            {
                System.out.println("Select Term Number.");
                ge.termNum = sc.nextInt();
                ge.matrix = fillRandom(ge.matrix,ge.termNum);
                c = 1;
            }else
            {
                ge.matrix = fillVisual(ge.matrix,ge.termNum);
                
                ge.termNum = ge.matrix.size();
            }
            System.out.println("Intial System: ");
            System.out.println(ge.matrix);
            printThis(ge.matrix);
            ArrayList<ArrayList<Double>> matrixCopy = new ArrayList<ArrayList<Double>>();
            matrixCopy.clear();
            ge.termNum = ge.matrix.size();
            System.out.println("TN = " + ge.termNum);
            System.out.println("Size" + ge.matrix.size());
            for(int i = 0; i < ge.termNum; i++)
            {
                matrixCopy.add(i,new ArrayList<Double>());
                for(int j = 0; j <= ge.termNum; j++)
                {
                    System.out.println("MATRIX"  + ge.matrix);
                    matrixCopy.get(i).add(j,ge.matrix.get(i).get(j));
                }
            }
            System.out.println(gaussianElimination(ge.matrix,ge.termNum));
            ge.matrix.clear();
            for(int i = 0; i<ge.termNum; i++)
            {
                ge.matrix.add(i,new ArrayList<Double>());
                for(int j = 0; j<=ge.termNum; j++)
                {
                    ge.matrix.get(i).add(j,matrixCopy.get(i).get(j));
                }
            }
        }
    }
    public static ArrayList<ArrayList<Double>> fillRandom(ArrayList<ArrayList<Double>> matrix,int termNum)
    {
        for(int i = 0; i<termNum; i++)
        {
            matrix.add(i,new ArrayList<Double>());
            for(int j = 0; j<=termNum; j++)
            {
                matrix.get(i).add(j,(int)(Math.random()*21)-10.0);
            }
        }
        return matrix;
    }
    public static ArrayList<ArrayList<Double>> fillVisual(ArrayList<ArrayList<Double>> matrix,int termNum)
    {
        MatrixInput mi = new MatrixInput(termNum);
        if(matrix.isEmpty())
        {
            for(int i = 0; i<termNum; i++)
            {
                matrix.add(i,new ArrayList<Double>());
                for(int j = 0; j<=termNum; j++)
                {
                    matrix.get(i).add(j,0.0);
                }
            }
        }
        mi.display(matrix,termNum);
        matrix = mi.enter(matrix,termNum);
        System.out.println(matrix);
        mi.dispose();
        return matrix;
    }
    public static ArrayList<Double> gaussianElimination(ArrayList<ArrayList<Double>> matrix,int termNum)
    {
        double temp1;
        double temp2;
        int c = 0;
        if(termNum < 2)
        {
            System.out.println("Error. The system must have at least 2 terms.");
            System.exit(0);
        }
        for(int i = 0; i < termNum; i++)
        {
            for(int j = 0; j < termNum; j++)
            {
                if(matrix.get(j).get(i) == 0)
                {
                    c++;
                }
            }
            if(c >= termNum)
            {
                System.out.println("Error. Not enough terms have non-zero values.");
                System.exit(0);
            }
            c = 0;
        }
        c = 0;
        for(int i = 0; i < termNum; i++)
        {
            for(int j = 0; j < termNum; j++)
            {
                if(matrix.get(i).get(j) == 0)
                {
                    c++;
                }
            }
            if(c >= termNum)
            {
                System.out.println("Error. At least 1 equation has no non-zero constants on any term.");
                System.exit(0);
            }
            c = 0;
        }
        ArrayList<ArrayList<Double>> matrixCopy = new ArrayList<ArrayList<Double>>();
        for(int i = 0; i<termNum; i++)
        {
            matrixCopy.add(i,new ArrayList<Double>());
            for(int j = 0; j<=termNum; j++)
            {
                matrixCopy.get(i).add(j,matrix.get(i).get(j));
            }
        }
        for(int i = 0; i < termNum; i++)
        {
            for(int j = 0; j <= termNum; j++)
            {
                for(int l = 0; matrixCopy.get(i).get(l) != 0 && l < termNum; l++)
                {
                    matrixCopy.get(i).set(j,matrixCopy.get(i).get(j)/matrixCopy.get(i).get(l));
                }
            }
        }
        for(int i = 0; i < termNum; i++)
        {
            for(int j = i+1; j < termNum; j++)
            {
                if(matrixCopy.get(i).equals(matrixCopy.get(j)))
                {
                    System.out.println("Error. At least 2 equations are equal.");
                    System.exit(0);
                }
            }
        }
        for(int l = 0; l<termNum; l++)
        {
            for(int j = 0; j<termNum; j++)
            {
                if(j == l)
                {
                    j++;
                }
                c = 0;
                while(matrix.get(l).get(l) == 0 || matrix.get(j).get(l) == 0)
                {
                    if(matrix.get(l).get(l) == 0)
                    {
                        for(int i = 0; i<=termNum; i++)
                        {
                            for(int h = 0; h < termNum; h++)
                            {
                                matrix.get(h).set(i,matrix.get(j+c).get(i)+matrix.get(h).get(i));
                            }
                        }
                    }
                    if(matrix.get(j).get(l) == 0)
                    {
                        for(int i = 0; i<=termNum; i++)
                        {
                            for(int h = 0; h < termNum; h++)
                            {
                                matrix.get(h).set(i,matrix.get(l+c).get(i)+matrix.get(h).get(i));
                            }
                        }
                    }
                    c++;
                    if(c+l == termNum || c+j == termNum)
                    {
                        c = 0;
                    }
                }
                temp1 = matrix.get(j).get(l);
                temp2 = -matrix.get(l).get(l);
                for(int i = 0; i<=termNum; i++)
                {
                    matrix.get(l).set(i,temp1*matrix.get(l).get(i));
                    matrix.get(j).set(i,temp2*matrix.get(j).get(i));
                }
                for(int i = 0; i<=termNum; i++)
                {
                    matrix.get(j).set(i,matrix.get(l).get(i)+matrix.get(j).get(i));
                    matrix.get(l).set(i,matrix.get(l).get(i)/temp1);
                    matrix.get(j).set(i,matrix.get(j).get(i)/temp2);
                }
                if(j == termNum-2 && l == termNum-1)
                {
                    j++;
                }
            }
        }
        for(int i = 0; i<termNum; i++)
        {
            for(int j = 0; j<termNum; j++)
            {
                if(j != i)
                {
                    matrix.get(i).set(j,0.0);
                }
            }
        }
        printThis(matrix);
        ArrayList<Double> output = new ArrayList<Double>();
        for(int i = 0; i<termNum; i++)
        {
            output.add(i,matrix.get(i).get(termNum)*1.0/matrix.get(i).get(i));
        }
        return output;
    }
    public static void printThis(ArrayList<ArrayList<Double>> matrix)
    {
        System.out.println();
        System.out.println("Current Matrix: ");
        for(ArrayList<Double> i : matrix)
        {
            for(Double j : i) 
            {
                if (j < 0.00000001 && j > -0.00000001)
                {
                    j = 0.0;  
                }
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }
}
