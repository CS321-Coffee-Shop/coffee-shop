import java.util.*;

public class Menu{

    protected ArrayList<MenuItem> menu;

    public Menu(ArrayList<MenuItem> menu){
        this.menu = menu;
    }
    public Menu(){
        menu = new ArrayList<MenuItem>();
    }
    public void setMenu(ArrayList<MenuItem> menu){
        this.menu = menu;
    }
    public ArrayList<MenuItem> getMenu(){
        return menu;
    }
    public boolean addItem(String name, double price, String[] ingredients){
        return menu.add(new MenuItem(name, price, ingredients));
    }
    public boolean addItem(MenuItem item){
        return menu.add(item);
    }
    public boolean removeItem(String name){
        return menu.remove(getItem(name));
    }
    public MenuItem getItem(String name){
        for(MenuItem i: menu){
            if(i.getName().equals(name)){
               return i;
            }
        }       
        return null; 
    }
    public MenuItem getItem(int i){
        return menu.get(i);
    }
    public int size(){
        return menu.size();
    }
    public void resetScores(){
        for(MenuItem i: menu){
           i.setScore(0);
        }
    }
    public double getTotalPrice(){
        double total = 0;
        
        for(MenuItem i: menu){
           total += i.getPrice();
        }
        return total;
    }
    @Override 
    public String toString(){
        return "";
    }
}
