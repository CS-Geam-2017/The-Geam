package com.Geam.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class RangeEn extends GeamObject{

	Handler handler;
	
	public RangeEn(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		speedX = 0;
		speedY = 0;
	}

	public void tick() {
		for(int i = 0; i < Handler.object.size(); i++){
			GeamObject tempObject = Handler.object.get(i);
			if (tempObject.getID() == ID.Player){
				int goX = tempObject.getX()+Player.Width2/2;
				int goY = tempObject.getY()+Player.Height2/2;
				speedX = goX - x;
				speedY = goY - y;
				
				if ((speedX < 300 && speedX > -100) && (speedY < 300 && speedY > -100)){
					if (speedX<-3) speedX = -3;
					if (speedX>3) speedX = 3;
				
					if (speedY<-3) speedY = -3;
					if (speedY>3) speedY = 3;
				
					x += speedX;
					y += speedY;
				}
				
				x = Geam.clamp(x, 0, Geam.WIDTH-16);
				y = Geam.clamp(y, 0, Geam.HEIGHT-16);
			}
		}	
	}

	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillOval(x, y, 16, 16);
		
	}


	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}

}
