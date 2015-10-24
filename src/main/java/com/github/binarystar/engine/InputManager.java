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

	// Mouse position relative to other entities
	public static float mouseWorldSpaceX(Camera c) {
		return (mouseX + (int)c.cameraTransform.position.x) / c.cameraTransform.scale.x;
	}
	public static float mouseWorldSpaceY(Camera c) {
		return (mouseY + (int)c.cameraTransform.position.y) / c.cameraTransform.scale.y;
	}
	
	// Mouse position relative to window
	public static float mouseScreenSpaceX() {
		return mouseX;
	}
	public static float mouseScreenSpaceY() {
		return mouseY;
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
