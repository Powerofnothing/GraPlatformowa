package main;

public class Main {

	public static void main(String[] args) {

		Game game;
		if (args.length == 0) {
			game = new Game(384, 240, 3);
		} else if (args.length == 1) {
			game = new Game(384, 240, Integer.parseInt(args[0]));
		} else {
			game = new Game(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		}
		game.start();

	}

}
