package com.github.binarystar;

import com.github.binarystar.engine.*;
import processing.core.PImage;

public class TestScreen extends GameScreen {
	
	public TestScreen() {
		super();

		// For testing
		PImage sprite = Main.Processing.loadImage("assets/sprite.png");
		PImage ui = Main.Processing.loadImage("assets/minimap_temp.png");
		
		Ship ship1 = new Ship(sprite, 300, 500);
		Ship ship2 = new Ship(sprite, 700, 500);
		entities.add(ship1);
		entities.add(ship2);
		
		// UI draw testing
		Entity stupidUI = new Entity();
		stupidUI.addComponent(new SpriteRenderer(ui));
		stupidUI.getComponent(Transform.class).position.add(Main.WIDTH - 80, 80);
		stupidUI.getComponent(Transform.class).scale.mult(0.5f);
		stupidUI.getComponent(SpriteRenderer.class).a = 100;
		uiElements.add(stupidUI);
		stupidUI.init();
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
