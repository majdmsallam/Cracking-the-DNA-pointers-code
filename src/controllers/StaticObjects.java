package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DNA_classes.AlgorithmAObject;
import DNA_classes.AlgorithmCObject;
import DNA_classes.BindingSite;
import DNA_classes.Seq_RevSeq_Occurrences;
import javafx.collections.ObservableList;

/**
 * @author MohamedAboSaleh This class contains all the static objects that are
 *         used in the system such as files paths and results collections.
 *
 */
public class StaticObjects {
	public static File binding_sites_file;
	public static File dna_sequence_file;
	public static File pfm_file;
	public static ArrayList<BindingSite> binding_sites;
	public static ArrayList<BindingSite> selected_binding_sites;
	public static String dna_sequence;
	public static ObservableList<BindingSite> observable_binding_sites;
	public static ObservableList<BindingSite> observable_selected_binding_sites;

	public static ArrayList<AlgorithmAObject> algorithm_A_results;
	public static List<Seq_RevSeq_Occurrences> algorithm_B_results;
	public static ArrayList<AlgorithmCObject> algorithm_C_results;
	public static HashMap<String, ArrayList<Number>> algorithm_D_results;

}
