package core.listener;

import java.awt.event.KeyEvent;

import org.jnativehook.keyboard.SwingKeyAdapter;

import core.Player;
import core.Recorder;
import model.Cmd;
import model.Command;

public class KeyListener extends SwingKeyAdapter{
	
	@Override
	public void keyPressed(KeyEvent keyEvent) {
		int keyCode = keyEvent.getKeyCode();
		if(keyCode == KeyEvent.VK_F8) {
			if(Recorder.isRun())
				Recorder.stop();
			else if(Player.isRun())
				Player.stop();
		}
		
		if(Recorder.isRun()) {
			Command command = new Command(Cmd.KEY_PRESS);
			command.setKeyCode(keyCode);
			Recorder.write(command);
		}
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		if(Recorder.isRun()) {
			Command command = new Command(Cmd.KEY_RELEASE);
			command.setKeyCode(keyEvent.getKeyCode());
			
			Recorder.write(command);
		}
	}
}
