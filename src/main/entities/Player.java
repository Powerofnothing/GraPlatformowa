package main.entities;

import main.Game;
import main.resources.Levels;
import main.resources.ResourceLoader;

public class Player extends Entity {

	private double vx, vy;
	private int shootingCooldown = 60; //TICKS
	private int hp, maxHp;

	public Player() {
		this.maxHp = 100;
		this.hp = maxHp;
		setX(0);
		setY(0);
		setTexture(ResourceLoader.loadImage("/textures/player.png"));
	}

	public void reset(int x, int y) {
		hp = maxHp;
		vx = 0;
		vy = 0;
		setX((double) x);
		setY((double) y);
	}

	public void update() {
		//fall
		if (!checkMapCollision(2, vx, vy) && vy < 3)
			vy += 0.2;
		if (vy > 0 && checkMapCollision(2, vx, vy))
			vy = 0.2;

		//jump
		if (checkMapCollision(2, vx, 1)) {
			vy = 0;
			if (Game.km.up)
				vy = -5;
		}
		if (checkMapCollision(0, vx, vy))
			vy = 0;

		//right
		if (Game.km.right && vx < 1.6)
			vx += 0.2;
		if ((checkMapCollision(1, vx, vy) || (getX() + getTexture().getWidth() >= Game.currentMap.getWidth())) && vx > 0)
			vx = 0;

		//left
		if (Game.km.left && vx > -1.6)
			vx -= 0.2;
		if ((checkMapCollision(3, vx, vy) || (getX() <= 0)) && vx < 0)
			vx = 0;

		//stop
		if (!Game.km.left && !Game.km.right)
			vx = 0;

		//move
		setX(getX() + vx);
		setY(getY() + vy);

		//shooting
		if (shootingCooldown <= 0) {
			if (Game.km.shootRight) {
				shootingCooldown += 60;
				Projectiles.newProjectile(0, 1, getX() + (getTexture().getWidth() >> 1), getY() + (getTexture().getHeight() >> 1));
			} else if (Game.km.shootLeft) {
				shootingCooldown += 60;
				Projectiles.newProjectile(0, -1, getX() + (getTexture().getWidth() >> 1), getY() + (getTexture().getHeight() >> 1));
			}
		}
		if (shootingCooldown > 0)
			shootingCooldown--;

		//isDead
		if (hp <= 0) {
			Levels.changeLevel(Game.currentLevel.getId());
		}
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
		System.out.println("Player's hp = " + hp);
	}

	public int getMaxHp() {
		return maxHp;
	}
}
