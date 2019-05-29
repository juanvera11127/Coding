

public class Inventory { 
      private String[] friends; 
      private int counter;  
   
      public Inventory() { 
            friends = new String[6]; 
            friends[0] = "Charmander"; 
            counter = 1; 
       }  

       public Inventory(String s) {
  friends = new String[6];
         friends[0] = s;   
      counter = 1; 
        }  

       public void deposit(String s) {
  friends[counter] = s;
       counter++;  
       }  

        public void withdraw(String s) {
       for (int i = 0; i < friends.length; i++) { 
                    if (s.equals(friends[i])) {
 counter--; 
                 for (int j = i; j < friends.length - 1; j++) {
       friends[j] = friends [j + 1];  
                                } 
                    } 
              } 
         }   

         public int counting() {
 return counter; 
         }  

          public void displayInventory() { 
                System.out.print("You have the following Pokemon: ");
  for (int i = 0; i < counter; i++) { 
                      System.out.print(friends[i] + ", "); 
                } 
           }  

}