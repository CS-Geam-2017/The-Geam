package com.Geam.main;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class KeyInput extends KeyAdapter {
	public static boolean leftKey = false;
	public static boolean upKey = false;
	public static boolean rightKey = false;
	public static boolean downKey = false;
	public static boolean RKRel = true;
	public static boolean space = false;
	boolean leftKey2 = false;
	boolean upKey2 = false;
	boolean rightKey2 = false;
	boolean downKey2 = false;
	protected int speedX;
	protected int speedY;
	protected int changeX = 5;
	protected int changeY = 5;
	boolean released = true;
	private Handler handler;
	public KeyInput(Handler handler){
		
	}
	Random r;
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		r = new Random();
		
		handler = new Handler();
		
		for(int i = 0; i < Handler.object.size(); i++){
			
			
			GeamObject tempObject = Handler.object.get(i);
			
			if(tempObject.getID() == ID.Player){
				//key events for player 1;
				if(key == KeyEvent.VK_W && upKey==false) {
					tempObject.setSpeedY(tempObject.getSpeedY()-changeY);
					upKey=true;
				}
				if(key == KeyEvent.VK_S && downKey==false) {
					tempObject.setSpeedY(tempObject.getSpeedY()+changeY);
					downKey=true;
				}
				if(key == KeyEvent.VK_A && leftKey==false) {
					tempObject.setSpeedX(tempObject.getSpeedX()-changeX);
					leftKey=true;
				}
				if(key == KeyEvent.VK_D && rightKey==false) {
					tempObject.setSpeedX(tempObject.getSpeedX()+changeX);
					rightKey=true;
				}
			}
			
			if(key == KeyEvent.VK_ESCAPE) System.exit(1);
			if(key == KeyEvent.VK_SPACE && Geam.start == false && released == true) Geam.start= true;
			else{
				space = true;
			}
			if(key == KeyEvent.VK_SPACE && Geam.paused == true && released == true){
					for (int p = 0; Handler.object.size() > 0; p = Handler.object.size()-1) {
						tempObject = Handler.object.get(p);
						handler.removeObject(tempObject);
				}
				handler.addObject(new Player(100, 100, ID.Player, handler));
				handler.addObject(new Tracker(Geam.WIDTH/2, Geam.HEIGHT/2, ID.Tracker, handler));
				Geam.start=false;
				
			}
				

			if(key == KeyEvent.VK_P && Geam.paused == false && released == true) Geam.paused = true;
			else if(key == KeyEvent.VK_P && Geam.paused == true & released == true) Geam.paused = false;
			released = false;
		}
	}

	

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < Handler.object.size(); i++){
			GeamObject tempObject = Handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				//key events for player
				if(key == KeyEvent.VK_W) {
					tempObject.setSpeedY(tempObject.getSpeedY()+changeY);
					upKey=false;
				}
				if(key == KeyEvent.VK_S) {
					tempObject.setSpeedY(tempObject.getSpeedY()-changeY);
					downKey=false;
				}
				if(key == KeyEvent.VK_A) {
					tempObject.setSpeedX(tempObject.getSpeedX()+changeX);
					leftKey=false;
				}
				if(key == KeyEvent.VK_D) {
					tempObject.setSpeedX(tempObject.getSpeedX()-changeX);
					rightKey=false;
				}
			}
			if(key == KeyEvent.VK_SPACE) {
				space=false;
			}
			released = true;
		}
	}
}
