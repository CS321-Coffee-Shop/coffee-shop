//for database of ingredients for individual preferences
public class Ingredients{
   
	//descriptive ingedient name
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
   public Ingredients(String ingredientName){
      this.ingredientName = ingredientName;
      this.description = "";
   }
   public String getName(){
      return ingredientName;
   }
}