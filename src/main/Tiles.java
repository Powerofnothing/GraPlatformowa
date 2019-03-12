package main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tiles {

	private static ArrayList<Tile> tiles;

	public static void init() {
		tiles = new ArrayList<Tile>();
		tiles.add(new Tile("sky1", 0, ResourceLoader.loadImage("/textures/sky1.png"), false));
		tiles.add(new Tile("dirt1", 1, ResourceLoader.loadImage("/textures/dirt1.png"), true));
		tiles.add(new Tile("dirt2", 2, ResourceLoader.loadImage("/textures/dirt2.png"), true));
		tiles.add(new Tile("dirt3", 3, ResourceLoader.loadImage("/textures/dirt3.png"), false));
		tiles.add(new Tile("dirt4", 4, ResourceLoader.loadImage("/textures/dirt4.png"), false));
	}

	public static Tile getTile(int id) {
		for (Tile tile : tiles) {
			if (tile.getId() == id) {
				return tile;
			}
		}
		return null;
	}

}

class Tile {

	private String name;
	private int id;
	private BufferedImage texture;
	private boolean solid;

	public Tile(String name, int id, BufferedImage texture, boolean solid) {
		this.name = name;
		this.id = id;
		this.texture = texture;
		this.solid = solid;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public boolean isSolid() {
		return solid;
	}

}
