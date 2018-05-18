package controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javax.swing.SwingUtilities;
import core.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import model.SceneUI;
import view.MainUI;

public class PreparePlayController implements Initializable {
	@FXML
	private TextArea filePathDescript;
	@FXML
	private Button browseBtn;
	@FXML
	private TextArea loopNum;
	@FXML
	private TextArea intervalNum;
	@FXML
	private Button okBtn;
	
	@FXML
	private Label descript;
	
	private File file;
	
	private boolean isInputValid(String input) {
		int checkInput;
		try {
			checkInput = Integer.parseInt(input);
			if (checkInput <= 0 || checkInput > 9) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public void initialize() {
		loopNum.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	loopNum.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		intervalNum.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	intervalNum.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initialize();
		browseBtn.setOnAction(event -> handleBrowseBtn(event));
		okBtn.setOnAction(event -> handleOKBtn(event));
	}
	
	private void handleBrowseBtn(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open file for playing.");
		
		//Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Script files (*.script)", "*.script");
        fileChooser.getExtensionFilters().add(extFilter);
		file = fileChooser.showOpenDialog(MainUI.primaryStage);
		
		if(file!=null) {
			filePathDescript.setText(file.getAbsolutePath());
		} else {
			filePathDescript.setText("file does not selected.");
		}
	}
	
	private void handleOKBtn(ActionEvent event) {
		if(file == null) {
			descript.setText("You must select the script.");
		} else {
			try {
				int loop = Integer.parseInt(loopNum.getText());
				int interval = Integer.parseInt(intervalNum.getText());
				MainUI.iconified(true);
				SwingUtilities.invokeAndWait(new Player(file, loop, interval));
				System.out.println("Player has benn finish.");
				MainUI.iconified(false);
			} catch (NumberFormatException e) {
				descript.setText("Please enter only number on loop or interval box.");
			} catch (InvocationTargetException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
