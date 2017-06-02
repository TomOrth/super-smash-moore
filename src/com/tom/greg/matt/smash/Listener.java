package com.tom.greg.matt.smash;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			Main.player1.left();
			break;
		case KeyEvent.VK_D:
			Main.player1.right();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			Main.player1.restLeft();
			break;
		case KeyEvent.VK_D:
			Main.player1.restRight();
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
