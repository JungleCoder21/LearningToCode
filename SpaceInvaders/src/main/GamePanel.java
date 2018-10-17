package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JPanel;
import inputs.KeyInputs;


public class GamePanel extends JPanel implements Runnable,MouseListener{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	Thread t;
	
	public static Player player;
	public BackGround backGround;
	
	private KeyInputs key;
	
	private ArrayList<Enemy> enemyArray;
	public static ArrayList<Bullet> enemyBullets;
	public static ArrayList<Explosion> explosionArray;
	public static ArrayList<PlayerBullet> playerBulletArray;
	public static HashMap<String, Integer> powerUpsMap;
	
	
	private boolean running;
	private boolean finishedGame;

	
	Font font = new Font("Serif",Font.BOLD,35);
	
	Image im;
	
	int score = 0;
	boolean playerHit;
	private volatile boolean isPaused = false;
	private volatile boolean gameOver = false;
	
	//for pause button to get brighter/darker
	private int G = 0;  //G value of RGB
	private int brighter = 1;
	
	int level;    //set new level (1-3)
	private long levelStartTimer;
	private long levelStartTimerDiff;
	private boolean levelStart;
	private int levelDelay = 4000;
	
	
	String enemyPath = "/res/enemyShip.png";
	String enemyPath2 = "/res/sampleShip2.png";
	BulletSoundsFX sound;
	TileMapReader reader;
	
	
	//32 tiles on each row!!!
	Integer citireNivel[] = {
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,1,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,2,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1,2,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,2,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
	};
	
	
	public boolean newGame;
	public boolean highScoreSelected;
	public boolean exitSelected;
	public boolean optionsSelected;
	
	Menu menu;
	
	public void addNotify(){
		super.addNotify();
		if(t == null){
			t = new Thread(this);
			t.start();
		}
	}
	
	public void restart(){
		level = 0; 
		score = 0;
		levelStartTimer = 0;
		levelStartTimerDiff = 0;
		levelDelay = 0;
		im = null;
		enemyArray.clear();
		playerBulletArray.clear();
		enemyBullets.clear();
		gameOver = false;
		isPaused = false;
		finishedGame = false;
		levelStart = true;
	}
	
	public synchronized void pauseGame(){
		//switch off gameUpdate
		isPaused = !isPaused;
	}
	
	public void resumeGame(){
		//start gameUpdate again
		isPaused = false;
	}
	
