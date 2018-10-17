package main;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Menu {
	ResourceLoader loader;
	BufferedImage menu;
	
	public Menu(){
		loader = new ResourceLoader();
		menu = loader.loadImage("/res/Menu.png");
	}
	
	
	public void drawMenu(Graphics g){
		g.drawImage(menu, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
	}
}
