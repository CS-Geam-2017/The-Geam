package com.Geam.main;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class KeyInput extends KeyAdapter {
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
		if (!(Geam.guessedKC.contains(key))&& Geam.lets.contains(""+(char)key)) {
			Geam.guessed += (char)key;
			Geam.guessedKC.add(key);
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		released = false;
		
	}

	

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		released = true;
	}
}
