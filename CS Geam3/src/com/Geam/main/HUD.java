package com.Geam.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	public static int HEALTH = 100;
	static boolean hp = true;
	public static void tick() {
		
		//if (HEALTH == 0) hp = false;
		//else if (HEALTH == 100) hp = true;
		//if (hp == true) HEALTH--;
		//else HEALTH++;
		
		HEALTH = Geam.clamp(HEALTH, 0, 100);
	}
	
	public static void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(15,15, 200, 32);
		g.setColor(Color.green);
		g.fillRect(15,15, HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		g.drawString(""+Geam.score, 1100, 50);
	}
}
