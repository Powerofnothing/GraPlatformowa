package main.entities;

import main.Game;
import main.resources.ResourceLoader;

public class Player extends Entity {

	private double vx, vy;
	private int shootingCooldown = 60; //TICKS

	public Player(int x, int y, String path) {
		setTexture(ResourceLoader.loadImage(path));
		setX(x);
		setY(y);
	}

	public void update() {

		//fall
		if (!checkCollision(2, vx, vy) && vy < 3)
			vy += 0.2;
		if (vy > 0 && checkCollision(2, vx, vy))
			vy = 0.2;

		//jump
		if (checkCollision(2, vx, 1)) {
			vy = 0;
			if (Game.km.up)
				vy = -5;
		}
		if (checkCollision(0, vx, vy))
			vy = 0;

		//right
		if (Game.km.right && vx < 1.6)
			vx += 0.2;
		if (checkCollision(1, vx, vy) && vx > 0)
			vx = 0;

		//left
		if (Game.km.left && vx > -1.6)
			vx -= 0.2;
		if (checkCollision(3, vx, vy) && vx < 0)
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
				Projectiles.newProjectile(0, 1, getX() + getTexture().getWidth() / 2, getY() + getTexture().getHeight() / 2);
			} else if (Game.km.shootLeft) {
				shootingCooldown += 60;
				Projectiles.newProjectile(0, -1, getX() + getTexture().getWidth() / 2, getY() + getTexture().getHeight() / 2);
			}
		}
		if (shootingCooldown > 0)
			shootingCooldown--;
	}

}
