package core.listener;

import java.awt.event.KeyEvent;

import org.jnativehook.keyboard.SwingKeyAdapter;

import core.Recorder;
import model.Cmd;
import model.Command;

public class KeyListener extends SwingKeyAdapter{
	
	@Override
	public void keyPressed(KeyEvent keyEvent) {
		Command command = new Command(Cmd.KEY_PRESS);
		int keyCode = keyEvent.getKeyCode();
		command.setKeyCode(keyCode);
		
		Recorder.write(command);
		System.out.println("keyEvent: " + keyEvent);
		System.out.println("keyCode: " + keyCode);
		if(keyCode == KeyEvent.VK_F8) {
			Recorder.stop();
		}
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		Command command = new Command(Cmd.KEY_RELEASE);
		command.setKeyCode(keyEvent.getKeyCode());
		
		Recorder.write(command);
	}
}
