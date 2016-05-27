package com.dedup.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dedup.example.exception.NonPositiveValueException;

/**
 * Test class for DeDup.java
 * 
 * @author Meenal Jadhav
 * @version 1.0
 *
 */
public class DeDupTest {
	public int[] randomIntegers = { 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, 1, 10000 };
	public int[] deDupIntegers = { 1, 2, 34, 25, 45, 3, 26, 85, 4, 86, 43, 10000 };

	private DeDup deDup = new DeDup(randomIntegers);
	
	/**
	 * Test for null values in constructor
	 */
	@Test(expected=IllegalArgumentException.class)
	public void nullValueConstuctorTest() {
		new DeDup(null);
	}

	/**
	 * Test for null values in constructor
	 */
	@Test(expected=IllegalArgumentException.class)
	public void nullValueSetterTest() {
		DeDup deDup = new DeDup(0);
		deDup.setRandomIntegers(null);
	}

	/**
	 * Test if size of arrays match for input and duplicate removed.
	 */
	@Test
	public void removeDuplicatesWithSetTest() {
		int[] dupsRemoved = deDup.removeDuplicatesWithSet();
		assertEquals(dupsRemoved.length, deDupIntegers.length);
	}
	
	/**
	 * Test if duplicate removed list is correct.
	 */
	@Test
	public void removeDuplicatesWithSetTestForContent() {
		int[] dupsRemoved = deDup.removeDuplicatesWithSet();
		List<Integer> verifyContents = new ArrayList<Integer>();
		for (int s : randomIntegers) {
			verifyContents.add(s);
		}
		for(int s : dupsRemoved) {
			assertTrue(verifyContents.contains(s));
		}
	}
	
	/**
	 * Pass in list with negative values in an array for test.
	 */
	@Test
	public void removeDuplicatesWithSetTestForNegative() {
		int[] input = { -1, 2, 34, 34, 25, -1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, -1, 10000 };
		deDup.setRandomIntegers(input);
		int[] dupsRemoved = deDup.removeDuplicatesWithSet();
		assertEquals(dupsRemoved.length, deDupIntegers.length);
	}

	/**
	 * Test if size of arrays match for input and duplicate removed.
	 */
	@Test
	public void removeDuplicateWithArrayTest() throws NonPositiveValueException {
		int[] dupsRemoved = deDup.removeDuplicatesWithArray();
		assertEquals(dupsRemoved.length, deDupIntegers.length);
	}

	/**
	 * Test for content of the dedup list.
	 * 
	 * @throws NonPositiveValueException
	 */
	@Test
	public void removeDuplicateWithArrayTestForContent() throws NonPositiveValueException {
		int[] dupsRemoved = deDup.removeDuplicatesWithArray();
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
	@Test(expected = NonPositiveValueException.class)
	public void removeDuplicateWithArrayTestWithExpection1() throws NonPositiveValueException {
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
	@Test(expected = NonPositiveValueException.class)
	public void removeDuplicateWithArrayTestWithExpection2() throws NonPositiveValueException {
		int[] input = { -1, 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, 1, 10000 };
		deDup.setRandomIntegers(input);
		int[] dupsRemoved = deDup.removeDuplicatesWithArray();
		assertNotEquals(dupsRemoved.length, deDupIntegers.length);
	}

	/**
	 * Test for largest value calucation.
	 */
	@Test
	public void findLargestValueTest() {
		int large = deDup.findLargestValue();
		assertEquals(10000, large);
	}

	/**
	 * Test if size of arrays match for input and duplicate removed.
	 */
	@Test
	public void removeDuplicateWithListTest() {
		int[] dupsRemoved = deDup.removeDuplicatesWithList();
		assertEquals(dupsRemoved.length, deDupIntegers.length);
	}

	/**
	 * Test for the contents of de-dup list
	 */
	@Test
	public void removeDuplicateWithListTestForContent() {
		int[] dupsRemoved = deDup.removeDuplicatesWithList();
		List<Integer> verifyContents = new ArrayList<Integer>();
		for (int s : randomIntegers) {
			verifyContents.add(s);
		}
		for(int s : dupsRemoved) {
			assertTrue(verifyContents.contains(s));
		}
	}

	/**
	 * Pass in list with negative values for test
	 */
	@Test
	public void removeDuplicateWithListTestForNegative() {
		int[] input = { -1, 2, 34, 34, 25, -1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, -1, 10000 };
		deDup.setRandomIntegers(input);
		int[] dupsRemoved = deDup.removeDuplicatesWithList();
		assertEquals(dupsRemoved.length, deDupIntegers.length);
	}

	/**
	 * Test for original order of the list. Also test if the input does not have
	 * any duplicates
	 */
	@Test
	public void removeDuplicateWithListTestForOrder() {
		int[] dupsRemoved = deDup.removeDuplicatesWithList();
		for (int i = 0; i < dupsRemoved.length; i++) {
			assertEquals(dupsRemoved[i], deDupIntegers[i]);
		}
	}

	/**
	 * Test of input with no duplicates.
	 */
	@Test
	public void removeDuplicateWithListTestForNoDups() {
		// Testing for input with no duplicates
		int[] input = { 1, 2, 34, 25, 45, 3, 26, 85, 4, 86, 43, 10000 };
		deDup.setRandomIntegers(input);
		int[] dupsRemoved = deDup.removeDuplicatesWithList();
		for (int i = 0; i < dupsRemoved.length; i++) {
			assertEquals(dupsRemoved[i], deDupIntegers[i]);
		}
	}
}
