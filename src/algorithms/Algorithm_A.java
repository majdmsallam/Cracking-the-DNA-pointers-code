package algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import DNA_classes.AlgorithmAObject;
import controllers.StaticObjects;

/**
 * @author MohamedAboSaleh This class contains all the relative methods to run
 *         Algorithm A for DNA analysis
 *
 */
public class Algorithm_A {

	/**
	 * @param dna_seq1
	 * @param dna_seq2
	 * @param k
	 * @return HashMap<String, ArrayList<Integer>> this private function iterate on
	 *         Functionality: dna_seq1 using a sliding window approach with window
	 *         size equals to k, and for each word or window it count the number of
	 *         occurrences of the word in dna_seq1 and dna_seq2 and returns a map of
	 *         the words with their number of occurrences
	 * 
	 */
	private static HashMap<String, ArrayList<Integer>> get_occurrences(String dna_seq1, String dna_seq2, int k) {
		HashMap<String, ArrayList<Integer>> res_map = new HashMap<String, ArrayList<Integer>>();
		for (int i = 0; i < dna_seq1.length() - k + 1; i++) {
			String st = dna_seq1.substring(i, i + k);
			if (!res_map.containsKey(st)) {

				ArrayList<Integer> occurrences = new ArrayList<Integer>();
				occurrences.add(1);
				occurrences.add(Utils.countOccurrences(dna_seq2, st));
				res_map.put(st, occurrences);
			} else {
				ArrayList<Integer> occurrencesArrayList = res_map.get(st);
				occurrencesArrayList.set(0, occurrencesArrayList.get(0) + 1);
			}

		}
		return res_map;
	}

	/**
	 * @param list
	 * @return boolean Functionality: this function gets a list that contains lists
	 *         of the number of occurrences of a reversed DNA sequence and returns
	 *         true if for all values the number of occurrences after the binding
	 *         site is greater than before the binding site
	 */
	private static boolean checkmatForReverse(ArrayList<ArrayList<Integer>> list) {
		for (ArrayList<Integer> l : list)
			if (l.get(0) > l.get(1))
				return false;
		return true;

	}

	/**
	 * @param list
	 * @return boolean Functionality: this method gets a list that contains lists of
	 *         the number of occurrences of a DNA sequence and returns true if for
	 *         all values the number of occurrences before the binding site is
	 *         greater than after the binding site
	 */
	private static boolean checkmat(ArrayList<ArrayList<Integer>> list) {
		for (ArrayList<Integer> l : list)
			if (l.get(0) < l.get(1))
				return false;
		return true;

	}

	/**
	 * @param map1
	 * @param map2
	 * @param map3
	 * @param map4
	 * @param map5
	 * @param word
	 * @return ArrayList<ArrayList<Integer>> Functionality: this method takes 5 hash
	 *         maps,each of them represents the occurrences of words in each 1/2,
	 *         1/4, 1/8, 1/16 and 1/32 before and after binding site, and also the
	 *         method takes a word, and returns a list of lists of the number of
	 *         occurrences in each segment.
	 * 
	 */
	private static ArrayList<ArrayList<Integer>> get_list(HashMap<String, ArrayList<Integer>> map1,
			HashMap<String, ArrayList<Integer>> map2, HashMap<String, ArrayList<Integer>> map3,
			HashMap<String, ArrayList<Integer>> map4, HashMap<String, ArrayList<Integer>> map5, String word) {
		ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
		results.add(map1.get(word));
		results.add(map2.get(word));
		results.add(map3.get(word));
		results.add(map4.get(word));
		results.add(map5.get(word));
		return results;

	}

	/**
	 * @param dna_seq
	 * @param k
	 * @return ArrayList<AlgorithmAObject> Functionality: This is the main method of
	 *         the algorithm, it uses all the methods defined above, it defines 5
	 *         segments 1/2, 1/4, 1/8, 1/16 and 1/32 each of them has two values,
	 *         which are the number of occurrences before and after the binding
	 *         site, after that it iterates through all the words that are found in
	 *         the 1/32, and using the get_list method it builds a list of all the
	 *         occurrences in all the segments, if the reversed complement sequence
	 *         can be found in the map, and all the occurrences before the binding
	 *         site are greater than after the binding site, this would be a
	 *         potential pointer.
	 */
	public static ArrayList<AlgorithmAObject> segmented_dna_analysis(String dna_seq, int k) {
		ArrayList<AlgorithmAObject> arrlist = new ArrayList<AlgorithmAObject>();
		HashMap<String, ArrayList<Integer>> halves = get_occurrences(dna_seq.substring(0, dna_seq.length() / 2),
				dna_seq.substring(dna_seq.length() / 2, dna_seq.length()), k);
		HashMap<String, ArrayList<Integer>> quarters = get_occurrences(
				dna_seq.substring(dna_seq.length() / 4, dna_seq.length() / 2),
				dna_seq.substring(dna_seq.length() / 2, (dna_seq.length() / 4) * 3), k);
		HashMap<String, ArrayList<Integer>> eighths = get_occurrences(
				dna_seq.substring((dna_seq.length() / 8) * 3, dna_seq.length() / 2),
				dna_seq.substring(dna_seq.length() / 2, (dna_seq.length() / 8) * 5), k);
		HashMap<String, ArrayList<Integer>> one_sixteen = get_occurrences(
				dna_seq.substring((dna_seq.length() / 16) * 7, dna_seq.length() / 2),
				dna_seq.substring(dna_seq.length() / 2, (dna_seq.length() / 16) * 9), k);
		HashMap<String, ArrayList<Integer>> one_32 = get_occurrences(
				dna_seq.substring((dna_seq.length() / 32) * 15, dna_seq.length() / 2),
				dna_seq.substring(dna_seq.length() / 2, (dna_seq.length() / 32) * 17), k);
		for (String seq : one_32.keySet()) {
			ArrayList<ArrayList<Integer>> results = get_list(halves, quarters, eighths, one_sixteen, one_32, seq);

			String reverse = Utils.rev_dna_seq(seq);
			if (one_32.containsKey(reverse)) {
				ArrayList<ArrayList<Integer>> results_for_reverse = get_list(halves, quarters, eighths, one_sixteen,
						one_32, reverse);

				if (checkmat(results) && (results.get(0)).get(0) > (results.get(0)).get(1)
						&& checkmatForReverse(results_for_reverse)) {
					arrlist.add(new AlgorithmAObject(seq, results, reverse, results_for_reverse));

				}
			}

		}
		return arrlist;
	}

}
