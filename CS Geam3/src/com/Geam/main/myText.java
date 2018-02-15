package com.Geam.main;
import java.awt.Color;
import java.awt.Graphics;

public class myText extends GeamObject {
	public myText(int x, int y, ID id, String text, int[] quests) {
		super(x, y, id, text);
		
	}

	public void tick(){
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString(text, x, y);
	}
		
}
