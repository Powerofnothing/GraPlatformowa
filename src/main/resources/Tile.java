package main.resources;

import java.awt.image.BufferedImage;

public class Tile {

	private String name;
	private int id;
	private BufferedImage texture;
	private boolean solid;

	Tile(String name, int id, BufferedImage texture, boolean solid) {
		this.name = name;
		this.id = id;
		this.texture = texture;
		this.solid = solid;
	}

	int getId() {
		return id;
	}

	BufferedImage getTexture() {
		return texture;
	}

	public boolean isSolid() {
		return solid;
	}

}
