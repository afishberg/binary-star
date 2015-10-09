package com.github.binarystar.engine;

import processing.core.PImage;

public class SpriteRenderer extends Component {

	public Transform transform;	// Transform component used to find drawing position/rotation
	public PImage sprite;		// Image to draw
	public float x, y, rot;		// Actual transform values that are rendered
	public int r, g, b, a;		// Tint values
	
	public SpriteRenderer(PImage sprite) {
		this.sprite = sprite;
		r = g = b = a = 255;
	}
	
	public void start() {
		this.transform = entity.getComponent(Transform.class);
	}
	
	public void update(float deltaTime) {
		if (transform == null){
			System.err.println("No transform component found");
			return;
		}
		x = transform.position.x;
		y = transform.position.y;
		rot = transform.rotation;
	}
	
}
