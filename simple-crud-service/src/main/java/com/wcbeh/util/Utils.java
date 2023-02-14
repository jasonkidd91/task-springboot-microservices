package com.wcbeh.util;

import java.util.Arrays;
import java.util.List;

public class Utils {
	/**
	 * Generate the permutations combinations with repetition allowed
	 * @param numbers - list of numbers to be loop and processed
	 * @param data - list of integer which size k representing combination's size
	 * @param index - current index for each recursive, this should be the exit recursive indicator
	 * @param result - output results for all combinations
	 */
	public static void permutation(int[] numbers, int[] data, int index, List<int[]> result){
		if(index == data.length) {
			int[] combination = data.clone();
	        result.add(combination);
		} else {
	       for(int i=0;i<numbers.length;i++) {
	    	   // reset for new recursive loop
	    	   if(index == 0) { data = new int[data.length]; }
	    	   // avoid duplicate for each recursive
	    	   if(!Utils.contains(data, numbers[i])) {
		    	   data[index] = numbers[i];
		    	   permutation(numbers, data, index+1, result);
	    	   }
	       }   
		}
    }
	/**
	 * Check if key exists within the list
	 * @param arr - list of numbers to be checked
	 * @param key - key number to be compared within the list
	 * @return if exists true else false
	 */
	public static boolean contains(final int[] arr, final int key) {
	    return Arrays.stream(arr).anyMatch(i -> i == key);
	}
	/**
	 * @deprecated
	 * Generate the permutations combinations with non-repetition allowed
	 * @param result - output results for all combinations
	 * @param data - list of integer which size k representing combination's size
	 * @param start - start looping index for each recursive
	 * @param currentIndex - current index for each recursive, this should be the exit recursive indicator
	 * @param numbers - list of numbers to be loop and processed
	 */
	public static void distinctPermutation(List<int[]> result, int data[], int start, int currentIndex, List<Integer> numbers) {
		if (currentIndex == data.length) {
	        int[] combination = data.clone();
	        result.add(combination);
	    } else if (start <= numbers.size()-1) {
	        data[currentIndex] = numbers.get(start);
	        distinctPermutation(result, data, start + 1, currentIndex + 1, numbers);
	        distinctPermutation(result, data, start + 1, currentIndex, numbers); //discard used value
	    }
	}
}
