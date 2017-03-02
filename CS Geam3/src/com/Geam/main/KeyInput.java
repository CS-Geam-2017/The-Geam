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
	
	public KeyInput(Handler handler){
		
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < Handler.object.size(); i++){
			
			GeamObject tempObject = Handler.object.get(i);
			if(tempObject.getID() == ID.Player){
				//key events for player 1;
				if(key == KeyEvent.VK_W && upKey==false) {
					tempObject.setSpeedY(tempObject.getSpeedY()-3);
					upKey=true;
				}
				if(key == KeyEvent.VK_S && downKey==false) {
					tempObject.setSpeedY(tempObject.getSpeedY()+3);
					downKey=true;
				}
				if(key == KeyEvent.VK_A && leftKey==false) {
					tempObject.setSpeedX(tempObject.getSpeedX()-3);
					leftKey=true;
				}
				if(key == KeyEvent.VK_D && rightKey==false) {
					tempObject.setSpeedX(tempObject.getSpeedX()+3);
					rightKey=true;
				}
			}
			if(tempObject.getID() == ID.Player2) {
				//key events for player 2
				if(key == KeyEvent.VK_UP && upKey2==false) {
					tempObject.setSpeedY(tempObject.getSpeedY()-5);
					upKey2=true;
				}
				if(key == KeyEvent.VK_DOWN && downKey2==false) {
					tempObject.setSpeedY(tempObject.getSpeedY()+3);
					downKey2=true;
				}
				if(key == KeyEvent.VK_LEFT && leftKey2==false) {
					tempObject.setSpeedX(tempObject.getSpeedX()-3);
					leftKey2=true;
				}
				if(key == KeyEvent.VK_RIGHT && rightKey2==false) {
					tempObject.setSpeedX(tempObject.getSpeedX()+3);
					rightKey2=true;
				}
			}
		}
	}

	

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < Handler.object.size(); i++){
			GeamObject tempObject = Handler.object.get(i);
			if(tempObject.getID() == ID.Player2) {
				//key events for player 2
				if(key == KeyEvent.VK_LEFT) {
					tempObject.setSpeedX(tempObject.getSpeedX()+3);
					leftKey2=false;
				}
				if(key == KeyEvent.VK_UP) {
					tempObject.setSpeedY(tempObject.getSpeedY()+3);
					upKey2=false;
				}
				if(key == KeyEvent.VK_RIGHT) {
					tempObject.setSpeedX(tempObject.getSpeedX()-3);
					rightKey2=false;
				}
				if(key == KeyEvent.VK_DOWN) {
					tempObject.setSpeedY(tempObject.getSpeedY()-3);
					downKey2=false;
				}
			}
			
			if(tempObject.getID() == ID.Player) {
				//key events for player
				if(key == KeyEvent.VK_W) {
					tempObject.setSpeedY(tempObject.getSpeedY()+3);
					upKey=false;
				}
				if(key == KeyEvent.VK_S) {
					tempObject.setSpeedY(tempObject.getSpeedY()-3);
					downKey=false;
				}
				if(key == KeyEvent.VK_A) {
					tempObject.setSpeedX(tempObject.getSpeedX()+3);
					leftKey=false;
				}
				if(key == KeyEvent.VK_D) {
					tempObject.setSpeedX(tempObject.getSpeedX()-3);
					rightKey=false;
				}
			}
		}
	}
}
