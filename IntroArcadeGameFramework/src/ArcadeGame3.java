import java.awt.Container;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import processing.core.*;

public class ArcadeGame3 extends PApplet {
	ArrayList<Thing> blocks;
	ArrayList<Thing> stars;
	ArrayList<Thing> explosionList;
	
	PImage star;
	Thing player;
	
	boolean started = false;
	int time = 0;

	public void setup() {
		size(600, 600);

		blocks = new ArrayList<Thing>();
		stars = new ArrayList<Thing>();
		explosionList = new ArrayList<Thing>();
		
		player = new Thing(this, 300, 400, 0, 0, 0, 0);
	}

	@Override
	public void draw() {
		background(255);
		
		spawnNewStars();
		drawAndMoveStars();

		// Move player toward the mouse
		if (mouseX < player.getLeftSideX()) {
			player.moveLeft(14);
		} else if (mouseX > player.getRightSideX()) {
			player.moveRight(14);
		}
		
		// If game has started, add gravity
		if (started) player.addVelocity(new PVector(0, 0.3F));
		player.move();
		player.draw();
		
		// Check for collisions with stars
		collisionDetection();
		updateExplosions();
	}

	private void drawAndMoveStars() {
		for (int i = 0; i < stars.size(); i++) {
			Thing star = stars.get(i);
			
			if (star.getSpeed() > 2) {			// if it's going too fast, slow it down a bit
				star.accelerate(0.99);
			}
			
			star.move();						// move it and draw it
			star.draw();
		}
	}

	private void collisionDetection() {
		for (int i = 0; i < stars.size(); i++) {
			Thing star = stars.get(i);
			
			if (player.isHitting(star)) {								// if we hit a star
				started = true;
				addVelocityToAllStars(new PVector(0, (float) 0.3));		// accelerate them all downward
				player.setVelocity(new PVector(0, -10));				// give player velocity upward
				stars.remove(star);										// remove the star

				createExplosionAt(star);
			}
		}
	}

	private void updateExplosions() {
		for (int i = 0; i < explosionList.size(); i++) {
			Thing e = explosionList.get(i);
			
			e.addVelocity(new PVector(0, 0.1F));
			e.move();
			e.draw();
		}
	}
	
	private void createExplosionAt(Thing star) {
		for (int i = 0; i < 100; i++) {
			PVector force = PVector.random2D();
			force.mult((float) (Math.random()*10));
			Thing explosion = new Thing(this, star.getCenterX(), star.getCenterY(), 0, 0, 0, 0, 1, 1);
			explosion.color = color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
			explosion.setVelocity(force);
			explosionList.add(explosion);
		}
	}

	private void addVelocityToAllStars(PVector force) {
		for (int i = 0; i < stars.size(); i++) {
			Thing star = stars.get(i);
			star.addVelocity(force);
		}
	}

	private void spawnNewStars() {
		time++;
		if (time > 50) {
			Thing star = new Thing(this, 0, 0, 0, 0, 0, 0);
			star.setRandomPosition(0, 600, -20, -30);
			star.setVelocity(new PVector(0, 2));
			star.setImage(loadImage("c:\\data\\star.png"));
			star.setSize(50,  50);
			float rot = (float)(-0.15 + Math.random()*0.3);
			star.setRotationSpeed(rot);
			stars.add(star);
			time = 0;
		}
	}
}