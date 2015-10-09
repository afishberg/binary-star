package com.github.binarystar.engine;

import java.util.*;

public class InputManager {
	
	// TODO: use HashMap instead because efficient
	private static ArrayList<Character> keysPressed = new ArrayList<Character>();
	
	private static boolean mouse = false;
	public static int mouseX, mouseY;
	
	//
	// Actual stuff we'll use
	//
	public static boolean isKeyPressed(char key) {
		return keysPressed.contains(key);
	}
	
	public static boolean isMousePressed() {
		return mouse;
	}
	
	//
	// Called from Processing stuff to update values
	//
	public static void keyPressed(Character key) {
		if (!keysPressed.contains(key)) {
			keysPressed.add(key);
		}
	}
	public static void keyReleased(Character key) {
		if (keysPressed.contains(key)) {
			keysPressed.remove(key);
		}
	}
	public static void mousePressed() {
		mouse = true;
	}
	public static void mouseReleased() {
		mouse = false;
	}
}
