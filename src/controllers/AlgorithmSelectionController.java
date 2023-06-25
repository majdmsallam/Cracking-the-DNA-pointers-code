package controllers;

import java.io.BufferedWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import DNA_classes.AlgorithmAObject;
import DNA_classes.AlgorithmCObject;
import DNA_classes.BindingSite;
import DNA_classes.Seq_RevSeq_Occurrences;
import algorithms.Algorithm_A;
import algorithms.Algorithm_B;
import algorithms.Algorithm_C;
import algorithms.Algorithm_D;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author MohamedAboSaleh this is a GUI controller for the "Select Algorithm
 *         Page"
 *
 */
public class AlgorithmSelectionController implements Initializable {
	private ArrayList<Integer> combo_values = new ArrayList<Integer>();
	private ArrayList<Integer> segments = new ArrayList<Integer>();
	@FXML
	private Button exit_btn;

	@FXML
	private Button run_system_btn;

	@FXML
	private RadioButton algorithm_A_radio_btn;

	@FXML
	private ToggleGroup algorithms_radio_btns;

	@FXML
	private RadioButton algorithm_B_radio_btn;

	@FXML
	private RadioButton algorithm_C_radio_btn;

	@FXML
	private RadioButton algorithm_D_radio_btn;

	@FXML
	private Button upload_pfm_file_btn;

	@FXML
	private TextField pfm_file_path;

	@FXML
	private ComboBox<Integer> word_size_combo_box;

	@FXML
	private Label select_k_label;

	@FXML
	private Button go_back_btn;

	@FXML
	private Label algorithm_A_description;

	@FXML
	private Label general_description;

	@FXML
	private Label algorithm_C_description;

	@FXML
	private Label algorithm_B_description;

	@FXML
	private Label algorithm_D_description;

	@FXML
	private Label select_num_of_segments;

	@FXML
	private ComboBox<Integer> number_of_segments_combo_box;

	/**
	 * @param event
	 * Functionality: This method load the word size combo box values based on Algorithm A,
	 * and removes some components.
	 */
	@FXML
	void algorithm_A_seleccted(ActionEvent event) {

		remove_descriptions();
		combo_values = new ArrayList<Integer>();
		for (int i = 5; i <= 10; i++)
			combo_values.add(i);
		algorithm_A_description.setVisible(true);
		select_k_label.setVisible(true);
		word_size_combo_box.setVisible(true);
		initialize(null, null);
	}

	/**
	 * @param event
	 * Functionality: This method load the word size combo box values based on Algorithm B,
	 * and removes some components.
	 */
	@FXML
	void algorithm_B_seleccted(ActionEvent event) {
		remove_descriptions();
		combo_values = new ArrayList<Integer>();
		for (int i = 5; i <= 10; i++)
			combo_values.add(i);
		algorithm_B_description.setVisible(true);
		select_k_label.setVisible(true);
		word_size_combo_box.setVisible(true);
		initialize(null, null);
	}

	/**
	 * @param event
	 * Functionality: This method load the word size combo box values based on Algorithm C,
	 *  and loads the number of segments combo values ,and removes some components.
	 */
	@FXML
	void algorithm_C_seleccted(ActionEvent event) {
		remove_descriptions();
		combo_values = new ArrayList<Integer>();
		for (int i = 5; i <= 10; i++)
			combo_values.add(i);
		if (segments.size() == 0)
			for (int i = 4; i <= 10; i += 2)
				segments.add(i);
		number_of_segments_combo_box.setVisible(true);
		select_num_of_segments.setVisible(true);
		algorithm_C_description.setVisible(true);
		select_k_label.setVisible(true);
		word_size_combo_box.setVisible(true);

		initialize(null, null);
	}

	/**
	 * @param event
	 * Functionality: This method load the word size combo box values based on Algorithm D,
	 * and removes some components.
	 */
	@FXML
	void algorithm_D_seleccted(ActionEvent event) {
		pfm_file_path.setText("");
		remove_descriptions();
		combo_values = new ArrayList<Integer>();
		for (int i = 5; i <= 14; i++)
			combo_values.add(i);
		algorithm_D_description.setVisible(true);
		select_k_label.setVisible(true);
		word_size_combo_box.setVisible(true);
		upload_pfm_file_btn.setVisible(true);
		pfm_file_path.setVisible(true);
		initialize(null, null);
	}

