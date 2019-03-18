package main.entities;

import main.Game;
import main.resources.ResourceLoader;

public class Projectile extends Entity {

	private int id;
	private double vx, range;
	private int damage = 50;
	private int type = 1; // 0 - ignores player, 1 - ignores creatures

	Projectile(int id, int type, int damage, double vx, String path) {
		this.id = id;
		this.type = type;
		this.vx = vx;
		this.damage = damage;
		setTexture(ResourceLoader.loadImage(path));
	}

	Projectile(int id, double range) {
		this.id = Projectiles.getProjectileFromList(id).getId();
		this.type = Projectiles.getProjectileFromList(id).getType();
		this.vx = Projectiles.getProjectileFromList(id).getVx();
		this.range = range;
		this.damage = Projectiles.getProjectileFromList(id).getDamage();
		setTexture(Projectiles.getProjectileFromList(id).getTexture());
	}

	void update() {
		if (checkMapCollision(1, vx, 0) || checkMapCollision(3, vx, 0)) {
			range = -1;
		} else {
			setX(getX() + vx);
			range -= Math.abs(vx);
		}
		if (type == 0) {
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
		} else if (type == 1) {
			boolean temp = true;
			if ((getY() >= Game.player.getY() + Game.player.getTexture().getHeight() - 1) || (getY() + getTexture().getHeight() - 1 <= Game.player.getY()))
				temp = false;
			else if ((getX() + getTexture().getWidth() - 1 <= Game.player.getX()) || (getX() >= Game.player.getX() + Game.player.getTexture().getWidth() - 1))
				temp = false;
			if (temp) {
				range = -1;
				Game.player.setHp(Game.player.getHp() - damage);
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

	int getType() {
		return type;
	}
}
