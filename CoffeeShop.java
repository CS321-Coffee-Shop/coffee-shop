import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class CoffeeShop{

   ArrayList<Ingredients> ingredientsList = new ArrayList<Ingredients>();
   ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
   ArrayList<Employee> employeeList = new ArrayList<Employee>();
   ArrayList<Customer> customerList = new ArrayList<Customer>();
   

   public void login(){
   
   }
   public void readInMenu(){
   
   }
   public static void openMenu(){
      
   }
   public static void main(String[] args){
   
      Scanner input = new Scanner(System.in);
      
      //set up overall frame
      JFrame.setDefaultLookAndFeelDecorated(true);
      JFrame frame = new JFrame("Coffee Shop");
      frame.setLayout(new GridLayout(1,1));
      frame.setLocationRelativeTo(null);
      frame.setPreferredSize(new Dimension(800,600));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JPanel panel = new JPanel();
      panel.setLayout(new BorderLayout());
      
      //failed attempt at making a nice background
      //ImagePanel picpanel = new ImagePanel(new ImageIcon("coffeebackground.png").getImage());
      
      //buttons
      /*
      ImageIcon preferIcon = new ImageIcon("list.jpg");
      ImageIcon randomIcon = new ImageIcon("dice.jpg");
      ImageIcon cartIcon = new ImageIcon("cart.png");
      */
      
      JButton menuButton = new JButton("Menu");
      JButton preferButton = new JButton("Input Your Preferences");
      JButton randomButton = new JButton("Randomized Drink");
      JButton cartButton = new JButton("View Cart");
      
      menuButton.setBackground(new Color(137,84,38));
      menuButton.setForeground(new Color(255,255,255));
      menuButton.setVerticalTextPosition(JButton.CENTER);
      menuButton.setHorizontalTextPosition(JButton.CENTER);
      menuButton.addActionListener(
         new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               openMenu();
            }});
      
      preferButton.setBackground(new Color(98,57,22));
      preferButton.setForeground(new Color(255,255,255));
      preferButton.setVerticalTextPosition(JButton.CENTER);
      preferButton.setHorizontalTextPosition(JButton.CENTER);
      preferButton.addActionListener(
         new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               //preferences
            }});
      
      randomButton.setBackground(new Color(202,150,104));
      randomButton.setForeground(new Color(255,255,255));
      randomButton.setVerticalTextPosition(JButton.CENTER);
      randomButton.setHorizontalTextPosition(JButton.CENTER);
      randomButton.addActionListener(
         new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               //random drink generator
            }});
      
      cartButton.setBackground(new Color(255,255,255));
      cartButton.setForeground(new Color(121,76,36));
      cartButton.setVerticalTextPosition(JButton.CENTER);
      cartButton.setHorizontalTextPosition(JButton.CENTER);
      cartButton.addActionListener(
         new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               //view cart
            }});
      
      //add buttons and panels to frame
      panel.add(menuButton, BorderLayout.CENTER);
      panel.add(preferButton, BorderLayout.EAST);
      panel.add(randomButton, BorderLayout.WEST);
      panel.add(cartButton, BorderLayout.SOUTH);
      frame.getContentPane().add(panel);
      
      frame.pack();         
      frame.setVisible(true);      
   }   
}
