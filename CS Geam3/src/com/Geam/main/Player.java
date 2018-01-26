package com.Geam.main;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import javax.imageio.ImageIO;

public class Player extends GeamObject {
	public static int Height = 30;
	public static int Width = 20;
	boolean moved = false;
	public static int GRAV = 5;
	public static int TERM = 20;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		
		speedX = 0;
		speedY = 0;
	}

	
	
	public void tick(){
		
		x += speedX;
		y += speedY;
		if(speedY<TERM) {
			speedY+=GRAV;
		}
		x = Geam.clamp(x, 0, Geam.WIDTH-34);
		y = Geam.clamp(y, 0, Geam.HEIGHT-75);
		
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue); 
		g.fillRect(x,  y, Width, Height);
	}
		
}