	public GamePanel(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));  // works with pack; difference vs setSize?
		player = new Player();
		key= new KeyInputs(player);
		addKeyListener(key);
		addMouseListener(this);
		setFocusable(true);
		requestFocus();
	
	}
	
	public void render(){
		im = createImage(800,800);
		Graphics2D g = (Graphics2D)im.getGraphics();
		
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHints(rh);
		
		if(newGame){
		//	backGround.render(g);
		player.draw(g);
		
		//g.drawImage(img, x, y, width, height, observer);
		g.setFont(new Font("pause/restart button", Font.BOLD, 20));
		g.drawString("Press P to Pause", 30, 30);
		g.drawString("Press R to Restart", 30, 50);
		
		if(finishedGame){
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawString("GOOD JOB!", 300, 300);
			g.drawString("Press R to restart", 300, 340);
		}
		
		for(int i=0;i<enemyArray.size();i++){
			enemyArray.get(i).draw(g);
		}
		
		//draw bullets
		if(playerBulletArray.size()>0){
		for(int i=0;i<playerBulletArray.size();i++){
			playerBulletArray.get(i).draw(g);
		}
		}
		
		//draw level number
		
		if(levelStartTimer != 0){
			g.setFont(new Font("Century Gothic",Font.PLAIN,18));
			String s = " - L E V E L - " + level;
			int length = (int)g.getFontMetrics().getStringBounds(s, g).getWidth();
			//transparency
			int alpha = (int)(255 *Math.sin(3.14*levelStartTimerDiff/levelDelay));
			if(alpha>255) alpha = 255;
			g.setColor(new Color(50,50,50,alpha));
			g.drawString(s, WIDTH/2 -length/2,HEIGHT/2);
		}
		
		if(enemyBullets.size()>0){
			for(int i=0;i<enemyBullets.size();i++){
				enemyBullets.get(i).draw(g);
			}
		}
		
		g.drawString(String.valueOf(score), 700, 50);
		
		
		Color color = new Color(G,G,G);
		g.setColor(color);
		
		if(playerHit){
			g.setFont(font);
			g.drawString("GAME OVER", 300, 500);
		}
		
		if(gameOver){
			g.setFont(font);
			g.drawString("GAME OVER", 300, 500);
		}
		
		if(isPaused){
			g.setFont(font);
			g.drawString("GAME PAUSED", 300, 500);
		}
	//	e.draw(g);
		
		if(G == 0){
			G += 15;
			brighter = 1;
		}else if(G == 255){
			G -= 15;
			brighter = 2;
		}else if(brighter == 2){
			G -= 15;
		}else if(brighter == 1){
			G += 15;
		}
		
		for(int i=0;i<explosionArray.size();i++){
		explosionArray.get(i).draw(g);
			}
		}else{
			menu.drawMenu(g);
		}
		
	}
	
	public void draw(){
		Graphics g = this.getGraphics();
		g.drawImage(im, 0, 0, null);
		g.dispose();
	}
	
	public void updateBullets(){
		//update bullets
	
		for(int i=0;i<playerBulletArray.size();i++){
			playerBulletArray.get(i).update();
			boolean remove = playerBulletArray.get(i).getRemove();
			if(remove){
				playerBulletArray.remove(i);
				i--;
			}
		}
	
		//update enemy bullets
		for(int i=0;i<enemyBullets.size();i++){
			enemyBullets.get(i).update();
			boolean remove = enemyBullets.get(i).getRemove();
			if(remove){
				enemyBullets.remove(i);
				i--;
			}
		}
		}
	
	public void loadLevels(){
		if(levelStartTimer == 0 && enemyArray.size() == 0){
			level ++;
			levelStart = false;
			levelStartTimer = System.nanoTime();
		}else{
			levelStartTimerDiff = (System.nanoTime() - levelStartTimer)/1000000;
			if(levelStartTimerDiff > levelDelay){
				levelStart = true;
				levelStartTimer = 0;
				levelStartTimerDiff = 0;
			}
		}
	}
	
	public void update(){
   		if(!isPaused && !finishedGame && !gameOver && newGame){  
	       	loadLevels();
	       	if(enemyArray.size()==0){
	       		createLevel1();
	       	}
	    //   	backGround.update();
			player.update();
			for(int i=0;i<enemyArray.size();i++){
				if(enemyArray.get(i).getPrize() == false && enemyArray.get(i).getY() >= 0){
				enemyArray.get(i).setFiring(true);
				}
				enemyArray.get(i).update();
			}
			
			
			updateBullets();
			checkCollisionAndEnemyOutOfBounds();
			checkPlayerEnemyBulletCollision();
			checkPlayerEnemyCollision();
			checkExplosionRemoveFlag();
			checkFinishedGame();
		}
	}
	
	
	public void checkExplosionRemoveFlag(){
		for(int i=0;i<explosionArray.size();i++){
			if(explosionArray.get(i).getRemove()){
				explosionArray.remove(i);
			}
		}
	}
	
	public void checkFinishedGame(){
		if(enemyArray.size() == 0 && level == 20){
			finishedGame = true;
		}
	}
	
	
	/**
	 * startRow represents the y
	 * on x Axis we have 32 tiles on each row
	 * for 25 rows(the max height for GamePanel) we have 625 total tiles available on the screen
	 * if i = 50(startRow = 2) we must still have a total of 625 printed tiles i<625 +startRow *25
	 */

	public void createLevel1(){
		for(int startRow = 0; startRow < citireNivel.length/32;startRow++){ // cate randuri sunt in total
			System.out.println(citireNivel.length/32);
			for(int i=0;i<32;i++){ //x este de la 0 la 32 pe fiecare rand
				if(citireNivel[startRow*32+i].equals(1)){
					enemyArray.add(new Enemy(i*32, -startRow*32,enemyPath));
				}else if(citireNivel[startRow*32+i].equals(2)){
					enemyArray.add(new EnemyType2(i*32, -startRow*32,enemyPath2));
				}
			}
		}
	}
	

	@Override
	public void run() {
		enemyArray = new ArrayList<Enemy>();
		playerBulletArray = new ArrayList<PlayerBullet>();
		enemyBullets = new ArrayList<Bullet>();
		explosionArray = new ArrayList<Explosion>();
		sound = new BulletSoundsFX();
		reader = new TileMapReader();
		menu = new Menu();
		powerUpsMap = new HashMap<String,Integer>();
	//	backGround = new BackGround("res/backgGround.png","/res/enemyShip.png");
		levelStart = true;
		levelStartTimer = 0;
		levelStartTimerDiff = 0;
		level = 0;
		
		running = true;
		long startTime;
		long URDtime;
		long sleepTime;
		long totalTime = 0;
		int period = 20; //sleep 20 ms for 50 FPS
		while(running){
			startTime = System.nanoTime();
			update();
			render();
			draw();
			URDtime = System.nanoTime() - startTime;
			totalTime += URDtime;
			if(totalTime/1000000>1 && !isPaused &&!finishedGame){
				totalTime = 0;
			}
			sleepTime = period - URDtime/1000000;
			try{
				Thread.sleep(sleepTime);
			}catch(Exception ex){
				System.out.println("sleep error");
			}
		}
	}
	
	public void checkCollisionAndEnemyOutOfBounds(){
		//checks wether enemy  is hit by a bullet or enemy is out of bounds
		for(int i=0;i<playerBulletArray.size();i++){
			PlayerBullet b = playerBulletArray.get(i);
			double bx = b.getx();
			double by = b.gety();
				for(int j=0;j<enemyArray.size();j++){
					
					//stop checking for all enemies, check regarding bullet Y and enemy Y
					//if player bullet Y < enemy Y it means the bullet has surpassed the enemy
					Enemy e = enemyArray.get(j);
					int ex = e.getX();
					int ey = e.getY();
					if(ey > GamePanel.HEIGHT){
						enemyArray.remove(j);
						j--;
						continue;
					}else if((bx+b.getBulletWidth()>= ex) &&(bx<=ex+25)&&(by+b.bulletHeight>=ey)&&(by<=ey+25) && e.getPrize() == false){
						playerBulletArray.remove(i);  
						Explosion explosion = new Explosion(ex,ey);
						explosionArray.add(explosion);
						if(e.getLifePoints() == 1 ){ //bullet hits when it has 1 lifePoint ==> goes auto to 0 lifePoints
							double rand = Math.random();
							if(rand < 0.2){
								e.setPrize(true);
								e.setFiring(false);
								score += 150;
								i--;
								break;
							}else if(rand >0.2){
								e.setPrize2(true);
								e.setFiring(false);
								score += 150;
								i--;
								break;
							}
						enemyArray.remove(j);
						score += 150;
						i--;
						j--;
						break;
						}else{
							e.setLifePoints(e.getLifePoints()-1);
							i--;
							break;
						}
					}
				}
				continue;
		}
	}
	
	public void checkPlayerEnemyCollision(){
		for(int i=0;i<enemyArray.size();i++){
			if(enemyArray.get(i).playerCollision(player)){
				if(enemyArray.get(i).getPrize() == true){
					powerUpsMap.put("rocket", 1);
				}else if(enemyArray.get(i).getPrize2() == true){
					powerUpsMap.put("meteor", 2);
				}
				else {
					player.setLifeBar(player.getLifeBar()-3);
				}
				enemyArray.remove(i);
				i--;
				if(player.getLifeBar() <= 0){
					explosionArray.add(new Explosion(player.getX(), player.getY()));
					explosionArray.add(new Explosion(player.getX()+10, player.getY()-10));
					explosionArray.add(new Explosion(player.getX()-10, player.getY()-10));
					explosionArray.add(new Explosion(player.getX()+10, player.getY()+10));
					explosionArray.add(new Explosion(player.getX()-10, player.getY()+10));
					gameOver =  true;
				}
			}
		}
	}
	
	
	public void checkPlayerEnemyBulletCollision(){
		// check if an enemy bullet hits the player; game finishes if so
		//add loose life system
		for(int i=0;i<enemyBullets.size();i++){
			Bullet b = enemyBullets.get(i);
			double bx = b.getx();
			double by = b.gety();
			if((bx+8>player.getX()-15) && (bx<player.getX()+15) && (by+8>player.getY()) && by<player.getY()+15){
				enemyBullets.remove(i);
				i--;
				player.setLifeBar(player.getLifeBar()-1);
				if(player.getLifeBar() <= 0){
					gameOver =  true;
				}
			}
		}
	}
	
	

	@Override
