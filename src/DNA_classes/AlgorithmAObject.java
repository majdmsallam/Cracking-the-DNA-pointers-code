package DNA_classes;

import java.util.ArrayList;

/**
 * @author MohamedAboSaleh This class represent the structure of the
 *         segmented_dna_analysis algorithm results.
 *
 */
public class AlgorithmAObject {
	private String sequence;
	private ArrayList<ArrayList<Integer>> occurrences;
	private String reversed_sequence;
	private ArrayList<ArrayList<Integer>> reversed_occurrences;

	public AlgorithmAObject(String sequence, ArrayList<ArrayList<Integer>> occurrences, String reversed_sequence,
			ArrayList<ArrayList<Integer>> reversed_occurrences) {
		this.sequence = sequence;
		this.occurrences = occurrences;
		this.reversed_sequence = reversed_sequence;
		this.reversed_occurrences = reversed_occurrences;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder s = new StringBuilder();
		s.append("=================================================================\n");
		s.append(sequence + ":\n");
		s.append("Occurrences in first and second halves: " + occurrences.get(0) + ".\n");
		s.append("Occurrences in second and 3rd quarters: " + occurrences.get(1) + ".\n");
		s.append("Occurrences in 3rd and fifth eighths: " + occurrences.get(2) + ".\n");
		s.append("Occurrences in 7/16 and 8/16 1/16: " + occurrences.get(3) + ".\n");
		s.append("Occurrences in 15/32 and 16/32 1/32: " + occurrences.get(4) + ".\n");
		s.append(reversed_sequence + ":\n");
		s.append("Occurrences in first and second halves: " + reversed_occurrences.get(0) + ".\n");
		s.append("Occurrences in second and 3rd quarters: " + reversed_occurrences.get(1) + ".\n");
		s.append("Occurrences in 3rd and fifth eighths: " + reversed_occurrences.get(2) + ".\n");
		s.append("Occurrences in 7/16 and 8/16 1/16: " + reversed_occurrences.get(3) + ".\n");
		s.append("Occurrences in 15/32 and 16/32 1/32: " + reversed_occurrences.get(4) + ".\n");
		s.append("=================================================================\n");
		return s.toString();
	}

	public ArrayList<ArrayList<Integer>> getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(ArrayList<ArrayList<Integer>> occurrences) {
		this.occurrences = occurrences;
	}

	public ArrayList<ArrayList<Integer>> getReversed_occurrences() {
		return reversed_occurrences;
	}

	public void setReversed_occurrences(ArrayList<ArrayList<Integer>> reversed_occurrences) {
		this.reversed_occurrences = reversed_occurrences;
	}

}
