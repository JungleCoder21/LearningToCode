package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class Bullet {
	ResourceLoader load;
	int angle;
	private double x;
	private double y;
	int speed;
	double dx;
	double dy;
	boolean remove;
	BufferedImage img;
	
	public Bullet(double x, double y, int angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
		setFiringModel();
	}
	
	
	public void setFiringModel(){
		speed = 10;
		
		double rad = Math.toRadians(angle);
		
		dy = Math.sin(rad) *speed;
		dx = Math.cos(rad) * speed;
		
		img = new ResourceLoader().loadImage("/res/laserRed.png");
	}
	
	public double getx(){return x;}
	public double gety(){return y;}
	
	public void setDy(int dy){this.dy = dy;}
	
	public void update(){
		x += dx;
		y += dy;
		if(x<0 || x > GamePanel.WIDTH){
			remove = true;
		}
		if(y<0 || y> GamePanel.HEIGHT){
			remove = true;
		}
	}
	
	
	public boolean getRemove(){return remove;}
	
	
	public void draw(Graphics g){
		g.setColor(Color.red);
		g.drawImage(img, (int)x, (int)y, 8, 8, null);
	}
	
	
}