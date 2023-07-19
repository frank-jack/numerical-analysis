import java.util.*;
public class Determinant
{
    public static void main(String[] args)
    {
        System.out.print('\u000C');
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Double>> matrix = new ArrayList<ArrayList<Double>>();
        System.out.println("Enter the size of the matrix.");
        int size = sc.nextInt();
        if(size < 2)
        {
            System.out.println("Error. The matrix must be at least of size 2.");
            System.exit(0);
        }
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
            for(int i = 0; i<size; i++)
            {
                matrix.add(i,new ArrayList<Double>());
                for(int j = 0; j<size; j++)
                {
                    matrix.get(i).add(j,(int)(Math.random()*21)-10.0);
                }
            }
            System.out.println(matrix);
        }else
        {
            for(int i = 0; i<size; i++)
            {
                matrix.add(i,new ArrayList<Double>());
                for(int j = 0; j<size; j++)
                {
                    System.out.println("Enter number.");
                    double n = sc.nextDouble();
                    matrix.get(i).add(j,n);
                }
                System.out.println("Next row.");
            }
            System.out.println(matrix);
        }
        if(e == 0)
        {
            System.out.println(determinant(matrix));
        }else
        {
            System.out.println(determinantDebugger(matrix));
        }
    }
    public static double determinant(ArrayList<ArrayList<Double>> matrix)
    {
        int size = matrix.size();
        if(size == 2)
        {
            double result = matrix.get(0).get(0)*matrix.get(1).get(1)-
            matrix.get(0).get(1)*matrix.get(1).get(0);
            return result;
        }else
        {
            ArrayList<ArrayList<Double>> temp = new ArrayList<ArrayList<Double>>();
            double sum = 0;
            for(int i = 0; i < size; i++)
            {
                temp.removeAll(temp);
                for(int j = 0; j < size - 1; j++)
                {
                    temp.add(j,new ArrayList<Double>());
                }
                int tempSize = temp.size();
                boolean flag = false;
                int tempRow = -1;
                for(int row = 0; row < tempSize; row++)
                {
                    for(int col = 0; col < tempSize; col++)
                    {
                        if(tempRow != row)
                        {
                            flag = false;
                        }
                        if(col == i)
                        {
                            flag = true;
                            tempRow = row;
                        }
                        if(flag == true)
                        {
                            temp.get(row).add(col, matrix.get(row+1).get(col+1));
                        }else
                        {
                            temp.get(row).add(col, matrix.get(row+1).get(col)); 
                        }
                    }
                }
                if(i % 2 == 0)
                {
                    sum = sum + (matrix.get(0).get(i) * determinant(temp));
                }else
                {
                    sum = sum + (-matrix.get(0).get(i) * determinant(temp));
                }
            }
            return sum;
        }
    }
    public static double determinantDebugger(ArrayList<ArrayList<Double>> matrix)
    {
        int size = matrix.size();
        if(size == 2)
        {
            System.out.println("Sub-matrix: " + matrix);
            double result = matrix.get(0).get(0)*matrix.get(1).get(1)-
            matrix.get(0).get(1)*matrix.get(1).get(0);
            System.out.println("2x2: " + result);
            return result;
        }else
        {
            ArrayList<ArrayList<Double>> temp = new ArrayList<ArrayList<Double>>();
            double sum = 0;
            for(int i = 0; i < size; i++)
            {
                temp.removeAll(temp);
                for(int j = 0; j < size - 1; j++)
                {
                    temp.add(j,new ArrayList<Double>());
                }
                int tempSize = temp.size();
                boolean flag = false;
                int tempRow = -1;
                for(int row = 0; row < tempSize; row++)
                {
                    for(int col = 0; col < tempSize; col++)
                    {
                        if(tempRow != row)
                        {
                            flag = false;
                        }
                        if(col == i)
                        {
                            flag = true;
                            tempRow = row;
                        }
                        if(flag == true)
                        {
                            temp.get(row).add(col, matrix.get(row+1).get(col+1));
                            System.out.println(temp.get(row) + "Same");
                        }else
                        {
                            temp.get(row).add(col, matrix.get(row+1).get(col));
                            System.out.println(temp.get(row) + "Different");  
                        }
                    }
                }
                if(i % 2 == 0)
                {
                    sum = sum + (matrix.get(0).get(i) * determinant(temp));
                    System.out.println("Multiplier: " + matrix.get(0).get(i));
                    System.out.println("Sum: " + sum);
                }else
                {
                    sum = sum + (-matrix.get(0).get(i) * determinant(temp));
                    System.out.println("Multiplier: " + -matrix.get(0).get(i));
                    System.out.println("Sum: " + sum);
                }
            }
            return sum;
        }
    }
}