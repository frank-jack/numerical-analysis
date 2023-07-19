import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.*;
import java.util.*;
import java.awt.Dialog;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.io.*;
public class MatrixInput extends Frame 
{
    public class TextField extends JTextField
    {
        int boxNum;
        public TextField(String temp, int boxNum)
        {
            super("0");
            this.boxNum = boxNum;
        }
        public int getBoxNum()
        {
            return this.boxNum;
        }
    }
    static ArrayList<ArrayList<TextField>> boxes = new ArrayList<ArrayList<TextField>>();
    int termNum;
    JFrame frame = new JFrame("Matrix");
    JPanel panel;
    JButton finish = new JButton("Finish");
    JButton random = new JButton("Randomize");
    JButton setTN = new JButton("Set Term Number");
    JButton exit = new JButton("Exit");
    boolean pressed = false;
    boolean pressedTN = false;
    ArrayList<ArrayList<Double>> matrix = new ArrayList<ArrayList<Double>>();
    public MatrixInput(int termNum)
    {
        panel = new JPanel(new GridLayout(termNum+1,termNum + 1,5,5));
        for(int l = 0; l < termNum; l++)
        {
            boxes.add(l,new ArrayList<TextField>());
            for(int j = 0; j<=termNum; j++)
            {
                TextField tf = new TextField(String.valueOf(0.0),(termNum+1)*l+j);
                tf.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        String text = tf.getText();
                        System.out.println(text+" "+tf.getBoxNum());
                    }
                }
                );
                panel.add(tf);
                boxes.get(l).add(j,tf);
            }
        }
        finish.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                pressed = true;
            }
        }
        );
        panel.add(finish);
        random.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                display(GaussianElimination.fillRandom(matrix,termNum),termNum);
            }
        }
        );
        panel.add(random);
        setTN.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                pressedTN = true;
            }
        }
        );
        panel.add(setTN);
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                System.exit(0);
            }
        }
        );
        panel.add(exit);
        frame.add(panel);
        frame.setSize(termNum*105,(termNum + 1)*105);
        frame.setVisible(true);
    }
    public void display(ArrayList<ArrayList<Double>> matrix,int termNum)
    {
        for(int i = 0; i<termNum; i++)
        {
            for(int j = 0; j<=termNum; j++)
            {
                boxes.get(i).get(j).setText(Double.toString(matrix.get(i).get(j)));
            }
        }
    }
    public ArrayList<ArrayList<Double>> enter(ArrayList<ArrayList<Double>> matrix, int termNum)
    {
        pressed = false;
        while(!pressed)
        {
            for(double i = 0; i < 1000; i++)
            {
                double x = Math.sin(i);
            }
            if(pressedTN)
            {
                String temp = JOptionPane.showInputDialog(frame,"Set Term Number");
                if(temp != null && !temp.isEmpty())
                {
                    int tempTN = (int)Double.parseDouble(temp);
                    setMatrixInput(tempTN);
                    termNum = tempTN;
                }
                pressedTN = false;
                matrix.clear();
                for(int i = 0; i<termNum; i++)
                {
                    matrix.add(i,new ArrayList<Double>());
                    for(int j = 0; j<=termNum; j++)
                    {
                        matrix.get(i).add(j,0.0);
                    }
                }
            }
            for(double i = 0; i < 1000; i++)
            {
                double x = Math.sin(i);
            }
        }
        pressed = false;
        for(int i = 0; i<termNum; i++)
        {
            for(int j = 0; j<=termNum; j++)
            {
                matrix.get(i).set(j,Double.valueOf(boxes.get(i).get(j).getText()));
            }
        }
        frame.dispose();
        return matrix;
    }
    public void setMatrixInput(int termNum)
    {
        panel.removeAll();
        panel.setLayout(new GridLayout(termNum+1,termNum + 1,5,5));
        for(int l = 0; l < termNum; l++)
        {
            boxes.add(l,new ArrayList<TextField>());
            for(int j = 0; j<=termNum; j++)
            {
                TextField tf = new TextField(String.valueOf(0.0),(termNum+1)*l+j);
                tf.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        String text = tf.getText();
                        System.out.println(text+" "+tf.getBoxNum());
                    }
                }
                );
                panel.add(tf);
                boxes.get(l).add(j,tf);
            }
        }
        finish.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                pressed = true;
            }
        }
        );
        panel.add(finish);
        random.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                display(GaussianElimination.fillRandom(matrix,termNum),termNum);
            }
        }
        );
        panel.add(random);
        setTN.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                pressedTN = true;
            }
        }
        );
        panel.add(setTN);
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                System.exit(0);
            }
        }
        );
        panel.add(exit);
        frame.add(panel);
        frame.setSize(termNum*105,(termNum + 1)*105);
        frame.setVisible(true);
    }
}