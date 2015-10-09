package com.github.binarystar;

import com.github.binarystar.engine.*;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.*;

public class Main extends PApplet {

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;
	public static final int FRAME_RATE = 60; // u filthy pleb
	
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	
	Ship ship1, ship2;
	
	public static void main(String args[]) {
		//PApplet.main(new String[] { "--present", "com.github.binarystar.Main" }); // set to fullscreen
		PApplet.main(new String[] { "com.github.binarystar.Main" });
	}
	
	public void setup() {
		background(0);
		frameRate(FRAME_RATE);

		PImage sprite = loadImage("assets/sprite.png");
		
		ship1 = new Ship(sprite, 300, 500);
		ship2 = new Ship(sprite, 700, 500);
		entities.add(ship1);
		entities.add(ship2);
	}
	
	public void settings() {
		size(WIDTH, HEIGHT);
	}

	int count = 0;
	final int WRAP_AROUND = 8;
	
	public void draw() {
		// TODO: probably separate from draw logic
		{
			for (Entity e : entities) {
				e.update(1f/FRAME_RATE);
			}
		}
		
		// redraw background
		background(0);

		// Iterate through our entity list and draw everything
		for (Entity e : entities) {
			SpriteRenderer s = e.getComponent(SpriteRenderer.class);
			
			// draw with Processing
			pushMatrix();
			translate(s.x, s.y);
			rotate(s.rot);
			tint(s.r, s.g, s.b, s.a);
			image(s.sprite, -s.sprite.width / 2, -s.sprite.height / 2);
			popMatrix();
		}
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
	
	// Ship class with a transform, sprite, and controls component
	public static class Ship extends Entity {

		Transform transform;
		
		public Ship(PImage sprite, int x, int y) {
			super();
			
			addComponent(new SpriteRenderer(sprite));
			addComponent(new ShipControls());
			// (Transform added automatically)
			
			init();
			
			this.transform = getComponent(Transform.class);
			transform.position.x = x;
			transform.position.y = y;
		}
	}
}
