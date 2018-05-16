package core.listener;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import core.Recorder;
import model.Cmd;
import model.Command;

public class KeyListener implements NativeKeyListener{

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		Command command = new Command(Cmd.KEY_PRESS);
		int keyCode = e.getKeyCode();
		command.setKeyCode(keyCode);
		
		Recorder.write(command);
		if(keyCode == NativeKeyEvent.VC_F8) {
			Recorder.stop();
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		Command command = new Command(Cmd.KEY_RELEASE);
		command.setKeyCode(e.getKeyCode());
		
		Recorder.write(command);
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		
	}

}
