import java.util.Random;

import processing.core.PApplet;

public class Spawner {
	private PApplet p;
	
	public Spawner(PApplet p) {
		this.p = p;
	}
	
	// create a new cookie at a random location.
	public Cookie spawn() {
		Random r = new Random();
		int x = r.nextInt(p.width);
		int y = r.nextInt(p.height);
		Cookie c = new Cookie(p, x, y, 0, 0, 0, 0);
		
		c.setSize(20,  20);
		
		return c;
	}
}
