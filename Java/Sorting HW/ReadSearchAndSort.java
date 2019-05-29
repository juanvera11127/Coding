import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadSearchAndSort {


    /*
    * ========================================================================
    * START TODO #2: "readWords"
    */
    public static String[]  readWords(String fileName)  throws FileNotFoundException{

        return null;
    }
    /*
    * END TODO #2: "readWords"
    * ========================================================================
    */

    /*
    * ========================================================================
    * START TODO 3: "countWordsInUnsorted"
    */
    public static int  countWordsInUnsorted(String[] wordsToCount,String countedWord){
        return 0;
    }
    /*
    * END TODO #3: "countWordsInUnsorted"
    * ========================================================================
    */



    /*
    * ========================================================================
    * START TODO #4: "mergeSort"
    */
    public static void mergeSort(String[] arrayToSort, String[] tempArray, int first, int last){

    }
    /*
    * END TODO #4: "mergeSort"
    * ========================================================================
    */



    /*
    * ========================================================================
    * START TODO #5: binary search
    */
    public static int binarySearch(String[] sortedWords, String query, int startIndex, int endIndex){
        return -1;
    }

    public static int getSmallestIndex(String[] words, String query, int startIndex, int endIndex){
        return -1;
    }

    public static int getLargestIndex(String[] words, String query, int startIndex, int endIndex){
        return -1;
    }

    public static int  countWordsInSorted(String[] wordsToCount,String countedWord){
        return 0;
    }
    /*
    * END TODO #5: binary search
    * ========================================================================
    */



    public static void main(String []args)  throws FileNotFoundException{


        /*
        * TODO 1
        * ========================================================================
        */

        String filename;
        String[] queryWords;
        
        filename = args[0];
        queryWords = new String[args.length-1];
        

        /*
        * TODO 1
        * ========================================================================
        */



        /*
        * TODO 2
        * ========================================================================
        */

        String[] allWords = readWords(filename);

        /*
        * TODO 2
        * ========================================================================
        */

        int timingCount = 100;
        System.out.println("\nArguments: use '" +  String.join(",",queryWords) + "' words, time " + timingCount + " iterations, search for words: " + String.join(",", queryWords) + "\n");




        System.out.println("NAIVE SEARCH:");


        // Record the current time
        long t0 = (new Date()).getTime();

        // Time how long it takes to run timingCount loops
        //   for countWordsInUnsorted
        for (int j = 0; j < timingCount; j++) {
            for (int i = 0; i < queryWords.length; i++) {

                /*
                * ========================================================================
                *   START: TODO #3
                */

                int count = countWordsInUnsorted(allWords,queryWords[i]);
                /*
                *   END: TODO #3
                * ========================================================================
                */

                // For the first one, print out the value
                if (j == 0)
                    System.out.println(queryWords[i] + ":" + count);

            }
        }

        // Record the current time
        long t1 = (new Date()).getTime();

        long timeToSeachNaive = t1 - t0;
        int searchCount = timingCount*queryWords.length;

        // Output how long the searches took, for how many searches
        // (remember: searches = timingcount * the number of words searched)
        System.out.printf("%d ms for %d searches, %f ms per search\n", timeToSeachNaive, searchCount, timeToSeachNaive*1.0f/searchCount);

        // Sort the list of words
        System.out.println("\nSORTING: ");

        /*
        * ========================================================================
        *   START: TODO #4
        */

        //call mergesort here

        /*
        *   END: TODO #4
        * ========================================================================
        */

        // Record the current time
        long t2 = (new Date()).getTime();

        // Output how long the sorting took
        long timeToSort = t2 - t1;
        System.out.printf("%d ms to sort %d words\n", timeToSort, allWords.length);

        // Output every 1000th word of your sorted wordlist
        int step = (int)(allWords.length*.00663 + 1);
        System.out.print("\nSORTED (every " + step + " word): ");
        for (int i = 0; i < allWords.length; i++) {
            if (i%step == 0)
                System.out.print(allWords[i] + " ");
        }
        System.out.println("\n");


        System.out.println("BINARY SEARCH:");

        // Run timingCount loops for countWordsInSorted
        // for the first loop, output the count for each word

        for (int j = 0; j < timingCount; j++) {
            for (int i = 0; i < queryWords.length; i++) {

                /*
                * ========================================================================
                *   START: TODO #5
                */
                int count = countWordsInSorted(allWords,queryWords[i]);

                /*
                *   END: TODO #5
                * ========================================================================
                */

                // For the first one, print out the value
                if (j == 0)
                    System.out.println(queryWords[i] + ":" + count);
                }
        }

        // Output how long the searches took, for how many searches
        // (remember: searches = timingcount * the number of words searched)

        // Record the current time
        long t3 = (new Date()).getTime();

        long timeToSeachBinary = t3 - t2;
        System.out.printf("%d ms for %d searches, %f ms per search\n", timeToSeachBinary, searchCount, timeToSeachBinary*1.0f/searchCount);
    }


}
