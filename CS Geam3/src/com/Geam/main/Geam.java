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
		int[] q = {0};
		int[] a = {3};
		int[] b = {1};
		int[] c = {2};
		int[] d = {4};
		
		myText Q = new myText(500,100,ID.Q,"",q);
		myText A = new myText(500,200,ID.A,"",a);
		myText B = new myText(500,200,ID.B,"",b);
		myText C = new myText(500,200,ID.C,"",c);
		myText D = new myText(500,200,ID.D,"",d);
		
		handler.addObject(Q);
		handler.addObject(A);
		handler.addObject(B);
		handler.addObject(C);
		handler.addObject(D);
		
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
		GeamObject tempObject;
		for(int i = 0; i<handler.object.size(); i++) {
			tempObject = handler.object.get(i);
			if(KeyInput.Question == 1) {
				if(tempObject.id==ID.Q) {
					tempObject.setText("Hamlet has come back to and see your mother married to your uncle how do you react?");
				}
			}
			
		}
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		frames++;
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Geam();
	}
	
	

	
}
