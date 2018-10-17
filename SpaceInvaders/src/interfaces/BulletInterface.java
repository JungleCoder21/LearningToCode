package interfaces;

import java.awt.Graphics;

public interface BulletInterface {
	/**
	 * Interface which should define all characteristics for both enemy and player bullets
	 * Animation should define basic x y movement
	 */
	
	public void animation();
	
	public void update();
	
	public void draw(Graphics g);
}
