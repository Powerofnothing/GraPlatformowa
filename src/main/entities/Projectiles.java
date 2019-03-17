package main.entities;

import java.awt.*;
import java.util.ArrayList;

public class Projectiles {

	private static ArrayList<Projectile> projectilesList;
	public static ArrayList<Projectile> projectiles;
	private static ArrayList<Projectile> projectilesToRemove;

	public static void init() {
		projectilesList = new ArrayList<>();
		projectiles = new ArrayList<>();
		projectilesToRemove = new ArrayList<>();
		projectilesList.add(new Projectile(0, 0, 50, 2.5, 200, "/textures/fireball.png"));
		projectilesList.add(new Projectile(1, 1, 50, 2.5, 300, "/textures/fireballblue.png"));
	}

	public static void update() {
		for (Projectile projectile : projectiles)
			projectile.update();
		projectiles.removeAll(projectilesToRemove);
	}

	public static void draw(Graphics g) {
		for (Projectile projectile : projectiles)
			projectile.draw(g);
	}

	public static void removeProjectile(Projectile p) {
		for (Projectile projectile : projectiles)
			if (projectile.hashCode() == p.hashCode())
				projectilesToRemove.add(p);
	}

	public static Projectile getProjectileFromList(int id) {
		for (Projectile projectile : projectilesList)
			if (projectile.getId() == id)
				return projectile;
		return null;
	}

	static void newProjectile(int id, int dir, double x, double y) {
		projectiles.add(new Projectile(id));
		projectiles.get(projectiles.size() - 1).setX(x - (projectilesList.get(id).getTexture().getWidth() >> 1));
		projectiles.get(projectiles.size() - 1).setY(y - (projectilesList.get(id).getTexture().getHeight() >> 1));
		projectiles.get(projectiles.size() - 1).setVx(projectiles.get(projectiles.size() - 1).getVx() * dir);
	}
}

