package main.entities;

import main.Game;
import main.resources.ResourceLoader;

import java.awt.image.BufferedImage;

public class Projectile extends Entity {

	private int id;
	private double vx, range;
	private double damage = 50;

	public Projectile(int id, int damage, double vx, double range, String path) {
		this.id = id;
		this.vx = vx;
		this.range = range;
		this.damage = damage;
		setTexture(ResourceLoader.loadImage(path));
	}

	public Projectile(int id, double vx, double range, BufferedImage texture) {
		this.id = id;
		this.vx = vx;
		this.range = range;
		this.damage = damage;
		setTexture(texture);
	}

	public void update() {
		if (checkCollision(1, vx, 0) || checkCollision(3, vx, 0)) {
			range = -1;
		} else {
			setX(getX() + vx);
			range -= vx;
		}
		if (range < 0) {
			Projectiles.removeProjectile(this);
		}
	}

	public int getId() {
		return id;
	}

	public double getRange() {
		return range;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}
}
