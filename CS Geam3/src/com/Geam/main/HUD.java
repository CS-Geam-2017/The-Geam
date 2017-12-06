package com.Geam.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	public static int HEALTH = 100;
	static boolean hp = true;
	public static int wrongs =0;
	public static void tick() {
		
	}
	
	public static void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		int a = 0;
		int b = 1;
		for(int i = 0; i<Geam.guessed.length(); i++) {
			a+=1;
			if(a>3) { 
				a=1;
				b+=1;
			}
			g.drawString(""+Geam.guessed.charAt(i), (a*20)+1000, (b*22)+250);
		}
		g.drawString(""+wrongs, 1000, 100);
		String display = "";
		for(int i = 0; i<Geam.secret.length();i++) {
			if(' ' == Geam.secret.charAt(i)) {
				display+=" ";
			}
			else if(Geam.guessed.contains(""+Geam.secret.charAt(i))){
				display+=Geam.secret.charAt(i);
			}
			else {
				display+="_";
			}
		}
		g.drawString(display, 500, 300);
		if(wrongs<=6) {
			for(int i = 0; i<wrongs; i++) {
				g.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
				if (i == 0) g.drawString("O", 100, 100);
				if (i == 1) g.drawString("|", 106, 130);
				if (i == 2) g.drawString("\\", 110, 130);
				if (i == 3) g.drawString("/", 96, 130);
				if (i == 4) g.drawString("\\", 110, 162);
				if (i == 5) {
					g.drawString("/", 96, 162);
					g.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
					g.drawString("X", 108, 90);
					g.drawString("X", 118, 90);
				}
			}
		}
	}
}
