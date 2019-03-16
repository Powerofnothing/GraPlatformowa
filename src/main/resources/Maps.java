package main.resources;

import java.util.ArrayList;

public class Maps {

	static int defaultTileWidth = 16;
	static int defaultTileHeight = 16;
	private static ArrayList<Map> maps;

	public static void init() {
		maps = new ArrayList<>();
		maps.add(new Map("Map0", 0, "/maps/map0"));
		maps.add(new Map("Map1", 1, "/maps/map1"));
	}

	public static Map getMap(int id) {
		for (Map map : maps)
			if (map.getId() == id)
				return map;
		return null;
	}

}

