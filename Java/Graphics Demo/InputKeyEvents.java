package javafirst;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputKeyEvents extends KeyAdapter {

	public void keyPressed(KeyEvent e) {
		if(Player.done) {
			Player.done = false;
			int keys = e.getKeyCode();
			
			if(keys == KeyEvent.VK_SPACE) {
			
				GraphicsDemo.isJumping = true;
				Bunchie.Jump(GraphicsDemo.b);
			}
		}
	}

}
