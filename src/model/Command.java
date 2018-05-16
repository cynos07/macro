package model;

public class Command {
	private Cmd cmd;

	//Keyboard Listener
	private long keyCode; // KEY LISTENER
	
	//Scroll Listener
	private long rotate;
	
	//Mouse Listener
	private int button;
	private double x;
	private double y;
	
	//Wait
	private long time; // WAIT

	public Command(Cmd cmd) {
		this.cmd = cmd;
	}
	
	public long getRotate() {
		return rotate;
	}
	public void setRotate(long rotate) {
		this.rotate = rotate;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}

	public long getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(long keyCode) {
		this.keyCode = keyCode;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getButton() {
		return button;
	}

	public void setButton(int button) {
		this.button = button;
	}
	
	
}
