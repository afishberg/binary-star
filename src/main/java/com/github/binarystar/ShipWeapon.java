package com.github.binarystar;

import com.github.binarystar.engine.*;

import processing.core.*;

public class ShipWeapon extends Component {

	private Transform transform;
	private String tag; // Indicates which "team" the bullet comes from
	
	private static PImage bullet = null;
	
	public ShipWeapon(String tag) {
		if (bullet == null) {
			bullet = Main.Processing.loadImage("assets/bullet.png");
		}
		
		this.tag = tag;
	}
	
	public void start() {
		transform = entity.getComponent(Transform.class);
	}

	public void update(float deltaTime) { 
	}
	
	public void fire() {
		PVector direction = new PVector((float)Math.cos(transform.rotation), (float)Math.sin(transform.rotation));
		Main.CurrentScreen.createEntity(new Bullet(transform.position, direction, tag));
	}
	
	
	private class Bullet extends Entity {
		
		private Transform t;
		
		public Bullet(PVector position, PVector direction, String tag) {
			super();
			
			addComponent(new SpriteRenderer(bullet));
			addComponent(new HitBox(0, 0, 16, 16, "bullet" + tag));
			
			init();
			
			t = this.getComponent(Transform.class);
			t.position = new PVector(position.x, position.y);
			t.velocity = new PVector(direction.x * 500, direction.y * 500);
		}
		
		public void update(float deltaTime) {
			super.update(deltaTime);
			
			float right = Main.CurrentScreen.mainCamera.CameraRight();
			float bottom = Main.CurrentScreen.mainCamera.CameraBottom();
			float left = Main.CurrentScreen.mainCamera.CameraLeft();
			float top = Main.CurrentScreen.mainCamera.CameraTop();
			
			if (t.position.x > right + 100 || t.position.x < left - 100 || t.position.y > bottom + 100 || t.position.y < top - 100) {
				Main.CurrentScreen.destroyEntity(this);
			}
		}
	}

}
