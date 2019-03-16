package main.entities;

import main.resources.ResourceLoader;

class Projectile extends Entity {

	private int id;
	private double vx, range;
	private int damage = 50;

	Projectile(int id, int damage, double vx, double range, String path) {
		this.id = id;
		this.vx = vx;
		this.range = range;
		this.damage = damage;
		setTexture(ResourceLoader.loadImage(path));
	}

	Projectile(int id) {
		this.id = Projectiles.getProjectileFromList(id).getId();
		this.vx = Projectiles.getProjectileFromList(id).getVx();
		this.range = Projectiles.getProjectileFromList(id).getRange();
		this.damage = Projectiles.getProjectileFromList(id).getDamage();
		setTexture(Projectiles.getProjectileFromList(id).getTexture());
	}

	void update() {
		if (checkMapCollision(1, vx, 0) || checkMapCollision(3, vx, 0)) {
			range = -1;
		} else {
			setX(getX() + vx);
			range -= vx;
		}
		for (Creature creature : Creatures.creatures) {
			if ((getY() >= creature.getY() + creature.getTexture().getHeight() - 1) || (getY() + getTexture().getHeight() - 1 <= creature.getY()))
				creature = null;
			else if ((getX() + getTexture().getWidth() - 1 <= creature.getX()) || (getX() >= creature.getX() + creature.getTexture().getWidth() - 1))
				creature = null;
			if (creature != null) {
				range = -1;
				creature.setHp(creature.getHp() - damage);
			}
		}
		if (range < 0) {
			Projectiles.removeProjectile(this);
		}
	}

	int getId() {
		return id;
	}

	private double getRange() {
		return range;
	}

	double getVx() {
		return vx;
	}

	void setVx(double vx) {
		this.vx = vx;
	}

	private int getDamage() {
		return damage;
	}
}
