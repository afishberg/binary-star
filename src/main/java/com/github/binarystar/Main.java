package com.github.binarystar;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Main extends PApplet {

	Ship ship;
	
	public static void main(String args[]) {
		//PApplet.main(new String[] { "--present", "com.github.binarystar.Main" }); // set to fullscreen
		PApplet.main(new String[] { "com.github.binarystar.Main" });
	}
	
	public void setup() {
		background(0);
		frameRate(30);
		
		// load sprite
		PImage sprite = loadImage("assets/sprite.png");

		ship = new Ship(sprite, 500, 500);
	}
	
	public void settings() {
		size(1000,1000);
	}

	int count = 0;
	final int WRAP_AROUND = 8;
	
	public void draw() {
		// redraw background
		background(0);

		// debugging output
		if (count++ >= frameRate * 5) {
			println(ship.pos);
			count = 0;
		}
		
		// game logic
		ship.update();
		
		// draw lines
		stroke(255);
		line(0, 500, 1000, 500);
		line(500, 0, 500, 1000);
		
		// draw ship
		pushMatrix();
		translate(ship.pos.x, ship.pos.y);
		rotate(ship.dir);
		image(ship.sprite, -ship.sprite.width / 2, -ship.sprite.height / 2);
		popMatrix();
		
		
		// wrap around
		if (ship.pos.x > width + width / WRAP_AROUND) {
			ship.pos = new PVector( -width / WRAP_AROUND , ship.pos.y);
		} else if (ship.pos.x < -width / WRAP_AROUND) {
			ship.pos = new PVector(width + width / WRAP_AROUND , ship.pos.y);
		}
		
		if (ship.pos.y > height + height / WRAP_AROUND) {
			ship.pos = new PVector(ship.pos.x , -height / WRAP_AROUND);
		} else if (ship.pos.y < -height / WRAP_AROUND) {
			ship.pos = new PVector(ship.pos.x , height + height / WRAP_AROUND);
		}
		
		
	}
	
	public void keyPressed() {
		switch (key) {
		case 'w':
			ship.accel();
			break;
		case 'a':
			ship.rotCCW();
			break;
		case 's':
			ship.decel();
			break;
		case 'd':
			ship.rotCW();
			break;
		case ' ':
			ship.breaks();
			break;
		default:
			break;
		}
	}
	
	
	public static class Ship {
		PImage sprite;
		PVector pos, vel;
		float dir;
		
		public Ship(PImage sprite, int x, int y) {
			this.sprite = sprite;
			pos = new PVector(x,y);
			vel = new PVector(0,0);
			dir = radians(-90);
		}
		
		public void update() {
			pos = pos.add(vel);
		}
		
		public void rotCW() {
			dir += radians(1);
		}
		
		public void rotCCW() {
			dir -= radians(1);
		}
		
		public void accel() {
			vel = vel.add(PVector.fromAngle(dir).div(10));
		}
		
		public void decel() {
			vel = vel.sub(PVector.fromAngle(dir).div(10));
		}
		
		public void breaks() {
			vel = vel.mult(0.8f);
		}
		
	}
}
