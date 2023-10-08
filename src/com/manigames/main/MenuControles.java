package com.manigames.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;






public class MenuControles {

	public String[] options = {"Back"};
	public int currentOption = 0;
	public int maxOpition = options.length - 1;
	public boolean enter;
	private BufferedImage menucontroles;
   

  
    
	public MenuControles(String path) {
		try {
			
			menucontroles = ImageIO.read(getClass().getResource(path));
		
		} catch (IOException e) {
			e.printStackTrace();
	}
	}public BufferedImage getSprite(int x,int y,int width,int height){
		return menucontroles.getSubimage(x, y, width, height);}



	public void update() {
		  
		
		  if(enter){
			enter = false;
			if(options[this.currentOption] == "Back") {
				Game.gameState = "menu";
			}
			}
	
	

	

	
	
	

		}




	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0,Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height);
		g.drawImage(menucontroles, 0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height,null);
		g.setFont(new Font("arial", Font.BOLD, 26));
		g.setColor(Color.white);
		g.drawString("Back", 100 ,830);
		if(options[this.currentOption] == "Back") {
			g.drawString(">",80,830 );
		}
		}

	
}

