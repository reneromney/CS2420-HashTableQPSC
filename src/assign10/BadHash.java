package assign10;

/**
 * Bad hash function.
 * 
 * @author Romeny Doria & Salvatore Stone Mele
 *  CADE:  doria           mele 
 *  Date Modified: 11/18/2015
 */

public class BadHash implements HashFunctor {

	/**
	 * This hash just takes the length of the string and returns it. 
	 */
	@Override
	public int hash(String item) {
		return item.length();
	}
	
	

}
