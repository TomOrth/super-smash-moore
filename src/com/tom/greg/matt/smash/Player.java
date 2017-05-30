package com.tom.greg.matt.smash;

import java.awt.Graphics;

public class Player extends Entity{

	public int health;
	
	public Player(int x, int y, int width, int height, int health) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += vX;
		y += vY;
	}
	
}