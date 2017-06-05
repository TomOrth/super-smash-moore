package com.tom.greg.matt.smash.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.tom.greg.matt.smash.Main;


public class ImageMap {
	
	private static HashMap<String, BufferedImage> map;
	
	public static void initialize() {
		map = new HashMap<String, BufferedImage>();
		try {
			map.put("fire", ImageIO.read(Main.class.getClass().getResource("/attacks/fire.gif")));
			map.put("rock", ImageIO.read(Main.class.getClass().getResource("/attacks/rocks.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage get(String key) {
		return map.get(key);
	}
}
