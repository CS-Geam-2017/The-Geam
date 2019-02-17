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

	public static int WIDTH = 1000;
	//Reg - WIDTH = 640, HEIGHT = WIDTH / 12*9
	//Full Screen - WIDTH = 1370, HEIGHT = WIDTH / 12*12

	public static int HEIGHT = 705;
	
	public static boolean start = false;
	public static boolean paused = false;
	
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
		new Window(WIDTH+15, HEIGHT+38, "Geam", this);
		
		
		r = new Random();
		
		int balls = 30;
		int rad = 75;
		double elastic = .9;
		double friction = 1;
		double gravity = 0.01;
		//handler.addObject(new BasicEnemy(200, 100, ID.BasicEnemy, handler,rad, elastic,friction));
		//handler.addObject(new BasicEnemy(200, 150, ID.BasicEnemy, handler,rad, elastic,friction));
		
		
		for (int i = 0 ; i < balls ; i++) {
			boolean flag = true;
			while(flag) {
				int x = r.nextInt(WIDTH-(rad+10))-(0);
				int y = r.nextInt((HEIGHT)-(rad+10))+(HEIGHT*0);
				if(Handler.object.size()>0) {
					boolean flag1 = false;
					for(int j = 0; j < Handler.object.size(); j++){
						GeamObject tempObject = Handler.object.get(j);
						if(tempObject.getID() == ID.BasicEnemy){
							double xDis = (x+(rad/2))-(tempObject.getBounds().x+(rad/2));
							double yDis = (y+(rad/2))-(tempObject.getBounds().y+(rad/2));
							if(((xDis*xDis)+(yDis*yDis))<=(rad*rad) || (x==tempObject.x && y==tempObject.y)) {
								flag1=true;;
							}
						}
					}
					if(!flag1) {
						handler.addObject(new BasicEnemy(x, y, ID.BasicEnemy, handler,rad, elastic, friction, gravity));
						flag=false;
					}
				}
				else {
					handler.addObject(new BasicEnemy(x, y, ID.BasicEnemy, handler,rad, elastic, friction, gravity));
					flag=false;
				}
			}
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
		System.exit(1);
	}
	
	public void run() {
		this.requestFocus();
		this.isMaximumSizeSet();
		long lastTime= System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		long timer2 = System.currentTimeMillis();
		int frames = 0;
		while(running){
			
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			//while(delta >= 1) {
				tick();
				//delta --;
			//}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer2 > 1000){
				timer2 += 1000;
				System.out.println("FPS:"+frames);
				frames = 0;
				
			}
			try {
				thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		stop();
	}
	
	private void tick() {
		if (true && paused == false){
			handler.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.GRAY);
		g.fillRect(0,  0, WIDTH, HEIGHT);
		if (paused == true){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
			g.drawString("Paused", 600, HEIGHT/2);
		}
		//if (start == false){
		//	g.setColor(Color.WHITE);
		//	g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		//	g.drawString("Press space to start", 600, HEIGHT/2);
		//}
		if (true) {
			handler.render(g);
		}
		g.dispose();
		bs.show();
	}	
	
	public static int clamp(int var, int min, int max){
		if (var>=max-10){
			return var = max;
		}
		else if (var<=min+10){
			return var = min;
		}
		else {
			return var;
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Geam();
	}

	
}
