package com.dedup.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.dedup.example.exception.NonPositiveValueException;

/**
 * Removes Duplicates from integer array.
 * 
 * @author Meenal Jadhav
 * @version 1.0
 * 
 *
 */
public class DeDup {

	/**
	 * final log4j logger
	 */
	private static final Logger LOGGER = Logger.getLogger(DeDup.class);

	/**
	 * int array to hold input integer values
	 */
	private int[] randomIntegers;
	
	/**
	 * Determine if random integer can be accessed
	 */
	private boolean access = false;

	/**
	 * Create DeDup Object for given int array
	 * @param input integer array
	 * @throws IllegalArgumentException if the input array is null
	 */
	public DeDup(final int ... input ) throws IllegalArgumentException {

		if(input == null) {
			throw new IllegalArgumentException("Input array is null");
		}
		this.randomIntegers = input;
		this.access = true;
	}

	/**
	 * setter to override the int array
	 * 
	 * @param input integer array
	 * @throws IllegalArgumentException - if the input is null
	 */
	public void setRandomIntegers(final int ... input) throws IllegalArgumentException{
		if(input == null) {
			throw new IllegalArgumentException("Input Array is null");
		}
		synchronized (this) {
			this.access = false;
			this.randomIntegers = input;
			this.access = true;
		}
	}
	
	/**
	 * getter for int array
	 * @return copy of original integer array
	 */
	public int[] getRandomIntegers(){
		if(this.access) {
			return randomIntegers.clone();
		} else {
			synchronized (this) {
				return getRandomIntegers();
			}
		}
	}

