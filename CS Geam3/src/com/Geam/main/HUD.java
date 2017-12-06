package com.Geam.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	public static int HEALTH = 100;
	static boolean hp = true;
	public static int wrongs =0;
	public static boolean win = false;
	public static void tick() {
		
	}
	
	public static void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		int a = 0;
		int b = 1;
		g.drawString("Guessed Letters", 1050, 120);
		for(int i = 0; i<Geam.guessed.length(); i++) {
			a+=1;
			if(a>3) { 
				a=1;
				b+=1;
			}
			g.drawString(""+Geam.guessed.charAt(i), (a*35)+1100, (b*37)+150);
		}
		String display = "";
		int wincond = 0;
		for(int i = 0; i<Geam.secret.length();i++) {
			if(' ' == Geam.secret.charAt(i)) {
				display+=" ";
				wincond++;
			}
			else if(Geam.guessed.contains(""+Geam.secret.charAt(i))){
				display+=Geam.secret.charAt(i);
				wincond++;
			}
			else {
				display+="_";
			}
			display += " ";
		}
		if(wincond==Geam.secret.length()) {
			win = true;
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
			g.drawString("You Win!", 300, 480);
			g.drawString("Space to play again", 300, 530);
		}
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		g.drawString(display, 410, 350);
		if(wrongs<=6) {
			for(int i = 0; i<wrongs; i++) {
				g.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
				if (i == 0) g.drawString("O", 200, 300);
				if (i == 1) g.drawString("|", 210, 345);
				if (i == 2) g.drawString("\\", 214, 340);
				if (i == 3) g.drawString("/", 197, 342);
				if (i == 4) g.drawString("\\", 214, 390);
				if (i == 5) {
					g.drawString("/", 197, 392);
					g.drawString("Game Over", 300, 480);
					g.drawString("The word was "+Geam.secret, 300, 530);
					g.drawString("Space to restart", 300, 580);
					g.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
					g.drawString("X", 216, 280);
					g.drawString("X", 223, 280);
					
				}
			}
		}
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
		g.drawString("___", 180, 400);
		g.drawString("|", 280, 400);
		g.drawString("|", 280, 350);
		g.drawString("|", 280, 300);
		g.drawString("|", 280, 270);
		g.drawString("__", 214, 214);
		g.drawString("l", 216, 265);
	}
}
