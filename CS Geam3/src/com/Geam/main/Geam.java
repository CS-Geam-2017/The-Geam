package com.Geam.main;

import java.awt.Canvas;
import java.awt.Color;
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

	public static final int WIDTH = 1370, HEIGHT = WIDTH / 12*9;
	//Reg - WIDTH = 640, HEIGHT = WIDTH / 12*9
	//Full Screen - WIDTH = 1370, HEIGHT = WIDTH / 12*12
	
	private Thread thread;
	private boolean running = false;
	
	
	private Random r;
	private Handler handler;
	private int frames;
	
	
	public Geam() {
		this.addKeyListener(new KeyInput(handler));
		// ^ Tells computer to listen for key inputs
		
		handler = new Handler();
		// ^ Needs to know what handler is before game is created
		new Window(WIDTH, HEIGHT, "Geam", this);
		
		
		r = new Random();
		
		//for(int i = 0; i < 1; i++){

			//handler.addObject(new Player(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Player));
			//handler.addObject(new Player((WIDTH),(HEIGHT), ID.Player));
		//}
		
		//handler.addObject(new Player(100, 100, ID.Player));
		//handler.addObject(new Player(100+64, 100, ID.Player2));
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime= System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		long timer2 = System.currentTimeMillis();
		//int frames = 0;
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
			
			if(System.currentTimeMillis() - timer2 > 1000){
				timer2 += 1000;
				System.out.println("FPS:"+frames);
				frames = 0;
			}
			
			if(System.currentTimeMillis() - timer > 100){
				timer += 100;
				
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.darkGray);
		g.fillRect(0,  0, WIDTH, HEIGHT);
		g.setColor(Color.magenta);
		g.drawLine(685, 513, 685, 513);
		for (double a = 0; a<=40; a+=.005) {
			g.setColor(Color.darkGray);
			g.fillRect(0,  0, WIDTH, HEIGHT);
			double t = 0;
			boolean reached =false;
			for (double b = 0; b<=2*3.14; b+=0.01) {
				Color mycolor;
				if (t<=2*3.14 && reached == false) {
					mycolor = new Color((int)t*39,66,244);
					t+=0.02;
				}
				else {
					reached = true;
					t-=0.02;
					mycolor = new Color((int)t*39,66,244);
				}
				g.setColor(mycolor);
				g.drawLine(685, 400, (int)(100*(Math.cos(a-(b))-Math.sin(a-b)))+685, (int)(100*(Math.sin(a-4*b)+Math.cos(a-b))+400));
			}
			frames++;
			bs.show();
			try {
				thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Geam();
	}
	
	

	
}
