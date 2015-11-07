package com.github.binarystar;

import com.github.binarystar.engine.*;

public class ShipControls extends Component {

	private Transform transform;
	private SpriteRenderer renderer;
	private ShipEngine movement;
	private ShipWeapon weapon;
	
	private boolean selected;
	private boolean ePressed;
	
	public ShipControls() {
		selected = false;
		ePressed = true;
	}
	
	public void start() {
		transform = entity.getComponent(Transform.class);
		renderer = entity.getComponent(SpriteRenderer.class);
		movement = entity.getComponent(ShipEngine.class);
		weapon = entity.getComponent(ShipWeapon.class);
	}
	
	public void update(float deltaTime) {
		
		// Select clicked ship
		if (InputManager.isMousePressed()) {
			float mouseX = InputManager.mouseWorldSpaceX(Main.CurrentScreen.mainCamera);
			float mouseY = InputManager.mouseWorldSpaceY(Main.CurrentScreen.mainCamera);
			selected = Math.abs(mouseX - transform.position.x) < renderer.sprite.width / 2 &&
					   Math.abs(mouseY - transform.position.y) < renderer.sprite.height / 2;
		}
		renderer.a = selected ? 255 : 150;
		
		// Move selected ships with keyboard
		if (selected) {
			if (InputManager.isKeyPressed('d')) {
				movement.engineCW();
			}
			if (InputManager.isKeyPressed('a')) {
				movement.engineCCW();
			}
			if (InputManager.isKeyPressed('w')) {
				movement.engineForward();
			}
			if (InputManager.isKeyPressed('s')) {
				movement.engineReverse();
			}
			if (InputManager.isKeyPressed(' ')) {
				movement.engineBrake();
			}
			if (InputManager.isKeyPressed('e')) {
				if (!ePressed) {
					weapon.fire();
					ePressed = true;
				}
			} else {
				ePressed = false;
			}
		}
	}
	
}
