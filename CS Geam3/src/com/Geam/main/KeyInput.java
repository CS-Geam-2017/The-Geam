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
		if(!(Geam.secret.contains(""+(char)key))& Geam.lets.contains(""+(char)key)&&(!(Geam.guessedKC.contains(key)))&& HUD.wrongs<6 && !(HUD.win)){
			HUD.wrongs+=1;
		}
		if (!(Geam.guessedKC.contains(key))&& Geam.lets.contains(""+(char)key)&& HUD.wrongs<6 && !(HUD.win)) {
			Geam.guessed += (char)key;
			Geam.guessedKC.add(key);
		}
		if(key == KeyEvent.VK_SPACE && HUD.wrongs>=6 && !(HUD.win)) {
			Geam.secret = Geam.words.get(r.nextInt(Geam.words.size()));
			HUD.wrongs=0;
			Geam.guessed = "";
			Geam.guessedKC.clear();
		}
		if(key == KeyEvent.VK_SPACE && HUD.win) {
			Geam.secret = Geam.words.get(r.nextInt(Geam.words.size()));
			HUD.wrongs=0;
			Geam.guessed = "";
			Geam.guessedKC.clear();
			HUD.win = false;
		}
		
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		released = false;
		
	}

	

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		released = true;
	}
}
