//for database of ingredients for individual preferences
public class Ingredients{
   
	//descriptive ingedient name
   String ingredientName;
   String description; //possibly unnecessary
   boolean isBase;
   
   public Ingredients(){
      this.ingredientName = "";
      this.description = ""; 
      this.isBase = false; 
   }
   public Ingredients(String ingredientName, String description){
      this.ingredientName = ingredientName;
      this.description = description;
      this.isBase = false; 
   }
   public Ingredients(String ingredientName){
      this.ingredientName = ingredientName;
      this.description = "";
      this.isBase = false; 
   }
   public Ingredients(String ingredientName, boolean isBase){
	      this.ingredientName = ingredientName;
	      this.description = "";
	      this.isBase = isBase;
   }
   public String getName(){
      return ingredientName;
   }
   public boolean getIsBase(){
	      return isBase;
   }
}