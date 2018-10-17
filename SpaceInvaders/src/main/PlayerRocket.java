package main;

public class PlayerRocket extends PlayerBullet {
/**
 * Make the mehtods add x and y to the right posn according to the x,y of the player ship
 * this means substracting pixels from the base bullet constructor
 * @param x
 * @param y
 * @param path
 */
	public PlayerRocket(int x, int y, String path) {
		super(x, y, path);
		// TODO Auto-generated constructor stub
	}
	
	public void animation(){
		bulletWidth = 8;
		bulletHeight = 15;
		dy = 13;
	}
	

}
