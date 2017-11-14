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
			
			
		}
	}

	

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i = 0; i < Handler.object.size(); i++){
			GeamObject tempObject = Handler.object.get(i);
		}
	}
}
