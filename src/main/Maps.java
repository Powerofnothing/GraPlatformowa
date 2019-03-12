package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Maps {

	public static int defaultTileWidth = 16;
	public static int defaultTileHeight = 16;
	private static ArrayList<Map> maps;

	public static void init() {
		maps = new ArrayList<Map>();
		maps.add(new Map("Standard", 0, "/maps/map1.txt"));
	}

	public static Map getMap(int id) {
		for (Map map : maps) {
			if (map.getId() == id) {
				return map;
			}
		}
		return null;
	}

	public static void update() {
		if ((Player.getX() - Game.currentMap.getOffsetX() > Game.width * 0.33) && (Game.currentMap.getOffsetX() < Game.currentMap.getMaxOffsetX())) {
			if(Player.getX() - Game.currentMap.getOffsetX() > Game.width * 0.66) {
				Game.currentMap.setOffSetX(Game.currentMap.getOffsetX() + 2);
			} else {
				Game.currentMap.setOffSetX(Game.currentMap.getOffsetX() + 1);
			}
		}
		if ((Player.getX() - Game.currentMap.getOffsetX() < Game.width * 0.33) && (Game.currentMap.getOffsetX() > 0)) {
			if(Player.getX() - Game.currentMap.getOffsetX() < Game.width * 0.22) {
				Game.currentMap.setOffSetX(Game.currentMap.getOffsetX() - 2);
			} else {
				Game.currentMap.setOffSetX(Game.currentMap.getOffsetX() - 1);
			}
		}
	}

	public static void draw(Graphics g) {
		for (int y = 0; y < Game.currentMap.getHeight(); y++) {
			for (int x = 0; x < Game.currentMap.getWidth(); x++) {
				g.drawImage(Game.currentMap.getTile(x, y).getTexture(), ((x * defaultTileWidth) - Game.currentMap.getOffsetX()) * Game.scale, ((y * defaultTileHeight) * Game.scale), defaultTileWidth * Game.scale, defaultTileHeight * Game.scale, null);
			}
		}
	}
}

class Map {

	private String name;
	private int id;
	private int width;
	private int height;
	private String path;
	private Tile[][] tiles;
	private int offsetX;

	public Map(String name, int id, String path) {
		this.name = name;
		this.id = id;
		this.path = path;
		loadMap();
	}

	private void loadMap() {
		Scanner in = ResourceLoader.loadMap(path);
		this.width = in.nextInt();
		this.height = in.nextInt();
		tiles = new Tile[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Tiles.getTile(in.nextInt());
			}
		}
	}

	public int getMaxOffsetX() {
		return (this.width * Maps.defaultTileWidth - Game.width);
	}

	public Tile getTileByXY(double x, double y) {
		int xx = (int) x;
		int yy = (int) y;
		xx /= (Maps.defaultTileWidth);
		yy /= (Maps.defaultTileHeight);
		if (xx >= width)
			xx = width - 1;
		if (yy >= height)
			yy = height - 1;
		if (xx < 0)
			xx = 0;
		if (yy < 0)
			yy = 0;
		return tiles[xx][yy];
	}

	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setOffSetX(int i) {
		if (i > this.getMaxOffsetX())
			i = getMaxOffsetX();
		this.offsetX = i;
	}

	public int getOffsetX() {
		return offsetX;
	}

}
