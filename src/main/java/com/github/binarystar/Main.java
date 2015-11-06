package com.github.binarystar;

import com.github.binarystar.engine.*;
import processing.core.PApplet;

public class Main extends PApplet {

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;
	public static final int FRAME_RATE = 60; // u filthy pleb

	public static PApplet Processing;
	public static GameScreen CurrentScreen;
	public static CollisionManager Collisions;

	private static float prevTime;
	
	public Main(){
		Processing = this;
	}
	
	public static void main(String args[]) {
		//PApplet.main(new String[] { "--present", "com.github.binarystar.Main" }); // set to fullscreen
		PApplet.main(new String[] { "com.github.binarystar.Main" });
	}
	
	public void setup() {
		// Setup things
		prevTime = System.nanoTime()/1000000000f;
		background(0);
		frameRate(FRAME_RATE);
		
		// Load initial screen
		Collisions = new CollisionManager();
		CurrentScreen = new TestScreen();
	}
	
	public void settings() {
		size(WIDTH, HEIGHT);
	}
	
	public void draw() {
		float dt = System.nanoTime()/1000000000f - prevTime;
		prevTime += dt;
		CurrentScreen.update(dt); // TODO: thread maybe
		CurrentScreen.render();
	}
	
	// Input stuff
	public void keyPressed() {
		InputManager.keyPressed(key);
	}
	public void keyReleased() {
		InputManager.keyReleased(key);
	}
	public void mousePressed() {
		InputManager.mousePressed();
	}
	public void mouseReleased() {
		InputManager.mouseReleased();
	}
	public void mouseMoved() {
		InputManager.mouseX = mouseX;
		InputManager.mouseY = mouseY;
	}
}
