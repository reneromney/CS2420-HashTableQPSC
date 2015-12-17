package assign10;

/**
 * A class for testing the hash functions. 
 * 
 * @author Romeny Doria & Salvatore Stone Mele
 *  CADE:  doria           mele 
 *  Date Modified: 11/18/2015
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class HashFunctionTester {
	
	GoodHash good = new GoodHash();
	MediocreHash medi = new MediocreHash();
	BadHash bad = new BadHash();
	
	/**
	 * These tests ensure that the basic functionality of the hash functions are in working order. We did not test java's as we are pretty
	 * sure java has extensively tested it themselves. 
	 */
	
	@Test
	public void testBadDefault(){
		assertEquals(4,bad.hash("abcd"));
	}
	
	@Test
	public void testBadSame(){
		assertEquals(bad.hash("abcd"),bad.hash("abcd"));
		
	}
	
	@Test
	public void testBadNone(){
		assertEquals(0,bad.hash(""));
	}
	
	@Test
	public void testBadOne(){
		assertEquals(1,bad.hash("a"));
	}
	
	
	@Test 
	public void testMediOne(){
		assertEquals(97,medi.hash("a"));
	}
	
	
	@Test 
	public void testMediNone(){
		assertEquals(0,medi.hash(""));
	}
	
	@Test 
	public void testMediDefault(){
		assertEquals(97+98,medi.hash("ab"));
	}
	
	
	@Test 
	public void testMediSame(){
		assertEquals(medi.hash("abcd"),medi.hash("abcd"));
	}
	
}
