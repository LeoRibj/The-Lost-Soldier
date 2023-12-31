package com.manigames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


import com.manigames.graficos.Sound;
import com.manigames.main.Game;

import com.manigames.world.Camera;

import com.manigames.world.World;
public class Enemy extends Entity{
private double speed = 0.7;
	
	private int maskx = 0, masky = 0 , maskw = 16, maskh = 16;
	
	private int frames = 0,maxFrames = 20,index = 0, maxIndex = 3;
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	private int life = 15;
	
	private boolean isDamage = false;
	private int damageFrames = 10, damageCurrent = 0;
	private boolean moved = false;
	private BufferedImage[] rightEnemy;
	private BufferedImage[] leftEnemy;
	private BufferedImage[] downEnemy;
	private BufferedImage[] upEnemy;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, height,height, sprite);
		
		rightEnemy = new BufferedImage[4];
		leftEnemy = new BufferedImage[4];
		downEnemy = new BufferedImage[4];
		upEnemy = new BufferedImage[4];
		
for(int i =0; i <4; i++) {
			
			rightEnemy[i] = Game.spritesheet.getSprite(240 + (i*16), 16, 16, 16);
			
		}
            for(int i =0; i <4; i++) {
			
			leftEnemy[i] = Game.spritesheet.getSprite(240 + (i*16), 32, 16, 16);
			
		}   for(int i =0; i <4; i++) {
			
			downEnemy[i] = Game.spritesheet.getSprite(240 + (i*16), 0, 16, 16);
			
		}  for(int i =0; i <4; i++) {
			
			upEnemy[i] = Game.spritesheet.getSprite(240 + (i*16), 48, 16, 16);
			
		}
	}
	public void update(){
		
		maskx = 0;
		masky = 0;
		maskw = 13;
		maskh = 16;
		moved = false;
		if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 100) {
			if(this.isColiddingWithPlayer() == false){
				if((int)x < Game.player.getX() && World.isFree3((int)(x+speed), this.getY())
						&& !isCollidding((int)(x+speed), this.getY())) {
					x+=speed;
					dir = right_dir;
					moved = true;
				}else if((int)x > Game.player.getX() && (World.isFree3((int)(x-speed), this.getY())
						&& !isCollidding((int)(x-speed), this.getY()))) {
					x-=speed;
					dir = left_dir;
					moved = true;
					}if((int)y < Game.player.getY() && World.isFree3(this.getX(), (int)(y+speed))
						&& !isCollidding(this.getX(), (int)(y+speed))) {
					y+=speed;
					dir = down_dir;
					moved = true;
				}else if((int)y> Game.player.getY() && World.isFree3(this.getX(), (int)(y-speed))
						&& !isCollidding(this.getX(), (int)(y-speed))) {
		        y-=speed;
		        dir = up_dir;
		        moved = true;
		        
				}
	}else {
		if(Game.rand.nextInt(100) < 40 ) {
		Game.player.life--;
		Game.player.isDamage = true;
		//System.out.println("vida �" + Game.player.life);
		}
		
	}
		}
		
		
		
		if(moved) {
		frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
				index = 0;
				
				}
			
			} }
			
			collidingBullet();
			collidingBullet2();
			
	      if(life <= 0) {
	    	  destroySelf();
	    	  if(Game.rand.nextInt(100) < 50 ) {
	    	  LifePack pack = new LifePack(this.getX(),this.getY(),16,16,Entity.LIFEPACK_EN);
				pack.setMask(3, 5, 8, 8);
				Game.entities.add(pack);
	    	  }
	    	  return;
	    	  
	      }
	      
	      if(isDamage) {
	    	  this.damageCurrent++;
	    	  if(this.damageCurrent == this.damageFrames) {
	    		  this.damageCurrent = 0;
	    		  this.isDamage = false;
	    	  }
	      }
	      
	}

	public void destroySelf() {
		Game.enemies.remove(this);
		Game.entities.remove(this);
	}
	
	public void collidingBullet() {
		for(int i = 0; i<Game.bulletShoot.size(); i++) {
			Entity e = Game.bulletShoot.get(i);
				if(Entity.isCollidding(this, e)) {
					isDamage = true;
					life-=10; 
					Game.bulletShoot.remove(i);
					return;
				}
			}
		}
	public void collidingBullet2() {
		for(int i = 0; i<Game.bulletShoot2.size(); i++) {
			Entity e = Game.bulletShoot2.get(i);
				if(Entity.isCollidding(this, e)) {
					isDamage = true;
					life-=15; 
					Game.bulletShoot2.remove(i);
					return;
				}
			}
		}
	
	
	public boolean isColiddingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(getX() + maskx ,getY()+ masky,maskw,maskh);
		
		Rectangle player = new Rectangle(Game.player.getX(),Game.player.getY(),16,16);
		
		return enemyCurrent.intersects(player);
			
		
	}
	
	public boolean isCollidding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext + maskx, ynext + masky, maskw, maskh); 
		for (int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);

			if (e == this) {
				continue;
			}
			Rectangle targetEnemy = new Rectangle(e.getX() + maskx, e.getY() + masky, maskw, maskh);
			if (enemyCurrent.intersects(targetEnemy)) {
				return true;
			}

		}

		return false;	
	}
	
	
		
		 
	
	public void render(Graphics g) {
		
		if(!isDamage) {
			if(dir == right_dir) {
				g.drawImage(rightEnemy[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				
				
					
				
				
					}else if(dir == left_dir) {
				g.drawImage(leftEnemy[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				
				 }	
				
				 if(dir == up_dir) {
							
					 g.drawImage(upEnemy[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				
				}else if(dir == down_dir) {
					g.drawImage(downEnemy[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
					
					}
	}else {
		g.drawImage(ENEMY_FEEDBACK, this.getX() - Camera.x,this.getY() - Camera.y, null);
	}
		//g.setColor(Color.blue);
		//g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, maskw, maskh);
	}
}

