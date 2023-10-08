package com.manigames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.manigames.main.Game;
import com.manigames.world.Camera;
import com.manigames.world.World;

public class Bulletshoot2 extends Entity{

private int dx;
private int dy;
private double spd = 4;
private int life = 55, curLife = 0;

public Bulletshoot2(int x, int y, int width, int height, BufferedImage sprite, int dx, int dy){
	super(x, y,width,height, sprite);
	this.dx = dx;
	this.dy = dy;
	
}
public void update() {
	if(World.isFreeitens((int) (x+(dx*spd)), (int) (y+(dy*spd)),3, 3)) {
	    x+=dx*spd;
	    y+=dy*spd;
		}else {
			Game.bulletShoot2.remove(this);
			return;
		}
curLife++;
if(curLife == life) {
	Game.bulletShoot2.remove(this);
	return;
}
}
public void render(Graphics g) {
	g.setColor(Color.yellow);
	g.fillRect(this.getX() - Camera.x,this.getY() - Camera.y, width, height);
}
}
