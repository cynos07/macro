package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import view.MainUI;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
	@FXML
	private Button recordBtn;
	@FXML
	private Button playBtn;
//	@FXML
//	private Button exitBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		recordBtn.setOnAction(event -> handleRecord(event));
		playBtn.setOnAction(event -> handlePlay(event));
//		exitBtn.setOnAction(event -> handleExit(event));
	}

	private void handleRecord(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save files for recording");
		
		//Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Script files (*.script)", "*.script");
        fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(MainUI.primaryStage);

		if (file == null) {
			System.out.println("[WARN] File's not saved.");
		} else {
//			Record.play();
		}
	}

	private void handlePlay(ActionEvent event) {
		System.out.println("[INFO] Play button selected.");
	}

	private void handleExit(ActionEvent event) {
		System.exit(1);
	}

}