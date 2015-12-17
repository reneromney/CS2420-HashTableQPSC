package assign10;

/**
 * A class for our mediocre hash function.
 * 
 * @author Romeny Doria & Salvatore Stone Mele
 *  CADE:  doria           mele 
 *  Date Modified: 11/18/2015
 */

public class MediocreHash implements HashFunctor{

	/**
	 * This has takes the sum of all the string character values and returns that. 
	 */
	@Override
	public int hash(String item) {
		int hash = 0;
		for(int i = 0; i <  item.length(); i++){
			int value = item.charAt(i); 
			hash = hash + value;
		}
		return hash;
	}

}
