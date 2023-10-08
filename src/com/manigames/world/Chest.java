package com.manigames.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.manigames.main.Game;

public class Chest extends Tile{
    private BufferedImage closed;
    private BufferedImage open;
	public Chest(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		closed = Game.spritesheet.getSprite(288, 128, 16, 16);
		open = Game.spritesheet.getSprite(304, 128, 16, 16);
	}
  public void render(Graphics g) {
    	 if(!Game.player.chestopen) {
			 g.drawImage(closed, this.getX() - Camera.x, this.getY() - Camera.y, null);
	 }else  {
			 g.drawImage(open, this.getX() - Camera.x, this.getY() - Camera.y, null);
		 }
    }
}
