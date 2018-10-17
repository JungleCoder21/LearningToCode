package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class Enemy {
	ResourceLoader loader;
	
	int x;
	int y;  //enemy x y coordinates
	public static int width = 25;
	public static int height = 25 ;
	int dy;
	int speed = 3;
	int lifePoints;
	
	boolean firing;     //add firing capabilities to random enemies;
		
	private long firingTimer;
	private long firingDelay;
	boolean prize;
	boolean prize2;
	
	BufferedImage img;
	BufferedImage img2;
	BufferedImage powerUp;
	BufferedImage powerUp2;

	public boolean colided;
	
	public Enemy(int x,int y,String enemyPath){
		loader = new ResourceLoader();
		
		this.x = x;
		this.y = y;
		
		dy = speed; //advances on the y axis by enemy height
		
		firingTimer = System.nanoTime();
		firingDelay = 2000;
		lifePoints = 3;
		
		
		img = loader.loadImage(enemyPath);
		powerUp = loader.loadImage("/res/life.png");
		powerUp2 = loader.loadImage("/res/enemyUFO.png");
	}
	
	public boolean playerCollision(Player p){
		if((x+width>=p.getX()-15) && (x<=p.getX()+15) && (y+height>=p.getY()) && (y<=p.getY()+15)){
			colided = true;
		}
		return colided;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	public int getSpeed(){return speed;}
	public boolean getPrize(){return prize;}
	
	public void setPrize(boolean b){prize = b;}
	
	public boolean getPrize2(){return prize2;}
	
	public void setPrize2(boolean b){prize2 = b;}
	
	public boolean getColided(){return colided;}
	
	public void setX(int x){this.x = x;}
	public void setY(int y){this.y = y;}
	public void setSpeed(int speed){this.speed = speed;}
	public void setFiring(boolean firing){this.firing = firing;}
	
	public int getLifePoints(){return lifePoints;}
	public void setLifePoints(int lifePoints){this.lifePoints = lifePoints;}
	
	public void update(){	
		if(!prize && !prize2){
			y += dy;
		}else {
			y += 1;
		}
		
		
		if(firing){
			long elapsed = (System.nanoTime() - firingTimer)/1000000;
			if(elapsed > firingDelay){
				addBullets();
				firingTimer = System.nanoTime();
			}
		}
		
		
	}
	
	public void addBullets(){
		GamePanel.enemyBullets.add(new Bullet(x-12, y-12, 90));
		GamePanel.enemyBullets.add(new Bullet(x-12, y, 90));
	}

	public void draw(Graphics g){
		if(!prize && y>-height && !prize2){
		g.drawImage(img, x, y, width, height, null);
		}else if(prize && y>-height){
			g.drawImage(powerUp, x, y, 25, 25, null);
		}else if(prize2 && y>-height){
			g.drawImage(powerUp2, x, y, 25, 25, null);
		}
	}
}
