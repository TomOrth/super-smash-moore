package com.tom.greg.matt.smash;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.atf.binder.KeyBinder;
import com.atf.binder.enums.KeyState;
import com.atf.keybinder.interfaces.Executor;
import com.tom.greg.matt.smash.entity.Player;
import com.tom.greg.matt.smash.utils.Audio;
import com.tom.greg.matt.smash.utils.ImageMap;

public class Main extends Canvas implements Runnable, Executor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 150, HEIGHT = WIDTH *5/7, SCALE = 3;
	public static boolean running = false, gameOver = false, startMsg = true;
	private Thread thread;
	private int seconds = 0;
	private KeyBinder binder;
	public Handler handler;
	public static Player player1, player2;
	public static String winnerMessage;
	
	public static void main(String[] args) {
		Main game = new Main();
		JFrame frame = new JFrame("MOORE SMASH!!!");
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();
	}
	
	private void init() {
		ImageMap.initialize();
		
		player1 = new Player(100, 200, 30, 90, 1000, "moore", "right");
		player2 = new Player(300, 200, 30, 90, 1000, "less", "left");
		handler = new Handler();
		binder = new KeyBinder();
		
		binder.when(KeyState.PRESSED).bindAction(KeyEvent.VK_SPACE, this);
		
		binder.when(KeyState.PRESSED).bindAction(KeyEvent.VK_W, player1::jump);
		binder.when(KeyState.PRESSED).bindAction(KeyEvent.VK_A, player1::left);
		binder.when(KeyState.PRESSED).bindAction(KeyEvent.VK_D, player1::right);
		binder.when(KeyState.PRESSED).bindAction(KeyEvent.VK_Q, player1::fire);
		binder.when(KeyState.PRESSED).bindAction(KeyEvent.VK_E, player1::rock);
		binder.when(KeyState.RELEASED).bindAction(KeyEvent.VK_A, player1::restLeft);
		binder.when(KeyState.RELEASED).bindAction(KeyEvent.VK_D, player1::restRight);
		
		binder.when(KeyState.PRESSED).bindAction(KeyEvent.VK_UP, player2::jump);
		binder.when(KeyState.PRESSED).bindAction(KeyEvent.VK_LEFT, player2::left);
		binder.when(KeyState.PRESSED).bindAction(KeyEvent.VK_RIGHT, player2::right);
		binder.when(KeyState.PRESSED).bindAction(KeyEvent.VK_SLASH, player2::fire);
		binder.when(KeyState.PRESSED).bindAction(KeyEvent.VK_SHIFT, player2::rock);
		binder.when(KeyState.RELEASED).bindAction(KeyEvent.VK_LEFT, player2::restLeft);
		binder.when(KeyState.RELEASED).bindAction(KeyEvent.VK_RIGHT, player2::restRight);
		
		addKeyListener(binder);
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.BLUE.brighter().brighter());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.GREEN);
		g.fillRect(0, getHeight()-85, getWidth(), 900);
		g.setColor(Color.WHITE);
		
		handler.render(g);
		
		if (startMsg) {
			g.setFont(new Font("Arial", 1, 20));
			g.drawString("See controls.txt for control layout", getWidth()/2-140, getHeight()/2-20);
			g.drawString("Press spacebar to start", getWidth()/2-100, getHeight()/2);
		}
		else {
			g.drawString("Player 1 Health: " + Integer.toString(player1.getHealth()), 5, 10);
			g.drawString("Time: " + Integer.toString(seconds), getWidth()/2-30, 10);
			g.drawString("Player 2 Health: " + Integer.toString(player2.getHealth()), getWidth()-130, 10);
		}
		if (gameOver) {
			g.setFont(new Font("Arial", 1, 20));
			g.drawString(winnerMessage, getWidth()/2-70, getHeight()/2-40);
			g.drawString("To play again, recompile the program", getWidth()/2-170, getHeight()/2-10);
			g.drawString("Give us an A (plz)", getWidth()/2-100, getHeight()/2+10);
		}
		
		
		
		g.dispose();
		bs.show();
	}
	
	private void tick() {
		handler.tick();
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

	@Override
	public void execute() {
		startMsg = false;
		Audio.loop("res/brawl.wav");
	}
}
