package com.manigames.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.manigames.main.Game;

public class Tile {
	
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_FLOOR2 = Game.spritesheet.getSprite(224, 96, 16, 16);
	public static BufferedImage TILE_WALL1 = Game.spritesheet.getSprite(16, 0, 16, 16);
	public static BufferedImage TILE_WALL2 = Game.spritesheet.getSprite(16, 16, 16, 16);
	public static BufferedImage TILE_WALL3 = Game.spritesheet.getSprite(96, 32, 16, 16);
	public static BufferedImage TILE_WALL4 = Game.spritesheet.getSprite(176, 80, 16, 16);
	public static BufferedImage Teto_TILE = Game.spritesheet.getSprite(176, 112, 16, 16);
	public static BufferedImage PONTECIMA_TILE = Game.spritesheet.getSprite(160, 96, 16, 16);
	public static BufferedImage PONTEbaixo_TILE = Game.spritesheet.getSprite(176, 96, 16, 16);
	public static BufferedImage PONTE_TILE = Game.spritesheet.getSprite(192, 96, 16, 16);
	public static BufferedImage Water_TILE = Game.spritesheet.getSprite(208, 96, 16, 16);
	public static BufferedImage Walltrees_Tile = Game.spritesheet.getSprite(224, 112, 16,16);
	public static BufferedImage DOOR_TILE = Game.spritesheet.getSprite(176, 16, 16, 16);
	public static BufferedImage ALAVANCA_TILE = Game.spritesheet.getSprite(224, 0, 16,16);
	public static BufferedImage BOSSDOOR_TILE = Game.spritesheet.getSprite(176, 80, 16,16);
	public static BufferedImage Chest_TILE = Game.spritesheet.getSprite(288, 128, 16,16);	
	
	
	private BufferedImage sprite;
	private int x;
	private int y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		
	}
	
	public int getX(){
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
		
	}
}
