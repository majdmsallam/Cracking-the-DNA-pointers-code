package DNA_classes;

import java.util.ArrayList;

/**
 * @author MajdMsallam This class represent the structure of the
 *         word_occurrences_DNA_analysis algorithm results.
 *
 */
public class Seq_RevSeq_Occurrences {
	private String seq;
	private ArrayList<Integer> occ_seq;
	private String rev_seq;
	private ArrayList<Integer> occ_rev_seq;

	public Seq_RevSeq_Occurrences(String seq, ArrayList<Integer> occ_seq, String rev_seq,
			ArrayList<Integer> occ_rev_seq) {
		// TODO Auto-generated constructor stub
		this.seq = seq;
		this.occ_seq = occ_seq;
		this.rev_seq = rev_seq;
		this.occ_rev_seq = occ_rev_seq;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public ArrayList<Integer> getOcc_seq() {
		return occ_seq;
	}

	public void setOcc_seq(ArrayList<Integer> occ_seq) {
		this.occ_seq = occ_seq;
	}

	public String getRev_seq() {
		return rev_seq;
	}

	public void setRev_seq(String rev_seq) {
		this.rev_seq = rev_seq;
	}

	public ArrayList<Integer> getOcc_rev_seq() {
		return occ_rev_seq;
	}

	public void setOcc_rev_seq(ArrayList<Integer> occ_rev_seq) {
		this.occ_rev_seq = occ_rev_seq;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return seq + " " + occ_seq.toString() + " " + rev_seq + " " + occ_rev_seq.toString();
	}
}
