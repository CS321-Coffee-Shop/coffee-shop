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
    public double getTotalPrice(){
        
        return 0;
    }
    @Override 
    public String toString(){
        return "";
    }
}
