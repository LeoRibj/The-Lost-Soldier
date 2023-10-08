package com.manigames.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.manigames.entities.Alavancacolision;
import com.manigames.entities.Player;
import com.manigames.main.Game;

public class Door extends Tile{
private BufferedImage closed;
private BufferedImage open;

	public Door(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		closed = Game.spritesheet.getSprite(160, 80, 16, 16);
		open = Game.spritesheet.getSprite(0, 0, 16, 16);
	}
    public void render(Graphics g) {
    	 if(!Game.player.acionada) {
			 g.drawImage(closed, this.getX() - Camera.x, this.getY() - Camera.y, null);
	 }else  {
			 g.drawImage(open, this.getX() - Camera.x, this.getY() - Camera.y, null);
		 }
    }
}
