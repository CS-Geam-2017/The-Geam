package com.Geam.main;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	protected int speed = 5;
	public KeyInput(Handler handler){
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < Handler.object.size(); i++){
			
			GeamObject tempObject = Handler.object.get(i);
			if(tempObject.getID() == ID.Player){
				//key events for player 1;
				if(key == KeyEvent.VK_W && upKey==false) {
					tempObject.setSpeedY(tempObject.getSpeedY()-speed);
					upKey=true;
				}
				if(key == KeyEvent.VK_S && downKey==false) {
					tempObject.setSpeedY(tempObject.getSpeedY()+speed);
					downKey=true;
				}
				if(key == KeyEvent.VK_A && leftKey==false) {
					tempObject.setSpeedX(tempObject.getSpeedX()-speed);
					leftKey=true;
				}
				if(key == KeyEvent.VK_D && rightKey==false) {
					tempObject.setSpeedX(tempObject.getSpeedX()+speed);
					rightKey=true;
				}
			}
			if(tempObject.getID() == ID.Player2) {
				//key events for player 2
				if(key == KeyEvent.VK_UP && upKey2==false) {
					tempObject.setSpeedY(tempObject.getSpeedY()-speed);
					upKey2=true;
				}
				if(key == KeyEvent.VK_DOWN && downKey2==false) {
					tempObject.setSpeedY(tempObject.getSpeedY()+speed);
					downKey2=true;
				}
				if(key == KeyEvent.VK_LEFT && leftKey2==false) {
					tempObject.setSpeedX(tempObject.getSpeedX()-speed);
					leftKey2=true;
				}
				if(key == KeyEvent.VK_RIGHT && rightKey2==false) {
					tempObject.setSpeedX(tempObject.getSpeedX()+speed);
					rightKey2=true;
				}
			}
			
			if(key == KeyEvent.VK_ESCAPE) System.exit(1);
			if(key == KeyEvent.VK_P) Geam.Pause = 1;
			if(key == KeyEvent.VK_R) Geam.Pause = 2;
		}
	}

	

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < Handler.object.size(); i++){
			GeamObject tempObject = Handler.object.get(i);
			if(tempObject.getID() == ID.Player2) {
				//key events for player 2
				if(key == KeyEvent.VK_LEFT) {
					tempObject.setSpeedX(tempObject.getSpeedX()+speed);
					leftKey2=false;
				}
				if(key == KeyEvent.VK_UP) {
					tempObject.setSpeedY(tempObject.getSpeedY()+speed);
					upKey2=false;
				}
				if(key == KeyEvent.VK_RIGHT) {
					tempObject.setSpeedX(tempObject.getSpeedX()-speed);
					rightKey2=false;
				}
				if(key == KeyEvent.VK_DOWN) {
					tempObject.setSpeedY(tempObject.getSpeedY()-speed);
					downKey2=false;
				}
			}
			
			if(tempObject.getID() == ID.Player) {
				//key events for player
				if(key == KeyEvent.VK_W) {
					tempObject.setSpeedY(tempObject.getSpeedY()+speed);
					upKey=false;
				}
				if(key == KeyEvent.VK_S) {
					tempObject.setSpeedY(tempObject.getSpeedY()-speed);
					downKey=false;
				}
				if(key == KeyEvent.VK_A) {
					tempObject.setSpeedX(tempObject.getSpeedX()+speed);
					leftKey=false;
				}
				if(key == KeyEvent.VK_D) {
					tempObject.setSpeedX(tempObject.getSpeedX()-speed);
					rightKey=false;
				}
			}
		}
	}
}
