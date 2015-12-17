package assign10;

import static org.junit.Assert.*;
import java.util.LinkedList;
import org.junit.Test;

/**
 * A class for testing the chaining hash table. 
 * 
 * @author Romeny Doria & Salvatore Stone Mele
 *  CADE:  doria           mele 
 *  Date Modified: 11/18/2015
 */

public class ChainingHashTableTest {
	
	ChainingHashTable tester = new ChainingHashTable(100 , new BadHash());
	ChainingHashTable tester1;
	LinkedList<String> list = new LinkedList<String>();
	LinkedList<String> empty = new LinkedList<String>();
	
	
	/**
	 * Testing size().
	 */
	
	/**
	 * This test ensures that when size is called on an occupied hashTable the correct value is returned.
	 */
	@Test
	public void testSizeDefault(){
		tester.add("a");
		tester.add("b");
		tester.add("c");
		assertEquals(3,tester.size());
	}
	
	/**
	 * This test ensures that when size is called on an empty hashTable 0 is returned.
	 */
	@Test 
	public void testSizeZero(){
		tester.size();
		assertEquals(0,tester.size());
	}
	
	/**
	 * This test ensures that when the table only has one value in it one is returned.
	 */
	@Test
	public void testSizeOne(){
		tester.add("a");
		assertEquals(1,tester.size());
	}
	
	/**
	 * Testing is empty
	 */
	
	/**
	 * This test ensures that when isEmpty is called on an empty list true is returned.
	 */
	@Test
	public void testIsEmpty(){
		assertTrue(tester.isEmpty());
	}
	
	/**
	 * This test ensures that when isEmpty is called on a non empty list false is returned.
	 */
	@Test
	public void testIsEmptyFalse(){
		tester.add("a");
		assertFalse(tester.isEmpty());
	}
	
	/**
	 * Testing clear
	 */
	
	/**
	 * This test shows that clear emptys a hashTable.
	 */
	@Test
	public void testClear(){
		tester.add("a");
		tester.add("b");
		tester.add("c");
		tester.clear();
		assertTrue(tester.isEmpty());
	}
	
	/**
	 * This test ensures that clear works with an empty list.
	 */
	@Test
	public void testClearEmpty(){
		tester.clear();
		assertTrue(tester.isEmpty());
	}
	
	/**
	 * This test ensures that when clear is called on a table that the table is still usable. 
	 */
	@Test
	public void testClearWorking(){
		tester.add("a");
		tester.clear();
		tester.add("a");
		tester.add("b");
		assertEquals(2,tester.size());
	}
	
	/**
	 * Testing contains.
	 */
	
	/**
	 * This test ensures that contains returns true when called on a hashtable that contains a value.
	 */
	@Test
	public void testContains(){
		tester.add("a");
		tester.add("b");
		tester.add("c");
		assertTrue(tester.contains("a"));
		assertTrue(tester.contains("b"));
		assertTrue(tester.contains("c"));
	}
	
	/**
	 * This test ensures that false is returned when contains is called on a hastable that doesnt contain the value.
	 */
	@Test
	public void testContainsFalse(){
		tester.add("a");
		assertFalse(tester.contains("B"));
	}
	/**
	 * This test ensures false is returned when contains is called on an empty hashtable. 
	 * 
	 */
	@Test
	public void testContainsEmpty(){
		assertFalse(tester.contains("b"));
	}
	
	/**
	 * Testing adds.
	 */
	
	/**
	 * This test ensures true is returned when a string is added to the hashTable. Also ensures size changes.
	 */
	@Test
	public void testAdds(){
		assertTrue(tester.add("a"));
		assertEquals(1,tester.size());
		assertTrue(tester.add("ba"));
		assertTrue(tester.add("cd"));
		assertTrue(tester.add("de"));
		assertTrue(tester.add("ee"));
		assertEquals(5,tester.size());
	}
	
	/**
	 * This test ensures false is returned when a string is added to as hashTable that already contains
	 * that string. Also ensures size does not change.
	 */
	@Test
	public void testAddsAlready(){
		assertTrue(tester.add("a"));
		assertFalse(tester.add("a"));
		assertEquals(1,tester.size());
	}
	
	/**
	 * Testing addAll
	 */
	
	/**
	 * This test ensures that addAll returns true when it adds all of the items from the list to the table. 
	 */
	@Test
	public void testAddAll(){
		tester.add("z");
		list.add("a");
		list.add("bb");
		list.add("ccc");
		assertTrue(tester.addAll(list));
		assertTrue(tester.contains("a"));
		assertTrue(tester.contains("bb"));
		assertTrue(tester.contains("ccc"));
	}
	
	/**
	 * This test ensures that addAll returns true when it gets to add only some of the items from the list to
	 * the table.
	 */
	@Test
	public void testAddSome(){
		tester.add("bb");
		list.add("a");
		list.add("bb");
		list.add("ccc");
		assertTrue(tester.addAll(list));
		assertTrue(tester.contains("a"));
		assertTrue(tester.contains("bb"));
		assertTrue(tester.contains("ccc"));
		assertEquals(3,tester.size());
	}
	
	/**
	 * This test ensures that addAll returns false when it add none of the values from the list to the table. 
	 */
	@Test
	public void testAddNone(){
		tester.add("a");
		tester.add("bb");
		tester.add("ccc");
		list.add("a");
		list.add("bb");
		list.add("ccc");
		assertFalse(tester.addAll(list));
		assertTrue(tester.contains("a"));
		assertTrue(tester.contains("bb"));
		assertTrue(tester.contains("ccc"));
		assertEquals(3,tester.size());
	}
	/**
	 * This test ensures that addAll does not change the list when it is called on an empty list. 
	 */
	@Test
	public void testAddAllEmpty(){
		assertFalse(tester.addAll(empty));
		assertTrue(tester.isEmpty());
	}
	
	/**
	 * Testing contains all
	 */
	/**
	 * Test to ensure that true is returned when containsAll is called on a list that has all the values in the list. 
	 */
	@Test
	public void testContainsAll(){
		list.add("b");
		list.add("e");
		list.add("ab");
		list.add("ABD");
		tester.add("b");
		tester.add("e");
		tester.add("ab");
		tester.add("ABD");
		assertTrue(tester.containsAll(list));
	}

	
	/**
	 * This test ensures that false is returned when contains all is called on a list with some items in the list. 
	 */
	@Test
	public void testContainsSome(){
		list.add("b");
		list.add("e");
		list.add("ab");
		list.add("ABD");
		tester.add("b");
		tester.add("e");
		assertFalse(tester.containsAll(list));
	}
	
	/**
	 * This test ensures the a list that contains no values in the tester returns false.
	 */
	@Test
	public void testContainsValueNotInTester(){
		list.add("b");
		list.add("e");
		list.add("ZZZ");
		tester.add("b");
		tester.add("e");
		assertFalse(tester.containsAll(list));
	}
	
	
	/**
	 * This test ensures that true is returned when containsAll is called on an empty list. 
	 * !!!
	 */
	@Test
	public void testContainsEmptyList(){
		assertTrue(tester.containsAll(empty));
	}
	
}