	@FXML
	void exit_system(ActionEvent event) {
		System.exit(0);
	}

	/**
	 * @param event Functionality: This method loads the controller of the "Choose
	 *              Binding Sites Page"
	 */
	@FXML
	void go_back(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ChooseBindingSitesPageController bindingSitesPageController = new ChooseBindingSitesPageController();
		try {
			bindingSitesPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param event
	 * Functionality: call the method relevant to the chosen algorithm
	 */
	@FXML
	void run_system(ActionEvent event) {
		RadioButton selected_radio_btn = (RadioButton) algorithms_radio_btns.getSelectedToggle();
		if (selected_radio_btn.equals(algorithm_A_radio_btn)) {
			runAlgorithmA();
		} else if (selected_radio_btn.equals(algorithm_B_radio_btn)) {
			runAlgorithmB();
		} else if (selected_radio_btn.equals(algorithm_C_radio_btn)) {
			runAlgorithmC();
		} else if (selected_radio_btn.equals(algorithm_D_radio_btn)) {
			runAlgorithmD();
		}

	}

	private void runAlgorithmA() {
		if (word_size_combo_box.getSelectionModel().getSelectedItem() == null) {
			// Display an error message if word size is not selected
			Alert alert = new Alert(AlertType.ERROR, "Please, select word size", ButtonType.OK);
			alert.showAndWait();
		} else {
			// Get the selected results file
			ArrayList<Object> arrlist = choose_results_file();
			if (arrlist != null) {
				File newFile = (File) arrlist.get(0);
				boolean canRun = (boolean) arrlist.get(1);
				if (canRun) {
					// Create a new thread to run the algorithm
					Thread thread = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							int word_size = word_size_combo_box.getSelectionModel().getSelectedItem();
							FileWriter fileWriter;
							try {
								chage_state(true);
								// Open the results file for writing
								fileWriter = new FileWriter(newFile, false);
								BufferedWriter writer = new BufferedWriter(fileWriter);
								writer.write(((RadioButton) algorithms_radio_btns.getSelectedToggle()).getText());
								writer.newLine();
								writer.write("Results for word size = " + word_size + ":");
								writer.newLine();
								int start;
								int end;
								// Process each selected binding site
								for (BindingSite bindingSite : StaticObjects.selected_binding_sites) {
									writer.newLine();
									writer.write(bindingSite.toString());
									writer.newLine();
									if (bindingSite.getStart() < 1500000) {

										start = 0;
										end = bindingSite.getEnd() + bindingSite.getStart();
									} else if (bindingSite.getEnd() + 1500000 > StaticObjects.dna_sequence.length()) {
										start = bindingSite.getStart()
												- (StaticObjects.dna_sequence.length() - bindingSite.getEnd());
										end = StaticObjects.dna_sequence.length();
									} else {
										start = bindingSite.getStart() - 1500000;
										end = bindingSite.getEnd() + 1500000;
									}
									// Run Algorithm A on the DNA sequence
									StaticObjects.algorithm_A_results = Algorithm_A.segmented_dna_analysis(
											StaticObjects.dna_sequence.substring(start, end), word_size);
									for (AlgorithmAObject obj : StaticObjects.algorithm_A_results) {
										writer.write(obj.toString());
									}

								}
								writer.close();

								pfm_file_path.setDisable(false);
								upload_pfm_file_btn.setDisable(false);
								chage_state(false);
								openFile(newFile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

					});
					thread.start();

				}

			}
		}
	}

	private void runAlgorithmB() {
		if (word_size_combo_box.getSelectionModel().getSelectedItem() == null) {
			// Display an error message if word size is not selected
			Alert alert = new Alert(AlertType.ERROR, "Please, select word size", ButtonType.OK);
			alert.showAndWait();
		} else {
			// Get the selected results file
			ArrayList<Object> arrlist = choose_results_file();
			if (arrlist != null) {
				File newFile = (File) arrlist.get(0);
				boolean canRun = (boolean) arrlist.get(1);
				if (canRun) {
					// Create a new thread to run the algorithm
					Thread thread = new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							int word_size = word_size_combo_box.getSelectionModel().getSelectedItem();
							FileWriter fileWriter;
							try {
								chage_state(true);
								// Open the results file for writing
								fileWriter = new FileWriter(newFile, false);
								BufferedWriter writer = new BufferedWriter(fileWriter);
								writer.write(((RadioButton) algorithms_radio_btns.getSelectedToggle()).getText());
								writer.newLine();
								writer.write("Results for word size = " + word_size + ":");
								writer.newLine();
								int start;
								int end;
								// Process each selected binding site
								for (BindingSite bindingSite : StaticObjects.selected_binding_sites) {
									writer.newLine();
									writer.write(bindingSite.toString());
									writer.newLine();
									if (bindingSite.getStart() < 1500000) {

										start = 0;
										end = bindingSite.getEnd() + bindingSite.getStart();
									} else if (bindingSite.getEnd() + 1500000 > StaticObjects.dna_sequence.length()) {
										start = bindingSite.getStart()
												- (StaticObjects.dna_sequence.length() - bindingSite.getEnd());
										end = StaticObjects.dna_sequence.length();
									} else {
										start = bindingSite.getStart() - 1500000;
										end = bindingSite.getEnd() + 1500000;
									}
									// Run Algorithm B on the DNA sequence
									StaticObjects.algorithm_B_results = Algorithm_B.word_occurrences_DNA_analysis(
											StaticObjects.dna_sequence.substring(start, end), word_size);
									for (Seq_RevSeq_Occurrences result : StaticObjects.algorithm_B_results) {
										writer.write(result.toString());
										writer.newLine();
									}
									writer.newLine();
									writer.newLine();
								}
								writer.close();

								pfm_file_path.setDisable(false);
								upload_pfm_file_btn.setDisable(false);
								chage_state(false);
								openFile(newFile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					});
					thread.start();

				}

			}
		}

	}

	private void runAlgorithmC() {
		if (word_size_combo_box.getSelectionModel().getSelectedItem() == null
				|| number_of_segments_combo_box.getSelectionModel().getSelectedItem() == null) {
			// Display an error message if word size or number of segments is not selected
			Alert alert = new Alert(AlertType.ERROR, "Please, select word size and number of segments", ButtonType.OK);
			alert.showAndWait();
		} else {
			// Get the selected results file
			ArrayList<Object> arrlist = choose_results_file();
			if (arrlist != null) {
				File newFile = (File) arrlist.get(0);
				boolean canRun = (boolean) arrlist.get(1);
				if (canRun) {
					// Create a new thread to run the algorithm
					Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {
							// Get the selected word size and number of segments
							int word_size = word_size_combo_box.getSelectionModel().getSelectedItem();
							int number_of_segemenst = number_of_segments_combo_box.getSelectionModel()
									.getSelectedItem();
							FileWriter fileWriter;
							try {
								chage_state(true);
								// Open the results file for writing
								fileWriter = new FileWriter(newFile, false);
								BufferedWriter writer = new BufferedWriter(fileWriter);
								// Write the selected algorithm to the file
								writer.write(((RadioButton) algorithms_radio_btns.getSelectedToggle()).getText());
								writer.newLine();
								writer.write("Results for word size = " + word_size + ", and number of segments = "
										+ number_of_segemenst + ":");
								writer.newLine();
								int start;
								int end;
								// Process each selected binding site
								for (BindingSite bindingSite : StaticObjects.selected_binding_sites) {
									writer.newLine();
									writer.write(bindingSite.toString());
									writer.newLine();
									if (bindingSite.getStart() < 1500000) {

										start = 0;
										end = bindingSite.getEnd() + bindingSite.getStart();
									} else if (bindingSite.getEnd() + 1500000 > StaticObjects.dna_sequence.length()) {
										start = bindingSite.getStart()
												- (StaticObjects.dna_sequence.length() - bindingSite.getEnd());
										end = StaticObjects.dna_sequence.length();
									} else {
										start = bindingSite.getStart() - 1500000;
										end = bindingSite.getEnd() + 1500000;
									}
									// Run Algorithm C on the DNA sequence
									StaticObjects.algorithm_C_results = Algorithm_C
											.segmented_word_distribution_analysis(
													StaticObjects.dna_sequence.substring(start, end),
													number_of_segemenst, word_size);
									for (AlgorithmCObject obj : StaticObjects.algorithm_C_results) {
										writer.write(obj.toString());
										writer.newLine();
									}
									writer.newLine();
									writer.newLine();
								}
								writer.close();

								pfm_file_path.setDisable(false);
								upload_pfm_file_btn.setDisable(false);
								chage_state(false);
								openFile(newFile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					});
					thread.start();

				}

			}
		}

	}


	private void runAlgorithmD() {
		if (word_size_combo_box.getSelectionModel().getSelectedItem() == null || StaticObjects.pfm_file == null) {
			// Display an error message if word size or PFM file is not selected
			Alert alert = new Alert(AlertType.ERROR, "Please, select word size and upload the PFM file", ButtonType.OK);
			alert.showAndWait();
		} else {
			// Choose results file
			ArrayList<Object> arrlist = choose_results_file();
			if (arrlist != null) {
				File newFile = (File) arrlist.get(0);
				boolean canRun = (boolean) arrlist.get(1);
				if (canRun) {
					// Create a new thread to run the algorithm
					Thread thread = new Thread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							int word_size = word_size_combo_box.getSelectionModel().getSelectedItem();
							FileWriter fileWriter;
							try {
								// Open the results file for writing
								fileWriter = new FileWriter(newFile, false);
								BufferedWriter writer = new BufferedWriter(fileWriter);
								// Write the selected algorithm to the file
								writer.write(((RadioButton) algorithms_radio_btns.getSelectedToggle()).getText());
								writer.newLine();
								pfm_file_path.setDisable(true);
								upload_pfm_file_btn.setDisable(true);
								chage_state(true);
								// Read PFM file and calculate PPM
								ArrayList<ArrayList<Double>> PFM = Algorithm_D
										.read_PFM_file(StaticObjects.pfm_file.getAbsolutePath());
								ArrayList<ArrayList<Double>> PPM = Algorithm_D.get_PPM(PFM);
								 // Calculate PWM map and max score
								ArrayList<Object> objects = Algorithm_D.get_PWM_map_max_score(PPM, word_size);
								double max_score = (double) objects.get(1);
								writer.write("Max Score (word size = " + word_size + "): " + max_score);
								writer.newLine();
								int start;
								int end;
								// Process each selected binding site

								for (BindingSite bindingSite : StaticObjects.selected_binding_sites) {
									writer.newLine();
									writer.write(bindingSite.toString());
									writer.newLine();
									if (bindingSite.getStart() < 1500000) {

										start = 0;
										end = bindingSite.getEnd() + bindingSite.getStart();
									} else if (bindingSite.getEnd() + 1500000 > StaticObjects.dna_sequence.length()) {
										start = bindingSite.getStart()
												- (StaticObjects.dna_sequence.length() - bindingSite.getEnd());
										end = StaticObjects.dna_sequence.length();
									} else {
										start = bindingSite.getStart() - 1500000;
										end = bindingSite.getEnd() + 1500000;
									}
									// Run Algorithm D on the DNA sequence
									StaticObjects.algorithm_D_results = Algorithm_D.potential_PWM_pointers(
											StaticObjects.dna_sequence.substring(start, end), PFM, word_size);
									for (String str : StaticObjects.algorithm_D_results.keySet()) {
										ArrayList<Number> arrlist = StaticObjects.algorithm_D_results.get(str);
										writer.write(str + "=> Score: " + arrlist.get(0) + ", downstream: "
												+ arrlist.get(1) + ", upstream: " + arrlist.get(2));
										writer.newLine();
									}

								}
								writer.close();

								pfm_file_path.setDisable(false);
								upload_pfm_file_btn.setDisable(false);
								chage_state(false);
								openFile(newFile);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					});
					thread.start();

				}

			}
		}
	}

	/**
	 * @return ArrayList<Object> Functionality: This method opens file dialog to
	 *         create new file with a given name from the user
	 */
	private ArrayList<Object> choose_results_file() {
		boolean canRun = false;
		JFileChooser fileChooser = new JFileChooser();
		ArrayList<Object> arrayList = new ArrayList<Object>();
		int returnValue = fileChooser.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String filePath = selectedFile.getAbsolutePath();

			// Add .txt extension to the file name
			if (!filePath.toLowerCase().endsWith(".txt")) {
				filePath += ".txt";
			}

			// Create and save the file
			File newFile = new File(filePath);
			if (newFile.exists()) {
				Alert alert = new Alert(AlertType.WARNING,
						newFile.getName() + " alreasy exists.\n Do you want to replace it?", ButtonType.YES,
						ButtonType.NO);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.YES) {
					boolean fileDeleted = newFile.delete();
					if (fileDeleted) {
						canRun = true;
						System.out.println("Existing file deleted successfully.");
					} else {
						System.out.println("Failed to delete the existing file.");
					}
				}

			}
			try {
				boolean fileCreated = newFile.createNewFile();
				if (fileCreated) {
					canRun = true;
					System.out.println("Text file created successfully.");
				} else {
					System.out.println("Text file creation failed.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			arrayList.add(newFile);
			arrayList.add(canRun);
			return arrayList;
		}
		return null;
	}

	/**
	 * @param flag Functionality: Enable or Disable unnecessary components
	 */
	private void chage_state(boolean flag) {
		algorithm_A_radio_btn.setDisable(flag);
		algorithm_B_radio_btn.setDisable(flag);
		algorithm_C_radio_btn.setDisable(flag);
		algorithm_D_radio_btn.setDisable(flag);
		go_back_btn.setDisable(flag);
		run_system_btn.setDisable(flag);
		word_size_combo_box.setDisable(flag);
		number_of_segments_combo_box.setDisable(flag);

	}

	/**
	 * @param event Functionality: This method opens file dialog to choose PFM file
	 */
	@FXML
	void upload_pfm_file(ActionEvent event) {
		File file = null;
		if (StaticObjects.pfm_file != null)
			file = StaticObjects.pfm_file;
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		StaticObjects.pfm_file = fileChooser.showOpenDialog(stage);
		if (StaticObjects.pfm_file != null && StaticObjects.pfm_file.exists() && !StaticObjects.pfm_file.isDirectory())
			pfm_file_path.setText(StaticObjects.pfm_file.getAbsolutePath());
		else if (file != null)
			StaticObjects.pfm_file = file;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		StaticObjects.pfm_file = null;
		// set the combo boxes values
		word_size_combo_box.getItems().clear();
		word_size_combo_box.getItems().addAll(combo_values);
		number_of_segments_combo_box.getItems().clear();
		number_of_segments_combo_box.getItems().addAll(segments);
		algorithms_radio_btns.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
			run_system_btn.setDisable(false);

		});

	}

	private void openFile(File file) {
		if (Desktop.isDesktopSupported()) {// check if the desktop is supported
			try {
				Desktop.getDesktop().open(file);// open the file
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
				alert.showAndWait();
			}
		}
	}

	/**
	 * Functionality: This method sets the unnecessary components to be invisible
	 */
	private void remove_descriptions() {

		algorithm_A_description.setVisible(false);
		algorithm_B_description.setVisible(false);
		algorithm_C_description.setVisible(false);
		algorithm_D_description.setVisible(false);
		general_description.setVisible(false);
		upload_pfm_file_btn.setVisible(false);
		pfm_file_path.setVisible(false);
		select_num_of_segments.setVisible(false);
		number_of_segments_combo_box.setVisible(false);
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/View/Algorithms selection.fxml"));
		Scene scene = new Scene(root);

		primaryStage.setTitle("Select Algorithm Page");
		primaryStage.setScene(scene);

		primaryStage.centerOnScreen();

		primaryStage.show();
	}

}
