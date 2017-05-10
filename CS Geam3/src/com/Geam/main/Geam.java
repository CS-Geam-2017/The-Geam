package com.Geam.main;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
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
	public static int[] checkedLi = new int[100000];
	public static int[][] followSet = new int[100000][2];
	public static int checkCount = 0;
	public static int golX = 0;
	public static int golY = 0;
	static int counting = 0;
	static int[][] openSet = new int[100000][8];
	public static boolean done = false;
	public static int lowestF= 1000000;
	
	private Thread thread;
	private boolean running = false;
	
	
	private Handler handler;
	public static int Pause = 0;
	
	public Geam() {
		this.addKeyListener(new KeyInput(handler));
		// ^ Tells computer to listen for key inputs
		
		handler = new Handler();
		// ^ Needs to know what handler is before game is created
		handler.addObject(new Player(300, 130, ID.Player, handler));
		handler.addObject(new Wall(380, 0, ID.Wall, handler));
		handler.addObject(new Wall(380, 412, ID.Wall, handler));
		handler.addObject(new Tracker(WIDTH/2, HEIGHT/2, ID.Tracker, handler));
		new Window(WIDTH, HEIGHT, "Geam", this);
		
		
		//for(int i = 0; i < 1; i++){

			//handler.addObject(new Player(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Player));
			//handler.addObject(new Player((WIDTH),(HEIGHT), ID.Player));
		//}
		
		
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
	
	public static boolean check(int x, int y, int end){
		boolean intersect = false;
		for (int i = 0; i <3; i++){
			GeamObject tempObject = Handler.object.get(i);
			if (tempObject.getID()==ID.Wall && end ==0){
				if (new Rectangle(x,y,10,10).intersects(tempObject.getBounds())){
					//System.out.println("working");
					intersect = true;
				}
			}
			if (tempObject.getID() == ID.Player && end == 3){
				if (new Rectangle(x,y,10,10).intersects(tempObject.getBounds())){
					intersect = true;
					//System.out.println("working1");
				}
			}
		}
		if ((x<= 0 || x >= WIDTH || y <= 0 || y>= HEIGHT) && end == 0){
			intersect = true;
			//System.out.println("working2");
		}
		if (end == 0){
			for(int i = 0; i<=counting; i++){
				if (openSet[i][0]==x && openSet[i][1]==y){
					intersect = true;
					//System.out.println("working3");
				}
			}
		}
		if (end == 2){
			//for(int i = 0; i<Handler.object.size(); i++){
				
			//}
		}
		
		//System.out.println(checkedLi[checkCount]+","+checkedLi[checkCount]);
		return !(intersect);
		
	}
	public static boolean CID(int x,int y){
		//System.out.println("working3");
		boolean YAY = false;
			GeamObject tempObject = Handler.object.get(0);
			if (tempObject.id==ID.Player){
				if (new Rectangle(x,y,1,1).intersects(tempObject.getBounds())){
					YAY = true;
				}
				else{
					YAY = false;
				}
			}
		//System.out.println(YAY);
		return YAY;
	}
	
	public void run() {
		golX = Tracker.goX;
		golY = Tracker.goY;
		int curX = 0;
		int curY = 0;
		int parX = 0;
		int parY = 0;
		int countingPar = 0;
		int g = 0;
		int h = (int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2)));
		for (int i = 0; i<Handler.object.size();i++){
			GeamObject tempObject = Handler.object.get(i);
			if (tempObject.getID()==ID.Tracker){
				curX = tempObject.getX();
				curY = tempObject.getY();
			}
			if(tempObject.getID()==ID.Player){
				golX = tempObject.getX();
				golY = tempObject.getY();
			}
		}
		//System.out.println(golX);
		//System.out.println(golY);
		parX = curX;
		parY = curY;
		h = (int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2)));
		openSet[0][0]=curX;
		openSet[0][1]=curY;
		openSet[0][2]=countingPar;
		openSet[0][3]= g;
		openSet[0][4]= h;
		openSet[0][5]= g+h;
		openSet[0][6]= 1;
		
		int direction = 0;
		this.requestFocus();
		this.isMaximumSizeSet();
		long lastTime= System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		long timer2 = System.currentTimeMillis();
		long pathFindT = System.currentTimeMillis();
		int frames = 0;
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
				if (KeyInput.rightKey == true && Player.walkAn < 5) Player.walkAn += 1;
				else Player.walkAn = 0;
			}
			if(System.currentTimeMillis() - pathFindT > 100){
				pathFindT += 100;
				while (done == false){
					direction = 0;
					while (direction <= 7){
						counting += 1;
						if (direction == 0 && check(parX-10,parY+10, 0)){
							System.out.println("working");
							curX = parX-10;
							curY = parY+10;
							for (int i = 0; i < counting; i++){
								if (openSet[i][0] == curX && openSet[i][1] == curY){
									if(openSet[openSet[countingPar][2]][3]+140 < 140+openSet[countingPar][3]){
										countingPar = openSet[countingPar][2];
									}
								}
							}
							g = 140+openSet[countingPar][3];
							openSet[counting][0] = parX-10;
							openSet[counting][1] = parY+10;
							openSet[counting][2]=countingPar;
							openSet[counting][3]= g;
							openSet[counting][4]= 10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2)));
							openSet[counting][5]= g+(10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2))));
							//openSet[counting][6]= 1;
						}
						else if(!check(parX-10,parY+10, 0)&& direction == 0){
							counting--;
						}
						if (direction == 1 && check(parX-10,parY, 0)){
							System.out.println("working1");
							curX = parX-10;
							curY = parY;
							for (int i = 0; i < counting; i++){
								if (openSet[i][0] == curX && openSet[i][1] == curY){
									if(openSet[openSet[countingPar][2]][3]+100 < 100+openSet[countingPar][3]){
										countingPar = openSet[countingPar][2];
									}
								}
							}
							g = 100+openSet[countingPar][3];
							openSet[counting][0] = parX-10;
							openSet[counting][1] = parY;
							openSet[counting][2]=countingPar;
							openSet[counting][3]= g;
							openSet[counting][4]= 10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2)));
							openSet[counting][5]= g+(10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2))));
							//openSet[counting][6]= 1;
						}
						else if(!check(parX-10,parY, 0)&& direction == 1){
							counting--;
						}
						if (direction == 2 && check(parX-10,parY-10, 0)){
							System.out.println("working2");
							curX = parX-10;
							curY = parY-10;
							for (int i = 0; i < counting; i++){
								if (openSet[i][0] == curX && openSet[i][1] == curY){
									if(openSet[openSet[countingPar][2]][3]+140 < 140+openSet[countingPar][3]){
										countingPar = openSet[countingPar][2];
									}
								}
							}
							g = 140+openSet[countingPar][3];
							openSet[counting][0] = parX-10;
							openSet[counting][1] = parY-10;
							openSet[counting][2]=countingPar;
							openSet[counting][3]= g;
							openSet[counting][4]= 10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2)));
							openSet[counting][5]= g+(10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2))));
							//openSet[counting][6]= 1;
						}
						else if(!check(parX-10,parY-10, 0)&& direction == 2){
							counting--;
						}
						if (direction == 3 && check(parX+10,parY-10, 0)){
							System.out.println("working3");
							curX = parX+10;
							curY = parY-10;
							for (int i = 0; i < counting; i++){
								if (openSet[i][0] == curX && openSet[i][1] == curY){
									if(openSet[openSet[countingPar][2]][3]+140 < 140+openSet[countingPar][3]){
										countingPar = openSet[countingPar][2];
									}
								}
							}
							g = 140+openSet[countingPar][3];
							openSet[counting][0] = parX+10;
							openSet[counting][1] = parY-10;
							openSet[counting][2]= countingPar;
							openSet[counting][3]= g;
							openSet[counting][4]= 10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2)));
							openSet[counting][5]= g+(10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2))));
							//openSet[counting][6]= 1;
						}
						else if(!check(parX+10,parY-10, 0)&& direction == 3){
							counting--;
						}
						if (direction == 4 && check(parX+10,parY, 0)){
							System.out.println("working4");
							curX = parX+10;
							curY = parY;
							for (int i = 0; i < counting; i++){
								if (openSet[i][0] == curX && openSet[i][1] == curY){
									if(openSet[openSet[countingPar][2]][3]+100 < 100+openSet[countingPar][3]){
										countingPar = openSet[countingPar][2];
									}
								}
							}
							g = 100+openSet[countingPar][3];
							openSet[counting][0] = parX+10;
							openSet[counting][1] = parY;
							openSet[counting][2]=countingPar;
							openSet[counting][3]= g;
							openSet[counting][4]= 10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2)));
							openSet[counting][5]= g+(10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2))));
							//openSet[counting][6]= 1;
						}
						else if(!check(parX+10,parY, 0)&& direction == 4){
							counting--;
						}
						if (direction == 5 && check(parX+10,parY+10, 0)){
							System.out.println("working5");
							curX = parX+10;
							curY = parY+10;
							for (int i = 0; i < counting; i++){
								if (openSet[i][0] == curX && openSet[i][1] == curY){
									if(openSet[openSet[countingPar][2]][3]+140 < 140+openSet[countingPar][3]){
										countingPar = openSet[countingPar][2];
									}
								}
							}
							g = 140+openSet[countingPar][3];
							openSet[counting][0] = parX+10;
							openSet[counting][1] = parY+10;
							openSet[counting][2]=countingPar;
							openSet[counting][3]= g;
							openSet[counting][4]= 10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2)));
							openSet[counting][5]= g+(10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2))));
							//openSet[counting][6]= 1;
						}
						else if(!check(parX+10,parY+10, 0)&& direction == 5){
							counting--;
						}
						if (direction == 6 && check(parX,parY+10, 0)){
							System.out.println("working6");
							curX = parX;
							curY = parY+10;
							for (int i = 0; i < counting; i++){
								if (openSet[i][0] == curX && openSet[i][1] == curY){
									if(openSet[openSet[countingPar][2]][3]+100 < 100+openSet[countingPar][3]){
										countingPar = openSet[countingPar][2];
									}
								}
							}
							g = 100+openSet[countingPar][3];
							openSet[counting][0] = parX;
							openSet[counting][1] = parY+10;
							openSet[counting][2]=countingPar;
							openSet[counting][3]= g;
							openSet[counting][4]= 10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2)));
							openSet[counting][5]= g+(10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2))));
							//openSet[counting][6]= 1;
						}
						else if(!check(parX,parY+10, 0)&& direction == 6){
							counting--;
						}
						if (direction == 7 && check(parX,parY-10, 0)){
							System.out.println("working7");
							curX = parX;
							curY = parY-10;
							for (int i = 0; i < counting; i++){
								if (openSet[i][0] == curX && openSet[i][1] == curY){
									if(openSet[openSet[countingPar][2]][3]+100 < 100+openSet[countingPar][3]){
										openSet[countingPar][6] = 1;
										countingPar = openSet[countingPar][2];
									}
								}
							}
							g = 100+openSet[countingPar][3];
							openSet[counting][0] = parX;
							openSet[counting][1] = parY-10;
							openSet[counting][2]=countingPar;
							openSet[counting][3]= g;
							openSet[counting][4]= 10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2)));
							openSet[counting][5]= g+(10*(int) Math.floor(Math.sqrt(Math.pow(curX-golX,2)+Math.pow(curY-golY,2))));
							//openSet[counting][6]= 1;
						}
						else if(!check(parX,parY-10, 0)&& direction == 7){
							counting--;
						}
						direction++;
					}
					if (CID(openSet[counting][0],openSet[counting][1])){
						done = true;
					}
					if (done != true){
						int k = 0;
						lowestF = counting;
						for (k = 0; k<counting-8; k++){
							//System.out.println(openSet[i][5]);
							if (openSet[k][5]<openSet[lowestF][5] && openSet[k][6] == 0 && !check(openSet[k][0],openSet[k][1],0)){
								lowestF=k;
							}
						}
						parX = openSet[lowestF][0];
						parY = openSet[lowestF][1];
						//System.out.println(lowestF);
						//System.out.println(parX+","+parY );
						handler.addObject(new PlaceHolder(parX,parY,ID.PlaceHolder,handler,0));
						openSet[lowestF][6] = 1;
						countingPar = lowestF;
						}
					render();
				}
				int count = counting;
				System.out.println(openSet[count][0] != WIDTH/2 && openSet[count][1] != HEIGHT/2);
				while (openSet[count][0] != WIDTH/2 && openSet[count][1] != HEIGHT/2){
					followSet[count][0] = openSet[countingPar][0];
					followSet[count][1] = openSet[countingPar][1];
					curX = followSet[count][0];
					curY = followSet[count][1];
					count++;
					countingPar = openSet[countingPar][2];
					//System.out.println("working");
					handler.addObject(new PlaceHolder(openSet[countingPar][0],openSet[countingPar][1],ID.PlaceHolder,handler,1));
					//System.out.println(openSet[countingPar][1]);
					//System.out.println(followSet[count]);
					//System.out.println(openSet[0][1]);
					render();
				}
				//System.out.println("");
				for (int i = 0; i<count; i++){
					System.out.println("working1");
					//System.out.println(followSet[i]+","+followSet[i+1]);
					handler.addObject(new PlaceHolder(parX,parY,ID.PlaceHolder,handler,1));
					render();
				}
				
			}
			//System.out.println("working2");
		}
		stop();
	}
	
	private void tick() {
		if (start == true && paused == false){
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
		if (start == false){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
			g.drawString("Press space to start", 600, HEIGHT/2);
		}
		if (start == true) {
			handler.render(g);
		}
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
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Geam();
	}
	
}
