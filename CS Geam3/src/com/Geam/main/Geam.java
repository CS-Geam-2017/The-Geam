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

	public static final int WIDTH = 1370, HEIGHT = 770;
	//Reg - WIDTH = 640, HEIGHT = WIDTH / 12*9
	//Full Screen - WIDTH = 1370, HEIGHT = WIDTH / 12*12
	
	private Thread thread;
	private boolean running = false;
	
	
	private Random r;
	private Handler handler;
	private int frames;
	public static boolean end = false;
	public static boolean title = true;
	
	
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
		myText act5 = new myText(20,200,ID.act5,"");
		
		handler.addObject(Q);
		handler.addObject(A);
		handler.addObject(B);
		handler.addObject(C);
		handler.addObject(D);
		handler.addObject(R);
		handler.addObject(act5);
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
					tempObject.setText("A: Kill Claudius on the Spot");
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
			else if(KeyInput.Question == 3) {
				if(tempObject.id==ID.Q) {
					tempObject.setText("Hamlet has decided to somehow prove that Claudius killed his father. How should he do it?");
				}
				else if(tempObject.id==ID.A) {
					tempObject.setText("A: Devise a play");
				}
				else if(tempObject.id==ID.B) {
					tempObject.setText("B: Who Cares");
				}
				else if(tempObject.id==ID.C) {
					tempObject.setText("C: Hire Someone");
				}
				else if(tempObject.id==ID.D) {
					tempObject.setText("D: Naptime");
				}
				
			}
			else if(KeyInput.Question == 4) {
				if(tempObject.id==ID.Q) {
					tempObject.setText("The arrival of actors and players have arrived what should Hamlet do?");
				}
				else if(tempObject.id==ID.A) {
					tempObject.setText("A: Send Them Away");
				}
				else if(tempObject.id==ID.B) {
					tempObject.setText("B: Lock them up");
				}
				else if(tempObject.id==ID.C) {
					tempObject.setText("C: Stab them");
				}
				else if(tempObject.id==ID.D) {
					tempObject.setText("D: Introduce them to the Play");
				}
				
			}
			else if(KeyInput.Question == 5) {
				if(tempObject.id==ID.Q) {
					tempObject.setText("Hamlet has come into Gertrude's room and she starts to interrogate him and it seems Claudius is beside her. What should he do?");
				}
				else if(tempObject.id==ID.A) {
					tempObject.setText("A: Pretend To Be in Love");
				}
				else if(tempObject.id==ID.B) {
					tempObject.setText("B: THIS IS HIS CHANCE");
				}
				else if(tempObject.id==ID.C) {
					tempObject.setText("C: Kill them Both");
				}
				else if(tempObject.id==ID.D) {
					tempObject.setText("D: Jump");
				}
				
			}
			else if(KeyInput.Question == 6) {
				if(tempObject.id==ID.Q) {
					tempObject.setText("Hamlet is captured and is being forced to tell where Polonius’ body is hidden. What should he do?");
				}
				else if(tempObject.id==ID.A) {
					tempObject.setText("A: Avoid It");
				}
				else if(tempObject.id==ID.B) {
					tempObject.setText("B: Just Say");
				}
				else if(tempObject.id==ID.C) {
					tempObject.setText("C: Try and Fight");
				}
				else if(tempObject.id==ID.D) {
					tempObject.setText("D: Suicide");
				}
				
			}
			else if(KeyInput.Question == 7 && KeyInput.act5==1) {
				if(tempObject.id==ID.Q) {
					tempObject.setText("Laertes and Hamlet are to fight how should Hamlet procede?");
				}
				else if(tempObject.id==ID.A) {
					tempObject.setText("A: Play to two hits");
				}
				else if(tempObject.id==ID.B) {
					tempObject.setText("B: Play to one hit and drink");
				}
				else if(tempObject.id==ID.C) {
					tempObject.setText("C: Let Laertes get the hit");
				}
				else if(tempObject.id==ID.D) {
					tempObject.setText("D: Just go to England");
				}
				else if(tempObject.id==ID.act5) {
					tempObject.setText("To win, you must answer both parts of this question correctly");
				}
				
			}
			else if(KeyInput.Question == 7 && KeyInput.act5==2) {
				if(tempObject.id==ID.Q) {
					tempObject.setText("Laertes and Hamlet fight once again and Hamlet is stabbed with Laertes poisoned sword how should Hamlet react?");
				}
				else if(tempObject.id==ID.A) {
					tempObject.setText("A: Take the Sword and Poke Back");
				}
				else if(tempObject.id==ID.B) {
					tempObject.setText("B: Lay there and die");
				}
				else if(tempObject.id==ID.C) {
					tempObject.setText("C: Stab Claudius");
				}
				else if(tempObject.id==ID.D) {
					tempObject.setText("D: Stab Laertes");
				}
				
			}
			else if(KeyInput.Question == 8) {
				tempObject.setText("");
				if(tempObject.id==ID.A) {
					tempObject.setX(20);
					end = true;
					tempObject.setText("The deed is done and Claudius is killed in the end those who sought out revenge are no more, those who chose to hide from their crimes are but not, Hamlet gives the land to Fortinbras, Horatio goes and tells the story of his friend, Denmark is a bloodstain and this Story is done.");
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
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		frames++;
		if(title) {
			KeyInput.check = true;
			g.setColor(Color.black);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 90));
			g.drawString("Hamlet Game", 370, 100);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
			g.drawString("Code by: Jake Lockey", 470, 200);
			g.drawString("Questions By: Jackson Boster", 420, 250);
			g.drawString("To answer a question, type the letter you want", 320, 380);
			g.drawString("Once you have have correct answer, press space to proceed", 230, 430);
			g.drawString("Enter to Start", 530, 600);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
			g.drawString("You might need to click the window for it to work", 460, 680);
			
		}
		else {
			g.setColor(Color.black);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
			handler.render(g);
		}
		
		if(end&&KeyInput.Question==8) {
			g.setColor(Color.black);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
			g.drawString("Enter to Restart", 500, 500);
		}
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Geam();
	}
	
	

	
}
