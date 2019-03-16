package main.graphics;

import main.Game;

import java.awt.*;

public class Hud {

	public static Font HUDfont = new Font("Consolas", Font.BOLD, 8 * Game.scale);

	public static void drawHUD(Graphics g) {
		g.setColor(new Color(255, 255, 255));
		g.fillRect(8, 8, 64 * Game.scale, 8 * Game.scale);
		g.setColor(new Color(255, 0, 0));
		g.drawRect(8, 8, 64 * Game.scale, 8 * Game.scale);
		g.fillRect(8, 8, Game.currentCharacter.getHp() * 64 / Game.currentCharacter.getMaxHp() * Game.scale, 8 * Game.scale);
		g.setColor(new Color(0, 0, 0));
		g.setFont(HUDfont);
		g.drawString("HP " + Game.currentCharacter.getHp() + "/" + Game.currentCharacter.getMaxHp(), 11, 5 + 8 * Game.scale);
	}

}
