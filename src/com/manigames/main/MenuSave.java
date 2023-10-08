package com.manigames.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.manigames.entities.Player;

public class MenuSave {
	public String[] options = {"Resume","Save", "Main Menu"};
	public int currentOption = 0;
	public int maxOpition = options.length - 1;
	
	public boolean up,down;
	public boolean enter;
	
	public static boolean saveGame = false;
	
	public void update() {
		if(saveGame) {
			saveGame = false;
			String[] opt1 = {"level","vidamax","vida","maxammo","ammo","hasgun","hasgun2"};
			int[] opt2 = {Game.CUR_LEVEL,(int)Game.player.maxLife,(int) Game.player.life,
					(int) Game.player.maxAmmo,(int) Game.player.ammo, Game.player.hasguns, Game.player.hasgun2s};
			Menu.saveGame(opt1,opt2,0);
			}
		
		if(up) {
			up=false;
			currentOption--;
			if(currentOption < 0)	
			currentOption = maxOpition;
			
		}if(down) {
			down=false;
			currentOption++;
			if(currentOption > maxOpition) 
				currentOption = 0;
		
	}
		if(enter){
			enter = false;
			if(options[this.currentOption] == "Resume") {
				Game.gameState = "normal";
			}if(options[this.currentOption] == "Save") {
				saveGame = true;
				Game.gameState = "ss";
			}if(options[this.currentOption] == "Main Menu") {
				Game.gameState = "menu";
				
			}
		}
}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,0,100));
		g2.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGTH*Game.SCALE);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD,15)); 
	    g.drawString("SaveStation", 70 ,20);
	    
	    g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString("Resume",90,40 );
		 g.setFont(new Font("arial", Font.BOLD, 10));
			g.drawString("Save",97, 60);
		 g.setFont(new Font("arial", Font.BOLD, 10));
			g.drawString("Main Menu",85,80 );
	
			if(options[this.currentOption] == "Resume") {
				g.drawString(">",83,40 );
			}else if(options[currentOption] == "Save") {
				g.drawString(">",90,60);
			}else if(options[currentOption] == "Main Menu") {
				g.drawString(">",78,80);
	
			}
			}
	
}
