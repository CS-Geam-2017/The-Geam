package com.Geam.main;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
public class Geam extends Canvas implements Runnable {

	/**
	 * Comment a greeting and your name to make sure it works
	 * Hello, Jake Lockey
	 * Hello, Trinity 
	 * Hello, Jackson Mansell
	 */
	
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1366, HEIGHT = 705;
	//Reg - WIDTH = 640, HEIGHT = WIDTH / 12*9
	//Full Screen - WIDTH = 1370, HEIGHT = WIDTH / 12*12
	
	public static boolean start = false;
	public static boolean paused = false;
	public static int score = 0;
	public static boolean shield = false;
	
	public static long PowerT = System.currentTimeMillis();
	
	private Thread thread;
	private boolean running = false;
	
	
	private Random r;
	
	private Handler handler;
	
	public static int Pause = 0;
	
	public Geam() {
		this.addKeyListener(new KeyInput(handler));
		// ^ Tells computer to listen for key inputs
		
		handler = new Handler();
		// ^ Needs to know what handler is before game is created
		
		new Window(WIDTH, HEIGHT, "Geam", this);
		// ^ Creates a new canvas with name "Geam"
		
		r = new Random();
		
		
		handler.addObject(new Player(100, 100, ID.Player, handler));
		handler.addObject(new Player2(100+64, 100, ID.Player2, handler));
		shield = true;
		handler.addObject(new Shield(r.nextInt(WIDTH-50), r.nextInt(HEIGHT-50), ID.Shield, handler));
		handler.addObject(new RangeEn(WIDTH-300, HEIGHT-150, ID.RangeEn, handler));
		for (int o = 0 ; o < 15 ; o++) {
			handler.addObject(new BasicEnemy(r.nextInt(Geam.WIDTH-50), r.nextInt(Geam.HEIGHT-50), ID.BasicEnemy, handler));
		}
		// ^ Adds all of our objects into the game
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		// ^ Sets the code to run
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
		System.exit(1);
		// ^ Tells the code to stop
	}
	
	public void run() {
		
		this.requestFocus();
		// ^ Makes it so the window that opens is already focused
		this.isMaximumSizeSet();
		// ^ Makes the window full screen
		long lastTime= System.nanoTime();
		// ^ Gets current time
		double amountOfTicks = 60.0;
		// ^ Sets the # of seconds to 60
		double ns = 1000000000 / amountOfTicks;
		// ^ Defines how many nano-seconds are in a second
		double delta = 0;
		long timer = System.currentTimeMillis();
		long timer2 = System.currentTimeMillis();
		// ^ Adding timers for things in the game
		int frames = 0;
		// ^ An int to count up frames
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta --;
			}
			if(running)
				render();
			frames++;
			// ^ Adds # of frames per sec
			
			if(System.currentTimeMillis() - timer2 > 1000){
				timer2 += 1000;
				System.out.println("FPS:"+frames);
				frames = 0;
				//handler.addObject(new BasicEnemy(r.nextInt(WIDTH-50), r.nextInt(HEIGHT-50), ID.BasicEnemy, handler));
				
				if (HUD.HEALTH>0&&start==true&&paused==false){
					handler.addObject(new BasicEnemy(r.nextInt(WIDTH-50), r.nextInt(HEIGHT-50), ID.BasicEnemy, handler));
					score += 1;
				}
			}
			// ^ Defines everything that happens every 1 sec, including reseting the FPS counter and increasing the score & # of balls
			
			if(System.currentTimeMillis() - timer > 100){
				timer += 100;
				if ((KeyInput.rightKey == true && Player.walkAn < 5) && paused == false && HUD.HEALTH>0&&start==true) Player.walkAn += 1;
				else Player.walkAn = 0;
			}
			// ^ Defines the amount of time between each animation frame of player1
			
			if(System.currentTimeMillis()- PowerT > 10000){
				PowerT += 10000;
				if (shield == false && Shield.pickedUp == false){
					shield = true;
					handler.addObject(new Shield(r.nextInt(WIDTH-50), r.nextInt(HEIGHT-50), ID.Shield, handler));
				}
				else if (shield == true && Shield.pickedUp == true){
					Shield.pickedUp=false;
					shield = false;
					for (int i = 0; i < Handler.object.size(); i++) {
						GeamObject tempObject = Handler.object.get(i);
						if (tempObject.id == ID.Shield){
							Handler.object.remove(tempObject);
						}
					}
				}
			}
			// ^ Defines the time between each shield pickup and amount of time that you have the shield
			
		}
		stop();
	}
	
	private void tick() {
		if (start == true && paused == false && HUD.HEALTH != 0){
			handler.tick();
			HUD.tick();
		}
		// ^ This runs as fast as possible, runs the game
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		// ^ Defines the render tool for the canvas
		
		g.setColor(Color.GRAY);
		g.fillRect(0,  0, WIDTH, HEIGHT);
		// ^ Makes the background
		
		
		
		if (start == false){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
			g.drawString("Press space to start", 600, HEIGHT/2);
		}
		// ^ Makes the start screen
		
		if (start == true) {
			handler.render(g);
			
			HUD.render(g);
			
		}
		// ^ Renders any other item in the game
		
		if (paused == true){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
			g.drawString("Paused", 600, HEIGHT/2);
		}
		// ^ Makes the pause screen
		
		if (HUD.HEALTH == 0){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Impact", Font.BOLD, 100));
			g.drawString("GAME OVER", 400, 400);
			g.setFont(new Font("Comic Sans MS",Font.BOLD , 30));
			g.drawString("Space to Restart", 540, 500);
		}
		// ^ Makes the game over screen
		
		g.dispose();
		bs.show();
	}	
	
	public static int clamp(int var, int min, int max){
		if (var>=max){
			return var = max;
		}
		else if (var<=min){
			return var = min;
		}
		else {
			return var;
		}
		// ^ A simple method to keep a coordinate in given bounds
	}
	
	public static void main(String[] args) {
		score = 0;
		new Geam();
		// ^ The start of the program, runs when start is pressed, and creates a new instance of Geam
	}

	
}
