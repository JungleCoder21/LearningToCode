package main;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ResourceLoader {
	BufferedImage img;
	public BufferedImage loadImage(String imagePath){
		try {
			img = ImageIO.read(getClass().getResource(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	
	
}
