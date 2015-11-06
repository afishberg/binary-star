package com.github.binarystar.engine;

import java.util.*;

import com.github.binarystar.Main;

public class GameScreen {
	
	public ArrayList<Entity> entities;
	public ArrayList<Entity> uiElements;
	public Camera mainCamera;
	private Transform cameraTransform;
	
	public GameScreen() {
		mainCamera = new Camera();
		cameraTransform = mainCamera.getComponent(Transform.class);
		entities = new ArrayList<Entity>();
		uiElements = new ArrayList<Entity>();
	}
	
	public void update(float deltaTime) {
		mainCamera.update(deltaTime);
		for (Entity e : entities) {
			e.update(deltaTime);
		}
		Main.Collisions.update();
		for (Entity e : uiElements) {
			e.update(deltaTime);
		}
	}
	
	public void render() {
		// redraw background
		Main.Processing.background(0);

		// Iterate through our entity list and draw everything
		for (Entity e : entities) {
			Main.Processing.pushMatrix();

			// Perform camera transformations.
			// Everything is reversed because everything moves in the opposite direction from camera's reference frame
			Main.Processing.translate(-cameraTransform.position.x, -cameraTransform.position.y);
			Main.Processing.scale(cameraTransform.scale.x, cameraTransform.scale.y);
			Main.Processing.rotate(-cameraTransform.rotation);
			
			// Render other entities
			e.render();
			
			Main.Processing.popMatrix();
		}
		
		// Draw UI, independent of camera
		for (Entity e : uiElements) {
			SpriteRenderer s = e.getComponent(SpriteRenderer.class);
			if (s != null)
				s.render();
		}
	}
}
