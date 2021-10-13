import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class CoffeeShop{
   public CoffeeShop(){
      
   }
   public void OpenProgram(){
      
   }
   public static void main(String[] args){
      Scanner input = new Scanner(System.in);
      JFrame frame = new JFrame("Coffee Shop");
      frame.setLayout(new GridLayout(1,1));
      frame.setLocationRelativeTo(null);
      frame.setPreferredSize(new Dimension(600,400));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();         
      frame.setVisible(true);
      
      JTabbedPane pane = new JTabbedPane();
      JPanel panel = new JPanel();
      
      pane.addTab("Kitchen", new KitchenPanel());
      frame.getContentPane().add(pane);
      frame.setVisible(true);
   
   }   
}