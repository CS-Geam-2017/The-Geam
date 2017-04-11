package com.Geam.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Shooter extends GeamObject{

	Handler handler;
	
	public static float speedX = 0;
	public static float speedY = 0;
	public static boolean xy = false;
	public Shooter(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
	}

	public void tick() {
		for(int i = 0; i < Handler.object.size(); i++){
			GeamObject tempObject = Handler.object.get(i);
			if (tempObject.getID() == ID.Player){
				int goX = tempObject.getX()+Player.Width2/2;
				int goY = tempObject.getY()+Player.Height2/2;
				boolean speedOWF = false;
				float speedDiv = 1;
				
				speedX = goX - x;
				speedY = goY - y;
				
				if ((speedX < 300 && speedX > -300) && (speedY < 300 && speedY > -300)){
					while (speedOWF == false){
						if (speedX / speedDiv > 5 || speedY / speedDiv > 5 || speedX / speedDiv < -5 || speedY / speedDiv < -5){
							speedDiv += 1;
						}
						else{
							speedOWF = true;
						}
					}
					speedX /= speedDiv;
					speedY /= speedDiv;
					xy = true;
					//handler.addObject(new Projectile(x, y, speedX, speedY, ID.Projectile, handler));
				}
				else {
					xy = false;
				}
			}
				
				x = Geam.clamp(x, 0, Geam.WIDTH-16);
				y = Geam.clamp(y, 0, Geam.HEIGHT-16);
			}
		}	


	public void render(Graphics g) {
		g.setColor(Color.PINK);
		g.fillOval(x, y, 16, 16);
		
	}


	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}

}
