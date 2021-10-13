public class MenuItem{
   
   String itemName;
   double price;
   String[] ingredients;
   
   public MenuItem(){
      this.itemName = "";
      this.price = 0.0;
      this.ingredients = new String[];   
   }
   public MenuItem(String itemName, double price, String[] ingredients){
      this.itemName = itemName;
      this.price = price;
      this.ingredients = ingredients;
   }   
   public void setItemIngredients(){
      
   }
   public String[] getItemIngredients(){
      
   }
}
