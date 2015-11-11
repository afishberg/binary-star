package com.github.binarystar.engine;

import java.util.*;

public class CollisionManager {

	private ArrayList<HitBox> colliders;
	private ArrayList<HitBox> toDelete;
	private ArrayList<HitBox> toAdd;
	
	public CollisionManager() {
		colliders = new ArrayList<HitBox>();
		toDelete = new ArrayList<HitBox>();
		toAdd = new ArrayList<HitBox>();
	}
	
	/**
	 * Add a HitBox to the collision manager
	 * @param h The HitBox to add
	 */
	public void add(HitBox h) {
		toAdd.add(h);
	}
	
	/**
	 * Remove a HitBox from the collision manager
	 * @param h The HitBox to remove
	 */
	public void remove(HitBox h) {
		toDelete.add(h);
	}
	
	public void update() {
		// Add new HitBoxes to main list
		for (HitBox h : toAdd) {
			colliders.add(h);
		}
		toAdd.clear();
		
		// Destroy HitBoxes marked for deletion
		for (HitBox h : toDelete) {
			colliders.remove(h);
		}
		toDelete.clear();
	}
	
	/**
	 * Manually test for a hit box collision
	 * @param h1 The HitBox to test
	 * @return The first HitBox found to be overlapping with h1
	 */
	public HitBox getOverlap(HitBox h1) {
		for (HitBox h2 : colliders) {
			// Don't let things collide with themselves
			if (h1 == h2 || h1.entity == h2.entity)
				continue;
			
			// Return first result
			if (h1.intersect(h2)) {
				return h2;
			}
		}
		return null;
	}
	
	/**
	 * Manually test for all HitBoxes overlapping with a single HitBox
	 * @param h1 The HitBox to test
	 * @return A list of HitBoxes that are overlapping with h1
	 */
	public ArrayList<HitBox> getAllOverlaps(HitBox h1) {
		ArrayList<HitBox> result = new ArrayList<HitBox>();
		for (HitBox h2 : colliders) {
			// Don't let things collide with themselves
			if (h1 == h2 || h1.entity == h2.entity)
				continue;
			
			// Add tag to result list
			if (h1.intersect(h2)) {
				result.add(h2);
			}
		}
		return result;
	}
}
