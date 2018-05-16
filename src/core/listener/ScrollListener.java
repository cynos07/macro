package core.listener;

import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

import core.Recorder;
import model.Cmd;
import model.Command;

public class ScrollListener implements NativeMouseWheelListener{

	@Override
	public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
		Command command = new Command(Cmd.KEY_PRESS);
		command.setRotate(e.getWheelRotation());
		
		Recorder.write(command);
	}

}
