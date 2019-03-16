package main.resources;

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

}
