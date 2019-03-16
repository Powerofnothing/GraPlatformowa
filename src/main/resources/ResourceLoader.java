package main.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class ResourceLoader {

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ResourceLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	static Scanner loadTxt(String path) {
		Scanner s = new Scanner(ResourceLoader.class.getResourceAsStream(path));
		return s;
	}

}