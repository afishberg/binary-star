package com.github.binarystar.engine;

public abstract class Component {
	
	/**
	 * The entity that the componenet will be attached to
	 */
	public Entity entity;
	
	/**
	 * Assign necessary references
	 * @param entity The entity this component is attached to
	 */
	public void init(Entity entity) {
		this.entity = entity;
	}
	
	/**
	 * Called after init; use this when you want to find other components so they're initialized properly first
	 */
	public abstract void start();
	
	/**
	 * Called all the time
	 * @param deltaTime The time in seconds since the last call
	 */
	public abstract void update(float deltaTime);
	
	/**
	 * Called every frame for drawing
	 */
	public void render() { }
}
