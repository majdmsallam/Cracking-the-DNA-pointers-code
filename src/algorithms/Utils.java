package algorithms;

/**
 * @author MohamedAboSaleh
 * This class contains methods that are common to all the algorithms
 *
 */
public class Utils {
	
	/**
	 * @param dna_seq
	 * @param pattern
	 * @return number of occurrences of pattern in dna_seq
	 * Functionality: This method iterates counts the number of occurrences of pattern in dna_seq
	 * and returns it.
	 */
	public static int countOccurrences(String dna_seq, String pattern) {
		int count = 0;
		int index = 0;
		while ((index = dna_seq.indexOf(pattern, index)) != -1) {
			count++;
			index += pattern.length();
		}
		return count;
	}

	/**
	 * @param dna_seq
	 * @return reversed dna_seq Functionality: This method iterates on all the
	 *         nucleic acid bases of the dna_seq to form the reversed complement
	 *         sequence and returns it.
	 */
	public static String rev_dna_seq(String dna_seq) {
		StringBuilder st = new StringBuilder();
		for (int i = 0; i < dna_seq.length(); i++) {
			if (dna_seq.charAt(i) == 'A')
				st.append('T');
			else if (dna_seq.charAt(i) == 'T')
				st.append('A');
			else if (dna_seq.charAt(i) == 'C')
				st.append('G');
			else if (dna_seq.charAt(i) == 'G')
				st.append('C');

		}
		return st.reverse().toString();
	}
}
