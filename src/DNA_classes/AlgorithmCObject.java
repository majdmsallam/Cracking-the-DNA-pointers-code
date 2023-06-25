package DNA_classes;

import java.util.ArrayList;

/**
 * @author MohamedAboSaleh This class represent the structure of the
 *         segmented_word_distribution_analysis algorithm results.
 *
 */
public class AlgorithmCObject {
	private String sequence;
	private ArrayList<Integer> occurrences;
	private String reversed_sequence;
	private ArrayList<Integer> reversed_occurrences;

	public AlgorithmCObject(String sequence, ArrayList<Integer> occurrences, String reversed_sequence,
			ArrayList<Integer> reversed_occurrences) {
		this.sequence = sequence;
		this.occurrences = occurrences;
		this.reversed_sequence = reversed_sequence;
		this.reversed_occurrences = reversed_occurrences;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(sequence + " ");
		stringBuilder.append(occurrences + " | ");
		stringBuilder.append(reversed_sequence + " ");
		stringBuilder.append(reversed_occurrences);

		return stringBuilder.toString();
	}

}
