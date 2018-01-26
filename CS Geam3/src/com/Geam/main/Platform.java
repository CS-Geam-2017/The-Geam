package com.Geam.main;

import java.awt.Color;
import java.awt.Graphics;

public class Platform extends GeamObject{
	public static int Height = 10;
	public static int Width = 70;

	public Platform(int x, int y, ID id) {
		super(x, y, id);
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x,  y, Width, Height);
		g.setColor(Color.RED);
		g.drawLine(x+10, y, x+Width-10, y);
	}

}
