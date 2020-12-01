import processing.core.PApplet;
import processing.core.PVector;

public class ArcadeGameBlank extends PApplet {
	
	Thing player;
	
	public void setup() {
		size(600, 600);
		
		player = new Thing(this, 300, 300, 0, 0, 0, 0, 20, 20);
	}
	
	public void draw() {
		background(255);
		
		PVector v = player.getDirectionToward(mouseX, mouseY);
		player.setVelocity(v);
		
		player.move();
		player.draw();
	}
	
	// runs whenever the mouse is released
	public void mouseReleased() {
		
	}
	
	// runs whenever a key is released
	public void keyReleased() {
		if (key == ' '); // do something
		if (key == 'a'); // do something
		// etc.
	}
}