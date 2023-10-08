package com.manigames.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Savesusses {
	public String[] options = {"OK"};
	public int currentOption = 0;
	public int maxOpition = options.length - 1;
	public boolean enter;
	
	public void update() {
		
		if(enter){
			enter = false;
			if(options[this.currentOption] == "OK") {
				Game.gameState = "menus";
				}
	}
	}
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0,0,0,100));
		g2.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGTH*Game.SCALE);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD,15)); 
	    g.drawString("Saved successfully:",55,60 );
	    g.drawString("Press enter to continue!",40,80);
	    g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString("ok",110,100 );
		if(options[this.currentOption] == "OK") {
			g.drawString(">",103,100 );
		}
	}
}
