package algorithms;

import java.util.ArrayList;
import java.util.HashMap;

import DNA_classes.AlgorithmCObject;

/**
 * @author MohamedAboSaleh This class contains all the relative methods to run
 *         Algorithm C for DNA analysis
 *
 */
public class Algorithm_C {
	/**
	 * @param dna_seq
	 * @param number_of_segments
	 * @param k
	 * @return ArrayList<AlgorithmCObject> Functionality: this method builds a list
	 *         of segments from the dna_seq, and iterates through the first segment
	 *         using a sliding window approach, and for each word it calculates the
	 *         number of occurrences in all the segments. after that it iterates
	 *         through the distribution map, and for each word and its reversed
	 *         complement, it checks if the number of occurrences before the binding
	 *         site is greater than after the binding site, and if the occurrences
	 *         before the binding site are in increasing order and after the binding
	 *         site are in decreasing order and and the opposite is correct is the
	 *         reversed complement. if a word satisfies all the conditions it would
	 *         be a potential pointer.
	 */
	public static ArrayList<AlgorithmCObject> segmented_word_distribution_analysis(String dna_seq,
			int number_of_segments, int k) {
		HashMap<String, ArrayList<Integer>> distribution_map = new HashMap<String, ArrayList<Integer>>();
		ArrayList<AlgorithmCObject> arrlist = new ArrayList<AlgorithmCObject>();
		ArrayList<String> segments = get_segments(dna_seq, number_of_segments);
		for (int i = 0; i < segments.get(0).length() - k + 1; i++) {
			String word = segments.get(0).substring(i, k + i);
			if (!distribution_map.containsKey(word)) {
				ArrayList<Integer> distribution = new ArrayList<Integer>();
				for (String segment : segments)
					distribution.add(Utils.countOccurrences(segment, word));
				distribution_map.put(word, distribution);

			}
		}
		for (String word : distribution_map.keySet()) {
			String reverse = Utils.rev_dna_seq(word);
			if (distribution_map.containsKey(reverse)) {
				ArrayList<Integer> lst = distribution_map.get(word);
				ArrayList<Integer> lst_for_reversed = distribution_map.get(reverse);
				if (check_downstream_upstream_distribution(lst) && increasing_decreasing_order(lst)
						&& !check_downstream_upstream_distribution(lst_for_reversed)
						&& decreasing_increasing_order(lst_for_reversed)) {
					arrlist.add(new AlgorithmCObject(word, lst, reverse, lst_for_reversed));
				}
			}

		}

		return arrlist;

	}

	/**
	 * @param distribution
	 * @return boolean Functionality: calculates the occurrences before and after
	 *         the binding site and returns true if the number of occurrences before
	 *         the binding site is greater than after the binding site, return true
	 *         or false accordingly.
	 */
	private static boolean check_downstream_upstream_distribution(ArrayList<Integer> distribution) {
		int downstream = 0;
		int upstream = 0;
		for (int i = 0; i < distribution.size() / 2; i++)
			downstream += distribution.get(i);
		for (int i = distribution.size() / 2; i < distribution.size(); i++)
			upstream += distribution.get(i);

		return downstream > upstream;
	}

	/**
	 * @param distribution
	 * @return boolean Functionality: checks if the distribution which is the number
	 *         of occurrences in the segments for a specific word is in increasing
	 *         order before the binding site, and in decreasing order after the
	 *         binding site, return true or false accordingly.
	 */
	private static boolean increasing_decreasing_order(ArrayList<Integer> distribution) {
		for (int i = 1; i < distribution.size() / 2; i++)
			if (distribution.get(i) < distribution.get(i - 1))
				return false;
		for (int i = distribution.size() / 2; i < distribution.size(); i++)
			if (distribution.get(i) > distribution.get(i - 1))
				return false;
		return true;
	}

	/**
	 * @param distribution
	 * @return boolean Functionality: checks if the distribution which is the number
	 *         of occurrences in the segments for a specific word is in decreasing
	 *         order before the binding site, and in increasing order after the
	 *         binding site, return true or false accordingly.
	 */
	private static boolean decreasing_increasing_order(ArrayList<Integer> distribution) {
		for (int i = 1; i < distribution.size() / 2; i++)
			if (distribution.get(i) > distribution.get(i - 1))
				return false;
		for (int i = distribution.size() / 2; i < distribution.size(); i++)
			if (distribution.get(i) < distribution.get(i - 1))
				return false;
		return true;
	}

	/**
	 * @param dna_seq
	 * @param number_of_segments
	 * @return ArrayList<String> Functionality: divides the dna_seq into
	 *         number_of_segments and returns array list of the segments
	 */
	private static ArrayList<String> get_segments(String dna_seq, int number_of_segments) {
		int segment_size = dna_seq.length() / number_of_segments;
		ArrayList<String> segments = new ArrayList<String>();
		for (int i = 0; i < number_of_segments; i++)
			segments.add(dna_seq.substring(i * segment_size, i * segment_size + segment_size));
		return segments;
	}

}
