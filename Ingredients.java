//for database of ingredients for individual preferences
public class Ingredients{
   
   String ingredientName;
   String description; //possibly unnecessary
   
   public Ingredients(){
      this.ingredientName = "";
      this.description = ""; 
   }
   public Ingredients(String ingredientName, String description){
      this.ingredientName = ingredientName;
      this.description = description;
   }
}