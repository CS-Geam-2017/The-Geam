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
	}
}
