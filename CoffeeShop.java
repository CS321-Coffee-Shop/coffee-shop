import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class CoffeeShop{

   public void login(){
   
   }
   public void openProgram(){
      
   }
   public void readInMenu(){
   
   }
   public static void main(String[] args){
   
      Scanner input = new Scanner(System.in);
      JFrame frame = new JFrame("Coffee Shop");
      frame.setLayout(new GridLayout(1,1));
      frame.setLocationRelativeTo(null);
      frame.setPreferredSize(new Dimension(800,600));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JPanel panel = new JPanel();
      
      JButton menuButton = new JButton("Menu");
      JButton preferButton = new JButton("Input Your Preferences");
      JButton randomButton = new JButton("Randomized Drink");
      
      frame.getContentPane().add(panel);
      panel.add(menuButton);
      panel.add(preferButton);
      panel.add(randomButton);
      
      frame.pack();         
      frame.setVisible(true);
            
   }   
}
