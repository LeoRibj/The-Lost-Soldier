package com.manigames.main;



import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.image.DataBufferInt;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.manigames.entities.Alavancacolision;
import com.manigames.entities.Bulletshoot;
import com.manigames.entities.Bulletshoot2;
import com.manigames.entities.Enemy;
import com.manigames.entities.Enemy2;
import com.manigames.entities.Enemy3;
import com.manigames.entities.Enemy4;
import com.manigames.entities.EnemyBullet;
import com.manigames.entities.Entity;

import com.manigames.entities.Player;
import com.manigames.graficos.Spritesheet;
import com.manigames.graficos.UI;
import com.manigames.world.World;



 public class Game extends Canvas implements Runnable, KeyListener, MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
    private boolean isRunning = true;
    private Thread thread;
	public static final int WIDTH = 240, HEIGTH = 160 ,SCALE = 3;
	
	public static int CUR_LEVEL = 1, MAX_LEVEL = 3;
	
	
	private BufferedImage image;
	
	public static List<Entity> entities;
	
	public static List<Enemy> enemies;
	
	public static List<Enemy2> enemies2;
	public static List<Enemy3> enemies3;
	public static List<Enemy4> enemies4;
			
	public static List<Bulletshoot> bulletShoot;
	public static List<Bulletshoot2> bulletShoot2;
	public static List<EnemyBullet> enemyBullet;
	public static Spritesheet spritesheet;
	
	
	public static World world;
	
	public static Player player;
	
	public static Random rand;
	
	public UI ui;
	
	public static String gameState = "menu";
	
	private boolean showMenssageGameover = true;
	private int framesGameOver = 0;
	private boolean restartGame = false;
	
	
	public Menu menu;
	public MenuPause menup;
	public MenuControles menuc;
	public MenuSave menus;
	public Savesusses ss;
	
	
	public int[] pixels;
	
    
	public Game() {
		rand = new Random();
		addKeyListener(this);
		addMouseListener(this);
		this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));  
		//(new Dimension(WIDTH*SCALE,HEIGTH*SCALE));
	initframe();
	//inicializando objetos
	
	
	image = new BufferedImage(WIDTH,HEIGTH,BufferedImage.TYPE_INT_ARGB);
	
	pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	
		
	
	
	
	entities = new ArrayList<Entity>();	
	enemies = new ArrayList<Enemy> ();
	enemies2 = new ArrayList<Enemy2>();
	enemies3 = new ArrayList<Enemy3>();
	enemies4 = new ArrayList<Enemy4>();
	bulletShoot = new ArrayList<Bulletshoot>();
	bulletShoot2 = new ArrayList<Bulletshoot2>();
	enemyBullet = new ArrayList<EnemyBullet>();
	
	spritesheet = new Spritesheet("/spritesheet.png");
	ui = new UI(this.spritesheet.getSprite(192, 0, 32, 16));
	player = new Player(0,0,16,16,spritesheet.getSprite(32, 0, 16,16));
	entities.add(player);
	world = new World("/level1.png");
	
	menu = new Menu();
	menup = new MenuPause();
    menuc = new MenuControles("/Controles.png");
    menus = new MenuSave();
	ss = new Savesusses();
	}
	
	public void initframe() {
	
		frame = new JFrame("the lost soldier");
		frame.add(this);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
	    thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		Game game = new Game();
        game.start();	
	}
	
	public void update() {
		
		if(gameState == "normal") {
			for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.update();
		}
			
		for(int i = 0; i < bulletShoot.size(); i++) {
			bulletShoot.get(i).update();
		}for(int i = 0; i < bulletShoot2.size(); i++) {
			bulletShoot2.get(i).update();
			
		    
		}for(int i = 0; i < enemyBullet.size(); i++) {
			enemyBullet.get(i).update();
		}
		
		
		}else if(gameState == "game_over") {
			this.framesGameOver++;
			if(this.framesGameOver == 30) {
				this.framesGameOver = 0;
				if(this.showMenssageGameover)
					this.showMenssageGameover = false;
					else
						this.showMenssageGameover = true;
			}
				
			if(restartGame) {
		      
				this.gameState = "menu";
				 
		    	   
		}
		}else if(gameState == "menu") {
				menu.update();
				
			}else if(gameState == "menup") {
				menup.update();
				
			}else if(gameState == "menuc") {
				menuc.update();
			}else if(gameState == "menus") {
				menus.update();
			}else if(gameState == "ss") {
				ss.update();
			}
		player.shoot = false;
		player.shoot2 = false;
	   player.correr = false;
	  // player.acionada = false;
	   player.use = false;
	   
	   
	}
	      
		
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
	Graphics g = image.getGraphics();
	g.setColor(new Color(0,0,0));
	g.fillRect(0, 0,WIDTH,HEIGTH);	
	
