package com.github.binarystar.engine;

import com.github.binarystar.Main;

public class Camera extends Entity {
	
	public static int CameraSpeed = 300;
	public static int CameraMouseThreshold = 100;
	
	public Transform cameraTransform;
	
	public Camera() {
		super();
		addComponent(new CameraComponent());
		init();
		
		cameraTransform = getComponent(Transform.class);
	}

	// World space positions of camera bounds
	public float CameraTop() {
		return cameraTransform.position.y / cameraTransform.scale.y;
	}
	public float CameraBottom() {
		return (cameraTransform.position.y + Main.HEIGHT) / cameraTransform.scale.y;
	}
	public float CameraLeft() {
		return cameraTransform.position.x / cameraTransform.scale.x;
	}
	public float CameraRight() {
		return (cameraTransform.position.x + Main.WIDTH) / cameraTransform.scale.x;
	}
	
	public class CameraComponent extends Component {

		private Transform transform;
		
		public void start() {
			transform = entity.getComponent(Transform.class);
		}

		public void update(float deltaTime) {
			// Move camera when mouse reaches edge of screen
			if (InputManager.mouseScreenSpaceX() > Main.WIDTH - CameraMouseThreshold)
				transform.velocity.x = CameraSpeed;
			else if (InputManager.mouseScreenSpaceX() < CameraMouseThreshold)
				transform.velocity.x = -CameraSpeed;
			else
				transform.velocity.x = 0;
			
			if (InputManager.mouseScreenSpaceY() > Main.HEIGHT - CameraMouseThreshold)
				transform.velocity.y = CameraSpeed;
			else if (InputManager.mouseScreenSpaceY() < CameraMouseThreshold)
				transform.velocity.y = -CameraSpeed;
			else
				transform.velocity.y = 0;
		}
	}
}
