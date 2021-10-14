public class Customer{

   String firstName;
   String lastName;
   String phoneNumber;
   String email;
   String password;
 
   public Customer(){
      firstName = "";
      lastName = "";
      phoneNumber = "";
      email = "";
      password = "";
   }
   public Customer(String firstName, String lastName, String phoneNumber, String email, String password){
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
      this.email = email;
      this.password = password;
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

}