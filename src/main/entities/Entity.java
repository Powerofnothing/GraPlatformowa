package main.entities;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

	private BufferedImage texture;
	private double x, y;

	public void draw(Graphics g) {
		g.drawImage(texture, ((int) x - Game.currentMap.getOffsetX()) * Game.scale, ((int) y) * Game.scale, texture.getWidth() * Game.scale, texture.getHeight() * Game.scale, null);
	}

	// 0 - N, 1 - E, 2 - S, 3 - W
	boolean checkMapCollision(int dir, double vx, double vy) {
		switch (dir) {
			case 0:
				if (Game.currentMap.getTileByXY(Math.floor(getX()), Math.floor(getY()) + vy).isSolid() || Game.currentMap.getTileByXY(Math.floor(getX()) - 1 + getTexture().getWidth(), getY() + vy).isSolid()) {
					return true;
				}
				break;
			case 1:
				if (Game.currentMap.getTileByXY(Math.floor(getX()) + vx + getTexture().getWidth(), getY()).isSolid() || Game.currentMap.getTileByXY(Math.floor(getX()) + vx + getTexture().getWidth(), Math.floor(getY()) - 2 + getTexture().getHeight()).isSolid()) {
					return true;
				}
				break;
			case 2:
				if (Game.currentMap.getTileByXY(Math.floor(getX()), (getY() + vy) + getTexture().getHeight() - 1).isSolid() || Game.currentMap.getTileByXY(Math.floor(getX()) - 1 + getTexture().getWidth(), (getY() + vy) + getTexture().getHeight() - 1).isSolid()) {
					return true;
				}
				break;
			case 3:
				if (Game.currentMap.getTileByXY(Math.floor(getX()) + vx, getY()).isSolid() || Game.currentMap.getTileByXY(Math.floor(getX()) + vx, Math.floor(getY()) - 2 + getTexture().getHeight()).isSolid()) {
					return true;
				}
				break;
		}
		return false;
	}

	void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	BufferedImage getTexture() {
		return texture;
	}

	public double getX() {
		return x;
	}

	double getY() {
		return y;
	}

	void setX(double x) {
		this.x = x;
	}

	void setY(double y) {
		this.y = y;
	}

}
