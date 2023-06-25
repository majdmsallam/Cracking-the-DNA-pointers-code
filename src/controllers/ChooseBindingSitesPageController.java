package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DNA_classes.BindingSite;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author MajdMsallam this is a GUI controller for the "Choose Binding Sites
 *         Page"
 *
 *
 */
public class ChooseBindingSitesPageController implements Initializable {

	@FXML
	private TableView<BindingSite> binding_sites_table;

	@FXML
	private TableColumn<BindingSite, String> chromosome_col;

	@FXML
	private TableColumn<BindingSite, String> binding_site_col;

	@FXML
	private TableColumn<BindingSite, Integer> start_col;

	@FXML
	private TableColumn<BindingSite, Integer> end_col;

	@FXML
	private TableColumn<BindingSite, Character> revesed_col;

	@FXML
	private Button remove_binding_site_btn;

	@FXML
	private Button choose_algorithm_btn;

	@FXML
	private TableView<BindingSite> selected_binding_sites_table;

	@FXML
	private TableColumn<BindingSite, String> selected_chromosome_col;

	@FXML
	private TableColumn<BindingSite, String> selected_binding_site_col;

	@FXML
	private TableColumn<BindingSite, Integer> selected_start_col;

	@FXML
	private TableColumn<BindingSite, Integer> selected_end_col;

	@FXML
	private TableColumn<BindingSite, Character> selected_revesed_col;

	@FXML
	private Button select_binding_site_btn;

	@FXML
	private Button exit_btn;

	@FXML
	private Button add_all_btn;

	@FXML
	private Button remove_all_btn;

	@FXML
	private Button go_back_btn;

