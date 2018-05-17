package core;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jnativehook.keyboard.SwingKeyAdapter;

import com.google.gson.Gson;
import com.sun.javafx.robot.FXRobot;

import model.Cmd;
import model.Command;

public class Player implements Runnable {
	private File file;
	private int loop;

	public Player(File file, int loop) {
		this.file = file;
		this.loop = loop;
	}

	@Override
	public void run() {
		Gson gson = new Gson();
		try {
			Robot robot = new Robot();
			
			for(int i=loop; i>0; i--) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String input = reader.readLine();
				while (input != null) {
					Command command = gson.fromJson(input, Command.class);

					Cmd cmd = command.getCmd();
					switch (cmd) {
					case KEY_PRESS:
						int keyCode = command.getKeyCode();
						if(keyCode == 157)
							keyCode = 524;
						System.out.println(keyCode);
						robot.keyPress(keyCode);
						break;
					case KEY_RELEASE:
						int keyCode2 = command.getKeyCode();
						if(keyCode2 == 157)
							keyCode2 = 524;
						System.out.println(keyCode2);
						robot.keyRelease(keyCode2);
						break;
					case MOUSE_MOVE:
						robot.mouseMove(command.getX(), command.getY());
						break;
					case MOUSE_PRESS:
						System.out.println("MOUSE_PRESS: " + command.getButton());
						System.out.println("MOUSE_PRESS_MASKED: " + InputEvent.getMaskForButton(command.getButton()));
						robot.mousePress(InputEvent.getMaskForButton(command.getButton()));
						break;
					case MOUSE_RELEASE:
						System.out.println("MOUSE_RELEASE: " + command.getButton());
						robot.mouseRelease(InputEvent.getMaskForButton(command.getButton()));
						break;
					case MOUSE_WHEEL:
						robot.mouseWheel(command.getRotate());
						break;
					case WAIT:
						robot.delay(Math.abs(command.getTime()));
						break;
					}
					input = reader.readLine();
				}
				reader.close();
			}
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
