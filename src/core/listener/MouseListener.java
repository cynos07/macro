package core.listener;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import core.Recorder;
import model.Cmd;
import model.Command;

public class MouseListener implements NativeMouseInputListener{

	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {
		
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent e) {
		Command command = new Command(Cmd.MOUSE_PRESS);
		command.setButton(e.getButton());
		
		Recorder.write(command);
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {
		Command command = new Command(Cmd.MOUSE_RELEASE);
		command.setButton(e.getButton());
		
		Recorder.write(command);
		
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent e) {
		Command command = new Command(Cmd.MOUSE_MOVE);
		command.setX(e.getX());
		command.setY(e.getY());
		
		Recorder.write(command);
		
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
