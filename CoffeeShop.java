import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.io.*;


public class CoffeeShop{

   public static ArrayList<Ingredients> ingredientsList = new ArrayList<Ingredients>();
   public static ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
   public static ArrayList<Employee> employeeList = new ArrayList<Employee>();
   public static ArrayList<Customer> customerList = new ArrayList<Customer>();
   public static ArrayList<MenuItem> cartList = new ArrayList<MenuItem>();
   public static ArrayList<MenuItem> orderQueue = new ArrayList<MenuItem>();
   
   protected static Customer user = new Customer();
   protected static Employee userE = new Employee();
   
   //Menu test attempt
   public static Menu menu = new Menu();
   
   public static MenuItem getRandomItem(){
      ArrayList<Ingredients> bases = new ArrayList<Ingredients>();
      ArrayList<Ingredients> ings;
      
      String[] names;
      
      int randIndex, randSize;      
      double price;
      boolean hasBase = false;
      
      for(Ingredients i: ingredientsList){
         if(i.getIsBase() && !user.getRestrictions().contains(i)){
            bases.add(i);
         }
      }
      
      ings = new ArrayList<>(user.getLikes());
      
      randSize = (int)(Math.random() * ings.size());
      
      if(ings.size() < 3){
         ArrayList<Ingredients> neutrals = new ArrayList<Ingredients>();
         
         for(Ingredients i: ingredientsList){
            if(!user.getLikes().contains(i) && !user.getDislikes().contains(i) && !user.getRestrictions().contains(i)){
               neutrals.add(i);
            }
         }
         
         int j = 0;
         
         while(ings.size() < 3 && neutrals.size() > 0){
            j = (int)(Math.random() * neutrals.size());
            
            ings.add(neutrals.get(j));
            
            neutrals.remove(j);
         }
      }
      
      for(int i = 0; i < randSize; i++){
         randIndex = (int)(Math.random() * ings.size());
      
         ings.remove(randIndex);
      }
      
      for(Ingredients b: bases){      
         if(ings.contains(b)){
            hasBase = true;
            break;
         }             
      }
      
      if(!hasBase){
         randIndex = (int)(Math.random() * bases.size());
         
         ings.add(bases.get(randIndex));
      }
      
      price = ings.size() * 0.5;
      
      names = new String[ings.size()];
      
      for(int i = 0; i < ings.size(); i++){
         names[i] = ings.get(i).getName();
      }
      
      return new MenuItem("Custom Item", price, names);
   }
   
