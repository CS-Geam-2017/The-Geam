package com.Geam.main;



import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BasicEnemy extends GeamObject {
	private Random r;
	public BasicEnemy(int x, int y, ID id) {
		// TODO Auto-generated constructor stub
		super(x, y, id);
		System.out.println("working");
		r = new Random();
		
		if (r.nextInt(2) == 1) speedX = 5;
		else speedX = -5;
		if (r.nextInt(2) == 1) speedY = 5;
		else speedY = -5;
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x+= speedX;
		y+= speedY;
		
		if(y <= 0 || y >= Geam.HEIGHT-50) speedY *= -1;
		if(x <= 0 || x >= Geam.WIDTH-50) speedX *= -1;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		int a = r.nextInt(3);
		if (a == 1) g.setColor(Color.red);
		else if (a == 2) g.setColor(Color.blue);
		else g.setColor(Color.green);
		g.fillOval(x, y, 50, 50);
	}
	
}
