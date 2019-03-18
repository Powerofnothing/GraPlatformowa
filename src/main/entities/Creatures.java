package main.entities;

import java.awt.*;
import java.util.ArrayList;

public class Creatures {

	private static ArrayList<Creature> creaturesList;
	public static ArrayList<Creature> creatures;
	private static ArrayList<Creature> creaturesToRemove;

	public static void init() {
		creaturesList = new ArrayList<>();
		creatures = new ArrayList<>();
		creaturesToRemove = new ArrayList<>();
		creaturesList.add(new Creature(0, 100, 13 * 16 + 1, 14 * 16 + 1, 50, 0.8, 250, 1, 120, "/textures/enemy1.png"));
		creaturesList.add(new Creature(1, 500, 850, 850, 50, 0, 400, 1, 60, "/textures/boss1.png"));
	}

	public static void update() {
		for (Creature creature : creatures)
			creature.update();
		creatures.removeAll(creaturesToRemove);
	}

	public static void draw(Graphics g) {
		for (Creature creature : creatures)
			creature.draw(g);
	}

	public static void removeCreature(Creature c) {
		for (Creature creature : creatures)
			if (creature.hashCode() == c.hashCode())
				creaturesToRemove.add(c);
	}

	public static Creature getCreatureFromList(int id) {
		for (Creature creature : creaturesList)
			if (creature.getId() == id)
				return creature;
		return null;
	}

	static void newCreature(int id) {
		creatures.add(new Creature(id));
	}

	public static void spawn(ArrayList<Integer> creaturesId) {
		creatures.removeAll(creatures);
		for (Integer i : creaturesId) {
			newCreature(i);
		}
	}
}
