package main.entities;

import java.util.ArrayList;

public class Characters {

	public static ArrayList<Character> characters;
	public static ArrayList<Character> charactersList;

	public static void init() {
		characters = new ArrayList<>();
		charactersList = new ArrayList<>();
		characters.add(new Character(0, 200, 100, 115, "/textures/player.png"));
		charactersList.add(new Character(0, 200, 100, 115, "/textures/player.png"));
	}

	public static Character getCharacter(int id) {
		for (Character character : characters) {
			if (character.getId() == id) {
				return character;
			}
		}
		return null;
	}

	public static Character getCharacterFromList(int id) {
		for (Character character : charactersList) {
			if (character.getId() == id) {
				return character;
			}
		}
		return null;
	}

	public static void reset(int id) {
		getCharacter(id).setHp(getCharacterFromList(id).getHp());
		getCharacter(id).setX(getCharacterFromList(id).getX());
		getCharacter(id).setY(getCharacterFromList(id).getY());
	}
}
