package com.Geam.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player2 extends GeamObject {
	
	public static int Height = 32;
	public static int Width = 64;
	Handler handler;
	
	public Player2(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		speedX = 0;
		speedY = 0;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 64, 32);
		}

	@Override
	public void tick() {
		x += speedX;
		y += speedY;
		
		x = Geam.clamp(x, 0, Geam.WIDTH-64);
		y = Geam.clamp(y, 0, Geam.HEIGHT-32);
		
		collision();
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,64,32);
	}
	public Rectangle getBoundsLeft(){
		return new Rectangle(x,y+1,1,30);
	}
	public Rectangle getBoundsRight(){
		return new Rectangle(x+Width,y+1,1,30);
	}
	public Rectangle getBoundsTop(){
		return new Rectangle(x+10,y,35,1);
	}
	public Rectangle getBoundsBottom(){
		return new Rectangle(x+10,y+Height,35,1);
	}
	
	private void collision() {
		for(int i = 0; i < Handler.object.size(); i++){
			
			GeamObject tempObject = Handler.object.get(i);
			
			if(tempObject.getID() == ID.BasicEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH += 2;
				}
			}
			if(tempObject.getID() == ID.Tracker){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH -= 2;
				}
			}
			if(tempObject.getID() == ID.Player){
				if(getBoundsLeft().intersects(tempObject.getBounds())){
					tempObject.setX(x-Width+19);
				}
				if(getBoundsRight().intersects(tempObject.getBounds())){
					tempObject.setX(x + Width);
					System.out.println(getBoundsBottom().intersects(tempObject.getBounds()));
					System.out.println(getBoundsTop().intersects(tempObject.getBounds()));

				}
				if(getBoundsTop().intersects(tempObject.getBounds())){
					tempObject.setY(y+Player.Height2);
				}
				if(getBoundsBottom().intersects(tempObject.getBounds())){
					tempObject.setY(y+Height);
				}
			}
		}
		
		
	}
}

