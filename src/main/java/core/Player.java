package core;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import com.google.gson.Gson;

import core.listener.KeyListener;
import core.listener.MouseListener;
import core.listener.MouseMotionListener;
import core.listener.ScrollListener;
import model.Cmd;
import model.Command;

public class Player implements Runnable {
	private File file;
	private int loop;
	
	private static boolean run = false;
	private static KeyListener keyListener;

	public Player(File file, int loop) {
		this.file = file;
		this.loop = loop;
	}

	@Override
	public void run() {
		
		run = true;
		Gson gson = new Gson();
		try {
			Robot robot = new Robot();
			

			GlobalScreen.registerNativeHook();

			keyListener = new KeyListener();
			GlobalScreen.addNativeKeyListener(keyListener);
			for(int i=loop; i>0; i--) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String input = reader.readLine();
				while (input != null && run) {
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
						robot.mousePress(command.getButton());
						break;
					case MOUSE_RELEASE:
						robot.mouseRelease(command.getButton());
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
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void stop() {
		try {
			GlobalScreen.unregisterNativeHook();
			GlobalScreen.removeNativeKeyListener(keyListener);
			Player.run = false;
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isRun() {
		return run;
	}

}
