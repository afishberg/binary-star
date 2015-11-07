package com.github.binarystar.engine;

import com.github.binarystar.Main;

import processing.core.*;

public class HitBox extends Component {

	public Rectangle box;
	public boolean hit;
	private Transform transform;
	
	private int offsetX, offsetY;
	
	public String tag;
	
	public HitBox(int offsetX, int offsetY, int w, int h, String tag) {
		box = new Rectangle(new PVector(), w, h);
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.tag = tag;
	}
	
	public void start() {
		this.transform = entity.getComponent(Transform.class);
		Main.Collisions.add(this);
	}
	
	public void destroy() {
		Main.Collisions.remove(this);
	}

	public void update(float deltaTime) {
		PVector off = new PVector(offsetX * (float)Math.cos(transform.rotation) 
										- offsetY * (float)Math.sin(transform.rotation), 
								  offsetY * (float)Math.cos(transform.rotation) 
								  		+ offsetX * (float)Math.sin(transform.rotation));
		box.updateTransform(PVector.add(transform.position, off), transform.rotation);
	}
	
	// Uses separating axis theorem to test for whether two HitBoxes overlap
	public boolean intersect(HitBox other) {
		Rectangle r1 = this.box;
		Rectangle r2 = other.box;
		PVector[] axes1 = r1.getAxes();
		PVector[] axes2 = r2.getAxes();
		
		for (int i = 0; i < axes1.length; i++) {
			PVector axis = axes1[i];
			if (!projectionsIntersect(r1.project(axis), r2.project(axis))) {
				return false;
			}
		}
		
		for (int i = 0; i < axes2.length; i++) {
			PVector axis = axes2[i];
			if (!projectionsIntersect(r1.project(axis), r2.project(axis))) {
				return false;
			}
		}
		
		return true;
	}

	public boolean projectionsIntersect(float[] p1, float[] p2) {
		return p1[0]>=p2[0] && p1[0]<=p2[1] || p2[0]>=p1[0] && p2[0]<=p1[1];
	}
	
	public void render() {
		// Debug drawing each rectangle point
		Main.Processing.pushMatrix();
		Main.Processing.ellipse(box.vertices[0].x, box.vertices[0].y, 10, 10);
		Main.Processing.ellipse(box.vertices[1].x, box.vertices[1].y, 10, 10);
		Main.Processing.ellipse(box.vertices[2].x, box.vertices[2].y, 10, 10);
		Main.Processing.ellipse(box.vertices[3].x, box.vertices[3].y, 10, 10);
		Main.Processing.popMatrix();
	}
	
	
	private class Rectangle {
		public PVector[] vertices;
		public float width, height;
		
		public Rectangle(PVector p, int w, int h) {
			this.width = w;
			this.height = h;
			vertices = new PVector[4];
			updateTransform(p, 0);
		}

		public void updateTransform(PVector p, float r) {
			// Should have probably used matrices here instead
			// oh well
			vertices[0] = new PVector(p.x + width/2f * (float)Math.cos(r) - height/2f * (float)Math.sin(r), 
									  p.y + height/2f * (float)Math.cos(r) + width/2f * (float)Math.sin(r));
			vertices[1] = new PVector(p.x - width/2f * (float)Math.cos(r) - height/2f * (float)Math.sin(r), 
									  p.y + height/2f * (float)Math.cos(r) - width/2f * (float)Math.sin(r));
			vertices[2] = new PVector(p.x - width/2f * (float)Math.cos(r) + height/2f * (float)Math.sin(r), 
									  p.y - height/2f * (float)Math.cos(r) - width/2f * (float)Math.sin(r));
			vertices[3] = new PVector(p.x + width/2f * (float)Math.cos(r) + height/2f * (float)Math.sin(r), 
									  p.y - height/2f * (float)Math.cos(r) + width/2f * (float)Math.sin(r));
		}
		
		// Returns an array of Rectangle's edge normals
		public PVector[] getAxes() {
			PVector[] axes = new PVector[4];
			for (int i = 0; i < 4; i++) {
				PVector edge = PVector.sub(vertices[i], vertices[i == 3 ? 0 : i + 1]);
				PVector normal = new PVector(edge.y, -edge.x).normalize();
				axes[i] = normal;
			}
			return axes;
		}
		
		// Projects the Rectangle onto a normal vector
		public float[] project(PVector axis) {
			float[] minMax = new float[2];
			minMax[0] = PVector.dot(axis, vertices[0]); // Min
			minMax[1] = minMax[0];						// Max
			
			for (int i = 1; i < 4; i++) {
				float p = PVector.dot(axis, vertices[i]);
				if (p < minMax[0])
					minMax[0] = p;
				else if (p > minMax[1])
					minMax[1] = p;
			}
			return minMax;
		}
	}
}
