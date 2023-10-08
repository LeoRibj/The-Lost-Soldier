package com.manigames.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


import com.manigames.entities.Bullet;
import com.manigames.entities.BulletMaxx;
import com.manigames.entities.Bulletshoot;
import com.manigames.entities.Bulletshoot2;
import com.manigames.entities.Chestcoli;
import com.manigames.entities.ClosedDoor;
import com.manigames.entities.DoorBossOpe;
import com.manigames.entities.Enemy;
import com.manigames.entities.Enemy2;
import com.manigames.entities.Enemy3;
import com.manigames.entities.Enemy4;
import com.manigames.entities.EnemyBullet;
import com.manigames.entities.Entity;
import com.manigames.entities.Key;
import com.manigames.entities.LifePack;
import com.manigames.entities.MaxxLife;
import com.manigames.entities.NextLevel;

import com.manigames.entities.Player;
import com.manigames.entities.Alavancacolision;
import com.manigames.entities.Bosskey;
import com.manigames.entities.SaveTent;

import com.manigames.entities.Weapon;
import com.manigames.entities.Weapon2;
import com.manigames.graficos.Spritesheet;
import com.manigames.main.Game;



public class World  {
	
	public static Tile[] tiles;
	
	public static int WIDTH,HEIGHT;
	
	public static final int TILE_SIZE = 16;
	
