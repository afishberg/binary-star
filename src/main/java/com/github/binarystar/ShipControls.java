package com.github.binarystar;

import com.github.binarystar.engine.*;

import processing.core.PVector;

public class ShipControls extends Component {

	private Transform transform;
	private SpriteRenderer renderer;
	
	private boolean selected;
	
	public ShipControls() {
		selected = false;
	}
	
	public void start() {
		transform = entity.getComponent(Transform.class);
		renderer = entity.getComponent(SpriteRenderer.class);
	}
	
	public void update(float deltaTime) {
		
		// Wrap screen bounds
		int width = Main.WIDTH;
		int height = Main.HEIGHT;
		
		if (transform.position.x > width + width / 8) {
			transform.position = new PVector( -width / 8 , transform.position.y);
		} else if (transform.position.x < -width / 8) {
			transform.position = new PVector(width + width / 8 , transform.position.y);
		}
		
		if (transform.position.y > height + height / 8) {
			transform.position = new PVector(transform.position.x , -height / 8);
		} else if (transform.position.y < -height / 8) {
			transform.position = new PVector(transform.position.x , height + height / 8);
		}
		
		// Select clicked ship
		if (InputManager.isMousePressed()) {
			// TODO: Take rotation into account
			selected = Math.abs(InputManager.mouseX - renderer.x) < renderer.sprite.width / 2 &&
					   Math.abs(InputManager.mouseY - renderer.y) < renderer.sprite.height / 2;
		}
		renderer.a = selected ? 255 : 150;
		System.out.println(renderer.a + " " + selected);
		
		// Move selected ships with keyboard
		if (selected) {
			if (InputManager.isKeyPressed('d')) {
				transform.rotation += 1 / Math.PI / 4;
			}
			if (InputManager.isKeyPressed('a')) {
				transform.rotation -= 1 / Math.PI / 4;
			}
			if (InputManager.isKeyPressed('w')) {
				transform.velocity.add(PVector.fromAngle(transform.rotation).mult(10));
			}
			if (InputManager.isKeyPressed('s')) {
				transform.velocity.sub(PVector.fromAngle(transform.rotation).mult(10));
			}
			if (InputManager.isKeyPressed(' ')) {
				transform.velocity.mult(0.8f);
			}
		}
	}
}
