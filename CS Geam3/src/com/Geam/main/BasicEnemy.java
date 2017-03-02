package com.Geam.main;



import java.awt.Color;
import java.awt.Graphics;

public class BasicEnemy extends GeamObject {

	public BasicEnemy(int x, int y, ID id) {
		// TODO Auto-generated constructor stub
		super(x, y, id);
		
		speedX = 5;
		speedY = 5;
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x+= speedX;
		y+= speedY;
		
		if(y <= 0 || y >= Geam.HEIGHT-35) speedY *= -1;
		if(x <= 0 || x >= Geam.WIDTH) speedX *= -1;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillOval(x, y, 50, 50);
	}
	
}
