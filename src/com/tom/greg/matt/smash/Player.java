package com.tom.greg.matt.smash;

import java.awt.Graphics;

public class Player extends Entity{

	public int health;
	
	public Player(int x, int y, int width, int height, int health) {
		super(x, y, width, height);
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void tick() {
		x += vX;
		y += vY;
	}
	
}