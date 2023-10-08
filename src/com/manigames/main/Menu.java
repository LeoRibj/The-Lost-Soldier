package com.manigames.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.manigames.entities.Player;
import com.manigames.world.World;

public class Menu {

	public String[] options = {"New Game", "Load Game", "Controls", "Quit"};
	public int currentOption = 0;
	public int maxOpition = options.length - 1;
	
	public boolean up,down;
	public boolean enter;
	
	
	
	public static boolean saveExists = false;
	public static boolean saveGame = false;
	
	public static int hasguns = 0;
	
	public void update() {
		
		
		
		File file = new File("save.txt");
		if(file.exists()){
			saveExists = true;
		}else{
			saveExists = false;
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
			if(options[this.currentOption] == "New Game") {
				Game.CUR_LEVEL = 1;
				String newWorld = "level" + Game.CUR_LEVEL + ".png";
	    		World.restartGame(newWorld); 
				Game.gameState = "normal";
				file = new File("save.txt");
				file.delete();
				
			}if(options[this.currentOption] == "Load Game") {
				file = new File("save.txt");
				if(file.exists()) {
					String saver = loadGame(0);
					applySave(saver);
					System.out.println("carregado");
				}
			}if(options[this.currentOption] == "Controls") {
				Game.gameState = "menuc";
			}if(options[this.currentOption] == "Quit") {
				System.exit(1);
		}
		}
		
	}
	public static void applySave(String str) {
		String[] spl = str.split("/");
		for(int i = 0; i < spl.length; i++) {
			String[] spl2 = spl[i].split(":");
			switch(spl2[0])
			{
			case "level":
				World.restartGame("level" + spl2[1] + ".png");
				Game.gameState = "normal";
				
				break;
			
			case "vidamax":
				Game.player.maxLife = Integer.parseInt(spl2[1]);
				
			
				break;
			
			case "vida":
			
				Game.player.life = Integer.parseInt(spl2[1]);
			
				break;
			
			case "maxammo":
				Game.player.maxAmmo = Integer.parseInt(spl2[1]);
				
			break;
		
			case "ammo":
		
				Game.player.ammo = Integer.parseInt(spl2[1]);
			break;
			case "hasgun":
				Game.player.hasguns = Integer.parseInt(spl2[1]);
				break;
			case"hasgun2":
			Game.player.hasgun2s = Integer.parseInt(spl2[1]);
			break;
			
			}
		}
	}
	
	
	public static String loadGame(int encode) {
		String line = "";
		File file = new File("save.txt");
		if(file.exists()) {
			try {
				String singleLine = null;
				BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
			try {
				while((singleLine = reader.readLine()) != null) {
					String[] trans = singleLine.split(":");
					char[] val = trans[1].toCharArray();
					trans[1] = "";
					for(int i = 0; i < val.length; i++) {
						val[i]-=encode;
						trans[1]+=val[i];
					}
					line+=trans[0];
					line+=":";
					line+=trans[1];
					line+="/";
				}
			}catch(IOException e) {}
		}catch(FileNotFoundException e) {}
	}
		return line;
}
	
	public static void saveGame(String[] val1,int[] val2, int encode) {
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter("save.txt"));
		}catch(IOException e){
			e.printStackTrace();
		}
		for(int i = 0; i< val1.length; i++) {
			String current = val1[i];
			current+=":";
			char[] value = Integer.toString(val2[i]).toCharArray();
			for(int n = 0; n < value.length; n++) {
				value[n]+=encode;
				current+=value[n];
			}
			try {
				write.write(current);
				if(i < val1.length - 1)
					write.newLine();
			}catch(IOException e) {}
		}
		try {
			write.flush();
			write.close();
		}catch(IOException e) {}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE,Game.HEIGTH*Game.SCALE);
		g.setColor(Color.green);
		g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString(">The Lost Soldier<",80, 20);
	
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString("New Game",80 ,30);
		g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString("Load Game",80 ,40);
		g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString("Controls",80 ,50);
		g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString("Quit",80 ,60);
		
		if(options[this.currentOption] == "New Game") {
			g.drawString(">",70 ,30 );
		}else if(options[currentOption] == "Load Game") {
			g.drawString(">",70 ,40);
		}else if(options[currentOption] == "Controls") {
			g.drawString(">",70 ,50);
		}else if(options[currentOption] == "Quit") {
			g.drawString(">",70 ,60);
			
		}
	
	}
}
