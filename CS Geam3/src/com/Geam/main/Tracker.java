package com.Geam.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tracker extends GeamObject{

	Handler handler;
	public static Image img = null;
	
	public Tracker(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		speedX = 3;
		speedY = 3;
	}

	public void tick() {
		for(int i = 0; i < Handler.object.size(); i++){
			GeamObject tempObject = Handler.object.get(i);
			if (tempObject.getID() == ID.Player2){
				int goX = tempObject.getX()+25;
				int goY = tempObject.getY()+8;
				speedX = goX - x;
				speedY = goY - y;
				
				if (speedX<-3) speedX = -3;
				if (speedX>3) speedX = 3;
				
				if (speedY<-3) speedY = -3;
				if (speedY>3) speedY = 3;
				
				x += speedX;
				y += speedY;
				
				x = Geam.clamp(x, 0, Geam.WIDTH-16);
				y = Geam.clamp(y, 0, Geam.HEIGHT-16);
			}
		}	
	}

	public void render(Graphics g) {
		try {
			img = ImageIO.read(new File("New Piskel clone.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(img, x, y, 40, 40, null);
		
	}


	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}

}
