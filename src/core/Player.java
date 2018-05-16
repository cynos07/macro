package core;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jnativehook.keyboard.SwingKeyAdapter;

import com.google.gson.Gson;
import com.sun.glass.events.KeyEvent;
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
						System.out.println(command.getKeyCode());
						robot.keyPress(command.getKeyCode());
					case KEY_RELEASE:
						robot.keyRelease(command.getKeyCode());
					case MOUSE_MOVE:
						robot.mouseMove(command.getX(), command.getY());
					case MOUSE_PRESS:
						robot.mousePress(command.getButton());
					case MOUSE_RELEASE:
						robot.mouseRelease(command.getButton());
					case MOUSE_WHEEL:
						robot.mouseWheel(command.getRotate());
					case WAIT:
						robot.delay(Math.abs(command.getTime()));
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
