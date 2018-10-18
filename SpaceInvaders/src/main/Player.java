package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Player {
	ResourceLoader loader;
	private HashMap<String, Boolean>PowerUp;
	int x;
	int y;
	int r;
	
	int bY; //background Y pos;
	int bY2 = GamePanel.HEIGHT;
	int continuousYposition; //keep the posn of By so to hardcode enemies
	
	int dx;
	int dy;
	int speed;
	
	int row = 0;
	int col = 0;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private boolean firing;
	private boolean rocketFiring;
	private boolean meteorFiring;
	private long firingRocketDelay;
	private long firingRocketTimer;
	private long firingMeteorDelay;
	private long firingMeteorTimer;
	private long firingTimer;
	private long firingDelay;
	
	private int lifeBar = 100;
	
	private int score;
	private BufferedImage img;
	private BufferedImage Bar;
	private BufferedImage playerLeft;
	private BufferedImage playerRight;
	private BufferedImage backGround;
	private BufferedImage rocketImage;
	private BufferedImage meteorImage;
	
	BulletSoundsFX bulletSound;
	int hartaCount = 0;
	
	
	private int[] harta = {
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		};
	public Player(){
		loader = new ResourceLoader();
		PowerUp = new HashMap<String,Boolean>();
		PowerUp.put("Rocket", false);
		PowerUp.put("Meteor", false);
		x = GamePanel.WIDTH/2;
		y = GamePanel.HEIGHT-100;
		r = 15;
		
		dx = 0;
		dy = 0;
		speed = 10;
		
		firing = false;
		firingTimer = System.nanoTime();
		firingRocketTimer = System.nanoTime();
		firingMeteorTimer = System.nanoTime();
		firingDelay = 200; //@5 shots/second
		firingRocketDelay = 300;
		firingMeteorDelay = 500;
		
		score = 0;
		img = loader.loadImage("/res/player.png");
		Bar = loader.loadImage("/res/LifeBar.png");
		playerLeft =  loader.loadImage("/res/playerLeft.png");
		playerRight =  loader.loadImage("/res/playerRight.png");
		backGround = loader.loadImage("/res/backgGround.png");
		rocketImage = loader.loadImage("/res/life.png");
		meteorImage = loader.loadImage("/res/enemyUFO.png");
		bulletSound = new BulletSoundsFX();
	}
	
	public int getbY(){return bY;}
	public int getbY2(){return bY2;}
	
	public int getLifeBar(){return lifeBar;}
	public void setLifeBar(int lifeBar){this.lifeBar = lifeBar;}
	
	public int getcontBY(){return continuousYposition;}
	
	public int getX(){return x;}
	public int getY(){return y;}
	public int getR(){return r;}
	public int getScore(){return score;}
	
	public void setLeft(boolean b){left = b;}
	public void setUp(boolean b){up = b;}
	public void setDown(boolean b){down = b;}
	
	public void setRight(boolean b){right = b;}
	public void setFiring(boolean b){firing = b;}
	public void setRocketFiring(boolean b){rocketFiring = b;}
	public boolean getRocketFiring(){return rocketFiring;}
	public void setMeteorFiring(boolean b){meteorFiring = b;}
	public boolean getMeteorFiring(){return meteorFiring;}
	
	public boolean getLeft(){return left;}
	
	public HashMap<String, Boolean> getPowerUp(){return PowerUp;}
	public void setPowerUp(HashMap<String, Boolean> powerUp){this.PowerUp = powerUp;}
	
	
	
	public void addScore(int i){
		score += i;
	}
	
	public void update(){
		if(left){
			dx = -speed;
		}if(right){
			dx = +speed;
		}
		if(up){
			dy = -speed;
		}else if(down){
			dy = +speed;
		}else{
			dy = 0;
		}
		x += dx;
		y += dy;
		if(x<r){x=r;}
		if(y<r){y=r;}
		if(x>GamePanel.WIDTH-r){x = GamePanel.WIDTH-r;}
		if(y>GamePanel.HEIGHT-r){y = GamePanel.HEIGHT-r;}
		dx = 0; //reset dx
		if(firing){
			long elapsed = (System.nanoTime() - firingTimer)/1000000;
			if(elapsed > firingDelay){
				GamePanel.playerBulletArray.add(new PlayerBullet(x+10, y,"/res/laserRed2.png"));
				GamePanel.playerBulletArray.add(new PlayerBullet(x-17, y,"/res/laserRed2.png"));
			//	bulletSound.startPlaying(1);
				firingTimer = System.nanoTime();
			}
		}
		
		if(rocketFiring && firing){
			long elapsed = (System.nanoTime() - firingRocketTimer)/1000000;
			if(elapsed > firingRocketDelay){
				GamePanel.playerBulletArray.add(new PlayerRocket(x+20, y,"/res/life.png"));
				GamePanel.playerBulletArray.add(new PlayerRocket(x-27, y,"/res/life.png"));
			//	bulletSound.startPlaying(1);
				firingRocketTimer = System.nanoTime();
			}
			
		} 
		if(meteorFiring && firing){
			long elapsed = (System.nanoTime() - firingMeteorTimer)/1000000;
			if(elapsed > firingMeteorDelay){
				PlayerUFO ufo1 = new PlayerUFO(x+20, y,"/res/enemyUFO.png",true);
				PlayerUFO ufo11 = new PlayerUFO(x, y,"/res/enemyUFO.png",true);
				PlayerUFO ufo12 = new PlayerUFO(x-20, y,"/res/enemyUFO.png",true);
				PlayerUFO ufo2 = new PlayerUFO(x-27, y,"/res/enemyUFO.png",false);
				PlayerUFO ufo21 = new PlayerUFO(x, y,"/res/enemyUFO.png",false);
				PlayerUFO ufo22 = new PlayerUFO(x+20, y,"/res/enemyUFO.png",false);
				GamePanel.playerBulletArray.add(ufo1);
				GamePanel.playerBulletArray.add(ufo11);
				GamePanel.playerBulletArray.add(ufo12);
				GamePanel.playerBulletArray.add(ufo2);
				GamePanel.playerBulletArray.add(ufo21);
				GamePanel.playerBulletArray.add(ufo22);
			//	bulletSound.startPlaying(1);
				firingMeteorTimer = System.nanoTime();
			}
		}
		if(bY2 == GamePanel.HEIGHT){
		bY2 = bY-GamePanel.HEIGHT;
		}
		if(bY == GamePanel.HEIGHT){
			bY = bY2- GamePanel.HEIGHT;
		}
		bY++;
		bY2++;
		continuousYposition ++; 
	}
	public void addPowerUp(){
		
	}
	
	public void drawLifeBar(Graphics g){
		for(int i=1;i<=lifeBar;i++){
			g.drawImage(Bar, 750, GamePanel.HEIGHT-i*8-5, null);
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.drawImage(backGround, 0, bY, GamePanel.WIDTH, GamePanel.HEIGHT,null);
		g.drawImage(backGround, 0, bY2, GamePanel.WIDTH, GamePanel.HEIGHT,null);
		if(left){
			g.drawImage(playerLeft, x-r, y-r, 2*r, 2*r, null, null); //this is for circle. switch to rectangle
		}else if(right){
			g.drawImage(playerRight, x-r, y-r, 2*r, 2*r, null, null);
		}else{
			g.drawImage(img, x-r, y-r, 2*r, 2*r, null, null);
		}
		if(rocketFiring){
			g.drawImage(rocketImage,10, 10, null);
		}
		
		drawLifeBar(g);
	}
}
