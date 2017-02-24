package com.Geam.main;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import javax.imageio.ImageIO;

public class Player extends GeamObject {
	public static int Height = 32;
	public static int Width = 64;
	public static int Height2 = 100;
	public static int Width2 = 42;
	public static Image img = null;
	boolean moved = false;
	public static int walkAn = 0;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		
		speedX = 0;
		speedY = 0;
	}

	
	
	public void tick(){
		x += speedX;
		y += speedY;
		
	}
	
	public void render(Graphics g) {
		if(id == ID.Player){
			if (moved==false) {
				try {
					img = ImageIO.read(new File("PersonWalkR0.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (KeyInput.upKey==true) {
				try {
					img = ImageIO.read(new File("PersonWalkU.png"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				moved=true;
			}
			if (KeyInput.downKey==true) {
				try {
					img = ImageIO.read(new File("PersonWalkD.png"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				moved=true;
			}
			if (KeyInput.leftKey==true) {
				try {
					img = ImageIO.read(new File("PersonWalkL.png"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				moved=true;
			}
			if (KeyInput.rightKey==true) {
				try {
					img = ImageIO.read(new File("PersonWalkR"+walkAn+".png"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				if(walkAn==3) Height2 = 98;
				if(walkAn==4){
					Width2 = 48;
					Height2 = 100;
				}
				if(walkAn==5) {
					Width2 = 52;
					Height2 = 98;
				}
				else {
					Width2 = 42;
					Height2 = 98;
				}
				moved=true;
			}
			g.drawImage(img , x, y, Width2, Height2, null);
		}
		if (id == ID.Player2){
			g.setColor(Color.blue); 
			g.fillOval(x,  y, Width, Height);
		}
	}
		
}
