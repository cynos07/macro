package model;

public enum SceneUI {
	MAIN("/mainUI.fxml"), 
	RECORD("/record.fxml"), 
	PLAY("/play.fxml");
	
	final private String filename;

	SceneUI(String filename) {
		this.filename = filename;
	}
	
	public String getFilename() {
		return this.filename;
	}
}
