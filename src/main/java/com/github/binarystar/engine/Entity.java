package com.github.binarystar.engine;

import java.util.*;

import com.github.binarystar.Main;

public class Entity {
	private ArrayList<Component> components;

	public Entity() {
		components = new ArrayList<Component>();
		Transform t = new Transform(); // Most things need a transform, so just gonna attach it to every Entity
		addComponent(t);
	}
	
	/**
	 * Initializes and starts all attached components
	 */
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
	
	/**
	 * Destroys all attached components and removes the Entity from current screen's entity list
	 */
	public void destroy() {
		for (Component c : components) {
			c.destroy();
		}
		Main.CurrentScreen.destroyEntity(this);
	}
	
	/**
	 * Updates all components attached to the entity
	 * @param deltaTime The tick length
	 */
	public void update(float deltaTime) {
		// Update components
		for (Component c : components) {
			c.update(deltaTime);
		}
	}
	
	/**
	 * Called for rendering things with Processing
	 */
	public final void render() {
		for (Component c : components) {
			c.render();
		}
	}
	
	/**
	 * Gets the component of a specified type from the entity
	 * @param tClass The type of component to retrieve
	 * @return The first component with a matching type found with a linear search
	 */
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
	
	/**
	 * Gets a list of all components of the specified type attached to the entity
	 * @param tClass The type of component to find
	 * @return A list of all found components
	 */
	@SuppressWarnings("unchecked")
	public final <T> ArrayList<T> getAllComponents(Class<T> tClass) {
		ArrayList<T> comps = new ArrayList<T>();
		for (Component c : components) {
			if (c.getClass().isAssignableFrom(tClass)) {
				comps.add((T)c);
			}
		}
		return comps;
	}
	
	/**
	 * Attaches a component to the Entity
	 * @param comp The component to attach
	 */
	public final Component addComponent(Component comp) {
		components.add(comp);
		return comp;
	}
}
