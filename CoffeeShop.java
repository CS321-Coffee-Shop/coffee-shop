import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.io.*;


public class CoffeeShop{

   public static ArrayList<Ingredients> ingredientsList = new ArrayList<Ingredients>();
   public static ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
   public static ArrayList<Employee> employeeList = new ArrayList<Employee>();
   public static ArrayList<Customer> customerList = new ArrayList<Customer>();
   public static ArrayList<MenuItem> cartList = new ArrayList<MenuItem>();
   
   protected static Customer user = new Customer();
   
   //Menu test attempt
   public static Menu menu = new Menu();
   
   /*Gets a sorted array of MenuItems based on user preferences
   */
   public static MenuItem[] getRecommendedItems(){
      MenuItem[] recommendations = new MenuItem[9];
      MenuItem temp, temp2 = null;
      
      for(int i = 0; i < menu.size(); i++){
         temp =  menu.getItem(i);
         
         for(String j: temp.getItemIngredients()){
            for(Ingredients r: user.getRestrictions()){
               if(r.getName().equals(j)){
                 temp.setScore(-99);
                 break;
               }
            }

            for(Ingredients l: user.getLikes()){
               if(temp.getScore() < -98){
                  break;
               }
               
               if(l.getName().equals(j)){
                 temp.incScore();
               }
            }
            
            for(Ingredients d: user.getDislikes()){
               if(temp.getScore() < -98){
                  break;
               }
               
               if(d.getName().equals(j)){
                 temp.decScore();
               }
            }
         }
         
         for(int z = 0; z < i + 1; z++){
            if(recommendations[z] != null){
               if(recommendations[z].getScore() < temp.getScore()){
                  temp2 = recommendations[z];
                  recommendations[z] = temp;
                  temp = temp2;
               }
            }
            else{
               recommendations[z] = temp;
            }  
         }
      }
      
      return recommendations;
   }

   public void login(){
	   JTextField username = new JTextField();
	   JTextField password = new JTextField();
		   
	   JButton login = new JButton("Log In");

   }
   
   /*
   Menu items will be read in from a text file dividing information by "|"
   The order will be [itemName]|price|[ingredients,separated,by,commas]
   and dividing individual items with a new line.
   This method will read in line by line and add each menu item to the menuItem array.
   We may need to have a character limit for item names/details/descriptions
   */
   public static void readInMenu() throws FileNotFoundException, IOException{
      File file = new File("menuInfo.txt");
      FileReader input = new FileReader(file);
      ArrayList<String> itemInfo = new ArrayList<String>();
      StringBuilder tempString = new StringBuilder();
      int data = '0';
      
      while(data!=-1){
         data = input.read();
         if((char)data!='\n'){
            tempString.append((char)data);
         }
         else{
            //System.out.println(tempString.toString());
            itemInfo.add(tempString.toString());
            tempString = new StringBuilder();
         }
      }
      String[] tempArray;
      String[] ingArray;
      String itemName = "";
      String tempIngred = "";
      double price = 0.0;
      
      for(int i=0;i<itemInfo.size();i++){
         //split into item name, price, ingredients array
         tempArray = itemInfo.get(i).split("\\|");
         itemName = tempArray[0].substring(0,tempArray[0].length());
         price = Double.parseDouble(tempArray[1]);
         tempIngred = tempArray[2].substring(1,tempArray[2].length()-1);
         ingArray = tempIngred.split(",");
         
         //menuItemList.add(new MenuItem(itemName,price,ingArray));
         menu.addItem(itemName,price,ingArray);
      }      
   }
   
   /*
   Menu items will be displayed as clickable buttons with item name and price
   When buttons are clicked, open a page with ingredient information and details of the item
   Customer should be able to add item to cart when detail page has opened.
   */
   public static void openMenu(){   
      JFrame.setDefaultLookAndFeelDecorated(true);
      JFrame frame = new JFrame("Menu");
      frame.setLayout(new BorderLayout());
      frame.setPreferredSize(new Dimension(800,600));
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
      //Menu should be able to resize itself and add more pages if over specified limit
      JPanel panel = new JPanel();
      //panel.setLayout(new GridLayout((int)Math.sqrt(menuItemList.size())+1,(int)Math.sqrt(menuItemList.size())));
      panel.setLayout(new GridLayout((int)Math.sqrt(menu.size())+1,(int)Math.sqrt(menu.size())));
      JButton button;
      
      //creating menu item buttons
      for(int i=0;i<menu.size();i++){
      
         MenuItem tempItem = menu.getItem(i);
        
         button = new JButton(tempItem.getName());
         button.setBackground(new Color(169,123,76));
         button.setForeground(Color.WHITE);
         button.setVerticalTextPosition(JButton.CENTER);
         button.setHorizontalTextPosition(JButton.CENTER);
         button.addActionListener(
            new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
               //view menu item details
                  JFrame.setDefaultLookAndFeelDecorated(true);
                  JFrame detailFrame = new JFrame(tempItem.getName()); // Used to say "Menu"
                  detailFrame.setLayout(new BorderLayout());
                  detailFrame.setPreferredSize(new Dimension(600,400));
                  detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                  detailFrame.getContentPane().setBackground(new Color(125,83,40));
                  
                  String temp = "<html>Ingredients List:<br/><br/>";
                  for(int j=0;j<tempItem.getItemIngredients().length;j++){
                     temp += tempItem.getItemIngredients()[j]+"<br/>";
                  }
                  temp += "</html>";
                  JLabel info = new JLabel(temp);
                  info.setForeground(new Color(255,255,255));
                  detailFrame.getContentPane().add(info);
                  
                  JButton cartButton = new JButton("Add to Cart");
                  cartButton.setBackground(new Color(255,255,255));
                  cartButton.setForeground(new Color(121,76,36));
                  cartButton.setVerticalTextPosition(JButton.CENTER);
                  cartButton.setHorizontalTextPosition(JButton.CENTER);
                  cartButton.addActionListener(
                     new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){
                        //add to cart
                        }});      
               
                  detailFrame.add(cartButton, BorderLayout.SOUTH);   
                  detailFrame.pack();
                  detailFrame.setLocationRelativeTo(null);         
                  detailFrame.setVisible(true);
               }});
         panel.add(button);
      }
      //cart buttons
      JButton cartButton = new JButton("View Cart");
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
      
      frame.add(cartButton, BorderLayout.SOUTH);
      frame.getContentPane().add(panel);
      frame.pack();
      frame.setLocationRelativeTo(null);         
      frame.setVisible(true);
   }
   public static void main(String[] args) throws IOException{
      
      readInMenu();
      
      //set up overall frame
      JFrame.setDefaultLookAndFeelDecorated(true);
      JFrame frame = new JFrame("Coffee Shop");
      frame.setLayout(new GridLayout(1,1));
      frame.setPreferredSize(new Dimension(800,600));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JPanel panel = new JPanel();
      panel.setLayout(new BorderLayout());
      
      JButton menuButton = new JButton("Menu", new ImageIcon("coffeeImage.jfif"));
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
      frame.setLocationRelativeTo(null);         
      frame.setVisible(true);      
      
      /*
      //Test recommendations
      user.addLike("chocolate","");
      user.addDislike("whole milk","");
      user.addRestriction("chocolate sauce]","");
      
      for(MenuItem i: getRecommendedItems()){
         System.out.println(i.getScore());
         System.out.println(i);
      }*/
   }   
}
