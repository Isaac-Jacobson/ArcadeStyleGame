import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import processing.core.*;

public class ArcadeGame extends PApplet {
	int width = 600, height = 800;
	Ship ship;
	Spawner spawner;
	ArrayList<Bullet> bullets;
	ArrayList<Cookie> cookies;
	
	PFont bigfont, smallfont;

	int counter = 80;

	public void setup() {
		size(width, height);

		bigfont = createFont("Georgia", 32);
		smallfont = createFont("Georgia", 12);
		
		bullets = new ArrayList<Bullet>();
		cookies = new ArrayList<Cookie>();

		ship = new Ship(this, width / 2, height / 2, 0, 0, 0, 0);
		PImage img = loadImage("ship.png");
		img.resize(100, 100);
		ship.setImage(img);
		spawner = new Spawner(this);
		
		textFont(smallfont);
	}

	@Override
	public void draw() {
		background(200);
		
		fill(0);
		stroke(0);
		text("Fuel: " + ship.getFuel(), width - 200, 60);
		
		ship.draw();

		if (ship.getFuel() > 1) {
			ship.move();
		} else {
			text("GAME OVER", width/2, height/2);
		}

		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			b.move();
			b.draw();
		}

		for (int i = 0; i < cookies.size(); i++) {
			Cookie b = cookies.get(i);
			b.move();
			b.draw();
			if (ship.isHitting(b)) {
				ship.setFuel(ship.getFuel() + b.getAmount());
				cookies.remove(b);
			}
		}

		if (ship.getFuel() > 1) {
			counter--;
			if (counter < 1) {
				Cookie c = spawner.spawn();
				cookies.add(c);
				counter = 80;
			}
		}
		updateKeys();
	}

	private void updateKeys() {

		if (checkKeyPressed(KeyEvent.VK_LEFT)) {
			ship.rotate(-4);
		}
		if (checkKeyPressed(KeyEvent.VK_RIGHT)) {
			ship.rotate(4);
		}
		if (checkKeyPressed(KeyEvent.VK_UP)) {
			ship.accelerateInDirection(0.1F, ship.getAngle());
		}
		if (checkKeyPressed(KeyEvent.VK_DOWN)) {
			ship.accelerateInDirection(-0.1F, ship.getAngle());
		}
		if (checkKeyPressed(KeyEvent.VK_SPACE)) {
			Bullet b = ship.shoot();
			bullets.add(b);
		}
	}

	// Handling multiple simultanious key presses
	public Set<Integer> keysPressed = new HashSet<Integer>();

	public boolean checkKeyPressed(int keycode) {
		return this.keysPressed.contains(keycode);
	}

	public void keyPressed() {
		// add key to the list of keys held down
		// with processing, the KeyEvent object is always available as
		// "keyEvent",
		// the getKeyChar() is already in the variable 'key', and getKeyCode()
		// is in the variable 'keyCode'.
		this.keysPressed.add(keyCode);

		// println("keys in current held list: " + this.keysPressed.toString());
	}

	public void keyReleased() {
		// remove key from the list of keys held down
		this.keysPressed.remove(keyCode);
	}

}