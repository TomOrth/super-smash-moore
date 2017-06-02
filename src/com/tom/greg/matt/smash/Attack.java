package com.tom.greg.matt.smash;

import java.awt.Graphics;

public class Attack extends Entity{

	private int vX;
	
	public Attack(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += vX;
	}
}