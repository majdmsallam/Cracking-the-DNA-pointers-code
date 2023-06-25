//better
package algorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author MajdMsallam This class contains all the relative methods to run
 *         Algorithm D for DNA analysis
 *
 */
public class Algorithm_D {

	/**
	 * @param dna_seq
	 * @param PFM
	 * @param k
	 * @return HashMap<String, ArrayList<Number>> Functionality: this method builds
	 *         a PPM and then PWM from the PFM, and calculates the high score in the
	 *         PWM, after that it iterates through all the word with size equals to
	 *         k in the dna_seq using sliding window approach, moving one position
	 *         at a time, and if the score of the word is 0.9 * the high score and
	 *         the number of occurrences of the word before the binding site is
	 *         greater than after the binding site, the word would be a potential
	 *         pointer.
	 */
	public static HashMap<String, ArrayList<Number>> potential_PWM_pointers(String dna_seq,
			ArrayList<ArrayList<Double>> PFM, int k) {
		ArrayList<ArrayList<Double>> PPM = get_PPM(PFM);
		ArrayList<Object> objects = get_PWM_map_max_score(PPM, k);
		HashMap<Character, ArrayList<Double>> PWM_map = (HashMap<Character, ArrayList<Double>>) objects.get(0);
		double max_score = (double) objects.get(1);
		HashMap<String, ArrayList<Number>> pointers = new HashMap<String, ArrayList<Number>>();
		int middle = dna_seq.length() / 2;

		for (int i = 0; i < dna_seq.length() - k + 1; i++) {
			double score = 0;
			String st = dna_seq.substring(i, i + k);
			if (!pointers.containsKey(st)) {
				if (check_string(st)) {
					for (int j = 0; j < st.length(); j++) {

						ArrayList<Double> lst = PWM_map.get(st.charAt(j));
						score += lst.get(j);
					}

					if (score > max_score * 0.9) {
						if (i < middle) {
							ArrayList<Number> l = new ArrayList<Number>();
							l.add(score);
							l.add(1);
							l.add(0);
							pointers.put(st, l);
						} else {
							ArrayList<Number> l = new ArrayList<Number>();
							l.add(score);
							l.add(0);
							l.add(1);
							pointers.put(st, l);
						}

					}
				}
			} else {
				ArrayList<Number> lst = pointers.get(st);
				if (i < middle) {
					int val = (int) lst.get(1);
					lst.set(1, val + 1);
				} else {
					int val = (int) lst.get(2);
					lst.set(2, val + 1);
				}

			}
		}
		pointers = remove_pointers(pointers);
		return pointers;
	}

	/**
	 * @param str
	 * @return check_string Functionality: this method check if the DNA sequence
	 *         contains just A T C G nucleotide bases.
	 */
	private static boolean check_string(String str) {
		char[] charArr = str.toCharArray();
		for (char ch : charArr)
			if (!(ch == 'T' || ch == 'A' || ch == 'C' || ch == 'G'))
				return false;
		return true;
	}

	/**
	 * @param pointers
	 * @return HashMap<String, ArrayList<Number>> Functionality: this method
	 *         iterates through all the pointers and remove all the pointers that
	 *         appears more after the binding site.
	 */
	private static HashMap<String, ArrayList<Number>> remove_pointers(HashMap<String, ArrayList<Number>> pointers) {
		HashMap<String, ArrayList<Number>> final_pointers = new HashMap<String, ArrayList<Number>>();
		for (String str : pointers.keySet()) {
			ArrayList<Number> lst = pointers.get(str);
			if ((int) lst.get(1) > (int) lst.get(2))
				final_pointers.put(str, lst);
		}
		return final_pointers;
	}

	/**
	 * @param filePath
	 * @return ArrayList<ArrayList<Double>> Functionality: this opens a Position
	 *         Frequency Matrix file, reads it and save the values in a list of
	 *         lists.
	 */
	public static ArrayList<ArrayList<Double>> read_PFM_file(String filePath) {

		ArrayList<ArrayList<Double>> PFM = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			int lineCount = 0;

			while ((line = reader.readLine()) != null) {
				lineCount++;

				if (lineCount > 2) {
					String[] parts = line.trim().split("\t");
					ArrayList<Double> row = new ArrayList<>();

					for (int i = 1; i < parts.length; i++) {
						row.add(Double.parseDouble(parts[i]));
					}

					PFM.add(row);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return PFM;
	}

	/**
	 * @param PFM
	 * @return ArrayList<ArrayList<Double>> Functionality: this method calculate the
	 *         Position Probability Matrix using the Position Frequency Matrix.
	 */
	public static ArrayList<ArrayList<Double>> get_PPM(ArrayList<ArrayList<Double>> PFM) {
		ArrayList<ArrayList<Double>> PPM = new ArrayList<ArrayList<Double>>();
		for (ArrayList<Double> pos : PFM) {
			double temp = sum_pos(pos);
			ArrayList<Double> temp_list = new ArrayList<Double>();
			for (Double val : pos)
				temp_list.add(val / temp);
			PPM.add(temp_list);
		}
		return PPM;
	}

	/**
	 * @param pos
	 * @return double Functionality: calculates the sum for each position.
	 */
	private static double sum_pos(ArrayList<Double> pos) {
		double sum = 0;
		for (Double val : pos)
			sum += val;
		return sum;
	}

	/**
	 * @param PPM
	 * @param k
	 * @return ArrayList<Object> Functionality: calculate the Position Weight Matrix
	 *         and the max score.
	 */
	public static ArrayList<Object> get_PWM_map_max_score(ArrayList<ArrayList<Double>> PPM, int k) {
		ArrayList<Object> result = new ArrayList<Object>();
		ArrayList<ArrayList<Double>> PWM = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < PPM.size(); i++) {
			ArrayList<Double> lst = new ArrayList<Double>();
			for (int j = 0; j < PPM.get(0).size(); j++)
				lst.add(Math.log(PPM.get(i).get(j) / 0.25) / Math.log(2));
			PWM.add(lst);
		}
		HashMap<Character, ArrayList<Double>> PWM_map = new HashMap<Character, ArrayList<Double>>();
		for (int i = 0; i < PWM.get(0).size(); i++) {
			ArrayList<Double> lst = new ArrayList<Double>();
			char base = 'A';
			switch (i) {
			case 1:
				base = 'C';
				break;
			case 2:
				base = 'G';
				break;
			case 3:
				base = 'T';
				break;

			default:
				break;
			}

			for (int j = 0; j < PWM.size(); j++)
				lst.add(PWM.get(j).get(i));
			PWM_map.put(base, lst);
		}
		double score = 0;
		for (int i = 0; i < k; i++) {
			ArrayList<Double> pos = PWM.get(i);
			score += get_max(pos);
		}
		result.add(PWM_map);
		result.add(score);
		return result;

	}

	private static double get_max(ArrayList<Double> pos) {
		double max = pos.get(0);
		for (Double val : pos)
			if (val > max)
				max = val;

		return max;

	}

}
