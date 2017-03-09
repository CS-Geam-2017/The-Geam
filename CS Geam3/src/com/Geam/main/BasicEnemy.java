package com.Geam.main;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GeamObject {
	private Random r;
	private static int Width = 20;
	private static int Height = 20;
	
	public BasicEnemy(int x, int y, ID id) {
		// TODO Auto-generated constructor stub
		super(x, y, id);
		r = new Random();
		
		if (r.nextInt(2) == 1) speedX = 3;
		else speedX = -3;
		if (r.nextInt(2) == 1) speedY = 3;
		else speedY = -3;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,Width,Height);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x+= speedX;
		y+= speedY;
		
		if(y <= 0 || y >= Geam.HEIGHT-Height) speedY *= -1;
		if(x <= 0 || x >= Geam.WIDTH-Width) speedX *= -1;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillOval(x, y, Width, Height);
	}
	
}
