package com.Geam.main;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class KeyInput extends KeyAdapter {
	public static boolean leftKey = false;
	public static boolean upKey = false;
	public static boolean rightKey = false;
	public static boolean downKey = false;
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
			if(tempObject.getID() == ID.Player2) {
				//key events for player 2
				if(key == KeyEvent.VK_UP && upKey2==false) {
					tempObject.setSpeedY(tempObject.getSpeedY()-changeY);
					upKey2=true;
				}
				if(key == KeyEvent.VK_DOWN && downKey2==false) {
					tempObject.setSpeedY(tempObject.getSpeedY()+changeY);
					downKey2=true;
				}
				if(key == KeyEvent.VK_LEFT && leftKey2==false) {
					tempObject.setSpeedX(tempObject.getSpeedX()-changeX);
					leftKey2=true;
				}
				if(key == KeyEvent.VK_RIGHT && rightKey2==false) {
					tempObject.setSpeedX(tempObject.getSpeedX()+changeX);
					rightKey2=true;
				}
			}
			
			if(key == KeyEvent.VK_ESCAPE) System.exit(1);
			if(key == KeyEvent.VK_SPACE && Geam.start == false && released == true) Geam.start= true;
			if(key == KeyEvent.VK_SPACE && HUD.HEALTH == 0 && released == true){
					for (int p = 0; Handler.object.size() > 0; p = Handler.object.size()-1) {
						tempObject = Handler.object.get(p);
						if(tempObject.getID() == ID.Player) handler.removeObject(tempObject);
						if(tempObject.getID() == ID.Player2) handler.removeObject(tempObject);
						if(tempObject.getID() == ID.BasicEnemy) handler.removeObject(tempObject);
						if(tempObject.getID() == ID.Tracker) handler.removeObject(tempObject);
				}
				handler.addObject(new Player(100, 100, ID.Player, handler));
				handler.addObject(new Player2(100+64, 100, ID.Player2, handler));
				handler.addObject(new Tracker(Geam.WIDTH/2, Geam.HEIGHT/2, ID.Tracker, handler));
				for (int o = 0 ; o < 15 ; o++) {
					handler.addObject(new BasicEnemy(r.nextInt(Geam.WIDTH-50), r.nextInt(Geam.HEIGHT-50), ID.BasicEnemy, handler));
				}
				Geam.score=0;
				Geam.start=false;
				HUD.HEALTH = 100;
				
			}
				

			if(key == KeyEvent.VK_P && Geam.paused == false && released == true && HUD.HEALTH != 0) Geam.paused = true;
			else if(key == KeyEvent.VK_P && Geam.paused == true & released == true) Geam.paused = false;
			released = false;
		}
	}

	

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < Handler.object.size(); i++){
			GeamObject tempObject = Handler.object.get(i);
			if(tempObject.getID() == ID.Player2) {
				//key events for player 2
				if(key == KeyEvent.VK_LEFT) {
					tempObject.setSpeedX(tempObject.getSpeedX()+changeX);
					leftKey2=false;
				}
				if(key == KeyEvent.VK_UP) {
					tempObject.setSpeedY(tempObject.getSpeedY()+changeY);
					upKey2=false;
				}
				if(key == KeyEvent.VK_RIGHT) {
					tempObject.setSpeedX(tempObject.getSpeedX()-changeX);
					rightKey2=false;
				}
				if(key == KeyEvent.VK_DOWN) {
					tempObject.setSpeedY(tempObject.getSpeedY()-changeY);
					downKey2=false;
				}
			}
			
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
			released = true;
		}
	}
}
