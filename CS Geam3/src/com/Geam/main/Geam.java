package com.Geam.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

	public static final int WIDTH = 620, HEIGHT = 645;
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
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS:"+frames);
				frames = 0;
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
		
		g.setColor(Color.white);
		g.fillRect(0,  0, 600, 600);
		for (int i = 0; i<=600; i+=10) {
			g.setColor(Color.black);
			g.drawLine(0, i, 600, i);
		}
		for (int i = 0; i<=600; i+=10) {
			g.setColor(Color.black);
			g.drawLine(i, 0, i, 600);
		}
		g.setColor(Color.blue);
		for (int i = 1; i<=550;i+=10) {
			g.fillRect(i, i, 9, 9);
		}
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Geam();
	}
	
	

	
}
