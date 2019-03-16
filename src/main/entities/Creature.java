package main.entities;

import main.Game;
import main.resources.ResourceLoader;

public class Creature extends Entity {

	private int id;
	private double x1, x2, vx, vy;
	private int hp;

	public Creature(int id, int hp, double x1, double x2, double y, double vx, String path) {
		this.id = id;
		this.x1 = x1;
		this.x2 = x2;
		this.vx = vx;
		this.hp = hp;
		setX(x1);
		setY(y);
		setTexture(ResourceLoader.loadImage(path));
	}

	public void update() {
		if (!Game.currentMap.getTileByXY(Math.floor(getX()), Math.floor(getY()) + getTexture().getHeight()).isSolid() && !Game.currentMap.getTileByXY(Math.floor(getX()) - 1 + getTexture().getWidth(), Math.floor(getY()) + getTexture().getHeight()).isSolid()) {
			if (vy < 3)
				vy += 0.2;
		}
		if (Game.currentMap.getTileByXY(Math.floor(getX()), Math.floor(getY()) + getTexture().getHeight()).isSolid() || Game.currentMap.getTileByXY(Math.floor(getX()) - 1 + getTexture().getWidth(), Math.floor(getY()) + getTexture().getHeight()).isSolid()) {
			vy = 0;
		}
		if(getX() > x2) {
			vx = - Math.abs(vx);
		} else if (getX() < x1) {
			vx = Math.abs(vx);
		}
		setX(getX() + vx);
		setY(getY() + vy);
	}

	public int getId() {
		return id;
	}

}
