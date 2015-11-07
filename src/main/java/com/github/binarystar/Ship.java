package com.github.binarystar;

import com.github.binarystar.engine.*;

import processing.core.PImage;

public class Ship extends Entity {

	private Transform transform;
	private String tag;
	
	public Ship(PImage sprite, int x, int y, String tag) {
		super();
		
		this.tag = tag;
		
		addComponent(new SpriteRenderer(sprite));
		addComponent(new HitBox(0, 0, sprite.width-50, sprite.height-80, "ship")); // Ship hit box
		addComponent(new HitBox(sprite.width, 0, sprite.width, sprite.height, "sensor")); // Ship sensor
		
		addComponent(new ShipEngine());
		addComponent(new ShipWeapon(tag));
		addComponent(new ShipControls());
		
		// (Transform added automatically)
		
		init();
		
		this.transform = getComponent(Transform.class);
		transform.position.x = x;
		transform.position.y = y;
	}
	
	@Override
	public void onCollision(HitBox thisBox, HitBox otherBox) {
		if (otherBox.tag.equals("ship") && thisBox.tag.equals("ship")) {
			// KABOOM
			thisBox.entity.destroy();
			otherBox.entity.destroy();
			
		} else if (thisBox.tag.equals("sensor")) {
			if (otherBox.tag.equals("ship")) {
				// Found a ship! Make it blink
				SpriteRenderer r = otherBox.entity.getComponent(SpriteRenderer.class);
				r.g = ~r.g;
			}
		} else if (thisBox.tag.equals("ship")) {
			if (otherBox.tag.startsWith("bullet") && !otherBox.tag.equals("bullet" + tag)) {
				otherBox.entity.destroy();
				thisBox.entity.destroy();
			}
		}
	}
	
}
