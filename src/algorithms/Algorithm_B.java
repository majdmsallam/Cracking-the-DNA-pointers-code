package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import DNA_classes.Seq_RevSeq_Occurrences;

/**
 * @author MajdMsallam This class contains all the relative methods to run
 *         Algorithm B for DNA analysis
 * 
 *
 */
public class Algorithm_B {

	/**
	 * @param dnaSeq
	 * @param k
	 * @return List<Seq_RevSeq_Occurrences> Functionality: this algorithm divides
	 *         the DNA sequence into two segments (down stream, up stream) it
	 *         iterates through the down stream sequence using a sliding window
	 *         approach with window size equals "k" moving the window one position
	 *         at a time, and count the occurrences of each word in the two segments
	 *         if the number of occurrences for a specific word is greater in the
	 *         down stream than the upstream and the reverse complement word
	 *         occurrences is greater in the up stream than the down stream, the
	 *         word would be a potential pointer.
	 */
	public static List<Seq_RevSeq_Occurrences> word_occurrences_DNA_analysis(String dnaSeq, int k) {
		String downstream = dnaSeq.substring(0, dnaSeq.length() / 2);
		String upstream = dnaSeq.substring(dnaSeq.length() / 2);

		HashMap<String, ArrayList<Integer>> mapOfWordOccurrences = new HashMap<>();

		for (int i = 0; i <= downstream.length() - k; i++) {
			String st = downstream.substring(i, i + k);
			if (!mapOfWordOccurrences.containsKey(st)) {
				ArrayList<Integer> occurrences = new ArrayList<>();
				occurrences.add(1);
				occurrences.add(Utils.countOccurrences(upstream, st));
				mapOfWordOccurrences.put(st, occurrences);
			} else {
				List<Integer> occurrences = mapOfWordOccurrences.get(st);
				occurrences.set(0, occurrences.get(0) + 1);
			}
		}

		List<Seq_RevSeq_Occurrences> finalList = new ArrayList<Seq_RevSeq_Occurrences>();
		for (String word : mapOfWordOccurrences.keySet()) {
			String rev = Utils.rev_dna_seq(word);
			if (mapOfWordOccurrences.containsKey(rev)) {
				ArrayList<Integer> lst1 = mapOfWordOccurrences.get(word);
				ArrayList<Integer> lst2 = mapOfWordOccurrences.get(rev);
				if (lst1.get(0) > lst1.get(1) * 1.5 && lst2.get(0) < lst2.get(1)) {
					finalList.add(new Seq_RevSeq_Occurrences(word, lst1, rev, lst2));
				}
			}
		}

		return finalList;
	}

}
