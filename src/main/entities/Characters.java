package main.entities;

import java.util.ArrayList;

public class Characters {

	public static ArrayList<Character> characters;

	public static void init() {
		characters = new ArrayList<>();
		characters.add(new Character(0,100, 115, "/textures/player.png"));
	}

	public static Character getCharacter(int id) {
		for (Character character : characters) {
			if (character.getId() == id) {
				return character;
			}
		}
		return null;
	}

}
