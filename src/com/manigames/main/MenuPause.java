package com.manigames.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MenuPause {

	public String[] options = {"Resume", "Main Menu"};
	public int currentOption = 0;
	public int maxOpition = options.length - 1;
	
	public boolean up,down;
	public boolean enter;
	
	
	public void update() {
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
			}
			if(options[this.currentOption] == "Main Menu") {
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
	    g.drawString("Paused", 90 , 20);
	    
	    g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString("Resume", 97 ,45);
		 g.setFont(new Font("arial", Font.BOLD, 10));
			g.drawString("Main Menu",90, 55);
	
			if(options[this.currentOption] == "Resume") {
				g.drawString(">",90,45 );
			}else if(options[currentOption] == "Main Menu") {
				g.drawString(">",83,55);
	
			}
			}
	
}
