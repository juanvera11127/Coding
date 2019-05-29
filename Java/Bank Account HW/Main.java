/*
 * 1/5/18
 * Team Dr Pepper's code to solve GSJ Team's Bank Account problem.
 * Created by Juan Vera and Cody Carson
 * 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	//ACCOUNT AMMOUNTS
	static double checking;
	static double savings;
	static double credit;

	// SETS MINIMUM TRANSACTION COUNT AMOUNT PER MONTH
	static int min = 5;

	// SETS FEE FOR NOT MEETING MINIMUM
	static int fee = 20;

	//TRANSACTION COUNT ON SPECIFIED ACCOUNT
	static int checkingCount;
	static int savingsCount;
	static int creditCount;
	
	//CHECKS IF LAST MONTH USER MET TRANSACTION AMOUNT
	static boolean checkingMet;
	static boolean savingsMet;
	static boolean creditMet;
	
	//OTHERS
	static Account account;
	static boolean isFinished;
	static String accountName = "";
	static String answer2 = "";
	static double amount;
	static String answer = "";
	static double[] loadedAmounts = new double[3];
	static int[] loadedCounts = new int[3];
	static boolean[] loadedBools = new boolean[3];

	public static void main(String[] args) {
		
		//READS ACCOUNTS.TXT FILE FROM BANK
		File f = new File("Accounts.txt");
		FileWriter w;
		Scanner s = null;
		try {

			s = new Scanner(f);
			while(s.hasNextDouble()) {
				
				for(int i = 0; i < 3; i++) {
					loadedAmounts[i] = s.nextDouble();
				}
				for(int i = 0; i < 3; i++) {
					loadedCounts[i] = s.nextInt();
				}
			}	
			while(s.hasNextBoolean()) {				
				for(int i = 0; i < 3; i++) {
					loadedBools[i] = s.nextBoolean();
				}
			}
			s.close();
		}
		catch (Exception e) {
			System.out.println("ERROR: File not found.");
		}
		
		//STORES FILE VALUES INTO VARS
		checking = loadedAmounts[0];
		savings = loadedAmounts[1];
		credit = loadedAmounts[2];
		
		checkingCount = loadedCounts[0];
		savingsCount = loadedCounts[1];
		creditCount = loadedCounts[2];
		
		checkingMet = loadedBools[0];
		savingsMet = loadedBools[1];
		creditMet = loadedBools[2];

		while (!isFinished) {
			
			// MACHINE INTERACTION WITH USER
			System.out.println("Welcome to Dr. Pepper Bank");
			System.out.println();
			System.out.println("Choose an account: checking, savings, credit");
			Scanner k = new Scanner(System.in);
			accountName = k.next();
			while (!accountName.equals("checking") && !accountName.equals("savings") && !accountName.equals("credit")) {
				System.out.println("That is not a valid account.  Choose an account: checking, savings, credit");
				accountName = k.nextLine();
			}

			// SELECTS DESIRED ACCOUNT
			if (accountName.equals("checking")) {
				account = new Account(checking, checkingCount);
			} else if (accountName.equals("savings")) {
				account = new Account(savings, savingsCount);
			} else if (accountName.equals("credit")) {
				account = new Account(credit, creditCount);
			}

			// MACHINE INTERACTION
			System.out.println(
					"Welcome to your " + accountName + " account. " + "You currently have " + account.getAmount()
							+ (account.getAmount() == 1 ? " dollar and " : " dollars and ") + account.getCount()
							+ (account.getCount() == 1 ? " transaction this month" : " transactions this month."));
			System.out.println();
			if (account.getCount() < min) {
				System.out.println("WARNING: YOU NEED " + (min - account.getCount())
						+ " MORE TRANSACTIONS BY THE END OF THE MONTH TO AVOID A CHARGE OF $" + fee + ".");
			}
			if(checkingMet) {
				checkingMet = false;
				checking -= fee;
				System.out.println();
				System.out.println("IMPORTANT: YOU DID NOT FULFILL YOUR TRANSACTIONS AMOUNT LAST MONTH FOR YOUR CHECKING ACCOUNT, YOU HAVE BEEN CHARGED $" + fee + ".");
			}
			if(savingsMet) {
				savingsMet = false;
				savings -= fee;
				System.out.println();
				System.out.println("IMPORTANT: YOU DID NOT FULFILL YOUR TRANSACTIONS AMOUNT LAST MONTH FOR YOUR SAVINGS ACCOUNT, YOU HAVE BEEN CHARGED $" + fee + ".");
			}
			if(creditMet) {
				creditMet = false;
				credit -= fee;
				System.out.println();
				System.out.println("IMPORTANT: YOU DID NOT FULFILL YOUR TRANSACTIONS AMOUNT LAST MONTH FOR YOUR CREDIT ACCOUNT, YOU HAVE BEEN CHARGED $" + fee + ".");
			}
			
			System.out.println();
			System.out.println("What would you like to do? (deposit or withdraw)");
			answer2 = k.next();
			while (!answer2.equals("deposit") && !answer2.equals("withdraw")) {
				System.out.println("That is not a valid action.  Choose an action: deposit, withdraw");
				answer2 = k.nextLine();
			}
			
			//DEPOSITS MONEY INTO SPECIFIED ACCOUNT
			if (answer2.equals("deposit")) {
				System.out.println("How much would you like to deposit?");
				amount = k.nextDouble();
				while(amount < 0) {
					System.out.println("Are you trying to cheat the system?  Please try again.");
					amount = k.nextInt();
				}
				account.depositAmount(amount);
				account.increment();

				
				//LOOPS PROGRAM
				System.out.println("You now have $" + account.getAmount() + " in your " + accountName + " account and " + account.getCount()
						+ " transactions this month.  Would you like to make another transaction? (y/n)");
				answer = k.next();
				while (!answer.equals("y") && !answer.equals("n")) {
					System.out.println("That is not a valid action.  Choose an action: y/n");
					answer = k.nextLine();
				}
				isFinished = answer.equals("n");

			}
			
			//WITHDRAWS MONEY FROM SPECIFIED ACCOUNT
			if (answer2.equals("withdraw")) {
				
				//ADDS $1 TO SAVINGS IF MONEY IS WITHDRAWN
				savings++;
				
				//INTERACTION
				System.out.println("How much would you like to withdraw?");
				amount = k.nextDouble();
				while(amount > account.getAmount() || amount < 0) {
					System.out.println("Oops, either your number exceeds your current bank amount or you are trying to cheat the system.  Please try again.");
					amount = k.nextDouble();
				}
				account.withdrawAmount(amount);
				account.increment();
				

				//LOOPS PROGRAM
				System.out.println("You now have $" + account.getAmount() + " in your " + accountName + " account and " + account.getCount()
						+ " transactions this month.  Would you like to make another transaction? (y/n)");
				answer = k.next();
				while (!answer.equals("y") && !answer.equals("n")) {
					System.out.println("That is not a valid action.  Choose an action: y/n");
					answer = k.nextLine();
				}
				isFinished = answer.equals("n");
			}
		}
		//END OF USER INTERACTION
		
		//STORES INFORMATION INTO FILE
		try {
			w = new FileWriter("Accounts.txt");
			BufferedWriter b = new BufferedWriter(w);
			b.write(String.valueOf(checking));
			b.write(String.valueOf(savings));
			b.write(String.valueOf(credit));
			b.write(checkingCount);
			b.write(savingsCount);
			b.write(creditCount);
			b.write(String.valueOf(checkingMet));
			b.write(String.valueOf(savingsMet));
			b.write(String.valueOf(creditMet));
			b.close();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thank You for using Dr. Pepper Bank's services.");
	}

}
