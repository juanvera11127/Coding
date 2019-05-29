import java.util.Scanner;

public class FindSum {

	public static void main(String[] args) {
		Scanner k = new Scanner(System.in);
		float total = 0;
		boolean isCount = true;
		float count = 0;
		while(k.hasNextFloat() || k.hasNextInt()) {
				if(isCount) {
					isCount = false;
					count = k.nextFloat();
				}
				else {
					isCount = true;
					total += k.nextFloat() * count;
				}
			
		}
		
		System.out.println(total);

	}

}
