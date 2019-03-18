package main.resources;

import main.Game;

import java.awt.*;
import java.util.Scanner;

public class Map {

	private String name;
	private int id;
	private int width;
	private int height;
	private String path;
	private Tile[][] tiles;
	private int offsetX;
	private int maxoffsetX;
	private int playerX, playerY;

	Map(String name, int id, String path) {
		this.name = name;
		this.id = id;
		this.path = path;
		loadMap();
	}

	private void loadMap() {
		Scanner in = ResourceLoader.loadTxt(path);
		this.width = in.nextInt();
		this.height = in.nextInt();
		maxoffsetX = this.width * Maps.defaultTileWidth - Game.width;
		tiles = new Tile[width][height];
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				tiles[x][y] = Tiles.getTile(in.nextInt());
		playerX = in.nextInt();
		playerY = in.nextInt();
	}

	public void update() {
		if ((Game.player.getX() - offsetX > Game.width * 0.35) && (offsetX < maxoffsetX))
			if (Game.player.getX() - offsetX > Game.width * 0.5)
				offsetX += 2;
			else
				offsetX += 1;
		if ((Game.player.getX() - offsetX < Game.width * 0.3) && (offsetX > 0))
			if (Game.player.getX() - offsetX < Game.width * 0.15)
				offsetX -= 2;
			else
				offsetX -= 1;
	}

	public void draw(Graphics g) {
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				g.drawImage(tiles[x][y].getTexture(), ((x * Maps.defaultTileWidth) - offsetX) * Game.scale, ((y * Maps.defaultTileHeight) * Game.scale), Maps.defaultTileWidth * Game.scale, Maps.defaultTileHeight * Game.scale, null);
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

	int getId() {
		return id;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffSetX(int i) {
		offsetX = i;
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public int getWidth() {
		return width * Maps.defaultTileWidth;
	}
}
