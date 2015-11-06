package com.github.binarystar;

import com.github.binarystar.engine.*;

import processing.core.PVector;

public class ShipMovement extends Component {

	private Transform transform;
	private SpriteRenderer renderer;
	
	private boolean selected;
	
	public ShipMovement() {
		selected = false;
	}
	
	public void start() {
		transform = entity.getComponent(Transform.class);
		renderer = entity.getComponent(SpriteRenderer.class);
	}
	
	public void update(float deltaTime) {
		
		// Wrap screen bounds
		float right = Main.CurrentScreen.mainCamera.CameraRight();
		float bottom = Main.CurrentScreen.mainCamera.CameraBottom();
		float left = Main.CurrentScreen.mainCamera.CameraLeft();
		float top = Main.CurrentScreen.mainCamera.CameraTop();
		
		if (transform.position.x > right + 100) {
			transform.position = new PVector(left - 100, transform.position.y);
		} else if (transform.position.x < left - 100) {
			transform.position = new PVector(right + 100, transform.position.y);
		}
		
		if (transform.position.y > bottom + 100) {
			transform.position = new PVector(transform.position.x , top - 100);
		} else if (transform.position.y < top - 100) {
			transform.position = new PVector(transform.position.x , bottom + 100);
		}
		
		// Select clicked ship
		if (InputManager.isMousePressed()) {
			// TODO: Take rotation and scale of ship into account
			float mouseX = InputManager.mouseWorldSpaceX(Main.CurrentScreen.mainCamera);
			float mouseY = InputManager.mouseWorldSpaceY(Main.CurrentScreen.mainCamera);
			selected = Math.abs(mouseX - transform.position.x) < renderer.sprite.width / 2 &&
					   Math.abs(mouseY - transform.position.y) < renderer.sprite.height / 2;
		}
		renderer.a = selected ? 255 : 150;
		
		// Move selected ships with keyboard
		if (selected) {
			if (InputManager.isKeyPressed('d')) {
				engineCW();
			}
			if (InputManager.isKeyPressed('a')) {
				engineCCW();
			}
			if (InputManager.isKeyPressed('w')) {
				engineForward();
			}
			if (InputManager.isKeyPressed('s')) {
				engineReverse();
			}
			if (InputManager.isKeyPressed(' ')) {
				engineBrake();
			}
		}
	}
	
	public void engine(String action){
		switch (action) {
		case "FORWARD":
			engineForward();
			break;
		case "REVERSE":
			engineReverse();
			break;
		case "BRAKE":
			engineBrake();
			break;
		case "CW":
			engineCW();
			break;
		case "CCW":
			engineCCW();
			break;
		}
	}
	
	public void engineForward() {
		transform.velocity.add(PVector.fromAngle(transform.rotation).mult(10));
	}
	
	public void engineReverse() {
		transform.velocity.sub(PVector.fromAngle(transform.rotation).mult(10));
	}
	
	public void engineBrake() {
		transform.velocity.mult(0.8f);
	}
	
	public void engineCW() {
		transform.rotation += 1 / Math.PI / 4;
	}
	
	public void engineCCW() {
		transform.rotation -= 1 / Math.PI / 4;
	}
}
