package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DNA_classes.BindingSite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author MohamedAboSaleh this is a GUI controller for the "Choose Binding
 *         Sites & DNA Sequence Files Page"
 *
 */
public class ChooseFilesPageController implements Initializable {

	@FXML
	private Button select_BS_file_btn;

	@FXML
	private Button next_page_btn;

	@FXML
	private TextField binding_sites_file_path_txt_field;

	@FXML
	private Button exit_btn;

	@FXML
	private Button select_DNA_sequence_file_btn;

	@FXML
	private TextField DNA_sequecne_file_path_txt_field;

	@FXML
	void exit_system(ActionEvent event) {
		System.exit(0);
	}

	/**
	 * @param event Functionality: This method loads the controller of the "Choose
	 *              Binding Sites Page"
	 */
	@FXML
	void go_to_next_page(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ChooseBindingSitesPageController bindingSitesPageController = new ChooseBindingSitesPageController();
		try {
			bindingSitesPageController.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param event Functionality: This method opens file dialog to choose binding
	 *              sites file
	 */
	@FXML
	void select_protein_binding_site_file(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		StaticObjects.binding_sites_file = fileChooser.showOpenDialog(stage);
		if (StaticObjects.binding_sites_file != null && StaticObjects.binding_sites_file.exists()
				&& !StaticObjects.binding_sites_file.isDirectory()) {
			read_binding_sites_file();
			if (StaticObjects.dna_sequence != null)
				next_page_btn.setDisable(false);
		}

	}

	/**
	 * Functionality: This method reads the chosen binding sites file
	 */
	private void read_binding_sites_file() {
		StaticObjects.binding_sites = new ArrayList<BindingSite>();
		StaticObjects.selected_binding_sites = new ArrayList<BindingSite>();
		binding_sites_file_path_txt_field.setText(StaticObjects.binding_sites_file.getAbsolutePath());
		try (BufferedReader br = new BufferedReader(
				new FileReader(StaticObjects.binding_sites_file.getAbsolutePath()))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.trim().split(" ");
				BindingSite bindingsite = new BindingSite(parts[0], parts[1], Integer.parseInt(parts[2]),
						Integer.parseInt(parts[3]), parts[4].charAt(0));
				StaticObjects.binding_sites.add(bindingsite);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * @param event Functionality: This method opens file dialog to choose DNA
	 *              sequence file
	 */
	@FXML
	void select_DNA_sequence_file(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fasta Files", "*.fasta"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		StaticObjects.dna_sequence_file = fileChooser.showOpenDialog(stage);
		if (StaticObjects.dna_sequence_file != null && StaticObjects.dna_sequence_file.exists()
				&& !StaticObjects.dna_sequence_file.isDirectory()) {
			read_dna_file();
			if (StaticObjects.binding_sites != null)
				next_page_btn.setDisable(false);
		}
	}

	/**
	 * Functionality: This method reads the chosen DNA file
	 */
	private void read_dna_file() {
		StaticObjects.dna_sequence = "";
		DNA_sequecne_file_path_txt_field.setText(StaticObjects.dna_sequence_file.getAbsolutePath());
		try (BufferedReader br = new BufferedReader(
				new FileReader(StaticObjects.dna_sequence_file.getAbsoluteFile()))) {
			br.readLine(); // Skip the first line
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				StaticObjects.dna_sequence += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/View/ChooseFilesPage.fxml"));
		Scene scene = new Scene(root);

		primaryStage.setTitle("Choose Binding Sites & DNA Sequence Files Page");
		primaryStage.setScene(scene);

		primaryStage.centerOnScreen();

		primaryStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if (StaticObjects.binding_sites_file != null) {
			binding_sites_file_path_txt_field.setText(StaticObjects.binding_sites_file.getAbsolutePath());
			DNA_sequecne_file_path_txt_field.setText(StaticObjects.dna_sequence_file.getAbsolutePath());
			next_page_btn.setDisable(false);
		}
	}

}
