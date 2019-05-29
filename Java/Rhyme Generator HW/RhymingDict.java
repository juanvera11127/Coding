import java.io.*;
import java.util.*;
import java.util.Arrays;

public class RhymingDict {
	private static Random random;



	// TO DO 2: Store a rhymeGroup (key) and word (value) in the Dictionary (hashtable)
	public static void storeRhyme(DictionaryInterface<String,LinkedList<String> > rhymingDictionary, String line) {
		String word = getWord(line);
		String rhyme = getRhymeGroup(line);
		if(!rhymingDictionary.contains(rhyme)) {
			LinkedList<String> newRhyme = new LinkedList<>();
			newRhyme.add(0, word);
			rhymingDictionary.put(rhyme, newRhyme);
		}
		else {
			rhymingDictionary.get(rhyme).add(0, word);
		}
	}
	

	// TO DO #3: Remove any of the unrhymables
	public static void removeUnrhymables(DictionaryInterface<String,LinkedList<String> > rhymingDict) {
		LinkedList<String> keys = rhymingDict.getKeys();

		for(int i = 0; i < keys.size(); i++) {
			if(rhymingDict.get(keys.get(i)).size() == 1) {
				rhymingDict.remove(keys.get(i));
			}
			
		}


	}

	// Given a pronunciation, get the rhyme group
	// get the more *heavily emphasized vowel* and follwing syllables
	// For "tomato", this is "-ato", and not "-omato", or "-o"
	// Tomato shares a rhyming group with "potato", but not "grow"
	private static String getRhymeGroup(String line) {

		int firstSpace = line.indexOf(" ");

		String pronunciation = line.substring(firstSpace + 1, line.length());

		int stress0 = pronunciation.indexOf("0");
		int stress1 = pronunciation.indexOf("1");
		int stress2 = pronunciation.indexOf("2");

		if (stress2 >= 0)
			return pronunciation.substring(stress2 - 2, pronunciation.length());
		if (stress1 >= 0)
			return pronunciation.substring(stress1 - 2, pronunciation.length());
		if (stress0 >= 0)
			return pronunciation.substring(stress0 - 2, pronunciation.length());

		// No vowels at all? ("hmmm", "mmm", "shh")
		return pronunciation;
	}

	private static String getWord(String line) {
		int firstSpace = line.indexOf(" ");

		String word = line.substring(0, firstSpace);

		return word;
	}

	// Load the dictionary
	public static void loadDictionary(DictionaryInterface<String,LinkedList<String> > rhymingDictionary) {

		// Load the file and read it
		try {
			String path = "cmudict/cmudict-abridged.dict";
			FileInputStream stream = new FileInputStream(new File(path));
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

			for(String line = reader.readLine(); line != null; line = reader.readLine()) {
				storeRhyme(rhymingDictionary, line);
			}
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
	}


	// Get two random indexes that are not the same
	public static int[] getTwoDifferentRandomIndexes(int length) {
		// Get the first random index.  Pick any index in the length
		int index0 = random.nextInt(length);

		// Tricky: this index is offset from the other by a
		// random number less than the total length but 1 or more,
		// so we know that the two indexes can't be the same
		int offset = random.nextInt(length - 1) + 1;
		int index1 = (index0 + offset)%length;

		int[] indexes =  {index0, index1};
		return indexes;
	}

	// Select two rhymeGroups, select two random words from each rhyme group, and make a rhyme.
	public static void createRhyme(DictionaryInterface<String,LinkedList<String> > rhymingDict) {

		// Get all the keys as an array
		LinkedList<String> keys = rhymingDict.getKeys();
		//System.out.printf("%d choices for rhyming groups\n", keys.length);

		// Pick out the two rhyme groups
		int[] groupIndexes = getTwoDifferentRandomIndexes(keys.size());
		String group0Key = keys.get(groupIndexes[0]);
		String group1Key = keys.get(groupIndexes[1]);

		// Uncomment to see which word groups were selected
		// System.out.printf("Create rhyme with groups '%s' and '%s' \n", group0Key, group1Key);

		LinkedList<String> group0 = rhymingDict.get(group0Key);
		LinkedList<String> group1 = rhymingDict.get(group1Key);

		// Uncomment to check the length of the word sets we selected
		//System.out.printf("\t%d words, %d words \n", group0.getLength(), group1.getLength());

		// Pick out word indexes from the two rhyme groups
		// We need four words, so two words from each group
		int[] group0Indexes = getTwoDifferentRandomIndexes(group0.size());
		int[] group1Indexes = getTwoDifferentRandomIndexes(group1.size());

		String word0A = group0.get(group0Indexes[0]);
		String word0B = group0.get(group0Indexes[1]);
		String word1A = group1.get(group1Indexes[0]);
		String word1B = group1.get(group1Indexes[1]);

		// Uncomment to check which words it picked
		//System.out.printf("\tWords: %s %s %s %s \n", word0A, word0B, word1A, word1B);

		// Choose between two poems
		if (random.nextFloat() > 0.5f)
			System.out.printf("\nRoses are %s,\nviolets are %s.\nI am %s\nand you are %s.\n", word0A, word1A, word0B, word1B);
		else
			System.out.printf("\nIf I were %s\nthen you'd be the %s,\nAnd we'd both be %s\nand never be %s\n", word0A, word1A, word0B, word1B);

	}

	// Reads in the pronunciation dictionary and makes a dictionary
	public static void main(String[] args) {

		// Save a seed.
		// Like in Lab01, the seeds allow us generate the same random numbers (the same poems) again
		long seed = System.currentTimeMillis() % 1000;
		if (args.length > 0)
			seed = Long.parseLong(args[0]);
		random = new Random(seed);

		// Set the number of poems to write
		int poemCount = 3;
		if (args.length > 1)
			poemCount = Integer.parseInt(args[1]);


		Hashtable<String,LinkedList<String> > rhymingDict = new Hashtable<>(20000);

		// Read in all the dictionary lines
		loadDictionary(rhymingDict);

		// Comment in if you want to experiment with the bucket size.
		/*
		LinkedList<String> keys = rhymingDict.getKeys();
		System.out.println(keys.size());
		System.out.println("Biggest bucket = " + rhymingDict.biggestBucket() + " average bucket = " + rhymingDict.averageBucket());
		*/

		removeUnrhymables(rhymingDict);
		for (int i = 0; i < poemCount; i++) {
			createRhyme(rhymingDict);
		}
	}

}
