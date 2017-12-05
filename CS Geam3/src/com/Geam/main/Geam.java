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
	public static String secret;
	public static String guessed;
	
	
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
				}
			}
		stop();
	}
	
	private void tick() {
		if (start == true){
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
		if (start == true) {
			handler.render(g);
			
			HUD.render(g);
			
		}
		// ^ Renders any other item in the game
		
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
		new Geam();
		// ^ The start of the program, runs when start is pressed, and creates a new instance of Geam
	}

	
}
