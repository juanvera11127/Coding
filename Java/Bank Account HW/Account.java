
public class Account {

	private static double amount;
	private static int transactionsCount;
	
	Account(double a, int t) {
		amount = a;
		transactionsCount = t;
	}
	
	static void depositAmount(double money) {
		amount += money;
		if(Main.accountName.equals("checking")) {
			Main.checking += money;
		}
		if(Main.accountName.equals("savings")) {
			Main.savings += money;
		}
		if(Main.accountName.equals("credit")) {
			Main.credit += money;
		}
	}
	
	static void withdrawAmount(double money) {
		amount -= money;
		if(Main.accountName.equals("checking")) {
			Main.checking -= money;
		}
		if(Main.accountName.equals("savings")) {
			Main.savings -= money;
		}
		if(Main.accountName.equals("credit")) {
			Main.credit -= money;
		}
	}
	
	static void increment() {
		transactionsCount++;
		if(Main.accountName.equals("checking")) {
			Main.checkingCount++;
		}
		if(Main.accountName.equals("savings")) {
			Main.savingsCount++;
		}
		if(Main.accountName.equals("credit")) {
			Main.creditCount++;
		}
	}
	
	static double getAmount() {
		return amount;
	}
	
	static int getCount() {
		return transactionsCount;
	}
}
