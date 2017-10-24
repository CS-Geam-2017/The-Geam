package com.Geam.main;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shield extends GeamObject {
	Handler handler;
	private static int Width = 50;
	private static int Height = 50;
	public static Image img = null;
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
		collision();
	}

	public void render(Graphics g) {
		
		if (pickedUp==false){
			try {
				img = ImageIO.read(new File("ShieldIcon.png"));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			Width = 50;
			Height = 50;
			g.drawImage(img , x, y, Width, Height, null);
		}
		if (pickedUp == true){
			//Defines the shape and position of the shield
			g.setColor(Color.blue);
			if (Player.imgK == 4){
				x = Handler.object.get(0).getX()+47;
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
					Geam.PowerT = System.currentTimeMillis();
					pickedUp = true;
				}
			}
			if(tempObject.getID() == ID.BasicEnemy && pickedUp==true){
				//Sets position of the ball after intersection
				if(getBounds().intersects(tempObject.getBounds())){
					if (Player.imgK == 3 || Player.imgK==4){
						if (Player.imgK == 3){
							tempObject.setSpeedX(-3);
							tempObject.setX(x-21);
						}
						else {
							tempObject.setSpeedX(3);
							tempObject.setX(x+5);
						}
					}
					if (Player.imgK == 1 || Player.imgK==2){
						if (Player.imgK == 1){
							tempObject.setSpeedY(-3);
							tempObject.setY(y-21);
						}
						else {
							tempObject.setSpeedY(3);
							tempObject.setY(y+7);
						}
					}
				}
			}
		}
	}
}
