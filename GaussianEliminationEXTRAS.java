import java.util.*;
public class GaussianEliminationEXTRAS
{
    public static void main(String[] args)
    {
        System.out.print('\u000C');
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of terms and equations the system will have (Minimum of 2).");
        int termNum = sc.nextInt();
        if(termNum < 2)
        {
            System.out.println("Error. The system must have at least 2 terms.");
            System.exit(0);
        }
        ArrayList<ArrayList<Double>> matrix = new ArrayList<ArrayList<Double>>();
        System.out.println("If you want random integers -10-10, enter 0. If you want to enter your own numbers, enter 1.");
        int d = sc.nextInt();
        int e = 0;
        if(d == 100)
        {
            d = 0;
            e = 1;
        }else if(d == 1000)
        {
            d = 1;
            e = 1;
        }
        if(d == 0)
        {
            matrix = fillRandom(matrix,termNum);
        }else if(d == 1)
        {
            matrix = fillVisual(matrix,termNum);
        }else if(e == 1)
        {
            matrix = fillScanner(matrix,termNum);
        }
        System.out.println("Intial System: ");
        System.out.println(matrix);
        printThis(matrix);
        if(e == 0)
        {
            System.out.println(gaussianElimination(matrix,termNum));
        }else
        {
            gaussianEliminationDebugger(matrix,termNum);
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
    public static ArrayList<ArrayList<Double>> fillScanner(ArrayList<ArrayList<Double>> matrix,int termNum)
    {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i<termNum; i++)
        {
            matrix.add(i,new ArrayList<Double>());
            for(int j = 0; j<=termNum; j++)
            {
                if(j != termNum)
                {
                    System.out.println("Input next coefficient.");
                }else
                {
                    System.out.println("Input equation Constant.");
                }
                double n = sc.nextDouble();
                matrix.get(i).add(j,n);
                }
            System.out.println("Next Equation.");
        }
        return matrix;
    }
    public static ArrayList<ArrayList<Double>> fillVisual(ArrayList<ArrayList<Double>> matrix,int termNum)
    {
        MatrixInput mi = new MatrixInput(termNum);
        for(int i = 0; i<termNum; i++)
        {
            matrix.add(i,new ArrayList<Double>());
            for(int j = 0; j<=termNum; j++)
            {
                matrix.get(i).add(j,0.0);
            }
        }
        mi.display(matrix,termNum);
        matrix = mi.enter(matrix,termNum);
        return matrix;
    }
    public static ArrayList<Double> gaussianElimination(ArrayList<ArrayList<Double>> matrix,int termNum)
    {
        double temp1;
        double temp2;
        int c = 0;
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
    public static ArrayList<Double> gaussianEliminationDebugger(ArrayList<ArrayList<Double>> matrix,int termNum)
    {
        double temp1;
        double temp2;
        int c = 0;
        //check if a term is non-existant
        printThis(matrix);
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
        //checks if one equation is all 0s
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
        //checks if two equations are equal
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
        //Multiplication and Subtraction with loop
        for(int l = 0; l<termNum; l++)
        {
            for(int j = 0; j<termNum; j++) 
            {
                if(j == l)
                {
                    j++;
                }
                //0 handling LOOK HERE
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
                }//end of while
                //multiplication
                temp1 = matrix.get(j).get(l);
                temp2 = -matrix.get(l).get(l);
                for(int i = 0; i<=termNum; i++)
                {
                    matrix.get(l).set(i,temp1*matrix.get(l).get(i));
                    matrix.get(j).set(i,temp2*matrix.get(j).get(i));
                    System.out.println(matrix.get(l).get(i));
                    System.out.println(matrix.get(j).get(i));
                }
                printThis(matrix);
                //subtraction and divsion
                for(int i = 0; i<=termNum; i++)
                {
                    matrix.get(j).set(i,matrix.get(l).get(i)+matrix.get(j).get(i));
                    matrix.get(l).set(i,matrix.get(l).get(i)/temp1);
                    matrix.get(j).set(i,matrix.get(j).get(i)/temp2);
                }
                printThis(matrix);
                if(j == termNum-2 && l == termNum-1)
                {
                    j++;
                }
            }
        }//end of for l
        //fill zeros
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
        //Output
        printThis(matrix);
        for(int i = 0; i<termNum; i++)
        {
            System.out.println("Variable "+ (i+1) +" = "+ matrix.get(i).get(termNum)*1.0/matrix.get(i).get(i));
        }
        ArrayList<Double> output = new ArrayList<Double>();
        for(int i = 0; i<termNum; i++)
        {
            output.add(i,matrix.get(i).get(termNum)*1.0/matrix.get(i).get(i));
        }
        System.out.println(output);
        return output;
    }
    public static void printThis(ArrayList<ArrayList<Double>> matrixL)
    {
        System.out.println();
        System.out.println("Current Matrix: ");
        for(ArrayList<Double> i : matrixL)
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
/*
        //term(1)
        //multplication 1 by 2 term(1)
        temp1 = matrix.get(1).get(0);
        temp2 = -matrix.get(0).get(0);
        for(int i = 0; i<=termNum; i++)
        {
            matrix.get(0).set(i,temp1*matrix.get(0).get(i));
            matrix.get(1).set(i,temp2*matrix.get(1).get(i));
            System.out.println(matrix.get(0).get(i));
            System.out.println(matrix.get(1).get(i));
        }
        System.out.println(matrix);
        
        //subtraction 1 by 2 term(1)
        for(int i = 0; i<=termNum; i++)
        {
            matrix.get(1).set(i,matrix.get(0).get(i)+matrix.get(1).get(i));
        }
        System.out.println(matrix);
        
        //multplication 1 by 3 term(1)
        temp1 = matrix.get(2).get(0);
        temp2 = -matrix.get(0).get(0);
        for(int i = 0; i<=termNum; i++)
        {
            matrix.get(0).set(i,temp1*matrix.get(0).get(i));
            matrix.get(2).set(i,temp2*matrix.get(2).get(i));
            System.out.println(matrix.get(0).get(i));
            System.out.println(matrix.get(2).get(i));
        }
        System.out.println(matrix);
        
        //subtraction 1 by 3 term(1)
        for(int i = 0; i<=termNum; i++)
        {
            matrix.get(2).set(i,matrix.get(0).get(i)+matrix.get(2).get(i));
        }
        System.out.println(matrix);
        
        //term(2)
        //multplication 2 by 1 term(2)
        temp1 = matrix.get(0).get(1);
        temp2 = -matrix.get(1).get(1);
        for(int i = 0; i<=termNum; i++)
        {
            matrix.get(1).set(i,temp1*matrix.get(1).get(i));
            matrix.get(0).set(i,temp2*matrix.get(0).get(i));
            System.out.println(matrix.get(1).get(i));
            System.out.println(matrix.get(0).get(i));
        }
        System.out.println(matrix);
        
        //subtraction 2 by 1 term(2)
        for(int i = 0; i<=termNum; i++)
        {
            matrix.get(0).set(i,matrix.get(1).get(i)+matrix.get(0).get(i));
        }
        System.out.println(matrix);
        
        //multplication 2 by 3 term(2)
        temp1 = matrix.get(2).get(1);
        temp2 = -matrix.get(1).get(1);
        for(int i = 0; i<=termNum; i++)
        {
            matrix.get(1).set(i,temp1*matrix.get(1).get(i));
            matrix.get(2).set(i,temp2*matrix.get(2).get(i));
            System.out.println(matrix.get(1).get(i));
            System.out.println(matrix.get(2).get(i));
        }
        System.out.println(matrix);
        
        //subtraction 2 by 3 term(2)
        for(int i = 0; i<=termNum; i++)
        {
            matrix.get(2).set(i,matrix.get(1).get(i)+matrix.get(2).get(i));
        }
        System.out.println(matrix);
        
        //term(3)
        //multplication 3 by 1 term(3)
        temp1 = matrix.get(0).get(2);
        temp2 = -matrix.get(2).get(2);
        for(int i = 0; i<=termNum; i++)
        {
            matrix.get(2).set(i,temp1*matrix.get(2).get(i));
            matrix.get(0).set(i,temp2*matrix.get(0).get(i));
            System.out.println(matrix.get(2).get(i));
            System.out.println(matrix.get(0).get(i));
        }
        System.out.println(matrix); 
        
        //subtraction 3 by 1 term(3)
        for(int i = 0; i<=termNum; i++)
        {
            matrix.get(0).set(i,matrix.get(2).get(i)+matrix.get(0).get(i));
        }
        System.out.println(matrix);
        
        //multplication 3 by 2 term(3)
        temp1 = matrix.get(1).get(2);
        temp2 = -matrix.get(2).get(2);
        for(int i = 0; i<=termNum; i++)
        {
            matrix.get(2).set(i,temp1*matrix.get(2).get(i));
            matrix.get(1).set(i,temp2*matrix.get(1).get(i));
            System.out.println(matrix.get(2).get(i));
            System.out.println(matrix.get(1).get(i));
        }
        System.out.println(matrix);
        
        //subtraction 3 by 2 term(3)
        for(int i = 0; i<=termNum; i++)
        {
            matrix.get(1).set(i,matrix.get(2).get(i)+matrix.get(1).get(i));
        }
        System.out.println(matrix);
        
        //Output
        for(int i = 0; i<termNum; i++)
        {
            System.out.println("Variable "+ (i+1) +" = "+ matrix.get(i).get(termNum)*1.0/matrix.get(i).get(i));
        }
        */
       
        //extra code for getting rid of term with no values
        /*for(int j = 0; j < termNum; j++)
                {
                    matrix.get(j).remove(i);
                }
                System.out.println(matrix);
                matrix.remove(termNum-1);
                termNum--;
                i = termNum;
                System.out.println(matrix);*/
                
        /*matrix.add(0,new ArrayList<Double>());
        matrix.add(1,new ArrayList<Double>());
        matrix.add(2,new ArrayList<Double>());
        matrix.get(0).add(0,0.0);
        matrix.get(0).add(1,1.0);
        matrix.get(0).add(2,1.0);
        matrix.get(0).add(3,1.0);
        matrix.get(1).add(0,0.0);
        matrix.get(1).add(1,1.0);
        matrix.get(1).add(2,1.0);
        matrix.get(1).add(3,1.0);
        matrix.get(2).add(0,0.0);
        matrix.get(2).add(1,1.0);
        matrix.get(2).add(2,1.0);
        matrix.get(2).add(3,1.0);
        System.out.println(matrix);*/
