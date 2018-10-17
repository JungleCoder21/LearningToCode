package main;

public class Rocket extends Bullet {

	public Rocket(double x, double y, int angle) {
		super(x, y, angle);
		// TODO Auto-generated constructor stub
	}
	
	public void setFiringModel(){
		speed = 7;
		
		double rad = Math.toRadians(angle);
		
		dy = Math.sin(rad) *speed;
		dx = Math.cos(rad) * speed;
		
		img = new ResourceLoader().loadImage("/res/life.png");
	}

}
