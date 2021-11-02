public class MenuItem{
   
   String itemName;
   double price;
   String[] ingredients;
   int score;
   
   public MenuItem(){
      this.itemName = "";
      this.price = 0.0;
      this.ingredients = new String[0];   
      score = 0;
   }
   public MenuItem(String itemName, double price, String[] ingredients){
      this.itemName = itemName;
      this.price = price;
      this.ingredients = ingredients;
      score = 0;
   }   
   public void setItemIngredients(){
      
   }
   public String[] getItemIngredients(){
      return this.ingredients;
   }
   public String getItemIngredientsString(){
      String ingsList = "";
      for(int i = 0; i < ingredients.length; i++){
         ingsList += ingredients[i];
         
         if(i < ingredients.length - 1){
            ingsList += ", ";
         }
      }
      
      return ingsList;
   }
   public void setPrice(double newPrice){
      this.price = newPrice;
   }
   public double getPrice(){
      return this.price;
   }
   public void setName(String newName){
      this.itemName = newName;
   }
   public String getName(){
      return this.itemName;
   }
   public void setScore(int newScore){
      this.score = newScore;
   }
   public void incScore(){
      this.score++;
   }
   public void decScore(){
      this.score--;
   }
   public int getScore(){
      return this.score;
   }

   @Override 
   public String toString(){
      //First idea. Feel free to change.
      return getName() + "\n$" + getPrice() + "\nIngredients: " + getItemIngredientsString();      
   }
}
