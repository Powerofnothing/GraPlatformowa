package main.resources;

import java.util.ArrayList;

public class Tiles {

	private static ArrayList<Tile> tiles;

	public static void init() {
		tiles = new ArrayList<>();
		tiles.add(new Tile("sky1", 0, ResourceLoader.loadImage("/textures/sky1.png"), false));
		tiles.add(new Tile("dirt1", 1, ResourceLoader.loadImage("/textures/dirt1.png"), true));
		tiles.add(new Tile("dirt2", 2, ResourceLoader.loadImage("/textures/dirt2.png"), true));
		tiles.add(new Tile("dirt3", 3, ResourceLoader.loadImage("/textures/dirt3.png"), false));
		tiles.add(new Tile("dirt4", 4, ResourceLoader.loadImage("/textures/dirt4.png"), false));
	}

	static Tile getTile(int id) {
		for (Tile tile : tiles)
			if (tile.getId() == id)
				return tile;
		return null;
	}

}

