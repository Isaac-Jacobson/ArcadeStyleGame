import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import processing.core.*;

public class ArcadeGame2 extends PApplet {
	ArrayList<Thing> blocks;
	Thing player;

	public void setup() {
		size(600, 600);

		blocks = new ArrayList<Thing>();
		player = new Thing(this, 300, 300, 0, 0, 0, 0);
		
		createLevel1Blocks();
	}

	private void createLevel1Blocks() {
		for (int x = 0; x < 500; x = x + 100) {
			Thing block = new Thing(this, x, 400, 0, 0, 0, 0, 100, 15);
			block.color = color(30, 60, 200);
			
			blocks.add(block);
		}
	}

	@Override
	public void draw() {
		background(200);

		player.draw();
		player.addVelocity(new PVector(0, 0.3F)); 		// add gravity
		player.move();

		checkForBlockCollisions();
		checkForCollisionWithInvisibleGround(580);

		for (Thing block : blocks) {
			block.draw();
		}

		updateKeys();
	}

	private void checkForCollisionWithInvisibleGround(int groundLevel) {
		if (player.getYNextStep() + player.height > groundLevel) {
			player.setVelocity(new PVector(0, 0));
			player.setBottomYValue(groundLevel + 1);
		}
	}

	private void checkForBlockCollisions() {
		for (int i = 0; i < blocks.size(); i++) {
			Thing b = blocks.get(i);

			if (player.willHitNextStep(b)) {
				if (player.isMovingDownish()) {
					player.setVelocity(new PVector(0, 0));
					player.setBottomYValue(b.getTopSideY() - 1);
				} else if (player.isMovingUpish()) {
					player.setVelocity(new PVector(0, 0));
					player.setTopYValue(b.getBottomSideY() + 1);
				}
			}
		}
	}

	public void mouseReleased() {

	}

	private void updateKeys() {

		if (checkKeyPressed(KeyEvent.VK_LEFT)) {
			player.moveLeft(4);
		}
		if (checkKeyPressed(KeyEvent.VK_RIGHT)) {
			player.moveRight(4);
		}

		// Space key is detected in the keyReleased method.
	}

	// Handling multiple simultanious key presses
	public Set<Integer> keysPressed = new HashSet<Integer>();

	public boolean checkKeyPressed(int keycode) {
		return this.keysPressed.contains(keycode);
	}

	public void keyPressed() {
		this.keysPressed.add(keyCode);
	}

	public void keyReleased() {

		if (checkKeyPressed(KeyEvent.VK_SPACE)) {
			player.setVelocity(new PVector(0, -8));
		}

		this.keysPressed.remove(keyCode);
	}

}