package main.resources;

import main.entities.Creatures;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Level {

	private int id;
	private String name;
	private int mapId;
	private ArrayList<Integer> creaturesId;
	private String path;

	Level(int id, String path) {
		this.id = id;
		this.path = path;
		creaturesId = new ArrayList<>();
		load();
	}

	private void load() {
		Scanner in = ResourceLoader.loadTxt(path);
		try {
			String s = in.next();
			while (s != null) {
				if (s.equals("name"))
					this.name = in.next();
				else if (s.equals("map"))
					mapId = in.nextInt();
				else if (s.equals("creature"))
					creaturesId.add(in.nextInt());
				s = in.next();
			}
		} catch (NoSuchElementException e) {
			System.out.println("Finished loading level " + id);
		}
	}

	public int getId() {
		return id;
	}

	public ArrayList<Integer> getCreaturesId() {
		return creaturesId;
	}

	public int getMapId() {
		return mapId;
	}


}
