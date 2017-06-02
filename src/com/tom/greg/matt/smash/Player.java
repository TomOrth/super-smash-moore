package com.tom.greg.matt.smash;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity{

	private int health;
	private String name;
	private String pos;
	private BufferedImage right, left, leftWalk, rightWalk, mainImage;

	public Player(int x, int y, int width, int height, int health, String name, String pos) {
		super(x, y, width, height);
		this.health = health;
		this.name = name;
		this.pos = pos;
		try {
			this.right = ImageIO.read(getClass().getResource("/" + name + "/right.gif"));
			this.left = ImageIO.read(getClass().getResource("/" + name + "/left.gif"));
			this.rightWalk = ImageIO.read(getClass().getResource("/" + name + "/rightWalk.gif"));
			this.leftWalk = ImageIO.read(getClass().getResource("/" + name + "/leftWalk.gif"));
			if (pos.equals("left")) this.mainImage = left;
			else this.mainImage = right;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void right() {
		vX = 2;
		this.mainImage = this.rightWalk;
	}
	
	public void left() {
		vX = -2;
		this.mainImage = this.leftWalk;
	}
	
	public void restLeft() {
		this.mainImage = this.left;
	}
	
	public void restRight() {
		this.mainImage = this.right;
	}
	

	@Override
	public void render(Graphics g) {
		g.drawImage(mainImage, x, y, null);
	}

	@Override
	public void tick() {
		x += vX;
		y += vY;
	}
	
}