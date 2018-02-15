package com.Geam.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	public static int Question = 1;
	public static boolean right = false;
	private Handler handler;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		GeamObject tempObject;
		for(int i = 0; i<handler.object.size();i++) {
			tempObject = handler.object.get(i);
			if(tempObject.id==ID.R) {
				if(Question == 1) {
					if(key == KeyEvent.VK_B) {
						tempObject.setText("(CORRECT) Hamlet leaves while in aw that your mother would marry such a man");
						right = true;
					}
					else if(key == KeyEvent.VK_A) {
						tempObject.setText("(INCORRECT) As you dive for Claudius the guards apprehend you and you are executed a dawn");
					}
					else if(key == KeyEvent.VK_C) {
						tempObject.setText("(INCORRECT) Hamlet thought that it just wasn’t worth it and live a very successful life");
					}
					else if(key == KeyEvent.VK_D) {
						tempObject.setText("(INCORRECT) You object to the marriage and demands the throne become his for this Hamlet has committed treason.");
					}
				}
				if(Question == 2) {
					if(key == KeyEvent.VK_A) {
						tempObject.setText("(INCORRECT) HAMLET JUST SAW A GHOST THIS ISN’T POSSIBLE!");
						right = true;
					}
					else if(key == KeyEvent.VK_B) {
						tempObject.setText("(INCORRECT) As Hamlet starts to yell I I’M NOT AFRAID OF NO GHOST Hamlet pulls out a proton pack and starts to zap the ghost . . . it doesn’t work because this is Shakespeare and not Ivan Reitman");
					}
					else if(key == KeyEvent.VK_C) {
						tempObject.setText("(CORRECT) Hamlet agrees to avenge the ghost even though he isn’t sure if it is real or not");
					}
					else if(key == KeyEvent.VK_D) {
						tempObject.setText("(INCORRECT) As soon as the ghost comes in front of Hamlet, he just say NOPE and run the other direction");
					}
				}
				if(right&& key==KeyEvent.VK_ENTER) {
					Question++;
					right=false;
					tempObject.setText("");
				}
			}
		}
		
		
	}

	

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
	}
}