	public World(String path){
		
		
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
		
		int[] pixels = new int[map.getWidth() * map.getHeight()];
		WIDTH = map.getTileWidth();
		HEIGHT = map.getHeight();
		tiles = new Tile[map.getWidth() * map.getHeight()];
		
		map.getRGB(0, 0, map.getWidth(), map.getHeight(),pixels, 0, map.getWidth());
		
		for(int xx = 0; xx < map.getWidth(); xx++) {
			for(int yy = 0; yy < map.getHeight(); yy++) {
				int pixelAtual = pixels[xx +(yy * map.getWidth())];
				tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
			if(pixelAtual == 0xFF000000) {
				tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
			
			}else if(pixelAtual == 0xFF6E966C) {
				tiles[xx + (yy * WIDTH)] = new FloorTile2(xx*16,yy*16,Tile.TILE_FLOOR2);
			
			}else if(pixelAtual == 0xFFFFFFFF) {
				tiles[xx + (yy * WIDTH)] = new WallTile1(xx*16,yy*16,Tile.TILE_WALL1);
			
			}else if(pixelAtual == 0xFF513828) {
				tiles[xx + (yy * WIDTH)] = new WallTile2(xx*16,yy*16,Tile.TILE_WALL2);
			
			}else if(pixelAtual == 0xFF6B4A35) {
				tiles[xx + (yy * WIDTH)] = new WallTile3(xx*16,yy*16,Tile.TILE_WALL3);
			
			}else if(pixelAtual == 0xFF808080) {
				tiles[xx + (yy * WIDTH)] = new WallTile4(xx*16,yy*16,Tile.TILE_WALL4);
			
			}else if(pixelAtual == 0xFF91003C) {
				tiles[xx + (yy * WIDTH)] = new Teto(xx*16,yy*16,Tile.Teto_TILE);
			
			}else if(pixelAtual == 0xFF5D4568) {
				tiles[xx + (yy * WIDTH)] = new Walltrees(xx*16,yy*16,Tile.Walltrees_Tile);
			
			}else if(pixelAtual == 0xFF93967B) {
				tiles[xx + (yy * WIDTH)] = new Ponte(xx*16,yy*16,Tile.PONTE_TILE);
			
			}else if(pixelAtual == 0xFF917F96) {
				tiles[xx + (yy * WIDTH)] = new Pontebaixo(xx*16,yy*16,Tile.PONTEbaixo_TILE);
			
			}else if(pixelAtual == 0xFF444444) {
				tiles[xx + (yy * WIDTH)] = new Pontecima(xx*16,yy*16,Tile.PONTECIMA_TILE);
			
			}else if(pixelAtual == 0xFF965B58) {
				tiles[xx + (yy * WIDTH)] = new Water(xx*16,yy*16,Tile.Water_TILE);
			}else if(pixelAtual == 0xFF666825) {
				tiles[xx + (yy * WIDTH)] = new Door(xx*16,yy*16,Tile.DOOR_TILE);
			}else if(pixelAtual == 0xFFB9AFFF) {
				tiles[xx + (yy * WIDTH)] = new BossDoor(xx*16,yy*16,Tile.BOSSDOOR_TILE);
			}else if(pixelAtual == 0xFFEBFFA3) {
				tiles[xx + (yy * WIDTH)] = new Chest(xx*16,yy*16,Tile.Chest_TILE);
			}else if(pixelAtual == 0xFF167A46) {
				tiles[xx + (yy * WIDTH)] = new Alavanca(xx*16,yy*16,Tile.ALAVANCA_TILE);
			}else if(pixelAtual == 0xFF00A59D) {
				tiles[xx + (yy * WIDTH)] = new Teto(xx*16,yy*16,Tile.Teto_TILE);
				LifePack pack = new LifePack(xx*16,yy*16,16,16,Entity.LIFEPACK_EN);
				pack.setMask(3, 5, 8, 8);
				Game.entities.add(pack);
				
			}else if(pixelAtual == 0xFFF7F700) {
				tiles[xx + (yy * WIDTH)] = new Teto(xx*16,yy*16,Tile.Teto_TILE);
				Bullet bullet = new Bullet(xx*16,yy*16,16,16,Entity.BULLET_EN);
				bullet.setMask(4,2,8,8);
				Game.entities.add(bullet);
				
			}else if(pixelAtual == 0xFFD300C5) {
				tiles[xx + (yy * WIDTH)] = new Teto(xx*16,yy*16,Tile.Teto_TILE);
				Game.entities.add(new Weapon(xx*16,yy*16,16,16,Entity.WEAPON_EN));
				
			}else if(pixelAtual == 0xFF91E28C) {
				//player
				tiles[xx + (yy * WIDTH)] = new Teto(xx*16,yy*16,Tile.Teto_TILE);
				Chestcoli pack = new Chestcoli(xx*16,yy*16,16,16,Entity.CHEST_EN);
				pack.setMask(2, 5, 10, 8);
				Game.entities.add(pack);
				
			}else if(pixelAtual == 0xFF60594A) {
				//player
				tiles[xx + (yy * WIDTH)] = new FloorTile2(xx*16,yy*16,Tile.TILE_FLOOR2);
				ClosedDoor en = new ClosedDoor(xx*16,yy*16,16,16,Entity.CLOSED_EN);
				en.setMask(0,15,16,4);
				Game.entities.add(en);
				
			}else if(pixelAtual == 0xFF680022) {
				//player
				tiles[xx + (yy * WIDTH)] = new Teto(xx*16,yy*16,Tile.Teto_TILE);
				Alavancacolision alavanca = new Alavancacolision(xx*16,yy*16,16,16,Entity.ALAVANCA_EN);
				alavanca.setMask(2,3,12,12);
				Game.entities.add(alavanca);
				
			}else if(pixelAtual == 0xFF0026FF) {
				//player
				Game.player.setX(xx*16);
				Game.player.setY(yy*16);
				
			}else if(pixelAtual == 0xFF00FFF6) {
				//life
				LifePack pack = new LifePack(xx*16,yy*16,16,16,Entity.LIFEPACK_EN);
				pack.setMask(3, 5, 8, 8);
				Game.entities.add(pack);
				
			}else if(pixelAtual == 0xFFA2FF9E) {
				
				Chestcoli pack = new Chestcoli(xx*16,yy*16,16,16,Entity.CHEST_EN);
				pack.setMask(2, 5, 10, 8);
				Game.entities.add(pack);
				
			}else if(pixelAtual == 0xFF9EB9FF) {
				//life
				NextLevel nxl = new NextLevel(xx*16,yy*16,16,16,Entity.NEXTLEVEL_EN);
				nxl.setMask(12, 5, 10, 8);
				Game.entities.add(nxl);
					
			}else if(pixelAtual == 0xFFFF00EE) {
				//weapon
				Game.entities.add(new Weapon(xx*16,yy*16,16,16,Entity.WEAPON_EN));
			}else if(pixelAtual == 0xFF0094FF) {
				//weapon2
				Game.entities.add(new Weapon2(xx*16,yy*16,16,16,Entity.WEAPON_EN2));
				
			}else if(pixelAtual == 0xFF888968) {
				//Save
				Game.entities.add(new SaveTent(xx*16,yy*16,16,16,Entity.SAVETENT_EN));
				
			}else if(pixelAtual == 0xFFFFFF00) {
				//bullet
				Bullet bullet = new Bullet(xx*16,yy*16,16,16,Entity.BULLET_EN);
				bullet.setMask(4,2,8,8);
				Game.entities.add(bullet);
			}else if (pixelAtual == 0xFF6302FF) {
				MaxxLife lifemax = new MaxxLife(xx*16,yy*16,16,16,Entity.MAXXLIFE_EN);
				lifemax.setMask(4,2,8,8);
				Game.entities.add(lifemax);
				
			}else if (pixelAtual == 0xFF00FF04) {
				BulletMaxx bulletmax = new BulletMaxx(xx*16,yy*16,16,16,Entity.BULLETMAXX_EN);
				bulletmax.setMask(4,2,8,8);
				Game.entities.add(bulletmax);
			}else if (pixelAtual == 0xFFFF0054) {
				Alavancacolision alavanca = new Alavancacolision(xx*16,yy*16,16,16,Entity.ALAVANCA_EN);
				alavanca.setMask(2,3,12,12);
				Game.entities.add(alavanca);
			}else if (pixelAtual == 0xFF700059) {
				Key key = new Key(xx*16,yy*16,16,16,Entity.KEY_EN);
				key.setMask(2,3,12,12);
				Game.entities.add(key);
			}else if (pixelAtual == 0xFFB5FFDD) {
				DoorBossOpe boss = new DoorBossOpe(xx*16,yy*16,16,16,Entity.BOSSDOROPEN_EN);
				boss.setMask(12, 5, 10, 8);
				Game.entities.add(boss);
			}else if (pixelAtual == 0xFFFFD8DA) {
				Bosskey bosskey = new Bosskey(xx*16,yy*16,16,16,Entity.BOSSKEY_EN);
				bosskey.setMask(2,3,12,12);
				Game.entities.add(bosskey);
			}else if(pixelAtual == 0xFFFF0004) {
			 //enemy
				Enemy en = new Enemy(xx*16,yy*16,16,16,Entity.ENEMY_EN);
				Game.entities.add(en);
				Game.enemies.add(en);
				
			
			
			}else if(pixelAtual == 0xFF706856) {
				 //enemy
					ClosedDoor en = new ClosedDoor(xx*16,yy*16,16,16,Entity.CLOSED_EN);
					en.setMask(0,15,16,4);
					Game.entities.add(en);
					
					
				
				
				}else if(pixelAtual == 0xFFFFA366) {
				 //enemy3
					Enemy3 en = new Enemy3(xx*16,yy*16,16,16,Entity.ENEMY_EN3);
					Game.entities.add(en);
					Game.enemies3.add(en);
					
				
				
				}else if(pixelAtual == 0xFFB2FF00) {
					 //enemy4
						Enemy4 en = new Enemy4(xx*16,yy*16,32,32,Entity.ENEMY_EN4);
						Game.entities.add(en);
						Game.enemies4.add(en);
					} else if(pixelAtual == 0xFFFF631C) {
				 //enemy2
					Enemy2 en = new Enemy2(xx*16,yy*16,32,32,Entity.ENEMY_EN2);
					Game.entities.add(en);
					Game.enemies2.add(en);
				} 
			
			else {
				tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
			}
			}
			
		}
			
		
		}
		
