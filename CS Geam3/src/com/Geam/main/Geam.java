package com.Geam.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import com.sun.javafx.geom.Point2D;
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
	private ArrayList<Point2D> Walls = new ArrayList<Point2D>();
	private Point2D start = new Point2D(101,51);
	private Point2D end = new Point2D(61,101);
	private Point2D cur = new Point2D(101,51);
	private ArrayList<Node> openSet = new ArrayList<Node>();
	private ArrayList<Node> closedSet = new ArrayList<Node>();
	
	public Geam() {
		this.addKeyListener(new KeyInput(handler));
		// ^ Tells computer to listen for key inputs
		
		handler = new Handler();
		// ^ Needs to know what handler is before game is created
		new Window(WIDTH, HEIGHT, "Geam", this);
		

		r = new Random();
	}
	
	public ArrayList<Point2D> getSurPoints(Point2D a){
		ArrayList<Point2D> list = new ArrayList<Point2D>();
		boolean added0 = false;
		boolean added1 = false;
		boolean added2 = false;
		boolean added3 = false;
		boolean added4 = false;
		boolean added5 = false;
		boolean added6 = false;
		boolean added7 = false;
		boolean added8 = false;
		for (int i = 0; i<=openSet.size(); i++) {
			if (openSet.get(i).xy != a && added0 == false) {
				if (Walls.contains(new Point2D(a.x-10,a.y-10))!= true) list.add(new Point2D(a.x-10,a.y-10));
			}
			if (openSet.get(i).xy != a && added0 == false) {
				if (Walls.contains(new Point2D(a.x,a.y-10))!= true) list.add(new Point2D(a.x,a.y-10));
			}
			if (Walls.contains(new Point2D(a.x+10,a.y-10))!= true) list.add(new Point2D(a.x+10,a.y-10));
			if (Walls.contains(new Point2D(a.x+10,a.y))!= true) list.add(new Point2D(a.x+10,a.y));
			if (Walls.contains(new Point2D(a.x+10,a.y+10))!= true) list.add(new Point2D(a.x+10,a.y+10));
			if (Walls.contains(new Point2D(a.x,a.y+10))!= true) list.add(new Point2D(a.x,a.y+10));
			if (Walls.contains(new Point2D(a.x-10,a.y+10))!= true) list.add(new Point2D(a.x-10,a.y+10));
			if (Walls.contains(new Point2D(a.x-10,a.y))!= true) list.add(new Point2D(a.x-10,a.y));
		}
		return list;
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
		Walls.removeAll(Walls);
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
			g.fillRect(i+10, i, 9, 9);
			Point2D a = new Point2D(i,i);
			Point2D b = new Point2D(i+10,i);
			Walls.add(a);
			Walls.add(b);
		}
		g.setColor(Color.green);
		g.fillRect((int)start.x, (int)start.y, 9, 9);
		g.setColor(Color.red);
		
		g.fillRect((int)end.x, (int)end.y, 9, 9);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Geam();
	}
	

	
}
