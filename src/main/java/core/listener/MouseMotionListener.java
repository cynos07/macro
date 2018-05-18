package core.listener;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseMotionListener;

import core.Recorder;
import model.Cmd;
import model.Command;

public class MouseMotionListener implements NativeMouseMotionListener{

	@Override
	public void nativeMouseMoved(NativeMouseEvent nativeEvent) {
		System.out.println(".");
		Command command = new Command(Cmd.MOUSE_MOVE);
		command.setX(nativeEvent.getX());
		command.setY(nativeEvent.getY());
		
		Recorder.write(command);
		
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent nativeEvent) {
		nativeMouseMoved(nativeEvent);
	}

}
