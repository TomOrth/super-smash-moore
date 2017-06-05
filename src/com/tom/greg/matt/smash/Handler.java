package com.tom.greg.matt.smash;

import java.awt.Graphics;

import com.tom.greg.matt.smash.entity.attack.Attack;
import com.tom.greg.matt.smash.entity.attack.FireAttack;
import com.tom.greg.matt.smash.utils.Audio;

public class Handler {
	
	public void render(Graphics g){
		Main.player1.render(g);
		for (int i = 0; i < Main.player1.getAttacks().size(); ++i) {
			Main.player1.getAttacks().get(i).render(g);
		}
		Main.player2.render(g);
		for (int j = 0; j < Main.player2.getAttacks().size(); ++j) {
			Main.player2.getAttacks().get(j).render(g);
		}
	}
	
	public void tick(){
		Main.player1.tick();
		for (int i = 0; i < Main.player1.getAttacks().size(); ++i) {
			Attack attack = Main.player1.getAttacks().get(i);
			attack.tick();
			if (Main.player2.getBounds().intersects(attack.getBounds())) {
				Main.player1.getAttacks().remove(i);
				int reduceAmt = attack instanceof FireAttack ? 15 : 20;
				Main.player2.reduceHealth(reduceAmt);
			}
		}
		Main.player2.tick();
		for (int j = 0; j < Main.player2.getAttacks().size(); ++j) {
			Attack at = Main.player2.getAttacks().get(j);
			at.tick();
			if (Main.player1.getBounds().intersects(at.getBounds())) {
				Main.player2.getAttacks().remove(j);
				int reduceAmt = at instanceof FireAttack ? 15 : 20;
				Main.player1.reduceHealth(reduceAmt);
			}
		}
		
		if (Main.player1.getHealth() <= 0) {
			Audio.play("res/death.wav");
			Main.gameOver = true;
			Main.winnerMessage = "Player 2 wins";
		}
		
		else if (Main.player2.getHealth() <= 0) {
			Audio.play("res/death.wav");
			Main.gameOver = true;
			Main.winnerMessage = "Player 1 wins";
		}
	}
}
