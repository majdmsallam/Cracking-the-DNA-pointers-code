package controllers;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		ChooseFilesPageController chooseFilesPageController = new ChooseFilesPageController();
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		chooseFilesPageController.start(primaryStage);

	}

}
