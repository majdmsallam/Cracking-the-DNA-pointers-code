package DNA_classes;

/**
 * @author MajdMsallam
 * This class represents the binding site structure.
 *
 */
public class BindingSite {
	private String chromosome;
	private String binding_site;
	private int start;
	private int end;
	private char reversed;

	public BindingSite(String chromosome, String binding_site, int start, int end, char reversed) {

		this.chromosome = chromosome;
		this.binding_site = binding_site;
		this.start = start;
		this.end = end;
		this.reversed = reversed;
	}

	public String getChromosome() {
		return chromosome;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public String getBinding_site() {
		return binding_site;
	}

	public void setBinding_site(String binding_site) {
		this.binding_site = binding_site;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public char getReversed() {
		return reversed;
	}

	public void setReversed(char reversed) {
		this.reversed = reversed;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return chromosome + " " + binding_site + " " + start + " " + end + " " + reversed;
	}

}
