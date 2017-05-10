package com.Geam.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Tracker extends GeamObject{

	Handler handler;
	public boolean check = true;
	public int[][] openSet = null;
	public int x1 = 0;
	public int y1 = 0;
	public static int goX = 0;
	public static int goY = 0;
	int f = 100;
	
	
	public Tracker(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		speedX = 3;
		speedY = 3;
	}

	public void tick() {
		for(int i = 0; i < Handler.object.size(); i++){
			GeamObject tempObject = Handler.object.get(i);
			if (tempObject.getID() == ID.Player){
				goX = tempObject.getX()+25;
				goY = tempObject.getY()+8;
				//speedX = goX - x;
				//speedY = goY - y;
				
				//if (speedX<-3) speedX = -3;
				//if (speedX>3) speedX = 3;
				
				//if (speedY<-3) speedY = -3;
				//if (speedY>3) speedY = 3;
				
				//x += speedX;
				//y += speedY;
				
				//x = Geam.clamp(x, 0, Geam.WIDTH-16);
				//y = Geam.clamp(y, 0, Geam.HEIGHT-16);
			//}
		//}
		}
		}
		if (Geam.done == true && Geam.start == true){
				f--;
				//if (Geam.followSet[f][0]!=0 || Geam.followSet[f][1]!=Geam.HEIGHT ){
					//x = Geam.openSet[Geam.lowestF][0];
					//y = Geam.openSet[Geam.lowestF][1];
				//}
		}
		collision();
	}
	private void collision() {
		for(int i = 0; i < Handler.object.size(); i++){
			
			GeamObject tempObject = Handler.object.get(i);
			if (tempObject.getID() == ID.Wall){
				if (tempObject.getBounds().intersects(getBounds())){
					
				}
			}
		}
	}

	public void render(Graphics g) {
		//g.setColor(Color.ORANGE);
		//g.fillOval(Geam.openSet[Geam.lowestF][0], Geam.openSet[Geam.lowestF][1], 16, 16);
		
	}


	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}

}
