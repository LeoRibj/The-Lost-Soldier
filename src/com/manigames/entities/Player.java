package com.manigames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.manigames.graficos.Spritesheet;
import com.manigames.main.Game;
import com.manigames.main.Menu;
import com.manigames.world.Camera;
import com.manigames.world.Door;
import com.manigames.world.Tile;
import com.manigames.world.World;



public class Player extends Entity{

	public boolean right,up,left,down;
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int rightgun_dir = 0, leftgun_dir = 1, upgun_dir = 2, downgun_dir = 3;
	public int rightgun2_dir = 0, leftgun2_dir = 1, upgun2_dir = 2, downgun2_dir = 3;
	public int dir = right_dir;
	public int dir_gun = rightgun_dir;
	public int dir_gun2 = rightgun2_dir;
	public double speed = 1.4;
	public double stamina = 10;
	public double maxStamina = 50;
	public boolean correr = false;
	public boolean walk = true;
	public boolean getkey = false;
	public boolean teto = false;
	private int frames = 0,maxFrames = 5,index = 0, maxIndex = 3;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] downPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] rightGun;
	private BufferedImage[] leftGun;
	private BufferedImage[] downGun;
	private BufferedImage[] upGun;
	private BufferedImage[] rightGun2;
	private BufferedImage[] leftGun2;
	private BufferedImage[] downGun2;
	private BufferedImage[] upGun2;
	
	public boolean bossope = false;
	
	public BufferedImage playerDamage;
	
	public static boolean hasGun = false;
	public int hasguns = 0;
	public static boolean hasGun2 = false;
	public int hasgun2s = 0;
	public  double ammo = 0;
	public  double maxAmmo = 30;
	
	public boolean isDamage = false;
	private int damageFrames = 0;
	private int cadence = 0;
	private int cadence1 = 0;
	
	private int maxcadence1 = 8;
	private int maxcadence = 13;
	
	public boolean shoot = false;
	public boolean shoot2 = false;
	
	
	
	public boolean chestopen = false;
	
	public double life = 20;
	public double maxLife = 100;
	public boolean acionada = false;
	public boolean use = false;
    public double bleeding = 0;
	public boolean getbosskey = false;
	//public boolean saveGame = false;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		rightGun = new BufferedImage[4];
		leftGun = new BufferedImage[4];
		downGun = new BufferedImage[4];
		upGun = new BufferedImage[4];
		rightGun2 = new BufferedImage[4];
		leftGun2 = new BufferedImage[4];
		downGun2 = new BufferedImage[4];
		upGun2 = new BufferedImage[4];
		
		
		
		playerDamage = Game.spritesheet.getSprite(0, 16, 16, 16);
		
		for(int i =0; i <4; i++) {
			
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 0, 16, 16);
			
		}
            for(int i =0; i <4; i++) {
			
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 16, 16, 16);
			
		}   for(int i =0; i <4; i++) {
			
			downPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 32, 16, 16);
			
		}  for(int i =0; i <4; i++) {
			
			upPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 48, 16, 16);
			
		}
          for(int i =0; i <4; i++) {
			
			rightGun[i] = Game.spritesheet.getSprite(96 + (i*16), 48, 16, 16);
			
		}
            for(int i =0; i <4; i++) {
			
			leftGun[i] = Game.spritesheet.getSprite(96 + (i*16), 64, 16, 16);
			
		}   for(int i =0; i <4; i++) {
			
			downGun[i] = Game.spritesheet.getSprite(96 + (i*16), 80, 16, 16);
			
		}  for(int i =0; i <4; i++) {
			
			upGun[i] = Game.spritesheet.getSprite(96 + (i*16), 96, 16, 16);
			
		}for(int i =0; i <4; i++) {
			
			rightGun2[i] = Game.spritesheet.getSprite(96 + (i*16), 112, 16, 16);
			
		}
            for(int i =0; i <4; i++) {
			
			leftGun2[i] = Game.spritesheet.getSprite(96 + (i*16), 128, 16, 16);
			
		}   for(int i =0; i <4; i++) {
			
			downGun2[i] = Game.spritesheet.getSprite(96 + (i*16), 144, 16, 16);
			
		}  for(int i =0; i <4; i++) {
			
			upGun2[i] = Game.spritesheet.getSprite(96 + (i*16), 96, 16, 16);
			
		}
           
	}
	
	
	
	public void update() {
	
	
	
	
		if(correr) {
			
			if(stamina >0) {
				
				Game.player.speed = 2.2;
				stamina--;
				//System.out.print("correndo");
			}
			}else{
			Game.player.speed = 1.5;
			}
		
		
		
		if(this.hasguns == 1) {
			Game.player.hasGun = true;
		}else if(this.hasguns == 0) {
			Game.player.hasGun = false;
		}
		if(this.hasgun2s == 1) {
			Game.player.hasGun2 = true;
		}else if(this.hasgun2s == 0) {
			Game.player.hasGun2 = false;
		}
	    
		
		moved = false;
		if(right  && World.isFree((int)(x+speed),this.getY())  && World.isFree3((int)(x+speed),this.getY())) {
			moved = true;
			dir = right_dir;
			dir_gun = rightgun_dir;
			dir_gun2 = rightgun2_dir;
			x+=speed;	
		}else if(left && World.isFree((int)(x-speed),this.getY()) && World.isFree3((int)(x-speed),this.getY()))  {
			moved = true;
			dir = left_dir;
			dir_gun = leftgun_dir;
			dir_gun2 = leftgun2_dir;
				x-=speed;
		}if(up  && World.isFree(this.getX(),(int)(y-speed)) && World.isFree3(this.getX(),(int)(y-speed)))  {
			moved = true;
			dir = up_dir;
			dir_gun = upgun_dir;
			dir_gun2 = upgun2_dir;
			y-=speed;
		}else if (down && World.isFree(this.getX(),(int)(y+speed)) && World.isFree3(this.getX(),(int)(y+speed))) {
			moved = true;
			dir = down_dir;
			dir_gun = downgun_dir;
			dir_gun2 = downgun2_dir;
			y+=speed;
		}
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex)
				index = 0;
			}
			
		}else {
			stamina++;
			if(stamina > maxStamina) {
				stamina = maxStamina;
				//System.out.print("andando");
			}
		}
		
		
		
		this.checkColisionLifePack();
		checkColisionmaxBossOpen();
		this.checkColisionBullet();
		checkColisionNlx();
		this.checkColisionGun();
		this.checkColisionGun2();
		checkColisionmaxLife();
		checkColisionsavetent();
		checkColisionbulletmax();
		checkColisionAlavanca();
		checkColisionkey();
		checkColisionbosskey();
		checkColisionCLosed();
		checkColisionchest();
		
		
		
		for(int i = 0; i<Game.enemyBullet.size(); i++) {
			Entity e = Game.enemyBullet.get(i);
				if(Entity.isCollidding(this, e)) {
				    Game.player.isDamage = true;
					life-=10; 
					Game.enemyBullet.remove(i);
					bleeding = 50;
					return;
					
				}
			}
		
		if(bleeding >0) {
			bleeding--;
			life--;
		}
		
		if(isDamage) {
			this.damageFrames++;
			if(this.damageFrames == 1) {
				this.damageFrames = 0;
				isDamage = false;
			}
		}
		
		
		

			if(shoot) {
				cadence1++;
		         
		    if(cadence1 == maxcadence1) {
		    	
		    	cadence1 = 0;
			if((hasGun == true) && ammo > 0 ) {
				ammo--;
			int dx = 0;
			int dy = 0;
			int px = 0;
			int py = 0;
			if(dir_gun == rightgun_dir) {
				 px = 12;
				 py= 8;
				dx = 1; 
			}else if(dir_gun == leftgun_dir){
				px = 2;
				 py= 8;
				 dx = -1;
			}else if(dir_gun == upgun_dir) {
				px = 9;
				 py= 2; 
				dy = -1;
			}else if (dir_gun == downgun_dir){
				px = 9;
				 py= 8;
				dy = 1;
			}
			Bulletshoot bullet = new Bulletshoot(this.getX()+px,this.getY()+py,2,2,null,dx,dy);
			Game.bulletShoot.add(bullet);
			
			}
			}}
			if(shoot2) {
				cadence++;
				
				if(cadence == maxcadence) {
					cadence = 0;
				if((hasGun2 == true) && ammo > 0 ) {
					ammo--;
				int dx = 0;
				int dy = 0;
				int px = 0;
				int py = 0;
				if(dir_gun2 == rightgun2_dir) {
					 px = 12;
					 py= 8;
					dx = 1; 
				}else if(dir_gun2 == leftgun2_dir){
					px = 2;
					 py= 8;
					 dx = -1;
				}else if(dir_gun2 == upgun2_dir) {
					px = 9;
					 py= 2; 
					dy = -1;
				}else if (dir_gun2 == downgun2_dir){
					px = 9;
					 py= 8;
					dy = 1;
				}
			
			
			Bulletshoot2 bullet2 = new Bulletshoot2(this.getX()+px,this.getY()+py,2,2,null,dx,dy);
			Game.bulletShoot2.add(bullet2);
			
		        }
				}}
		
		if(life <= 0) {
			life = 0;
			Game.gameState = "game_over";
			
		}
	
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGTH/2),0,World.HEIGHT*16 - Game.HEIGTH);
	
	
	}
	
	
	public void checkColisionGun() {
		
			for(int i = 0;i < Game.entities.size(); i++) {
		     Entity atual = Game.entities.get(i);
			if(atual instanceof Weapon) {
				if(Entity.isCollidding(this, atual)) {
					this.hasguns = 1;
					this.hasgun2s = 0;
							Game.entities.remove(atual);
						
				}
			}
		}
	}
	public void checkColisionGun2() {
		for(int i = 0;i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Weapon2) {
				if(Entity.isCollidding(this, atual)) {
					this.hasgun2s = 1;
					this.hasguns = 0;
							Game.entities.remove(atual);
						
				}
			}
		}
	}
	
	
	public void checkColisionBullet() {
		
			for(int i = 0;i < Game.entities.size(); i++) {
				Entity atual = Game.entities.get(i);
				if(atual instanceof Bullet) {
					if(Entity.isCollidding(this, atual)) {
						ammo+=10; 
						if(ammo > 30)
							ammo = maxAmmo;
						Game.entities.remove(atual);
	    }		
       }
      }
	}
	
	public void checkColisionNlx() {
		
		for(int i = 0;i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof NextLevel) {
				if(Entity.isCollidding(this, atual)) {
						Game.CUR_LEVEL++;
						if(Game.CUR_LEVEL > Game.MAX_LEVEL) {
							Game.gameState = "menu";
						}
						
					String newWorld = "level" + Game.CUR_LEVEL + ".png";
					World.nextlevel(newWorld);
					
						
					}
    }		
   }
	}
	
	
	public void checkColisionmaxLife() {
		
		for(int i = 0;i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof MaxxLife) {
				if(Entity.isCollidding(this, atual)) {
					Game.player.maxLife += 50;
							Game.entities.remove(atual);
						
				}
			}
		}
	}
	


