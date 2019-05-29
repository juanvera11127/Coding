

public class PC {

      private String[] pokemon;
      private int counter;

      public PC() {
            counter = 12;
            pokemon = new String[12];
            pokemon[0] = "Charmander";
            pokemon[1] = "Charmeleon";
            pokemon[2] = "Charizard";
            pokemon[3] = "Vulpix";
            pokemon[4] = "Nine Tails";
            pokemon[5] = "Growlithe";
            pokemon[6] = "Arcanine";
            pokemon[7] = "Ponyta";
            pokemon[8] = "Rapidash";
            pokemon[9] = "Magmar";
            pokemon[10] = "Flareon";
            pokemon[11] = "Moltres"; }

      public void withdraw(String s) {
            for (int i = 0; i < pokemon.length; i++) {
                  if (s.equals(pokemon[i])) {
                        counter--;
                        for (int j = i; j < pokemon.length - 1; j++) {
                              pokemon[j] = pokemon [j + 1];
                        }
                  }
             }
      }

      public void deposit(String s) {
             if (pokemon.length < 12) {
                   pokemon[counter] = s;
                   counter++;
             }
      }

      public int counting() {
            return counter;
      }

      public void displayPokemon() {
            System.out.print("You can choose from the following Pokemon: ");
            for (int i = 0; i < counter; i++) {
                  System.out.print(pokemon[i] + ", ");
            }
       }

} 