import processing.core.PApplet;


public class Cookie extends Thing {
	private int amount;  // how much fuel is in the cookie.
	
	public Cookie(PApplet p, double x, double y, double xspeed, double yspeed,
			double xacceleration, double yacceleration) {
		super(p, x, y, xspeed, yspeed, xacceleration, yacceleration);
		
		amount = 10;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void draw() {
		p.fill(p.color(0, 255, 0));
		p.ellipse(position.x, position.y, width, height);
	}
	
}
