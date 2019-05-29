import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
	
	static int size = 100000;
	static final int MAX_SIZE = 250000000;
	static int[] a;
	static int counter = 0;
	static final int r = 5;
	
	public static void main(String[] args) {		
		System.out.println("Input number of cycles");
		Scanner kboard = new Scanner(System.in);
		int num = kboard.nextInt();
		while(num > 0) {
			while (size <= MAX_SIZE) {
				a = new int[size];
				Tools.populate(a, MAX_SIZE/100);
				System.out.println("size " + size + ":");
				System.out.println("Starting MergeSort...");
				Runner(1);
				System.out.println("Starting QuickSelect Iterative...");
				Runner(2);
				System.out.println("Starting QuickSelect Recursive...");				
				Runner(3);
				System.out.println("Starting MM...");				
				Runner(4);
				if(counter%3 == 0) 
					size = size * 5 / 2;
				else 
					size *= 2;
				counter++;
			}
			num--;
		}
		System.out.println("done");
	}

	public static void Runner(int choice) {	
		int[] arr = Tools.copy(a);
		long start = System.currentTimeMillis();
		
		switch (choice) {
		case 1 :
			int[] buffer = new int[arr.length];
			MergeSort(arr, buffer, 0, size-1);
			int time = (int) (System.currentTimeMillis() - start);
			long sum = System.currentTimeMillis();
			Tools.calculate(arr[0] + arr[size/4] + arr[size/2] + arr[size*3/4] + arr[size-1]);
			for(int i = 0; i < 4; i++) {
				arr = Tools.copy(a);
			}
			System.out.println(time + (int) (System.currentTimeMillis() - sum)/5 + "ms");
			break;
		case 2 :
			start = System.currentTimeMillis();
			Tools.calculate(QuickSelectIterative(arr, 0));
			arr = Tools.copy(a);
			Tools.calculate(QuickSelectIterative(arr, size/4));
			arr = Tools.copy(a);
			Tools.calculate(QuickSelectIterative(arr, size/2));
			arr = Tools.copy(a);
			Tools.calculate(QuickSelectIterative(arr, size/4*3));
			arr = Tools.copy(a);
			Tools.calculate(QuickSelectIterative(arr, size-1));
			System.out.println("\nAverage: " + (int) (System.currentTimeMillis() - start)/5 + "ms");
			break;
		case 3 :
			start = System.currentTimeMillis();
			Tools.calculate(QuickSelectRecursive(arr, 0));
			arr = Tools.copy(a);
			Tools.calculate(QuickSelectRecursive(arr, size/4));
			arr = Tools.copy(a);
			Tools.calculate(QuickSelectRecursive(arr, size/2));
			arr = Tools.copy(a);
			Tools.calculate(QuickSelectRecursive(arr, size/4*3));
			arr = Tools.copy(a);
			Tools.calculate(QuickSelectRecursive(arr, size-1));
			System.out.println("\nAverage: " + (int) (System.currentTimeMillis() - start)/5 + "ms");
			break;
		case 4 :
			start = System.currentTimeMillis();
			Tools.calculate(MM(arr, 0));
			arr = Tools.copy(a);
			Tools.calculate(MM(arr, size/4));
			arr = Tools.copy(a);
			Tools.calculate(MM(arr, size/2));
			arr = Tools.copy(a);
			Tools.calculate(MM(arr, size/4*3));
			arr = Tools.copy(a);
			Tools.calculate(MM(arr, size-1));
			System.out.println("\nAverage: " + (int) (System.currentTimeMillis() - start)/5 + "ms");
			break;
		}
		System.out.println();
	}

	public static void MergeSort(int[] arr, int[] temp, int low, int high) {
		if(low < high) {
			int mid = (low + high) / 2;
			
			MergeSort(arr, temp, low, mid);
			MergeSort(arr, temp, mid+1, high);
			Merge(arr, temp, low, mid, high);
		}
	}
	
	public static void Merge(int[] arr, int[] temp, int low, int mid, int high) {
		int i = low;
		int j = mid + 1;
		int k = low;
		while(i <= mid && j <= high) {
			if(arr[i] < arr[j]) {
				temp[k] = arr[i];
				i++;
			}
			else {
				temp[k] = arr[j];
				j++;
			}
			k++;
		}
		if(i > mid) {
			while(j <= high) {
				temp[k] = arr[j];
				j++;
				k++;
			}
		}
		else {
			while(i <= mid) {
				temp[k] = arr[i];
				i++;
				k++;
			}
		}
		for(int p = low; p <= high; p++) {
			arr[p] = temp[p];
		}
	}

	public static int QuickSelectIterative(int[] arr, int k) {
		int low = 0;
		int high = arr.length-1;
		while(low <= high) {
			int pivot = Partition(arr, low, high, low);
			
			if(pivot == k) {
				return arr[pivot];
			}
			if(k < pivot) {
				high = pivot-1;
			}
			else {
				low = pivot+1;
			}
		}
		return arr[low];
	}
		
	public static int QuickSelectRecursive(int[] arr, int k) {
		if(arr.length >= 1) {
			int pivot = Partition(arr, 0, arr.length-1, 0);
			if(k == pivot) {
				return arr[k];
			}
			if(k < pivot) {
				return QuickSelectRecursive(Tools.copy(arr, 0, pivot), k);
			}
			return QuickSelectRecursive(Tools.copy(arr, pivot+1, arr.length), k-pivot-1);
		}
		return -1;
	}
	
	public static int Partition(int[] arr, int low, int high, int pivotposition) {
		int pivot = arr[pivotposition];
		Tools.swap(arr, low, pivotposition);
		int j = low;

		for(int i = low+1; i <= high; i++) {
			if(arr[i] < pivot) {
				j++;
				Tools.swap(arr, j, i);
			}
		}
		Tools.swap(arr, low, j);
		return j;
	}


	public static int MM(int[] arr, int k) {

		int v = findMM(arr, k);
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == v) {
				v = i;
				break;
			}
		}

		v = Partition(arr, 0, arr.length-1, v);
		if(k == v) 
			return arr[v];		
		if ( k < v) 
			return MM(Tools.copy(arr, 0, v), k);		
		return MM(Tools.copy(arr, v+1, arr.length), k-v-1);
	}
	
	private static int findMM(int[] arr, int k) {
		if(arr.length <= r) {
			Arrays.sort(arr);
			return arr[k];
		}	
		int num = arr.length/r;
		int[] M = new int[num];
		int[][] a = new int[num][r];

		for(int i = 0; i < num; i++) {
			a[i] = Tools.copy(arr, i * r, (i+1) * r);
			Arrays.sort(a[i]);
			M[i] = a[i][2];
		}

		return findMM(M, (int)Math.ceil(M.length/2));	
	}
}