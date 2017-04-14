package com.Geam.main;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Shield extends GeamObject {
	Handler handler;
	private static int Width = 50;
	private static int Height = 50;
	public static boolean pickedUp = false;
	private static boolean hitting = false;
	
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
		if (pickedUp==false){
			g.setColor(Color.green);
			Height = 50;
			Width = 50;
			g.fillRect(x, y, Width, Height);
		}
		if (pickedUp == true){
			g.setColor(Color.blue);
			if (Player.imgK == 4){
				x = Handler.object.get(0).getX()+50;
				y = Handler.object.get(0).getY()-5;
				Width = 5;
				Height = 110;
			}
			else if (Player.imgK == 3){
				x = Handler.object.get(0).getX()-8;
				y = Handler.object.get(0).getY()-5;
				Width = 5;
				Height = 110;
			}
			else if (Player.imgK == 2){
				x = Handler.object.get(0).getX()-5;
				y = Handler.object.get(0).getY()+108;
				Width = 52;
				Height = 5;
			}
			else if (Player.imgK == 1){
				x = Handler.object.get(0).getX()-5;
				y = Handler.object.get(0).getY()-8;
				Width = 52;
				Height = 5;
			}
			g.fillRect(x, y, Width, Height);
		}
	}
	
	private void collision(){
		for(int i = 0; i < Handler.object.size(); i++){
			
			GeamObject tempObject = Handler.object.get(i);
			
			if(tempObject.getID() == ID.Player && pickedUp == false){
				if(getBounds().intersects(tempObject.getBounds())){
					pickedUp = true;
				}
			}
			if(tempObject.getID() == ID.BasicEnemy && pickedUp==true){
				if(getBounds().intersects(tempObject.getBounds())){
					if (Player.imgK == 3 || Player.imgK==4){
						tempObject.setSpeedX(tempObject.getSpeedX()*-1);
					}
					if (Player.imgK == 1 || Player.imgK==2){
						tempObject.setSpeedY(tempObject.getSpeedY()*-1);
					}
				}
			}
		}
	}
}