public void checkColisionmaxBossOpen() {
		
		for(int i = 0;i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof DoorBossOpe) {
				if(Entity.isCollidding(this, atual)) {
					if(getbosskey) {
					if(use) {
						bossope = true;
					}
					}
				}
			}
		}
	}
	
	
public void checkColisionbulletmax() {
		
		for(int i = 0;i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof BulletMaxx) {
				if(Entity.isCollidding(this, atual)) {
					Game.player.maxAmmo += 10;
					Game.entities.remove(atual);
    }		
   }
  }
}
public void checkColisionsavetent() {
		
		for(int i = 0;i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof SaveTent) {
				if(Entity.isCollidding(this, atual)) {
					Game.gameState = "menus";
					Game.entities.remove(atual);
    }		
   }
  }
}
	public void checkColisionLifePack() {
		for(int i = 0;i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof LifePack) {
				if(Entity.isCollidding(this, atual)) {
					life+=25;
							if(life > maxLife) 
							life = maxLife; 
							Game.entities.remove(atual);
						
				}
			}
		}
	}
	
	public void checkColisionkey() {
		for(int i = 0;i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Key) {
				if(Entity.isCollidding(this, atual)) {
					getkey = true;
							Game.entities.remove(atual);
						
				}
			}
		}
	}
	
	public void checkColisionCLosed() {
		for(int i = 0;i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof ClosedDoor) {
				if(Entity.isCollidding(this, atual)) {
					acionada = false;
							
						
				}
			}
		}
	}
	
	public void checkColisionchest() {
		for(int i = 0;i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Chestcoli) {
				if(Entity.isCollidding(this, atual)) {
					if(getkey) {
						
					
					if(use) {
						chestopen = true;
						getkey = false;
					}
					}	
				}
			}
		}
	}
	public void checkColisionbosskey() {
		for(int i = 0;i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if(atual instanceof Bosskey) {
				if(Entity.isCollidding(this, atual)) {
					getbosskey = true;
							Game.entities.remove(atual);
						
				}
			}
		}
	}
	
	public void checkColisionAlavanca(){
	for(int i = 0;i < Game.entities.size(); i++) {
		Entity atual = Game.entities.get(i);
		if(atual instanceof Alavancacolision) {
			
			if(Entity.isCollidding(this, atual)) {
				if(use) {
	        System.out.println("ligando");
          Game.player.acionada = true;
	         
			
			}
			}
		}		
		}
		}
	
	
	public void render(Graphics g) {
		
		
		if(!isDamage) {
			if (hasGun == false && hasGun2 == false) {	
		if(dir == right_dir) {
		g.drawImage(rightPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
		
		
			
		
		
			}else if(dir == left_dir) {
		g.drawImage(leftPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
		
		 }	
		
		 if(dir == up_dir) {
					
			 g.drawImage(upPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
		
		}else if(dir == down_dir) {
			g.drawImage(downPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
			
			}
			}
		
		else if(hasGun == true){
			if(dir_gun == rightgun_dir) {
				g.drawImage(rightGun[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				
				}else if(dir_gun == leftgun_dir) {
				g.drawImage(leftGun[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				
				 }if(dir_gun == upgun_dir) {
						g.drawImage(upGun[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				 }else if(dir_gun == downgun_dir) {
						g.drawImage(downGun[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				 }	
			
		}else if(hasGun2 == true){
			if(dir_gun2 == rightgun2_dir) {
				g.drawImage(rightGun2[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				
				}else if(dir_gun2 == leftgun2_dir) {
				g.drawImage(leftGun2[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				
				 }if(dir_gun2 == upgun2_dir) {
						g.drawImage(upGun2[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				 }else if(dir_gun2 == downgun2_dir) {
						g.drawImage(downGun2[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
				 }	
			
		}
		
		
	
	            
}else if(isDamage == true){
	g.drawImage(playerDamage,this.getX() - Camera.x, this.getY() - Camera.y, null);
}
	  
	}
}
