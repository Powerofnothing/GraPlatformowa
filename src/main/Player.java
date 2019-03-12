package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

	private static BufferedImage texture;
	private static double x, y;
	private static double vx, vy;

	public static void init() {
		texture = ResourceLoader.loadImage("/textures/player.png");
		x = 100;
		y = 100;
	}

	public static void update() {
		//fall
		if (!Game.currentMap.getTileByXY(Math.floor(x), Math.floor(y) + texture.getHeight()).isSolid() && !Game.currentMap.getTileByXY(Math.floor(x) - 1 + texture.getWidth(), Math.floor(y) + texture.getHeight()).isSolid()) {
			if (vy < 2)
				vy += 1;
		}
		//jump
		if (Game.currentMap.getTileByXY(Math.floor(x), Math.floor(y) + texture.getHeight()).isSolid() || Game.currentMap.getTileByXY(Math.floor(x) - 1 + texture.getWidth(), Math.floor(y) + texture.getHeight()).isSolid()) {
			vy = 0;
			if (Game.km.up == true) {
				vy = -10;
			}
		}
		if (Game.currentMap.getTileByXY(Math.floor(x), Math.floor(y) + vy).isSolid() || Game.currentMap.getTileByXY(Math.floor(x) - 1 + texture.getWidth(), y + vy).isSolid()) {
			vy = 0;
		}
		//right
		if (Game.km.right)
			if (vx < 2)
				vx += 0.2;
		if (Game.currentMap.getTileByXY(Math.floor(x) + vx + texture.getWidth(), y).isSolid() || Game.currentMap.getTileByXY(Math.floor(x) + vx + texture.getWidth(), Math.floor(y) - 2 + texture.getHeight()).isSolid()) {
			if (vx > 0)
				vx = 0;
		}
		if (Game.km.left)
			if (vx > -2)
				vx -= 0.2;
		if (Game.currentMap.getTileByXY(Math.floor(x) + vx, y).isSolid() || Game.currentMap.getTileByXY(Math.floor(x) + vx, Math.floor(y) - 2 + texture.getHeight()).isSolid()) {
			if (vx < 0)
				vx = 0;
		}
		if (!Game.km.left && !Game.km.right) {
			vx = 0;
		}
		x += vx;
		y += vy;
	}

	public static void draw(Graphics g) {
		g.drawImage(texture, ((int) x - Game.currentMap.getOffsetX()) * Game.scale, ((int) y) * Game.scale, texture.getWidth() * Game.scale, texture.getHeight() * Game.scale, null);
	}

	public static double getX() {
		return x;
	}

	public static double getY() {
		return y;
	}

	public static double getVX() {
		return vx;
	}
}
