package assign10;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Class for timing the hash functors.
 * 
 * @author Romeny Doria & Salvatore Stone Mele
 *  CADE:  doria           mele 
 *  Date Modified: 11/18/2015
 */

public class HashFunctorsTimingTest {
	
	public static int seed = 70;
	// Main for testing runtimes
	public static void main(String[] args) {
		

		// try computing T(N)/F(N), see if it converges
		DecimalFormat formatter = new DecimalFormat("0000E0");

		System.out
				.println("\nN\tT(N)\t|\tT(N)/logN\tT(N)/NlogN\t\tT(N)/N\t\tT(N)/N^2\tT(N)/N^3\tAvg Col");
		System.out
				.println("-----------------------------------------------------------------------------------");

		// Loops 100 times and use the average running time.
		int timesToLoop = 100;
		long collisions = 0;
		// For each problem size n . . .
		for (int n = 1000; n <= 20000; n += 1000) {
			System.out.print(n + "\t");
			QuadProbeHashTable d[] = new QuadProbeHashTable[timesToLoop];
			for(int i = 0; i < timesToLoop; i++){
				QuadProbeHashTable qH = new QuadProbeHashTable(n,new GoodHash());
				generateStrings(n,qH,getSeed());
				d[i] = qH;
				collisions = collisions + qH.getCollisions();
				qH.resetCollisions();
			}

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				d[i].add(randoString());	
			}

			midpointTime = System.nanoTime();
			// Run a loop to capture the cost of running the "timesToLoop" loop
			// and
			// finding the min
			for (int i = 0; i < timesToLoop; i++) {

			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ timesToLoop;

			System.out.println(formatter.format(averageTime)
					+ "\t|\t"
					+ formatter.format(averageTime/ (Math.log10(n) / Math.log10(2)))
					+ "\t\t"
					+ formatter.format(averageTime/ (n * (Math.log10(n) / Math.log10(2)))) + "\t\t"
					+ formatter.format(averageTime / n) + "\t\t"
					+ formatter.format(averageTime / (n * n)) + "\t\t"
					+ formatter.format(averageTime / (n * n * n)) + "\t\t"
					+ formatter.format(collisions/timesToLoop));
		}

	}
	
	/**
	 * This method will generate a hashtable of size n with random strings. Allows for a string. 
	 * @param n size of hash table
	 * @param ht hash table
	 * @param seed the seed you want to use. 
	 */
	private static void generateStrings(int n, QuadProbeHashTable ht, int seed) {
		//final int SEED = 300;
		final char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z' };

		Random r = new Random(seed);
		//QuadProbeHashTable result = ht;
		String[] s = new String[n];

		for (int i = 0; i < n; i++) {
			s[i] = "";
			//Pick random length between 1 and 12. 
			int length = r.nextInt(12) + 1;
			for (int j = 0; j < length; j++)
				s[i] += letters[r.nextInt(letters.length)];
			if (!ht.add(s[i]))
				i--;
		}
	}
	
	/**
	 * Makes a random String ranging from size 1-12
	 */
	private static String randoString() {
		final char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z' };

		Random r = new Random();
		// QuadProbeHashTable result = ht;
		String s = "";

		// Pick random length between 1 and 12.
		int length = r.nextInt(12) + 1;
		for (int j = 0; j < length; j++)
			s += letters[r.nextInt(letters.length)];
		
		return s;
	}
	
	
	public static int getSeed(){
		seed++;
		return seed;
	}
}