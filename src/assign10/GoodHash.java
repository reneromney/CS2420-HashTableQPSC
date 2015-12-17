package assign10;

/**
 * A class for our good hash function.
 * WE USED JAVA's HASH CODE THIS IS NOT OUR METHOD. 
 * 
 * @author Romeny Doria & Salvatore Stone Mele
 *  CADE:  doria           mele 
 *  Date Modified: 11/18/2015
 */


public class GoodHash implements HashFunctor {

	@Override
	public int hash(String item) {
		return Math.abs(item.hashCode());
	}

}
