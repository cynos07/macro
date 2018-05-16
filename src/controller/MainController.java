package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import model.SceneUI;
import view.MainUI;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.SwingUtilities;

import core.Player;
import core.Recorder;

public class MainController implements Initializable {
	@FXML
	private Button recordBtn;
	@FXML
	private Button playBtn;
	@FXML
	private MenuItem usageMenu;
	
//	@FXML
//	private Button exitBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		usageMenu.setOnAction(event -> handleUsage(event));
		recordBtn.setOnAction(event -> handleRecord(event));
		playBtn.setOnAction(event -> handlePlay(event));
//		exitBtn.setOnAction(event -> handleExit(event));
	}
	
	private void handleUsage(ActionEvent event) {
		try {
			MainUI.popup("Usage", SceneUI.Usage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void handleRecord(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save file for recording");
		
		//Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Script files (*.script)", "*.script");
        fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(MainUI.primaryStage);

		if (file == null) {
			System.out.println("[WARN] File's not saved.");
		} else {
			try {
				MainUI.iconified(true);
				SwingUtilities.invokeAndWait(new Recorder(file));
				System.out.println("Recorder has benn finish.");
				MainUI.iconified(false);
			} catch (InvocationTargetException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void handlePlay(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open file for playing.");
		
		//Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Script files (*.script)", "*.script");
        fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(MainUI.primaryStage);
		
		if(file == null) {
			System.out.println("[WARN] File's not opened.");
		} else {
			try {
				int loop = 1;
				MainUI.iconified(true);
				SwingUtilities.invokeAndWait(new Player(file, loop));
				System.out.println("Player has benn finish.");
				MainUI.iconified(false);
			} catch (InvocationTargetException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void handleExit(ActionEvent event) {
		System.exit(1);
	}

}