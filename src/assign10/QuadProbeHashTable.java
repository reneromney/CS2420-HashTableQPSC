/**
 * A class that represents a hash table that uses quadratic probing.  
 * 
 * @author Romeny Doria & Salvatore Stone Mele
 *  CADE:  doria           mele 
 *  Date Modified: 11/18/2015
*/

package assign10;

import java.util.Collection;

public class QuadProbeHashTable implements Set<String>{
	
	private String[] hashTable;
	private HashFunctor hasher;
	private int elements;
	private int capacity;
	private int initialCapacity;
	private long collision = 0;
	
	
	/** Constructs a hash table of the given capacity that uses the hashing function
	  * specified by the given functor.
	  */
	public QuadProbeHashTable(int capacity, HashFunctor functor){
		//If the capacity is not prime then increase it to the next prime number.
		if(isPrime(capacity) == false)
			capacity = nextPrime(capacity);
		
		//Makes a backing array for the hash table and all other necessary variables. 
		hashTable = new String[capacity];
		hasher = functor;
		elements = 0;
		this.capacity = capacity;
		this.initialCapacity = capacity;
	}
	
	
	/**
	 * Rehashes the table when lambda gets to .5 or past .5
	 * @param hashTable that user wants to rehash.
	 */
	private void rehasher(String[] hashTable){
		//Gets the new capacity.
		capacity = nextPrime((2*capacity));
		//Makes new temporary hashTable to formulate bigger table.
		QuadProbeHashTable newTable = new QuadProbeHashTable(capacity,hasher);
		//Goes through the old hashTable and adds all the values that are not null to the new table. 
		for(int i = 0; i < hashTable.length; i++)
			if(hashTable[i] != null)
				newTable.add(hashTable[i]);
		//Sets the old hashTable array equal to the new one. 
		this.hashTable = newTable.hashTable;
	}

	/**
	 * Gets current lambda of hashTable. 
	 * @return the lambda of hashTable.
	 */
	private double getLambda(){
		return ((double)elements/(double)capacity);
	}
	
	
	@Override
	public boolean add(String item) {
		//If lambda is to big resize.
		if(getLambda() >= .5)
			rehasher(hashTable);
		
		//If the table already has the item return false.
		if(contains(item))
			return false;
		
		//Gets items hashCode
		int hashCode = (hasher.hash(item)) % hashTable.length;
		
		//Finds Location where the hashTable is not null.
		int location = hashCode;
		int i = 1;
		while(hashTable[location] != null){
			location = (location +  ((2*i) - 1)) % capacity;
			//Uncomment if you want to count collisions.
			//collision++;
			i++;
		}
		
		//Add item to hashcode location. 
		hashTable[location] = item;
		

		elements++;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean isAdded = false;
		for(String i: items)
			if(add(i))
				isAdded = true;
		return isAdded;
	}

	@Override
	public void clear() {
		elements = 0;
		hashTable = new String[initialCapacity];
		
		
	}

	@Override
	public boolean contains(String item) {
		// Generates hashCode
		int hashCode = (hasher.hash(item)) % hashTable.length;
		//Sets location to hashCode.
		int location = hashCode;
		int i = 1;
		//Checks to see if given location is null.
		while(hashTable[location] != null){
			if(hashTable[location].equals(item))
				return true;
			//If location is not null and not the item quad prob to next location.
			location = ((location) +  ((2*i) - 1)) % capacity;
			i++;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for(String i: items)
			if(!contains(i))
				return false;
		return true;
	}

	@Override
	public boolean isEmpty() {
		if(elements == 0)
			return true;
		return false;
	}

	
	@Override
	public int size() {
		return elements;
	}

	/**
	 * Next Prime:
	 * 
	 * This function takes an integer and returns the smallest prime number that
	 * is larger than it.
	 * 
	 * @param A
	 *            positive integer
	 * @return The smallest prime number that is bigger than the integer given.
	 *         (Note: If the input integer is not positive will return -1.)
	 */
	private int nextPrime(int n) {

		if (n <= 0)
			return -1;

		// Checks each number greater than the integer given if its prime.
		int next_int = n + 1;
		while (!(isPrime(next_int) == true)) {
			next_int++;
		}

		return next_int;

	}
	
	
	/**
	 * Is Prime:
	 * 
	 * This function determines whether a integer is prime or not.
	 * 
	 * @param An
	 *            integer.
	 * @return Returns true or false depending on whether the integer given is
	 *         prime or not.
	 */
	private boolean isPrime(int a) {

		if (a <= 1)
			return false;

		// Iterates from 2 to number in question and checks to see if int a is
		// divisible by the iterations.
		int step = 2;
		for (int i = 2; i <= a; i++) {
			if (a == step)
				return true;
			else if (a % step == 0)
				return false;
			else
				step++;
		}

		return true;

	}
	
	public long getCollisions(){
		return collision;
	}
	
	public void resetCollisions(){
		collision = 0;
	}
	
	

}
