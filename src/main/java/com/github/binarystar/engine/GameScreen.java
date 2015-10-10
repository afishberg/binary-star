package com.github.binarystar.engine;

import java.util.*;

import com.github.binarystar.Main;

public class GameScreen {
	
	public ArrayList<Entity> entities;
	
	public GameScreen() {
		entities = new ArrayList<Entity>();
	}
	
	public void update(float deltaTime) {
		for (Entity e : entities) {
			e.update(deltaTime);
		}
	}
	
	public void render() {
		// redraw background
		Main.Processing.background(0);

		// Iterate through our entity list and draw everything
		for (Entity e : entities) {
			SpriteRenderer s = e.getComponent(SpriteRenderer.class);
			if (s != null)
				s.render();
		}
	}
}
