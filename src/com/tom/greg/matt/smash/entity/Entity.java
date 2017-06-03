package com.tom.greg.matt.smash.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {

	public int x, y;
	public int width, height;
	public double vX, vY;

	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public Rectangle getBoundsTop() {
		return new Rectangle(getX() + 10, getY(), getWidth() - 20, 5);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle(getX() + 10, getY() + getHeight() - 5, getWidth() - 20, 5);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getVX() {
		return vX;
	}

	public void setVX(double vX) {
		this.vX = vX;
	}

	public double getVY() {
		return vY;
	}

	public void setVY(double vY) {
		this.vY = vY;
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle(getX(), getY() - 10, 5, getHeight() - 20);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle(getX() + getWidth() - 5, getY() + 10, 5, getHeight() - 20);
	}

	public abstract void render(Graphics g);

	public abstract void tick();
	
}
