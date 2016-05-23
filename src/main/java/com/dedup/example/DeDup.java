package com.dedup.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class DeDup {

	private static Logger logger = Logger.getLogger(DeDup.class);

	private int[] randomIntegers;

	public DeDup(int[] input) {

		this.randomIntegers = input;
	}

	public void setRandomIntegers(int[] input) {
		this.randomIntegers = input;
	}

	/**
	 * Removes duplication with the use of Set.
	 * 
	 * Advantage - Use of standard java data structure.
	 * 
	 * Disadvantage - Multiple looping of input dataset. Once to add to set and
	 * then to convert back into int array as return value.
	 * 
	 * It is possible to clean the code with use of third party libraries like
	 * Apache ArrayUtils to convert set back into int array, but the looping is
	 * still unavoidable.
	 * 
	 * @return
	 */
	public int[] removeDuplicatesWithSet() {
		if(logger.isDebugEnabled()) {
			logger.debug("Removing Duplicates with Set");
		}
		Set<Integer> s = new HashSet<Integer>();
		for (int i : randomIntegers) {
			s.add(i);
		}
		int[] returnValue = new int[s.size()];
		int i = 0;
		for (Integer in : s) {
			returnValue[i++] = in;
		}
		return returnValue;
	}

	/**
	 * Removes Duplicates using additional int array.
	 * 
	 * Advantage - Use of primitive array only.
	 * 
	 * Disadvantage - Multiple looping of array. The total size required would
	 * also be large as second array size is dependent on the largest value in
	 * the array. Works only with values greater than zero.
	 * 
	 * @return - sorted array with duplicates removed.
	 */
	public int[] removeDuplicatesWithArray() throws Exception {
		logger.debug("Removing Duplicates with Array");
		int largest = findLargestValue();
		int[] deDup = new int[largest + 1];
		for (int s : randomIntegers) {
			if (s < 1)
				throw new Exception("Zero or negative value in the input array.");
			deDup[s] = s;
		}

		// Find total non zero elements to determine final array size
		int total = 0;
		for (int s : deDup) {
			if (s > 0)
				total++;
		}
		int[] returnValue = new int[total];
		int i = 0;
		for (int s : deDup) {
			if (s > 0)
				returnValue[i++] = s;
		}
		return returnValue;
	}

	protected int findLargestValue() {
		int large = Integer.MIN_VALUE;
		for (int s : randomIntegers) {
			if (s > large) {
				large = s;
			}
		}
		return large;
	}

	/**
	 * Removes duplicates with the use of ArrayList
	 * 
	 * Advantage - Use of standard Java Data Structure. Maintains the order of
	 * input array.
	 * 
	 * Disadvantage - Multiple looping of input dataset. Once to add to list and
	 * then to convert back into int array as return value
	 * 
	 * It is possible to clean the code with use of third party libraries like
	 * Apache ArrayUtils to convert List back into int array.
	 * 
	 * @return
	 */
	public int[] removeDuplicatesWithList() {
		logger.debug("Removing Duplicates with List to preserve order");
		List<Integer> list = new ArrayList<Integer>();
		for (int s : randomIntegers) {
			if (!list.contains(s)) {
				list.add(s);
			}
		}
		int[] returnValue = new int[list.size()];
		int i = 0;
		for (Integer s : list) {
			returnValue[i++] = s;
		}
		return returnValue;
	}

	public static void main(String[] args) throws Exception {
		int[] input = { 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86, 25, 43, 2, 1, 10000, 11, 16, 19, 1, 18, 4, 9, 3,
				20, 17, 8, 15, 6, 2, 5, 10, 14, 12, 13, 7, 8, 9, 1, 2, 15, 12, 18, 10, 14, 20, 17, 16, 3, 6, 19, 13, 5,
				11, 4, 7, 19, 16, 5, 9, 12, 3, 20, 7, 15, 17, 10, 6, 1, 8, 18, 4, 14, 13, 2, 11 };
		logger.info("Start");
		DeDup deDup = new DeDup(input);
		
		long start = System.currentTimeMillis();
		deDup.removeDuplicatesWithSet();
		
		long set = System.currentTimeMillis();
		deDup.removeDuplicatesWithArray();

		long array = System.currentTimeMillis();
		deDup.removeDuplicatesWithList();
		long list = System.currentTimeMillis();

		if (logger.isInfoEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("Total time taken is ").append((list - start)).append(" milliseconds\n")
					.append("Time for Set implementation is ").append((set - start)).append(" milliseconds\n")
					.append("Time for Array implementation is ").append((array - set)).append(" milliseconds\n")
					.append("Time for List implementation is ").append((list - array)).append(" milliseconds\n");
			logger.info(sb.toString());
		}
		logger.info("End");
	}

}
