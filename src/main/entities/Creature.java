package main.entities;

import main.Game;
import main.resources.ResourceLoader;

public class Creature extends Entity {

	private int id;
	private double x1, x2, vx, vy;
	private int hp;
	private int projectileId;
	private int shootingCooldown;
	private int shootingTimer;
	private int range;

	Creature(int id, int hp, double x1, double x2, double y, double vx, int range, int projectileId, int shootingCooldown, String path) {
		this.id = id;
		this.x1 = x1;
		this.x2 = x2;
		this.vx = vx;
		this.hp = hp;
		this.range = range;
		this.projectileId = projectileId;
		this.shootingCooldown = shootingCooldown;
		setX(x1);
		setY(y);
		setTexture(ResourceLoader.loadImage(path));
	}

	Creature(int id) {
		this.id = Creatures.getCreatureFromList(id).getId();
		this.x1 = Creatures.getCreatureFromList(id).getX1();
		this.x2 = Creatures.getCreatureFromList(id).getX2();
		this.vx = Creatures.getCreatureFromList(id).getVx();
		this.hp = Creatures.getCreatureFromList(id).getHp();
		this.range = Creatures.getCreatureFromList(id).getRange();
		this.projectileId = Creatures.getCreatureFromList(id).getProjectileId();
		this.shootingCooldown = Creatures.getCreatureFromList(id).getShootingCooldown();
		this.shootingTimer = shootingCooldown;
		setX(Creatures.getCreatureFromList(id).getX());
		setY(Creatures.getCreatureFromList(id).getY());
		setTexture(Creatures.getCreatureFromList(id).getTexture());
	}

	void update() {
		if (!Game.currentMap.getTileByXY(Math.floor(getX()), Math.floor(getY()) + getTexture().getHeight()).isSolid() && !Game.currentMap.getTileByXY(Math.floor(getX()) - 1 + getTexture().getWidth(), Math.floor(getY()) + getTexture().getHeight()).isSolid()) {
			if (vy < 3)
				vy += 0.2;
		}
		if (Game.currentMap.getTileByXY(Math.floor(getX()), Math.floor(getY()) + getTexture().getHeight()).isSolid() || Game.currentMap.getTileByXY(Math.floor(getX()) - 1 + getTexture().getWidth(), Math.floor(getY()) + getTexture().getHeight()).isSolid()) {
			vy = 0;
		}
		if (getX() > x2) {
			vx = -Math.abs(vx);
		} else if (getX() < x1) {
			vx = Math.abs(vx);
		}
		setX(getX() + vx);
		setY(getY() + vy);
		if (shootingTimer <= 0 && getX() + Game.player.getTexture().getWidth() - Game.player.getX() <= range) {
			shootingTimer += shootingCooldown;
			double yy = Game.player.getY() + Game.player.getTexture().getHeight() / 2 - Projectiles.getProjectileFromList(projectileId).getTexture().getHeight() / 2;
			if (yy > getY() + getTexture().getHeight() - 1 - (1 + Projectiles.getProjectileFromList(projectileId).getTexture().getHeight()) / 2)
				yy = getY() + getTexture().getHeight() - 1 - (1 + Projectiles.getProjectileFromList(projectileId).getTexture().getHeight()) / 2;
			else if( yy < getY())
				yy = getY();
			if (Game.player.getX() < this.getX()) {
				Projectiles.newProjectile(projectileId, range, -1, getX() + (getTexture().getWidth() >> 1), yy);
			} else {
				Projectiles.newProjectile(projectileId, range, 1, getX() + (getTexture().getWidth() >> 1), yy);
			}
		}
		if (shootingTimer > 0)
			shootingTimer--;
		if (hp <= 0)
			Creatures.removeCreature(this);
	}

	int getId() {
		return id;
	}

	private double getX1() {
		return x1;
	}

	private double getX2() {
		return x2;
	}

	int getHp() {
		return hp;
	}

	void setHp(int hp) {
		this.hp = hp;
	}

	private double getVx() {
		return vx;
	}

	private int getProjectileId() {
		return projectileId;
	}

	private int getShootingCooldown() {
		return shootingCooldown;
	}

	private int getRange() {
		return range;
	}
}
