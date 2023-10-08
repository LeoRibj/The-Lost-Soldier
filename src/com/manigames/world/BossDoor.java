package com.manigames.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.manigames.main.Game;

public class BossDoor extends Tile{
	private BufferedImage closed;
	private BufferedImage open;
	public BossDoor(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		open = Game.spritesheet.getSprite(0, 0, 16, 16);
		closed = Game.spritesheet.getSprite(176,80, 16, 16);
	}
	public void render(Graphics g) {
		if(!Game.player.bossope) {
			 g.drawImage(closed, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else {
			g.drawImage(open, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}

}
