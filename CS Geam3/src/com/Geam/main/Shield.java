package com.Geam.main;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Shield extends GeamObject {
	Handler handler;
	private static int Width = 50;
	private static int Height = 50;
	public static boolean pickedUp = false;
	
	public Shield(int x, int y, ID id, Handler handler) {
		// TODO Auto-generated constructor stub
		super(x, y, id);
		
		this.handler = handler;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,Width,Height);
	}

	public void tick() {
		// TODO Auto-generated method stub
		collision();
	}

	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.green);
		g.fillRect(x, y, Width, Height);
	}
	
	private void collision(){
		for(int i = 0; i < Handler.object.size(); i++){
			
			GeamObject tempObject = Handler.object.get(i);
			
			if(tempObject.getID() == ID.Player && pickedUp == false){
				if(getBounds().intersects(tempObject.getBounds())){
					pickedUp = true;
				}
			}
		}
	}
}
