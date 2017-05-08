package com.Geam.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlaceHolder extends GeamObject{

	Handler handler;
	int ending = 0;
	
	public PlaceHolder(int x, int y, ID id, Handler handler, int end) {
		super(x, y, id);
		ending = end;
		this.handler = handler;
		
		speedX = 3;
		speedY = 3;
	}

	public void tick() {
	}

	public void render(Graphics g) {
		if (ending == 0) g.setColor(Color.green);
		else g.setColor(Color.blue);
		g.fillRect(x,y,10,10);
		
	}


	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}

}
