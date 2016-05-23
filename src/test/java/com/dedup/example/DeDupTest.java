package com.dedup.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;;

public class DeDupTest {
	public int[] randomIntegers = { 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, 1, 10000 };
	public int[] deDupIntegers = { 1, 2, 34, 25, 45, 3, 26, 85, 4, 86, 43, 10000 };

	private DeDup deDup = new DeDup(randomIntegers);

	@Test
	public void removeDuplicatesWithSetTest() {
		int[] dupsRemoved = deDup.removeDuplicatesWithSet();
		assertEquals(dupsRemoved.length, deDupIntegers.length);
		List<Integer> verifyContents = new ArrayList<Integer>();
		for (int s : randomIntegers) {
			verifyContents.add(s);
		}
		for(int s : dupsRemoved) {
			assertTrue(verifyContents.contains(s));
		}
		
		int[] input = { -1, 2, 34, 34, 25, -1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, -1, 10000 };
		deDup.setRandomIntegers(input);
		dupsRemoved = deDup.removeDuplicatesWithSet();
		assertEquals(dupsRemoved.length, deDupIntegers.length);


	}

	@Test
	public void removeDuplicateWithArrayTest() throws Exception {
		int[] dupsRemoved = deDup.removeDuplicatesWithArray();
		assertEquals(dupsRemoved.length, deDupIntegers.length);
		List<Integer> verifyContents = new ArrayList<Integer>();
		for (int s : randomIntegers) {
			verifyContents.add(s);
		}
		for(int s : dupsRemoved) {
			assertTrue(verifyContents.contains(s));
		}
		
	}

	/**
	 * Test for zero value in the input
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void removeDuplicateWithArrayTestWithExpection1() throws Exception {
		int[] input = { 0, 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, 1, 10000 };
		deDup.setRandomIntegers(input);
		int[] dupsRemoved = deDup.removeDuplicatesWithArray();
		assertNotEquals(dupsRemoved.length, deDupIntegers.length);
	}

	/**
	 * Test for negative value in the input
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void removeDuplicateWithArrayTestWithExpection2() throws Exception {
		int[] input = { -1, 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, 1, 10000 };
		deDup.setRandomIntegers(input);
		int[] dupsRemoved = deDup.removeDuplicatesWithArray();
		assertNotEquals(dupsRemoved.length, deDupIntegers.length);
	}

	@Test
	public void findLargestValueTest() {
		int large = deDup.findLargestValue();

		assertEquals(10000, large);
	}

	@Test
	public void removeDuplicateWithListTest() {
		int[] dupsRemoved = deDup.removeDuplicatesWithList();
		assertEquals(dupsRemoved.length, deDupIntegers.length);
		List<Integer> verifyContents = new ArrayList<Integer>();
		for (int s : randomIntegers) {
			verifyContents.add(s);
		}
		for(int s : dupsRemoved) {
			assertTrue(verifyContents.contains(s));
		}
		
		int[] input = { -1, 2, 34, 34, 25, -1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, -1, 10000 };
		deDup.setRandomIntegers(input);
		dupsRemoved = deDup.removeDuplicatesWithList();
		assertEquals(dupsRemoved.length, deDupIntegers.length);
	}

	/**
	 * Test for original order of the list. Also test if the input does not have
	 * any duplicates
	 */
	@Test
	public void removeDuplicateWithListTestForOrder() {
		int[] dupsRemoved = deDup.removeDuplicatesWithList();
		assertEquals(dupsRemoved.length, deDupIntegers.length);
		for (int i = 0; i < dupsRemoved.length; i++) {
			assertEquals(dupsRemoved[i], deDupIntegers[i]);
		}

		// Testing for input with no duplicates
		int[] input = { 1, 2, 34, 25, 45, 3, 26, 85, 4, 86, 43, 10000 };
		deDup.setRandomIntegers(input);
		dupsRemoved = deDup.removeDuplicatesWithList();
		assertEquals(dupsRemoved.length, deDupIntegers.length);
		for (int i = 0; i < dupsRemoved.length; i++) {
			assertEquals(dupsRemoved[i], deDupIntegers[i]);
		}
	}

}
