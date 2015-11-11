package com.github.binarystar;

import java.util.*;

import com.github.binarystar.engine.*;

import processing.core.PImage;

public class Ship extends Entity {

	private Transform transform;
	private String tag;
	private HitBox collider, sensor;
	private ShipControls controls;
	
	public Ship(PImage sprite, int x, int y, String tag) {
		super();
		
		this.tag = tag;
		
		addComponent(new SpriteRenderer(sprite));
		addComponent(collider = new HitBox(0, 0, sprite.width-50, sprite.height-80, "ship")); // Ship hit box
		addComponent(sensor = new HitBox(sprite.width, 0, sprite.width, sprite.height, "sensor")); // Ship sensor
		
		addComponent(new ShipEngine());
		addComponent(new ShipWeapon(tag));
		addComponent(controls = new ShipControls());
		
		// (Transform added automatically)
		
		init();
		
		this.transform = getComponent(Transform.class);
		transform.position.x = x;
		transform.position.y = y;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		// Activate sensor when f is pressed
		if (InputManager.isKeyPressed('f') && controls.selected) {
			HitBox otherBox = sensor.getOverlap();
			if (otherBox != null) {
				if (otherBox.tag.equals("ship")) {
					// Found a ship! Make it blink
					SpriteRenderer r = otherBox.entity.getComponent(SpriteRenderer.class);
					r.g = ~r.g;
				}
			}
		}
		
		// Check for collisions with ship
		ArrayList<HitBox> otherBoxes = collider.getAllOverlaps();
		for (HitBox b : otherBoxes) {
			onCollision(b);
		}
	}

	public void onCollision(HitBox otherBox) {
		if (otherBox.tag.equals("ship")) {
			// KABOOM
			destroy();
			otherBox.entity.destroy();
			
		} else if (otherBox.tag.startsWith("bullet") && !otherBox.tag.equals("bullet" + tag)) {
			otherBox.entity.destroy();
			destroy();
		}
	}
	
}
