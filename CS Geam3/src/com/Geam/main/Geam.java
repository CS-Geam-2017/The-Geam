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
	private static ArrayList<Point2D> Walls = new ArrayList<Point2D>();
	public static Node start = new Node(new Point2D(101,51),null, 0, getDist(new Point2D(101,51),new Point2D(121,201)), getDist(new Point2D(101,51),new Point2D(121,201))) ;
	public static Node end = new Node(new Point2D(241,51), null, 10000, 0, 10000);
	public static Node cur = start;
	private static ArrayList<Node> openSet = new ArrayList<Node>();
	private static ArrayList<Point2D> openSetXY = new ArrayList<Point2D>();
	private static ArrayList<Point2D> closedSet = new ArrayList<Point2D>();
	
	public Geam() {
		this.addKeyListener(new KeyInput(handler));
		// ^ Tells computer to listen for key inputs
		//System.out.println(openSet.size());
		openSet.add(start);
		//System.out.println(getDist(end.xy,start.xy));
		closedSet.add(new Point2D(200,200));
		getSurPoints(start);
		openSet.remove(0);
		closedSet.add(start.xy);
		handler = new Handler();
		// ^ Needs to know what handler is before game is created
		new Window(WIDTH, HEIGHT, "Geam", this);
		r = new Random();
	}
	public static double getDist(Point2D a,Point2D b) {
		return (Math.sqrt(Math.pow(a.x-b.x,2.0) + Math.pow((a.y-b.y), 2.0)))*2;
	}
	
	public static void getSurPoints(Node a){
		boolean added0 = false;
		boolean added1 = false;
		boolean added2 = false;
		boolean added3 = false;
		boolean added4 = false;
		boolean added5 = false;
		boolean added6 = false;
		boolean added7 = false;
		for(int i = 0; i<openSet.size(); i++) {
			openSetXY.add(openSet.get(i).xy);
		}
		for (int i = 0; i<openSet.size(); i++) {
			//for(int s = 0; s<closedSet.size(); s++) {
				//System.out.println(s);
				if (openSetXY.contains(new Point2D(a.xy.x-10,a.xy.y-10)) != true && added0 != true && closedSet.contains(new Point2D(a.xy.x-10,a.xy.y-10)) != true) {
					//System.out.println(added0);
					if (Walls.contains(new Point2D(a.xy.x-10,a.xy.y-10))!= true) {
						added0 = true;
						//System.out.println(openSet.get(i).xy);
						//System.out.println(new Point2D(a.xy.x-10,a.xy.y-10));
						//System.out.println("NOTWORKING0");
						openSet.add(new Node(new Point2D(a.xy.x-10,a.xy.y-10), a, a.getGcost()+14, getDist(new Point2D(a.xy.x-10,a.xy.y-10), end.xy), (a.getGcost()+14)+getDist(new Point2D(a.xy.x-10,a.xy.y-10), end.xy)));
					}
				}
				else {
					if(Walls.contains(new Point2D(a.xy.x-10,a.xy.y-10))!= true) {
						if(openSet.get(i).Fcost>(a.Gcost+14+getDist(new Point2D(a.xy.x-10,a.xy.y-10),end.xy))&& getDist(openSet.get(i).xy,a.xy) <=15) {
							openSet.get(i).changeParN(a);
						}
					}
				}
				
				
				if (openSetXY.contains(new Point2D(a.xy.x,a.xy.y-10)) != true && added1 != true && closedSet.contains(new Point2D(a.xy.x,a.xy.y-10)) != true) {
					if (Walls.contains(new Point2D(a.xy.x,a.xy.y-10))!= true) {
						added1 = true;
						//System.out.println("NOTWORKING1");
						openSet.add(new Node(new Point2D(a.xy.x,a.xy.y-10), a, a.getGcost()+10, getDist(new Point2D(a.xy.x,a.xy.y-10), end.xy), (a.getGcost()+10)+getDist(new Point2D(a.xy.x,a.xy.y-10), end.xy)));
					}
				}
				else {
					if(Walls.contains(new Point2D(a.xy.x,a.xy.y-10))!= true) {
						if(openSet.get(i).Fcost>(a.Gcost+10+getDist(new Point2D(a.xy.x,a.xy.y-10),end.xy))&& getDist(openSet.get(i).xy,a.xy) <=15) {
							openSet.get(i).changeParN(a);
						}
					}
				}
				
				
				
				if (openSetXY.contains(new Point2D(a.xy.x+10,a.xy.y-10)) != true && added2 != true && closedSet.contains(new Point2D(a.xy.x+10,a.xy.y-10)) != true) {
					if (Walls.contains(new Point2D(a.xy.x+10,a.xy.y-10))!= true) {
						added2 = true;
						//System.out.println("NOTWORKING2");
						openSet.add(new Node(new Point2D(a.xy.x+10,a.xy.y-10), a, a.getGcost()+14, getDist(new Point2D(a.xy.x+10,a.xy.y-10), end.xy), (a.getGcost()+14)+getDist(new Point2D(a.xy.x+10,a.xy.y-10), end.xy)));
					}
				}
				else {
					if(Walls.contains(new Point2D(a.xy.x+10,a.xy.y-10))!= true) {
						if(openSet.get(i).Fcost>(a.Gcost+14+getDist(new Point2D(a.xy.x+10,a.xy.y-10),end.xy))&& getDist(openSet.get(i).xy,a.xy) <=15) {
							openSet.get(i).changeParN(a);
						}
					}
				}
				
				
				if (openSetXY.contains(new Point2D(a.xy.x+10,a.xy.y)) != true && added3 != true && closedSet.contains(new Point2D(a.xy.x+10,a.xy.y)) != true) {
					if (Walls.contains(new Point2D(a.xy.x+10,a.xy.y))!= true) {
						added3 = true;
						//System.out.println("NOTWORKING3");
						openSet.add(new Node(new Point2D(a.xy.x+10,a.xy.y), a, a.getGcost()+10, getDist(new Point2D(a.xy.x+10,a.xy.y), end.xy), (a.getGcost()+10)+getDist(new Point2D(a.xy.x+10,a.xy.y), end.xy)));
					}
				}
				else {
					if(Walls.contains(new Point2D(a.xy.x+10,a.xy.y))!= true&& getDist(openSet.get(i).xy,a.xy) <=15) {
						if(openSet.get(i).Fcost>(a.Gcost+10+getDist(new Point2D(a.xy.x+10,a.xy.y),end.xy))) {
							openSet.get(i).changeParN(a);
						}
					}
				}
				
	
				if (openSetXY.contains(new Point2D(a.xy.x+10,a.xy.y+10)) != true && added4 != true && closedSet.contains(new Point2D(a.xy.x+10,a.xy.y+10)) != true) {
					if (Walls.contains(new Point2D(a.xy.x+10,a.xy.y+10))!= true) {
						added4 = true;
						//System.out.println("NOTWORKING4");
						openSet.add(new Node(new Point2D(a.xy.x+10,a.xy.y+10), a, a.getGcost()+14, getDist(new Point2D(a.xy.x+10,a.xy.y+10), end.xy), (a.getGcost()+14)+getDist(new Point2D(a.xy.x+10,a.xy.y+10), end.xy)));
					}
				}
				else {
					if(Walls.contains(new Point2D(a.xy.x+10,a.xy.y+10))!= true) {
						if(openSet.get(i).Fcost>(a.Gcost+14+getDist(new Point2D(a.xy.x+10,a.xy.y+10),end.xy))&& getDist(openSet.get(i).xy,a.xy) <=15) {
							openSet.get(i).changeParN(a);
						}
					}
				}
				
				
				if (openSetXY.contains(new Point2D(a.xy.x,a.xy.y+10)) != true && added5 != true && closedSet.contains(new Point2D(a.xy.x,a.xy.y+10)) != true) {
					if (Walls.contains(new Point2D(a.xy.x,a.xy.y+10))!= true) {
						added5 = true;
						//System.out.println("NOTWORKING5");
						openSet.add(new Node(new Point2D(a.xy.x,a.xy.y+10), a, a.getGcost()+10, getDist(new Point2D(a.xy.x,a.xy.y+10), end.xy), (a.getGcost()+10)+getDist(new Point2D(a.xy.x,a.xy.y+10), end.xy)));
					}
				}
				else {
					if(Walls.contains(new Point2D(a.xy.x,a.xy.y+10))!= true) {
						if(openSet.get(i).Fcost>(a.Gcost+10+getDist(new Point2D(a.xy.x,a.xy.y+10),end.xy))&& getDist(openSet.get(i).xy,a.xy) <=15) {
							openSet.get(i).changeParN(a);
						}
					}
				}
				
				
				if (openSetXY.contains(new Point2D(a.xy.x-10,a.xy.y+10)) != true && added6 != true && closedSet.contains(new Point2D(a.xy.x-10,a.xy.y+10)) != true) {
					if (Walls.contains(new Point2D(a.xy.x-10,a.xy.y+10))!= true) {
						added6 = true;
						//System.out.println("NOTWORKING6");
						openSet.add(new Node(new Point2D(a.xy.x-10,a.xy.y+10), a, a.getGcost()+14, getDist(new Point2D(a.xy.x-10,a.xy.y+10), end.xy), (a.getGcost()+14)+getDist(new Point2D(a.xy.x-10,a.xy.y+10), end.xy)));
					}
				}
				else {
					if(Walls.contains(new Point2D(a.xy.x-10,a.xy.y+10))!= true) {
						if(openSet.get(i).Fcost>(a.Gcost+14+getDist(new Point2D(a.xy.x-10,a.xy.y+10),end.xy))&& getDist(openSet.get(i).xy,a.xy) <=15) {
							openSet.get(i).changeParN(a);
						}
					}
				}
				
				
				if (openSetXY.contains(new Point2D(a.xy.x-10,a.xy.y)) != true && added7 != true && closedSet.contains(new Point2D(a.xy.x-10,a.xy.y)) != true) {
					if (Walls.contains(new Point2D(a.xy.x-10,a.xy.y))!= true) {
						added7 = true;
						//System.out.println("NOTWORKING7");
						openSet.add(new Node(new Point2D(a.xy.x-10,a.xy.y), a, a.getGcost()+10, getDist(new Point2D(a.xy.x-10,a.xy.y), end.xy), (a.getGcost()+10)+getDist(new Point2D(a.xy.x-10,a.xy.y), end.xy)));
					}
				}
				else {
					if(Walls.contains(new Point2D(a.xy.x-10,a.xy.y))!= true) {
						if(openSet.get(i).Fcost>(a.Gcost+10+getDist(new Point2D(a.xy.x-10,a.xy.y),end.xy))&& getDist(openSet.get(i).xy,a.xy) <=15) {
							openSet.get(i).changeParN(a);
						}
					}
				}
				//System.out.println(closedSet.size());
				//System.out.println(openSet.size());
			//}
		}
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
				//System.out.println("FPS:"+frames);
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
		for(int i = 1; i<600; i+=10){
			g.fillRect(i,1, 9, 9);
			g.fillRect(1, i, 9, 9);
			Walls.add(new Point2D(i,1));
			Walls.add(new Point2D(1,i));
		}
		for(int i = 1; i<300; i+=10) {
			g.fillRect(201, i, 9, 9);
			Walls.add(new Point2D(201,i));
		}
		for(int i = 1; i<400; i+=10) {
			g.fillRect(i, 321, 9, 9);
			Walls.add(new Point2D(i,321));
		}
		for(int i = 31; i<321; i+=10) {
			g.fillRect(251, i, 9, 9);
			Walls.add(new Point2D(251,i));
		}
		//for (int i = 1; i<=200;i+=10) {
			//g.fillRect(i, i, 9, 9);
			//g.fillRect(i+10, i, 9, 9);
			//Point2D a = new Point2D(i,i);
			//Point2D b = new Point2D(i+10,i);
			//Walls.add(a);
			//Walls.add(b);
		//}
		Node nextNode = new Node(null, null, 0, 0, 100000000);
		int nextNodePlace = 0;
		//System.out.println(openSet.size());
		//g.setColor(Color.magenta);
		//for(int i = 0; i<closedSet.size();i++) {
			//g.fillRect((int)closedSet.get(i).x, (int)closedSet.get(i).y, 9, 9);
		//}
		g.setColor(Color.cyan);
		for(int i = 0; i<openSet.size();i++) {
			//System.out.println(i);
			g.fillRect((int)openSet.get(i).xy.x, (int)openSet.get(i).xy.y, 9, 9);
			if (openSet.get(i).Fcost<nextNode.Fcost && closedSet.contains(openSet.get(i).xy)!=true) {
				nextNode = openSet.get(i);
				//System.out.println(openSet.get(i));
				nextNodePlace = i;
			}
		}
		
		closedSet.add(nextNode.xy);
		//System.out.println(nextNode.xy);
		//System.out.println(closedSet.size()-openSet.size());
		openSet.remove(nextNodePlace);
		g.setColor(Color.green);
		g.fillRect((int)start.xy.x, (int)start.xy.y, 9, 9);
		g.setColor(Color.red);
		g.fillRect((int)end.xy.x, (int)end.xy.y, 9, 9);
		//System.out.println(openSet.size());
		//System.out.println((nextNode.Hcost)+" "+nextNode.Fcost);
		if(!(closedSet.contains(end.xy))) {
			getSurPoints(nextNode);
		}
		else {
			//System.out.println("Working");
			Node a = nextNode;
			while (a != start) {
				//System.out.println("hi");
				a = a.ParN;
				//System.out.println(a.xy);
				g.setColor(Color.magenta);
				g.fillRect((int)a.xy.x,(int) a.xy.y, 9, 9);
				bs.show();
			}
			//System.out.println("Hello");
			g.dispose();
			bs.show();
			stop();
		}
		//System.out.println("working");
		g.setColor(Color.black);
		g.fillRect((int)nextNode.xy.x, (int)nextNode.xy.y, 9, 9);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Geam();
	}
	

	
}
