package model;

public class Command {
	private Cmd cmd;

	//Keyboard Listener
	private int keyCode; // KEY LISTENER
	
	//Scroll Listener
	private int rotate;
	
	//Mouse Listener
	private int button;
	private int x;
	private int y;
	
	
	//Wait
	private int time; // WAIT

	public Command(Cmd cmd) {
		this.cmd = cmd;
	}
	
	public Cmd getCmd() {
		return cmd;
	}
	
	public int getRotate() {
		return rotate;
	}
	public void setRotate(int rotate) {
		this.rotate = rotate;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getButton() {
		return button;
	}

	public void setButton(int button) {
		this.button = button;
	}
	
	
}
