package com.github.binarystar.engine;

import java.util.*;

public class Entity {
	private ArrayList<Component> components;

	public Entity() {
		components = new ArrayList<Component>();
		Transform t = new Transform(); // Most things need a transform, so just gonna attach it to every Entity
		addComponent(t);
	}
	
	public final void init() {
		// Initialize components
		for (Component c : components) {
			c.init(this);
		}
		// Perform post-initialization startup on components
		for (Component c : components) {
			c.start();
		}
	}
	
	public final void update(float deltaTime) {
		// Update components
		for (Component c : components) {
			c.update(deltaTime);
		}
	}
	
	@SuppressWarnings("unchecked")
	public final <T> T getComponent(Class<T> tClass) {
		// Find the component with the desired type and return it
		for (Component c : components) {
			if (c.getClass().isAssignableFrom(tClass)) {
				return (T)c;
			}
		}
		return null;
	}
	
	public final void addComponent(Component comp) {
		components.add(comp);
	}
}