		 catch (IOException e) {
			
			e.printStackTrace();
		}
		}
		public static boolean isFree(int xnext, int ynext) {
			
			int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext + TILE_SIZE -1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext + TILE_SIZE - 1 ) / TILE_SIZE;
		
		int x4 = (xnext + TILE_SIZE - 1)/ TILE_SIZE;
		int y4 = (ynext + TILE_SIZE - 1) / TILE_SIZE;
		if(Game.player.acionada) {
			return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile1) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof WallTile2) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof WallTile3) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof Alavanca) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof Chest) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile1) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile2) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile3) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof Alavanca) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof Chest) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile1) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile2) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile3) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof Alavanca) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof Chest) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile1) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile2) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile3) ||
		             (tiles[x4 + (y4*World.WIDTH)] instanceof Chest) ||
			         (tiles[x4 + (y4*World.WIDTH)] instanceof Alavanca));
		}else {
			return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile1) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof WallTile2) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof WallTile3) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof Alavanca) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof Chest) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof Door) ||
					 (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile1) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile2) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile3) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof Alavanca) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof Door) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof Chest) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile1) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile2) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile3) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof Alavanca) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof Door) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof Chest) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile1) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile2) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile3) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof Door) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof Chest) ||
			         (tiles[x4 + (y4*World.WIDTH)] instanceof Alavanca));
		}
		
		 
	}
		
		public static boolean isFree2(int xnext, int ynext) {
			int x1 = xnext / TILE_SIZE;
			int y1 = ynext / TILE_SIZE;
			
			int x2 = (xnext + 33 )/ TILE_SIZE;
			int y2 = ynext / TILE_SIZE;
			
			int x3 = xnext / TILE_SIZE;
			int y3 = (ynext + 33 ) / TILE_SIZE;
			
			int x4 = (xnext + 33 )/ TILE_SIZE;
			int y4 = (ynext + 33 ) / TILE_SIZE;
			return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile1) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof WallTile2) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof WallTile3) ||
					 
					 (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile1) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile2) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile3) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile1) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile2) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile3) ||
		            
		             (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile3) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile1) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile2) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile3));
		
		}
		public static boolean isFree3(int xnext, int ynext) {
			int x1 = xnext / TILE_SIZE;
			int y1 = ynext / TILE_SIZE;
			
			int x2 = (xnext + TILE_SIZE-1) / TILE_SIZE;
			int y2 = ynext / TILE_SIZE;
			
			int x3 = xnext / TILE_SIZE;
			int y3 = (ynext + TILE_SIZE -1 ) / TILE_SIZE;
			
			int x4 = (xnext + TILE_SIZE -1)/ TILE_SIZE;
			int y4 = (ynext + TILE_SIZE -1) / TILE_SIZE;
			
			return !(
					 (tiles[x1 + (y1*World.WIDTH)] instanceof Walltrees) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof WallTile4) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof Water) ||
					 (tiles[x2 + (y2*World.WIDTH)] instanceof Walltrees) || 
		             (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile4) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof Water) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof Walltrees) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile4) || 
		             (tiles[x3 + (y3*World.WIDTH)] instanceof Water) ||
		             (tiles[x4 + (y4*World.WIDTH)] instanceof Walltrees) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile4) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof Water));
					 
		}
		
   public static boolean isFreeitens(int xnext, int ynext, int width , int height) {
			
			int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext + width -1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext + height - 1 ) / TILE_SIZE;
		
		int x4 = (xnext + width - 1)/ TILE_SIZE;
		int y4 = (ynext + height - 1) / TILE_SIZE;
		
			return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile1) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof WallTile2) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof WallTile3) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof Alavanca) ||
					 (tiles[x1 + (y1*World.WIDTH)] instanceof Chest) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile1) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile2) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof WallTile3) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof Alavanca) ||
		             (tiles[x2 + (y2*World.WIDTH)] instanceof Chest) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile1) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile2) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof WallTile3) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof Alavanca) ||
		             (tiles[x3 + (y3*World.WIDTH)] instanceof Chest) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile1) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile2) ||
		         	 (tiles[x4 + (y4*World.WIDTH)] instanceof WallTile3) ||
		             (tiles[x4 + (y4*World.WIDTH)] instanceof Chest) ||
			         (tiles[x4 + (y4*World.WIDTH)] instanceof Alavanca));
		}
		public static void nextlevel(String level) {
			Game.entities.clear();
			Game.enemies.clear();
			Game.enemies2.clear();
			Game.enemies3.clear();
			Game.entities = new ArrayList<Entity>();	
			Game.enemies = new ArrayList<Enemy>();
			Game.enemies2 = new ArrayList<Enemy2>();
			Game.enemies3 = new ArrayList<Enemy3>();
			Game.player.acionada = false;
			Game.player.getkey = false;
			Game.player.getbosskey = false;
			Game.player.bossope = false;
			Game.player.chestopen = false;
			Game.bulletShoot = new ArrayList<Bulletshoot>();
			Game.bulletShoot2 = new ArrayList<Bulletshoot2>();
			Game.enemyBullet = new ArrayList<EnemyBullet>();
			Game.spritesheet = new Spritesheet("/spritesheet.png");
			//Game.player = new Player(0,0,16,16,Game.spritesheet.getSprite(32, 0, 16,16));
			Game.entities.add(Game.player);
			Game.world = new World("/"+level);
			return;
		}
		public static void restartGame(String level) {
			Game.player.ammo = 0;
			Game.entities.clear();
			Game.enemies.clear();
			Game.enemies2.clear();
			Game.enemies3.clear();
			Game.entities = new ArrayList<Entity>();	
			Game.enemies = new ArrayList<Enemy>();
			Game.enemies2 = new ArrayList<Enemy2>();
			Game.enemies3 = new ArrayList<Enemy3>();
		
			Game.bulletShoot = new ArrayList<Bulletshoot>();
			Game.bulletShoot2 = new ArrayList<Bulletshoot2>();
			Game.enemyBullet = new ArrayList<EnemyBullet>();
			Game.spritesheet = new Spritesheet("/spritesheet.png");
			Game.player = new Player(0,0,16,16,Game.spritesheet.getSprite(32, 0, 16,16));
			Game.entities.add(Game.player);
			Game.world = new World("/"+level);
			return;
		}
	
	public void render(Graphics g) {
		
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGTH >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) {
					continue;
				}
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
		
	}
	
}
