package com.manigames.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.manigames.entities.Player;
import com.manigames.main.Game;

public class UI {

	private BufferedImage ui;
	private BufferedImage uibu;
	private BufferedImage ui2;
	private BufferedImage uibu2;
	private BufferedImage ui3;
	private BufferedImage uibu3;
	private BufferedImage key;
	private BufferedImage uistamina; 
	private BufferedImage bosskey;
	private BufferedImage bleeding;
	private BufferedImage poison;
	private BufferedImage fire;

	

	public UI(BufferedImage sprite) {
	
	 ui = (Game.spritesheet.getSprite(192, 0, 32, 16));
	 uibu = (Game.spritesheet.getSprite(192, 16, 32, 16));
	 ui2 = (Game.spritesheet.getSprite(192, 32, 48, 16));
	 uibu2 = (Game.spritesheet.getSprite(192, 64, 48, 16));
	 ui3 = (Game.spritesheet.getSprite(192, 48, 48, 16));
	 uibu3 = (Game.spritesheet.getSprite(192, 80, 48, 16));
	 uistamina = (Game.spritesheet.getSprite(160, 32, 32,16));
	 key = (Game.spritesheet.getSprite(176, 48, 16, 16));
	 bosskey = (Game.spritesheet.getSprite(176, 64, 16, 16));
	 bleeding = (Game.spritesheet.getSprite(256, 128, 16, 16));
	 poison = (Game.spritesheet.getSprite(272, 128, 16, 16));
	 fire = (Game.spritesheet.getSprite(240, 128, 16, 16));
	}








	public void render(Graphics g) {
		
		if(Game.player.bleeding >0) {
			g.drawImage(bleeding,215,-5,16, 16, null);
		}
		
		g.drawImage(poison,225,-5,16, 16, null);
		g.drawImage(fire,206,-4,16, 16, null);
		
		if(!Game.player.getkey) {
			
		}else {
			g.drawImage(key, 12, 21,16, 16, null);
		}
		
		if(!Game.player.getbosskey) {
			
		}else {
			g.drawImage(bosskey, 19, 20,16, 16, null);
		}
		
		
		if(Game.player.maxAmmo == 30) {
		g.setColor(Color.BLACK);
		g.fillRect(18, 16, 21, 2);
		g.setColor(Color.YELLOW);
		g.fillRect(18, 16, (int)((Game.player.ammo / Game.player.maxAmmo)*21), 2);
		g.drawImage(uibu, 13, 12,32 , 16, null);
		}else if(Game.player.maxAmmo == 40) {
			g.setColor(Color.BLACK);
			g.fillRect(18, 16, 31, 2);
			g.setColor(Color.YELLOW);
			g.fillRect(18, 16, (int)((Game.player.ammo / Game.player.maxAmmo)*31), 2);
			g.drawImage(uibu2, 13, 12,48 , 16, null);
		}else if(Game.player.maxAmmo == 50) {
			g.setColor(Color.BLACK);
			g.fillRect(18, 16, 37, 2);
			g.setColor(Color.YELLOW);
			g.fillRect(18, 16, (int)((Game.player.ammo / Game.player.maxAmmo)*37), 2);
			g.drawImage(uibu3, 13, 12,48 , 16, null);
		}
		
		
		if( Game.player.maxLife == 100) {
		g.setColor(Color.black);
		g.fillRect(18, 8, 21,5);
		g.setColor(Color.red);
		g.fillRect(18, 8, (int)((Game.player.life / Game.player.maxLife)*21), 5);
		g.drawImage(ui, 8, 5,32 , 16, null);
		}
		
		else if(Game.player.maxLife == 150) {
			g.setColor(Color.black);
			g.fillRect(18, 8, 30,5);
			g.setColor(Color.red);
			g.fillRect(18, 8, (int)((Game.player.life / Game.player.maxLife)*30), 5);
			g.drawImage(ui2, 8, 5,48 , 16, null);
		}
		
		else if(Game.player.maxLife == 200) {
			g.setColor(Color.black);
			g.fillRect(18, 8, 37,5);
			g.setColor(Color.red);
			g.fillRect(18, 8, (int)((Game.player.life / Game.player.maxLife)*37), 5);
			g.drawImage(ui3, 8, 5,48 , 16, null);
		}
		g.setColor(Color.black);
		g.fillRect(18, 20, 21,3);
		g.setColor(Color.blue);
		g.fillRect(18, 20, (int)((Game.player.stamina/Game.player.maxStamina)*21),3);
	g.drawImage(uistamina, 13, 17, 32, 16, null);
	}
}
