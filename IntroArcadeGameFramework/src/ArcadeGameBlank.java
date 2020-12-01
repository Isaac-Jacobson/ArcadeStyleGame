import processing.core.PApplet;
import processing.core.PVector;

public class ArcadeGameBlank extends PApplet {
	Thing player;

	public void setup() {
		size(600, 600);

		player = new Thing(this, 300, 300, 0, 0, 0, 0, 20, 20);
		player.setRotationSpeed((float)-0.05);
	}

	public void draw() {
		background(255);

		player.move();
		player.draw();
	}

	// runs whenever the mouse is released
	public void mouseReleased() {
	
	}

	public void keyPressed() {
		if (key == CODED) {
			if (keyCode == UP) {
				// what happens when they press up
			}
			
			if (keyCode == DOWN) {
				// what happens when they press up
			}
			
			if (keyCode == RIGHT) {
				// what happens when they press up
			}
			
			if (keyCode == LEFT) {
				// what happens when they press up
			}
		}
	}
	
	// runs whenever a key is released
	public void keyReleased() {
		if (key == ' ') {
			// do something
		}

		if (key == 'a') {
			// do something
		}
		
	}
}