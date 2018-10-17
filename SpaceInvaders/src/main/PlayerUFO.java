package main;

public class PlayerUFO extends PlayerBullet{
	
	boolean firing;
	long firingTimer;
	long firingDelay;
	private int bulletInitialX;
	private int bulletgettoX;

	public PlayerUFO(int x, int y, String path, boolean whereTo) {
		super(x, y, path);
		// TODO Auto-generated constructor stub
		firingTimer = System.nanoTime();
		firingDelay = 200;
		bulletInitialX = x;
		if(whereTo == true){
		bulletgettoX = x + 50;
		}else{
			bulletgettoX = x-50;
		}
	}
	
	public void animation(){
		bulletWidth = 8;
		bulletHeight = 15;
		if(x < bulletgettoX){
			dx = 10;
			dy = 0;
		}else if(x>bulletgettoX){
			dx = - 10;
			dy = 0;
		}
		else{
			dx = 0;
			dy = 20;
		}
	}
	
	public double getInitialx(){return bulletInitialX;}
	public void setInitialx(int x){this.bulletInitialX = x;}
}