   /*Gets a sorted array of MenuItems based on user preferences
   */
   public static MenuItem[] getRecommendedItems(){
      MenuItem[] recommendations = new MenuItem[menu.size()]; 
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
            if(z >= recommendations.length){
               break;
            }
            
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

   public static void preferencesSubMenu(int option){
	   JFrame frame = new JFrame("Preference Options");
	   frame.setPreferredSize(new Dimension(800,600));
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	   
	   JPanel panel = new JPanel();
	   frame.add(panel);
      panel.setBackground(new Color(125,83,40));
	      
	   ArrayList<Ingredients> ings;
	   String text, ingText = "";
	      
	   switch(option){
	      case 1:
	         ings = user.getLikes();
	         text = "likes";
	         break;
	            
	      case 2:
	         ings = user.getDislikes();
	         text = "dislikes";            
	         break;
	            
	      default:
	         ings = user.getRestrictions();
	         text = "restrictions";
	   }
	      
      for(int i = 0; i < ings.size(); i++){
	      ingText += ings.get(i).getName();
	      
	      if(i < ings.size() - 1){
	         ingText += ", ";
	      }
	      else{
	         ingText += ".";
	      }
	   }
	      
	   if(ings.size() < 1){
	      ingText = "None.";
	   }
         
	   JLabel lbl = new JLabel("Your current " + text + " are " + ingText, SwingConstants.CENTER);
      lbl.setForeground(new Color(125,83,40));
      lbl.setBackground(new Color(255, 255, 255));
      lbl.setOpaque(true);
      
      panel.setLayout(new BorderLayout());
	   panel.add(lbl, BorderLayout.NORTH);
         
      JPanel buttonPane = new JPanel();
	   buttonPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
      buttonPane.setBackground(new Color(125,83,40));
      
      JButton addButton = makeLightBrownButton("Add");
	   JButton removeButton = makeLightBrownButton("Remove");
	   JButton backButton = makeWhiteButton("Back");
	      
	   addButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
	         preferencesAddMenu(option);
	         frame.setVisible(false); 
	         frame.dispose();
	      }
	   });
	      
	   removeButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
	         preferencesRemoveMenu(option);
	         frame.setVisible(false); 
	         frame.dispose();
	      }
	   });
	      
	   backButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
	         preferencesMenu();
	         frame.setVisible(false); 
	         frame.dispose();
	      }
	   });
	      
	   buttonPane.add(addButton);
	   buttonPane.add(removeButton);
      
      buttonPane.setLayout(new GridLayout(1,2));
      
      panel.add(buttonPane);
      panel.add(backButton, BorderLayout.SOUTH);
	      
	   frame.pack();
	   frame.setLocationRelativeTo(null);         
	   frame.setVisible(true);
	}
	   
	   public static void preferencesRemove(int option, int itemIndex){      
	      switch(option){
	         case 1:
	            user.removeLike(itemIndex);
	            break;
	            
	         case 2:
	            user.removeDislike(itemIndex);
	            break;
	            
	         default:
	            user.removeRestriction(itemIndex);
	      }
	   }
	   
	   public static void preferencesRemoveMenu(int option){
	      JFrame frame = new JFrame("Preference Options");
	      frame.setPreferredSize(new Dimension(800,600));
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	      JPanel panel = new JPanel();
	      frame.add(panel);
         panel.setBackground(new Color(125,83,40));

	      JLabel lbl = new JLabel("Select one of the ingredients and click Remove.");
         lbl.setForeground(new Color(255, 255, 255));
	      panel.add(lbl);
	      
	      String[] choices;

	      switch(option){
	         case 1:
	            choices = user.getLikesNames();
	            break;
	            
	         case 2:
	            choices = user.getDislikesNames();
	            break;
	            
	         default:
	            choices = user.getRestrictionsNames();
	      }
	      
	      JComboBox<String> cb = new JComboBox<String>(choices);

	      panel.add(cb);
	      
	      JButton removeButton = makeWhiteButton("Remove");
		   JButton backButton = makeWhiteButton("Back");
	      
	      removeButton.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
	            preferencesRemove(option, cb.getSelectedIndex());
	            cb.removeItemAt(cb.getSelectedIndex());
	         }
	      });
	      
	      backButton.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
	            preferencesSubMenu(option);
	            frame.setVisible(false); 
	            frame.dispose();
	         }
	      });
	      
	      panel.add(removeButton);
	      panel.add(backButton);
	      
	      frame.pack();
	      frame.setLocationRelativeTo(null);         
	      frame.setVisible(true);
	   }
	   
	   public static void preferencesAdd(int option, String name){      
	      Ingredients ing = new Ingredients(name);
	      
	      for(Ingredients i: ingredientsList){
	         if(ing.getName().equals(i.getName())){
	            ing = i;
	         }
	      }
	      
	      switch(option){
	         case 1:
	            user.removeDislike(ing.getName());
	            user.removeRestriction(ing.getName());
	            user.addLike(ing);
	            break;
	            
	         case 2:
	            user.removeLike(ing.getName());
	            user.removeRestriction(ing.getName());
	            user.addDislike(ing);
	            break;
	            
	         default:
	            user.removeLike(ing.getName());
	            user.removeDislike(ing.getName());
	            user.addRestriction(ing);
	      }
	   }
	   
	   public static void preferencesAddMenu(int option){
	      JFrame frame = new JFrame("Preference Options");
	      frame.setPreferredSize(new Dimension(800,600));
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	      JPanel panel = new JPanel();
	      frame.add(panel);
         panel.setBackground(new Color(125,83,40));

	      JLabel lbl = new JLabel("Select one of the ingredients and click Add.");
         lbl.setForeground(new Color(255, 255, 255));
	      panel.add(lbl);
	      
	      ArrayList<Ingredients> chosenList;
	      
	      switch(option){
	         case 1:
	            chosenList = user.getLikes();
	            break;
	            
	         case 2:
	            chosenList = user.getDislikes();
	            break;
	            
	         default:
	            chosenList = user.getRestrictions();
	      }
	      
	      JComboBox<String> cb = new JComboBox<String>();
         cb.setForeground(new Color(125,83,40));
         cb.setBackground(new Color(255, 255, 255)); //light brown 169,123,76
	      
	      for(int i = 0; i < ingredientsList.size(); i++){
	         if(!chosenList.contains(ingredientsList.get(i))){
	            cb.addItem(ingredientsList.get(i).getName());
	         }
	      }
	      
	      panel.add(cb);
	      
	      JButton addButton = makeWhiteButton("Add");
		   JButton backButton = makeWhiteButton("Back");
	      
	      addButton.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
	            preferencesAdd(option, String.valueOf(cb.getSelectedItem()));
	            cb.removeItemAt(cb.getSelectedIndex());
	         }
	      });
	      
	      backButton.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
	            preferencesSubMenu(option);
	            frame.setVisible(false); 
	            frame.dispose();
	         }
	      });
	      
	      panel.add(addButton);
	      panel.add(backButton);
	      
	      frame.pack();
	      frame.setLocationRelativeTo(null);         
	      frame.setVisible(true);
	   }

	public static void preferencesMenu(){
	   JFrame.setDefaultLookAndFeelDecorated(true);
	   JFrame frame = new JFrame("Preference Options");
	   
	   frame.setPreferredSize(new Dimension(800,600));
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            
	   JPanel panel = new JPanel();
      panel.setLayout(new BorderLayout());
	   frame.add(panel);

      JButton menuButton = makeLightBrownButton("View Custom Menu");
	   JButton likesButton = makeLightBrownButton("View Likes");
	   JButton dislikesButton = makeLightBrownButton("View Dislikes");
		JButton restrictionsButton = makeLightBrownButton("View Restrictions");
		JButton backButton = makeBackButton(frame);
	      
      menuButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
            MenuItem[] items = getRecommendedItems();
            items = Arrays.copyOfRange(items, 0, Math.min(4, items.length));
	         displayItems(items, "Custom Menu");
	         //frame.setVisible(false); 
	         //frame.dispose();
	      }
	   });
      
	   likesButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
	         preferencesSubMenu(1);
	         frame.setVisible(false); 
	         frame.dispose();
	      }
	   });
	      
	   dislikesButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
	         preferencesSubMenu(2);
	         frame.setVisible(false); 
	         frame.dispose();
	      }
	   });
	      
	   restrictionsButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
	         preferencesSubMenu(3);
	         frame.setVisible(false); 
	         frame.dispose();
	      }
	   });
	                  
      JPanel buttonPane = new JPanel();
      buttonPane.setLayout(new GridLayout(2,2));
      buttonPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
      buttonPane.setBackground(new Color(125,83,40));
	      
      buttonPane.add(menuButton);
	   buttonPane.add(likesButton);      
	   buttonPane.add(dislikesButton);      
	   buttonPane.add(restrictionsButton);
            
      panel.add(buttonPane);
	   panel.add(backButton, BorderLayout.SOUTH);
      
	   frame.pack();
	   frame.setLocationRelativeTo(null);         
	   frame.setVisible(true); 
	}

   public static void login(){
      
      Customer c = new Customer("a", "a", "111", "abcd", "abcd");
      customerList.add(c);
      Employee e = new Employee(123, "a", "g");
      employeeList.add(e);
      
      JFrame frame = new JFrame("Log In");
      
      //frame.setLayout(new GridLayout(1,1));
      frame.setPreferredSize(new Dimension(800,600));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
      JPanel panel = new JPanel();
      panel.setLayout(new BorderLayout());
      
      JLabel userName = new JLabel("Email");
      JLabel password = new JLabel("Password");
      
      JTextField customerUsername = new JTextField();	   
      JPasswordField customerPassword = new JPasswordField();
      JPasswordField employeePassword = new JPasswordField();
     
      JButton loginAsCustomer = new JButton("Log In");
      JButton loginAsEmployee = new JButton("Log In");
      
      JButton customerLogin = new JButton("Customer");
      JButton employeeLogin = new JButton("Employee");
      JButton signUp = new JButton("Create Account");
      
      JButton employeeSignUp = new JButton("Employee");
      JButton customerSignUp = new JButton("Customer");
      
      JTextField customerFirstName = new JTextField("First Name");
      JTextField customerLastName = new JTextField("Last Name");
      JTextField customerPhoneNumber = new JTextField("Phone Number");
      JTextField customerEmail = new JTextField("Email");
      JTextField customerPasswordField = new JTextField("Password");
      
      JTextField employeeFirstName = new JTextField("First Name");
      JTextField employeeLastName = new JTextField("Last Name");
      JTextField employeePhoneNumber = new JTextField("Phone Number");
      JTextField employeeEmail = new JTextField("Email");
      JTextField employeePasswordField = new JTextField("Password");
      
      JButton signUpAsCustomer = new JButton("Sign Up");
       
      customerLogin.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               customerLogin.setVisible(false);
               customerLogin.setEnabled(false);
               employeeLogin.setVisible(false);
               employeeLogin.setEnabled(false);
               signUp.setVisible(false);
               signUp.setEnabled(false);
               panel.add(customerUsername);
               panel.add(customerPassword);
               panel.add(loginAsCustomer);
               customerUsername.setBounds(300, 250, 100, 20);
               customerPassword.setBounds(300, 300, 100, 20);
               loginAsCustomer.setBounds(320, 350, 70, 20);
               panel.setLayout(new BorderLayout());
            }
         });
      
      employeeLogin.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               customerLogin.setVisible(false);
               customerLogin.setEnabled(false);
               employeeLogin.setVisible(false);
               employeeLogin.setEnabled(false);
               signUp.setVisible(false);
               signUp.setEnabled(false);
               panel.add(employeePassword);
               panel.add(loginAsEmployee);
               employeePassword.setBounds(300, 300, 100, 20);
               loginAsEmployee.setBounds(320, 350, 70, 20);
               panel.setLayout(new BorderLayout());
            }
         });
      
      signUp.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               customerLogin.setVisible(false);
               customerLogin.setEnabled(false);
               employeeLogin.setVisible(false);
               employeeLogin.setEnabled(false);
               signUp.setVisible(false);
               signUp.setEnabled(false);
               panel.add(customerSignUp);
               panel.add(employeeSignUp);
               customerSignUp.setBounds(300, 250, 100, 20);
               employeeSignUp.setBounds(300, 300, 100, 20);
               panel.setLayout(new BorderLayout());
            }
         });
      
      customerSignUp.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               customerSignUp.setVisible(false);
               customerSignUp.setEnabled(false);
               employeeSignUp.setVisible(false);
               employeeSignUp.setEnabled(false);
               panel.add(customerFirstName);
               panel.add(customerLastName);
               panel.add(customerPhoneNumber);
               panel.add(customerEmail);
               panel.add(customerPasswordField);
               panel.add(signUpAsCustomer);
               customerFirstName.setBounds(300, 150, 100, 20);
               customerLastName.setBounds(300, 200, 100, 20);
               customerPhoneNumber.setBounds(300, 250, 100, 20);
               customerEmail.setBounds(300, 300, 100, 20);
               customerPasswordField.setBounds(300, 350, 100, 20);
               signUpAsCustomer.setBounds(300, 400, 100, 20);
               panel.setLayout(new BorderLayout());
            }
         });
      
      employeeSignUp.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               customerSignUp.setVisible(false);
               customerSignUp.setEnabled(false);
               employeeSignUp.setVisible(false);
               employeeSignUp.setEnabled(false);
               panel.add(employeeFirstName);
               panel.add(employeeLastName);
               panel.add(employeePhoneNumber);
               panel.add(employeeEmail);
               panel.add(employeePasswordField);
            
               employeeFirstName.setBounds(300, 200, 100, 20);
               employeeLastName.setBounds(300, 250, 100, 20);
               employeePasswordField.setBounds(300, 300, 100, 20);
            
               panel.setLayout(new BorderLayout());
            }
         });
      
      signUpAsCustomer.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String firstNameCustomer = customerFirstName.getText();
               String lastNameCustomer = customerLastName.getText();
               String phoneNumberCustomer = customerPhoneNumber.getText();
               String emailCustomer = customerEmail.getText();
               String passwordCustomer = customerPasswordField.getText();
            
               Customer customer = new Customer(firstNameCustomer, lastNameCustomer, phoneNumberCustomer, emailCustomer, passwordCustomer);
               customerList.add(customer);
               user = customer;
            }
         });
      
      loginAsCustomer.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String email = customerUsername.getText();
            //String password = customerPassword.getPassword().toString();
               String password = String.valueOf(customerPassword.getPassword());
               System.out.println(email + " " + password);
            
               for(int i = 0; i<customerList.size(); i++) {
                  if(customerList.get(i).email.equals(email) && customerList.get(i).password.equals(password)) {
                     user = customerList.get(i);
                     System.out.println("reached");
                     displayMenu();
                     break;
                  }
               }
            }
         });
      
      loginAsEmployee.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String password = String.valueOf(employeePassword.getPassword());
               int p = Integer.parseInt(password);
            
            
               for(int i = 0; i<employeeList.size(); i++) {
                  if(employeeList.get(i).password == p) {
                     userE = employeeList.get(i);
                     displayMenu();
                     break;
                  }
               }
            }
         });
   
      
      /*
      panel.add(username);
      panel.add(password);
      panel.add(login);
      username.setBounds(300, 250, 100, 20);
      password.setBounds(300, 300, 100, 20);
      login.setBounds(320, 350, 70, 20);*/
      
      panel.add(customerLogin);
      panel.add(employeeLogin);
      panel.add(signUp);
      customerLogin.setBounds(200, 250, 100, 20);
      employeeLogin.setBounds(400, 250, 100, 20);
      signUp.setBounds(300, 300,100,20);
      
      panel.setLayout(new BorderLayout());
      
      frame.add(panel);
      //frame.getContentPane().add(panel);
         
      frame.pack();
      frame.setLocationRelativeTo(null);         
      frame.setVisible(true); 
   
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
         tempIngred = tempArray[2].substring(1,tempArray[2].length()-2);
         ingArray = tempIngred.split(",");
         
         for(String name: ingArray){
             if(i < 1){
                if(name.equals("water") || name.equals("whole milk")){
                  ingredientsList.add(new Ingredients(name, true));
                }
             }
             
             //add new Ingredients to ingredientsList
             for(int j = 0; j < ingredientsList.size(); j++){
                if(name.equals(ingredientsList.get(j).getName())){
                   j = ingredientsList.size();
                }
                else if(j == ingredientsList.size() - 1){
                  if(name.equals("water") || name.equals("whole milk")){
                     ingredientsList.add(new Ingredients(name, true));
                  }
                  else{
                     ingredientsList.add(new Ingredients(name));
                  }
                }
             }
          }
         
         menu.addItem(itemName,price,ingArray);
      }      
   }
   
   public static JButton makeButton(String text){
      JButton button = new JButton(text);
      button.setVerticalTextPosition(JButton.CENTER);
      button.setHorizontalTextPosition(JButton.CENTER);
      
      return button;
   }
   
   public static JButton makeLightBrownButton(String text){
      JButton button = makeButton(text);
      button.setBackground(new Color(169,123,76));
      button.setForeground(new Color(255,255,255));
            
      return button;
   }
   
   public static JButton makeDarkBrownButton(String text){
      JButton button = makeButton(text);
      button.setBackground(new Color(125,83,40));
      button.setForeground(new Color(255,255,255));
            
      return button;
   }
   
   public static JButton makeWhiteButton(String text){
      JButton button = makeButton(text);
      button.setBackground(new Color(255,255,255));
      button.setForeground(new Color(121,76,36));
            
      return button;
   }
   
   public static JButton makeCartButton(MenuItem item){
      JButton cartButton = makeWhiteButton("Add to Cart");
      cartButton.addActionListener(
         new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               //add to cart
               cartList.add(item);
      }});
      
      return cartButton;
   }
   
   public static JButton makeBackButton(JFrame frame){
      JButton backButton = makeWhiteButton("Back");
      backButton.addActionListener(
         new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               frame.setVisible(false); 
	            frame.dispose();
      }});
      
      return backButton;
   }
   
   public static JFrame makeItemFrame(MenuItem item){
      JFrame.setDefaultLookAndFeelDecorated(true);
      JFrame detailFrame = new JFrame(item.getName()); // Used to say "Menu"
      detailFrame.setLayout(new BorderLayout());
      detailFrame.setPreferredSize(new Dimension(600,400));
      detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      detailFrame.getContentPane().setBackground(new Color(125,83,40));
                  
      String temp = "<html>Ingredients List:<br/><br/>";
      for(int j=0;j<item.getItemIngredients().length;j++){
         temp += item.getItemIngredients()[j]+"<br/>";
      }
      temp += "</html>";
      JLabel info = new JLabel(temp);
      info.setForeground(new Color(255,255,255));
      detailFrame.getContentPane().add(info);
   
      return detailFrame;
   }
   
   public static void displayItemDetails(MenuItem item){
      JFrame detailFrame = makeItemFrame(item);
      JButton cartButton = makeCartButton(item);
               
      detailFrame.add(cartButton, BorderLayout.SOUTH);   
      detailFrame.pack();
      detailFrame.setLocationRelativeTo(null);         
      detailFrame.setVisible(true);
   }
   
   public static JButton makeItemButton(MenuItem item){
      JButton button = new JButton(item.getName());
      button.setBackground(new Color(169,123,76));
      button.setForeground(Color.WHITE);
      button.setVerticalTextPosition(JButton.CENTER);
      button.setHorizontalTextPosition(JButton.CENTER);
      button.addActionListener(
         new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               displayItemDetails(item);
            }
         });
      
      return button;
   } 
   
   public static JPanel makeItemsPanel(MenuItem[] items){
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout((int)Math.sqrt(items.length),(int)Math.sqrt(items.length)));
      
      for(MenuItem i: items){
         JButton button = makeItemButton(i);
         
         panel.add(button);
      }
      
      panel.setBackground(new Color(125,83,40));
      panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
      
      return panel;
   }
   
   public static void displayItems(MenuItem[] items, String pageName){   
      JFrame.setDefaultLookAndFeelDecorated(true);
      JFrame frame = new JFrame(pageName);
      frame.setLayout(new BorderLayout());
      frame.setPreferredSize(new Dimension(800,600));
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      JButton back = makeBackButton(frame);
      
      frame.add(makeItemsPanel(items), BorderLayout.CENTER);
      frame.add(back, BorderLayout.SOUTH);
      frame.pack();
      frame.setLocationRelativeTo(null);         
      frame.setVisible(true);
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
         
         //button = makeItemButton(tempItem); Item button function for easy reuse
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
               //JFrame detailFrame = makeItemFrame(tempItem); Item detail function for easy reuse
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
                  
                  //JButton cartButton = makeCartButton(tempItem); Cart button function for easy reuse
                  
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
                           cartList.add(tempItem);
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
                  JFrame.setDefaultLookAndFeelDecorated(true);
                  JFrame detailFrame = new JFrame("Your Cart");
                  detailFrame.setLayout(new BorderLayout());
                  detailFrame.setPreferredSize(new Dimension(600,400));
                  detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                  detailFrame.getContentPane().setBackground(new Color(125,83,40));
                  
                  double priceTotal = 0.0;
                  String temp = "<html>Your Cart:<br/><br/>";
                  for(int j=0;j<cartList.size();j++){
                     priceTotal += cartList.get(j).getPrice();
                     temp += cartList.get(j).getName()+"\t"+cartList.get(j).getPrice()+"<br/><br/>";
                  }
                  temp += "<br/>Total: "+ priceTotal +"</html>";
                  JLabel info = new JLabel(temp);
                  info.setForeground(new Color(255,255,255));
                  detailFrame.getContentPane().add(info);
                  
                  JButton checkOutButton = new JButton("Check Out");
                  checkOutButton.setBackground(new Color(255,255,255));
                  checkOutButton.setForeground(new Color(121,76,36));
                  checkOutButton.setVerticalTextPosition(JButton.CENTER);
                  checkOutButton.setHorizontalTextPosition(JButton.CENTER);
                  
                  JButton editButton = new JButton("Edit Cart");
                  editButton.setBackground(new Color(255,255,255));
                  editButton.setForeground(new Color(121,76,36));
                  editButton.setVerticalTextPosition(JButton.CENTER);
                  editButton.setHorizontalTextPosition(JButton.CENTER);
                  
                  JPanel bottomPanel = new JPanel();
                  bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                  
                  bottomPanel.add(editButton);
                  bottomPanel.add(checkOutButton);   
                  detailFrame.add(bottomPanel, BorderLayout.SOUTH);
                  detailFrame.pack();
                  detailFrame.setLocationRelativeTo(null);         
                  detailFrame.setVisible(true);
            }});      
      
      frame.add(cartButton, BorderLayout.SOUTH);
      frame.getContentPane().add(panel);
      frame.pack();
      frame.setLocationRelativeTo(null);         
      frame.setVisible(true);
   }
   
   public static void displayMenu() {
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
                  preferencesMenu();
               }});
         
      randomButton.setBackground(new Color(202,150,104));
      randomButton.setForeground(new Color(255,255,255));
      randomButton.setVerticalTextPosition(JButton.CENTER);
      randomButton.setHorizontalTextPosition(JButton.CENTER);
      randomButton.addActionListener(
            new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
                  displayItemDetails(getRandomItem());
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
   }
   
   public static void main(String[] args) throws IOException{
      
      login();
      
      readInMenu();
      /*
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
               preferencesMenu();
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
