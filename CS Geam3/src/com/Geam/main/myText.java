package com.Geam.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class myText extends GeamObject {
	public myText(int x, int y, ID id, String text) {
		super(x, y, id, text);
		
	}

	public void tick(){
		
	}
	
	public void render(Graphics g) {
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		g.setColor(Color.BLACK);
		System.out.println(text.length());
		if(text.length()>83) {
			g.drawString(text.substring(0, Sp), x, y);
			g.drawString(text.substring(Sp+1), x, y+50);
		}
		else if(text.length()>166){
			g.drawString(text.substring(0, Sp), x, y);
			g.drawString(text.substring(Sp+1,Sp2), x, y+50);
			g.drawString(text.substring(Sp2+1), x, y+100);
		}
		else {
			g.drawString(text, x, y);
		}
		
	}
		
}
