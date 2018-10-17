package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import interfaces.BulletInterface;

/**
 * These should be the standard bullets, nothing fancy
 * Going only on the y axis at 90 degrees(270 considering the inverted axis)
 * Other bullets can and will most probaby use the x axis also
 * @author acosere
 *
 */

public class PlayerBullet implements BulletInterface {
	ResourceLoader load;
	double x;
	private double y;
	int speed;
	double dy;
	double dx;
	boolean remove;
	BufferedImage img;
	public int bulletWidth;
	public int bulletHeight;
	
	public PlayerBullet(int x, int y, String path){
		this.x = x;
		this.y = y;
		load = new ResourceLoader();
		img = load.loadImage(path);
		bulletWidth = 5;
		bulletHeight = 10;
		speed = 10;		
	}

	@Override
	public void animation() {
		// TODO Auto-generated method stub
		dy = speed;
		dx = 0;
	}

	@Override
	public void update() {
		animation();
		// TODO Auto-generated method stub
		x += dx;
		y -= dy;
		if(y+bulletHeight<0){
			remove = true;
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, (int)x, (int)y, bulletWidth, bulletHeight, null);
	}
	
	//getters and setters
	
	public boolean getRemove(){return remove;}
	public double getx(){return x;}
	public void setx(int x){this.x = x;}
	
	
	public double gety(){return y;}
	public double getBulletWidth(){return bulletWidth;}
	public double getBulletHeight(){return bulletHeight;}
}
