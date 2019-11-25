import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.io.*;
import java.math.MathContext;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class LongestCommonSubstring {
    public int LcsLength;
    public int LcsStartIndexInS1;
    public int LcsStartIndexInS2;

    static ThreadMXBean bean = ManagementFactory.getThreadMXBean();
    // defining variables
    static int numberOfTrials = 50; //  everybody gets 50 trials
    static int MAXINPUTSIZE = 9000; //
    static int MININPUTSIZE = 1; //
    static int inputSize = 0;

    static String ResultsFolderPath = "/home/ryan/Results/"; // pathname to results folder
    static FileWriter resultsFile;
    static PrintWriter resultsWriter;
    static String BookPath1 = "/home/ryan/Results/Frankenstein/";
    static String BookPath2 = "/home/ryan/Results/Mathilda/";

    static String Book1 = readABook(BookPath1);
    static String Book2 = readABook(BookPath2);
    
    public LongestCommonSubstring()
    {
        LcsLength = 0;
        LcsStartIndexInS1 = 0;
        LcsStartIndexInS2 = 0;
    }

    public static void main(String[] args){
        // tests
        String tester1, tester2, tester3, testBook1, testBook2;
        int count = 0;
        LongestCommonSubstring testLCS = new LongestCommonSubstring();

        tester1 = generateRandomString(125);
        System.out.println(tester1);
        tester2 = generateRandomString(125);
        System.out.println(tester2);

        testLCS = LCSBrute(tester1, tester2);
        System.out.println(testLCS.LcsLength + " " + testLCS.LcsStartIndexInS1 + " " + testLCS.LcsStartIndexInS2);
        printSubString1(tester1, testLCS);
        printSubString2(tester2, testLCS);

        tester3 = generateSameCharString(125);
        System.out.println(tester3);

        testBook1 = generateRandomSubString(Book1, 125);
        System.out.println(testBook1);
        testBook2 = generateRandomSubString(Book2, 125);
        System.out.println(testBook2);


        int testCount1 = LCSFast(tester1, tester2);
        System.out.println("Brute " + testLCS.LcsLength + " Fast" + testCount1);

        LongestCommonSubstring testLCS2 = LCSBrute(testBook1, testBook2);
        printSubString1(testBook1, testLCS2);
        printSubString2(testBook2, testLCS2);
        int testCount2 = LCSFast(testBook1, testBook2);
        System.out.println("Brute = " + testLCS2.LcsLength + "\n Fast = " + testCount2);

        // testing for the longest common substrings of two books
       /* LongestCommonSubstring books = new LongestCommonSubstring();
        books = LCSBrute(Book1, Book2);
        printSubString1(Book1, books);
        printSubString2(Book2, books); */

        // some other tests
        String testString10 = new String("helloadfakjdfa");
        String testString100 = new String("adsfcvuqkhello");
        LongestCommonSubstring testObj3 = LCSBrute(testString10, testString100);
        int testCount3 = LCSFast(testString10, testString100);
        System.out.println(testString10 + "\n" + testString100);
        System.out.println("The longest common substring is length " + testObj3.LcsLength + " for Brute and " + testCount3 + " for fast");

        String testString11 = new String("abcdef");
        String testString101 = new String("qwrtyu");
        LongestCommonSubstring testObj4 = LCSBrute(testString11, testString101);
        int testCount4 = LCSFast(testString11, testString101);
        System.out.println(testString11 + "\n" + testString101);
        System.out.println("The longest common substring is " + testObj4.LcsLength + " for Brute and " + testCount4 + " for fast");

        String testString12 = new String("abcdefhellowhatsupuaydfakjdfkjasfabcdef");
        String testString102 = new String("qwrtyuabcdef;aksdfjuhauhkgjdfkguhdkjsjd");
        LongestCommonSubstring testObj5 = LCSBrute(testString12, testString102);
        int testCount5 = LCSFast(testString12, testString102);
        System.out.println(testString12 + "\n" + testString102);
        System.out.println("The longest common substring is " + testObj5.LcsLength + " for Brute and " + testCount5 + " for fast");

        String testString13 = new String("abcdefhjjjjowhatsupujjjjakjdfkjasfjjjj");
        String testString103 = new String("qwrtyuabjjjj;aksdfjuhauhkgjdfkguhdjjjj");
        LongestCommonSubstring testObj6 = LCSBrute(testString13, testString103);
        int testCount6 = LCSFast(testString12, testString103);
        System.out.println(testString13 + "\n" + testString103);
        System.out.println("The longest common substring is " + testObj6.LcsLength + " for Brute and " + testCount6 + " for fast");


        // print set up for each function test
        /* **********************************************UNCOMMENT ONE******************************************/
 /*       System.out.println("Running first full experiment ...");
        runFullExperiment("LCSBrute-Exp1-ThrowAway.txt");
        System.out.println("Running second full experiment ...");
        runFullExperiment("LCSBrute-Exp2.txt");
        System.out.println("Running third full experiment ...");
        runFullExperiment("LCSBrute-Exp3.txt");


        System.out.println("Running first full experiment ...");
        runFullExperiment("LCSBrute-Exp1-ThrowAway.txt");
        System.out.println("Running second full experiment ...");
        runFullExperiment("LCSBrute-Exp2.txt");
        System.out.println("Running third full experiment ...");
        runFullExperiment("LCSBrute-Exp3.txt");
/*

        System.out.println("Running first full experiment ...");
        runFullExperiment("LCSBruteBook-Exp1-ThrowAway.txt");
        System.out.println("Running second full experiment ...");
        runFullExperiment("LCSBruteBook-Exp2.txt");
        System.out.println("Running third full experiment ...");
        runFullExperiment("LCSBruteBook-Exp3.txt");

        System.out.println("Running first full experiment ...");
        runFullExperiment("LCSFastX-Exp1-ThrowAway.txt");
        System.out.println("Running second full experiment ...");
        runFullExperiment("LCSFastX-Exp2.txt");
        System.out.println("Running third full experiment ...");
        runFullExperiment("LCSFastX-Exp3.txt");

        System.out.println("Running first full experiment ...");
        runFullExperiment("LCSFast-Exp1-ThrowAway.txt");
        System.out.println("Running second full experiment ...");
        runFullExperiment("LCSFast-Exp2.txt");
        System.out.println("Running third full experiment ...");
        runFullExperiment("LCSFast-Exp3.txt");
*/
 /*
        System.out.println("Running first full experiment ...");
        runFullExperiment("LCSFastBook-Exp1-ThrowAway.txt");
        System.out.println("Running second full experiment ...");
        runFullExperiment("LCSFastBook-Exp2.txt");
        System.out.println("Running third full experiment ...");
        runFullExperiment("LCSFastBook-Exp3.txt");
*/

        /* **********************************************UNCOMMENT ONE******************************************/
    }


    // function to run each of the experiments on the functions
    static void runFullExperiment(String resultsFileName) {

        // making sure that we have results files available or can create new
        try {
            resultsFile = new FileWriter(ResultsFolderPath + resultsFileName);
            resultsWriter = new PrintWriter(resultsFile);
        } catch (Exception e) {
            System.out.println("*****!!!!!  Had a problem opening the results file " + ResultsFolderPath + resultsFileName);
            return;
        }

        ThreadCPUStopWatch BatchStopwatch = new ThreadCPUStopWatch(); // for timing an entire set of trials
        //ThreadCPUStopWatch TrialStopwatch = new ThreadCPUStopWatch(); // for timing an individual trial

        resultsWriter.println("#InputValue(x)    AverageTime "); // # marks a comment in gnuplot data
        resultsWriter.flush();

        // for each size of input we want to test: in this case incrementing by 1

        for (int inputSize = MININPUTSIZE; inputSize <= MAXINPUTSIZE; inputSize *= 2) {
            int lcsCount = 0;
            String testS1, testS2;

            /* repeat for desired number of trials (for a specific size of input)... */
            System.out.println("Running test for input size " + inputSize + " ... ");
            // will hold total amount of time
            // will reset after each batch of trials
            long batchElapsedTime = 0;

            // generate strings
            /* **********************************************UNCOMMENT ONE******************************************/
            //testS1 = generateSameCharString(inputSize); testS2 = generateSameCharString(inputSize);
            //testS1 = generateRandomString(inputSize); testS2 = generateRandomString(inputSize);
            testS1 = generateRandomSubString(Book1, inputSize); testS2 = generateRandomSubString(Book2, inputSize);

            /* **********************************************UNCOMMENT ONE******************************************/

            /* force garbage collection before each batch of trials run so it is not included in the time */
            System.gc();
            System.out.print("    Running trial batch...");

            BatchStopwatch.start(); // comment this line if timing trials individually

            // run the trials
            for (int trial = 0; trial < numberOfTrials; trial++) {

                //actual beginning of trial
                /* **********************************************UNCOMMENT ONE******************************************/
                //LCSBrute(testS1, testS2);
                //LCSFast(testS1, testS2);

                /* **********************************************UNCOMMENT ONE^^^******************************************/
                //System.out.println("....done.");// *** uncomment this line if timing trials individually
            }

            batchElapsedTime = BatchStopwatch.elapsedTime(); // *** comment this line if timing trials individually

            double averageTimePerTrialInBatch = (double) batchElapsedTime / (double) numberOfTrials; // calculate the average time per trial in this batch
            /* print data for this size of input */

            resultsWriter.printf("%12d  %15.2f\n", inputSize, (double) averageTimePerTrialInBatch); // might as well make the columns look nice
            resultsWriter.flush();
            System.out.println(" ....done.");
        }
    }


    // brute force method from the pseudocode
    public static LongestCommonSubstring LCSBrute(String testString1, String testString2)
    {
        // declaring variables
        LongestCommonSubstring newLCS = new LongestCommonSubstring();
        int lengthString1 = testString1.length();
        int lengthString2 = testString2.length();
        int lcsLength = 0;
        int i, j, k;

        // letters of the first string
        for (i = 0; i < lengthString1; i++)
        {
            // letters of the second string
            for (j = 0; j < lengthString2; j++)
            {
                // letters of the substring
                for (k = 0; k < Math.min(lengthString1 - i - 1, lengthString2 - j - 1); k++)
                {
                    // if the substring ends get out of here
                    if (testString1.charAt(i + k) != testString2.charAt(j + k))
                        break;
                }
                // found new LCS
                if ( k > lcsLength)
                {
                    lcsLength = k;
                    newLCS.LcsLength = lcsLength;
                    newLCS.LcsStartIndexInS1 = i;
                    newLCS.LcsStartIndexInS2 = j;
                }
            }
        }

        return newLCS;
    }

    // contains a clause that exits if the substring is the length of a string
    // i think that may make it n^2 haha
    public static LongestCommonSubstring LCSBruteSlightlyFaster(String testString1, String testString2)
    {
        int lengthS1 = testString1.length();
        int lengthS2 = testString2.length();
        int lcsLength = 0;
        int i, j = 0, k;

        for (i = 0; i < lengthS1; i++)
        {
            for (j = 0; j < lengthS2; j++)
            {
                for (k = 0; k < Math.min(lengthS1 - i - 1, lengthS2 - j - 1); k++)
                {
                    if (testString1.charAt(i + k) != testString2.charAt(j + k))
                        break;

                }
                // if the LCS has the same length as one of the strings there is no need for further comparison
                if ( k == lengthS1 || k == lengthS2)
                {
                    LongestCommonSubstring newLCS = new LongestCommonSubstring();
                    newLCS.LcsLength = lcsLength;
                    newLCS.LcsStartIndexInS1 = i;
                    newLCS.LcsStartIndexInS2 = j;

                    return newLCS;
                }
                if ( k > lcsLength)
                {
                    lcsLength = k;
                }
            }
        }
        LongestCommonSubstring newLCS = new LongestCommonSubstring();
        newLCS.LcsLength = lcsLength;
        newLCS.LcsStartIndexInS1 = i;
        newLCS.LcsStartIndexInS2 = j;

        return newLCS;
    }
    
    // https://www.programcreek.com/2015/04/longest-common-substring-java/
    public static int LCSFast(String testS1, String testS2)
    {
        // declaring variables
        int lengthS1 = testS1.length();
        int lengthS2 = testS2.length();
        int max = 0;

        LongestCommonSubstring result = new LongestCommonSubstring();
        // 2d array will keep track of matches but will not iterate through everyone
        int[][] dp = new int[lengthS1][lengthS2];

        // letters of the first string
        for ( int i = 0; i < lengthS1; i++)
        {
            // letters of the second string
            for ( int j = 0; j < lengthS2; j++)
            {
                // if the characters from each string are the same
                if(testS1.charAt(i) == testS2.charAt(j))
                {
                 // a base so we are not out of bounds
                    if ( i == 0 || j == 0)
                    {
                        dp[i][j] = 1;
                    }
                    // continuing counting
                    else
                    {
                        dp[i][j] = dp[i-1][j-1]+1;
                    }
                    // found a larger match
                    if(max < dp[i][j])
                        max = dp[i][j];
                }
            }
        }
        return max;
    }
    

    // source: https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
    public static String generateRandomString(int size)
    {
        // length bounded by 256
        byte[] array = new byte[size*size];
        new Random().nextBytes(array);

        String randomString = new String(array, Charset.forName("UTF-8"));

        // create a string buffer to store the result
        StringBuffer newS = new StringBuffer();

        // remove the special chars
        String AlphaString = randomString.replaceAll("[^A-Za-z0-9]", "");

        // append chars to the stringbuffer
        for ( int k = 0; k < AlphaString.length(); k++) {
            if (Character.isLetter(AlphaString.charAt(k)) && (size > 0))
            {
                newS.append(AlphaString.charAt(k));
            size--;
            }
        }
        // return the stringbuffer to string
        return newS.toString();
    }
    
    // creates string of x's for worst case scenario
    public static String generateSameCharString(int size)
    {
        StringBuilder newS = new StringBuilder();
        for (int i = 0; i < size; i++)
        {
            newS.append('x');
        }
        
        return newS.toString();
        
    }

    // reads a text file into a string
    public static String readABook(String path)
    {
        String content = "";
        try
        {
            content = new String (Files.readAllBytes(Paths.get(path)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }

    // picks a random index and creates a string of size from that index
    public static String generateRandomSubString(String book, int size)
    {
        Random r = new Random();
        int low = 0;
        int lengthB = book.length() - size;
        int result = r.nextInt(lengthB-low) + low;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++)
        {
            sb.append(book.charAt(result));
            result++;
        }

        return sb.toString();
    }

    // prints a substring from a given string
    public static void printSubString1(String string, LongestCommonSubstring toPrint)
    {
        System.out.println(string.substring(toPrint.LcsStartIndexInS1, toPrint.LcsStartIndexInS1 + toPrint.LcsLength));
    }
    // prints a substring from string2 :D
    public static void printSubString2(String string, LongestCommonSubstring toPrint)
    {
        System.out.println(string.substring(toPrint.LcsStartIndexInS2, toPrint.LcsStartIndexInS2 + toPrint.LcsLength));
    }
}
