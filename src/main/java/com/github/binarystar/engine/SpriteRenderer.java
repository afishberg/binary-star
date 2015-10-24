package com.github.binarystar.engine;

import com.github.binarystar.Main;

import processing.core.PImage;

public class SpriteRenderer extends Component {

	public Transform transform;	// Transform component used to find drawing position/rotation
	public PImage sprite;		// Image to draw
	public int r, g, b, a;		// Tint values
	
	public SpriteRenderer(PImage sprite) {
		this.sprite = sprite;
		r = g = b = a = 255;
	}
	
	public void start() {
		this.transform = entity.getComponent(Transform.class);
	}
	
	public void update(float deltaTime) { }
	
	public void render() {
		if (transform == null)
			System.err.println("Transform not found on " + this.toString() + "; you may not have called init() on the entity after adding components to it.");

		// draw with Processing
		Main.Processing.pushMatrix();
		Main.Processing.translate(transform.position.x, transform.position.y);
		Main.Processing.rotate(transform.rotation);
		Main.Processing.scale(transform.scale.x, transform.scale.y);
		Main.Processing.tint(r, g, b, a);
		Main.Processing.image(sprite, -sprite.width / 2, -sprite.height / 2);
		Main.Processing.popMatrix();
	}
	
}
