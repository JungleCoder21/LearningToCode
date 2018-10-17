package interfaces;

import java.awt.Graphics;

import main.Bullet;
import main.Player;

public interface EnemyInterface {
	
	/**
	 * Define the move pattern
	 * Add special patterns for more complex enemies
	 */
	public void movement();
	
	
	/**
	 * describe how the enemy should shoot
	 */
	public void shoot();
	
	/**
	 * update the enemy Position
	 */
	
	public void update();
	
	
	/**
	 * draw the enemy to the screen or an off-screen image
	 * @param g
	 */
	public void draw(Graphics g);
	
	/**
	 * check if enemy and player collide
	 * @param p
	 */
	public void playerCollision(Player p);
	
	/**
	 * check if enemy has been hit by a player bullet
	 * @param b
	 */
	public void bulletCollision(Bullet b);
}
