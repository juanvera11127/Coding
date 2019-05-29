import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	private static int size = 2;

	public static void main(String[] args) {
		
		//user chooses method
		System.out.println("1- Classic \n2- Divide and Conquer \n3- Strassen\n4- Done");
		Scanner kboard = new Scanner(System.in);
		
		Queue<Integer> queue = new LinkedList<>();
		int choice = kboard.nextInt();
		while(choice != 4) {
			queue.add(choice);
			choice = kboard.nextInt();
		}
		while(queue.size() > 0) {
			choice = queue.remove();
			if(choice == 1) {			
				ClassicRunner(size);
			}
			if(choice == 2) {			
				DivideAndConquerRunner(size);
			}
			if(choice == 3) {			
				StrassenRunner(size);
			}
		}
	}

	public static void ClassicRunner(int size) {
		System.out.println("starting classic");
		long start = System.currentTimeMillis();
		long total = System.currentTimeMillis();
		int[][] a = new int[size][size];
		int[][] b = new int[size][size];
		int[][] c = new int[size][size];
		populate(a, 1);
		populate(b, 1);
		boolean isFinished = false;
		while (!isFinished) {
			start = System.currentTimeMillis();
			System.out.println("Testing size " + size + "... ");
			Classic(size, a, b, c);
			System.out.println("Time for size " + size + ": " + (int) (System.currentTimeMillis() - start) + "ms");
			System.out.println("Time elapsed: " + (System.currentTimeMillis() - total) + "ms");
			size *= 2;
			if(size > 10000) {
				isFinished = true;
			}
			else {
				a = new int[size][size];
				b = new int[size][size];
				c = new int[size][size];
				populate(a, 1);
				populate(b, 1);				
			}
		}
	}

	public static void DivideAndConquerRunner(int size) {
		System.out.println("starting divide and conquer");
		long start = System.currentTimeMillis();
		long total = System.currentTimeMillis();
		int[][] a = new int[size][size];
		int[][] b = new int[size][size];
		int[][] c = new int[size][size];
		populate(a, 1);
		populate(b, 1);
		boolean isFinished = false;
		while (!isFinished) {
			start = System.currentTimeMillis();
			System.out.println("Testing size " + size + "... ");
			DivideAndConquer(size, a, b, c);
			System.out.println("Time for size " + size + ": " + (int) (System.currentTimeMillis() - start) + "ms");
			System.out.println("Time elapsed: " + (System.currentTimeMillis() - total) + "ms");
			size *= 2;
			if(size > 8000) {
				isFinished = true;
			}
			else {
				a = new int[size][size];
				b = new int[size][size];
				c = new int[size][size];
				populate(a, 1);
				populate(b, 1);				
			}
		}
	}
	
	public static void StrassenRunner(int size) {
		System.out.println("starting strassen");
		long start = System.currentTimeMillis();
		long total = System.currentTimeMillis();
		int[][] a = new int[size][size];
		int[][] b = new int[size][size];
		int[][] c = new int[size][size];
		populate(a, 1);
		populate(b, 1);
		boolean isFinished = false;
		while (!isFinished) {
			start = System.currentTimeMillis();
			System.out.println("Testing size " + size + "... ");
			Strassen(size, a, b, c);
			System.out.println("Time for size " + size + ": " + (int) (System.currentTimeMillis() - start) + "ms");
			System.out.println("Time elapsed: " + (System.currentTimeMillis() - total) + "ms");
			size *= 2;
			if(size > 8000) {
				isFinished = true;
			}
			else {
				a = new int[size][size];
				b = new int[size][size];
				c = new int[size][size];
				populate(a, 1);
				populate(b, 1);				
			}
		}
	}
	
	public static void populate(int[][] arr, int n) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				arr[i][j] = n;
			}
		}	
	}
	
	public static void print(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + "   ");
			}
			System.out.println();
		}
		System.out.println();
	}
	

	public static void Classic(int size, int[][] a, int[][] b, int[][] c) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					c[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return;
	}

	public static void DivideAndConquer(int size, int[][] a, int[][] b, int[][] c) {
		if (size == 2) {
			c[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0];
			c[0][1] = a[0][0] * b[0][1] + a[0][1] * b[1][1];
			c[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0];
			c[1][1] = a[1][0] * b[0][1] + a[1][1] * b[1][1];
		}

		else {
			int[][] a11 = copy(a, 0, size / 2, 0, size / 2);
			int[][] a12 = copy(a, 0, size / 2, size / 2, size);
			int[][] a21 = copy(a, size / 2, size, 0, size / 2);
			int[][] a22 = copy(a, size / 2, size, size / 2, size);
			int[][] b11 = copy(b, 0, size / 2, 0, size / 2);
			int[][] b12 = copy(b, 0, size / 2, size / 2, size);
			int[][] b21 = copy(b, size / 2, size, 0, size / 2);
			int[][] b22 = copy(b, size / 2, size, size / 2, size);
			
			int[][] c1 = new int[size/2][size/2];
			int[][] c2 = new int[size/2][size/2];
			int[][] c3 = new int[size/2][size/2];
			int[][] c4 = new int[size/2][size/2];
			int[][] c5 = new int[size/2][size/2];
			int[][] c6 = new int[size/2][size/2];
			int[][] c7 = new int[size/2][size/2];
			int[][] c8 = new int[size/2][size/2];
			
			DivideAndConquer(size/2, a11, b11, c1);
			DivideAndConquer(size/2, a12, b21, c2);
			DivideAndConquer(size/2, a11, b12, c3);
			DivideAndConquer(size/2, a12, b22, c4);
			DivideAndConquer(size/2, a21, b11, c5);
			DivideAndConquer(size/2, a22, b21, c6);
			DivideAndConquer(size/2, a21, b12, c7);
			DivideAndConquer(size/2, a22, b22, c8);

			combine(add(c1,c2), add(c3,c4), add(c5,c6), add(c7,c8), c);
		}

		return;
	}
	
	public static void Strassen(int size, int[][] a, int[][] b, int[][] c) {
		if (size == 2) {
			c[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0];
			c[0][1] = a[0][0] * b[0][1] + a[0][1] * b[1][1];
			c[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0];
			c[1][1] = a[1][0] * b[0][1] + a[1][1] * b[1][1];
		}

		else {
			int[][] a11 = copy(a, 0, size / 2, 0, size / 2);
			int[][] a12 = copy(a, 0, size / 2, size / 2, size);
			int[][] a21 = copy(a, size / 2, size, 0, size / 2);
			int[][] a22 = copy(a, size / 2, size, size / 2, size);
			int[][] b11 = copy(b, 0, size / 2, 0, size / 2);
			int[][] b12 = copy(b, 0, size / 2, size / 2, size);
			int[][] b21 = copy(b, size / 2, size, 0, size / 2);
			int[][] b22 = copy(b, size / 2, size, size / 2, size);

			int[][] P = new int[size/2][size/2];
			int[][] Q = new int[size/2][size/2];
			int[][] R = new int[size/2][size/2];
			int[][] S = new int[size/2][size/2];
			int[][] T = new int[size/2][size/2];
			int[][] U = new int[size/2][size/2];
			int[][] V = new int[size/2][size/2];
			
			Strassen(size/2, add(a11,a22), add(b11,b22), P);
			Strassen(size/2, add(a21,a22), b11, Q);
			Strassen(size/2, a11, sub(b12,b22), R);
			Strassen(size/2, a22, sub(b21,b11), S);
			Strassen(size/2, add(a11, a12), b22, T);
			Strassen(size/2, sub(a21, a11), add(b11, b12), U);
			Strassen(size/2, sub(a12, a22), add(b21, b22), V);
		
			combine(add(sub(add(P,S),T),V),
					add(R,T),
					add(Q,S),
					add(sub(add(P,R),Q),U), c);
		}

		return;
	}

	public static int[][] combine(int[][] c11, int[][] c12, int[][] c21, int[][] c22, int[][] c) {
		int length = c.length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (i < length / 2 && j < length / 2) {
					c[i][j] = c11[i][j];
				}
				if (i < length / 2 && j >= length / 2) {
					c[i][j] = c12[i][j - length / 2];
				}
				if (i >= length / 2 && j < length / 2) {
					c[i][j] = c21[i - length / 2][j];
				}
				if (i >= length / 2 && j >= length / 2) {
					c[i][j] = c22[i - length / 2][j - length / 2];
				}
			}
		}
		return c;
	}

	public static int[][] add(int[][] a, int[][] b) {
		int size = a.length;
		int[][] c = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				c[i][j] = a[i][j] + b[i][j];
			}
		}
		return c;
	}
	
	public static int[][] sub(int[][] a, int[][] b) {
		int size = a.length;
		int[][] c = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				c[i][j] = a[i][j] - b[i][j];
			}
		}
		return c;
	}

	public static int[][] copy(int[][] m, int startRow, int endRow, int startCol, int endCol) {
		int[][] toReturn = new int[endRow-startRow][endCol-startCol];
		for (int i = startRow; i < endRow; i++) {
			for (int j = startCol; j < endCol; j++) {
				toReturn[i - startRow][j - startCol] = m[i][j];
			}
		}

		return toReturn;
	}

}
