package com.tom.greg.matt.smash;

import java.awt.Graphics;

public class Attack extends Entity{

	private String name;
	
	public Attack(int x, int y, int width, int height, int vX, String name) {
		super(x, y, width, height);
		this.vX = vX;
		this.name = name;
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void tick() {
		x += vX;
	}
}