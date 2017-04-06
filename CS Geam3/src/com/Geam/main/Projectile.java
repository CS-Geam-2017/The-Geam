package com.Geam.main;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JFrame;

public class Projectile extends GeamObject {
	Handler handler;
	private static int Width = 20;
	private static int Height = 20;
	JFrame frame = new JFrame();
	private float speedXPro;
	private float speedYPro;
	
	public Projectile(int x, int y, float speedX, float speedY, ID id, Handler handler) {
		// TODO Auto-generated constructor stub
		super(x, y, id);
		
		this.handler = handler;
		
		new Random();
		
		speedXPro = speedX;
		speedYPro = speedY;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,Width,Height);
	}

	public void tick() {
		// TODO Auto-generated method stub
		System.out.println(speedYPro);
		x+= speedXPro;
		y+= speedYPro;
		
		if(y <= 0 || y >= Geam.HEIGHT-Height) handler.removeObject(this);
		if(x <= 0 || x >= Geam.WIDTH-Width) handler.removeObject(this);
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.black);
		g.fillOval(x, y, Width, Height);
	}
	
}
