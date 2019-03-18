package main.resources;

import main.Game;
import main.entities.*;

import java.util.ArrayList;

public class Levels {

	private static ArrayList<Level> levels;

	public static void init() {
		levels = new ArrayList<>();
		levels.add(new Level(0, "/levels/level0"));
		levels.add(new Level(1, "/levels/level1"));
	}

	public static Level getLevel(int id) {
		for (Level level : levels) {
			if (level.getId() == id) {
				return level;
			}
		}
		return null;
	}

	public static void changeLevel(int id) {
		Game.currentLevel = Levels.getLevel(id);
		for(Creature creature : Creatures.creatures)
			Creatures.removeCreature(creature);
		for(Projectile projectile : Projectiles.projectiles)
			Projectiles.removeProjectile(projectile);
		Game.currentMap = Maps.getMap(Game.currentLevel.getMapId());
		Game.currentMap.setOffSetX(0);
		Creatures.spawn(Game.currentLevel.getCreaturesId());
		Game.player.reset(Game.currentMap.getPlayerX(), Game.currentMap.getPlayerY());
	}

}
