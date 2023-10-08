package com.manigames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import com.manigames.main.Game;
import com.manigames.world.Camera;


public class Entity {

	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(6*16, 0, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(7*16, 0, 16, 16);
	public static BufferedImage WEAPON_EN2 = Game.spritesheet.getSprite(144, 0, 16, 16);
	public static BufferedImage SAVETENT_EN = Game.spritesheet.getSprite(176, 0, 16, 16);
	public static BufferedImage BULLET_EN = Game.spritesheet.getSprite(6*16, 16, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(240, 0, 16, 16);
	public static BufferedImage ENEMY_EN3 = Game.spritesheet.getSprite(240, 64, 16, 16);
	public static BufferedImage ENEMY_EN2 = Game.spritesheet.getSprite(128, 192, 32, 32);
	public static BufferedImage ENEMY_EN4 = Game.spritesheet.getSprite(32, 64, 32, 32);
	public static BufferedImage ENEMY_FEEDBACK = Game.spritesheet.getSprite(304, 0, 16, 16);
	public static BufferedImage ENEMY_FEEDBACK2 = Game.spritesheet.getSprite(64, 128, 32, 32);
	public static BufferedImage ENEMY_FEEDBACK4 = Game.spritesheet.getSprite(32, 128, 32, 32);
	public static BufferedImage MAXXLIFE_EN = Game.spritesheet.getSprite(160, 0, 16, 16);
	public static BufferedImage BULLETMAXX_EN = Game.spritesheet.getSprite(160,16, 16, 16);
	public static BufferedImage NEXTLEVEL_EN = Game.spritesheet.getSprite(304,304, 16, 16);
	public static BufferedImage ALAVANCA_EN = Game.spritesheet.getSprite(304, 304, 16,16);
	public static BufferedImage KEY_EN = Game.spritesheet.getSprite(160, 48, 16,16);
	public static BufferedImage BOSSKEY_EN = Game.spritesheet.getSprite(160, 64, 16,16);
	public static BufferedImage BOSSDOROPEN_EN = Game.spritesheet.getSprite(304,304, 16, 16);
	public static BufferedImage CHEST_EN = Game.spritesheet.getSprite(304,304, 16, 16);
	public static BufferedImage CLOSED_EN = Game.spritesheet.getSprite(304,304, 16, 16);
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	

	
	private BufferedImage sprite;
	
	private int maskx, masky, mwidth, mheight;
	
	
	public Entity(int x,int y,int width,int height,BufferedImage sprite) {
		
	 
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.mwidth = width;
		this.mheight = height;
	
	}
	
	public void setMask(int maskx, int masky, int mwidth, int mheight) {
		this.maskx = maskx;
		this.masky = masky;
		this.mwidth = mwidth;
		this.mheight = mheight;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	
	public int getX(){
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void update() {}
		
		public double calculateDistance(int x1, int y1, int x2, int y2) {
			return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		}
		
		
	
	public static boolean isCollidding(Entity d , Entity e) {
		Rectangle e1Mask = new Rectangle(d.getX() + d.maskx, d.getY() + d.masky, d.mwidth, d.mheight);
		Rectangle e2Mask = new Rectangle(e.getX() + e.maskx, e.getY() + e.masky, e.mwidth, e.mheight);
		
		return e1Mask.intersects(e2Mask);
		
	}
	
	public void render(Graphics g) {
		
	g.drawImage(sprite,this.getX() - Camera.x,this.getY() - Camera.y,null);
	
	//g.setColor(Color.cyan);
	//g.fillRect(this.getX() + maskx - Camera.x,this.getY() + masky - Camera.y,mwidth,mheight);
	
	}
	
	
}
