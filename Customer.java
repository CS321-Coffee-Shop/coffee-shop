public class Customer{

   String firstName;
   String lastName;
   String phoneNumber;
   String email;
   
 
   public Customer(){
      firstName = "";
      lastName = "";
      phoneNumber = "";
      email = "";
   }
   public Customer(String firstName, String lastName, String phoneNumber, String email){
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
      this.email = email;
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
}