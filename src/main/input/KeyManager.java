package main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys;
	public boolean up, down, left, right, shootRight, shootLeft;

	public KeyManager() {
		keys = new boolean[256];
	}

	public void update() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		shootLeft = keys[KeyEvent.VK_LEFT];
		shootRight = keys[KeyEvent.VK_RIGHT];
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {

	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		keys[keyEvent.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		keys[keyEvent.getKeyCode()] = false;
	}
}