	/**
	 * <p>Removes duplication with the use of Set.</p>
	 * 
	 * <p>Advantage - Use of standard java data structure.
	 * Reduced development effort by using core collection classes rather than implementing our own collection classes.</p>
	 * 
	 *<p> Disadvantage - Multiple looping of input dataset. Once to add to set and
	 * then to convert back into int array as return value.</p>
	 * 
	 * <p>It is possible to clean the code with use of third party libraries like
	 * Apache ArrayUtils to convert set back into int array, but the looping is
	 * still unavoidable.</p>
	 * 
	 * <p>@return int array with duplicates removed</p>
	 */
	public int[] removeDuplicatesWithSet() {
		final long start = System.currentTimeMillis();
		Set<Integer> hashSet = new HashSet<Integer>();
		for (int i : getRandomIntegers()) {
			hashSet.add(i);
		}
		int[] returnValue = new int[hashSet.size()];
		int i = 0;
		for (Integer in : hashSet) {
			returnValue[i++] = in;
		}
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Remove Duplicates with Set Implementation took " + (System.currentTimeMillis() - start) + " milliseconds");
		}
		return returnValue;
	}

	/**
	 * <p>Removes Duplicates using additional int array.</p>
	 * 
	 * <p>Advantage - Use of primitive array only.</p>
	 * 
	 * <p>Disadvantage - Multiple looping of array. The total size required would
	 * also be large as second array size is dependent on the largest value in
	 * the array. Works only with values greater than zero.We need to know the size of the array in advance.
	 * Since array is of fixed size, if we allocate more memory than requirement then the memory space will be wasted</p>
	 * 
	 * <p>@return - sorted array with duplicates removed.</p>
	 * <p>@throws NonPositiveValueException - zero or negative value in the array</p>
	 */
	public int[] removeDuplicatesWithArray() throws NonPositiveValueException {
		final long start = System.currentTimeMillis();
		final int largest = findLargestValue();
		int[] deDup = new int[largest + 1];
		for (int s : getRandomIntegers()) {
			if (s < 1) {
				throw new NonPositiveValueException("Zero or negative value in the input array.");
			}
			deDup[s] = s;
		}

		// Find total non zero elements to determine final array size
		int total = 0;
		for (int s : deDup) {
			if (s > 0) {
				total++;
			}
		}
		int[] returnValue = new int[total];
		int i = 0;
		for (int s : deDup) {
			if (s > 0) {
				returnValue[i++] = s;
			}
		}
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Remove Duplicates with Array Implementation took " + (System.currentTimeMillis() - start) + " milliseconds");
		}
		return returnValue;
	}
	/**
	 * Find the largest value
	 * @return largest value in the array
	 */
	protected int findLargestValue() {
		int large = Integer.MIN_VALUE;
		for (int s : getRandomIntegers()) {
			if (s > large) {
				large = s;
			}
		}
		return large;
	}

	/**
	 * <p>Removes duplicates with the use of ArrayList</p>
	 * 
	 * <p>Advantage - Use of standard Java Data Structure.ArrayList maintains the order of
	 * input array.</p>
	 * 
	 * <p>Disadvantage - Multiple looping of input dataset. Once to add to list and
	 * then to convert back into int array as return value.</p>
	 * 
	 * <p>It is possible to clean the code with use of third party libraries like
	 * Apache ArrayUtils to convert List back into int array.</p>
	 * 
	 * <p>@return  int array with duplicates removed in same order as input</p>
	 */
	public int[] removeDuplicatesWithList() {
		final long start = System.currentTimeMillis();
		List<Integer> list = new ArrayList<Integer>();
		for (int s : getRandomIntegers()) {
			if (!list.contains(s)) {
				list.add(s);
			}
		}
		int[] returnValue = new int[list.size()];
		int i = 0;
		for (Integer s : list) {
			returnValue[i++] = s;
		}
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Remove Duplicates with List Implementation took " + (System.currentTimeMillis() - start) + " milliseconds");
		}
		return returnValue;
	}
	/**
	 * Main Method
	 * @param args  the program is not expecting any arguments
	 * @throws NonPositiveValueException if the array contains any negative or zero value an exception is raised
	 * during Array implementation.
	 */
	public static void main(String ... args) throws NonPositiveValueException {
		final int[] input = { 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, 1, 10000, 11, 16, 19, 1, 18, 4, 9, 3,
				20, 17, 8, 15, 6, 2, 5, 10, 14, 12, 13, 7, 8, 9, 1, 2, 15, 12, 18, 10, 14, 20, 17, 16, 3, 6, 19, 13, 5,
				11, 4, 7, 19, 16, 5, 9, 12, 3, 20, 7, 15, 17, 10, 6, 1, 8, 18, 4, 14, 13, 2, 11 };
		LOGGER.info("Start");
		
		
		DeDup deDup = new DeDup(input);
		
		long start = System.currentTimeMillis();
		int[] deDupFromSetImpl = deDup.removeDuplicatesWithSet();
		long afterSet = System.currentTimeMillis();
		
		
		long beforeArray = System.currentTimeMillis();
		int[] deDupFromArrayImpl = deDup.removeDuplicatesWithArray();
		long afterArray = System.currentTimeMillis();
		
		long beforeList = System.currentTimeMillis();
		int[] deDupFromListImpl = deDup.removeDuplicatesWithList();
		long afterList = System.currentTimeMillis(); 
		
		if (LOGGER.isInfoEnabled()) {
			StringBuilder sb = new StringBuilder();

			sb.append("\nTotal time taken is ").append((afterList - start)).append(" milliseconds\n");

			sb.append("\n\nInput Integer Array has ").append(input.length).append(" elements\n");
			for (int s : input) {
				sb.append(s).append(' ');
			}

			sb.append("\n\nOutput from Set Implementation has ").append(deDupFromSetImpl.length).append(" elements and took ").append((afterSet - start)).append(" milliseconds\n");
			for (int s : deDupFromSetImpl) {
				sb.append(s).append(' ');
			}
			sb.append("\n\nOutput from Array Implementation has ").append(deDupFromArrayImpl.length).append(" elements and took ").append((afterArray - beforeArray)).append(" milliseconds\n");
			for(int s : deDupFromArrayImpl) {
				sb.append(s).append(' ');
			}
			sb.append("\n\nOutput from List Implementation has ").append(deDupFromArrayImpl.length).append(" elements and took ").append((afterList - beforeList)).append(" milliseconds\n");
			for (int s : deDupFromListImpl) {
				sb.append(s).append(' ');
			}

			LOGGER.info(sb.toString());
		}
		LOGGER.info("End");
	}

}
