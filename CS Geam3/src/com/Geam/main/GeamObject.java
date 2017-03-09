package com.Geam.main;


import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @author S59587
 *
 */
public abstract class GeamObject {

	protected int x, y;
	protected ID id;
	protected int speedX, speedY;
	
	public GeamObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setId(ID id){
		this.id = id;
	}
	public ID getID(){
		return id;
	}
	public void setSpeedX(int speedX){
		this.speedX = speedX;
	}
	public void setSpeedY(int speedY){
		this.speedY = speedY;
	}
	public int getSpeedX(){
		return speedX;
	}
	public int getSpeedY(){
		return speedY;
	}
	
}
