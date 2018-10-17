package main;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class BackGround {
	ResourceLoader loader;
	BufferedImage img;
	BufferedImage img0;
	BufferedImage img1;
	Image tileBackGround;
	TileMapReader reader;
	
	int yCoord = -1200; //draw the full image  yCoord + ImageHeight = GamePanel.height
	
	public BackGround(String imagePath0, String imagePath1){
		loader = new ResourceLoader();
		img = loader.loadImage(imagePath0);
		img1 = loader.loadImage(imagePath1);
		reader = new TileMapReader();
		draw();
	}
	
	//constantly update bg position
	public void update(){
		yCoord ++;
		//System.out.println(yCoord);
	}
	
	public int getyCoord(){return yCoord;}
	
	/**
	 * Constructs the bg from tiles
	 * Keeps 2 extra yPosition tiles
	 * @param g
	 */
	
	public void draw(){
		tileBackGround = new BufferedImage(800, 2000, BufferedImage.TYPE_INT_ARGB);
		Graphics g = tileBackGround.getGraphics();
		for(int yPosn = 0;yPosn<63;yPosn++){
			for(int xPosn = 0;xPosn < 25; xPosn++){
				g.drawImage(img, xPosn*32, yPosn*32, null);
			}
		}
	}
	
	public void render(Graphics g){
		g.drawImage(tileBackGround, 0, yCoord, null);
	}
}
