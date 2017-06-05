package com.tom.greg.matt.smash.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.tom.greg.matt.smash.Main;
import com.tom.greg.matt.smash.entity.attack.Attack;
import com.tom.greg.matt.smash.entity.attack.FireAttack;
import com.tom.greg.matt.smash.entity.attack.RockAttack;
import com.tom.greg.matt.smash.utils.Audio;

public class Player extends Entity{

	private int health;
	private String name;
	private String pos;
	private BufferedImage right, left, leftWalk, rightWalk, mainImage;
    private boolean isJumping;
    public ArrayList<Attack> attacks;
	public Player(int x, int y, int width, int height, int health, String name, String pos) {
		super(x, y, width, height);
		this.health = health;
		this.name = name;
		this.pos = pos;
		this.isJumping = false;
		attacks = new ArrayList<Attack>();
		try {
			this.right = ImageIO.read(getClass().getResource("/" + name + "/right.gif"));
			this.left = ImageIO.read(getClass().getResource("/" + name + "/left.gif"));
			this.rightWalk = ImageIO.read(getClass().getResource("/" + name + "/rightWalk.gif"));
			this.leftWalk = ImageIO.read(getClass().getResource("/" + name + "/leftWalk.gif"));
			this.vY = 4;
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

	public ArrayList<Attack> getAttacks() {
		return attacks;
	}
	public void right() {
		if (!Main.gameOver) {
			vX = 2;
			pos = "right";
			this.mainImage = this.rightWalk;
		}
	}
	
	public void left() {
		if (!Main.gameOver) {
			vX = -2;
			pos = "left";
			this.mainImage = this.leftWalk;
		}
	}
	
	public void restLeft() {
		if (!Main.gameOver) {
			vX = 0;
			pos = "left";
			this.mainImage = this.left;
		}
	}
	
	public void restRight() {
		if (!Main.gameOver) {
			vX = 0;
			pos = "right";
			this.mainImage = this.right;
		}
	}
	
	public void fire() {
		if (!Main.gameOver) {
			int wX = pos.equals("left") ? x -3 : x + 3, vX = pos.equals("left") ? -5 : 5;
			attacks.add(new FireAttack(wX, y - 3, vX));
			Audio.play(name.equals("moore") ? "res/pkfire.wav" : "res/Hit1.wav");
		}
	}
	
	public void rock() {
		if (!Main.gameOver) {
			int wX = pos.equals("left") ? x -3 : x + 3, vX = pos.equals("left") ? -3 : 3;
			attacks.add(new RockAttack(wX, y - 3, vX));
		    Audio.play(name.equals("moore") ? "res/pkrock.wav" : "res/Rattack1.wav");
		}
	}
	
	public void jump() {
		if (!isJumping && !Main.gameOver) {
			isJumping = true;
			vY = -50;
		}
	}
	
	public void reduceHealth(int reduce) {
		health -= reduce;
	}
	
	public void increaseHealth(int increase) {
		health += increase;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(mainImage, x, y, null);
	}

	@Override
	public void tick() {
		x += vX;
		y += vY;
		vY = 4;
		if (y > 100) {
			y = 100;
			isJumping = false;
		}
	}
	
}