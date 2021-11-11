import java.util.ArrayList;

public class Customer{

   String firstName;
   String lastName;
   String phoneNumber;
   String email;
   String password;
   protected ArrayList<Ingredients> likes;
   protected ArrayList<Ingredients> dislikes;
   protected ArrayList<Ingredients> restrictions;
 
   public Customer(){
      firstName = "";
      lastName = "";
      phoneNumber = "";
      email = "";
      password = "";
      likes = new ArrayList<Ingredients>();
      dislikes = new ArrayList<Ingredients>();
      restrictions = new ArrayList<Ingredients>();
   }
   public Customer(String firstName, String lastName, String phoneNumber, String email, String password){
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
      this.email = email;
      this.password = password;
      likes = new ArrayList<Ingredients>();
      dislikes = new ArrayList<Ingredients>();
      restrictions = new ArrayList<Ingredients>();
   }
   public String getName(){
      return firstName+" "+lastName;
   }
   public void setName(String firstName, String lastName){
      this.firstName = firstName;
      this.lastName = lastName;
   }
   public String getPhoneNumber(){
      return this.phoneNumber;
   }
   public void setPhoneNumber(String phoneNumber){
      this.phoneNumber = phoneNumber;
   }
   public String getPassword(){
      return this.password;
   }
   public void setPassword(String password){
      this.password = password;
   }
   public ArrayList<Ingredients> getLikes(){
      return likes;
   }
   public void setLikes(ArrayList<Ingredients> likes){
      this.likes = likes;
   }
   public ArrayList<Ingredients> getDislikes(){
      return dislikes;
   }
   public void setDislikes(ArrayList<Ingredients> dislikes){
      this.dislikes = dislikes;
   }
   public ArrayList<Ingredients> getRestrictions(){
      return restrictions;
   }
   public void setRestrictions(ArrayList<Ingredients> restrictions){
      this.restrictions = restrictions;
   }
   public boolean addLike(String name, String description){
        return likes.add(new Ingredients(name, description));
    }
    public boolean addLike(Ingredients ingredient){
        return likes.add(ingredient);
    }
    public boolean removeLike(String name){
        return likes.remove(getLike(name));
    }
    public boolean removeLike(int i){
        return likes.remove(getLike(i));
    }
    public Ingredients getLike(String name){
        for(Ingredients i: likes){
            if(i.getName() == name){
               return i;
            }
        }       
        return null; 
    }
    public Ingredients getLike(int i){
        return likes.get(i);
    }
    public boolean addDislike(String name, String description){
        return dislikes.add(new Ingredients(name, description));
    }
    public boolean addDislike(Ingredients ingredient){
        return dislikes.add(ingredient);
    }
    public boolean removeDislike(String name){
        return dislikes.remove(getDislike(name));
    }
    public boolean removeDislike(int i){
        return dislikes.remove(getDislike(i));
    }
    public Ingredients getDislike(String name){
        for(Ingredients i: dislikes){
            if(i.getName() == name){
               return i;
            }
        }       
        return null; 
    }
    public Ingredients getDislike(int i){
        return dislikes.get(i);
    }
    public boolean addRestriction(String name, String description){
        return restrictions.add(new Ingredients(name, description));
    }
    public boolean addRestriction(Ingredients ingredient){
        return restrictions.add(ingredient);
    }
    public boolean removeRestriction(String name){
        return restrictions.remove(getRestriction(name));
    }
    public boolean removeRestriction(int i){
        return restrictions.remove(getRestriction(i));
    }
    public Ingredients getRestriction(String name){
        for(Ingredients i: restrictions){
            if(i.getName() == name){
               return i;
            }
        }       
        return null; 
    }
    public Ingredients getRestriction(int i){
        return restrictions.get(i);
    }
   
    public String[] getLikesNames(){
        String[] names = new String[likes.size()];
        
        for(int i = 0; i < likes.size(); i++){
            names[i] = likes.get(i).getName();
        }
        
        return names;
    }
    public String[] getDislikesNames(){
        String[] names = new String[dislikes.size()];
        
        for(int i = 0; i < dislikes.size(); i++){
            names[i] = dislikes.get(i).getName();
        }
        
        return names;
    }
    public String[] getRestrictionsNames(){
        String[] names = new String[restrictions.size()];
        
        for(int i = 0; i < restrictions.size(); i++){
            names[i] = restrictions.get(i).getName();
        }
        
        return names;
    }
}