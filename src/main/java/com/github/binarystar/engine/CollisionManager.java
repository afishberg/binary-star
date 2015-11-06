package com.github.binarystar.engine;

import java.util.*;

public class CollisionManager {

	private ArrayList<HitBox> colliders;
	
	public CollisionManager() {
		colliders = new ArrayList<HitBox>();
	}
	
	public void add(HitBox h) {
		colliders.add(h);
	}
	
	public void update() {
		for (HitBox h1 : colliders) {
			for (HitBox h2 : colliders) {
				// Don't let things collide with themselves
				if (h1 == h2 || h1.entity == h2.entity)
					continue;
				
				// Trigger onCollision() if HitBoxes overlap
				if (h1.intersect(h2)) {
					h1.entity.onCollision(h1, h2);
				}
			}
		}
	}
	
}
