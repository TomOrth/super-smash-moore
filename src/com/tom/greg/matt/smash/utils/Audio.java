package com.tom.greg.matt.smash.utils;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
	public static void play(String filename) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loop(String filename) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
