import processing.core.PApplet;
import processing.core.PVector;

public class Ship extends Thing {
	private int fuel;
	
	public Ship(PApplet p, double x, double y, double xspeed, double yspeed,
			double xacceleration, double yacceleration) {
		super(p, x, y, xspeed, yspeed, xacceleration, yacceleration);

		fuel = 1000;
	}
	
	public void move() {
		position.add(velocity);
		if (isOffScreen()) {
			if (this.getRightSideX() < 0) this.setLeftXValue(p.width);
			if (this.getLeftSideX() > p.width) this.setRightXValue(0);
			if (this.getTopSideY() > p.height) this.setBottomYValue(0);
			if (this.getBottomSideY() < 0)	this.setTopYValue(p.height);
		}
		
		fuel = fuel - 1;
	}

	public Bullet shoot() {
		// Create the bullet.		
		PVector bv = PVector.fromAngle((float) this.angle);
		bv.setMag(10);  // sets speed to 10
		
		// PApplet, x, y, xspeed, yspeed, 0, 0
		Bullet b = new Bullet(p, this.getCenterX(), this.getCenterY(), bv.x, bv.y, 0, 0);
		b.setSize(2,  2);
		b.angle = angle;
		
		return b;
	}
	
	public int getFuel() {
		return fuel;
	}
	
	public void setFuel(int amount) {
		fuel = fuel + amount;
	}
}











