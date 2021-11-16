import java.util.*;

public class Order{
   double priceTotal;
   String customerName;
   ArrayList<MenuItem> orderList;
   
   public Order(){
      priceTotal = 0.0;
      customerName = "";
      orderList = new ArrayList<MenuItem>();
   }
   public Order(double p,String name, ArrayList<MenuItem> list){
      priceTotal = p;
      customerName = name;
      orderList = list;
   }
   public String getName(){
      return customerName;
   }
   public ArrayList<MenuItem> getOrder(){
      return orderList;
   }
   public String toString(){
      String order = "";
      for(int i=0;i<orderList.size();i++){
         order += orderList.get(i).getName()+", ";
      }
      order = order.substring(0, order.length()-2);
      return order;
   }
}