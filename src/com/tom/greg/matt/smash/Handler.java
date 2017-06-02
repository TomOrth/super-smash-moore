package com.tom.greg.matt.smash;

import java.awt.Graphics;

public class Handler {
	
	public void render(Graphics g){
		Main.player1.render(g);
		Main.player2.render(g);
	}
	
	public void tick(){
		Main.player1.tick();
		Main.player2.tick();
	}
}
