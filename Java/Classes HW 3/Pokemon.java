

import java.util.Scanner;

public class Pokemon
{
      public static void main(String[] args)
      {
             //initialize PC
             PC box = new PC();
             //display PC
             box.displayPokemon();
             System.out.println();
             //initialize keyboard and ask which pokemon user would like to withdraw
             Scanner kb = new Scanner(System.in);
             System.out.print("Which pokemon would you like to add to your Inventory? ");
             String i = kb.nextLine();
             if (i.equals(“Charmander”)) {
            	 
            	 i = “Charmander”;
             }
             else if (i.equalsIgnoreCase("Charmeleon"))
                   i = "Charmeleon";
             else if (i.equalsIgnoreCase("Charizard"))
                   i = "Charizard";
             else if (i.equalsIgnoreCase("Vulpix"))
                   i = "Vulpix";
             else if (i.equalsIgnoreCase("Nine Tails"))
                   i = "Nine Tails";
             else if (i.equalsIgnoreCase("Growlithe"))
                   i = "Growlithe";
             else if (i.equalsIgnoreCase("Arcanine"))
                   i = "Arcanine";
             else if (i.equalsIgnoreCase("Ponyta"))
                   i = "Ponyta";
             else if (i.equalsIgnoreCase("Rapidash"))
                   i = "Rapidash";
             else if (i.equalsIgnoreCase("Magmar"))
                   i = "Magmar";
             else if (i.equalsIgnoreCase("Flareon"))
                   i = "Flareon";
             else if (i.equalsIgnoreCase("Moltres"))
                   i = "Moltres";
             else {
                   System.out.print("You must choose one of the listed pokemon. Please enter again: ");
                   i = kb.nextLine();
             }

             //withdraw from pc
             box.withdraw(i);
             //initialize inventory and deposit first pokemon
             Inventory box2 = new Inventory(i);
             //box2.deposit(i);
             box2.displayInventory();
             System.out.print("Continue adding to Inventory? (y/n) ");
             String a = kb.nextLine();
             while (a.equalsIgnoreCase("y"))
             {
                   if(box2.counting() == 6)
                   {
                         System.out.println("Inventory is full.");
                         break;
                    }
                    box.displayPokemon();
                    System.out.println();
                    System.out.print("Which pokemon would you like to deposit to Inventory? ");
                    String input = kb.nextLine();
                    if (input.equalsIgnoreCase("Charmander"))
                          input = "Charmander";
                    else if (input.equalsIgnoreCase("Charmeleon"))
                          input = "Charmeleon";
                    else if (input.equalsIgnoreCase("Charizard"))
                          input = "Charizard";
                    else if (input.equalsIgnoreCase("Vulpix"))
                          input = "Vulpix";
                    else if (input.equalsIgnoreCase("Nine Tails"))
                          input = "Nine Tails";
                    else if (input.equalsIgnoreCase("Growlithe"))
                          input = "Growlithe";
                    else if (input.equalsIgnoreCase("Arcanine"))
                          input = "Arcanine";
                    else if (input.equalsIgnoreCase("Ponyta"))
                          input = "Ponyta";
                    else if (input.equalsIgnoreCase("Rapidash"))
                          input = "Rapidash";
                    else if (input.equalsIgnoreCase("Magmar"))
                          input = "Magmar";
                    else if (input.equalsIgnoreCase("Flareon"))
                          input = "Flareon";
                    else if (input.equalsIgnoreCase("Moltres"))
                          input = "Moltres";
                    else {
                          System.out.print("You must choose one of the listed pokemon. Please enter again: ");
                          input = kb.nextLine();
                    }
                    box.withdraw(input);
                    box2.deposit(input);
                    box2.displayInventory();
                    System.out.print("Continue adding to Inventory? (y/n) ");
                    a = kb.nextLine();
             }
             System.out.print("Would you like to withdraw pokemon from your Inventory? (y/n) ");
             String m = kb.nextLine();
             if (m.equalsIgnoreCase("y"))
             {
                   if (box2.counting() < 1)
                   {
                         System.out.println("You must have at least one pokemon.");
                         System.exit(0);
                   }
                   while (m.equalsIgnoreCase("y"))
                   {
                         box2.displayInventory();
                         System.out.println();
                         System.out.print("Which pokemon would you like to withdraw from your Inventory? ");
                         String storage = kb.nextLine();
                         if (storage.equalsIgnoreCase("Charmander"))
                               storage = "Charmander";
                         else if (storage.equalsIgnoreCase("Charmeleon"))
                               storage = "Charmeleon";
                         else if (storage.equalsIgnoreCase("Charizard"))
                               storage = "Charizard";
                         else if (storage.equalsIgnoreCase("Vulpix"))
                               storage = "Vulpix";
                         else if (storage.equalsIgnoreCase("Nine Tails"))
                               storage = "Nine Tails";
                         else if (storage.equalsIgnoreCase("Growlithe"))
                               storage = "Growlithe";
                         else if (storage.equalsIgnoreCase("Arcanine"))
                               storage = "Arcanine";
                         else if (storage.equalsIgnoreCase("Ponyta"))
                               storage = "Ponyta";
                         else if (storage.equalsIgnoreCase("Rapidash"))
                               storage = "Rapidash";
                         else if (storage.equalsIgnoreCase("Magmar"))
                               storage = "Magmar";
                         else if (storage.equalsIgnoreCase("Flareon"))
                               storage = "Flareon";
                         else if (storage.equalsIgnoreCase("Moltres"))
                               storage = "Moltres";
                         else {
                               System.out.print("You must choose one of the listed pokemon. Please enter again: ");
                               storage = kb.nextLine();
                         }
                         box2.withdraw(storage);
                         box.deposit(storage);
                         System.out.print("Continue withdrawing from your Inventory? (y/n) ");
                         m = kb.nextLine();
                   }
             }
             System.out.println("In your inventory...");
             box2.displayInventory();
             System.out.println();
             System.out.println("Thank you! Have a great day. ");

      }

} 