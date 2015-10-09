package com.github.binarystar.engine;

import processing.core.PVector;

public class Transform extends Component {

	public PVector position, velocity;
	public float rotation;

	public Transform() {
		position = new PVector();
		velocity = new PVector();
		rotation = 0.0f;
	}
	
	public void start() { }
	
	public void update(float deltaTime) {
		position.add(PVector.mult(velocity, deltaTime));
	}
	
}
