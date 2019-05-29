import java.util.*;
import java.util.Scanner;
import java.io.*;

public class runner {

	public static void main(String[] args){
		int number;
		Scanner kb = new Scanner(System.in);
		System.out.println("give me a number boi");
		try {
			int a = kb.nextInt();
			File swag = new File("swag.txt");
			Scanner file = new Scanner(swag);
			
		}
		catch(InputMismatchException e) {
			System.out.println("u fucked up: " + e.getCause());
		}	
		catch(FileNotFoundException e) {
			System.out.println("file not found caught");
		}
		number = 5;
		System.out.println(number);
	}
}
