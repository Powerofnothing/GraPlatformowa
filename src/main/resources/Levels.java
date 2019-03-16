package main.resources;

import main.Game;
import main.entities.Character;
import main.entities.Characters;
import main.entities.Creature;
import main.entities.Creatures;

import java.util.ArrayList;

public class Levels {

	private static ArrayList<Level> levels;

	public static void init() {
		levels = new ArrayList<>();
		levels.add(new Level(0, "/levels/level0"));
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
		Characters.reset(Game.currentLevel.getCharacterId());
		Creatures.spawn(Game.currentLevel.getCreaturesId());
		Game.currentMap = Maps.getMap(Game.currentLevel.getMapId());
		Game.currentMap.setOffSetX(0);
		Game.currentCharacter = Characters.getCharacter(Game.currentLevel.getCharacterId());
	}

}
