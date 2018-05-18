package model;

public enum SceneUI {
	MAIN("/mainUI.fxml"), 
	Usage("/usage.fxml"), 
	PLAY("/play.fxml"), PREPARSE_PLAY("/beforePlay.fxml");
	
	final private String filename;

	SceneUI(String filename) {
		this.filename = filename;
	}
	
	public String getFilename() {
		return this.filename;
	}
}
