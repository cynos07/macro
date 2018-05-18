package view;

import java.io.File;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.SceneUI;

public class MainUI extends Application {
	private static Group root = new Group();
	public static Stage primaryStage;

	public Parent createContent() {
		go(SceneUI.MAIN);
		return root;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainUI.primaryStage = primaryStage;
		primaryStage.setTitle("I wanna use macro!");
		primaryStage.setResizable(false);
		Scene scene = new Scene(createContent());

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void go(SceneUI ui) {
		try {
			replaceSceneContent(ui.getFilename());
			// MainController main = (MainController)replaceSceneContent("/Main.fxml");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void iconified(boolean isIconified) {
		for(Stage stage : com.sun.javafx.stage.StageHelper.getStages()) {
			System.out.println("stage.. changed to " + isIconified);
			stage.setIconified(isIconified);
		}
	}

	public static void popup(String title, SceneUI scene) throws Exception {
		Stage stage = new Stage();
		stage.setScene(new Scene(FXMLLoader.load(MainUI.class.getResource(scene.getFilename()))));
		stage.setTitle(title);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}

	private static Initializable replaceSceneContent(String fxml) throws Exception {
		FXMLLoader loader = new FXMLLoader(MainUI.class.getResource(fxml));
		Pane page = loader.load();
		root.getChildren().clear();
		root.getChildren().addAll(page);
		return (Initializable) loader.getController();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
