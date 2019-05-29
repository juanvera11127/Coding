/*
 * Created by Angela, Juan, and Cody A.K.A TEAM MAGMA
 * 
 * Created for TEAM DENIM
 * 
 * Program is designed to create and tend a farm with the amount of activities per animal depending on user input
 * 
 */

import java.util.Scanner;

public class Farm {
	
	//VARS
	static int cowWeeks = 0, pigWeeks = 0, chickenWeeks = 0, sheepWeeks = 0, horseWeeks = 0;
	static int type;
	static boolean hasCow = false, hasPig = false, hasChicken = false, hasSheep = false, hasHorse = false;
	
	public static void main(String[] args) {

		// VARIABLES PASSED INTO CONSTRUCTORS
		String cowColor = "", pigColor = "", chickenColor = "", sheepColor = "", horseColor = "";
		int cowWash = 0, pigWash = 0, chickenWash = 0, sheepWash = 0, horseWash = 0;
		int cowFeed = 0, pigFeed = 0, chickenFeed = 0, sheepFeed = 0, horseFeed = 0;
		int cowEx = 0, pigEx = 0, chickenEx = 0, sheepEx = 0, horseEx = 0;
		int cowMilk = 0, pigMud = 0, chickenEggs = 0, sheepSheared = 0, horseCombed = 0;

		// SCANNER FOR USER INPUT
		Scanner kb = new Scanner(System.in);

		// USER INTERACTION, ASKS WHICH ANIMALS OWNER HAS AND THE RATE OF ACTIVITIES OF
		// EACH
		boolean have = false;
		while (!have) {
			System.out.print("What kind of animals do you have?(1-cow, 2-pig, 3-chicken, 4-sheep, 5-horse, 6-done) ");
			type = kb.nextInt();
			switch (type) {
			// COW INFO
			case (1): {
				hasCow = true;
				System.out.print("How many weeks have you had these cows? ");
				cowWeeks = kb.nextInt();

				System.out.println("What color are your cows?");
				cowColor = kb.next();

				System.out.println("How many times a day do you need to feed these cows?");
				cowFeed = kb.nextInt();

				System.out.println("How many times a week do you need to wash these cows?");
				cowWash = kb.nextInt();

				System.out.println("How many hours a week do your cows need to exercise for?");
				cowEx = kb.nextInt();

				System.out.println("How many times a week do you need to milk these cows?");
				cowMilk = kb.nextInt();

				break;
			}
			// PIG INFO
			case (2): {
				hasPig = true;
				System.out.print("How many weeks have you had these pigs? ");
				pigWeeks = kb.nextInt();

				System.out.println("What color are your pigs?");
				pigColor = kb.next();

				System.out.println("How many times a day do you need to feed these pigs?");
				pigFeed = kb.nextInt();

				System.out.println("How many times a week do you need to wash these pigs?");
				pigWash = kb.nextInt();

				System.out.println("How many hours a week do your pigs need to exercise for?");
				pigEx = kb.nextInt();

				System.out.println("How many times a week do your pigs need to play in the mud?");
				pigMud = kb.nextInt();

				break;
			}
			// CHICKEN INFO
			case (3): {
				hasChicken = true;
				System.out.print("How many weeks have you had these chickens? ");
				chickenWeeks = kb.nextInt();

				System.out.println("What color are your chickens?");
				chickenColor = kb.next();

				System.out.println("How many times a day do you need to feed these chickens?");
				chickenFeed = kb.nextInt();

				System.out.println("How many times a week do you need to wash these chickens?");
				chickenWash = kb.nextInt();

				System.out.println("How many hours a week do your chickens need to exercise for?");
				chickenEx = kb.nextInt();

				System.out.println("How many times a week do your chickens lay eggs?");
				chickenEggs = kb.nextInt();

				break;
			}
			// SHEEP INFO
			case (4): {
				hasSheep = true;
				System.out.print("How many weeks have you had these sheep? ");
				sheepWeeks = kb.nextInt();

				System.out.println("What color are your sheep?");
				sheepColor = kb.next();

				System.out.println("How many times a day do you need to feed these sheep?");
				sheepFeed = kb.nextInt();

				System.out.println("How many times a week do you need to wash these sheep?");
				sheepWash = kb.nextInt();

				System.out.println("How many hours a week do your sheep need to exercise for?");
				sheepEx = kb.nextInt();

				System.out.println("How many times a week do you need to shear these sheep?");
				sheepSheared = kb.nextInt();

				break;
			}
			// HORSE INFO
			case (5): {
				hasHorse = true;
				System.out.print("How many weeks have you had these horses? ");
				horseWeeks = kb.nextInt();

				System.out.println("What color are your horses?");
				horseColor = kb.next();

				System.out.println("How many times a day do you need to feed these horses?");
				horseFeed = kb.nextInt();

				System.out.println("How many times a week do you need to wash these horses?");
				horseWash = kb.nextInt();

				System.out.println("How many hours a week do your horses need to exercise for?");
				horseEx = kb.nextInt();

				System.out.println("How many times a week do you need to comb these horses?");
				horseCombed = kb.nextInt();

				break;
			}
			// USER IS DONE WITH ANIMAL INFO
			case (6): {
				have = true;
				break;
			}
			default: {
				System.out.print(
						"Invalid input. Please try again. (1-cows, 2-pigs, 3-chickens, 4-sheep, 5-horses, 6-done) ");
			}
			}
		}
		// CREATES ANIMAL CLASSES BASED ON USER INPUTS
		Cow cw = new Cow(cowColor, cowFeed, cowWash, cowEx, cowMilk);
		Pig pg = new Pig(pigColor, pigFeed, pigWash, pigEx, pigMud);
		Chicken chkn = new Chicken(chickenColor, chickenFeed, chickenWash, chickenEx, chickenEggs);
		Sheep shp = new Sheep(sheepColor, sheepFeed, sheepWash, sheepEx, sheepSheared);
		Horse hrs = new Horse(horseColor, horseFeed, horseWash, horseEx, horseCombed);

		// ANIMALS ARE TENDED PER USERS REQUESTS
		cw.incrementFed(cowWeeks);
		cw.incrementWashed(cowWeeks);
		cw.incrementExercised(cowWeeks);
		cw.incrementMilked(cowWeeks);

		pg.incrementFed(pigWeeks);
		pg.incrementWashed(pigWeeks);
		pg.incrementExercised(pigWeeks);
		pg.incrementMud(pigWeeks);

		chkn.incrementFed(chickenWeeks);
		chkn.incrementWashed(chickenWeeks);
		chkn.incrementExercised(chickenWeeks);
		chkn.incrementEggs(chickenWeeks);

		shp.incrementFed(sheepWeeks);
		shp.incrementWashed(sheepWeeks);
		shp.incrementExercised(sheepWeeks);
		shp.incrementSheared(sheepWeeks);

		hrs.incrementFed(horseWeeks);
		hrs.incrementWashed(horseWeeks);
		hrs.incrementExercised(horseWeeks);
		hrs.incrementCombed(horseWeeks);

		// USER DECIDES WHAT ACTIONS TO DO
		boolean what = false;
		while (!what) {
			System.out.println("What would you like to do? (1-check on animals, 2-make corrections, 3-done) ");
			type = kb.nextInt();
			switch (type) {
			case (1): {
				// CHECK COWS
				if (hasCow) {
					System.out.printf("You have had your %s cows for %d weeks.%n", cowColor, cowWeeks);
					System.out.printf("In that time, they have been fed %d times and cleaned %d times, ", cw.getFed(),
							cw.getWashed());
					System.out.printf("taken out for exercise %d times and milked %d times.%n", cw.getExercised(),
							cw.getMilked());
				}
				// CHECK PIGS
				if (hasPig) {
					System.out.printf("You have had your %s pigs for %d weeks.%n", pigColor, pigWeeks);
					System.out.printf("In that time, they have been fed %d times, given water %d times, ", pg.getFed(),
							pg.getWashed());
					System.out.printf("taken out for exercise %d times, and played in the mud %d times.%n",
							pg.getExercised(), pg.getMud());
				}
				// CHECK CHICKENS
				if (hasChicken) {
					System.out.printf("You have had your %s chickens for %d weeks.%n", chickenColor, chickenWeeks);
					System.out.printf("In that time, they have been fed %d times, given water %d times, ",
							chkn.getFed(), chkn.getWashed());
					System.out.printf("taken out for exercise %d times, and had %d eggs.%n", chkn.getExercised(),
							chkn.getEggs());
				}
				// CHECK SHEEP
				if (hasSheep) {
					System.out.printf("You have had your %d sheep, for %d weeks.%n", sheepColor, sheepWeeks);
					System.out.printf("In that time, they have been fed %d times, given water %d times, ", shp.getFed(),
							shp.getWashed());
					System.out.printf("taken out for exercise %d times, and sheared %d times.%n", shp.getExercised(),
							shp.getSheared());
				}
				// CHECK HORSES
				if (hasHorse) {
					System.out.printf("You have had your %s horses, for %d weeks.%n", horseColor, horseWeeks);
					System.out.printf("In that time, they have been fed %d times, given water %d times, ", hrs.getFed(),
							hrs.getWashed());
					System.out.printf("taken out for exercise %d times, and combed %d times.%n", hrs.getExercised(),
							hrs.getCombed());
				}
				break;
			}
			// USER WANTS TO RESET RATES
			case (2): {
				System.out.println(
						"What animal would you like to make corrections to? (1-cow, 2-pig, 3-chicken, 4-sheep, 5-horse)");
				type = kb.nextInt();
				switch (type) {
				// RESET COW
				case (1):
					System.out.println(
							"What rate would you like to change? 1-feeding, 2-washing, 3-exercising, 4-milking");
					type = kb.nextInt();
					System.out.println("What will your new rate be?");
					switch (type) {
					case (1):
						cw.setFed(kb.nextInt());
						break;
					case (2):
						cw.setWashed(kb.nextInt());
						break;
					case (3):
						cw.setExercised(kb.nextInt());
						break;
					case (4):
						cw.setMilked(kb.nextInt());
						break;
					default:
						System.out.println(
								"that is not a correct response, 1-feeding, 2-washing, 3-exercising, 4-milking");
					}
					break;
				// RESET PIG
				case (2):
					System.out.println(
							"What rate would you like to change? 1-feeding, 2-washing, 3-exercising, 4-mudPlay");
					type = kb.nextInt();
					System.out.println("What will your new rate be?");
					switch (type) {
					case (1):
						pg.setFed(kb.nextInt());
						break;
					case (2):
						pg.setWashed(kb.nextInt());
						break;
					case (3):
						pg.setExercised(kb.nextInt());
						break;
					case (4):
						pg.setMud(kb.nextInt());
						break;
					default:
						System.out.println(
								"that is not a correct response, 1-feeding, 2-washing, 3-exercising, 4-mudPlay");
					}
					break;
				// RESET CHICKEN
				case (3):
					System.out
							.println("What rate would you like to change? 1-feeding, 2-washing, 3-exercising, 4-Eggs");
					type = kb.nextInt();
					System.out.println("What will your new rate be?");
					switch (type) {
					case (1):
						chkn.setFed(kb.nextInt());
						break;
					case (2):
						chkn.setWashed(kb.nextInt());
						break;
					case (3):
						chkn.setExercised(kb.nextInt());
						break;
					case (4):
						chkn.setEggs(kb.nextInt());
						break;
					default:
						System.out
								.println("that is not a correct response, 1-feeding, 2-washing, 3-exercising, 4-Eggs");
					}
					break;
				// RESET SHEEP
				case (4):
					System.out.println(
							"What rate would you like to change? 1-feeding, 2-washing, 3-exercising, 4-shearing");
					type = kb.nextInt();
					System.out.println("What will your new rate be?");
					switch (type) {
					case (1):
						shp.setFed(kb.nextInt());
						break;
					case (2):
						shp.setWashed(kb.nextInt());
						break;
					case (3):
						shp.setExercised(kb.nextInt());
						break;
					case (4):
						shp.setSheared(kb.nextInt());
						break;
					default:
						System.out.println(
								"that is not a correct response, 1-feeding, 2-washing, 3-exercising, 4-shearing");
					}
					break;
				// RESET HORSE
				case (5):
					System.out.println(
							"What rate would you like to change? 1-feeding, 2-washing, 3-exercising, 4-Combing");
					type = kb.nextInt();
					System.out.println("What will your new rate be?");
					switch (type) {
					case (1):
						hrs.setFed(kb.nextInt());
						break;
					case (2):
						hrs.setWashed(kb.nextInt());
						break;
					case (3):
						hrs.setExercised(kb.nextInt());
						break;
					case (4):
						hrs.setCombed(kb.nextInt());
						break;
					default:
						System.out.println(
								"that is not a correct response, 1-feeding, 2-washing, 3-exercising, 4-Combing");
					}
					break;
				}

				break;
			}
			// USER IS DONE
			case (3): {
				System.out.println("End of Program.");
				what = true;
				kb.close();
				break;
			}
			default: {
				System.out.print("Invalid input. Please try again. (1-check on animals, 2-make corrections, 3-done)");
			}
			}
		}
	}
}
