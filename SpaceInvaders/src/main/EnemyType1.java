package main;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class EnemyType1 {
ResourceLoader loader;
	
	int x;
	int y;  //enemy x y coordinates
	int width = 25;
	int height = 25 ;
	int dx;
	int dy;
	int speed = 3;
	
	boolean moveLeft;   //check this boolean to set movement either to left or to right
	boolean descend;	//used to check wether enemies should get closer to player
	boolean firing;     //add firing capabilities to random enemies;
	
	private long firingTimer;
	private long firingDelay;
	
	BufferedImage img;

	public boolean colided;
	
	public EnemyType1(int x,int y){
		loader = new ResourceLoader();
		
		this.x = x;
		this.y = y;
		
		dx = 1;  //adcanve by width/5
		dy = 0; //advances on the y axis by enemy height
		
		firingTimer = System.nanoTime();
		firingDelay = 300;
		
		
		img = loader.loadImage("/res/enemyShip.png");
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
	public boolean getLeft(){return moveLeft;}
	public boolean getColided(){return colided;}
	
	public void setLeft(boolean left){moveLeft = left;}
	public void setX(int x){this.x = x;}
	public void setY(int y){this.y = y;}
	public void setSpeed(int speed){this.speed = speed;}
	public void setDescend(boolean descend){this.descend = descend;}
	public void setFiring(boolean firing){this.firing = firing;}
	
	
	public void update(){
		//dy = 25;
		y += 2;
		
		if(firing){
			long elapsed = (System.nanoTime() - firingTimer)/1000000;
			if(elapsed > firingDelay){
				GamePanel.enemyBullets.add(new Bullet(x-12, y-12, 90));
				firingTimer = System.nanoTime();
			}
		}
	}
	

	public void draw(Graphics g){
		g.drawImage(img, x, y, width, height, null);
	}
}
