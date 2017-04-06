package com.Geam.main;

import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;

public class Player extends GeamObject {
	
	Handler handler;
	
	public static int Height2 = 100;
	public static int Width2 = 42;
	public static Image img = null;
	boolean moved = false;
	public static int walkAn = 0;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		speedX = 0;
		speedY = 0;
	}

	public Rectangle getBounds(){
		return new Rectangle(x,y,50,100);
	}
	public Rectangle getBoundsLeft(){
		return new Rectangle(x,y+10,1,80);
	}
	public Rectangle getBoundsRight(){
		return new Rectangle(x+Width2,y+10,1,80);
	}
	public Rectangle getBoundsTop(){
		return new Rectangle(x+5,y,30,1);
	}
	public Rectangle getBoundsBottom(){
		return new Rectangle(x+5,y+Height2,30,1);
	}
	 
	
	public void tick(){
		x += speedX;
		y += speedY;
		
		x = Geam.clamp(x, 0, Geam.WIDTH-Width2);
		y = Geam.clamp(y, 0, Geam.HEIGHT-Height2);
		
		collision();
	}
	
	private void collision() {
		for(int i = 0; i < Handler.object.size(); i++){
			
			GeamObject tempObject = Handler.object.get(i);
			
			if(tempObject.getID() == ID.BasicEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH -= 2;
				}
			}
			if(tempObject.getID() == ID.Player2) {
				
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					tempObject.setX(x - Player2.Width);
				}
				if(getBoundsRight().intersects(tempObject.getBounds())){
					tempObject.setX(x+Width2);
				}
				if(getBoundsTop().intersects(tempObject.getBounds())){
					tempObject.setY(y-Player2.Height);
				}
				if(getBoundsBottom().intersects(tempObject.getBounds())){
					tempObject.setY(y+Height2);
				}
			}
			if(tempObject.getID() == ID.RangeEn){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH += 2;
				}
			}
		}
	}
		

	public void render(Graphics g) {
			if (moved==false) {
				try {
					img = ImageIO.read(new File("PersonWalkR0.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Width2 = 42;
			Height2 = 100;
			if (KeyInput.upKey==true && HUD.HEALTH!=0) {
				try {
					img = ImageIO.read(new File("PersonWalkU.png"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				moved=true;
			}
			if (KeyInput.downKey==true && HUD.HEALTH!=0) {
				try {
					img = ImageIO.read(new File("PersonWalkD.png"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				moved=true;
			}
			if (KeyInput.leftKey==true && HUD.HEALTH!=0) {
				try {
					img = ImageIO.read(new File("PersonWalkL.png"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				moved=true;
			}
			if (KeyInput.rightKey==true && HUD.HEALTH!=0) {
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
					Height2 = 100;
				}
				moved=true;
			}
			if (KeyInput.rightKey!=true && KeyInput.leftKey!=true && KeyInput.upKey!=true && KeyInput.downKey!=true) {
				try {
					img = ImageIO.read(new File("PersonWalkR0.png"));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			g.drawImage(img , x, y, Width2, Height2, null);
	}
		
}
