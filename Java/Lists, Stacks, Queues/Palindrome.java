import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class Palindrome {

	static WordDictionary dictionary = new WordDictionary();

	// Get all words that can be formed starting at this index
	public static String[] getWords(String text, int index) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i <= text.length() - index; i++) {
			String maybeWord = text.substring(index, index + i);
			if (dictionary.isWord(text.substring(index, index + i)) && maybeWord.length() > 0) {
				words.add(maybeWord);
			}
		}

		return words.toArray(new String[0]);
	}

	public static String reverseStringAndRemoveNonAlpha(String text) {
		/*
		 * TODO 3
		 */
		String temp = "";
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < text.length(); i++) {
			if (Character.isAlphabetic(text.charAt(i))) {
				stack.push(text.charAt(i));
			}
		}
		while (stack.size() > 0) {
			temp += stack.pop();
		}
		return temp;
		/*
		 * TODO 3
		 */
	}

	// Returns true if the text is a palindrome, false if not.
	public static boolean isPalindrome(String text) {

		/*
		 * TODO 4: Implement "explorePalindrome"
		 */
		String temp = text.toLowerCase();
		Stack<Character> stack = new Stack<Character>();
		Queue<Character> queue = new Queue<Character>();
		for (int i = 0; i < temp.length(); i++) {
			if (Character.isAlphabetic(temp.charAt(i))) {
				stack.push(temp.charAt(i));
				queue.enqueue(temp.charAt(i));
			}
		}
		if (stack.size() == 0) {
			return false;
		}
		for (int i = 0; i < stack.size(); i++) {
			if (stack.pop() != queue.dequeue()) {
				return false;
			}
		}
		return true;
		/*
		 * TODO 4: Implement "explorePalindrome"
		 */
	}

	public static void explorePalindrome(String text) {

		/*
		 * TODO 5: Implement "explorePalindrome" & helper function
		 */
			String tempOriginal = text;
			String tempReversed = reverseStringAndRemoveNonAlpha(tempOriginal).toLowerCase();
			if(!tempReversed.isEmpty()) {
			Stack<String> stack = new Stack<String>();
			decomposeText(tempOriginal, tempReversed, 0, stack);
		}

		/*
		 * TODO 5
		 */

	}

	public static void decomposeText(String originalText, String textToDecompose, int index,
			Stack<String> decomposition) {

		/*
		 * TODO 5: Implement "explorePalindrome" & helper function
		 */
		String[] wordList = null;
		if (index == textToDecompose.length()) {
			System.out.print(originalText + ": ");
			Stack<String> stack = new Stack<String>();
			int size = decomposition.size();
			for(int i = 0; i < size; i++) {
				stack.push(decomposition.pop());
			}
			for(int i = 0; i < size; i++) {
				decomposition.push(stack.pop());
				System.out.print(decomposition.peek() + " ");
			}
			System.out.println();
		}
		wordList = getWords(textToDecompose, index);

		for (int i = 0; i < wordList.length; i++) {
			decomposition.push(wordList[i]);
			decomposeText(originalText, textToDecompose, index + wordList[i].length(), decomposition);
			decomposition.pop();
		}
		/*
		 * TODO 5
		 */
	}

	// This function looks at the arguments that are passed
	// and decides whether to test palindromes or expand them
	public static void main(String[] args) throws IOException {

		if (args.length == 0) {
			System.out.println("ERROR: Remember to set the mode with an argument: 'test' or 'expand'");
		}
		 else  {
			 String mode = args[0];

			// Default palindromes to use if none are provided
			String[] testPalindromes = {"A", "ABBA", "oh no an oboe", "salami?", "I'm alas, a salami"};
			if (args.length > 1)
				testPalindromes = Arrays.copyOfRange(args, 1, args.length);

			// Test whether the provided strings are palindromes
			if (mode.equals("test")) {

				for (int i = 0; i < testPalindromes.length; i++) {
					String text = testPalindromes[i];
					boolean result = isPalindrome(text);
					System.out.println("'" + text + "': " + result);
				}

			} else if (mode.equals("expand")) {
				for (int i = 0; i < testPalindromes.length; i++) {

					explorePalindrome(testPalindromes[i]);
				}
			} else {
				System.out.println("unknown mode: " + mode);
			}
		}
	}
}
