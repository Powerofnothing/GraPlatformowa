package main.entities;

import java.util.ArrayList;

public class Creatures {

	private static ArrayList<Creature> creatures;

	public static void init() {
		creatures = new ArrayList<>();
		creatures.add(new Creature(0, 100, 59, 150, 100, 1.5, "/textures/enemy1.png"));
	}

	public static Creature getCreature(int id) {
		for (Creature creature : creatures) {
			if (creature.getId() == id) {
				return creature;
			}
		}
		return null;
	}

}
