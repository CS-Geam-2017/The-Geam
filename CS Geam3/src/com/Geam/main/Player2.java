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
		
		this.handler = handler;
		
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
		}
		
	}
}

