package assign10;

import java.util.Collection;
import java.util.LinkedList;


/**
 * A representation for the chaining hash table. 
 * 
 * @author Romeny Doria & Salvatore Stone Mele
 *  CADE:  doria           mele 
 *  Date Modified: 11/18/2015
 */

public class ChainingHashTable implements Set<String> {
	
	private LinkedList<String>[] hashTable;
	private HashFunctor hasher;
	private int elements;
	private int capacity;
	private long collision;
	
	
	/** Constructs a hash table of the given capacity that uses the hashing function
	  * specified by the given functor.
	  */
	@SuppressWarnings("unchecked") //We a know its going to be a LinkedList<String> so we are casting it.  
	public ChainingHashTable(int capacity, HashFunctor functor){
		 hashTable = (LinkedList<String>[]) new LinkedList[capacity];
		 elements = 0;
		 hasher = functor;
		 this.capacity = capacity;
	}
	

	@Override
	public boolean add(String item) {
		//Checks to see if item is already in the hashTable
		if(contains(item))
			return false;
		//Generates hash
		int hashCode = hasher.hash(item)%hashTable.length;
		//Adds to linked list at hash.
		if(hashTable[hashCode] == null)
			hashTable[hashCode] = new LinkedList<String>();
		
		//Uncomment this if you wanna count collisions.
//		if(hashTable[hashCode].size() >= 1)
//			collision = collision + hashTable[hashCode].size();
	
		//If its not in the list already we are going to add it to the first of the linked list.
		hashTable[hashCode].addFirst(item);
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

	@SuppressWarnings("unchecked") //Same suppression as in constructor. 
	@Override
	public void clear() {
		elements = 0;
		hashTable = (LinkedList<String>[]) new LinkedList[capacity];
		
	}

	@Override
	public boolean contains(String item) {
		//Generates hashCode
		int hashCode = (hasher.hash(item))%hashTable.length;
		//Checks to see if its in LinkedList at desired hash. If it is return true.
		if(hashTable[hashCode] == null)
			return false;
		//Assumes that a linked list has been instantiated at particular hashCode index.
		if(hashTable[hashCode].contains(item) == true)
			return true;
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for(String i: items)
			if(!contains(i) == true)
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
	
	
	public long getCollisions(){
		return collision;
	}
	
	public void resetCollisions(){
		collision = 0;
	}
	
	

}