/*	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT){
			player.setLeft(true);
		}
		if(key == KeyEvent.VK_RIGHT){
			player.setRight(true);
		}
		if(key == KeyEvent.VK_DOWN){
			player.setDown(true);
		}
		if(key == KeyEvent.VK_UP){
			player.setUp(true);
		}
		
		if(key == KeyEvent.VK_SPACE){
			player.setFiring(true);
			sound.startPlaying(Clip.LOOP_CONTINUOUSLY);
		}
		if(key == KeyEvent.VK_P){
			pauseGame();
		}
		
		if(key == KeyEvent.VK_R){
			restart();
		}
		if(key == KeyEvent.VK_CONTROL){
			player.setRocketFiring(!player.getRocketFiring());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT){
			player.setLeft(false);
		}
		if(key == KeyEvent.VK_RIGHT){
			player.setRight(false);
		}
		if(key == KeyEvent.VK_DOWN){
			player.setDown(false);
		}
		if(key == KeyEvent.VK_UP){
			player.setUp(false);
		}
		if(key == KeyEvent.VK_SPACE){
			player.setFiring(false);
			//sound.stopPlaying();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	} */

	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getPoint());
		if(e.getX()>=55 && e.getX() <=313 && e.getY() >=103 && e.getY()<=152 ){
			newGame = true;
		}
		if(e.getX()>=30 && e.getX()<= 304 && e.getY()>= 281 && e.getY()<=329){
			System.exit(0);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
