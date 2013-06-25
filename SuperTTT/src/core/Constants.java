package core;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Constants {

	public static BufferedImage imgX,imgO;
	
	public static void init() {
		try {
			imgX = ImageIO.read(new File("X3.png"));
			imgO = ImageIO.read(new File("O3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
