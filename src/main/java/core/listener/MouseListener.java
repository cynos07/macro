package core.listener;

import java.awt.event.MouseEvent;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.SwingMouseAdapter;

import core.Recorder;
import model.Cmd;
import model.Command;

public class MouseListener extends SwingMouseAdapter{

	@Override
	public void nativeMouseReleased(NativeMouseEvent nativeEvent) {
		mouseReleased(this.getJavaKeyEvent(nativeEvent));
	}
	
	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		Command command = new Command(Cmd.MOUSE_PRESS);
		command.setButton(mouseEvent.getButton());
		
		Recorder.write(command);
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		Command command = new Command(Cmd.MOUSE_RELEASE);
		command.setButton(mouseEvent.getButton());
		
		System.out.println("mouse Released!");
		Recorder.write(command);
	}
}
