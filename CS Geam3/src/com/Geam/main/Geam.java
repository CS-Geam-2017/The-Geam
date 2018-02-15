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
		
		myText Q = new myText(20,50,ID.Q,"");
		myText A = new myText(500,200,ID.A,"");
		myText B = new myText(500,300,ID.B,"");
		myText C = new myText(500,400,ID.C,"");
		myText D = new myText(500,500,ID.D,"");
		myText R = new myText(20,600,ID.R,"");
		
		handler.addObject(Q);
		handler.addObject(A);
		handler.addObject(B);
		handler.addObject(C);
		handler.addObject(D);
		handler.addObject(R);
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
				else if(tempObject.id==ID.A) {
					tempObject.setText("A: Kill Claudius on the Spot.");
				}
				else if(tempObject.id==ID.B) {
					tempObject.setText("B: Say nothing");
				}
				else if(tempObject.id==ID.C) {
					tempObject.setText("C: Leave Denmark");
				}
				else if(tempObject.id==ID.D) {
					tempObject.setText("D: Continue Being a Prince");
				}
				
			}
			else if(KeyInput.Question == 2) {
				if(tempObject.id==ID.Q) {
					tempObject.setText("The Ghost of King Hamlet has come to Hamlet saying that he was murdered and his son needs to avenge his death. What should Hamlet do?");
				}
				else if(tempObject.id==ID.A) {
					tempObject.setText("A: Scream and Run Around Frantically");
				}
				else if(tempObject.id==ID.B) {
					tempObject.setText("B: Try to Ghostbuster it");
				}
				else if(tempObject.id==ID.C) {
					tempObject.setText("C: Agree to the Ghost’s Terms");
				}
				else if(tempObject.id==ID.D) {
					tempObject.setText("D: NOPE");
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
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
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