	/**
	 * @param event Functionality: This method loads the controller of the "Choose
	 *              Binding Sites & DNA Sequence Files Page"
	 */
	@FXML
	void go_back(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ChooseFilesPageController chooseFilesPageController = new ChooseFilesPageController();
		try {
			chooseFilesPageController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param event Functionality: This method is applied when the Add All button is
	 *              clicked, it removes all the binding sites from the binding_sites
	 *              list and adds all the binding sites to selected_binding_sites
	 *              list
	 */
	@FXML
	void add_all(ActionEvent event) {
		StaticObjects.selected_binding_sites.addAll(StaticObjects.binding_sites);
		StaticObjects.binding_sites = new ArrayList<BindingSite>();
		StaticObjects.observable_selected_binding_sites.addAll(StaticObjects.observable_binding_sites);
	}

	/**
	 * @param event Functionality: This method loads the controller of the "Select
	 *              Algorithm Page"
	 */
	@FXML
	void choose_algorithm(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		AlgorithmSelectionController algorithmSelectionController = new AlgorithmSelectionController();
		try {
			algorithmSelectionController.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void exit_system(ActionEvent event) {
		System.exit(0);
	}

	/**
	 * @param event Functionality: This method is applied when the Remove All button
	 *              is clicked, it removes all the binding sites from the
	 *              selected_binding_sites list and adds all the binding sites to
	 *              binding_sites list
	 */
	@FXML
	void remove_all(ActionEvent event) {
		StaticObjects.binding_sites.addAll(StaticObjects.selected_binding_sites);
		StaticObjects.selected_binding_sites = new ArrayList<BindingSite>();
		StaticObjects.observable_selected_binding_sites.removeAll(StaticObjects.binding_sites);
	}

	@FXML
	void remove_binding_site(ActionEvent event) {
		if (selected_binding_sites_table.getSelectionModel().getSelectedItem() == null) {
			// Show an error alert if no item is selected in the
			// selected_binding_sites_table
			Alert alert = new Alert(AlertType.ERROR, "Please, seclect Binding Site to remove", ButtonType.OK);
			alert.showAndWait();
		} else {
			remove_binding_site_btn.setDisable(true);
			BindingSite selected = selected_binding_sites_table.getSelectionModel().getSelectedItem();
			StaticObjects.selected_binding_sites.remove(selected);
			StaticObjects.binding_sites.add(selected);
			StaticObjects.observable_selected_binding_sites.remove(selected);
		}
	}

	@FXML
	void select_binding_site(ActionEvent event) {
		if (binding_sites_table.getSelectionModel().getSelectedItem() == null) {
			// Show an error alert if no item is selected in the binding_sites_table
			Alert alert = new Alert(AlertType.ERROR, "Please, seclect Binding Site", ButtonType.OK);
			alert.showAndWait();
		} else {
			BindingSite selected = binding_sites_table.getSelectionModel().getSelectedItem();
			StaticObjects.binding_sites.remove(selected);
			StaticObjects.selected_binding_sites.add(selected);
			StaticObjects.observable_selected_binding_sites.add(selected);
			select_binding_site_btn.setDisable(true);

		}
	}

	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/View/ChooseBindingSitesPage.fxml"));
		Scene scene = new Scene(root);

		primaryStage.setTitle("Choose Binding Sites Page");
		primaryStage.setScene(scene);

		primaryStage.centerOnScreen();

		primaryStage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if (StaticObjects.selected_binding_sites.size() != 0) {
			remove_all_btn.setDisable(false);
			choose_algorithm_btn.setDisable(false);
		}
		// Setting the binding sites table view values
		StaticObjects.observable_binding_sites = FXCollections.observableArrayList(StaticObjects.binding_sites);
		chromosome_col.setCellValueFactory(new PropertyValueFactory<BindingSite, String>("Chromosome"));
		binding_site_col.setCellValueFactory(new PropertyValueFactory<BindingSite, String>("Binding_site"));
		start_col.setCellValueFactory(new PropertyValueFactory<BindingSite, Integer>("Start"));
		end_col.setCellValueFactory(new PropertyValueFactory<BindingSite, Integer>("End"));
		revesed_col.setCellValueFactory(new PropertyValueFactory<BindingSite, Character>("Reversed"));
		binding_sites_table.setItems(StaticObjects.observable_binding_sites);

		// Setting the selected binding sites table view values
		StaticObjects.observable_selected_binding_sites = FXCollections
				.observableArrayList(StaticObjects.selected_binding_sites);
		selected_chromosome_col.setCellValueFactory(new PropertyValueFactory<BindingSite, String>("Chromosome"));
		selected_binding_site_col.setCellValueFactory(new PropertyValueFactory<BindingSite, String>("Binding_site"));
		selected_start_col.setCellValueFactory(new PropertyValueFactory<BindingSite, Integer>("Start"));
		selected_end_col.setCellValueFactory(new PropertyValueFactory<BindingSite, Integer>("End"));
		selected_revesed_col.setCellValueFactory(new PropertyValueFactory<BindingSite, Character>("Reversed"));
		selected_binding_sites_table.setItems(StaticObjects.observable_selected_binding_sites);
		// Add a listener to the observable_selected_binding_sites list
		StaticObjects.observable_selected_binding_sites.addListener(new ListChangeListener<BindingSite>() {
			@Override
			public void onChanged(Change<? extends BindingSite> arg0) {
				// Disable select_binding_site_btn and add_all_btn if the binding_sites list is
				// empty
				if (StaticObjects.binding_sites.size() == 0) {
					select_binding_site_btn.setDisable(true);
					add_all_btn.setDisable(true);
				} else {
					add_all_btn.setDisable(false);
				}
				// Disable choose_algorithm_btn, remove_binding_site_btn, and remove_all_btn if
				// the selected_binding_sites list is empty
				if (StaticObjects.selected_binding_sites.size() == 0) {
					choose_algorithm_btn.setDisable(true);
					remove_binding_site_btn.setDisable(true);
					remove_all_btn.setDisable(true);
				} else {
					choose_algorithm_btn.setDisable(false);
					remove_all_btn.setDisable(false);
				}
				initialize(null, null);

			}
		});

		binding_sites_table.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				// Check if an item is selected in the binding_sites_table
				if (binding_sites_table.getSelectionModel().getSelectedItem() != null) {
					// Enable select_binding_site_btn and disable remove_binding_site_btn
					select_binding_site_btn.setDisable(false);
					remove_binding_site_btn.setDisable(true);
				}
			}
		});
		selected_binding_sites_table.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				// Check if an item is selected in the selected_binding_sites_table
				if (selected_binding_sites_table.getSelectionModel().getSelectedItem() != null) {
					// Enable remove_binding_site_btn and disable select_binding_site_btn
					remove_binding_site_btn.setDisable(false);
					select_binding_site_btn.setDisable(true);
				}
			}
		});

	}

}
