public class Tools {
	public static void populate(int[] arr, int max) {
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max);
		}	
	}
	
	public static void print(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static int[] copy(int[] arr) {
		int[] toReturn = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			toReturn[i] = arr[i];
		}
		return toReturn;
	}
	
	public static int[] copy(int[] n, int start, int end) {
		int[] toReturn = new int[end-start];
		for (int i = start; i < end; i++) {
				toReturn[i - start] = n[i];
		}
		return toReturn;
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void calculate(int a) {
		
	}
}