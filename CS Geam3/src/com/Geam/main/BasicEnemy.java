package com.Geam.main;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GeamObject {
	private Random r;
	Handler handler;
	private static int Width = 20;
	private static int Height = 20;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		// TODO Auto-generated constructor stub
		super(x, y, id);
		
		this.handler = handler;
		
		r = new Random();
		
		if (r.nextInt(2) == 1) speedX = 3;
		else speedX = -3;
		if (r.nextInt(2) == 1) speedY = 3;
		else speedY = -3;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,Width,Height);
	}

	public void tick() {
		// TODO Auto-generated method stub
		x+= speedX;
		y+= speedY;
		
		x = Geam.clamp(x, 0, Geam.WIDTH-20);
		y = Geam.clamp(y, 0, Geam.HEIGHT-20);
		
		if(y <= 0 || y >= Geam.HEIGHT-Height) speedY *= -1;
		if(x <= 0 || x >= Geam.WIDTH-Width) speedX *= -1;
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillOval(x, y, Width, Height);
	}
	
}
