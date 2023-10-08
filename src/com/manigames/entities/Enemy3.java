package com.manigames.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.manigames.main.Game;
import com.manigames.world.Camera;
import com.manigames.world.World;

public class Enemy3 extends Entity{
private double speed = 1;
	
	private int maskx = 0, masky = 0 , maskw = 16, maskh = 16;
	
	private int frames = 0,maxFrames = 20,index = 0, maxIndex = 3;
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	private int life = 15;
	public boolean shoot = false;
	private int cadence = 0;
	private boolean isDamage = false;
	private int damageFrames = 10, damageCurrent = 0;
	public boolean ismoved = false;
	private BufferedImage[] rightEnemy;
	private BufferedImage[] leftEnemy;
	private BufferedImage[] downEnemy;
	private BufferedImage[] upEnemy;
	private boolean moved = false;
	public Enemy3(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, height,height, sprite);
		
		rightEnemy = new BufferedImage[4];
		leftEnemy = new BufferedImage[4];
		downEnemy = new BufferedImage[4];
		upEnemy = new BufferedImage[4];
		
for(int i =0; i <4; i++) {
			
			rightEnemy[i] = Game.spritesheet.getSprite(240 + (i*16), 64, 16, 16);
			
		}
            for(int i =0; i <4; i++) {
			
			leftEnemy[i] = Game.spritesheet.getSprite(240 + (i*16), 80, 16, 16);
			
		}   for(int i =0; i <4; i++) {
			
			downEnemy[i] = Game.spritesheet.getSprite(240 + (i*16), 96, 16, 16);
			
		}  for(int i =0; i <4; i++) {
			
			upEnemy[i] = Game.spritesheet.getSprite(240 + (i*16), 112, 16, 16);
			
		}
	}
	public void update(){
		
		maskx = 0;
		masky = 0;
		maskw = 13;
		maskh = 16;
		moved = false;
		if(this.calculateDistance(this.getX(), this.getY(), Game.player.getX(), Game.player.getY()) < 118) {
			if(this.isColiddingWithPlayer() == false){
				
				if((int)x < Game.player.getX() && World.isFree3((int)(x+speed), this.getY())
						&& !isCollidding((int)(x+speed), this.getY())) {
					x+=speed;
					dir = right_dir;
					shoot = true;
					moved = true;
				}else if((int)x > Game.player.getX() && (World.isFree3((int)(x-speed), this.getY())
						&& !isCollidding((int)(x-speed), this.getY()))) {
					x-=speed;
					dir = left_dir;
					shoot = true;
					moved = true;
					}if((int)y < Game.player.getY() && World.isFree3(this.getX(), (int)(y+speed))
						&& !isCollidding(this.getX(), (int)(y+speed))) {
					y+=speed;
					dir = down_dir;
					shoot = true;
					moved = true;
				}else if((int)y> Game.player.getY() && World.isFree3(this.getX(), (int)(y-speed))
						&& !isCollidding(this.getX(), (int)(y-speed))) {
		        y-=speed;
		        dir = up_dir;
		       shoot = true;
		       moved = true;
		       
				}
	}else {
		
		if(Game.rand.nextInt(100) < 40 ) {
		Game.player.life--;
		Game.player.isDamage = true;
		//System.out.println("vida é" + Game.player.life);
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
	    	
	    	  if(Game.rand.nextInt(100) < 50) {
	    		  Bullet bul = new Bullet(this.getX(),this.getY(),16,16,Entity.BULLET_EN);
					bul.setMask(4,2,8,8);
					Game.entities.add(bul);
	    	 }else if(Game.rand.nextInt(100) < 45) {
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
	      if(shoot) {
	    	  shoot = false;
				cadence++;
				if(cadence == 50){
				if(speed > 0) {
					
				int dx = 0;
				int dy = 0;
				int px = 0;
				int py = 0;
				if(dir == right_dir) {
					 px = 12;
					 py= 8;
					dx = 1; 
				}else if(dir == left_dir){
					px = 2;
					 py= 8;
					 dx = -1;
				}else if(dir == up_dir) {
					px = 9;
					 py= 2; 
					dy = -1;
				}else if (dir == down_dir){
					px = 9;
					 py= 8;
					dy = 1;
				}
				
			
			EnemyBullet bullet = new EnemyBullet(this.getX()+px,this.getY()+py,2,2,null,dx,dy);
			Game.enemyBullet.add(bullet);
			cadence = 0;
		}}}
	   
	    
	   
	}
	

	public void destroySelf() {
		Game.enemies3.remove(this);
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
		Rectangle enemyCurrent = new Rectangle(xnext + maskx, ynext + masky, maskw, maskh); // Classe que cria
																							// retangulos fictícios para
																							// testar colisões.
		for (int i = 0; i < Game.enemies3.size(); i++) {
			Enemy3 e = Game.enemies3.get(i);

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

