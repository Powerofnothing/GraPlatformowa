package main;

import main.entities.*;
import main.entities.Player;
import main.graphics.Display;
import main.graphics.Hud;
import main.input.KeyManager;
import main.resources.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

	private boolean running = false;
	private Thread thread;
	private int FPS = 60;
	private final double TIMEPERTICK = 1000000000 / FPS;
	private Display display;
	private BufferStrategy bs;
	private Graphics g;

	public static int width, height, scale;
	public static KeyManager km;

	public static Level currentLevel;
	public static Map currentMap;
	public static Player player;

	Game(int w, int h, int s) {
		width = w;
		height = h;
		scale = s;
	}

	private void init() {

		display = new Display(width, height);
		km = new KeyManager();
		display.getFrame().addKeyListener(km);

		Tiles.init();
		Creatures.init();
		Projectiles.init();
		Maps.init();
		Levels.init();
		player = new Player();

		Levels.changeLevel(0);
	}

	private void update() {
		km.update();

		currentMap.update();
		player.update();
		Creatures.update();
		Projectiles.update();
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width * scale, height * scale);
		// draw here

		currentMap.draw(g);
		player.draw(g);
		Creatures.draw(g);
		Projectiles.draw(g);
		Hud.drawHUD(g);

		// draw here
		bs.show();
		g.dispose();
	}

	public void run() {
		init();
		long now;
		long lastTime = System.nanoTime();
		long diff;
		while (running) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			now = System.nanoTime();
			diff = now - lastTime;
			if (diff > TIMEPERTICK) {
				lastTime = System.nanoTime();
				update();
				render();
			}
		}
		stop();
	}

	public void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
