package com.tom.greg.matt.smash;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {
	
	public static final int WIDTH = 150, HEIGHT = WIDTH *5/7, SCALE = 3;
	public static boolean running = false, gameOver = false;
	private Thread thread;
	private int seconds = 0;
	public Handler handler;
	
	private void init() {
		
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		g.setColor(Color.BLUE.brighter().brighter());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	private void tick() {
		
	}
	
	public Main() {
		Dimension size = new Dimension(WIDTH*SCALE, HEIGHT*SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
	}
	
	private synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this, "Thread");
		thread.start();
	}
	
	private synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {

		init();
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
		int frames = 0;
		int ticks = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println(frames + " fps " + ticks + " updates/sec ");
				if(!gameOver) seconds += ticks/60;
				frames = 0;
				ticks = 0;
			}
		}
		
		stop();
		
	}
}
