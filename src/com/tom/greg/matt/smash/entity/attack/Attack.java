package com.tom.greg.matt.smash.entity.attack;

import java.awt.Graphics;

import com.tom.greg.matt.smash.entity.Entity;
import com.tom.greg.matt.smash.utils.ImageMap;

public class Attack extends Entity{

	private String name;
	
	public Attack(int x, int y, int width, int height, int vX, String name) {
		super(x, y, width, height);
		this.vX = vX;
		this.name = name;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(ImageMap.get(name), x, y, null);
	}

	@Override
	public void tick() {
		x += vX;
	}
}