package main;

import sun.misc.ClassLoaderUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

	public static Scanner loadMap(String path) {
		Scanner s = new Scanner(ResourceLoader.class.getResourceAsStream(path));
		return s;
	}

}