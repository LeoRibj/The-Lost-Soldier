package com.manigames.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.manigames.entities.Alavancacolision;
import com.manigames.entities.Player;
import com.manigames.main.Game;

public class Alavanca extends Tile{
     public BufferedImage off;
	 public BufferedImage on;
	public Alavanca(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
		off = Game.spritesheet.getSprite(224, 0, 16, 16);
		on = Game.spritesheet.getSprite(224, 16, 16, 16);
	}
	public void render(Graphics g) {
		 if(!Game.player.acionada) {
			 g.drawImage(off, this.getX() - Camera.x, this.getY() - Camera.y, null);
	 }else  {
			 g.drawImage(on, this.getX() - Camera.x, this.getY() - Camera.y, null);
		 }
	 }
	}


