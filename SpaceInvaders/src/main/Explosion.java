package main;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class Explosion implements Runnable{
	ResourceLoader loader;
	int x;   //get x and y from enemy posN;
	int y;
	
	BufferedImage img;
	
	boolean remove;
	
	public Explosion(int x, int y){
		this.x = x;
		this.y = y;
		loader = new ResourceLoader();
		img = loader.loadImage("/res/laserRedShot.png");
	}
	
	public void setX(int x){this.x = x;}
	public void setY(int y){this.y = y;}
	public void setRemove(boolean remove){this.remove = remove;}
	
	
	public boolean getRemove(){return remove;}
	
	
	public void draw(Graphics g){
		long frameTime = System.nanoTime();
		g.drawImage(img, x, y, null);
		remove = true;
		
	}

	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		
	}
}
