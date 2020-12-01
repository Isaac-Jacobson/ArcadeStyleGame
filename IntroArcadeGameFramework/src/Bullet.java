import processing.core.PApplet;


public class Bullet extends Thing {

	public Bullet(PApplet p, double x, double y, double xspeed, double yspeed,
			double xacceleration, double yacceleration) {
		super(p, x, y, xspeed, yspeed, xacceleration, yacceleration);
		// TODO Auto-generated constructor stub
	}

	public void move() {
		position.add(velocity);
	}
}
