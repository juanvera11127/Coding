import java.util.Scanner;

public class ShelterDriver {

	public static AnimalShelter[][] list = new AnimalShelter[2][5];
	public static Animal animal = new Animal("cat", 3);
	public static Volunteer volunteer = new Volunteer("john");
	public static Customer customer = new Customer("joe");

	public static void main(String[] args) {
		
		//SETS ANIMAL TYPES
		Animal cat = new Animal("cat", 20);
		Animal bat = new Animal("bat", 30);
		Animal dog = new Animal("dog", 21);
		Animal snake = new Animal("snake", 35);
		Animal monkey = new Animal("monkey", 70);
		Animal rat = new Animal("rat", 32);
		Animal hamster = new Animal("hamster", 53);
		Animal mouse = new Animal("mouse", 73);
		Animal bird = new Animal("bird", 93);
		Animal pig = new Animal("pig", 123);
		
		//SETS VOLUNTEERS
		Volunteer john = new Volunteer("John");
		Volunteer fred = new Volunteer("Fred");
		Volunteer juan = new Volunteer("Juan");
		Volunteer angela = new Volunteer("Angela");
		Volunteer cody = new Volunteer("Cody");
		Volunteer andrea = new Volunteer("Andrea");
		Volunteer samantha = new Volunteer("Samantha");
		Volunteer tony = new Volunteer("Tony");
		Volunteer erik = new Volunteer("Erik");
		Volunteer timmy = new Volunteer("Timmy");

		//SETS CUSTOMERS
		Customer frank = new Customer("Frank");
		Customer billy = new Customer("Billy");
		Customer andrew = new Customer("Andrew");
		Customer alex = new Customer("Alex");
		Customer stephanie = new Customer("Stephanie");
		Customer ana = new Customer("Ana");
		Customer sam = new Customer("Sam");
		Customer christopher = new Customer("Christopher");
		Customer anthony = new Customer("Anthony");
		Customer eddie = new Customer("Eddie");		

		//ASSIGNS ABOVE TO ANIMAL SHELTER
		list[0][0] = new AnimalShelter(cat, john, frank);
		list[0][1] = new AnimalShelter(bat, fred, billy);
		list[0][2] = new AnimalShelter(dog, juan, andrew);
		list[0][3] = new AnimalShelter(snake, angela, alex);
		list[0][4] = new AnimalShelter(monkey, cody, stephanie);
		list[1][0] = new AnimalShelter(rat, andrea, ana);
		list[1][1] = new AnimalShelter(hamster, samantha, sam);
		list[1][2] = new AnimalShelter(mouse, tony, christopher);
		list[1][3] = new AnimalShelter(bird, erik, anthony);
		list[1][4] = new AnimalShelter(pig, timmy, eddie);


		Scanner kb = new Scanner(System.in);

		boolean inUse = true;
		boolean recognized = false;
		int person;
		int count = 0;
		System.out.print("Please enter your name: ");
		String name = kb.next();
		//PERSON CHECK
		System.out.print("Are you a volunteer(1) or a customer(2)? ");
		person = kb.nextInt();

		while (inUse) {

			switch (person) {
			case (1): // volunteer
			{
				recognized = false;
				for (int i = 0; i < list.length; i++) {
					for (int j = 0; j < list[i].length; j++) {
						if (list[i][j].getVolunteerName().equals(name)) {
							recognized = true;
						}
					}
				}
				
				if (!recognized) {
					System.out.println("I'm sorry, your name is not in our database, please try again:");
				}
				while (!recognized) {
					name = kb.next();
					for (int i = 0; i < list.length; i++) {
						for (int j = 0; j < list[i].length; j++) {
							if (list[i][j].getVolunteerName().equals(name)) {
								recognized = true;
							}
						}
					}
					if (!recognized) {
						System.out.println("I'm sorry, your name is not in our database, please try again:");
					}
				}

				if(count == 0) {
					count++;
					System.out.println("Welcome back, " + name + ".  We do not have space for any more donations, thank you.");

				}
				
				System.out.println("What would you like to do? (1-browse, 2-search by type, 3-done)");
				int action = kb.nextInt();
				switch (action) {
				case (1): {
					System.out.println("These are the animals we currently have: ");
					for (int i = 0; i < list.length; i++) {
						for (int j = 0; j < list[i].length; j++) {
							System.out.print(list[i][j].getAnimalType() + ", ");
						}
						System.out.println();
					}

					break;
				}
				case (2): {
					System.out.println("What kind of animal are you looking for? ");
					boolean hasAnimal = false;
					int index1 = 0;
					int index2 = 0;
					String species = kb.next();
					for (int i = 0; i < list.length; i++) {
						for (int j = 0; j < list[i].length; j++) {
							if (list[i][j].getAnimalType().equals(species)) {
								hasAnimal = true;
								index1 = i;
								index2 = j;
							}

						}
					}
					if(hasAnimal) {
						System.out.println("we currently have that animal registered with us.  Adoption will cost $" + list[index1][index2].getAnimalCost() + ".  Would you like to buy it? (1-yes, 2-no)");
						action = kb.nextInt();
						if(action == 1) {
							list[index1][index2].setAnimalCost(0.00);
							System.out.println("Here's your " + list[index1][index2].getAnimalType() + ".  Have a nice day!");
							list[index1][index2].setAnimalType("");
						}
						
					}
					else{
						System.out.println("I'm sorry, that animal is not registered with us.");
					}
					break;
				}
				case (3): {
					inUse = false;
					break;
				}
				default: {
					System.out.print("What would you like to do?(1-browse, 2-search by type, 3-done) ");
					action = kb.nextInt();
				}

				}
				break;
			}
			case (2): // customer
			{				recognized = false;
			for (int i = 0; i < list.length; i++) {
				for (int j = 0; j < list[i].length; j++) {
					if (list[i][j].getCustomerName().equals(name)) {
						recognized = true;
					}
				}
			}
			
			if (!recognized) {
				System.out.println("I'm sorry, your name is not in our database, please try again:");
			}
			while (!recognized) {
				name = kb.next();
				for (int i = 0; i < list.length; i++) {
					for (int j = 0; j < list[i].length; j++) {
						if (list[i][j].getCustomerName().equals(name)) {
							recognized = true;
						}
					}
				}
				if (!recognized) {
					System.out.println("I'm sorry, your name is not in our database, please try again:");
				}
			}

			if(count == 0) {
				count++;
				System.out.println("Welcome back, " + name + ".");

			}
			
			System.out.println("What would you like to do? (1-browse, 2-search by type, 3-done)");
			int action = kb.nextInt();
			switch (action) {
			case (1): {
				System.out.println("These are the animals we currently have: ");
				for (int i = 0; i < list.length; i++) {
					for (int j = 0; j < list[i].length; j++) {
						System.out.print(list[i][j].getAnimalType() + ", ");
					}
					System.out.println();
				}

				break;
			}
			case (2): {
				System.out.println("What kind of animal are you looking for? ");
				boolean hasAnimal = false;
				int index1 = 0;
				int index2 = 0;
				String species = kb.next();
				for (int i = 0; i < list.length; i++) {
					for (int j = 0; j < list[i].length; j++) {
						if (list[i][j].getAnimalType().equals(species)) {
							hasAnimal = true;
							index1 = i;
							index2 = j;
						}

					}
				}
				if(hasAnimal) {
					System.out.println("we currently have that animal registered with us.  Adoption will cost $" + list[index1][index2].getAnimalCost() + ".  Would you like to buy it? (1-yes, 2-no)");
					action = kb.nextInt();
					if(action == 1) {
						list[index1][index2].setAnimalCost(0.00);
						System.out.println("Here's your " + list[index1][index2].getAnimalType() + ".  Have a nice day!");
						list[index1][index2].setAnimalType("");
					}
					
				}
				else{
					System.out.println("I'm sorry, that animal is not registered with us.");
				}
				break;
			}
			case (3): {
				inUse = false;
				break;
			}
			default: {
				System.out.print("What would you like to do?(1-browse, 2-search by type, 3-done) ");
				action = kb.nextInt();
			}

			}
			break;
		}
			}

		}
		System.out.println("Come again!");

	}
}