package com.Geam.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	public static boolean leftKey = false;
	public static boolean upKey = false;
	public static boolean rightKey = false;
	public static boolean downKey = false;
	public static boolean released = true;
	protected int speedX;
	protected int speedY;
	public static boolean ground = false;
	
	public KeyInput(Handler handler){
		
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < Handler.object.size(); i++){
			
			GeamObject tempObject = Handler.object.get(i);
			
			if(tempObject.getID() == ID.Player){
				//key events for player 1;
				if(key == KeyEvent.VK_W && upKey==false&&ground==true) {
					tempObject.setSpeedY(tempObject.getSpeedY()-70);
					ground = false;
					upKey=true;
				}
				if(key == KeyEvent.VK_S && downKey==false) {
					tempObject.setSpeedY(tempObject.getSpeedY());
					downKey=true;
				}
				if(key == KeyEvent.VK_A && leftKey==false) {
					tempObject.setSpeedX(tempObject.getspeedX()-10);
					leftKey=true;
				}
				if(key == KeyEvent.VK_D && rightKey==false) {
					tempObject.setSpeedX(tempObject.getspeedX()+10);
					rightKey=true;
				}
			}
		}
	}

	

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < Handler.object.size(); i++){
			GeamObject tempObject = Handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				//key events for player
				if(key == KeyEvent.VK_W) {
					tempObject.setSpeedY(tempObject.getSpeedY());
					upKey=false;
				}
				if(key == KeyEvent.VK_S) {
					tempObject.setSpeedY(tempObject.getSpeedY());
					downKey=false;
				}
				if(key == KeyEvent.VK_A) {
					tempObject.setSpeedX(tempObject.getspeedX()+10);
					leftKey=false;
				}
				if(key == KeyEvent.VK_D) {
					tempObject.setSpeedX(tempObject.getspeedX()-10);
					rightKey=false;
				}
			}
		}
	}
}
