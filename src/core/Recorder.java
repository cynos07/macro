package core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import core.listener.KeyListener;
import core.listener.MouseListener;
import core.listener.ScrollListener;
import model.Cmd;
import model.Command;

public class Recorder implements Runnable{
	private static boolean run = true;
	
	public static final int convertRate = 1000000;
	private static File file;
	private static long prevTime;
	private static BufferedWriter out;
	
	private static KeyListener keyListener;
	private static MouseListener mouseListener;
	private static ScrollListener scrollListener;
	
	public Recorder(File file) {
		this.file = file;
		prevTime = System.nanoTime();
	}
	

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
        	out = new BufferedWriter(new FileWriter(file));
    		
			GlobalScreen.registerNativeHook();

			keyListener = new KeyListener();
			mouseListener = new MouseListener();
			scrollListener = new ScrollListener();
			
			GlobalScreen.addNativeKeyListener(keyListener);
		    GlobalScreen.addNativeMouseListener(mouseListener);
		    GlobalScreen.addNativeMouseMotionListener(mouseListener);
		    GlobalScreen.addNativeMouseWheelListener(scrollListener);
			
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(run) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("run finished.");
	}
	
	public static void stop() {
		try {
			GlobalScreen.unregisterNativeHook();
			GlobalScreen.removeNativeKeyListener(keyListener);
		    GlobalScreen.removeNativeMouseListener(mouseListener);
		    GlobalScreen.removeNativeMouseMotionListener(mouseListener);
		    GlobalScreen.removeNativeMouseWheelListener(scrollListener);
		    
			out.close();
			
			System.out.println("run chaned to false");
			run = false;
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static int calculateTime() {
        long currentTime = System.nanoTime();
        long timeMs = Math.abs(currentTime-prevTime);
        prevTime = currentTime;

        return (int) (timeMs/convertRate);
	}

	public static void write(Command command) {
		try {
			Command waitCommand = new Command(Cmd.WAIT);
			waitCommand .setTime(calculateTime());
			out.write(CommandParser.getInstance().toString(waitCommand));
			out.newLine();
			
			out.write(CommandParser.getInstance().toString(command));
			out.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