/* renderização do jogo*/
	//Graphics2D g2 = (Graphics2D) g;
	world.render(g);
	for(int i = 0; i < entities.size(); i++) {
		Entity e = entities.get(i);
		e.render(g);
	}
	for(int i = 0; i < bulletShoot.size(); i++) {
		bulletShoot.get(i).render(g);
	}for(int i = 0; i < bulletShoot2.size(); i++) {
		bulletShoot2.get(i).render(g);
	}for(int i = 0; i < enemyBullet.size(); i++) {
		enemyBullet.get(i).render(g);
	}
	
	
	ui.render(g);
	
	/*g.setFont(new Font("arial", Font.BOLD,15));
	g.setColor(Color.WHITE);
	g.drawString((int)player.life + "/"+ (int) player.maxLife, 63, 37);*/
	
	if(gameState == "game_over") {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color (0,0,0,100));
		g2.fillRect(0, 0, WIDTH*SCALE, HEIGTH*SCALE);
		g.setFont(new Font("arial", Font.BOLD,15));
		g.setColor(Color.WHITE);
		g.drawString("Game Over", 70,60);
		g.setFont(new Font("arial", Font.BOLD,10));
		if(showMenssageGameover)
		g.drawString("<PRESS ENTER TO RETURN TO THE MENU>", 15,80);
		
		
	}else if(gameState == "menu") {
		menu.render(g);
	}else if(gameState == "menup") {
		menup.render(g);
	}else if(gameState == "menus") {
		menus.render(g);
	}else if(gameState == "ss") {
		ss.render(g);
	}
	
	
	/***/
	g.dispose();
	g = bs.getDrawGraphics();
	g.drawImage//(image,0, 0,WIDTH*SCALE,HEIGTH*SCALE,null);
	(image,0, 0,Toolkit.getDefaultToolkit().getScreenSize().width
			,Toolkit.getDefaultToolkit().getScreenSize().height,null);
	
	if(gameState == "menuc") {
		menuc.render(g);
	}
	//UI fonte
	
	//g.setFont(new Font("arial", Font.BOLD,10));
	//g.setColor(Color.WHITE);
	//g.drawString("Bullets:" + (int)(Game.player.ammo) + "/" + (int)(Game.player.maxAmmo), 50,);
	
	bs.show();
	}
	
	
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				update();
				render();
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("fps" +frames);
				frames = 0;
				timer+=1000;
				
			}
		
		}
		stop();
	}

	
	public void keyTyped(KeyEvent e) {
		
		
	}

	
	public void keyPressed(KeyEvent e) {
		
	if(e.getKeyCode() == KeyEvent.VK_RIGHT || 
			e.getKeyCode() == KeyEvent.VK_D){
		
	player.right = true;
		
	}else if(e.getKeyCode() == KeyEvent.VK_LEFT || 
			e.getKeyCode() == KeyEvent.VK_A) {
		
		player.left = true;
		
	}
	if(e.getKeyCode() == KeyEvent.VK_UP || 
			e.getKeyCode() == KeyEvent.VK_W) {
		player.up = true;
	
	if(gameState == "menu"){
		menu.up = true;
		
	}
	
	if(gameState == "menup"){
		menup.up = true;
		
	}

	if(gameState == "menus"){
		menus.up = true;}
	
	}else if(e.getKeyCode() == KeyEvent.VK_DOWN || 
			e.getKeyCode() == KeyEvent.VK_S) {
		player.down = true;
	
	if(gameState == "menu"){
		menu.down = true;
		
	}	if(gameState == "menup"){
		menup.down = true;
	}if(gameState == "menus"){
			menus.down = true;
			}
		
	
	}
	
	if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
		player.correr = true;
		
	}
	if(e.getKeyCode() == KeyEvent.VK_SPACE) {
		player.shoot = true;
		player.shoot2 = true;
	}
	if(e.getKeyCode() == KeyEvent.VK_ENTER) {
		this.restartGame = true;
		if(gameState == "menu") {
			menu.enter = true;
		}
		if(gameState == "menup") {
			menup.enter = true;
		}if(gameState == "menuc") {
			menuc.enter = true;
		}
		
		if(gameState == "menus"){
			menus.enter = true;}
		
		if(gameState == "ss"){
			ss.enter = true;
	    }
	}
	if(e.getKeyCode() == KeyEvent.VK_E) {
		player.use = true;
	}
	}
	
	
	

	
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || 
				e.getKeyCode() == KeyEvent.VK_D){
			
		player.right = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT || 
				e.getKeyCode() == KeyEvent.VK_A) {
			
			player.left = false;
			
			
		}
		if(e.getKeyCode() == KeyEvent.VK_UP || 
				e.getKeyCode() == KeyEvent.VK_W) {
			
		player.up = false;
		
		
		
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN || 
				e.getKeyCode() == KeyEvent.VK_S) {
			
		player.down = false;
		
	}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.restartGame = false;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(gameState == "normal") {
				gameState = "menup";
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			player.correr = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.shoot = false;
			player.shoot2 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_E) {
			player.use = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
	

